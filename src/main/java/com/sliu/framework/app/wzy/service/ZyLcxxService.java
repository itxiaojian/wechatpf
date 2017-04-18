package com.sliu.framework.app.wzy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.bx.model.BxBxspyjb;
import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wzy.dao.ZyLcxxDao;
import com.sliu.framework.app.wzy.model.ZyLcxx;


/**
 * 主页--流程信息
 * @author duanpeijun
 * @version 创建时间：2015年6月3日  下午3:58:03
 */
@Service
public class ZyLcxxService extends BaseBO<ZyLcxxDao>{
	
	@Autowired
	private ZyLcxxDao zyLcxxDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:58:08
	 * @param start
	 * @param limit
	 * @param lczl    流程种类
	 * @param lcmc  流程名称
	 * @return
	 */
	public Pagination<Map<String,Object>> getLcxxList(Integer start,
			    Integer limit,String lczl,String lcmc){
		return zyLcxxDao.getLcxxList(start, limit, lczl, lcmc);
	}
	
	/**
	 * 流程类型查询
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:58:28
	 * @param zdmc   字段名称
	 * @param zdz      字段值
	 * @return
	 */
	public List<Map<String, Object>> getTjcx(String zdmc,  Integer zdz){
		
		return zyLcxxDao.getTjcx(zdmc, zdz);
	}
	
	/**
	 *  保存
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:58:44
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveLCXX(ZyLcxx entity){
		SysYh yh = AppUtil.getCurrentUser();
		entity.setBmbh(yh.getBmbh());
		entity.setFqr(yh.getYhbh());
		entity.setFqrxm(yh.getXm());
		entity.setLczt("1");
		entity.setKssj(Calendar.getInstance().getTime());
		zyLcxxDao.save(entity);
		return "1";
	}
	
	
	/**
	 * 查看流程信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:58:50
	 * @param id  主键ID
	 * @return
	 */
	@Transactional
	public ZyLcxx getLcxxById(Long id) {
		ZyLcxx esse = zyLcxxDao.get(id);
		if(esse!=null){
			return esse;
		}
		return null;
	}
	
	/**
	 * 获取审核人
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @return
	 */
	public List<Map<String, Object>> getShr(String yhbh){
		return zyLcxxDao.getShr(yhbh);
	}
	
	/**
	 * 获取审核人
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @return
	 */
	public List<Map<String, Object>> getShr1(String yhbh){
		return zyLcxxDao.getShr1(yhbh);
	}
	
	/**
	 * 驳回更新
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @return
	 */
	public List<Map<String, Object>> getUpdatexx(String id){
		return zyLcxxDao.getUpdatexx(id);
	}
	
	/**
	 * 学生请假的 保存
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	  * @param request
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	public String saveQjxx(HttpServletRequest request) throws ParseException{
		String openId = request.getParameter("openId");
		String sqly = request.getParameter("sqly");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String shr= request.getParameter("shr");
		//SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String fqr ="";
		String fqrxm = "";
		String bmbh ="";
		String bmmc="";
	    Date kssj = sim.parse(starttime);
		Date jssj = sim.parse(endtime);
		List<Map<String,Object>> user=zyLcxxDao.getUser(openId);
		if(user.size()!=0){
			 fqr=user.get(0).get("yhbh")+"";
			 fqrxm=user.get(0).get("xm")+"";
			 bmbh=user.get(0).get("bmbh")+"";
			 bmmc=user.get(0).get("bmmc")+"";
		}else{
			SysYh yh = AppUtil.getCurrentUser();
			  fqr= yh.getYhbh();
			 fqrxm= yh.getXm();
			 bmbh = yh.getBmbh();
			 bmmc = zyLcxxDao.getBmmc(bmbh).get(0).get("bmmc")+"";
		}
		ZyLcxx  entity = new ZyLcxx();
		entity.setKssj(kssj);
		entity.setJssj(jssj);
		entity.setLcnr(sqly);
		entity.setShr(shr);
		entity.setLczl("2");
		entity.setLczt("1");
		if(zyLcxxDao.getLczl("sqjj").size()!=0){
        entity.setLcmc(zyLcxxDao.getLczl("sqjj").get(0).get("zdmc")+"");
		}
		entity.setFqr(fqr);
		entity.setFqrxm(fqrxm);
		entity.setBmbh(bmbh);
		entity.setBmmc(bmmc);
		zyLcxxDao.save(entity);
		return "1";
	}
	
	/**
	 * 出差申请的保存
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	  * @param request
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	public String saveCcxx(HttpServletRequest request) throws ParseException{
		String openId = request.getParameter("openId");
		String sqly = request.getParameter("sqly");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String shr= request.getParameter("shr");
		//SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String fqr ="";
		String fqrxm = "";
		String bmbh ="";
		String bmmc="";
	    Date kssj = sim.parse(starttime);
		Date jssj = sim.parse(endtime);
		List<Map<String,Object>> user=zyLcxxDao.getUser(openId);
		if(user.size()!=0){
			 fqr=user.get(0).get("yhbh")+"";
			 fqrxm=user.get(0).get("xm")+"";
			 bmbh=user.get(0).get("bmbh")+"";
			 bmmc=user.get(0).get("bmmc")+"";
		}else{
			SysYh yh = AppUtil.getCurrentUser();
			  fqr= yh.getYhbh();
			 fqrxm= yh.getXm();
			 bmbh = yh.getBmbh();
			 if( zyLcxxDao.getBmmc(bmbh).size()==0){
				 bmmc ="jszj";
			 }else{
			 bmmc = zyLcxxDao.getBmmc(bmbh).get(0).get("bmmc")+"";
		    }
		}
		ZyLcxx  entity = new ZyLcxx();
		entity.setKssj(kssj);
		entity.setJssj(jssj);
		entity.setLcnr(sqly);
		entity.setShr(shr);
		entity.setLczl("1");
		entity.setLczt("1");
		if(zyLcxxDao.getLczl("sqcc").size()!=0){
		entity.setLcmc(zyLcxxDao.getLczl("sqcc").get(0).get("zdmc")+"");
		}
		entity.setFqr(fqr);
		entity.setFqrxm(fqrxm);
		entity.setBmbh(bmbh);
		entity.setBmmc(bmmc);
		zyLcxxDao.save(entity);
		return "1";
	}
	
	/**
	 * 获取需我审批的页面
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @return
	 */
	public List<Map<String, Object>> getSqxx(String yhbh){
		return zyLcxxDao.getSqxx(yhbh);
	}
	
