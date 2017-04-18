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
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />

<title>一卡通大额消费提醒列表</title>
<style type="text/css">
	.anniu{top:1.2%;right:1%; }
    .anniu img{display:block;width:100%;height:30%;} 
    
	 img{ pointer-events: none; }
</style>
</head>
<body style="overflow: auto;">
	<div class="main">
<!-- 		<div class="DYtop" > -->
<%-- 			<img style="width:100%; " src="<%=path%>/resources/img/wfw.png" /><br/> --%>
<!-- 			<div class="anniu"> -->
<%-- 			<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}" style="float:right;width:40px;height:50px;" > --%>
<!-- 			   <img  style="width:70%" -->
<%-- 			    src="<%=path%>/resources/img/zyan.png" /> --%>
<!-- 			    </a> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="top">
			<img style="width: 100%;" src="<%=path%>/resources/img/wzy/logo.png" />
	
			<div class="anniu" style="position: absolute; top:1%; left: 87%;">
				<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}"> <img 
					 src="<%=path%>/resources/img/wzy/fanhui.png" />
				</a>
			</div>
		</div>
		<div class="middle">
			<h1>一卡通大额消费提醒列表</h1>
		</div>

		<div class="bottom">
			<%-- <div class="tupian">
				<img src="<%=path%>/resources/img/LBT.png" />
			</div> --%>
			<div>
				
					<c:forEach items="${list}" var="list">
					<table>
						<tr>
					<table style="width: 100%">
						<tr>
							<a href=" <%=path%>/wtx/TxYktdexftx/totxYktdexftxDetail?id=${list.id} "
								class="wenzi" style="text-decoration:none;">${list.txnr }</a>
								
							<span style="float: right;"> <fmt:formatDate
									value="${list.txsj }" type="date" dateStyle="medium" /></span>
						</tr>
					</table>
						</tr>
					</table>
					</c:forEach>
				
			</div>
		</div>
	</div>
</body>
</html>