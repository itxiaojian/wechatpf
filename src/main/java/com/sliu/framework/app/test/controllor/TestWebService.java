package com.sliu.framework.app.test.controllor;

import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;



/**
*
@Author oufeng	
@Date 2016年7月28日 下午10:40:48
@Version 1.0
*/
public class TestWebService {
	 private static String token="Ptzfc2mDZ9ccqG4XwwbqxnpBQWfS3TGW";
	 private static String  url="http://oa.wxc.edu.cn/service";
	 
	 //获取待办事宜
	 public static Object  getMethodnewtask(String username,int readtype,int offset,int pagesize) throws AxisFault{
		    //  使用RPC方式调用WebService        
	        RPCServiceClient serviceClient = new RPCServiceClient();
	        Options options = serviceClient.getOptions();
	        //  指定调用WebService的URL
	        EndpointReference targetEPR = new EndpointReference(url);
	        options.setTo(targetEPR);
	        //  指定newtask方法的参数值
	        Object[] opAddEntryArgs = new Object[] {token,username,readtype,offset,pagesize};
	        //  指定newtask方法返回值的数据类型的Class对象
	        Class[] classes = new Class[] {String.class,String.class,int.class,int.class,int.class};
	        //  指定要调用的newtask方法及WSDL文件的命名空间
	        QName opAddEntry = new QName("http://ws.apache.org/axis2", "newtask");
	        //  调用newtask方法并输出该方法的返回值
		  return serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
	 }
	 
	 //获取最新公文
	 public static Object  getMethoddoclist(String username,int offset,int pagesize) throws AxisFault{
		    //  使用RPC方式调用WebService        
	        RPCServiceClient serviceClient = new RPCServiceClient();
	        Options options = serviceClient.getOptions();
	        //  指定调用WebService的URL
	        EndpointReference targetEPR = new EndpointReference(url);
	        options.setTo(targetEPR);
	        //  指定doclist方法的参数值
	        Object[] opAddEntryArgs = new Object[] {token,username,offset,pagesize};
	        //  指定doclist方法返回值的数据类型的Class对象
	        Class[] classes = new Class[] {String.class,String.class,int.class,int.class,int.class};
	        //  指定要调用的doclist方法及WSDL文件的命名空间
	        QName opAddEntry = new QName("http://ws.apache.org/axis2", "doclist");
	        //  调用doclist方法并输出该方法的返回值
		  return serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
	 }
	 
	 //获取最新网络公告
	 public static Object  getMethonetboard(String username,int offset,int pagesize) throws AxisFault{
		    //  使用RPC方式调用WebService        
	        RPCServiceClient serviceClient = new RPCServiceClient();
	        Options options = serviceClient.getOptions();
	        //  指定调用WebService的URL
	        EndpointReference targetEPR = new EndpointReference(url);
	        options.setTo(targetEPR);
	        //  指定netboard方法的参数值
	        Object[] opAddEntryArgs = new Object[] {token,username,offset,pagesize};
	        //  指定netboard方法返回值的数据类型的Class对象
	        Class[] classes = new Class[] {String.class,String.class,int.class,int.class,int.class};
	        //  指定要调用的netboard方法及WSDL文件的命名空间
	        QName opAddEntry = new QName("http://ws.apache.org/axis2", "netboard");
	        //  调用netboard方法并输出该方法的返回值
		  return serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
	 }
	 
	 
	 public static void main(String[] args) throws Exception{  
		Object b =TestWebService.getMethoddoclist("admin",1,20);
		Object c =TestWebService.getMethonetboard("admin",1,20);
		//System.out.println("a==============:"+a.toString());
		//System.out.println("b==============:"+b.toString());
		JSONObject jsonb = JSONObject.fromObject(b);
		String success=jsonb.getString("success");
		String message=jsonb.getString("message");
		String totalnum=jsonb.getString("totalnum");
		String recnum=jsonb.getString("recnum");
		String pagesize=jsonb.getString("pagesize");
		String lists=jsonb.getString("lists");
		JSONArray jsonlist = JSONArray.fromObject(lists);
		/*for(int i=0;i<jsonlist.size();i++){
			JSONObject jobj =  (JSONObject) jsonlist.get(i);
			String title = jobj.get("title")+"";
			String url = jobj.get("url")+"";
			String time = jobj.get("time")+"";
			System.out.println("title"+i+"============="+title);
			System.out.println("url"+i+"============="+url);
			System.out.println("time"+i+"============="+time);
		} 
		System.out.println("sucess============="+success);
		System.out.println("message============="+message);
		System.out.println("totalnum============="+totalnum);
		System.out.println("recnum============="+recnum);
		System.out.println("pagesize============="+pagesize);*/
		
		Object a= TestWebService.getMethodnewtask("01000027",0,1,20);
		System.out.println("a============="+a.toString());
		
	    } 
}


