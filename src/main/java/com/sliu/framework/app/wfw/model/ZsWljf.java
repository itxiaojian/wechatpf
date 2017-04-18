package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.axis2.databinding.types.soapencoding.Decimal;

/**
 * 网络计费
 * @author wangxiangyang
 * @date 2015年6月1日
 */
@Entity
@Table(name = "zs_wljf")
public class ZsWljf implements Serializable {

	/** id */
	private Long id;

	/** 账户 */
	private String zh;

	/** 姓名 */
	private String xm;

	/** 账户状态*/
	private String zhzt;

	/** 费用状态*/
	private String fyzt;

	/** 余额 */
	private Decimal ye;
	
	/** 时间总计 */
	private String sjzj;
	
	/** 流量总计 */
	private String llzj;
	
	/** 用户组 */
	private String yhz;
	
	/** 手机号 */
	private String sjh;
	
	/** 电话号码 */
	private String dh;
	
	/** 上次登录时间 */
	private String scdlsj;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "xm", length = 32, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getZhzt() {
		return zhzt;
	}

	public void setZhzt(String zhzt) {
		this.zhzt = zhzt;
	}

	public String getFyzt() {
		return fyzt;
	}

	public void setFyzt(String fyzt) {
		this.fyzt = fyzt;
	}

	public Decimal getYe() {
		return ye;
	}

	public void setYe(Decimal ye) {
		this.ye = ye;
	}

	public String getSjzj() {
		return sjzj;
	}

	public void setSjzj(String sjzj) {
		this.sjzj = sjzj;
	}

	public String getLlzj() {
		return llzj;
	}

	public void setLlzj(String llzj) {
		this.llzj = llzj;
	}

	public String getYhz() {
		return yhz;
	}

	public void setYhz(String yhz) {
		this.yhz = yhz;
	}

	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getScdlsj() {
		return scdlsj;
	}

	public void setScdlsj(String scdlsj) {
		this.scdlsj = scdlsj;
	}
}
