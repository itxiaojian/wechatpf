package com.sliu.framework.app.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.sys.model.SysZzjg;
import com.sliu.framework.app.util.AppUtil;

@Repository
public class SysAreaDao extends HibernateBaseDaoImpl<SysZzjg, String>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	public List<Map<String, Object>> findAttacheArea(String id,boolean isparentId){
		String sql = "";
		if(isparentId == true){
			
			sql = "select JB as \"orgSqe\",ID as \"id\",SJBH as \"parentId\","
					+ "NAME as \"text\" from sys_zzjg where BMBH='"+AppUtil.getCurrentUser().getBmbh()+"' and JB <> '3'";
		}else{
			sql = "select JB as \"orgSqe\",ID as \"id\",SJBH as \"parentId\","
					+ "NAME as \"text\" from sys_zzjg where SJBH='"+AppUtil.getCurrentUser().getBmbh()+"' and JB <> '3'";
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
	
	public List<Map<String,Object>> findOrgArchive(Integer start,Integer limit,String OrgId){
	    SysYh user = AppUtil.getCurrentUser();    //获得session用户
        String userCode = user.getUsername();
        String OrgIdStr = OrgId==null?"":" AND US.BMBH='"+OrgId+"'";
        
		 if(!userCode.equals("admin")){
		     OrgIdStr += " AND US.YHBH='"+user.getYhbh()+"'";
        }
		
		String sql = "select US.YHBH as \"id\", US.XM as \"userName\","
				+ "US.DLM as \"userCode\",US.DLM as \"assobjcode\" ,"
				+ "jg.BMBH as \"jgdh\",jg.BMMC as \"jgmc\" from "
				+ "sys_yh US left join sys_zzjg jg on US.BMBH=jg.BMBH "
				+ "where jg.JB <> '3' "+OrgIdStr
				+" and US.DLM <> 'admin' order by US.YHBH";
		System.out.println("====="+sql);
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String, Object>> getYhOrgOrMebTree(String _dc) {
		String userCode = null;
		String sql = "select org.sjbh as pid,org.BMMC as \"text\",org.BMBH as \"value\" from sys_zzjg org";
//		SysYh user = AppUtil.getCurrentUser();    //获得session用户
//		userCode = user.getUsername();
//		if(userCode.equals("admin")){
//		   // sql = "select org.id as \"id\",org.name as \"text\" from UNTECK_ORGANIZATION org where ";
//			
//			if(_dc.equals("treeContact") || _dc.equals("loadAllOrg")){   //跟节点
//				sql += " org.SJBH = '0' ";
//			}else{
//				sql += "org.SJBH = '"+ _dc +"'";
//				
//			}
//		}else{
//			
//			if(_dc.equals("treeContact")){   //跟节点
//				List<Map<String,Object>> list=this.getJg();
//				if(list.size()!=0){
//					sql += " org.BMBH = '"+list.get(0).get("bmbh")+"' ";
//				}
//			}else if(_dc.equals("loadAllOrg")){  
//				sql += " org.SJBH = '0' ";
//			}else{
//				sql += "org.SJBH = '"+ _dc +"'";
//				
//			}
//		}
		
		return (jdbcTemplate.queryForList(sql));
	}
	

	/**
	 * 获取机构树
	 * @return
	 */
	public List<Map<String,Object>> getOrgTree(String node){
		String userCode = null;
		String sql = "select org.BMBH as \"id\",org.BMMC as \"text\" from sys_zzjg org where org.JB <> '3' and ";
		SysYh user = AppUtil.getCurrentUser();    //获得session用户
		userCode = user.getUsername();
		if(userCode.equals("admin")){
		   // sql = "select org.id as \"id\",org.name as \"text\" from UNTECK_ORGANIZATION org where ";
			
			if(node.equals("treeContact") || node.equals("loadAllOrg")){   //跟节点
				sql += " org.SJBH = '0' ";
			}else{
				sql += "org.SJBH = '"+ node +"'";
				
			}
		}else{
			
			if(node.equals("treeContact")){   //跟节点
				sql += " org.BMBH = '"+user.getBmbh()+"' ";
			}else if(node.equals("loadAllOrg")){  
				sql += " org.SJBH = '0' ";
			}else{
				sql += "org.SJBH = '"+ node +"'";
				
			}
		}
		
		return (jdbcTemplate.queryForList(sql));
	}

	public List<Map<String, Object>> getYhByjg(String bmbh) {
		String sql="SELECT yhbh, dlm, xm, mm, bmbh, gwbh, sjh, yx, zhdlsj, yhzt FROM sys_yh where bmbh='"+bmbh+"' or gwbh='"+bmbh+"'";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 获取机构树
	 * @return
	 */
	public List<Map<String,Object>> getOrgTree1(String node){
		String userCode = null;
		String sql = "select org.BMBH as \"id\",org.BMMC as \"text\" from sys_zzjg org where org.JB <> '3' and ";
		SysYh user = AppUtil.getCurrentUser();    //获得session用户
		userCode = user.getUsername();
		if(userCode.equals("admin")){
		   // sql = "select org.id as \"id\",org.name as \"text\" from UNTECK_ORGANIZATION org where ";
			
			if(node.equals("treeContact") || node.equals("loadAllOrg")){   //跟节点
				sql += " org.SJBH = '0' ";
			}else{
				sql += "org.SJBH = '"+ node +"'";
				
			}
		}else{
			
			if(node.equals("treeContact")){   //跟节点
				List<Map<String,Object>> list=this.getJg();
				if(list.size()!=0){
					sql += " org.BMBH = '"+list.get(0).get("bmbh")+"' ";
				}
			}else if(node.equals("loadAllOrg")){  
				sql += " org.SJBH = '0' ";
			}else{
				sql += "org.SJBH = '"+ node +"'";
				
			}
		}
		
		return (jdbcTemplate.queryForList(sql));
	}
	
	public List<Map<String,Object>> getJg(){
		String sql="select bmbh,bmmc from sys_zzjg where jb='1'";
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 获取机构树
	 * @return
	 */
	public List<Map<String,Object>> getOrgOrMebTree(String node){
		String userCode = null;
		String sql = "select user.DLM as \"id\",user.XM as \"text\" from sys_yh user where user.yhzt=1 and ";
		SysYh user = AppUtil.getCurrentUser();    //获得session用户
		userCode = user.getUsername();
		if(userCode.equals("admin")){
		   // sql = "select org.id as \"id\",org.name as \"text\" from UNTECK_ORGANIZATION org where ";
			
			if(node.equals("treeContact") || node.equals("loadAllOrg")){   //跟节点
				sql += " user.BMBH = '00001' ";
			}else{
				sql += "user.BMBH = '"+ node +"'";
				
			}
		}else{
			
			if(node.equals("treeContact")){   //跟节点
				sql += " user.BMBH = '"+user.getBmbh()+"' ";
			}else if(node.equals("loadAllOrg")){  
				sql += " user.BMBH = '00001' ";
			}else{
				sql += "user.BMBH = '"+ node +"'";
				
			}
		}
		
		return (jdbcTemplate.queryForList(sql));
	}
}
