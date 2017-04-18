package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sh_whbym")
public class ShWhbYm implements Serializable{
/**
*
*
@Author oufeng	
@Date 2015年7月6日 下午12:12:09
@Version 1.0
*/
	/**id*/
	private Long id;
	
	/**海报id*/
	private Long hbid;

	/**模板id*/
	private String mbid;
	
	/**封面主题*/
	private String fmzt;
	
	/**封面正文*/
	private String fmzw;
	
	/**封面图片*/
	private String fmtp;
	
	/**备注*/
	private String bz;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=100, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	 @Column(name="HBID", length=100, nullable=true)
	public Long getHbid() {
		return hbid;
	}

	public void setHbid(Long hbid) {
		this.hbid = hbid;
	}

	 @Column(name="MBID", length=100, nullable=true)
	public String getMbid() {
		return mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	 @Column(name="FMZT", length=100, nullable=true)
	public String getFmzt() {
		return fmzt;
	}

	public void setFmzt(String fmzt) {
		this.fmzt = fmzt;
	}

	 @Column(name="FMZW", length=300, nullable=true)
	public String getFmzw() {
		return fmzw;
	}

	public void setFmzw(String fmzw) {
		this.fmzw = fmzw;
	}
	
	 @Column(name="FMTP", length=300, nullable=true)
	public String getFmtp() {
		return fmtp;
	}

	public void setFmtp(String fmtp) {
		this.fmtp = fmtp;
	}
	
	@Column(name="BZ", length=256, nullable=true)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}