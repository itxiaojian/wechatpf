<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,target-densitydpi=device-dpi" /> 
<meta charset="utf-8">
<meta content="no-cache" http-equiv="Pragma" />
<meta content="no-cache" http-equiv="Cache-Control" />
<meta content="0" http-equiv="Expires" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/wzy/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/wzy/bootstrap.min.css">
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/swipe.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">

var appid="${map.appid}";//$('#appid').val();
var nonceStr="${map.nonceStr}";//$('#nonceStr').val();
var timestamp="${map.timestamp}";//$('#timestamp').val();
var signature="${map.signature}";//$('#signature').val();
wx.config({
  debug: false,
  appId: '${map.appid}',
  timestamp: '${map.timestamp}',
  nonceStr: '${map.nonceStr}',
  signature: '${map.signature}',
  jsApiList: [
    'checkJsApi',
    'onMenuShareTimeline',
    'onMenuShareAppMessage',
    'onMenuShareQQ',
    'onMenuShareWeibo',
    'hideMenuItems',
    'showMenuItems',
    'hideAllNonBaseMenuItem',
    'showAllNonBaseMenuItem',
    'translateVoice',
    'startRecord',
    'stopRecord',
    'onRecordEnd',
    'playVoice',
    'pauseVoice',
    'stopVoice',
    'uploadVoice',
    'downloadVoice',
    'chooseImage',
    'previewImage',
    'uploadImage',
    'downloadImage',
    'getNetworkType',
    'openLocation',
    'getLocation',
    'hideOptionMenu',
    'showOptionMenu',
    'closeWindow',
    'scanQRCode',
    'chooseWXPay',
    'openProductSpecificView',
    'addCard',
    'chooseCard',
    'openCard'
  ]
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	wx.hideOptionMenu();
});


	/* 返回首页 */
	function shouye() {
		WeixinJSBridge.call("closeWindow");
	}
	
	function index(){
		var openId = "${openId}";
		if(openId==""||openId==null){
			location.href="";
		}
	}
</script>
<style type="text/css">
   body {-webkit-overflow-scrolling: touch;overflow-scrolling: touch;}
   .GZ_top{ width:100%; height:50px; background:url(<%=path%>/resources/img/wzy/logo.png) center no-repeat; background-size:100%,100%; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
   .GS_main{width:100%; position:absolute; top:50px; left:0; bottom:30px;overflow:auto; font-size:12px}
   .GS_foot{width:100%; height:30px; background-color:#ccc;position:absolute; bottom:0; left:0; overflow:hidden; font-size:12px; color:#fff; text-align:center; line-height:30px;}
   .banner_img{ width:100%;}
   .ZY_btn img{ float:right; margin-top:5px; margin-right:20px;}
   .GZ_chatu{height:100px;width:170px; position:absolute; background-size:100%; bottom:30px; right:10px;}
   .col-xs-3 a{color:#666;}
   a:link{text-decoration:none;} 
</style>
<title>微主页</title>
</head>
<script>
	document.getElementsByTagName('html')[0].style.fontSize=window.screen.width/10+'px'
</script>
<body>
	<div class="container-fluid">
		<div class="GZ_chatu">
		</div>
		<div class="GZ_top">
			<div class="ZY_btn">
				<a href="#" onclick="shouye()"><img src="<%=path%>/resources/img/wzy/fanhui.png" width="40"></a>
			</div>
		</div>
		<div class="GS_main container-fluid">
			<c:forEach var="lbt" items="${listLbt}" varStatus="obj">
			<div class="banner_img">
<%-- 				<img src="<%=path%>/resources/img/wzy/banner99.png" style="width:100%;height:100;"> --%>
				<c:if test="${lbt.url !='-1' }">
            			<a href="${lbt.url }"><img src="<%=path%>/util/file/getFile?imgId=${lbt.tpbcid}" style="width:100%;height:100;"></a> 
            		</c:if>
            		<c:if test="${lbt.url == '-1' }">
                		<img src="<%=path%>/util/file/getFile?imgId=${lbt.tpbcid}"style="width:100%;height:100;" >
                	</c:if>
			</div>
			</c:forEach>
			<div class="container">
				<div class="row" style="text-align:center;">
        			<c:forEach var="zycd" items="${listmenu}" varStatus="obj">
        				<c:choose>
							<c:when test="${zycd.cdtbmc =='wzy_xydh.png'}">
								<div class="col-xs-3" style="padding-left:0px;padding-right:0px;padding-top:5%;">
									<a href="${zycd.cdurl}openId=${openId}"> 
										<img src="<%=path%>/resources/img/wzy/${zycd.cdtbmc}" width="50">
										<br>${zycd.cdmc}
									</a>
								</div>
							</c:when>
							<c:when test="${zycd.cdtbmc =='wzy_flzs.png'}">
								<div class="col-xs-3" style="padding-left:0px;padding-right:0px;padding-top:5%;">
									<a href="<%=path%>/${zycd.cdurl}"> 
										<img src="<%=path%>/resources/img/wzy/${zycd.cdtbmc}" width="50">
										<br>${zycd.cdmc}
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-xs-3" style="padding-left:0px;padding-right:0px;padding-top:5%;">
									<a href="<%=path%>/${zycd.cdurl}openId=${openId}"> 
									<img src="<%=path%>/resources/img/wzy/${zycd.cdtbmc}" width="50">
									<br>${zycd.cdmc}
									</a>
								</div>
							</c:otherwise>
            			</c:choose>
           			</c:forEach>
        		</div>
			</div>
		</div>
		<div class="GS_foot">
			合肥智圣系统集成有限公司 版权所有 Copyright © 2016 
		</div>
	</div>
</body>
</html>
