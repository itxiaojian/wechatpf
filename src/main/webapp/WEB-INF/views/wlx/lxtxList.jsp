<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%><html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3"></meta>
<!-- <link rel="stylesheet" type="text/css" href="css/style.css"> -->
<title>离校提醒记录</title>
<style type="text/css">
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
body{ margin:0; padding:0px; overflow-x:hidden}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.logo{ width:100%; height:150px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01{ width:100%; height:120px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.top_01 span{ float:left;}
.top_01 img{ margin-top:15%; display:block; float:left;}
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_02{width:100%; position:absolute; top:62px; left:0; bottom:98px; background-color:#fff; overflow:auto;}
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
.foot_01{width:100%; height:38px; background-color:#ccc;position:absolute; bottom:0; left:0; overflow:hidden}
/*微主页*/
.banner{ width:100%; overflow:hidden;}
.YM_iocn{width:100%; overflow:hidden;}
.YM_iocn li{ width:25%; text-align:center; float:left; font-size:36px; color:#666; line-height:100px; }
.YM_iocn img{ display:block; margin-left:30%; margin-top:15%;}
.chahua{ position:absolute; bottom:5.5%; right:0%;}
.FH_btn{ position:absolute; top:1.5%; right:5%;}
/*办理流程消息提醒*/
.XXTI_msg_title{ width:100%; height:40px; background-color:#41a1ce; float:left; font-size:14px; line-height:40px; 
text-align:center; color:#fff; box-sizing:border-box;}
.XXTI_msg_main li{ width:100%; height:35px; line-height:35px; border-bottom:1px solid #d9d9d9; float:left;}
.XXTI_msg_main span{ font-size:13px;}
.XXTI_msg_H{ float:left; color:#d53301;border-left:3px solid #d53301; text-indent:14px;}
.XXTI_msg_L{ float:left; color:#03bf0c;border-left:3px solid #03bf0c; text-indent:14px;}
.XXTI_msg_Y{ float:left; color:#bf9503;border-left:3px solid #bf9503; text-indent:14px;}
.XXTI_msg_R{ float:right; color:#999;}
.XXTI_msg_main img{ float:right; margin-top:8px; margin-right:3px;}
</style>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
/* 返回首页 */
function shouye(openId) {
	location.href = "<%=path%>/wsh/zy/zhuye?openId="+openId;
}

function toDetail(tacheid,openId){
	location.href = "<%=path%>/wlx/ZsLxTx/totxDetail?openId="+openId+"&tacheid="+tacheid;
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
	<div class="logo" style="position:fixed">
    	<img src="<%=path%>/resources/img/wzy/logo.png" width="100%" height="40%">
    </div>
    <div class="main_02">
    	<div class="XXTI_msg_title">办理流程消息提醒</div>
        <div class="XXTI_msg_main">
        	<ul>
        	<c:forEach var="data" items="${list}" varStatus="obj">
        		<c:choose>
        		<c:when test="${data.blzt=='0'}">
            	     <li onclick="toDetail('${data.tacheid}','${openId}')">
                		<span class="XXTI_msg_H">${data.tachename}【不通过】</span>
                    	<span class="XXTI_msg_R">${data.bltime}&nbsp;&nbsp;<img src="<%=path%>/resources/img/wzy/L_T.png" width="10" height="20"></span>
                    </li>
                  </c:when>
                  <c:when test="${data.blzt=='1'}">
               	 	 <li onclick="toDetail('${data.tacheid}','${openId}')">
                		<span class="XXTI_msg_L">${data.tachename}【通过】</span>
                    	<span class="XXTI_msg_R">${data.bltime}&nbsp;&nbsp;<img src="<%=path%>/resources/img/wzy/L_T.png" width="10" height="20"></span>
               		 </li>
                </c:when>
                <c:otherwise>
                	 <li onclick="toDetail('${data.tacheid}','${openId}')">
                		<span class="XXTI_msg_Y">${data.tachename}【可能办理】</span>
                   	    <span class="XXTI_msg_R">${data.bltime}&nbsp;&nbsp;<img src="<%=path%>/resources/img/wzy/L_T.png" width="10" height="20"></span>
               		 </li>
                </c:otherwise>
                </c:choose>
                </c:forEach>
            </ul>
        </div>
        <c:if test="${empty list}">
            <div class="text">
				<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
			</div> 
		</c:if>
    </div>
    <div class="foot_01">
    	<img src="<%=path%>/resources/img/wzy/BQ.png" width="100%" height="100%">
    </div>
    
    <div class="FH_btn" onclick="shouye('${openId}');"> 
    	<a href="javascript:void(0)"><img style="width:38px;height:38px;" src='<%=path%>/resources/img/wzy/FH.png'></a>
    </div>
</div>
</body>
</html>
