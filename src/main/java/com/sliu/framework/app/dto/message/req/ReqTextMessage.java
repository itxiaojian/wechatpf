package com.sliu.framework.app.dto.message.req;

/**
 * 文本消息
 * 
 * @author ZDD
 * @date 2014-03-19
 */
public class ReqTextMessage extends ReqBaseMessage {
	
	 // 消息内容  
    private String Content;
    
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	} 
    
    

}
