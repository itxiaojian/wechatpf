<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<html eiiebffcjbffiheggaebebgceaeccbia_b="1"
	bdgjjgagedbdaebhbjbcabcdgeeebecf_b="1"
	idceifdedfeiefjgfcjfbchejgbcbeid_b="1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>校车信息</title>
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/resources/css/stylesea.css" rel="stylesheet"
	type="text/css">
<script src="<%=path%>/libs/js/jquery.js" type="text/javascript"></script>
<script src="<%=path%>/resources/js/wsh/shscxk/bootstrap.js"
	type="text/javascript"></script>
<script src="<%=path%>/resources/js/wsh/shscxk/site.js" type="text/javascript"></script>
<script src="<%=path%>/resources/js/jquery/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
</head>
<body >
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="校车时刻">
	<a href="#" target="content" onfocus="this.blur()"><span>校车时刻</span></a>
	</li>
</ul>
</div>
	<header>
		<div class="headinfo">
		<c:if test="${!empty map}">
			<c:forEach var="listCph" items="${listCph}" varStatus="status">
				<span> <a id="a${status.index} " onclick="show()"
					href="<%=path%>/wsh/ShXcsk/toShxcskRoute1?cph=${listCph.cph}">${listCph.cph}
						<c:choose>
							<c:when test="${status.index==0}">
							|
						</c:when>
							<c:when test="${status.last==true} ">
							</c:when>
							<c:otherwise>|</c:otherwise>
						</c:choose>
				</a>
				</span>
			</c:forEach>
			</c:if>
			<c:if test="${empty map}">
			<span>
			<a id="a0 " href="#" onclick="show()">校车时刻</a>
			</span>
			</c:if>
			<div class="anniu" style="top:5%;" >
				<a style="float:right;width:40px;height:50px;" href="<%=path%>/wsh/zy/zhuye?openId=${openId}" >
			   <img style="width:70%" src="<%=path%>/resources/img/wfwzy.png" >
			   </a>
			      </div>
		</div>
		<c:if test="${empty map}">
		<div class="text">
			<p>校车时刻信息暂无...</p>
		</div>  
		</c:if>
		<c:if test="${!empty map}">
			<div class="busline">
			
					<c:forEach var="list" items="${map}" varStatus="status">
						<c:if test="${status.index==0 }">
				<div class="left">
							<span class="leftspan01">${list.cfd}</span>
							<span class="leftspan02">${list.cfsj}~${list.ddsj}</span>
						
				</div>
				<div class="zhongjian">
							<span class="leftspan01" style="text-align: center;color: blue;font-size: 20px;">${list.cph}</span>
			    </div>
				<div class="right">
							<span class="rightspan01">${list.mdd}</span>
							<span class="rightspan02">${list.cfsj}~${list.ddsj}</span>
						
				</div>
				</c:if>
					</c:forEach>
			</div>
		</c:if>
	</header>
	<c:if test="${!empty map}">
		<div class="container">
			<div class="buslineinfo">
				<c:forEach var="map" items="${map}" varStatus="status">
					<ul>
						<c:if test="${status.index == 0}">
							<li class="li_01"><img
								src="<%=path%>/resources/img/startup.png" />${map.cfd}</li>
						</c:if>
						<c:if test="${status.index != 0}">
							<c:choose>
								<c:when test="${status.last == true}">
									<li class="li_01"><img
										src="<%=path%>/resources/img/ended.png" />${map.mdd}</li>
								</c:when>
								<c:otherwise>
									<li class="li_01"><img
										src="<%=path%>/resources/img/luguo.png" />${map.mdd}</li>
								</c:otherwise>
							</c:choose>
						</c:if>
						<div class="yuanicon">${status.index+1}</div>
						<li class="li_02">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.cfsj}</li>
						<p style="display: none">40196</p>
					</ul>
				</c:forEach>
			</div>
		</div>
	</c:if>
</body>
</html>