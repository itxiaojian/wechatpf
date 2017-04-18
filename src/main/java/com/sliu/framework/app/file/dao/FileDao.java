package com.sliu.framework.app.file.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.common.dao.jdbc.NamedParameterJdbcPager;
import com.sliu.framework.app.file.model.SysFjgl;

/** 
 * @author:zhangyi 
 * @version 创建时间：2016年6月13日 下午2:20:08 
 * 附件管理
 */
@Repository
public class FileDao extends HibernateBaseDaoImpl<SysFjgl, Long>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcPager jdbcPager;
	
	/**
	 * 获取状态
	 * @author 状态
	 * @date 2015年8月07日
	 * @return
	 */
	public List<Map<String,Object>> getImgId(String fname){
		String sql=	"select id from sys_fjgl where fname='"+fname+"'";
		return jdbcTemplate.queryForList(sql);
	}

}
