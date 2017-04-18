package com.sliu.framework.app.dto.message.resp;

/**
 * 文本信息
 * 
 * @author ZDD
 * @date 2014-03-19
 */
public class RespTextMessage extends RespBaseMessage{
	
	// 回复的消息内容  
    private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	} 
    
    

}
