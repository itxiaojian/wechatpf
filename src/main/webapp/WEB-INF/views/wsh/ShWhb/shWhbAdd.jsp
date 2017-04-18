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
	href="<%=path%>/resources/js/wsh/shwhb/pc_edit.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/home.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/index.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/edit.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/activity.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/uploadify.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/fill.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/jquery-1.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/jquery-ui-1.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/bootstrap.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/jquery-ui-1.8.22.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/filter.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/global.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/wsh/shwhb/home.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/wsh/shwhb/whbjs.js"></script>
<script>
jQuery(function($) {
	$("#add_page").click(function() {
		$("#divHidden").show();
	});

	$("#cancel").click(function() {
		$("#divHidden").hide();
	});
	
});
	</script>
</head>

<body>
	<iframe style="display: none;"></iframe>
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
	<form id="context-data-form" class="none">
		<input name="hbid" value="${map.id }" id="hbid" type="hidden">
	</form>
	<div id="header-management"></div>
	<div class="container">
		<div class="row home-container">
			<div class="span2"></div>
			<div class="span12">
				<div class="right-content">

					<!--模板不同edit_mobile不同-->

					<h3 class="bread-crumb">
						
	<a href="<%=path%>/wsh/ShWhb/toCreatWhb">微海报</a>
						<span class="gt"> &gt; </span> ${poster_name}
					</h3>
					<section class="container-fluid border-1scbcbcb">
						<section class="row-fluid">
							<div class="span2">
								<img
									src="${photo_route}"
									class="img_size">
							</div>
							<div class="span10 p15">
								<p class="lh30">
									<strong> ${poster_name} </strong>
								</p>
								<p class="lh30">背景音乐：
								<c:if test="${music_name==''}">未选择音乐</c:if>
								${music_name}</p>
								<a class="edit-to float-right"
									href="<%=path%>/wsh/ShWhb/toUpdateWhbZt?id=${map.id}">
									<img
									src="<%=path%>/resources/js/wsh/shwhb/icon-edit.png">
									编辑
								</a>
							</div>
						</section>
					</section>
					<section id="vue_edit" class="vue_edit">
						<!--v-if-start-->
						<!--v-if-end-->
						<!--v-if-start-->
						<!--v-if-end-->
						<!--v-if-start-->
						<div id="divHidden" style="display:none;">
						<section class="add_page_sections">
							<section class="add_section_box">
								<section>
									<span>请选择页面模板</span> <i id="cancel" class="add_cancel iconfontEdit"><img
											style="width: 25px; height: 25px;"
											src="<%=path%>/resources/js/wsh/shwhb/cancel.png"></i>
								</section>
								<section class="add_templates_box">
									<section class="add_crosswise">
										<section class="add_templates"
											data-poster_template_section_id="27">
											<input name="click_bgtp1" value="<%=path%>/resources/js/wsh/shwhb/cover_section1.jpg" id="click_bgtp1" type="hidden">
											<img id="photo1"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section1.jpg" onclick="xzmb(1);">
											<p>单图片</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel1"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="28">
											<input name="click_bgtp2" value="<%=path%>/resources/js/wsh/shwhb/gn_section2_bg.jpg" id="click_bgtp2" type="hidden">
											<img id="photo2"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section2.jpg" onclick="xzmb(2);">
											<p>上图下文白底</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel2"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="29">
											<input name="click_bgtp3" value="<%=path%>/resources/js/wsh/shwhb/gn_section2_bg.jpg" id="click_bgtp3" type="hidden">
											<img id="photo3"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section3.jpg" onclick="xzmb(3);">
											<p>上图下文红底</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel3"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="30">
											<input name="click_bgtp4" value="<%=path%>/resources/js/wsh/shwhb/gn_section4_bg.jpg" id="click_bgtp4" type="hidden">
											<img id="photo4"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section4.jpg" onclick="xzmb(4);">
											<p>中部文字</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel4"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="31">
											<input name="click_bgtp5" value="<%=path%>/resources/js/wsh/shwhb/gn_section5_bg.jpg" id="click_bgtp5" type="hidden">
											<img id="photo5"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section5.jpg" onclick="xzmb(5);">
											<p>暗底白字</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel5"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="32">
											<input name="click_bgtp6" value="<%=path%>/resources/js/wsh/shwhb/gn_section6_bg.jpg" id="click_bgtp6" type="hidden">
											<img id="photo6"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section6.jpg" onclick="xzmb(6);">
											<p>文字封面</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel6"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="33">
											<input name="click_bgtp7" value="<%=path%>/resources/js/wsh/shwhb/gn_section7_bg.jpg" id="click_bgtp7" type="hidden">
											<img id="photo7"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section7.jpg" onclick="xzmb(7);">
											<p>黑板报</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel7"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="34">
											<input name="click_bgtp8" value="<%=path%>/resources/js/wsh/shwhb/gn_section8_bg.jpg" id="click_bgtp8" type="hidden">
											<img id="photo8"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section8.jpg" onclick="xzmb(8);">
											<p>白底暗字</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel8"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="35">
											<input name="click_bgtp9" value="<%=path%>/resources/js/wsh/shwhb/gn_section9_bg.jpg" id="click_bgtp9" type="hidden">
											<img id="photo9"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section9.jpg" onclick="xzmb(9);">
											<p>白底黑板报</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel9"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="37">
											<input name="click_bgtp10" value="<%=path%>/resources/js/wsh/shwhb/gn_section11_bg.jpg" id="click_bgtp10" type="hidden">
											<img id="photo10"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section11.jpg" onclick="xzmb(10);">
											<p>靠下白底黑字</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel10"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="38">
											<input name="click_bgtp11" value="<%=path%>/resources/js/wsh/shwhb/gn_section12_bg.jpg" id="click_bgtp11" type="hidden">
											<img id="photo11"
												src="<%=path%>/resources/js/wsh/shwhb/cover_section12.jpg" onclick="xzmb(11);">
											<p>靠下斜白底黑字</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel11"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<section class="add_templates"
											data-poster_template_section_id="39">
											<input name="click_bgtp12" value="<%=path%>/resources/js/wsh/shwhb/gn_section13_bg.jpg" id="click_bgtp12" type="hidden">
											<img
												src="<%=path%>/resources/js/wsh/shwhb/cover_section13.jpg" onclick="xzmb(12);">
											<p>分享页面</p>
											<i class="add_sure iconfontEdit"><img
													id="click_cancel12"
													style="width: 32px; height: 32px; display: none;"
													src="<%=path%>/resources/js/wsh/shwhb/confirm.png" /></i>
										</section>
										<!--v-repeat-->
									</section>
								</section>
								<a class="btn btn-success add_choose" onclick="addModel();">确定</a>
							</section>
						</section>
						</div>
						<!--v-if-end-->
						<section class="edit_block" id="edit_block">
							<section class="page_choose" id="page_choose">
								<!--v-repeat-->
								<div class="add_page" id="add_page">+</div>
							</section>
							<!--v-repeat-->
							<!--v-if-start-->
							<section class="notify_create" id="notifyPage">点击上方加号添加新页面</section>
							<!--v-if-end-->
						</section>
						<section class="phone" id="phone">
							<section class="phone_ac">
								<span>页面预览</span> <a class="a_preview" id="preview">
