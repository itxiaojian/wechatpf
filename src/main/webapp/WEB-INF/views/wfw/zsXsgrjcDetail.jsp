<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1"></meta>
    <script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
    <script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
	</script>
	<link href="<%=path%>/resources/css/tab-import.css" rel="stylesheet" style="text/css" />
	<link href="<%=path%>/resources/css/css.css" rel="stylesheet" style="text/css" />
	<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
	<title>我的奖惩</title>
	<style>
		.td_left {
			text-align: right;
			padding-left: 5px;
			width: 10%;
		}
		.td_right {
			text-align: right;
			padding-right: 5px;
			width: 10%;
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
 
  <body style="overflow: auto;overflow-x: scroll;" onload="wx.hideOptionMenu();">
   <form action="" method="POST" id="Form1">  
   		<div class="tab-container">
				<!--我的奖惩-->
				<label style="font-size: 15pt;">微服务-奖惩查询-详细信息</label>
					<table width="90%" border="0" align="center" cellpadding="0" style="margin-top: 30px;margin-bottom: 30px; "
						cellspacing="0" class="content02">
						<tr class="bgcolor01">
								<td class="td_right" width="25%" align="center">
									学号：
								</td>
								<td class="td_left" width="25%" align="center">
									${map1.xh }
								</td>
								<td class="td_right"width="25%" align="center">
									姓名：
								</td>
								<td class="td_left" width="25%" align="center">
									${map1.xm }
								</td>
						</tr>
						<tr class="bgcolor02">
								<td class="td_right" width="25%" align="center">
									学年：
								</td>
								<td class="td_left" width="25%" align="center">
									${map1.ksxn }
								</td>
								<td class="td_right"width="25%" align="center">
									学期：
								</td>
								<td class="td_left" width="25%" align="center">
									${map1.ksxq }
								</td>
						</tr>
						<tr class="bgcolor01">
								<td class="td_right" width="25%" align="center">
									奖惩名称：
								</td>
								<td class="td_left" width="25%" align="center">
									${map1.mc }
								</td>
								<td class="td_right"width="25%" align="center">
									奖惩事由：
								</td>
								<td class="td_left" width="25%" align="center">
									${map1.jcsy }
								</td>
						</tr>
						<tr class="bgcolor02">
								<td class="td_right" width="25%" align="center">
									奖惩结果：
								</td>
								<td class="td_left" width="25%" align="center">
									${map1.jcjg }
								</td>
								<td class="td_right"width="25%" align="center">
									奖惩金额：
								</td>
								<td class="td_left" width="25%" align="center">
									${map1.je }
								</td>
						</tr>
						<tr class="bgcolor01">
								<td class="td_right" width="25%" align="center">
									备注：
								</td>
								<td class="td_left" width="75%" align="center" colspan="3">
									${map1.bz }
								</td>
						</tr>
					</table> 
		</div>
	</form>		
  </body>
  <script type="text/javascript">var str="";</script>
<script type="text/javascript" src="<%=path%>/resources/js/wysy.js"></script>
<script type="text/javascript">
var cn=4;
//onload时触发水印绘制
window.onload=function(){
	watermark({ watermark_txt: "${text}" });
	for(var i=0;i<cn;i++){
		var my=document.getElementById('mask_div0'+i);
		if(my!=null){
			var p=my.parentNode;
			p.removeChild(my);
			//my.remove();
		}
	}
};

//onresize时触发水印绘制
window.onresize = function () {
	deleteWatermark();
	watermark({ watermark_txt: "${text}" });
	for(var i=0;i<cn;i++){
		var my=document.getElementById('mask_div0'+i);
		if(my!=null){
			var p=my.parentNode;
			p.removeChild(my);
			//my.remove();
		}
	}
};
</script>
</html>
