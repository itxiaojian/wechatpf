package com.sliu.framework.app.wsh.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.common.dao.support.Pagination;
import com.sliu.framework.app.support.weixin.WeixinUtils;
import com.sliu.framework.app.wsh.dao.ShSwzlDao;
import com.sliu.framework.app.wsh.model.ShSwzl;
import com.sliu.framework.app.wtx.dao.TxSktxDao;
import com.sliu.framework.app.wtx.dao.TxTxxxlsjlDao;
import com.sliu.framework.app.wtx.model.TxSktx;
import com.sliu.framework.app.wtx.model.TxTxxxlsjl;
import com.sliu.framework.app.wtx.service.TxSktxService;
import com.sliu.framework.app.wxutil.model.TemplateDataView;
import com.sliu.framework.app.wxutil.model.WxTemplate;

/**
 * 失物招领
 * @author liujiansen
 * @date 2015年6月16日
 */
@Service
public class ShSwzlService extends BaseBO<ShSwzl>{

	protected Logger logger = LoggerFactory.getServiceLog(ShSwzl.class);
	
	@Autowired
	private ShSwzlDao dao;
	@Autowired
	private TxSktxService txSktxService;
	@Autowired
	private TxTxxxlsjlDao txDao;
	@Autowired
	private TxSktxDao sktxdao;

	
	/**
	 * 根据页数和标题查询失物招领数据
	 * @author liujiansen
	 * @date 2015年6月16日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getSwzlList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String bt=request.getParameter("bt");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
   		String lx=request.getParameter("lx");
   		if(lx==null||"".equals(lx)){
   			lx="";
   		}
		return dao.getSwzlList(pages, bt,lx);
	}
	
	/**
	 * 根据页数和标题查询拾卡招领数据
	 * @author liujiansen
	 * @date 2015年6月16日
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getSkzlList(HttpServletRequest request){
		String pages=request.getParameter("pages");
		String bt=request.getParameter("bt");
   		if(pages==null||"".equals(pages)){
   			pages="1";
   		}
   		String lx=request.getParameter("lx");
   		if(lx==null||"".equals(lx)){
   			lx="";
   		}
		return dao.getSkzlList(pages, bt,lx);
	}
	
	/**
	 * 根据标题获取失物招领的总条数
	 * @author liujiansen
	 * @date 2015年6月16日
	 * @param request
	 * @return
	 */
	public int getCount(HttpServletRequest request){
		String bt=request.getParameter("bt");
		String lx=request.getParameter("lx");
		if(lx==null||"0".equals(lx)){
   			lx="";
   		}
		return dao.getCount(bt,lx);
	}
	
	/**
	 * 失物招领信息保存
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@Transactional
	public void saveSwzl(HttpServletRequest request, HttpServletResponse response){
		ShSwzl swzl = new ShSwzl();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String lx = request.getParameter("type");
		String fbr = request.getParameter("openId");
		String fbrxm = request.getParameter("contact");
		String dd = request.getParameter("bjdd");
		String yktkh =request.getParameter("kh");
		String bz1 = request.getParameter("bz1");
		swzl.setBt(title);
		swzl.setFbr(fbr);
		swzl.setFbrxm(fbrxm);
		swzl.setFqsj(new Date());
		swzl.setLx(lx);
		swzl.setXwzs(content);
		swzl.setXwzt("1");
		swzl.setDd(dd);
		swzl.setYktkh(yktkh);
		swzl.setBz(bz1);
		Long id = dao.save(swzl);
		if("3".equals(lx)){
			txSktxService.toSkSend(id);
		}
	}
	
	/**
	 * 根据编号获取失物招领信息
	 * @author liujiansen
	 * @date 2015年6月17日
	 * @param id 编号
	 * @return
	 */
	public List<Map<String,Object>> getSwzlById(String openId,String id){
		return dao.getSwzlById(id);
	}
	
	/**
	 * 失物招领认领
	 * @author liujiansen
	 * @date 2015年6月17日
	 */
	@Transactional
	public void updateSwzl(String id){
		dao.update(id);
	}

	/**
	 * 失物招领信息删除
	 * @author liujiasen
	 * @date 2015年7月20日
	 * @param request
	 */
	@Transactional
	public void deleteSwzl(HttpServletRequest request) {
		String id=request.getParameter("id");
		dao.delete(Long.parseLong(id));
	}

	@Transactional
	public Pagination<Map<String, Object>> pager(Integer start, Integer limit,
			String code, String fbrxm) {
		return dao.pager(start, limit, code, fbrxm);
	}

	@Transactional
	public void deleteEntity(Long id) {
		dao.delete(id);
	}
	
	/**
	 * 分页查询拾卡信息
	 * @author zhangyan
	 * @date 2016年11月14日 上午9:37:06
	 * @param 
	 * @return
	 */
	public Pagination<Map<String,Object>> getSkList(Integer start,Integer limit,String code){
		return dao.getSkList(start, limit, code);
	}
	
	/**
	 * 后台添加拾卡信息
	 * @author zhangyan
	 * @date 2016年11月14日 上午10:09:57
	 * @param 
	 * @return
	 */
	@Transactional
	public String sksave(ShSwzl entity){
		entity.setLx("4");
		dao.save(entity);
		return "1";
	}
	
