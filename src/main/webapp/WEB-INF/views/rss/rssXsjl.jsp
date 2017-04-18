<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,target-densitydpi=device-dpi" /> 
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/wzy/style_gs.css">
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/rss/rss_xsjl.js"></script>
<title>微主页</title>
<script type="text/javascript">
    path="<%=path%>";
    url_rss= "http://rss.ahbvc.edu.cn/rss/news.jsp?siteId=31&pageId=438&channelId=262";
</script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<style type="text/css">
.logo{
   background-color: white;
   height:1.4rem;
}
.main{
	top:1.4rem;
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
</head>
<script>
// 	var browser={
// 	    versions:function(){
// 	            var u = navigator.userAgent, app = navigator.appVersion;
// 	            return {         //移动终端浏览器版本信息
// 	                 trident: u.indexOf('Trident') > -1, //IE内核
// 	                presto: u.indexOf('Presto') > -1, //opera内核
// 	                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
// 	                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
// 	                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
// 	                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
// 	                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
// 	                iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
// 	                iPad: u.indexOf('iPad') > -1, //是否iPad
// 	                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
// 	            };
// 	         }(),
// 	         language:(navigator.browserLanguage || navigator.language).toLowerCase()
// 	}
// 	alert("语言版本: "+browser.language);
// 	alert(" 是否为移动终端: "+browser.versions.mobile);
// 	alert(" ios终端: "+browser.versions.ios);
// 	alert(" android终端: "+browser.versions.android);
// 	alert(" 是否为iPhone: "+browser.versions.iPhone);
// 	alert(" 是否iPad: "+browser.versions.iPad);
// 	alert(navigator.userAgent);
	document.getElementsByTagName('html')[0].style.fontSize=window.screen.width/10+'px';
	function doSub(num){
		if(num!=''&&num!=null){
			document.getElementById('zhuti').style.fontSize=window.screen.width/num+"px";
			var oA = document.getElementsByTagName("a");
			for(var  i = 0; i < oA.length; i++){
		        oA[i].style.fontSize=window.screen.width/num+"px";
		    }
			var oP = document.getElementsByTagName("span");
			for(var  i = 0; i < oP.length; i++){
		        oP[i].style.fontSize=window.screen.width/num+"px";
		    }
		}
	}
	
</script>
<body id="zhuti">
<%-- <div class="header">
	<img src="<%=path%>/resources/img/wzy/top_03.png">
</div> --%>
	<div class="phone">
		<div class="logo">
			<img style="height:1.4rem" src="<%=path%>/resources/img/wzy/logo.png">
		</div>
		<div class="main">

			<div class="tupian">
				 <img
						src="<%=path%>/resources/img/wzy/banner03.png" style="max-width:100%;">
			</div>
			<div class="title_name" style="margin-top:-6px;">
				<span class="title_nametext">&nbsp;&nbsp;学术交流</span> 
				<span class="title_nameTX" style="float: right;">字号：
					<a href="javascript:;" onclick="doSub(21)">大</a>
					<a href="javascript:;" onclick="doSub(23)">中</a>
					<a href="javascript:;" onclick="doSub(25)">小</a>
				</span>
				<!-- <a class="title_nameTX" href="#">更多</a> -->
			</div>
			<div class="New_list" style="padding-left:0.2rem;padding-right:0.2rem;">
				<ul id="tbl1">
				</ul>
				<div id="loading" style="display: none；font-size:10px;">
					<font color='red'>正在加载数据。。。</font>
				</div>
			</div>
		</div>
		<div class="footer">
			<img src="<%=path%>/resources/img/wzy/BQ.png">
		</div>
		<div class="SY_icon">
			<a href="<%=path%>/rss/torsspage?openId=${openId}"><img src="<%=path%>/resources/img/wzy/FH.png"></a>
		</div>
	</div>
</body>
</html>
