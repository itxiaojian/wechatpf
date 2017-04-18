package com.sliu.framework.app.bx.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*报修维修记录表
@Author oufeng	
@Date 2015年8月13日 上午10:53:59
@Version 1.0
*/
@Entity
@Table(name="bx_bxwxjl")
public class BxBxwxjl {

	//主键
	private Long id;
	
	//报修申请id
	private Long bxid;
	
	//维修人编号
	private String wxrbh;
	
	//维修人姓名
	private String wxrxm;
	
	//维修状态
	private String wxzt;
	
	//派单时间
	private  Date pdsj;
	
	//维修时间
	private Date wxsj;
	
	//维修用时
	private String wxys;
	
	//费用额度
	private String fyed;
	
	//耗用用料
	private String hcyl;
	
	//备注
	private String bz;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="BXID", length=19, nullable=true)
	public Long getBxid() {
		return bxid;
	}

	public void setBxid(Long bxid) {
		this.bxid = bxid;
	}

	@Column(name="WXRBH", length=32, nullable=true)
	public String getWxbh() {
		return wxrbh;
	}

	public void setWxbh(String wxbh) {
		this.wxrbh = wxbh;
	}

	@Column(name="WXRXM", length=32, nullable=true)
	public String getWxrxm() {
		return wxrxm;
	}

	public void setWxrxm(String wxrxm) {
		this.wxrxm = wxrxm;
	}

	@Column(name="WXZT", length=2, nullable=true)
	public String getWxzt() {
		return wxzt;
	}

	public void setWxzt(String wxzt) {
		this.wxzt = wxzt;
	}

	@Column(name="PDSj", length=32, nullable=true)
	public Date getPdsj() {
		return pdsj;
	}

	public void setPdsj(Date pdsj) {
		this.pdsj = pdsj;
	}

	@Column(name="WXSJ", length=32, nullable=true)
	public Date getWxsj() {
		return wxsj;
	}

	public void setWxsj(Date wxsj) {
		this.wxsj = wxsj;
	}

	@Column(name="WXYS", length=50, nullable=true)
	public String getWxys() {
		return wxys;
	}

	public void setWxys(String wxys) {
		this.wxys = wxys;
	}

	@Column(name="FYED", length=50, nullable=true)
	public String getFyed() {
		return fyed;
	}

	public void setFyed(String fyed) {
		this.fyed = fyed;
	}

	@Column(name="HCYL", length=516, nullable=true)
	public String getHcyl() {
		return hcyl;
	}

	public void setHcyl(String hcyl) {
		this.hcyl = hcyl;
	}

	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}


