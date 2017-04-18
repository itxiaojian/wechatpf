package com.sliu.framework.app.xzxx.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.xzxx.dao.XxXjxxbDao;
import com.sliu.framework.app.xzxx.model.XxXjxxb;

/**
 * 校长信箱-信件信息表 Service
 * 
 * @author liujiansen
 * @since 2015-09-01
 */

@Service
public class XxXjxxbService extends BaseBO<XxXjxxb> {
	@Autowired
	private XxXjxxbDao dao;

	/**
	 * 校长信箱信件信息
	 * 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param pages
	 * @param code
	 * @param lx
	 * @param yhbh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String, Object>> getXzxxList(HttpServletRequest request) throws UnsupportedEncodingException {
		String Currentpage = request.getParameter("Currentpage");
		if (Currentpage == null) {
			Currentpage = "1";
		}
		String code = request.getParameter("code");
		if(code!=null){
			code = new String(code.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			code="";
	   		}
		String lx = request.getParameter("lx");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> wxyh = dao.getUser(openId);
		List<Map<String, Object>> list = null;
		if (wxyh.size() != 0) {
			list = dao.getXzxxList(Currentpage, code, lx, wxyh.get(0).get("yhid")
					+ "", wxyh.get(0).get("dlm")+"");
		}
		return list;
	}
	
	/**
	 * 获取我的信件总数量
	 * @author zhangyan
	 * @date 2016年12月15日 下午3:35:11
	 * @param 
	 * @return
	 */
	public int getxjAcount(String yhbh,String lx){
		return dao.getxjAcount(yhbh,lx);
	}
	
	
	/**
	 * 获取信件总数量
	 * @author zhangyan
	 * @date 2016年12月16日 上午8:33:37
	 * @param 
	 * @return
	 */
	public int getAcount(){
		return dao.getAcount();
	}
	
	
	/**
	 * 手机端校长信箱总页数
	 * @author zhangyan
	 * @date 2016年12月15日 下午4:35:34
	 * @param 
	 * @return
	 */
	public int getPagecount(HttpServletRequest request) throws UnsupportedEncodingException {
		String code = request.getParameter("code");
		if(code!=null){
			code = new String(code.getBytes("ISO-8859-1"), "UTF-8"); 
   		}else{
   			code="";
   		}
		String lx = request.getParameter("lx");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> wxyh = dao.getUser(openId);
		String yhbh ="";
		String dlm ="";
		if (wxyh.size() != 0) {
			 yhbh = wxyh.get(0).get("yhid")+"";
		     dlm = wxyh.get(0).get("dlm")+"";
		}
		return dao.getPagecount(code,lx,yhbh,dlm);
	}

	/**
	 * 根据openId获取绑定用户的信息
	 * 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getUser(String openId) {
		return dao.getUser(openId);
	}

	/**
	 * 在微服务主页展示的信件
	 * 
	 * @author duanpeijun
	 * @date 2015年9月1日
	 * @return
	 */
	public List<Map<String, Object>> getXjxxb(String openId) {
		return dao.getXjxxb(openId);
	}

	/**
	 * 后台:校长信箱
	 * 
	 * @author duanpeijun
	 * @date 2015年9月1日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getxjxxbList(Integer start,
			Integer limit, String code) {
		SysYh yh =AppUtil.getCurrentUser();
		String yhxm =yh.getUsername();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		return dao.getXjxxbList(start, limit, code,yhxm,jsList);
	}

	/**
	 * 后台： 校长信箱发布邮件
	 * 
	 * @author duanpeijun
	 * @date 2015年9月1日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveXxXjxxb(XxXjxxb entity,HttpServletRequest request) {
		SysYh yh = AppUtil.getCurrentUser();
		entity.setClzt("1");
		entity.setFjrxm(yh.getXm());
		entity.setFjrbh(yh.getUsername());
		entity.setXxsj(Calendar.getInstance().getTime());
		entity.setIpdz(AppUtil.getIpAddr(request));
		dao.save(entity);
		return "1";
	}

	/**
	 * 后台： 校长信箱修改邮件
	 * 
	 * @author:zhangyi
	 * @date 2015年9月1日
	 * @param id
	 * @return
	 */
	@Transactional
	public XxXjxxb getXxjxxbById(Long id) {
		XxXjxxb entity = dao.get(id);
		if (entity != null) {
			return entity;
		}
		return null;
	}
	
