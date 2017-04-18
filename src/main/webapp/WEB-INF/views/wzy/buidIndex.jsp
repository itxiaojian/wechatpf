<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/wfw/style.css">
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<title>个人信息</title>
</head>
<style>
img {
	pointer-events: none;
}

a {
	-webkit-touch-callout: none;
}
</style>
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
<body onload="wx.hideOptionMenu()">
	<div class="phone_01" style="-webkit-overflow-scrolling: touch;">
		<div class="top_01">
			<span style="width: 10%; float: right;"> <img
				src="<%=path%>/resources/css/wfw/image/fanhui.png"
				style="margin-top: 30%">
			</span>
		</div>
		<div class="main_01">
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>
	</div>
	<div class="foot_01">
		<img src="<%=path%>/resources/css/wfw/image/foot.png" width="100%"
			height="100%">
	</div>
	</div>
</body>
</html>
