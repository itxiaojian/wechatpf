package com.sliu.framework.app.sys.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxUserInfo;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.sys.model.SysWxyh;

/**
 * 微信用户
 * @author liujiasen
 * @date 2015年5月18日
 */
@Service
public class SysWxyhBO {

	@Autowired
	private SysWxyhDao sysWxyhDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	@Autowired
	public HttpServletRequest request;
	
	@Transactional
	public String save(String username,String password,String openId){
		String str="0";
		password=md5PasswordEncoder.encodePassword(password,null);
//		password=md5PasswordEncoder.encodePassword(password,username);
		String accessToken = WeixinCacheUtils.getAccessToken();//获取微信公众号的accessToken
		WxUserInfo wxUserInfo= WeixinUtils.getUserInfo(accessToken, openId);//根据openId和公众号Token获取关注用户的基本信息
		List<Map<String,Object>> list = sysWxyhDao.getYh(username, password);
		List<Map<String,Object>> wx = sysWxyhDao.getWxyh(openId);
		List<Map<String,Object>> tyyh = sysWxyhDao.getBdyh(openId);
//		List<Map<String,Object>> wxUser = sysWxyhDao.getWxUser(openId);
//		if(wxUser.size()!=0){
			SysWxyh wxyh=new SysWxyh();
			if(tyyh.size()==0){
				if(list.size()!=0){
					String jsbh=list.get(0).get("jsbh")+"";
					if("".equals(jsbh)){
						str="4";
					}else{
						str="1";
						if(wx.size()==0){
							wxyh.setCjsj(new Date());
							wxyh.setFzid(wxUserInfo.getGroupId()+"");
							wxyh.setWxh(openId);
							wxyh.setWxnc(wxUserInfo.getNickname());
							wxyh.setYhid(list.get(0).get("yhbh")+"");
							wxyh.setXb(wxUserInfo.getSex());
							wxyh.setYhyy(wxUserInfo.getULanguage());
							wxyh.setYhszcs(wxUserInfo.getCity());
							wxyh.setYhszgj(wxUserInfo.getCountry());
							wxyh.setYhszsf(wxUserInfo.getProvince());
							wxyh.setYhtx(wxUserInfo.getHeadimgurl());
							wxyh.setUnionid(wxUserInfo.getUnionid());
							wxyh.setYhbzm(wxUserInfo.getMemo());
							wxyh.setZt(1);
							sysWxyhDao.save(wxyh);
						}else{
							str="2";
						}
					}
				}
			}else{
				str="3";
			}
//		}else{
//			str="5";
//		}
		return str;
	}

	public Pagination<Map<String, Object>> getWxyhList(Integer start,
			Integer limit, String xm, String wxh) {
		return sysWxyhDao.getWxyhList(start, limit, xm, wxh);
	}

	public void update(String id,String zt) {
		if(zt.equals("1")){
			zt="2";
		}else{
			zt="1";
		}
		sysWxyhDao.update(id,zt);
	}
	
	@Transactional
	public void delete(String[] ids) {
		for(int i=0;i<ids.length;i++){
			sysWxyhDao.delete(ids[i]);
		}
	}
}
