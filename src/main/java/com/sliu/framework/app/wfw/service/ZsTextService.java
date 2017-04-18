package com.sliu.framework.app.wfw.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wfw.dao.ZsTextDao;
import com.sliu.framework.app.wsh.model.ShSwzl;

@Service
public class ZsTextService extends BaseBO<ShSwzl>{
	protected Logger logger = LoggerFactory.getServiceLog(ShSwzl.class);
	
	@Autowired
	private ZsTextDao dao;
	
	@Transactional
	public List<Map<String,Object>> getSwzlxx(HttpServletRequest request){
		String  json=request.getParameter("json");
		JSONObject myJsonObject = new JSONObject(json);
		String  pageSize = myJsonObject.getString("pageSize");
		String  pageNum = myJsonObject.getString("pageNum");
		String  lostType = myJsonObject.getString("lostType");
		String  status = myJsonObject.getString("status");
		//pageAmount 总页数
	    //pageSize 每页多少条数据
		//pageNum 页数
	    //resultAmount 总记录数
		List<Map<String,Object>> list = dao.getSwzlxx(pageSize,pageNum,lostType);
		int pageAmount  = dao.getPageAmount(pageSize,pageNum,lostType);
		List<Map<String,Object>> resultAmount = dao.resultAmount(lostType);
		Map<String,Object>  map = new HashMap<String,Object>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		if(resultAmount.size()!=0){
		map.put("resultAmount", (resultAmount.get(0).get("zs")+""));
		}
	    map.put("pageAmount",pageAmount);
		map.put("lostType", lostType);
		map.put("status", status);
		map.put("retCode", "200");
		list.add(map);
		return list;
	}
	
	@Transactional
	public String save(HttpServletRequest request){
		String  json=request.getParameter("json");
		String str="";
		JSONObject myJsonObject = new JSONObject(json);
		String  description = myJsonObject.getString("description");
		String  details = myJsonObject.getString("details");
		String  openId = myJsonObject.getString("openId");
		String  lostTime = myJsonObject.getString("lostTime");
		String  place = myJsonObject.getString("place");
		String  multiselect = myJsonObject.getString("multiselect");
		List<Map<String,Object>>  user = dao.getUser(openId);
		if(user.size()!=0){
		ShSwzl swzl = new ShSwzl();
		swzl.setBt(description);
		swzl.setDd(place);
		swzl.setLx(multiselect);
		swzl.setXwzt("1");
		swzl.setFbr(openId);
		swzl.setFbrxm(user.get(0).get("xm").toString());
		SimpleDateFormat _s=new SimpleDateFormat("yyyy-MM-dd");
		try {
			swzl.setFqsj(_s.parse(lostTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		swzl.setXwzs(details);
		dao.save(swzl);
		str="1";
		}
		return str;
	}
	
	@Transactional
	public List<Map<String,Object>> getMySwzlxx(HttpServletRequest request){
		String  json=request.getParameter("json");
		JSONObject myJsonObject = new JSONObject(json);
		String  pageSize = myJsonObject.getString("pageSize");
		String  pageNum = myJsonObject.getString("pageNum");
		String  lostType = myJsonObject.getString("lostType");
		String  openId = myJsonObject.getString("openId");
		//String  status = myJsonObject.getString("status");
		//pageAmount 总页数
	    //pageSize 每页多少条数据
		//pageNum 页数
	    //resultAmount 总记录数
		List<Map<String,Object>> list = dao.myGetSwzlxx(pageSize,pageNum,lostType,openId);
		int pageAmount  = dao.myGetPageAmount(pageSize,pageNum,lostType,openId);
		List<Map<String,Object>> resultAmount = dao.myResultAmount(lostType,openId);
		Map<String,Object>  map = new HashMap<String,Object>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		if(resultAmount.size()!=0){
		map.put("resultAmount", (resultAmount.get(0).get("zs")+""));
		}
	    map.put("pageAmount",pageAmount);
		map.put("lostType", lostType);
		map.put("retCode", "200");
		list.add(map);
		return list;
	}
	
	@Transactional
	public String update(HttpServletRequest request){
		String  json=request.getParameter("json");
		String str="";
		JSONObject myJsonObject = new JSONObject(json);
		String  lostId = myJsonObject.getString("lostId");
		String  description = myJsonObject.getString("description");
		String  details = myJsonObject.getString("details");
		String  openId = myJsonObject.getString("openId");
		String  lostTime = myJsonObject.getString("lostTime");
		String  place = myJsonObject.getString("place");
		String  multiselect = myJsonObject.getString("lostType");
		List<Map<String,Object>>  user = dao.getUser(openId);
		if(user.size()!=0){
		ShSwzl swzl = new ShSwzl();
		swzl.setBt(description);
		swzl.setDd(place);
		swzl.setLx(multiselect);
		swzl.setXwzt("1");
		swzl.setFbr(openId);
		swzl.setFbrxm(user.get(0).get("xm").toString());
		SimpleDateFormat _s=new SimpleDateFormat("yyyy-MM-dd");
		try {
			swzl.setFqsj(_s.parse(lostTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		swzl.setXwzs(details);
		swzl.setId(Long.parseLong(lostId));
		dao.update(swzl);
		str="1";
		}
		return str;
	}
	
	@Transactional
	public List<Map<String,Object>> showLost(HttpServletRequest request){
		String  json=request.getParameter("json");
		JSONObject myJsonObject = new JSONObject(json);
		String  lostId = myJsonObject.getString("lostId");
		List<Map<String,Object>> list = dao.getSwzlxx(lostId);
		return list;
	}
	
	@Transactional
	public String delete(HttpServletRequest request){
		String  json=request.getParameter("json");
		String str="";
		JSONObject myJsonObject = new JSONObject(json);
		String  lostId = myJsonObject.getString("lostId");
		String  lostType = myJsonObject.getString("lostType");
		dao.delete(Long.parseLong(lostId));
		str="1";
		return str;
	}
	
	@Transactional
	public String endLost(HttpServletRequest request){
		String  json=request.getParameter("json");
		String str="";
		JSONObject myJsonObject = new JSONObject(json);
		String  lostId = myJsonObject.getString("lostId");
		String  lostZt = myJsonObject.getString("lostZt");
		dao.endLost(lostId,lostZt);
		str="1";
		return str;
	}
}
