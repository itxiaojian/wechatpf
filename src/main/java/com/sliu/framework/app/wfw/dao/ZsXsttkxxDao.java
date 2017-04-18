package com.sliu.framework.app.wfw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wfw.model.ZsXsttkxx;

/**
  *课表异动
  @Author oufeng	
  @Date 2016年8月10日 下午15:02:48
  @Version 1.0
*/
@Repository
public class ZsXsttkxxDao extends HibernateBaseDaoImpl<ZsXsttkxx, Long> {
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		private NamedParameterJdbcPager jdbcPager;
		
	
		/**
		 * 获取获取学年学期
		 * @author oufeng
		 * @date 2016年8月8日
		 * @return
		 */
		public List<Map<String,Object>> getXnXq(){
			String sql="SELECT  xn,dxq as xq FROM zs_xskb  ORDER BY xn desc,dxq DESC LIMIT 1";
			return jdbcTemplate.queryForList(sql);
		}
		
		/**
		 * 获取调停课信息
		 * @author oufeng
		 * @date 2016年8月8日
		 * @return
		 */
		public List<Map<String,Object>> getTkxx(String ksxq,String yhbh,String pages){
			String str="";
			String str1="";
			if(ksxq!=null&&!"".equals(ksxq)){
				str+=" and a.ttkbz='"+ksxq+"'";
			}
			if(yhbh!=null&&!"".equals(yhbh)){
				str+=" and b.xh='"+yhbh+"'";
			}
			if(pages!=null&&!"".equals(pages)){
				int num=(Integer.parseInt(pages)-1)*10;
				str1+=" order by b.xn DESC,b.dxq DESC limit "+num+",10 ";
			}
			String sql="SELECT a.kch,b.kcmc,a.dd,a.jc,a.zc,b.xh,b.xm,b.xn,b.dxq,"
					+ " a.rkjsgh,a.tksj,a.ttkbz,a.ttyy,a.rkjs  "
					+ " fROM zs_xsttkxx a,zs_xskb b WHERE a.kch=b.kcbh "+str
					+ " GROUP BY xh,xm,xn,dxq,kcbh,jc,dd "+str1;
			return jdbcTemplate.queryForList(sql);
		}
		

		/**
		 * 获取学生信息
		 * @author oufeng
		 * @date 2016年8月8日
		 * @return
		 */
		public List<Map<String,Object>> getXsxx(String xn,String xq ,String yhbh){
			String sql="SELECT xn,dxq,xh,xm  FROM zs_xskb "
					+ " WHERE xn='"+xn+"' AND dxq='"+xq+"' AND xh='"+yhbh+"' LIMIT 1 ";
			return jdbcTemplate.queryForList(sql);
		}
	}