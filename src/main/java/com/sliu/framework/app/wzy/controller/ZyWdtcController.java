package com.sliu.framework.app.wzy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.model.WxUserInfo;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.dao.SysWxyhDao;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.model.ZyCdpz;
import com.sliu.framework.app.wzy.model.ZyTcbz;
import com.sliu.framework.app.wzy.model.ZyTchf;
import com.sliu.framework.app.wzy.model.ZyWdtc;
import com.sliu.framework.app.wzy.service.ZyWdtcService;

/**
 * 主页——我的吐槽
 * 
 * @author duanpeijun
 * @version 创建时间：2015年6月8日 下午4:43:15
 */
@Controller
@RequestMapping("/wzy/ZyWdtc")
public class ZyWdtcController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZyWdtcController.class);

	@Autowired
	private ZyWdtcService zyWdtcService;
	@Autowired
	private SysWxyhDao sysWxyhDao;

	/**
	 * 功能：点击主菜单"我的吐槽"后显示的列表
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月8日 下午4:48:42
	 * @return
	 */
	@RequestMapping(value = "/wdtcPage")
	public ModelAndView openPage() {
		ModelAndView modelAndView = new ModelAndView("/wzy/zyWdtcList");
		return modelAndView;
	}

	/**
	 * 分页查询 我的吐槽
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月8日 下午4:53:31
	 * @param start
	 * @param limit
	 * @param zt
	 * @param tcbt
	 * @return
	 */
	@RequestMapping(value = "/getWdtcList")
	@ResponseBody
	public Pagination<Map<String, Object>> getWdtcList(Integer start,
			Integer limit, String tclx) {
		Pagination<Map<String, Object>> list = zyWdtcService.getWdtcList(start,
				limit,tclx);
		return list;
	}
	
	@RequestMapping(value = "/getLx")
	@ResponseBody
	public List<Map<String,Object>> getLx(){
		return zyWdtcService.getLx();
	}

	/**
	 * 我的吐槽,详情页面
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月8日 下午4:53:31
	 * @return
	 */
	@RequestMapping(value = "/zyWdtcDetail")
	@ResponseBody
	public ModelAndView zyWdtcDetail(String openId,HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		String url = "";
		List<Map<String,Object>> user = sysWxyhDao.getWxyh(openId);
		if(user.size()!=0){
			url = "/wzy/zyWdtcDetail";
		}else{
			url="/sys/SysWxyh/wxbd";
			modelAndView.addObject("url","/wzy/ZyWdtc/zyWdtcDetail");
		}
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){
			modelAndView = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = zyWdtcService.getList(request);
		List<Map<String, Object>> list2 = zyWdtcService.getHuati();
		List<Map<String, Object>> list3 = zyWdtcService.getZanshuliang();
		List<Map<String, Object>> yhid = zyWdtcService.getYh(openId);
		modelAndView.addObject("yhid", yhid);

		for (Map<String, Object> map : list) {
			Long id = Long.parseLong(map.get("id").toString());

			// 根据吐槽的ID获取回复的内容
			List<Map<String, Object>> listHF = zyWdtcService.getHfList(id);
			
			map.put("listHF", listHF);
			list1.add(map);
		}
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("list", list1);
		modelAndView.addObject("list2", list2);
		modelAndView.addObject("zs", list2.get(0).get("size"));
		modelAndView.addObject("list3", list3);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("size",list.size());
		modelAndView.setViewName(url);
		}

		return modelAndView;
	}

	/**
	 * 加载更多查询借阅信息
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/zyWdtcLoadmore")
	@ResponseBody
   	public List<List<Map<String,Object>>> zyWdtcLoadmore(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<List<Map<String,Object>>> newlist=new ArrayList<List<Map<String,Object>>>();
		String url = "";

		url = "/wsh/ShBbq/shBbqDetail";
		String pages = request.getParameter("pages");
		String openId = request.getParameter("openId");
		if(pages == null){
			pages = "1";
		}
		List<Map<String, Object>> list = zyWdtcService.getList(request);
		List<Map<String, Object>> listHF = zyWdtcService.getHfList1();
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("size",list.size());
		modelAndView.setViewName(url);
		newlist.add(list);
		newlist.add(listHF);
   		return newlist;
   	}
	
	/**
	 * 吐槽发布
	 * @author:duanpeijun 
	 * @version 创建时间：2015年6月18日 上午10:37:50 
	 * @param tcnr
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/doTucao")
	@ResponseBody
	public ResponseData save(String tcnr, String openId) {
		String url = "";
		ZyWdtc entity = new ZyWdtc();
		String accessToken = WeixinCacheUtils.getAccessToken();// 获取微信公众号的accessToken
		WxUserInfo wxUserInfo = WeixinUtils.getUserInfo(accessToken, openId);// 根据openId和公众号Token获取关注用户的基本信息
		if (wxUserInfo != null) {
			entity.setTcr(openId);
			entity.setTcrxm(wxUserInfo.getNickname());
			entity.setTcnr(tcnr);
			entity.setTcsj(Calendar.getInstance().getTime());
			entity.setTxdz(wxUserInfo.getHeadimgurl());
			entity.setBzcs(0);
			entity.setHfcs(0);
			entity.setTclx(1);
//			entity.setZt("1");
		}

		String result = zyWdtcService.saveWdtc(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}

	/**
	 * 吐槽回复
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月16日
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doTcreply")
	@ResponseBody
	public ResponseData Reply(String hfnr, Long id, String openId) {

		ZyTchf entity = new ZyTchf();
		String accessToken = WeixinCacheUtils.getAccessToken();// 获取微信公众号的accessToken
		WxUserInfo wxUserInfo = WeixinUtils.getUserInfo(accessToken, openId);// 根据openId和公众号Token获取关注用户的基本信息
		if (wxUserInfo != null) {
			entity.setTczj(id);
			entity.setHfnr(hfnr);
			entity.setHfr(openId);
			entity.setHfrxm(wxUserInfo.getNickname());
			entity.setHfsj(Calendar.getInstance().getTime());
		}
		String result = zyWdtcService.saveTchf(entity, id);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}

	/**
	 * 点赞
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doTczhan")
	@ResponseBody
	public ResponseData DoTCZhan(Long id,String openId) {

		String accessToken = WeixinCacheUtils.getAccessToken();//获取微信公众号的accessToken
		WxUserInfo wxUserInfo= WeixinUtils.getUserInfo(accessToken, openId);//根据openId和公众号Token获取关注用户的基本信息
		List list = zyWdtcService.getzan(id,openId);
		if (list.size() > 0) {
			return ResponseData.FAILED_NO_DATA;
		} else {
			ZyTcbz entity = new ZyTcbz();
			entity.setTczj(id);
			entity.setZr(openId);
			if(wxUserInfo!=null){
				entity.setZrxm(wxUserInfo.getNickname());
			}
			String result = zyWdtcService.saveDianzan(entity, id);
			if (!"1".equalsIgnoreCase(result)) {
				return ResponseData.FAILED_NO_DATA;
			}
			return ResponseData.SUCCESS_NO_DATA;
		}
	}

	/**
	 * 后台：管理员删除我的吐槽
	 * 
	 * @author duanpeijun
	 * @version 创建时间：2015年6月9日 上午9:46:24
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids) {

		zyWdtcService.deleteWdtc(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 前台：我的吐槽页面“删除”按钮
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteWdtcList")
	@ResponseBody
	public ResponseData deleteWdtcList(Long id,HttpServletRequest request){
		zyWdtcService.deleteWdtcList(id);
		String openId = request.getParameter("openId");
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	
	/**
	 * @author   wangxiangyang
	 * @version 创建时间：2016年9月29日
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getBbqList")
	@ResponseBody
	public Pagination<Map<String, Object>> getZpqList(Integer start,Integer limit){
		
		return zyWdtcService.getBbqList(start, limit);
		
	}
	
	/**
	 * 
	 * @author   wangxiangyang
	 * @version 创建时间：2016年9月29日
	 * @return
	 */
	@RequestMapping(value = "/toBbq")
	public ModelAndView toBbq(){
		ModelAndView modelAndView = new ModelAndView("/wsh/ShBbq/toBbq");
		return modelAndView;
	}
	
	/**
	 * 表白墙,详情页面
	 * @author duanpeijun
	 * @date 2015年7月13日
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/shBbqDetail")
	@ResponseBody
	public ModelAndView shBbqDetail(String openId,HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		String url = "";

		url = "/wsh/ShBbq/shBbqDetail";
		String pages = request.getParameter("pages");
		if(pages == null){
			pages = "1";
		}
		if(openId == null || "".equalsIgnoreCase(openId)){
			modelAndView = AppUtil.runWx(openId);
		}
		else{
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = zyWdtcService.getBbq(request);
		List<Map<String, Object>> yhid = zyWdtcService.getYh(openId);
		modelAndView.addObject("yhid", yhid);
		for (Map<String, Object> map : list) {
			Long id = Long.parseLong(map.get("id").toString());
			// 根据吐槽的ID获取回复的内容
			List<Map<String, Object>> listHF = zyWdtcService.getHfList(id);
			map.put("listHF", listHF);
			list1.add(map);
		}
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("list", list1);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("size",list.size());
		modelAndView.setViewName(url);
		}

		return modelAndView;
	}
	
	/**
	 * 加载更多查询借阅信息
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ShBbqLoadmore")
	@ResponseBody
   	public List<List<Map<String,Object>>> ShBbqLoadmore(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<List<Map<String,Object>>> newlist=new ArrayList<List<Map<String,Object>>>();
		String url = "";

		url = "/wsh/ShBbq/shBbqDetail";
		String pages = request.getParameter("pages");
		String openId = request.getParameter("openId");
		if(pages == null){
			pages = "1";
		}
		List<Map<String, Object>> list = zyWdtcService.getBbq(request);
		List<Map<String, Object>> listHF = zyWdtcService.getHfList1();
		modelAndView.addObject("openId", openId);
		modelAndView.addObject("pages",pages);
		modelAndView.addObject("size",list.size());
		modelAndView.setViewName(url);
		newlist.add(list);
		newlist.add(listHF);
   		return newlist;
   	}

	/**
	 * 表白发布
	 * @author duanpeijun
	 * @date 2015年7月13日
	 * @param tcnr
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/doBiaoBai")
	@ResponseBody
	public ResponseData saveBbq(String tcnr, String openId,String nm) {
		ZyWdtc entity = new ZyWdtc();
		String accessToken = WeixinCacheUtils.getAccessToken();// 获取微信公众号的accessToken
		WxUserInfo wxUserInfo = WeixinUtils.getUserInfo(accessToken, openId);// 根据openId和公众号Token获取关注用户的基本信息
		if (wxUserInfo != null) {
			entity.setTcr(openId);
			if("1".equals(nm)){
				entity.setTcrxm(wxUserInfo.getNickname());
			}else if("0".equals(nm)){
				entity.setTcrxm("***");
			}
			entity.setTcnr(tcnr);
			entity.setTcsj(Calendar.getInstance().getTime());
			entity.setTxdz(wxUserInfo.getHeadimgurl());
			entity.setBzcs(0);
			entity.setHfcs(0);
			entity.setTclx(2);
//			entity.setZt("1");
		}

		String result = zyWdtcService.saveBbq(entity);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 前台：表白墙页面“删除”按钮
	 * @author duanpeijun
	 * @date 2015年7月30日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteBbqList")
	@ResponseBody
	public ResponseData deleteBbqList(Long id,HttpServletRequest request){
		zyWdtcService.deleteBbqList(id);
		String openId = request.getParameter("openId");
		return ResponseData.SUCCESS_NO_DATA;
	}
	/**
	 * 根据字典种类找出字典
	   @author:wangchunlin 
	 * @version 创建时间：2016年1月12日  
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getDicByZt", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicByZt(String zdzl) {
		return zyWdtcService.getDicByZt("zt");
	}
	/**
	 * 审批
	 * @author:wangchunlin 
	 * @version 创建时间：2016年1月21日  
	 * @return
	 */
	@RequestMapping(value = "/updateTestProcess")
	@ResponseBody
	public ResponseData update(ModelMap model, ZyWdtc entity,String id,
			HttpServletRequest request, HttpServletResponse response) {
		ZyWdtc oldEntity = zyWdtcService.getWdtcById(Long.parseLong(id));
	      if (oldEntity == null) {
	          return new ResponseData(false, "记录不存在");
	      }
	      oldEntity.setTcnr(entity.getTcnr());
	      oldEntity.setZt(entity.getZt());
	      zyWdtcService.update(oldEntity);
		return ResponseData.SUCCESS_NO_DATA;
	}
}
