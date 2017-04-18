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
<meta name="viewport" content="width=device-width,user-scalable=yes, maximum-scale=3.0, initial-scale=1.0,">
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/xyxw.css" />

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

<script type="text/javascript">var PATHNAME="<%=path%>"</script>
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
<style type="text/css">
	.anniu{top:1.1%;right:1%; }
    .anniu img{display:block;width:100%;height:6.9%;} 
</style>
<title>学院相关信息</title>
</head>
<body style="overflow: auto;" onload="wx.hideOptionMenu()">
   <div class="main">
      <div  class="DYtop" >
               <img style="width:100%;:50px; "  src="<%=path%>/resources/img/wzy/logo.png" />
               <div class="anniu">
             <a href="#" onclick="return_prepage();">
			   <img  
			    src="<%=path%>/resources/img/wzy/FH.png" />
			    </a>
			</div>
       </div>
       
       <%-- <div class="middle">
			<c:choose>
				<c:when test="${map.xwlx =='2'}"><h1>学院风光</h1></c:when>
				<c:when test="${map.xwlx =='3'}"><h1>规章制度</h1></c:when>
				<c:when test="${map.xwlx =='4'}"><h1>办事流程</h1></c:when>
				<c:when test="${map.xwlx =='5'}"><h1>宿管动态</h1></c:when>
				<c:when test="${map.xwlx =='6'}"><h1>服务帮助</h1></c:when>
				<c:when test="${map.xwlx =='7'}"><h1>组织架构</h1></c:when>
				<c:when test="${map.xwlx =='8'}"><h1>学院校历</h1></c:when>
				<c:otherwise><h1>学院简介</h1></c:otherwise>
			</c:choose>
       </div> --%>
       
       <div class="bottom" style="border:0px;">
             <div class="h">
                <h2>${map.xwbt}</h2>
             </div>
             <div  style="font-size:100%;text-align:center;margin-top: -7px;"> <%-- 来源单位：${map.bmbh }&nbsp;&nbsp;&nbsp; --%>发布时间：<fmt:formatDate value="${map.sxsj }" type="date" dateStyle="medium"/></div>
             <div >
                 ${map.xwnr}
			</div>
       </div>
   </div>
</body>
</html>