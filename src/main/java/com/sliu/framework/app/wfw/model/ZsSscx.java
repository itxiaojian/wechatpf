package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 宿舍查询
 * @author oufeng
 * @version 创建时间：2015年6月30日
 */
@Entity
@Table(name = "SH_SSCX")
public class ZsSscx implements Serializable{

	private Long id;//主键
	
	private String  xsxm;//学生姓名
	
	private String  xssfzh;//学生身份证
	
	private String szss;//所在宿舍
	
	private String bz;//备注
	
	private String xh;//学号
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "xsxm", length = 30, nullable = true)
	public String getXsxm() {
		return xsxm;
	}

	public void setXsxm(String xsxm) {
		this.xsxm =xsxm;
	}

	@Column(name = "xssfzh", length = 30, nullable = true)
	public String getXssfzh() {
		return xssfzh;
	}

	public void setXssfzh(String xssfzh) {
		this.xssfzh = xssfzh;
	}

	@Column(name = "szss", length = 30, nullable = true)
	public String getSzss() {
		return szss;
	}
	
	public void setSzss(String szss) {
		this.szss = szss;
	}

	@Column(name = "bz", length = 30, nullable = true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "xh", length = 30, nullable = true)
	public String getXh() {
		return xh;
	}
	
	public void setXh(String xh) {
		this.xh = xh;
	}
}
