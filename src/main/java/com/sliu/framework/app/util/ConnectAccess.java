package com.sliu.framework.app.util;
/** 
 * @author:zhangyi 
 * @version 创建时间：2015年7月29日 上午11:45:08 
 * 类说明 
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConnectAccess {

	/**
	 * 
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月1日 上午9:09:34 
	 * @return
	 * @throws Exception 
	 */
	public static Connection GetConnectAccessFile() throws Exception  {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		/**
		 * 直接连接access文件。
		 */
		String dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=D://www//SKCMS//EL8Rt8Yj.MDB";
		Properties pro = new Properties();
	    pro.setProperty("charSet","GB2312");
	    Connection  conn = DriverManager.getConnection(dbur1,pro);
	    return conn;
//		Connection conn = DriverManager.getConnection(dbur1, "username", "password");
//		Statement stmt = conn.createStatement();
//		
//		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd 23:59:59",Locale.CHINA);
//		SimpleDateFormat sfd2=new SimpleDateFormat("yyyy/MM/dd 00:00:00",Locale.CHINA);
//		Calendar cal=Calendar.getInstance(Locale.CHINA);
//		//当前时间，貌似多余，其实是为了所有可能的系统一致
//		cal.setTimeInMillis(System.currentTimeMillis());
//		System.out.println("当前时间:"+sdf1.format(cal.getTime()));
//		String endday = sdf1.format(cal.getTime());
//		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//		System.out.println("周一时间:"+sfd2.format(cal.getTime()));
//		String beginday = sfd2.format(cal.getTime());
//		
//
//		ResultSet rs = stmt.executeQuery("SELECT ID,ClassID,Title,Content,UpdateTime from Article_skweb where ClassID='201512229100' "
//				+ "and UpdateTime>=#"+beginday+"# and UpdateTime <=#"+endday+"# order by UpdateTime desc");
//		while (rs.next()) {
//			System.out.println(rs.getString("Title"));
//			System.out.println(rs.getString("UpdateTime"));
////		    System.out.println(rs.getString("Content"));
//			String con = rs.getString("Content").toString();
////			System.out.println("-----------"+con.replace('\"','\''));	
////			System.out.println("con1---------->"+con);
//			String pattern = "/UpFiles/image";  
//	        Pattern p = Pattern.compile(pattern);  
//	        Matcher m = p.matcher(con);  
//	        // 替换为 $img$和一个空格  
//	        String s = m.replaceAll("http://192.168.10.19/UpFiles/image");  
////	        System.out.println(s);  
////			con.replaceAll("UpFiles","192.168.10.19");
////			System.out.println("con2---------->"+con);						
////		       String str = con;  
////		       // 加个一个或多个空白字符  
////		       String pattern = "/UpFiles/image/";  
////		       // 不区分大小写  
////		       //Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
////		         
////		       // 区分大小写  
////		       Pattern p = Pattern.compile(pattern);  
////		       Matcher m = p.matcher(str);  
////		       // 替换为 $img$和一个空格  
////		       String s = m.replaceAll("http://192.168.10.19/UpFiles/image/");  
//		       System.out.println("------------>"+s);  						
//		}
//		rs.close();
//		stmt.close();
//		conn.close();
//		return conn;
	}
	public  static Connection ConnectAccessDataSource()throws Exception {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		/**
		 * 采用ODBC连接方式 如何建立ODBC连接？
		 * 答：在windows下，【开始】->【控制面板】->【性能和维护】->【管理工具】->【数据源】，在数据源这里添加一个指向a1.mdb文件的数据源。
		 * 比如创建名字为dataS1
		 */
		String dbur1 = "jdbc:odbc:gswz";// 此为ODBC连接方式
		Properties pro = new Properties();
	    pro.setProperty("charSet","GB2312");
		Connection conn = DriverManager.getConnection(dbur1,pro);
		
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery("select * from Goods_skweb");
//		while (rs.next()) {
//			System.out.println(rs.getString(1));
//		}
//		rs.close();
//		stmt.close();
//		conn.close();
		return conn;
	}
	
	/**
	 * 初学者请注意：
	 * 1:先建立一个access文件a1.mdb,并放在D:/下;
	 * 2:在数据库文件a1.mdb中建立一个表Table1；
	 * 3：为Table1添加一列，并插入至少一条记录；
	 * 4：本文是一个完整的类，直接拿去运行就可以。
	 */
	public static void main(String args[]) throws Exception {
		ConnectAccess ca=new ConnectAccess();
		ca.GetConnectAccessFile();
//		ca.ConnectAccessDataSource();
	}
}

