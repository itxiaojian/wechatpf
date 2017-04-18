package com.sliu.framework.app.company.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.util.ConnectAccess;
import com.sliu.framework.app.wzy.model.ZyXyxw;

/**
 * 公司微信平台
 * @author : zhangyi
 * @version 创建时间：2015年7月30日 下午1:43:48
 */
@Repository
public class CompanyDao extends HibernateBaseDaoImpl<ZyXyxw, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	public List<Map<String, Object>> gsjjDetail() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,ClassID,ClassName,ClassDividePercent,content,uptime from class_skweb where ClassID='201350324190'");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("ClassName", rs.getString("ClassName"));
				map.put("ClassDividePercent", rs.getString("ClassDividePercent"));
				map.put("content", rs.getString("content"));
				map.put("uptime", rs.getString("uptime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	/**
	 * 公司资质
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @return
	 */
	public List<Map<String, Object>> gszzDetail() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,ClassID,ClassName,ClassDividePercent,content,uptime from class_skweb where ClassID='201468203287'");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("ClassName", rs.getString("ClassName"));
				map.put("ClassDividePercent", rs.getString("ClassDividePercent"));
				
				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image/";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image/");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				
				map.put("uptime", rs.getString("uptime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 公司荣誉
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	public List<Map<String, Object>> gsryDetail() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,ClassID,ClassName,ClassDividePercent,content,uptime from class_skweb where ClassID='201462579766'");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("ClassName", rs.getString("ClassName"));
				map.put("ClassDividePercent", rs.getString("ClassDividePercent"));
				
				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image/";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image/");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				
				map.put("uptime", rs.getString("uptime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 服务体系
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	public List<Map<String, Object>> fwtxDetail() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,ClassID,ClassName,ClassDividePercent,content,uptime from class_skweb where ClassID='201519247341'");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("ClassName", rs.getString("ClassName"));
				map.put("ClassDividePercent", rs.getString("ClassDividePercent"));
				
				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image/";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image/");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				
				map.put("uptime", rs.getString("uptime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 公司资讯列表
	 * @author duanpeijun
	 * @date 2015年8月03日
	 * @return
	 */
	public List<Map<String, Object>> gszxList() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,ClassID,Title,Content,UpdateTime from Article_skweb where ClassID='201512229100' order by UpdateTime desc");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("Title", rs.getString("Title"));
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 公司资讯详情
	 * @author duanpeijun
	 * @date 2015年8月03日
	 * @return
	 */
	public List<Map<String, Object>> gszxDetail(Long id) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,ClassID,Title,Content,UpdateTime from Article_skweb where ClassID='201512229100' and id ="+id);
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("Title"));

				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 解决方案列表
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	public List<Map<String, Object>> jjfaList() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select ID,ClassID,Title,Content,UpdateTime,PicUrl from photo_skweb where ClassID='201291211776'order by UpdateTime desc");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("Title"));
				map.put("content", rs.getString("content"));
			
				String con = rs.getString("PicUrl").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/photo/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/photo/image");  
				//System.out.println("------------>"+s);  
				map.put("PicUrl", s);
				
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 解决方案详情
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	public List<Map<String, Object>> jjfaDetail(Long id) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select ID,ClassID,Title,Content,UpdateTime,PicUrl from photo_skweb where ClassID='201291211776' and id ="+id);
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("Title"));

				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 成功案例列表（教育，政府，金融，企业，医疗）
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	public List<Map<String, Object>> cgalList(Long ClassID) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select ID,ClassID,Title,Content,UpdateTime,PicUrl from goods_skweb where ClassID='"+ClassID+"' order by UpdateTime desc");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("Title"));
				map.put("content", rs.getString("content"));
			
				String con = rs.getString("PicUrl").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/products/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/products/image");  
				//System.out.println("------------>"+s);  
				map.put("PicUrl", s);
				
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 成功案例详情（教育，政府，金融，企业，医疗）
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	public List<Map<String, Object>> cgalDetail(Long id,Long ClassID) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select ID,ClassID,Title,Content,UpdateTime,PicUrl from goods_skweb where ClassID='"+ClassID+"' and id ="+id);
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("Title"));
				map.put("Content", rs.getString("Content"));
				String con = rs.getString("PicUrl").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/products/image";
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/products/image");
				//System.out.println("------------>"+s);  
				map.put("PicUrl", s);
				map.put("content", s+rs.getString("Content"));
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 获取近期的公司新增信息
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月3日 下午4:53:48 
	 * @return
	 */
	public List<Map<String, Object>> getLastDayXwzx() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ConnectAccess.GetConnectAccessFile();
			Statement stmt = conn.createStatement();
			
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd 23:59:59",Locale.CHINA);
			SimpleDateFormat sfd2=new SimpleDateFormat("yyyy/MM/dd 00:00:00",Locale.CHINA);
			Calendar cal=Calendar.getInstance(Locale.CHINA);
			//当前时间
			cal.setTimeInMillis(System.currentTimeMillis());
			System.out.println("当前时间:"+sdf1.format(cal.getTime()));
			String endday = sdf1.format(cal.getTime());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			System.out.println("周日时间:"+sfd2.format(cal.getTime()));
			String beginday = sfd2.format(cal.getTime());

			//查找新闻咨询
			ResultSet rs = stmt.executeQuery("SELECT ID,ClassID,Title,Content,UpdateTime from Article_skweb where ClassID='201512229100' "
					+ "and UpdateTime>=#"+beginday+"# and UpdateTime <=#"+endday+"# order by UpdateTime desc");

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("Title"));

				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			
			//查找公司资质
			rs = stmt.executeQuery("SELECT ID,ClassID,ClassName,ClassDividePercent,content,uptime from class_skweb where ClassID='201468203287' "
					+ "and uptime>=#"+beginday+"# and uptime <=#"+endday+"# order by uptime desc");
			
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("ClassName"));

				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				
				map.put("UpdateTime", rs.getString("uptime"));
				list.add(map);
			}
			
			//查找公司荣耀
			rs = stmt.executeQuery("SELECT ID,ClassID,ClassName,ClassDividePercent,content,uptime from class_skweb where ClassID='201462579766' "
					+ "and uptime>=#"+beginday+"# and uptime <=#"+endday+"# order by uptime desc");

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("ClassName"));

				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				
				map.put("UpdateTime", rs.getString("uptime"));
				list.add(map);
			}
			
			//查找服务体系
			rs = stmt.executeQuery("SELECT ID,ClassID,ClassName,ClassDividePercent,content,uptime from class_skweb where ClassID='201519247341' "
					+ "and uptime>=#"+beginday+"# and uptime <=#"+endday+"# order by uptime desc");

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("ClassName"));

				String con = rs.getString("content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				map.put("UpdateTime", rs.getString("uptime"));
				list.add(map);
			}
			
			//查找解决方案
			rs = stmt.executeQuery("select ID,ClassID,Title,Content,UpdateTime,PicUrl from photo_skweb where ClassID='201291211776'"
					+ "and UpdateTime>=#"+beginday+"# and UpdateTime <=#"+endday+"# order by UpdateTime desc");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("Title"));
				String con = rs.getString("Content").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/image";  
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);  
				Matcher m = p.matcher(str);  
				// 替换为 $img$和一个空格  
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/image");  
				//System.out.println("------------>"+s);  
				map.put("content", s);
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			
			//成功案例
			rs = stmt.executeQuery("select ID,ClassID,Title,Content,UpdateTime,PicUrl from goods_skweb "
					+ "where UpdateTime>=#"+beginday+"# and UpdateTime <=#"+endday+"# order by UpdateTime desc");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ID", rs.getString("ID"));
				map.put("ClassID", rs.getString("ClassID"));
				map.put("Title", rs.getString("Title"));
				map.put("Content", rs.getString("Content"));
				String con = rs.getString("PicUrl").toString();
				//System.out.println("-----------"+con.replace('\"','\''));
				String str = con;  
				// 加个一个或多个空白字符  
				String pattern = "/UpFiles/products/image";
				// 不区分大小写  
				//Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);  
				// 区分大小写  
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(str);
				// 替换为 $img$和一个空格
				String s = m.replaceAll("http://www.zs-si.com/UpFiles/products/image");
				//System.out.println("------------>"+s);
				map.put("PicUrl", s);
				String ss = "<img src='"+s+"'/><br/>";
				map.put("content", ss+map.get("Content"));
				map.put("UpdateTime", rs.getString("UpdateTime"));
				list.add(map);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
