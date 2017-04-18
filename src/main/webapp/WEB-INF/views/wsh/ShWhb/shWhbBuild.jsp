<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<%
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
	String value = formatter.format(currentTime); //将日期时间格式化
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
<script type="text/javascript">
</script>

<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/jquery-ui-custom.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/core.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/home.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/edit.css">
<script type="text/javascript"
	src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/fill.css" />

<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wbm/activity.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/js/wsh/shwhb/uploadify.css" />

<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/shwhb/jquery-ui-1.8.22.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/filter.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wbm/jquery.uploadify.min.js"></script>
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
<script type="text/javascript"
	src="<%=path%>/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    var i=0;
    function toAddImg(){
    	if(i>=0){
    		if ($("#file1").val().length > 0) {
    			$("#file1").val("");
    		}
    	}
    	$("#file1").click();
    }
    function selectFile(){
    	if ($("#file1").val().length > 0) {
            ajaxFileUpload();
            i++;
        } else {
            alert("请选择图片");
        }
    }
    function ajaxFileUpload() {
        $.ajaxFileUpload({
            url : '<%=path%>/wsh/upload/tempimg', //用于文件上传的服务器端请求地址
			secureuri : false, //一般设置为false
			fileElementId : 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
			type : 'post',
			dataType : 'HTML', //返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{

				$("#poster_template_cover").attr("src", data);
				$("#fileName").val(data);
				$("#file1").val("");
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						alert(data.error);
					} else {
						alert(data.msg);
					}
				}
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				alert(e);
			}
		})
		return false;
	}
</script>

