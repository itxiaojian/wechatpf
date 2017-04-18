package com.sliu.framework.app.calmutil;

import java.util.List;
import java.util.Map;

import com.sliu.framework.app.calmutil.filter.core.FilterService;
import com.sliu.framework.app.spring.SpringApplicationContextHolder;
import com.sliu.framework.app.sys.service.SysGjzglService;

/** 
 * @author:zhangyi 
 * @version 创建时间：2016年8月23日 下午5:01:22 
 * 工具类
 */
public class CalmUtil {
	
	//引入关键字管理service
	private static SysGjzglService sysGjzglService = (SysGjzglService) SpringApplicationContextHolder.getBean(SysGjzglService.class);
	
	/**
	 * 过滤关键字
	 * @author:zhangyi 
	 * @version 创建时间：2016年8月23日 下午5:05:09 
	 * @return
	 */
	public static String filterGjz(String strValue) {
		List<Map<String, Object>> listgjz = sysGjzglService.getSysGjzglListAll();//获取关键字列表
		if(listgjz.size()>0){
			if(strValue!=null){
				String[] keywords = new String[listgjz.size()];
				for(int i=0 ;i<listgjz.size();i++){
					keywords[i] = listgjz.get(i).get("gjz").toString();
				}
				FilterService filterService = new FilterService(keywords);
				String newstr = filterService.filter(strValue);
				return newstr;
			}
		}else{
			System.out.println("----暂无关键字过滤---");
			return strValue;
		}
		return strValue;
	}
	
}
