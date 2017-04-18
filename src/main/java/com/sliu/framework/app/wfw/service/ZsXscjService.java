package com.sliu.framework.app.wfw.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.dao.ZsXscjDao;
import com.sliu.framework.app.wfw.model.ZsXjyd;
import com.sliu.framework.app.wfw.model.ZsXscjTxjl;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

/**
 * 学生成绩
 * @author liujiasen
 * @date 2015年5月18日
 */
@Service
public class ZsXscjService{

	@Autowired
	private ZsXscjDao zsXscjDao;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	public Pagination<Map<String, Object>> getXscjList(Integer start,
			Integer limit, String xm, String wxh) {
		return zsXscjDao.getXscjList(start, limit, xm, wxh);
	}
	
	/**
	 * 按学年和学期获取当前登录人的考试成绩
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getGrcj(String ksqh,String yhid,HttpServletRequest request,String whStr) 
			throws UnsupportedEncodingException{
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("~");
			//ksxn=arr[0]+"-"+arr[1];
			ksxn=arr[0];
			ksxq=arr[1];
		}else{
			//ksxn=zsXscjDao.getNewXn(whStr).get("ksxn")+"";
			//ksxq=zsXscjDao.getNewXq(ksxn,whStr).get("ksxq")+"";
			//Map<String,Object>  mapXn  =   AppUtil.fromNowGetXnXq(new Date());
/*			Map<String,Object>  mapXn  =   AppUtil.getXnNew(new Date());
			String xn = (String) mapXn.get("xn");
			String xq = (String) mapXn.get("xq");
			ksxn =xn;
			ksxq =xq ;*/
			if(zsXscjDao.getNewXn(whStr).size()!=0){
			ksxn=zsXscjDao.getNewXn(whStr).get(0).get("ksxn")+"";}
			if(zsXscjDao.getNewXq(ksxn,whStr).size()!=0){
			ksxq=zsXscjDao.getNewXq(ksxn,whStr).get(0).get("ksxq")+"";}
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXscjDao.getGrcj(ksxn, ksxq,yhid,pages,whStr);
	}

	/**
	 * 获取个人考试成绩学年学期
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getKsqh(String openId){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> ksqh=zsXscjDao.getKsqh(openId);
		for(int i=0;i<ksqh.size();i++){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("ksqh", ksqh.get(i).get("ksxn")+"-"+ksqh.get(i).get("ksxq"));
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 获取辅导员所带的班级
	 * @author zhangyan
	 * @date 2016年8月19日
	 * @return
	 */
	public List<Map<String,Object>> getBj(String openId){
		
		return zsXscjDao.getBj(openId);
	}
	
	/**
	 * 获取个人的用户信息
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public List<Map<String,Object>> getWxyh(String openId){
		return zsXscjDao.getWxyh(openId);
	}
	
	
	/**
	 * 获取学年和学期
	 * @author 
	 * @date 2016年1月13日
	 * @return
	 */
