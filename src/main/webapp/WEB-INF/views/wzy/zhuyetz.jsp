<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="no-cache" http-equiv="Pragma" />
<meta content="no-cache" http-equiv="Cache-Control" />
<meta content="0" http-equiv="Expires" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8" />
<meta name="viewport"content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3" />
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/zhuye.css" />

<%-- <link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue" /> --%>
<%-- <link href="<%=path%>/resources/css/base.css" rel="stylesheet" /> --%>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<%-- <script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT> --%>
<%-- <script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT> --%>
<%-- <script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script> --%>


<script type="text/javascript">
	/* 返回首页 */
	function shouye() {
		WeixinJSBridge.call("closeWindow");
	}
	
	function index(){
		var url = "${newurl}";
		location.href=url;
	}
</script>
<title>微主页</title>
</head>

<body class="body" onload="index()">
	<img src="<%=path%>/resources/img/wzy/1.gif" />
</body>
</html>