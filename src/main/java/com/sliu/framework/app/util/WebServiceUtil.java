package com.sliu.framework.app.util;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class WebServiceUtil {
	
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
	
	public static void main(String[] args) throws Exception {
		String endpoint="http://172.16.100.5/services/TrjnSearchService?wsdl";
		Service service=new Service();
		Call call=null;
		call = (Call)service.createCall(); 
		call.setUsername("syntong");
		call.setPassword("syntong881516608657");//密码
		call.setTargetEndpointAddress(new URL(endpoint));
	    call.setOperationName(new QName("http://www.w3.org/2001/XMLSchema","account"));
        call.addParameter("account", XMLType.XSD_STRING,ParameterMode.IN);
        //call.addParameter("pwd",XMLType.XSD_DATE,javax.xml.rpc.ParameterMode.IN);
        //call.setReturnType(XMLType.XSD_DATE);
        System.out.println("=============invoke");
        String[] ret=(String[]) call.invoke(new Object[] {"03000041"});
		System.out.println("============="+ret[0]);
	}
}
