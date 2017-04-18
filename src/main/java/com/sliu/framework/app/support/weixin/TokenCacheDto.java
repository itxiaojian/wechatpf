package com.sliu.framework.app.support.weixin;

import java.util.Date;

public class TokenCacheDto {

	// 获取到的凭证  
	private String token;  
	// 凭证有效时间，单位：秒  
	private int expiresIn;
	//凭证生成时间
	private Date createTime;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
