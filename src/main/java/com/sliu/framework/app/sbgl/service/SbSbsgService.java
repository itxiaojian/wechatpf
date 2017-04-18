package com.sliu.framework.app.sbgl.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.dao.SbSbsgDao;
import com.sliu.framework.app.sbgl.dao.SbSbsgyjDao;
import com.sliu.framework.app.sbgl.dao.SbxxDao;
import com.sliu.framework.app.sbgl.model.SbSbsgxx;
import com.sliu.framework.app.sbgl.model.SbSbsgyjb;
import com.sliu.framework.app.sbgl.model.SbSbxx;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;

@Service
public class SbSbsgService extends BaseBO<SbSbsgxx> {

	@Autowired
	private SbSbsgDao sbSbsgDao;

	@Autowired
	private SbSbsgyjDao sbSbsgyjDao;

	@Autowired
	private SbxxDao sbxxDao;

	/**
	 * 后台：查询设备申购表
	 * 
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbsgList(Integer start,
			Integer limit, String code, String sblb, String sqzt) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		return sbSbsgDao.getSbsgList(start, limit, yhbh, code, sblb, sqzt);
	}

	/**
	 * 后台：查询设备申购部门审批
	 * 
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param gjz
	 *            关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbsgBmList(Integer start,
			Integer limit, String code, String sblb, String sqzt) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		return sbSbsgDao.getSbsgBmspList(start, limit, yhbh, code, sblb, sqzt);
	}

	/**
	 * 后台：查询设备申购领导审批
	 * 
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param gjz
	 *            关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbsgLdList(Integer start,
			Integer limit, String code, String sblb, String sqzt) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		return sbSbsgDao.getSbsgLdspList(start, limit, yhbh, code, sblb, sqzt);
	}

	/**
	 * 后台：查询设备申购财务审批
	 * 
	 * @author oufeng
	 * @date 2015年8月20日
	 * @param start
	 * @param limit
	 * @param gjz
	 *            关键字
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbsgCwList(Integer start,
			Integer limit, String code, String sblb) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		return sbSbsgDao.getSbsgCwspList(start, limit, yhbh, code, sblb);
	}

	/**
	 * 编辑
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日
	 * @param entity
	 * @param id
	 *            主键ID
	 */
	@Transactional
	public void update(SbSbsgxx entity, String id) {
		entity.setId(Long.parseLong(id));
		sbSbsgDao.update(entity);
	}

