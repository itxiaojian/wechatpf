<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link href="<%=path%>/resources/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />

<title>规章制度列表</title>
</head>
<body style="overflow: auto;" >
	<div class="main">
		<div class="DYtop">
			<img style="width:100%;" src="<%=path%>/resources/img/BT.jpg" />
			<div class="anniu">
			<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}" style="float:right;width:40px;height:50px;" >
			   <img  style="width:70%"
			    src="<%=path%>/resources/img/zyan.png" />
			    </a>
			</div>
		</div>

		<div class="middle">
			<h1>规章制度列表</h1>
		</div>

		<div class="bottom">
			<div class="tupian">
				<img src="<%=path%>/resources/img/gzzdBT.png" />
			</div>
			<div>
			
				<c:forEach items="${list}" var="list">
				<table style="width: 100%">
				<tr>
				            <a href="<%=path%>/wzy/ZyXyxgxx/zyXyxgxxDetail?id=${list.id}" class="wenzi">${list.xwbt }</a> 
							<span style="float:right;"> <fmt:formatDate value="${list.sxsj }" type="date" dateStyle="medium"/></span>
				</tr>
				</table>
				</c:forEach>
			
			</div>
		</div>
		</div>
</body>
</html>