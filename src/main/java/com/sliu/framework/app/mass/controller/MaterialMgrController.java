package com.sliu.framework.app.mass.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.mass.service.MassInfoService;
import com.sliu.framework.app.mass.service.MaterialMgrService;
import com.sliu.framework.app.model.WxSucaiInfo;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxtuwen.service.WxSucaiMpnewsService;

/**
 * 素材管理 Controller
 * @author zhangyi
 *
 */
@Controller
@RequestMapping(value="/wxauth/materialmgr")
public class MaterialMgrController extends BaseController{

	@Autowired
	private MaterialMgrService materialMgrService;
	@Autowired
	private WxSucaiMpnewsService wxSucaiMpnewsService;
	@Autowired
	private MassInfoService massInfoService;
	
	/**
	 * 打开素材管理页面
	 * @return
	 */
	@RequestMapping(value = "/materialmgrView")
	public ModelAndView openMaterialMgrPage(){
		ModelAndView view = new ModelAndView("/weixin/mass/materialMgrList");
		return view;
	}
	
	/**
	 * 打开上传图片素材 页面
	 * @return
	 */
	@RequestMapping(value = "/imageMaterialView")
	public ModelAndView openImageMaterialView(){
		ModelAndView view = new ModelAndView("/weixin/mass/imageMaterial");
		return view;
	}
	
	
	/**
	 * 打开上传语音素材 页面
	 * @return
	 */
	@RequestMapping(value = "/voiceMaterialView")
	public ModelAndView openVoiceMaterialView(){
		ModelAndView view = new ModelAndView("/weixin/mass/voiceMaterial");
		return view;
	}
	
	
	
	/**
	 * 打开上传视频素材 页面
	 * @return
	 */
	@RequestMapping(value = "/videoMaterialView")
	public ModelAndView openVideoMaterialView(){
		ModelAndView view = new ModelAndView("/weixin/mass/videoMaterial");
		return view;
	}
	
	/**
	 * 打开图文消息页面
	 * @return
	 */
	@RequestMapping(value = "/tuwenView")
	public ModelAndView openTuWenView(){
		ModelAndView view = new ModelAndView("/weixin/mass/tuwenMsg");
		return view;
	}
	
	
	/**
	 * 获取素材列表
	 * @param start
	 * @param limit
	 * @param scmc   素材名称
	 * @param sclx   素材类型
	 * @return
	 */
	@RequestMapping(value = "/getSuCaiPagination", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String,Object>> getSuCaiPagination(Integer start,Integer limit, String scmc,String sclx) {
		return materialMgrService.getSuCaiPagination(start, limit, scmc,sclx);
	}
	
	
	/**
	 * 获得图文消息详细信息
	 * @param start
	 * @param limit
	 * @param scid  素材id
	 * @return
	 */
	@RequestMapping(value = "/getTuWenInfos", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String,Object>>getTuWenInfos(Integer start,Integer limit, String scid){
		
		return materialMgrService.getTuWenInfos(start, limit, scid);
		
	}
	
	
	
	
	/**
	 * 上传图片素材
	 * @return
	 */
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public String UploadImageMaterial(@RequestParam("attachMentFile") MultipartFile attachMentFile){    
		
		try{
			materialMgrService.UploadImageMaterial(attachMentFile);
			return "{\"success\":\"上传成功\"}";	
		}catch(Exception e) {
			return "{\"failure\":\""+e.getMessage()+"\"}";
		}
	}
	
	
	/**
	 * 上传语音素材
	 * @param attachMentFile   语音文件
	 * @return
	 */
	@RequestMapping(value="/uploadVoice")
	@ResponseBody
	public String UploadVoiceMaterial(@RequestParam("attachMentFile") MultipartFile attachMentFile){
		
		try{
			materialMgrService.UploadVoiceMaterial(attachMentFile);
			return "{\"success\":\"上传成功\"}";	
		}catch(Exception e) {
			return "{\"failure\":\""+e.getMessage()+"\"}";	
		}
	}
	
	
	
	/**
	 * 上传视频 
	 * @param attachMentFile
	 * @return
	 */
	@RequestMapping(value="/uploadVideo")
	@ResponseBody
	public String UploadVideoMaterial(@RequestParam("attachMentFile") MultipartFile attachMentFile){
		try{
			materialMgrService.UploadVideoMaterial(attachMentFile);
			return "{\"success\":\"上传成功\"}";	
		}catch(Exception e) {
			return "{\"failure\":\""+e.getMessage()+"\"}";	
		}
	}
	
	
	/**
	 * 上传图文消息
	 * @return
	 */
	@RequestMapping(value="/uploadTuWenInfo")
	@ResponseBody
	public ResponseData uploadTuWenInfo(Long ids){    
		return materialMgrService.uploadTuWenInfo(ids);
	}
	
	
	
	
	/**
	 * 保存图文名称
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/saveTuWenMcInfo")
    @ResponseBody
    public ResponseData save(WxSucaiInfo entity) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		if(entity.getScName() != null){
			list = materialMgrService.queryByName(entity.getScName());
		}
		
		if(list.size() > 0){
			return new ResponseData(false, "该图文名称已存在");
		}else{
			materialMgrService.insertEntity(entity);
		}
		
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 修改图文名称
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/updateTuWenMcInfo")
    @ResponseBody
    public ResponseData updateTuWenMcInfo(WxSucaiInfo entity)
    {
	  WxSucaiInfo oldEntity = (WxSucaiInfo)materialMgrService.getEntity(entity.getScId());
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
     
      oldEntity.setScName(entity.getScName());
      oldEntity.setRemark(entity.getRemark());
      materialMgrService.updateEntity(oldEntity);
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	
	
	/**
	 * 删除图文名称 信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteTuWenInfo")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(Long[] ids)
    {
      for (Long id : ids)
      {
    	  //查询该图文是否绑定属性信息
    	  List<Map<String,Object>> list = wxSucaiMpnewsService.getSucaiMpnews(id);
    	  if(list.isEmpty()){
    		  //删除
        	  materialMgrService.deleteTuWenMcInfo(id);
    	  }else{
    		  return new ResponseData(false,"该图文绑定了属性信息，请先删除属性信息");
    	  }
    	 
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 取得素材表中3天内的图片列表(上传的素材大于3天微信平台将会删除)
	 * @return
	 */
	@RequestMapping(value = "/getImageList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getImageList() {
		return materialMgrService.getImageList();
	}
	
	/**
	 * 公司咨询图文消息的发送
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月10日 下午2:22:53 
	 * @return
	 */
	@RequestMapping(value = "/dsfsXwtxfs", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData dsfsXwtxfs() {
		Long scid=materialMgrService.dsfsXwtxfs();
		if(scid!=null){
			ResponseData res = materialMgrService.uploadTuWenInfo(scid);
			if(res.isSuccess()){
				String media_id = res.getMessage().toString();
				massInfoService.massAllPreview("mpnews", media_id);
			};
		}
		 return ResponseData.SUCCESS_NO_DATA;
	}
	
}
