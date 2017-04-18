package com.sliu.framework.app.wsh.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.model.SysZzjg;
import com.sliu.framework.app.wsh.dao.ZsSkkqbDao;
import com.sliu.framework.app.wsh.model.ShEssc;
import com.sliu.framework.app.wsh.model.ZsSkkqb;

/**
 * 二手市场
 * @author liujiansen
 * @date 2015年6月30日
 */
@Service
public class ZsSkkqbService extends BaseBO<ZsSkkqb>{

	protected Logger logger = LoggerFactory.getServiceLog(ShEssc.class);
	
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
	public List<Map<String,Object>> getGoodList(HttpServletRequest request,HttpServletResponse response){
		String pages=request.getParameter("pages");
		String openId = request.getParameter("openId");
		String keyword=request.getParameter("keyword");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
   		return dao.getGoodList(pages, keyword,openId);
	}
	
	/**
	 * 获取商品的总条数
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param splx 商品类型
	 * @return
	 */
	public int getCount(HttpServletRequest request){
		String keyword=request.getParameter("keyword");
		String openId=request.getParameter("openId");
		return dao.getCount(keyword,openId);
	}

	/**
	 * 根据商品Id获取商品信息
	 * @author liujiasen
	 * @date 2015年7月1日
	 * @param id 商品Id
	 * @return
	 */
	public List<Map<String, Object>> getGood(String id) {
		return dao.getGood(id);
	}

	/**
	 * 保存发布商品信息
	 * @author liujiasen
	 * @date 2015年7月1日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String saveGood(HttpServletRequest request,HttpServletResponse response) {
		String str="0";
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String openId=request.getParameter("openId");
		List<Map<String,Object>> yh=dao.getYh(openId);
		List<Map<String,Object>> user=null;
		if(yh.size()!=0){
			user=dao.getUser(yh.get(0).get("yhid").toString());
		}
		String kcmcStr=request.getParameter("kcmc");
		String time=request.getParameter("time");
		String sxsj=request.getParameter("sxsj");
		String bz=request.getParameter("bz");
		Date date=new Date();
		try {
			date=sim.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(user!=null&&user.size()!=0){
			Date jzsj=new Date(date.getTime()+Integer.parseInt(sxsj)*60*1000);
			String[] arr=kcmcStr.split("_");
			String kch=arr[0];
			String kcmc=arr[1];
			ZsSkkqb kqb=new ZsSkkqb();
			kqb.setBz(bz);
			kqb.setEwmsxsj(jzsj);
			kqb.setKch(kch);
			kqb.setKcmc(kcmc);
			kqb.setSksj(date);
			kqb.setGh(user.get(0).get("dlm").toString());
			kqb.setXm(user.get(0).get("xm").toString());
			Long id=dao.save(kqb);
			str=id+"";
		}
		return str;
	}

	/**
	 * 根据商品Id删除商品
	 * @author liujiasen
	 * @date 2015年7月20日
	 * @param id 商品Id
	 */
	@Transactional
	public void delete(String id) {
		dao.delete(Long.parseLong(id));
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2016年3月28日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getKc(String openId) {
		List<Map<String,Object>> yh=dao.getYh(openId);
		List<Map<String,Object>> kc=null;
		if(yh.size()!=0){
			List<Map<String,Object>> user=dao.getUser(yh.get(0).get("yhid").toString());
			if(user.size()!=0){
				kc=dao.getKc(user.get(0).get("dlm").toString());
			}
		}
		return kc;
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2016年3月30日
	 * @param kcbh
	 * @param openId
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getSkdd(String kcbh, String openId) {
		String[] arr=kcbh.split("_");
		String kch=arr[0];
//		String kcmc=arr[1];
		List<Map<String,Object>> yh=dao.getYh(openId);
		String jsgh="";
		if(yh.size()!=0){
			List<Map<String,Object>> user=dao.getUser(yh.get(0).get("yhid").toString());
			if(user.size()!=0){
				jsgh=user.get(0).get("dlm").toString();
			}
		}
		List<Map<String,Object>> zzjg = dao.getSkdd(kch,jsgh);
		return zzjg;
	}
}
