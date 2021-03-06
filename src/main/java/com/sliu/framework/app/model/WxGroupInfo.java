/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */
package com.sliu.framework.app.model;
// Generated 2014-11-5 15:27:27 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxGroupInfo generated by hbm2java
 */
@Entity
@Table(name="WX_GROUP_INFO"
)
public class WxGroupInfo  implements java.io.Serializable {


     private Long groupId;
     private String name;
     private Integer GCount;
     private String memo;

    public WxGroupInfo() {
    }

	
    public WxGroupInfo(Long groupId) {
        this.groupId = groupId;
    }
    public WxGroupInfo(Long groupId, String name, Integer GCount, String memo) {
       this.groupId = groupId;
       this.name = name;
       this.GCount = GCount;
       this.memo = memo;
    }
   
     @Id 
    
    @Column(name="GROUP_ID", unique=true, nullable=false)
    public Long getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    @Column(name="NAME", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="G_COUNT")
    public Integer getGCount() {
        return this.GCount;
    }
    
    public void setGCount(Integer GCount) {
        this.GCount = GCount;
    }
    
    @Column(name="MEMO", length=500)
    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }




}
