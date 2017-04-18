package com.sliu.framework.app.mass.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.model.WxSendallLog;

@Repository
public class WxSendallLogDao extends HibernateBaseDaoImpl<WxSendallLog, Long>{

	@Transactional
	public void saveWxSendallLog(WxSendallLog sendallLog) {
		this.save(sendallLog);
	}
	
	@Transactional
	public void updateSendallLog(String fromUserName, String toUserName, String Status, Integer TotalCount,
			Integer FilterCount, Integer SentCount, Integer ErrorCount, String MsgID) {
		String hql = "update WxSendallLog t set t.fromusername = ?, t.tousername = ?, t.status = ?, t.totalcount = ?," +
				"t.filtercount = ?, t.sentcount = ?, t.errorcount = ? where t.msgid = ?";
		this.bulkUpdate(hql, fromUserName, toUserName, Status, TotalCount, FilterCount, SentCount, ErrorCount, MsgID);
		
	}

}
