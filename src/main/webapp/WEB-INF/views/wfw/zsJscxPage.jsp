<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/wzy/style_03.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<meta name="viewport" content="user-scalable=no"></meta>
<meta charset="utf-8">
<title>教室查询</title>
</head>
<style>
.CXBiao{top:120px;width:100%; height:120px; border-bottom:1px solid #ccc; line-height:130px;position:absolute;}
.CXBiao select{font-size:36px; width:200px; height:50px; border-radius:5px; color:#2e87d3;}
.CXBiao input{font-size:36px; width:150px; height:50px; border-radius:10px; color:#2e87d3; background-color:#2e87d3; color:#fff;}
.msg_main001{ width:25%; padding-left:2.5%; padding-right:2.5%; color:#999;text-align:center;}
.msg_main002{ width:65%; padding-left:2.5%; padding-right:2.5%;color:#999;text-align:center;}
.SKDi{ width:100%; height:100px;border-bottom:1px solid #ccc; line-height:100px;color:#fc4312; text-align:center;}
select option{font-size:13px;}
.footer1{
     height:91px; width:100%; position:relative; bottom:0; left:0; width:100%; overflow:hidden;  
     margin-top:-91px;
     padding: 0px;
     font-family: '微软雅黑';
}
     
.footer1 img {   
     width: 10rem;
     }
</style>
<script type="text/javascript">
function query(){
	var year = $("#year").val();
	var xq = $("#xq").val();
	var week = $("#week").val();
	var dsweek = $("#dsweek").val();
	var jxl = $("#jxl").val();
	if(year== null || year == ""){
		alert("请选择学年!");
		return false;
	}else if(xq== null || xq == ""){
		alert("请选择学期!");
		return false;
	}else if(week== null || week == ""){
		alert("请选择星期!");
		return false;
	}else if(dsweek== null || dsweek == ""){
		alert("请选择周次!");
		return false;
	 }else if(jxl== null || jxl == ""){
	    alert("请选择楼号!");
	    return false; 
    }else{
		$("#myForm").submit();
	}
}

function Change(){
    var objS = document.getElementById("pid");
    var jxlmc = objS.options[objS.selectedIndex].value;
    var openId = $("#openId").val();
    if(jxlmc == "选择教学楼查看"){
    	 location.href="<%=path%>/wfw/ZsJscx/toJscx?openId="+openId;
    }else{
         location.href="<%=path%>/wfw/ZsJscx/toJscx?jxl="+jxlmc+"&openId="+openId;
    }
	}

function getYear(value){
	$('#year').val(value);
}
function getXq(value){
	$('#xq').val(value);
}
function getWeek(value){
	$('#week').val(value);
}
function getDsWeek(value){
	$('#dsweek').val(value);
}
function getJxl(value){
	$('#jxl').val(value);
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
	
/* 	var u = navigator.userAgent, app = navigator.appVersion;
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if(isiOS){
		document.getElementsByTagName('html')[0].style.fontSize=window.screen.width/10+'px';
	}else{
		document.getElementsByTagName('html')[0].style.fontSize='38px';
	}
// 	alert('是否是Android：'+isAndroid);
// 	alert('是否是iOS：'+isiOS); */
</script>
<body>
<div class="phone_01" style="overflow:auto;">
<form action="<%=path%>/wfw/ZsJscx/toJscx" id="myForm" class="wwx_f_l" style="width: 55%;margin-top: 4px;" method="get">
<input type="hidden" name="openId" id="openId" value="${openId }">
<input type="hidden" name="year" id="year" value="">
<input type="hidden" name="xq" id="xq" value="">
<input type="hidden" name="week" id="week" value="">
<input type="hidden" name="dsweek" id="dsweek" value="">
<input type="hidden" name="jxl" id="jxl" value="">
      <div class="top_01" style="overflow:auto;">
          <span style="width:85%; padding-left:5%">
              <select onChange="getJxl(this.value)">
              	<option  value="">楼号</option>
				<c:forEach var="data" items="${listAllJxl}" varStatus="obj">
					<option value="${data.jxl}">${data.jxl }</option>
				</c:forEach>
              </select>
              <select onChange="getYear(this.value)">
              	<option  value="">学年</option>
				<option value="2013-2014">2013-2014</option>
				<option value="2014-2015">2014-2015</option>
				<option value="2015-2016">2015-2016</option>
				<option value="2016-2017">2016-2017</option>
				<option value="2017-2018">2017-2018</option>
				<option value="2018-2019">2018-2019</option>
				<option value="2019-2020">2019-2020</option>
              </select>
        </span>
        <span style="width:10%"><a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
              <img src="<%=path%>/resources/img/wzy/fh1.png" style="margin-top:30%"></a>
         </span>  
      </div>
    	<div class="CXBiao">
          <select onChange="getXq(this.value)" style="margin-left:5%;">
            <option  value="">学期</option>
            <option value="1">1</option>
			<option value="2">2</option>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;
          <select onChange="getWeek(this.value)">
            <option  value="">星期</option>
            <option <c:if test="${week =='星期一'}">selected="selected"</c:if> value="星期一">星期一</option>
			<option <c:if test="${week =='星期二'}">selected="selected"</c:if> value="星期二">星期二</option>
			<option <c:if test="${week =='星期三'}">selected="selected"</c:if> value="星期三">星期三</option>
			<option <c:if test="${week =='星期四'}">selected="selected"</c:if> value="星期四">星期四</option>
			<option <c:if test="${week =='星期五'}">selected="selected"</c:if> value="星期五">星期五</option>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;
          <select onChange="getDsWeek(this.value)">
            <option  value="">周次</option>
            <option <c:if test="${dsweek =='1'}">selected="selected"</c:if> value="1">1</option>
			<option <c:if test="${dsweek =='2'}">selected="selected"</c:if> value="2">2</option>
			<option <c:if test="${dsweek =='3'}">selected="selected"</c:if> value="3">3</option>
			<option <c:if test="${dsweek =='4'}">selected="selected"</c:if> value="4">4</option>
			<option <c:if test="${dsweek =='5'}">selected="selected"</c:if> value="5">5</option>
			<option <c:if test="${dsweek =='6'}">selected="selected"</c:if> value="6">6</option>
			<option <c:if test="${dsweek =='7'}">selected="selected"</c:if> value="7">7</option>
			<option <c:if test="${dsweek =='8'}">selected="selected"</c:if> value="8">8</option>
			<option <c:if test="${dsweek =='9'}">selected="selected"</c:if> value="9">9</option>
			<option <c:if test="${dsweek =='10'}">selected="selected"</c:if> value="10">10</option>
			<option <c:if test="${dsweek =='11'}">selected="selected"</c:if> value="11">11</option>
			<option <c:if test="${dsweek =='12'}">selected="selected"</c:if> value="12">12</option>
			<option <c:if test="${dsweek =='13'}">selected="selected"</c:if> value="13">13</option>
			<option <c:if test="${dsweek =='14'}">selected="selected"</c:if> value="14">14</option>
			<option <c:if test="${dsweek =='15'}">selected="selected"</c:if> value="15">15</option>
			<option <c:if test="${dsweek =='16'}">selected="selected"</c:if> value="16">16</option>
			<option <c:if test="${dsweek =='17'}">selected="selected"</c:if> value="17">17</option>
			<option <c:if test="${dsweek =='18'}">selected="selected"</c:if> value="18">18</option>
			<option <c:if test="${dsweek =='19'}">selected="selected"</c:if> value="19">19</option>
			<option <c:if test="${dsweek =='20'}">selected="selected"</c:if> value="20">20</option>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" style="width:15%;height:50px;font-size:20px;" value="查询" onclick="query()">
          
     </div>
    <div class="main_02" style="top:240px;">
<%--     	<div class="M_box">
        	<div class="M_msg">
            	<div class="M_title" style=" font-size:36px; text-indent:72px;">
                     2014-2015学年第二学期第六周（星期二)
                    <img style=" float:right; margin-top:1%; margin-right:2%" src="<%=path%>/resources/img/wzy/dian.png" width="90" height="90">
                </div>
                <div class="M_main">
                	<ul>
                    	<div class="SKDi">
                        	北区图书馆
                        </div>
                    	<li>
                        	<span class="msg_main01" style="text-align:center">教室名称</span>
                            <span class="msg_main02" style="text-align:center">上课情况</span>
                        </li>
                        <li>
                        	<span class="msg_main001" style=" text-align:left;">北图301机房</span>
                            <span class="msg_main002">5-6节（数据库技术与应用实)</span>
                        </li>
                         <li>
                        	<span class="msg_main001" style=" text-align:left;">北图301机房</span>
                            <span class="msg_main002">5-6节（数据库技术与应用实)</span>
                        </li>
                         <li>
                        	<span class="msg_main001" style=" text-align:left;">北图301机房</span>
                            <span class="msg_main002">5-6节（数据库技术与应用实)</span>
                        </li>
                       
                    </ul>
                    <ul>
                    	<div class="SKDi">
                        	南区图书馆
                        </div>
                    	<li>
                        	<span class="msg_main01" style="text-align:center">教室名称</span>
                            <span class="msg_main02" style="text-align:center">上课情况</span>
                        </li>
                        <li>
                        	<span class="msg_main001" style=" text-align:left;">南图301机房</span>
                            <span class="msg_main002">5-6节（数据库技术与应用实)</span>
                        </li>
                         <li>
                        	<span class="msg_main001" style=" text-align:left;">南图301机房</span>
                            <span class="msg_main002">5-6节（数据库技术与应用实)</span>
                        </li>
                         <li>
                        	<span class="msg_main001" style=" text-align:left;">南图301机房</span>
                            <span class="msg_main002">5-6节（数据库技术与应用实)</span>
                        </li>
                       
                    </ul>
                </div>
            </div>
        </div> --%>	
    </div>

</form>
</div>
</body>
</html>
