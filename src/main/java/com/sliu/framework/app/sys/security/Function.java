package com.sliu.framework.app.sys.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;

/**
 * 系统功能 sys_function
 * 
 * @author liuxiantao
 * 
 */
public class Function implements java.io.Serializable {
	/* id */
	private Long id;

	private Long parentId;

	/* name */
	private String name;

	private String url;

	/* 内置角色名称 */
	private List<String> rolenames;

	private String iconcls;

	private Integer orderno;

	private Collection<ConfigAttribute> authorities;

	public Collection<ConfigAttribute> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<ConfigAttribute> authorities) {
		this.authorities = authorities;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getRolenames() {
		return rolenames;
	}

	public void setRolenames(List<String> rolenames) {
		this.rolenames = rolenames;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}
}
