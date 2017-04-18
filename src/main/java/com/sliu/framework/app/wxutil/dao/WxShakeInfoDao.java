package com.sliu.framework.app.wxutil.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.wxutil.model.WxShakeInfo;

/**
 * 周边摇一摇信息
 * @author : zhangyi
 * @version 创建时间：2016年3月22日 下午2:20:35
 */
@Repository
public class WxShakeInfoDao extends HibernateBaseDaoImpl<WxShakeInfo, Long>{

	@Autowired
	private JdbcTemplate jdbcTemplate;

}
