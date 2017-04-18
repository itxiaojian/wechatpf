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
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wsh.dao.ShWsqDao;
import com.sliu.framework.app.wsh.dao.ShWsqcyrDao;
import com.sliu.framework.app.wsh.dao.ShWsqfbxxDao;
import com.sliu.framework.app.wsh.model.ShWsq;

/**
 * 微上墙
 * @author liujiasen
 * @date 2015年7月7日
 */
@Service
public class ShWsqService extends BaseBO<ShWsq>{

	protected Logger logger = LoggerFactory.getServiceLog(ShWsq.class);
	
	@Autowired
	private ShWsqDao dao;
	@Autowired
	private ShWsqcyrDao cDao;
	@Autowired
	private ShWsqfbxxDao fDao;
	
	/**
	 * 根据页数查询微上墙数据
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 */
	public List<Map<String,Object>> getWsqList(HttpServletRequest request,
			HttpServletResponse response){
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
   		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
   		List<Map<String,Object>> page=dao.getWsqList(pages);
   		if(page.size()!=0){
   			for(int i=0;i<page.size();i++){
   				Map<String,Object> map=new HashMap<String, Object>();
   				map.put("id", page.get(i).get("id"));
   				String url=this.getUrl(request, response,page.get(i).get("id")+"");
   				map.put("hdmc", page.get(i).get("hdmc"));
   				map.put("logo", page.get(i).get("logo"));
   				map.put("sqpf", page.get(i).get("sqpf"));
   				map.put("kssj", page.get(i).get("kssj"));
   				map.put("jzsj", page.get(i).get("jzsj"));
   				map.put("bz", page.get(i).get("bz"));
   				map.put("url", url);
   				int res=AppUtil.timeComparison(page.get(i).get("kssj")+":00", sim.format(new Date()), page.get(i).get("jzsj")+":00");
   				map.put("res", res);
   				int cyrs=cDao.getCyrCount(page.get(i).get("id")+"");
   				int fbxxs=fDao.getFbxxCount(page.get(i).get("id")+"");
   				map.put("cyrs", cyrs);
   				map.put("fbxxs", fbxxs);
   				list.add(map);
   			}
   		}
		return list;
	}
	
	/**
	 * 获取微上墙的总条数
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @return
	 */
	public int getCount(HttpServletRequest request){
		return dao.getCount();
	}

	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	public String getUrl(HttpServletRequest request,HttpServletResponse response,String id) {
		
		String result = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toWsqYhfb?id="+id;//进入微报名

        try {
                result = java.net.URLEncoder.encode(result,"utf-8");
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        }
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PropertiesInfo.appId+"&redirect_uri="+result
        		+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		return url;
	}

	/**
	 * 微上墙活动添加
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public void saveWsq(HttpServletRequest request, HttpServletResponse response) {
		SysYh user=AppUtil.getCurrentUser();
		String hdmc=request.getParameter("name");
		String logo=request.getParameter("logo");
		String sqpf=request.getParameter("skin_id");
		String kssj=request.getParameter("startdate")+" "+
					request.getParameter("starthour")+":"+request.getParameter("startminute")+":00";
		String jzsj=request.getParameter("enddate")+" "+
				request.getParameter("endhour")+":"+request.getParameter("endminute")+":00";
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ShWsq sq=new ShWsq();
		sq.setBz(user.getUsername());
		sq.setHdmc(hdmc);
		sq.setSqpf(sqpf);
		sq.setLogo(logo);
		try {
			sq.setKssj(sim.parse(kssj));
			sq.setJzsj(sim.parse(jzsj));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(sq.getHdmc()!=null&&sq.getKssj()!=null&&sq.getJzsj()!=null){
			dao.save(sq);
		}
	}

	/**
	 * 检查当前选择的时间段内是否有其他上墙活动
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public ResponseData check(HttpServletRequest request, HttpServletResponse response) {
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		String id=request.getParameter("id");
		String str="";
		List<Map<String,Object>> wsq=null;
		if(id!=null&&!"".equals(id)){
			wsq=dao.getWsqNotById(start,end,id);
		}else{
			wsq=dao.getWsqByTime(start,end);
		}
		ResponseData data=null;
		if(wsq.size()!=0){
			str=wsq.get(0).get("hdmc")+"";
			data=new ResponseData(true, str);
		}else{
			data=new ResponseData(true, str);
		}
		return data;
	}

	/**
	 * 删除上墙活动
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param parameter
	 */
	@Transactional
	public String delete(String id) {
		String str="";
		dao.delete(Long.parseLong(id));
		str="1";
		return str;
	}
	
	/**
	 * 获取微上墙信息
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param id
	 * @return
	 */
	@Transactional
	public ShWsq getEntity(Long id){
		return dao.get(id);
	}

	/**
	 * 微上墙信息修改
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param request
	 * @param response
	 */
	@Transactional
	public void updateWsq(HttpServletRequest request,HttpServletResponse response) {
		SysYh user=AppUtil.getCurrentUser();
		String hdmc=request.getParameter("name");
		String id=request.getParameter("wsqid");
		String logo=request.getParameter("logo");
		String sqpf=request.getParameter("skin_id");
		String kssj=request.getParameter("startdate")+" "+
					request.getParameter("starthour")+":"+request.getParameter("startminute")+":00";
		String jzsj=request.getParameter("enddate")+" "+
				request.getParameter("endhour")+":"+request.getParameter("endminute")+":00";
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ShWsq sq=new ShWsq();
		if(sq.getHdmc()!=null&&sq.getKssj()!=null&&sq.getJzsj()!=null){
			sq.setBz(user.getUsername());
			sq.setHdmc(hdmc);
			sq.setSqpf(sqpf);
			sq.setLogo(logo);
			try {
				sq.setKssj(sim.parse(kssj));
				sq.setJzsj(sim.parse(jzsj));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sq.setId(Long.parseLong(id));
			dao.update(sq);
		}
	}

}
