package com.sliu.framework.app.wxutil.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wfw.dao.ZsZggzDao;
import com.sliu.framework.app.wxutil.dao.WxTemplateDataDao;
import com.sliu.framework.app.wxutil.dao.WxTemplateNewsDao;
import com.sliu.framework.app.wxutil.model.TemplateData;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;
import com.sliu.framework.app.wxutil.model.WxTemplateNews;


/**
 * 微信模版消息主体
 * @author : zhangyi
 * @version 创建时间：2016年4月19日 上午9:32:19
 */
@Service
public class WxTemplateService {

	@Autowired
	private WxTemplateDataDao wxTemplateDao;
	
	@Autowired
	private WxTemplateNewsDao wxTemplateNewsDao;
	
	@Autowired
	private ZsZggzDao zsZggzDao;
	
	/**
	 * 分页查询 
	 * @author  zhangyi
	 * @version 创建时间：2016年4月18日  下午3:15:31
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTemplateNewsList(Integer start, Integer limit){
		
		return wxTemplateNewsDao.getTemplateList(start, limit);
		
	}
	
	/**
	 * 保存消息主体
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月19日 上午11:07:25 
	 * @param entity
	 */
	@Transactional
	public void saveNews(WxTemplateNews entity) {
		entity.setAddtime(new Date());
		wxTemplateNewsDao.save(entity);
	}

	/**
	 * 修改消息主体
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月19日 上午11:12:18 
	 * @param entity
	 * @param id
	 */
	@Transactional
	public void updateNews(WxTemplateNews entity, String id) {
		entity.setId(Long.parseLong(id));
		entity.setAddtime(new Date());
		wxTemplateNewsDao.update(entity);
	}
	
	/**
	 * 删除消息主体
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:17:36
	 * @param ids
	 */
	@Transactional
	public void deleteNews(Long[] ids) {
		for(int i=0;i<ids.length;i++){
			wxTemplateNewsDao.delete(ids[i]);
		}
	}
	
	/**
	 * 分页查询模版消息主体 
	 * @author  zhangyi
	 * @version 创建时间：2016年4月18日  下午3:15:31
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTemplateList(Integer start, Integer limit,Long templateId){
		
		return wxTemplateDao.getTemplateList(start, limit,templateId);
		
	}
	
	/**
	 *  查看微信模板实例信息
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:25:37
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTemplateList(){
		return wxTemplateDao.getCx();
	}
	
	/**
	 * 增加
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:25:44
	 * @param entity  
	 */
	@Transactional
	public void save(TemplateData entity) {
		wxTemplateDao.save(entity);
	}
	
	/**
	 * 编辑
	 * @author   zhangyi
	 * @version 创建时间：2016年4月18日  下午3:17:01
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(TemplateData entity,String id){
		entity.setId(Long.parseLong(id));
		wxTemplateDao.update(entity);
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
			wxTemplateDao.delete(ids[i]);
		}
	}

	/**
	 * 发送模版消息
	 * @author:zhangyi 
	 * @version 创建时间：2016年4月21日 上午11:03:11 
	 * @param toUser
	 * @param content
	 * @param msgtype
	 */
	@Transactional
	public void massTemplatenews(String toUser, String content, String msgtype) {
		WxTemplate temp = new WxTemplate();
		Long templateId= Long.parseLong(content.trim());
		WxTemplateNews news = wxTemplateNewsDao.get(templateId);
		if(news!=null){
			temp.setTemplate_id(news.getTemplateId());
			temp.setTopcolor("#"+news.getTopcolor());
			temp.setTouser(toUser);
			temp.setUrl(news.getUrl());
			
			Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
			List<Map<String,Object>> datas = wxTemplateDao.getTemplateDateBytem(templateId);
			if(datas.size()>0){
//				for(int i=0;i<datas.size();i++){
//					TemplateDataView dataView = new TemplateDataView();
//					dataView.setColor(datas.get(i).get("color").toString());
//					dataView.setValue(datas.get(i).get("value").toString());
//					if(i==0){
//						mapdata.put("first",dataView);
//					}else if(i>0&&i<datas.size()-1){
//						mapdata.put("keyword"+i,dataView);
//					}else if(i==datas.size()-1){
//						mapdata.put("remark",dataView);
//					}
//				}
				for(Map<String,Object> map:datas){
					TemplateDataView dataView = new TemplateDataView();
					dataView.setColor("#"+map.get("color").toString());
					dataView.setValue(map.get("value").toString());
					mapdata.put(map.get("bz").toString(),dataView);
				}
			}
			temp.setData(mapdata);
		}
		WeixinUtils.sendTemplateMessage(toUser,temp);
	}
	
	/**
	 * 定时发送工资提醒消息
	 * @author:zhangyi 
	 * @version 创建时间：2016年5月10日 上午10:52:43
	 */
	@Transactional
	public void sendWagesNews(){
		List<Map<String,Object>> listWage = zsZggzDao.getGrgzForSendNews();
		String url = "";
		List<Map<String,Object>> listlj = zsZggzDao.getDict("xtlj");
		if(listlj.size()>0){
			url = listlj.get(0).get("zdz").toString();
		}
		if(listWage.size()>0){
			for(Map<String,Object> map:listWage){
				String toUser = map.get("wxh").toString();
				WxTemplate temp = new WxTemplate();
				temp.setTemplate_id("13BuPu7fazbmBTi5d5XUWaBmAux0PVOnH-tAvYYeing");
				temp.setTopcolor("#000000");
				temp.setTouser(toUser);
				temp.setUrl(url+toUser);
				Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
				//提醒
				TemplateDataView dataView1 = new TemplateDataView();
				dataView1.setColor("#FF6600");
				dataView1.setValue("您好，您这个月的总工资为 "+map.get("sfgz").toString()+"元，请注意查收。");
				mapdata.put("first", dataView1);
				//发放日期
				TemplateDataView dataView2 = new TemplateDataView();
				dataView2.setColor("#000000");
				dataView2.setValue(" "+map.get("nf").toString()+" 年 "+map.get("yf").toString()+" 月");
				mapdata.put("keyword1", dataView2);
				//发放金额
				TemplateDataView dataView3 = new TemplateDataView();
				dataView3.setColor("#000000");
				dataView3.setValue(" "+map.get("sfgz").toString()+" 元");
				mapdata.put("keyword2", dataView3);
				//具体详情
				TemplateDataView dataView4 = new TemplateDataView();
				dataView4.setColor("#000000");
				dataView4.setValue(" 基本工资为 "+map.get("jbgz").toString()+"元,补助工资为 "+map.get("bzgz").toString()+"元,合计为 "+map.get("sfgz").toString()+"元");
				mapdata.put("keyword3", dataView4);
				//备注
				TemplateDataView dataView5 = new TemplateDataView();
				dataView5.setColor("#99CC00");
				dataView5.setValue("  ★  查看详细请点击，祝工作愉快！  ★");
				mapdata.put("remark", dataView5);
				temp.setData(mapdata);
				WeixinUtils.sendTemplateMessage(toUser,temp);
			} 
		}
	}

}
