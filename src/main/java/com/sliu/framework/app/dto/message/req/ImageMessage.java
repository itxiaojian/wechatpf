package com.sliu.framework.app.dto.message.req;

/**
 * 图片消息
 * 
 * @author ZDD
 * @date 2014-03-19
 */
public class ImageMessage extends ReqBaseMessage {
	
	// 图片链接  
    private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
    
    

}
