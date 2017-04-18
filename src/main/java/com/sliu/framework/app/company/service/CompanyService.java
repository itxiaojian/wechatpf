package com.sliu.framework.app.company.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.company.dao.CompanyDao;
import com.sliu.framework.app.wzy.model.ZyXyxw;

/**
 * 公司微信平台
 * @author : zhangyi
 * @version 创建时间：2015年7月30日 下午1:43:21
 */
@Service
public class CompanyService  extends BaseBO<ZyXyxw>{

	@Autowired
	private CompanyDao companyDao;

	/**
	 * 获取公司简介
	 * @author:zhangyi 
	 * @version 创建时间：2015年7月30日 下午1:50:08 
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> gsjjDetail() {
		return companyDao.gsjjDetail();
	}
	
	/**
	 * 获取公司资质
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> gszzDetail() {
		return companyDao.gszzDetail();
	}
	
	/**
	 * 获取公司荣誉
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> gsryDetail() {
		return companyDao.gsryDetail();
	}
	
	/**
	 * 获取服务体系
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> fwtxDetail() {
		return companyDao.fwtxDetail();
	}
	
	/**
	 * 获取公司资讯列表
	 * @author duanpeijun
	 * @date 2015年8月03日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> gszxList() {
		return companyDao.gszxList();
	}
	
	/**
	 * 获取公司资讯详情
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> gszxDetail(Long id) {
		return companyDao.gszxDetail(id);
	}
	
	/**
	 * 获取解決方案列表
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> jjfaList() {
		return companyDao.jjfaList();
	}
	
	/**
	 * 获取解决方案详情
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> jjfaDetail(Long id) {
		return companyDao.jjfaDetail(id);
	}
	
	/**
	 * 获取成功案例列表（教育，政府，金融，企业，医疗）
	 * @author duanpeijun
	 * @date 2015年7月31日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> cgalList(Long ClassID) {
		return companyDao.cgalList(ClassID);
	}
	
	/**
	 * 获取成功案例详情（教育，政府，金融，企业，医疗）
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> cgalDetail(Long id,Long ClassID) {
		return companyDao.cgalDetail(id,ClassID);
	}
	
}
