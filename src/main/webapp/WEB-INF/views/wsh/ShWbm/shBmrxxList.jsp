<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
<title>报名功能</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<script type="text/javascript">
	function Sc(id,bmid){
		var ret = window.confirm("确定要删除这项报名吗？");
		if (!ret) {
			return false;
		}
		var url = '<%=path%>/wsh/ShWbm/deleteBmxx';
		$.ajax({
			url: url,
			type : 'post',
			dataType : 'json',
			data:{id:id},
			success: function(data){
				if(data == '1'){
						location.href ="<%=path%>/wsh/ShWbm/toBmrs?id="+bmid;
				}else{
					alert("提交失败");
				}
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
	}
</script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/jquery-ui-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/core.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/home.css">

	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/activity.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-tagsinput.css">

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>

	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery-ui-1.8.22.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/filter.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/global.js"></script>

	<body>
		<div class="container">
			<div class="row home-container">
				
				<div class="span12">
					<div class="right-content">
						<h3>报名信息</h3>
<!-- <div class="apply-field-list"> -->
<!-- 	<div class="show-list" onselectstart="return false"> -->
<!-- 		<div class="show-icon"></div><span id="show-column">显示列</span> -->
<!-- 	</div> -->
<!-- 	<ul class="apply-name-list hide"> -->
<!-- 		<li><div class="apply_arrow"></div></li> -->
<%-- 		<c:forEach var="data" items="${bt}" varStatus="obj"> --%>
<%-- 				<li><input class="list-select" type="checkbox" data-index="${data.id }" checked="">${data.bt }</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
	

<!-- </div> -->
	
		<table class="table table-hover">
		<thead>
			<tr>
			<th>编号</th>
<%-- 					<c:forEach var="dat" items="${bt}" varStatus="ob"> --%>
						<th class="config-name-0 ">报名人微信昵称</th>
<!-- 						<th class="config-name-0 ">问题</th> -->
<!-- 						<th class="config-name-0 ">答案信息</th> -->
<%-- 					</c:forEach> --%>
						<th>操作</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="da" items="${list}" varStatus="o">
<%-- 					<c:forEach var="d" items="${bt}" varStatus="s"> --%>
<%-- 									<c:if test="${da.bt==d.bt }"> --%>
					<tr>			<td>${o.count }</td>
									<td class="config-value-0">${da.nickname }</td>
<%-- 									<td class="config-value-0">${da.bt }</td> --%>
<%-- 									<td class="config-value-0">${da.nr }</td> --%>
						<th><a href="#" class="js-del" onclick="Sc(${da.ybmrid },${da.bmid });">删除</a></th>
					</tr>
<%-- 									</c:if> --%>
<%-- 					</c:forEach> --%>
				</c:forEach>
				</tbody>
		<tfoot></tfoot>
	</table>
	<div>
<div class="modal apply-detail-modal hide">
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-labelledby="modal_label" aria-hidden="true">x</button>
	<h3>数据详情</h3>
	</div>
	<div class="modal-body">
		<div id="user_field_0"></div>
	</div>
</div>	
		</div>
	<script type="text/javascript">
jQuery(function($){
	$(document).bind('click',function(e){ 
		var target = $(e.target); 
		if(target.closest(".show-list").length == 0 && target.closest(".apply-name-list").length == 0 ){ 
		    $(".apply-name-list").addClass('hide');
		  }
		}); 
	$('.list-select').bind('click',function(){
		$('.config-value-'+$(this).attr('data-index')).toggleClass('hide');
		$('.config-name-'+$(this).attr('data-index')).toggleClass('hide');
	});
	$('.show-list').click(function(){
		$('.apply-name-list').toggleClass('hide');
	});
	$('.show-list').bind('mouseover',function(){
		$('.show-icon').css('background',"url(/css/img/apply_show_list_hover.png) no-repeat");
	});
	$('.show-list').bind('mouseout',function(){
		$('.show-icon').css('background',"url('/css/img/apply_show_list.png') no-repeat");
	});
	
	$('.show-detail').click(function(){
		var id = $(this).attr('data-id');
		$.ajax({
			url: 'http://weixiao.qq.com/home/14284/activity/view_user/'+16112,
			type : 'get',
			dataType : 'json',
			success: function(data){
				$('.modal-body').empty();
					var content = "<form class='form-horizontal'><fieldset><div class='control-group'><label class='control-label config-name'>报名时间: </label><div class='controls config-value'>"+data.result[id]['fields'].created_at+'</span></div></div>';
				for(var i=0;i<data.user_fileds_count;i++) {
					content += "<div class='control-group'><label class='control-label config-name'>"+data.activity_configs[i].name+": </label>";
				if(data.result[id]['fields'][data.activity_configs[i].id] == null) {
					content += "<div class='controls config-value'></span></div></div>";
				}else{
			       content += "<div class='controls config-value'>"+(data.result[id]['fields'][data.activity_configs[i].id].value).replace(new RegExp('\n', 'g'), "<br>")+'</span></div></div>';
				}
				}
				//console.log(data);
				content +="</fieldset></form>";
				$('.modal-body').append(content);
				$('.apply-detail-modal').modal('show');
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
	});
});
</script>					</div>
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

<div class="modal version-modal hide">
		
</div>
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
</script>		<div id="footer">
	<c:choose>
					<c:when test="${pages > 1}">
						<a href="<%=path%>/wsh/ShWbml/toBmrs?pages=${pages - 1}">上一页</a>
					</c:when>
					<c:otherwise>
						上一页
					</c:otherwise>
				</c:choose>
				第${pages}页
				<c:choose>
					<c:when test="${pages < count}">
						<a href="<%=path%>/wsh/ShWbml/toBmrs?pages=${pages + 1}">下一页</a>
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
	
</body></html>