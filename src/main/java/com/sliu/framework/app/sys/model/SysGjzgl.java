package com.sliu.framework.app.sys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

/**
 * 关键字管理
 * @author : zhangyi
 * @version 创建时间：2016年8月23日 下午3:02:20
 */
@Entity
@Table(name="SYS_GJZGL")
public class SysGjzgl {
	
	private Long id;
	// 关键字值
	private String gjz;  

	//状态
	private String zt;
	
	public SysGjzgl(){
		
	}
	
	public SysGjzgl(Long id){
		this.id = id;
	}
	
	public SysGjzgl(Long id,String gjz,String zt){
		this.id = id;
		this.gjz = gjz;
		this.zt = zt;
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
	
	@Column(name="GJZ", length=256)
	public String getGjz() {
		return gjz;
	}
	
	public void setGjz(String gjz) {
		this.gjz = gjz;
	}
	
	@Column(name="ZT", length=30)
	public String getZt() {
		return zt;
	}
	
	public void setZt(String zt) {
		this.zt = zt;
	}
	
	
	
}
