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

/**
 * 微信摇一摇周边信息
 * @author : zhangyi
 * @version 创建时间：2016年3月22日 下午2:06:05
 */
@Entity
@Table(name="WX_SHAKE_INFO"
)
public class WxShakeInfo  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String uuid;
	private String major;
	private String minor;
	private String distance;
	private String pageId;
	private String openid;
	private String poiId;
	private String bz;

    public WxShakeInfo() {
    }

	
    public WxShakeInfo(Long id) {
        this.id = id;
    }
    
    public WxShakeInfo(Long id, String uuid,String major,String minor,String distance,String pageId,String openid,String poiId,String bz) {
       this.id = id;
       this.uuid = uuid;
       this.major = major;
       this.minor = minor;
       this.distance = distance;
       this.pageId = pageId;
       this.openid = openid;
       this.poiId = poiId;
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

    @Column(name="UUID", length=100)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

    @Column(name="MAJOR", length=100)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name="MINOR", length=100)
	public String getMinor() {
		return minor;
	}


	public void setMinor(String minor) {
		this.minor = minor;
	}

	@Column(name="DISTANCE", length=100)
	public String getDistance() {
		return distance;
	}


	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Column(name="PAGE_ID", length=100)
	public String getPageId() {
		return pageId;
	}


	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	@Column(name="OPENID", length=200)
	public String getOpenid() {
		return openid;
	}


	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name="POI_ID", length=100)
	public String getPoiId() {
		return poiId;
	}


	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}

	@Column(name="BZ", length=500)
	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}
    
    

}
