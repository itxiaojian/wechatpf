package com.sliu.framework.app.wfw.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.wfw.dao.ZsXsttkxxDao;
import com.sliu.framework.app.wfw.model.ZsXsttkxx;

/**
  *课表异动
  @Author oufeng	
  @Date 2016年8月10日 下午15:02:48
  @Version 1.0
*/
@Service
public class ZsXsttkxxService extends BaseBO<ZsXsttkxx> {
		
	@Autowired
	private ZsXsttkxxDao dao;
	
	
	/**
	 * 按学年和学期获取当前登录人的信息
	 * @author oufeng
	 * @date 2018年8月8日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getXsxx(String yhbh) 
			throws UnsupportedEncodingException{
		String ksxn=dao.getXnXq().get(0).get("xn")+"";
		String ksxq=dao.getXnXq().get(0).get("xq")+"";
		List<Map<String,Object>> list = dao.getXsxx(ksxn,ksxq,yhbh);
		return list;
	}
	
	/**
	 * 获取查询条件
	 * @author oufeng
	 * @date 2016年8月8日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getKsqh(String yhbh) 
			throws UnsupportedEncodingException{
		List<Map<String,Object>> list = dao.getXnXq();
		return list;
	}
	
	/**
	 * 获取查询条件
	 * @author oufeng
	 * @date 2016年8月8日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getTkxx(String ksqh,String yhbh,String pages) 
			throws UnsupportedEncodingException{
		List<Map<String,Object>> list = dao.getTkxx(ksqh,yhbh,pages);
		return list;
	}
}