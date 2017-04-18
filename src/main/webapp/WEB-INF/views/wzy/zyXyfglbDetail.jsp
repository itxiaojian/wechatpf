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
<link href="<%=path%>/resources/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
	<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/dongtai.css" />
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>

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
</script>
<style type="text/css">
	.anniu{top:1.1%;right:1%; }
    .anniu img{display:block;width:100%;height:6.9%;} 
</style>
<title>学院风光列表</title>
</head>
<body style="overflow: auto;" onload="wx.hideOptionMenu()">
 <form method="post" action="<%=path%>/wzy/ZyXyxgxx/zyXyxgxxDetail" id="form1">
	<div class="main">
		<div class="DYtop">
			<img style="width:100%; " src="<%=path%>/resources/img/wzy/logo.png" />
			<div class="anniu">
			<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}" >
			   <img 
			    src="<%=path%>/resources/img/wzy/FH.png" />
			    </a>
			</div>
		</div>

	<!-- 	<div class="middle">
			<h1>学院风光</h1>
		</div> -->
<div class="bottom" style="border:0px;">
		<div> <input type="hidden"  value="${num}" id="num">
		</div>
		<div class="content" id="ceshi">
        
        <c:forEach items="${list}" var="list" varStatus="status" >
            <div class="newslist" onclick="document.location='<%=path%>/wzy/ZyXyxgxx/zyXyxgxxDetail?id=${list.id}';">
            <div class="newstitle" style="text-align: center">${list.xwbt }</div>
            <div class="newstime">时间:${list.sxsj}</div>
            <div id="divid${status.count}">
                 ${list.xwnr}
			</div>
            <div class="newsimg" id="newsid${status.count}" style="text-align: center"></div>
            <div class="newsurl">
            <span id="t_28">显示全文>></span>
            </div>
            </div>
        </c:forEach>
        
            </div>
 </div>           
		</div>
		</form>
</body>
</html>

<script>

$(function (){
	var num = $("#num").val();
	for(i=1;i<=num;i++){
		$("#divid"+i).hide();
		var val=$("#divid"+i).find("img");
		$("#newsid"+i).html(val.get(0)); 
	}	
});



    function DisplayText(NewsID) {
        var AllText = document.getElementById("c_" + NewsID);
        var SubText = document.getElementById("n_" + NewsID);
        var ButtonText = document.getElementById("t_" + NewsID);

        if (AllText.style.display == "none") {
            AllText.style.display = "block";
            SubText.style.display = "none";
            ButtonText.innerText = "隐藏全文>>";
        }
        else {
            AllText.style.display = "none";
            SubText.style.display = "block";
            ButtonText.innerText = "显示全文>>";
        }
    }
</script>