package com.sliu.framework.app.wfw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsXsjzdxxDao;
import com.sliu.framework.app.wfw.model.ZsXsjzdxx;

/**
 * 奖学金信息
 * @author chenhui
 * @version 创建时间：2015年6月9日
 */
@Service
public class ZsXsjzdxxService  extends BaseBO<ZsXsjzdxx>{

	protected Logger logger = LoggerFactory.getServiceLog(ZsXsjzdxx.class);
	
	@Autowired
	private ZsXsjzdxxDao zsXsjzdxxDao;
	
	public List<Map<String,Object>> getJzdxxList(String bjmc,String openId,HttpServletRequest request,String yhbh){
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsjzdxxDao.getJzdxxList(bjmc,openId,pages,yhbh);
	}
	
	/**当前登录人的信息
	 * 
	 * @author duanpeijun
	 * @date 2015年7月15日
	 * @param gzffsj
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getGrTsjyxxBysj(String openId,HttpServletRequest request){
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXsjzdxxDao.getGrTsjyxx(openId,pages);
	}
	
	
	/**
	 * 获取个人的用户信息
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getWxyh(String openId){
		return zsXsjzdxxDao.getWxyh(openId);
	}
	
	/**
	 * 获取当前学生信息
	 * @author chenhui
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getXs(String openId){
		return zsXsjzdxxDao.getXs(openId);
	}
	
	/**
	 * 辅导员获取当前学生信息
	 * @author chenhui
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getXsxx(String xh){
		return zsXsjzdxxDao.getXsxx(xh);
	}
	
	/**
	 * 助学金信息
	 * @author chenhui
	 * @version 创建时间：2015年6月9日
	 */

		public List<Map<String,Object>> getZxxxList(String bjmc,String openId,HttpServletRequest request,String yhbh){
			String pages=request.getParameter("pages");
	   		if(pages==null||"".equals(pages)){
	   			pages="1";
	   		}
			return zsXsjzdxxDao.getZxxxList(bjmc,openId,pages,yhbh);
		}
		
		/**
		 * 贷款信息
		 * @author chenhui
		 * @version 创建时间：2015年6月9日
		 */

			public List<Map<String,Object>> getDkxxList(String bjmc,String openId,HttpServletRequest request,String yhbh){
				String pages=request.getParameter("pages");
		   		if(pages==null||"".equals(pages)){
		   			pages="1";
		   		}
				return zsXsjzdxxDao.getDkxxList(bjmc,openId,pages,yhbh);
			}
	
			/**
			 * 贷款信息
			 * @author chenhui
			 * @version 创建时间：2015年6月9日
			 */

				public List<Map<String,Object>> getJzdxxListDetail(String xh){
					
					return zsXsjzdxxDao.getJzdxxListDetail(xh);
				}

				/**
				 * 助学金信息
				 * @author chenhui
				 * @version 创建时间：2015年6月9日
				 */

					public List<Map<String,Object>> getZxxxListDetail(String xh){
						
						return zsXsjzdxxDao.getZxxxListDetail(xh);
					}

					/**
					 * 助学金信息
					 * @author chenhui
					 * @version 创建时间：2015年6月9日
					 */

						public List<Map<String,Object>> getDkxxListDetail(String xh){
							
							return zsXsjzdxxDao.getDkxxListDetail(xh);
						}
											
					
			/**
			 * 根据id查看学生的详细信息
			 * @author chenhui
			 * @date  2016年8月9日
			 * @param id
			 * @return
			 */
			public List<Map<String,Object>> getXsrzxxDetail(Long id){
				
				return zsXsjzdxxDao.getXsrzxxDetail(id);
			}
			
			/**
			 * 获取字典表中的班级
			 * @author chenhui
			 * @date 2015年8月07日
			 * @return
			 */
			@Transactional
			public List<Map<String,Object>> getBj(String openId){
				return zsXsjzdxxDao.getBj(openId);
			}
}