	/**
	 * 后台修改拾卡信息
	 * @author zhangyan
	 * @date 2016年11月14日 上午10:16:33
	 * @param 
	 * @return
	 */
	@Transactional
	public String skupdate(ShSwzl entity,Long id){
		ShSwzl sk = dao.get(id);
		sk.setBt(entity.getBt());
		sk.setDd(entity.getDd());
		sk.setFbrxm(entity.getFbrxm());
		sk.setXwzs(entity.getXwzs());
		sk.setYktkh(entity.getYktkh());
		dao.update(sk);
		return "1";
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
	
	
	
	//正则表达式判断导入信息类型
		@Transactional
		private String validate(Row row) {
//	        String v = getSV(row.getCell(0));
//	        if(v!=null&&!v.matches("^[0-9A-Za-z]{1,12}$")){
//	            return "第"+(row.getRowNum()+1)+"行第1列，必需是数字字母且长度不超过12";
//	        }
	        return null;
	    }
	
	/**
	 * 上传excel表格
	 * @author zhangyan
	 * @date 2016年11月14日 上午10:21:06
	 * @param 
	 * @return
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
                        ShSwzl sk = new ShSwzl();
                        
                        String bt=getSV(hssfRow.getCell(0));
                        sk.setBt(bt);
                        String xgh=getSV(hssfRow.getCell(1));
                        sk.setYktkh(xgh);
                        String dd=getSV(hssfRow.getCell(2));
                        sk.setDd(dd);
                        String lxr=getSV(hssfRow.getCell(3));
                        sk.setFbrxm(lxr);
                        String xq=getSV(hssfRow.getCell(4));
                        sk.setXwzs(xq);
                        sk.setLx("4");
                        dao.save(sk);
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
	
	
	@Transactional
	public void sendsktx(Long id) {
		
//		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date date = calendar.getTime();
		List<Map<String,Object>> skjl =new  ArrayList<Map<String,Object>>();
		skjl=dao.skxx(id);
		
		
			if(skjl.size()>0){
				for(Map<String,Object> map:skjl){
					String toUser = map.get("wxh").toString();
					String txnr="  尊敬的"+""+map.get("xm")+""+"，您好，您的校园卡"+map.get("yktkh")+""+"补办已完成，请携带身份证或学生证以及临时卡至网络中心信息化服务大厅处领取，谢谢！";
					WxTemplate temp = new WxTemplate();
					temp.setTemplate_id("MeBjKZmxV0rglj-YP2306iDrkd5oLFumWb1junejVh0");  //公司
//    				temp.setTemplate_id("OdbJbpHKYG7vcw53hBw8BO0156ur9J55F4P966xOI7s"); //运维
//					temp.setTemplate_id("sBPuQiMZLU6HrSwbKgO8TIjlHw4ZYkaL2Rmoh04i6Dg"); //工商
					temp.setTopcolor("#000000");
					temp.setTouser(toUser);
//		 			temp.setUrl(url+toUser);
					Map<String,TemplateDataView> mapdata = new HashMap<String,TemplateDataView>();
					//提醒
					TemplateDataView dataView1 = new TemplateDataView();
					dataView1.setColor("#FF6600");
					dataView1.setValue(" "+txnr+" ");
					mapdata.put("first", dataView1);
					
					//挂失卡片卡号
					TemplateDataView dataView2 = new TemplateDataView();
					dataView2.setColor("#000000");
					dataView2.setValue(" "+map.get("yktkh").toString()+" ");
					mapdata.put("keyword1", dataView2);
					
					//拾到人手机号码
					TemplateDataView dataView3 = new TemplateDataView();
					dataView3.setColor("#000000");
					dataView3.setValue("65689271");
					mapdata.put("keyword2", dataView3);
					//登记时间
					TemplateDataView dataView4 = new TemplateDataView();
					dataView4.setColor("#000000");
					dataView4.setValue(" "+s.format(date)+"");
					mapdata.put("keyword3", dataView4);
					//捡获者
//					TemplateDataView dataView5 = new TemplateDataView();
//					dataView5.setColor("#000000");
//					dataView5.setValue(" "+map.get("fbrxm").toString()+" ");
//					mapdata.put("PickerName", dataView5);
					//备注
					TemplateDataView dataView5 = new TemplateDataView();
					dataView5.setColor("#99CC00");
					dataView5.setValue(" 如有疑问，请联系网络中心。 ");
					mapdata.put("remark", dataView5);
					temp.setData(mapdata);
					WeixinUtils.sendTemplateMessage(toUser,temp);
				} 
			}
//			if(skjl.size()>0){
//				for(Map<String,Object> map:skjl){
//					String txnr= map.get("xm")+"您好，"
//							+map.get("fbrxm")+"在"+map.get("dd")+"捡到卡号为"
//							+map.get("yktkh")+"的卡"
//							+"请速与之联系！";
//					//保存数据
//					TxTxxxlsjl lsjl=new TxTxxxlsjl();
//					//存入类型
//					lsjl.setTxlx("6");
//					lsjl.setOpenid( map.get("wxh").toString());
//					lsjl.setTxdx(map.get("xm")+"");
//					lsjl.setTxnr(txnr);
//					lsjl.setBh(map.get("yhbh")+"");
//					lsjl.setKh(map.get("yktkh")+"");
//					lsjl.setSftx("1");
//				    lsjl.setTxsj(date);
//					txDao.save(lsjl);
//					
//					TxSktx sktx =new TxSktx();
//					sktx.setTxdx(map.get("xm")+"");
//					sktx.setOpenid(map.get("wxh").toString());
//					sktx.setTxnr(txnr);
//					sktx.setTxsj(date);
//					sktx.setKh(map.get("yktkh")+"");
//					sktxdao.save(sktx);
//			    }
//			}
//			sktxdao.deleteTxxxlsjl();
	}
}
