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
 * 微会议人员
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午2:33:49
 */
@Entity
@Table(name="WX_MEET_MENBER")
public class WxMeetMenber  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long hyid;
	private String hyryid;
	private String ryxm;
	private Integer sfdc;
	private DateTime dcsj;
	private String bz;

    public WxMeetMenber() {
    }

	
    public WxMeetMenber(Long id) {
        this.id = id;
    }
    
    public WxMeetMenber(Long id,Long hyid,String hyryid,String ryxm,Integer sfdc,DateTime dcsj,String bz) {
       this.id = id;
       this.hyid = hyid;
       this.hyryid = hyryid;
       this.ryxm = ryxm;
       this.sfdc = sfdc;
       this.dcsj = dcsj;
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
	
	@Column(name="HYID", length=100)
	public Long getHyid() {
		return hyid;
	}


	public void setHyid(Long hyid) {
		this.hyid = hyid;
	}

	@Column(name="HYRYID", length=100)
	public String getHyryid() {
		return hyryid;
	}


	public void setHyryid(String hyryid) {
		this.hyryid = hyryid;
	}

	@Column(name="RYXM", length=100)
	public String getRyxm() {
		return ryxm;
	}


	public void setRyxm(String ryxm) {
		this.ryxm = ryxm;
	}

	@Column(name="SFDC", length=100)
	public Integer getSfdc() {
		return sfdc;
	}


	public void setSfdc(Integer sfdc) {
		this.sfdc = sfdc;
	}

	@Column(name="DCSJ", length=100)
	public DateTime getDcsj() {
		return dcsj;
	}


	public void setDcsj(DateTime dcsj) {
		this.dcsj = dcsj;
	}


	@Column(name="BZ", length=500)
	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}
    
}