<!-- 								 <i -->
<!-- 									class="iconfontPhone icon_phone"><img -->
<!-- 											style="width: 32px; height: 32px;" -->
<%-- 											src="<%=path%>/resources/js/wsh/shwhb/mobile.png"></i> <span id="preview_text">手机预览海报</span> --%>
								</a>
							</section>
							<section id="qrcode" class="qrcode">
								<div id="pre_qrcode"></div>
							</section>
							<!--v-if-start-->
							<section class="section-drag" id="xgyl"></section>
							<!--v-if-end-->
							<!--v-repeat-->
							<a class="a_publish" id="a_publish" onclick="hbfb('${map.id}','<%=path%>/wsh/ShWhb/toCreatWhb');">发布</a>
						</section>
					</section>
					<!-- 海报id -->
					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/shwhb/jquery.js"></script>
					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/shwhb/ajax.js"></script>
					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/shwhb/qrcode.js"></script>
<!-- 					<script type="text/javascript" -->
<%-- 						src="<%=path%>/resources/js/wsh/shwhb/vue.js"></script> --%>
<!-- 					<script type="text/javascript" -->
<%-- 						src="<%=path%>/resources/js/wsh/shwhb/vue_edit.js"></script> --%>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	
</script>
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
	<script type="text/javascript" charset="UTF-8">
		jQuery(function($) {
			function showUpdateDialog() {
				var is_show = 0;
				if (is_show) {
					$('.version-modal').modal({
						backdrop : 'static',
						keyboard : false
					});
					$('.version-modal').modal('show');
				}
				$('#sure')
						.click(
								function() {
									$
											.ajax({
												type : 'post',
												data : {
													"publish_date" : $(
															'.version-publish-date')
															.attr("data-time")
												},
												dataType : 'json',
												url : "http://weixiao.qq.com/home/14506/index/set_version",
												success : function(data) {
													$('.version-modal').modal(
															'hide');
												}
											});
								});
			}

			var $qrcode = $('#qrcode_dialog');
			var $close = $('.qrcode-close');
			// 获取url参数
			function getUrlParam(name) {
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
				var r = window.location.search.substr(1).match(reg);
				if (r != null)
					return r[2];// unescape(r[2]);
				return null;
			}
			if (getUrlParam('qrcode')) {
				$qrcode.removeClass('hide');
				$close.click(function() {
					$qrcode.addClass('hide');
					showUpdateDialog();
				});
			} else {
				showUpdateDialog();
			}
		});
	</script>
	<!-- end -->
</body>
</html>