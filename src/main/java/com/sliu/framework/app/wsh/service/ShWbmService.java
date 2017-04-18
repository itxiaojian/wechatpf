package com.sliu.framework.app.wsh.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.support.PropertiesInfo;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.dao.ShBmrxxtxjlDao;
import com.sliu.framework.app.wsh.dao.ShWbmBdDao;
import com.sliu.framework.app.wsh.dao.ShWbmDao;
import com.sliu.framework.app.wsh.dao.ShWbmYbmrDao;
import com.sliu.framework.app.wsh.model.ShBmrxxtxjl;
import com.sliu.framework.app.wsh.model.ShWbm;
import com.sliu.framework.app.wsh.model.ShWbmybmr;

/**
 * 微报名
 * @author liujiansen
 * @date 2015年6月24日
 */
@Service
public class ShWbmService extends BaseBO<ShWbm>{

	protected Logger logger = LoggerFactory.getServiceLog(ShWbm.class);
	
	@Autowired
	private ShWbmDao dao;
	@Autowired
	private ShWbmBdDao bdDao;
	@Autowired
	private ShWbmYbmrDao yDao;
	@Autowired
	private ShBmrxxtxjlDao bDao;
	
	/**
	 * 根据页数查询微报名数据
	 * @author liujiansen
	 * @date 2015年6月24日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getSwzlList(HttpServletRequest request,
			HttpServletResponse response){
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
   		String bt=request.getParameter("bt");
   		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
   		List<Map<String,Object>> page=dao.getSwzlList(pages,bt);
   		if(page.size()!=0){
   			for(int i=0;i<page.size();i++){
   				Map<String,Object> map=new HashMap<String, Object>();
   				map.put("id", page.get(i).get("id"));
   				String url=this.getUrl(request, response,page.get(i).get("id")+"");
   				map.put("bmbt", page.get(i).get("bmbt"));
   				map.put("bmjs", page.get(i).get("bmjs"));
   				map.put("bmcgts", page.get(i).get("bmcgts"));
   				map.put("bmjzsj", page.get(i).get("bmjzsj"));
   				map.put("zbflogo", page.get(i).get("zbflogo"));
   				map.put("kjmp", page.get(i).get("kjmp"));
   				int res=AppUtil.timeComp(sim.format(new Date()), page.get(i).get("bmjzsj")+":00");
   				map.put("res", res);
   				map.put("url", url);
   				int bmrs=yDao.getCount(page.get(i).get("id")+"");
   				map.put("bmrs", bmrs);
   				list.add(map);
   			}
   		}
   		System.out.println(list.toString());
		return list;
	}
	
	/**
	 * 获取微报名的总条数
	 * @author liujiansen
	 * @date 2015年6月24日
	 * @param request
	 * @return
	 */
	public int getCount(HttpServletRequest request){
		String bt=request.getParameter("bt");
		return dao.getCount(bt);
	}

