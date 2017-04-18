<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
   <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>  
 <link href="<%=path%>/resources/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/xyxw.css" />

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
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<title>学院简介</title>
</head>
<body style="overflow: auto;" onload="wx.hideOptionMenu()">
   <div class="main">
      <div  class="DYtop" >
               <img style="width:100%; "  src="<%=path%>/resources/img/wzy/logo.png" />
               <div class="anniu">
               <a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}">
			   <img  
			    src="<%=path%>/resources/img/wzy/FH.png" />
			    </a>
			</div>
		
       </div>
       
    <!--    <div class="middle">
			<h1>学院简介</h1>
       </div> -->
       
       <div class="bottom" style="border:0px;">
             <div class="h">
                <h2>${map.xwbt}</h2>
             </div>
             <div  style="font-size:85%;text-align:center;margin-top: -7px;"> <%-- 来源单位：${map.bmbh }&nbsp;&nbsp;&nbsp; --%>发布时间：<fmt:formatDate value="${map.sxsj }" type="date" dateStyle="medium"/></div>
             <div >
                 ${map.xwnr}
             </div>
       </div>
   </div>
</body>
</html>