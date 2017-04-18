<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3"></meta>
<title>离校流程详细信息</title>
</head>
<style type="text/css">
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
body{ margin:0; padding:0px; overflow-x:hidden}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.logo{ width:100%; height:50px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01{ width:100%; height:120px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.top_01 span{ float:left;}
.top_01 img{ margin-top:15%; display:block; float:left;}
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_02{width:100%; position:absolute; top:50px; left:0;  background-color:#fff; overflow:hidden;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ height:100px; font-size:34px; line-height:100px; float:left; text-align:center; border-bottom:#e5e5e5 1px solid;}
.CJ_msg_02 li{font-size:34px; float:left; width:100%; text-indent:34px; line-height:100px;}
.CJ_msg_02 span{ float:left; color:#2e87d3;}
.CJ_msg_01 img{ float:left; margin-top:12%; margin-left:16%;}
.photo_GR{ position:absolute; top:18%; right:2%;}
.foot_01{width:100%; height:30px; background-color:#ccc;position:absolute; bottom:0; left:0; overflow:hidden}
/*微主页*/
.banner{ width:100%; overflow:hidden;}
.YM_iocn{width:100%; overflow:hidden;}
.YM_iocn li{ width:25%; text-align:center; float:left; font-size:36px; color:#666; line-height:100px; }
.YM_iocn img{ display:block; margin-left:30%; margin-top:15%;}
.chahua{ position:absolute; bottom:5.5%; right:0%;}
.FH_btn{ position:absolute; top:3%; right:5%;}
/*离校流程*/
.WX_student_msg{ width:94%; border:1px solid #aaa; border-radius:10px; float:left; padding-bottom:5px; margin-left:3%;margin-top:1%;}
.WX_student_msg img { float:left;}
.WX_student_msg span:nth-of-type(1){ width:30%; float:left}
.WX_student_msg span:nth-of-type(2){ width:70%; float:left}
.WX_student_msg li{ font-size:12px; color:#333; float:left; margin-top:5px;}
.msg_li_width01{ width:30%;}
.msg_li_width02{width:60%;}
.msg_li_width03{width:100%;}
.PD_msg{ font-size:10px; float:right; width:100%; border-bottom:1px solid #ccc;padding-bottom:5px; margin-top:4%;}
.PD_msg span{ float:right; margin-right:3%;}
.student_msg_main{ width:100%;float:left;margin-top:-30px;margin-bottom:20px;}
.student_msg_main li{ width:32%; height:100px; border:1px solid #ccc; border-radius:5px; float:left; box-sizing:border-box; margin-top:5%; position:relative;}
.student_msg_main_left{ margin-left:2%}
.student_msg_title{ width:100%; float:left; margin-top:10px;margin-left:3%}
.student_msg_Ftitle{ width:100%; float:left;margin-top:5px; margin-left:3%}
.msg_ZE{ width:100%; height:15px; background-color:#00a0e9; border:1px #00a0e9 solid; float:left; border-radius:0 0 5px 5px; position:absolute; bottom:0; left:0; text-align:center; line-height:56px;}
/*流程详细*/
.XX_msg_main{ width:94%; border-radius:10px; border:1px solid #00a0e9; margin-top:40px; float:left;margin-left:3%}
.XX_msg_lan{ width:100%; height:10px; background-color:#8bc0f2; float:left;}
.XX_msg_title{ width:100%; height:35px;float:left; font-size:13px; line-height:35px;}
.XX_msg_JG{ font-size:13px; padding:3%; padding-top:5px; float:left;}
.XX_msg_JG span{ line-height:30px; width:100%; float:left;}
.H_color{ color:#666;}
.fenlan{ width:100%; height:2px; background-color:#8bc0f2; float:left; margin-top:10px;}
</style>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
/* 返回首页 */

function fanhui(openId) {
	/* alert(openId); */
    location.href="<%=path%>/wlx/ZsLxlc/toLxlc?openId="+openId;
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
<body>
<div class="phone_01">
		<div class="logo">
				<img style="width:100%;" src="<%=path%>/resources/img/wzy/logo.png" />

				<div class="anniu" style="position: absolute; top:1%; left: 87%;width: 12%;">
					<a href="#" onclick="fanhui('${openId }');"> <img style="width:100%;margin-top:8%;" src="<%=path%>/resources/img/wzy/FH.png" />

					</a>
				</div>
		</div>
	</div>
	<div class="main_02">
		<div class="WX_student_msg">
			<span style="width:50%;">
				<img style="width:50%;margin-top:10%;margin-left:30%" src="<%=path%>/resources/img/wzy/student.png">
			</span>
			<span style="width:50%;margin-top:3%;">
			<c:if test="${! empty map1}">
				<c:forEach var="map" items="${map1}" varStatus="obj" begin="0" end="0">
				<ul>
					<li class="msg_li_width01" style="width:100%;">学号：${map.stuid}</li>
					<li class="msg_li_width01" style="width:100%;">姓名：${map.stuname}</li>
					<li class="msg_li_width01" style="width:100%;">院系：${map.xsyx}</li>
					<li class="msg_li_width01" style="width:100%;">专业：${map.xszy}</li>
				</ul>
				</c:forEach>
			</c:if>
			<c:if test="${!empty map2}">
				<c:forEach var="map" items="${map2}" varStatus="obj" begin="0" end="0">
				<ul>
					<li class="msg_li_width01" style="width:100%;">学号：${map.stuid}</li>
					<li class="msg_li_width01" style="width:100%;">姓名：${map.stuname}</li>
					<li class="msg_li_width01" style="width:100%;">院系：${map.xsyx}</li>
					<li class="msg_li_width01" style="width:100%;">专业：${map.xszy}</li>
				</ul>
				</c:forEach>
			</c:if>
			</span>
		</div>
		<div class="student_msg_main">
		<c:if test="${! empty map1}">
		<c:forEach var="map" items="${map1}" varStatus="obj" begin="0" end="0">
			<div class="XX_msg_main">
				<div class="XX_msg_title" style="margin-top:2%;margin-left:2%;">
				<c:choose>
					<c:when  test="${map.blzt == 1}">
						<img style="width:5%;" src="<%=path%>/resources/img/wzy/TG.png">
				    </c:when>
				    <c:when  test="${map.blzt == 0}">
				    	<img style="width:5%;" src="<%=path%>/resources/img/wzy/WT.png">
				    </c:when>
				    <c:otherwise>
				    	<img style="width:5%;" src="<%=path%>/resources/img/wzy/BL.png">
				    </c:otherwise></c:choose>
					&nbsp;&nbsp;${map.tachename}
				</div>
				<div class="XX_msg_lan">&nbsp;</div>
				<div class="XX_msg_JG">
					<span>
						详细情况：
						<span class="H_color">
							${map.reason}
						</span>
					</span>
					<div class="fenlan"></div>
					<span>
						流程指导：
						<span class="H_color">时间：${map.sj }</span>
						<span class="H_color">地点：${map.dd }</span>
						<span class="H_color">注意事项：${map.zysx }</span>
					</span>
				</div>
			</div>
			</c:forEach>
			</c:if>
			<c:if test="${! empty map2}">
		    <c:forEach var="map" items="${map2}" varStatus="obj" begin="0" end="0">
			<div class="XX_msg_main">
				<div class="XX_msg_title" style="margin-top:2%;margin-left:2%;">
				<c:choose>
					<c:when  test="${map.blzt == 1}">
						<img style="width:5%;" src="<%=path%>/resources/img/wzy/TG.png">
				    </c:when>
				    <c:when  test="${map.blzt == 0}">
				    	<img style="width:5%;" src="<%=path%>/resources/img/wzy/WT.png">
				    </c:when>
				    <c:otherwise>
				    	<img style="width:5%;" src="<%=path%>/resources/img/wzy/BL.png">
				    </c:otherwise></c:choose>
					&nbsp;&nbsp;${map.tachename}
				</div>
				<div class="XX_msg_lan">&nbsp;</div>
				<div class="XX_msg_JG">
					<span>
						详细情况：
						<span class="H_color">
							${map.reason}
						</span>
					</span>
					<div class="fenlan">&nbsp;</div>
					<span>
						流程指导：
						<span class="H_color">时间：${map.sj }</span>
						<span class="H_color">地点：${map.dd }</span>
						<span class="H_color">注意事项：${map.zysx }</span>
					</span>
				</div>
			</div>
			</c:forEach>
			</c:if>
		</div>
	</div>
	<div class="foot_01">
		<img style="width:100%;" src="<%=path%>/resources/img/wzy/BQ.png">
	</div>
</body>
</html>