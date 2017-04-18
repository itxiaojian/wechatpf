package com.sliu.framework.app.wfw.controller;

import java.io.UnsupportedEncodingException;

import com.sliu.framework.app.support.PropertiesInfo;

/** 
 * 装换路径code
 * @author:zhangyi 
 * @version 创建时间：2015年6月11日 上午10:42:31 
 * 类说明 
 */
public class TestController {
   
	public static void main(String args[]){

		   String result1 = "http://wx.wxc.edu.cn:8080/wxpt/wzy/ZyXyxw/zhuye";//进入微主页
		   String result2 = "http://wx.wxc.edu.cn:8080/wxpt/wfw/zy/zhuye";//进入微服务
		   String result3 = "http://wx.wxc.edu.cn:8080/wxpt/wsh/zy/zhuye";//进入微生活
		   
//		   String result1 = "http://wx.zs-si.com:9080/wxpt/wzy/ZyXyxw/zhuye";//进入微主页
//		   String result2 = "http://wx.zs-si.com:9080/wxpt/wfw/zy/zhuye";//进入微服务
//		   String result3 = "http://wx.zs-si.com:9080/wxpt/wsh/zy/zhuye";//进入微生活5948123

           try {
                   result1 = java.net.URLEncoder.encode(result1,"utf-8");
                   result2 = java.net.URLEncoder.encode(result2,"utf-8");
                   result3 = java.net.URLEncoder.encode(result3,"utf-8");
           } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
           }
           
           System.out.println("-------result1-------->"+result1);
           System.out.println("-------result2-------->"+result2);
           System.out.println("-------result3-------->"+result3);
           String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result1+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
           String url2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result2+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
           String url3 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result3+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
           
           System.out.println("----url1---->"+url1);
           System.out.println("----url2---->"+url2);
           System.out.println("----url3---->"+url3);
           
           int c; 
           c = 2; 
           System.out.println(c); System.out.println(c++); System.out.println(c); 
           
           int[] arr= new int[10];

           System.out.println(arr[1]);
           
           System.err.println("5"+2);
           
	}
	
}
