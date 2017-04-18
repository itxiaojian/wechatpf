package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.wfw.dao.ZsTxlDao;

@Service
public class ZsTxlService{
	
	@Autowired
	private ZsTxlDao zsTxlDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	/**
	 *  查看通讯录信息
	 * @author   wangxiangyang
	 * @version 创建时间：2016年6月12日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTxlList1(String openId){
		return zsTxlDao.getTxlList1(openId);
	}	
	
	/**
	 *  查看通讯录信息
	 * @author   wangxiangyang
	 * @version 创建时间：2016年6月12日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTxlList(String txlxx,String openId){
		return zsTxlDao.getTxlList(txlxx,openId);
	}
}
