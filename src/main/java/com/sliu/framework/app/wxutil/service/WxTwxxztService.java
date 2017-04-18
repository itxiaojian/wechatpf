package com.sliu.framework.app.wxutil.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.file.dao.FileDao;
import com.sliu.framework.app.file.model.SysFjgl;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wfw.dao.ZsZggzDao;
import com.sliu.framework.app.wxutil.dao.WxTwxxnrDao;
import com.sliu.framework.app.wxutil.dao.WxTwxxztDao;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;
import com.sliu.framework.app.wxutil.model.WxTwxxnr;
import com.sliu.framework.app.wxutil.model.WxTwxxzt;


/**
 * 图文消息主体
 * @author : zhangyi
 * @version 创建时间：2016年9月6日 上午11:17:25
 */
@Service
public class WxTwxxztService {

	@Autowired
	private WxTwxxztDao wxTwxxztDao;
	
	@Autowired
	private WxTwxxnrDao wxTwxxnrDao;
	
	@Autowired
	private ZsZggzDao zsZggzDao;
	
	@Autowired
	private FileDao fileDao;
	
	/**
	 * 分页查询图文消息 
	 * @author  zhangyi
	 * @version 创建时间：2016年4月18日  下午3:15:31
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTwxxnrList(Integer start, Integer limit,Long ztid){
		
		return wxTwxxnrDao.getTwxxnrList(start, limit,ztid);
		
	}
	
	/**
	 * 分页查询图文消息 
	 * @author chenhui
	 * @version 创建时间：2016年4月18日  下午3:15:31
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Map<String, Object>> getTwxxnr(int ztid){
		
		return wxTwxxnrDao.getTwxxnr(ztid);
		
	}
	
	
	public List<Map<String,Object>> getTwxxbt(int id){
		return wxTwxxztDao.getTwxxbt(id);
	}

	/**
	 * 保存消息主体
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月19日 上午11:07:25 
	 * @param entity
	 * @param fileNameU 
	 * @param filename 
	 * @param data 
	 */
	@Transactional
	public String saveNews(WxTwxxnr entity, String data, String filename, String fileNameU) {
		Long fileid = null ;
		if(data!=null&&!"".equals(data)){
			SysFjgl fjgl = new SysFjgl();
			fjgl.setContent(data);
			fjgl.setFname(fileNameU);
			fjgl.setLxid((long) 1);
			fileid =fileDao.save(fjgl);
		}
		entity.setSltid(fileid);
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String thistime = sim.format(new Date());
		entity.setTjsj(thistime);
		wxTwxxnrDao.save(entity);
		return "1";
	}
	
	/**
	 * 修改消息内容（包含缩略图）
	 * @author:zhangyi 
	 * @version 创建时间：2016年9月7日 下午2:41:51 
	 * @param entity
	 * @param data
	 * @param filename
	 * @param fileNameU
	 * @return
	 */
	@Transactional
	public String updateNewsAll(WxTwxxnr entity, String data, String filename,String fileNameU) {
		Long fileid = null ;
		if(data!=null&&!"".equals(data)){
			SysFjgl fjgl = new SysFjgl();
			fjgl.setContent(data);
			fjgl.setFname(fileNameU);
			fjgl.setLxid((long) 1);
			fileid =fileDao.save(fjgl);
		}
		WxTwxxnr twxxnr = wxTwxxnrDao.get(entity.getId());
		twxxnr.setPx(entity.getPx());
		twxxnr.setSltid(fileid);
		twxxnr.setXxbt(entity.getXxbt());
		twxxnr.setXxnr(entity.getXxnr());
		//twxxnr.setZtid(entity.getZtid());
		
		wxTwxxnrDao.update(twxxnr);
		
		fileDao.delete(entity.getSltid());
		return "1";
	}
	
	/**
	 * 单独修改消息内容
	 * @author:zhangyi 
	 * @version 创建时间：2016年9月7日 下午2:51:25 
	 * @param entity
	 * @return
	 */
	@Transactional
	public String updateNewsOnly(WxTwxxnr entity) {
		WxTwxxnr twxxnr = wxTwxxnrDao.get(entity.getId());
		twxxnr.setPx(entity.getPx());
		twxxnr.setXxbt(entity.getXxbt());
		twxxnr.setXxnr(entity.getXxnr());
		//twxxnr.setZtid(entity.getZtid());
		
		wxTwxxnrDao.update(twxxnr);
		return "1";
	}
	
