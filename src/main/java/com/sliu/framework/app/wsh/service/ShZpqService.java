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
import com.sliu.framework.app.file.model.SysFjgl;
import com.sliu.framework.app.wsh.dao.ShFjglzpqDao;
import com.sliu.framework.app.wsh.dao.ShZpqDao;
import com.sliu.framework.app.wsh.dao.ShZpqdzDao;
import com.sliu.framework.app.wsh.model.ShZpq;
import com.sliu.framework.app.wsh.model.ShZpqdz;
import com.sliu.framework.app.wsh.model.SysFjglzpq;
import com.sliu.framework.app.wzy.dao.ZyTcbzDao;
import com.sliu.framework.app.wzy.model.ZyTcbz;
import com.sliu.framework.app.wzy.model.ZyWdtc;

/**
 * 照片墙
 * 
 * @author wangxiangyang
 * @version 创建时间：2016年9月7日
 */
@Service
public class ShZpqService extends BaseBO<ShZpq> {

	@Autowired
	private ShZpqDao shZpqDao;
	
	@Autowired
	private ShZpqdzDao shZpqdzDao;
	
	@Autowired
	private ShFjglzpqDao shFjglzpqDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 照片墙查询
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getZpq(HttpServletRequest request) {
		
		String pages=request.getParameter("pages");
		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return shZpqDao.getBbq(pages);
	}
	
	/**
	 * 用户信息查询
	 * @author wangxiangyang
	 * @date 2016年9月8日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getWxyh(String openId) {
		
		return shZpqDao.getWxyh(openId);
	}
	
	/**
	 * 用户信息查询
	 * @author wangxiangyang
	 * @date 2016年9月8日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getTP(String openId) {
		
		return shZpqDao.getTP(openId);
	}
	
	/**
	 * 用户信息查询
	 * @author wangxiangyang
	 * @date 2016年9月8日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getYh(String openId) {
		
		return shZpqDao.getYh(openId);
	}
	
	/**
	 * 照片墙发布
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveZpq(ShZpq entity) {
		shZpqDao.save(entity);
		return "1";
	}
	
	/**
	 * 删除
	 * @author wangxiangyang
	 * @date 2016年9月7日
	 * @param id
	 */
	@Transactional
	public void deleteZpqList(Long id){
		shZpqDao.deleteZpq(id);
	}
	
	/**
	 * 判断是否赞过
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getzan(Long id,String openId) {

		return shZpqdzDao.getZanlist(id,openId);

	}

	/**
	 * 点赞
	 * 
	 * @param entity
	 * @param id   我的吐槽ID
	 * @return
	 */
	@Transactional
	public String saveDianzan(ShZpqdz entity, Long id) {
		shZpqdzDao.save(entity);
		ShZpq zpq = shZpqDao.get(id);
		if (zpq != null) {
			if (zpq.getBzcs() == null) {
				zpq.setBzcs(1);
			} else {
				zpq.setBzcs(zpq.getBzcs() + 1);
			}
		}
		shZpqDao.update(zpq);
		return "1";
	}
	
	@Transactional
	public void saveFile(String data, String wjlx, Long lxid,String fileNameU,String openId) {
		SysFjglzpq fjgl = new SysFjglzpq();
		fjgl.setContent(data);
		fjgl.setFname(fileNameU);
		fjgl.setLxid(lxid);
		fjgl.setWjlx(wjlx);
		fjgl.setWxh(openId);
		fjgl.setTcsj(Calendar.getInstance().getTime());
		shFjglzpqDao.save(fjgl);
	}

	@Transactional
	public SysFjglzpq getfile(Long imgId) {
		return shFjglzpqDao.get(imgId);
	}
	
	@Transactional
	public List<Map<String, Object>> getImgId(HttpServletRequest request) {
		String fname=request.getParameter("fname");
		return shFjglzpqDao.getImgId(fname);
	}
	
	/**
	 * @author   wangxiangyang
	 * @version 创建时间：2016年9月29日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getZpqList(Integer start, Integer limit){
		
		return shZpqDao.getZpqList(start, limit);
		
	}
	
	/**
	 * @author wangxiangyang
	 * @version 创建时间：2016年9月29日
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {

		for (int i = 0; i < ids.length; i++) {
			shZpqDao.delete(ids[i]);
		}
	}
}
