package com.sliu.framework.app.wtx.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.dao.ZsXscjDao;
import com.sliu.framework.app.wfw.model.ZsXscjTxjl;
import com.sliu.framework.app.wtx.dao.TxLxlcDao;
import com.sliu.framework.app.wtx.dao.TxTshstxDao;
import com.sliu.framework.app.wtx.dao.TxTxGjDao;
import com.sliu.framework.app.wtx.dao.TxTxxxlsjlDao;
import com.sliu.framework.app.wtx.dao.TxXsjqfDao;
import com.sliu.framework.app.wtx.dao.TxYxlcDao;
import com.sliu.framework.app.wtx.dao.TxZggzDao;
import com.sliu.framework.app.wtx.model.TxLxlcTxjl;
import com.sliu.framework.app.wtx.model.TxTshstx;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;
import com.sliu.framework.app.wtx.model.TxXsjqftx;
import com.sliu.framework.app.wtx.model.TxYxlcTxjl;
import com.sliu.framework.app.wtx.model.TxZggzTxjl;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

/**
  * 提醒
  * @author oufeng
  * @date 2016年8月16日
  */
@Service
public class TxTxGjService extends BaseBO<TxTshstx>{

		@Autowired
		private TxTshstxDao tsdao;
		
		@Autowired
		private TxTxxxlsjlDao txDao;
		
		@Autowired
		private TxZggzDao gzdao;
		
		@Autowired
		private TxXsjqfDao jqfdao;
		
		@Autowired
		private ZsXscjDao zsXscjDao;
		
		@Autowired
		private TxTxGjDao dao;
		
		@Autowired
		private TxLxlcDao lxdao;
		
		@Autowired
		private TxYxlcDao yxdao;
		
