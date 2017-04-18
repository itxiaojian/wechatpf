<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
<title>报名功能</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<script type="text/javascript">
	(function(w){
		w._config = {
			SYS_PATH: 'http://weixiao.qq.com'
		};

		w.get_config = function(name, default_value) {
			default_value = default_value ? default_value : null;
			return _config[name] ? _config[name] : default_value;
		};

		w.add_config = function(name, value) {
			_config[name] = value;
		}
	})(window);
	
	function Sc(id){
		var ret = window.confirm("确定要删除这项报名吗？");
		if (!ret) {
			return false;
		}
		var url = '<%=path%>/wsh/ShWbm/delete';
		$.ajax({
			url: url,
			type : 'post',
			dataType : 'json',
			data:{id:id},
			success: function(data){
				if(data == '1'){
						location.href ="<%=path%>/wsh/ShWbm/toWbmList";
				}else{
					alert("提交失败");
				}
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
	}
	function toView(id,time){
// 		var endTimes   =  time.substring(0,10).split('-');
// 		var endTime=new Date(endTimes[0],(endTimes[1]-1),endTimes[2],time.substring(11,13),time.substring(14,16),0);
// 		var today=new Date();               //获取当前日期  
// 		var year=today.getFullYear();       //获取当前年份  
// 		var month=today.getMonth();         //获取月份  
// 		var day=today.getDate();            //获取日期  
// 		var hour=today.getHours();          //获取小时  
// 		var min=today.getMinutes();         //获取分钟  
// 		var second=today.getSeconds();      //获取秒
// 		var beginTime=new Date(year,month,day,hour,min,second);
		
// 		var a =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;
// 		if(a>0){
			location.href ='<%=path%>/wsh/ShWbm/toWbmView?id='+id
// 		}else{
// 			alert("报名活动已结束！");
// 		}
	}
</script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/jquery-ui-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/core.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/home.css">

	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/activity.css">

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery-ui-1.8.22.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/filter.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/global.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
	<style id="qb_dict_style" type="text/css">#dict__tip {position: absolute; visibility: hidden;z-index: 50000; overflow:visible;}#dict__tip {border: 3px solid #2e9dff;padding: 0 12px 10px 12px;font-size: 12px;min-width:215px; max-width: 393px;background-color:#ffffff;box-shadow:0px 1px 8px rgba(0,0,0,0.2)}#dict__tip_translate_result {display:block;text-align:left; border: none; background: none}#dict__tip_translate_result span {margin:0px;padding:0px;line-height: 22px;font-family:Microsoft YaHei;font-size: 14px;color: #333333}#dict__tip_dict_info {border: none;background: none;position: relative;height: 30px;line-height: 30px;font-family:Microsoft YaHei,Tahoma, NSimsun, Simsun, sans-serif}#dict__tip_dict_info .dict-copyright {color: #999999;position: absolute;left: 0px;top: 0px}#dict__tip_dict_info .dict-link {position: absolute;right: 0px;top: 0px;}#dict__tip_dict_info #dict__tip_translate_link {padding-left: 10px;}#dict__tip_dict_info .dict-link a{color: #0066cc;text-decoration: none;}#dict__tip_dict_info .dict-link a:hover{text-decoration: underline;}</style>
</head>
	
	<body>
	<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="微报名">
	<a href="<%=path%>/wsh/ShWbm/toWbmList" target="content" onfocus="this.blur()"><span>微报名</span></a>
	</li>
</ul>
</div>
		<div class="container">
			<div class="row home-container">
				<div class="span12">
					<div class="right-content">
						<script type="text/javascript" src="<%=path%>/resources/js/wbm/qrcode.min.js"></script>
<%-- 	<script type="text/javascript" src="<%=path%>/resources/js/wbm/ZeroClipboard.min.js"></script> --%>
<h3>报名信息</h3>

<a href="<%=path%>/wsh/ShWbm/toWbmAdd" class="btn btn-success add-apply-btn">+添加新报名</a>

<div>
		<table class="table table-hover">
		<thead>
			<tr>
				<th class="span1">编号</th>
				<th>名称</th>
				<th class="span2">截止时间</th>
				<th class="span2">报名结果</th>
				<th class="span3">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="data" items="${list}" varStatus="obj">
						<tr>
				<td>${obj.count }</td>
				<td>
					<c:if test="${data.res==1 }"><span style="color: #fff;padding: 2px 5px;background-color: #b2b2b2;">已结束</span></c:if>
					<c:if test="${data.res==0 }"><span style="color: #fff;padding: 2px 5px;background-color: #fd9e0d;">进行中</span></c:if>
					${data.bmbt }
				</td>
				<td>${data.bmjzsj }</td>
				<td><a href="<%=path%>/wsh/ShWbm/toBmrs?id=${data.id }">${data.bmrs }人报名 »</a></td>
				<td><a href="<%=path%>/wsh/ShWbm/toWbmBdUpdate?id=${data.id }">编辑</a>
					<a href="#" class="js-del" onclick="Sc(${data.id });">删除</a> 
					<a href="#" data-id="" class="link">链接</a>
					<div class="apply_popover hide">
						<div class="apply_arrow"></div>
						<input id="href" type="text" value="${data.url }">
						<button class="btn btn-success click2copy" data-clipboard-text="${data.url }">复制</button>
					</div> 
					<a class="qrcode" href="${data.url }" data-url="${data.url }" data-original-title="">二维码</a>
					<a href="#" onclick="toView('${data.id }','${data.bmjzsj }');">预览</a>
				</td>
			</tr>
			</c:forEach>
				</tbody>
	</table>
	<div>
		</div>
	</div>

<script type="text/javascript">
jQuery(function($){
	$('.publish').click(function(e) {
		var url = $(this).data('url');
		var qr = document.getElementById('qrcode-wrp');
		$('.publish_modal input').val(url);
		$('.publish_modal .click2copy').attr('data-clipboard-text', url);
		qr.innerHTML = '';
		new QRCode(qr, {
				text: url,
				width: 120,
				height: 120
			});
		$('#publish_modal').modal('show');
	});
	
// 	$(".js-del").click(function(e){
// 		e.preventDefault();
// 		var ret = window.confirm("确定要删除这项报名吗？");
// 		if (!ret) {
// 			return false;
// 		}
// 		location.href = this.href;
// 	});
	$(".link").click(function(e){
		e.preventDefault();
		$(".qrcode").popover('hide');
		$popover = $(this).parent().find('.apply_popover');
		if(window.$url == null) {
			window.$url = $(this);
			}
		if (window.$url == null || window.$url.data('id') != $(this).data('id') ) {
			window.$url.parent().find('.apply_popover').addClass("hide");
			$popover.removeClass("hide");
			window.$url = $(this);
			}
		else {
			if ($popover.hasClass("hide")) {
				   $popover.removeClass("hide");
					}
					else {
				   $popover.addClass("hide");
					}
			}
	});

	window.$popover = null;
	$(".qrcode").click(function(e){
		e.preventDefault();
		$('.apply_popover').addClass("hide");
		window.codeurl = $(this).data('url');
		if (window.$popover != null) {
			is_current = $(window.$popover).attr('popover_show') == 'show';
			$(window.$popover).removeAttr('popover_show').popover('hide');
			window.$popover = null;
			if (is_current) {
				return;
			}
		}
		$(this).popover('show').attr('popover_show', 'show');
		window.$popover = $(this);
	});

	var show_popover = function() {
		var qr = document.getElementById("qrcode_poster");
		if (qr == null) {
			setTimeout(show_popover, 200);
			return;
		}
		$(qr).html('');
			 new QRCode(qr, {
					text: window.codeurl,
					width: 150,
					height: 150,
				});
		};
	$(".qrcode").popover({
		trigger : 'toggle',
		placement : 'bottom',
		html : true,
		content: function() {
			setTimeout(show_popover, 200);
			return  '<div id="qrcode_div"><h5 style="text-align: center;">扫一扫查看报名页</h5> <section id="qrcode_poster"  style="margin-left: 50px;"></section></div>';
		},
		title:"<h4>报名页二维码</h4>"
	});

});
	
</script>
					</div>
				</div>
			</div>
		</div>
		
<style>
.version-modal{
	padding-bottom:20px; 
}
.version-modal .version-model-body{
	padding: 20px;
	margin-bottom: 20px;
	max-height: 100%;
}
.version-modal .version-model-body ul,
.version-modal .version-model-body ol {
	margin: 14px 0 14px 0;
	padding: 0 0 0 40px;
}
.version-modal .version-model-body ul,
.version-modal .version-model-body ul li{
	list-style: disc;
}
.version-modal .version-model-body ol,
.version-modal .version-model-body ol li{
	list-style: decimal;
}

.version-header{
	height: 70px;
	text-align: center;
	background-color: #f3f3f3;
}
.version-publish-date{
	color: #ac7b53;
	margin-bottom: 10px;
}
.version-sure-btn{
	margin-left:40%;
	margin-right:40%;
	background-color: #ff900c;
	color: #fff;
	font-size: 13px;
	padding: 10px;
	border-radius:5px;
	cursor: pointer;
	
}
</style>

<script type="text/javascript">
jQuery(function($) {
	var is_show = 0;
	if(is_show){
		$('.version-modal').modal({
			backdrop: 'static',
			keyboard:false});
		$('.version-modal').modal('show');
	}
	$('#sure').click(function(){
		$.ajax({
			type : 'post',
			data:{"publish_date":$('.version-publish-date').attr("data-time")},
			dataType : 'json',
			url : "http://weixiao.qq.com/home/14284/index/set_version",
			success : function(data){
				$('.version-modal').modal('hide');
			},
		});
	});
	
});
</script>		
<div id="footer">
	<c:choose>
					<c:when test="${pages > 1}">
						<a href="<%=path%>/wsh/ShWbm/toWbmList?pages=${pages - 1}">上一页</a>
					</c:when>
					<c:otherwise>
						上一页
					</c:otherwise>
				</c:choose>
				第${pages}页
				<c:choose>
					<c:when test="${pages < count}">
						<a href="<%=path%>/wsh/ShWbm/toWbmList?pages=${pages + 1}">下一页</a>
					</c:when>
					<c:otherwise>
					下一页
				</c:otherwise>
				</c:choose>
				总共${count}页
</div>

		<script type="text/javascript">
		
		jQuery(function($) {
			var $navs = $("div.left-menu").find("a");
			$navs.each(function() {
				if ($(this).attr("data-menu") == 'acitvity-apply') {
					// 激活菜单显示
					$(this).parent().addClass("active");
				}
			});

			window.show_msg = function (msg, type) {
				$('#page_tips').remove();
				var	$msg = $('<div class="page_tips error" id="page_tips"><div class="inner"></div></div>');
				switch (type) {
					case 'error' :
						$msg.attr('class', 'page_tips error');
						break;
					case 'success' :
						$msg.attr('class', 'page_tips success');
						break;
					default :
				}
				if ($.trim(msg) !== '') {
					$msg.find('.inner').text(msg);
					$msg.hide().appendTo('body').fadeIn('fast', function() {
						setTimeout(function() {
							$msg.fadeOut('normal', function() {
								$(this).remove();
							});
						}, 2000);
					});
				}
			}
		});
		</script>
	
<div id="global-zeroclipboard-html-bridge" class="global-zeroclipboard-container" style="position: absolute; left: 0px; top: -9999px; width: 1px; height: 1px; z-index: 999999999;"><object id="global-zeroclipboard-flash-bridge" name="global-zeroclipboard-flash-bridge" width="100%" height="100%" type="application/x-shockwave-flash" data="#"><param name="allowScriptAccess" value="sameDomain"><param name="allowNetworking" value="all"><param name="menu" value="false"><param name="wmode" value="transparent"><param name="flashvars" value="trustedOrigins=weixiao.qq.com%2C%2F%2Fweixiao.qq.com%2Chttp%3A%2F%2Fweixiao.qq.com&amp;swfObjectId=global-zeroclipboard-flash-bridge&amp;jsVersion=2.2.0"><div id="global-zeroclipboard-flash-bridge_fallbackContent">&nbsp;</div></object></div><div id="dict__tip" style="visibility: hidden; top: auto; left: auto; right: auto;"><div id="dict__tip_dict_info" style="width: auto;"><span id="dict__tip_dict_link" class="dict-link"><a id="dict__tip_copy_translate" href="javascript:void(0)" target="_blank">复制</a><a id="dict__tip_translate_link" href="http://weixiao.qq.com/home/14284/activity/applies#" target="_blank">去Google翻译</a></span><span id="dict__tip_dict_copyright" class="dict-copyright">翻译结果</span></div><div id="dict__tip_translate_result"></div></div></body></html>