<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
   <link href="<%=path%>/resources/css/tsjyxx.css" rel="stylesheet" type="text/css">
  
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3"></meta>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
  <link href="<%=path%>/resources/css/tsjyxx.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
   <script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function Change(){
   	 var objS = document.getElementById("pid");
   	 var grade = objS.options[objS.selectedIndex].value;
     var openId = document.getElementById("openId").value;
     location.href="<%=path%>/wfw/ZsXsgkxx/toGkxxByQh?ksqh="+grade+"&openId="+openId;
	}
	
	var i=0;
	function loadMore(page,openId){
		i=page;
		i++;
		var objS = document.getElementById("pid");
	   	var grade = objS.options[objS.selectedIndex].value;
		location.href ="<%=path%>/wfw/ZsXsgkxx/toGkxxByQh?pages="+i+"&openId="+openId+"&ksqh="+grade;
	}
	
    function setValue(){
    	var size=$("#size").val();
		if(size!=null&&size!=''){
			 if(size<=10){
			}else if(size%10==0){
				var num = (parseInt(size/10-1)*10+1);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}else{
				var num = (parseInt(size/10)*10);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}
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
	<title>学生挂科信息</title>
  </head>
 <style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;line-height:38px;}
.top_01 select{ font-size:16px; width:53%; height:44%; border-radius:2px;margin-top:-12%;color:#2e87d3;margin-left:1%;}
.span_hz{ float:left; font-size:15px;margin-top:0.1%;}
.top_01 img{  display:block; float:left;} 
.fanhui{margin-top:4%;float:right;margin-right:-2rem;width:5%;}
.span_input{line-height: normal; }
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ width:32%; height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_01 img{ float:left; margin-top:10%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
/**页面表格的行高*/
th,td{padding-bottom:2px;padding-top:2px;line-height:2px;}

.M_box{ width:90%; margin:auto;}
.M_msg{ width:100%;border:1px solid #f2f2f2; border-radius:20px; margin-top:20%; float:left;}
.M_title{ width:100%; height:50px; background-color:#8bc0f2; color:#fff; line-height:50px; font-size:18px; float:left;border-radius:10px 10px 0 0 ;}
.M_title span{font-size:10px;}
.M_title img{ float:left;}
.M_main{ clear:both; font-size:10px;}
.M_main li{ float:left; padding-top:2%; width:100%;}
.M_main li span{ float:left;}
.M_main ul{ float:left;border-bottom:1px solid #8bc0f2; padding-bottom:20px;}
.msg_main01{ text-indent:30px;width:32%;}
.msg_main02{ text-indent:45px;width:68%;}
.M_tab{ width:100%; border-bottom:1px solid #c5e0fa; float:left;}
.M_tab span{ width:50%; line-height:40px; font-size:15px; text-align:center; float:left; color:#2e87d3;}
</style>
 
<body style="max-width:640px;margin-left: auto;margin-right: auto;background-color:white;" onload="setValue();">
<input type="hidden" name="openId" id="openId" value="${openId }"> 
<input type="hidden" name="size" id="size" value="${size }">
<input type="hidden" name="qh" id="qh" value="${qh }">

 <div class="top_01" style="height:50px;">
 
    	<span class="span_hz" style="width:80%; padding-left:5%;margin-top: 5.375;">
              <img src="<%=path%>/resources/img/wzy/c11-04.png" style=" margin-top:1%" width="30" height="30"> 学生挂科信息&nbsp;&nbsp; &nbsp;&nbsp; 
<!--                <select id="pid" style="font-size:13px;color:#2e87d3;" onchange="Change()"> -->
<%--             	<c:if test="${empty qhlist}"> --%>
<!-- 					<option style="font-size:13px;"  value="" >无</option> -->
<%-- 				</c:if> --%>
<%-- 				  	<c:forEach var="list" items="${qhlist}" varStatus="s"> --%>
<%-- 						<option style="font-size:13px;" value="${list.ksqh }" <c:if test="${list.ksqh==qh }">selected="selected"</c:if>>${list.ksqh }</option> --%>
<%-- 					</c:forEach> --%>
<!--             </select> -->
		</span>
		
        <span style="width:10%">
        	<a href="javascript:;" onclick="javascript:history.go(-1)">
        		<img  src="<%=path%>/resources/img/wzy/fh1.png" style="width:6%;margin-top:3.5%;">
            </a>
        </span>
  </div>
<div class="main_02">
	<div class="M_box">
	    <div class="M_msg">
	     <c:if test="${!empty xslist}">
	         <c:forEach var="xslist" items="${xslist}" varStatus="obj" begin="0" end="0">
	         <div class="M_title">
	             <img style="margin-top:3%; margin-left:2%;width:10%" src="<%=path%>/resources/img/wzy/tsjy.png" >${xslist.xm}
	             <span>&nbsp;&nbsp;学号:${xslist.xh}</span>
	               <img style="float:right; margin-top:3%; margin-right:2%;width:10%" src="<%=path%>/resources/img/wzy/tsdian.png">
	         </div>
	        </c:forEach>
            <div class="M_main" id="TAB01${obj.count}" style="display: block;"> 
                 <c:forEach var="xxlist" items="${xxlist}"> 
<%--                 <c:if test="${xxlist.xh == xslist.xh"> --%>
	             <ul>
	              <li>
	                  <span class="msg_main01">学年学期</span>
	                  <span class="msg_main02">${xxlist.xn}&nbsp;&nbsp;${xxlist.xq }</span>
	                </li>
	                <li>
	                  <span class="msg_main01">课程编号</span>
	                  <span class="msg_main02">${xxlist.kcbh}</span>
	                </li>
	                <li>
	                  <span class="msg_main01">课程名称</span>
	                  <span class="msg_main02">${xxlist.kcmc}</span>
	                </li>
	                <li>
	                  <span class="msg_main01">课程体系</span>
	                  <span class="msg_main02">${xxlist.kctx}</span>
	                </li>
	                <li>
	                  <span class="msg_main01" >考试性质</span>
	                  <span class="msg_main02">${xxlist.ksxz}</span>
	                </li>
	                <li>
	                  <span class="msg_main01">成绩</span>
	                  <span class="msg_main02" style="color:#fc4312">${xxlist.cj}</span>
	                </li>
	                 <li>
	                  <span class="msg_main01">学分</span>
	                  <span class="msg_main02">${xxlist.xf}</span>
	                </li>	
	                 <li>
	                  <span class="msg_main01">任课教师</span>
	                  <span class="msg_main02">${xxlist.rkjs}</span>
	                </li>	
	                 <li>
	                  <span class="msg_main01">任课教师工号</span>
	                  <span class="msg_main02">${xxlist.rkjsgh}</span>
	                </li>		                
	             </ul>	
<%-- 	             </c:if>              --%>
	             </c:forEach>
	           </div>
	      </c:if>
     
      <c:choose>  
   <c:when test="${empty xslist}">
        <div class="text">
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>  
   </c:when>  
   <c:otherwise>
   </c:otherwise>  
</c:choose>
	 </div>  
   </div>
</div>
		
</body>
  <script type="text/javascript">
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
</html>
