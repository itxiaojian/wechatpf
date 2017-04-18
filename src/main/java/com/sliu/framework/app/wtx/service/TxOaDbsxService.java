package com.sliu.framework.app.wtx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.util.WebServiceUtil;
import com.sliu.framework.app.wtx.dao.TxOaDbsxDao;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

@Service
public class TxOaDbsxService {

	@Autowired
	private TxOaDbsxDao dao;
	
	
	/**
	 * 获取OA待办事项
	 * @author oufeng
	 * @throws AxisFault 
	 * @date 2016年8月3日
	 */
	public void getOaDbsx() throws AxisFault{
		int offset=1;
		int pagesize=20;
		int readtype=0;
		List<Map<String,Object>> list=dao.getYhbh();
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				String  yhbh = list.get(i).get("yhbh").toString();
				String  wxh = list.get(i).get("wxh").toString();
				String  xm = list.get(i).get("xm").toString();
				Object object = WebServiceUtil.getMethodnewtask(yhbh,readtype,offset,pagesize);
				JSONObject jsonb = JSONObject.fromObject(object);
				String success=jsonb.getString("success");
				if("success".equals(success)){
					String totalnum=jsonb.getString("totalnum");
					if(!"0".equals(totalnum)){
					String lists=jsonb.getString("lists");
					JSONArray jsonlist = JSONArray.fromObject(lists);
					if(jsonlist.size()!=0){
						this.sendMbMessahe(totalnum,wxh,yhbh,xm);
						String message=jsonb.getString("message");
						String recnum=jsonb.getString("recnum");
						String pageSize=jsonb.getString("pagesize");
						//System.out.println("jsonlist===================="+jsonlist);
						//System.out.println("yhbh===================="+yhbh);
						//System.out.println("totalnum===================="+totalnum);
						/*for(int j=0;j<jsonlist.size();j++){
						JSONObject jobj =  (JSONObject) jsonlist.get(j);
							}*/
					  }
					}
				}
			}
		}
		//System.out.println("====================over=======================");
	}
	
	/**
	 * 发送消息
	 * @author oufeng
	 * @date 2015年5月19日
	 * @param totalnum
	 * @param wxh
	 * @param yhbh 
	 * @return
	 */
	public void sendMbMessahe(String totalnum,String wxh,String yhbh,String xm){
		WxTemplate temp = new WxTemplate();
		temp.setTemplate_id("CTsYAOjyp36Wmy9E3pqf1aLug51cNuoN6RuWBB6M5Eg");
		temp.setTopcolor("#000000");
		temp.setTouser(wxh);
		temp.setUrl("oa.wxc.edu.cn");
		Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
		//提醒
		TemplateDataView dataView1 = new TemplateDataView();
		dataView1.setColor("#FF6600");
		dataView1.setValue(" 您好,您有未读的待办事项");
		mapdata.put("first", dataView1);
		//姓名
		TemplateDataView dataView2 = new TemplateDataView();
		dataView2.setColor("#000000");
		dataView2.setValue(" "+xm+" ");
		mapdata.put("name", dataView2);							
		//正文
		TemplateDataView dataView3 = new TemplateDataView();
		dataView3.setColor("#000000");
		dataView3.setValue(" 您有"+totalnum+"个未读的待办事项 ");
		mapdata.put("childName", dataView3);
		//备注
		TemplateDataView dataView5 = new TemplateDataView();
		dataView5.setColor("#99CC00");
		dataView5.setValue("  ★ 您可以进入皖西学院的电子政务系统查看详情★");
		mapdata.put("remark", dataView5);
		temp.setData(mapdata);
		WeixinUtils.sendTemplateMessage(wxh,temp);
	}
	
	
	
	/**
	 * 获取OA待办事项
	 * @author oufeng
	 * @throws AxisFault 
	 * @date 2016年8月3日
	 */
	public List<Map<String,Object>> getOaDbsxById(String openId,String page) throws AxisFault{
		int offset=1;
		if("1".equals(page)){
		 offset=Integer.parseInt(page);}
		else{offset=(Integer.parseInt(page)-1)*10;}
		List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
		int pagesize=10;
		int readtype=0;
		List<Map<String,Object>> list=dao.getWxyh(openId);
		if(list.size()!=0){
				String  yhbh = list.get(0).get("yhbh").toString();
				String  wxh = list.get(0).get("wxh").toString();
				String  xm = list.get(0).get("xm").toString();
				Object object = WebServiceUtil.getMethodnewtask(yhbh,readtype,offset,pagesize);
				//System.out.println("object===================="+object);
				JSONObject jsonb = JSONObject.fromObject(object);
				String success=jsonb.getString("success");
				if("success".equals(success)){
					String recnum=jsonb.getString("recnum");
					String pageSize=jsonb.getString("pagesize");
					String totalnum=jsonb.getString("totalnum");
					if(!"0".equals(totalnum)){
					String lists=jsonb.getString("lists");
					JSONArray jsonlist = JSONArray.fromObject(lists);
					if(jsonlist.size()!=0){
						for(int j=0;j<jsonlist.size();j++){
						JSONObject jobj =(JSONObject) jsonlist.get(j);
						String time =jobj.get("time")+"";
						String id= jobj.get("id")+"";
						String title=jobj.get("title")+"";
						String url=jobj.get("url")+"";
						String readed = jobj.get("readed")+"";
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("id", id);
						map.put("title", title);
						map.put("url",url);
						map.put("xm", xm);
						map.put("time",time);
						map.put("readed", readed);
						list1.add(map);
							}
					    }
					}
				}
			}
		return list1;
	}	
	
	/**
	 * 获取用户编号
	 * @author oufeng
	 * @date 2016年8月3日
	 */
	public List<Map<String,Object>> getWxyh(String openId){
		return	dao.getWxyh(openId);
	}
}
