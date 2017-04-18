package com.sliu.framework.app.wsh.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.wsh.dao.WjObjectDao;
import com.sliu.framework.app.wsh.dao.WjReplayDao;
import com.sliu.framework.app.wsh.model.WjObject;


@Service
public class WjObjectService {

	@Autowired
	private WjObjectDao dao;
	
	@Autowired
	private WjReplayDao rsDao;
	
	/**
	 * 新建问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param obj
	 */
	@Transactional
	public void intsertObjectBean(WjObject obj){
		dao.save(obj);
	}
	
	/**
	 * 修改问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param obj 问卷类
	 */
	@Transactional
	public void updateObjectBean(WjObject obj){
		dao.update(obj);
	}
	
	/**
	 * 删除问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param oid 问卷id
	 */
	@Transactional
	public void delObjectBean(int oid){
		dao.delObj(oid);
		dao.delQue(oid);
		dao.delRep(oid);
		dao.delAns(oid);
		dao.delSel(oid);
	}
	
	/**
	 * 查看问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @return
	 */
	public List<Map<String,Object>> ListObjectBean(String bt){
		return dao.ListObjectBean(bt);
	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月24日
	 * @param bt
	 * @return
	 */
	public List<Map<String,Object>> ListObject(String bt,String openId){
		List<Map<String,Object>> list=dao.ListObjectBean(bt);
		List<Map<String,Object>> obj=new ArrayList<Map<String,Object>>();
		for(int i=0;i<list.size();i++){
			Map<String,Object> map=new HashMap<String, Object>();
			if(rsDao.exit(Integer.parseInt(list.get(i).get("oid")+""), openId, "")){
				map.put("sfcy", "1");
			}else{
				map.put("sfcy", "0");
			}
			map.put("oid", list.get(i).get("oid"));
			map.put("title", list.get(i).get("title"));
			map.put("createtime", list.get(i).get("createtime"));
			map.put("state", list.get(i).get("state"));
			obj.add(map);
		}
		return obj;
	}
	
	/**
	 * 查找发布后的问卷
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findPublishedObjectBeanByID(String id){
		return dao.findPublishedObjectBeanByID(id);
	}
	/*
	public WjObject findPublishedObjectBeanByID(String id){
		return dao.get(id);
	}*/
	/**
	 * 根据编号查找标题和内容
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> findObjectBeanByID(String id){
		return dao.findObjectBeanByID(id);
	}
	
	/**
	 * 根据编号查找详细信息
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getObjectBean(String id){
		return dao.getObjectBean(id);
	}
	
	/**
	 * 查找问卷一共几条数据
	 * @author liujiasen
	 * @date 2015年6月4日
	 * @param oid 问卷id
	 * @return
	 */
	public int getCount(int oid) {
		return dao.getCount(oid);
	}

	/**
	 * 发布
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param request
	 * @return
	 */
	public String objFb(HttpServletRequest request) {
		String str="0";
		String id = request.getParameter("id");
		dao.objFb(id);
		str="1";
		return str;
	}
	
	/**
	 * 撤销
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param request
	 * @return
	 */
	public String objCx(HttpServletRequest request) {
		String str="0";
		String id = request.getParameter("id");
		dao.objCx(id);
		this.deleteHd(id);
		str="1";
		return str;
	}
	
	/**
	 * 删除提交的答案刚
	 * @author liujiansen
	 * @date 2015年8月11日
	 * @param id
	 */
	public void deleteHd(String id){
		dao.delRep(Integer.parseInt(id));
		dao.delAns(Integer.parseInt(id));
	}
	
	/**
	 * 终止评议
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param request
	 * @return
	 */
	public String objZz(HttpServletRequest request) {
		String str="0";
		String id = request.getParameter("id");
		dao.objZz(id);
		str="1";
		return str;
	}

	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月22日
	 * @param oid
	 * @param qseq
	 * @return
	 */
	public List<Map<String, Object>> getWjXz(String oid, String qseq) {
		return dao.getWjXz(oid,qseq);
	}

	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月23日
	 * @param oid
	 * @param qseq
	 * @return
	 */
	public List<Map<String, Object>> getWjXzBt(String oid, String qseq) {
		List<Map<String, Object>> list=dao.getWjXz(oid,qseq);
		List<Map<String, Object>> bt=new ArrayList<Map<String,Object>>();
		String content="";
		String btsj="";
		String bttx="";
		for(int i=0;i<list.size();i++){
			Map<String, Object> map=new HashMap<String, Object>();
			content=list.get(i).get("content")+"";
			btsj="{value:"+list.get(i).get("xzs")+", name:'"+list.get(i).get("content")+"'}";
			bttx=list.get(i).get("xzs")+"";
			map.put("content", content);
			map.put("btsj", btsj);
			map.put("bttx", bttx);
			bt.add(map);
		}
		return bt;
	}
}
