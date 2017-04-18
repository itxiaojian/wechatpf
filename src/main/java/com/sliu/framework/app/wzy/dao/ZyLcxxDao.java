package com.sliu.framework.app.wzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wzy.model.ZyLcxx;


/**
 * 主页--流程信息
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:49:50
 */
@Repository
public class ZyLcxxDao extends HibernateBaseDaoImpl<ZyLcxx, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 查询流程信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:49:57
	 * @param start
	 * @param limit
	 * @param lczl    流程种类
	 * @param lcmc  流程名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getLcxxList(Integer start,Integer limit,String lczl,String lcmc){
		
		String sql = "select a.id, a.lczl, a.lcmc, a.lcnr, DATE_FORMAT(a.kssj,'%Y-%m-%d %H:%i:%S') as kssj, DATE_FORMAT(a.jssj,'%Y-%m-%d %H:%i:%S') as jssj, a.fqr, a.fqrxm, b.bmmc, a.bmbh, a.lczt from ZY_LCXX a "
				    + " left join SYS_ZZJG b on a.BMBH = b.BMBH where 1=1";
		if(StringUtils.hasText(lczl)){
			sql += " and a.lczl = '"+lczl+"'";
		}
		if(StringUtils.hasText(lcmc)){
			sql += " and a.lcmc = '"+lcmc+"'";
		}
		
		sql += " order by a.kssj desc";
		 
		return jdbcPager.queryPage(sql, start, limit);
	}
	
	/**
	 * 流程种类查询
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:51:31
	 * @param zdmc   字段名称
	 * @param zdz      字段值
	 * @return
	 */
	public List<Map<String, Object>> getTjcx(String zdmc, Integer zdz){
		
		SysYh user = AppUtil.getCurrentUser();
		
	    String sql = "select a.zdmc, a.zdz, a.zdbm from sys_sjzd a where a.zl ="+"'lcxx'"+" and a.jb ="+"'2'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
		
	}
	
	/**
	 * 获取审核人
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @return
	 */
	public List<Map<String, Object>> getShr(String yhbh){
	    String sql = "SELECT a.yhbh,a.xm,c.jsmc,b.jsbh,d.bmmc,a.bmbh "
                             +"  FROM  sys_yh  a ,sys_zzjg  d,sys_yhjs b ,sys_js c "
                             +"  WHERE a.bmbh = d.bmbh  "
                             + "  AND a.yhbh = b.yhbh "
                             +"   AND b.jsbh = c.jsbh  "
                             +"  AND  c.jsmc = 'ROLE_INSTRUCTOR'     "
                             +" AND d.bmbh="
                             +" (SELECT sjbh FROM sys_zzjg WHERE bmbh = "
                             + " (SELECT b.sjbh FROM  sys_yh  a ,sys_zzjg  b "
                             +"  WHERE a.bmbh = b.bmbh   AND a.yhbh='"+yhbh+"'))"
                             +   " UNION  "
                             + " SELECT a.yhbh,a.xm,c.jsmc,b.jsbh,d.bmmc,a.bmbh "
                             + "  FROM  sys_yh  a ,sys_zzjg  d,sys_yhjs b ,sys_js c "
                             +" WHERE a.bmbh = d.bmbh " 
                             +"  AND a.yhbh = b.yhbh " 
                             +" AND b.jsbh = c.jsbh " 
                             +" AND  c.jsmc = 'ROLE_INSTRUCTOR'   "  
                             +" AND d.bmbh= (SELECT b.sjbh FROM  sys_yh  a ,sys_zzjg  b  "
                             +" WHERE a.bmbh = b.bmbh   AND a.yhbh='"+yhbh+"')";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 获取审核人
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @return
	 */
	public List<Map<String, Object>> getShr1(String yhbh){
	  /*  String sql = "SELECT a.yhbh,a.xm, c.jsmc,   b.jsbh, d.bmmc,  a.bmbh "
                   + " FROM         sys_yh  a ,    sys_zzjg  d,sys_yhjs b ,sys_js c  WHERE "
                   + "    a.bmbh = d.bmbh  AND a.yhbh = b.yhbh "   
                   +  "   AND b.jsbh = c.jsbh  AND  c.jsmc = 'ROLE_Teacher' "
                   +  "  AND d.bmbh= (  SELECT     b.bmbh    FROM   sys_yh  a ,  "
                   + "   sys_zzjg  b  WHERE     a.bmbh = b.bmbh "
                   + "   AND a.yhbh='"+yhbh+"') ";*/
		String sql = " SELECT  a.yhbh,b.jsbh,c.jsmc,a.bmbh,d.bmmc,a.xm  "
				+ " FROM  sys_yh a,sys_yhjs b ,sys_js c,sys_zzjg d "
				+ " WHERE a.yhbh = b.yhbh AND b.jsbh = c.jsbh"
				+ " and a.bmbh = d.bmbh "
				+ " and c.jsmc ='ROLE_SPJS'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	/**
	 * 根据openId获取绑定用户的信息
	 * @author oufeng
	 * @date 2016年1月5日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getUser(String openId){
		String sql="SELECT b.bmbh,c.bmmc,a.wxnc, a.wxh,b.xm,b.dlm,a.yhid as yhbh FROM sys_wxyh a "
				+ " left join sys_yh b on a.yhid=b.yhbh "
				+" left join sys_zzjg c on b.bmbh = c.bmbh "
				+ " where a.zt='1' "
				+ " and a.wxh='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 驳回的更新
	 * @author oufeng
	 * @date 2016年1月5日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getUpdatexx(String id){
		String sql="select id,lczl,lcmc,DATE_FORMAT(kssj,'%Y-%m-%d %H:%i') as kssj,"
				+ "  DATE_FORMAT(jssj,'%Y-%m-%d %H:%i') as jssj,shyj,"
				+ "  fqrxm,shr,bmbh,bmmc,lczt,lcnr,shr,DATE_FORMAT(shsj,'%Y-%m-%d %H:%i') as shsj "
				+ "  from zy_lcxx  where id ='"+id+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取部门名称
	 * @author oufeng
	 * @date 2016年1月5日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getBmmc(String bmbh){
		String sql="SELECT a.bmmc from sys_zzjg a where  a.bmbh='"+bmbh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取流程种类
	 * @author oufeng
	 * @date 2016年1月5日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getLczl(String str){
		String sql="SELECT  zdmc,zdbm,zdz  FROM sys_sjzd WHERE zl='lczl'   AND jb ='2' and zdbm='"+str+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取申请信息的页面
	 * @author oufeng
	 * @date 2016年1月5日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getSqxx(String yhbh){
		String sql="select id,lczl,lcmc,kssj,jssj,fqrxm,shr,bmbh,bmmc,lczt,lcnr from zy_lcxx"
				+ "  where shr =  '"+yhbh+"' and lczt='1'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取申请信息的详情的页面
	 * @author oufeng
	 * @date 2016年1月5日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getDetail(String id){
		String sql="select a.id,a.lczl,a.lcmc,DATE_FORMAT(a.kssj,'%Y-%m-%d %H:%i') as kssj,"
				+ "  DATE_FORMAT(a.jssj,'%Y-%m-%d %H:%i') as jssj,a.shyj,"
				+ "  a.fqrxm,a.shr,a.bmbh,a.bmmc,a.lczt,a.lcnr,a.shr,"
				+ " DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i') as shsj,b.zdmc as lcztmc  "
				+ "  from zy_lcxx a ,sys_sjzd b  "
				+ "  where a.lczt = b.zdz "
				+ " and b.zl='lczt' and b.jb='2'"
				+ " and a.id ='"+id+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 申请的通过
	 * @author oufeng
	 * @date 2016年1月5日
	 * @param id 用户Id
	 * @return
	 */
	public void updateSqxxtg(String id,String shyj,String shr){
		String sql="update zy_lcxx set shr='"+shr+"',lczt = 2  where id='"+id+"'";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 驳回
	 * @author oufeng
	 * @date 2016年1月5日
	 * @param id 用户Id
	 * @return
	 */
	public void updateSqxxbh(String id,String shyj,String shr){
		String sql="update zy_lcxx set shr='"+shr+"', lczt = 3  where id='"+id+"'";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取审批信息
	 * @author oufeng
	 * @date 2016年1月6日
	 * @return
	 */
	public List<Map<String, Object>> getSpxx(String yhbh) {
		String sql="SELECT a.id,a.lczt, DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i') as shsj, shyj,b.xm as shrxm  from zy_lcxx a ,sys_yh b "
				+ " where a.shr = b.yhbh and a.fqr='"+yhbh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取审批信息
	 * @author oufeng
	 * @date 2016年1月6日
	 * @return
	 */
	public List<Map<String, Object>> getCcjl(String yhbh) {
		String sql="SELECT  a.id,a.lczl,a.lcmc,DATE_FORMAT(a.kssj,'%Y-%m-%d %H:%i') as kssj,"
				+ "  DATE_FORMAT(a.jssj,'%Y-%m-%d %H:%i') as jssj,"
				+ "  a.fqrxm,a.shr,a.bmbh,a.bmmc,a.lczt,a.lcnr,c.zdmc as lcztmc, "
				+ " DATE_FORMAT(a.shsj,'%Y-%m-%d %H:%i') as shsj, "
				+ " shyj,b.xm as shrxm  from zy_lcxx a ,sys_yh b,sys_sjzd c "
				+ " where a.shr = b.yhbh "
				+" and a.lczt = c.zdz  and c.zl='lczt' and c.jb ='2'"
				+ " and a.fqr='"+yhbh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得角色
	 * @author oufeng
	 * @date 2016年1月6日
	 * @return
	 */
	public List<Map<String,Object>> getJs(String yhbh){
		String str="";
		if(yhbh!=null&&!"".equals(yhbh)){
			str=str+" and a.yhbh='"+yhbh+"'";
		}
		String sql="select c.jsmc  from sys_yh a ,sys_yhjs b ,sys_js c where a.yhbh = b.yhbh and b.jsbh = c.jsbh"+str;
		return jdbcTemplate.queryForList(sql);
	}
	
}
