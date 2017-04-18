package com.sliu.framework.app.support.random;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 随机数
 * @author zhangdongdong
 *
 */
public  class RandomUtil {
	
	/**
	 * 生成4位随机数
	 * @return
	 */
	public static String generateWord() {  
        String[] beforeShuffle = new String[] {"1", "2", "3", "4", "5", "6", "7","8", "9" };  
        List list = Arrays.asList(beforeShuffle);  
        Collections.shuffle(list);    //使用默认随机源对指定列表进行置换。
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(list.get(i));  
        }  
        String afterShuffle = sb.toString();  
        String result = afterShuffle.substring(3, 7);  
        return result;  
    }  
}
