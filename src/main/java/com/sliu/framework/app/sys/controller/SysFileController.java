package com.sliu.framework.app.sys.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.Pager;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.SettingUtil;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.likegene.framework.core.query.QueryFilter;
import com.sliu.framework.app.sys.bo.SysFileBO;
import com.sliu.framework.app.sys.model.SysFile;
import com.sliu.framework.app.util.AppUtil;

/**
 * 文件管理 Controller
 * 
 * @author lxt
 * @since 2014-11-10 14:25:23
 */
@Controller
@RequestMapping("/sys/SysFile")
public class SysFileController extends BaseController {

	protected final transient static Logger logger = LoggerFactory
			.getPresentationLog(SysFileController.class);

	@Autowired
	private SysFileBO sysFileBO;

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return "/sys/SysFile/add";
	}

	@RequestMapping(value = "/detail")
	public String detail(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysFile", sysFileBO.get(id));
		return "/sys/SysFile/detail";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("Q_userId_S_EQ", AppUtil.getCurrentUser().getYhbh());
		filter.addSorted("createTime", "desc");
		Pager pager = sysFileBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysFile/list";
	}

	@RequestMapping(value = "/selectList")
	public String selectList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("Q_userId_S_EQ", AppUtil.getCurrentUser().getYhbh());
		filter.addSorted("createTime", "desc");
		Pager pager = sysFileBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysFile/select_list";
	}

	@RequestMapping(value = "/attachmentList")
	public String attachmentList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		// filter.addFilter("Q_userId_S_EQ", AppUtil.getCurrentUser().getId());
		if (!StringUtils.equals("1", request.getParameter("all"))) {
			filter.addFilter("Q_refObj_S_EQ", request.getParameter("refObj"));
			filter.addFilter("Q_refId_S_EQ", request.getParameter("refId"));
		}
		filter.addSorted("createTime", "desc");
		Pager pager = sysFileBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysFile/attachment_list";
	}

	@RequestMapping(value = "/attachments")
	public String attachments(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		QueryFilter filter = new QueryFilter(request);
		filter.addSorted("createTime", "desc");
		Pager pager = sysFileBO.getPager(filter);
		model.put("pager", pager);
		return "/sys/SysFile/attachments";
	}

	@RequestMapping(value = "/delete")
	public String delete(String[] ids) {
		for (String id : ids) {
			sysFileBO.remove(id);
		}
		return "/common/success";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(ModelMap model,
			@ModelAttribute("oaFileForm") FileForm oaFileForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		Result result = sysFileBO.saveFiles(oaFileForm, request);
		if (!result.isSuccess()) {
			model.addAllAttributes(result);
			return "/sys/SysFile/add";
		}
		return "/common/successjs";
	}

	@RequestMapping(value = "/downloadFile/{id}")
	public String downloadFile(@PathVariable("id") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (id.contains(".")) {
			id = id.substring(0, id.indexOf("."));
		}
		SysFile attachment = sysFileBO.get(id);

		if (attachment == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			// String userAgent = request.getHeader("User-Agent").toLowerCase();
			// if (userAgent.indexOf("firefox") > -1)
			// {
			// response.setHeader("Content-disposition",
			// "attachment; filename*=\"UTF-8''" +
			// URLEncoder.encode(attachment.getName(), "utf-8") + "\"");
			// }
			// else
			// {
			// response.setHeader("Content-disposition",
			// "attachment; filename=\"" +
			// URLEncoder.encode(attachment.getName(), "utf-8") + "\"");
			// }

			String path = SettingUtil.getSetting("uploadfilesavepath", null)
					+ attachment.getPath();
			if (!path.contains(":") && !path.startsWith("/")) {
				path = AppUtil.getAppWebappPath() + "/" + path;
			}
			java.io.File file = new java.io.File(path);
			if (file.exists()) {
				response.setContentType(attachment.getType());

				ServletOutputStream outputStream = response.getOutputStream();
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				InputStream inputStream = new FileInputStream(file);
				try {
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
				} catch (IOException e) {
					logger.error("downloadFile", "", e);
				} finally {
					outputStream.close();
					inputStream.close();
				}
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		return null;
	}

	@RequestMapping(value = "/edit")
	public String edit(String id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("sysFile", sysFileBO.get(id));
		return "/sys/SysFile/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ModelMap model,
			@ModelAttribute("sysFile") SysFile entity,
			HttpServletRequest request, HttpServletResponse response) {
		SysFile e = sysFileBO.get(entity.getId());
		e.setDescn(entity.getDescn());
		Result result = sysFileBO.update(e);
		if (!result.isSuccess()) {
			model.putAll(result);
			return "/sys/SysFile/edit";
		}
		return "/common/success";
	}
}
