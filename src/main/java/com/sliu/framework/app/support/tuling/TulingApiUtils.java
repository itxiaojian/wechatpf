package com.sliu.framework.app.support.tuling;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TulingApiUtils {

	private static Logger logger = LoggerFactory.getLogger(TulingApiUtils.class); 
	
	//API KEY
	public static final String API_KEY = "dd86b7526af1118891b22281d8ee4b82";
	//微信接口调用地址
	private static String apiUrl = "http://www.tuling123.com/openapi/api?key=dd86b7526af1118891b22281d8ee4b82";  
	
	public static String getTulingResult(String content){
		String result = "";
		String requestUrl = apiUrl + "&info=" + content;      
        /** 发送httpget请求 */  
        HttpGet request = new HttpGet(requestUrl);  
        try {
			HttpResponse response = HttpClients.createDefault().execute(request);
			 //200即正确的返回码 
		    if(response.getStatusLine().getStatusCode()==200){ 
		        result = EntityUtils.toString(response.getEntity()); 
		        logger.info("调用图灵接口返回结果："+result); 
		    } 			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        return result;  
    }  
	
	public static void main(String[] args) {
		String result = TulingApiUtils.getTulingResult("帮我找个妹子来");
		System.out.println(result);
	}
}
