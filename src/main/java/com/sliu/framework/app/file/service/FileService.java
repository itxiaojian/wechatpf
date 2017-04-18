package com.sliu.framework.app.file.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sliu.framework.app.file.dao.FileDao;
import com.sliu.framework.app.file.model.SysFjgl;

/** 
 * @author:zhangyi 
 * @version 创建时间：2016年6月13日 上午11:44:15 
 * 附件管理
 */
@Service
public class FileService {
	
	@Autowired
	private FileDao fileDao;
	
	@Transactional
	public void saveFile(String data, String wjlx, Long lxid,String fileNameU) {
		SysFjgl fjgl = new SysFjgl();
		fjgl.setContent(data);
		fjgl.setFname(fileNameU);
		fjgl.setLxid(lxid);
		fjgl.setWjlx(wjlx);
		fileDao.save(fjgl);
	}

	@Transactional
	public SysFjgl getfile(Long imgId) {
		return fileDao.get(imgId);
	}
	
	@Transactional
	public List<Map<String, Object>> getImgId(HttpServletRequest request) {
		String fname=request.getParameter("fname");
		return fileDao.getImgId(fname);
	}

}
