<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<% 
	String path = request.getContextPath();
response.addHeader("X-Frame-OPTIONS", "SAMEORIGIN");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="no-cache" http-equiv="Pragma" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="0" http-equiv="Expires" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta charset="utf-8" />
<title>${setting['app']}</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/main.css" />

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}
/*提示信息*/

.login_info{
	color:red;

}
</style>
</head>
<body >
<div class="container PNGFIX">
	  <div class="login_container PNGFIX">
	    <div class="login_panel PNGFIX">
	       <div class="login_logo"><img src="images/logo.png" class="PNGFIX" style="margin-top: -50px"></div>
	       <div class="login_box">
	       	<form id="loginForm" action="<%=path%>/j_spring_security_check" class="login_form" method="post">
		         <div class="login_input fl">
		           <div class="t01"><b class="t02"></b><input type="text" id="username" name="j_username" class="input_txt PNGFIX"></div>
		           <div class="t01"><b class="t03"></b><input type="password" id="password" name="j_password" class="input_txt PNGFIX"></div>
		           <div class="t01">
<!-- 		           		<div class="login_input fl"> -->
		           			<b class="t04"></b><input type="text" id="validateCode" name="validateCode" class="input_txt PNGFIX">
<!-- 		           		</div> -->
<!-- 		           		<div class="login_btn fl" style="margin-top: 6px;"> -->
<%-- 				   			<img id="imgObj" alt="验证码" src="<%=path%>/sys/SysCode/code" style="width:70px;height: 20px;" /><a href="javascript:;" onclick="changeImg()">换一张</a> --%>
<!-- 				   		</div> -->
				   </div>
<!-- 				   <div class="t01"> -->
<%-- 				   		<img id="imgObj" alt="验证码" src="<%=path%>/sys/SysCode/code" /><a href="javascript:;" onclick="changeImg()">换一张</a> --%>
<!-- 				   </div> -->
		           <div class="clear"></div>
		         </div>
		         <div class="login_btn fl">
		         	<div>
			           <button type="button" onclick="login()" value="" class="PNGFIX"></button>
		         	</div>
		         	<div style="margin-top: 17px;">
		         		<a href="javascript:;" onclick="changeImg()"><img id="imgObj" alt="验证码" src="<%=path%>/sys/SysCode/code" style="width:70px;height: 20px;" /></a>
		         	</div>
		         </div>
		         <div class="clr"></div>
	         </form>
	         <div class="login_info" > ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}  </div>
	       </div>
	    </div>
	     <div class="footer">版权所有：${setting['copyright'] }</div>
	  </div>
	</div>
<script>
if(window.parent != window) {
	window.parent.location.href = "<%=request.getContextPath()%>/system/login/login.jsp";		
}
	$(document).ready(function(){
		//居中
		 document.getElementById("username").focus();
		 $("#username").keydown(function(event){
		 	if(event.keyCode==13){
				login()
			}
		 })
		 $("#password").keydown(function(event){
		 	if(event.keyCode==13){
				login()
			}
		 })
		 
	})


    function validateEmpty(str){
    	if (str == "") {
    	return true;
    	}
    	return false;
    }
function login(){
	var comCode = document.getElementById("validateCode").value;
	var loginName = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	if(validateEmpty(loginName)){
		   return false;
		}
	if(comCode==null||comCode==''){
		alert("请填写验证码。");
		return false;
	}
	if(loginName.length<1 || loginName.length> 20){
		   return false;
		}
/* 	if(validateEmpty(password)){
	     return false;
	} */
  	document.getElementById("loginForm").submit();
  }
  
function changeImg() {
    var imgSrc = $("#imgObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
}
//时间戳   
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    //url = url.substring(0, 17);
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}
</script>
</body>
</html>