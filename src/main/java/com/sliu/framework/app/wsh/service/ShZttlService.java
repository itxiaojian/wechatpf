package com.sliu.framework.app.wsh.service;

import java.util.Calendar;
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
import com.sliu.framework.app.wsh.dao.ShZttlDao;
import com.sliu.framework.app.wsh.dao.ShZttlplDao;
import com.sliu.framework.app.wsh.model.ShZttl;

/**
 * 主页——专题讨论
 * 
 * @author zhangyan
 * @version 创建时间：2016年7月14日 
 */
@Service
public class ShZttlService extends BaseBO<ShZttl> {

	@Autowired
	private ShZttlDao shZttlDao;
	
	@Autowired
	private ShZttlplDao shZttlplDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	/**
	 * 分页查询专题讨论
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param start
	 * @param limit
	 * @param ztmc  专题名称
	 * @param ztbt  专题标题
	 * @return
	 */
	public Pagination<Map<String, Object>> getZttlList(Integer start,
			Integer limit, String ztmc, String ztbt) {
		return shZttlDao.getZttlList(start, limit, ztmc, ztbt);
	}
	
	/**
	 * 查看专题
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日 
	 * @param id
	 * @return
	 */
	@Transactional
	public ShZttl getXyxwById(Long id) {
		ShZttl entity = shZttlDao.get(id);
		if(entity!=null){
			return entity;
		}
		return null;
	}
	
	/**
	 * 发布专题
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveZttl(ShZttl entity) {
		SysYh yh = AppUtil.getCurrentUser();
		entity.setFbr(yh.getXm());
		entity.setZt("1");
		entity.setFbsj(Calendar.getInstance().getTime());
		shZttlDao.save(entity);
		return "1";
	}
	
	/**
	 * 修改专题
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月14日 
	 * @param entity
	 * @param id
	 * @return
	 */
	@Transactional
	public String updateZttl(ShZttl entity, Long id) {
		ShZttl esse = shZttlDao.get(id);
		esse.setZtmc(entity.getZtmc());
		esse.setZtbt(entity.getZtbt());
		esse.setZtnr(entity.getZtnr());
		esse.setJhjssj(entity.getJhjssj());
		shZttlDao.update(esse);
		return "1";
	}


	/**
	 * 删除
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月14日 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		shZttlDao.delete(id);
		shZttlplDao.deletepl(id);
	}
	
	/**
	 * 查询专题列表
	 * 
	 * @author zhangyan
	 * @version 创建时间：2016年7月20日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getList(HttpServletRequest request) {
		
		String pages=request.getParameter("pages");
		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return shZttlDao.getCx(pages);
	}
	
	/**
	 * 获取专题详细内容
	 * @author  zhangyan
	 * @version 2016年07月20日
	 * @param zbm
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDetailById(String id) {
		return shZttlDao.getDetailById(id);
	}

	
	/**
	 * 前台：专题详情页面评论“删除”按钮
	 * @author zhangyan
	 * @date 2016年7月21日
	 * @param id
	 */
	@Transactional
	public void deleteWdtcList(Long id){
//		shZttlDao.delete(id);
		shZttlplDao.deleteHf(id);
	}
}
