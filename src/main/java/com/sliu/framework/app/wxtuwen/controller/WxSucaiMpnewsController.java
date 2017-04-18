package com.sliu.framework.app.wxtuwen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.model.WxSucaiMpnews;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxtuwen.service.WxSucaiMpnewsService;

/**
 * 
 * 微信图文信息  Controller
 * @author zhangyi
 *
 */
@Controller
@RequestMapping(value="/wxauth/wxsucaimpnews")
public class WxSucaiMpnewsController extends BaseController{
	
	@Autowired
	private WxSucaiMpnewsService wxSucaiMpnewsService;
	
	/**
	 * 保存图文详细信息
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/saveSucaiMpnews")
    @ResponseBody
    public ResponseData save(WxSucaiMpnews entity)
    {
		wxSucaiMpnewsService.insertEntity(entity);
		
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 修改图文详细信息
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/updateSucaiMpnews")
    @ResponseBody
    public ResponseData updateSucaiMpnews(WxSucaiMpnews entity)
    {
	  WxSucaiMpnews oldEntity = (WxSucaiMpnews)wxSucaiMpnewsService.getEntity(entity.getMpnewsId());
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
      
      oldEntity.setAuthor(entity.getAuthor());
      oldEntity.setContentSourceUrl(entity.getContentSourceUrl());
      oldEntity.setDigest(entity.getDigest());
      oldEntity.setMpContent(entity.getMpContent());
      oldEntity.setScId(entity.getScId());
      oldEntity.setShowCoverPic(entity.getShowCoverPic());
      oldEntity.setThumbMediaId(entity.getThumbMediaId());
      oldEntity.setTitle(entity.getTitle());
      wxSucaiMpnewsService.updateEntity(oldEntity);
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 删除图文消息详细信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteSucaiMpnews")
    @ResponseBody
    public ResponseData deleteSucaiMpnews(Long[] ids)
    {
      for (Long id : ids)
      {
    	  wxSucaiMpnewsService.deleteSucaiMpnews(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }

}
