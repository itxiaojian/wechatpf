<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<% String path = request.getContextPath();response.addHeader("X-Frame-OPTIONS", "SAMEORIGIN");%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${setting['app'] }</title>
<!--框架必需start-->
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
<link href="<%=path%>/system/layout/skin/style.css" rel="stylesheet" type="text/css" id="skin"  skinPath="system/layout/skin/"/>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/main.js"></script>
<!--框架必需end-->

<!--引入弹窗组件start-->
<script type="text/javascript" src="<%=path%>/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/popup/dialog.js"></script>
<!--引入弹窗组件end-->


<!--分隔条start-->
<script type="text/javascript" src="<%=path%>/libs/js/nav/spliter.js"></script>
<!--分隔条end-->

<style>
div.tabMenuDiv {
	height: 25px;
	width: 100%;
	border-bottom:3px solid #80C0E7;
}

div.tabMenuDiv ul {
	LIST-STYLE-TYPE: none;
	MARGIN: 0px;
	margin-LEFT: 4px;
	PADDING: 0px;
	width: 100%;
	overflow: hidden;
	height:25px;
	font-weight: bold;
}

div.tabMenuDiv ul * {
	line-height: 24px;
}

div.tabMenuDiv ul LI {
	MIN-WIDTH: 84px;
	height: 23px;
	MARGIN: 0px 0px 0px 3px;
	FLOAT: left;
	background-color:#dedede;
	width: 120px;
}

div.tabMenuDiv ul LI A {
	display: block;
	height: 25px;
	TEXT-ALIGN: center;
	PADDING-LEFT: 0px;
	FONT-SIZE: 12px;
	TEXT-DECORATION: none;
	white-space: nowrap;
	padding-left: 3px;
	padding-right: 3px;
}

div.tabMenuDiv ul LI A SPAN {
	display: block;
	color: #000000;
}

div.tabMenuDiv ul LI SPAN {
	display: block;
	color: #000000;
	text-align: center;
}

div.tabMenuDiv ul .tab-selected A span {
	color: #005D9B;
}

div.tabMenuDiv ul li.tab-selected {
	height: 26px;
	BACKGROUND-color: #80C0E7;
}

div.tabMenuDiv ul LI A:hover {
	BACKGROUND-POSITION: 0px -60px;
	color: #005D9B;
}

div.tabMenuDiv ul .tab-disabled {
	opacity: .4
}

</style>

 
<script type="text/javascript">

function displayNav(tabNav){
	try{
		document.getElementById("tabMenuID_").innerHTML = tabNav;
		
		$("#tabMenuID_ a").click(function(){
			$("#tabMenuID_ li").each(function(){
				$(this).removeClass("tab-selected");
			} );
			$(this).parent().addClass("tab-selected");
		} );
	}catch(e){alert(e);}
}

function bookmarksite(title, url){
    if (window.sidebar) // firefox
        window.sidebar.addPanel(title, url, "");
    else 
        if (window.opera && window.print) { // opera
            var elem = document.createElement('a');
            elem.setAttribute('href', url);
            elem.setAttribute('title', title);
            elem.setAttribute('rel', 'sidebar');
            elem.click();
        }
        else 
            if (document.all)// ie
                window.external.AddFavorite(url, title);
}
function backHome(){
	document.getElementById("frmleft").contentWindow.homeHandler();
}

function getContentHeight() {
	$("#tabContent").height($("#rbox_middleright").height() - 50);
	return $("#rbox_middleright").height() - 30;
}

function getContentWidth() {
	return $("#rbox_middleright").width();
}
function closeProgress() {
    try {
        if (top.progressFlag == 1) {
            top.Dialog.close();
            top.progressFlag = 0
        } else {
            if (top.progressFlag == 2) {
                top.hideSimpleProgress();
                top.progressFlag = 0
            }
        }
    } catch(a) {}
}

