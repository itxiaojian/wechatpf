<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>

<html eiiebffcjbffiheggaebebgceaeccbia_b="1" bdgjjgagedbdaebhbjbcabcdgeeebecf_b="1" idceifdedfeiefjgfcjfbchejgbcbeid_b="1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>教师监考信息</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=3.0,user-scalable=yes;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script><style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function query(){
		$("#myForm").submit();
	}
</script>
<script type="text/javascript">
var i=0;
function loadMore(page,openId,qh){
	i=page;
	i++;
	var url ="<%=path%>/wfw/ZsJsjkxx/toJsjkxxZj";
	var html= "";
    $.ajax({
		url :url,
		data : {
			pages:i,
			openId:openId
		},
		type : "post",
		success : function(data) {
			var rst = eval(data);
			$.each(rst,function(i,value){             
		    html+="<div id='lidw"+i+"' class='maring1'>"
				+"<div class='wwx_f_l' style='width:100%'>"
				+"<table id='tableJyxm' style='width:100%;'>"
				+"<tr class='odd-row'>"
			    +"<td class='first'>教师工号</td>"
			    +"<td class='last'>"+value.jsgh+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>姓名</td>"
			    +"<td class='last'>"+value.xm+"</td>"
			    +"</tr>"
			    +"<tr class='odd-row'>"
			    +"<td class='first'>课程编号</td>"
			    +"<td class='last'>"+value.jkkcbh+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>课程名称</td>"
			    +"<td class='last'>"+value.jkkcmc+"</td>"
			    +"</tr>"
			    +"<tr class='odd-row'>"
			    +"<td class='first'>考试方式</td>"
			    +"<td class='last'>"+value.ksfs+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>考试时长</td>"
			    +"<td class='last'>"+value.kssc+"</td>"
			    +"</tr>"
			    +"<tr class='odd-row'>"
			    +"<td class='first'>考试时间</td>"
			    +"<td class='last'>"+value.kssj+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>监考地点</td>"
			    +"<td class='last'>"+value.jkdd+"</td>"
			    +"</tr>"
			    +"<tr class='odd-row'>"
			    +"<td class='first'>考试人数</td>"
			    +"<td class='last'>"+value.ksrs+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>参考班级</td>"
			    +"<td class='last'>"+value.cjbj+"</td>"
			    +"</tr>"
			    +"<tr class='odd-row'>"
			    +"<td class='first'>监考日期</td>"
			    +"<td class='last'>"+value.jkrq+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>考试性质</td>"
			    +"<td class='last'>"+value.ksxz+"</td>"
			    +"</tr>"
			    +"<tr class='odd-row'>"
			    +"<td class='first'>备注</td>"
			    +"<td class='last'>"+value.bz+"</td>"
			    +"</tr>"
                +"</table>"
            	+"</div>"
				+"<div class='wwx_clear'></div>"
			    +"</div>";	
			})
		    $('.-ft').before(html);
			$('.btn-block').removeAttr("onclick");
			$('.btn-block').attr("onclick","loadMore('"+i+"','"+openId+"')");
			},
		   error : function() {
			alert("error");
		}
	  });
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

//点击“加载更多之后”定位到最后一条数据
function ScrollDiv() { 
	var size=$("#size").val();
		if(size!=null&&size!=''){
			 if(size<=10){
	//				document.getElementById('lidw1').scrollIntoView(true);
			}else if(size%10==0){
				var num = (parseInt(size/10-1)*10+1);
				//alert(num);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}else{
				var num = (parseInt(size/10)*10);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}
		}
}

$(function() {
	    $("table tr:nth-child(odd)").addClass("odd-row");
		$("table td:first-child, table th:first-child").addClass("first");
		$("table td:last-child, table th:last-child").addClass("last");
	});
</script>
<style type="text/css">
.btn-block {
    display: block;
    width: 100%;
}
.btn-lg, .btn-group-lg > .btn {
    border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
    border-top-left-radius: 6px;
    border-top-right-radius: 6px;
    font-size: 18px;
    line-height: 1.33;
    padding-bottom: 10px;
    padding-left: 16px;
    padding-right: 16px;
    padding-top: 10px;
}
.btn-default {
    background-color: #fff;
    border-bottom-color: #ccc;
    border-left-color: #ccc;
    border-right-color: #ccc;
    border-top-color: #ccc;
    color: #333;
}
.btn {
border-bottom-style: solid;
    border-bottom-width: 1px;
    border-image-outset: 0 0 0 0;
    border-image-repeat: stretch stretch;
    border-image-slice: 100% 100% 100% 100%;
    border-image-source: none;
    border-image-width: 1 1 1 1;
    border-left-style: solid;
    border-left-width: 1px;
    border-right-style: solid;
    border-right-width: 1px;
    border-top-style: solid;
    border-top-width: 1px;
    cursor: pointer;
     text-align: center;
    vertical-align: middle;
    white-space: nowrap;
}
button, input, optgroup, select, textarea {
    -x-system-font: none;
    color: inherit;
    font-family: inherit;
    font-feature-settings: inherit;
    font-kerning: inherit;
    font-language-override: inherit;
    font-size: inherit;
    font-size-adjust: inherit;
    font-stretch: inherit;
    font-style: inherit;
    font-synthesis: inherit;
    font-variant-alternates: inherit;
    font-variant-caps: inherit;
    font-variant-east-asian: inherit;
    font-variant-ligatures: inherit;
    font-variant-numeric: inherit;
    font-variant-position: inherit;
    font-weight: inherit;
    line-height: inherit;
    margin-bottom: 0;
    margin-left: 0;
    margin-right: 0;
    margin-top: 0;
}
</style>
<style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.span_hz{ float:left; font-size:11px;margin-top:0.1%;}
/* .top_01 img{ margin-top:15%; display:block; float:left;} */
.fanhui{margin-top:3%;float:right;margin-right:4%;width:5%;}
.span_input{margin-top:-14%;width:65%;height:50%;margin-left:35%;}
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ width:32%; height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_01 img{ float:left; margin-top:10%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
</style>
</head>

<body id="cardunion" class="mode_webapp2" style="background-color: white; padding-bottom: 0;" onload="ScrollDiv();wx.hideOptionMenu();">
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="教师监考信息">
	<a href="#" target="content" onfocus="this.blur()"><span>教师监考信息</span></a>
	</li>
</ul>
</div>
<!-- <div class="bg1"> -->
<!-- 	<div class="style1"> -->
<!-- 		<div class="wwx_f_l" style="position: relative;"> -->
<%-- 		<a class="font1" href="<%=path%>/wfw/ZsJsjkxx/toJsjkxx?openId=${openId }">教师工号&姓名</a> --%>
<!-- 			&nbsp;&nbsp;&nbsp; -->
<!-- 			<!-- <span class="font1" >教师工号</span> -->
<!-- 		</div> -->
<%-- 		<form action="<%=path%>/wfw/ZsJsjkxx/toJsjkxx" id="myForm" class="wwx_f_a"  method="post"> --%>
<!-- 			<input type="text" class="inputhaha" name="code" id="code" > -->
<%-- 			<input type="hidden" name="openId" id="openId" value="${openId }"> --%>
<%-- 			<input type="hidden" name="size" id="size" value="${size }"> --%>
<!-- 			<span><a class="font1" href="#" style="margin-left: 10px;" onclick="query();">查询</a></span> -->
<!-- 			</form> -->
<!-- 				<div class="wwx_clear"></div> -->
<!-- 			<div class="anniu" style="left:85%;"> -->
<%-- 	    <a style="float:right;width:40px;height:50px;" href="<%=path%>/wfw/zy/zhuye?openId=${openId}" > --%>
<%-- 			   <img style="width:80%" src="<%=path%>/resources/img/wfwzy.png" > --%>
<!-- 		    </a> -->
<!-- 	 </div> -->
<!-- 	</div> -->
<!-- </div> -->

<div class="top_01">
		<span class="span_hz" style="width:30%; padding-left:5%;margin-top: 12px;">
        	教师工号&姓名:&nbsp;&nbsp;
        </span>
		<form action="<%=path%>/wfw/ZsJsjkxx/toJsjkxx" id="myForm" class="wwx_f_a"  method="post" style="display: inline;">
			<input type="text" class="inputhaha" name="code" id="code" style="margin-top: 10px;" >
			<input type="hidden" name="openId" id="openId" value="${openId }">
			<input type="hidden" name="size" id="size" value="${size }">
		</form>
		<span><a class="font1" href="#" style="margin-left: 10px;font-size: 14px;" onclick="query();">查询</a></span>
		<span style="width:10%">
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fh1.png" style="width:5%;margin-top: 2.5%;">
            </a>
        </span>
</div>
<div class="zhengwen">
<!-- <div>
	<div class="style2">
		<span class="color1">教师监考信息</span>
	</div>
</div> -->
<div>
	<div class="style4">
	<c:forEach var="data" items="${list}" varStatus="obj">
		<div id="lidw${obj.count }" class="maring1">
				<div class="wwx_f_l" style="width:100%">
					<table style="width: 100%; font-size:17px;">
					    <tr>
					       <td>教师工号</td>
					       <td>${data.jsgh }</td>
					    </tr>
					    <tr>
					       <td>姓名</td>
					       <td>${data.xm }</td>
					    </tr>
					    <tr>
					       <td>课程编号</td>
					       <td>${data.jkkcbh }</td>
					    </tr>
					    <tr>
					       <td>课程名称</td>
					       <td>${data.jkkcmc }</td>
					    </tr>
					    <tr>
					       <td>考试方式</td>
					       <td>${data.ksfs}</td>
					    </tr>
					    <tr>
					       <td>考试时长</td>
					       <td>${data.kssc }</td>
					    </tr>
					    <tr>
					       <td>考试时间</td>
					       <td>${data.kssj}</td>
					    </tr>
					    <tr>
					       <td>监考地点</td>
					       <td>${data.jkdd }</td>
					    </tr>
					    <tr>
					       <td>考试人数</td>
					       <td>${data.ksrs}</td>
					    </tr>
					    <tr>
					       <td>参考班级</td>
					       <td>${data.cjbj }</td>
					    </tr>
					    <tr>
					       <td>监考日期</td>
					       <td>${data.jkrq }</td>
					    </tr>
					    <tr>
					        <td>考试性质</td>
					        <td>${data.ksxz }</td>
					    </tr>
					    <tr>
					       <td>备注</td>
					       <td>${data.bz }</td>
					    </tr>
					</table>
				</div>
				<div class="wwx_clear"></div>
			</div>	
		</c:forEach>
<c:choose>  
   <c:when test="${empty list}">
        <div class="text">
			<!-- 			<p>教师监考信息暂无...</p> -->
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>  
		<div class="-ft" style="display:none;">
			<button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${openId }');">加载更多</button>
		</div>
   </c:when>  
   <c:otherwise>
        <div class="-ft" style="margin-top: -20px;">
			<button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${openId }');">加载更多</button>
		</div>  
   </c:otherwise>  
</c:choose>
		</div>
</div>
</div>
</body>
<script type="text/javascript">var str="";</script>
<script type="text/javascript" src="<%=path%>/resources/js/wysy.js"></script>
<script type="text/javascript">
var cn=4;
//onload时触发水印绘制
// window.onload=function(){
// 	watermark({ watermark_txt: "${text}" });
// 	for(var i=0;i<cn;i++){
// 		var my=document.getElementById('mask_div0'+i);
// 		if(my!=null){
// 			var p=my.parentNode;
// 			p.removeChild(my);
// 			//my.remove();
// 		}
// 	}
// };

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