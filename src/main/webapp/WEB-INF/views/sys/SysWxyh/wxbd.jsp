<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<% 
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/> 
<title>绑定学校帐号</title>
<link rel="shortcut icon" href="<%=path%>/system/login/resources/images/favicon.ico" />
<link href="<%=path%>/system/login/resources/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/system/login/resources/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/resources/js/jquery.i18n.properties-1.0.9.js" ></script>
<script type="text/javascript" src="<%=path%>/system/login/resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/resources/js/md5.js"></script>
<script type="text/javascript">
	var homePath="<%=path%>";
</script>
<script type="text/javascript" src="<%=path%>/system/login/resources/js/page_login.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></script>
<!--[if IE]>
  <script src="resources/js/html5.js"></script>
<![endif]-->
<!--[if lte IE 6]>
	<script src="resources/js/DD_belatedPNG_0.0.8a-min.js" language="javascript"></script>
	<script>
	  DD_belatedPNG.fix('div,li,a,h3,span,img,.png_bg,ul,input,dd,dt');
	</script>
<![endif]-->
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
	var yhjy="${yhjy}";
	if(yhjy=='Y'){
		alert("该微信号已绑定过账户！且已经被停用，请联系管理员。");
		//WeixinJSBridge.call("closeWindow");
	}
});
// 	function load(){
// 		wx.hideOptionMenu();
// 		var yhjy="${yhjy}";
// 		if(yhjy=='Y'){
// 			alert("该微信号已绑定过账户！且已经被停用，请联系管理员。");
// 			//WeixinJSBridge.call("closeWindow");
// 		}
// 	}
</script>
</head>
<body class="loginbody" style="margin: 0px;">
<div class="dataEye">
	<div class="loginbox">
		<div class="login-content">
			<div class="loginbox-title">
				<h3>绑定学校帐号</h3>
			</div>
			<form id="signupForm">
			<input type="hidden" name="url" id="url" value="${url }">
			<input type="hidden" name="openId" id="openId" value="${openId}">
			<div class="login-error"></div>
			<div class="row">
				<label class="field">学号或教工号</label>
				<input type="text" class="input-text-user input-click" name="email" id="email">
			</div>
			<div class="row">
				<label class="field">密码</label>
				<input type="password" class="input-text-password input-click" name="password" id="password">
			</div>
			<div class="row btnArea">
				<a class="login-btn" id="submit">绑定帐号</a>
			</div>
			<div class="row tips">
				<p>友情提示：</p>
				<p>用户帐号：学号或教工号</p>
				<p>用户密码：用户密码：与校园信息门户密码相同，默认为身份证号后8位，有X时需大写。</p>
				<p>注意：一个微信号只能绑定一个学号/工号，一个学号/工号可以绑定多个微信号。</p>
				<p style="color:red">注意：请注意不要在其他公众号中泄漏自己的用户名和密码，以免导致信息泄漏。</p>
			</div>
			</form>
		</div>
	</div>
</div>
<div class="loading">
<!-- 	<div class="mask"> -->
<!-- 		<div class="loading-img"> -->
<%-- 		<img src="<%=path%>/system/login/resources/images/loading.gif" width="31" height="31"> --%>
<!-- 		</div> -->
<!-- 	</div> -->
</div>
</body>
</html>