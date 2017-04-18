package com.sliu.framework.app.wfw.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="zs_xsttkxx")
public class ZsXsttkxx implements Serializable{
	
	//主键id
	private Long id;
	
	//课程号
	private String kch;
	
	//周次
	private String zc;
	
	//节次
	private String jc;
	
	//地点
	private String dd;
	
	//任课老师
	private String rkjs;
	
	//任课教师工号
	private String rkjsgh;
	
	//调课时间
	private Date tksj;
	
	//调/停课标志
	private String ttkbz;
	
	//调停原因
	private String ttyy;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="kch", length=32, nullable=true)
	public String getKch() {
		return kch;
	}

	public void setKch(String kch) {
		this.kch = kch;
	}

	@Column(name="zc", length=10, nullable=true)
	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	@Column(name="jc", length=32, nullable=true)
	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	@Column(name="dd", length=60, nullable=true)
	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	@Column(name="rkjs", length=36, nullable=true)
	public String getRkjs() {
		return rkjs;
	}

	public void setRkjs(String rkjs) {
		this.rkjs = rkjs;
	}

	@Column(name="rkjsgh", length=36, nullable=true)
	public String getRkjsgh() {
		return rkjsgh;
	}

	public void setRkjsgh(String rkjsgh) {
		this.rkjsgh = rkjsgh;
	}

	@Column(name="tksj", nullable=true)
	public Date getTksj() {
		return tksj;
	}

	public void setTksj(Date tksj) {
		this.tksj = tksj;
	}

	@Column(name="ttkbz", length=2, nullable=true)
	public String getTtkbz() {
		return ttkbz;
	}

	public void setTtkbz(String ttkbz) {
		this.ttkbz = ttkbz;
	}

	@Column(name="ttyy", length=500, nullable=true)
	public String getTtyy() {
		return ttyy;
	}

	public void setTtyy(String ttyy) {
		this.ttyy = ttyy;
	}
}