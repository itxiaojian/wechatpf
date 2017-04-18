package com.sliu.framework.app.bx.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 报修维修任务
 * @author oufeng
 * @since  2016-11-02
 */
@Entity
@Table(name="bx_bxwxrw")
public class BxBxwxrw {
	//主键
	private Long id;
	
	//维修工编号
	private  String  wxrbh;
	
	//报修Id
	private String bxid;
	
	//报修地址
	private String bxdz;
	
	//预约时间
	private Date yysj;
	
	//报修状态
	private String bxzt;
	
	//报修种类
	private String bxzl;
	
	//创建时间
	private Date cjsj;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="wxrbh", length=50, nullable=true)
	public String getWxrbh() {
		return wxrbh;
	}

	public void setWxrbh(String wxrbh) {
		this.wxrbh = wxrbh;
	}

	@Column(name="bxid", length=10, nullable=true)
	public String getBxid() {
		return bxid;
	}

	public void setBxid(String bxid) {
		this.bxid = bxid;
	}

	@Column(name="bxdz", length=200, nullable=true)
	public String getBxdz() {
		return bxdz;
	}

	public void setBxdz(String bxdz) {
		this.bxdz = bxdz;
	}

	@Column(name="yysj", nullable=true)
	public Date getYysj() {
		return yysj;
	}

	public void setYysj(Date yysj) {
		this.yysj = yysj;
	}

	@Column(name="bxzt", length=5, nullable=true)
	public String getBxzt() {
		return bxzt;
	}

	public void setBxzt(String bxzt) {
		this.bxzt = bxzt;
	}

	@Column(name="bxzl", length=20, nullable=true)
	public String getBxzl() {
		return bxzl;
	}

	public void setBxzl(String bxzl) {
		this.bxzl = bxzl;
	}

	@Column(name="cjsj", nullable=true)
	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
}