		/**
		 * 图书归还提醒
		 * @author oufeng
		 * @date 2016年8月10日
		 * @param openId
		 * @return
		 * @throws ParseException 
		 */
		@Transactional
		public void sendTshstx() throws ParseException{
			String sfkq="";
			List<Map<String,Object>> listkg  =dao.getSfkq("tstx");
			if(listkg.size()!=0){
			 sfkq = listkg.get(0).get("kgzt").toString();
			}
			if("1".equals(sfkq)){
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat s1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Map<String,Object>> list =new  ArrayList<Map<String,Object>>();
			String title="";
			String date =s.format(new Date());
			List<Map<String,Object>> listSize=tsdao.getTstxjlSize();
			if(listSize.size()==0){
				list=tsdao.getTshstx(date);
			}else{
				list=tsdao.getTshstx1(date);
			}
			String url = "";
			List<Map<String,Object>> listlj = tsdao.getDict("tslj");
			if(listlj.size()>0){
				url = listlj.get(0).get("zdz").toString();
			}
			if(list.size()>0){
				for(Map<String,Object> map:list){
			
					String toUser = map.get("wxh").toString();
					String ygq = map.get("kgq").toString();
					if("2".equals(ygq)){title="您借的图书即将到期";}else if("1".equals(ygq)){title="您借的图书已到期";}
					WxTemplate temp = new WxTemplate();
					temp.setTemplate_id("KurcXJG-0WLRIcufUhUoPII0MI9qdkEdbaBWJdXC7BU");
					temp.setTopcolor("#000000");
					temp.setTouser(toUser);
		 			temp.setUrl(url+toUser);
					Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
					//提醒
					TemplateDataView dataView1 = new TemplateDataView();
					dataView1.setColor("#FF6600");
					dataView1.setValue(" "+title+" ");
					mapdata.put("first", dataView1);
					//应归还书名
					TemplateDataView dataView2 = new TemplateDataView();
					dataView2.setColor("#000000");
					dataView2.setValue(" "+map.get("tsmc").toString()+" ");
					mapdata.put("name", dataView2);
					//归还日期
					TemplateDataView dataView3 = new TemplateDataView();
					dataView3.setColor("#000000");
					dataView3.setValue(" "+map.get("yghsj").toString()+" ");
					mapdata.put("date", dataView3);
					//备注
					TemplateDataView dataView6 = new TemplateDataView();
					dataView6.setColor("#99CC00");
					dataView6.setValue("  ★ 请合理安排时间，归还图书！  ★");
					mapdata.put("remark", dataView6);
					temp.setData(mapdata);
					WeixinUtils.sendTemplateMessage(toUser,temp);
				} 
			}
			if(list.size()>0){
				for(Map<String,Object> map:list){
					String toUser = map.get("wxh").toString();
					TxTshstx entity = new TxTshstx();
					entity.setBh(map.get("bh").toString());
					entity.setWxh(toUser);
					entity.setTsmc(map.get("tsmc").toString());
					entity.setTsbh(map.get("tsbh").toString());
					entity.setYghsj(s1.parse(map.get("yghsj").toString()));
					//entity.setGhsj(s1.parse(map.get("ghsj").toString()));
					entity.setJcsj(s1.parse(map.get("jcsj").toString()));
					tsdao.save(entity);
					
					TxTxxxlsjl txlsjl = new TxTxxxlsjl();
					txlsjl.setOpenid(toUser);
					txlsjl.setTxdx(map.get("bh").toString());
					String ygq = map.get("kgq").toString();
					if("2".equals(ygq)){title="即将到期,";}else if("1".equals(ygq)){title="已到期,";}
					txlsjl.setTxnr("图书归还通知:您所借的图书:"+map.get("tsmc").toString()+title+"应归还时间:"+map.get("yghsj").toString()+",请合理安排时间，归还图书。");
					txlsjl.setTxsj(new Date());
					txlsjl.setTxlx("2");
					txDao.save(txlsjl);
				}
			}
		}
	}
		/**
		 * 定时发送工资提醒消息
		 * @author:oufeng
		 * @version 创建时间：2016年8月8日
		 */
		@Transactional
		public void sendWagesNews(){
			String sfkq="";
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Map<String,Object>> listkg  =dao.getSfkq("gztx");
			if(listkg.size()!=0){
			 sfkq = listkg.get(0).get("kgzt").toString();
			}
			if("1".equals(sfkq)){
			Calendar a=Calendar.getInstance();
			int year = a.get(Calendar.YEAR);
			int month = a.get(Calendar.MONTH)+1;
			int _month = month-1;
			int _year=0;
			if(_month==0){_year=year-1;_month=12;}else{_year=year;}
			List<Map<String,Object>> listWage  =new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list = gzdao.getGrgzjl(year,month);
			if(list.size()==0){
				listWage= gzdao.getGrgzForSendNews(year,month);
			}else{
				listWage=gzdao.getGrgzForSendNews1(year,month);
			}
			String url = "";
			List<Map<String,Object>> listlj = gzdao.getDict("gzlj");
			if(listlj.size()>0){
				url = listlj.get(0).get("zdz").toString();
			}
			if(listWage.size()>0){
				for(Map<String,Object> map:listWage){
					String toUser = map.get("wxh").toString();
					WxTemplate temp = new WxTemplate();
					temp.setTemplate_id("cQzhCMc43i-P4hWWWi6eVDlULr74f8au7Xg_I_vvf7g");
					temp.setTopcolor("#000000");
					temp.setTouser(toUser);
		 			temp.setUrl(url+toUser);
					Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
					//提醒
					TemplateDataView dataView1 = new TemplateDataView();
					dataView1.setColor("#FF6600");
					dataView1.setValue("您好，您这个月的总工资为 "+map.get("yfhj").toString()+"元，请注意查收。");
					mapdata.put("first", dataView1);
					//名称
					TemplateDataView dataView2 = new TemplateDataView();
					dataView2.setColor("#000000");
					dataView2.setValue(" "+map.get("nf").toString()+" 年 "+map.get("yf").toString()+" 月工资");
					mapdata.put("keyword1", dataView2);
					//应发金额
					TemplateDataView dataView3 = new TemplateDataView();
					dataView3.setColor("#000000");
					dataView3.setValue(" "+map.get("yfhj").toString()+" 元");
					mapdata.put("keyword2", dataView3);
					//实发金额
					TemplateDataView dataView4 = new TemplateDataView();
					dataView4.setColor("#000000");
					dataView4.setValue(" "+map.get("sfhj").toString()+"元,扣款合计为 "+map.get("kkhj").toString()+"元");
					mapdata.put("keyword3", dataView4);
					//时间
					TemplateDataView dataView5 = new TemplateDataView();
					dataView5.setColor("#000000");
					dataView5.setValue(" "+s.format(new Date())+"");
					mapdata.put("keyword4", dataView5);
					//备注
					TemplateDataView dataView6 = new TemplateDataView();
					dataView6.setColor("#99CC00");
					dataView6.setValue("  ★  您也可以进入微信的微服务查看详情！  ★");
					mapdata.put("remark", dataView6);
					temp.setData(mapdata);
					WeixinUtils.sendTemplateMessage(toUser,temp);
				} 
			}
			 if(listWage.size()>0){
					for(Map<String,Object> map:listWage){
						TxZggzTxjl txjl =new TxZggzTxjl();
						String toUser = map.get("wxh").toString();
						txjl.setNf(map.get("nf").toString());
						txjl.setXm(map.get("xm").toString());
						txjl.setYf(map.get("yf").toString());
						txjl.setGh(map.get("gh").toString());
						txjl.setWxh(toUser);
						txjl.setZt("1");
						gzdao.save(txjl);
						
						TxTxxxlsjl txlsjl = new TxTxxxlsjl();
						txlsjl.setOpenid(toUser);
						txlsjl.setTxdx(map.get("gh").toString());
						txlsjl.setTxnr("第"+map.get("nf").toString()+"年"+map.get("yf").toString()+"月工资发放通知:您的实发合计为:"
								+map.get("sfhj").toString()+"元,扣款合计为 :"+map.get("kkhj").toString()+"元");
						txlsjl.setTxsj(new Date());
						txlsjl.setTxlx("6");
						txDao.save(txlsjl);
					}
			    }
			 gzdao.deleteGrgzjl(_year,_month);
		   }
		}

