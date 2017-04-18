package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 回复内容表
 * @author liujiansen
 * @since  2015-06-04
 */
@Entity
@Table(name="wj_replay")
public class WjReplay implements Serializable{
	
	/**回复ID*/
	private Integer replayid;
	
	/**回复者代码*/
	private String replaycode;
	
	/**回复者IP*/
	private String replayip;
	
	/**主题Id*/
	private Integer oid;
	
	/**回复时间*/
	private java.util.Date replaytime;
	
	/**备注*/
	private String remark;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="replayId", length=10, nullable=true)
	public Integer getReplayid() {
		return replayid;
	}
	
	public void setReplayid(Integer replayid) {
		this.replayid = replayid;
	}
	
	@Column(name="replayCode", length=100, nullable=true)
	public String getReplaycode() {
		return replaycode;
	}
	
	public void setReplaycode(String replaycode) {
		this.replaycode = replaycode;
	}
	
	@Column(name="replayIp", length=100, nullable=true)
	public String getReplayip() {
		return replayip;
	}
	
	public void setReplayip(String replayip) {
		this.replayip = replayip;
	}
	
	@Column(name="oid", length=10, nullable=true)
	public Integer getOid() {
		return oid;
	}
	
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	
	@Column(name="replayTime", length=19, nullable=false)
	public java.util.Date getReplaytime() {
		return replaytime;
	}
	
	public void setReplaytime(java.util.Date replaytime) {
		this.replaytime = replaytime;
	}
	
	@Column(name="remark", length=200, nullable=false)
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
