package com.sliu.framework.app.dto.message.resp;

/**
 * 音乐消息
 * 
 * @author ZDD
 * @date 2014-03-19
 */
public class MusicMessage extends RespBaseMessage {
	
	// 音乐  
    private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
    
    

}
