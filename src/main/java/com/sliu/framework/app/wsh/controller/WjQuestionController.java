//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wsh.service.WjObjectService;
import com.sliu.framework.app.wsh.service.WjQuestionService;
import com.sliu.framework.app.wsh.service.WjReplayService;
import com.sliu.framework.app.wsh.service.WjSelecterService;

/**
 * 
 * @author liujiasen
 * @date 2015年6月4日
 */
@Controller
@RequestMapping("/wsh/WjQuestion")
public class WjQuestionController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(WjQuestionController.class);
	
	@Autowired
	private WjObjectService wjObjectService;
	
	@Autowired
	private WjSelecterService ss;
	
	@Autowired
	private WjQuestionService qs;
	
	@Autowired
	private WjReplayService rs;
	
	/**
	 * 问卷调查问题列表
	 * @author liujiasen
	 * @date 2015年6月9日
	 * @return
	 */
	@RequestMapping(value="/toQuesList")
   	public ModelAndView toQuesList(String id){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/quesList");
   		wjObjectService.deleteHd(id);
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
   		int oid = Integer.parseInt(id);
   		List<Map<String,Object>> ob = wjObjectService.findObjectBeanByID(id);
   		if(ob.size()!=0){
   			mav.addObject("ob", ob.get(0));
   		}
   		
   		List<Map<String,Object>> quesList = qs.litQuesByOid(oid);
   		if(quesList.size()!=0){
   			mav.addObject("quesList", quesList);
   			mav.addObject("size", quesList.size());
   		}
   		
   		List<Map<String,Object>> selList = ss.listSelecterByOid(oid);
   		if(selList.size()!=0){
   			mav.addObject("selList", selList);
   		}
   		return mav;
   	}
	
	/**
	 * 问卷调查页面
	 * @author liujiasen
	 * @date 2015年6月9日
	 * @return
	 */
	@RequestMapping(value="/toDcwj")
   	public ModelAndView toDcwj(String id,HttpServletRequest request){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/preview");
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
   		String openId=request.getParameter("openId");
   		int oid = Integer.parseInt(id);
   		List<Map<String,Object>> ob = wjObjectService.findObjectBeanByID(id);
   		if(ob.size()!=0){
   			mav.addObject("ob", ob.get(0));
   		}
   		mav.addObject("openId", openId);
   		List<Map<String,Object>> quesList = qs.litQuesByOid(oid);
   		//List<Map<String,Object>> txtQuesList = qs.getTxtQByOid(oid);
   		if(quesList.size()!=0){
   			mav.addObject("quesList", quesList);
   			//mav.addObject("size", (quesList.size()+txtQuesList.size()-1));
   			mav.addObject("size", quesList.size());
   		}
   		
   		List<Map<String,Object>> selList = ss.listSelecterByOid(oid);
   		if(selList.size()!=0){
   			mav.addObject("selList", selList);
   		}
   		return mav;
   	}
	
	/**
	 * 新增题目
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param id 问卷编号
	 * @return
	 */
	@RequestMapping(value="/toAddQues")
   	public ModelAndView toAddQues(String id){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/newQues");
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
		int oid = Integer.parseInt(id);
		List<Map<String,Object>> list=wjObjectService.findObjectBeanByID(id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
		int state = Integer.parseInt(list.get(0).get("state")+"");
		if (state == 1 || state == 2) {
	    //清空回复表中的数据
	    rs.delReplay(oid);
	    //修改问卷状态为草稿
	    rs.updateObj(oid);
	  }
   		return mav;
   	}
	
	/**
	 * 保存问题
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("oid");
		int oid = Integer.parseInt(id);
		int count = wjObjectService.getCount(oid);
		int seq = 1;
		String content = request.getParameter("content");
		if(content!=null&&content.trim().length()>0){
			//content=new String(content.getBytes("iso8859-1"),"UTF-8");
			}
		String selCount = request.getParameter("listCnt");
		int listCnt = Integer.parseInt(selCount);
		String type=request.getParameter("qtype");
		int qtype = Integer.parseInt(type);
		if (count == 0) {
			//往题目表中插入题目
			int insertQues = qs.addQues(oid, content, qtype, seq);
			if (insertQues > 0) {
				//插入选项数据
				for (int i = 1; i <= listCnt; i++) {
					String name = String.valueOf("txt" + i);
					String value = request.getParameter(name);
					//value=new String(value.getBytes("iso8859-1"),"UTF-8");
					ss.addSelecter(oid, seq, value, i);
				}
			}
		} else {
			//往题目表中插入题目
			int addQues = qs.addQues(oid, content, qtype, (count + 1));
			if (addQues  > 0) {
				//插入选项数据
				for (int i = 1; i <= listCnt; i++) {
			String name = String.valueOf("txt" + i);
			String value = request.getParameter(name);
			//value=new String(value.getBytes("iso8859-1"),"UTF-8");
			ss.addSelecter(oid, (count + 1), value, i);
				}
			}
		}
		ModelAndView mav=new ModelAndView("/wsh/wjdc/quesList");
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
   		List<Map<String,Object>> ob = wjObjectService.findObjectBeanByID(id);
   		if(ob.size()!=0){
   			mav.addObject("ob", ob.get(0));
   		}
   		
   		List<Map<String,Object>> quesList = qs.litQuesByOid(oid);
   		if(quesList.size()!=0){
   			mav.addObject("quesList", quesList);
   			mav.addObject("size", quesList.size());
   		}
   		
   		List<Map<String,Object>> selList = ss.listSelecterByOid(oid);
   		if(selList.size()!=0){
   			mav.addObject("selList", selList);
   		}
   		return mav;
	}
	
	/**
	 * 插入题目
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param id 问卷编号
	 * @return
	 */
	@RequestMapping(value="/toAdd")
   	public ModelAndView toAdd(String id,String seq){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/addQues");
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
   		if(seq!=null&&!"".equals(seq)){
   			mav.addObject("seq", seq);
   		}
		int oid = Integer.parseInt(id);
		List<Map<String,Object>> list=wjObjectService.findObjectBeanByID(id);
   		if(list.size()!=0){
   			mav.addObject("map", list.get(0));
   		}
		int state = Integer.parseInt(list.get(0).get("state")+"");
		if (state == 1 || state == 2) {
	    //清空回复表中的数据
	    rs.delReplay(oid);
	    //修改问卷状态为草稿
	    rs.updateObj(oid);
	  }
   		return mav;
   	}
	
	/**
	 * 插入问题
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/saveQes")
	public ModelAndView saveQes(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("oid");
		String seq = request.getParameter("seq");
		int oid = Integer.parseInt(id);
		
		int qseq = Integer.parseInt(seq);
		String content=request.getParameter("content");
		String selCount = request.getParameter("listCnt");
		int listCnt = Integer.parseInt(selCount);
		String type=request.getParameter("qtype");
		int qtype = Integer.parseInt(type);
		int count = wjObjectService.getCount(oid);
		//如果题目的最大顺序号小于所传进来的顺序号 则先修改顺序号再进行插入 否则直接插入
		if (count > qseq) {
			//修改问题表中题目的顺序
			int updateQuesOrder = qs.updateQuesOrder(oid, qseq);
			if (updateQuesOrder > 0) {
			//修改选项表中题目的顺序
			int updateSseq = ss.updateSelecterSeq(oid, qseq);
				if (updateSseq > 0) {
			//往题目表中插入题目
					int insertQues = qs.addQues(oid,content, qtype, (qseq+1));
					if (insertQues > 0) {
					//插入选项数据
						for (int i = 1; i <= listCnt; i++) {
							String name = String.valueOf("txt" + i);
							String value = request.getParameter(name);
							ss.addSelecter(oid, (qseq+1), value, i);
						}
					}
				}
			}
		} else {

			//往题目表中插入题目
			int addQues = qs.addQues(oid,content, qtype, (qseq+1));
			if (addQues > 0) {
		//插入选项数据
				for (int i = 1; i <= listCnt; i++) {
					String name = String.valueOf("txt" + i);
					String value = request.getParameter(name);
					ss.addSelecter(oid, (qseq+1), value, i);
				}
			}
		}
		ModelAndView mav=new ModelAndView("/wsh/wjdc/quesList");
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
   		List<Map<String,Object>> ob = wjObjectService.findObjectBeanByID(id);
   		if(ob.size()!=0){
   			mav.addObject("ob", ob.get(0));
   		}
   		
   		List<Map<String,Object>> quesList = qs.litQuesByOid(oid);
   		if(quesList.size()!=0){
   			mav.addObject("quesList", quesList);
   			mav.addObject("size", quesList.size());
   		}
   		
   		List<Map<String,Object>> selList = ss.listSelecterByOid(oid);
   		if(selList.size()!=0){
   			mav.addObject("selList", selList);
   		}
   		return mav;
	}
	
	/**
	 * 编辑题目
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param id 问卷编号
	 * @return
	 */
	@RequestMapping(value="/toUpdate")
   	public ModelAndView toUpdate(String id,String seq){
   		ModelAndView mav=new ModelAndView("/wsh/wjdc/updateQues");
		int oid = Integer.parseInt(id);
		int qseq = Integer.parseInt(seq);
		List<Map<String,Object>> ques=qs.getQuesBySeq(qseq,oid);
		if(ques.size()!=0){
			mav.addObject("map", ques.get(0));
		}
		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
   		if(seq!=null&&!"".equals(seq)){
   			mav.addObject("seq", seq);
   		}
		List<Map<String,Object>> list=wjObjectService.findObjectBeanByID(id);
		List<Map<String,Object>> selList=ss.listSelecterBySeq(qseq+"",oid);
		if(selList.size()!=0){
			mav.addObject("selList", selList);
		}
		int state = Integer.parseInt(list.get(0).get("state")+"");
		if (state == 1 || state == 2) {
		    //清空回复表中的数据
		    rs.delReplay(oid);
		    //修改问卷状态为草稿
		    rs.updateObj(oid);
		}
   		return mav;
   	}
	
	/**
	 * 编辑题目
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param id 问卷编号
	 * @return
	 */
	@RequestMapping(value="/update")
   	public ModelAndView update(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("oid");
		int oid = Integer.parseInt(id);
		String qseq = request.getParameter("seq");
		int seq = Integer.parseInt(qseq);
		int delSel = ss.deleteSelecter(seq, oid);
		if (delSel > 0) {
			int deleteQues = qs.deleteQues(seq, oid);
			if (deleteQues > 0) {
				String content = request.getParameter("content");
				//content=new String(content.getBytes("iso8859-1"),"UTF-8");
				int qtype = Integer.parseInt(request.getParameter("qtype"));
				int addQues = qs.addQues(oid, content, qtype, seq);
				if (addQues > 0) {
			//插入选项数据
			int listCnt = Integer.parseInt(request
					.getParameter("listCnt"));
			for (int i = 1; i <= listCnt; i++) {
				String name = String.valueOf("txt_" + i);
				String value = request.getParameter(name);
				//value=new String(value.getBytes("iso8859-1"),"UTF-8");
				ss.addSelecter(oid, seq, value, i);
			}
				}
			}
		}
		ModelAndView mav=new ModelAndView("/wsh/wjdc/quesList");
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
   		List<Map<String,Object>> ob = wjObjectService.findObjectBeanByID(id);
   		if(ob.size()!=0){
   			mav.addObject("ob", ob.get(0));
   		}
   		
   		List<Map<String,Object>> quesList = qs.litQuesByOid(oid);
   		if(quesList.size()!=0){
   			mav.addObject("quesList", quesList);
   			mav.addObject("size", quesList.size());
   		}
   		
   		List<Map<String,Object>> selList = ss.listSelecterByOid(oid);
   		if(selList.size()!=0){
   			mav.addObject("selList", selList);
   		}
   		return mav;
   	}
	
	/**
	 * 删除题目
	 * @author liujiansen
	 * @date 2015年6月9日
	 * @param id 问卷编号
	 * @return
	 */
	@RequestMapping(value="/delete")
   	public ModelAndView delete(String id,String seq){
		int oid = Integer.parseInt(id);
		int qseq = Integer.parseInt(seq);
		List<Map<String, Object>> ob = wjObjectService.findObjectBeanByID(id);
		int count = qs.getQuesCount(oid);
		int state = Integer.parseInt(ob.get(0).get("state") + "");
		if (state == 1 || state == 2) {
			// 清空回复表中的数据
			rs.delReplay(oid);
			// 修改问卷状态为草稿
			rs.updateObj(oid);
		}
		ModelAndView mav=new ModelAndView("/wsh/wjdc/quesList");
   		if(id!=null&&!"".equals(id)){
   			mav.addObject("oid", id);
   		}
   		if(ob.size()!=0){
   			mav.addObject("ob", ob.get(0));
   		}
   		
   		List<Map<String,Object>> quesList = qs.litQuesByOid(oid);
   		if(quesList.size()!=0){
   			mav.addObject("quesList", quesList);
   			mav.addObject("size", quesList.size());
   		}
   		
   		List<Map<String,Object>> selList = ss.listSelecterByOid(oid);
   		if(selList.size()!=0){
   			mav.addObject("selList", selList);
   		}
		// 如果题目顺序等于１的情况下执行以下代码
		if (qseq == 1) {
			if (count == 1) {
				int flags = ss.deleteSelecter(qseq, oid);// 删除选项
				if (flags > 0) {
					qs.deleteQues(qseq, oid);// 删除问题
				}
			} else {
				int flags = ss.deleteSelecter(qseq, oid);// 删除选项
				if (flags > 0) {
					int flagq = qs.deleteQues(qseq, oid);// 删除问题
					if (flagq > 0) {
						int updateQseq = qs.updateQseq(qseq, oid);// 修改问题顺序
						if (updateQseq > 0) {
							ss.updateSseq(qseq, oid);// 修改选项顺序
						}
					}
				}
			}
		}
		// 如果题目顺序小于题目总数并且不等于１的情况下执行以下代码
		if (qseq < count && qseq != 1) {
			int flags = ss.deleteSelecter(qseq, oid);
			if (flags > 0) {
				int flagq = qs.deleteQues(qseq, oid);
				if (flagq > 0) {
					int updateQseq = qs.updateQseq(qseq, oid);
					if (updateQseq > 0) {
						ss.updateSseq(qseq, oid);
					}
				}
			}
		}
		// 如果题目的顺序等于题目的总数的情况下执行以下代码
		if (qseq == count) {
			int flags = ss.deleteSelecter(qseq, oid);
			if (flags > 0) {
				qs.deleteQues(qseq, oid);
			}
		}
		return mav;
	}
	
}
