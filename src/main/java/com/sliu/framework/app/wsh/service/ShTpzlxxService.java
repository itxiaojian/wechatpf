package com.sliu.framework.app.wsh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.wsh.dao.ShTpzlxxDao;
import com.sliu.framework.app.wsh.model.ShTpzlxx;

/**
 * 投票专栏选项
 * 
 * @author zhangyan
 * @version 创建时间：2016年7月14日 
 */
@Service
public class ShTpzlxxService extends BaseBO<ShTpzlxx> {

	@Autowired
	private ShTpzlxxDao shTpzlxxDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	
	/**
	 * 插入选项
	 * @author zhangyan
	 * @date 2016年7月25日
	 * @param id
	 * @param value 选项内容
	 */
	public void addSelecter(int id,String value) {
		shTpzlxxDao.addSelecter(id,value);
	}
	
	/**
	 * 查看选项内容
	 * @author zhangyan
	 * @date 2016年7月25日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getXxlist(Long id) {
		return shTpzlxxDao.getXxlist(id);
	}
	
	/**
	 * 查看选项内容
	 * @author zhangyan
	 * @date 2016年7月25日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getXxnrlist(Long id) {
		return shTpzlxxDao.getXxnrlist(id);
	}
	
}
