package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 贫困生信息
 * @author duanpeijun
 * @version 创建时间：2015年6月9日
 */
@Entity
@Table(name = "ZS_PKSXX")
public class ZsPksxx implements Serializable{

	private Long id;//主键
	
	private String zy;//专业
	
	private String pksdj;//贫困生等级
	
	private String xm;//姓名
	
	private String bj;//班级
	
	private String xh;//学号
	
	private String xy;//学院
	
	private String bz;//备注

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "zy", length = 60, nullable = true)
	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	@Column(name = "pksdj", length = 30, nullable = true)
	public String getPksdj() {
		return pksdj;
	}

	public void setPksdj(String pksdj) {
		this.pksdj = pksdj;
	}
	
	@Column(name = "xm", length = 30, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "bj", length = 30, nullable = true)
	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	@Column(name = "xh", length = 30, nullable = true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "xy", length = 30, nullable = true)
	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	@Column(name = "bz", length = 500, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
