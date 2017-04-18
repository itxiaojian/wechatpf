package com.sliu.framework.app.wzy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 主页--学院新闻
 * @author zhangyi
 * @version 创建时间：2015年6月3日  下午3:48:10
 */
@Entity
@Table(name = "ZY_XYXW")
public class ZyXyxw implements Serializable {

	/** id */
	private Long id;

	/** 新闻标题*/
	private String xwbt;

	/** 新闻注释 */
	private String xwzs;

	/** 新闻内容 */
	private String xwnr;

	/** 生效时间 */
	private Date sxsj;

	/** 过期时间*/
	private String gqsj;

	/** 发布人*/
	private String fbr;

	/** 发布人姓名 */
	private String fbrxm;
	
	/** 部门名称*/
	private String bmmc;
	
	/** 发布部门 */
	private String bmbh;
	
	/** 新闻类型 */
	private String xwlx;
	
	/** 新闻状态 */
	private String xwzt;
	
	/**排序*/
	private Integer px;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "xwbt", length = 50, nullable = false)
	public String getXwbt() {
		return xwbt;
	}

	public void setXwbt(String xwbt) {
		this.xwbt = xwbt;
	}

	@Column(name = "xwzs", length = 200, nullable = false)
	public String getXwzs() {
		return xwzs;
	}

	public void setXwzs(String xwzs) {
		this.xwzs = xwzs;
	}

	@Column(name = "xwnr", length = 8000, nullable = false)
	public String getXwnr() {
		return xwnr;
	}

	public void setXwnr(String xwnr) {
		this.xwnr = xwnr;
	}

	@Column(name = "sxsj", length = 19, nullable = true)
	public Date getSxsj() {
		return sxsj;
	}

	public void setSxsj(Date sxsj) {
		this.sxsj = sxsj;
	}

	@Column(name = "gqsj", length = 19, nullable = true)
	public String getGqsj() {
		return gqsj;
	}

	public void setGqsj(String gqsj) {
		this.gqsj = gqsj;
	}

	@Column(name = "fbr", length = 36, nullable = false)
	public String getFbr() {
		return fbr;
	}

	public void setFbr(String fbr) {
		this.fbr = fbr;
	}

	@Column(name = "fbrxm", length = 20)
	public String getFbrxm() {
		return fbrxm;
	}

	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}

	@Column(name = "bmmc", length = 100)
	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	@Column(name = "bmbh", length = 36, nullable = true)
	public String getBmbh() {
		return bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	@Column(name = "xwlx", length = 2, nullable = true)
	public String getXwlx() {
		return xwlx;
	}

	public void setXwlx(String xwlx) {
		this.xwlx = xwlx;
	}

	@Column(name = "xwzt", length = 2, nullable = true)
	public String getXwzt() {
		return xwzt;
	}

	public void setXwzt(String xwzt) {
		this.xwzt = xwzt;
	}

	@Column(name = "px", nullable = true)
	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}

	
}
