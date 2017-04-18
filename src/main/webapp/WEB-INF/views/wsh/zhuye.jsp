<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="no-cache" http-equiv="Pragma" />
<meta content="no-cache" http-equiv="Cache-Control" />
<meta content="0" http-equiv="Expires" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/wzy/style_gs.css" />

<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js" type="text/javascript"></script>
<script type="text/javascript">
		/* 返回首页 */
		function shouye() {
			WeixinJSBridge.call("closeWindow");
		}
</script>
<style type="text/css">
.footer1{ 
     height:5%; width:100%; position:absolute; bottom:0; left:0; overflow:hidden;  
     margin: 0px;
     padding: 0px;
     font-family: '微软雅黑';
     }
     
.footer1 img {   
     width: 100%;
     }
     
.title_name {
    width: 10rem;
    height: 1rem;
    background-color: #f2f2f2;
    border-bottom: #ccc 1px solid;
}
.title_nametext {
    float: left;
    line-height: 1rem;
    font-size: 0.45rem;
    border-left: #EC9F1F 0.1rem solid;
}

.New_list{ padding-left:0.5rem; clear:both}
.New_list li{ border-bottom:#f2f2f2 1px solid; line-height:0.7rem; height:0.7rem;}
.New_list a{ font-size:0.4rem; color:#2e87d3; line-height:0.7rem; float:left;}
.New_list_time {
    float: right;
    color: #999;
    font-size: 0.3rem;
    margin-right: 0; 
    line-height: 0.7rem; 
}
</style>
<style type="text/css">
	.anniu{top:1.2%;right:1%; }
    .anniu img{display:block;width:32px;height:32px;} 
    
    .phone_banner{width:100%;}
    .Phone_chatu{width:100%;margin-top:5%;margin-bottom:5px;}
    
	 img{ pointer-events: none; }
 	 a{-webkit-touch-callout:none;}
</style>
<script>
	var u = navigator.userAgent, app = navigator.appVersion;
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if(isiOS){
		document.getElementsByTagName('html')[0].style.fontSize=window.screen.width/10+'px';
	}else{
		document.getElementsByTagName('html')[0].style.fontSize='36px';
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

// 	alert('是否是Android：'+isAndroid);
// 	alert('是否是iOS：'+isiOS);
</script>
<title>微生活</title>
</head>

<body class="body" onload="wx.hideOptionMenu();">
	<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="微生活主页"><a href="#"
				target="content" onfocus="this.blur()"><span>微生活主页</span></a></li>
		</ul>
	</div>
	<style type="text/css">
body {
	line-height: 100%
}
</style>
	<div class="top">
		<img style="width: 100%;height:50px;" src="<%=path%>/resources/img/wzy/logo.png" />

		<div class="anniu" style="position: absolute; top:2%; left: 87%; width: 9%;">
			<a href="#" 
				onclick="shouye()"> <img 
				 src="<%=path%>/resources/img/wzy/fanhui.png" />
			</a>
		</div>
	</div>

	<!-- 第一页 -->
	<!-- <div class="main" style="width: 100%; height: 100%;">
		<input type="hidden" value="${map.appid }" name="appid" id="appid" />
		<input type="hidden" value="${map.nonceStr }" name="nonceStr" id="nonceStr" />
		<input type="hidden" value="${map.timestamp }" name="timestamp" id="timestamp" />
		<input type="hidden" value="${map.signature }" name="signature" id="signature" />
		<div class="content1">
			<div class="yi">
				<div class="middle">
<%-- 					<a id="div" href="<%=path%>/wsh/ShSwzl/toSwzlList?openId=${openId}" --%>
						style="height: 100%; width: 100%; float: left" class="img"> <img
<%-- 						src="<%=path%>/resources/img/swzl.png" width="40px" height="40px" /> --%>
						<p>失物招领</p>
					</a>
				</div>
			</div>
			<div class="yi">
				<div class="middle">
<%-- 					<a href="<%=path%>/wsh/WjObject/toDcwj?openId=${openId}" --%>
						style="height: 100%; width: 100%;" class="img"> <img
<%-- 						src="<%=path%>/resources/img/wjdc.png" width="40px" height="40px"> --%>
						<p>微问卷</p> </img>
					</a>
				</div>
			</div>
		</div>
-->
<div class="main">
		<div class="phone_banner">
        	<img src="<%=path %>/resources/img/wzy/banner_03.png" style="width:100%;"/>
        </div>
        <div class="index01_icon" style="margin-top:0.5rem;">
				<ul>
					<c:forEach var="zycd" items="${listmenu}" varStatus="obj">
			            <li><a href="<%=path%>/${zycd.cdurl}openId=${openId}"> <img
								src="<%=path%>/resources/img/wzy/${zycd.cdtbmc}">
								${zycd.cdmc}
						</a></li>
		            </c:forEach>
				</ul>
			</div>
			<div class="Phone_chatu">
            	<img src="<%=path %>/resources/img/wzy/chatu03.png" style="width:100%;"/>
            </div>
<!--          <div class="title_name"> -->
<!--             <span class="title_nametext">&nbsp;&nbsp;生活通知</span> -->
<!--         </div> -->
<!--          <div class="New_list"> -->
<!--             <ul> -->
<%--             	<c:forEach var="swzl" items="${swzl }" varStatus="obj"> --%>
<!-- 	                <li> -->
<%--             		<c:if test="${swzl.lx=='2' }"> --%>
<!-- 	                	<span style="float: left;font-size: 0.4rem;color: red;">招领</span> -->
<%-- 	                	<a style="margin-left: 15px;" href="<%=path%>/wsh/ShSwzl/toSwzlDetail?id=${swzl.id }&openId=${openId}"> --%>
<%--                 				<c:choose>   --%>
<%-- 									<c:when test="${fn:length(swzl.bt) > 8}">   --%>
<%--      									&nbsp;&nbsp;<c:out value="${fn:substring(swzl.bt,0,8)}..." />   --%>
<%-- 									</c:when>   --%>
<%-- 									<c:otherwise>   --%>
<%--      									&nbsp;&nbsp;<c:out value="${swzl.bt}" />   --%>
<%-- 									</c:otherwise>   --%>
<%-- 								</c:choose> --%>
<!-- 	                	</a> -->
<%-- 	                	<span  style="margin-right:5px;" class="New_list_time">${swzl.fqsj }</span> --%>

<%--             		</c:if> --%>
<%--             		<c:if test="${swzl.lx=='1' }"> --%>
<!-- 	                	<span style="float: left;font-size: 0.4rem;color: blue;">失物</span> -->
<%-- 	                	<a style="margin-left: 15px;" href="<%=path%>/wsh/ShSwzl/toSwzlDetail?id=${swzl.id }&openId=${openId}"> --%>
<%-- 	                		<c:choose>   --%>
<%-- 								<c:when test="${fn:length(swzl.bt) > 8}">   --%>
<%--     									&nbsp;&nbsp;<c:out value="${fn:substring(swzl.bt,0,8)}..." />   --%>
<%-- 								</c:when>   --%>
<%-- 								<c:otherwise>   --%>
<%--     									&nbsp;&nbsp;<c:out value="${swzl.bt}" />   --%>
<%-- 								</c:otherwise>   --%>
<%-- 							</c:choose> --%>
<!-- 	                	</a> -->
<%-- 	                	<span style="margin-right:5px;" class="New_list_time" >&nbsp;&nbsp; ${swzl.fqsj }</span> --%>
<%--             		</c:if> --%>
<!--             		</li> -->
<%--             	</c:forEach> --%>
<!--             </ul> -->
<!--         </div>   -->
    </div>
 	</div>
 	<div class="footer1">
        <img src="<%=path%>/resources/img/wzy/BQ.png"/>
     </div> 
	</div>
</body>
</html>