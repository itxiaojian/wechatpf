package com.sliu.framework.app.bx.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*保修主题
@Author oufeng	
@Date 2015年8月6日 上午10:34:18
@Version 1.0
*/
@Entity
@Table(name="bx_bxzt")
public class BxBxzt implements java.io.Serializable  {
    //ID
	private Long id;
	
	//主题名称
	private  String ztmc;
	
   //主题级别
	private Integer ztjb;
	
	//上级主题
	private  String sjzt;
	
	//添加时间
	private Date tjsj;
	
	//备注
	private  String bz;
	
	//排序
	private Long px;
	
	//主题编号
	private String ztbh;

	@Id 
    @Column(name="ID", unique=true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Column(name="ZTMC", length=32)
	public String getZtmc() {
		return ztmc;
	}

	public void setZtmc(String ztmc) {
		this.ztmc = ztmc;
	}

	@Column(name="ZTJB", length=2)
	public Integer getZtjb() {
		return ztjb;
	}

	public void setZtjb(Integer ztjb) {
		this.ztjb = ztjb;
	}

	@Column(name="SJZT", length=20)
	public String getSjzt() {
		return sjzt;
	}

	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
	}

	@Column(name="tjsj", nullable=true)
	public Date getTjsj() {
		return tjsj;
	}

	public void setTjsj(Date tjsj ) {
		this.tjsj = tjsj;
	}

    @Column(name="BZ", length=512)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	  @Column(name="px", length=20)
	public Long getPx() {
		return px;
	}

	public void setPx(Long px) {
		this.px = px;
	}

	  @Column(name="ztbh", length=20)
	public String getZtbh() {
		return ztbh;
	}

	public void setZtbh(String ztbh) {
		this.ztbh = ztbh;
	}
	
}


