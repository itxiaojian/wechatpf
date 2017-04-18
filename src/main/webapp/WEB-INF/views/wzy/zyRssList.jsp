<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<link href="<%=path%>/resources/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xwlb.css" />
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<title>学院新闻</title>
<script type="text/javascript">
    path="<%=path%>";
</script>
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
.wenzi{
	position:absolute;
 	left:45%;
 	margin-top:4%;
 	z-index:2;
 	font-size:15px;
 	margin-bottom:4%;
}

a{
text-decoration: none;
}

.tupian1{
	width:100%;
}

.main1{
	margin-top:5px;
	margin-left:5px;
	margin-right:4px;
}

.main2{
	margin-top:5px;
	margin-bottom:5px;
	margin-left:4px;
	width:97.5%;
	}
</style>
</head>
<body style="overflow: auto;" onload="wx.hideOptionMenu()">
	<div class="main">
		<div>
			<img style="width: 100%; height:52px;"
				src="<%=path%>/resources/img/wzy/logo.png" /><br/>
			<div class="anniu">
				<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}"
					style="float: right;"> <img
					 src="<%=path%>/resources/img/wzy/FH.png" style="width:100%;height:7%;" />
				</a>
			</div>
		</div>

		<!-- <div class="middle">
			<h1>学院新闻</h1>
		</div> -->

		<div class="bottom" style="border:0px;">
			<div class="tupian" style="margin-top: 2px;">
				<img src="<%=path%>/resources/img/LBT.png" />
			</div >
			<div id="tbl1" style="width: 95%;"></div>
			<div class="main2">
				<span><a href="<%=path%>/rss/rssList?openId=${openId}">
				<img class="tupian1" style="max-width:100%" src="<%=path%>/resources/img/zy_zhxw.png" />
				<!-- <span class="wenzi">综合新闻</span> -->
				</a></span>
			</div>
			<div class="main1">
				<span><a href="<%=path%>/rss/rssTzgg?openId=${openId}">
				<img class="tupian1"style="max-width:100%"  src="<%=path%>/resources/img/zy_tzgg.png" />
				<!-- <span class="wenzi">通知公告</span> -->
				</a>
				</span>
			</div>
			<div class="main1">
				<span><a href="<%=path%>/rss/rssXykx?openId=${openId}">
				<img class="tupian1" style="max-width:100%" src="<%=path%>/resources/img/zy_xykx.png" />
				<!-- <span class="wenzi">校园快讯</span> -->
				</a></span>
			</div>
			<%-- <div class="main1">
				<span><a href="<%=path%>/rss/rssXsjl?openId=${openId}">
				<img class="tupian1" style="max-width:100%" src="<%=path%>/resources/img/zy_xsjl.png" />
				<!-- <span class="wenzi">学术交流</span> -->
				</a></span>
			</div> --%>
		</div>
	</div>
</body>
</html>