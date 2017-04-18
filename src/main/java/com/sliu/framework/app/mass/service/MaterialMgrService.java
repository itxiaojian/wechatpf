package com.sliu.framework.app.mass.service;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.company.dao.CompanyDao;
import com.sliu.framework.app.mass.dao.AttachMentDao;
import com.sliu.framework.app.mass.dao.MaterialMgrDao;
import com.sliu.framework.app.model.Attachment;
import com.sliu.framework.app.model.WxSucaiInfo;
import com.sliu.framework.app.model.WxSucaiMpnews;
import com.sliu.framework.app.support.file.FileCommonOperate;
import com.sliu.framework.app.support.random.RandomUtil;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.util.SecurityContextUtil;
import com.sliu.framework.app.wxtuwen.dao.WxSucaiMpnewsDao;

/**
 * 素材管理 Service
 * @author zhangyi
 *
 */
@Service
public class MaterialMgrService {
	
	@Value("${file.store.path}")
	private String filePath;
	
	@Autowired
	private MaterialMgrDao materialMgrDao;
	
	@Autowired
	private AttachMentDao attachMentDao;
	
	@Autowired
	private WxSucaiMpnewsDao wxSucaiMpnewsDao;
	
	@Autowired
	private MassInfoService massInfoService;
	
	//公司微信平台调用dao
	@Autowired
    private CompanyDao companyDao;
	
	
	/**
	 * 获取素材列表
	 * @param start
	 * @param limit
	 * @param scmc 素材名称
	 * @param sclx  素材类型
	 * @return
	 */
	public Pagination<Map<String,Object>> getSuCaiPagination(Integer start,Integer limit, String scmc,String sclx) {
		return materialMgrDao.getSuCaiPagination(start, limit, scmc,sclx);
	}
	
	
	/**
	 *获得图文消息详细信息  
	 * @param start
	 * @param limit
	 * @param scid  素材ID
	 * @return
	 */
	public Pagination<Map<String,Object>> getTuWenInfos(Integer start,Integer limit, String scid) {
		return materialMgrDao.getTuWenInfos(start, limit, scid);
	}
	
	
	/**
	 * 上传图片素材
	 */
	@Transactional					
	public void UploadImageMaterial(MultipartFile attachMentFile){
		//获得session用户
		SysYh user=SecurityContextUtil.getCurrentUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = sdf.format(new Date());		//当前日期格式化到秒
		String random = RandomUtil.generateWord();	//获得随机数
		String filename = "";       //文件名
		String fileNameSuffix ="";  //文件后缀名
		String result = "";
		String fileNameU = "";
		String mediaId = "";
		String path = "";
		InputStream inputStream = null;
		
		try {
			//FileInputStream fileInputStream = new FileInputStream(attachMentFile.toString());
			 
	            if(attachMentFile.isEmpty()){  
	                System.out.println("文件未上传");  
	                throw new RuntimeException("文件未上传");
	            }else{  
	                System.out.println("文件长度: " + attachMentFile.getSize());  
	                System.out.println("文件类型: " + attachMentFile.getContentType());  
	                System.out.println("文件名称: " + attachMentFile.getName());  
	                System.out.println("文件原名: " + attachMentFile.getOriginalFilename()); 
	                filename = attachMentFile.getOriginalFilename();
	                fileNameSuffix = filename.substring( filename.lastIndexOf('.')+1 );
	                System.out.println("文件后缀名："+fileNameSuffix);
	            }  
	            //文件输入流
	            inputStream = attachMentFile.getInputStream();
	            //拼装上传路径
	            path = filePath+ File.separator + "images";
	            //文件名称 （日期+随机数+原文件后缀名）
	            fileNameU = date+random+"."+fileNameSuffix;
	            
	            System.out.println("文件保存路径："+path);
	            System.out.println("上传后的文件名："+fileNameU);
	            System.out.println("========================================");
	            //上传到本地服务器
	            String temp = new FileCommonOperate().uploadFile(inputStream, path, fileNameU.trim());
	            System.out.println(temp);
	            System.out.println("文件保存的绝对路径："+path+File.separator+fileNameU);
	            //上传到微信服务器
	            result = WeixinUtils.uploadMedia("image", path+File.separator+fileNameU,attachMentFile);
	            System.out.println("微信服务器返回结果："+result);
	            if(!result.isEmpty()){
	            	
	            	if(result.startsWith("error|")){
		                throw new RuntimeException("图片上传到微信服务器失败");
	            	}else{
	            		//保存到数据库
	            		mediaId = result;
	            		WxSucaiInfo sucai = new WxSucaiInfo();    //素材对象
	            		Attachment attachment = new Attachment(); //附件对象
	            		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	            		String newdate = df.format(new Date());
	            		sucai.setScName(fileNameU);
	            		sucai.setScType(1);     //图片
	            		sucai.setMediaId(mediaId);
	            		sucai.setRemark(path);
	            		sucai.setCreateUser(user.getYhbh());
	            		sucai.setCreateTime(df.parse(newdate));
	            		//保存附件表
	            		attachment.setFileName(attachMentFile.getOriginalFilename());  //文件原名
	            		attachment.setFileCode(fileNameU);                             //上传后文件名
	            		attachment.setDescn(path);
	            		attachment.setDelFlag(0);
	            		attachment.setCreateTime(df.parse(newdate));
	            		attachment.setCreateUser(user.getYhbh());
	            		attachment.setFileSize(attachMentFile.getSize());
	            		attachment.setFileType("image");
	            		Long attachmentId = attachMentDao.save(attachment);
	            		sucai.setAttachmentId(attachmentId);
						materialMgrDao.save(sucai);
						
						
	            	}
	            	
	            }else{
	            	System.out.println("图片上传到微信服务器失败");  
	                throw new RuntimeException("图片上传到微信服务器失败");
	            }
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 上传语音素材
	 * @param attachMentFile  语音文件
	 */
	@Transactional
	public void UploadVoiceMaterial(MultipartFile attachMentFile){
		//获得session用户
		SysYh user=SecurityContextUtil.getCurrentUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = sdf.format(new Date());		//当前日期格式化到秒
		String random = RandomUtil.generateWord();	//获得随机数
		String filename = "";       //文件名
		String fileNameSuffix ="";  //文件后缀名
		String result = "";
		String fileNameU = "";
		String mediaId = "";
		String path = "";
		InputStream inputStream = null;
		try {
	            if(attachMentFile.isEmpty()){  
	                System.out.println("文件未上传");  
	                throw new RuntimeException("文件未上传");
	            }else{  
	                filename = attachMentFile.getOriginalFilename();
	                fileNameSuffix = filename.substring( filename.lastIndexOf('.')+1 );
	            }  
	            //文件输入流
	            inputStream = attachMentFile.getInputStream();
	            //拼装上传路径
	            path = filePath+ File.separator + "voice";
	            //文件名称 （日期+随机数+原文件后缀名）
	            fileNameU = date+random+"."+fileNameSuffix;
	            //上传到本地服务器
	            new FileCommonOperate().uploadFile(inputStream, path, fileNameU.trim());
	            //上传到微信服务器
	            result = WeixinUtils.uploadMedia("voice", path+File.separator+fileNameU,attachMentFile);
	            System.out.println("微信返回结果："+result);
	            if(!result.isEmpty()){
	            	if(result.startsWith("error|")){
		                throw new RuntimeException("语音上传到微信服务器失败");
	            	}else{
	            		//保存到数据库
	            		mediaId = result;
	            		WxSucaiInfo sucai = new WxSucaiInfo();    //素材对象
	            		Attachment attachment = new Attachment(); //附件对象
	            		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	            		String newdate = df.format(new Date());
	            		sucai.setScName(fileNameU);
	            		sucai.setScType(2);     //语音
	            		sucai.setMediaId(mediaId);
	            		sucai.setRemark(path);
	            		sucai.setCreateUser(user.getYhbh());
	            		sucai.setCreateTime(df.parse(newdate));
	            		//
	            		attachment.setFileName(attachMentFile.getOriginalFilename());  //文件原名
	            		attachment.setFileCode(fileNameU);                             //上传后文件名
	            		attachment.setDescn(path);
	            		attachment.setDelFlag(0);
	            		attachment.setCreateTime(df.parse(newdate));
	            		attachment.setCreateUser(user.getYhbh());
	            		attachment.setFileSize(attachMentFile.getSize());
	            		attachment.setFileType("voice");
	            		Long attachmentId = attachMentDao.save(attachment);
	            		sucai.setAttachmentId(attachmentId);
						materialMgrDao.save(sucai);
						
	            	}
	            	
	            }else{
	                throw new RuntimeException("语音上传到微信服务器失败");
	            }
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 上传视频
	 * @param attachMentFile  视频文件
	 */
	@Transactional
	public void UploadVideoMaterial(MultipartFile attachMentFile){
		//获得session用户
		SysYh user=SecurityContextUtil.getCurrentUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = sdf.format(new Date());		//当前日期格式化到秒
		String random = RandomUtil.generateWord();	//获得随机数
		String filename = "";       //文件名
		String fileNameSuffix ="";  //文件后缀名
		String result = "";
		String fileNameU = "";
		String mediaId = "";
		String path = "";
		InputStream inputStream = null;
		try {
	            if(attachMentFile.isEmpty()){  
	                throw new RuntimeException("文件未上传");
	            }else{  
	                filename = attachMentFile.getOriginalFilename();
	                fileNameSuffix = filename.substring( filename.lastIndexOf('.')+1 );
	            }  
	            //文件输入流
	            inputStream = attachMentFile.getInputStream();
	            //拼装上传路径
	            path = filePath+ File.separator + "video";
	            //文件名称 （日期+随机数+原文件后缀名）
	            fileNameU = date+random+"."+fileNameSuffix;
	            //上传到本地服务器
	            new FileCommonOperate().uploadFile(inputStream, path, fileNameU.trim());
	            //上传到微信服务器
	            result = WeixinUtils.uploadMedia("video", path+File.separator+fileNameU,attachMentFile);
	            System.out.println("微信返回结果："+result);
	            if(!result.isEmpty()){
	            	if(result.startsWith("error|")){
		                throw new RuntimeException("视频上传到微信服务器失败");
	            	}else{
	            		//保存到数据库
	            		mediaId = result;
	            		WxSucaiInfo sucai = new WxSucaiInfo();    //素材对象
	            		Attachment attachment = new Attachment(); //附件对象
	            		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	            		String newdate = df.format(new Date());
	            		sucai.setScName(fileNameU);
	            		sucai.setScType(3);     //视频
	            		sucai.setMediaId(mediaId);
	            		sucai.setRemark(path);
	            		sucai.setCreateUser(user.getYhbh());
	            		sucai.setCreateTime(df.parse(newdate));
	            		//
	            		attachment.setId(Long.parseLong(date+random));
	            		attachment.setFileName(attachMentFile.getOriginalFilename());   //文件原名
	            		attachment.setFileCode(fileNameU);                              //上传后文件名
	            		attachment.setDescn(path);
	            		attachment.setDelFlag(0);
	            		attachment.setCreateTime(df.parse(newdate));
	            		attachment.setCreateUser(user.getYhbh());
	            		attachment.setFileSize(attachMentFile.getSize());
	            		attachment.setFileType("voice");
	            		Long attachmentId = attachMentDao.save(attachment);
	            		sucai.setAttachmentId(attachmentId);
						materialMgrDao.save(sucai);
						
						
	            	}
	            	
	            }else{
	                throw new RuntimeException("视频上传到微信服务器失败");
	            }
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	
	
	
	/**
	 * 上传图文消息 
	 * @param scid
	 */
	@Transactional
	public ResponseData uploadTuWenInfo(Long scid){
		List<Map<String, Object>>  list  = wxSucaiMpnewsDao.queryByName(scid);
		String result = "";
		
		if(list.isEmpty()){
			return new ResponseData(false, "请填写图文属性");
		}else{
			result = new WeixinUtils().media_uploadnews(list);
			
			System.out.println("上传图文消息微信返回的结果集："+result);
			
			if(!result.isEmpty()){
            	if(result.startsWith("error|")){
	                return new  ResponseData(false,"图文消息上传到微信服务器失败");
            	}else{
            		//上传成功后根据素材编号修改素材的MEDIA_ID
            		//System.out.println("=======修改mediaid========");
            		updateSucaiMediaID(scid,result);
            		return new ResponseData(true, result);
            	}
            	
            }else{
                return new ResponseData(false, "图文消息上传到微信服务器失败");
            }
		}
	}
	
	
	
	
	
	/**
	 * 根据素材名称查询素材是否存在
	 * @param scmc
	 * @return
	 */
	public List<Map<String,Object>> queryByName(String scmc){
		return materialMgrDao.queryByName(scmc);
	}
	
	
	@Transactional
	public Long insertEntity(WxSucaiInfo entity){
		//获得session用户
		SysYh user=SecurityContextUtil.getCurrentUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		try {
			entity.setCreateTime(sdf.parse(date));
			entity.setCreateUser(user.getYhbh());
			entity.setScType(4);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return materialMgrDao.save(entity);
	}
	
	/**
	 * 根据素材id获得 图片信息
	 * @param scid
	 * @return
	 */
	public WxSucaiInfo getEntity(Long scid){
		return materialMgrDao.get(scid);
	}
	
	@Transactional
	public void updateEntity(WxSucaiInfo entity){
		
		//获得session用户
		SysYh user=SecurityContextUtil.getCurrentUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		try {
				entity.setCreateTime(sdf.parse(date));
				entity.setCreateUser(user.getYhbh());
				materialMgrDao.update(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
	}
	
	/**
	 * 根据素材编号修改素材的MEDIA_ID
	 * @param scid 素材编号
	 * @param mediaId 
	 */
	@Transactional
	public void updateSucaiMediaID(Long scid,String mediaId){
		
		
		WxSucaiInfo oldEntity = materialMgrDao.get(scid);
		if(oldEntity == null){
			throw new RuntimeException("图文素材不存在");
		}
		oldEntity.setMediaId(mediaId);
		materialMgrDao.update(oldEntity);
		
	}
	
	
	
	
	
	/**
	 * 删除图文名称
	 * @param id
	 * @return
	 */
	@Transactional
	public WxSucaiInfo deleteTuWenMcInfo(Long id){
		return materialMgrDao.delete(id);
	}
	
	
	/**
	 * 取得素材表中3天内的图片列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	public List<Map<String, Object>> getImageList() {
		return materialMgrDao.getImageList();
	}
	

	/**
	 * 返回根路径
	 * @author:zhangyi 
	 * @version 创建时间：2015年7月2日 下午4:35:45 
	 * @return
	 */
	public String returnPath(){
		return filePath;
	}
	
	/**
	 * 定时根据新闻变化发送图文消息
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月3日 下午4:49:50
	 */
	@Transactional
	public Long dsfsXwtxfs(){
		//获取最新新闻资讯
		List<Map<String,Object>> listXwzx = companyDao.getLastDayXwzx();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String  scName = sdf.format(new Date());
		if(listXwzx.size()>0){
			WxSucaiInfo entity = new WxSucaiInfo();
			entity.setScName(scName+"公司资讯提醒");
			entity.setRemark("");
			Long id = this.insertEntity(entity);
			int i = 1;
			for(Map<String,Object> map:listXwzx){
				//手动保存
				WxSucaiMpnews wxs = new WxSucaiMpnews();
				wxs.setAuthor("合肥智圣集成");
				wxs.setScId(id);
				wxs.setContentSourceUrl("");
				wxs.setShowCoverPic(0);
				wxs.setContentSourceUrl("");
				wxs.setDigest("");
				wxs.setMpContent(map.get("content").toString());
				if(i == 1){
					wxs.setThumbMediaId("4vD4y3pzbdkyBzAu8JjoEPwm6o0u6tIpJgFKzFTDfUYbX-bCiLcObzs3BGQzzIKx");
					i=i+1;
				}else{
					wxs.setThumbMediaId("cMYWJeONTuAkjXCUAiCwYklBmLL11EFWoaJdp6SheQbqABnygMWwbgOsrtRT17vZ");
				}
				wxs.setTitle(map.get("Title").toString());
				wxSucaiMpnewsDao.save(wxs);
			}
			return id;
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月11日 上午11:47:54 
	 * @return
	 */
	public ResponseData dsSendNews() {
		Long scid=this.dsfsXwtxfs();
		if(scid!=null){
			ResponseData res = this.uploadTuWenInfo(scid);
			if(res.isSuccess()){
				String media_id = res.getMessage().toString();
				massInfoService.massAllPreview("mpnews", media_id);
			};
		}
		 return ResponseData.SUCCESS_NO_DATA;
	}
	
}

