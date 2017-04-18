package com.sliu.framework.app.sbgl.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sbgl.dao.SbSbwxjlDao;
import com.sliu.framework.app.sbgl.model.SbSbwxjl;

@Service
public class SbSbwxjlService extends BaseBO<SbSbwxjl>{

	@Autowired
	private SbSbwxjlDao dao;
	
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
	public String saveSbjyjl(SbSbwxjl entity,String wxdw,String wxfzr,String wxsqr) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmss");
		String wxdh=sim.format(new Date())+entity.getSbbh();
		entity.setWxdh(wxdh);
//		entity.setWxdw(wxdw.split(",")[0]);
//		entity.setWxfzr(wxfzr.split(",")[0]);
//		entity.setWxsqr(wxsqr.split(",")[0]);
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
	public void update(SbSbwxjl entity,String id,String wxdh,String wxdw,String wxfzr,String wxsqr){
		entity.setWxdh(wxdh);
//		entity.setWxdw(wxdw.split(",")[0]);
//		entity.setWxfzr(wxfzr.split(",")[0]);
//		entity.setWxsqr(wxsqr.split(",")[0]);
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
	public void updateWc(String id,String wxzt,String gzfxjcl,String kssj,String wcsj,String wxfy) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date start=new Date();
		Date end=new Date();
		try {
			start=sim.parse(kssj.split(",")[0]);
			end=sim.parse(wcsj.split(",")[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SbSbwxjl entity=dao.get(Long.parseLong(id));
		entity.setGzfxjcl(gzfxjcl.split(",")[0]);
		entity.setWxfy(new BigDecimal(wxfy.split(",")[0]));
		entity.setWxzt(wxzt.split(",")[0]);
		entity.setKssj(start);
		entity.setWcsj(end);
		if("2".equals(wxzt.split(",")[0])){
			dao.updateJh(entity.getSbbh(),entity.getGzfxjcl());
		}
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