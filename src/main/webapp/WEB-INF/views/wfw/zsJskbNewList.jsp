<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>教师课表</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css335/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/stat/css/sjtj.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">

 function toJskbByXn(value){
	 var zs="";
	 if($("#zs").find("option:selected").val()!='' && $("#zs").find("option:selected").val()!='-1' ){
		 zs=$("#zs").find("option:selected").val();
	 }
	 location.href="<%=path%>/wfw/ZsKb/toJskbNew?openId=${openId}&xn="+value+"&zs="+zs;
 }
 
 function toJskbByZs(value){
	 var xn="";
	 if($("#xnxq").find("option:selected").val()!='' && $("#xnxq").find("option:selected").val()!='-1' ){
		 xn=$("#xnxq").find("option:selected").val();
	 }
	 location.href="<%=path%>/wfw/ZsKb/toJskbNew?openId=${openId}&zs="+value+"&xn="+xn;
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
<style>
.KB_search select{width:90px;}
@media(max-width:320px){
.KB_search select{width:70px;}
}

#KBCX section{ display:none;}
#KBCX{ width:100%; height:100px;float:left;}
.KBCX_title{width:100%;float:left;}
.KBCX_title span{ width:14.2%; height:40px; float:left; font-size:12px; line-height:40px; text-align:center;}
.active{ border-bottom:4px solid #79DBFF;}
.KBCX_title div{ display:none; float:left;}
#KBCX table{ text-align:center;}
#KBCX td{ padding:6px;font-size:12px;}
.KB_search{height:50px; line-height:50px; border-bottom:1px solid #ccc;}
td{font-size:12px;}
</style>
<body>
<div class="iphone">
	<div class="WZY_top01">
    	<div class="row">
          <div class="col-sm-12" style="position:relative;">
            <img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive">
            <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
            <img style="top:18%;right:20px;" class="Home_btn" src="<%=path%>/resources/img/wzy/FH.png" 
            width="34" height="34">
            </a>
          </div>
    	</div>
    </div>
    <div class="New_main01">
	  <div class="KB_search">
        	<span>
            <a class="glyphicon glyphicon-list-alt"></a><s style="color:#054CAB;">&nbsp;学年学期</s>
            	<select onchange="toJskbByXn(this.value)" id="xnxq" style="appearance:none;-moz-appearance:none;
                  -webkit-appearance:none;height:20px;line-height:20px;">
                	<c:if test="${!empty xns}">
					    <c:forEach var="data" items="${xns}" varStatus="obj">
					  <option <c:if test="${xn==data.xn}">selected="selected"</c:if> value="${data.xn }">${data.xn1}</option>
					     </c:forEach>
					</c:if>
                </select>
            </span>
            <span>
            	<a class="glyphicon glyphicon-book"></a><s style="color:#054CAB;">&nbsp;教学周</s>
 					<select id="zs"  onchange="toJskbByZs(this.value)" style="appearance:none;-moz-appearance:none;
                  -webkit-appearance:none;height:20px;line-height:20px;">
						<option style="font-size:12px;" <c:if test="${zs=='-1'}">selected="selected"</c:if>  value="-1">全部</option>
							<c:forEach var="data" items="${zss}" varStatus="obj">
	    						<option <c:if test="${zs==data.zs}">selected="selected"</c:if> value="${data.zs }">
	    						第${data.zs}周
	    						</option>
							</c:forEach>
			       </select>
            </span>
        </div>
    	<div id="KBCX">
        	<div class="KBCX_title" id="KBCX_Title">
            	<span <c:if test="${week=='2'}">class="active"</c:if>>星期一</span>
                <span <c:if test="${week=='3'}">class="active"</c:if>>星期二</span>
                <span <c:if test="${week=='4'}">class="active"</c:if>>星期三</span>
                <span <c:if test="${week=='5'}">class="active"</c:if>>星期四</span>
                <span <c:if test="${week=='6'}">class="active"</c:if>>星期五</span>
                <span <c:if test="${week=='7'}">class="active"</c:if>>星期六</span>
                <span <c:if test="${week=='1'}">class="active"</c:if>>星期日</span>
                
            </div>
            <section  <c:if test="${week=='2'}">style="display:block;"</c:if><c:if test="${week!='2'}">style="display:none;"</c:if>
             class="container-fluid">
               <table width="100%" class="table-bordered table-striped">
                  <tbody>
                  <c:forEach var="data" begin="0" end="0" items="${jskb1}" varStatus="obj">
                    <tr>
                      <td width="20%">第1节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                    </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="0" end="0" items="${jskb2}" varStatus="obj">
                     <tr>
                      <td width="20%">第2节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="0" end="0" items="${jskb3}" varStatus="obj">
                     <tr>
                      <td width="20%">第3节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="0" end="0" items="${jskb4}" varStatus="obj">
                     <tr>
                      <td width="20%">第4节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="0" end="0" items="${jskb5}" varStatus="obj">
                     <tr>
                      <td width="20%">第5节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="0" end="0" items="${jskb6}" varStatus="obj">
                     <tr>
                      <td width="20%">第6节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="0" end="0" items="${jskb7}" varStatus="obj">
                     <tr>
                      <td width="20%">第7节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="0" end="0" items="${jskb8}" varStatus="obj">
                     <tr>
                      <td width="20%">第8节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="0" end="0" items="${jskb9}" varStatus="obj">
                     <tr>
                      <td width="20%">第9节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="0" end="0" items="${jskb10}" varStatus="obj">
                     <tr>
                      <td width="20%">第10节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="0" end="0" items="${jskb11}" varStatus="obj">
                     <tr>
                      <td width="20%">第11节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  </tbody>
                </table>
            </section>
            <section <c:if test="${week=='3'}">style="display:block;"</c:if><c:if test="${week!='3'}">style="display:none;"</c:if>
             class="container-fluid">
               <table width="100%" class="table-bordered table-striped">
                  <tbody>
                  <c:forEach var="data" begin="1" end="1" items="${jskb1}" varStatus="obj">
                    <tr>
                      <td width="20%">第1节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                    </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="1" end="1" items="${jskb2}" varStatus="obj">
                     <tr>
                      <td width="20%">第2节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="1" end="1" items="${jskb3}" varStatus="obj">
                     <tr>
                      <td width="20%">第3节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="1" end="1" items="${jskb4}" varStatus="obj">
                     <tr>
                      <td width="20%">第4节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="1" end="1" items="${jskb5}" varStatus="obj">
                     <tr>
                      <td width="20%">第5节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="1" end="1" items="${jskb6}" varStatus="obj">
                     <tr>
                      <td width="20%">第6节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="1" end="1" items="${jskb7}" varStatus="obj">
                     <tr>
                      <td width="20%">第7节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="1" end="1" items="${jskb8}" varStatus="obj">
                     <tr>
                      <td width="20%">第8节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="1" end="1" items="${jskb9}" varStatus="obj">
                     <tr>
                      <td width="20%">第9节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="1" end="1" items="${jskb10}" varStatus="obj">
                     <tr>
                      <td width="20%">第10节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="1" end="1" items="${jskb11}" varStatus="obj">
                     <tr>
                      <td width="20%">第11节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  </tbody>
                </table>
            </section>
            <section <c:if test="${week=='4'}">style="display:block;"</c:if><c:if test="${week!='4'}">style="display:none;"</c:if> 
            class="container-fluid">
               <table width="100%" class="table-bordered table-striped">
                  <tbody>
                  <c:forEach var="data" begin="2" end="2" items="${jskb1}" varStatus="obj">
                    <tr>
                      <td width="20%">第1节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                    </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="2" end="2" items="${jskb2}" varStatus="obj">
                     <tr>
                      <td width="20%">第2节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="2" end="2" items="${jskb3}" varStatus="obj">
                     <tr>
                      <td width="20%">第3节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="2" end="2" items="${jskb4}" varStatus="obj">
                     <tr>
                      <td width="20%">第4节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="2" end="2" items="${jskb5}" varStatus="obj">
                     <tr>
                      <td width="20%">第5节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="2" end="2" items="${jskb6}" varStatus="obj">
                     <tr>
                      <td width="20%">第6节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="2" end="2" items="${jskb7}" varStatus="obj">
                     <tr>
                      <td width="20%">第7节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="2" end="2" items="${jskb8}" varStatus="obj">
                     <tr>
                      <td width="20%">第8节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="2" end="2" items="${jskb9}" varStatus="obj">
                     <tr>
                      <td width="20%">第9节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="2" end="2" items="${jskb10}" varStatus="obj">
                     <tr>
                      <td width="20%">第10节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="2" end="2" items="${jskb11}" varStatus="obj">
                     <tr>
                      <td width="20%">第11节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  </tbody>
                </table>
            </section>
             <section  <c:if test="${week=='5'}">style="display:block;"</c:if><c:if test="${week!='5'}">style="display:none;"</c:if>
             class="container-fluid">
               <table width="100%" class="table-bordered table-striped">
                  <tbody>
                  <c:forEach var="data" begin="3" end="3" items="${jskb1}" varStatus="obj">
                    <tr>
                      <td width="20%">第1节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                    </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="3" end="3" items="${jskb2}" varStatus="obj">
                     <tr>
                      <td width="20%">第2节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="3" end="3" items="${jskb3}" varStatus="obj">
                     <tr>
                      <td width="20%" >第3节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="3" end="3" items="${jskb4}" varStatus="obj">
                     <tr>
                      <td width="20%">第4节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="3" end="3" items="${jskb5}" varStatus="obj">
                     <tr>
                      <td width="20%">第5节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="3" end="3" items="${jskb6}" varStatus="obj">
                     <tr>
                      <td width="20%">第6节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="3" end="3" items="${jskb7}" varStatus="obj">
                     <tr>
                      <td width="20%">第7节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="3" end="3" items="${jskb8}" varStatus="obj">
                     <tr>
                      <td width="20%">第8节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="3" end="3" items="${jskb9}" varStatus="obj">
                     <tr>
                      <td width="20%">第9节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="3" end="3" items="${jskb10}" varStatus="obj">
                     <tr>
                      <td width="20%">第10节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="3" end="3" items="${jskb11}" varStatus="obj">
                     <tr>
                      <td width="20%">第11节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  </tbody>
                </table>
            </section>
            <section <c:if test="${week=='6'}">style="display:block;"</c:if><c:if test="${week!='6'}">style="display:none;"</c:if>
             class="container-fluid">
               <table width="100%" class="table-bordered table-striped">
                  <tbody>
                  <c:forEach var="data" begin="4" end="4" items="${jskb1}" varStatus="obj">
                    <tr>
                      <td width="20%">第1节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                    </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="4" end="4" items="${jskb2}" varStatus="obj">
                     <tr>
                      <td width="20%">第2节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="4" end="4" items="${jskb3}" varStatus="obj">
                     <tr>
                      <td width="20%">第3节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="4" end="4" items="${jskb4}" varStatus="obj">
                     <tr>
                      <td width="20%">第4节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="4" end="4" items="${jskb5}" varStatus="obj">
                     <tr>
                      <td width="20%">第5节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="4" end="4" items="${jskb6}" varStatus="obj">
                     <tr>
                      <td width="20%">第6节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="4" end="4" items="${jskb7}" varStatus="obj">
                     <tr>
                      <td width="20%">第7节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="4" end="4" items="${jskb8}" varStatus="obj">
                     <tr>
                      <td width="20%">第8节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="4" end="4" items="${jskb9}" varStatus="obj">
                     <tr>
                      <td width="20%">第9节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="4" end="4" items="${jskb10}" varStatus="obj">
                     <tr>
                      <td width="20%">第10节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="4" end="4" items="${jskb11}" varStatus="obj">
                     <tr>
                      <td width="20%">第11节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  </tbody>
                </table>
            </section>
             <section <c:if test="${week=='7'}">style="display:block;"</c:if><c:if test="${week!='7'}">style="display:none;"</c:if>
             class="container-fluid">
               <table width="100%" class="table-bordered table-striped">
                  <tbody>
                  <c:forEach var="data" begin="5" end="5" items="${jskb1}" varStatus="obj">
                    <tr>
                      <td width="20%">第1节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                    </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="5" end="5" items="${jskb2}" varStatus="obj">
                     <tr>
                      <td width="20%">第2节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="5" end="5" items="${jskb3}" varStatus="obj">
                     <tr>
                      <td width="20%">第3节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="5" end="5" items="${jskb4}" varStatus="obj">
                     <tr>
                      <td width="20%">第4节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="5" end="5" items="${jskb5}" varStatus="obj">
                     <tr>
                      <td width="20%">第5节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="5" end="5" items="${jskb6}" varStatus="obj">
                     <tr>
                      <td width="20%">第6节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="5" end="5" items="${jskb7}" varStatus="obj">
                     <tr>
                      <td width="20%">第7节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="5" end="5" items="${jskb8}" varStatus="obj">
                     <tr>
                      <td width="20%">第8节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="5" end="5" items="${jskb9}" varStatus="obj">
                     <tr>
                      <td width="20%">第9节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="5" end="5" items="${jskb10}" varStatus="obj">
                     <tr>
                      <td width="20%">第10节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="5" end="5" items="${jskb11}" varStatus="obj">
                     <tr>
                      <td width="20%">第11节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  </tbody>
                </table>
            </section>
            <section <c:if test="${week=='1'}">style="display:block;"</c:if><c:if test="${week!='1'}">style="display:none;"</c:if>
            class="container-fluid">
               <table width="100%" class="table-bordered table-striped">
                  <tbody>
                  <c:forEach var="data" begin="6" end="6" items="${jskb1}" varStatus="obj">
                    <tr>
                      <td width="20%">第1节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                    </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="6" end="6" items="${jskb2}" varStatus="obj">
                     <tr>
                      <td width="20%">第2节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="6" end="6" items="${jskb3}" varStatus="obj">
                     <tr>
                      <td width="20%">第3节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="6" end="6" items="${jskb4}" varStatus="obj">
                     <tr>
                      <td width="20%">第4节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="6" end="6" items="${jskb5}" varStatus="obj">
                     <tr>
                      <td width="20%">第5节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="6" end="6" items="${jskb6}" varStatus="obj">
                     <tr>
                      <td width="20%">第6节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="6" end="6" items="${jskb7}" varStatus="obj">
                     <tr>
                      <td width="20%">第7节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="6" end="6" items="${jskb8}" varStatus="obj">
                     <tr>
                      <td width="20%">第8节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="20%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="6" end="6" items="${jskb9}" varStatus="obj">
                     <tr>
                      <td width="20%">第9节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                   <c:forEach var="data" begin="6" end="6" items="${jskb10}" varStatus="obj">
                     <tr>
                      <td width="20%">第10节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  <c:forEach var="data" begin="6" end="6" items="${jskb11}" varStatus="obj">
                     <tr>
                      <td width="20%">第11节</td>
                      <td width="50%">${data.kcmc}</td>
                      <td width="30%">${data.jsmc}</td>
                     </tr>
                   </c:forEach>
                  </tbody>
                </table>
            </section>
            <div style="height:10px;"></div>
            <div>
            <span style="color:red;font-size:12px;margin-left:14px;">温馨提示：由于教务课表调整频繁，结果仅供参照，实际以教务系统为准。</span></div>
        </div>
    </div>
    <div class="WZY_foot01">
    	<div class="row">
          <div class="col-sm-12">
            <img src="<%=path%>/resources/img/wzy/BQ.png" class="img-responsive">
          </div>
    	</div>
    </div>
</div>
<script>
			var Okbcx = document.getElementById("KBCX");
			var Otitle = document.getElementById("KBCX_Title");
			var Ospan = Otitle.getElementsByTagName("span");
			var Osection = Okbcx.getElementsByTagName("section");
			for(var i=0;i<Ospan.length;i++){
				Ospan[i].index = i;
				Ospan[i].onclick = function(){
					
				for(var i=0;i<Ospan.length;i++){
				Ospan[i].className = " ";
				Osection[i].style.display = "none";
				}
				
				this.className = "active";
				Osection[this.index].style.display = "block";
					}
				}
</script>
</html>