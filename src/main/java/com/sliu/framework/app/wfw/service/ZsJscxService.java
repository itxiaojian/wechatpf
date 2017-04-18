package com.sliu.framework.app.wfw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsJscxDao;
import com.sliu.framework.app.wfw.model.ZsJscx;

/**
 * 教室查询
 * @author duanpeijun
 * @date 2015年7月8日
 *
 */
@Service
public class ZsJscxService  extends BaseBO<ZsJscx> {

protected Logger logger = LoggerFactory.getServiceLog(ZsJscx.class);
	
	@Autowired
	private ZsJscxDao ZsJscxDao;
	
	/**
	 * 查询所有教室
	 * @author duanpeijun
	 * @date 2015年7月8日
	 * @param request
	 * @return
	 *
	 */
	public List<Map<String, Object>> getJscxList(HttpServletRequest request,String jxl){
		return ZsJscxDao.getJscxList(jxl);
	}
	
	/**
	 * 查询所有教室
	 * @author duanpeijun
	 * @date 2015年7月8日
	 * @param request
	 * @return
	 *
	 */
	public List<Map<String, Object>> getAllJscxList(){
		return ZsJscxDao.getAllJscxList();
	}

	/**
	 * 根据教学楼名称查询教室
	 * @author duanpeijun
	 * @date 2015年7月8日
	 * @param jxlmc
	 * @return
	 */
	public List<Map<String, Object>> getJscxListByJxl(String jxlmc) {
		return ZsJscxDao.getJscxListByJxl(jxlmc);
	}
	
	/**
	 * 查询是否有课
	 * @author:zhangyi 
	 * @version 创建时间：2015年7月9日 上午10:58:53 
	 * @param i
	 * @param jsbh
	 * @return
	 */
	public Map<String, Object> getJscxQk(int i, String jsbh,String kcmc,String year,String xq,String week,String dsweek) {
		List<Map<String, Object>> list = ZsJscxDao.getJscxQk(i,jsbh,kcmc,year,xq,week,dsweek);
		Map<String, Object> map = new HashMap<String, Object>();
		if(list.size()>0){
			map =  list.get(0);
		}else{
			map.put("yk", 0);
		}
		map.put("js", i);
		return map;
	}
	
	/**
	 * 查询是否有课
	 * @author:zhangyi 
	 * @version 创建时间：2015年7月9日 上午10:58:53 
	 * @param i
	 * @param jsbh
	 * @return
	 */
	public List<Map<String, Object>> getJscxAll(String jsbh,String kcmc,String year,String xq,String week,String dsweek) {
		List<Map<String, Object>> list = ZsJscxDao.getJscxAll(jsbh,kcmc,year,xq,week,dsweek);
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(list.size()>0){
//			map =  list.get(0);
//		}else{
//			map.put("yk", 0);
//		}
//		map.put("js", i);
		return list;
	}
	
	/**
	 * 教学教室查询
	 * @author duanpeijun
	 * @date 2015年7月9日
	 * @return
	 */
	/*public List<Map<String,Object>> getJxjsList(){
		return shJscxDao.getJxjsList();
	}*/
	
}
