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
<%--   	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script> --%>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
   <script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	var i=0;
	function loadMore(page,openId){
		i=page;
		i++;
	    var qh
	    location.href ="<%=path%>/wfw/ZsWljf/toWljf?pages="+i+"&openId="+openId;
	}
	
	function TZ(openId,zh){
	    location.href ="<%=path%>/wfw/ZsWljf/toWljfxx?zh="+zh+"&openId="+openId;
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
	
    wx.ready(function(){
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    	wx.hideOptionMenu();
    });
    
	</script>
    <%-- <link href="<%=path%>/resources/css/tab-import.css" rel="stylesheet" style="text/css" />
	<link href="<%=path%>/resources/css/css.css" rel="stylesheet" style="text/css" /> --%>
	<title>网络计费信息</title>
  </head>
 <style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;
 line-height:38px;}
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
.M_main ul{ float:left; padding-bottom:20px;}
.msg_main01{ text-indent:10px;width:40%;}
.msg_main02{ text-indent:30px;width:60%;}
.M_tab{ width:100%; border-bottom:1px solid #c5e0fa; float:left;}
.M_tab span{ width:50%; line-height:40px; font-size:15px; text-align:center; float:left; color:#2e87d3;}
</style>
 
<body style="max-width:640px;margin-left: auto;margin-right: auto;background-color:white;" onload="setValue();">
<input type="hidden" name="openId" id="openId" value="${openId }"> 
<input type="hidden" name="size" id="size" value="${size }">

 <div class="top_01" style="height:50px;">
 
    	<span class="span_hz" style="width:80%; padding-left:5%;margin-top: 5.375;">
              	&nbsp;&nbsp;网络计费&nbsp;&nbsp; 
		</span>
		
        <span style="width:10%">
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
        		<img  src="<%=path%>/resources/img/wzy/FH.png" style="width:10%;margin-top:2.5%;">
            </a>
        </span>
  </div>
<div class="main_02" style="height:100%;">
	<div class="M_box">
	    <div class="M_msg">
	    <c:forEach var="data1" items="${Wljflist}" varStatus="obj">
		         <div class="M_title">
		             <img style="margin-top:4%; margin-left:2%;width:10%" src="<%=path%>/resources/img/wzy/tsjy.png" >${data1.xm}
		             <span>&nbsp;&nbsp;学号:${data1.zh}</span>
		               <img style="float:right; margin-top:4%; margin-right:2%;width:10%" src="<%=path%>/resources/img/wzy/tsdian.png">
		         </div>
	            <div class="M_main" id="TAB01${obj.count}" style="display: block;"> 
	                 <ul>
		             
		                 	<li>
	                        	<span class="msg_main01">账户状态</span>
	                            <span class="msg_main02" style="color:#fc4312">${data1.zhzt}</span>
	                        </li>
							<li>
	                        	<span class="msg_main01">费用状态</span>
	                            <span class="msg_main02">${data1.fyzt}</span>
	                        </li>
	                        <li>
	                        	<span class="msg_main01">余额</span>
	                            <span class="msg_main02">${data1.ye}</span>
	                        </li>
	                        <li>
	                        	<span class="msg_main01">上机总计</span>
	                            <span class="msg_main02">${data1.sjzs}</span>
	                        </li>
	                    	<li>
	                        	<span class="msg_main01">流量总计</span>
	                            <span class="msg_main02" >${data1.llzj}</span>
	                        </li>
	                        <li>
	                        	<span class="msg_main01">用户组</span>
	                            <span class="msg_main02" >${data1.yhz}</span>
	                        </li>
	                        <li>
	                        	<span class="msg_main01">手机号</span>
	                            <span class="msg_main02" >${data1.sjh}</span>
	                        </li>
	                        <li>
	                        	<span class="msg_main01">电话</span>
	                            <span class="msg_main02" >${data1.dh}</span>
	                        </li>
	                        <li>
	                        	<span class="msg_main01">上次登录时间</span>
	                            <span class="msg_main02" >${data1.scdlsj}</span>
	                        </li>
	                        <li style="border-bottom:1px solid #8bc0f2;" onclick="TZ('${openId}','${data1.zh}')">
	                        	<span class="masg_main01" style="width:100%;text-align:center;color:#8bc0f2">详细信息</span>
	                        </li>
	                       </ul>
	                       </div>
		             </c:forEach>
	                <c:if test="${js != 'ROLE_STUDENT' }">
		            <div class="-ft" style="margin-bottom:0rem;width:100%;height:45px;">
		            <button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${openId }');" style="bottom:0px;">加载更多</button>
        	   		</div>
        	   		</c:if> 
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
