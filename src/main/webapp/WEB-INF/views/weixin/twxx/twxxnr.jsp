<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html class="gecko firefox firefox40 win js">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=10">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js"
	type="text/javascript"></script>
<title>图文信息内容</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"  href="<%=path%>/resources/css/GJSW.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/bootstrap.min.css">
<style type="text/css">
.new_main img{ width:100%;height:300px;}
</style>
</head>
<body>
<div class="iphone">
	<div class="WZY_top">
    	<a onclick="location.href='javascript:history.go(-1);'">
    	<img src="<%=path%>/resources/img/wzy/FH.png" width="40" height="40">
    	</a>
    </div>
    <div class="WZY_main02">
        <div class="container">
        <div class="new_msg_title">
            	<c:forEach var="data" items="${list}" varStatus="obj">
							<h3>${data.xxbt }</h3>		
                <h5 style="color:#999999">${data.tjsj }&nbsp;&nbsp;&nbsp;&nbsp;</h5>
                <h5 style="color:#0478D8">合肥智圣系统</h5>
                </c:forEach>
            </div>
            <div class="msg_box">
                <div class="new_main">
                	<c:forEach var="data" items="${list}" varStatus="obj">
                	${data.xxnr }&nbsp;&nbsp;&nbsp;&nbsp;
                	</c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="WZY_foot">
    </div>
</div>
</body>
</html>
