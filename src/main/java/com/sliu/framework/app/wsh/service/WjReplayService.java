package com.sliu.framework.app.wsh.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.dao.WjReplayDao;
import com.sliu.framework.app.wsh.model.WjAnswer;
import com.sliu.framework.app.wsh.model.WjReplay;


@Service
public class WjReplayService {

	@Autowired
	private WjReplayDao dao;
	
	@Autowired
	private WjObjectService wjObjectService;
	
	/**
	 * 获取所有问题回答情况
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @param oid 问卷编号
	 * @return
	 */
	@Transactional
	public Map<Integer,List<Map<Integer,Integer>>> getAllAnswer(int oid) {
		return dao.getAllAnswer(oid);
	}
	
	@Transactional
	public List<Map<String,Object>> getAnswers(String oid,String qSeq)  {
		return dao.getAnswers(oid, qSeq);
	}
	
	/**
	 * 得到问题总数
	 * @param con
	 * @param oid
	 * @return
	 */
	@Transactional
	public int getQuesCount(int oid) {
		return dao.getQuesCount(oid);
	}
	
	/**
	 * 得到选项总数
	 * @param con
	 * @param oid
	 * @return
	 */
	@Transactional
	public int getSelCount(int oid, int qSeq) {
		return dao.getSelCount(oid, qSeq);
	}
	
	/**
	 * 根据主题ID查询回复总数
	 * @param oid 主题ID
	 * @return
	 */
	@Transactional
	public int getAnswerCount(int oid) {
		return dao.getAnswerCount(oid);
	}
	
	/**
	 * 根据主题Id和题目序号查询该问题的回答数
	 * @param con 数据库连接
	 * @param oid 主题Id
	 * @param qSeq 题目序号
	 * @return 回答数
	 */
	@Transactional
	public int getAnswerCount(int oid,int qSeq) {
		return dao.getAnswerCount(oid, qSeq);
	}
	
	/**
	 * 根据主题Id和题目序号和选项序号查询该问题选项的回答数
	 * @param con 数据库连接
	 * @param oid 主题Id
	 * @param qSeq 题目序号
	 * @param seSeq 选项序号
	 * @return 问题选项的回答数
	 */
	@Transactional
	public int getAnswerCount(int oid,int qSeq,int seSeq) {
		return dao.getAnswerCount(oid, qSeq, seSeq);
	}
	
	/**
	 * 将回复信息存储到数据库
	 * @author liujiansen
	 * @date 2015年6月10日
	 * @param r 回复人信息
	 * @param answers 调查问卷问题答案信息
	 * @return
	 */
	@Transactional
	public boolean save(WjReplay r,List<WjAnswer> answers) {
		boolean falg= false;
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		r.setReplaytime(currentTime);
		dao.saveRep(r);
		List<Map<String,Object>> list=dao.getRepId(r.getOid(), r.getReplayip(), r.getReplaycode());
		if(list.size()!=0){
			String replayId=list.get(0).get("replayid")+"";
			for(int i=0;i<answers.size();i++){
				WjAnswer a = answers.get(i);
				a.setRemark("");
				dao.saveAns(replayId, a);
			}
			falg=true;
			return falg;
		}else{
			return false;
		}
	}
	
	// 删除问卷回复情况
	@Transactional
	public void delReplay(int oid){
		dao.delAns(oid);
		dao.delRep(oid);
	}
	
	@Transactional
	public void updateObj(int oid){
		dao.updateObj(oid);
	}
	
	/**
	 * 判断是否存在回复
	 * @param oid
	 * @param code
	 * @return
	 */
	@Transactional
	public boolean exit(int oid,String replayIp,String replayCode) {
		return dao.exit(oid, replayIp, replayCode);
	}

