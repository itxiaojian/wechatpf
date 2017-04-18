/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */
package com.sliu.framework.app.wxutil.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户分组
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午2:33:49
 */
@Entity
@Table(name="WX_YHFZ")
public class WxYhfz  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String fzmc;
	private String cjr;
	private Date tjsj;
	private String bz;

    public WxYhfz() {
    }

    public WxYhfz(Long id) {
        this.id = id;
    }
    
    public WxYhfz(Long id,String fzmc,String cjr,Date tjsj,String bz) {
       this.id = id;
       this.fzmc = fzmc;
       this.cjr = cjr;
       this.tjsj = tjsj;
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

	
	@Column(name="FZMC", length=100)
	public String getFzmc() {
		return fzmc;
	}
	
	public void setFzmc(String fzmc){
		this.fzmc = fzmc;
	}

	@Column(name="CJR", length=36)
	public String getCjr() {
		return cjr;
	}


	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	@Column(name="TJSJ")
	public Date getTjsj() {
		return tjsj;
	}

	public void setTjsj(Date tjsj) {
		this.tjsj = tjsj;
	}

	@Column(name="BZ", length=500)
	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}
    
    

}
