package com.sliu.framework.app.wlx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sys.model.SysWxyh;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.dao.ZsXscjDao;

@Repository
public class lxlcDao extends HibernateBaseDaoImpl<SysWxyh, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;
	
	/**
	 * @author wangxiangyang
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlx(String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=""+yh.get(0).get("dlm")+"";
			}
		}
		String sql= "SELECT b.tacheid,a.stuid,a.stuname,a.xsyx,a.xszy,a.blzt,b.tachename,c.dd"
                    +" FROM zs_lx_tache b LEFT JOIN zs_lx_tachestatus a ON b.tacheid = a.tacheid"
                    +" LEFT JOIN zs_lx_zdxx c ON c.tacheid = a.tacheid "
				    +"  where 1=1  and a.stuid  = '"+str+"'"
		            +"   order by a.tacheid";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * @author wangxiangyang
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlx1(String openId){
		String stuid="";
		String stuname="";
		String xsyx="";
		String xszy="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=this.getGrxx(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				stuname=yh.get(0).get("stuname")+"";
				xsyx=yh.get(0).get("xsyx")+"";
				xszy=yh.get(0).get("xszy")+"";
				stuid=user.get(0).get("yhid")+""+"";
			}
		}
		String sql= 
				"SELECT a.tacheid,a.stuid,a.stuname,a.xsyx,a.blzt,a.tachename,b.dd  FROM  (  "
				+"SELECT a.tacheid,'"+stuid+"' AS stuid,'"+stuname+"'  as stuname,'"+xsyx+"' as xsyx,'"+xszy+"' as xszy ,"
			  	+" b.blzt,a.tachename,b.dd FROM zs_lx_tache a LEFT JOIN "
			  	+"(SELECT b.tacheid,a.stuid,a.stuname,a.xsyx,a.xszy,a.blzt,b.tachename,c.dd"
			     +"   FROM zs_lx_tache b LEFT JOIN zs_lx_tachestatus a ON b.tacheid = a.tacheid"
			     +"   LEFT JOIN zs_lx_zdxx c ON c.tacheid = a.tacheid "
			     +"   WHERE 1=1  AND a.stuid  = '"+stuid+"')b ON a.tacheid = b.tacheid"
			     +"   WHERE b.stuid IS NULL)a,zs_lx_zdxx b"
			     +"   WHERE a.tacheid= b.tacheid"
			     +"   order by a.tacheid";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取学生的专业，院系
	 * @author:oufeng
	 * @version 2016年6月14日
	 * @return
	 */
	public List<Map<String, Object>> getGrxx(String stuid){
		String str="";
		if (StringUtils.hasText(stuid)) {
			str += " and stuid = '"+stuid+"'";
		}
		String sql =" SELECT stuid,stuname,xsyx,xszy FROM  zs_lx_tachestatus where 1=1   "+str+"  GROUP BY  stuid  ";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
/**
	 * @author wangxiangyang 
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlxxx(int tacheid,String openId){
		String str="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=""+yh.get(0).get("dlm")+"";
			}
		}
		String sql="select  a.tacheid,a.stuid,a.stuname,a.xsyx,a.xszy,a.blzt,a.reason,b.sj,b.dd,b.zysx,c.tachename from zs_lx_tachestatus a"
				+ " left join zs_lx_zdxx b on b.tacheid=a.tacheid left join zs_lx_tache c on c.tacheid = a.tacheid where 1=1  and a.tacheid = '"+tacheid+"' and a.stuid = '"+str+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * @author wangxiangyang 
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlxxx1(int tacheid,String openId){
		String str="";
		String stuid="";
		String stuname="";
		String xsyx="";
		String xszy="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=this.getGrxx(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				stuname=yh.get(0).get("stuname")+"";
				xsyx=yh.get(0).get("xsyx")+"";
				xszy=yh.get(0).get("xszy")+"";
				stuid=user.get(0).get("yhid")+""+"";
			}
			str+=" and a.tacheid='"+tacheid+"'";
		}
		String sql="select a.tacheid,a.stuid,a.stuname,a.xsyx,a.xszy,b.sj,b.dd,b.zysx,a.tachename "
				+"from(SELECT a.tacheid,'"+stuid+"' AS stuid,'"+stuname+"'  as stuname,'"+xsyx+"' as xsyx,"
				+"'"+xszy+"' as xszy,a.tachename,a.blzt "
				+"from (select a.tacheid,b.stuid,b.stuname,b.xsyx,b.xszy,"
				+" b.blzt,a.tachename FROM zs_lx_tache a LEFT JOIN "
			  	+"(SELECT b.tacheid,a.stuid,a.stuname,a.xsyx,a.xszy,a.blzt,b.tachename "
			    +"   FROM zs_lx_tache b LEFT JOIN zs_lx_tachestatus a ON b.tacheid = a.tacheid"
			    +"   LEFT JOIN zs_lx_zdxx c ON c.tacheid = a.tacheid "
			    +"  WHERE 1=1  AND a.stuid  = '"+stuid+"')b ON a.tacheid = b.tacheid"
			    +"  WHERE b.stuid IS NULL)a"
                +"  where 1=1"+str+")a,zs_lx_zdxx b"
                +"   WHERE a.tacheid= b.tacheid";
		return jdbcTemplate.queryForList(sql);
	}
	
}
