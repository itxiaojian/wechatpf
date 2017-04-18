package com.sliu.framework.app.bx.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.bx.dao.BxBxspyjbDao;
import com.sliu.framework.app.bx.dao.BxBxsqDao;
import com.sliu.framework.app.bx.model.BxBxspyjb;
import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

/**
 * 报修审批意见
 * @author liujiansen
 * @date 2015年8月6日
 */
@Service
public class BxBxspyjbService extends BaseBO<BxBxsq>{

	protected Logger logger = LoggerFactory.getServiceLog(BxBxsq.class);
	
	@Autowired
	private BxBxsqDao dao;
	@Autowired
	private BxBxspyjbDao yjDao;
	
	/**
	 * 申请数据保存
	 * @author liujiansen
	 * @date 2015年8月7日
	 * @param request
	 * @return
	 */
	@Transactional
	public String save(HttpServletRequest request) {
		String str="";
		String lxhm=request.getParameter("lxhm");
		//String bz=request.getParameter("bz");
		String bz3=request.getParameter("bz3");
		String bz1=request.getParameter("bz1");
		String bz2=request.getParameter("bz2");
		String yysj=request.getParameter("yysj");
		String nr=request.getParameter("nr");
		String wxdl=request.getParameter("wxdl");
		String wxxl=request.getParameter("wxxl");
		String fw=request.getParameter("fw");
		String ly=request.getParameter("ly");
		String lh=request.getParameter("lh");
		String openId=request.getParameter("openId");
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat _s=new SimpleDateFormat("yyyy-MM-dd");
		Date time=new Date();
		Date _yysj=new Date();
	/*	try {
			time=s.parse(yysj+" "+(sim.format(new Date())).substring(0, (sim.format(new Date())).length()));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
	    try {
			 _yysj=_s.parse(yysj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		BxBxsq sq=new BxBxsq();
		String bxbh="";
		String xgh="";
		String sqrxm="";
		String yhbh="";
		List<Map<String,Object>> user=dao.getUser(openId);
		if(user.size()!=0 && !"".equals(openId) && openId !=null){
			bxbh=sim.format(new Date())+(user.get(0).get("dlm")+"");
			xgh=user.get(0).get("dlm")+"";
			sqrxm=user.get(0).get("xm")+"";
			sq.setBxbh(bxbh);
			sq.setSqrxm(sqrxm);
			sq.setXgh(xgh);
		}else{
			SysYh yh=AppUtil.getCurrentUser();
			bxbh=sim.format(new Date())+yh.getUsername().substring(
					yh.getUsername().length()-4, yh.getUsername().length());
			xgh=yh.getUsername();
			yhbh=yh.getYhbh();
			sqrxm=yh.getXm();
			sq.setBxbh(bxbh);
			sq.setSqrxm(sqrxm);
			sq.setXgh(xgh);
		}
		//sq.setBz(bz);
		if(!"".equals(bz1)&&!"".equals(bz2)&&"".equals(bz3)){
			sq.setBz(bz1+";"+bz2);
		}else if(!"".equals(bz1)&&!"".equals(bz2)&&!"".equals(bz3)){
			sq.setBz(bz1+";"+bz2+";"+bz3);
		}else if(!"".equals(bz1)&&"".equals(bz2)&&"".equals(bz3)){
			sq.setBz(bz1);
		}else if("".equals(bz1)&&!"".equals(bz2)&&!"".equals(bz3)){
			sq.setBz(bz2+";"+bz3);
		}else{sq.setBz(bz1);}
		sq.setDz(ly+"-"+lh);
		sq.setFw(fw);
		sq.setLh(lh);
		sq.setLxhm(lxhm);
		sq.setLy(ly);
		sq.setNr(nr);
		sq.setYysj(_yysj);
		sq.setZt("1");
		sq.setWxdl(wxdl.split("_")[1]);
		String[] arr={};
		if(wxxl==null||"".equals(wxxl)||"-1".equals(wxxl)){
			arr=wxdl.split("_");
		}else{
			arr=wxxl.split("_");
		}
		sq.setSbztbh(arr[0]);
		sq.setSbztmc(arr[1]);
		int i=dao.checkSave(sq);
		if(i!=0){
			str="2";
			return str;
		}
		List<Map<String,Object>>  list= dao.getPj(openId,yhbh);
		List<Map<String,Object>>  list1= dao.getSfclwc(openId,yhbh);
		if(list.size()==0 && list1.size()==0){
		    str="1";
		    dao.save(sq);
		}else if(list1.size()!=0){
			str="0";
		}else if(list.size()!=0 && list1.size() ==0){
			str="3";
		}
		return str;
	}
	
	/**
	 * 申请驳回修改
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param request
	 * @return
	 */
	@Transactional
	public String update(HttpServletRequest request) {
		String str="";
		String lxhm=request.getParameter("lxhm");
		String id=request.getParameter("id");
		//String bz=request.getParameter("bz");
		String bz3=request.getParameter("bz3");
		String bz1=request.getParameter("bz1");
		String bz2=request.getParameter("bz2");
		String yysj=request.getParameter("yysj");
		String nr=request.getParameter("nr");
		String wxdl=request.getParameter("wxdl");
		String wxxl=request.getParameter("wxxl");
		String fw=request.getParameter("fw");
		String ly=request.getParameter("ly");
		String lh=request.getParameter("lh");
		String openId=request.getParameter("openId");
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat _s=new SimpleDateFormat("yyyy-MM-dd");
		Date time=new Date();
		Date _yysj=new Date();
		/*try {
			time=s.parse(yysj+" "+(sim.format(new Date())).substring(0, (sim.format(new Date())).length()));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		  try {
				 _yysj=_s.parse(yysj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		BxBxsq sq=new BxBxsq();
		BxBxspyjb yj=new BxBxspyjb();
		String bxbh="";
		String xgh="";
		String sqrxm="";
		List<Map<String,Object>> user=dao.getUser(openId);
		if(user.size()!=0 && openId!=null && !"".equals(openId)){
			bxbh=sim.format(new Date())+(user.get(0).get("dlm")+"");
			xgh=user.get(0).get("dlm")+"";
			sqrxm=user.get(0).get("xm")+"";
			sq.setBxbh(bxbh);
			sq.setSqrxm(sqrxm);
			sq.setXgh(xgh);
			yj.setSprbh(user.get(0).get("yhid")+"");
			yj.setSprxm(user.get(0).get("xm")+"");
		}else{
			SysYh yh=AppUtil.getCurrentUser();
			bxbh=sim.format(new Date())+yh.getUsername().substring(
					yh.getUsername().length()-4, yh.getUsername().length());
			xgh=yh.getUsername();
			sqrxm=yh.getXm();
			sq.setBxbh(bxbh);
			sq.setSqrxm(sqrxm);
			sq.setXgh(xgh);
			yj.setSprbh(yh.getYhbh());
			yj.setSprxm(yh.getXm());
		}
		//sq.setBz(bz);
		if(!"".equals(bz1)&&!"".equals(bz2)&&"".equals(bz3)){
			sq.setBz(bz1+";"+bz2);
		}else if(!"".equals(bz1)&&!"".equals(bz2)&&!"".equals(bz3)){
			sq.setBz(bz1+";"+bz2+";"+bz3);
		}else if(!"".equals(bz1)&&"".equals(bz2)&&"".equals(bz3)){
			sq.setBz(bz1);
		}else if("".equals(bz1)&&!"".equals(bz2)&&!"".equals(bz3)){
			sq.setBz(bz2+";"+bz3);
		}else{sq.setBz(bz1);}
		sq.setDz(ly+"-"+lh);
		sq.setFw(fw);
		sq.setLh(lh);
		sq.setLxhm(lxhm);
		sq.setLy(ly);
		sq.setNr(nr);
		//sq.setYysj(time);
		sq.setYysj(_yysj);
		sq.setWxdl(wxdl.split("_")[1]);
		sq.setZt("1");
		String[] arr={};
		if(wxxl==null||"".equals(wxxl)){
			arr=wxdl.split("_");
		}else{
			arr=wxxl.split("_");
		}
		sq.setSbztbh(arr[0]);
		sq.setSbztmc(arr[1]);
		sq.setId(Long.parseLong(id));
		//更新申请表
		dao.update(sq);
		//保存意见记录
		yj.setBxid(Long.parseLong(id));
		yj.setBz("");
		yj.setSftg("1");
		yj.setSpsj(new Date());
		yj.setSpyj("修改了申请表单");
		yj.setZt("1");
		yjDao.save(yj);
		str="1";
		return str;
	}

	/**
	 * 获取申请信息
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getSq(String yhbh) {
		return yjDao.getSq(yhbh);
	}

	/**
	 * 获取审批信息
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @return
	 */
	public List<Map<String, Object>> getSpxx(String yhbh) {
		return yjDao.getSpxx(yhbh);
	}
	
	/**
	 * 根据Id获取申请信息
	 * @author liujiansen
	 * @date 2015年8月10日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getSqxx(String id) {
		return yjDao.getSqxx(id);
	}
	
	/**
	 * 获取维修的大类
	 * @author liujiansen
	 * @date 2015年8月6日
	 * @return
	 */
	public List<Map<String,Object>> getWxDl(){
		return dao.getWxDl();
	}
	
	/**
	 * 获取维修楼宇
	 * @author liujiansen
	 * @date 2015年8月6日
	 * @return
	 */
	public List<Map<String,Object>> getLy(){
		return dao.getLy();
	}
	
	/**
	 * 报修评价
	 * @author liujiansen
	 * @date 2015年8月11日
	 * @param request
	 * @return
	 */
	@Transactional
	public String bxFwpj(HttpServletRequest request) {
		String str="";
		String id=request.getParameter("id");
		String pj=request.getParameter("pj");
		//满意度-oufeng
		String myd=request.getParameter("myd");
		BxBxsq sq=dao.get(Long.parseLong(id));
		//sq.setPj(pj);
		List<Map<String,Object>> list=this.getPjById(id);
		String _pj="";
		if(list.size()!=0){
			_pj=list.get(0).get("pj")+"";
		}
		//sq.setPj(pj);
		if(!"".equals(_pj) && _pj!=null && !"null".equals(_pj)){
			  sq.setPj(_pj+";"+pj);
			}else{sq.setPj(pj);}
		if("1".equals(myd) || "2".equals(myd)){
			sq.setZt("8");
		}else{
			sq.setZt("7");
		}
		sq.setMyd(myd);
		dao.update(sq);
		str="1";
		return str;
	}
	
	/**
	 * 获取拼接的评价的信息
	 * @author oufeng
	 * @date 2016年3月25日
	 * @return
	 */
	public List<Map<String, Object>> getPjById(String id) {
		return dao.getPjById(id);
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
		String yhbh=request.getParameter("yhbh");
		List<Map<String,Object>> user ;
		String xgh="";
		if(openId !=null&&!"".equals(openId)&&openId!=""){
		user = dao.getUser(openId);
			 xgh=(String) user.get(0).get("dlm");
		}
		if("".equals(yhbh) || yhbh ==null){
			SysYh yh = AppUtil.getCurrentUser();
			yhbh = yh.getYhbh();
		}
		String str="";
		List<Map<String,Object>>  list= dao.getPj(xgh,yhbh);
		List<Map<String,Object>>  list1= dao.getSfclwc(xgh,yhbh);
		if(list.size()==0 && list1.size()==0){
		    str="1";
		}else if(list1.size()!=0){
			str="0";
		}else if(list.size()!=0 && list1.size() ==0){
			str="2";
		}
		return str;
	}
}
