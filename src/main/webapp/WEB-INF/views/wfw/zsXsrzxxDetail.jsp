<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<title>学生详细信息</title>
</head>
<style>
 img{ pointer-events: none; }
 a{-webkit-touch-callout:none;}
 
 *,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:120px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.top_01 span{ float:left;}
.top_01 img{ margin-top:15%; display:block; float:left;}
.main_02{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#fff; overflow:auto;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}

.M_box{ width:90%; margin:auto;}
.M_msg{ width:100%; height:100%; border:1px solid #f2f2f2; border-radius:20px; margin-top:6%; float:left;padding-bottom:50px; margin-bottom:50px;}
.M_title{ width:100%; height:120px; background-color:#8bc0f2; color:#fff; line-height:120px; font-size:45px; float:left;border-radius:20px 20px 0 0 ;}
.M_title span{font-size:36px}
.M_title img{ float:left;}
.M_main{ clear:both; font-size:36px;}
.M_main li{ float:left; padding-top:5%; width:100%}
.M_main li span{ float:left;}
.M_main ul{ float:left;border-bottom:1px solid #8bc0f2; padding-bottom:50px;}
.msg_main01{ text-indent:70px;width:33%}
.msg_main02{ text-indent:100px;width:67%}
.msg_main04{width:25%; color:#333;text-align:center}
.Tou_M{ text-align:center; border-bottom:1px solid #ccc; margin-top:20px; padding-top:20px;}
.msg_main_title{ width:100%; height:80px; background-color:#8bc0f2;font-size:36px; line-height:80px; color:#fff;}
 
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

function fanhui(){
	location.href="<%=path%>/wfw/zy/zhuye?openId=${openId}";
}
</script>
<body>
<div class="phone_01">
      <div class="top_01">
          <span style="width:60%; padding-left:5%">
             <img src="<%=path%>/resources/img/wzy/c11-04.png" style=" margin-top:5%" width="60" height="60"> 学生详细信息&nbsp;&nbsp;
          </span>
          <span style="width:20%;">
              &nbsp;
          </span>
          <a href="javascript:;" onclick="javascript:history.go(-1)">
          <span style="width:10%" >
              <img src="<%=path%>/resources/img/wzy/fh1.png" style="margin-top:30%">
          </span>
          </a>
      </div>
    <div class="main_02">
    	<div class="M_box">
    	<c:forEach var="data" items="${rzxxlist }">
        	<div class="M_msg" style=" margin-top:5%;">
            	<div class="M_title">
                	<img  style=" margin-top:1%; margin-left:2%" src="<%=path%>/resources/img/wzy/mp.png" width="100" height="100">
                    ${data.xm }
                    <span> &nbsp;&nbsp;&nbsp;&nbsp;${data.xb }</span>
                	<span> &nbsp;&nbsp;&nbsp;&nbsp;${data.xh }</span>
                    <img style=" float:right; margin-top:1%; margin-right:2%" src="<%=path%>/resources/img/wzy/dian.png" width="90" height="90">
                </div>
                <div class="M_main">
                	<div class="Tou_M"><img src="<%=path%>/resources/img/wzy/msg_tou.png"></div>
                      <ul>
                    	<li>
                        	<span class="msg_main01">班级</span>
                            <span class="msg_main02" style="color:#fc4312">${data.bjmc }</span>
                        </li>
                        <li>
                        	<span class="msg_main01">年级</span>
                            <span class="msg_main02">${data.nj }</span>
                        </li>
                        <li>
                        	<span class="msg_main01">宿舍楼</span>
                            <span class="msg_main02">${data.gyq }&nbsp;&nbsp;${data.gyl }</span>
                        </li>
                        <li>
                        	<span class="msg_main01">房间</span>
                            <span class="msg_main02">${data.fjh }寝室&nbsp;&nbsp;${data.cwh }</span>
                        </li>
                        <li>
                        	<span class="msg_main01">入住时间</span>
                            <span class="msg_main02">${data.rzsj }</span>
                        </li>
                        <li>
                        	<span class="msg_main01">学籍状态</span>
                            <span class="msg_main02">${data.xjzt }</span>
                        </li>
                    	
                    </ul>
                    <c:if test="${empty rzxxlist }">
                    <ul>
                       <li> 
                         <span class="msg_main01" style="width: 100%;">暂无学生基本信息</span>
                       </li>
                    </ul>
                    </c:if>  
                    <ul style="width:100%;">
                    	<div class="msg_main_title" >&nbsp;&nbsp;• 晚归考勤情况</div>
                    	<li >
                        	<span class="msg_main04" style="color:#999;width:60%;">晚归时间</span>
                            <span class="msg_main04" style="color:#999;width:40%;">登记老师</span>
                        </li>
                        <c:forEach  var="kqxxlist" items="${kqxxlist}" varStatus="obj">
                          <li >
                        	<span class="msg_main04" style="width:60%;">${kqxxlist.wgsj }</span>
                            <span class="msg_main04" style="width:40%;">${kqxxlist.djls }</span>
                          </li>
                        </c:forEach>
                        <c:if test="${empty kqxxlist}">
                              <div class="text">
			                      <p style="margin-left:30px;color:red;">晚归考勤信息暂无...</p>
		                      </div>  
                        </c:if>  
                    </ul>             
                </div>
            </div>
            </c:forEach>
        </div>	
    </div>
    <div class="foot_01">
    	<img src="<%=path%>/resources/img/wzy/BQ.png" width="100%" height="100%">
    </div>
</div>
</body>
</html>
