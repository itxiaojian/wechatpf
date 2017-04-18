package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsWljf;


/**
 * 网络计费信息
 * @author wangxiangyang
 * @date  2016年8月9日
 */
@Repository
public class ZsWljfDao extends HibernateBaseDaoImpl<ZsWljf, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	@Autowired
	private ZsXscjDao dao;

	/**
	 * 查询网络计费信息
	 * @author wangxiangyang
	 * @date 2016年8月9日
	 * @param str
	 * @param user
	 * @return
	 */	
	public List<Map<String,Object>> getWljfList(String openId,String pages){
		String str="";
		String str1="";
		List<Map<String,Object>> user=dao.getWxyh(openId);
		if(user.size()!=0){
			List<Map<String,Object>> yh=dao.getYh(user.get(0).get("yhid")+"");
			if(yh.size()!=0 && !"admin".equals(yh.get(0).get("dlm"))){
				str +=" and zh='"+yh.get(0).get("dlm")+"'";
			}
			if("admin".equals(yh.get(0).get("dlm"))){
				if(pages!=null&&!"".equals(pages)){
					int num=Integer.parseInt(pages)*2;
					str1=str1+" order by zh DESC limit "+num+" ";
				}
			}
		}
		
		String sql = "select id,zh,xm,zhzt,fyzt,ye,sjzs,llzj,yhz,sjh,dh,scdlsj from zs_wljf where 1=1 "
				   + str + str1;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询网络计费信息
	 * @author wangxiangyang
	 * @date 2016年8月9日
	 * @param str
	 * @param user
	 * @return
	 */	
	public List<Map<String,Object>> getWljfListxx(String openId,String zh){
		
		String sql = "select a.id,(select b.xm from zs_wljf b where b.zh=a.yhbh) as xm,"
				   + "a.yhbh,a.ip,a.dlsj,a.zxsj,a.khdsl,a.zxyy,a.sc,a.xxll,a.sxll,a.jfzll from zs_wljfxx a "
				   + "where 1=1 "
				   + "and a.yhbh="+zh;
		return jdbcTemplate.queryForList(sql);
	}

}
