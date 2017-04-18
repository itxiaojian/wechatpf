/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */
package com.sliu.framework.app.process.model;
// Generated 2014-6-11 9:35:04 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * zhangyi
 */
@Entity
@Table(name="TEST_PROCESS"
)
public class TestProcess  implements java.io.Serializable {


     private String id;
     private String name;
     private String processcode;
     private String processStatus;
     private String procInstId;

    public TestProcess() {
    }

	
    public TestProcess(String id) {
        this.id = id;
    }
    public TestProcess(String id, String name, String processcode, String processStatus, String procInstId) {
       this.id = id;
       this.name = name;
       this.processcode = processcode;
       this.processStatus = processStatus;
       this.procInstId = procInstId;
    }
   
//    @Id 
//    @GeneratedValue(generator = "tableGenerator")     
//   	@GenericGenerator(name = "tableGenerator", strategy="com.unteck.common.dao.key.SequenceGenerator") 
//    @Column(name="ID", unique=true, nullable=false)
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", length = 36, nullable = false)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="NAME", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="PROCESSCODE", length=100)
    public String getProcesscode() {
        return this.processcode;
    }
    
    public void setProcesscode(String processcode) {
        this.processcode = processcode;
    }
    
    @Column(name="PROCESS_STATUS", length=10)
    public String getProcessStatus() {
        return this.processStatus;
    }
    
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }
    
    @Column(name="PROC_INST_ID", length=100)
    public String getProcInstId() {
        return this.procInstId;
    }
    
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }




}
