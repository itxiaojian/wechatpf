package com.sliu.framework.app.process.support.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * @author 朱增鹏
 * @date 2013-3-27 下午3:24:28
 */
public class FileCommonOperate {

	/**
	 * 
	 * @param inputStream 待上传文件的输入流
	 * @param path 上传目标路径
	 * @param fileName 上传后文件名
	 * @return
	 */
	public String uploadFile(InputStream inputStream, String path, String fileName) {
		try {
			File file = createFile(path, fileName);
			OutputStream outputStream = new FileOutputStream(file);			
			try {
				IOUtils.copyLarge(inputStream, outputStream);
			} finally {
				outputStream.close();
			}			
			return file.getName();
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not add record", e);
		}
	}	
	
	private File createFile(String path, String fileName) {
	 	File file = new File(path);
	 	if(!file.exists())
	 		file.mkdirs();
	 	file = new File(path, fileName);
	  	return file;
	}
	
	/**
	 * 下载文件
	 * @param request 
	 * @param response
	 * @param filePath 完整文件路径(包含文件名)
	 * @param saveFileName 下载时保存到本地的文件名
	 * @throws UnsupportedEncodingException
	 */
	public void downLoadFile(HttpServletRequest request, HttpServletResponse response, String filePath, String saveFileName) throws UnsupportedEncodingException {
		if (request.getHeader("User-Agent").toLowerCase()
				.indexOf("firefox") > 0) {
			saveFileName = new String(saveFileName.getBytes("UTF-8"),
					"ISO8859-1");// firefox浏览器
		} else if (request.getHeader("User-Agent").toUpperCase()
				.indexOf("MSIE") > 0) {
			saveFileName = URLEncoder.encode(saveFileName, "UTF-8");// IE浏览器
		} else {
			saveFileName = URLEncoder.encode(saveFileName, "UTF-8");
		}
		try {
			FileInputStream fis = new FileInputStream(new File(filePath));
			response.setHeader("Content-Disposition", "attachment; filename=\""+ saveFileName + "\""); 
			response.setContentType("application/octet-stream");
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
			    os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public void multiFilesToZip(List<String> filePathAndNames,String zipFilePath ,String zipFileName){
		String zipPathAndName = zipFilePath+ "/"+zipFileName;
		byte[] buffer = new byte[1024]; 
		try {
			File f = new File(zipFilePath);
			if(!f.exists()){
				f.mkdirs();
			}
			f = new File(zipPathAndName);
			if(!f.exists()){
				f.createNewFile();
			}
			
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPathAndName));    
			if(filePathAndNames != null && filePathAndNames.size() > 0){
				for(String fileCodeAndPath : filePathAndNames){
					File file = new File(fileCodeAndPath);
					if(!file.exists()){
						continue;
					}
					FileInputStream fis = new FileInputStream(new File(fileCodeAndPath)); 
					String fileCode = fileCodeAndPath.substring(fileCodeAndPath.lastIndexOf("/")+1);
					out.putNextEntry(new ZipEntry(fileCode));
					int len;    
	                // 读入需要下载的文件的内容，打包到zip文件    
	                while ((len = fis.read(buffer)) > 0) {    
	                    out.write(buffer, 0, len);    
	                }    
	                out.closeEntry();    
	                fis.close();
				}
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File("D:" + File.separator + "ttt.txt"));
			FileCommonOperate t = new FileCommonOperate();
			String temp = t.uploadFile(fileInputStream, "d:/temz", "zzp123321.txt");
			System.out.println(temp);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