function windowClose(){
	window.opener=null;
	  window.open('', '_self'); //IE7必需的.
	  window.close();
	}

	var callbacks = new Array();

	function title(title) {
		if ($("#_Title_" + (Dialog._dialogArray.length-1)).length != 0) {
			$("#_Title_" + (Dialog._dialogArray.length-1)).show();
			$("#_Title_"+ (Dialog._dialogArray.length-1)).text(title);
		}
	}
	//{url:'',width:'',height:'',params:{id:'',other:''}}
	function onView(config) {
		config = config ||{};
		
		var width = 700;
		var height = 500;
		var title = "";
		if (config.width)
			width = config.width;
		
		if (config.title)
			title = config.title;
		
		if (config.height)
			height = config.height;
		
		var iframeName = "InnerFrameName" + Dialog._dialogArray.length;
		Dialog.open({InnerFrameName:iframeName,URL:"about:blank",Title:title,Width:width,Height:height,ShowOkButton:false,ShowCancelButton:false,CancelEvent:closeView,ButtonAlign:"center"}); 
		callbacks[Dialog._dialogArray.length - 1] = config.callback;
		
		$("#postForm").empty();
		var post = false;
		var params = config.params||{};
		for(p in params) {
			var val = params[p];
			if ($.isArray(val)){
				for(var i = 0; i < val.length; i++)				
					$("#postForm").append('<input type="hidden" name="' + p + '" value="' + val[i] + '"/>');		
			} else {
				$("#postForm").append('<input type="hidden" name="' + p + '" value="' + val + '"/>');		
			}
			post = true;
		}
		if (post) {
			$("#postForm").attr("action",config.url);
			$("#postForm").attr("target",iframeName);
			$("#postForm").submit();
		} else {
			$("[name="+iframeName+"]").attr("src",config.url);
		}
	}

	var params = {};

	function funCall() {
			if (callbacks[Dialog._dialogArray.length - 1] != null) {
				callbacks[Dialog._dialogArray.length - 1].call();
				callbacks[Dialog._dialogArray.length - 1] = null;
			}
	}

	function closeView() {
		funCall();
		if (Dialog._dialogArray.length == 0) {
			params = {};
		}				
		if(Dialog._dialogArray[Dialog._dialogArray.length - 1] != null)
			Dialog._dialogArray[Dialog._dialogArray.length - 1].close();
	}

	function put(name, value) {
		params[name] = value;
	}

	function get(name) {
		var val = params[name];
		params[name] = null;
		return val;
	}

function showMsg(count) {
	if (count == "") {
		document.getElementById("msgBox").innerHTML = "";
	} else {
		document.getElementById("msgBox").innerHTML = '<img src="<%=path%>/resources/images/newpm.gif"><a style="color:blue;" href="<%=path%>/admin/oa/OaShortMessage/listIn" target="content" onclick="'+"window.frames['frmleft'].selectMenu('<%=path%>/admin/oa/OaShortMessageBox/listIn')"+'">[您有'+count+'条未读信息]</a>';
	}
}

$(document).ready(function(){
	<c:if test="${user.msgCount > 0}">
		 showMsg('${user.msgCount}');
	</c:if>
$("#tabContent").height($("#bs_right").height() - 50);

});
	
</script>
<style>
a {
	behavior:url(<%=path%>/libs/js/method/focus.htc)
}
</style>
</head>
<body>
<div sytle="display:none;">
	<form id="postForm" action="" method="post">
	</form>
</div>

<div id="mainFrame" style="background-color:white;">
<!--头部与导航start-->
<div id="hbox">
	<div id="bs_bannercenter" style="height: 100px;">
	<div id="bs_bannerright">
	<div id="bs_bannerleft1">
<%-- 		<p style="line-height:60px;font-size:22pt;font-weight:bold;margin:0px 20px;color:#ffffff;text-shadow:2px 2px 5px orange; ">${setting['app']}</p>
 --%>	
 		<p style="line-height:100px;font-size:22pt;font-weight:bold;margin:0px 20px;color:#ffffff;text-shadow:2px 2px 5px orange; "><img src="skin/logo.png" class="PNGFIX"></p>	
	</div>
	</div>
	</div>
	<div id="bs_navcenter" >
	<div id="bs_navleft">
	<div id="bs_navright">
		<div class="bs_nav">
			<div class="bs_navleft">
