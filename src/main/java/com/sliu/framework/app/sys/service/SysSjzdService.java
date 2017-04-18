package com.sliu.framework.app.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.dao.SysSjzdDao;
import com.sliu.framework.app.sys.model.SysSjzd;

/**
 * 数据字典管理
 * @author : zhangyi
 * @version 创建时间：2015年10月23日 下午3:03:10
 */
@Service
public class SysSjzdService {

	@Autowired
	private SysSjzdDao sysSjzdDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	/**
	 * 分页获取一级字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午2:59:09 
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getFirstList(Integer start,Integer limit,String zdzl,String zdmc) {
		return sysSjzdDao.getFirstList(start, limit,zdzl,zdmc);
	}
	
	/**
	 * 分页获取二级字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午2:59:09 
	 * @param start
	 * @param limit
	 * @param yhzh 
	 * @param yhxm 
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> getSecondDict(Integer start,Integer limit,String zl) {
		return sysSjzdDao.getSecondDict(start, limit,zl);
	}

	
	/**
	 * 保存
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 下午2:59:38 
	 * @param sysSjzd
	 * @return
	 */
	@Transactional
	public String save(SysSjzd sysSjzd) {
		sysSjzdDao.save(sysSjzd);
		return "1";	
	}
	
	/**
	 * 修改
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String update(SysSjzd entity) {
		sysSjzdDao.update(entity);
		return "1";
	}

	/**
	 * 验证字典值zl是否重复
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String checkzl(String zl) {
		String str="";
		List<Map<String, Object>> list = sysSjzdDao.checkzl(zl);
		if(list.isEmpty()){
			str = "";
		}else{
			str = "1";
		}
		return str;
	}

	/**
	 * 删除
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String delete(String id) {
		sysSjzdDao.delete(id);
		return "1";
	}
	
	/**
	 * 删除子字典
	 * @author:sliu 
	 * @version 创建时间：2015年6月4日 上午9:29:41 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String deleteson(String zl) {
		sysSjzdDao.deleteson(zl);
		return "1";
	}


}
