//-------------------------------------------------------------------------
// lxtgene@163.com
//-------------------------------------------------------------------------
package com.sliu.framework.app.wsh.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.likegene.framework.core.BaseController;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;

/**
 * 二手市场
 * @author liujiasen
 * @date 2015年6月30日
 */
@Controller
@RequestMapping("/wsh/upload")
public class UploadController extends BaseController {

	protected final transient Logger logger = LoggerFactory
			.getPresentationLog(UploadController.class);

	 	// @Autowired
	    // TemplateService templateService;
		@Value("${file.store.path}")
		private String filePath;
	    @Autowired
	    CommonsMultipartResolver multipartResolver;
	 
	    //@ResponseBody
	    @RequestMapping(value = "/tempimg")
	    public void addCategory(HttpServletRequest request, String path1,
	            String path2, HttpServletResponse actioncontext) {
	        String re=null;
	        String sub=null;
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile imgFile1 = multipartRequest.getFile("file");// 获取上次文件名
	        List<String> fileTypes = new ArrayList<String>();
	        fileTypes.add("jpg");
	        fileTypes.add("jpeg");
	        fileTypes.add("bmp");
	        fileTypes.add("gif");
	        fileTypes.add("png");
	        fileTypes.add("mp3");
	        if (!(imgFile1.getOriginalFilename() == null || "".equals(imgFile1
	                .getOriginalFilename()))) {
	            String uploadfile = request.getSession().getServletContext().getRealPath("/")+ "resources"
	        	+ File.separator +"home";
	            File file1 = this.getFile(imgFile1, uploadfile, fileTypes);
	            re=file1.toString();
	            //int i=re.lastIndexOf("\\");
	            sub=re.substring(re.lastIndexOf("\\")+1);
	        }
	        actioncontext.setContentType("text/html");
	        PrintWriter out;
	        try {
	            out = actioncontext.getWriter();
	            out.println(request.getContextPath()+"/resources/home/" + sub);
	            //out.flush();
	            //out.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //return "imgfile/"+sub;
	    }
	    // private File getFile(MultipartFile imgFile, String typeName,
	    private File getFile(MultipartFile imgFile, String brandName, List fileTypes) {
	        String fileName = imgFile.getOriginalFilename();
	        // 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
	        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
	                fileName.length());
	        // 对扩展名进行小写转换
	        ext = ext.toLowerCase();
	        File file = null;
	        if (fileTypes.contains(ext)) { // 如果扩展名属于允许上传的类型，则创建文件
	            file = this.creatFolder(brandName, fileName);
	            try {
	                imgFile.transferTo(file); // 保存上传的文件
	            } catch (IllegalStateException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return file;
	    }
	    private File creatFolder(String brandName, String fileName) {
	        File file = null;
	        File firstFolder = new File(brandName);
	        String suffix = fileName.substring(fileName.lastIndexOf('.'));
	        String prefix = System.currentTimeMillis() + "";
	        String newfileName = prefix + suffix;
	        if (firstFolder.exists()) { // 如果一级文件夹存在，则检测二级文件夹
	            file = new File(brandName + "\\" + newfileName);
	        } else { // 如果一级不存在，则创建一级文件夹
	            firstFolder.mkdir();
	            file = new File(brandName + "\\" + newfileName);
	        }
	        return file;
	    }

	    /****
		 * HTMLEditor增加上传图片功能：
		 * 1、上传图片后，需要将图片的位置及图片的名称返回给前台HTMLEditor
		 * 2、前台HTMLEditor根据返回的值将图片显示出来
		 * 3、进行统一保存
		 * @param 上传图片功能
		 * @return JSON结果
		 * @throws IOException
		 */
	    @RequestMapping(value = "/upload")
		public void HTMLEditorAddImg(HttpServletRequest request, String path1,
	            String path2, HttpServletResponse actioncontext) {
	        String re=null;
	        String sub=null;
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile imgFile1 = multipartRequest.getFile("userfile");// 获取上次文件名
	        List<String> fileTypes = new ArrayList<String>();
	        fileTypes.add("jpg");
	        fileTypes.add("jpeg");
	        fileTypes.add("bmp");
	        fileTypes.add("gif");
	        fileTypes.add("png");
	        fileTypes.add("mp3");
	        if (!(imgFile1.getOriginalFilename() == null || "".equals(imgFile1
	                .getOriginalFilename()))) {
	            String uploadfile = request.getSession().getServletContext().getRealPath("/")+ "resources"
	        	+ File.separator +"home";
	            File file1 = this.getFile(imgFile1, uploadfile, fileTypes);
	            re=file1.toString();
	            //int i=re.lastIndexOf("\\");
	            sub=re.substring(re.lastIndexOf("\\")+1);
	        }
	        actioncontext.setContentType("text/html");
	        PrintWriter out;
	        try {
	            out = actioncontext.getWriter();
	            //out.println(request.getContextPath()+"/resources/home/" + sub);
	            out.println("{success:'true',fileURL:'" + request.getContextPath()+"/resources/home/" + sub + "'}");
	            //out.flush();
	            //out.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
}
