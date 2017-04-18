package com.sliu.framework.app.wzy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.file.dao.FileDao;
import com.sliu.framework.app.file.model.SysFjgl;
import com.sliu.framework.app.wzy.dao.ZyTppzDao;
import com.sliu.framework.app.wzy.model.ZyTppz;

/**
 * 主页轮播图 Service
 * 
 * @author liujiansen
 * @since 2016-03-07
 */

@Service
public class ZyTppzService extends BaseBO<ZyTppz> {
	@Autowired
	private ZyTppzDao dao;
	
	@Autowired
	private FileDao fileDao;

	/**
	 * 查询图片列表信息
	 * 
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param start
	 * @param limit
	 * @param tpmc
	 * @param tplx
	 * @return
	 */
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit,
			String tpmc, String lx) {
		return dao.pager(start, limit, tpmc, lx);
	}

	/**
	 * 获取图片类型
	 * 
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @return
	 */
	public List<Map<String, Object>> getTplx(String zl) {
		return dao.getTplx(zl);
	}

    /**
     * 获取主页轮播图
     * @author:zhangyi 
     * @version 创建时间：2016年6月29日 上午10:09:29 
     * @return
     */
	public List<Map<String, Object>> getListLbt() {
		return dao.getListLbt();
	}
    
	public List<Map<String, Object>> getTpbcidById(Long id) {
		return dao.getTpbcidById(id);
	}

	/**
	 * 上传保存
	 * @author zhangyi
	 * @date 2016年3月7日
	 * @param attachMentFile
	 * @param request
	 * @param response
	 * @return
	 */
    @Transactional
	public void savemag(String data,String fileNameU ,String urltext, Integer px, String tpmc) {
    	SysFjgl fjgl = new SysFjgl();
		fjgl.setContent(data);
		fjgl.setFname(fileNameU);
		fjgl.setLxid(1);
		fjgl.setWjlx("1");
		Long imageid =fileDao.save(fjgl);
		
		ZyTppz entity = new ZyTppz();
		if(urltext != null&& !"".equalsIgnoreCase(urltext.trim())){
			entity.setUrl(urltext);
		}else{
			entity.setUrl("-1");
		}
		entity.setPx(px);
		entity.setLx(1);
		entity.setTpmc(tpmc);
		entity.setTpbcid(imageid);
		dao.save(entity);
	}
    
    /**
	 * 修改
	 * @author zhangyan
	 * @date 2016年6月30日
	 * @param attachMentFile
	 * @param request
	 * @param response
	 * @return
	 */
    @Transactional
   	public void update(String data,String fileNameU ,String urltext, Integer px, String tpmc,String tpbcid,String id) {
    	SysFjgl fjgl=fileDao.get(Long.parseLong(tpbcid));
//    	SysFjgl fjgl = new SysFjgl();
   		fjgl.setContent(data);
   		fjgl.setFname(fileNameU);
   		fileDao.update(fjgl);
   		
   		ZyTppz entity=dao.get(Long.parseLong(id));
//   		ZyTppz entity = new ZyTppz();
   		entity.setUrl(urltext);
   		entity.setPx(px);
   		entity.setLx(1);
   		entity.setTpmc(tpmc);
   		entity.setTpbcid(Long.parseLong(tpbcid));
   		dao.update(entity);
   	}
    
    @Transactional
   	public void update1(String urltext, Integer px, String tpmc,String id) {
   		
   		ZyTppz entity=dao.get(Long.parseLong(id));
   		entity.setUrl(urltext);
   		entity.setPx(px);
   		entity.setLx(1);
   		entity.setTpmc(tpmc);
   		dao.update(entity);
   	}

    /**
     * 删除
     * @author zhangyan
     * @date 2016年6月30日
     * @param id
     * @param tpbcid 
     */
    @Transactional
	public void deleteEntity(Long id,Long tpbcid) {
    	dao.delete(id);
		fileDao.delete(tpbcid);
	}

}
