//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.general.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.util.ChartUtil;
import com.sliu.framework.app.wsh.service.WjObjectService;
import com.sliu.framework.app.wsh.service.WjQuestionService;
import com.sliu.framework.app.wsh.service.WjReplayService;
import com.sliu.framework.app.wsh.service.WjSelecterService;

/**
 * 
 * @author liujiasen
 * @date 2015年6月8日
 */
@Controller
@RequestMapping("/wsh/WjReplay")
public class WjReplsyController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WjReplsyController.class);
	
	@Autowired
	private WjObjectService wjObjectService;
	
	@Autowired
	private WjSelecterService ss;
	
	@Autowired
	private WjQuestionService qs;
	
	@Autowired
	private WjReplayService rs;
	
	/**
	 * 问卷调查信息列表
	 * @author liujiasen
	 * @date 2015年6月8日
	 * @return
	 */
	@RequestMapping(value="/toWjjgBt")
   	public ModelAndView toWjjgBt(String id,HttpServletRequest request,
			HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/wjjgBt");
   		int oid = Integer.parseInt(id);
   		//WjObjectService wj = new WjObjectService();
   		//回复总数
   		int rcount = rs.getAnswerCount(oid);
   		
   		mav.addObject("rcount", rcount);
   		mav.addObject("id", id);
   		
   		List<Map<String,Object>> quesList =qs.litQuesByOid(oid);
   		Map<Integer, List<Map<Integer, Integer>>> allCount = rs.getAllAnswer(oid);
   		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
   		if (quesList != null && quesList.size() > 0) {
			for (int i = 0; i < quesList.size(); i++) {
				Map<String,Object> mp=new HashMap<String, Object>();
				Map<String,Object> ques = quesList.get(i);
				int seq = Integer.parseInt(ques.get("seq")+"");
				int qtype = Integer.parseInt(ques.get("qtype")+"");
				List<Map<Integer, Integer>> clist = allCount.get(i + 1);
				//double rcount_d = (double) clist.get(0).get(0);
				String p = "";
				String p1 = "";
				if(qtype!=3){
					
					DefaultPieDataset dataset = new DefaultPieDataset();
					//List<Map<String,Object>> selList = ss.listSelecterBySeq(seq, oid);
					List<Map<String,Object>> selList = ss.listSelecterBySeq(seq+"", oid);
					int count = 0;
					for (int j = 0; j < selList.size(); j++) {
						Map<String,Object> sel = selList.get(j);
						//int selseq = Integer.parseInt(sel.get("selseq")+"");
						count = clist.get(j + 1).get(j + 1);
						double count_d = (double) count;
						//double result = MyTool.division(count_d, rcount_d);
						dataset.setValue(sel.get("content")+"", new Double(count_d));
					}
					
					try {
						p = ChartUtil.generatePieChart(dataset, "图"+ (i + 1), 400, 200, null, new PrintWriter(response.getWriter()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					p1 = request.getContextPath()
							+ "/servlet/DisplayChart?filename=" + p;
					mp.put("p1", p1);
				}
				mp.put("seq", seq);
				mp.put("qtype", qtype);
				mp.put("content", ques.get("content")+"");
				res.add(mp);
			}
   		}
   		if(res.size()!=0){
   			mav.addObject("res", res);
   		}
   		List<Map<String,Object>> list=wjObjectService.findObjectBeanByID(id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
   		return mav;
   	}
	
	/**
	 * 保存调查问卷结果
	 * @author liujiansen
	 * @date 2015年6月10日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveWj")
	@ResponseBody
	public String saveWj(HttpServletRequest request) {
		return rs.saveWj(request);
	}
	
	/**
	 * 问答题结果列表
	 * @author liujiasen
	 * @date 2015年6月23日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toYjList")
   	public ModelAndView toYjList(HttpServletRequest request,HttpServletResponse response){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/yjList");
   		String oid=request.getParameter("oid");
   		String qSeq=request.getParameter("seq");
   		List<Map<String,Object>> list=rs.getAnswers(oid, qSeq);
   		if(list.size()!=0){
   			mav.addObject("list", list);
   		}
   		return mav;
	}
}
