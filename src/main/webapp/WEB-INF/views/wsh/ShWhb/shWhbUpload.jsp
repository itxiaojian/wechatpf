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
	(function(w) {
		w._config = {
			SYS_PATH : 'http://weixiao.qq.com'
		};

		w.get_config = function(name, default_value) {
			default_value = default_value ? default_value : null;
			return _config[name] ? _config[name] : default_value;
		};

		w.add_config = function(name, value) {
			_config[name] = value;
		}
	})(window);
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
<%-- <script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script> --%>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
 <script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload.js"></script>
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
    		$("#filename").val($("#file1").val());
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
				$("#img1").attr("src", data);
				if($('#type').val()==0){
					if(document.getElementById("imgDiv").style.display=="none"){
						document.getElementById("imgDiv").style.display="block";
					}else{
						document.getElementById("imgDiv").style.display="block";
					}
				}
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
		$("#music").click(function() {
			document.getElementById("photo").style.background="#eee none repeat scroll 0 0";
			document.getElementById("music").style.background="white none repeat scroll 0 0";
			document.getElementById("sclx").innerHTML="音乐上传";
			$("#name").val("");
		});

		$("#photo").click(function() {
			document.getElementById("music").style.background="#eee none repeat scroll 0 0";
			document.getElementById("photo").style.background="white none repeat scroll 0 0";
			document.getElementById("sclx").innerHTML="图片上传";
			$("#type").removeAttr("value");
			$("#type").attr("value","1");
			$("#file1").removeAttr("accept");
			$("#file1").attr("accept","image/*");
			$("#name").val("");
		});
	});
	 function valite(){
	    	if ($("#name").val()==''|| $("#name").val()==null) {
	            alert("请输入名称");
	            return false;
	        }
	    	/* if ($("#file1").val()==''|| $("#file1").val()==null) {
	            alert("请选择上传文件");
	            return false;
	        } */
    	 $('#publish-form').submit();
	    }
</script>
<style type="text/css">
.form-horizontal .control-label {
    float: left;
    padding-top: 5px;
    text-align: right;
    width: 140px;
}
</style>
</head>

<body>
	<div style="display:none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="微海报模版上传">
			<a href="<%=path%>/wsh/ShWhb//toUpload" target="content" onfocus="this.blur()"><span>微海报模版上传</span></a>
			</li>
		</ul>
		</div>
	<div id="header-management">
		<div class="header-inner"></div>
	</div>
	<div class="container">
		<div class="row home-container">
			<div class="span2"></div>
			<div class="span12">
			
				<div class="right-content" >
					<h3>微海报模板</h3>
					<section>
					<form id="publish-form" class="form-horizontal" action="<%=path%>/wsh/ShWhb/save"
							method="post">
						<header class="inf_title">
						<div style="background: white none repeat scroll 0 0;float: left;height: 30px;" id="music">
							<span style="font-size: 15px;cursor:pointer;">背景音乐</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<div style="float:left;background: #eee none repeat scroll 0 0; margin-left: 20px;width: 96px;" id="photo">
							<span  style="font-size: 15px;cursor:pointer;">图片</span>
						</div>
						</header>
						<input type="hidden" id="type" name="type" value="0">
						<%-- 	<input type="hidden" id="openId" name="openId" value="${openId }"> --%>
							<input type="hidden" id="fileName" name="fileName" value="">
							<div class="control-group" style="">
								<label class="control-label" for="input01"> <span class="notify_import">*</span>
									<span class="poster_name_notify">名称:</span> 
								</label> 
								<div class="controls" style="position: relative;">
									<input type="text" id="name" name="name"   style="height:30px;width:270px;margin-bottom: 0px;background: white;"/> 
								</div>
							</div>
							<div class="control-group" style="">
								<label class="control-label" for="input01">
									<span class="notify_import">*</span>
									<span class="poster_name_notify">时间:</span>  
								</label>
								<div class="controls" style="position: relative;">
									<input id="time" name="time" type="text"
										value="<%=value%>" style="height:30px;width:270px;margin-bottom: 0px;background: white;" /> 
								</div>
							</div>
							<div class="control-group" style="">
								<label id="add-product-image" class="control-label"  for="input01" onclick="toAddImg();">
									<span class="notify_import">*</span> 
									<span class="poster_name_notify" id="sclx">音乐上传:</span>
								</label>
								<div id="uploadDiv" class="controls" style="position: relative;">
									<div class="file-container-main" style="width: 270px;height: 35px;">
										<div class="file-container" style="width: 270px;height: 35px;">
											<table cellspacing="0" cellpadding="0" style="border-style:none;position:absolute;z-index:10;">
												<tbody>
													<tr>
														<td class="ali01" style="border-style:none;padding:0;margin:0;">
														<input id="filename" name="filename" type="text" style="width: 270px; height: 30px; background: white none repeat scroll 0% 0%;"/>
														<input id="file1" name="file" type="file" onchange="selectFile();" 
														style="width: 270px; height: 30px; background: white none repeat scroll 0% 0%;font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;">
													</td>
														<td class="ali01" style="border-style:none;;padding:0;margin:0;">
														<input class="fileBtn" type="button" value="">
													</td>
													</tr>
												</tbody>
											</table>
		<!-- 										<input id="file1" name="file" type="file"  -->
		<!-- 											style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" -->
		<!-- 											accept="audio/mpeg" capture="camera" onchange="selectFile();"> -->
										</div>
									</div>
								</div>
							</div>
							<div class="control-group" id="imgDiv" style="display: none;">
								<img id="img1" src="" align="center" style="height: 100%; text-align: center;" />
							</div>
							<div class="poster_input">
								<input type="button" value="开始上传" id="create_submit" onClick="valite()"
									class="btn btn-success create" />
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