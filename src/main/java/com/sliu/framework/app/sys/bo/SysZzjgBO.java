package com.sliu.framework.app.sys.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.dao.SysAreaDao;
import com.sliu.framework.app.sys.model.SysZzjg;

/**
 * 区域 Business
 * 
 * @author lxt
 * @since 2014-11-19 17:16:17
 * 
 */
@Service
public class SysZzjgBO extends BaseBO<SysZzjg> {

	protected static Logger logger = LoggerFactory
			.getServiceLog(SysZzjgBO.class);
	@Autowired
	private SysAreaDao orgDao;
	
	@Override
	public <K extends Serializable> Result remove(K ids) {
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_sjbh_S_EQ", ids);
		SysZzjg file = dao.getUnique(filter, SysZzjg.class);
		if (file != null) {
			Result result = new Result();
			result.addErrormsg("本区域下有区域名称");
			return result;
		}
		return super.remove(ids);
	}

	public String getAreaToString(List<SysZzjg> sysAreas) {
		StringBuffer sysAreaJson = new StringBuffer();
		sysAreaJson.append("{\"treeNodes\":[");
		if (sysAreas.size() > 0) {
			for (int i = 0; i < sysAreas.size(); i++) {
				if (i < sysAreas.size() - 1) {
					sysAreaJson.append("{\"id\":\"" + sysAreas.get(i).getId()
							+ "\",\"value\":\"" + sysAreas.get(i).getBmbh()
							+ "\",\"name\":\"" + sysAreas.get(i).getBmmc()
							+ "\",\"parentId\":\""
							+ sysAreas.get(i).getSjbh()
							+ "\",\"open\":\"true\"},");
				} else {
					sysAreaJson.append("{\"id\":\"" + sysAreas.get(i).getId()
							+ "\",\"value\":\"" + sysAreas.get(i).getBmbh()
							+ "\",\"name\":\"" + sysAreas.get(i).getBmmc()
							+ "\",\"parentId\":\""
							+ sysAreas.get(i).getSjbh()
							+ "\",\"open\":\"true\"}");
				}
			}
		}
		sysAreaJson.append("]}");
		return sysAreaJson.toString();
	}

	public List<Map<String, Object>> findAttacheArea(String id,boolean isparentId){
		return orgDao.findAttacheArea(id,isparentId);
	}
	
	public List<Map<String,Object>> findOrgArchive(Integer start,Integer limit,String OrgId){
		return orgDao.findOrgArchive(start, limit, OrgId);
	}

	public List<Map<String,Object>> getOrgTree(String node){
		return orgDao.getOrgTree(node);
	}
	
	public List<Map<String,Object>> getYhByjg(String bmbh){
		return orgDao.getYhByjg(bmbh);
	}
	
	public List<Map<String,Object>> getOrgTree1(String node){
		return orgDao.getOrgTree1(node);
	}

	public List<Map<String,Object>> getOrgOrMebTree(String node){
		List<Map<String,Object>> list=orgDao.getOrgTree(node);
		if(list.size()==0){
			list=orgDao.getOrgOrMebTree(node);
		}
		return list;
	}
	
	public List<Map<String, Object>> getYhOrgOrMebTree(String _dc) {
		List<Map<String,Object>> list=orgDao.getYhOrgOrMebTree(_dc);
//		if(list.size()==0){
//			list=orgDao.getOrgOrMebTree(node);
//		}
		return list;
	}
}
