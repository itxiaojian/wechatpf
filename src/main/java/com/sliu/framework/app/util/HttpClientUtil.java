package com.sliu.framework.app.util;


import com.sliu.framework.app.util.HttpClientUtil;
import com.sliu.framework.app.util.MD5Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
*未读邮件
@Author oufeng	
@Date 2016年8月10日 下午15:02:48
@Version 1.0
*/
public class HttpClientUtil {
	
	 private static String url="http://mail.wxc.edu.cn/api/service/auth/get_token";
	
		public static String getToken1(String auth_key,String api_secret,String auth_timestamp){ 
			String str=api_secret+auth_key+auth_timestamp;
			String auth_signature = MD5Util.string2MD5(str);
	        String strResult = "";
	        try {
				PostMethod postMethod = new PostMethod(url);
				postMethod.addParameter("auth_key", auth_key);
				postMethod.addParameter("auth_timestamp", auth_timestamp);
				postMethod.addParameter("auth_signature", auth_signature);
				postMethod.addParameter("email", auth_key);
				postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				postMethod.addRequestHeader("Content-Length","131");

				HttpClient client = new HttpClient();
				client.executeMethod(postMethod);
				String responseDate = postMethod.getResponseBodyAsString();
				return responseDate;
      } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {
	            e.printStackTrace();  
	        }  
	        return strResult;  
	    }  
		
		
		 public static String getToken(String auth_key,String api_secret,String auth_timestamp){ 
			 String str=api_secret+auth_key+auth_timestamp;
			 String auth_signature = MD5Util.string2MD5(str);
		        DefaultHttpClient httpclient = new DefaultHttpClient();  
		        String smsUrl="http://mail.wxc.edu.cn/api/service/auth/get_token";  
		        HttpPost httppost = new HttpPost(smsUrl);  
		        String strResult = "";  
		        try {  
		                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
		                nameValuePairs.add(new BasicNameValuePair("auth_key", auth_key)); 
		                nameValuePairs.add(new BasicNameValuePair("auth_signature", auth_signature)); 
		                nameValuePairs.add(new BasicNameValuePair("auth_timestamp", auth_timestamp)); 
		                httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
		                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  
		                  
		                HttpResponse response = httpclient.execute(httppost);  
		                if (response.getStatusLine().getStatusCode() == 200) {  
		                    /*读返回数据*/  
		                    String conResult = EntityUtils.toString(response.getEntity()); 
		                    strResult= conResult;
		                   }
		                } catch (ClientProtocolException e) {  
		                    e.printStackTrace();  
		                } catch (IOException e) {  
		                    e.printStackTrace();  
		                }  
		        return strResult;  
		    }  
		 
		 
		 public static String getWdMail1(String auth_type,String auth_key,String auth_timestamp,
					String auth_token,String auth_signature,String email) throws UnsupportedEncodingException{
		        String strResult = "";
		        String str=" auth auth_key="+auth_key+" ,auth_timestamp="+auth_timestamp+" ,auth_token="+auth_token;
		        String url="http://mail.wxc.edu.cn/api/user/"+email+"/mail/-/unread?max-results=1";
		        try {
					PostMethod postMethod = new PostMethod(url);
					postMethod.addParameter("auth_type","auth");
					postMethod.addParameter("auth_key", auth_key);
					postMethod.addParameter("auth_token", auth_token);
					postMethod.addParameter("auth_timestamp", auth_timestamp);
					postMethod.addParameter("auth_signature", auth_signature);
					postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
					postMethod.addRequestHeader("Content-Length","131");
					postMethod.addRequestHeader("Authorization",str);
					HttpClient client = new HttpClient();
					client.executeMethod(postMethod);
					String responseDate = postMethod.getResponseBodyAsString();
				     System.out.println("result================="+responseDate);
					return responseDate;
		  } catch (ClientProtocolException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {
		            e.printStackTrace();  
		        }  
		        return strResult;  
		    }  
		    
		    public static String getWdMail2(String auth_type,String auth_key,String auth_timestamp,
					String auth_token,String auth_signature,String email) throws UnsupportedEncodingException{
		          String str=" auth auth_key="+auth_key+" ,auth_timestamp="+auth_timestamp+" ,auth_token="+auth_token
		        		  +",auth_signature="+auth_signature;
		          String url="http://mail.wxc.edu.cn/api/user/"+email+"/mail/-/unread?max-results=1";
		          DefaultHttpClient httpclient = new DefaultHttpClient();  
		          HttpPost httppost = new HttpPost(url);  
		          String strResult = "";  
		          try {  
		               List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		               nameValuePairs.add(new BasicNameValuePair("auth_type", "auth")); 
		               nameValuePairs.add(new BasicNameValuePair("auth_key", auth_key)); 
		               nameValuePairs.add(new BasicNameValuePair("auth_timestamp", auth_timestamp)); 
		               nameValuePairs.add(new BasicNameValuePair("auth_signature", auth_signature)); 
		               nameValuePairs.add(new BasicNameValuePair("auth_token", auth_token)); 
		              // nameValuePairs.add(new BasicNameValuePair("email", email)); 
		               httppost.addHeader("Content-type", "application/atom+xml;type=feed");
		               httppost.addHeader("Authorization", str);
		               httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  
		                 
		               HttpResponse response = httpclient.execute(httppost);  
		               if (response.getStatusLine().getStatusCode() == 200) {  
		                   /*读返回数据*/  
		                   String conResult = EntityUtils.toString(response.getEntity()); 
		                   System.out.println("conResult=================="+conResult);
		                  }
		               } catch (ClientProtocolException e) {  
		                   e.printStackTrace();  
		               } catch (IOException e) {  
		                   e.printStackTrace();  
		               }  
		       return strResult; 
		    }
		    
		    public static String getWdMail(String email) throws UnsupportedEncodingException{
		    	 String auth_key ="api@mail.wxc.edu.cn";
				 String api_secret ="2f1e535ab33f94758ec42e060714c8e2";
				 //系统当前的时间戳
				 String auth_timestamp = System.currentTimeMillis()/1000+"";
				 //获取token
				 String auth_token=HttpClientUtil.getToken(auth_key, api_secret,auth_timestamp);
				 //md5加密
				 String _str=api_secret+auth_key+auth_timestamp+auth_token;
				 String auth_signature = MD5Util.string2MD5(_str);
				 String auth_type="auth";
		    	//参数进行处理
		    	   auth_key=java.net.URLEncoder.encode(auth_key,"utf-8");
				   auth_type=java.net.URLEncoder.encode(auth_type,"utf-8");
				   auth_timestamp=java.net.URLEncoder.encode(auth_timestamp,"utf-8");
				   auth_signature=java.net.URLEncoder.encode(auth_signature,"utf-8");
				   auth_token=java.net.URLEncoder.encode(auth_token,"utf-8");
				 email=java.net.URLEncoder.encode(email,"utf-8");
				 //授权Authorization的值
		          String str=" auth auth_key="+auth_key+",auth_timestamp="+auth_timestamp+",auth_token="+auth_token
		        		 +",auth_signature="+auth_signature;
		          String params="&auth_type="+auth_type+"&auth_key="+auth_key+"&auth_timestamp="+auth_timestamp
		        		  +"&auth_token="+auth_token+"&auth_signature="+auth_signature;
		          String url="http://mail.wxc.edu.cn/api/user/"+email+"/mail/-/unread?max-results=10"+params;
		          HttpClient httpClient = new HttpClient();
		          httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		          GetMethod getMethod = new GetMethod(url);
		          // 设置 get 请求超时为 5 秒
		          getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		          // 设置请求重试处理，用的是默认的重试处理：请求三次
		          getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		          String strResult = "";
		          try { 
		              getMethod.addRequestHeader("Authorization",str);
		              getMethod.addRequestHeader("Content-type", "application/atom+xml;type=feed");
		              int status = httpClient.executeMethod(getMethod);
		               // HTTP响应头部信息，这里简单打印
		               /*Header[] headers =  getMethod.getResponseHeaders();
		               for (Header h : headers)
		               System.out.println(h.getName() + "------------ " + h.getValue());*/
		               if (status == 200) {  
		                   /*读返回数据*/  
		            	   byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
		            	   strResult = new String(responseBody, "utf-8");
		            	   //System.out.println("strResult===================="+strResult);
		            	   if(!"".equals(strResult)){
		                   int i =strResult.indexOf("<opensearch:totalResults>");
		                   int j =strResult.indexOf("</opensearch:totalResults>");
		                   if(i!=0){
		                	   strResult=strResult.substring(i+23,j);
		                    }
		            	   }
		                  }
		               } catch (ClientProtocolException e) {  
		                   e.printStackTrace();  
		               } catch (IOException e) {  
		                   e.printStackTrace();  
		               }  
		       return strResult; 
		    }
		    
		

		public   static  void  main(String[] args) throws UnsupportedEncodingException{
			 String auth_key ="api@mail.wxc.edu.cn";
			 String api_secret ="2f1e535ab33f94758ec42e060714c8e2";
			 String auth_timestamp = System.currentTimeMillis()/1000+"";
			// getToken1(auth_key,api_secret,auth_timestamp);
			 getToken(auth_key,api_secret,auth_timestamp);
		  }
	}