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
import com.sliu.framework.app.wzy.model.ZyXyxw;

/**
 * 主页学院新闻
 * @author zhangyi
 * @version 创建时间：2015年6月3日  下午3:53:13
 */
@Repository
public class ZyXyxwDao extends HibernateBaseDaoImpl<ZyXyxw, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
/**
 *  分页查询学院新闻
 * @author   zhangyi
 * @version 创建时间：2015年6月3日  下午3:53:20
 * @param start
 * @param limit
 * @param xwlx  新闻类型
 * @param xwbt  新闻标题
 * @return
 */
	public Pagination<Map<String,Object>> getXyxwList(Integer start, Integer limit,String xwlx,String xwbt) {

		String sql = "select a.id,a.xwbt,a.xwzs,a.xwnr,DATE_FORMAT(a.sxsj,'%Y-%m-%d %H:%i:%S') as sxsj,DATE_FORMAT(a.gqsj,'%Y-%m-%d %H:%i:%S') as gqsj,a.fbr,a.fbrxm,b.bmmc,a.bmbh,a.xwlx,a.xwzt from ZY_XYXW a "
				+ " left join SYS_ZZJG b on a.BMBH=b.BMBH where 1=1";
		if (StringUtils.hasText(xwlx)) {
			sql += " and a.xwlx = '"+xwlx+"'";
		}
		if (StringUtils.hasText(xwbt)) {
			sql += " and a.xwbt = '"+xwbt+"'";
		}
		
		sql += " order by a.sxsj desc";
		
		return jdbcPager.queryPage(sql, start, limit);
	}
	
  /**
   *  查询新闻列表  
   * @author   duanpeijun
   * @version 创建时间：2015年6月3日  下午3:54:16
   * @return
   */
   public List<Map<String, Object>> getList() {
	String sql = "select a.xwbt, a.id, a.sxsj  from ZY_XYXW a";
	List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
	return list;
   }

   /**
    * 获取主页菜单
    * @author:zhangyi 
    * @version 创建时间：2016年1月13日 上午11:09:08 
    * @return
    */
	public List<Map<String, Object>> getListMenu(String mklx) {
		String sql = "select t.cdmc, t.id,t.cdtbmc,t.cdurl,t.px from zy_zycdpz t where t.sfqy='1' and t.mklx='"+mklx+"' order by t.px asc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	 /**
     *  查询相应的招生考试的详细列表
     * @author   oufeng
     * @version 创建时间：2015年6月3日  下午3:54:16
     * @return
     */
     public List<Map<String, Object>> getDetailById(String id) {
    	 String str="";
    	 if (StringUtils.hasText(id)) {
 			str += " and id = '"+id+"'";
 		}else{str+=" and  1=2";}	 
    	 String sql = " SELECT  id,xwbt,DATE_FORMAT(sxsj,'%Y-%m-%d')AS sxsj,xwzs,xwnr,bmbh,bmmc,fbrxm,fbr FROM zy_xyxw "
  			   + " WHERE 1=1"+str;
    	 List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    	 return list;
     }
     
     /**
      *  查询新闻列表  
      * @author   duanpeijun
      * @version 创建时间：2015年6月3日  下午3:54:16
      * @return
      */
      public List<Map<String, Object>> getTitle(String zdbm) {
    	  String sql = "SELECT zdmc FROM sys_sjzd WHERE zl='zsxw'  AND zdbm ='"+zdbm+"'";
    	  List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    	  return list;
      }
      
      /**
      *  分页查询招生新闻
      * @author   zhangyi
      * @version 创建时间：2015年6月3日  下午3:53:20
      * @param start
      * @param limit
      * @param xwlx  新闻类型
      * @param xwbt  新闻标题
      * @return
      */
     	public Pagination<Map<String,Object>> getZsxwList(Integer start, Integer limit,String xwlx,String xwbt) {
     		SysYh yh =AppUtil.getCurrentUser();
     		String yhbh=yh.getYhbh();
     		String sql = "select a.id,a.xwbt,a.xwzs,a.xwnr,a.px,DATE_FORMAT(a.sxsj,'%Y-%m-%d %H:%i:%S') as sxsj,DATE_FORMAT(a.gqsj,'%Y-%m-%d %H:%i:%S') as gqsj,a.fbr,a.fbrxm,b.bmmc,a.bmbh,a.xwlx,a.xwzt,c.maxPx from ZY_XYXW a "
     				+ " left join SYS_ZZJG b on a.BMBH=b.BMBH left join (select max(px) as maxPx,xwlx from ZY_XYXW group by xwlx)c on a.xwlx=c.xwlx where a.fbr='"+yhbh+"' and 1=1";
     		if (StringUtils.hasText(xwlx)) {
     			sql += " and a.xwlx = '"+xwlx+"'";
     		}
     		if (StringUtils.hasText(xwbt)) {
     			sql += " and a.xwbt = '"+xwbt+"'";
     		}
     		sql += " order by a.xwlx,a.px asc";
     		return jdbcPager.queryPage(sql, start, limit);
     	}
     	
     	/**
     	 * 获取招生新闻类型
     	 * @author zhangyan
     	 * @date 2016年10月11日 上午10:38:18
     	 * @param 
     	 * @return
     	 */
     	public List<Map<String, Object>> getTjcx(String zdmc, Integer zdz) {
    		String sql =" select a.zdz,a.zdmc,a.zdbm from sys_sjzd a where a.zl="+"'zsxw'"+" and a.jb ="+"'2'";
    		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
    		return list;
    	}
     	
     	public List<Map<String, Object>> getBypx(int px, String xwlx) {
    		String sql =" select a.id from zy_xyxw a where a.px="+px+" and a.xwlx ='"+xwlx+"'";
    		return jdbcTemplate.queryForList(sql);
    	}
     	
     	 /**
         *  查询相应的招生考试的列表
         * @author   oufeng
         * @version 创建时间：2015年6月3日  下午3:54:16
         * @return
         */
         public List<Map<String, Object>> getListByZdbm(String zdbm) {
      	String sql = " SELECT  id,xwbt,DATE_FORMAT(sxsj,'%Y-%m-%d')AS sxsj,xwzs,px  FROM zy_xyxw "
      			   + " WHERE xwlx IN(SELECT zdz FROM sys_sjzd WHERE zl='zsxw'  AND zdbm ='"+zdbm+"') and xwzt=1"
      			   + " order by px ";
      	List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
      	return list;
         }

}
