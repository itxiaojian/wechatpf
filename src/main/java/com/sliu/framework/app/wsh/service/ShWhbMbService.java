package com.sliu.framework.app.wsh.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.dao.ShWhbMbDao;
import com.sliu.framework.app.wsh.dao.ShWhbYmDao;
import com.sliu.framework.app.wsh.dao.ShWhbZtDao;
import com.sliu.framework.app.wsh.model.ShWhbFJ;
import com.sliu.framework.app.wsh.model.ShWhbMb;
import com.sliu.framework.app.wsh.model.ShWhbYm;
import com.sliu.framework.app.wsh.model.ShWhbZt;

@Service
public class ShWhbMbService extends BaseBO<ShWhbMb> {

	protected Logger logger = LoggerFactory.getServiceLog(ShWhbMb.class);

	@Autowired
	private ShWhbMbDao mbdao;
	
	@Autowired
	private ShWhbZtDao ztdao;
	
	@Autowired
	private ShWhbYmDao ymdao;

	/**
	 * 
	 * 
	 @Author oufeng
	 * @Date 2015年7月3日 下午1:52:07
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getMb(HttpServletRequest request) {
		return mbdao.getMb();
	}

	/**
	 * 删除
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年7月7日 下午08:49:44
	 * @param ids
	 */
/*	@Transactional
	public void delete(Integer id) {
		mbdao.delete(id);
	}*/

	/**
	 * 
	 * 获得未发布
	 * 
	 * @Author oufeng
	 * @Date 2015年7月3日 下午1:52:07
	 * @Version 1.0
	 */
	@Transactional
	public List<Map<String, Object>> getMbNo(HttpServletRequest request) {
		return mbdao.getMbNo();
	}

	/**
	 * 
	 * 删除添加的海报
	 * @Author oufeng
	 * @Date 2015年7月3日 下午1:52:07
	 * @Version 1.0
	 */
	public void deleteMb(int id) {
		 mbdao.deleteYm(id);
		 mbdao.deleteHb(id);
	}
	
	/**
	 * 
	 * 获得未发布
	 * 
	 * @Author oufeng
	 * @Date 2015年7月3日 下午1:52:07
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getMbHidden(HttpServletRequest request) {
		return mbdao.getMbHidden();
	}
	
	/**
	 * 
	 * 获得未发布
	 * 
	 * @Author oufeng
	 * @Date 2015年7月3日 下午1:52:07
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getBjMsic(HttpServletRequest request) {
		return mbdao.getBjMusic();
	}
	
	/**
	 * 
	 * 获得主题信息
	 * 
	 * @Author oufeng
	 * @Date 2015年7月3日 下午1:52:07
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getZt(Long id) {
		return ztdao.getZt(id);
	}
	
	/**
	 * 
	 * 保存附件
	 * @throws ParseException 
	 * 
	 * @Author oufeng
	 * @Date 2015年7月3日 下午1:52:07
	 * @Version 1.0
	 */
	@Transactional
	public void saveFuJian(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		// String openId=request.getParameter("openId");
		String name = request.getParameter("fileName");
		String type = request.getParameter("type");
		String times = request.getParameter("time");
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = formatter.parse(times);
		String fname = request.getParameter("name");
		// List<Map<String,Object>> list=mbdao.getWxUser(openId);
		ShWhbFJ shwhbfj = new ShWhbFJ();
		shwhbfj.setFjmc(fname);
		shwhbfj.setFjlx(type);
		shwhbfj.setFjlj(name);
		shwhbfj.setTjsj(date);
		mbdao.save(shwhbfj);
	}
	
