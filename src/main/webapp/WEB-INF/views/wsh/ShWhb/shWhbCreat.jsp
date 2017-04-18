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
<title>微海报</title>
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/bootstrap-custom.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/jquery-ui-custom.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/core.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/home.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/fill.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/edit.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/index.css">

<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/jquery-1.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/bootstrap.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/jquery-ui-1.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/filter.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/global.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/home.js"></script>
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
<script type="text/javascript">
	jQuery(function($) {
		$(".create_poster").click(function() {
			$("#divHidden").show();
		});

		$(".cancel").click(function() {
			$("#divHidden").hide();
		});
		
	});
</script>

<script type="text/javascript">
	function xzmb(num){
		var flag = true;
		var size=$("#mbsize").val();
		var div = document.getElementById("click_cancel"+num);
		if (flag) {
			for(var i=1;i<=size;i++){
				if(i==num){
					div.style.display = "block";
				}else{
					var divs = document.getElementById("click_cancel"+i);
					divs.style.display = "none";
				}
			}
		} else {
			div.style.display = "none";
		}
		flag = !flag;
	}
	   function deleteTem(id){
			   if(confirm("您确定要删除吗？")){
				   $.ajax({
						cache : true,
						type : "POST",
						url : "<%=path%>/wsh/ShWhb/deleteMb",
						data : {
							id:$('.myform'+id).val()},
						async : false,
						error : function(request) {
							alert("网络错误,删除错误!");
						},
						success : function(data) {
								window.self.location="<%=path%>/wsh/ShWhb/toCreatWhb";
								 alert("删除成功!");
						}
					});
				}
	   }
	   function conMb(){
		   var size=$("#mbsize").val();
		   var num=0;
		   var bgtp="";
		   for(var i=1;i<=size;i++){
			   var div= document.getElementById("click_cancel"+i);
			   var a=div.style.display;
			   if(a=='block'){
				   num=num+1;
				   bgtp=$("#bgtp"+i).val()
			   }
		   }
			if(num==0){
				alert('请选择模板!');
				return false;
			}
			window.self.location="<%=path%>/wsh/ShWhb/toSaveWhb?bgtp="+bgtp;
			//$('#formId').submit();
	   }
	   
	   function toUpdate(id){
		   if(confirm("编辑会清空之前的页面信息，确定进行编辑？")){
			   window.self.location="<%=path%>/wsh/ShWhb/toUpdateWhb?hbid="+id;
		   }
	   }
</script>
</head>

<body>
	<iframe style="display: none;"></iframe>
	<div style="display:none;">
	<ul class="tab-menu tab" id="tabMenuID_">
		<li class="tab-selected" title="微海报">
		<a href="<%=path%>/wsh/ShWhb/toCreatWhb" target="content" onfocus="this.blur()"><span>微海报</span></a>
		</li>
	</ul>
	</div>
<style type="text/css">
.WPA3-SELECT-PANEL {
	z-index: 2147483647;
	width: 463px;
	height: 292px;
	margin: 0;
	padding: 0;
	border: 1px solid #d4d4d4;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 15px #d4d4d4;
}

.WPA3-SELECT-PANEL * {
	position: static;
	z-index: auto;
	top: auto;
	left: auto;
	right: auto;
	bottom: auto;
	width: auto;
	height: auto;
	max-height: auto;
	max-width: auto;
	min-height: 0;
	min-width: 0;
	margin: 0;
	padding: 0;
	border: 0;
	clear: none;
	clip: auto;
	background: transparent;
	color: #333;
	cursor: auto;
	direction: ltr;
	filter:;
	float: none;
	font: normal normal normal 12px "Helvetica Neue", Arial, sans-serif;
	line-height: 16px;
	letter-spacing: normal;
	list-style: none;
	marks: none;
	overflow: visible;
	page: auto;
	quotes: none;
	-o-set-link-source: none;
	size: auto;
	text-align: left;
	text-decoration: none;
	text-indent: 0;
	text-overflow: clip;
	text-shadow: none;
	text-transform: none;
	vertical-align: baseline;
	visibility: visible;
	white-space: normal;
	word-spacing: normal;
	word-wrap: normal;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	-ms-box-shadow: none;
	-o-box-shadow: none;
	box-shadow: none;
	-webkit-border-radius: 0;
	-moz-border-radius: 0;
	-ms-border-radius: 0;
	-o-border-radius: 0;
	border-radius: 0;
	-webkit-opacity: 1;
	-moz-opacity: 1;
	-ms-opacity: 1;
	-o-opacity: 1;
	opacity: 1;
	-webkit-outline: 0;
	-moz-outline: 0;
	-ms-outline: 0;
	-o-outline: 0;
	outline: 0;
	-webkit-text-size-adjust: none;
	font-family: Microsoft YaHei, Simsun;
}

