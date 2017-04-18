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
<%-- <link href="<%=path%>/resources/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css" /> --%>
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
	
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	
	<script type="text/javascript">
	/* 返回首页 */
	function shouye() {
		WeixinJSBridge.call("closeWindow");
	}
	</script>
	<style type="text/css">
	a{
		text-decoration:none;
		 color: #666666;
     }
	</style>

<title>公司资讯列表</title>
</head>
<body style="overflow: auto;">
	<div class="main">
		<div class="DYtop" >
			<img style="width:100%; " src="<%=path%>/resources/img/gslogo.png" /><br/>
			<div class="anniu" style="position: absolute; top:3%; left: 87%;">
			<a href="#" style="float:right;width:40px;height:50px;" onclick="shouye()" >
			   <img  style="width:70%"
			    src="<%=path%>/resources/img/syan.png" />
			    </a>
			</div>
		</div>

		<div class="middle">
			<h1>公司资讯列表</h1>
		</div>

		<div class="bottom">
			<%-- <div class="tupian">
				<img src="<%=path%>/resources/img/LBT.png" />
			</div> --%>
			<div style="width: 95%">
					<c:forEach items="${map}" var="map" varStatus="status">
				<table style="width: 100%">
				<tr>
				            <a href="<%=path%>/com/company/gszxDetail?id=${map.ID}" class="wenzi" style="width:200px;">${map.Title}</a> 
							<span style="float:right;"> ${map.UpdateTime }</span>
				</tr>
				</table>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>