	/**
	 * 报名活动添加
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 */
	@Transactional
	public Long saveWbm(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String bmbt=request.getParameter("activity_name");
		String bmjs=request.getParameter("description");
		String bmcgts=request.getParameter("success_tip");
		String bmjzsjDate=request.getParameter("end_date");
		String bmjzsjHour=request.getParameter("end_hour");
		String bmjzsjMin=request.getParameter("end_minute");
		String time=bmjzsjDate+" "+bmjzsjHour+":"+bmjzsjMin;
		Date bmjzsj=new Date();
		try {
			bmjzsj=sim.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ShWbm wbm=new ShWbm();
		wbm.setBmbt(bmbt);
		wbm.setBmcgts(bmcgts);
		wbm.setBmjs(bmjs);
		wbm.setBmjzsj(bmjzsj);
		Long id=dao.save(wbm);
		return id;
	}
	
	/**
	 * 根据编号获取报名信息
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param id 编号
	 * @return
	 */
	@Transactional
	public ShWbm getWbm(String id){
		return dao.get(Long.parseLong(id));
	}

	/**
	 * 根据编号修改报名信息
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String updateWbm(HttpServletRequest request,HttpServletResponse response) {
		String str="";
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id=request.getParameter("id");
		String bmbt=request.getParameter("activity_name");
		String bmjs=request.getParameter("description");
		String bmcgts=request.getParameter("success_tip");
		String bmjzsjDate=request.getParameter("end_date");
		String bmjzsjHour=request.getParameter("end_hour");
		String bmjzsjMin=request.getParameter("end_minute");
		String time=bmjzsjDate+" "+bmjzsjHour+":"+bmjzsjMin;
		Date bmjzsj=new Date();
		try {
			bmjzsj=sim.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ShWbm wbm=new ShWbm();
		wbm.setId(Long.parseLong(id));
		wbm.setBmbt(bmbt);
		wbm.setBmcgts(bmcgts);
		wbm.setBmjs(bmjs);
		wbm.setBmjzsj(bmjzsj);
		dao.update(wbm);
		str="1";
		return str;
	}

	/**
	 * 删除报名活动
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String delete(HttpServletRequest request,
			HttpServletResponse response,String id) {
		String str="";
		//String id=request.getParameter("id");
		dao.delete(Long.parseLong(id));
		bdDao.deleteByBmId(id);
		str="1";
		return str;
	}

	/**
	 * 删除已报名人信息
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String deleteDmxx(HttpServletRequest request,HttpServletResponse response) {
		String str="";
		String id=request.getParameter("id");
		yDao.delete(Long.parseLong(id));
		bDao.delete(id);
		str="1";
		return str;
	}

	/**
	 * 保存报名信息
	 * @author liujiasen
	 * @date 2015年6月29日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String saveBm(HttpServletRequest request,HttpServletResponse response) {
		String str="";
		String size=request.getParameter("size");
		if(size!=null&&!"".equals(size)){
			String openId=request.getParameter("openId");
			String bmid=request.getParameter("wbmid");
			ShWbmybmr bmr=new ShWbmybmr();
			bmr.setBmid(Long.parseLong(bmid));
			bmr.setOpenid(openId);
			Long id=yDao.save(bmr);
			int num=Integer.parseInt(size)+1;
			for(int i=1;i<num;i++){
				String bdlx=request.getParameter("bdlx_"+i);
				String bt="";
				String nr="";
				if("1".equals(bdlx)){
					bt=request.getParameter("dhwbbt_"+i);
					nr=request.getParameter("dhwb_"+i);
				}else if("2".equals(bdlx)){
					bt=request.getParameter("wbkbt_"+i);
					nr=request.getParameter("wbk_"+i);
				}else if("3".equals(bdlx)){
					bt=request.getParameter("xlbt_"+i);
					nr=request.getParameter("xlk_"+i);
				}else if("4".equals(bdlx)){
					bt=request.getParameter("dxbt_"+i);
					int dxs=Integer.parseInt(request.getParameter("dxs_"+i))+1;
					for(int j=1;j<dxs;j++){
						nr=request.getParameter("dx_"+i+"_"+j)+"|"+nr;
					}
					nr=nr.substring(0, nr.length()-1);
				}
				ShBmrxxtxjl bmrxx=new ShBmrxxtxjl();
				bmrxx.setBt(bt);
				bmrxx.setNr(nr);
				bmrxx.setYbmrid(id);
				bDao.save(bmrxx);
			}
			str="1";
		}
		return str;
	}

	/**
	 * 根据用户的openId获取已报名人的信息
	 * @author liujiasen
	 * @date 2015年6月29日
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> getBmr(String openId) {
		return yDao.getBmr(openId);
	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年6月29日
	 * @param request
	 * @param response
	 * @return
	 */
	public String getUrl(HttpServletRequest request,HttpServletResponse response,String id) {
		
		String result = "http://wx.zs-si.com:8080/wxpt/wsh/ShWbm/toWbmView?id="+id;//进入微报名

        try {
                result = java.net.URLEncoder.encode(result,"utf-8");
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        }
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result
        		+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		return url;
	}
}
