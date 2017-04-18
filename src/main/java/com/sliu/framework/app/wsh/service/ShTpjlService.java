package com.sliu.framework.app.wsh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.wsh.dao.ShTpjlDao;
import com.sliu.framework.app.wsh.model.ShTpjl;

/**
 * 主页——投票记录
 * 
 * @author zhangyan
 * @version 创建时间：2016年7月27日 
 */
@Service
public class ShTpjlService extends BaseBO<ShTpjl> {

	@Autowired
	private ShTpjlDao shTpjlDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	/**
	 * 根据选项id查找投票记录
	 * @author zhangyan
	 * @date 2016年7月72日
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTpjl(String xxid){
		return shTpjlDao.getTpjl(xxid);
	}
	
	/**
	 * 查看选项内容
	 * @author zhangyan
	 * @date 2016年7月25日
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getJllist(String id,String openId) {
		return shTpjlDao.getJllist(id,openId);
	}
	
	/**
	 * 投票总数
	 * @author zhangyan
	 * @date 2016年7月25日
	 * @param id
	 * @return
	 */
	public int getSum(String id){
		return shTpjlDao.getSum(id);
	}

}
