<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/wzy/style_gs.css">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
	
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
var appid="${map1.appid}";//$('#appid').val();
var nonceStr="${map1.nonceStr}";//$('#nonceStr').val();
var timestamp="${map1.timestamp}";//$('#timestamp').val();
var signature="${map1.signature}";//$('#signature').val();
wx.config({
  debug: false,
  appId: '${map1.appid}',
  timestamp: '${map1.timestamp}',
  nonceStr: '${map1.nonceStr}',
  signature: '${map1.signature}',
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

var u = navigator.userAgent, app = navigator.appVersion;
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
if(isiOS){
	document.getElementsByTagName('html')[0].style.fontSize=window.screen.width/10+'px';
}else{
	document.getElementsByTagName('html')[0].style.fontSize='38px';
}
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
<style type="text/css">
	.anniu{top:1.1%;right:1%; }
    .anniu img{display:block;width:100%;height:6.9%;} 
    .DYtop1 {
	    width: 100%;
	    margin-left: auto;
	    margin-right: auto;
	}
</style>
<title>迎新公告</title>
</head>
<body style="overflow: auto;" id="zhuti">
	<div class="main" style="top:1rem;">
		<div class="DYtop1">
			<img style="width:100%; " src="<%=path%>/resources/img/wzy/logo.png" />
		<div class="anniu">
			<a href="<%=path%>/wsh/zy/zhuye?openId=${openId}">
			   <img  style="margin-top:-5px;"
			    src="<%=path%>/resources/img/wzy/FH.png" />
			    </a>
			</div>
		</div>
		<div class="bottom" style="border:0px;">
			<div class="tupian">
				<img src="<%=path%>/resources/img/zzjgBT.png" style="margin-top:-1.2%;"/>
			</div>
			<div class="title_name" style="width: 100%;margin-top:-1%">
				<span class="title_nametext">&nbsp;&nbsp;迎新公告</span> 
				<span class="title_nameTX" style="float: right;">字号：
					<a href="javascript:;" onclick="doSub(41)">大</a>
					<a href="javascript:;" onclick="doSub(43)">中</a>
					<a href="javascript:;" onclick="doSub(45)">小</a>
				</span>
				<!-- <a class="title_nameTX" href="#">更多</a> -->
			</div>
			<div class="New_list">
			
				<c:forEach items="${Yxgglist}" var="list">
				 <table style="width: 100%">
						    <tr>
							<a style="margin-left:-12px;" href="<%=path%>/wyx/WyxYxgg/YxggxxDetail?id=${list.id}">
								<c:choose>  
									<c:when test="${fn:length(list.title) > 15}">  
     									<c:out value="${fn:substring(list.title,0,15)}..." />  
									</c:when>  
									<c:otherwise>  
     									<c:out value="${list.title}" />  
									</c:otherwise>  
							    </c:choose>
							</a>
							<span style="float:right;">${fn:substring(list.fbtime,0,10) }</span>
						    </tr>
			    </table>
				</c:forEach>
			</div>
		</div>
		<div class="footer" style="margin-bottom: 7px;">
			<img src="<%=path%>/resources/img/wzy/BQ.png">
		</div>
		</div>
</body>
</html>