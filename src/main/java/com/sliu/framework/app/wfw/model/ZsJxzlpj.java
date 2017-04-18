package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教学质量评价
 * @author duanpeijun
 * @version 创建时间：2015年6月15日
 */
@Entity
@Table(name = "zs_jxzlpj")
public class ZsJxzlpj  implements Serializable{

	private Long id;//主键
	
	private Long pjid;//评教ID
	
	private String xh;//序号
	
	private String zsskqk;//准时上课情况
	
	private String ktqfpj;//课堂气氛评价
	
	private String sshdpj;//师生互动评价
	
	private String jstdpj;//教师态度评价
	
	private String zysppj;//专业水平评价
	
	private Date tjsj;//添加时间
	
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

	@Column(name = "pjid", nullable = false)
	public Long getPjid() {
		return pjid;
	}

	public void setPjid(Long pjid) {
		this.pjid = pjid;
	}

	@Column(name = "xh", nullable = true)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "zsskqk", nullable = false)
	public String getZsskqk() {
		return zsskqk;
	}

	public void setZsskqk(String zsskqk) {
		this.zsskqk = zsskqk;
	}

	@Column(name = "ktqfpj", nullable = false)
	public String getKtqfpj() {
		return ktqfpj;
	}

	public void setKtqfpj(String ktqfpj) {
		this.ktqfpj = ktqfpj;
	}

	@Column(name = "sshdpj", nullable = false)
	public String getSshdpj() {
		return sshdpj;
	}

	public void setSshdpj(String sshdpj) {
		this.sshdpj = sshdpj;
	}

	@Column(name = "jstdpj", nullable = false)
	public String getJstdpj() {
		return jstdpj;
	}

	public void setJstdpj(String jstdpj) {
		this.jstdpj = jstdpj;
	}

	@Column(name = "zysppj", nullable = false)
	public String getZysppj() {
		return zysppj;
	}

	public void setZysppj(String zysppj) {
		this.zysppj = zysppj;
	}

	@Column(name = "tjsj", nullable = true)
	public Date getTjsj() {
		return tjsj;
	}

	public void setTjsj(Date tjsj) {
		this.tjsj = tjsj;
	}

	@Column(name = "bz", nullable = true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
