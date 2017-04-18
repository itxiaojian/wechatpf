package com.sliu.framework.app.wtx.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 职工工资
 * @author oufeng
 * @date 2016年8月4日
 */
@Entity
@Table(name = "zs_zggz")
public class TxZggz {
	
	/** id */
	private Long id;

	/**年份*/
	private String nf;
	
	/**月份*/
	private String yf;
	
	/** 工号 */
	private String gh;
	
	/** 姓名 */
	private String xm;

	/** 薪级 */
	private String xj;
	
	/** 岗位*/
	private  String gw;
	
	/** 基础性绩效*/
	private BigDecimal  jcxjx;
	
	/** 应发合计 */
	private BigDecimal yfhj;
	
	/** 医保*/
	private BigDecimal yb;
	
	/** 公会费 */
	private BigDecimal ghf;
	
	/** 公积金 */
	private BigDecimal gjj;
	
	/** 预扣养老金*/
	private BigDecimal ykylj;
	
	/** 应税工资*/
	private BigDecimal ysgz;
	
	/** 个人所得税*/
	private BigDecimal grsds;
	
	/** 扣款合计*/
	private BigDecimal kkhj;
	
	/** 实发合计*/
	private BigDecimal sfhj;
	
	/** 工资发放时间*/
	private Date gzffsj;
	
	/** 基本工资*/
	private BigDecimal jbgz;
	
	/** 监考补助*/
	private BigDecimal jkbz;
	
	/** 坐班补助*/
	private BigDecimal zbbz;
	
	/** 上课补助*/
	private BigDecimal skbz;
	
	/** 论文补助*/
	private BigDecimal lwbz;
	
	/** 工资合计*/
	private BigDecimal gzhj;
	
	/** 社保扣除*/
	private BigDecimal sbkc;
	
	/** 公积金扣除*/
	private BigDecimal gjjkc;
	
	/** 实发工资*/
	private BigDecimal sfgz;
	
	/** 备注*/
	private BigDecimal bz;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nf", length = 10, nullable = true)
	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	@Column(name = "yf", length = 10, nullable = true)
	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	@Column(name = "gh", length=30, nullable = true)
	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	@Column(name = "xm", length = 30, nullable = true)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "xj", length = 10, nullable = true)
	public String getXj() {
		return xj;
	}

	public void setXj(String xj) {
		this.xj = xj;
	}

	@Column(name = "gw", length = 10, nullable = true)
	public String getGw() {
		return gw;
	}

	public void setGw(String gw) {
		this.gw = gw;
	}

	@Column(name = "jcxjx", nullable = true)
	public BigDecimal getJcxjx() {
		return jcxjx;
	}

	public void setJcxjx(BigDecimal jcxjx) {
		this.jcxjx = jcxjx;
	}

	@Column(name = "yfhj", nullable = true)
	public BigDecimal getYfhj() {
		return yfhj;
	}

	public void setYfhj(BigDecimal yfhj) {
		this.yfhj = yfhj;
	}

	@Column(name = "yb", nullable = true)
	public BigDecimal getYb() {
		return yb;
	}

	public void setYb(BigDecimal yb) {
		this.yb = yb;
	}

	@Column(name = "ghf", nullable = true)
	public BigDecimal getGhf() {
		return ghf;
	}

	public void setGhf(BigDecimal ghf) {
		this.ghf = ghf;
	}

	@Column(name = "gjj", nullable = true)
	public BigDecimal getGjj() {
		return gjj;
	}

	public void setGjj(BigDecimal gjj) {
		this.gjj = gjj;
	}

	@Column(name = "ykylj", nullable = true)
	public BigDecimal getYkylj() {
		return ykylj;
	}

	public void setYkylj(BigDecimal ykylj) {
		this.ykylj = ykylj;
	}

	@Column(name = "ysgz", nullable = true)
	public BigDecimal getYsgz() {
		return ysgz;
	}

	public void setYsgz(BigDecimal ysgz) {
		this.ysgz = ysgz;
	}

	@Column(name = "grsds", nullable = true)
	public BigDecimal getGrsds() {
		return grsds;
	}

	public void setGrsds(BigDecimal grsds) {
		this.grsds = grsds;
	}

	@Column(name = "kkhj", nullable = true)
	public BigDecimal getKkhj() {
		return kkhj;
	}

	public void setKkhj(BigDecimal kkhj) {
		this.kkhj = kkhj;
	}

	@Column(name = "sfhj", nullable = true)
	public BigDecimal getSfhj() {
		return sfhj;
	}

	public void setSfhj(BigDecimal sfhj) {
		this.sfhj = sfhj;
	}

	@Column(name = "gzffsj", nullable = true)
	public Date getGzffsj() {
		return gzffsj;
	}

	public void setGzffsj(Date gzffsj) {
		this.gzffsj = gzffsj;
	}

	@Column(name = "jbgz", nullable = true)
	public BigDecimal getJbgz() {
		return jbgz;
	}

	public void setJbgz(BigDecimal jbgz) {
		this.jbgz = jbgz;
	}

	@Column(name = "jkbz", nullable = true)
	public BigDecimal getJkbz() {
		return jkbz;
	}

	public void setJkbz(BigDecimal jkbz) {
		this.jkbz = jkbz;
	}

	@Column(name = "zbbz", nullable = true)
	public BigDecimal getZbbz() {
		return zbbz;
	}

	public void setZbbz(BigDecimal zbbz) {
		this.zbbz = zbbz;
	}

	@Column(name = "skbz", nullable = true)
	public BigDecimal getSkbz() {
		return skbz;
	}

	public void setSkbz(BigDecimal skbz) {
		this.skbz = skbz;
	}

	public BigDecimal getLwbz() {
		return lwbz;
	}

	public void setLwbz(BigDecimal lwbz) {
		this.lwbz = lwbz;
	}

	public BigDecimal getGzhj() {
		return gzhj;
	}

	public void setGzhj(BigDecimal gzhj) {
		this.gzhj = gzhj;
	}

	@Column(name = "sbkc", nullable = true)
	public BigDecimal getSbkc() {
		return sbkc;
	}

	public void setSbkc(BigDecimal sbkc) {
		this.sbkc = sbkc;
	}

	@Column(name = "gjjkc", nullable = true)
	public BigDecimal getGjjkc() {
		return gjjkc;
	}

	public void setGjjkc(BigDecimal gjjkc) {
		this.gjjkc = gjjkc;
	}

	@Column(name = "sfgz", nullable = true)
	public BigDecimal getSfgz() {
		return sfgz;
	}

	public void setSfgz(BigDecimal sfgz) {
		this.sfgz = sfgz;
	}

	@Column(name = "bz",length=500,nullable = true)
	public BigDecimal getBz() {
		return bz;
	}

	public void setBz(BigDecimal bz) {
		this.bz = bz;
	}

}