	/**
	 * 删除
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			sbSbsgDao.delete(ids[i]);
		}
	}

	/**
	 * 根据字典种类找到菜单
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日 下午3:57:48
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getBmByLx(String zdzl) {
		return sbSbsgDao.getBmByLx(zdzl);
	}

	/**
	 * 查看审批意见
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getSbsgCheck(String id) {
		return sbSbsgDao.getSbsgCheck(id);
	}

	/**
	 * 获得状态
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日 下午3:57:48
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getZt() {
		return sbSbsgDao.getZt();
	}

	/**
	 * 获得申购状态(字典表)
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日 下午3:57:48
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getSqzt() {
		return sbSbsgDao.getSqzt();
	}

	/**
	 * 获得部门状态(字典表)
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日 下午3:57:48
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getBmzt() {
		return sbSbsgDao.getBmzt();
	}

	/**
	 * 获得申请状态(字典表)
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日 下午3:57:48
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getLdzt() {
		return sbSbsgDao.getLdzt();
	}

	/**
	 * 根据字典种类找到菜单
	 * 
	 * @author oufeng
	 * @version 创建时间：2015年8月20日 下午3:57:48
	 * @param string
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getSgr() {
		return sbSbsgDao.getSgr();
	}

	/**
	 * 设备部门审批的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSaveSpyjBm(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		// spyj 审批意见
		String spyj = request.getParameter("spyj");
		try {
			spyj = new String(spyj.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SbSbsgyjb sbSbsgyjb = new SbSbsgyjb();
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgyjb.setSgid(Long.parseLong(ids[i]));
			sbSbsgyjb.setSftg("0");
			sbSbsgyjb.setSpsj(new Date());
			sbSbsgyjb.setSpyj(spyj);
			sbSbsgyjb.setSphj("1");
			sbSbsgyjb.setSprbh(yhbh);
			sbSbsgyjb.setSpzt("4");
			sbSbsgyjb.setSprxm(yhxm);
			sbSbsgyjDao.save(sbSbsgyjb);
		}
	}

	/**
	 * 设备部门审批的更新申购信息表
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toUpdateSbsgBm(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgDao.toUpdateSbsgBm(Integer.parseInt(ids[i]));
		}
	}

	/**
	 * 设备部门审批驳回的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSaveSpyjBmBh(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		// spyj 审批意见
		String spyj = request.getParameter("spyj");
		try {
			spyj = new String(spyj.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SbSbsgyjb sbSbsgyjb = new SbSbsgyjb();
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgyjb.setSgid(Long.parseLong(ids[i]));
			sbSbsgyjb.setSftg("1");
			sbSbsgyjb.setSpsj(new Date());
			sbSbsgyjb.setSpyj(spyj);
			sbSbsgyjb.setSphj("1");
			sbSbsgyjb.setSpzt("3");
			sbSbsgyjb.setSprbh(yhbh);
			sbSbsgyjb.setSprxm(yhxm);
			sbSbsgyjDao.save(sbSbsgyjb);
		}
	}

	/**
	 * 设备部门审批的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toUpdateSbsgBmBh(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgDao.toUpdateSbsgBmBh(Integer.parseInt(ids[i]));
		}
	}

	/**
	 * 设备领导审批的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSaveSpyjLd(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		// spyj 审批意见
		String spyj = request.getParameter("spyj");
		try {
			spyj = new String(spyj.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SbSbsgyjb sbSbsgyjb = new SbSbsgyjb();
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgyjb.setSgid(Long.parseLong(ids[i]));
			sbSbsgyjb.setSftg("0");
			sbSbsgyjb.setSpsj(new Date());
			sbSbsgyjb.setSpyj(spyj);
			sbSbsgyjb.setSphj("1");
			sbSbsgyjb.setSpzt("6");
			sbSbsgyjb.setSprbh(yhbh);
			sbSbsgyjb.setSprxm(yhxm);
			sbSbsgyjDao.save(sbSbsgyjb);
		}
	}

	/**
	 * 设备领导审批的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toUpdateSbsgLd(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgDao.toUpdateSbsgLd(Integer.parseInt(ids[i]));
		}
	}

	/**
	 * 设备领导审批驳回的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSaveSpyjLdBh(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		// spyj 审批意见
		String spyj = request.getParameter("spyj");
		try {
			spyj = new String(spyj.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SbSbsgyjb sbSbsgyjb = new SbSbsgyjb();
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgyjb.setSgid(Long.parseLong(ids[i]));
			sbSbsgyjb.setSftg("1");
			sbSbsgyjb.setSpsj(new Date());
			sbSbsgyjb.setSpyj(spyj);
			sbSbsgyjb.setSphj("2");
			sbSbsgyjb.setSpzt("5");
			sbSbsgyjb.setSprbh(yhbh);
			sbSbsgyjb.setSprxm(yhxm);
			sbSbsgyjDao.save(sbSbsgyjb);
		}
	}

	/**
	 * 设备领导审批的驳回保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toUpdateSbsgLdBh(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgDao.toUpdateSbsgLdBh(Integer.parseInt(ids[i]));
		}
	}

	/**
	 * 设备财务审批的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSaveSpyjCw(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		// spyj 审批意见
		String spyj = request.getParameter("spyj");
		try {
			spyj = new String(spyj.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SbSbsgyjb sbSbsgyjb = new SbSbsgyjb();
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgyjb.setSgid(Long.parseLong(ids[i]));
			sbSbsgyjb.setSftg("0");
			sbSbsgyjb.setSpsj(new Date());
			sbSbsgyjb.setSpyj(spyj);
			sbSbsgyjb.setSphj("1");
			sbSbsgyjb.setSpzt("8");
			sbSbsgyjb.setSprbh(yhbh);
			sbSbsgyjb.setSprxm(yhxm);
			sbSbsgyjDao.save(sbSbsgyjb);
		}
	}

	/**
	 * 设备部门审批驳回的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toSaveSpyjCwBh(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		// spyj 审批意见
		String spyj = request.getParameter("spyj");
		try {
			spyj = new String(spyj.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SbSbsgyjb sbSbsgyjb = new SbSbsgyjb();
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgyjb.setSgid(Long.parseLong(ids[i]));
			sbSbsgyjb.setSftg("1");
			sbSbsgyjb.setSpsj(new Date());
			sbSbsgyjb.setSpyj(spyj);
			sbSbsgyjb.setSphj("3");
			sbSbsgyjb.setSpzt("7");
			sbSbsgyjb.setSprbh(yhbh);
			sbSbsgyjb.setSprxm(yhxm);

			sbSbsgyjDao.save(sbSbsgyjb);
		}
	}

	/**
	 * 设备财务审批的保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toUpdateSbsgCw(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgDao.toUpdateSbsgCw(Integer.parseInt(ids[i]));
		}
	}

	/**
	 * 设备财务审批的驳回保存
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public void toUpdateSbsgCwBh(HttpServletRequest request,
			HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		String yhbh = yh.getYhbh();
		String yhxm = yh.getXm();
		// 设备的申购的id
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			sbSbsgDao.toUpdateSbsgCwBh(Integer.parseInt(ids[i]));
		}
	}

	/**
	 * 获取用户编号
	 * 
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String, Object>> getBmbh(String yhbh) {
		return sbSbsgDao.getBmbh(yhbh);
	}

	/**
	 * 设备申购的保存
	 * 
	 * @author oufeng
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 * @date 2015年8月11日
	 */
	@Transactional
	public void saveSbxx(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			UnsupportedEncodingException {
		String id = request.getParameter("id");
		String sbmc = request.getParameter("sbmc");
		sbmc = new String(sbmc.getBytes("ISO-8859-1"), "UTF-8");
		String sbxh = request.getParameter("sbxh");
		sbxh = new String(sbxh.getBytes("ISO-8859-1"), "UTF-8");
		String cgsl = request.getParameter("cgsl");
		String sblb = request.getParameter("sblb");
		sblb = new String(sblb.getBytes("ISO-8859-1"), "UTF-8");
		String sccj = request.getParameter("sccj");
		sccj = new String(sccj.getBytes("ISO-8859-1"), "UTF-8");
		String bmmc = request.getParameter("bmmc");
		String sybm = request.getParameter("sybm");
		sybm = new String(sybm.getBytes("ISO-8859-1"), "UTF-8");
		String xm = request.getParameter("xm");
		String sgrq = request.getParameter("sgrq");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(sgrq);
		SbSbxx sbxx = new SbSbxx();
		sbxx.setSblb(sblb);
		sbxx.setSbmc(sbmc);
		sbxx.setSbxh(sbxh);
		sbxx.setSbjb("");
		sbxx.setSybm(sybm);
		sbxx.setSyfw("");
		sbxx.setSccj(sccj);
		sbxx.setCcbh("");
		// sbxx.setCcrq();
		sbxx.setGmrq(date);
		sbxxDao.save(sbxx);
	}
}
