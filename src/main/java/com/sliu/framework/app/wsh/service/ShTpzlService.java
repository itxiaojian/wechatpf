package com.sliu.framework.app.wsh.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wsh.dao.ShTpjlDao;
import com.sliu.framework.app.wsh.dao.ShTpzlDao;
import com.sliu.framework.app.wsh.dao.ShTpzlxxDao;
import com.sliu.framework.app.wsh.model.ShTpjl;
import com.sliu.framework.app.wsh.model.ShTpzl;

/**
 * 主页——投票专栏
 * 
 * @author zhangyan
 * @version 创建时间：2016年7月14日 
 */
@Service
public class ShTpzlService extends BaseBO<ShTpzl> {

	@Autowired
	private ShTpzlDao shTpzlDao;
	@Autowired
	private ShTpzlxxDao shTpzlxxDao;
	@Autowired
	private ShTpjlDao shTpjlDao;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	/**
	 * 分页查询投票专栏
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param start
	 * @param limit
	 * @param ztmc  专题名称
	 * @param ztbt  专题标题
	 * @return
	 */
	public Pagination<Map<String, Object>> getTpzlList(Integer start,
			Integer limit) {
		return shTpzlDao.getTpzlList(start, limit);
	}
	
	/**
	 * 查看新闻
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日 
	 * @param id
	 * @return
	 */
	@Transactional
	public ShTpzl getXyxwById(Long id) {
		ShTpzl entity = shTpzlDao.get(id);
		if(entity!=null){
			return entity;
		}
		return null;
	}
	
	/**
	 * 发布投票内容
	 * @author   zhangyan
	 * @version 创建时间：2016年7月14日  
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveTpzl(ShTpzl entity) {
		SysYh yh = AppUtil.getCurrentUser();
//		entity.setBmbh(yh.getBmbh());
//		entity.setFbr(yh.getYhbh());
		entity.setFqr(yh.getXm());
		entity.setZt("1");
		entity.setFbsj(Calendar.getInstance().getTime());
		shTpzlDao.save(entity);
		return "1";
	}
	
	/**
	 * 修改投票内容
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月14日 
	 * @param entity
	 * @param id
	 * @return
	 */
	@Transactional
	public String updateTpzl(ShTpzl entity, Long id) {
		ShTpzl esse = shTpzlDao.get(id);
		esse.setTpnr(entity.getTpnr());
		esse.setJssj(entity.getJssj());
		shTpzlDao.update(esse);
		return "1";
	}


	/**
	 * 删除
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月14日 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		shTpjlDao.deletejl(id);
		shTpzlxxDao.deletexx(id);
		shTpzlDao.delete(id);
		
		
	}
	
	/**
	 * 根据id查找投票内容
	 * @author zhangyan
	 * @date 2016年7月22日
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTpnr(Long id){
		return shTpzlDao.getTpnr(id);
	}
	
	/**
	 * 前台：查询投票列表
	 * 
	 * @author zhangyan
	 * @version 创建时间：2016年7月26日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getList(HttpServletRequest request) {
		
		String pages=request.getParameter("pages");
		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
		return shTpzlDao.getList(pages);
	}
	
	/**
	 * 获取投票详细内容
	 * @author  zhangyan
	 * @version 2016年07月26日
	 * @param zbm
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getDetailById(String id) {
		return shTpzlDao.getDetailById(id);
	}
	
	/**
	 * 保存投票结果
	 * @author zhangyan
	 * @date 2016年6月10日
	 * @param request
	 * @return
	 */
	@Transactional
	public String saveTp(HttpServletRequest request) {
		String errMsg="";
	    String replayIp = "";
	    String openId=request.getParameter("openId");
	    if(openId==null||"".equals(openId)){
	    	replayIp=AppUtil.getIpAddr(request);
	    }else{
	    	replayIp=openId;
	    }
	    String id = request.getParameter("chkRadio");//主题Id
		List<Map<String,Object>> ob = shTpzlDao.getDetailById(id);
//		int zt = Integer.parseInt(ob.get(0).get("zt")+"");
//		if(zt==2)
//		{
//			//errMsg = "该问卷已终止评议，不能提交!";
//			errMsg = "2";
//			//response.sendRedirect("voteFail.jsp?errMsg="+errMsg);
//			return errMsg;
//		}
		if(this.exit(id,replayIp))
	    {
			//errMsg = "您的答案已提交，不能重复提交!";
			errMsg = "1";
			//response.sendRedirect("voteFail.jsp?errMsg="+errMsg);
			return errMsg;
		}
	    
		
		//回复信息
		ShTpjl tpjl = new ShTpjl();
		tpjl.setXxid(Long.parseLong(id));
		tpjl.setTpr(replayIp);
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		tpjl.setTpsj(currentTime);
		
		boolean falg = this.save(tpjl);
		if(falg){
			return errMsg;
		}else{
			return null;
		}
	}
	
	/**
	 * 判断是否存在回复
	 * @param oid
	 * @param code
	 * @return
	 */
	@Transactional
	public boolean exit(String oid,String replayIp) {
		return shTpjlDao.exit(oid, replayIp);
	}
	

	/**
	 * 将回复信息存储到数据库
	 * @author zhangyan
	 * @date 2016年7月27日
	 * @param r 
	 * @param answers 
	 * @return
	 */
	@Transactional
	public boolean save(ShTpjl r) {
		boolean falg= false;
		shTpjlDao.saveRep(r);
			falg=true;
			return falg;
	}
}
