package com.sliu.framework.app.wsh.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.file.model.SysFjgl;
import com.sliu.framework.app.model.WxUserInfo;
import com.sliu.framework.app.support.random.RandomUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.model.ShZpq;
import com.sliu.framework.app.wsh.model.ShZpqdz;
import com.sliu.framework.app.wsh.model.SysFjglzpq;
import com.sliu.framework.app.wsh.service.ShZpqService;


/**
 * 照片墙
 * 
 * @author wangxiangyang
 * @version 创建时间：2016年9月7日
 */
@Controller
@RequestMapping("/wsh/ShZpq")
public class ShZpqController extends BaseController {
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ShZpqController.class);
	
	@Autowired
	private ShZpqService shZpqService;
	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	/**
	 * 照片墙页面
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/toZpq")
	@ResponseBody
	public ModelAndView shZpqDetail(String openId,HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wsh/ShZpq/shZpqDetail";
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){
			modelAndView = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list = shZpqService.getZpq(request);
		List<Map<String, Object>> list1 = shZpqService.getTP(openId);
		List<Map<String, Object>> dlr = shZpqService.getYh(openId);
		modelAndView.addObject("dlr", dlr);
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("list", list);
		modelAndView.addObject("list1", list1);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("size",list.size());
		modelAndView.setViewName(url);
		}

		return modelAndView;
	}
	
	/**
	 * 照片墙页面
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/ZpqLoadmore")
	@ResponseBody
	public List<Map<String, Object>> ZpqLoadmore(String openId,HttpServletRequest request) {

		String url = "";

		url = "/wsh/ShZpq/shZpqDetail";
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		List<Map<String, Object>> list = shZpqService.getZpq(request);
		List<Map<String, Object>> dlr = shZpqService.getYh(openId);
		
		for(int i=0;i<=list.size()-1;i++){
				Map<String, Object> map = list.get(i);
				Map<String, Object> map3 = dlr.get(0);
				map.put("yhid", map3.get("yhid"));
				map.put("dlm", map3.get("dlm"));
				map.put("wxh", map3.get("wxh"));
			}
		return list;
	}

	/**
	 * 发布照片墙信息
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/ZpqFb")
	@ResponseBody
	public ResponseData saveZbq(String tcnr, String openId,String nm,String bz1,
			String bz2,String bz3,String bz4,String bz5,String bz6) {
		ShZpq entity = new ShZpq();
//		String accessToken = WeixinCacheUtils.getAccessToken();// 获取微信公众号的accessToken
//		WxUserInfo wxUserInfo = WeixinUtils.getUserInfo(accessToken, openId);// 根据openId和公众号Token获取关注用户的基本信息
		List<Map<String, Object>> list1 = shZpqService.getWxyh(openId);
		
		if (list1 != null) {
			entity.setTcr(openId);
			if("1".equals(nm)){
				entity.setTcrxm((String) list1.get(0).get("wxnc"));
			}else if("0".equals(nm)){
				entity.setTcrxm("***");
			}
			entity.setTcnr(tcnr);
			entity.setTcsj(Calendar.getInstance().getTime());
			entity.setTxdz((String) list1.get(0).get("yhtx"));
			entity.setBzcs(0);
			entity.setHfcs(0);
			entity.setTclx(2);
			entity.setBz1(bz1);
			entity.setBz2(bz2);
			entity.setBz3(bz3);
			entity.setBz4(bz4);
			entity.setBz5(bz5);
			entity.setBz6(bz6);
//			entity.setZt("1");
		}

		String result = shZpqService.saveZpq(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 点赞
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doTczhan")
	@ResponseBody
	public ResponseData DoTCZhan(Long id,String openId) {

//		String accessToken = WeixinCacheUtils.getAccessToken();//获取微信公众号的accessToken
//		WxUserInfo wxUserInfo= WeixinUtils.getUserInfo(accessToken, openId);//根据openId和公众号Token获取关注用户的基本信息
		
		List<Map<String, Object>> list1 = shZpqService.getWxyh(openId);
		List list = shZpqService.getzan(id,openId);
		if (list.size() > 0) {
			return ResponseData.FAILED_NO_DATA;
		} else {
			ShZpqdz entity = new ShZpqdz();
			entity.setTczj(id);
			entity.setZr(openId);
			if(list1!=null){
				entity.setZrxm((String) list1.get(0).get("wxnc"));
			}
			String result = shZpqService.saveDianzan(entity, id);
			if (!"1".equalsIgnoreCase(result)) {
				return ResponseData.FAILED_NO_DATA;
			}
			return ResponseData.SUCCESS_NO_DATA;
		}
	}
	
	/**
	 * 删除
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteZpqList")
	@ResponseBody
	public ResponseData deleteBbqList(Long id,HttpServletRequest request){
		shZpqService.deleteZpqList(id);
		String openId = request.getParameter("openId");
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	@RequestMapping(value = "/fileUload")
	@ResponseBody
	public String ftpUpload(MultipartFile file,String wjlx,Long lxid,String openId) throws Exception{
		//File files = new File("");
		String str="";
		if (!file.isEmpty()) {
//			if(file.getSize()>10000000){
//          	throw new Exception("上传失败：文件大小不能超过10M");           
//          }
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String date = sdf.format(new Date());		//当前日期格式化到秒
			String random = RandomUtil.generateWord();	//获得随机数
			String filename = file.getOriginalFilename();
			String prefix=filename.substring(filename.lastIndexOf(".")+1);
			String fileNameU = date+random+"."+prefix;//文件名称
//			BufferedImage srcImage = ImageIO.read(new File("c:/xxx.jpg"));
//			byte[] data1 = ((DataBufferByte) srcImage.getData().getDataBuffer()).getData(); 
			try {
				BASE64Encoder encoder =  new BASE64Encoder();  
				// 通过base64来转化图片
				String data = encoder.encode(file.getBytes());
//				String data2 = encoder.encode(data1);
				shZpqService.saveFile(data,wjlx,lxid,fileNameU,openId);
				str=fileNameU;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return "errorView";
		}
		return str;
	}
	
	/**
	 * 取得图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getFile")
	public void getImg(HttpServletRequest request,HttpServletResponse response,Long imgId){
		SysFjglzpq img = shZpqService.getfile(imgId);
		String data = img.getContent();
		BASE64Decoder decoder = new BASE64Decoder();  
		try {
			byte[] bytes = decoder.decodeBuffer(data);
			for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据  
                    bytes[i] += 256;
                }  
            }
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes);
            out.flush();
            out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * 取得imgId
	 * @param request
	 * @param response
	 */
		@RequestMapping(value="/getImgId")
		@ResponseBody
	   	public Long  getBxzt(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException{
	   		List<Map<String,Object>> list=shZpqService.getImgId(request);
	   		Long id =1l;
	   		if(list.size()!=0){
	   			id=Long.parseLong((list.get(0).get("id")+""));
	   		}
	   		return id;
	   	}
		
		/**
		 * @author   wangxiangyang
		 * @version 创建时间：2016年9月29日
		 * @param start
		 * @param limit
		 * @return
		 */
		@RequestMapping(value = "/getZpqList")
		@ResponseBody
		public Pagination<Map<String, Object>> getZpqList(Integer start,Integer limit){
			
			return shZpqService.getZpqList(start, limit);
			
		}
		
		/**
		 * 
		 * @author   wangxiangyang
		 * @version 创建时间：2016年9月29日
		 * @return
		 */
		@RequestMapping(value = "/toZpq1")
		public ModelAndView toBbq(){
			ModelAndView modelAndView = new ModelAndView("/wsh/ShZpq/toZpq");
			return modelAndView;
		}
	
		
		/**
		 * 
		 * @author wangxiangyang
		 * @version 创建时间：2016年9月29日
		 * @param ids
		 * @return
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		@ResponseBody
		public ResponseData delete(Long[] ids) {

			shZpqService.delete(ids);
			return ResponseData.SUCCESS_NO_DATA;
		}
}
