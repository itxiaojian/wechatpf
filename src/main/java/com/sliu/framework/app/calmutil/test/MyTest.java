package com.sliu.framework.app.calmutil.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 使用状态机思想解析字符
 * 
 */
public class MyTest {
	/**
	 * 解析字符串中%后面两个字符
	 * @author : zhangyi
	 * @version 创建时间：2016年8月23日 下午2:32:49
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long total = 1000000;
		int i = 0;
		String source = "%中国%人民银行%%AB&%C%DE%F";
		char splitChar = '%';
		while (i < total) {
			useDFA(source, splitChar);
			i++;
		}
		System.out.println("useDFA--->>" + useDFA(source, splitChar) + "    use time:" + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		i = 0;
		while (i < total) {
			useJDKTools(source, splitChar);
			i++;
		}
		System.out.println("useJDKTools--->>" + useJDKTools(source, splitChar) + " use time:" + (System.currentTimeMillis() - start));
	}

	public static String useJDKTools(String str, char chat) {
		StringTokenizer tokenizer = new StringTokenizer(str, String.valueOf(chat));
		List<String> list = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (token != null && token.length() >= 2) {
				list.add(token.substring(0, 2));
			}
		}
		return Arrays.toString(list.toArray());
	}

	public static String useDFA(String str, char chat) {
		List<String> codes = new ArrayList<String>();
		Status currentState = null;
		char c1 = '\0';
		char c2 = '\0';
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			if (currentChar == chat) {
				if (currentState == Status.SECOND) {
					char[] s1 = { c1, c2 };
					codes.add(new String(s1));
				}
				currentState = Status.DONE;
			} else if (currentState == Status.DONE) {
				c1 = currentChar;
				currentState = Status.FIRST;
			} else if (currentState == Status.FIRST) {
				c2 = currentChar;
				currentState = Status.SECOND;
			} else if (currentState == Status.SECOND) {
				char[] s1 = { c1, c2 };
				codes.add(new String(s1));
				currentState = null;
			}
		}
		if (currentState == Status.SECOND) {
			char[] s1 = { c1, c2 };
			codes.add(new String(s1));
		}
		return Arrays.toString(codes.toArray());
	}

	public enum Status {
		DONE, FIRST, SECOND;
	}
}
