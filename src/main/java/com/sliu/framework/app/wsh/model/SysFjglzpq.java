package com.sliu.framework.app.wsh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 照片墙附件管理
 * @author : wangxiangyang
 * @version 创建时间：2016年9月8日
 */
@Entity
@Table(name="SYS_FJGLZPQ")
public class SysFjglzpq implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**文件名称*/
	private String fname;
	
	/**文件类型*/
	private String wjlx;
	
	/**类型id*/
	private Long lxid;

	/**备注*/
	private String bz;
	
	/**文件内容*/
	private String content;
	
	private Date tcsj;//吐槽时间
	
	private String wxh;//吐槽人
	
    @Id 
    @GeneratedValue(generator = "tableGenerator")     
    @GenericGenerator(name = "tableGenerator", strategy="com.sliu.framework.app.common.dao.key.SequenceGenerator") 
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="FNAME", length=100, nullable=true)
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Column(name="WJLX", length=10, nullable=true)
	public String getWjlx() {
		return wjlx;
	}
	
	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}
	
	@Column(name="LXID", length=10, nullable=true)
	public Long getLxid() {
		return lxid;
	}
	
	public void setLxid(long lxid) {
		this.lxid = lxid;
	}
	
	
	@Column(name="BZ", length=300, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name="CONTENT", length=10000000,nullable=true)
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public Date getTcsj() {
		return tcsj;
	}

	public void setTcsj(Date tcsj) {
		this.tcsj = tcsj;
	}

	public String getWxh() {
		return wxh;
	}

	public void setWxh(String wxh) {
		this.wxh = wxh;
	}
	
}

