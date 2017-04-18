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
import com.sliu.framework.app.wsh.dao.ShEsscDao;
import com.sliu.framework.app.wsh.model.ShEssc;

/**
 * 二手市场
 * @author liujiansen
 * @date 2015年6月30日
 */
@Service
public class ShEsscService extends BaseBO<ShEssc>{

	protected Logger logger = LoggerFactory.getServiceLog(ShEssc.class);
	
	@Autowired
	private ShEsscDao dao;
	
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
		String splx=request.getParameter("splx");
		String keyword=request.getParameter("keyword");
		String orderBy=request.getParameter("order_by");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
   		return dao.getGoodList(pages, splx,keyword,orderBy);
	}
	
	/**
	 * 获取商品的总条数
	 * @author liujiasen
	 * @date 2015年6月30日
	 * @param splx 商品类型
	 * @return
	 */
	public int getCount(HttpServletRequest request){
		String splx=request.getParameter("splx");
		String keyword=request.getParameter("keyword");
		String orderBy=request.getParameter("order_by");
		return dao.getCount(splx,keyword,orderBy);
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
	public void saveGood(HttpServletRequest request,HttpServletResponse response) {
		String openId=request.getParameter("openId");
		String spzp=request.getParameter("fileName");
		String spmc=request.getParameter("goods_name");
		String spms=request.getParameter("goods_detail");
		String jg=request.getParameter("goods_price");
		String splx=request.getParameter("first_id");
		String lxfs=request.getParameter("qqnum");
		List<Map<String,Object>> list=dao.getWxUser(openId);
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmss");
		ShEssc essc=new ShEssc();
		essc.setSpmc(spmc);
		essc.setJg(jg);
		essc.setLxfs(lxfs);
		essc.setSjsj(new Date());
		essc.setSpbh(sim.format(new Date()));
		essc.setSplx(splx);
		essc.setSpms(spms);
		essc.setSpzp(spzp);
		essc.setWpzt("1");
		essc.setWzbh(openId);
		if(list.size()!=0){
			essc.setWzxm(list.get(0).get("nickname")+"");
		}
		dao.save(essc);
	}

	/**
	 * 商品的售出或下架
	 * @author liujiasen
	 * @date 2015年7月1日
	 * @param request
	 * @param response
	 */
	public void updateGood(HttpServletRequest request,HttpServletResponse response) {
		String id=request.getParameter("id");
		String lx=request.getParameter("lx");
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(lx!=null&&!"".equals(lx)){
			if("2".equals(lx)){
				String xjsj=sim.format(new Date());
				dao.update(id,lx,xjsj);
			}else if("3".equals(lx)){
				String scsj=sim.format(new Date());
				dao.update(id,lx,scsj);
			}
		}
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
}
