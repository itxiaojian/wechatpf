package com.sliu.framework.app.wlx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wlx.model.LxTxjl;

@Repository
public class WlxLxtxDao extends HibernateBaseDaoImpl<LxTxjl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 离校流程信息
	 * @author oufeng
	 * @version 2016年6月8日
	 * @return
	 */
    public List<Map<String, Object>> getLxLcxx(String stuid,String tacheid){
    	String str = "";
		 if (stuid != null && !"".equals(stuid)) {
			str += " and  a.stuid ='" + stuid + "'";
		 }else{str+=" and 1=2";}
		 if (tacheid != null && !"".equals(tacheid)) {
				str += " and  a.tacheid ='" + tacheid + "'";
			 }else{str+=" and 1=2";}
			String sql ="select a.tacheid,a.blrid,a.blrxm,a.bltime,a.blzt,a.reason,a.stuname,a.stuid,a.xsyx,a.xszy,b.tachename  "
                         +" from zs_lx_tachestatus a ,zs_lx_tache b WHERE a.tacheid = b.tacheid "+str;
			List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
			return list;
		}
    
	/**
	 * 获取openId
	 * @author oufeng
	 * @date 2016年6月8日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getOpenId(){
		/*String sql = "select a.stuid,a.wxh,a.tacheid from (SELECT a.stuid,b.wxh,a.tacheid  "
						+" FROM  (SELECT a.stuid,a.tacheid FROM zs_lx_tachestatus a "
						+ " WHERE  a.tacheid NOT IN (SELECT  jlid FROM zs_txjl group by jlid)) a ,sys_wxyh b"
						+" WHERE a.stuid = b.yhid"
						+" GROUP BY  a.stuid,a.tacheid,b.wxh "
		                +"  union all  SELECT a.stuid,b.wxh,a.tacheid  "
			            +" FROM  (SELECT a.stuid,a.tacheid FROM zs_lx_tachestatus a "
			            + " WHERE  a.stuid NOT IN (SELECT  xsxh FROM zs_txjl group by xsxh)) a ,sys_wxyh b"
			            +" WHERE a.stuid = b.yhid"
		             	+" GROUP BY  a.stuid,a.tacheid,b.wxh) a group by a.stuid,a.wxh,a.tacheid ";*/
		String sql=" SELECT a.stuid,a.wxh,a.tacheid,a.blzt,a.reason,a.tachename FROM "
				+ " (SELECT a.stuid,b.wxh,a.tacheid,a.blzt,a.reason,c.tachename"
                +" FROM  (SELECT a.stuid,a.tacheid,a.blzt,a.reason FROM zs_lx_tachestatus a "
                +" WHERE  NOT EXISTS (SELECT  f.jlid FROM zs_txjl f  WHERE a.tacheid=f.JLID )) a ,"
                + " sys_wxyh b,zs_lx_tache c "
                +" WHERE a.stuid = b.yhid"
                +" and a.tacheid=c.tacheid "
                +"	GROUP BY  a.stuid,a.tacheid,b.wxh,a.blzt,a.reason,c.tachename "
                +"	UNION ALL  SELECT a.stuid,b.wxh,a.tacheid,a.blzt,a.reason,c.tachename"
                +"	 FROM  (SELECT a.stuid,a.tacheid,a.blzt,a.reason FROM zs_lx_tachestatus a "
                +"  WHERE  NOT EXISTS (SELECT  f.xsxh FROM zs_txjl f WHERE a.stuid=f.xsxh)) a ,"
                +" sys_wxyh b,zs_lx_tache c"
                +"	 WHERE a.stuid = b.yhid"
                +"	and a.tacheid = c.tacheid"
                +"	 GROUP BY  a.stuid,a.tacheid,b.wxh,a.blzt,a.reason,c.tachename) a "
                +" GROUP BY a.stuid,a.wxh,a.tacheid ,a.blzt,a.reason,a.tachename";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 获取openId
	 * @author oufeng
	 * @date 2016年6月8日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getOpenId1(){
		String sql = "  SELECT a.stuid,b.wxh,a.tacheid,a.blzt,a.reason,c.tachename"
					+"	FROM  (SELECT a.stuid,a.tacheid,a.blzt,a.reason FROM zs_lx_tachestatus a "
					+"	 WHERE NOT EXISTS (SELECT  f.jlid FROM zs_txjl f  WHERE a.tacheid=f.JLID ))a ,"
					+"  sys_wxyh b,zs_lx_tache c "
					+"	 WHERE a.stuid = b.yhid "
                    +"   and a.tacheid=c.tacheid "
					+"	 GROUP BY  a.stuid,a.tacheid,b.wxh,a.blzt,a.reason";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取openId
	 * @author oufeng
	 * @date 2016年6月8日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getOpenId(String xh){
		String str = "";
		if (xh != null && !"".equals(xh)) {
			str += " and  b.dlm ='" + xh + "'";
		}else{str+=" and 1=2";}
		String sql = "select a.wxh from sys_wxyh a ,sys_yh b  where a.yhid=b.yhbh "+str;
		return jdbcTemplate.queryForList(sql);
	} 
	
	/**
	 * 获取提醒数据表是否有数据
	 * @author oufeng
	 * @date 2016年6月8日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getTxjlSize(){
		String sql = "select  id,jlid,xsxh,txnr,txsj  from zs_txjl  ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取提醒数据表是否有数据
	 * @author oufeng
	 * @date 2016年6月8日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getOpenIdbyId(String id){
		String sql = "select  wxh from zs_txjl where 1=1 and id=  '"+id+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取提醒的列表数据
	 * @author oufeng
	 * @date 2016年6月8日
	 * @param bxbh
	 * @return
	 */
	public List<Map<String, Object>> getTxlist(String dlm,String openId){
		String str="";
		if(!"".equals(dlm) && !"admin".equals(dlm)){
			str+=" and c.xsxh = '"+dlm+"' and c.wxh='"+openId+"'";
		}else if("admin".equals(dlm)){
			str+="";
		}else if("".equals(dlm)){
			str +=" and 1=2";
		}
		String sql = "SELECT  c.jlid AS tacheid,b.tachename,a.blzt,DATE_FORMAT(c.txsj,'%Y-%m-%d')AS bltime,c.xsxh "
                   +" FROM  zs_txjl c, zs_lx_tachestatus a ,zs_lx_tache b "
                   +" WHERE c.jlid  = b.tacheid "
                   +" AND c.xsxh = a.stuid "
                   +" AND c.jlid = a.tacheid "
                   +str
                   +" GROUP BY c.jlid,b.tachename,a.blzt,DATE_FORMAT(c.txsj,'%Y-%m-%d'),c.xsxh "
                   +" ORDER BY a.tacheid";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据openId获取绑定用户的信息
	 * @author oufeng
	 * @date 2016年6月8日
	 * @param openId 
	 * @return
	 */
	public List<Map<String,Object>> getUser(String openId){
		String sql="SELECT a.wxnc, a.wxh,b.xm,b.dlm,a.yhid FROM sys_wxyh a "
				+ "left join sys_yh b on a.yhid=b.yhbh where a.zt='1' and a.wxh='"+openId+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * @author oufeng
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlxxx(int tacheid,String openId){
		String str="";
		List<Map<String,Object>> user=this.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=this.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0){
				str=""+yh.get(0).get("dlm")+"";
			}
		}
		String sql="select  a.tacheid,a.stuid,a.stuname,a.xsyx,a.xszy,a.blzt,a.reason,b.sj,b.dd,b.zysx,c.tachename from zs_lx_tachestatus a"
				+ " left join zs_lx_zdxx b on b.tacheid=a.tacheid left join zs_lx_tache c on c.tacheid = a.tacheid where 1=1  and a.tacheid = '"+tacheid+"' and a.stuid = '"+str+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 根据openId获取绑定用户的ID
	 * @author oufeng
	 * @date 2016年6月7日
	 * @param wxh openId
	 * @return
	 */
	public List<Map<String,Object>> getWxyh(String wxh){
		String sql="select yhid from sys_wxyh where wxh='"+wxh+"' and zt=1";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获得提醒的状态的列表
	 * @author oufeng
	 * @date 2016年6月7日
	 * @param wxh openId
	 * @return
	 */
	public List<Map<String,Object>> getTxZtList(){
		String sql="SELECT a.stuid,a.tacheid,a.blztf,a.blzts,a.id FROM "
                 +" (SELECT  a.stuid,a.tacheid,a.blzt AS blztf,b.blzt AS blzts,b.id "
                 +" FROM zs_lx_tachestatus a,zs_txjl b"
                 +" WHERE a.tacheid =b.jlid "
                 +" AND a.stuid=b.xsxh "
                 +" ORDER BY a.stuid,a.tacheid)a "
                 +" WHERE (a.blztf =1 AND  blzts =0)OR(a.blztf =0 AND  blzts =1)";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取用户信息
	 * @author oufeng
	 * @date 2016年6月7日
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
}
