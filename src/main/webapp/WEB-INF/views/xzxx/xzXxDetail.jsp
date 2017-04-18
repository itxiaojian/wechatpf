<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>查看信箱</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon"
	href="<%=path%>/resources/css/xzxx/favicon.ico">
<link href="<%=path%>/resources/css/xzxx/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="<%=path%>/resources/css/xzxx/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link href="<%=path%>/resources/css/xzxx/custom.css" rel="stylesheet">
<link href="<%=path%>/resources/css/xzxx/animate.css" rel="stylesheet">
<link href="<%=path%>/resources/css/xzxx/style.css?v=4.1.0"
	rel="stylesheet">

</head>
<style type="text/css">
.font-noraml{font-size:14px;font-weight:bold;}
.fa {font-size:180%;}
.mail-body {padding: 0px 20px 20px 20px;}

</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
<!-- 			<div class="col-sm-3"> -->
<!-- 			</div> -->
			<div class="col-sm-9 animated fadeInRight">
			<c:forEach var="map" items="${map}" varStatus="obj">
				<div class="mail-box-header">
					<h2 class="btn btn-block btn-primary compose-mail">信件详情</h2>
					<div class="mail-tools tooltip-demo m-t-md">

						<c:if test="${map.clzt=='1' }">
							<span class="font-noraml">处理状态： </span>审批中<br>
						</c:if>
						<c:if test="${map.clzt=='2' }">
							<span class="font-noraml">处理状态： </span>审批驳回<br>
						</c:if>
						<c:if test="${map.clzt=='3' }">
							<span class="font-noraml">处理状态： </span>审批通过<br>
						</c:if>
						<c:if test="${map.clzt=='4' }">
							<span class="font-noraml">处理状态： </span>未回复<br>
						</c:if>
						<c:if test="${map.clzt=='5' }">
							<span class="font-noraml">处理状态： </span>未评价<br>
						</c:if>
						<c:if test="${map.clzt=='6' }">
							<span class="font-noraml">处理状态： </span>已结束<br>
						</c:if>
						<c:if test="${map.clzt=='7' }">
							<span class="font-noraml">处理状态： </span>禁用<br>
						</c:if>
							<span class="font-noraml">写信时间： </span>${map.xxsj }<br>
							<span class="font-noraml">信件主题： </span>${map.xjbt }<br>
							<span class="font-noraml"><img style="width:100%;" src="<%=path%>/resources/js/xzxx/fl.png"></span><br>
							<span class="font-noraml">信件内容： </span>${map.xjnr}<br>

					</div>
				</div>
				<div class="mail-box">
				
					<div class="mail-body">
							<h2 class="btn btn-block btn-primary compose-mail">处理详情</h2>

							<div class="mail-tools tooltip-demo m-t-md">
							<span class="font-noraml">处理时间： </span>${map.clsj }<br>
							<span class="font-noraml">审核时间： </span>${map.shsj }<br>
							<span class="font-noraml"><img style="width:100%;" src="<%=path%>/resources/js/xzxx/fl.png"></span><br>
							<span class="font-noraml">处理结果： </span>${map.cljg }<br>
							<span class="font-noraml"><img style="width:100%;" src="<%=path%>/resources/js/xzxx/fl.png"></span><br>
							<c:choose>
								<c:when test="${map.myd=='1'}">
									<span class="font-noraml">评价：</span> 非常满意&nbsp;&nbsp;&nbsp;&nbsp;${map.pj}<br>
								</c:when>
								<c:when test="${map.myd=='1'}">
									<span class="font-noraml">评价： </span>满意&nbsp;&nbsp;&nbsp;&nbsp;${map.pj}<br>
								</c:when>
								<c:when test="${map.myd=='3'}">
									<span class="font-noraml">评价： </span>不满意&nbsp;&nbsp;&nbsp;&nbsp;${map.pj}<br>
								</c:when>
								<c:otherwise>
									<span class="font-noraml">评价： </span>${map.pj}<br>
								</c:otherwise>
							</c:choose>
							<c:if test="${map.clzt=='2' }">
								<span class="font-noraml">退回理由： </span>${map.thly }<br>
							</c:if>
							<span class="font-noraml">备注： </span>${map.bz}<br>

					</div>
					</div>

				</div>
			</c:forEach>
				
			</div>
		</div>
	</div>
	<div class="gohome">
		<a class="animated bounceInUp"
			href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}&lx=${lx}&Currentpage=${Currentpage}"
			title="返回首页"><i class="fa fa-home"></i></a>
	</div>

	<!-- 全局js -->
	<script src="<%=path%>/resources/css/xzxx/js/jquery.min.js?v=2.1.4"></script>
	<script src="<%=path%>/resources/css/xzxx/js/bootstrap.min.js?v=3.3.6"></script>



	<!-- 自定义js -->
	<script src="<%=path%>/resources/css/xzxx/js/content.js?v=1.0.0"></script>


	<!-- iCheck -->
	<script src="<%=path%>/resources/css/xzxx/js/icheck.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.i-checks').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
			});
		});
	</script>

</body>

</html>
