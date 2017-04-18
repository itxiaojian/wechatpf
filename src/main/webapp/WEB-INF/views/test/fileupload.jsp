<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
﻿
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>图片上传</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=path%>/resources/src/favicon.ico">
<link rel="stylesheet" media="screen"
	href="<%=path%>/resources/src/published.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>

<link rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
 	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>

<style id="mailListAppendCss" type="text/css">
</style>
</head>
<body>
	<form action="<%=path%>/util/file/fileUload" method="post" enctype="multipart/form-data">
		文件类型：<input type="text" name="wjlx"><br>
		类型ID：<input type="text" name="lxid"><br>
		图片：<input type="file" name="file"><br>
		<input type="submit" value="上传">
	</form>
	<img src="<%=path%>/util/file/getFile?imgId=12">
</body>
</html>