	/**
	 * 获取需我审批的详情的页面
	 * @author   oufeng
	 * @version 创建时间：2016年1月5日  
	 * @return
	 */
	public List<Map<String, Object>> getDetail(String id){
		return zyLcxxDao.getDetail(id);
	}
	
	/**
	 * 审核通过
	 * @author oufeng
	 * @date 2015年12月15日
	 * @param request
	 * @return
	 */
	@Transactional
	public String updateSqxxtg(HttpServletRequest request) {
		String str = "";
		String yhbh="";
		String id = request.getParameter("id");
		String shyj = request.getParameter("shyj");
		String openId = request.getParameter("openId");
		ZyLcxx  zylyxx = zyLcxxDao.get(Long.parseLong(id));
		if(openId==null || "".equals(openId)){
    		SysYh  yh =AppUtil.getCurrentUser();
 		     yhbh = yh.getYhbh()+"";
			yhbh = "1" ;
		}else{
		List<Map<String, Object>> user = zyLcxxDao.getUser(openId);
		if (user.size() != 0) {
			yhbh = user.get(0).get("yhbh")+"";
		}
		}
    	//zyLcxxDao.updateSqxxtg(id,shyj,yhbh);
		zylyxx.setShyj(shyj);
		zylyxx.setShsj(new Date());
		zylyxx.setLczt("2");
		zylyxx.setShr(yhbh);
		zyLcxxDao.save(zylyxx);
		str = "1";
		return str;
	}
	
	/**
	 * 申请驳回
	 * @author oufeng
	 * @date 2015年12月15日
	 * @param request
	 * @return
	 */
	@Transactional
	public String updateSqxxbh(HttpServletRequest request) {
		String str = "";
		String yhbh="";
		String id = request.getParameter("id");
		ZyLcxx  zylyxx = zyLcxxDao.get(Long.parseLong(id));
		String shyj = request.getParameter("shyj");
		String openId = request.getParameter("openId");
		if(openId==null || "".equals(openId)){
			SysYh  yh =AppUtil.getCurrentUser();
			yhbh = yh.getYhbh()+"";
		}else{
		List<Map<String, Object>> user = zyLcxxDao.getUser(openId);
		if (user.size() != 0) {
			yhbh =user.get(0).get("yhbh")+"";
		}
		}
		zylyxx.setShyj(shyj);
		zylyxx.setShsj(new Date());
		zylyxx.setLczt("3");
		zylyxx.setShr(yhbh);
		//zyLcxxDao.updateSqxxbh(id,shyj,yhbh);
		zyLcxxDao.save(zylyxx);
		str = "1";
		return str;
	}
	
	/**
	 * 获取审批信息
	 * @author oufeng
	 * @date 2016年1月6日
	 * @return
	 */
	public List<Map<String, Object>> getSpxx(String yhbh) {
		return zyLcxxDao.getSpxx(yhbh);
	}
	
