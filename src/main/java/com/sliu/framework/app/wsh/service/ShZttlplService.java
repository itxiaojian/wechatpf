package com.sliu.framework.app.wsh.service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.model.WxUserInfo;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.dao.ShZttlplDao;
import com.sliu.framework.app.wsh.model.ShZttlpl;

/**
 * 主页——专题讨论评论
 * 
 * @author zhangyan
 * @version 创建时间：2016年7月14日 
 */
@Service
public class ShZttlplService extends BaseBO<ShZttlpl> {

	@Autowired
	private ShZttlplDao shZttlplDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	

	/**
	 * 专题评论
	 * 
	 * @author zhangyan
	 * @version 创建时间：2016年7月20日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String savepl(String plnr, Long id, String openId) {
		ShZttlpl entity = new ShZttlpl();
		SysYh yh = AppUtil.getCurrentUser();
		String accessToken = WeixinCacheUtils.getAccessToken();// 获取微信公众号的accessToken
		WxUserInfo wxUserInfo = WeixinUtils.getUserInfo(accessToken, openId);// 根据openId和公众号Token获取关注用户的基本信息
		if (wxUserInfo != null) {
			entity.setZtid(id);
			entity.setPlnr(plnr);
			entity.setPlrwxh(openId);
			entity.setPlr(wxUserInfo.getNickname());
			entity.setPlsj(Calendar.getInstance().getTime());
		}
		shZttlplDao.save(entity);
		return "1";
	}

	/**
	 * 查看评论
	 * @author zhangyan
	 * @date 2016年7月20日
	 * @return
	 */
	public List<Map<String, Object>> getPinglun(Long id,String pages){
		
		return shZttlplDao.getPinglun(id,pages);
	}
	
	
	/**
	 * 后台-查看评论
	 * @author zhangyan
	 * @date 2016年7月20日
	 * @return
	 */
	public List<Map<String, Object>> getPinglunht(Long id,String pages){
		
		return shZttlplDao.getPinglunht(id,pages);
	}
	
	/**
	 * 总页数
	 * @author oufeng
	 * @throws UnsupportedEncodingException 
	 * @date 2015年8月10日
	 */
	public int getCount(Long id) {
		return shZttlplDao.getCount(id);
	}
	
}