	/**
	 * 
	 * 保存主题
	 * @throws ParseException 
	 * 
	 * @Author oufeng
	 * @Date 2015年7月3日 下午1:52:07
	 * @Version 1.0
	 */
	@Transactional
	public Long saveZt(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		// String openId=request.getParameter("openId");
		String name = request.getParameter("fileName");
		String music_route = request.getParameter("music");
//		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String poster_name = request.getParameter("poster_name");
		String music_name = request.getParameter("music_name");
		// List<Map<String,Object>> list=mbdao.getWxUser(openId);
		SysYh user=AppUtil.getCurrentUser();
		ShWhbZt shwhbzt = new ShWhbZt();
		shwhbzt.setCjrbh(user.getYhbh());
		shwhbzt.setBjyy(music_route);
		shwhbzt.setHbfm(name);
		shwhbzt.setHbmc(poster_name);
		shwhbzt.setZt("0");
		shwhbzt.setBz(music_name);
		shwhbzt.setCjsj(new Date());
		Long id=ztdao.save(shwhbzt);
		return id;
	}

	/**
	 * 保存页面
	 * @author liujiansen
	 * @date 2015年8月19日
	 * @param request
	 * @return
	 */
	@Transactional
	public String saveYm(HttpServletRequest request) {
		String str="0";
		String hbid=request.getParameter("hbid");
		String bgtp=request.getParameter("bgtp");
		String zw=request.getParameter("zw");
		String zt=request.getParameter("zt");
		ShWhbYm ym=new ShWhbYm();
		ym.setFmtp(bgtp);
		ym.setFmzt(zt);
		ym.setFmzw(zw);
		ym.setHbid(Long.parseLong(hbid));
		ymdao.save(ym);
		str="1";
		return str;
	}

	/**
	 * 删除页面
	 * @author liujiansen
	 * @date 2015年8月19日
	 * @return
	 */
	@Transactional
	public String deleteYm(HttpServletRequest request) {
		String hbid=request.getParameter("hbid");
		String zt=request.getParameter("zt");
		return ymdao.deleteYm(hbid, zt);
	}

	/**
	 * 修改页面
	 * @author liujiansen
	 * @date 2015年8月19日
	 * @param request
	 * @return
	 */
	@Transactional
	public String updateYm(HttpServletRequest request) {
		String str="0";
		str=ymdao.updateYm(request);
		return str;
	}
	
	/**
	 * 获取最新发布的海报
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @return
	 */
	public List<Map<String, Object>> getWhb() {
		return mbdao.getWhb();
	}
	
	/**
	 * 根据海报编号获取海报的页面
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @return
	 */
	public List<Map<String, Object>> getWhbYm(String hbid) {
		return mbdao.getWhbYm(hbid);
	}
	
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param hbid
	 * @return
	 */
	public ArrayList<String> getWhbYmTp(String hbid) {
		ArrayList<String> arr=new ArrayList<String>();
		List<Map<String,Object>> ym=mbdao.getWhbYm(hbid);
		if(ym.size()!=0){
			for(int i=0;i<ym.size();i++){
				arr.add("\""+(ym.get(i).get("fmtp")+"").trim()+"\"");
			}
		}
		System.out.println(arr.toString());
		return arr;
	}

	/**
	 * 删除页面
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param hbid
	 */
	@Transactional
	public void deleteYm(String hbid) {
		ymdao.deleteYm(hbid);
	}

	/**
	 * 获取海报
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param hbid
	 * @return
	 */
	public List<Map<String, Object>> getWhbZt(String hbid) {
		return ztdao.getWhbZt(hbid);
	}
	
	/**
	 * 修改
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param request
	 * @param response
	 */
	@Transactional
	public void updateZt(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("fileName");
		String id = request.getParameter("id");
		String music_route = request.getParameter("music");
		String poster_name = request.getParameter("poster_name");
		String music_name = request.getParameter("music_name");
		ShWhbZt shwhbzt = ztdao.get(Long.parseLong(id));
		shwhbzt.setBjyy(music_route);
		shwhbzt.setHbfm(name);
		shwhbzt.setHbmc(poster_name);
		shwhbzt.setZt("0");
		shwhbzt.setBz(music_name);
		shwhbzt.setId(Long.parseLong(id));
		ztdao.update(shwhbzt);
	}

	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月20日
	 * @param request
	 * @return
	 */
	public String whbZtfb(HttpServletRequest request) {
		String str="0";
		String id=request.getParameter("id");
		ztdao.whbZtfb(id);
		str="1";
		return str;
	}
}