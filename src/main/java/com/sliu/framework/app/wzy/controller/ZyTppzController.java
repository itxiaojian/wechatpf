package com.sliu.framework.app.wzy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Encoder;

import com.likegene.framework.core.BaseController;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.file.service.FileService;
import com.sliu.framework.app.support.random.RandomUtil;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wzy.service.ZyTppzService;

/**
 * 主页轮播图 Controller
 * 
 * @author liujiansen
 * @since 2016-03-07
 */
@Controller
@RequestMapping(value = "/wzy/ZyTppz")
public class ZyTppzController extends BaseController {
	@Autowired
	private ZyTppzService service;
	
	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav=new ModelAndView("/wzy/zyTppzList");
		return mav;
	}

	/**
	 * 查询图片列表信息
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param start
	 * @param limit
	 * @param tpmc
	 * @param tplx
	 * @return
	 */
	@RequestMapping(value = "/pager")
	@ResponseBody
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit, String tpmc, String lx) {
		return service.pager(start, limit, tpmc, lx);
	}
	
	/**
	 * 获取图片类型
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @return
	 */
	@RequestMapping(value = "/getTplx")
	@ResponseBody
	public List<Map<String, Object>> getTplx() {
		return service.getTplx("tplx");
	}

	/**
	 * 保存
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@SuppressWarnings("restriction")
	@RequestMapping(value = "/fileUload")
	@ResponseBody
	public String ftpUpload(HttpServletRequest request,HttpServletResponse response,MultipartFile file) throws Exception{
		//File files = new File("");
		String urltext = request.getParameter("url");
		String pxtext = request.getParameter("px");
		String tpmctext = request.getParameter("tpmc");
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
			try {
				BASE64Encoder encoder =  new BASE64Encoder();  
				// 通过base64来转化图片
				String data = encoder.encode(file.getBytes());
//				String data2 = encoder.encode(data1);
				//Long imgid = fileService.saveFile(data,fileNameU);
				service.savemag(data,fileNameU,urltext,Integer.parseInt(pxtext),tpmctext);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return "0";
		}
		return "1";
	}
	
	/**
	 * 修改
	 * @author zhangyan
	 * @date 2016年6月30日
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */	
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(HttpServletRequest request,HttpServletResponse response,MultipartFile file) throws Exception{
		//File files = new File("");
		String urltext = request.getParameter("url");
		String pxtext = request.getParameter("px");
		String tpmctext = request.getParameter("tpmc");
		String tpbcid = request.getParameter("tpbcid");
		String id=request.getParameter("id");
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
			try {
				BASE64Encoder encoder =  new BASE64Encoder();  
				// 通过base64来转化图片
				String data = encoder.encode(file.getBytes());
//				String data2 = encoder.encode(data1);
				//Long imgid = fileService.saveFile(data,fileNameU);
				service.update(data,fileNameU,urltext,Integer.parseInt(pxtext),tpmctext,tpbcid,id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			service.update1(urltext,Integer.parseInt(pxtext),tpmctext,id);
		}
		return "1";
	}
	
	/**
	 * 删除
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseData delete(Long[] ids) {
		for (Long id : ids) {
			String tpbc=service.getTpbcidById(id).get(0).get("tpbcid").toString();
			Long tpbcid=Long.parseLong(tpbc);
			service.deleteEntity(id,tpbcid);
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
}
