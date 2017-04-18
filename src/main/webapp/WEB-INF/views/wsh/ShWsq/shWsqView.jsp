<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="3">
				
<title>${wsq.hdmc}</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">


<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/jquery-ui-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/core.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/index.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/wall.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/horizon.css">

	
<!-- <script type="text/javascript" src="%E5%82%BB%E5%82%BB%E5%93%92-%E5%BE%AE%E6%A0%A1_files/jquery-1.js"></script> -->
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
<!-- 	<script type="text/javascript" src="%E5%82%BB%E5%82%BB%E5%93%92-%E5%BE%AE%E6%A0%A1_files/jquery-ui-1.js"></script> -->
	<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/filter.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/global.js"></script>
<script type="text/javascript">
// 	function myrefresh()
// 	{
// 	   window.location.reload();
// 	}
// 	setTimeout('myrefresh()',3000); //指定3秒刷新一次

	function ScrollDiv() {    
	    var ex = document.getElementById("panelBodyWrapper-5");  
	        ex.scrollTop = ex.scrollHeight;   
	} 
</script>
	
</head>
	
	<body style="" onload="ScrollDiv();">
		<form id="context-data-form" class="none">
			<input name="context" value="#" type="hidden">
			<input name="hdid" id="hdid" value="${wsq.id}" type="hidden">
		</form>
		<div class="wall-header">
			<div class="wall-header-inner">
				<div class="wall-logo">
					<a href="#">
						<img src="${wsq.logo}" alt="LOGO" title="LOGO">
					</a>
				</div>
				<div class="wall-ativity-name">
					${wsq.hdmc}			</div>
				<div class="wall-desc">
				</div>
			</div>
		</div>
		<div class="container">
			<style type="text/css">
.wall-wrapper {
	background: rgba(255,255,255,0.5)!important;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#7fffffff',endcolorstr='#7fffffff');
	padding: 20px;
	overflow: hidden;
	position: relative;
}
.wall-content {
	height: 510px;
	overflow: hidden;
	position: relative;
}
.wall-content li {
	background-color: #f3f3f3;
	font-size: 24px;
	margin-bottom: 10px;
	padding: 10px;
	overflow: hidden;
}
.wall-content .avatar {
	width: 100px;
	height: 100px;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
}
.wall-content .nickname {
	font-size: 18px;
	line-height: 1.334;
	text-align: center;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
	width: 110px;
}
.wall-msg {
	margin-left: 120px;
	font-size: 24px;
	line-height: 1.334;
	word-wrap: break-word;
	word-break: break-all;
}
.fullscreen-msg {
	display: none;
	position: absolute;
	width: 100%;
	height: 100%;
	background: #f3f3f3;
	z-index: 10;
}
.fullscreen-msg .avatar {
	float: left;
}
.fullscreen-msg .nickname {
	font-size: 30px;
	width: 500px;
	text-align: left;
	padding-left: 20px;
	line-height: 100px;
}
.fs-title {
	margin: 20px 30px 0px;
	padding-bottom: 15px;
	overflow: hidden;
	border-bottom: 1px solid #c6c6c6;
}
.fs-content {
	margin: 10px 30px 20px;
}
.fs-content {
	font-size: 48px;
	line-height: 1.334;
	height: 344px;
	word-wrap: break-word;
	word-break: break-all;
	overflow: auto;
}
a.fs-close {
	display: block;
	position: absolute;
	top: 20px;
	right: 20px;
	color: #555;
	text-decoration: none;
	text-align: center;
	background-color: #e5e5e5;
	width: 40px;
	height: 40px;
	font-size: 30px;
	line-height: 36px;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	border-radius: 20px;
}
.fs-close:hover {
	color: #f00;
	background-color: #ebebeb;
}
</style>

<div class="wall-wrapper">
	<div class="wall-content" style="overflow: auto;" id="panelBodyWrapper-5">
		<div class="fullscreen-msg" id="fs-msg">
			<a href="javascript:void(0);" class="fs-close" id="fs-close">×</a>
			<div class="fs-title">
				<img class="avatar" src="<%=path%>/resources/js/wbm/default-avatar.jpg" id="fs-avatar">
				<div class="nickname" id="fs-nickname"></div>
			</div>
			<div class="fs-content" id="fs-content"></div>
		</div>
		<ul>
 		<c:forEach var="data" items="${fbxx}" varStatus="obj"> 
 			<li style=""> 
 				<div class="float-left"> 
 					<img class="avatar" src="${data.bz }" height="100" width="100"> 
 					<div class="nickname" title="${data.cyrxm }">${data.cyrxm }</div> 
 				</div> 
 				<div style="font-size: 2em;" class="wall-msg">${data.fbnr }</div> 
 			</li> 
 		</c:forEach> 
