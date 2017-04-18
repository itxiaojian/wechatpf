<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,initial-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
	<title>微点名</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wsh/main.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<style type="text/css">
		.demo{min-height: 250px;text-align: center;width: 100%;}
		.demo p{line-height:30px}
		#code{margin-top:10px}
	</style>
	<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wsh/jquery.qrcode.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var str = "${url}";
			$('#code').qrcode(str);
		})
		function toList(openId){
			location.href ="<%=path%>/wsh/ZsSkkqb/toEsscList?openId="+openId;
		}
	</script>
</head>

<body>

<div id="main" style="margin-top: 10%;">
   <h2 class="top_title" style="text-align: center;">扫描二维码进行签到</h2>
   <div class="demo">
   		<div id="code"></div>
   		<div class="controls" style="margin-top: 5%;">
			<input id="save_apply" class="btn btn-success" type="button" onclick="toList(${openId});" value="返回">
		</div>
   </div>

</div>


</body>
</html>