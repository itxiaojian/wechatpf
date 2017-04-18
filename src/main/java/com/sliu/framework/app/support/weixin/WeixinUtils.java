package com.sliu.framework.app.support.weixin;

import java.awt.Menu;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import EmojiFilter.EmojiFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sliu.framework.app.dto.AccessToken;
import com.sliu.framework.app.dto.JsapiTicket;
import com.sliu.framework.app.dto.WeiXinOauth2Token;
import com.sliu.framework.app.dto.WxUser.ReqShakeInfo;
import com.sliu.framework.app.dto.WxUser.ReqUserUpdateRemark;
import com.sliu.framework.app.model.WxGroupInfo;
import com.sliu.framework.app.model.WxUserInfo;
import com.sliu.framework.app.support.PropertiesInfo;
import com.sliu.framework.app.support.security.MyX509TrustManager;
import com.sliu.framework.app.util.ResponseData;
import com.sliu.framework.app.wxutil.model.WxShakeInfo;
import com.sliu.framework.app.wxutil.model.WxTemplate;
  
public class WeixinUtils {
	private static Logger logger = LoggerFactory.getLogger(WeixinUtils.class);  
	
    // 获取access_token的接口地址（GET） 限200（次/天）  
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	
	//获取jsapi_ticket调用微信JS接口的地址（GET）
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";  

	// 菜单创建（POST） 限1000（次/天）  
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
    
	// 菜单查询（GET） 限10000（次/天）
    public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    
	// 菜单删除（GET） 限1000 （次/天）
    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    
    //获取用户基本信息 (GET)
    public static String user_info_url ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    //设置用户备注名(POST)
    public static String user_updateremark_url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	
    //创建分组接口URL(POST)
    public static String group_create_url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    
    //修改分组接口URL(POST)
    public static String group_update_url = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
    
    //移动用户分组接口(POST)
    public static String user_update_group_url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
    
    //发送客服消息,http请求方式: POST
    public static String custom_message__send_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
   
    //获取关注者列表,http请求方式: GET
    public static String user_get_all_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
    
    //查询所有分组,http请求方式: GET（请使用https协议）
    public static String group_get_all_url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    
    //根据分组进行群发【订阅号与服务号认证后均可用】
    public static String sendall_url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    
    //上传多媒体文件,公众号可调用本接口来上传图片、语音、视频等文件到微信服务器，上传后服务器会返回对应的media_id，公众号此后可根据该media_id来获取多媒体。
    public static String upload_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    
    //永久上传图片消息
    public static String upload_material_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
    
    //通过用户的OpenID查询其所在的GroupID
    public static String user_get_groupid_url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
    
    //上传图文消息素材
    public static String media_uploadnews_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    
    //消息群发预览接口
    public static String mass_preview_url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
   
    //微信的授权接口
    public static String authorize_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    
    public static String shake_ul = "https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=ACCESS_TOKEN";
    
