package com.sliu.framework.app.wlx.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wlx.dao.WlxLxtxDao;
import com.sliu.framework.app.wlx.model.LxTxjl;

/**
 * @author oufeng
 * @date 2016年6月8日
 */
@Service
public class WlxLxtxService extends BaseBO<LxTxjl>{
	
	@Autowired
	private WlxLxtxDao lxtxDao;
	
	
	
	
	
    /**
     * 学生离校提醒
     * @throws Exception
     * @author oufeng
     * @date 2016年6月8日
     */
	@Transactional
	 public void xsLxTx() {
		 List<Map<String, Object>>  openIdList =null;
		 List<Map<String, Object>>  txjlSizeList = lxtxDao.getTxjlSize();
		 if(txjlSizeList.size()==0){
			 openIdList = lxtxDao.getOpenId1();
		 }else{
			 openIdList = lxtxDao.getOpenId();
		 }
		 String openId="";
		 String stuid="";
		 String tacheid="";
		 String blzt="";
		 String reason="";
		 String tachename="";
		 String text="";
		 String zt="";
		 List<Map<String, Object>> list=null;
		 if(openIdList.size()!=0){
			 for(int i=0;i<openIdList.size();i++){
				  openId = openIdList.get(i).get("wxh")+"";
				  stuid = openIdList.get(i).get("stuid")+"";
				  tacheid = openIdList.get(i).get("tacheid")+"";
				  blzt = openIdList.get(i).get("blzt")+"";
				  reason = openIdList.get(i).get("reason")+"";
				  tachename = openIdList.get(i).get("tachename")+"";
				  //list = lxtxDao.getLxLcxx(stuid,tacheid);
				  //if(list.size()!=0){
					 // for(int j=0;j<list.size();j++){
						  if("1".equals(blzt)){
							  //消息循环发送
							   text="您好,您的离校流程:"+tachename+",已办理完成";
							   zt="1";
						  }else if("0".equals(blzt)){
							  //消息循环发送
							  if(!"".equals(reason) && !"null".equals(reason) && reason!=null){
							   text="您好,您的离校流程:"+tachename+",未办理完成("+reason+")";
							   zt="0";
							  }else{
								text="您好,您的离校流程:"+tachename+",未办理完成";
								zt="0";
							  }
						  }else{
							  //消息循环发送
							  if(!"".equals(reason) && !"null".equals(reason) && reason!=null){
							   text= "您好,您的离校流程:"+tachename+",可能需要办理("+reason+")";
							  }else{
								text="您好,您的离校流程:"+tachename+",可能需要办理";
							  }
						  }
						  WeixinUtils.massPreview("text", openId, text);
					  //}
				  //}
				  //if(list.size()!=0){
					  //for(int j=0;j<list.size();j++){
						  LxTxjl txjl = new LxTxjl();
							    txjl.setJlid(Long.parseLong(tacheid));
							    txjl.setTxnr(text);
							    txjl.setTxsj(new Date());
							    txjl.setXsxh(stuid);
							    txjl.setWxh(openId);
							    txjl.setBlzt(zt);
							    lxtxDao.save(txjl);
					  //}
				  //}
			 }
		 }
	 }
	
	/**
	 * @author:oufeng
	 * @version:2016年6月8日
	 * @return
	 */
	public List<Map<String, Object>> getTxlist(String openId){
		String dlm = "";
		 List<Map<String, Object>>  list = lxtxDao.getUser(openId);
		 if(list.size()!=0){
			  dlm =list.get(0).get("dlm")+"";
		 }
		return lxtxDao.getTxlist(dlm,openId);
	}
	