		/**
		 * 缴欠费提醒
		 * @author oufeng
		 * @date 2016年8月10日
		 * @param openId
		 * @return
		 * @throws ParseException 
		 */
		@Transactional
		public void sendJqftx() throws ParseException{
			String sfkq="";
			List<Map<String,Object>> listkg  =dao.getSfkq("jftx");
			if(listkg.size()!=0){
			 sfkq = listkg.get(0).get("kgzt").toString();
			}
			if("1".equals(sfkq)){
			List<Map<String,Object>> list =new  ArrayList<Map<String,Object>>();
			String xn = AppUtil.fromNowGetXn(new Date()).get("xn").toString();
			List<Map<String,Object>> listSize=jqfdao.getJqfjlSize(xn);
			if(listSize.size()==0){
				list=jqfdao.getJqftx(xn);
			}else{
				list=jqfdao.getJqftx1(xn);
			}
			String url = "";
			List<Map<String,Object>> listlj = jqfdao.getDict("jflj");
			if(listlj.size()>0){
				url = listlj.get(0).get("zdz").toString();
			}
			if(list.size()>0){
				for(Map<String,Object> map:list){
					String toUser = map.get("wxh").toString();
					WxTemplate temp = new WxTemplate();
					temp.setTemplate_id("1pehuL-_ifNy2FWBa8gUEnmEePnF_arvmsNmD11V3Po");
					temp.setTopcolor("#000000");
					temp.setTouser(toUser);
		 			temp.setUrl(url+toUser);
					Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
					//提醒
					TemplateDataView dataView1 = new TemplateDataView();
					dataView1.setColor("#FF6600");
					dataView1.setValue(" 第"+xn+"学年,"+map.get("xm").toString()+"同学的缴费通知 ");
					mapdata.put("first", dataView1);
					//学生姓名
					TemplateDataView dataView2 = new TemplateDataView();
					dataView2.setColor("#000000");
					dataView2.setValue(" "+map.get("xm").toString()+" ");
					mapdata.put("keyword1", dataView2);
					//缴费类型
					TemplateDataView dataView3 = new TemplateDataView();
					dataView3.setColor("#000000");
					dataView3.setValue(" "+map.get("jfxm").toString()+" ");
					mapdata.put("keyword2", dataView3);
					//缴费金额
					TemplateDataView dataView4 = new TemplateDataView();
					dataView4.setColor("#000000");
					dataView4.setValue(" "+map.get("qfje").toString()+" ");
					mapdata.put("keyword3", dataView4);
					//截止日期
					TemplateDataView dataView5 = new TemplateDataView();
					dataView5.setColor("#000000");
					dataView5.setValue(" "+"为有助于学校各项工作的顺利开展，请您尽快缴纳欠费！"+" ");
					mapdata.put("keyword4", dataView5);
					//备注
					TemplateDataView dataView6 = new TemplateDataView();
					dataView6.setColor("#99CC00");
					dataView6.setValue("  ★  您也可以进入微信的微服务查看详情！ ★");
					mapdata.put("remark", dataView6);
					temp.setData(mapdata);
					WeixinUtils.sendTemplateMessage(toUser,temp);
				} 
			}
			if(list.size()>0){
				for(Map<String,Object> map:list){
					String toUser = map.get("wxh").toString();
					TxXsjqftx entity = new TxXsjqftx();
					entity.setWxh(toUser);
					entity.setXh(map.get("xh").toString());
					entity.setXm(map.get("xm").toString());
					entity.setXn(map.get("xn").toString());
					entity.setTxnr(map.get("jfxm").toString()+";"+map.get("Qfje").toString());
					jqfdao.save(entity);
					
					TxTxxxlsjl txlsjl = new TxTxxxlsjl();
					String nr="第"+xn+"学年,"+map.get("xm").toString()+"同学的缴费通知:"
							+"欠费类型:"+map.get("jfxm").toString()+"欠费金额:"+map.get("qfje").toString();
					txlsjl.setOpenid(toUser);
					txlsjl.setTxdx(map.get("xh").toString());
					txlsjl.setTxnr(nr);
					txlsjl.setTxsj(new Date());
					txlsjl.setTxlx("7");
					txDao.save(txlsjl);
				}
			}
			//删除上个学期的记录
			jqfdao.deleteJqfjl(Integer.parseInt(xn)-1);
		}
	}	
		
