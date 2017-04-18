package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysWxyh;
import com.sliu.framework.app.wfw.dao.WfwZyDao;
import com.sliu.framework.app.wzy.dao.ZyCdpzDao;

/**
*解除绑定
@Author oufeng	
@Date 2016年1月13日 下午3:16:03
@Version 1.0
*/
@Service
public class WfwZyService extends BaseBO<SysWxyh>{
	
	protected Logger logger = LoggerFactory.getServiceLog(WfwZyService.class);
	
	@Autowired
	private WfwZyDao dao;
	
	@Autowired
	private ZyCdpzDao zyCdpzDao;
	
	/**
	 * 解除绑定
	 * @author oufeng
	 * @date 2016年1月14日
	 * @param request
	 * @return
	 */
	public String  jsbd(String openId){
		 return  dao.jcbd(openId);
	}

	/**
	 * 根据模块类型和角色名称获取菜单
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月8日 上午9:22:02 
	 * @param mklx
	 * @param jsmc
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getListMenu(String mklx, String jsmc) {
		List<Map<String, Object>> list = zyCdpzDao.getListMenu(mklx,jsmc);
		return list;
	}

	/**
	 * 显示失物招领信息
	 * @author liujiansen
	 * @date 2016年3月10日
	 * @param pages
	 * @return
	 */
	public List<Map<String,Object>> getSwzlList(String pages){
		return dao.getSwzlList(pages);
	}

	/**
	 * 获取全角色的菜单
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月29日 上午11:54:05
	 * @param string
	 * @param jsmc
	 * @return
	 */
	public List<Map<String, Object>> getListAllMenu(String mklx, String jsmc) {
		List<Map<String, Object>> list = zyCdpzDao.getAllListMenu(mklx,jsmc);
		return list;
	}
}


