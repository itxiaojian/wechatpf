package com.sliu.framework.app.sys.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 微信用户
 * @author liujiasen
 * @date 2015年5月18日
 */
@Entity
@Table(name = "sys_wxyh")
public class SysWxyh implements Serializable {

	/** id */
	private String id;

	/** 用户ID */
	private String yhid;

	/** 微信号 */
	private String wxh;

	/** 分组ID */
	private String fzid;

	/** 微信昵称 */
	private String wxnc;
	
	/** 性别 */
	private Integer xb;
	
	/** 用户的语言 */
	private String yhyy;

	/** 用户所在城市 */
	private String yhszcs;
	
	/** 用户所在省份 */
	private String yhszsf;
	
	/** 用户所在国家 */
	private String yhszgj;
	
	/** 用户头像 */
	private String yhtx;
	
	private String unionid;
	
	/** 用户备注名*/
	private String yhbzm;

	/** 创建时间*/
	private Date cjsj;
	
	/** 关注事件
	private String yhgzsj;

	/** 状态 */
	private Integer zt;

	/** 备注 */
	private String bz;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "yhid", length = 36, nullable = true)
	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	@Column(name = "wxh", length = 150, nullable = true)
	public String getWxh() {
		return wxh;
	}

	public void setWxh(String wxh) {
		this.wxh = wxh;
	}

	@Column(name = "fzid", length = 36, nullable = true)
	public String getFzid() {
		return fzid;
	}

	public void setFzid(String fzid) {
		this.fzid = fzid;
	}

	@Column(name = "wxnc", length = 50, nullable = true)
	public String getWxnc() {
		return wxnc;
	}

	public void setWxnc(String wxnc) {
		this.wxnc = wxnc;
	}
	
	@Column(name = "xb", nullable = true)
	public Integer getXb() {
		return xb;
	}

	public void setXb(Integer xb) {
		this.xb = xb;
	}

	@Column(name = "yhyy", length = 100, nullable = true)
	public String getYhyy() {
		return yhyy;
	}

	public void setYhyy(String yhyy) {
		this.yhyy = yhyy;
	}

	@Column(name = "yhszcs", length = 100, nullable = true)
	public String getYhszcs() {
		return yhszcs;
	}

	public void setYhszcs(String yhszcs) {
		this.yhszcs = yhszcs;
	}

	@Column(name = "yhszsf", length = 100, nullable = true)
	public String getYhszsf() {
		return yhszsf;
	}

	public void setYhszsf(String yhszsf) {
		this.yhszsf = yhszsf;
	}

	@Column(name = "yhszgj", length = 100, nullable = true)
	public String getYhszgj() {
		return yhszgj;
	}

	public void setYhszgj(String yhszgj) {
		this.yhszgj = yhszgj;
	}

	@Column(name = "yhtx", length = 200, nullable = true)
	public String getYhtx() {
		return yhtx;
	}

	public void setYhtx(String yhtx) {
		this.yhtx = yhtx;
	}

	@Column(name = "unionid", length = 100, nullable = true)
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(name = "yhbzm", length = 200, nullable = true)
	public String getYhbzm() {
		return yhbzm;
	}

	public void setYhbzm(String yhbzm) {
		this.yhbzm = yhbzm;
	}

	@Column(name = "cjsj", length = 19, nullable = true)
	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	@Column(name = "zt", nullable = true)
	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	@Column(name = "bz", length = 150, nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