		/**
		 * 成绩提醒
		 * @author oufeng
		 * @date 2015年5月20日
		 * @param ksqh
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		@Transactional
		public void xsCjTx(){
			String sfkq="";
			List<Map<String,Object>> listkg  =dao.getSfkq("cjtx");
			if(listkg.size()!=0){
			 sfkq = listkg .get(0).get("kgzt").toString();
			}
			if("1".equals(sfkq)){
			//SimpleDateFormat _s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    //String time =_s.format(new Date());
			/*String path = request.getContextPath();
	    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; */
	   	    String url = "";//成绩的页面
	   	    List<Map<String,Object>> txurl =zsXscjDao.getSjzdByZl("cjlj");
	   	    if(txurl.size()!=0){
	   	    	url=txurl.get(0).get("zdz").toString();
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
					temp.setTemplate_id("FdUxgfOr-BSIrAZIfuLVrxGGkEzpjvodSq4XSAI_3mc");
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
				}}
			if(listCj.size()!=0){
				for(Map<String,Object> map:listCj){
					String toUser =map.get("wxh").toString();	
					TxTxxxlsjl txlsjl = new TxTxxxlsjl();
					String nr="第"+map.get("ksxn").toString()+"学年"+map.get("ksxq").toString()+map.get("xm").toString()+"同学考试成绩通知:"
							+map.get("kscj").toString();
					txlsjl.setOpenid(toUser);
					txlsjl.setTxdx(map.get("xh").toString());
					txlsjl.setTxnr(nr);
					txlsjl.setTxsj(new Date());
					txlsjl.setTxlx("8");
					txDao.save(txlsjl);
				}
			}
		}
	}
		
		/**
		 * 定时发送离校提醒
		 * @author:wangxiangyang
		 * @version 创建时间：2016年8月11日
		 */
		@Transactional
		public void lxtx(){
			String sfkq="";
			List<Map<String,Object>> listkg  =dao.getSfkq("lxtx");
			if(listkg.size()!=0){
			 sfkq = listkg.get(0).get("kgzt").toString();
			}
			if("1".equals(sfkq)){
			Calendar a=Calendar.getInstance();
			int year = a.get(Calendar.YEAR);
			int lastyear = year-1;
			String blzt1="";
			List<Map<String,Object>> listWage  =new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list = lxdao.getLxlcjl(year);
			if(list.size()==0){
				listWage= lxdao.getGrgzForSendNews(year);
			}else{
				listWage=lxdao.getGrgzForSendNews1(year);
			}
			String url = "";
			List<Map<String,Object>> listlj = lxdao.getDict("lxlj");
			if(listlj.size()>0){
				url = listlj.get(0).get("zdz").toString();
			}
			if(listWage.size()>0){
				for(Map<String,Object> map:listWage){
					String toUser = map.get("wxh").toString();
					WxTemplate temp = new WxTemplate();
					temp.setTemplate_id("Un-8cQEr0nPTpJAfFLKireaUSKoP35DCTC_NFpMWPs0");
					temp.setTopcolor("#000000");
					temp.setTouser(toUser);
		 			temp.setUrl(url+toUser);
					Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
					//提醒
					TemplateDataView dataView1 = new TemplateDataView();
					dataView1.setColor("#FF6600");
					dataView1.setValue("您好，您有新的流程信息： "+map.get("tachename").toString()+"变动，请注意查看。");
					mapdata.put("first", dataView1);
					//姓名
					TemplateDataView dataView2 = new TemplateDataView();
					dataView2.setColor("#000000");
					dataView2.setValue(" "+map.get("stuname").toString());
					mapdata.put("keyword1", dataView2);
					//审核结果
					TemplateDataView dataView3 = new TemplateDataView();
					dataView3.setColor("#000000");
					if("1".equals(map.get("blzt").toString())){
						blzt1 = "通过";
					}else if("0".equals(map.get("blzt").toString())){
						blzt1 = "未通过";
					}
					dataView3.setValue(" "+blzt1);
					mapdata.put("keyword2", dataView3);
					//备注
					TemplateDataView dataView4 = new TemplateDataView();
					dataView4.setColor("#99CC00");
					dataView4.setValue("  ★  查看详细请点击，祝工作愉快！  ★");
					mapdata.put("remark", dataView4);
					temp.setData(mapdata);
					WeixinUtils.sendTemplateMessage(toUser,temp);
				} 
			}
			 if(listWage.size()>0){
					for(Map<String,Object> map:listWage){
						TxLxlcTxjl lxjl =new TxLxlcTxjl();
						String toUser = map.get("wxh").toString();
						lxjl.setTacheid(map.get("tacheid").toString());
						lxjl.setStuid(map.get("stuid").toString());
						lxjl.setStuname(map.get("stuname").toString());
						lxjl.setBlzt(map.get("blzt").toString());
						lxjl.setBltime(map.get("bltime").toString());
						lxjl.setWxh(toUser);
						lxdao.save(lxjl);
					}
			    }
			 lxdao.deleteLxlcjl(lastyear);
			}
		   }
		
		/**
		 * 定时发送迎新提醒
		 * @author:wangxiangyang
		 * @version 创建时间：2016年8月11日
		 */
		@Transactional
		public void yxtx(){
			String sfkq="";
			List<Map<String,Object>> listkg  =dao.getSfkq("yxtx");
			if(listkg.size()!=0){
			 sfkq = listkg.get(0).get("kgzt").toString();
			}
			if("1".equals(sfkq)){
			Calendar a=Calendar.getInstance();
			int year = a.get(Calendar.YEAR);
			int lastyear = year-1;
			String blzt1="";
			List<Map<String,Object>> listWage  =new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list = yxdao.getYxlcjl(year);
			if(list.size()==0){
				listWage= yxdao.getGrgzForSendNews(year);
			}else{
				listWage=yxdao.getGrgzForSendNews1(year);
			}
			String url = "";
			List<Map<String,Object>> listlj = yxdao.getDict("yxlj");
			if(listlj.size()>0){
				url = listlj.get(0).get("zdz").toString();
			}
			if(listWage.size()>0){
				for(Map<String,Object> map:listWage){
					String toUser = map.get("wxh").toString();
					WxTemplate temp = new WxTemplate();
					temp.setTemplate_id("ZOXpGnm34XmpeVtMxuWbtyvhVSXLSsH_OvHSTyC3624");
					temp.setTopcolor("#000000");
					temp.setTouser(toUser);
		 			temp.setUrl(url+toUser);
					Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
					//提醒
					TemplateDataView dataView1 = new TemplateDataView();
					dataView1.setColor("#FF6600");
					dataView1.setValue("您好，您有新的流程信息： "+map.get("tachename").toString()+"变动，请注意查看。");
					mapdata.put("first", dataView1);
					//姓名
					TemplateDataView dataView2 = new TemplateDataView();
					dataView2.setColor("#000000");
					dataView2.setValue(" "+map.get("stuname").toString());
					mapdata.put("keyword1", dataView2);
					//审核结果
					TemplateDataView dataView3 = new TemplateDataView();
					dataView3.setColor("#000000");
					if("1".equals(map.get("blzt").toString())){
						blzt1 = "通过";
					}else if("0".equals(map.get("blzt").toString())){
						blzt1 = "未通过";
					}
					dataView3.setValue(" "+blzt1);
					mapdata.put("keyword2", dataView3);
					//备注
					TemplateDataView dataView4 = new TemplateDataView();
					dataView4.setColor("#99CC00");
					dataView4.setValue("  ★  查看详细请点击，祝工作愉快！  ★");
					mapdata.put("remark", dataView4);
					temp.setData(mapdata);
					WeixinUtils.sendTemplateMessage(toUser,temp);
				} 
			}
			 if(listWage.size()>0){
					for(Map<String,Object> map:listWage){
						TxYxlcTxjl yxjl =new TxYxlcTxjl();
						String toUser = map.get("wxh").toString();
						yxjl.setTacheid(map.get("tacheid").toString());
						yxjl.setStuid(map.get("stuid").toString());
						yxjl.setBlzt(map.get("blzt").toString());
						yxjl.setWxh(toUser);
						yxdao.save(yxjl);
					}
			    }
			 yxdao.deleteYxlcjl(lastyear);
			}
		   }
}
