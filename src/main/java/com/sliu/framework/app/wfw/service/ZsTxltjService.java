package com.sliu.framework.app.wfw.service;

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
import com.sliu.framework.app.wfw.dao.ZsTxltjDao;
import com.sliu.framework.app.wfw.model.ZsTxltj;


/**
 * 主页--  通讯录添加
 * @author wangxiangyang
 * @version 创建时间：2016年7月1日
 */
@Service
public class ZsTxltjService extends BaseBO<ZsTxltjDao>{

	@Autowired
	private ZsTxltjDao zsTxltjDao;
	
	
	
	/**
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pagination<Map<String, Object>> getTxltjList(Integer start, Integer limit){
		
		return zsTxltjDao.getTxltjList(start, limit);
		
	}
	
	
	/**
	 *  查看
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @return
	 */
	@Transactional
	public List<Map<String,Object>> getTxltjList(){
		return zsTxltjDao.getCx();
	}
	
	/**
	 * 增加
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @param entity  
	 */
	@Transactional
	public String save(ZsTxltj entity,String yhxm,String dhhm) {
		String str = "";
		List<Map<String,Object>> list = zsTxltjDao.CC(yhxm,dhhm);
		if (list.size()==0) {
		zsTxltjDao.save(entity);
		str = "1";
		}else{
			str = "0";
		}
		return str;
	}
	
	/**
	 * 编辑
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @param entity
	 * @param id  主键ID
	 */
	@Transactional
	public void update(ZsTxltj entity,String id){
		entity.setId(Long.parseLong(id));
		zsTxltjDao.update(entity);
	}
	
	/**
	 * 删除
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月1日
	 * @param ids
	 */
	@Transactional
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++){
		zsTxltjDao.delete(ids[i]);
		}
	}
	
	/**
	 * 导出通讯录
	 * @author   wangxiangyang
	 * @version 创建时间：2016年7月4日
	 * @param code 
	 */
	@Transactional
	public void exportExcel(HttpServletRequest request,HttpServletResponse response, String code)throws Exception{
		List<Map<String,Object>> list = zsTxltjDao.exportExcel(code);
		String[] top = new String[]{"编号","用户姓名","电话号码","备注"};
		String[] key = new String[]{"id","yhxm","dhhm","bz"};
		ExportExcelUtil.exportExcel(request,response,top,key,list);
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
	  * 获取exl表格内容
	 * @author wangxiangyang
	 * @version 创建时间：2016年7月6日 
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
	 * @author wangxiangyang
	 * @version 创建时间：2016年7月6日 
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
                        ZsTxltj txltj = new ZsTxltj();
                        String yhxm=getSV(hssfRow.getCell(1));
                        String dhhm=getSV(hssfRow.getCell(2));
                        String bz=getSV(hssfRow.getCell(3));
                        List<Map<String,Object>> list = zsTxltjDao.CC(yhxm,dhhm);
                        if (list.size()==0){
                        	txltj.setYhxm(yhxm);
                            txltj.setDhhm(dhhm);
                            txltj.setBz(bz);
                            zsTxltjDao.save(txltj);
                        }
                        else{
                        	 continue;
                        }
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