	/**
	 * 保存回复信息
	 * @author liujiansen
	 * @date 2015年6月10日
	 * @param request
	 * @return
	 */
	public String saveWj(HttpServletRequest request) {
		String errMsg="";
		String replayCode = "";
	    String replayIp = "";
	    String openId=request.getParameter("openId");
	    if(openId==null||"".equals(openId)){
	    	replayIp=AppUtil.getIpAddr(request);
	    }else{
	    	replayIp=openId;
	    }
	    String id = request.getParameter("id");//主题Id
		int oid = Integer.parseInt(id);
		//ObjectBean ob = ObjectBeanService.findObjectBeanByID(oid);
		List<Map<String,Object>> ob = wjObjectService.findObjectBeanByID(id);
		if("1".equals(ob.get(0).get("anonymousFlag")))
		{
			replayCode = "anonymous";//匿名
		}
		if("0".equals(ob.get(0).get("anonymousFlag")))
		{
			if(openId!=null||!"".equals(openId)){
				String userName = dao.getUser(openId).get(0).get("nickname")+"";
				replayCode = userName;
			}
		}
		int status = Integer.parseInt(ob.get(0).get("state")+"");
		if(status==2)
		{
			//errMsg = "该问卷已终止评议，不能提交!";
			errMsg = "2";
			//response.sendRedirect("voteFail.jsp?errMsg="+errMsg);
			return errMsg;
		}
		if(this.exit(oid,replayIp,replayCode))
	    {
			//errMsg = "您的答案已提交，不能重复提交!";
			errMsg = "1";
			//response.sendRedirect("voteFail.jsp?errMsg="+errMsg);
			return errMsg;
		}
	    
		Enumeration e = request.getParameterNames();
		List lname = new LinkedList();
		List<WjAnswer> answers = new ArrayList<WjAnswer>();
		Map map = new HashMap();
		while (e.hasMoreElements()) {//取得所有参数名
			String key = (String) e.nextElement();
			if (key.startsWith("sub") || key.startsWith("oid"))
				key = (String) e.nextElement();
			String ln2 = key;//截取参数的后两个字符，以利于参数的正确排序
			map.put(ln2, key);
			lname.add(ln2);
			Collections.sort(lname);
		}
		
		//回复信息
		WjReplay replay = new WjReplay();
		replay.setReplaycode(replayCode);
		replay.setReplayip(replayIp);
		replay.setOid(oid);
		replay.setRemark("");
		
		//取得参数的值
		for (int i = 0; i < lname.size(); i++) {
			
			String name = (String) map.get(lname.get(i));
			
			//判断是否为复选框
			if (name.startsWith("check")) {
				String checkbox[] = request.getParameterValues(name);
				if (checkbox != null) {
					int size = checkbox.length;
					String s = "";
					for (int j = 0; j < size; j++) {
					WjAnswer answer = new WjAnswer();
					answer.setOid(oid);
					answer.setQseq(Integer.parseInt(name.substring(name.lastIndexOf("_")+1)));
					String cValue = checkbox[j];
					int seSeq = Integer.parseInt(cValue);
					answer.setSeseq(seSeq);
					answer.setSevalue(cValue);
					answers.add(answer);
				  }
				}
			} else if(name.startsWith("radio") || name.startsWith("select")) {
				WjAnswer answer = new WjAnswer();
				answer.setOid(oid);
				answer.setQseq(Integer.parseInt(name.substring(name.lastIndexOf("_")+1)));
				String value = request.getParameter(name);
				int seSeq = Integer.parseInt(value);
				answer.setSeseq(seSeq);
				answer.setSevalue(value);
				answers.add(answer);
			}else if(name.startsWith("txt")) {
				String[] arr=name.split("_");
				WjAnswer answer = new WjAnswer();
				answer.setOid(oid);
				answer.setQseq(Integer.parseInt(arr[1]));
				String value = request.getParameter(name);
				answer.setSeseq(Integer.parseInt(arr[2]));
				answer.setSevalue(value);
				answers.add(answer);
			}
		}
		
		boolean falg = this.save(replay,answers);
		if(falg){
			return errMsg;
		}else{
			return null;
		}
	}
}
