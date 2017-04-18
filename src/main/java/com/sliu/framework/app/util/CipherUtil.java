package com.sliu.framework.app.util;
/** 
 * @author:zhangyi 
 * @version 创建时间：2015年9月1日 上午10:03:02 
 * 类说明 
 */
import java.security.MessageDigest;   


public class CipherUtil{   
       
    //十六进制下数字到字符的映射数组   
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",   
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};   
       
      
    public static String generatePassword(String inputString){   
        return encodeByMD5(inputString);   
     }   
       
        
    public static boolean validatePassword(String password, String inputString){   
        if(password.equals(encodeByMD5(inputString))){   
            return true;   
         } else{   
            return false;   
         }   
     }   
      
    private static String encodeByMD5(String originString){   
        if (originString != null){   
            try{   
                //创建具有指定算法名称的信息摘要   
                 MessageDigest md = MessageDigest.getInstance("MD5");   
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算   
                byte[] results = md.digest(originString.getBytes());   
                //将得到的字节数组变成字符串返回   
                 String resultString = byteArrayToHexString(results);   
                return resultString.toUpperCase();   
             } catch(Exception ex){   
                 ex.printStackTrace();   
             }   
         }   
        return null;   
     }   
       
      
    private static String byteArrayToHexString(byte[] b){   
         StringBuffer resultSb = new StringBuffer();   
        for (int i = 0; i < b.length; i++){   
             resultSb.append(byteToHexString(b[i]));   
         }   
        return resultSb.toString();   
     }   
       
      
    private static String byteToHexString(byte b){   
        int n = b;   
        if (n < 0)   
             n = 256 + n;   
        int d1 = n / 16;   
        int d2 = n % 16;   
        return hexDigits[d1] + hexDigits[d2];   
     }
    
    public static void main(String[] args) {   
        String pwd1="123";   
        String pwd2="";   
        String pwd3="b3531cae6e1e5cd2b673da89d066c059";
        CipherUtil cipher = new CipherUtil();   
        System.out.println("未加密的密码:"+pwd1);   
       //将123加密   
        pwd2 = cipher.generatePassword(pwd1);   
        System.out.println("加密后的密码:"+pwd2);   
          
        System.out.print("验证密码是否下确:");   
       if(cipher.validatePassword(pwd2, pwd1)) {   
            System.out.println("正确");   
        }   
       else {   
            System.out.println("错误");   
        }  
       //将pwd3解密
//       System.out.println("加密后的密码:"+cipher.byteArrayToHexString(pwd3));  
    }   
}  