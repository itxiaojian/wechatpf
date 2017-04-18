package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 微报名
 * @author liujiansen
 * @since  2015-06-24
 */
@Entity
@Table(name="sh_wbm")
public class ShWbm implements Serializable{
	
	/**id*/
	private Long id;
	
	/**报名标题*/
	private String bmbt;
	
	/**报名介绍*/
	private String bmjs;
	
	/**报名成功提示*/
	private String bmcgts;
	
	/**报名截止时间*/
	private java.util.Date bmjzsj;
	
	/**主办方LOGO*/
	private String zbflogo;
	
	/**卡券门票*/
	private String kjmp;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="BMBT", length=100, nullable=true)
	public String getBmbt() {
		return bmbt;
	}
	
	public void setBmbt(String bmbt) {
		this.bmbt = bmbt;
	}
	
	@Column(name="BMJS", length=300, nullable=true)
	public String getBmjs() {
		return bmjs;
	}
	
	public void setBmjs(String bmjs) {
		this.bmjs = bmjs;
	}
	
	@Column(name="BMCGTS", length=100, nullable=true)
	public String getBmcgts() {
		return bmcgts;
	}
	
	public void setBmcgts(String bmcgts) {
		this.bmcgts = bmcgts;
	}
	
	@Column(name="BMJZSJ", length=19, nullable=true)
	public java.util.Date getBmjzsj() {
		return bmjzsj;
	}
	
	public void setBmjzsj(java.util.Date bmjzsj) {
		this.bmjzsj = bmjzsj;
	}
	
	@Column(name="ZBFLOGO", length=500, nullable=true)
	public String getZbflogo() {
		return zbflogo;
	}
	
	public void setZbflogo(String zbflogo) {
		this.zbflogo = zbflogo;
	}
	
	@Column(name="KJMP", length=10, nullable=true)
	public String getKjmp() {
		return kjmp;
	}
	
	public void setKjmp(String kjmp) {
		this.kjmp = kjmp;
	}
	
}
