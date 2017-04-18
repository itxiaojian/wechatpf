package com.sliu.framework.app.wzy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 主页轮播图
 * @author zhangyan
 * @since  2016-06-29
 */
@Entity
@Table(name="zy_tppz")
public class ZyTppz implements Serializable{

	/**主键*/
	private Long id;
	
	/**图片名称*/
	private String tpmc;
	
	/**图片保存id*/
	private Long tpbcid;
	
	/**图片类型*/
	private Integer lx;
	
	/**链接地址*/
	private String url;
	
	/**排序*/
	private Integer px;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="TPMC", length=200, nullable=true)
	public String getTpmc() {
		return tpmc;
	}
	
	public void setTpmc(String tpmc) {
		this.tpmc = tpmc;
	}
	
	@Column(name="TPBCID", length=500, nullable=true)
	public Long getTpbcid() {
		return tpbcid;
	}

	public void setTpbcid(Long tpbcid) {
		this.tpbcid = tpbcid;
	}

	@Column(name="LX", length=10, nullable=true)
	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	@Column(name="URL", length=36, nullable=true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="px", length=10, nullable=true)
	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}
}