<script type="text/javascript">
	jQuery(function($) {
		//背景音乐打开
		$(".switch").click(function() {
			$("#divHidden").show();
		});
         //背景音乐框的叉的取消按钮
		$(".music_cancel").click(function() {
			$("#divHidden").hide();
		});
         //添加背景音乐的保存按钮
		$("#music_save").click(function() {
			var radioes = $('input:radio:checked').val();
			if (radioes == '' || radioes == null) {
				alert("请选择一个音乐！");
				return false;
			}
			$("#divHidden").hide();
			var a = $('input:radio:checked').next('span').text();
			$('#music_name_hidden').val(a);
			$('#music_name').text(a);
			$('#music_select').show();
			$('#switch_img').show();
			$('.switch').hide();
		});
         //选择音乐 的链接
		$("#music_select").click(function() {
			$("#divHidden").show();
		});
		//背景开关的显示与消失
		$("#switch_img").click(function(){
			$("#switch_img").hide();
			$(".switch").show();
			$('input:radio:checked').val('');
			$('#music_select').hide();
			var small= $('#music_id');
		    for (var i = 0; i < small.length; i++) {
		            small[i].checked = false;
		    }
		    $('#music_name').text('');
		});
	});

	function valite() {
		var name = $('#poster_name').val();
		if (name == '' | name == null) {
			alert('请输入海报名称!');
			return false;
		}
		/* var a = $('#file1').val();
		if(a=='' || a==null){
		alert('请选择一个图片!');
		return false;
		}  */
		$('#publish-form').submit();
	}
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
	<div class="container">
		<div class="row home-container">
			<div class="span2"></div>
			<div class="span12">
				<div class="right-content">

					<h3>创建微海报</h3>
					<section>
						<header class="inf_title">微海报基本信息</header>
						<form id="publish-form" action="<%=path%>/wsh/ShWhb/toAddWhb"
							method="post">
							<!--v-if-start-->
							<!--v-if-end-->

							<!-- music_alert_box -->
							<div id="divHidden" style="display: none">
								<section class="music_choose" height="70% " v-transition>
									<section class="music_box">
										<section>
											<span class="music_title">选择背景音乐</span> <i
												class="music_cancel iconfont"
												v-on="click: hide_choose_music"> <img id=""
												style="width: 32px; height: 32px;"
												src="<%=path%>/resources/js/wsh/shwhb/cancel.png" />
											</i>
											<c:if test="${empty list}">
												<p>没有添加背景音乐</p>
											</c:if>
										</section>
										<c:forEach var="data" items="${list}" varStatus="obj">
											<section>
												<div>
													<label for="music_${data.id} "> <input
														id="music_id" type="radio" name="music"
														value="${data.fjlj}" /> <span>${data.fjmc}</span><span
														class="listen_music"> </span>
													</label>
												</div>
											</section>
										</c:forEach>
										<input type="button" id="music_save" value="保存"
											style="width: 45px; height: 24px; align: center; left-margin: 99px;"
											class="btn btn-success" />
									</section>
								</section>
							</div>
							<!-- music_alert_box -->

							<label for="poster_name"> <span class="notify_import">*</span>
								<span class="poster_name_notify">海报名称： </span> <input
								id="poster_name" class="poster_name" name="poster_name" value=""
								style="overfolw: visible; height: 25px; width: 140px;"
								type="text"> <!--v-if-start--> <!--v-if-end-->
							</label> <br />
							<div class="poster_music">
								背景音乐： <span class="switch" style="display:marker"> <span class="switch_st"></span></span>
								&nbsp;&nbsp;&nbsp;<img id="switch_img" style="width: 6%; height: 13%; display: none;cursor:pointer;"
									src="<%=path%>/resources/js/wsh/shwhb/switch.png" />
								&nbsp;&nbsp;&nbsp; <span id="music_name"
									style="font-size: 15px; opacity: 0.5;"></span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
									style="font-size: 13px; cursor: pointer; text-decoration: underline; display: none; color: #0866AE"
									id="music_select">选择音乐</span>
								<!--v-if-start-->
								<!--v-if-end-->
								<!--v-if-start-->
								<!--v-if-end-->
							</div>
							<br />
							<!-- 							<div class="upload-box bg-green"> -->
							<!-- 								<ul id="upload-img-list" -->
							<!-- 									class="upload-img-list group border-box" -->
							<!-- 									style="position: relative;"> -->
							<!-- 									<li> -->
							<!-- 										<div id="add-product-image" class="btn-add-img" -->
							<!-- 											style="z-index: 1;" onclick="toAddImg();"> -->
							<!-- 											<span class="notify_import">*</span>  -->
							<!-- 											<img id="img1" -->
							<!-- 												alt="海报封面:" src="" align="center" -->
							<!-- 												style="height: 100%; text-align: center;" /> -->
							<!-- 										</div> -->
							<!-- 									</li> -->
							<!-- 									<div id="uploadDiv" class="controls upload" -->
							<!-- 										style="position: absolute; top: 25px; left: 35px; width: 163px; height: 161px; overflow: hidden; z-index: 0;"> -->
							<!-- 										<input id="file1" name="file" type="file"  -->
							<!-- 											style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" -->
							<!-- 											accept="image/*" capture="camera" onchange="selectFile();"> -->
							<!-- 									</div> -->
							<!-- 								</ul> -->
							<!-- 							</div> -->

							<div class="poster_img">
								<div>
									<span class="poster_cover">海报封面：</span> <img
										id="poster_template_cover" class="poster_template_cover"
										src="${bgtp }" />
									<div id="uploader" class="uploadify"
										style="height: 30px; width: 80px;">
										<div id="uploadify-button">
											<span class="uploadify-button-text" onclick="file1.click()"
												style="cursor: pointer;">上传图片</span>
											<div style="display: none;">
												<input id="file1" name="file" type="file"
													font-size: 999px; opacity: 0; position:
													absolute; top: 0px; left: 0px; width: 100%; height: 100%;" 
		 											accept="image/*"
													capture="camera" onchange="selectFile();">
											</div>
										</div>
									</div>
								</div>

							</div>
							<br /> <br />
							<div class="poster_input">
								<input type="button" value="创建海报" id="create_submit"
									onClick="valite()" class="btn btn-success create" /> <input
									name="music_id" value="" type="hidden"> <input
									name="music_name" id="music_name_hidden" value="" type="hidden">
								<input name="fileName" id="fileName" value="${bgtp }" type="hidden">

							</div>
						</form>

					</section>

					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/shwhb/vue.min.js"></script>
					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/shwhb/vue_fill.js"></script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>