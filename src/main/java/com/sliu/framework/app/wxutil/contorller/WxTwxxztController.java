package com.sliu.framework.app.wxutil.contorller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Encoder;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.SignUtil;
import com.sliu.framework.app.support.random.RandomUtil;
import com.sliu.framework.app.support.weixin.WeixinCacheUtils;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxutil.model.WxTemplate;
import com.sliu.framework.app.wxutil.model.WxTwxxnr;
import com.sliu.framework.app.wxutil.model.WxTwxxzt;
import com.sliu.framework.app.wxutil.service.WxTwxxztService;


/**
 * 图文消息主体
 * @author : zhangyi
 * @version 创建时间：2016年9月2日 下午5:29:07
 */
@SuppressWarnings("restriction")
@Controller
@RequestMapping("/weixin/twxx")
public class WxTwxxztController  extends BaseController{

	protected final transient Logger logger = LoggerFactory.getPresentationLog(WxTemplate.class);
	
	@Autowired
	private WxTwxxztService wxTwxxztService;
	
	/**
	 * 进入图文消息主体界面
	 * @author:zhangyi 
	 * @version 创建时间：2016年9月6日 下午2:37:44 
	 * @return
	 */
	@RequestMapping(value = "/twxxztPage")
	public ModelAndView openPage(){
		ModelAndView modelAndView = new ModelAndView("/weixin/twxx/twxxztPage");
		return modelAndView;
	}
	
	
	/**
	 * 圖文信息列表
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/twxxDetail")
	@ResponseBody
	public ModelAndView twxxDetail(int id){
		ModelAndView mav = new ModelAndView("/weixin/twxx/twxxzt");
		List<Map<String, Object>> bts = wxTwxxztService.getTwxxbt(id);
		if (bts.size() != 0) {
			mav.addObject("bts", bts);
		}
		return mav;
	}
	
	
	/**
	 * 圖文信息内容
	 * 
	 * @author chenhui
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/twxxnr")
	@ResponseBody
	public ModelAndView getTwxxnr(int id)  {
		ModelAndView mav=new ModelAndView("/weixin/twxx/twxxnr");
				List<Map<String, Object>> list = wxTwxxztService.getTwxxnr(id);
		   		if(list.size()!=0){
		   			mav.addObject("list", list);
		   		}
		   		return mav;		
		}




	/**
	 * 获取图文消息内容
	 * @author:zhangyi 
	 * @version 创建时间：2016年9月6日 下午2:48:27 
	 * @param start
	 * @param limit
	 * @param ztid
	 * @return
	 */
	@RequestMapping(value = "/getTwxxzt")
	@ResponseBody
	public Pagination<Map<String, Object>> getTwxxzt(Integer start,Integer limit){
		
		return wxTwxxztService.getTwxxztList(start, limit);
		
	}
	
	/**
	 * 获取图文消息内容
	 * @author:zhangyi 
	 * @version 创建时间：2016年9月6日 下午2:48:27 
	 * @param start
	 * @param limit
	 * @param ztid
	 * @return
	 */
	@RequestMapping(value = "/getTwxxnrList")
	@ResponseBody
	public Pagination<Map<String, Object>> getTwxxnrList(Integer start,Integer limit,Long ztid){
		
		return wxTwxxztService.getTwxxnrList(start, limit, ztid);
		
	}
	
	/**
	 * 获取图文消息主体
	 * @author:zhangyi 
	 * @version 创建时间：2016年9月6日 下午2:48:46 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getTwxxztList")
	@ResponseBody
	public Pagination<Map<String, Object>> getTwxxztList(Integer start,Integer limit){
		
		return wxTwxxztService.getTwxxztList(start, limit);
		
	}
	
	/**
	 * 增加消息内容
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:27
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveNews")
	@ResponseBody
	public String saveNews(ModelMap model,WxTwxxnr entity,MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		String data = "";
		String filename ="";
		String fileNameU ="";
		String res = "0";
		if (!file.isEmpty()) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String date = sdf.format(new Date());		//当前日期格式化到秒
			String random = RandomUtil.generateWord();	//获得随机数
			filename = file.getOriginalFilename();
			String prefix=filename.substring(filename.lastIndexOf(".")+1);
			fileNameU = date+random+"."+prefix;//文件名称
			try {
				BASE64Encoder encoder =  new BASE64Encoder();
				// 通过base64来转化图片
				 data = encoder.encode(file.getBytes());
				 res = wxTwxxztService.saveNews(entity,data,filename,fileNameU);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/**
	 * 修改消息内容
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:29:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateNews", method = RequestMethod.POST)
	@ResponseBody
	public String update(ModelMap model,WxTwxxnr entity,MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		String data = "";
		String filename ="";
		String fileNameU ="";
		String res = "0";
		if (!file.isEmpty()) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String date = sdf.format(new Date());		//当前日期格式化到秒
			String random = RandomUtil.generateWord();	//获得随机数
			filename = file.getOriginalFilename();
			String prefix=filename.substring(filename.lastIndexOf(".")+1);
			fileNameU = date+random+"."+prefix;//文件名称
			try {
				BASE64Encoder encoder =  new BASE64Encoder();
				// 通过base64来转化图片
				 data = encoder.encode(file.getBytes());
				 res = wxTwxxztService.updateNewsAll(entity,data,filename,fileNameU);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			res = wxTwxxztService.updateNewsOnly(entity);
		}
		
		//wxTwxxztService.updateNews(entity);
		return res;
	}  
	
	/**
	 * 删除消息内容
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:43
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteNews", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData deleteNews(Long[] ids){
		wxTwxxztService.deleteNews(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	
	/**
	 * 增加消息主体
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:27
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ResponseData save(ModelMap model,WxTwxxzt entity,
			HttpServletRequest request,HttpServletResponse response){
		wxTwxxztService.save(entity);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 编辑消息主体
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:29:37
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData update(WxTwxxzt entity, String id) {
		wxTwxxztService.update(entity, id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 删除消息主体
	 * @author   zhangyi
	 * @version 创建时间：2016年4月19日 下午3:28:43
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Long[] ids){
		wxTwxxztService.delete(ids);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
}
