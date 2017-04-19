package com.sliu.framework.app.wxutil.model;

import java.util.Map;


/** 
 * @author:zhangyi 
 * @version 创建时间：2016年4月15日 上午11:59:19 
 * 微信模板消息类 
 */

public class WxTemplate  implements java.io.Serializable { 
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
	 */
	private Long id;
    /**
     * 模板消息id
     */
    private String template_id;
    /**
     * 用户openId
     */
    private String touser;
    /**
     * URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）
     */
    private String url;
    /**
     * 标题颜色
     */
    private String topcolor;
    /**
     * 详细内容
     */
    private Map<String,TemplateDataView> data;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTouser() {
        return touser;
    }
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTopcolor() {
        return topcolor;
    }
    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }
    public Map<String, TemplateDataView> getData() {
        return data;
    }
    public void setData(Map<String, TemplateDataView> data) {
        this.data = data;
    }
}