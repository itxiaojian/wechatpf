package com.sliu.framework.app.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportExcelUtil{    
    
//    @Autowired    
//    private StudentExportService studentExportService;    
    
    public static void exportExcel(HttpServletRequest request, HttpServletResponse response,String[] header,String[] keys,List list)     
    throws Exception {    
            
    	response.setContentType("octets/stream");
//		response.addHeader("Content-Disposition", "attachment;filename=test.xls");
		String excelName = request.getParameter("fileName");
		//转码防止乱码
//		response.addHeader("Content-Disposition", "attachment;filename="+new String( excelName.getBytes("utf-8"), "ISO8859-1" )+".xls");
		excelName=URLDecoder.decode(excelName,"UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="+new String( excelName.getBytes("UTF-8"), "ISO8859-1" )+".xls");
//		String[] headers = new String[]{"编号","姓名","年龄","性别"};
		String[] headers = header;
		try {
			OutputStream out = response.getOutputStream();
			exportExcel(excelName,headers, keys, list, out,"yyyy-MM-dd");
			out.close();
			System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
   } 
	
	/**
	 * 
	 * @Description: 生成excel并导出到客户端（本地）
	 * @Auther: liujiansen
	 * @Date: 2013-8-22 下午3:05:49
	 */
	public static void exportExcel(String title,String[] headers,String[] keys,List mapList,OutputStream out,String pattern){
		//声明一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		//设置表格默认列宽度为15个字符
		sheet.setDefaultColumnWidth(20);
		//生成一个样式，用来设置标题样式
		HSSFCellStyle style = workbook.createCellStyle();
		//设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式,用于设置内容样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style2.setWrapText(true);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		//产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for(int i = 0; i<headers.length;i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		for (int i=0;i<mapList.size();i++) {
			Map<String,Object> map = (Map<String, Object>) mapList.get(i);
			row = sheet.createRow(i+1);
			int j = 0;
			Object value = null;
			for(int a=0;a<keys.length;a++){
				value=map.get(keys[a]);
				HSSFCell cell = row.createCell(j++);
				cell.setCellStyle(style2);
				if(value instanceof Integer){
					cell.setCellValue(String.valueOf(value));
				}else{
					if(map.get(keys[a])!=null){
						cell.setCellValue(map.get(keys[a]).toString());
					}else{
						cell.setCellValue("");
					}
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