    //模版消息接口
    public static String template_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    
    /**
	 * 发起https请求并获取结果 
	 * 
	 * @param requestUrl   请求地址
	 * @param requestMethod   请求方式（GET、POST） 
	 * @param outputStr     提交的数据
	 * @return      JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
		        JSONObject jsonObject = null;  
		        StringBuffer buffer = new StringBuffer();  
		        try {  
		            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
		            TrustManager[] tm = { new MyX509TrustManager() };  
		            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
		            sslContext.init(null, tm, new java.security.SecureRandom());  
		            // 从上述SSLContext对象中得到SSLSocketFactory对象  
		            SSLSocketFactory ssf = sslContext.getSocketFactory();  
		  
		            URL url = new URL(requestUrl);
		            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
		            httpUrlConn.setSSLSocketFactory(ssf);

		            httpUrlConn.setDoOutput(true);
		            httpUrlConn.setDoInput(true);
		            httpUrlConn.setUseCaches(false);
		            // 设置请求方式（GET/POST）  
		            httpUrlConn.setRequestMethod(requestMethod);  
		  
		            if ("GET".equalsIgnoreCase(requestMethod))  
		                httpUrlConn.connect();  
		  
		            // 当有数据需要提交时  
		            if (StringUtils.hasText(outputStr)) {  
		                OutputStream outputStream = httpUrlConn.getOutputStream();  
		                // 注意编码格式，防止中文乱码  
		                outputStream.write(outputStr.getBytes("UTF-8"));  
		                outputStream.close();  
		            }  
		  
		            // 将返回的输入流转换成字符串  
		            InputStream inputStream = httpUrlConn.getInputStream();  
		            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
		            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
		  
		            String str = null;  
		            while ((str = bufferedReader.readLine()) != null) {  
		                buffer.append(str);  
		            }  
		            bufferedReader.close();  
		            inputStreamReader.close();  
		            // 释放资源  
		            inputStream.close();  
		            inputStream = null;  
		            httpUrlConn.disconnect();  
		            jsonObject = (JSONObject) JSON.parse(buffer.toString());  
		        } catch (ConnectException ce) {  
		        	logger.error("Weixin server connection timed out.");  
		        } catch (Exception e) {  
		        	logger.error("https request error:{}", e);  
		        }  
		        return jsonObject;  
		    }  

	
	/**
	 * 获取access_token 
	 * 
	 * @param appid   凭证
	 * @param appsecret   密钥 
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {  
	    AccessToken accessToken = null;  
	  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
	    // 如果请求成功  
	    if (null != jsonObject) {  
	        try {  
	            accessToken = new AccessToken();
	            accessToken.setToken(jsonObject.getString("access_token"));  
	            accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));  
	        } catch (JSONException e) {  
	            accessToken = null;  
	            // 获取token失败  
	            //log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	            logger.error("获取token失败 errcode:{} errmsg:{}"+ jsonObject.getInteger("errcode")+"-"+ jsonObject.getString("errmsg"));
	        }  
	    }  
	    return accessToken;  
	}  
	
	/**
	 * 获取微信js接口凭证
	 * @author liujiasen
	 * @date 2015年7月21日
	 * @param accessToken
	 * @return
	 */
	public static JsapiTicket getJsapiTicket(String accessToken) {
		JsapiTicket jsapiTicket = null;  
	  
	    String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);  
	    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
	    // 如果请求成功  
	    if (null != jsonObject) {  
	        try {  
	        	jsapiTicket = new JsapiTicket();  
	        	jsapiTicket.setTicket(jsonObject.getString("ticket"));  
	        	jsapiTicket.setExpiresIn(jsonObject.getInteger("expires_in"));  
	        } catch (JSONException e) {  
	        	jsapiTicket = null;  
	            // 获取token失败  
	            //log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	            logger.error("获取token失败 errcode:{} errmsg:{}"+ jsonObject.getInteger("errcode")+"-"+ jsonObject.getString("errmsg"));
	        }  
	    }  
	    return jsapiTicket;  
	}

	
	/**
	 * 创建菜单 
	 * 
	 * @param menu   菜单实例 
	 * @param accessToken   有效的access_token 
	 * @return    0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {  
	    int result = 0;  
	  
	    // 拼装创建菜单的url  
	    String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
	    // 将菜单对象转换成json字符串  
	    String jsonMenu = JSON.toJSONString(menu);  
	    // 调用接口创建菜单  
	    JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);  
	  
	    if (null != jsonObject) {
	        if (0 != jsonObject.getInteger("errcode")) {  
	            result = jsonObject.getInteger("errcode");  
	            logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));  
	        }  
	    }  
	  
	    return result;  
	}  

	/**
	 * 获取用户基本信息
	 *  
	 * @param accessToken    有效的access_token
	 * @param openid         用户标识，对当前公众号唯一
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static WxUserInfo getUserInfo(String accessToken , String openid) {
		WxUserInfo userinfo = new WxUserInfo();
		//拼装获取用户基本信息url
		String url = user_info_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
		// 调用接口获取用户信息
	    JSONObject jsonObject = httpRequest(url, "GET", null); 
	    // 如果请求成功  
	    if (null != jsonObject) {  
	    	try{
	    		String nick =  EmojiFilter.filterEmoji(jsonObject.getString("nickname"));
	    		userinfo.setSubscribe(jsonObject.getInteger("subscribe"));
	    		userinfo.setOpenid(jsonObject.getString("openid"));
//	    		userinfo.setNickname(AjaxUtil.StringFilter(jsonObject.getString("nickname")));
	    		
	    		StringBuilder nicksb = new StringBuilder();
	    		if(nick !=null){
		    		int l = nick.length();
		    		for (int i = 0; i < l; i++) {
			    		char _s = nick.charAt(i);
			    		if (isEmojiCharacter(_s)) {
			    			nicksb.append(_s);
			    		}
		    		}
	    		}
	    		String newnick = nicksb.toString();

//	    		String nick1 = "";
//	    		try {
//					nick1 = new String(nick.getBytes(),"gbk");
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
	    		userinfo.setNickname(newnick);
//	    		String urlEnCode = URLEncoder.encode(nickname,"UTF-8");
//	    		userinfo.setNickname(urlEnCode);
	    		userinfo.setSex(jsonObject.getInteger("sex"));
	    		userinfo.setCity(jsonObject.getString("city"));
	    		userinfo.setCountry(jsonObject.getString("country"));
	    		userinfo.setProvince(jsonObject.getString("province"));
	    		userinfo.setULanguage(jsonObject.getString("language"));
	    		userinfo.setHeadimgurl(jsonObject.getString("headimgurl"));	    		
	    		//userinfo.setSubscribeTime(WeixinUtils.formatTime(jsonObject.getString("subscribe_time")));
	    	}catch(JSONException e){
	    		userinfo = null;
		        // 获取userinfo失败  
	    		System.out.println("获取userinfo失败 errcode:"+jsonObject.getInteger("errcode")+"_"+"errmsg:"+jsonObject.getString("errmsg"));
	    	}
	    	
	    }
		     
		return userinfo;
	}
	
	public static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
 
                (codePoint == 0x9) ||
 
                (codePoint == 0xA) ||
 
                (codePoint == 0xD) ||
 
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
 
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
 
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
 
    }
	
    /** 
     * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss） 
     *  
     * @param i 消息创建时间 
     * @return 
     */  
    public static String formatTime(String i) {
        // 将微信传入的CreateTime转换成long类型，再乘以1000
        long msgCreateTime = Long.parseLong(i) * 1000L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return sdf.format(new Date(msgCreateTime));
    }  
	
    /**
     * 调用微信接口设置信息用户备注名,成功返回TRUE,失败返回FALSE
     * @param remark
     * @param openid
     * @return
     */
    public static ResponseData updateWxUserRemark(String remark, String openid) {
    	ResponseData responseData = ResponseData.SUCCESS_NO_DATA;
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = user_updateremark_url.replace("ACCESS_TOKEN", accessToken);
    	ReqUserUpdateRemark reqInfo = new ReqUserUpdateRemark();
    	reqInfo.setOpenid(openid);
    	reqInfo.setRemark(remark);
    	JSONObject jsonObject = httpRequest(requestUrl, "POST", JSON.toJSONString(reqInfo));  
    	Integer errcode = jsonObject.getInteger("errcode");
    	String errmsg = jsonObject.getString("errmsg");
    	if(errcode != 0 ) {
    		responseData.setSuccess(false);
    		responseData.setMessage(errmsg);
    	}
    	return responseData;
    }
    
    /**
     * 调用微信接口,创建微信分组信息
     * @param groupName
     * @return
     */
    public static Long createGroup(String groupName) {
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = group_create_url.replace("ACCESS_TOKEN", accessToken);
		Map<String, Object> createReqMap = new HashMap<String, Object>();
		Map<String, Object> groupMap = new HashMap<String, Object>();
		groupMap.put("name", groupName);
		createReqMap.put("group", groupMap);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", JSON.toJSONString(createReqMap));  
		String errcode = jsonObject.getString("errcode");
		if(!StringUtils.hasText(errcode)) {
			JSONObject object = jsonObject.getJSONObject("group");
			return object.getLong("id");
		}
		return 0L;
    }
    
    /**
     * 调用微信接口,更新微信分组信息
     * @param groupId
     * @param groupName
     * @return
     */
    public static ResponseData updateGroup(Long groupId, String groupName) {
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = group_update_url.replace("ACCESS_TOKEN", accessToken);
		Map<String, Object> createReqMap = new HashMap<String, Object>();
		Map<String, Object> groupMap = new HashMap<String, Object>();
		groupMap.put("id", groupId);
		groupMap.put("name", groupName);
		createReqMap.put("group", groupMap);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", JSON.toJSONString(createReqMap));  
		Integer errcode = jsonObject.getInteger("errcode");
		String errmsg = jsonObject.getString("errmsg");
		if(errcode == 0 ) {
			return ResponseData.SUCCESS_NO_DATA;
		}else {
			return new ResponseData(false, errmsg);
		}
    }
    
    /**
     * 调用微信接口,移动用户分组
     * @param openid
     * @param groupId
     * @return
     */
    
    public static ResponseData setUserGroup(String openid, Long groupId) {
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = user_update_group_url.replace("ACCESS_TOKEN", accessToken);
    	Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put("openid", openid);
    	reqMap.put("to_groupid", groupId);
    	JSONObject jsonObject = httpRequest(requestUrl, "POST", JSON.toJSONString(reqMap));  
    	Integer errcode = jsonObject.getInteger("errcode");
		String errmsg = jsonObject.getString("errmsg");
		if(errcode == 0 ) {
			return ResponseData.SUCCESS_NO_DATA;
		}else {
			return new ResponseData(false, errmsg);
		}
    }
    
    /**
     * 发送客服消息----文本
     * @param text
     */
    public static void sendCustomMessage_text(String openid, String text) {
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = custom_message__send_url.replace("ACCESS_TOKEN", accessToken);
    	//----构建发送文本消息报文
    	Map<String, Object> requsetMap = new HashMap<String, Object>();
    	Map<String, Object> textMap = new HashMap<String, Object>();
    	textMap.put("content", text);
    	requsetMap.put("touser", openid);
    	requsetMap.put("msgtype", "text");
    	requsetMap.put("text", textMap);
    	httpRequest(requestUrl, "POST", JSON.toJSONString(requsetMap));
    }
    
    /**
     * 取得所有关注者列表,
     * @return
     */
    public static List<Object> getAllSubscribeUsers() {
    	List<Object> list = new ArrayList<Object>();
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	//从头拉取,不需传入next_openid
    	String requestUrl = user_get_all_url.replace("ACCESS_TOKEN", accessToken);
    	JSONObject jsonObject = httpRequest(requestUrl, "GET", "");
    	String openid = jsonObject.getString("next_openid");
    	Integer countNum = jsonObject.getInteger("count");
    	JSONObject openidData = jsonObject.getJSONObject("data");
    	JSONArray jsonArray = openidData.getJSONArray("openid");
    	list.addAll(Arrays.asList(jsonArray.toArray()));
    	if(countNum == 10000 && StringUtils.hasText(openid)) {
    		list = getAllSubscribeUsers_Next(list, openid);
    	}
    	return list;
    }
    
    /**
     * 递归拉取关注数超过10000的用户
     * @param list
     * @param next_openid
     * @return
     */
    private static List<Object> getAllSubscribeUsers_Next(List<Object> list, String next_openid) {
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	//每次拉取如果超过10000个,则要从下个OPEN_ID继续拉取
    	String requestUrl = user_get_all_url.replace("ACCESS_TOKEN", accessToken) + "&next_openid="+next_openid;
    	JSONObject jsonObject = httpRequest(requestUrl, "GET", "");
    	String openid = jsonObject.getString("next_openid");
    	JSONObject openidData = jsonObject.getJSONObject("data");
    	if(openidData != null) {
        	JSONArray jsonArray = openidData.getJSONArray("openid");
        	list.addAll(Arrays.asList(jsonArray.toArray()));
        	if(StringUtils.hasText(openid)) {
        		getAllSubscribeUsers_Next(list, openid);
        	}    		
    	}
    	return list;
    }
    
    /**
     * 调用微信接口取得所有分组信息
     * @return
     */
    public static List<WxGroupInfo> getAllGroupInfo() {
    	List<WxGroupInfo> list = new ArrayList<WxGroupInfo>();
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = group_get_all_url.replace("ACCESS_TOKEN", accessToken);
    	JSONObject jsonObject = httpRequest(requestUrl, "GET", "");
    	JSONArray jsonArray = jsonObject.getJSONArray("groups");
    	if(jsonArray == null) {
    		return list;
    	}
    	for(int i=0; i<jsonArray.size(); i++) {
    		JSONObject groupJson = jsonArray.getJSONObject(i);
    		WxGroupInfo groupInfo = new WxGroupInfo();
    		groupInfo.setGroupId(groupJson.getLong("id"));
    		groupInfo.setName(groupJson.getString("name"));
    		groupInfo.setGCount(groupJson.getInteger("count"));
    		list.add(groupInfo);
    	}
    	return list;
    }
    
    /**
     * 调用微信接口进行消息群发
     * @param toObject
     * @param content
     * @param msgtype
     */
    public static JSONObject sendAllMessage(Long toObject, String content, String msgtype) {
    	logger.info("--开始调用微信接口群发消息,toObject:{}---content:{}---msgtype:{}", toObject, content, msgtype);
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = sendall_url.replace("ACCESS_TOKEN", accessToken);
    	//构造请求参数
    	Map<String, Object> filterMap = new HashMap<String, Object>();
    	Map<String, Object> mediaIdMap = new HashMap<String, Object>();
    	Map<String, Object> requestMap = new HashMap<String, Object>();
    	if(toObject == -1) {
    		filterMap.put("is_to_all", true);
    	}else {
    		filterMap.put("is_to_all", false);
    		filterMap.put("group_id", toObject);
    	}
    	requestMap.put("filter", filterMap);
		if("text".equals(msgtype)) {//群发文本消息
			mediaIdMap.put("content", content);
			requestMap.put("text", mediaIdMap);
			requestMap.put("msgtype", "text");
		}else if("image".equals(msgtype)) {//群发图片消息
			mediaIdMap.put("media_id", content);
			requestMap.put("image", mediaIdMap);
			requestMap.put("msgtype", "image");
		}else if("voice".equals(msgtype)) {//群发语音消息
			mediaIdMap.put("media_id", content);
			requestMap.put("voice", mediaIdMap);
			requestMap.put("msgtype", "voice");
		}else if("mpvideo".equals(msgtype)) {//群发视频消息
			mediaIdMap.put("media_id", content);
			requestMap.put("mpvideo", mediaIdMap);
			requestMap.put("msgtype", "mpvideo");
		}else if("mpnews".equals(msgtype)) {//群发图文消息
			mediaIdMap.put("media_id", content);
			requestMap.put("mpnews", mediaIdMap);
			requestMap.put("msgtype", "mpnews");
		}    	
		//调用接口发送
		String outputStr = JSON.toJSONString(requestMap);
		logger.info("群发请求报文:{}", outputStr);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", outputStr);
		return jsonObject;
    }
    
    /**
     * 调用微信接口上传素材文件
     * @param fileType  图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param filePath
     * @param attachMentFile 
     * @return
     * @throws IOException
     */
    public static String uploadMedia(String fileType, String filePath, MultipartFile attachMentFile) throws IOException {
		File file = new File(filePath);  
		if (!file.exists() || !file.isFile()) {  
		    throw new IOException("文件不存在");  
		}
		String filename = "";
		String fileNameSuffix = "";
		String filelength = "";
		String accessToken = WeixinCacheUtils.getAccessToken();
		String requestUrl = upload_media_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", fileType);

//		if("image".equalsIgnoreCase(fileType)){
//			requestUrl = upload_material_url.replace("ACCESS_TOKEN", accessToken);
//			System.out.println("文件长度: " + attachMentFile.getSize());  
//          System.out.println("文件类型: " + attachMentFile.getContentType());  
//          System.out.println("文件名称: " + attachMentFile.getName());  
//          System.out.println("文件原名: " + attachMentFile.getOriginalFilename()); 
//          filename = attachMentFile.getOriginalFilename();
//          fileNameSuffix = filename.substring( filename.lastIndexOf('.')+1 );
//          filelength = attachMentFile.getSize()+"";
//          System.out.println("文件后缀名："+fileNameSuffix);
//
//		}
//		String requestUrl = upload_media_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", fileType);
		//发起HTTP请求
		URL urlObj = new URL(requestUrl);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();  
//		if("image".equalsIgnoreCase(fileType)){
//             System.out.println("文件后缀名："+fileNameSuffix);
//     		 con.setRequestProperty("filename", filename);  
//    		 con.setRequestProperty("filelength", filelength);
//    		 con.setRequestProperty("type", fileType);  
//		}
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式  
		con.setDoInput(true);  
		con.setDoOutput(true);  
		con.setUseCaches(false); // post方式不能使用缓存  	
		// 设置请求头信息  
		con.setRequestProperty("Connection", "Keep-Alive");  
		con.setRequestProperty("Charset", "UTF-8");  	
		// 设置边界  
		String BOUNDARY = "----------" + System.currentTimeMillis();  
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY); 
		// 请求正文信息  
		// 第一部分：  
		StringBuilder sb = new StringBuilder();  
		sb.append("--"); // 必须多两道线  
		sb.append(BOUNDARY);  
		sb.append("\r\n");  
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName() + "\"\r\n");  
		sb.append("Content-Type:application/octet-stream\r\n\r\n");  
		byte[] head = sb.toString().getBytes("utf-8");  
		// 获得输出流  
		OutputStream out = new DataOutputStream(con.getOutputStream());  
		// 输出表头  
		out.write(head);  
		// 文件正文部分  
		// 把文件已流文件的方式 推入到url中  
		DataInputStream in = new DataInputStream(new FileInputStream(file));  
		int bytes = 0;  
		byte[] bufferOut = new byte[1024];  
		while ((bytes = in.read(bufferOut)) != -1) {  
			out.write(bufferOut, 0, bytes);  
		}  
		in.close();  
		// 结尾部分  
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
		out.write(foot);  
		out.flush();  
		out.close();  
		StringBuffer buffer = new StringBuffer();  
		BufferedReader reader = null;  
		try {  
		    // 定义BufferedReader输入流来读取URL的响应  
		    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
		    String line = null;  
		    while ((line = reader.readLine()) != null) {  
		    	buffer.append(line);  
		    }   
		} catch (IOException e) {  
		    System.out.println("发送POST请求出现异常！" + e);  
		e.printStackTrace();  
		throw new IOException("数据读取异常");  
		} finally {  
		    if(reader!=null){  
		    	reader.close();  
		    }  
		    //关闭HTTP连接
		    con.disconnect();
		}  
		JSONObject jsonObject = (JSONObject) JSON.parse(buffer.toString());
		Integer errcode = jsonObject.getInteger("errcode");
		if(errcode != null) {
			String errmsg = jsonObject.getString("errmsg");
		return "error|" + errmsg;
		}else {
			String media_id = jsonObject.getString("media_id");
			return media_id;
		} 	     		 
    }
    
    /**
     * 根据用户openid取得对应用户的分组ID
     * @param openid
     * @return
     */
    public static String getGroupIdByOpenId(String openid) {
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = user_get_groupid_url.replace("ACCESS_TOKEN", accessToken);
    	Map<String, Object> requestMap = new HashMap<String, Object>();
    	requestMap.put("openid", openid);
    	JSONObject jsonObject = httpRequest(requestUrl, "POST", JSON.toJSONString(requestMap));  
    	Integer errcode = jsonObject.getInteger("errcode");
		if(errcode != null ) {
			String errmsg = jsonObject.getString("errmsg");
			return "error|"+errmsg;
		}else {
			return jsonObject.getString("groupid");
		}
    }
    
    /**
     * 调用微信接口上传图文消息素材
     * @param articlesJson
     * @return
     */
    public String media_uploadnews(List<Map<String, Object>> articles) {
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = media_uploadnews_url.replace("ACCESS_TOKEN", accessToken);
    	Map<String, Object> requestMap = new HashMap<String, Object>();
    	requestMap.put("articles", articles);
    	JSONObject jsonObject = httpRequest(requestUrl, "POST", JSON.toJSONString(requestMap));  
    	Integer errcode = jsonObject.getInteger("errcode");
		if(errcode != null ) {
			String errmsg = jsonObject.getString("errmsg");
			return "error|"+errmsg;
		}else {
			return jsonObject.getString("media_id");
		}    
	}
    
	/**
	 * 调用微信接口给某个用户测试群发效果
	 * @param msgtype ----mpnews：图文消息;text：文本;voice：语音;image：图片;mpvideo:视频
	 * @param touser
	 * @param content 群发内容：文本就是发送的文本,其它素材是对应的media_id
	 * @return
	 */
    public static ResponseData massPreview(String msgtype, String touser, String content) {
    	String accessToken = WeixinCacheUtils.getAccessToken();
    	String requestUrl = mass_preview_url.replace("ACCESS_TOKEN", accessToken);
    	//构造请求参数
    	Map<String, Object> mediaIdMap = new HashMap<String, Object>();
    	Map<String, Object> requestMap = new HashMap<String, Object>();
    	if("text".equals(msgtype)) {//群发文本消息
    		mediaIdMap.put("content", content);
    		requestMap.put("touser", touser);
			requestMap.put("text", mediaIdMap);
			requestMap.put("msgtype", "text");
		}else if("image".equals(msgtype)) {//群发图片消息
			mediaIdMap.put("media_id", content);
			requestMap.put("touser", touser);
			requestMap.put("image", mediaIdMap);
			requestMap.put("msgtype", "image");
		}else if("voice".equals(msgtype)) {//群发语音消息
			mediaIdMap.put("media_id", content);
			requestMap.put("touser", touser);
			requestMap.put("voice", mediaIdMap);
			requestMap.put("msgtype", "voice");
		}else if("mpvideo".equals(msgtype)) {//群发视频消息
			mediaIdMap.put("media_id", content);
			requestMap.put("touser", touser);
			requestMap.put("mpvideo", mediaIdMap);
			requestMap.put("msgtype", "mpvideo");
		}else if("mpnews".equals(msgtype)) {//群发图文消息
			mediaIdMap.put("media_id", content);
			requestMap.put("touser", touser);
			requestMap.put("mpnews", mediaIdMap);
			requestMap.put("msgtype", "mpnews");
		}   
		//调用接口发送
		JSONObject jsonObject = httpRequest(requestUrl, "POST", JSON.toJSONString(requestMap));
    	Integer errcode = jsonObject.getInteger("errcode");
    	String errmsg = jsonObject.getString("errmsg");
		if(errcode == 0 ) {
			return ResponseData.SUCCESS_NO_DATA;
		}else {
			return new ResponseData(false, errmsg);
		}
    }

    /**
     * 同步微信上的自定义菜单
     * @author:zhangyi 
     * @version 创建时间：2015年6月4日 下午2:47:10
     * @param map2 
     */
	public static int refreshWxZdycd(Map<String, Object> map) {
	    int result = 0;  
	    String accessToken = WeixinCacheUtils.getAccessToken();
	    // 拼装创建菜单的url  
	    String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
//	    Map<String,String> map = new HashMap<String,String>();
	    // 将菜单对象转换成json字符串  
	    String jsonMenu = JSON.toJSONString(map);
	    // 调用接口创建菜单  
	    JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);  
	    if (null != jsonObject) {  
	        if (0 != jsonObject.getInteger("errcode")) {
	            result = jsonObject.getInteger("errcode");  
	            logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));  
	        }  
	    }  
	    return result;  
	}
	
	/**
	 * 获取
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月11日 上午10:28:29 
	 * @param appId
	 * @param appSecret
	 * @param code
	 * @return
	 */
	public static WeiXinOauth2Token getOauth2AccessToken(String code) {
        WeiXinOauth2Token wat = new WeiXinOauth2Token();
        String requestUrl = authorize_url.replace("APPID", PropertiesInfo.appId).replace("SECRET", PropertiesInfo.appSecret).replace("CODE", code);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
                try {
                        wat = new WeiXinOauth2Token();
                        wat.setAccessToken(jsonObject.getString("access_token"));
                        wat.setExpiresIn(jsonObject.getInteger("expires_in"));
                        wat.setRefreshToken(jsonObject.getString("refresh_token"));
                        wat.setOpenId(jsonObject.getString("openid"));
                        wat.setScope(jsonObject.getString("scope"));
                } catch (Exception e) {
                        wat = null;
                        String errorCode = jsonObject.getString("errcode");
                        String errorMsg = jsonObject.getString("errmsg");
                        logger.error("获取网页授权凭证失败 errcode{},errMsg", errorCode, errorMsg);
                }
        }
        return wat;
	}

	

	/**
	 * 发送https请求
	 * @author:zhangyi 
	 * @version 创建时间：2015年6月11日 上午10:28:57 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
            JSONObject jsonObject = null;
            try {
                    // 创建SSLContext对象，并使用我们指定的信任管理器初始化
                    TrustManager[] tm = { new MyX509TrustManager() };
                    SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                    sslContext.init(null, tm, new java.security.SecureRandom());
                    // 从上述SSLContext对象中得到SSLSocketFactory对象
                    SSLSocketFactory ssf = sslContext.getSocketFactory();
                    URL url = new URL(requestUrl);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setSSLSocketFactory(ssf);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    // 设置请求方式（GET/POST）
                    conn.setRequestMethod(requestMethod);
                    //conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                    // 当outputStr不为null时向输出流写数据
                    if (null != outputStr) {
                            OutputStream outputStream = conn.getOutputStream();
                            // 注意编码格式
                            outputStream.write(outputStr.getBytes("UTF-8"));
                            outputStream.close();
                    }
                    // 从输入流读取返回内容
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String str = null;
                    StringBuffer buffer = new StringBuffer();
                    while ((str = bufferedReader.readLine()) != null) {
                            buffer.append(str);
                    }
                    // 释放资源
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    inputStream = null;
                    conn.disconnect();
                    jsonObject = (JSONObject) JSON.parse(buffer.toString());  
            } catch (ConnectException ce) {  
	        	logger.error("Weixin server connection timed out.");  
	        } catch (Exception e) {  
	        	logger.error("https request error:{}", e);  
	        }  
            return jsonObject;
}
	
	/**
	 * 获取周边摇一摇基本信息
	 *  
	 * @param accessToken    有效的access_token
	 * @param openid         用户标识，对当前公众号唯一
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static WxShakeInfo getShakeInfo(String ticket,int needPoi) {
		WxShakeInfo shakeInfo = new WxShakeInfo();
		String accessToken = WeixinCacheUtils.getAccessToken();
		//拼装获取用户基本信息urlaccess_token=ACCESS_TOKEN&ticket=TICKET&need_poi=NEED_POI";
		if(ticket != null){
			String url = shake_ul.replace("ACCESS_TOKEN", accessToken);
			// 调用接口获取用户信息
			ReqShakeInfo shakeinfo = new ReqShakeInfo();
			shakeinfo.setTicket(ticket);
			shakeinfo.setNeed_poi(needPoi);
		    JSONObject jsonObject = httpRequest(url, "POST", JSON.toJSONString(shakeinfo)); 
		    // 如果请求成功  
		    if (null != jsonObject) {
		    	try{
		    		JSONObject data = jsonObject.getJSONObject("data");
		    		JSONObject beacon_info = data.getJSONObject("beacon_info");
		    		String distance = beacon_info.getString("distance");
		    		String major = beacon_info.getString("major");
		    		//String measure_power = beacon_info.getString("measure_power");
		    		String uuid = beacon_info.getString("uuid");
		    		String minor = beacon_info.getString("minor");
		    		//String brand_userame = data.getString("brand_userame");
		    		String openid = data.getString("openid");
		    		String pageId = data.getString("page_id");
		    		
		    		shakeInfo.setUuid(uuid);
		    		shakeInfo.setDistance(distance);
		    		shakeInfo.setMajor(major);
		    		shakeInfo.setMinor(minor);
		    		shakeInfo.setOpenid(openid);
		    		shakeInfo.setPageId(pageId);
		    	}catch(JSONException e){
		    		shakeInfo = null;  
			        // 获取userinfo失败  
		    		System.out.println("获取shakeinfo失败 errcode:"+jsonObject.getInteger("errcode")+"_"+"errmsg:"+jsonObject.getString("errmsg"));
		    	}
		    }
	    }
		return shakeInfo;
	}

	 /**
     * 发送模板消息
     * appId 公众账号的唯一标识
     * appSecret 公众账号的密钥
     * openId 用户标识
     */
    public static ResponseData sendTemplateMessage(String openId,WxTemplate temp) {
    	String access_token = WeixinCacheUtils.getAccessToken();
        String url = template_url.replace("ACCESS_TOKEN", access_token);
//        temp.setUrl("http://weixin.qq.com/download");
//        temp.setTouser(openId);
//        temp.setTopcolor("#000000");
//        temp.setTemplate_id("ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY");
//        temp.setTemplateId("LBBm6qscHUcz-0Gh7PlBonsLKtJBHjCLIirnaiBZ4xQ");
//        Map<String,TemplateDataView> m = new HashMap<String,TemplateDataView>();
//        TemplateDataView first = new TemplateDataView();
//        first.setColor("#000000");
//        first.setValue("这里填写您要发送的模板信息");
//        m.put("first", first);
//        TemplateDataView name = new TemplateDataView();  
//        name.setColor("#000000");  
//        name.setValue("另一行内人");  
//        m.put("name", name);
//        TemplateDataView wuliu = new TemplateDataView();  
//        wuliu.setColor("#000000");  
//        wuliu.setValue("N行");  
//        m.put("wuliu", wuliu);
//        TemplateDataView orderNo = new TemplateDataView();  
//        orderNo.setColor("#000000");  
//        orderNo.setValue("**666666");  
//        m.put("orderNo", orderNo);
//        TemplateDataView receiveAddr = new TemplateDataView();  
//        receiveAddr.setColor("#000000");  
//        receiveAddr.setValue("*测试模板");  
//        m.put("receiveAddr", receiveAddr);
//        TemplateDataView remark = new TemplateDataView();
//        remark.setColor("#000000");  
//        remark.setValue("***备注说明***");  
//        m.put("Remark", remark);
//        temp.setData(m);
        String jsonString = JSON.toJSONString(temp);
        JSONObject jsonObject = httpRequest(url, "POST", jsonString);
        System.out.println(jsonObject);
        int result = 0;
        if (null != jsonObject) {
             if (0 != jsonObject.getInteger("errcode")) {
            	 	access_token = WeixinCacheUtils.createAccessTokenPutCache();
            	 	jsonObject = httpRequest(url, "POST", jsonString);
            	 	if (null != jsonObject) {
                        if (0 != jsonObject.getInteger("errcode")) {
                        	result = jsonObject.getInteger("errcode");
                        	logger.error("错误 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
                    	} 
            	 	}
             }
         }
        logger.info("模板消息发送结果："+result);
        
        Integer errcode = jsonObject.getInteger("errcode");
    	String errmsg = jsonObject.getString("errmsg");
	    if(errcode == 0 ) {
			return ResponseData.SUCCESS_NO_DATA;
		}else {
			return new ResponseData(false, errmsg);
		}
    }
    
}
