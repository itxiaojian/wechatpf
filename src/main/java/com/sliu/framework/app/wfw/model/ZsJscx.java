package com.sliu.framework.app.wfw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 校友信息
 * @author duanpeijun
 * @date 2015年7月8日
 */
@Entity
@Table(name = "sh_jscx")
public class ZsJscx implements Serializable{
	
	private Long id;//主键
	
	private String jsbh;//教室编号
	
	private String jsmc;//教室名称
	
	private String jxl;//教学楼
	
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

	@Column(name = "jsbh", nullable = false)
	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	@Column(name = "jsmc", nullable = false)
	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	@Column(name = "jxl", nullable = false)
	public String getJxl() {
		return jxl;
	}

	public void setJxl(String jxl) {
		this.jxl = jxl;
	}

	@Column(name = "bz", nullable = false)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
