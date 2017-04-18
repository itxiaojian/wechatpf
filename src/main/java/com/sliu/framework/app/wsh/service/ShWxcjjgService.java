package com.sliu.framework.app.wsh.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.wsh.dao.ShWxcjjgDao;
import com.sliu.framework.app.wsh.model.ShWxcjjg;

/**
 * 微信抽奖结果
 * 
 * @author liujiansen
 * @date 2015年6月18日
 */
@Service
public class ShWxcjjgService extends BaseBO<ShWxcjjg> {

	protected Logger logger = LoggerFactory.getServiceLog(ShWxcjjg.class);

	@Autowired
	private ShWxcjjgDao dao;

	/**
	 * 根据时间获取抽奖配置信息
	 * 
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getWxcj() {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String time = sim.format(new Date());
		return dao.getWxcj(time);
	}

	/**
	 * 抽奖
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	public String doWxcj(HttpServletRequest request,
			HttpServletResponse response) {
		List<Map<String, Object>> list=this.getWxcj();
		Object[][] prizeArr = new Object[][] {
				// id,min,max，prize【奖项】,v【中奖率】
				// 里面的指针转动
				{ 1, 1, 14, "一等奖", Integer.parseInt(list.get(0).get("ydjsl")+"") }, { 2, 346, 364, "一等奖", Integer.parseInt(list.get(0).get("ydjsl")+"") },
				{ 3, 16, 44, "不要灰心", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 }, { 4, 46, 74, "神马也没有", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 },
				{ 5, 76, 104, "祝您好运", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 }, { 6, 106, 134, "二等奖", Integer.parseInt(list.get(0).get("rdjsl")+"") },
				{ 7, 136, 164, "再接再厉", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 }, { 8, 166, 194, "神马也没有", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 },
				{ 9, 196, 224, "运气先攒着", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 }, { 10, 226, 254, "三等奖", Integer.parseInt(list.get(0).get("sdjsl")+"") },
				{ 11, 256, 284, "要加油哦", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 }, { 12, 286, 314, "神马也没有", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 },
				{ 13, 316, 344, "谢谢参与", Integer.parseInt(list.get(0).get("sdjsl")+"")+10 } };
		String number = request.getParameter("num");
		String openId = request.getParameter("user");
		Object result[] = award(prizeArr);// 抽奖后返回角度和奖品等级
		String str = "{\"angle\":\"" + result[0] + "\",\"msg\":\"" + result[2]
				+ "\",\"num\":\"" + (Integer.parseInt(number) - 1) + "\"}";
		ShWxcjjg jg=new ShWxcjjg();
		jg.setCjid(Long.parseLong(list.get(0).get("id")+""));
		jg.setCzjx(result[2]+"");
		jg.setOpenid(openId);
		dao.save(jg);
		return str;
	}

	/**
	 * 抽奖并返回角度和奖项
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param prizeArr
	 * @return
	 */
	public Object[] award(Object[][] prizeArr) {
		// 概率数组
		Integer obj[] = new Integer[prizeArr.length];
		for (int i = 0; i < prizeArr.length; i++) {
			obj[i] = (Integer) prizeArr[i][4];
		}
		Integer prizeId = getRand(obj); // 根据概率获取奖项id
		// 旋转角度
		int angle = new Random().nextInt((Integer) prizeArr[prizeId][2]
				- (Integer) prizeArr[prizeId][1])
				+ (Integer) prizeArr[prizeId][1];
		String msg = (String) prizeArr[prizeId][3];// 提示信息
		return new Object[] { angle, prizeId, msg };
	}

	/**
	 * 根据概率获取奖项
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param obj
	 * @return
	 */
	public Integer getRand(Integer obj[]) {
		Integer result = null;
		List<Map<String, Object>> list=this.getWxcj();
		try {
			int sum = 0;// 概率数组的总概率精度
			for (int i = 0; i < obj.length; i++) {
				sum += obj[i];
			}
			for (int i = 0; i < obj.length; i++) {// 概率数组循环
				int randomNum = new Random().nextInt(sum);// 随机生成1到sum的整数
				if (randomNum < obj[i]) {// 中奖
					if (i == Integer.parseInt(list.get(0).get("ydjsl")+"") || 
						i == Integer.parseInt(list.get(0).get("rdjsl")+"") || 
						i == Integer.parseInt(list.get(0).get("sdjsl")+"")) {
						// 查询数据库的奖品是否已经被领完

						result = i;
						break;
					} else {
						result = i;
						break;
					}
				} else {
					sum -= obj[i];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据用户的openId获取用户的中奖信息
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getZjjg(String openId){
		return dao.getZjjg(openId);
	}
	
	/**
	 * 根据用户的openId和抽奖编号获取用户的中奖信息
	 * @author liujiansen
	 * @date 2015年6月18日
	 * @param openId
	 * @return
	 */
	public List<Map<String,Object>> getCjjg(String openId,String cjid){
		return dao.getCjjg(openId, cjid);
	}
}
