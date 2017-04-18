package com.sliu.framework.app.wtx.service;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import com.sliu.framework.app.util.HttpClientUtil;


/**
*未读邮件
@Author oufeng	
@Date 2016年8月10日 下午15:02:48
@Version 1.0
*/
@Service
public class TxWdyjService {
	
    
	public static  void  main(String[] args) throws UnsupportedEncodingException{
		 String email="jwczsb@wxc.edu.cn";
		 //String email="chxy@wxc.edu.cn";
		 HttpClientUtil.getWdMail(email);
		 System.out.println("mail================="+ HttpClientUtil.getWdMail(email));
     }
}