	/**
	 * 删除消息内容
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:17:36
	 * @param ids
	 */
	@Transactional
	public void deleteNews(Long[] ids) {
		for(int i=0;i<ids.length;i++){
			WxTwxxnr twxxnr = wxTwxxnrDao.get(ids[i]);
			fileDao.delete(twxxnr.getSltid());
			wxTwxxnrDao.delete(ids[i]);
		}
	}
	
	/**
	 * 分页查询图文消息主体 
	 * @author  zhangyi
	 * @version 创建时间：2016年4月18日  下午3:15:31
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTwxxztList(Integer start, Integer limit){
		
		return wxTwxxztDao.getTwxxztList(start, limit);
		
	}
	
	
	/**
	 * 增加
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:25:44
	 * @param entity  
	 */
	@Transactional
	public void save(WxTwxxzt entity) {
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String thistime = sim.format(new Date());
		SysYh yh = AppUtil.getCurrentUser();
		entity.setTjrxm(yh.getXm());
		entity.setTjrbh(yh.getYhbh());
		entity.setTjsj(thistime);
		
		wxTwxxztDao.save(entity);
	}
	
	/**
	 * 编辑
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:17:01
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(WxTwxxzt entity,String id){
		WxTwxxzt twxxzt = wxTwxxztDao.get(entity.getId());
		twxxzt.setXxbt(entity.getXxbt());
		twxxzt.setXxsm(entity.getXxsm());
		wxTwxxztDao.update(twxxzt);
	}
	
	/**
	 * 删除
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:17:36
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++){
			wxTwxxztDao.delete(ids[i]);
		}
	}

	/**
	 * 以模版消息方式发送图文消息
	 * @author:zhangyi 
	 * @version 创建时间：2016年9月7日 下午5:23:24 
	 * @param response 
	 * @param request 
	 * @param string
	 * @param content
	 */
	@Transactional
	public void sentTwxx(String openId, String twxxid, HttpServletRequest request, HttpServletResponse response) {
		SysYh yh = AppUtil.getCurrentUser();
		//WxTwxxzt twxx = wxTwxxztDao.get(Long.parseLong(twxxid));
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String thistime = sim.format(new Date());
		String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
		String url = basePath+"/weixin/twxx/twxxDetail?id="+twxxid;//进入微主页
		
		String toUser = openId;
		WxTemplate temp = new WxTemplate();
		temp.setTemplate_id("O1NwAoBNo2eqDuyGDNckd1TMufmdjq-8sXRBsRh9ous");
		//temp.setTemplate_id("3GGw481dbPiqR0p4ggFqxkbI0OkzZ06KFDlLU_FEO8w");
		temp.setTopcolor("#000000");
		temp.setTouser(toUser);
		temp.setUrl(url);
		Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
		//提醒
		TemplateDataView dataView1 = new TemplateDataView();
		dataView1.setColor("#FF6600");
		dataView1.setValue("您好，您有新的图文消息，请点击查看。");
		mapdata.put("first", dataView1);
		//内容
		TemplateDataView dataView2 = new TemplateDataView();
		dataView2.setColor("#000000");
		dataView2.setValue("有关学校的图文消息，请及时点击查看！");
		mapdata.put("keyword1", dataView2);
		//系统
		TemplateDataView dataView3 = new TemplateDataView();
		dataView3.setColor("#000000");
		dataView3.setValue("安商掌上校园微信服务系统。");
		mapdata.put("keyword2", dataView3);
		//发送人
		TemplateDataView dataView4 = new TemplateDataView();
		dataView4.setColor("#000000");
		dataView4.setValue(yh.getXm());
		mapdata.put("keyword3", dataView4);
		//时间
		TemplateDataView dataView5 = new TemplateDataView();
		dataView5.setColor("#000000");
		dataView5.setValue(thistime);
		mapdata.put("keyword4", dataView5);
		//备注
		TemplateDataView dataView6 = new TemplateDataView();
		dataView6.setColor("#99CC00");
		dataView6.setValue("  ★   请点击查看详细内容，谢谢您的使用！  ★");
		mapdata.put("remark", dataView6);
		temp.setData(mapdata);
		WeixinUtils.sendTemplateMessage(toUser,temp);
	}

}
