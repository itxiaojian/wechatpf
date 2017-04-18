package com.sliu.framework.app.wzy.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wzy.dao.ZyTcbzDao;
import com.sliu.framework.app.wzy.dao.ZyTchfDao;
import com.sliu.framework.app.wzy.dao.ZyWdtcDao;
import com.sliu.framework.app.wzy.model.ZyCdpz;
import com.sliu.framework.app.wzy.model.ZyTcbz;
import com.sliu.framework.app.wzy.model.ZyTchf;
import com.sliu.framework.app.wzy.model.ZyWdtc;

/**
 * 主页——我的吐槽
 * 
 * @author duanpeijun
 * @version 创建时间：2015年6月8日 下午4:43:39
 */
@Service
public class ZyWdtcService extends BaseBO<ZyWdtc> {

	@Autowired
	private ZyWdtcDao zyWdtcDao;

	@Autowired
	private ZyTchfDao zyTchfDao;

	@Autowired
	private ZyTcbzDao zyTcbzDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	public Pagination<Map<String, Object>> getWdtcList(Integer start,
			Integer limit, String tclx) {
		return zyWdtcDao.getWdtcList(start, limit,tclx);
	}
	
	/**
	 * 获取字典表中的吐槽类型
	 * @author duanoeijun
	 * @date 2015年7月29日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getLx(){
		return zyWdtcDao.getLx();
	}

	/**
	 * 查询吐槽详情
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月8日 下午6:20:38
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getList(HttpServletRequest request) {
		
		String pages=request.getParameter("pages");
		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zyWdtcDao.getCx(pages);
	}
	
	/**
	 * 话题： 吐槽的数量
	 * @author duanpeijun
	 * @date 2015年7月14日
	 * @return
	 */
	public List<Map<String, Object>> getHuati(){
		return zyWdtcDao.getHuati();
	}

	/**
	 * 获取回复的列表
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月17日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getHfList(Long id) {

		return zyTchfDao.getHfCx(id);

	}
	/**
	 * 获取回复的列表
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月17日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getHfList1() {

		return zyTchfDao.getHfCx1();

	}

	/**
	 * 吐槽发布
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月16日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveWdtc(ZyWdtc entity) {
		zyWdtcDao.save(entity);
		return "1";
	}

	/**
	 * 吐槽回复
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月16日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveTchf(ZyTchf entity, Long id) {
		zyTchfDao.save(entity);
		ZyWdtc wdtc = zyWdtcDao.get(id);
		if (wdtc != null) {
			if (wdtc.getHfcs() == null) {
				wdtc.setHfcs(1);
			} else {
				wdtc.setHfcs(wdtc.getHfcs() + 1);
			}
		}
		zyWdtcDao.update(wdtc);
		return "1";
	}
	
	/**
	 * 赞数量
	 * @author duanpeijun
	 * @date 2015年7月14日
	 * @return
	 */
	public List<Map<String, Object>> getZanshuliang(){
		
		return zyTcbzDao.getZan();
	}

	/**
	 * 判断是否赞过
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getzan(Long id,String openId) {

		return zyTcbzDao.getZanlist(id,openId);

	}

	/**
	 * 点赞
	 * 
	 * @param entity
	 * @param id   我的吐槽ID
	 * @return
	 */
	@Transactional
	public String saveDianzan(ZyTcbz entity, Long id) {
		zyTcbzDao.save(entity);
		ZyWdtc wdtc = zyWdtcDao.get(id);
		if (wdtc != null) {
			if (wdtc.getBzcs() == null) {
				wdtc.setBzcs(1);
			} else {
				wdtc.setBzcs(wdtc.getBzcs() + 1);
			}
		}
		zyWdtcDao.update(wdtc);
		return "1";
	}

	/**
	 * 后台：管理员删除吐槽
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月9日 上午9:43:25
	 * @param ids
	 */
	@Transactional
	public void deleteWdtc(Long[] ids) {

		for (int i = 0; i < ids.length; i++) {
			zyWdtcDao.delete(ids[i]);
		}
	}
	
	/**
	 * 前台：我的吐槽页面“删除”按钮
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @param id
	 */
	@Transactional
	public void deleteWdtcList(Long id){
		zyWdtcDao.delete(id);
		zyWdtcDao.deleteHf(id);
		zyWdtcDao.deleteBz(id);
	}
	
	/**
	 * 查询表白墙
	 * @author duanpeijun
	 * @date 2015年7月13日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getBbq(HttpServletRequest request) {
		
		String pages=request.getParameter("pages");
		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zyWdtcDao.getBbq(pages);
	}
	
	/**
	 * 用户信息查询
	 * @author wangxiangyang
	 * @date 2016年9月8日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getYh(String openId) {
		
		return zyWdtcDao.getYh(openId);
	}
	
	/**
	 * 表白发布
	 * @author duanpeijun
	 * @date 2015年7月13日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveBbq(ZyWdtc entity) {
		zyWdtcDao.save(entity);
		return "1";
	}
	
	/**
	 * 前台：表白墙页面“删除”按钮
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @param id
	 */
	@Transactional
	public void deleteBbqList(Long id){
		zyWdtcDao.delete(id);
		zyWdtcDao.deleteHf(id);
		zyWdtcDao.deleteBz(id);
	}

	/**
	 * 通过数据字典
	 * @author wangchunlin
	 * @date 2016年1月21日
	 * @param 
	 */
	public List<Map<String, Object>> getDicByZt(String zdzl) {
		// TODO Auto-generated method stub
		return zyWdtcDao.getDicByZt(zdzl);
	}
	/**
	 * 审批
	 * @author wangchunlin
	 * @date 2016年1月21日
	 * @param 
	 */
	@Transactional
	public ZyWdtc getWdtcById(long id) {
		ZyWdtc entity = zyWdtcDao.get(id);
		if(entity!=null){
			return entity;
		}
		return null;
	
	}
	
	/**
	 * @author   wangxiangyang
	 * @version 创建时间：2016年9月29日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getBbqList(Integer start, Integer limit){
		
		return zyWdtcDao.getBbqList(start, limit);
		
	}
}
