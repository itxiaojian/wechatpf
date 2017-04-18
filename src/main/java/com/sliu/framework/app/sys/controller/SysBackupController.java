package com.sliu.framework.app.sys.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.Pager;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.SettingUtil;
import com.likegene.framework.core.formvalidator.FormValidatorManager;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.bo.SysBackupBO;
import com.sliu.framework.app.sys.model.BackUpBean;
import com.sliu.framework.app.sys.model.SysBackup;
import com.sliu.framework.app.util.AppUtil;

/**
 * sys_backup Controller
 * 
 * @author lxt
 * @since 2014-12-08 10:35:36
 */
@Controller
@RequestMapping("/sys/SysBackup")
public class SysBackupController extends BaseController {

	protected final transient static Logger logger = LoggerFactory
			.getPresentationLog(SysBackupController.class);

	@Autowired
	private SysBackupBO sysBackupBO;

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return "/sys/SysBackup/add";
	}

	@RequestMapping(value = "/detail")
	public String detail(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysBackup", sysBackupBO.get(id));
		return "/sys/SysBackup/detail";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		int start = filter.getStart();
		int pageSize = filter.getLimit();
		String backPath = SettingUtil.getSetting("db.backupfolder", null)
				.toString();
		String filename = request.getParameter("Q_fileName_S_LK");
		String backtime = request.getParameter("Q_backupTime_D_GT");
		File file = new File(backPath);
		String[] fileNames = file.list();
		List<BackUpBean> list = new ArrayList<BackUpBean>();
		if (fileNames != null) {
			list = this.getFileList(fileNames, filename, backtime);
		}
		int rowCount = list.size();
		List<BackUpBean> listFile = new ArrayList<BackUpBean>();
		for (int i = start; i < (start + pageSize); i++) {
			if (i < list.size()) {
				listFile.add(list.get(i));
			}
		}
		Pager pager = new Pager(start, pageSize);
		pager.setRowCount(rowCount);
		pager.setItems(listFile);
		model.put("pager", pager);
		return "/sys/SysBackup/list";
	}

	public List<BackUpBean> getFileList(String[] files, String filename,
			String backtime) {
		List<BackUpBean> list = new ArrayList<BackUpBean>();
		for (String file : files) {
			if (filename != null && !"".equals(filename) && backtime != null
					&& !"".equals(backtime)) {
				if (file.contains(filename) && file.contains(backtime)) {
					BackUpBean bean = new BackUpBean();
					bean.setFileName(file);
					String str = file.substring(6, 20);
					SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
					Date time = new Date();
					try {
						time = sf.parse(str);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bean.setBackTime(time);
					list.add(bean);
				}
			} else if (filename != null && !"".equals(filename)) {
				if (file.contains(filename)) {
					BackUpBean bean = new BackUpBean();
					bean.setFileName(file);
					String str = file.substring(6, 20);
					SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
					Date time = new Date();
					try {
						time = sf.parse(str);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bean.setBackTime(time);
					list.add(bean);
				}
			} else if (backtime != null && !"".equals(backtime)) {
				if (file.contains(backtime)) {
					BackUpBean bean = new BackUpBean();
					bean.setFileName(file);
					String str = file.substring(6, 20);
					SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
					Date time = new Date();
					try {
						time = sf.parse(str);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bean.setBackTime(time);
					list.add(bean);
				}
			} else {
				BackUpBean bean = new BackUpBean();
				bean.setFileName(file);
				String str = file.substring(6, 20);
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date time = new Date();
				try {
					time = sf.parse(str);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				bean.setBackTime(time);
				list.add(bean);
			}
		}
		return list;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ModelMap model,
			@ModelAttribute("sysBackup") SysBackup entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysBackupConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysBackup/add";
		}
		Result result = sysBackupBO.save(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysBackup/add";
		}
		return "/common/success";
	}

	@RequestMapping(value = "/backUp")
	public String backUp(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();
		String backPath = SettingUtil.getSetting("db.backupfolder", null)
				.toString();
		String dbBackup = SettingUtil.getSetting("db.backup", null).toString();
		Date time = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String backName = "backup" + sf.format(time) + ".sql";
		File file = new File(backPath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		try {
			Runtime rt = Runtime.getRuntime();
			String cmd = String.format(dbBackup, backPath + backName); // 备份
			logger.debug("backUp", cmd);
			Process process = rt.exec(cmd);
			InputStreamReader inputStr = new InputStreamReader(
					process.getErrorStream());
			BufferedReader br = new BufferedReader(inputStr);
			String errorString = "";
			String line = "";
			while ((line = br.readLine()) != null) {
				errorString += line;
			}

			result.put("msg", errorString);
			if (errorString.isEmpty()) {
				result.put("code", "1");
			} else {
				result.put("code", "0");
				file = new File(backPath + backName);
				if (file.exists()) {
					file.delete();
				}
			}
		} catch (Exception e) {
			result.put("code", "0");
			result.put("msg", e.getMessage());
		}
		writeJson(response, AppUtil.toJsonExclusion(result, HashMap.class));
		return "/common/json";
	}

	@RequestMapping(value = "/regain")
	public String regain(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();
		String fileName = request.getParameter("fileName");
		String backPath = SettingUtil.getSetting("db.backupfolder", null)
				.toString();
		String dbBackup = SettingUtil.getSetting("db.recover", null).toString();
		String backFilePath = backPath + fileName;
		try {
			Runtime rt = Runtime.getRuntime();
			String cmd = String.format(dbBackup, backFilePath);// 还原
			logger.debug("regain", cmd);
			Process process = rt.exec(cmd);
			InputStreamReader inputStr = new InputStreamReader(
					process.getErrorStream(), "gbk");
			BufferedReader br = new BufferedReader(inputStr);
			String errorString = "";
			String line = "";
			while ((line = br.readLine()) != null) {
				errorString += line;
			}
			result.put("msg", errorString);
			if (errorString.isEmpty()) {
				result.put("code", "1");
			} else {
				result.put("code", "0");
			}
		} catch (Exception e) {
			result.put("code", "0");
			result.put("msg", e.getMessage());
		}
		writeJson(response, AppUtil.toJsonExclusion(result, HashMap.class));
		return "/common/json";
	}

	@RequestMapping(value = "/ajaxTest", method = RequestMethod.GET)
	public String ajaxTest(ModelMap model, HttpServletRequest request) {

		model.put("json", "1");
		return "/common/json";
	}

	@RequestMapping(value = "/delete")
	public String delete(String[] ids) {
		for (String id : ids) {
			SysBackup back = sysBackupBO.get(id);
			String path = "";
			if (back != null) {
				path = back.getPath() + back.getFileName();
			}

			sysBackupBO.remove(id);
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
		}
		return "/common/success";
	}

	@RequestMapping(value = "/edit")
	public String edit(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysBackup", sysBackupBO.get(id));
		return "/sys/SysBackup/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ModelMap model,
			@ModelAttribute("sysBackup") SysBackup entity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> errors = FormValidatorManager.validate(
				"saveSysBackupConfig", request);
		if (errors.size() != 0) {
			return "/sys/SysBackup/edit";
		}
		Result result = sysBackupBO.update(entity);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysBackup/edit";
		}
		return "/common/success";
	}
}
