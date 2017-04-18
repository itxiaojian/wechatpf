 <%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
   <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>  
 <link href="<%=path%>/resources/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/xyxw.css" />
	
	<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
var appid="${ma1p.appid}";//$('#appid').val();
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
	<title>一键救援</title>
<style type="text/css">
	.anniu{top:1.1%;right:1%; }
    .anniu img{display:block;width:100%;height:6.9%;} 
</style>
</head>
<body style="overflow: auto;" onload="wx.hideOptionMenu()">
   <div class="main">
     <div  class="DYtop" >
               <img style="width:100%;height: 10%; "  src="<%=path%>/resources/img/wzy/logo.png" />
               <div class="anniu">
               <a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}"  >
			   <img  
			    src="<%=path%>/resources/img/wzy/FH.png" />
			    </a>
			</div>
		
       </div>
       <div class="neirong">
          <div class="shang">
               <h style="font-weight:bold;">一键救援</h>
          </div>
          <div class="xia">
                <p>请点击电话号码直接拨打</p>
               <c:forEach items="${map}" var="map">
                <p>${map.bmmc }</p>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <img style="width: 10%;" src="<%=path%>/resources/img/dianhuatubiao.jpg" />
                <a href="tel:${map.dhhm}">${map.dhhm}</a>
                </c:forEach>
          </div>
       </div>
   </div>
</body>
</html>