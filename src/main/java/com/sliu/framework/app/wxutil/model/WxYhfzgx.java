/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */
package com.sliu.framework.app.wxutil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户分组关系
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午2:33:49
 */
@Entity
@Table(name="WX_YHFZGX")
public class WxYhfzgx  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long fzid;
	private String rybh;
	private String rymc;
	private String bmbh;
	private String openid;
	private String bz;

    public WxYhfzgx() {
    }

    public WxYhfzgx(Long id) {
        this.id = id;
    }
    
    public WxYhfzgx(Long id,Long fzid,String rybh,String rymc,String bmbh,String openid,String bz) {
       this.id = id;
       this.fzid = fzid;
       this.rybh = rybh;
       this.rymc = rymc;
       this.bmbh = bmbh;
       this.openid = openid;
       this.bz = bz;
    }
   
    @Id 
    @GeneratedValue(generator = "tableGenerator")     
    @GenericGenerator(name = "tableGenerator", strategy="com.sliu.framework.app.common.dao.key.SequenceGenerator")       
    @Column(name="ID", unique=true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="FZID")
	public Long getFzid() {
		return fzid;
	}

	public void setFzid(Long fzid) {
		this.fzid = fzid;
	}

	@Column(name="RYBH", length=32)
	public String getRybh() {
		return rybh;
	}

	public void setRybh(String rybh) {
		this.rybh = rybh;
	}

	@Column(name="RYMC", length=32)
	public String getRymc() {
		return rymc;
	}

	public void setRymc(String rymc) {
		this.rymc = rymc;
	}

	@Column(name="BMBH", length=32)
	public String getBmbh() {
		return bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	@Column(name="OPENID", length=200)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name="BZ", length=500)
	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}
    
    

}
