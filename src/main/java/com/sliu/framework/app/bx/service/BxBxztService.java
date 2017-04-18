package com.sliu.framework.app.bx.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.bx.dao.BxBxztDao;
import com.sliu.framework.app.bx.model.BxBxzt;

/**
 * 报修主题后台配置
 * @author oufeng
 * @since 2015-08-06 13:16:17
 */
@Service
public class BxBxztService extends BaseBO<BxBxzt> {

	protected static Logger logger = LoggerFactory
			.getServiceLog(BxBxztService.class);
	
	@Autowired
	private BxBxztDao ztdao;

	public List<Map<String,Object>> getOrgOrMebTree(String node){
		List<Map<String,Object>> list=ztdao.getOrgTree(node);
		if(list.size()==0){
//			list=ztdao.getOrgOrMebTree(node);
		}
		return list;
	}

	/**
	 * 更新
	 * @author:oufeng
	 * @return
	 */
	@Transactional
	public String update(HttpServletRequest request){
		String str="";
		String id =request.getParameter("id");
		String dm = request.getParameter("dm");
		String bz = request.getParameter("bz");
		String name = request.getParameter("name");
		String pid = request.getParameter("pid");
		String px = request.getParameter("px");
		/* List<Map<String, Object>> list =zzjgDao.checkData(id,dm,name);
		 if(list.size()!=0){
			 str="2";
		 }else{*/
		ztdao.updateZzjg(id,pid,name,dm,px,bz);
			str="1";
		/* }*/
		return str;
	}

	
	/**
	 *新增
	 * @author:oufeng
	 * @return
	 */
	@Transactional
	public String save(HttpServletRequest request){
		String str="";
		String jb="";
		String id="0";
		//String id =request.getParameter("id");
		String dm = request.getParameter("dm");
		String bz = request.getParameter("bz");
		String name = request.getParameter("name");
		String sjbh = request.getParameter("sjbh");
		String pid = request.getParameter("pid");
		String px = request.getParameter("px");
		if("".equals(pid)){pid="0";}
		List<Map<String, Object>> jblist = ztdao.getJb(pid);
		List<Map<String, Object>> idList = ztdao.getMaxId();
		if(jblist.size()!=0){
			jb=jblist.get(0).get("jb").toString();
		}
		if(idList.size()!=0){
			id=idList.get(0).get("id").toString();
		}
	/*	 List<Map<String, Object>> list =bxztDao.checkData(id,dm,name);
		 if(list.size()!=0){
			 str="2";
		 } else{*/
			 BxBxzt bxzt =new BxBxzt();
	        bxzt.setBz(bz);
	        bxzt.setPx(Long.parseLong(px));
	        bxzt.setSjzt(sjbh);
	        bxzt.setZtjb(Integer.parseInt(jb));
	        bxzt.setZtbh(dm);
	        bxzt.setTjsj(new Date());
	        bxzt.setZtmc(name);
	        bxzt.setId(Long.parseLong(id)+1);
	        ztdao.save(bxzt);
			 str="1";
		// }
		return str;
	}
	
	/**
	 * 删除
	 * @author:oufeng
	 * @return
	 */
	@Transactional
	public String delete(HttpServletRequest request){
		String str="";
		String id =request.getParameter("id");
		ztdao.delete(Long.parseLong(id));
		str="1";
		return str;
	}

	
	@Override
	public <K extends Serializable> Result remove(K ids) {
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_sjzt_S_EQ", ids);
		BxBxzt file = dao.getUnique(filter, BxBxzt.class);
		if (file != null) {
			Result result = new Result();
			result.addErrormsg("本区域下有区域名称");
			return result;
		}
		return super.remove(Long.parseLong(ids+""));
	}

	public String getBxztToString(List<BxBxzt> bxzt) {
		StringBuffer bxztJson = new StringBuffer();
		bxztJson.append("{\"treeNodes\":[");
		if (bxzt.size() > 0) {
			for (int i = 0; i < bxzt.size(); i++) {
				if (i < bxzt.size() - 1) {
					bxztJson.append("{\"id\":\"" + bxzt.get(i).getId()
							+ "\",\"value\":\"" + bxzt.get(i).getZtbh()
							+ "\",\"name\":\"" + bxzt.get(i).getZtmc()
							+ "\",\"parentId\":\""
							+ bxzt.get(i).getSjzt()
							+ "\",\"open\":\"true\"},");
				} else {
					bxztJson.append("{\"id\":\"" + bxzt.get(i).getId()
							+ "\",\"value\":\"" + bxzt.get(i).getZtbh()
							+ "\",\"name\":\"" + bxzt.get(i).getZtmc()
							+ "\",\"parentId\":\""
							+ bxzt.get(i).getSjzt()
							+ "\",\"open\":\"true\"}");
				}
			}
		}
		bxztJson.append("]}");
		return bxztJson.toString();
	}
	
	/**
	 * 获得维修大类的数据字典
	 * @author oufeng
	 * @date 2015年8月11日
	 */
	@Transactional
	public List<Map<String,Object>>getMaxId(){
		return ztdao.getMaxId();
	}

	public List<Map<String, Object>> findAttacheArea(String id,boolean isparentId){
		return ztdao.findAttacheArea(id,isparentId);
	}
	
}
