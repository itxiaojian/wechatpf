package com.sliu.framework.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 微信自定义菜单
 * @author zhangyi
 * @since  2015-06-03
 */
@Entity
@Table(name="wx_zdycd")
public class WxZdycd implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**名称*/
	private String mc;
	
	/**类型 click view*/
	private String lx;
	
	/**上级ID*/
	private Integer sjid;
	
	/**当前级别 ： 1级 2级*/
	private Integer dqjb;
	
	/**链接view时用*/
	private String lj;
	
	/**创建时间*/
	private java.util.Date cjsj;
	
	/**排序*/
	private Integer px;
	
	/**状态1正常*/
	private Integer zt;
	
	/**备注*/
	private String bz;
	
	/**KZ*/
	private String kz;
	
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
	
	@Column(name="MC", length=100, nullable=true)
	public String getMc() {
		return mc;
	}
	
	public void setMc(String mc) {
		this.mc = mc;
	}
	
	@Column(name="LX", length=100, nullable=true)
	public String getLx() {
		return lx;
	}
	
	public void setLx(String lx) {
		this.lx = lx;
	}
	
	@Column(name="SJID", length=10, nullable=true)
	public Integer getSjid() {
		return sjid;
	}
	
	public void setSjid(Integer sjid) {
		this.sjid = sjid;
	}
	
	@Column(name="DQJB", length=10, nullable=true)
	public Integer getDqjb() {
		return dqjb;
	}
	
	public void setDqjb(Integer dqjb) {
		this.dqjb = dqjb;
	}
	
	@Column(name="LJ", length=500, nullable=true)
	public String getLj() {
		return lj;
	}
	
	public void setLj(String lj) {
		this.lj = lj;
	}
	
	@Column(name="CJSJ", length=19, nullable=true)
	public java.util.Date getCjsj() {
		return cjsj;
	}
	
	public void setCjsj(java.util.Date cjsj) {
		this.cjsj = cjsj;
	}
	
	@Column(name="PX", length=10, nullable=true)
	public Integer getPx() {
		return px;
	}
	
	public void setPx(Integer px) {
		this.px = px;
	}
	
	@Column(name="ZT", length=10, nullable=true)
	public Integer getZt() {
		return zt;
	}
	
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	
	@Column(name="BZ", length=300, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name="KZ", length=100, nullable=true)
	public String getKz() {
		return kz;
	}
	
	public void setKz(String kz) {
		this.kz = kz;
	}
	
}