.WPA3-SELECT-PANEL a {
	cursor: auto;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-TOP {
	height: 25px;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-CLOSE {
	float: right;
	display: block;
	width: 47px;
	height: 25px;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-CLOSE:hover {
	background-position: 0 -25px;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-MAIN {
	padding: 23px 20px 45px;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-GUIDE {
	margin-bottom: 42px;
	font-family: "Microsoft Yahei";
	font-size: 16px;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-SELECTS {
	width: 246px;
	height: 111px;
	margin: 0 auto;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-CHAT {
	float: right;
	display: block;
	width: 88px;
	height: 111px;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-CHAT:hover {
	background-position: -88px -80px;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-AIO-CHAT {
	float: left;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-QQ {
	display: block;
	width: 76px;
	height: 76px;
	margin: 6px;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-QQ-ANONY {
	background-position: -130px 0;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-LABEL {
	display: block;
	padding-top: 10px;
	color: #00a2e6;
	text-align: center;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-BOTTOM {
	padding: 0 20px;
	text-align: right;
}

.WPA3-SELECT-PANEL .WPA3-SELECT-PANEL-INSTALL {
	color: #8e8e8e;
}
</style>
	<style type="text/css">
.WPA3-CONFIRM {
	z-index: 2147483647;
	width: 285px;
	height: 141px;
	margin: 0;
}

.WPA3-CONFIRM {
}

.WPA3-CONFIRM * {
	position: static;
	z-index: auto;
	top: auto;
	left: auto;
	right: auto;
	bottom: auto;
	width: auto;
	height: auto;
	max-height: auto;
	max-width: auto;
	min-height: 0;
	min-width: 0;
	margin: 0;
	padding: 0;
	border: 0;
	clear: none;
	clip: auto;
	background: transparent;
	color: #333;
	cursor: auto;
	direction: ltr;
	filter:;
	float: none;
	font: normal normal normal 12px "Helvetica Neue", Arial, sans-serif;
	line-height: 16px;
	letter-spacing: normal;
	list-style: none;
	marks: none;
	overflow: visible;
	page: auto;
	quotes: none;
	-o-set-link-source: none;
	size: auto;
	text-align: left;
	text-decoration: none;
	text-indent: 0;
	text-overflow: clip;
	text-shadow: none;
	text-transform: none;
	vertical-align: baseline;
	visibility: visible;
	white-space: normal;
	word-spacing: normal;
	word-wrap: normal;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	-ms-box-shadow: none;
	-o-box-shadow: none;
	box-shadow: none;
	-webkit-border-radius: 0;
	-moz-border-radius: 0;
	-ms-border-radius: 0;
	-o-border-radius: 0;
	border-radius: 0;
	-webkit-opacity: 1;
	-moz-opacity: 1;
	-ms-opacity: 1;
	-o-opacity: 1;
	opacity: 1;
	-webkit-outline: 0;
	-moz-outline: 0;
	-ms-outline: 0;
	-o-outline: 0;
	outline: 0;
	-webkit-text-size-adjust: none;
}

.WPA3-CONFIRM * {
	font-family: Microsoft YaHei, Simsun;
}

.WPA3-CONFIRM .WPA3-CONFIRM-TITLE {
	height: 40px;
	margin: 0;
	padding: 0;
	line-height: 40px;
	color: #2b6089;
	font-weight: normal;
	font-size: 14px;
	text-indent: 80px;
}

.WPA3-CONFIRM .WPA3-CONFIRM-CONTENT {
	height: 55px;
	margin: 0;
	line-height: 55px;
	color: #353535;
	font-size: 14px;
	text-indent: 29px;
}

.WPA3-CONFIRM .WPA3-CONFIRM-PANEL {
	height: 30px;
	margin: 0;
	padding-right: 16px;
	text-align: right;
}

.WPA3-CONFIRM .WPA3-CONFIRM-BUTTON {
	position: relative;
	display: inline-block !important;
	display: inline;
	zoom: 1;
	width: 99px;
	height: 30px;
	margin-left: 10px;
	line-height: 30px;
	color: #000;
	text-decoration: none;
	font-size: 12px;
	text-align: center;
}

.WPA3-CONFIRM .WPA3-CONFIRM-BUTTON-FOCUS {
	width: 78px;
}

.WPA3-CONFIRM .WPA3-CONFIRM-BUTTON .WPA3-CONFIRM-BUTTON-TEXT {
	line-height: 30px;
	text-align: center;
	cursor: pointer;
}

.WPA3-CONFIRM-CLOSE {
	position: absolute;
	top: 7px;
	right: 7px;
	width: 10px;
	height: 10px;
	cursor: pointer;
}
</style>

	<div class="container">
	<input type="hidden" id="mbsize" name="mbsize" value="${size }"/>
		<div class="row home-container">
			<div class="span2"></div>
			<div class="span12">
				<div class="right-content">
					<div id="poster_model">
						<!--v-if-start-->
						<!--v-if-end-->
						<!--v-if-start-->
						<!--v-if-end-->
						<!-- <section class="page_tips error" v-if="is_show_notify"
							v-transition>
							<div class="inner" v-model="notify_text"></div>
						</section>
						<section class="page_tips error success" v-if="is_show_success"
							v-transition>
							<div class="inner" v-model="notify_text"></div>
						</section> -->
						<form action="" method ="post" id="formId">
						<div style="display: none" id="divHidden">
							<section id="poster_choose" class="poster_choose"
								style="height: 100%" v-transition>
								<section class="tem_box">
									<section>
										<span class="tem_title">请选择海报模板</span> <i
											class="cancel iconfont" v-on="click: hide_choose_templates"><img
											style="width: 32px; height: 32px;"
											src="<%=path%>/resources/js/wsh/shwhb/cancel.png">
											</i>
									</section>
									<c:if test="${empty listMb}">没有模板！</c:if>
									<section class="tems">
										<c:forEach var="data" items="${listMb}" varStatus="status">

											<div class="tem" data-template_id="${data.id}"
												id="photo${status.count }" onclick="xzmb(${status.count });">
												<img class="photo" src='${data.mbtp}' />
												<p class="tem_name">${data.mbzt}</p>
												<input type="hidden" id="bgtp${status.count }" name="bgtp${status.count }" value="${data.mbtp}" />
												<i class=" iconfont"><img
													id="click_cancel${status.count}"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
											</div>
										</c:forEach>
									</section>
									<input class="btn btn-success add-apply-btn tem_choose"
									style= "top:-25px; position:relative;width:95px;height:27px"
										 type="button" value="确定" onClick="conMb()"/>
								</section>
							</section>
						</div>
						</form>
						<!--v-if-start-->
						<!--v-if-end-->
						<section class="create_title">
							<h3 class="title_name">微海报</h3>
							<a class="btn btn-success add-apply-btn create_poster">+创建海报</a>
						</section>
						<!--v-if-start-->
						<!--v-if-end-->
						<!--v-if-start-->
						<!--v-if-end-->
						<!--v-if-start-->
						<c:if test="${empty list}">
						</c:if>
						<c:if test="${!empty list}">
							<section class="unpublished">
								<h1>未发布</h1>
								<section class="unpublished_list">
									<c:forEach var="data" items="${list}" varStatus="obj">
										<div class="unpublished_lists">
											<a href="javascript:;" onclick="toUpdate(${data.id});"><img
												src='${data.mbtp}' data-poster_id="12536"></a>
											<section class="lists_inf">
												<p style="padding-top: 0px; padding-bottom: 0px;">${data.mbzt}</p>
												<div>
													<span class="time">${data.cjsj}</span> <i
														class="iconfont delete" data-poster_id="12536"
														onClick="deleteTem(${data.id})">
														<img style="width: 15px; height: 15px;"
														src="<%=path%>/resources/js/wsh/shwhb/delete.png"></i> 
														<a href="javascript:;" onclick="toUpdate(${data.id});"><i
														class="iconfont edit"><img style="width: 15px; height: 15px;"
														src="<%=path%>/resources/js/wsh/shwhb/modify.png"></i> </a>
														<input value="${data.id}" class="myform${data.id}" name="id" style="display:none;"/>
												</div>
											</section>
										</div>
									</c:forEach>
									<!--v-repeat-->
								</section>
							</section>
						</c:if>
						<!--v-if-end-->
						<!--v-if-start-->
						<c:if test="${empty map}">
							<section class="published">
								<h1>已发布</h1>
								<!--v-if-start-->
								<section class="published_none">
									<img src="<%=path%>/resources/js/wsh/shwhb/info.png">
									<h2 class="none_title">还没有发布微海报</h2>
									<h2 class="none_title1">赶快发布微海报，和大家一起分享和传播吧！</h2>
								</section>
								<!--v-if-end-->
								<section class="published_list">
									<!--v-repeat-->
								</section>
							</section>
						</c:if>
						<!--v-if-end-->
						<c:if test="${! empty map}">
							<section class="published">
								<h1>已发布</h1>
								<section class="published_list">
									<!--v-repeat-->
									<c:forEach var="data" items="${map}" varStatus="obj">
										<div class="unpublished_lists" style="cursor: default;">
											<img src='${data.mbtp}' data-poster_id="12536">
											<section class="lists_inf">
												<p>${data.mbzt}</p>
												<div>
													<span class="time">${data.cjsj}</span> <i
														class="iconfont delete" data-poster_id="12536"
														onClick="deleteTem('${data.id}')">
														<img style="width: 15px; height: 15px;"
														src="<%=path%>/resources/js/wsh/shwhb/delete.png"></i> 
														<input value="${data.id}" class="myform${data.id}" name="id" style="display:none;"/>
												</div>
											</section>
										</div>
									</c:forEach>
								</section>
							</section>
						</c:if>
					</div>
<!-- 					<script type="text/javascript" -->
<%-- 						src="<%=path%>/resources/js/wsh/shwhb/qrcode.js"></script> --%>
<!-- 					<script type="text/javascript" -->
<%-- 						src="<%=path%>/resources/js/wsh/shwhb/vue.js"></script> --%>
<!-- 					<script type="text/javascript" -->
<%-- 						src="<%=path%>/resources/js/wsh/shwhb/vue_index.js"></script> --%>
				</div>
			</div>
		</div>
	</div>

	<style>
.version-modal {
	padding-bottom: 20px;
}

.version-modal .version-model-body {
	padding: 20px;
	margin-bottom: 20px;
	max-height: 100%;
}

.version-modal .version-model-body ul,.version-modal .version-model-body ol
	{
	margin: 14px 0 14px 0;
	padding: 0 0 0 40px;
}

.version-modal .version-model-body ul,.version-modal .version-model-body ul li
	{
	list-style: disc;
}

.version-modal .version-model-body ol,.version-modal .version-model-body ol li
	{
	list-style: decimal;
}

.version-header {
	height: 70px;
	text-align: center;
	background-color: #f3f3f3;
}

.version-publish-date {
	color: #ac7b53;
	margin-bottom: 10px;
}

.version-sure-btn {
	margin-left: 40%;
	margin-right: 40%;
	background-color: #ff900c;
	color: #fff;
	font-size: 13px;
	padding: 10px;
	border-radius: 5px;
	cursor: pointer;
}
</style>
</body>
</html>