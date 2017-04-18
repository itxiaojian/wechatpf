<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>

<html eiiebffcjbffiheggaebebgceaeccbia_b="1" bdgjjgagedbdaebhbjbcabcdgeeebecf_b="1" idceifdedfeiefjgfcjfbchejgbcbeid_b="1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>校车查询</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script><style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
<script type="text/javascript">
	function query(){
		var keyWord = $("#bt").val();
		if(keyWord == null || keyWord == ""){
			alert("请输入关键词查询!");
			return false;
		}else{
			$("#myForm").submit();
		}
	}
</script>
<style type="text/css">
	.style1{
		margin: 0px 5%;
		padding-top: 10px;
		padding-bottom: 5px;
	}
	.style2{
		text-align: center;
		margin: 10px 0px;
	}
	.style3{
		background:url("<%=path%>/resources/images/bg1.png") repeat-x;
		height: 30px;
		padding-top: 7px;
	}
	.style3 a{
		margin-right: 10%;
	}
	.style3 .selected{
		color: #37b0c9;
	}
	.style4{
		margin: 20px 5%;
	}
	.maring1{
		margin-bottom: 30px;
	}
	.style4 p{
		margin-bottom: 5px;
		font-size: 14px;
		color: #323e48;
	}
	.style4 .title{
		font-size: 16px;
	}
	.bg1{
		background-color: #37b0c9;
	}
	.font1{
		font-weight: bold;
		color: white;
		font-size: 16px;
	}
	.input1{
		width:60%;
		margin-left:11%;
		height: 30px;
	}
	.color1{
		color: #37b0c9;
	}
	#lianjie:hover{
	 text-decoration:none;
	}
	#lianjie1{
	 text-decoration:none;
	}
	#divs{
	border:5px solid #44ddee;height:80px;width:400px;margin:1px 0 0 1px;
	}
</style>
</head>

<body id="cardunion" class="mode_webapp2" style="background-color: white;">
<div class="bg1">
	<div class="style1">
		<div class="wwx_f_l" style="position: relative;top:6px;">
			<a id ="lianjie1" class="font1" href="<%=path%>/wsh/ShXcsk/toShxcskList">车牌号</a>
		</div>
		<form action="<%=path%>/wsh/ShXcsk/toShxcskList" id="myForm" class="wwx_f_l" style="width: 55%;margin-right: 10px;" method="post">
			<input type="text" class="input1" name="bt" id="bt" style="position: relative;top:2px;">
			<span  style="position: relative;top:2px;"><a class="font1" href='#' style="margin-left: 10px;" onclick="query();">查询</a></span>
			</form>
				<div class="wwx_clear"></div>
	</div>
</div>
<c:if test="${!empty list}"> 
<div>
	<div class="style4">
	<c:forEach var="data" items="${list}" varStatus="obj">
		<div class="maring1">
				<div class="wwx_f_l" style="width:60%" id='divs'>
					<p class="title">
						<a id = 'lianjie' href="<%=path%>/wsh/ShXcsk/toShxcskDetail?cph=${data.cph }">
						<span  style="color: red">出发站：${data.cfd} -----> 目的地：${data.mdd}</span></a>
					</p>
						<p style="margin-left: 28px;">时间：${data.cfsj }</p>
					    <p style="margin-left: 28px;">车牌号：${data.cph}</p>
				</div>
				<div class="wwx_clear"></div>
			</div>	
		</c:forEach>
		</div>
	<div class="style4" style="text-align: center;">
				<c:choose>
					<c:when test="${pages > 1}">
						<a href="<%=path%>/wsh/ShXcsk/toShxcskList?pages=${pages - 1}">上一页</a>
					</c:when>
					<c:otherwise>
						上一页
					</c:otherwise>
				</c:choose>
				第${pages}页
				<c:choose>
					<c:when test="${pages < count}">
						<a href="<%=path%>/wsh/ShXcsk/toShxcskList?pages=${pages + 1}">下一页</a>
					</c:when>
					<c:otherwise>
					下一页
				</c:otherwise>
				</c:choose>
				总共${count}页
			</div>
</div>
</c:if>
</body></html>