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
	.main{
	top:1.1rem;
}
</style>
<title>服务帮助列表</title>
</head>
<body style="overflow: auto;" id="zhuti" onload="wx.hideOptionMenu()">
	<div class="main">
		<div class="DYtop1">
			<img style="width:100%; " src="<%=path%>/resources/img/wzy/logo.png" />
		<div class="anniu">
			<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}">
			   <img  
			    src="<%=path%>/resources/img/wzy/FH.png" />
			    </a>
			</div>
		</div>
		<div class="bottom" style="border:0px;">
			<div class="tupian">
				<img src="<%=path%>/resources/img/zzjgBT.png" style="margin-top:-4px;"/>
			</div>
			<div class="title_name" style="width: 100%;">
				<span class="title_nametext">&nbsp;&nbsp;服务帮助列表</span> 
				<span class="title_nameTX" style="float: right;">字号：
					<a href="javascript:;" onclick="doSub(41)">大</a>
					<a href="javascript:;" onclick="doSub(43)">中</a>
					<a href="javascript:;" onclick="doSub(45)">小</a>
				</span>
				<!-- <a class="title_nameTX" href="#">更多</a> -->
			</div>
			<div class="New_list">
			
				<c:forEach items="${list}" var="list">
				<table style="width: 100%">
				<tr>
				            <a href="<%=path%>/wzy/ZyXyxgxx/zyXyxgxxDetail?id=${list.id}">
				            <c:choose>
					 			<c:when test="${fn:length(list.xwbt) > 12}">
									<c:out value="${fn:substring(list.xwbt,0,12)}..." />
								</c:when>
								<c:otherwise>
									<c:out value="${list.xwbt}" />
								</c:otherwise>
							</c:choose>
							</a> 
							<span style="float:right;"> <fmt:formatDate value="${list.sxsj }" type="date" dateStyle="medium"/></span>
				</tr>
				</table>
				</c:forEach>
			<c:if test="${empty list}"><div><span style="color:red;font-size:13px;margin-left:16px;">暂无数据!</span></div></c:if>
			</div>
		</div>
		<div class="footer" >
			<img src="<%=path%>/resources/img/wzy/BQ.png">
		</div>
		</div>
</body>
</html>