	/**
	 * 获取出差的流程记录
	 * @author oufeng
	 * @date 2016年1月6日
	 * @return
	 */
	public List<Map<String, Object>> getCcjl(String yhbh) {
		return zyLcxxDao.getCcjl(yhbh);
	}
	
	/**
	 * 获取用户信息
	* @author oufeng
	 * @date 2016年1月6日
	 * @return
	 */
	public List<Map<String, Object>> getUser(String openId) {
		return zyLcxxDao.getUser(openId);
	}
	
	/**
	 * 获取角色
	* @author oufeng
	 * @date 2016年1月6日
	 * @return
	 */
	public List<Map<String, Object>> getJs(String yhbh) {
		return zyLcxxDao.getJs(yhbh);
	}
	
	/**
	 * 获取角色名称
	* @author oufeng
	 * @date 2016年1月6日
	 * @return
	 */
	public List<Object> getJsList(String yhbh) {
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
		return jsList;
	}
	
	
	/**
	 * 申请驳回修改
	 * @author oufeng
	 * @date 2015年1月6日
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	public String updateQjxx(HttpServletRequest request) throws ParseException {
		String str="";
		String id=request.getParameter("id");
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		String sqly=request.getParameter("sqly");
		String shr=request.getParameter("shr");
		String openId=request.getParameter("openId");
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			String fqr ="";
			String fqrxm = "";
			String bmbh ="";
			String bmmc="";
		    Date kssj = sim.parse(starttime);
			Date jssj = sim.parse(endtime);
			List<Map<String,Object>> user=zyLcxxDao.getUser(openId);
			if(user.size()!=0){
				 fqr=user.get(0).get("yhbh")+"";
				 fqrxm=user.get(0).get("xm")+"";
				 bmbh=user.get(0).get("bmbh")+"";
				 bmmc=user.get(0).get("bmmc")+"";
			}else{
				SysYh yh = AppUtil.getCurrentUser();
				  fqr= yh.getYhbh();
				 fqrxm= yh.getXm();
				 bmbh = yh.getBmbh();
				 bmmc = zyLcxxDao.getBmmc(bmbh).get(0).get("bmmc")+"";
			}
			ZyLcxx  entity = new ZyLcxx();
			entity.setKssj(kssj);
			entity.setJssj(jssj);
			entity.setLczl("2");
			entity.setLczt("1");
			entity.setFqr(fqr);
			entity.setFqrxm(fqrxm);
			entity.setBmbh(bmbh);
			entity.setBmmc(bmmc);
			entity.setLcnr(sqly);
			entity.setShr(shr);
			entity.setLcmc(zyLcxxDao.getLczl("sqjj").get(0).get("zdmc")+"");
			entity.setId(Long.parseLong(id));
			zyLcxxDao.update(entity);
			str="1";
		return str;
	}
	
	/**
	 * 申请驳回修改
	 * @author oufeng
	 * @date 2015年1月6日
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	public String updateCcxx(HttpServletRequest request) throws ParseException {
		String str="";
		String id=request.getParameter("id");
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		String sqly=request.getParameter("sqly");
		String shr=request.getParameter("shr");
		String openId=request.getParameter("openId");
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			String fqr ="";
			String fqrxm = "";
			String bmbh ="";
			String bmmc="";
		    Date kssj = sim.parse(starttime);
			Date jssj = sim.parse(endtime);
			List<Map<String,Object>> user=zyLcxxDao.getUser(openId);
			if(user.size()!=0){
				 fqr=user.get(0).get("yhbh")+"";
				 fqrxm=user.get(0).get("xm")+"";
				 bmbh=user.get(0).get("bmbh")+"";
				 bmmc=user.get(0).get("bmmc")+"";
			}else{
				SysYh yh = AppUtil.getCurrentUser();
				  fqr= yh.getYhbh();
				 fqrxm= yh.getXm();
				 bmbh = yh.getBmbh();
				 bmmc = zyLcxxDao.getBmmc(bmbh).get(0).get("bmmc")+"";
			}
			ZyLcxx  entity = new ZyLcxx();
			entity.setKssj(kssj);
			entity.setJssj(jssj);
			entity.setLczl("1");
			entity.setLczt("1");
			entity.setFqr(fqr);
			entity.setFqrxm(fqrxm);
			entity.setBmbh(bmbh);
			entity.setBmmc(bmmc);
			entity.setLcnr(sqly);
			entity.setShr(shr);
			entity.setLcmc(zyLcxxDao.getLczl("sqcc").get(0).get("zdmc")+"");
			entity.setId(Long.parseLong(id));
			zyLcxxDao.update(entity);
			str="1";
		return str;
	}
}
