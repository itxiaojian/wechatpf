package com.sliu.framework.app.file.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sliu.framework.app.file.model.SysFjgl;
import com.sliu.framework.app.file.service.FileService;
import com.sliu.framework.app.support.random.RandomUtil;
import com.sliu.framework.app.util.ImgCompressUtil;

/** 
 * @author:zhangyi 
 * @version 创建时间：2016年6月13日 上午11:20:52 
 * 类说明 
 */
@SuppressWarnings("restriction")
@Controller
@RequestMapping(value="/util/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "/testfile")
	public ModelAndView openWxShakeInfoPage(String ticket) {
		ModelAndView modelAndView = new ModelAndView();
		String url = "/test/fileupload";
		modelAndView.setViewName(url);
		return modelAndView;
	}

	@RequestMapping(value = "/fileUload")
	@ResponseBody
	public String ftpUpload(MultipartFile file,String wjlx,Long lxid,HttpServletRequest request) throws Exception{
		//File files = new File("");
		String str="";
		if (!file.isEmpty()) {
//			if(file.getSize()>10000000){
//          	throw new Exception("上传失败：文件大小不能超过10M");           
//          }
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String date = sdf.format(new Date());		//当前日期格式化到秒
			String random = RandomUtil.generateWord();	//获得随机数
			String filename = file.getOriginalFilename();
			String prefix=filename.substring(filename.lastIndexOf(".")+1);
			String fileNameU = date+random+"."+prefix;//文件名称
//			BufferedImage srcImage = ImageIO.read(new File("c:/xxx.jpg"));
//			byte[] data1 = ((DataBufferByte) srcImage.getData().getDataBuffer()).getData(); 
			if(file.getSize()<=5*1024*1024){
				try {
					byte[] bytes =ImgCompressUtil.CompressPicture(file, 200, 200, true);
					BASE64Encoder encoder =  new BASE64Encoder();  
					// 通过base64来转化图片
					String data = encoder.encode(bytes);
//					String data2 = encoder.encode(data1);
					if(data.length()>=4194304){
						str="1";
					}else{
						str=fileNameU;
					}
					fileService.saveFile(data,wjlx,lxid,fileNameU);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				str="1";
			}
		
		} else {
			return "errorView";
		}
		return str;
	}
	
	/**
	 * 取得图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getFile")
	public void getImg(HttpServletRequest request,HttpServletResponse response,Long imgId){
		SysFjgl img = fileService.getfile(imgId);
		String data = img.getContent();
		BASE64Decoder decoder = new BASE64Decoder();  
		try {
			byte[] bytes = decoder.decodeBuffer(data);
			for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据  
                    bytes[i] += 256;
                }  
            }
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes);
            out.flush();
            out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * 取得imgId
	 * @param request
	 * @param response
	 */
		@RequestMapping(value="/getImgId")
		@ResponseBody
	   	public Long  getBxzt(HttpServletRequest request, HttpServletResponse response) 
	   			throws UnsupportedEncodingException{
	   		List<Map<String,Object>> list=fileService.getImgId(request);
	   		Long id =1l;
	   		if(list.size()!=0){
	   			id=Long.parseLong((list.get(0).get("id")+""));
	   		}
	   		return id;
	   	}
}
