<%@ page session="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>${setting.app}</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta content="no-cache" http-equiv="Pragma" />
<meta content="no-cache" http-equiv="Cache-Control" />
<meta content="0" http-equiv="Expires" />
</head>
<style>
* {padding:0px;margin:0px;}
</style>
<script>
if(window.parent != window) {
	window.parent.location.href = "<%=request.getContextPath()%>/jsp/loginlimit.jsp";		
}
</script>
<body style="text-align:center;width:100%;padding-top:100px;">
		<font color="red">账号在其他地方登录<a href="<%=request.getContextPath()%>/">点击重新登录</a></font>
</body>
</html>