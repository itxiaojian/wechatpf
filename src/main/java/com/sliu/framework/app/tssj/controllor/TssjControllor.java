package com.sliu.framework.app.tssj.controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.tssj.service.TjsjService;
import com.sliu.framework.app.util.ResponseData;

/** 
 * @author:zhangyi 
 * @version 创建时间：2015年9月2日 下午4:31:50 
 * 类说明 
 */
@Controller
@RequestMapping(value = "/tjsj/tb")
public class TssjControllor extends BaseController{
	
	@Autowired
	private TjsjService tjsjService;
	
	/**
	 * 数据同步
	 * @author liujiansen
	 * @date 2015年9月10日
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/sjtb", method = RequestMethod.POST)
	@ResponseBody
	public String sjtb(String type) {
		String str="";
		tjsjService.saveTssj(type);
		str="1";
		return str;
	}
	
	/**
	 * 新生数据同步
	 * @author:zhangyi 
	 * @version 创建时间：2016年8月15日 下午2:23:01 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/xssjtb", method = RequestMethod.POST)
	@ResponseBody
	public String xssjtb() {
		String str="";
		tjsjService.saveXsTssj();
		str="1";
		return str;
	}
	
	/**
	 * 新生数据同步
	 * @author:chenhui
	 * @version 创建时间：2016年9月1日  
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/xssjztb")
    @ResponseBody
	  public ResponseData xssjztb() {
		tjsjService.saveXsTssj();
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 数据同步
	 * @author:chenhui
	 * @version 创建时间：2016年9月1日  
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/sjztb")
    @ResponseBody
	  public ResponseData sjztb(String type) {
		tjsjService.saveTssj(type);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * ad数据同步
	 * @author:zhangyi 
	 * @version 创建时间：2015年10月23日 上午9:46:08 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/adsjtb", method = RequestMethod.POST)
	@ResponseBody
	public String adsjtb(String type) {
		String str="";
		str = tjsjService.saveAdTssj(type);
		return str;
	}
}
