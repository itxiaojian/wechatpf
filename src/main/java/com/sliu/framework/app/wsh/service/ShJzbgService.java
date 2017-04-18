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
import com.sliu.framework.app.wsh.dao.ShJzbgDao;
import com.sliu.framework.app.wsh.dao.ShJzbgplDao;
import com.sliu.framework.app.wsh.model.ShJzbg;

/**
 * 讲座报告
 * 
 * @author zhangyan
 * @version 创建时间：2016年8月1日 
 */
@Service
public class ShJzbgService extends BaseBO<ShJzbg> {

	@Autowired
	private ShJzbgDao shJzbgDao;
	
	@Autowired
	private ShJzbgplDao shJzbgplDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	/**
	 * 分页查询讲座报告
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	 * @param start
	 * @param limit
	 * @param jzmc  讲座名称
	 * @param jzbt  讲座标题
	 * @return
	 */
	public Pagination<Map<String, Object>> getJzbgList(Integer start,
			Integer limit, String jzmc, String jzbt) {
		return shJzbgDao.getJzbgList(start, limit, jzmc, jzbt);
	}
	
	/**
	 * 查看讲座
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日 
	 * @param id
	 * @return
	 */
	@Transactional
	public ShJzbg getXyxwById(Long id) {
		ShJzbg entity = shJzbgDao.get(id);
		if(entity!=null){
			return entity;
		}
		return null;
	}
	
	/**
	 * 发布讲座报告
	 * @author   zhangyan
	 * @version 创建时间：2016年8月1日  
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveJzbg(ShJzbg entity) {
		SysYh yh = AppUtil.getCurrentUser();
		entity.setFbr(yh.getXm());
		entity.setZt("1");
		entity.setJzsj(Calendar.getInstance().getTime());
		shJzbgDao.save(entity);
		return "1";
	}
	
	/**
	 * 修改讲座报告
	 * @author:zhangyan 
	 * @version 创建时间：2016年8月1日 
	 * @param entity
	 * @param id
	 * @return
	 */
	@Transactional
	public String updateJzbg(ShJzbg entity, Long id) {
		ShJzbg esse = shJzbgDao.get(id);
		esse.setJzmc(entity.getJzmc());
		esse.setJzbt(entity.getJzbt());
		esse.setJznr(entity.getJznr());
		esse.setBz(entity.getBz());
		esse.setZt(entity.getZt());
//		esse.setJhjssj(entity.getJhjssj());
		shJzbgDao.update(esse);
		return "1";
	}


	/**
	 * 删除
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月14日 
	 * @param id
	 */
	@Transactional
	public void deleteJzbg(Long id) {
		shJzbgDao.delete(id);
		shJzbgplDao.deletepl(id);
	}
	
	/**
	 * 查询讲座报告
	 * 
	 * @author zhangyan
	 * @version 创建时间：2016年8月1日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getList(HttpServletRequest request) {
		
		String pages=request.getParameter("pages");
		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return shJzbgDao.getCx(pages);
	}
	
	/**
	 * 获取讲座报告详细内容
	 * @author  zhangyan
	 * @version 2016年8月1日
	 * @param zbm
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDetailById(String id) {
		return shJzbgDao.getDetailById(id);
	}

	
	/**
	 * 前台：讲座报告详情页面评论“删除”按钮
	 * @author zhangyan
	 * @date 2016年8月1日
	 * @param id
	 */
	@Transactional
	public void deletePj(Long id){
//		shZttlDao.delete(id);
		shJzbgplDao.deleteHf(id);
	}
}

