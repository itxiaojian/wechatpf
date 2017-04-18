package com.sliu.framework.app.bx.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.bx.dao.BxBxshDao;
import com.sliu.framework.app.bx.dao.BxBxwcDao;
import com.sliu.framework.app.bx.model.BxBxspyjb;
import com.sliu.framework.app.bx.model.BxBxsq;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

@Service
public class BxBxwcService extends BaseBO<BxBxsq> {

	@Autowired
	private BxBxwcDao dao;

	@Autowired
	private BxBxshDao bxshDao;

	/**
	 * 总页数
	 * @author oufeng
	 * @throws UnsupportedEncodingException 
	 * @date 2015年8月10日
	 */
	public int getBxwcCount(HttpServletRequest request) throws UnsupportedEncodingException {
		String bm = request.getParameter("bm");
		String wxdl=request.getParameter("wxdl");
		String bxzt=request.getParameter("bxzt");
		if(bm!=null){
	   		bm = new String(bm.getBytes("ISO-8859-1"), "UTF-8"); 
	   		}else{
	   			bm="";
	   		}
	   		if(bxzt!=null){
	   	   		bxzt = new String(bxzt.getBytes("ISO-8859-1"), "UTF-8"); 
	   	   		}else{
	   	   			bxzt="";
	   	   		}
	   		if(wxdl!=null){
	   	   		wxdl = new String(wxdl.getBytes("ISO-8859-1"), "UTF-8"); 
	   	   		}else{
	   	   			wxdl="";
	   	   		}
		return dao.getBxwcCount(bm,wxdl,bxzt);
	}

	/**
	 * 报修处理的数据
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getBxwc(String bm,String wxdl,String bxzt, String pages) {
		return dao.getBxwc(bm.trim(), wxdl,bxzt,pages);
	}

	/**
	 * 报修处理的查看
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getSee(String id) {
		Integer idInt = Integer.parseInt(id);
		return dao.getSee(idInt);
	}

	/**
	 * 报修处理结束保存到报修意见表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccCon(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		String id = request.getParameter("id");
		dao.toSuccWxjl(Integer.parseInt(id));
		dao.toSuccSp(Integer.parseInt(id));
		// dao.toSuccSpyj(Integer.parseInt(id));
	}

	/**
	 * 报修处理结束保存审批意见表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccSpyjCon(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		String id = request.getParameter("id");
		BxBxspyjb bxspyjb = new BxBxspyjb();
		bxspyjb.setBxid(Long.parseLong(id));
		bxspyjb.setZt("8");
		bxspyjb.setSftg("0");
		bxspyjb.setSpsj(new Date());
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		bxspyjb.setSpyj("结束");
		bxshDao.save(bxspyjb);
	}

	/**
	 * 批量报修处理结束保存或更新维修表和审批表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccCheckBox(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		String idList = request.getParameter("idList");
		String[] ids = idList.trim().split(",");
		for (int i = 0; i < ids.length; i++) {
			dao.toSuccWxjl(Integer.parseInt(ids[i]));
			dao.toSuccSp(Integer.parseInt(ids[i]));
		}
	}

	/**
	 * 批量报修处理结束保存到报修意见表
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSuccSpyjCheckBox(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		String idList = request.getParameter("idList");
		String[] ids = idList.trim().split(",");
		BxBxspyjb bxspyjb;
		for (int i = 0; i < ids.length; i++) {
			 bxspyjb = new BxBxspyjb();
			bxspyjb.setBxid(Long.parseLong(ids[i]));
			bxspyjb.setZt("8");
			bxspyjb.setSftg("0");
			bxspyjb.setSpsj(new Date());
			bxspyjb.setSprbh(yhbh);
			bxspyjb.setSprxm(yhxm);
			bxspyjb.setSpyj("结束");
			bxshDao.save(bxspyjb);
		}
	}

	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toRejecCon(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();

		// BxBxspyjb bxspyjb = new BxBxspyjb();
		String id = request.getParameter("id");
		// str 驳回理由
		String str = request.getParameter("str");
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.toRejecWxjl(Integer.parseInt(id));
		dao.toRejecSp(Integer.parseInt(id));
		// dao.toRejecSpyj(Integer.parseInt(id),yhbh,yhxm,new Date(),str);
	}

	/**
	 * 报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toRejecSpyjCon(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		BxBxspyjb bxspyjb = new BxBxspyjb();
		String id = request.getParameter("id");
		// str 驳回理由
		String str = request.getParameter("str");
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bxspyjb.setBxid(Long.parseLong(id));
		bxspyjb.setZt("7");
		bxspyjb.setSftg("1");
		bxspyjb.setSpsj(new Date());
		bxspyjb.setSprbh(yhbh);
		bxspyjb.setSprxm(yhxm);
		bxspyjb.setSpyj(str);
		bxshDao.save(bxspyjb);
	}

	/**
	 * 批量的报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toRejecCheckBox(HttpServletRequest request,
			HttpServletResponse response) {
		String idList = request.getParameter("idList");
		String[] ids = idList.trim().split(",");
		for (int i = 0; i < ids.length; i++) {
			dao.toRejecWxjl(Integer.parseInt(ids[i]));
			dao.toRejecSp(Integer.parseInt(ids[i]));
			// dao.toRejecSpyj(Integer.parseInt(ids[i]),yhbh,yhxm,new
			// Date(),str);
		}
	}

	/**
	 * 批量的报修驳回
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toRejecSpyjCheckBox(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		BxBxspyjb bxspyjb;
		// str 驳回理由
		String str = request.getParameter("str");
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String idList = request.getParameter("idList");
		String[] ids = idList.trim().split(",");
		for (int i = 0; i < ids.length; i++) {
			 bxspyjb = new BxBxspyjb();
			bxspyjb.setBxid(Long.parseLong(ids[i]));
			bxspyjb.setZt("7");
			bxspyjb.setSftg("1");
			bxspyjb.setSpsj(new Date());
			bxspyjb.setSprbh(yhbh);
			bxspyjb.setSprxm(yhxm);
			bxspyjb.setSpyj(str);
			bxshDao.save(bxspyjb);
			// dao.toRejecSpyj(Integer.parseInt(ids[i]),yhbh,yhxm,new
			// Date(),str);
		}
	}

	/**
	 * 根据维修大类获得报修的主题
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getBxzt(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			id = new String(id.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.getBxzt(id);
	}

	/**
	 * 获得维修大类的数据字典
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getWxdl() {
		return dao.getWxdl();
	}
}
