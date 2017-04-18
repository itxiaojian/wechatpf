/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */
package com.sliu.framework.app.model;
// Generated 2014-12-4 11:16:45 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * WxQukuanInfo generated by hbm2java
 */
@Entity
@Table(name="WX_QUKUAN_INFO"
)
public class WxQukuanInfo  implements java.io.Serializable {


     private Long ckId;
     private String openid;
     private String khmc;
     private String khzh;
     private String lxdh;
     private Date tkrq;
     private BigDecimal tkje;
     private String tkwd;
     private String remark;
     private Date createTime;

    public WxQukuanInfo() {
    }

	
    public WxQukuanInfo(Long ckId) {
        this.ckId = ckId;
    }
    public WxQukuanInfo(Long ckId, String openid, String khmc, String khzh, String lxdh, Date tkrq, BigDecimal tkje, String tkwd, String remark, Date createTime) {
       this.ckId = ckId;
       this.openid = openid;
       this.khmc = khmc;
       this.khzh = khzh;
       this.lxdh = lxdh;
       this.tkrq = tkrq;
       this.tkje = tkje;
       this.tkwd = tkwd;
       this.remark = remark;
       this.createTime = createTime;
    }
   
    @Id 
    @GeneratedValue(generator = "tableGenerator")     
    @GenericGenerator(name = "tableGenerator", strategy="com.sliu.framework.app.common.dao.key.SequenceGenerator")      
    @Column(name="CK_ID", unique=true, nullable=false)
    public Long getCkId() {
        return this.ckId;
    }
    
    public void setCkId(Long ckId) {
        this.ckId = ckId;
    }
    
    @Column(name="OPENID", length=100)
    public String getOpenid() {
        return this.openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    @Column(name="KHMC", length=100)
    public String getKhmc() {
        return this.khmc;
    }
    
    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }
    
    @Column(name="KHZH", length=100)
    public String getKhzh() {
        return this.khzh;
    }
    
    public void setKhzh(String khzh) {
        this.khzh = khzh;
    }
    
    @Column(name="LXDH", length=50)
    public String getLxdh() {
        return this.lxdh;
    }
    
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="TKRQ", length=10)
    public Date getTkrq() {
        return this.tkrq;
    }
    
    public void setTkrq(Date tkrq) {
        this.tkrq = tkrq;
    }
    
    @Column(name="TKJE", precision=15)
    public BigDecimal getTkje() {
        return this.tkje;
    }
    
    public void setTkje(BigDecimal tkje) {
        this.tkje = tkje;
    }
    
    @Column(name="TKWD", length=100)
    public String getTkwd() {
        return this.tkwd;
    }
    
    public void setTkwd(String tkwd) {
        this.tkwd = tkwd;
    }
    
    @Column(name="REMARK", length=500)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME", length=26)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }




}