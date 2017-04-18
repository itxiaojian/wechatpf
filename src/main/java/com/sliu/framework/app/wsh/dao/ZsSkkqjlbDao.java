package com.sliu.framework.app.wsh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.wsh.model.ZsSkkqjlb;

@Repository
public class ZsSkkqjlbDao extends HibernateBaseDaoImpl<ZsSkkqjlb, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	@Autowired
	private ZsSkkqbDao dao;
	
	/**
	 * 查询商品数据
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param pages 页数
	 * @param splx 商品类型
	 * @return
	 */
	public List<Map<String,Object>> getGoodList(String pages,String keyword,String dxq,String id,String xn){
		String str="";
		if(keyword!=null&&!"".equals(keyword)){
			str=str+" and xm like '%"+keyword+"%' ";
		}
		str=str+" order by DATE_FORMAT(qdsj,'%Y-%m-%d %H:%i') desc";
//		if(pages!=null&&!"".equals(pages)){
//			int num=(Integer.parseInt(pages)-1)*10;
//			str=str+" limit "+num+",10 ";
//		}
		String sql="SELECT distinct a.xh, a.xm,c.xh as yqxh FROM zs_xskb a left join zs_skkqb b on a.kcbh=b.kch and a.skdd=b.bz and a.xn='"+xn+"' "
				+ "left join zs_skkqjlb c on b.id=c.kqid and a.xh=c.xh where a.dxq="+dxq+" and b.id="+id+" "+str;
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取商品的总条数
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param splx 商品类型
	 * @return
	 */
	public int getCount(String keyword,String dxq,String id,String xn){
		int rows = this.getGoodList("",keyword,dxq,id,xn).size();
		if(rows%10==0){
			return rows/10;
		}else{
			return rows/10+1;
		}
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2016年3月30日
	 * @param kqid
	 * @param xh
	 * @return
	 */
	public List<Map<String,Object>> getQdxx(String kqid,String xh){
		String str="";
		if(kqid!=null&&!"".equals(kqid)){
			str=str+" and kqid = "+kqid+" ";
		}
		if(xh!=null&&!"".equals(xh)){
			str=str+" and xh = '"+xh+"' ";
		}
		String sql="SELECT id, kqid, xh, xm, DATE_FORMAT(qdsj,'%Y-%m-%d %H:%i') as qdsj, bz FROM zs_skkqjlb where 1=1 "+str;
		return jdbcTemplate.queryForList(sql);
	}
}