	/**
	 * 后台:信件审核列表
	 * 
	 * @author duanpeijun
	 * @date 2015年9月2日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getXjshList(Integer start,
			Integer limit,String code) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		/* Map<String, Object> temp = list.get(0); Object value =
		 temp.get("jsmc"); String jsmc = value.toString();*/
		return dao.getXjshList(start, limit,code, jsList);
	}
	
	/**
	 * 后台： 校长信箱审核通过邮件
	 * 
	 * @author duanpeijun
	 * @date 2015年9月6日
	 * @param entity
	 * @return
	 */
	@Transactional
	public XxXjxxb getXxjxxbByTg(Long id) {
		XxXjxxb entity = dao.get(id);
		if (entity != null) {
			return entity;
		}
		return null;
	}
	
	/**
	 * 后台:校长信箱已审核列表
	 * 
	 * @author duanpeijun
	 * @date 2015年9月1日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getxjyshList(Integer start,
			Integer limit, String code) {
		SysYh yh =AppUtil.getCurrentUser();
		String yhxm =yh.getUsername();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		return dao.getXjyshList(start, limit, code, yhxm,jsList);
	}
	
	/**
	 * 后台:校长信箱受理列表
	 * 
	 * @author duanpeijun
	 * @date 2015年9月6日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getxjslList(Integer start,
			Integer limit, String code) {
		SysYh yh =AppUtil.getCurrentUser();
		String yhbm =yh.getBmbh();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		return dao.getXjslList(start, limit, code,yhbm,jsList);
	}
	
	/**
	 * 后台:校长信箱已受理列表
	 * 
	 * @author duanpeijun
	 * @date 2015年9月6日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getxjyslList(Integer start,
			Integer limit, String code) {
		SysYh yh =AppUtil.getCurrentUser();
		String yhbm =yh.getBmbh();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		return dao.getXjyslList(start, limit, code,yhbm,jsList);
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
		return dao.getDicByLx(zdzl);
	}

	/**
	 * 查找部门
	 * 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @return
	 */
	public List<Map<String, Object>> getZzjg() {
		return dao.getZzjg(null);
	}

	/**
	 * 新建信件
	 * 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 * @throws UnsupportedEncodingException 
	 */
	@Transactional
	public String saveXj(HttpServletRequest request) 
			throws SerialException, SQLException, UnsupportedEncodingException {
		String str = "";
		String xjbt = request.getParameter("xjbt");
		String slbmbh = request.getParameter("slbmbh");
		String txdz = request.getParameter("txdz");
		String lxdh = request.getParameter("lxdh");
		String xjnr1 = request.getParameter("xjnr");
		// Blob xjnr = new SerialBlob(xjnr1.getBytes("utf-8"));  
		String bz = request.getParameter("bz");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> jg = dao.getZzjg(slbmbh);
		XxXjxxb xx = new XxXjxxb();
		xx.setBz(bz);
		xx.setClzt("1");
		List<Map<String, Object>> user = dao.getUser(openId);
		if (user.size() != 0) {
			xx.setFjrbh(user.get(0).get("dlm") + "");
			xx.setFjrxm(user.get(0).get("xm") + "");
		}
		xx.setLxdh(lxdh);
		xx.setXxsj(new Date());
		xx.setXjnr(xjnr1);
		xx.setXjbt(xjbt);
		xx.setTxdz(txdz);
		xx.setSlbmbh(slbmbh);
		if (jg.size() != 0) {
			xx.setSlbmmc(jg.get(0).get("bmmc") + "");
		}
		//新建信箱获得ip地址
		xx.setIpdz(AppUtil.getIpAddr(request));
		List<Map<String, Object>>  listDlm = this.getUser(openId);
		String  dlm= listDlm.get(0).get("dlm").toString();
		List<Map<String,Object>>  list1= dao.getSfclwc(dlm);
		List<Map<String,Object>>  list= dao.getSfpj(dlm);
		if(list.size()==0 && list1.size()==0){
		    str="1";
		dao.save(xx);
		}else if(list1.size()!=0){
			str="0";
		}else if(list.size()!=0 && list1.size() ==0){
			str="2";
		}
		return str;
	}

	/**
	 * 修改信件
	 * 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 * @throws UnsupportedEncodingException 
	 */
	@Transactional
	public String updateXj(HttpServletRequest request) 
			throws SerialException, SQLException, UnsupportedEncodingException {
		String str = "";
		String xjbt = request.getParameter("xjbt");
		String slbmbh = request.getParameter("slbmbh");
		String txdz = request.getParameter("txdz");
		String lxdh = request.getParameter("lxdh");
		String xjnr1 = request.getParameter("xjnr");
		// Blob xjnr = new SerialBlob(xjnr1.getBytes("utf-8"));  
		String bz = request.getParameter("bz");
		String id = request.getParameter("xjid");
		XxXjxxb xx = dao.get(Long.parseLong(id));
		xx.setBz(bz);
		xx.setClzt("1");
		xx.setLxdh(lxdh);
		xx.setXxsj(new Date());
		xx.setXjnr(xjnr1);
		xx.setXjbt(xjbt);
		xx.setTxdz(txdz);
		xx.setSlbmbh(slbmbh);
		List<Map<String, Object>> jg = dao.getZzjg(slbmbh);
		if (jg.size() != 0) {
			xx.setSlbmmc(jg.get(0).get("bmmc") + "");
		}
		dao.update(xx);
		str = "1";
		return str;
	}

	/**
	 * 删除信件
	 * 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @return
	 */
	@Transactional
	public String deleteXj(HttpServletRequest request) {
		String str = "";
		String id = request.getParameter("id");
		dao.delete(Long.parseLong(id));
		str = "1";
		return str;
	}

	/**
	 * 信件评价信息保存
	 * 
	 * @author liujiansen
	 * @date 2015年9月2日
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@Transactional
	public String savePj(HttpServletRequest request) throws UnsupportedEncodingException {
		String str = "";
		String id = request.getParameter("id");
		String pj = request.getParameter("pj");
		/*if(pj!=null){
   			pj= new String(pj.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			pj="";
	   		}*/
		String myd = request.getParameter("myd");
		XxXjxxb xx = dao.get(Long.parseLong(id));
		xx.setPj(pj);
		xx.setMyd(Integer.parseInt(myd));
		xx.setClzt("6");
		dao.update(xx);
		str = "1";
		return str;
	}

	/**
	 * 获得角色
	 * 
	 * @author oufeng
	 * @date 2015年9月2日
	 * @param request
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getJs(String yhbh) {
		return dao.getJs(yhbh);
	}
	
	/**
	 * 信件查看列表
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getXjckList(String bm,String bmbh,String clzt,String bmmc,String yhbh,String xm){
		//SysYh yh =AppUtil.getCurrentUser();
		//String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		return dao.getXjckList(bm, bmmc, clzt, jsList, xm);
	}
	
	/**
	 * 总页数
	 * @author oufeng
	 * @throws UnsupportedEncodingException 
	 * @date 2015年8月10日
	 */
	/*public int getCount(HttpServletRequest request) throws UnsupportedEncodingException{
		String bm = request.getParameter("bm");
		String bmmc = request.getParameter("bmmc");
		String clzt = request.getParameter("clzt");
		String bmbh = request.getParameter("bmbh");
		String yhxm = request.getParameter("yhxm");
		if(bm!=null){
	   		bm = new String(bm.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bm="";
	   		}
		if(bmmc!=null){
			bmmc = new String(bmmc.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bmmc="";
	   		}
		if(clzt!=null){
			clzt = new String(clzt.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			clzt="";
	   		}
		return dao.getCount(bmmc, bmbh, clzt, yhxm, bmmc);
	}*/
	
	/**
	 * 信件查看详情
	 * @author oufeng
	 * @date 2015年8月10日
	 */
	@Transactional
	public List<Map<String,Object>>getXjckDetail(String bxbh,String xm){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		return dao.getXjckDetail(bxbh, jsList,xm);
	}
	
	/**
	 *  从数据字典中获取处理状态到信件查看页面
	 * @author duanpeijun
	 * @date 2015年9月11日
	 * @return
	 */
	public List<Map<String, Object>> getClztList(){
		return dao.getClztList();
	}
	
	/**
	 * 根据字典种类找到满意度
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月20日 下午3:57:48 
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDicByMyd(String zdzl) {
		return dao.getDicByMyd(zdzl);
	}
	
	/**
	 * 查询有无未完成的处理状态
	 * @author duanpeijun
	 * @date 2015年9月18日
	 * @return
	 */
	public Integer getClztsize(){
		SysYh yh =AppUtil.getCurrentUser();
		String yhxm =yh.getUsername();
		int str=0;
		List<Map<String,Object>>  list1= dao.getSfclwc(yhxm);
		List<Map<String,Object>>  list= dao.getSfpj(yhxm);
		if(list.size()==0 && list1.size()==0){
		    str=0;
		}else if(list1.size()!=0){
			str=1;
		}else if(list.size()!=0 && list1.size() ==0){
			str=2;
		}
		return str;
	}
	
	/**
	 * 获得评价信息
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param request
	 * @return
	 */
	@Transactional
	public String getPj(HttpServletRequest request) {
		String openId=request.getParameter("openId");
		//String yhbh=request.getParameter("yhbh");
		List<Map<String,Object>> user ;
		String dlm="";
		if(openId !=null&&!"".equals(openId)&&openId!=""){
		user = dao.getUser(openId);
			 dlm=(String) user.get(0).get("dlm");
		}
		String str="";
		List<Map<String,Object>>  list= dao.getClztsizeList(dlm);
		if(list.size()==0){
		str="1";
		}else {
			str="0";
		}
		return str;
	}
	
	/**
	 * 查询校长信箱的详情
	 * @author duanpeijun
	 * @date 2015年9月18日
	 * @return
	 */
	public List<Map<String, Object>> getXzxxDetail(HttpServletRequest request){
  		String id = request.getParameter("id");
  		String openId = request.getParameter("openId");
		List<Map<String,Object>> user ;
		String dlm="";
		if(openId !=null&&!"".equals(openId)&&openId!=""){
		user = dao.getUser(openId);
		dlm=(String) user.get(0).get("dlm");
		}
		return dao.getXzxxDetail(id,dlm);
	}
	
	/**
	 * 总页数
	 * @author oufeng
	 * @throws UnsupportedEncodingException 
	 * @date 2015年8月10日
	 */
	public int getCountw(HttpServletRequest request) throws UnsupportedEncodingException {
		String time = request.getParameter("time");
		String clzt = request.getParameter("clzt");
		String bmbh = request.getParameter("bmbh");
		String pagesw = request.getParameter("pagesw");
		if(bmbh!=null){
			bmbh = new String(bmbh.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bmbh="";
	   		}
		return dao.getCountw(time, bmbh, clzt);
	}
	
	/**
	 * 总页数
	 * @author oufeng
	 * @throws UnsupportedEncodingException 
	 * @date 2015年8月10日
	 */
	public int getCounty(HttpServletRequest request) throws UnsupportedEncodingException{
		String time = request.getParameter("time");
		String clzt = request.getParameter("clzt");
		String bmbh = request.getParameter("bmbh");
		String pagesy = request.getParameter("pagesy");
		if(bmbh!=null){
			bmbh = new String(bmbh.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bmbh="";
	   		}
		return dao.getCounty(time, bmbh, clzt);
	}
	
	/**
	 * 
	 * @author:zhangyi 
	 * @version 创建时间：2015年9月29日 下午4:53:24 
	 * @param time
	 * @param bmbh
	 * @param clzt
	 * @param pagesw
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getXjmhckListQt(String time,String bmbh,String nr,
			String pagesw){
		SysYh yh =AppUtil.getCurrentUser();
//		String yhbh = yh.getYhbh();
//		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
//		for (int i = 0; i < list.size(); i++) {
//			Map<String,Object> map = (Map<String,Object>) list.get(i);
//			Iterator<String> iterator = map.keySet().iterator();
//			while (iterator.hasNext()) {
//				String key = (String) iterator.next();
//				Object object = map.get(key);
//			    jsList.add(object);
//			}
//		}
		return dao.getXjmhckList(time,bmbh, nr, jsList,pagesw);
	}
	
	/**
	 * 信件已回复查看列表
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getYhfList(String time,String bmbh,String nr,String pagesy){
		return dao.getYhfList(time, bmbh, nr,pagesy);
	}
	
	@Transactional
	public List<Map<String,Object>>getXjmhckDetailWeb(String bxbh){
		return dao.getXjmhckDetailWeb(bxbh);
	}
	
	/**
	 * 信件未回复查看列表
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getXjmhckList(String time,String bmbh,String clzt,
			String pagesw){
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>> list = this.getJs(yhbh);
		List<Object> jsList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String,Object>) list.get(i);
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object object = map.get(key);
			    jsList.add(object);
			}
		}
		return dao.getXjmhckList(time,bmbh, clzt, jsList,pagesw);
	}
	
	/**
	 * 我要写信
	 * 
	 * @author liujiansen
	 * @date 2015年9月1日
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 * @throws UnsupportedEncodingException 
	 */
	@Transactional
	public String saveWyxx(HttpServletRequest request) 
			throws SerialException, SQLException, UnsupportedEncodingException {
		String str = "";
		String xjbt = request.getParameter("xjbt");
		String slbmbh = request.getParameter("slbmbh");
		String txdz = request.getParameter("txdz");
		String lxdh = request.getParameter("lxdh");
		String xjnr1 = request.getParameter("xjnr");
		 //Blob xjnr = new SerialBlob(xjnr1.getBytes("utf-8"));  
		String bz = request.getParameter("bz");
		String openId = request.getParameter("openId");
		List<Map<String, Object>> jg = dao.getZzjg(slbmbh);
		XxXjxxb xx = new XxXjxxb();
		xx.setBz(bz);
		xx.setClzt("1");
		List<Map<String, Object>> user = dao.getUser(openId);
		if (user.size() != 0) {
			xx.setFjrbh(user.get(0).get("dlm") + "");
			xx.setFjrxm(user.get(0).get("xm") + "");
		}
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		List<Map<String,Object>> yhdm = this.getDlm(yhbh);
		String yhxm =yh.getXm();
		xx.setFjrbh(yhdm.get(0).get("dlm").toString());
		xx.setFjrxm(yhxm);
		xx.setLxdh(lxdh);
		xx.setXxsj(new Date());
		xx.setXjnr(xjnr1);
		xx.setXjbt(xjbt);
		xx.setTxdz(txdz);
		xx.setSlbmbh(slbmbh);
		if (jg.size() != 0) {
			xx.setSlbmmc(jg.get(0).get("bmmc") + "");
		}
		//新建信箱获得ip地址
		xx.setIpdz(AppUtil.getIpAddr(request));
		
		SysYh yh1 =AppUtil.getCurrentUser();
		String yhbh1 = yh1.getYhbh();
		List<Map<String, Object>>  listDlm = this.getDlm(yhbh1);
		String dlm = listDlm.get(0).get("dlm").toString();
		List<Map<String,Object>>  list1= dao.getSfclwc(dlm);
		List<Map<String,Object>>  list= dao.getSfpj(dlm);
		if(list.size()==0 && list1.size()==0){
		str="1";
		dao.save(xx);
		}else if(list1.size()!=0){
			str="0";
		}else if(list.size()!=0 && list1.size() ==0){
			str="2";
		}
		return str;
	}
	
	/**
	 * 获得记录总数
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getZs(){
		return dao.getZs();
	}
	
	/**
	 * 获得未记录总数
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getWzs(){
		return dao.getWzs();
	}
	
	/**
	 * 获得登陆名
	 * @author duanpeijun
	 * @date 2015年9月9日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDlm(String yhbh){
		return dao.getDlm(yhbh);
	}
	
	/**
	 * 获得部门编号
	 * @author oufeng
	 * @date 2016年2月1日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getBmbh(String bmmc){
		return dao.getBmbh(bmmc);
	}
	
	/**
	 * 是否有受理部门编号
	 * @author oufeng
	 * @date 2016年2月1日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> hasSlbmbh(String id){
		return dao.hasSlbmbh(id);
	}
	
	/**
	 * 获取受理部门编号
	 * @author oufeng
	 * @date 2016年2月1日
	 * @param pages
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getSlbmbh(String slbmmc){
		return dao.getSlbmbh(slbmmc);
	}
	
	/**
	 * 获得评价信息
	 * @author oufeng
	 * @date 2015年8月11日
	 * @param request
	 * @return
	 */
	@Transactional
	public String getsfPj(HttpServletRequest request) {
		//String openId=request.getParameter("openId");
		//String yhbh=request.getParameter("yhbh");
		SysYh yh =AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		List<Map<String, Object>>  listDlm = this.getDlm(yhbh);
		String dlm = listDlm.get(0).get("dlm").toString();
		String str="";
		List<Map<String,Object>>  list= dao.getSfpj(dlm);
		if(list.size()==0){
		str="1";
		}else {
			str="0";
		}
		return str;
	}
	
	/**
	 * 获取受理部门编号
	 * @author oufeng
	 * @date 2016年2月1日
	 * @param pages
	 * @return
	 */
	@Transactional
	public void updateSlbmbh(String bmbh,String slbmmc,String id){
		 dao.updateSlbmbh(bmbh,slbmmc,id);
	}
}
