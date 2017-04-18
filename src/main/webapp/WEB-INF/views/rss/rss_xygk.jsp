<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
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
	src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/rss/rss_main_xygk.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>

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

<script type="text/javascript">
    path="<%=path%>";
	</script>
	<style type="text/css">
	.bt5{
	color: #000000;
    font-size: 16px;
    font-weight: bold;
    }
	</style>
<style type="text/css">
	.anniu{top:1.1%;right:1%; }
    .anniu img{display:block;width:100%;height:6.9%;} 
</style>
<script type="text/javascript">
    function return_prepage()  
    {  
    if(window.document.referrer==""||window.document.referrer==window.location.href)  
    {  
    window.location.href="{dede:type}[field:typelink /]{/dede:type}";  
    }else  
    {  
    window.location.href=window.document.referrer;  
    }  
      
    }  
</script>
</head>
<body style="overflow: auto;">
	<div class="main">
		<div>
			<img style="width: 100%;"
				src="<%=path%>/resources/img/wzy/logo.png" /><br />
			<div class="anniu">
				<a href="#" onclick="return_prepage();"
					style="float: right;"> <img
					src="<%=path%>/resources/img/wzy/FH.png" />
				</a>
			</div>
		</div>

		<div>
		    <input id='links' value='${url}' type="hidden">
		</div>
		<div id="head">
        <table width="98%" cellspacing="0" cellpadding="0" border="0" align="center">
        	<tbody>
        		<tr>
        			<td valign="middle" align="center" height="50" id="bt5">
        				${title}
        			<td>
        		</tr>
        		<tr>
        			<td bgcolor="#eeeeee" align="center" height="20" width='100%'>
        				<font style="color:#666666;font-size:12px;">发布时间:${time}&nbsp;&nbsp;&nbsp;</font>
        			<td>
        		</tr>
        	</tbody>
        </table>
        </div>
		<div class="bottom" style="border:0px;">
			<div id="main" style="padding-left:1.5%;" >
			</div>
		</div>
	</div>
</body>
</html>