public List<Map<String,Object>> getXnAndXq(){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object>  mapXn  =   AppUtil.fromNowGetXnXq(new Date());
		String xn = (String) mapXn.get("xn");
		String xq = (String) mapXn.get("xq");
		Long lxn = Long.parseLong(xn);
		for(int i=0;i<3;i++){
			Long lxns = lxn-i;
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("ksqh", lxns+"-"+(lxns+1)+"~春季学期");
			map.put("ksqh1", lxns+"-"+(lxns+1)+"~秋季学期");
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 获取个人的权限
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @return
	 */
	public String getCxtj(String str, String yhid){
		return zsXscjDao.getCxtj(str,yhid);
	}

	/**
	 * 按学年和学期获取当前登录人的考试成绩
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getXsxx(String ksqh,String openId,HttpServletRequest request) 
			throws UnsupportedEncodingException{
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("~");
			//ksxn=arr[0]+"-"+arr[1];
			ksxn=arr[0];
			ksxq=arr[1];
				//ksxn = new String(ksxn.getBytes("ISO-8859-1"), "UTF-8"); 
				//ksxq = new String(ksxq.getBytes("ISO-8859-1"), "UTF-8"); 
		}else{
			String whStr = "";
			List<Map<String,Object>> user = this.getWxyh(openId);
			if(user.size()!=0){
				String yhid =user.get(0).get("yhid")+"";
				whStr = this.getCxtj("", yhid);
			}
			if(zsXscjDao.getNewXn(whStr).size()!=0){
			ksxn=zsXscjDao.getNewXn(whStr).get(0).get("ksxn")+"";}
			if(zsXscjDao.getNewXq(ksxn,whStr).size()!=0){
			ksxq=zsXscjDao.getNewXq(ksxn,whStr).get(0).get("ksxq")+"";}
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXscjDao.getXsxx(ksxn, ksxq,openId,pages);
	}
	
	
	/**
	 * 学生成绩的提醒
	 * @author liujiasen
	 * @date 2015年5月20日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@Transactional
	public void xsCjTx(){
		//SimpleDateFormat _s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    //String time =_s.format(new Date());
		/*String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; */
   	    String url = "";//成绩的页面
   	    List<Map<String,Object>> txurl =zsXscjDao.getSjzdByZl("cjtx");
   	    if(txurl.size()!=0){
   	    	url=txurl.get(0).get("zdz").toString()+"?openId=";
   	    }
   	    List<Map<String,Object>> listCj =null;
   	    List<Map<String,Object>> listCjSave =null;
		List<Map<String,Object>> listCjTxjl =zsXscjDao.getXstxjl();
		if(listCjTxjl.size()==0){
			List<Map<String,Object>> listCjOne =zsXscjDao.getTxxx();
			listCjSave =zsXscjDao.getTxSavexx();
			if(listCjOne.size()!=0){
			listCj=listCjOne;}
		}else if(listCjTxjl.size()!=0){
			//List<Map<String,Object>> listCjTwo =zsXscjDao.getTxxx1();
			List<Map<String,Object>> listCjTwo =zsXscjDao.getTxxx2();
			listCjSave=zsXscjDao.getTxSavexx2();
			if(listCjTwo.size()!=0){
			listCj=listCjTwo;}
		}
		if(listCj.size()!=0){
			for(Map<String,Object> map:listCj){
				//String toUser = "oCfRiw2c0nyVtPIvlY4ERx5fmSTw";
				String toUser =map.get("wxh").toString();
				WxTemplate temp = new WxTemplate();
				temp.setTemplate_id("CTsYAOjyp36Wmy9E3pqf1aLug51cNuoN6RuWBB6M5Eg");
				temp.setTopcolor("#000000");
				temp.setTouser(toUser);
				temp.setUrl(url+toUser);
				Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
				//提醒
				TemplateDataView dataView1 = new TemplateDataView();
				dataView1.setColor("#FF6600");
				dataView1.setValue(" "+map.get("xm").toString()+"同学您好:您的考试成绩如下:");
				mapdata.put("first", dataView1);
				//学生姓名
				TemplateDataView dataView2 = new TemplateDataView();
				dataView2.setColor("#000000");
				dataView2.setValue(" "+map.get("xm").toString()+" ");
				mapdata.put("childName", dataView2);
				//考试名称
				TemplateDataView dataView3 = new TemplateDataView();
				dataView3.setColor("#000000");
				dataView3.setValue(" 第"+map.get("ksxn").toString()+"学年"+map.get("ksxq").toString()+"考试 ");
				mapdata.put("courseName", dataView3);
				//考试成绩
				TemplateDataView dataView4 = new TemplateDataView();
				dataView4.setColor("#B22222");
				dataView4.setValue(" "+map.get("kscj").toString()+" ");
				mapdata.put("score", dataView4);
				//备注
				TemplateDataView dataView5 = new TemplateDataView();
				dataView5.setColor("#99CC00");
				dataView5.setValue("  ★ 您也 可以进入微服务查询历史成绩信息!★");
				mapdata.put("remark", dataView5);
				temp.setData(mapdata);
				WeixinUtils.sendTemplateMessage(toUser,temp);
			} 
		}
		if(listCjSave.size()!=0){
			for(Map<String,Object> map:listCjSave){
				//保存到记录表
				String toUser =map.get("wxh").toString();	
				ZsXscjTxjl jl=new ZsXscjTxjl();
				jl.setKscj(map.get("kscj").toString());
				jl.setKsxn(map.get("ksxn").toString());
				jl.setKsxq(map.get("ksxq").toString());
				jl.setSftx("1");
				jl.setKcbh(map.get("kcbh").toString());
				jl.setTxsj(new Date());
				jl.setWxh(toUser);
				jl.setXsxh(map.get("xh").toString());
				zsXscjDao.save(jl);
			}
		}
	}
	
	/**
	 * 成绩预警-查班级信息
	 * @author zhangyan
	 * @date 2016年8月15日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
//	public List<Map<String,Object>> getBjxx(String ksqh,String openId,HttpServletRequest request) 
//			throws UnsupportedEncodingException{
////		ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8");
//		String ksxn="";
//		String ksxq="";
//		if(ksqh!=null&&!"".equals(ksqh)){
//			String[] arr=ksqh.split("~");
//			//ksxn=arr[0]+"-"+arr[1];
//			ksxn=arr[0];
//			ksxq=arr[1];
//				//ksxn = new String(ksxn.getBytes("ISO-8859-1"), "UTF-8"); 
//				//ksxq = new String(ksxq.getBytes("ISO-8859-1"), "UTF-8"); 
//		}else{
//			String whStr = "";
////			List<Map<String,Object>> user = this.getWxyh(openId);
////			if(user.size()!=0){
////				String yhid =user.get(0).get("yhid")+"";
////				whStr = this.getCxtj("", yhid);
////			}
//			if(zsXscjDao.getNewXn1(openId).size()!=0){
//				ksxn=zsXscjDao.getNewXn1(openId).get("ksxn")+"";}
//			if(zsXscjDao.getNewXq1(ksxn,openId).size()!=0){
//				ksxq=zsXscjDao.getNewXq1(ksxn,openId).get("ksxq")+"";}
//		}
//		String pages=request.getParameter("pages");
//   		if(pages==null||"".equals(pages)){
//   			pages="1";
//   		}
//   		String bjbh=request.getParameter("bjbh");
//   		String bjbm=" ";
//   		if(bjbh !=null && !"".equals(bjbh)){
//   			bjbm=bjbh;
//   		}
//   		bjbm = new String(bjbm.getBytes("ISO-8859-1"), "UTF-8"); 
//		return zsXscjDao.getBjxx(ksxn, ksxq,openId,pages,bjbm);
//	}
	
	/**
	 * 成绩预警
	 * @author zhangyan
	 * @date 2016年8月15日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getCjList(String ksqh,String bjbh,String openId,HttpServletRequest request) throws UnsupportedEncodingException{
//		ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8");
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("-");
			ksxn=arr[0]+"-"+arr[1];
			ksxq=arr[2];
		}else{
			ksxn=zsXscjDao.getNewXn1(openId).get("ksxn")+"";
			ksxq=zsXscjDao.getNewXq1(ksxn,openId).get("ksxq")+"";
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
//   		String bjbh=request.getParameter("bjbh");
//   		if(bjbh!=null||!"".equals(bjbh)){
//   			bjbh = new String(bjbh.getBytes("ISO-8859-1"), "UTF-8"); 
//   		}
		return zsXscjDao.getCjList(ksxn, ksxq,openId,pages,bjbh);
	}
	
	/**
	 * 成绩预警查询
	 * @author zhangyan
	 * @date 2016年8月15日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getCjListcx(String ksqh,String bjbh,String openId,HttpServletRequest request) throws UnsupportedEncodingException{
//		ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8");
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("-");
			ksxn=arr[0]+"-"+arr[1];
			ksxq=arr[2];
		}else{
			ksxn=zsXscjDao.getNewXn1(openId).get("ksxn")+"";
			ksxq=zsXscjDao.getNewXq1(ksxn,openId).get("ksxq")+"";
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return zsXscjDao.getCjList(ksxn, ksxq,openId,pages,bjbh);
	}
	
	/**
	 * 成绩预警查询-查班级信息
	 * @author zhangyan
	 * @date 2016年8月15日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getBjxxcx(String ksqh,String openId,HttpServletRequest request) 
			throws UnsupportedEncodingException{
//		ksqh = new String(ksqh.getBytes("ISO-8859-1"), "UTF-8");
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("~");
			//ksxn=arr[0]+"-"+arr[1];
			ksxn=arr[0];
			ksxq=arr[1];
				//ksxn = new String(ksxn.getBytes("ISO-8859-1"), "UTF-8"); 
				//ksxq = new String(ksxq.getBytes("ISO-8859-1"), "UTF-8"); 
		}else{
			String whStr = "";
//			List<Map<String,Object>> user = this.getWxyh(openId);
//			if(user.size()!=0){
//				String yhid =user.get(0).get("yhid")+"";
//				whStr = this.getCxtj("", yhid);
//			}
			if(zsXscjDao.getNewXn1(openId).size()!=0){
				ksxn=zsXscjDao.getNewXn1(openId).get("ksxn")+"";}
			if(zsXscjDao.getNewXq1(ksxn,openId).size()!=0){
				ksxq=zsXscjDao.getNewXq1(ksxn,openId).get("ksxq")+"";}
		}
		String pages=request.getParameter("pages");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
   		String bjbh=request.getParameter("bjbh");
//   		if(bjbh!=null||!"".equals(bjbh)){
   			bjbh = new String(bjbh.getBytes("ISO-8859-1"), "UTF-8"); 
//   		}
   		
		return zsXscjDao.getBjxx(ksxn, ksxq,openId,pages,bjbh);
	}
	
	
	
	/**
	 * 成绩预警-查班级学生成绩
	 * @author zhangyan
	 * @date 2016年8月19日
	 * @param ksqh
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,Object>> getBjcj(String bjbh,String kskm,String ksqh,String openId) {
		String ksxn="";
		String ksxq="";
		if(ksqh!=null&&!"".equals(ksqh)){
			String[] arr=ksqh.split("~");
			//ksxn=arr[0]+"-"+arr[1];
			ksxn=arr[0];
			ksxq=arr[1];
				//ksxn = new String(ksxn.getBytes("ISO-8859-1"), "UTF-8"); 
				//ksxq = new String(ksxq.getBytes("ISO-8859-1"), "UTF-8"); 
		}else{
			String whStr = "";
//			List<Map<String,Object>> user = this.getWxyh(openId);
//			if(user.size()!=0){
//				String yhid =user.get(0).get("yhid")+"";
//				whStr = this.getCxtj("", yhid);
//			}
			if(zsXscjDao.getNewXn1(openId).size()!=0){
				ksxn=zsXscjDao.getNewXn1(openId).get("ksxn")+"";}
			if(zsXscjDao.getNewXq1(ksxn,openId).size()!=0){
				ksxq=zsXscjDao.getNewXq1(ksxn,openId).get("ksxq")+"";}
		}
		return zsXscjDao.getBjcj(bjbh,kskm,ksxn, ksxq,openId);
	}
	
}
