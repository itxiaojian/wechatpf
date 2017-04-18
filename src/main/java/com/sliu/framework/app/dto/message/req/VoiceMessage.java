package com.sliu.framework.app.dto.message.req;

/**
 * 音频消息
 * 
 * @author ZDD
 * @date 2014-03-19
 */
public class VoiceMessage extends ReqBaseMessage {
	
	 // 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;
    
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
    
    

}
