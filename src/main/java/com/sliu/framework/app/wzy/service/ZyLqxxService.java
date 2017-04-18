package com.sliu.framework.app.wzy.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.likegene.framework.core.BaseBO;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.util.ExportExcelUtil;
import com.sliu.framework.app.wzy.dao.ZyLqxxDao;
import com.sliu.framework.app.wzy.model.ZyLqxx;

/**
 * 主页--  录取信息
 * @author zhangyan
 * @version 创建时间：2016年7月4日 
 */
@Service
public class ZyLqxxService extends BaseBO<ZyLqxxDao> {
	
	@Autowired
	private ZyLqxxDao zyLqxxDao;

	
	/**
	 * 发布
	 * @author   zhangyan
	 * @version 创建时间：2016年7月5日  
	 * @param entity
	 * @return
	 */
	@Transactional
	public String save(ZyLqxx entity){
//		SysYh yh = AppUtil.getCurrentUser();
//		entity.setXm(entity.getXm());
//		entity.setKsh(entity.getKsh());
//		entity.setSfzh(entity.getSfzh());
//		entity.setLqzy(entity.getLqzy());
//		entity.setZf(entity.getZf());
		zyLqxxDao.save(entity);
		return "1";
	}
	
	/**
	 * 修改录取信息
	 * @author:zhangyan 
	 * @version 创建时间：2016年7月5日  
	 * @param entity
	 * @param id
	 * @return
	 */
	@Transactional
	public String update(ZyLqxx entity, Long id) {
		zyLqxxDao.update(entity);
		return "1";
	}
	
	/**
	 * 删除录取信息
	 * @author   zhangyan
	 * @version 创建时间：2016年7月5日  
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids){
		
		for(int i=0;i<ids.length;i++){
			zyLqxxDao.delete(ids[i]);
		}
	}

	
	/**
	 *  查看录取信息
	 * @author   zhangyan
	 * @version 创建时间：2016年7月4日  
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getLqxxCx(HttpServletRequest request){
		String ksh = request.getParameter("ksh");
		String sfzh=request.getParameter("sfzh");
		return zyLqxxDao.getLqxxCx(ksh,sfzh);
	}
	
	/**
	 * 分页查询录取信息list
	 * @author   liusong
	 * @version 创建时间：2016年7月5日  
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getLqxxList(Integer start, Integer limit,String code){
		
		return zyLqxxDao.getLqxxList(start, limit,code);
		
	}
	
	/** 
	 * 导出信息至exl表格
	 * @author liusong E-mail: 2629690209@qq.com
	 * @version 创建时间：2015年11月11日 上午10:19:22 
	 * 方法说明 
	 */
	@Transactional
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,String code) throws Exception {
		List<Map<String,Object>> list=zyLqxxDao.getLqxxList(code);
		String[] header=new String[]{"考生号","身份证号","姓名","总分","录取专业"};
		String[] keys=new String[]{"KSH","SFZH","XM","ZF","LQZY"};
		ExportExcelUtil.exportExcel(request, response, header, keys, list);
	}
	
	
	//正则表达式判断导入信息类型
	@Transactional
	private String validate(Row row) {
//        String v = getSV(row.getCell(0));
//        if(v!=null&&!v.matches("^[0-9A-Za-z]{1,12}$")){
//            return "第"+(row.getRowNum()+1)+"行第1列，必需是数字字母且长度不超过12";
//        }
        return null;
    }
	
	/** 
	 * 获取当前exl表格内容
	 * @author liusong E-mail: 2629690209@qq.com
	 * @version 创建时间：2015年11月11日 上午10:19:22 
	 * 方法说明 
	 */
	@Transactional
	private String getSV(Cell cell)
    {
        if (cell == null)
            return null;
        switch (cell.getCellType())
        {
        case Cell.CELL_TYPE_NUMERIC:
            return "" + cell.getNumericCellValue();
        case Cell.CELL_TYPE_STRING:
            return StringUtils.trim(cell.getStringCellValue());
        default:
            break;
        }

        return null;
    }
	
	/** 
	 * 上传exl表格内容
	 * @author liusong E-mail: 2629690209@qq.com
	 * @version 创建时间：2015年11月11日 上午10:19:22 
	 * 方法说明 
	 */
	@Transactional
	public String importMember(MultipartFile file) {
		if (file.getSize() == 0)
        {
            return "上传文件为空";
        }
		try
        {
			 Workbook hssfWorkbook = null;
		        try {
		        	hssfWorkbook = new HSSFWorkbook(file.getInputStream());
		        } catch (Exception ex) {
		        	hssfWorkbook = new XSSFWorkbook(file.getInputStream());
		        }
            //HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());
		    Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
            if (hssfSheet != null)
            {
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
                {
                    //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    Row hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null)
                    {
                        String result = validate(hssfRow);
                        if (result != null) {
                            return result;
                        }
                        ZyLqxx lqxx = new ZyLqxx();
                        
                        String ksh=getSV(hssfRow.getCell(0));
                        lqxx.setKsh(ksh);
                        String sfzh=getSV(hssfRow.getCell(1));
                        lqxx.setSfzh(sfzh);
                        String xm=getSV(hssfRow.getCell(2));
                        lqxx.setXm(xm);
                        String zf=getSV(hssfRow.getCell(3));
                        lqxx.setZf(zf);
                        String lqzy=getSV(hssfRow.getCell(4));
                        lqxx.setLqzy(lqzy);
                        zyLqxxDao.save(lqxx);
                    }
                }
            }
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
          return e.getMessage();
        }               
		return null;
	}
}
