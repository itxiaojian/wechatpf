package com.sliu.framework.app.wxtuwen.service;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.company.dao.CompanyDao;
import com.sliu.framework.app.model.WxSucaiInfo;
import com.sliu.framework.app.model.WxSucaiMpnews;
import com.sliu.framework.app.wxtuwen.dao.WxSucaiMpnewsDao;

/**
 * 微信图文消息 Service
 * @author zhangyi
 *
 */
@Service
public class WxSucaiMpnewsService {
	@Autowired
    private WxSucaiMpnewsDao wxSucaiMpnewsDao;
	
	//公司微信平台调用dao
	@Autowired
    private CompanyDao companyDao;
	
	/**
	 * 保存图文详细信息
	 * @param entity
	 * @return
	 */
	@Transactional
	public Long insertEntity(WxSucaiMpnews entity){
//		if(1 == entity.getShowCoverPic()){		
//		}if(0 == entity.getShowCoverPic()){		
//		}
		return wxSucaiMpnewsDao.save(entity);
	}
	
	
	/**
	 * 根据id获得图文消息详细信息
	 * @param mpnewsId
	 * @return
	 */
	@Transactional
	public WxSucaiMpnews getEntity(Long mpnewsId){
		return wxSucaiMpnewsDao.get(mpnewsId);
	}
	
	/**
	 * 修改微信图文消息详细信息
	 * @param entity
	 */
	@Transactional
	public void updateEntity(WxSucaiMpnews entity){
		
			wxSucaiMpnewsDao.update(entity);
	}
	
	
	/**
	 * 删除图文消息详细信息
	 * @param id
	 * @return
	 */
	@Transactional
	public WxSucaiMpnews deleteSucaiMpnews(Long id){
		return wxSucaiMpnewsDao.delete(id);
	}
	
	
	/**
	 * 获得图文素材详细信息 
	 * @param scid  素材编号
	 * @return
	 */
	public List<Map<String,Object>> getSucaiMpnews(Long scid){
		return wxSucaiMpnewsDao.queryByName(scid);
	}
	
}
