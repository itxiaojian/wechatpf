package com.sliu.framework.app.wsh.service;

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
import com.sliu.framework.app.wsh.dao.ShWbmBdDao;
import com.sliu.framework.app.wsh.model.ShWbmbd;

/**
 * 微报名
 * @author liujiansen
 * @date 2015年6月24日
 */
@Service
public class ShWbmBdService extends BaseBO<ShWbmbd>{

	protected Logger logger = LoggerFactory.getServiceLog(ShWbmbd.class);
	
	@Autowired
	private ShWbmBdDao dao;
	
	/**
	 * 报名活动表单添加
	 * @author liujiasen
	 * @date 2015年6月25日
	 * @param request
	 * @param response
	 */
	@Transactional
	public String saveWbmBd(HttpServletRequest request, HttpServletResponse response) {
		String str="";
		int count=Integer.parseInt(request.getParameter("field_count"));
		String bmid=request.getParameter("activity_id");
		for(int i=0;i<count;i++){
			ShWbmbd bd=new ShWbmbd();
			String bdlx=request.getParameter("field_type_"+i);
			String bt=request.getParameter("field_name_"+i);
			String zdxzz=request.getParameter("field_max_select_"+i);
			String ddhz=request.getParameter("field_value_"+i);
			String sfbt=request.getParameter("field_nullable_"+i);
			bd.setBdlx(bdlx);
			bd.setBmid(Long.parseLong(bmid));
			bd.setBt(bt);
			bd.setDdhz(ddhz);
			bd.setSfbt(sfbt);
			bd.setZdxzz(Integer.parseInt(zdxzz));
			dao.save(bd);
		}
		str="1";
		return str;
	}
	
	/**
	 * 根据报名编号获得报名的表单数据
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param bmid 报名编号
	 * @return
	 */
	public List<Map<String,Object>> getWbmBd(String bmid){
		List<Map<String,Object>> bd=dao.getWbmBd(bmid);
		return bd;
	}
	
	/**
	 * 
	 * @author liujiasen
	 * @date 2015年7月7日
	 * @param ddhz
	 * @param bd
	 * @param i
	 * @param list
	 */
	public void getList(String ddhz,List<Map<String, Object>> bd,int i,List<Map<String, Object>> list){
		String[] arr = ddhz.split("\\|");
		if (arr.length > 0) {
			for (int j = 0; j < arr.length; j++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", bd.get(i).get("id"));
				map.put("bmid", bd.get(i).get("bmid"));
				map.put("bdlx", bd.get(i).get("bdlx"));
				map.put("bt", bd.get(i).get("bt"));
				map.put("zdxzz", bd.get(i).get("zdxzz"));
				map.put("sfbt", bd.get(i).get("sfbt"));
				map.put("ddhz", bd.get(i).get("ddhz"));
				map.put("bz", bd.get(i).get("bz"));
				map.put("sl", arr.length);
				map.put("select", arr[j]);
				list.add(map);
			}
		}
	}

	/**
	 * 报名活动表单修改
	 * @author liujiasen
	 * @date 2015年6月26日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String updateWbmBd(HttpServletRequest request,HttpServletResponse response) {
		String str="";
		int count=Integer.parseInt(request.getParameter("field_count"));
		String bmid=request.getParameter("activity_id");
		dao.deleteByBmId(bmid);
		for(int i=0;i<count;i++){
			ShWbmbd bd=new ShWbmbd();
			String bdlx=request.getParameter("field_type_"+i);
			String bt=request.getParameter("field_name_"+i);
			String zdxzz=request.getParameter("field_max_select_"+i);
			String ddhz=request.getParameter("field_value_"+i);
			String sfbt=request.getParameter("field_nullable_"+i);
			bd.setBdlx(bdlx);
			bd.setBmid(Long.parseLong(bmid));
			bd.setBt(bt);
			bd.setDdhz(ddhz);
			bd.setSfbt(sfbt);
			bd.setZdxzz(Integer.parseInt(zdxzz));
			dao.save(bd);
		}
		str="1";
		return str;
	}
}
