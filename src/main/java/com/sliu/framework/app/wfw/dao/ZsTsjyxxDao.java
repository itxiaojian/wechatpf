package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsTsjyxx;

@Repository
public class ZsTsjyxxDao extends HibernateBaseDaoImpl<ZsTsjyxx, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * 获取当前登录人的信息
	 * @author liujiasen
	 * @date 2015年5月21日
	 * @return
	 */
	public List<Map<String,Object>> getGrTsjyxx(String openId,String pages){
		String str="";
		String str1="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0 && !"admin".equals(yh.get(0).get("dlm"))){
				str +=" and bh='"+yh.get(0).get("dlm")+"'";
			}
			if("admin".equals(yh.get(0).get("dlm"))){
				if(pages!=null&&!"".equals(pages)){
					int num=Integer.parseInt(pages)*5;
					str1=str1+" order by jcsj DESC limit "+num+" ";
				}
			}
		}
		
		String sql="select distinct bh,xm from zs_tsjyxx where 1=1 " + str + str1 ;
		return jdbcTemplate.queryForList(sql);
	}
	
	
	
	/**
	 * 获取当前登录人的图书未归还信息
	 * @author zhangyan
	 * @date 2016年4月26日
	 * @return
	 */
	public List<Map<String,Object>> getwghxx(String jcsj,String openId,String pages,String yhbh){
		String str="";
		String str1="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0 && !"admin".equals(yh.get(0).get("dlm"))){
				str +=" and bh='"+yh.get(0).get("dlm")+"' ";
				if(pages!=null&&!"".equals(pages)){
					if(Integer.parseInt(pages)==1){
						int num=Integer.parseInt(pages)*10;
						str1=str1+" order by jcsj DESC limit "+num+" ";
					}else{
						int num = (Integer.parseInt(pages) - 1) * 10;
						str1=str1+" order by jcsj DESC limit "+num+",10 ";
					}
				}
			}
			if("admin".equals(yh.get(0).get("dlm"))){
				if(pages!=null&&!"".equals(pages)){
					int num=Integer.parseInt(pages)*5;
					str1=str1+" order by jcsj DESC ";
				}
			}
		}if(jcsj!=null&&!"".equals(jcsj)){
			str+= " and jcsj like '%"+jcsj+"%'";
		}
		
		
		String sql="select id,bh,xm,kh,tsbh,tsmc,DATE_FORMAT(jcsj,'%Y-%m-%d') as jcsj,DATE_FORMAT(yghsj,'%Y-%m-%d') as yghsj,"
				+ "DATE_FORMAT(ghsj,'%Y-%m-%d') as ghsj, DATE_FORMAT(now(), '%Y-%m-%d') as dqsj,bz from zs_tsjyxx  "
				+ "where 1=1 and bh in("+yhbh+") and ghsj is null" + str+str1;
		
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取当前登录人的图书已归还信息
	 * @author zhangyan
	 * @date 2016年4月26日
	 * @return
	 */
	public List<Map<String,Object>> getyghxx(String jcsj,String openId,String pages,String yhbh){
		String str="";
		String str1="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0 && !"admin".equals(yh.get(0).get("dlm"))){
				str +=" and bh='"+yh.get(0).get("dlm")+"' ";
				if(pages!=null&&!"".equals(pages)){
					if(Integer.parseInt(pages)==1){
						int num=Integer.parseInt(pages)*10;
						str1=str1+" order by jcsj DESC limit "+num+" ";
					}else{
						int num = (Integer.parseInt(pages) - 1) * 10;
						str1=str1+" order by jcsj DESC limit "+num+",10 ";
					}
				}
			}
			if("admin".equals(yh.get(0).get("dlm"))){
				if(pages!=null&&!"".equals(pages)){
					int num=Integer.parseInt(pages)*5;
					str1=str1+" order by jcsj DESC  ";
				}
			}
		}if(jcsj!=null&&!"".equals(jcsj)){
			str+= " and jcsj like '%"+jcsj+"%'";
		}
		
		
		String sql="select id,bh,xm,kh,tsbh,tsmc,DATE_FORMAT(jcsj,'%Y-%m-%d') as jcsj,DATE_FORMAT(yghsj,'%Y-%m-%d') as yghsj,"
				+ "DATE_FORMAT(ghsj,'%Y-%m-%d') as ghsj, DATE_FORMAT(now(), '%Y-%m-%d') as dqsj,bz from zs_tsjyxx  "
				+ "where 1=1 and bh in("+yhbh+") and ghsj is not null" + str + str1;
		
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取图书借阅的排行
	 * @author oufeng
	 * @date 2016年4月21日
	 * @return
	 */
	public List<Map<String,Object>> getPh(String openId,String pages){
		String str="";
		if(pages!=null&&!"".equals(pages)){
			if(Integer.parseInt(pages)==1){
				int num=Integer.parseInt(pages)*10;
				str=str+" order by COUNT(tsbh) DESC limit "+num+" ";
			}else{
				int num=(Integer.parseInt(pages)-1)*10;
				str=str+" order by COUNT(tsbh) DESC limit "+num+",10";
			}
		}
		String sql="SELECT COUNT(tsbh) AS jycs,tsbh,tsmc FROM zs_tsjyxx  GROUP BY  tsbh,tsmc "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取图书借阅的排行
	 * @author oufeng
	 * @date 2016年4月21日
	 * @return
	 */
	public List<Map<String,Object>> getXq(String openId,String tsbh){
		String str="";
		if(tsbh!=null&&!"".equals(tsbh)){
			str+= " and a.tsbh = '"+tsbh+"'";
		}
		String sql=" SELECT a.tsbh,a.tsmc,b.tsjs,a.bz  FROM "
                 +"(SELECT  kh,tsbh,tsmc,bz FROM  zs_tsjyxx GROUP BY tsbh,tsmc) a LEFT JOIN zs_tsxq b "
                 +" ON a.tsbh= b.tsbh where 1=1  "
                 +str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取图书借阅的排行
	 * @author oufeng
	 * @date 2016年4月21日
	 * @return
	 */
	public List<Map<String,Object>> getGhTo(String openId,String dateStr){
		String str="";
		if(dateStr!=null&&!"".equals(dateStr)){
			str+= " and substring(a.yghsj,1,10) = '"+dateStr+"'";
		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0 && !"admin".equals(yh.get(0).get("dlm"))){
				str +=" and a.bh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql=" select a.tsbh,a.tsmc,a.yghsj,a.jcsj,a.bz  "
                 + " from zs_tsjyxx a "
                 + " where 1=1  "
                 +str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取图书借阅的排行
	 * @author oufeng
	 * @date 2016年4月21日
	 * @return
	 */
	public List<Map<String,Object>> getGhBe(String openId,String dateStr,String _dateStr){
		String str="";
		if(dateStr!=null&&!"".equals(dateStr)){
			str+= " and  '"+dateStr+"' <=" + " substring(a.yghsj,1,10) " + " and  substring(a.yghsj,1,10)< '"+_dateStr+"'";
			
		}
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0 && !"admin".equals(yh.get(0).get("dlm"))){
				str +=" and a.bh='"+yh.get(0).get("dlm")+"'";
			}
		}
		String sql=" select a.tsbh,a.tsmc,a.jcsj,a.yghsj,a.bz  "
                 + " from zs_tsjyxx a "
                 + " where 1=1  "
                 +str;
		return jdbcTemplate.queryForList(sql);
   }
}
