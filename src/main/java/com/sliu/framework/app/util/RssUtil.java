package com.sliu.framework.app.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class RssUtil {

	public static String downloadXMLFile(String url) throws IOException {
		// 爬取指定url下的内容
		URL u = new URL(url);
		URLConnection uc = u.openConnection();
		uc.connect();
		InputStream is = uc.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		String temp = null;
		StringBuffer sb = new StringBuffer();
		while (null != (temp = br.readLine())) {
			sb.append(temp);
		}
		String rst = new String(sb.toString());
		br.close();
		isr.close();
		is.close();
		return rst;
	}
}
