package com.sliu.framework.app.sys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.sys.dao.SysZzjgDao;
import com.sliu.framework.app.sys.model.SysZzjg;

@Service
public class SysZzjgService {
	
	@Autowired
	private SysZzjgDao zzjgDao;
	
	public List<Map<String,Object>> getOrgOrMebTree(String node){
		List<Map<String,Object>> list=zzjgDao.getOrgTree(node);
		if(list.size()==0){
//			list=zzjgDao.getOrgOrMebTree(node);
		}
		return list;
	}
	
	public List<Map<String, Object>> findAttacheArea(String id,boolean isparentId){
		return zzjgDao.findAttacheArea(id,isparentId);
	}
	
	
	/**
	 * 更新
	 * @author:oufeng
	 * @return
	 */
	@Transactional
	public String update(HttpServletRequest request){
		String str="";
		String id =request.getParameter("id");
		String dm = request.getParameter("dm");
		String bz = request.getParameter("bz");
		String name = request.getParameter("name");
		String pid = request.getParameter("pid");
		String px = request.getParameter("px");
		/* List<Map<String, Object>> list =zzjgDao.checkData(id,dm,name);
		 if(list.size()!=0){
			 str="2";
		 }else{*/
			zzjgDao.updateZzjg(id,pid,name,dm,px,bz);
			str="1";
		/* }*/
		return str;
	}

	
	/**
	 *新增
	 * @author:oufeng
	 * @return
	 */
	@Transactional
	public String save(HttpServletRequest request){
		String str="";
		String jb="";
		String id =request.getParameter("id");
		String dm = request.getParameter("dm");
		String bz = request.getParameter("bz");
		String name = request.getParameter("name");
		String sjbh = request.getParameter("sjbh");
		String pid = request.getParameter("pid");
		String px = request.getParameter("px");
		if("".equals(pid)){pid="0";}
		List<Map<String, Object>> jblist = zzjgDao.getJb(pid);
		if(jblist.size()!=0){
			jb=jblist.get(0).get("jb").toString();
		}
		 List<Map<String, Object>> list =zzjgDao.checkData(id,dm,name);
		 if(list.size()!=0){
			 str="2";
		 } else{
			 SysZzjg zzjg =new SysZzjg();
			 zzjg.setId(dm);
			 zzjg.setBmbh(dm);
			 zzjg.setBmmc(name);
			 zzjg.setSjbh(sjbh);
			 zzjg.setBz(bz);
			 zzjg.setJb(Integer.parseInt(jb)+1);
			 zzjg.setPx(Integer.parseInt(px));
			 zzjgDao.save(zzjg);
			 str="1";
		 }
		return str;
	}
	
	/**
	 * 删除
	 * @author:oufeng
	 * @return
	 */
	@Transactional
	public String delete(HttpServletRequest request){
		String str="";
		String id =request.getParameter("id");
		zzjgDao.delete(id);
		str="1";
		return str;
	}


}