    /**
     * 学生离校状态改变的提醒
     * @throws Exception
     * @author oufeng
     * @date 2016年6月8日
     */
	@Transactional
	 public void xsLxTxZt() {
		String blztf="";
		String blzts="";
		List<Map<String, Object>>  txztList =null;
		List<Map<String, Object>>  list =null;
		List<Map<String, Object>>  openIdList =null;
		 List<Map<String, Object>>  txjlSizeList = lxtxDao.getTxjlSize();
		 if(txjlSizeList.size()!=0){
			   txztList = lxtxDao.getTxZtList();
			 if(txztList.size()!=0){
				 for(int i=0;i<txztList.size();i++){
					 blztf= txztList.get(i).get("blztf")+"";
					 blzts=txztList.get(i).get("blzts")+"";
					 list = lxtxDao.getLxLcxx(txztList.get(i).get("stuid")+"",txztList.get(i).get("tacheid")+"");
						 if("1".equals(blztf) && "0".equals(blzts) ){
							 //提醒
							  WeixinUtils.massPreview("text",lxtxDao.getOpenIdbyId(txztList.get(i).get("id")+"").get(0).get("wxh")+"", "您好,您的离校流程:"+list.get(0).get("tachename")+""+",已办理完成");
							 //更新数据库
							    LxTxjl txjl = new LxTxjl();
							    txjl.setId(Long.parseLong(txztList.get(i).get("id")+""));
							    txjl.setTxnr("您好,您的离校流程:"+list.get(0).get("tachename")+""+",已办理完成");
							    txjl.setTxsj(new Date());
							    txjl.setJlid(Long.parseLong(txztList.get(i).get("tacheid")+""));
							    txjl.setXsxh(txztList.get(i).get("stuid")+"");
							    txjl.setWxh(lxtxDao.getOpenIdbyId(txztList.get(i).get("id")+"").get(0).get("wxh")+"");
							    txjl.setBlzt("1");
							    lxtxDao.update(txjl);
					     }
						 if("0".equals(blztf) && "1".equals(blzts) ){
							 //提醒
							 if(!"".equals(list.get(0).get("reason")+"") && !"null".equals(list.get(0).get("reason")+"") && list.get(0).get("reason")+""!=null){
								 WeixinUtils.massPreview("text",lxtxDao.getOpenIdbyId(txztList.get(i).get("id")+"").get(0).get("wxh")+"", "您好,您的离校流程:"+list.get(0).get("tachename")+""+",未办理完成("+list.get(0).get("reason")+""+")");
						    }else{
						    	 WeixinUtils.massPreview("text", lxtxDao.getOpenIdbyId(txztList.get(i).get("id")+"").get(0).get("wxh")+"", "您好,您的离校流程:"+list.get(0).get("tachename")+""+",未办理完成");
						    }
							 //更新数据库
							    LxTxjl txjl = new LxTxjl();
							    txjl.setId(Long.parseLong(txztList.get(i).get("id")+""));
							    txjl.setJlid(Long.parseLong(txztList.get(i).get("tacheid")+""));
							    if(!"".equals(list.get(0).get("reason")+"") && !"null".equals(list.get(0).get("reason")+"") && list.get(0).get("reason")+""!=null){
							    	 txjl.setTxnr("您好,您的离校流程:"+list.get(0).get("tachename")+""+",未办理完成("+list.get(0).get("reason")+""+")");
							    }else{
							    	 txjl.setTxnr("您好,您的离校流程:"+list.get(0).get("tachename")+""+",未办理完成");
							    }
							    txjl.setTxsj(new Date());
							    txjl.setXsxh(txztList.get(i).get("stuid")+"");
							    txjl.setWxh(lxtxDao.getOpenIdbyId(txztList.get(i).get("id")+"").get(0).get("wxh")+"");
							    txjl.setBlzt("0");
							    lxtxDao.update(txjl);
					     }
				    }
			   }
		 }
	}
	 
	/**
	 * @author wangxiangyang
	 * @date 2016年6月7日
	 * @return
	 */
	public List<Map<String,Object>> getGrlxxx(int tacheid,String openId){
		return lxtxDao.getGrlxxx(tacheid,openId);
	}
}
