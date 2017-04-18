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
 * 系统用户
 * 
 * @author 
 * @since 2015-04-09
 */
@Entity
@Table(name = "sys_yh")
public class SysYh implements Serializable, UserDetails {

	/** yhbh */
	private String yhbh;

	/** 用户名 */
	private String xm;

	/** 登录名 */
	private String username;

	/** 密码 */
	private String password;

	/** 邮箱 */
	private String yx;

	/** 最后登录时间 */
	private Date zhdlsj;

	/** 状态(0:已删除,1:启用,2:禁用) */
	private Integer yhzt;
	
	/** 部门编号*/
	private String bmbh;
	
	/** 岗位编号*/
	private String gwbh;
	
	/** 手机号*/
	private String sjh;
	
//	private String userCode;

	private Set<SysJs> sysJs = new HashSet<SysJs>();

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "yhbh", length = 36, nullable = false)
	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	@ManyToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinTable(name = "sys_yhjs", joinColumns = { @JoinColumn(name = "yhbh") }, inverseJoinColumns = { @JoinColumn(name = "jsbh") })
	public Set<SysJs> getSysJs() {
		return sysJs;
	}

	public void setSysJs(Set<SysJs> sysJs) {
		this.sysJs = sysJs;
	}

	@Column(name = "xm", length = 255, nullable = false)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "dlm", length = 255, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "mm", length = 255, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "yx", length = 50, nullable = true)
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}

	@Column(name = "zhdlsj", length = 19, nullable = true)
	public Date getZhdlsj() {
		return zhdlsj;
	}

	public void setZhdlsj(Date zhdlsj) {
		this.zhdlsj = zhdlsj;
	}

	@Column(name = "yhzt", length = 10, nullable = true)
	public Integer getYhzt() {
		return yhzt;
	}

	public void setYhzt(Integer yhzt) {
		this.yhzt = yhzt;
	}
	
//	@Column(name = "user_code", length = 12, nullable = true)
//	public String getUserCode() {
//		return userCode;
//	}
//
//	public void setUserCode(String userCode) {
//		this.userCode = userCode;
//	}

	@Column(name = "bmbh", length = 20, nullable = true)
	public String getBmbh() {
		return bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	@Column(name = "gwbh", length = 20, nullable = true)
	public String getGwbh() {
		return gwbh;
	}

	public void setGwbh(String gwbh) {
		this.gwbh = gwbh;
	}

	@Column(name = "sjh", length = 20, nullable = true)
	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	@Transient
	public boolean isEnabled() {
		return this.yhzt == 1 ? true : false;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		return (Collection) sysJs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof SysYh))
			return false;

		return username.equals(((SysYh) obj).getUsername());
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}
}
