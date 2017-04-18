package com.sliu.framework.app.wxzdycd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxZdycd;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxzdycd.dao.WxZdycdDao;

/**
 * 微信自定义菜单service
 * @author zhangyi
 * 2015-06-03
 */
@Service
public class WxZdycdService {

	@Autowired
	private WxZdycdDao zdycdDao;
	
	@Transactional
	public List<Map<String, Object>> getWxZdycdList() {
		return zdycdDao.getWxZdycdList();
	}
	
	/**
	 * 分页得到微信一级菜单
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional
	public Pagination<Object> getFirstMenu(Integer start,Integer limit) {
		return zdycdDao.getFirstMenu(start, limit);
	}
	
	/**
	 * 获取子集菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 上午11:11:20 
	 * @param start
	 * @param limit
	 * @param sjid 上级菜单Id
	 * @return
	 */
	@Transactional
	public Pagination<Object> getSonMenu(Integer start,Integer limit,Integer sjid) {
		return zdycdDao.getSonMenu(start, limit,sjid);
	}
	

	/**
	 * 根据字典种类找出子字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:36:46 
	 * @param zdzl 字典种类
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		return zdycdDao.getDicByLx(zdzl);
	}

	/**
	 * 保存
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午9:28:47 
	 * @param wxZdycd
	 * @return
	 */
	@Transactional
	public String save(WxZdycd wxZdycd) {
		wxZdycd.setZt(1);
		Long re = zdycdDao.save(wxZdycd);
		return "1";
	}
	
	/**
	 * 修改
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String update(WxZdycd entity) {
		WxZdycd wxZdycd = zdycdDao.get(entity.getId());
		wxZdycd.setBz(entity.getBz());
		wxZdycd.setDqjb(entity.getDqjb());
		wxZdycd.setLx(entity.getLx());
		wxZdycd.setLj(entity.getLj());
		wxZdycd.setPx(entity.getPx());
		wxZdycd.setZt(entity.getZt());
		wxZdycd.setMc(entity.getMc());
		wxZdycd.setSjid(entity.getSjid());
		zdycdDao.update(wxZdycd);
		return "1";
	}

	/**
	 * 根据上级菜单找出下级菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午10:12:27 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getSonMenu(Long id) {
		return zdycdDao.getSonMenuList(id);
	}

	/**
	 * 删除
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 上午10:17:03 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		zdycdDao.delete(id);
	}

	/**
	 * 同步微信菜单
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月4日 下午2:45:37
	 */
	@Transactional	
	public void refreshWxZdycd() {
		
		 Map<String,Object> map = new HashMap<String,Object>();//整体的map
		 List<Map<String, Object>> listMapButton = new ArrayList<Map<String, Object>>();//一级菜单map
		 List<Map<String, Object>> listMapParent = new ArrayList<Map<String, Object>>();//查询数据库中一级菜单信息
		 listMapParent = zdycdDao.getListMapParent();
		 for(Map<String, Object> parent:listMapParent){
			 Map<String, Object> mapparent = new HashMap<String,Object>();
			 mapparent.put("name", parent.get("name"));
			 if("parent".equalsIgnoreCase(parent.get("type").toString())){
				 List<Map<String, Object>> listMapSon = new ArrayList<Map<String, Object>>();//查出数据库保存的二级菜单
				 List<Map<String, Object>> listSubButton = new ArrayList<Map<String, Object>>();//子集菜单集合
				 listMapSon = zdycdDao.getListMapSon(Integer.parseInt(parent.get("id").toString()));
				 if(listMapSon!=null){
					 if(listMapSon.size() > 0 ){
						 for(Map<String, Object> son:listMapSon){
							 Map<String,Object> mapSubButton = new HashMap<String,Object>();//整体的map
							 if("click".equalsIgnoreCase(son.get("type").toString())){
								 mapSubButton.put("type", "click");
								 mapSubButton.put("name", son.get("name"));
								 mapSubButton.put("key", son.get("key"));
							 }else if("view".equalsIgnoreCase(son.get("type").toString())){
								 mapSubButton.put("type", "view");
								 mapSubButton.put("name", son.get("name"));
								 mapSubButton.put("url", son.get("url"));
							 }
							 listSubButton.add(mapSubButton);
						 }
					 }
				 }
				 mapparent.put("sub_button", listSubButton);
			 }else if("view".equalsIgnoreCase(parent.get("type").toString())){
				 mapparent.put("type", "view");
				 mapparent.put("url", parent.get("url"));
			 }
			 listMapButton.add(mapparent);
		 }
		 map.put("button", listMapButton);
		 
//		 System.err.println("---------------------->"+map);
//		 String jsonMenu = JSON.toJSONString(map);
//		 System.err.println("---------------------->"+jsonMenu);
		 
		 WeixinUtils.refreshWxZdycd(map);
	}
	
//	/**
//	 * 与微信平台同步微信分组信息
//	 * @return
//	 */
//	@Transactional
//	public void refreshWxZdycd() {
//		List<WxZdycd> list = WeixinUtils.getAllGroupInfo();
//		for(WxZdycd groupInfo:list) {
//			zdycdDao.saveGroupInfo(groupInfo);
//		}
//	}
}
