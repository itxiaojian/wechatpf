/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package com.sliu.framework.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * @author bsli123@starit.com.cn
 * @date 2012-2-3 下午3:11:24
 */
public class AjaxUtil {
	
    private static final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static  String formatDate(Date date)throws ParseException{
        return sdf.format(date);
    }
    
    public static Date parse(String strDate) throws ParseException{

        return sdf.parse(strDate);
    }
	
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}
	
	public static String getRemoteAddr(HttpServletRequest request) {  
	    String remoteIp = request.getHeader("X-Real-IP"); //nginx反向代理  
	    if (StringUtils.hasText(remoteIp)) {  
	        return remoteIp;  
	    } else {  
	        remoteIp = request.getHeader("x-forwarded-for");//apache反射代理  
	        if (StringUtils.hasText(remoteIp)) {  
	            String[] ips = remoteIp.split(",");  
	            for (String ip : ips) {  
	                if (!"null".equalsIgnoreCase(ip)) {  
	                    return ip;  
	                }  
	            }  
	        }  
	        return request.getRemoteAddr();  
	    }  
	}
	
    public   static   String StringFilter(String   str)   throws   PatternSyntaxException   {     
	        // 只允许字母和数字       
	        // String   regEx  =  "[^a-zA-Z0-9]";                     
	           // 清除掉所有特殊字符  
		  String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
		  Pattern   p   =   Pattern.compile(regEx);     
		  Matcher   m   =   p.matcher(str);     
		  return   m.replaceAll("").trim();     
	}  
    
    

	private AjaxUtil() {}
}
