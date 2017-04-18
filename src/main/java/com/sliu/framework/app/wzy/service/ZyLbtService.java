package com.sliu.framework.app.wzy.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.sys.model.SysYh;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.wzy.dao.ZyLbtDao;
import com.sliu.framework.app.wzy.model.ZyLbt;

/**
 * 主页轮播图 Service
 * 
 * @author liujiansen
 * @since 2016-03-07
 */

@Service
public class ZyLbtService extends BaseBO<ZyLbt> {
	@Autowired
	private ZyLbtDao dao;

	/**
	 * 查询图片列表信息
	 * 
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param start
	 * @param limit
	 * @param tpmc
	 * @param tplx
	 * @return
	 */
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit,
			String tpmc, String tplx) {
		return dao.pager(start, limit, tpmc, tplx);
	}

	/**
	 * 获取图片类型
	 * 
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @return
	 */
	public List<Map<String, Object>> getTplx(String zl) {
		return dao.getTplx(zl);
	}

	/**
	 * 上传保存
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param attachMentFile
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String insertEntity(MultipartFile attachMentFile,HttpServletRequest request, HttpServletResponse response) {
		String str="0";
		String re = null;
		String sub = null;
		String fileName = null;
		String tplx=request.getParameter("tplx");
		String sfsx=request.getParameter("sfsx");
		List<String> fileTypes = new ArrayList<String>();
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("png");
		fileTypes.add("gif");
		if(!attachMentFile.isEmpty()){
			fileName = attachMentFile.getOriginalFilename();
			String uploadfile = request.getSession().getServletContext().getRealPath("/")+ "resources" + File.separator + "home";
			File file1 = this.getFile(attachMentFile, uploadfile, fileTypes);
			re = file1.toString();
			sub = re.substring(re.lastIndexOf("\\") + 1);
//			System.out.println(fileName);
			ZyLbt lbt=new ZyLbt();
			SysYh user=AppUtil.getCurrentUser();
			lbt.setScr(user.getUsername());
			lbt.setScsj(new Date());
			lbt.setSfsx(Integer.parseInt(sfsx));
			lbt.setTplx(Integer.parseInt(tplx));
			lbt.setTpmc(fileName);
			lbt.setTpdz("/resources/home/" + sub);
			dao.save(lbt);
			str="1";
		}
//		System.out.println(request.getContextPath() + "/resources/home/" + sub);
		return str;
	}
	
	/**
	 * 修改
	 * @author liujiansen
	 * @date 2016年3月7日
	 * @param attachMentFile
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String updateEntity(MultipartFile attachMentFile,HttpServletRequest request, HttpServletResponse response) {
		String str="0";
		String re = null;
		String sub = null;
		String fileName = null;
		String tplx=request.getParameter("tplx");
		String sfsx=request.getParameter("sfsx");
		String id=request.getParameter("id");
		List<String> fileTypes = new ArrayList<String>();
		fileTypes.add("gif");
		fileTypes.add("png");
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		ZyLbt lbt=dao.get(Long.parseLong(id));
		SysYh user=AppUtil.getCurrentUser();
		if(!attachMentFile.isEmpty()){
			fileName = attachMentFile.getOriginalFilename();
			String uploadfile = request.getSession().getServletContext().getRealPath("/")+ "resources" + File.separator + "home";
			File file1 = this.getFile(attachMentFile, uploadfile, fileTypes);
			re = file1.toString();
			sub = re.substring(re.lastIndexOf("\\") + 1);
			lbt.setScr(user.getUsername());
			lbt.setScsj(new Date());
			lbt.setSfsx(Integer.parseInt(sfsx));
			lbt.setTplx(Integer.parseInt(tplx));
			lbt.setTpmc(fileName);
			lbt.setTpdz("/resources/home/" + sub);
			str="1";
		}else{
			lbt.setScr(user.getUsername());
			lbt.setScsj(new Date());
			lbt.setSfsx(Integer.parseInt(sfsx));
			lbt.setTplx(Integer.parseInt(tplx));
		}
		dao.update(lbt);
		return str;
	}
	
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

    /**
     * 删除
     * @author liujiansen
     * @date 2016年3月7日
     * @param id
     */
    @Transactional
	public void deleteEntity(Long id) {
		dao.delete(id);
	}
}
