package com.sliu.framework.app.mass.dao;

import org.springframework.stereotype.Repository;

import com.sliu.framework.app.common.dao.hibernate4.HibernateBaseDaoImpl;
import com.sliu.framework.app.model.Attachment;

/**
 * 附件管理Dao
 * @author zhangyi
 *
 */
@Repository
public class AttachMentDao extends HibernateBaseDaoImpl<Attachment, Long> {
		
}
