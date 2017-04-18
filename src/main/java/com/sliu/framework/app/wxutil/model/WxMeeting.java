/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */
package com.sliu.framework.app.wxutil.model;
// Generated 2014-11-5 15:27:27 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

/**
 * 微会议
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午2:33:49
 */
@Entity
@Table(name="WX_MEETING")
public class WxMeeting  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String hybt;
	private String hynr;
	private DateTime kssj;
	private DateTime jhjssj;
	private DateTime sjjssj;
	private String cjr;
	private Integer hyzt;
	private Integer hyrs;
	private String bz;

    public WxMeeting() {
    }

	
    public WxMeeting(Long id) {
        this.id = id;
    }
    
    public WxMeeting(Long id,String hybt,String hynr,DateTime kssj,DateTime jhjssj,DateTime sjjssj,String cjr,Integer hyzt,Integer hyrs,String bz) {
       this.id = id;
       this.hybt = hybt;
       this.hynr = hynr;
       this.kssj = kssj;
       this.jhjssj = jhjssj;
       this.sjjssj = sjjssj;
       this.cjr = cjr;
       this.hyzt = hyzt;
       this.hyrs = hyrs;
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

	
	@Column(name="HYBT", length=100)
	public String getHybt() {
		return hybt;
	}


	public void setHybt(String hybt) {
		this.hybt = hybt;
	}


	@Column(name="HYNR", length=1000)
	public String getHynr() {
		return hynr;
	}


	public void setHynr(String hynr) {
		this.hynr = hynr;
	}

	@Column(name="KSSJ", length=30)
	public DateTime getKssj() {
		return kssj;
	}


	public void setKssj(DateTime kssj) {
		this.kssj = kssj;
	}

	@Column(name="JHJSSJ", length=30)
	public DateTime getJhjssj() {
		return jhjssj;
	}


	public void setJhjssj(DateTime jhjssj) {
		this.jhjssj = jhjssj;
	}

	@Column(name="SJJSSJ", length=30)
	public DateTime getSjjssj() {
		return sjjssj;
	}


	public void setSjjssj(DateTime sjjssj) {
		this.sjjssj = sjjssj;
	}

	@Column(name="CJR", length=36)
	public String getCjr() {
		return cjr;
	}


	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	@Column(name="HYZT")
	public Integer getHyzt() {
		return hyzt;
	}


	public void setHyzt(Integer hyzt) {
		this.hyzt = hyzt;
	}

	@Column(name="HYRS")
	public Integer getHyrs() {
		return hyrs;
	}


	public void setHyrs(Integer hyrs) {
		this.hyrs = hyrs;
	}

	@Column(name="BZ", length=500)
	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}
    
    

}
