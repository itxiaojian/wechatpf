package com.sliu.framework.app.dto.message.event;

/**\
 * 自定义菜单事件    //注： 点击view类型（跳转到URL）的菜单按钮时，微信服务器不会推送自定义菜单事件。
 * 
 * @author ZDD
 * @date 2014-04-21
 */
public class MenuEvent extends BaseEvent {
	
	//事件KEY值，与自定义菜单接口中的KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	

}
