<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String path = request.getContextPath();%>

<html eiiebffcjbffiheggaebebgceaeccbia_b="1" bdgjjgagedbdaebhbjbcabcdgeeebecf_b="1" idceifdedfeiefjgfcjfbchejgbcbeid_b="1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>学籍异动明细</title>
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/xyxw.css" />
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
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
<body style="overflow: auto;" onload="wx.hideOptionMenu();">
   <div class="main">
       <div class="middle">
				<h1>学籍异动具体信息</h1>
				<div class="anniu" >
				   <a style="float:right;width:30px;height:30px;" href="#" onclick="return_prepage();">
			   <img  style="width:65%" src="<%=path%>/resources/img/fanhui.png" />
			   </a>
			      </div>
       </div>
       <c:forEach var="data" items="${list}" varStatus="obj">
       <div class="bottom">
             <div class="h">
                <h2 style="height: 5%">${data.ydlx}</h2>
             </div>
             <div  style="font-size:80%;text-align:center;"> 学号：${data.xh}&nbsp;&nbsp;&nbsp;发生时间：<fmt:formatDate value="${data.fssj}" type="date" dateStyle="medium"/></div>
             <div >
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${data.xxqk}
             </div>
       </div>
       </c:forEach>
   </div>
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
<%-- <body>
 <div class="box1" id="formContent" whiteBg="true">
 <c:forEach var="data" items="${list}" varStatus="obj">
 	<h1 >${data.ydlx}</h1>
	<table style="border-style: solid;width:100%;">
	 		<tr>
		    	<td></td>
		    	<td align="right" style="font-size: 10pt;">学号：${data.xh}</td>
		 	
		    	<td align="center" style="font-size: 10pt;">发生时间：${data.fssj}</td>
		    	<td></td>
		 	
	 		</tr>
	 		
	 		<tr>
		    	<td align="center" colspan="4" style="font-size: 10pt;">
		    	${data.xxqk}</td>
	 		</tr>
		 </table>
 </c:forEach>
	</div>
	</body> --%>
</html>
