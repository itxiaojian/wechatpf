//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.sbgl.contorller;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.model.SbSbxx;
import com.sliu.framework.app.sbgl.service.SbxxService;
import com.sliu.framework.app.util.ResponseData;

/**
 * 设备管理controller
 * @author : zhangyi
 * @version 创建时间：2015年8月20日 上午10:06:29
 */
@Controller
@RequestMapping("/sb/sbSbxx")
public class SbxxController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(SbxxController.class);

	@Autowired
	private SbxxService sbxxService;

	@RequestMapping(value = "/sbxxPage")
	public ModelAndView openPage() {
		ModelAndView modelAndView = new ModelAndView("/sbgl/sbgl/sbSbxxList");
		return modelAndView;
	}
	
	/**
	 * 添加设备信息
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月20日 上午10:09:51 
	 * @param model
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveSbxx", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(ModelMap model, SbSbxx entity,String sybmbh,String sbwhr,
			HttpServletRequest request, HttpServletResponse response) {

		String result = sbxxService.saveSbxx(entity,sybmbh,sbwhr);
		if (!"1".equalsIgnoreCase(result)) {
			return ResponseData.FAILED_NO_DATA;
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 根据字典种类找出字典
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getDicByLx", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicByLx(String zdzl) {
		return sbxxService.getDicByLx("sblb");
	}
	
	/**
	 * 删除功能
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月10日 上午11:22:07 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public ResponseData deleteTuWenMcInfo(Long[] ids)
    {
      for (Long id : ids) {
    	  	sbxxService.delete(id);
      }
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 报废功能
	 * @author:liujiansen
	 * @version 创建时间：2015年6月10日 上午11:22:07 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/sbBfcl")
	@ResponseBody
	public ResponseData sbBfcl(String id)
	{
		sbxxService.sbBfcl(id);
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	/**
	 * 获取设备信息列表
	 * @author:zhangyi 
	 * @version 创建时间：2015年8月20日 上午10:13:24 
	 * @param start
	 * @param limit
	 * @param sybm
	 * @param syzt
	 * @param gjz
	 * @return
	 */
	@RequestMapping(value = "/getSbxxList")
	@ResponseBody
	public Pagination<Map<String, Object>> getSbxxList(Integer start,
			Integer limit, String sybm, String syzt,String gjz,String cd) {
		Pagination<Map<String, Object>> list = sbxxService.getSbxxList(start,
				limit, sybm, syzt,gjz,cd);
		return list;
	}
  
	/**
	 * 
	 * @author   zhangyi
	 * @version 创建时间：2015年6月3日  下午4:05:34
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean
					.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!StringUtils.pathEquals(name, "class")) {
					params.put(name,
							propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@RequestMapping(value="/updatezj")
    @ResponseBody
    public ResponseData updatezj(SbSbxx entity,String id,String sybmbh,String sbwhr){
		SbSbxx oldEntity = sbxxService.getSbxxById(Long.parseLong(id));
		 if (oldEntity == null) {
	          return new ResponseData(false, "记录不存在");
	      }
		 MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
		 /* 不计提折旧 */
	      if("1".equalsIgnoreCase(entity.getZjff())){
	    	  oldEntity.setZcjz(entity.getZcyz());
	      }
	      /* 平均年限法 */
	      if("2".equalsIgnoreCase(entity.getZjff())){
	    	  BigDecimal bignum0 = new BigDecimal("1");        //b0 = 1
	    	  BigDecimal bignum1 = new BigDecimal("100");      //b1 = 100
	    	  BigDecimal bignum2 = entity.getJcl();            //b2 = 20
	    	  BigDecimal bignum3 = bignum1.subtract(bignum2); //b3 = 80
	    	  BigDecimal bignum4 = bignum3.divide(bignum1,mc);    //b4 = 0.8
	    	  int i =entity.getZjnx();                         //
	    	  BigDecimal bignum5 = new BigDecimal(i);          //b5 = 4
	    	  BigDecimal bignum6 = bignum4.divide(bignum5,mc);    //b6 = 0.2
	    	  BigDecimal bignum7 = bignum0.subtract(bignum6);  //b7 = 0.8
	    	  BigDecimal bignum8 = entity.getZcyz();           //b8 = 10000
	    	  BigDecimal bignum9 = bignum8.multiply(bignum7,mc);  //b9=8000
	    	  oldEntity.setZcjz(bignum9);
	      }
	      /* 年限总和法 */
	      if("3".equalsIgnoreCase(entity.getZjff())){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	  		  String date1 = sdf.format(new Date());
	  		  BigDecimal bignum0 = new BigDecimal(date1);     //当前日期
	  		  //System.out.println("--------------0---------->"+bignum0);
	  		  String date2 = sdf.format(entity.getGmrq());    
	  		  BigDecimal bignum1 = new BigDecimal(date2);     //购买日期
	  		  BigDecimal bignum2 = bignum0.subtract(bignum1); //尚可使用年限
	  		//System.out.println("--------------2---------->"+bignum2);
		  	  int i =entity.getZjnx();
		  	  BigDecimal bignum3 = new BigDecimal(i);         //折旧年限
	  		  BigDecimal bignum4 = bignum2.divide(bignum3,mc);//b4 = 0.5
	  		//System.out.println("--------------4---------->"+bignum4);
	  		  BigDecimal bignum5 = new BigDecimal("100");
	  		  BigDecimal bignum6 = entity.getJcl();           //折旧率
	  		  BigDecimal bignum7 = bignum5.subtract(bignum6); //bignum7 = 80
	  		//System.out.println("--------------7---------->"+bignum7);
	  		  BigDecimal b7 = bignum7.divide(bignum5,mc);     //b7 = 0.8
	  		//System.out.println("--------------b7---------->"+b7);
	  		  BigDecimal bignum8 = entity.getZcyz();          //资产原值
	  		  BigDecimal bignum9 = bignum8.multiply(b7,mc); //b9 = 8000
	  		//System.out.println("--------------9---------->"+bignum9);
	  		  BigDecimal bignum10 = bignum9.multiply(bignum4);   //b10 = 4000
	  		//System.out.println("--------------10---------->"+bignum10);
	  		  BigDecimal bignum11 = bignum8.subtract(bignum10);  //zcjz = 6000
	  		//System.out.println("--------------11---------->"+bignum11);
	          oldEntity.setZcjz(bignum11);
	      }
	      /* 双倍余额折旧法  */
	      if("4".equalsIgnoreCase(entity.getZjff())){
	    	  BigDecimal bignum0 = new BigDecimal("1");        //b0 = 1
	    	  BigDecimal bignum1 = new BigDecimal("2");        //b1 = 2
	    	  int i =entity.getZjnx();
	    	  BigDecimal bignum2 = new BigDecimal(i);          //b2 = 4
	    	  BigDecimal bignum3 = bignum1.divide(bignum2,mc);    //b3 = 0.5
	    	  BigDecimal bignum4 = bignum0.subtract(bignum3);
	    	  BigDecimal bignum5 = entity.getZcyz(); 
	    	  BigDecimal bignum6 = bignum5.multiply(bignum4,mc);
	    	  oldEntity.setZcjz(bignum6);
	      }
	      oldEntity.setZcyz(entity.getZcyz());
	      oldEntity.setGmrq(entity.getGmrq());
	      oldEntity.setZjff(entity.getZjff());
	      oldEntity.setJcl(entity.getJcl());
	      oldEntity.setZjnx(entity.getZjnx());
	      sbxxService.update(oldEntity);
	      return ResponseData.SUCCESS_NO_DATA;
	}

	@RequestMapping(value="/update")
    @ResponseBody
    public ResponseData update(ModelMap model, SbSbxx entity,String id,String sybmbh,String sbwhr)
    {
      SbSbxx oldEntity = sbxxService.getSbxxById(Long.parseLong(id));
      if (oldEntity == null) {
          return new ResponseData(false, "记录不存在");
      }
      oldEntity.setSblb(entity.getSblb());
      oldEntity.setSbmc(entity.getSbmc());
      //oldEntity.setSbbh(entity.getSbbh());
      oldEntity.setSbxh(entity.getSbxh());
      oldEntity.setSbjb(entity.getSbjb());
      oldEntity.setSybm(entity.getSybm());
      oldEntity.setSybmbh(entity.getSybmbh());
      oldEntity.setSyfw(entity.getSyfw());
      oldEntity.setSccj(entity.getSccj());
      oldEntity.setCcbh(entity.getCcbh());
      oldEntity.setCcrq(entity.getCcrq());
      oldEntity.setGmrq(entity.getGmrq());
      oldEntity.setGmjg(entity.getGmjg());
      oldEntity.setJdzq(entity.getJdzq());
      oldEntity.setSyqx(entity.getSyqx());
      oldEntity.setScjdrq(entity.getScjdrq());
      oldEntity.setXcjdrq(entity.getXcjdrq());
      oldEntity.setSyzt(entity.getSyzt());
      oldEntity.setSbwhr(sbwhr.split(",")[0]);
      oldEntity.setZjff(entity.getZjff());
      oldEntity.setZcyz(entity.getZcyz());
      oldEntity.setJcl(entity.getJcl());
      oldEntity.setZjnx(entity.getZjnx());
      sbxxService.update(oldEntity);
      return ResponseData.SUCCESS_NO_DATA;
    }
	
	/**
	 * 时间单位
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getDicByZl", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicByZl(String zdzl) {
		return sbxxService.getDicByLx("djzq");
	}
	
	/**
	 * 折旧方法
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getDicByZj", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicByZj(String zdzl) {
		return sbxxService.getDicByLx("zjff");
	}
	
	/**
	 * 使用状况
	 * @version 创建时间：2015年6月3日 下午3:34:33 
	 * @param zdzl
	 * @return
	 */
	@RequestMapping(value = "/getDicBySy", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDicBySy(String zdzl) {
		return sbxxService.getDicByLx("sbsyzk");
	}
}
