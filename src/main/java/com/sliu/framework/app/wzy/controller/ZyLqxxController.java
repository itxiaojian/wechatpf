package com.sliu.framework.app.wzy.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.logger.Logger;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.model.ZyLqxx;
import com.sliu.framework.app.wzy.service.ZyLqxxService;

/**
 * 主页--录取信息
 * @author zhangyan
 * @version 创建时间：2016年7月4日  
 */

@Controller
@RequestMapping("/wzy/ZyLqxx")
public class ZyLqxxController extends BaseController {
	
	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(ZyLqxx.class);
	
	@Autowired
	private ZyLqxxService zyLqxxService;
	
	/**
	 * 功能：点击“保存”按钮
	 * @author   zhangyan
	 * @version 创建时间：2016年7月5日  
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model,ZyLqxx entity,
			HttpServletRequest request,HttpServletResponse response){
		
		String result  = zyLqxxService.save(entity);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 修改录取信息
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月5日 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(ZyLqxx entity,Long id){
		
		String result  = zyLqxxService.update(entity,id);
		if(!"1".equalsIgnoreCase(result)){
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除录取信息
	 * @author   zhangyan
	 * @version 创建时间：2016年7月5日  
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		zyLqxxService.delete(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	
	/**
	 *  功能：跳转到录取信息详情页面
	 * zyLqxxDetail.jsp
	 * @author   zhangyan
	 * @version 创建时间：2016年7月4日 
	 * @return
	 */
	@RequestMapping(value="/zyLqxxDeatail")
	@ResponseBody
	public ModelAndView viewLqxx(HttpServletRequest request,
			HttpServletResponse response)throws UnsupportedEncodingException{
		
		ModelAndView mav = new ModelAndView("/wzy/zyLqxxDetail");
		//String openId = AppUtil.getOpenId(request, response);
		String openId = request.getParameter("openId");
		String ksh = request.getParameter("ksh");
		String sfzh=request.getParameter("sfzh");
		if((ksh!=null&&!"".equals(ksh))||(sfzh!=null&&!"".equals(sfzh))){
			List<Map<String,Object>> lqxxlist = zyLqxxService.getLqxxCx(request);
//			if (lqxxlist.size() != 0) {
				mav.addObject("lqxxlist", lqxxlist);
//			}else{
//				mav.addObject("lqxxlist1", "0");
//			}
			mav.addObject("size",lqxxlist.size());
		}
		mav.addObject("ksh", ksh);
		mav.addObject("sfzh", sfzh);
		mav.addObject("openId", openId);
		
		String jsapi_ticket = WeixinCacheUtils.getJsapiTicket();

        // 注意 URL 一定要动态获取，不能 hardcode
        //String url1 = "http://wx.zs-si.com:8080/wxpt/wsh/ShWsq/toDemo";
        HttpServletRequest httpRequest=(HttpServletRequest)request; 
        String url1 = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + httpRequest.getContextPath()      //项目名称  
                + httpRequest.getServletPath();      //请求页面或其他地址  
            //+ "?" + (httpRequest.getQueryString()); //参数 
        Map<String, String> ret = SignUtil.sign(jsapi_ticket, url1);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        mav.addObject("map", ret);
		return mav;
	}


	/**
	 * 跳转录取信息页面
	 * @author   liusong
	 * @version 创建时间：2016年7月5日
	 * @return
	 */
	@RequestMapping(value = "/LqxxPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/wzy/zyLqxxList");
		return modelAndView;
	}
	
	/**
	 * 查询录取信息list列表
	 * @author   liusong
	 * @version 创建时间：2016年7月5日  
	 * @return
	 */
	@RequestMapping(value = "/getLqxxList")
	@ResponseBody
	public Pagination<Map<String, Object>> getLqxxList(Integer start,Integer limit,String code){
		
		return zyLqxxService.getLqxxList(start, limit,code);
		
	}
	
	/** 
	 * 录取信息导出
	 * @author liusong E-mail: 2629690209@qq.com
	 * @version 创建时间：2015年11月11日 上午10:19:22 
	 * 方法说明 
	 */
	@RequestMapping(value = "/export")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,String code) throws Exception{
		zyLqxxService.exportExcel(request,response,code);
	}
	
	/** 
	 * 录取信息导入
	 * @author liusong E-mail: 2629690209@qq.com
	 * @version 创建时间：2015年11月11日 上午10:19:22 
	 * 方法说明 
	 */
	@RequestMapping(value="/upload")
    @ResponseBody
    public String upload(@RequestParam("fileData") MultipartFile fileData)
    {
        String message = zyLqxxService.importMember(fileData);
        if (message == null)
            return "{\"success\":true}";
        else
            return "{\"success\":false,\"message\":\""+message+"\"}";
    }

}
