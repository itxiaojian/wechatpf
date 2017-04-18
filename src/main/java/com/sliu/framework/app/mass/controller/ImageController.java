package com.sliu.framework.app.mass.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.mass.service.MaterialMgrService;

@Controller
@RequestMapping(value = "/wxauth/mass")
public class ImageController extends BaseController {
	private static final Logger _logger = LoggerFactory.getLogger(ImageController.class);
	
	@Value("${file.store.path}")
	private String uploadFilePath;
	
	@Value("${file.store.path}")
	private String filePath;
	
	@Autowired
	private MaterialMgrService materialMgrService;
	
	/**
	 * 图片展现
	 */
	@RequestMapping("/{imageName}/show")
	public void showImage(@PathVariable String imageName, HttpServletResponse response) {
		try {			
			response.setHeader("Content-Type", "application/octet-stream");
			filePath = materialMgrService.returnPath();
			File file = null;
			String imagePath = filePath + File.separator + "images" + File.separator + imageName;
			file = new File(imagePath);
			if(file != null) {
				response.setHeader("Content-Length", String.valueOf(file.length()));  
				response.setHeader("X-Accel-Redirect", toPathEncoding(response.getCharacterEncoding(), imagePath));			
				response.setHeader("X-Accel-Charset", "utf-8");			
				FileUtils.copyFile(file, response.getOutputStream());
			}	
		} catch (Exception e) {
			_logger.error(e.getMessage());
		}
	}
	
	protected static final String DEFAULT_FILE_ENCODING = "ISO-8859-1";
	private String toPathEncoding(String origEncoding, String fileName) throws UnsupportedEncodingException{
		return new String(fileName.getBytes(origEncoding), DEFAULT_FILE_ENCODING);
	}
}
