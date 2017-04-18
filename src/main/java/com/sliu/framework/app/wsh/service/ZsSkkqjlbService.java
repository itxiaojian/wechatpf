package com.sliu.framework.app.wsh.service;

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
import com.sliu.framework.app.wsh.dao.ZsSkkqbDao;
import com.sliu.framework.app.wsh.dao.ZsSkkqjlbDao;
import com.sliu.framework.app.wsh.model.ZsSkkqb;
import com.sliu.framework.app.wsh.model.ZsSkkqjlb;

/**
 * 二手市场
 * @author liujiansen
 * @date 2015年6月30日
 */
@Service
public class ZsSkkqjlbService extends BaseBO<ZsSkkqjlb>{

	protected Logger logger = LoggerFactory.getServiceLog(ZsSkkqjlb.class);
	
	@Autowired
	private ZsSkkqjlbDao dao;
	
	@Autowired
	private ZsSkkqbDao qbDao;
	
	/**
	 * 查询商品数据
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param pages 页数
	 * @param splx 商品类型
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getGoodList(HttpServletRequest request,HttpServletResponse response){
		String pages=request.getParameter("pages");
		String keyword=request.getParameter("keyword");
		String id=request.getParameter("id");
		if(pages==null||"".equals(pages)){
			pages="1";
		}
		ZsSkkqb kqb=qbDao.get(Long.parseLong(id));
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM");
		String[] times=sim.format(kqb.getSksj()).split("-");
   		String dxq="";
   		String xn="";
   		int num=Integer.parseInt(times[1]);
   		if(num<=8){
   			dxq="2";
   			xn=(Integer.parseInt(times[0])-1)+"";
   		}else{
   			xn=times[0];
   			dxq="1";
   		}
   		return dao.getGoodList(pages, keyword,dxq,id,xn);
	}
	
	/**
	 * 获取商品的总条数
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param splx 商品类型
	 * @return
	 */
	@Transactional
	public int getCount(HttpServletRequest request){
		String keyword=request.getParameter("keyword");
		String id=request.getParameter("id");
		ZsSkkqb kqb=qbDao.get(Long.parseLong(id));
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM");
		String[] times=sim.format(kqb.getSksj()).split("-");
   		String dxq="";
   		String xn="";
   		int num=Integer.parseInt(times[1]);
   		if(num<=8){
   			dxq="2";
   			xn=(Integer.parseInt(times[0])-1)+"";
   		}else{
   			xn=times[0];
   			dxq="1";
   		}
		return dao.getCount(keyword,dxq,id,xn);
	}

	/**
	 * 保存
	 * @author liujiansen
	 * @date 2016年3月29日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String saveMs(String id,String openId) {
		String str="0";
		List<Map<String,Object>> yh=qbDao.getYh(openId);
		List<Map<String,Object>> user=null;
		if(yh.size()!=0){
			user=qbDao.getUser(yh.get(0).get("yhid").toString());
			List<Map<String,Object>> list=dao.getQdxx(id, user.get(0).get("dlm").toString());
			if(list.size()!=0){
				str="error";
			}else{
				ZsSkkqjlb jl=new ZsSkkqjlb();
				jl.setKqid(Long.parseLong(id));
				jl.setQdsj(new Date());
				jl.setXh(user.get(0).get("dlm").toString());
				jl.setXm(user.get(0).get("xm").toString());
				dao.save(jl);
				str="1";
			}
		}
		return str;
	}
}
