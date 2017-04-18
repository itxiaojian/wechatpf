package com.sliu.framework.app.dto;

/**
 * view类型的菜单
 * 
 * @author ZDD
 * @date 2014-04-18
 *
 */
public class ViewButton extends Button {
	
	private String type;  
    private String url;
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	} 
    
    

}
