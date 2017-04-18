package com.sliu.framework.app.wfw.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.model.ZsXscjTxjl;

@Repository
public class ZsXscjDao extends HibernateBaseDaoImpl<ZsXscjTxjl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 分页查询信息数据
	 * @author liujiasen
	 * @date 2015年5月19日
	 * @param start
	 * @param limit
	 * @param xm
	 * @param wxh
	 * @return
	 */
	public Pagination<Map<String,Object>> getXscjList(Integer start, Integer limit,String xm,String wxh) {
		String str="";
		if(xm!=null&&!"".equals(xm)){
			str=str+" and b.xm like '%"+xm+"%'";
		}
		if(wxh!=null&&!"".equals(wxh)){
			str=str+" and a.wxh like '%"+wxh+"%'";
		}
		String sql = "SELECT id, ksxn, ksxq, xh, kskm, kcbh, kscj, sftg, bkcj, xf, bz FROM zs_xscj where 1=1 "+str;
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月3日
	 * @param str
	 * @param user
	 * @return
	 */
	public String getCxtj(String str,String yhid){
		List<Map<String,Object>> yh=this.getYh(yhid);
		if(yh.size()!=0){
			String js=(yh.get(0).get("jsmc")+"").trim();
			if("ROLE_STUDENT".equals(js)){
				//学生角色
				str=" and xh='"+yh.get(0).get("dlm")+"'";
			}else if("ROLE_TEACHER".equals(js)){
				//老师角色
				List<Map<String,Object>> sk=getLssk((yh.get(0).get("dlm")+"").trim());
				
				if(sk.size()!=0){
				String kc="";
				str=" and kcbh in (";
				if(sk.size()!=0){
					for(int i=0;i<sk.size();i++){
						if(i<sk.size()-1){
							kc=kc+"'"+sk.get(i).get("kcbh")+"',";
						}else{
							kc=kc+"'"+sk.get(i).get("kcbh")+"')";
						}
					}
				}else{
					kc=kc+"'')";
				}
				str=str+kc;
			}else{
				str=" and kcbh='xxxxxxxxxx'";
			}
				
			}
			else if("ROLE_INSTRUCTOR".equals(js)){
				//辅导员角色
				List<Map<String,Object>> bj=getBjxx((yh.get(0).get("dlm")+"").trim());
				if(bj.size()!=0){
				str=" and bjbh in (";
				String cx="";
				if(bj.size()!=0){
					for(int i=0;i<bj.size();i++){
						if(i<bj.size()-1){
							cx=cx+"'"+bj.get(i).get("bjbh")+"',";
						}else{
							cx=cx+"'"+bj.get(i).get("bjbh")+"')";
						}
					}
				}else{
					cx=cx+"'')";
				}
				str=str+cx;
			}else{
				str=" and bjbh ='xxxxxxxxxx'";
		    	}
			}
		}
		return str;
	}
	
	/**
	 * 按学年和学期获取当前登录人的考试成绩
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getGrcj(String ksxn,String ksxq,String yhid,String pages,String whStr){
		String str="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str=str+" and a.ksxn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str=str+" and a.ksxq = '"+ksxq+"'";
		}
//			List<Map<String,Object>> yh=this.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and a.xh='"+yh.get(0).get("dlm")+"'";
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by a.ksxn DESC limit "+num+" ";
			}else{
				int num = (Integer.parseInt(pages) - 1) * 10;
				str=str+" order by a.ksxn DESC limit "+num+",10 ";
			}
			
		}
		String sql="select a.id,a.ksxn,a.ksxq,a.xh,a.xm,"
				+ "a.kskm,a.kcbh,a.kscj,a.sftg,a.bkcj,a.xf,a.bz"
				+ "  from zs_xscj a where 1=1 "+whStr+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登录人的最新考试成绩的学年
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getNewXn(String whStr){
		String sql="select ksxn from zs_xscj where 1=1 "+whStr +" ORDER BY ksxn DESC  LIMIT 1";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登陆人的最新考试成绩的学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksxn
	 * @return
	 */
	public List<Map<String,Object>> getNewXq(String ksxn,String whStr){
		String sql="select ksxq as ksxq from zs_xscj where ksxn='"+ksxn+"' "+whStr 
				 +" ORDER BY ksxq asc LIMIT 1";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取个人考试成绩学年学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getKsqh(String openId){
		String str="";
		List<Map<String,Object>> user=this.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=this.getCx(str, user);
		}
		String sql="select a.ksxn,a.ksxq from zs_xscj a where 1=1 "+str+" group by a.KSXN,a.KSXQ order by a.KSXN desc,a.KSXQ desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取老师的所授课程
	 * @author liujiansen
	 * @date 2015年8月3日
	 * @param jsgh
	 * @return
	 */
	public List<Map<String,Object>> getLssk(String jsgh){
		String sql="select jsgh,kcbh from zs_jskb where jsgh='"+jsgh+"' group by jsgh,kcbh";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据当前登陆人获取登录人的班级信息
	 * @author liujiasen
	 * @date 2015年5月28日
	 * @return
	 */
	public List<Map<String,Object>> getBjxx(String dlm){
		String str="";
		if(dlm!=null&&!"".equals(dlm)){
			str=str+" and dlm='"+dlm+"'";
		}
		String sql="select id,dlm,xm,bjbh,bjmc,bz from sys_xsbjb where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据openId获取绑定用户的ID
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param wxh openId
	 * @return
	 */
	public List<Map<String,Object>> getWxyh(String wxh){
		String sql="SELECT yhid,d.jsmc FROM sys_wxyh a ,sys_yh b ,sys_yhjs c,sys_js d WHERE "
				+" a.yhid = b.yhbh "
				+" AND b.yhbh = c.yhbh "
			    +" AND c.jsbh = d.jsbh "
			    +" AND wxh='"+wxh+"' AND zt=1 ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取用户信息
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getYh(String id){
		String str="";
		if(id!=null&&!"".equals(id)){
			str=str+" and a.yhbh='"+id+"'";
		}
		String sql="select a.dlm,a.xm,a.yhbh,c.jsmc from sys_yh a "
				+ "left join sys_yhjs b on a.yhbh=b.yhbh left join sys_js c on b.jsbh=c.jsbh where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 按学年和学期获取当前登录人的考试成绩
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getXsxx(String ksxn,String ksxq,String openId,String pages){
		String str="";
		String str1="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str=str+" and a.ksxn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str=str+" and a.ksxq = '"+ksxq+"'";
		}
		List<Map<String,Object>> user=this.getWxyh(openId);
		if(user.size()!=0){
//			List<Map<String,Object>> yh=this.getYh(user.get(0).get("yhid")+"");
//			if(yh.size()!=0){
//				str=str+" and a.xh='"+yh.get(0).get("dlm")+"'";
//			}
			str=str+this.getCxtj(str, user.get(0).get("yhid").toString());
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str1+=" order by a.ksxn DESC limit "+num+" ";
			}else{
				int num = (Integer.parseInt(pages) - 1) * 10;
				str1+=" order by a.ksxn DESC limit "+num+",10 ";
			}
			
		}
		String sql=
				"SELECT  ksxn,ksxq,xh,xm FROM (SELECT a.ksxn,a.ksxq,a.xh,a.xm "
                 +" FROM zs_xscj a where 1=1 "
                 +str
                 +str1
             +")a GROUP BY  ksxn,ksxq,xh,xm";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询学生提醒的记录
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getXstxjl(){
		String ksxn="";
		String ksxq="";
		String str="";
		Map<String,Object> map = AppUtil.getXnNew(new Date());
		if(map.size()==2){
			ksxn=map.get("xn").toString();
			ksxq=map.get("xq").toString();
		}
		str+=" and  ksxn='"+ksxn+"' and ksxq='"+ksxq+"'";
		String sql="select id,xsxh,ksxn,ksxq,kscj,wxh,sftx  from zs_xscj_txjl where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询学生提醒的记录
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getSjzdByZl(String zdbm){
		String sql="select zdz,zdmc FROM sys_sjzd WHERE zl='txlj' and zdbm ='"+zdbm+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取学生成绩的提醒的信息
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getTxxx(){
		String ksxn="";
		String ksxq="";
		String str="";
		Map<String,Object> map = AppUtil.getXnNew(new Date());
		if(map.size()==2){
			ksxn=map.get("xn").toString();
			ksxq=map.get("xq").toString();
		}
		str+=" and  ksxn='"+ksxn+"' and ksxq='"+ksxq+"'";
		String sql=
				 " SELECT a.ksxn,a.ksxq, a.xh,GROUP_CONCAT(a.kscj SEPARATOR ';') AS kscj,a.xm,b.wxh "
				   +" FROM"
				   +" (SELECT ksxn,ksxq,xh,CONCAT(CONCAT(CONCAT(kskm,':'),kscj),'分') AS kscj,xm "
				   + " FROM zs_xscj where 1=1 "+str+")a" 
				   +" LEFT JOIN"
				   +" sys_wxyh b  ON a.xh=b.yhid "
				   +" WHERE 1=1 "
				   +" AND b.wxh IS NOT NULL "  
				   +" AND b.bz IS  NULL  "
				   +" OR (b.bz IS NOT NULL AND b.bz != '8') "
				   +" GROUP  BY  b.wxh,a.xh,a.ksxn,a.ksxq  ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取学生成绩的提醒的信息
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getTxSavexx(){
		String ksxn="";
		String ksxq="";
		String str="";
		Map<String,Object> map = AppUtil.getXnNew(new Date());
		if(map.size()==2){
			ksxn=map.get("xn").toString();
			ksxq=map.get("xq").toString();
		}
		str+=" and  ksxn='"+ksxn+"' and ksxq='"+ksxq+"'";
		String sql=
				   " SELECT a.ksxn,a.ksxq, a.xh,kscj,a.xm,b.wxh,kcbh  "
				   +"FROM "
				   +" (SELECT ksxn,ksxq,xh,kcbh,CONCAT(CONCAT(CONCAT(kskm,':'),kscj),'分') AS kscj,xm "
				   +" FROM zs_xscj WHERE 1=1 "+str+")a "
				   +" LEFT JOIN "
				   +" sys_wxyh b  ON a.xh=b.yhid "
				   +" WHERE 1=1 "
				   +" AND b.bz IS NULL  "
				   +" OR (b.bz IS NOT NULL AND b.bz != '8') "
				   +" AND b.wxh IS NOT NULL ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取学生成绩的提醒的信息
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getTxxx1(){
		String ksxn="";
		String ksxq="";
		String str="";
		Map<String,Object> map =AppUtil.getXnNew(new Date());
		if(map.size()==2){
			ksxn=map.get("xn").toString();
			ksxq=map.get("xq").toString();
		}
		str+=" and  ksxn='"+ksxn+"' and ksxq='"+ksxq+"'";
		String sql=
				 " SELECT a.ksxn,a.ksxq, a.xh,GROUP_CONCAT(a.kscj SEPARATOR ';') AS kscj,a.xm,b.wxh "
				  +" FROM "
				  +" (SELECT ksxn,ksxq,xh,CONCAT(CONCAT(kskm,kscj),'分')AS kscj,xm "
				  +" FROM zs_xscj WHERE 1=1 "+str+")a "
				  +" RIGHT JOIN "
		 		  +" (SELECT a.yhid ,a.wxh,a.bz  FROM sys_wxyh a LEFT JOIN (select ksxn,ksxq,xsxh "
		 		  +" from zs_xscj_txjl "
		 		  +" where 1=1 "+str+") b ON a.yhid=b.xsxh "
				  +" WHERE b.xsxh IS NULL and a.wxh<>'' "
				  +" and a.yhid<>'1') b  ON a.xh=b.yhid "
				  +" WHERE 1=1 "
				  +" AND b.wxh IS NOT NULL "
				  +" AND a.ksxn IS NOT NULL "
				  +" AND a.bz IS  NULL  "
				   +" OR (a.bz IS NOT NULL AND a.bz != '8') "
				  //+" and b.wxh='o47pzs-huRorGn0Cttb0JABe13K4'"
				  +" GROUP  BY  b.wxh,a.xh,a.ksxn,a.ksxq";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取学生成绩的提醒的信息
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getTxxx2(){
		String ksxn="";
		String ksxq="";
		String str="";
		Map<String,Object> map =AppUtil.getXnNew(new Date());
		if(map.size()==2){
			ksxn=map.get("xn").toString();
			ksxq=map.get("xq").toString();
		}
		str+=" and  ksxn='"+ksxn+"' and ksxq='"+ksxq+"'";
		String sql=
				" SELECT b.ksxn,b.ksxq,b.xh,b.wxh,GROUP_CONCAT(b.kscj SEPARATOR ';') AS kscj,b.xm "
				+" FROM(SELECT a.ksxn,a.ksxq,a.xh,a.wxh,a.kscj,a.kcbh,a.xm FROM "
				+" (SELECT ksxn,ksxq,xh,kskm,kcbh,CONCAT(CONCAT(CONCAT(kskm,':'),kscj),'分')AS kscj,"
				+" sftg,xf,xm ,b.wxh FROM zs_xscj a ,sys_wxyh b  "
				+" WHERE 1=1 "+str+" AND a.xh=b.yhid "
				+" AND a.bz IS  NULL  "
				+" OR (a.bz IS NOT NULL AND a.bz != '8') "
			    + ")a "
				+" LEFT JOIN zs_xscj_txjl b ON a.ksxn=b.ksxn  AND a.ksxq=b.ksxq "
				+" AND a.xh =b.xsxh AND a.kcbh=b.kcbh"
				+" WHERE b.kscj IS NULL "
				+" AND b.kcbh IS NULL)b"
				+" GROUP BY b.wxh,b.xh,b.ksxn,b.ksxq";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取学生成绩的提醒的信息
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id 用户Id
	 * @return
	 */
	public List<Map<String,Object>> getTxSavexx2(){
		String ksxn="";
		String ksxq="";
		String str="";
		Map<String,Object> map =AppUtil.getXnNew(new Date());
		if(map.size()==2){
			ksxn=map.get("xn").toString();
			ksxq=map.get("xq").toString();
		}
		str+=" and  ksxn='"+ksxn+"' and ksxq='"+ksxq+"'";
		String sql=
				" SELECT a.ksxn,a.ksxq,a.xh,a.wxh,a.kscj,a.kcbh FROM "
				+" (SELECT ksxn,ksxq,xh,kskm,kcbh,"
				+ " CONCAT(CONCAT(CONCAT(kskm,':'),kscj),'分')AS kscj,"
				+ " sftg,xf,xm ,b.wxh FROM zs_xscj a ,sys_wxyh b "
				+ " WHERE 1=1 "+str+" AND a.xh=b.yhid"
				+ " AND a.bz IS  NULL  "
				+ " OR (a.bz IS NOT NULL AND a.bz != '8') "
			    + " )a "
				+ " LEFT JOIN zs_xscj_txjl b ON a.ksxn=b.ksxn "
				+ " AND a.ksxq=b.ksxq AND a.xh =b.xsxh AND a.kcbh=b.kcbh"
				+ " WHERE b.kscj IS NULL "
				+ " AND b.kcbh IS NULL";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 学生成绩预警
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @param str
	 * @param user
	 * @return
	 */
	public String getCx(String str,List<Map<String,Object>> user){
		List<Map<String,Object>> yh=this.getYh(user.get(0).get("yhid")+"");
		if(yh.size()!=0){
			String js=(yh.get(0).get("jsmc")+"").trim();
			if("ROLE_INSTRUCTOR".equals(js)){
				//辅导员角色
				List<Map<String,Object>> bj=this.getBjxxList((yh.get(0).get("dlm")+"").trim());
				str=" and bjbh in (";
				String cx="";
				if(bj.size()!=0){
					for(int i=0;i<bj.size();i++){
						if(i<bj.size()-1){
							cx=cx+"'"+bj.get(i).get("classname")+"',";
						}else{
							cx=cx+"'"+bj.get(i).get("classname")+"')";
						}
					}
				}else{
					cx=cx+"'')";
				}
				str=str+cx;
			}
		}
		return str;
	}
	
	/**
	 * 成绩预警——查询班级信息
	 * @author zhangyan
	 * @date 2016年8月15日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getBjxx(String ksxn,String ksxq,String openId,String pages,String bjbh){
		String str="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str=str+" and a.ksxn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str=str+" and a.ksxq = '"+ksxq+"'";
		}
		if(bjbh!=null&&!"".equals(bjbh)){
			str=str+" and concat(ifnull(a.bjbh,'')) like '%"+bjbh+"%'";
		}
		List<Map<String,Object>> user=this.getWxyh(openId);
		if(user.size()!=0){
			str=str+this.getCx(str, user);
		}
//		if(pages!=null&&!"".equals(pages)){
//			int num=Integer.parseInt(pages)*5;
//			str=str+" order by a.ksxn DESC limit "+num+" ";
//		}
		String sql="select distinct a.bjbh from zs_xscj a where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登录人的最新考试信息的学年
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @return
	 */
	public Map<String,Object> getNewXn1(String openId){
		String str="";
		List<Map<String,Object>> user=this.getWxyh(openId);
		if(user.size()!=0){
			str=str+this.getCx(str, user);
		}
		String sql="select max(a.ksxn) as ksxn from zs_xscj a  where 1=1 "+str;
		return jdbcTemplate.queryForMap(sql);
	}
	

	/**
	 * 获取当前登陆人的最新挂科信息的学期
	 * @author zhangyan
	 * @date 2016年8月8日
	 * @param ksxn
	 * @return
	 */
	public Map<String,Object> getNewXq1(String ksxn,String openId){
		String str="";
		List<Map<String,Object>> user=this.getWxyh(openId);
		if(user.size()!=0){
			str=this.getCx(str, user);
		}
		String sql="select max(a.ksxq) as ksxq from zs_xscj a  where 1=1 "+str+" and a.ksxn='"+ksxn+"'";
		return jdbcTemplate.queryForMap(sql);
	}
	
	/**
	 * 获取班级各科考试的最高分、最低分、平均成绩
	 * @author zhangyan
	 * @date 2016年8月15日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getCjList(String ksxn,String ksxq,String openId,String pages,String bjbh){
		String str="";
		String str1="";
		if(ksxn!=null&&!"".equals(ksxn)){
			str=str+" and ksxn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str=str+" and ksxq = '"+ksxq+"'";
		}
		if(bjbh!=null&&!"".equals(bjbh)){
			str=str+" and concat(ifnull(bjbh,'')) like '%"+bjbh+"%'";
		}
		List<Map<String,Object>> user=this.getWxyh(openId);
		if(user.size()!=0){
			str=str+this.getCx(str, user);
		}
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str1=str1+" order by ksxn DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str1=str1+" order by ksxn DESC limit "+num+",10";
			}
		}
		String sql="select  a.ksxn,a.ksxq,a.kskm,a.bjbh,"
				+ " MAX(CASE zl WHEN '最低分' THEN kscj ELSE 0 END ) zdf,  "
				+ " MAX(CASE zl WHEN '平均分' THEN kscj ELSE 0 END ) pjf ,  "
				+ " MAX(CASE zl WHEN '最高分' THEN kscj ELSE 0 END ) zgf from ("
				+ " select round(avg(kscj),2)as kscj,'平均分'as zl,ksxn,ksxq,kskm,bjbh from zs_xscj where 1=1 "+str+" GROUP BY ksxn,ksxq,kskm,bjbh UNION ALL"
				+ " select round(max(kscj),2)as kscj,'最高分'as zl,ksxn,ksxq,kskm,bjbh from zs_xscj where 1=1 "+str+" GROUP BY ksxn,ksxq,kskm,bjbh UNION ALL"
				+ " select round(min(kscj),2)as kscj,'最低分'as zl,ksxn,ksxq,kskm,bjbh from zs_xscj where 1=1 "+str+"GROUP BY ksxn,ksxq,kskm,bjbh"
				+ ")a  GROUP BY a.bjbh, a.kskm,a.ksxn,a.ksxq"+str1;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 成绩预警——查询班级学生成绩
	 * @author zhangyan
	 * @date 2016年8月19日
	 * @param ksxn
	 * @param ksxq
	 * @return
	 */
	public List<Map<String,Object>> getBjcj(String bjbh,String kskm,String ksxn,String ksxq,String openId){
		String str="";
		if(bjbh!=null&&!"".equals(bjbh)){
			str=str+" and a.bjbh= '"+bjbh+"'";
		}
		if(kskm!=null && !"".equals(kskm)){
			str=str+" and a.kskm= '"+kskm+"'";
		}
		if(ksxn!=null&&!"".equals(ksxn)){
			str=str+" and a.ksxn = '"+ksxn+"'";
		}
		if(ksxq!=null&&!"".equals(ksxq)){
			str=str+" and a.ksxq = '"+ksxq+"'";
		}
		
//		List<Map<String,Object>> user=this.getWxyh(openId);
//		if(user.size()!=0){
//			str=str+this.getCx(str, user);
//		}
		String sql="select a.xh,a.kskm,a.xm,a.xf,a.kscj,a.bjbh from zs_xscj a where 1=1 "+str+"order by a.kscj desc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取辅导员所带的班级
	 * @author zhangyan
	 * @date 2016年8月19日
	 * @return
	 */
	public List<Map<String,Object>> getBj(String openId){
		String str="";
		List<Map<String,Object>> user=this.getWxyh(openId);
		List<Map<String,Object>> yh=this.getYh(user.get(0).get("yhid")+"");
		String dlm = yh.get(0).get("dlm").toString();
		if(dlm!=null&&!"".equals(dlm)){
			str=str+" and loginid='"+dlm+"'";
		}
		String sql="select classname from fdy_and_bj where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据当前登陆人获取登录人的班级信息
	 * @author zhangyan
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String,Object>> getBjxxList(String dlm){
		String str="";
		if(dlm!=null&&!"".equals(dlm)){
			str=str+" and loginid='"+dlm+"'";
		}
		String sql="select id,classid,classname,loginid from fdy_and_bj where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
