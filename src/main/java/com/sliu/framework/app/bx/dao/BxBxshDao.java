package com.sliu.framework.app.bx.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxspyjb;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;

/**
 * 报修审核
 * @Author oufeng
 * @Date 2015年8月10日 下午4:37:13
 * @Version 1.0
 */
@Repository
public class BxBxshDao extends HibernateBaseDaoImpl<BxBxspyjb, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcPager jdbcPager;

	/**
	 * 报修的维修大类
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
public List<Map<String, Object>> getWxdl(){
		String sql = "select  a.id ,ztmc from bx_bxzt a,bx_bxsq b   "
				+ " where a.ztmc = b.wxdl and  ztjb= 2 and  b.zt=1  group by a.ztmc ,a.id   order by px  ";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String, Object>> getWxdl(String id ) {
		String str = "";
		if (id != null && !"".equals(id)) {
			str += " and  id=" + id + "";
		}
	String sql = "select  wxdl  from bx_bxsq   "
			+ " where 1=1 "+str;
	return jdbcTemplate.queryForList(sql);
}
	
	/**
	 * 维修工种类
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getWxzl(String id) {
		String str = "";
		String idstr="";
		if (id != null && !"".equals(id)) {
		String[] ids = id.trim().split(","); 
		for(int i=0; i<ids.length; i++) { 
			if(ids.length==1){
				idstr = "'"+ids[i]+"'";
			}else if(i==ids.length-1){
			 idstr+= "'"+ids[i]+"'";
		}else{
			idstr+= "'"+ids[i]+"'"+",";
		}
		}
		}
		str += " and  b.id  in(" + idstr + ")";
		String sql = "select zdbm,zdz from sys_sjzd a ,bx_bxsq b  "
				+ " where a.zdbm = b.wxdl "
				+str
				+ " and  zl ='wxglx' and jb=2"
		        +" group by zdbm,zdz ";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 获得维修工
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getWxg() {
		String sql ="SELECT  a.yhbh,a.xm FROM sys_yh a,sys_yhjs b ,sys_js c" 
				+"  WHERE  1=1  AND a.yhbh = b.yhbh "
				+" AND b.jsbh = c.jsbh AND c.jsmc= 'ROLE_WXG'";
		 return jdbcTemplate.queryForList(sql);
				
	}

	/**
	 * 获得派出人
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getSender(Integer id) {
		String str = "";
		if (id == 1) {
			str = "ROLE_WXG_MG";
		} else if (id == 2) {
			str = "ROLE_WXG_SG";
		} else if (id == 3) {
			str = "ROLE_WXG_DG";
		} else  {
			str = "ROLE_WXG_HG";
		}
		String sql = "select  a.yhbh,a.xm from sys_yh a,sys_yhjs b ,sys_js c  "
				+ "  where  1=1  and a.yhbh = b.yhbh "
				+ " and b.jsbh = c.jsbh	 and c.jsmc= "
				+"'"+str+"'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public void toRejecWxjl(Integer dm) {
		String str = "";
		if (dm != null && !"".equals(dm)) {
			str += " and  bxid =" + dm + "";
		}
		String sql = "update  " + "  bx_bxwxjl set  wxzt =3  where 1=1 " + str;
		jdbcTemplate.execute(sql);
	}

	/**
	 * 派单完成更新报修意见表
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public void updateSpyj(Long dm) {
		String str = "";
		if (dm != null && !"".equals(dm)) {
			str += " and  bxid =" + dm + "";
		}
		String sql = "update  " + "  bx_bxspyjb set  zt =2  where 1=1 " + str;
		jdbcTemplate.execute(sql);
	}
	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public void toRejecSp(Integer dm) {
		String str = "";
		if (dm != null && !"".equals(dm)) {
			str += " and  id =" + dm + "";
		}
		String sql = "update  " + "  bx_bxsq set  zt =3  where 1=1  " + str;
		jdbcTemplate.execute(sql);
	}

	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public void updateBxsp(Long bxId) {
		String str = "";
		if (bxId != null && !"".equals(bxId)) {
			str += " and  id =" + bxId + "";
		}
		String sql = "update  " + "  bx_bxsq set  zt =2  where 1=1  " + str;
		jdbcTemplate.execute(sql);
	}

	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public void toRejecSpyj(Integer dm, String yhbh, String yhxm, Date data,
			String str) {
		String str1 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data1 = sdf.format(data);
		if (dm != null && !"".equals(dm)) {
			str1 += " and  bxid =" + dm + "";
		}
		String sql = "update  " + "  bx_bxspyjb set  zt =3,  " + "  spyj = '"
				+ str + "'," + " spsj='" + data1 + "'," + " sprbh='" + yhbh
				+ "'," + "sprxm='" + yhxm + "'," + " sftg=1 " + " where 1=1  "
				+ str1;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 报修主题
	 * @author chenhui
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getBxzt  (String id) {
		String str = "";
		if (str != null && !"".equals(id)) {
			str += " and  a.sjzt ='" + id + "'";
		}
		String sql = "select  a.id,a.ztmc from bx_bxzt a where  1=1 " +str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取openId
	 * @author chenhui
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getOpenId(String xgh){
		String str = "";
		if (xgh != null && !"".equals(xgh)) {
			str += " and  b.dlm ='" + xgh + "'";
		}
		String sql = "select a.wxh from sys_wxyh a ,sys_yh b  where a.yhid=b.yhbh "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取学工号
	 * @author chenhui
	 * @date 2015年8月11日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getWxrbh(String id ){
		String str = "";
		if (id != null && !"".equals(id)) {
			str += " and  bxid ='" + id + "'";
		}
		String sql = "select wxrbh,MAX(pdsj) as pdsj,wxrxm from bx_bxwxjl  where 1=1 "+str +" group by bxid,wxrbh";
		return jdbcTemplate.queryForList(sql);
	}

	
}
