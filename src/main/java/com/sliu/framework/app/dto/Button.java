package com.sliu.framework.app.dto;

/**
 * 按钮的基类  （所有一级菜单、二级菜单都共有一个相同的属性，那就是name）
 * 
 * @author ZDD
 * @date 2014-04-02
 */
public class Button {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	

}
