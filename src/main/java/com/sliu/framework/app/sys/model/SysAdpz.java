package com.sliu.framework.app.sys.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * ad服务器配置
 * @author : zhangyi
 * @version 创建时间：2015年10月23日 下午2:22:30
 */
@Entity
@Table(name = "sys_adpz")
public class SysAdpz implements Serializable {

	/** 主键 */
	private Long id;

	/** 服务器地址 */
	private String fwqdz;

	/** 端口号 */
	private String dkh;

	/** 同步用户名 */
	private String tbyhm;

	/** 同步密码 */
	private String tbmm;

	/** 修改时间 */
	private Date xgsj;
	
	/** 修改人*/
	private String xgr;
	
	/** 登录前缀*/
	private String dlqz;
	
	/** 域节点*/
	private String yjd;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="FWQDZ", length=50, nullable=true)
	public String getFwqdz() {
		return fwqdz;
	}

	public void setFwqdz(String fwqdz) {
		this.fwqdz = fwqdz;
	}

	@Column(name="DKH", length=50, nullable=true)
	public String getDkh() {
		return dkh;
	}

	public void setDkh(String dkh) {
		this.dkh = dkh;
	}

	@Column(name="TBYHM", length=50, nullable=true)
	public String getTbyhm() {
		return tbyhm;
	}

	public void setTbyhm(String tbyhm) {
		this.tbyhm = tbyhm;
	}

	@Column(name="TBMM", length=50, nullable=true)
	public String getTbmm() {
		return tbmm;
	}

	public void setTbmm(String tbmm) {
		this.tbmm = tbmm;
	}

	@Column(name="XGSJ", length=19, nullable=true)
	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	@Column(name="XGR", length=50, nullable=true)
	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	@Column(name="DLQZ", length=50, nullable=true)
	public String getDlqz() {
		return dlqz;
	}

	public void setDlqz(String dlqz) {
		this.dlqz = dlqz;
	}

	@Column(name="YJD", length=150, nullable=true)
	public String getYjd() {
		return yjd;
	}

	public void setYjd(String yjd) {
		this.yjd = yjd;
	}
	
}
