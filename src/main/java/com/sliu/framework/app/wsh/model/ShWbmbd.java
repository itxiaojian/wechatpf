package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 微报名表单
 * @author liujiansen
 * @since  2015-06-25
 */
@Entity
@Table(name="sh_wbmbd")
public class ShWbmbd implements Serializable{
	
	/**id*/
	private Long id;
	
	/**报名id*/
	private Long bmid;
	
	/**1、单行文字；2、多行文字；3、下拉框；4、多项选择*/
	private String bdlx;
	
	/**标题*/
	private String bt;
	
	/**最大选择数*/
	private Integer zdxzz;
	
	/**多行值*/
	private String ddhz;
	
	/**是否必填（1、是 0、否）*/
	private String sfbt;
	
	/**备注*/
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
	
	@Column(name="BMID", length=19, nullable=true)
	public Long getBmid() {
		return bmid;
	}
	
	public void setBmid(Long bmid) {
		this.bmid = bmid;
	}
	
	@Column(name="BDLX", length=10, nullable=true)
	public String getBdlx() {
		return bdlx;
	}
	
	public void setBdlx(String bdlx) {
		this.bdlx = bdlx;
	}
	
	@Column(name="BT", length=150, nullable=true)
	public String getBt() {
		return bt;
	}
	
	public void setBt(String bt) {
		this.bt = bt;
	}
	
	@Column(name="ZDXZZ", length=10, nullable=true)
	public Integer getZdxzz() {
		return zdxzz;
	}
	
	public void setZdxzz(Integer zdxzz) {
		this.zdxzz = zdxzz;
	}
	
	@Column(name="DDHZ", length=516, nullable=true)
	public String getDdhz() {
		return ddhz;
	}
	
	public void setDdhz(String ddhz) {
		this.ddhz = ddhz;
	}
	
	@Column(name="SFBT", length=4, nullable=true)
	public String getSfbt() {
		return sfbt;
	}
	
	public void setSfbt(String sfbt) {
		this.sfbt = sfbt;
	}
	
	@Column(name="BZ", length=600, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