<%-- 				<li class="fontTitle">欢迎用户：<sec:authentication property="principal.fullname"/></li> --%>
				<li class="fontTitle">欢迎用户：<sec:authentication property="principal.xm"/></li>
				<li id="msgBox"></li>
				<div class="clear"></div>	
			</div>	
			<div class="float_left" style="padding:2px 0 0 80px;" id="positionContent"></div>	
			<div class="float_right padding_top2 padding_right5">
				<span class="icon_fullscreen hand" id="fullSrceen" hideNav="true">开启全屏</span>
				<span class="icon_delete hand" onclick='top.Dialog.confirm("确定要退出系统吗?",function(){window.location="<%=path%>/j_spring_security_logout"});'>退出系统</span>
				<div class="clear"></div>
			</div>
			<div class="float_right padding_top2 padding_right5">
				【今天是
				<script>
					var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
					var now = new Date();
				    var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate()
				    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
					document.write(currentime)
				</script>】
			</div>	
			<div class="clear"></div>
		</div>
	</div>
	</div>
	</div>
</div>
<!--头部与导航end-->

<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
	<tr>
		<!--左侧区域start-->
		<td id="hideCon" class="ver01 ali01">
							<div id="lbox">
								<div id="lbox_topcenter">
								<div id="lbox_topleft">
								<div id="lbox_topright">
									<div class="lbox_title">
										操作菜单
									</div>
								</div>
								</div>
								</div>
								<div id="lbox_middlecenter">
								<div id="lbox_middleleft">
								<div id="lbox_middleright">
										<div id="bs_left">
										<IFRAME height="100%" width="100%"  frameBorder=0 id=frmleft name=frmleft src="<%=path%>/system/layout/left.jsp"  allowTransparency="true"></IFRAME>
										</div>
										<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
								</div>
								</div>
								</div>
								<div id="lbox_bottomcenter">
								<div id="lbox_bottomleft">
								<div id="lbox_bottomright">
									<div class="lbox_foot"></div>
								</div>
								</div>
								</div>
							</div>
		</td>
		<!--左侧区域end-->
		
		<!--分隔栏区域start-->
		<td class="spliter main_shutiao" targetId="hideCon" beforeClickTip="收缩面板" afterClickTip="展开面板" beforeClickClass="bs_leftArr" afterClickClass="bs_rightArr">
		</td>
		<!--分隔栏区域end-->
		
		<!--右侧区域start-->
		<td class="ali01 ver01"  width="100%">
							<div id="rbox" class="box1"  style="background-color:white;">
								<div id="rbox_topcenter">
								<div id="rbox_topleft">
								<div id="rbox_topright">
								</div>
								</div>
								</div>
								<div id="rbox_middlecenter">
								<div id="rbox_middleleft">
								<div id="rbox_middleright">
									<div id="bs_right">
									<div style="height:100%;width:100%;">
											<div class="tabMenuDiv" style="padding-top:5px;"><ul id="tabMenuID_" class="tab"></ul><div class="clear"></div></div>
											<div id="tabContent" class="basicTab_con" style="">
											<IFRAME width="100%" height="100%" frameBorder=0 id="content" name="content" 
												 allowTransparency="true">
												  </IFRAME>
											</div>
									</div>
								</div>
								</div>
								</div>
								<div id="rbox_bottomcenter" >
								<div id="rbox_bottomleft">
								<div id="rbox_bottomright">

								</div>
								</div>
								</div>
							</div>
		</td>
		<!--右侧区域end-->
	</tr>
</table>

<!--尾部区域start-->
<div id="fbox">
	<div id="bs_footcenter">
	<div id="bs_footleft">
	<div id="bs_footright">
		版权所有：${setting['copyright'] }
	</div>
	</div>
	</div>
</div>
</div>
<!--尾部区域end-->

<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--窗口任务栏区域start-->
<div id="dialogTask" class="dialogTaskBg" style="display:none;"></div>
<!--窗口任务栏区域end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->

</body>
</html>
