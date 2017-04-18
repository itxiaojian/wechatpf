package com.sliu.framework.app.sbgl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.dao.SbSbbyjlDao;
import com.sliu.framework.app.sbgl.model.SbSbbyjl;

@Service
public class SbSbbyjlService extends BaseBO<SbSbbyjl>{

	@Autowired
	private SbSbbyjlDao dao;
	
	/**
	 * 后台：查询设备检验记录列表
	 * @author duanpeijun
	 * @date 2015年8月21日
	 * @param start
	 * @param limit
	 * @param sbbh  设备编号
	 * @param sbmc  设备名称
	 * @return
	 */
	public Pagination<Map<String, Object>> getSbwxjlList(Integer start, Integer limit,String code,String jyzt,String sblb){
		
		return dao.getSbwxjlList(start, limit,code,jyzt,sblb);
		}
	
	/**
	 * 后台： 添加设备检验记录
	 * @author duanpeijun
	 * @date 2015年8月21日
	 * @param entity
	 * @return
	 */
	@Transactional
	public String saveSbjyjl(SbSbbyjl entity) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmss");
		String wxdh=sim.format(new Date())+entity.getSbbh();
		entity.setWxdh(wxdh);
		dao.save(entity);
		return "1";
	}
	
	/**
	 * 后台： 修改设备检验记录
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月3日  下午3:57:01
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(SbSbbyjl entity,String id,String wxdh){
		entity.setWxdh(wxdh);
		entity.setId(Long.parseLong(id));
		dao.update(entity);
	}
	/**
	 * 
	 * @author liujiansen
	 * @date 2015年8月24日
	 * @param id
	 * @param zt
	 * @param gzms
	 */
	@Transactional
	public void updateWc(String id,String wxzt,String gzms,String kssj,String wcsj,String bypf) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date start=new Date();
		Date end=new Date();
		try {
			start=sim.parse(kssj.split(",")[0]);
			end=sim.parse(wcsj.split(",")[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SbSbbyjl entity=dao.get(Long.parseLong(id));
		entity.setGzms(gzms.split(",")[0]);
		entity.setBypf(bypf.split(",")[0]);
		entity.setWxzt(wxzt.split(",")[0]);
		entity.setKssj(start);
		entity.setWcsj(end);
		dao.update(entity);
	}
	
	/**
	 * 后台： 删除设备检验记录
	 * @author duanpeijun
	 * @date 2015年8月20日
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		dao.delete(id);
	}
	
}