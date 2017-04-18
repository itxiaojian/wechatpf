package com.sliu.framework.app.wxutil.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wxutil.dao.WxShakeInfoDao;
import com.sliu.framework.app.wxutil.dao.WxYhfzDao;
import com.sliu.framework.app.wxutil.dao.WxYhfzgxDao;
import com.sliu.framework.app.wxutil.model.WxShakeInfo;
import com.sliu.framework.app.wxutil.model.WxYhfz;
import com.sliu.framework.app.wxutil.model.WxYhfzgx;

/**
 * 微用户分组
 * @author : zhangyi
 * @version 创建时间：2016年3月23日 下午4:15:28
 */
@Service
public class WxYhfzService {

	@Autowired
	private WxShakeInfoDao wxShakeInfoDao;
	
	@Autowired
	private WxYhfzDao wxYhfzDao;
	
	@Autowired
	private WxYhfzgxDao wxYhfzgxDao;

	@Transactional
	public WxShakeInfo test(String ticket) {
		WxShakeInfo entity = WeixinUtils.getShakeInfo(ticket, 1);
		
		return entity;
	}

	/**
	 * 查询自己添加的用户分组
	 * @author:zhangyi 
	 * @version 创建时间：2016年3月23日 下午4:11:40 
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> ownerpageList(Integer start,
			Integer limit) {
		return wxYhfzDao.ownerpageList(start,limit);
	}

	/**
	 * 添加用户分组
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月22日 下午3:45:16 
	 * @param entity
	 */
	@Transactional
	public void save(WxYhfz entity,String toUsers) {
		if(toUsers!=null&&!"".equalsIgnoreCase(toUsers)){
			String [] list = toUsers.split(",");
			SysYh user = AppUtil.getCurrentUser();
			entity.setCjr(user.getUsername());
			entity.setTjsj(new Date());
			Long fzid = wxYhfzDao.save(entity);
			//循环保存用户分组人员信息
			for(int i = 0;i<list.length;i++){
				WxYhfzgx menber = new WxYhfzgx();
				menber.setFzid(fzid);
				menber.setRybh(list[i]);
				wxYhfzgxDao.save(menber);
			}
		}
	}

	/**
	 * 修改
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月28日 下午3:08:59 
	 * @param entity
	 * @param toUser
	 */
	@Transactional
	public void update(WxYhfz entity, String toUsers) {
		if(toUsers!=null&&!"".equalsIgnoreCase(toUsers)){
			Long fzid = entity.getId();
			WxYhfz oldmeet = wxYhfzDao.get(fzid);
			oldmeet.setFzmc(entity.getFzmc());
			oldmeet.setBz(entity.getBz());
			wxYhfzgxDao.deleteByFzId(fzid);
			String [] list = toUsers.split(",");
			wxYhfzDao.update(oldmeet);
			//循环保存用户分组人员信息
			for(int i = 0;i<list.length;i++){
				WxYhfzgx menber = new WxYhfzgx();
				menber.setFzid(fzid);
				menber.setRybh(list[i]);
				wxYhfzgxDao.save(menber);
			}
		}
	}

	/**
	 * 获取用户分组签到情况
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月25日 下午4:54:50 
	 * @param start
	 * @param limit
	 * @param hyid
	 * @return
	 */
	@Transactional
	public Pagination<Map<String, Object>> menberList(Integer start,
			Integer limit, Long fzid) {
		return wxYhfzgxDao.getMenBerList(start,limit,fzid);
	}
	
	/**
	 * 结束用户分组
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月26日 下午4:55:49 
	 * @param hyid
	 */
	@Transactional
	public void over(Long hyid) {
	    Date date1 = new Date();     
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
		WxYhfz meet = wxYhfzDao.get(hyid);
		wxYhfzDao.update(meet);
	}

	/**
	 * 删除用户分组（关联删除用户分组人员）
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月28日 下午1:44:12 
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++){
			wxYhfzgxDao.deleteByFzId(ids[i]);
			wxYhfzDao.delete(ids[i]);
		}
	}

	/**
	 * 获取自己的分组
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月26日 下午3:04:23 
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getowerlist() {
		return wxYhfzDao.getowerlist();
	}

	/**
	 * 获取分组的用户
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月26日 下午3:08:05 
	 * @param fzid
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getmenberlist(Long fzid) {
		return wxYhfzgxDao.getmenberlist(fzid);
	}
	
}
