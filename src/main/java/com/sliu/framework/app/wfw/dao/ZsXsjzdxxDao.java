package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXsjzdxx;

/**
 * 奖助贷信息
 * 
 * @author chenhui
 * @date 2015年6月19日
 */
@Repository
public class ZsXsjzdxxDao extends HibernateBaseDaoImpl<ZsXsjzdxx, String> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsXscjDao dao;

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月5日
	 * @param str
	 * @param user
	 * @param code
	 * @return
	 */
	public String getZsXsjzdxx(String str, List<Map<String, Object>> user, String code) {
		List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
		if (yh.size() != 0) {
			String js = (yh.get(0).get("jsmc") + "").trim();
			if ("ROLE_INSTRUCTOR".equals(js)) {
				// 辅导员角色
				List<Map<String, Object>> bj = dao.getBjxx((yh.get(0).get("dlm") + "").trim());
				str = " and bj in (";
				String cx = "";
				if (bj.size() != 0) {
					for (int i = 0; i < bj.size(); i++) {
						if (i < bj.size() - 1) {
							cx = cx + "'" + bj.get(i).get("bjbh") + "',";
						} else {
							cx = cx + "'" + bj.get(i).get("bjbh") + "')";
						}
					}
				} else {
					cx = cx + "'')";
				}
				str = str + cx;
				if (code != null && !"".equals(code)) {
					str = str + " and concat(ifnull(xh,''),ifnull(xm,'')) like '%" + code + "%'";
				}
			} else {
				if (code != null && !"".equals(code)) {
					str = str + " and concat(ifnull(xh,''),ifnull(xm,'')) like '%" + code + "%'";
				}
			}
		}
		return str;
	}

	/**
	 * 根据当前登陆人获取登录人的班级信息
	 * 
	 * @author chenhui
	 * @date 2015年5月28日
	 * @return
	 */
	public List<Map<String, Object>> getBjxx(String dlm) {
		String str = "";
		if (dlm != null && !"".equals(dlm)) {
			str = str + " and dlm='" + dlm + "'";
		}
		String sql = "select id,dlm,xm,bjbh,bjmc,bz from sys_xsbjb where 1=1 " + str;
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 获取用户信息
	 * 
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param id
	 *            用户Id
	 * @return
	 */
	public List<Map<String, Object>> getYh(String id) {
		String str = "";
		if (id != null && !"".equals(id)) {
			str = str + " and a.yhbh='" + id + "'";
		}
		String sql = "select a.dlm,a.xm,a.yhbh,c.jsmc from sys_yh a " + "left join sys_yhjs b on a.yhbh=b.yhbh left join sys_js c on b.jsbh=c.jsbh where 1=1 " + str;
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据页数和标题查询奖学金数据
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param pages
	 *            页数
	 * @param code
	 *            学号和姓名
	 * @return
	 */
	public List<Map<String, Object>> getJzdxxList(String bjmc, String openId, String pages, String yhbh) {
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		String str = "";
		if (bjmc != null && !"".equals(bjmc)) {
			str = str + "and t.bjmc like '%" + bjmc + "%'";
		}
		if (Integer.parseInt(pages) == 1) {
			int num = Integer.parseInt(pages) * 10;
			str = str + " order by t.bjmc DESC limit " + num + " ";
		} else {
			int num = (Integer.parseInt(pages) - 1) * 10;
			str = str + " order by t.bjmc DESC limit " + num + ",10";
		}

		String sql = "select t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.lx=1 and xh='" + thisdlm + "'";
		String jssql = "select b.jsbh,b.jsmc from sys_yh t left join sys_yhjs a on t.yhbh=a.YHBH left join sys_js b on a.jsbh=b.jsbh where t.yhbh='" + user.get(0).get("yhid").toString() + "'";
		List<Map<String, Object>> listjs = jdbcTemplate.queryForList(jssql);
		if (listjs.size() > 0) {
			// String jsbhString = "ROLE_INSTRUCTOR";
			for (Map<String, Object> map : listjs) {
				String jsbh = map.get("jsmc").toString();
				if ("ROLE_INSTRUCTOR".equalsIgnoreCase(jsbh)) {
					sql = "select distinct t.xm,t.xh,t.BJMC,t.lx,t.xb from zs_xsjzdxx t where t.bjbh in (select a.classid as bjbh from fdy_and_bj a where a.loginid='" + thisdlm
							+ "')AND t.lx=1 " + str;
				}

			}
		}
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据页数和标题查询助学金数据
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param pages
	 *            页数
	 * @param code
	 *            学号和姓名
	 * @return
	 */
	public List<Map<String, Object>> getZxxxList(String code, String openId, String pages, String yhbh) {
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		String str = "";
		if (code != null && !"".equals(code)) {
			str = str + "and t.bjmc like '%" + code + "%'";
		}
		if (Integer.parseInt(pages) == 1) {
			int num = Integer.parseInt(pages) * 10;
			str = str + " order by t.bjmc DESC limit " + num + " ";
		} else {
			int num = (Integer.parseInt(pages) - 1) * 10;
			str = str + " order by t.bjmc DESC limit " + num + ",10";
		}
		String sql = "select t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.lx=2 and xh='" + thisdlm + "'";
		String jssql = "select b.jsbh,b.jsmc from sys_yh t left join sys_yhjs a on t.yhbh=a.YHBH left join sys_js b on a.jsbh=b.jsbh where t.yhbh='" + user.get(0).get("yhid").toString() + "'";
		List<Map<String, Object>> listjs = jdbcTemplate.queryForList(jssql);
		if (listjs.size() > 0) {
			// String jsbhString = "ROLE_INSTRUCTOR";
			for (Map<String, Object> map : listjs) {
				String jsbh = map.get("jsmc").toString();
				if ("ROLE_INSTRUCTOR".equalsIgnoreCase(jsbh)) {
					sql = "select distinct t.xm,t.xh,t.BJMC,t.lx,t.xb from zs_xsjzdxx t where t.bjbh in (select a.classid as bjbh from fdy_and_bj a where a.loginid='" + thisdlm
							+ "')AND t.lx=2 " + str;
				}

			}
		}
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据页数和标题查询贷款数据
	 * 
	 * @author duanpeijun
	 * @date 2015年6月18日 
	 *            学号和姓名
	 * @return
	 */
	public List<Map<String, Object>> getDkxxList(String code, String openId, String pages, String yhbh) {
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		String str = "";
		if (code != null && !"".equals(code)) {
			str = str + "and t.bjmc like '%" + code + "%'";
		}
		if (Integer.parseInt(pages) == 1) {
			int num = Integer.parseInt(pages) * 10;
			str = str + " order by t.bjmc DESC limit " + num + " ";
		} else {
			int num = (Integer.parseInt(pages) - 1) * 10;
			str = str + " order by t.bjmc DESC limit " + num + ",10";
		}
		String sql = "select t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.lx=3 and xh='" + thisdlm + "'";
		String jssql = "select b.jsbh,b.jsmc from sys_yh t left join sys_yhjs a on t.yhbh=a.YHBH left join sys_js b on a.jsbh=b.jsbh where t.yhbh='" + user.get(0).get("yhid").toString() + "'";
		List<Map<String, Object>> listjs = jdbcTemplate.queryForList(jssql);
		if (listjs.size() > 0) {
			// String jsbhString = "ROLE_INSTRUCTOR";
			for (Map<String, Object> map : listjs) {
				String jsbh = map.get("jsmc").toString();
				if ("ROLE_INSTRUCTOR".equalsIgnoreCase(jsbh)) {
					sql = "select distinct t.xm,t.xh,t.BJMC,t.lx,t.xb from zs_xsjzdxx t where t.bjbh in (select a.classid as bjbh from fdy_and_bj a where a.loginid='" + thisdlm
							+ "')AND t.lx=3 " + str;
				}
			}
		}
		System.out.println(listjs);
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据openId获取绑定用户的ID
	 * 
	 * @author liujiansen
	 * @date 2015年6月15日
	 * @param wxh
	 *            openId
	 * @return
	 */
	public List<Map<String, Object>> getWxyh(String wxh) {
		String sql = "select yhid from sys_wxyh where wxh='" + wxh + "' and zt=1";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 根据id查看学生的详细信息
	 * @author chenhui
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String, Object>> getXsrzxxDetail(Long id){
		
		String sql="select t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.id='"+ id +"' ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查看学生的基本信息
	 * @author chenhui
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String, Object>> getXs(String openId){
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		String sql="select t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.xh='"+ thisdlm +"' GROUP BY t.XH";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查看学生的基本信息
	 * @author chenhui
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String, Object>> getXsxx(String xh){
		String sql="select distinct t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.xh='"+ xh +"' GROUP BY t.XH";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据xh查看学生的详细奖学金信息
	 * @author chenhui
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String, Object>> getJzdxxListDetail(String xh){
		
		String sql="select t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.lx=1 and t.xh='"+ xh +"' ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据xh查看学生的详细贷款信息
	 * @author chenhui
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String, Object>> getDkxxListDetail(String xh){
		
		String sql="select t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.lx=3 and t.xh='"+ xh +"' ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据xh查看学生的详细助学金信息
	 * @author chenhui
	 * @date 2016年8月9日
	 * @return
	 */
	public List<Map<String, Object>> getZxxxListDetail(String xh){
		
		String sql="select t.id,t.xh,t.xm,t.xb,t.lx,t.zy,DATE_FORMAT(t.sj,'%Y-%m-%d')as sj,t.yx,t.je,t.bjmc,t.bjbh from zs_xsjzdxx t where 1=1 AND t.lx=2 and t.xh='"+ xh +"' ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取班级
	 * @author 班级
	 * @date 2015年8月07日
	 * @return
	 */
	public List<Map<String,Object>> getBj(String openId){
		List<Map<String, Object>> user = dao.getWxyh(openId);
		String thisdlm = "";
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			thisdlm = yh.get(0).get("dlm").toString();

		}
		String sql="select t.classname from fdy_and_bj t where t.loginid='" + thisdlm+"'";
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 获取当前登录人的信息
	 * 
	 * @author chenhui
	 * @date 2015年5月21日
	 * @return
	 */
	public List<Map<String, Object>> getGrTsjyxx(String openId, String pages) {
		String str = "";
		List<Map<String, Object>> user = dao.getWxyh(openId);
		if (user.size() != 0) {
			List<Map<String, Object>> yh = dao.getYh(user.get(0).get("yhid") + "");
			if (yh.size() != 0 && !"admin".equals(yh.get(0).get("dlm"))) {
				str += " and xh='" + yh.get(0).get("dlm") + "'";
			}
		}

		String sql = "select distinct xh,xm from zs_xsjzdxx where 1=1 " + str;
		return jdbcTemplate.queryForList(sql);
	}
}
