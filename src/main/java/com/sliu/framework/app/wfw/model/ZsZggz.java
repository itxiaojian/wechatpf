package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
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
 * @author liujiasen
 * @date 2015年5月22日
 */
@Entity
@Table(name = "zs_zggz")
public class ZsZggz implements Serializable {

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
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "gh", length=30, nullable = true)
	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
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
}