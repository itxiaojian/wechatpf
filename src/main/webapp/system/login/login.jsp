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
<!-- <link type="text/css" rel="stylesheet" href="css/base.css" /> -->
<!-- <link type="text/css" rel="stylesheet" href="css/main.css" /> -->

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
<style>
*{ margin:0px; padding:0px; font-family:"微软雅黑";}
.top{ width:100%; height:86px; background:url(<%=path%>/system/login/images/BG_top.png); margin-top:0px; line-height:86px; }
.top img{ float:left; margin-top:4px; margin-left:80px;}
.zhu_title{ font-size:28px;}
.fu_title{ font-size:18px; color:#999;}
.B_center{ width:1200px; margin:auto; overflow:hidden; position:relative;}
.main{ width:100%; height:500px;}
.chatu{ padding-top:50px; margin-left:30px;}
.login_box{ width:270px; height:300px; border:#ccc 1px solid; border-radius:10px; position:absolute; right:76px; top:50px; text-align:center;}
.login_box input{ width:216px; height:38px; border:#ccc 1px solid; border-radius:10px; color:#999; text-indent:36px; margin-top:20px;}
.login_box button{width:216px; height:38px; margin-top:20px; border:none; background-color:#f4aa2a; color:#fff;border-radius:10px;}
.gongneng{ text-align:center; overflow:hidden;}
.gongneng span{ width:20%; float:left; line-height:45px; color:#666;}
.foot{ width:100%; height:110px;background: url(<%=path%>/system/login/images/shui.gif) center no-repeat; line-height:30px; text-align:center; color:#fff; font-size:12px; position:fixed; left:0px; bottom:0px; z-index:-999;}
.yhm{ position:absolute; left:36px; top:28px;}
.mima{ position:absolute; left:36px; top:88px;}
.yzm{ position:absolute; left:36px; top:148px;}
.yzm_tu{position:absolute; right:36px; top:148px;}
</style>
</head>
<body>
<div class="top">
	<div class="B_center">
        <span><img src="images/logo.png"/></span>&nbsp;
        <span class="zhu_title"></span>&nbsp;
        <span class="fu_title">微信服务管理平台</span>
    </div>
</div>
<div class="main">
	<div class="B_center">
    	<img class="chatu" src="images/chatu.gif"/>
        <div class="login_box">
        	<form id="loginForm" action="<%=path%>/j_spring_security_check" class="login_form" method="post">
	         
        	<img class="yhm" src="images/yhm.png"/>
            <img class="mima" src="images/mima.png"/>
            <img class="yzm" src="images/ewm.png"/>
            <a href="javascript:;" onclick="changeImg()"><img class="yzm_tu" id="imgObj" alt="验证码" src="<%=path%>/sys/SysCode/code"/></a>
        	<input type="text" id="username" name="j_username" placeholder="用户名"/>
            <input type="password" id="password" name="j_password" placeholder="密码"/>
            <input type="text" id="validateCode" name="validateCode"  placeholder="请输入验证码" style="margin-bottom:10px;"/>
            <div class="login_info" > ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}  </div>
            <hr/>
            <button onclick="login()">登录</button>
            </form>
        </div>
    </div>
    <div class="gongneng">	
    	<div class="B_center">
            <span><img src="images/wxgl.png"/></span>
            <span><img src="images/wsbx.png"/></span>
            <span><img src="images/xzxx.png"/></span>
            <span><img src="images/zhcx.png"/></span>
            <span><img src="images/xtgl.png"/></span>
        </div>
    </div>
</div>
<div class="foot_shui"></div>
<div class="foot">合肥智圣  版权所有Copyright©2016 ALL Right Reserved</div>
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