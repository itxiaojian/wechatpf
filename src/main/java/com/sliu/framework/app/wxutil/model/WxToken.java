package com.sliu.framework.app.wxutil.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

/** 
 * @author:zhangyi 
 * @version 创建时间：2016年8月3日 下午3:57:57 
 * 保存微信token值，作为缓存
 */
@Entity
@Table(name="WX_TOKEN")
public class WxToken {
	
	private Long id;
	// 获取到的凭证  
	private String token;  
	// 凭证有效时间，单位：秒  
	private int expiresIn;
	//凭证生成时间
	private String createTime;
	
	public WxToken(){
		
	}
	
	public WxToken(Long id){
		this.id = id;
	}
	
	public WxToken(Long id,String token,int expiresIn,String createTime){
		this.id = id;
		this.token = token;
		this.expiresIn = expiresIn;
		this.createTime = createTime;
	}
	
    @Id 
    @GeneratedValue(generator = "tableGenerator")     
    @GenericGenerator(name = "tableGenerator", strategy="com.sliu.framework.app.common.dao.key.SequenceGenerator")       
    @Column(name="ID", unique=true, nullable=false)
	public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="TOKEN", length=1000)
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name="EXPIRES_IN", length=11)
	public int getExpiresIn() {
		return expiresIn;
	}
	
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	@Column(name="CREATE_TIME", length=30)
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
