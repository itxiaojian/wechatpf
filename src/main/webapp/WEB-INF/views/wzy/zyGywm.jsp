<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link href="<%=path%>/resources/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />

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
</script>
<style type="text/css">
	.anniu{top:1.1%;right:1%; }
    .anniu img{display:block;width:100%;height:6.9%;}
</style>
<style type="text/css">
   body {-webkit-overflow-scrolling: touch;overflow-scrolling: touch;}
   .GZ_top{ width:100%; height:50px; background:url(<%=path%>/resources/img/wzy/logo.png) center no-repeat; background-size:100%,100%; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
   .GS_main{width:100%; position:absolute; top:50px; left:0; bottom:30px;overflow:auto; font-size:12px}
   .GS_foot{width:100%; height:30px; background-color:#ccc;position:fixed; bottom:0; left:0; overflow:hidden; font-size:12px; color:#fff; text-align:center; line-height:30px;}
   .banner_img{ width:100%;}
   .ZY_btn img{ float:right; margin-top:5px; margin-right:20px;}
   .GZ_chatu{height:100px;width:170px; position:absolute; background-size:100%; bottom:30px; right:10px;}
   .col-xs-3 a{color:#666;}
   a:link{text-decoration:none;} 
</style>
<title>关于我们</title>
</head>
<body  style="overflow: auto;" onload="wx.hideOptionMenu()">

  <div class="DYtop" >
			<img style="width:100%;height:10%;" src="<%=path%>/resources/img/wzy/logo.png" /><br/>
			<div class="anniu">
			<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}" >
			   <img 
			    src="<%=path%>/resources/img/wzy/FH.png" />
		    </a>
			</div>
		</div>
<!-- <div class="middle">
   <h1>关于我们</h1>
</div> -->
<div class="bottom" style="border:0px;">
<div class="xia" style="margin-left: 2%;margin-top: 3%">
<span style="font-weight: 600">${map.xwnr}</span><br />
</div>
<div class="GS_foot">
	合肥智圣系统集成有限公司 版权所有 Copyright © 2016 
</div>
</div>
</body>
</html>