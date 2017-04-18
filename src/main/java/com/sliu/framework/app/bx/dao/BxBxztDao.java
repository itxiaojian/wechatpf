package com.sliu.framework.app.bx.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.bx.model.BxBxzt;
import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

@Repository
public class BxBxztDao extends HibernateBaseDaoImpl<BxBxzt, Long> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取机构树
	 * @return
	 */
	public List<Map<String,Object>> getOrgTree(String node){
		String userCode = null;
		String sql = "select org.id as \"id\",org.ztbh as \"bmbh\",org.ztmc as \"text\",PX as \"px\",BZ as \"bz\" "
				+ "  from bx_bxzt org where 1=1 ";
		SysYh user = AppUtil.getCurrentUser();    //获得session用户
		userCode = user.getUsername();
		List<Map<String,Object>> js=this.getJs(user.getYhbh());
		int num=0;
		if(js.size()!=0){
			for(int i=0;i<js.size();i++){
				if("ROLE_ZJL".equals(js.get(i).get("jsmc"))||"ROLE_FZJL".equals(js.get(i).get("jsmc"))){
					num++;
				}
			}
			if(userCode.equals("admin")||num>0){
					if(node.equals("0") || node.equals("loadAllOrg")){   //跟节点
						sql += " and org.SJZT = '0'  order by org.px";
					}else{
						sql += " and org.SJZT = '"+ node +"' order by org.px";
					}
				}else{
					
					if(node.equals("0")){   //跟节点
						sql += "  order by org.px ";
					}else if(node.equals("loadAllOrg")){  
						sql += " and  org.SJZT = '0'  order by org.px";
					}else{
					sql += "  and  org.SJZT = '"+ node +"' order by org.px";
					}
				}
		}else{
			if(userCode.equals("admin")){
					if(node.equals("0") || node.equals("loadAllOrg")){   //跟节点
						sql += "  and  org.SJZT = '0'  order by org.px";
					}else{
						sql += " and  org.SJZT = '"+ node +"' order by org.px";
					}
				}else{
					if(node.equals("0")){   //跟节点
						sql += "  order by org.px";
					}else if(node.equals("loadAllOrg")){  
						sql += "  and  org.SJZT = '0'  order by org.px";
					}else{
						sql += " and  org.SJZT = '"+ node +"'  order by org.px";
						
					}
				}
		}
		return (jdbcTemplate.queryForList(sql));
	}

	/**
	 *获取级别
	 * @return
	 */
	public List<Map<String,Object>>  getJb(String pid) {
		String sql="select ztjb as jb from bx_bxzt where id='"+pid+"'";
		 return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 *编辑
	 * @return
	 */
	public void  updateZzjg(String id,String pid,String name,String dm,String px,String bz) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sim.format(new Date());
		String sql="update bx_bxzt set ztbh ='"+dm+"',ztmc='"
					+name+"',px='"+px+"',bz='"+bz+"',tjsj=' "+date+"'"
					+ "  where id='"+id+"' and sjzt='"+pid+"'";
		 jdbcTemplate.execute(sql);
	}
	
//	/**
//	 * 获取机构树
//	 * @return
//	 */
//	public List<Map<String,Object>> getOrgOrMebTree(String node){
//		String userCode=""; 
//		String sql = "select user.DLM as \"id\",user.XM as \"text\" from sys_yh user"
//				+ "  where user.yhzt=1 and  ";
//		SysYh user = AppUtil.getCurrentUser();    //获得session用户
//		userCode = user.getUsername();
//		if(userCode.equals("admin")){
//			if(node.equals("0") || node.equals("loadAllOrg")){   //跟节点
//				sql += " user.BMBH = '00001' ";
//			}else{
//				sql += "user.BMBH = '"+ node +"'";
//			}
//		}else{
//			if(node.equals("0")){   //跟节点
//				sql += " user.BMBH = '"+user.getBmbh()+"' ";
//			}else if(node.equals("loadAllOrg")){  
//				sql += " user.BMBH = '00001' ";
//			}else{
//				sql += "user.BMBH = '"+ node +"'";
//			}
//		}
//		return (jdbcTemplate.queryForList(sql));
//	}
	
	/**
	 * 获取人员的角色
	 * @author liujiansen
	 * @date 2015年9月28日
	 * @param yhbh
	 * @return
	 */
	public List<Map<String,Object>> getJs(String yhbh){
		String sql="select c.jsbh,c.jsmc,c.bz,c.jszt from sys_yh a left join sys_yhjs b on a.yhbh=b.yhbh "
				+ "left join sys_js c on b.jsbh=c.jsbh where a.yhbh='"+yhbh+"'";
		return jdbcTemplate.queryForList(sql);
	}

	
	/**
	 * 报修主题最大的id
	 * @author oufeng
	 * @date 2015年8月11日
	 * @return
	 */
	public List<Map<String, Object>> getMaxId() {
		String sql = "select max(id) as id from bx_bxzt " ;
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> findAttacheArea(String id,boolean isparentId){
		String sql = "";
		if(isparentId == true){
		    sql = "select ZTJB as \"orgSqe\",ID as \"id\",SJZT as \"parentId\","
					+ "ZTMC as \"text\",PX as \"px\",BZ as \"bz\" from bx_bxzt  order by px";
		}else{
			sql = "select ZTJB as \"orgSqe\",ID as \"id\",SJZT as \"parentId\","
					+ "ZTMC as \"text\",PX as \"px\",BZ as \"bz\"  from bx_bxzt  order by px";
		}
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if(!list.isEmpty()){
			for(Map<String,Object> map : list){
				if(!list.isEmpty()){
//					map.put("checked",false);
					map.put("leaf", false);
				}
			}
		}
		return list;
	}
	
}