<!--  			<li style="display: none;"> -->
<!-- 				<div class="float-left"> -->
<!-- 					<img class="avatar" src="" width="100" height="100"> -->
<!-- 					<div class="nickname" title=""></div> -->
<!-- 				</div> -->
<!-- 				<div class="wall-msg"></div> -->
<!-- 			</li>  -->
		</ul>
	</div>
</div>

			<div class="wall-footer">
				<!--<img src="%E5%82%BB%E5%82%BB%E5%93%92-%E5%BE%AE%E6%A0%A1_files/wall_footer_logo.png" alt="微校" title="微校">
				<span>微校(weixiao.qq.com)提供技术支持</span>
			</div>
			<div class="wall-btns">
				<a data-original-title="" href="http://weixiao.qq.com/activity/wall/13648" class="btn-wall-index"></a>
				<a data-original-title="" href="http://weixiao.qq.com/activity/danmu/13648" class="btn-wall-danmu"></a>
				<a data-original-title="" href="http://weixiao.qq.com/activity/signed_wall/13648" class="btn-wall-signed"></a>
				<a data-original-title="" href="http://weixiao.qq.com/activity/wall_lottery/13648" class="btn-wall-prize"></a>
				<a data-original-title="" href="#" class="btn-wall-poll"></a>
				<a data-original-title="" href="javascript:void(0);" class="btn-wall-fullscreen js-fullscreen"></a>
			</div> -->
		</div>
		
		<script type="text/javascript">
			var token = '';
			
			$('.btn-wall-index').tooltip({
				title : '主页'
			});
			$('.btn-wall-danmu').tooltip({
				title : '弹幕上墙'
			});
			$('.btn-wall-signed').tooltip({
				title : '签到'
			});
			$('.btn-wall-prize').tooltip({
				title : '抽奖'
			});
							$('.btn-wall-poll').tooltip({
					title : '还没创建投票，后台创建后，刷新页面'
				});
				$('.btn-wall-poll').attr('href','#');
				$('.btn-wall-poll').click(function(){
				});
						/* 
			Native FullScreen JavaScript API
			-------------
			Assumes Mozilla naming conventions instead of W3C for now
			*/

			(function() {
				var 
					fullScreenApi = { 
						supportsFullScreen: false,
						isFullScreen: function() { return false; }, 
						requestFullScreen: function() {}, 
						cancelFullScreen: function() {},
						fullScreenEventName: '',
						prefix: ''
					},
					browserPrefixes = 'webkit moz o ms khtml'.split(' ');
				
				// check for native support
				if (typeof document.cancelFullScreen != 'undefined') {
					fullScreenApi.supportsFullScreen = true;
				} else {	 
					// check for fullscreen support by vendor prefix
					for (var i = 0, il = browserPrefixes.length; i < il; i++ ) {
						fullScreenApi.prefix = browserPrefixes[i];
						
						if (typeof document[fullScreenApi.prefix + 'CancelFullScreen' ] != 'undefined' ) {
							fullScreenApi.supportsFullScreen = true;
							
							break;
						}
					}
				}
				
				// update methods to do something useful
				if (fullScreenApi.supportsFullScreen) {
					fullScreenApi.fullScreenEventName = fullScreenApi.prefix + 'fullscreenchange';
					
					fullScreenApi.isFullScreen = function() {
						switch (this.prefix) {	
							case '':
								return document.fullScreen;
							case 'webkit':
								return document.webkitIsFullScreen;
							default:
								return document[this.prefix + 'FullScreen'];
						}
					}
					fullScreenApi.requestFullScreen = function(el) {
						return (this.prefix === '') ? el.requestFullScreen() : el[this.prefix + 'RequestFullScreen']();
					}
					fullScreenApi.cancelFullScreen = function(el) {
						return (this.prefix === '') ? document.cancelFullScreen() : document[this.prefix + 'CancelFullScreen']();
					}		
				}

				// jQuery plugin
				if (typeof jQuery != 'undefined') {
					jQuery.fn.requestFullScreen = function() {
				
						return this.each(function() {
							var el = jQuery(this);
							if (fullScreenApi.supportsFullScreen) {
								fullScreenApi.requestFullScreen(el);
							}
						});
					};
				}

				// export api
				window.fullScreenApi = fullScreenApi;	
			})();

			if (window.fullScreenApi.supportsFullScreen) {				
				// handle button click
				// $('.js-fullscreen').click(function(e) {
				// 	window.fullScreenApi.requestFullScreen(document.documentElement);
				// });
				$('.js-fullscreen').tooltip({
					title : '按F11使用浏览器全屏<br/>功能获得更好效果'
				});
				
			} else {
				$('.js-fullscreen').tooltip({
					title : '你的浏览器不支持<br/>请手动按F11进入全屏'
				});
			}
		</script>
	
</body></html>