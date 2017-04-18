<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1" name="viewport">
<title>学生考勤</title>
<link href="<%=path%>/resources/css/wfw/jzdstyle.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/wfw/bootstrap.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript"> 
	function query(){
        var objS = document.getElementById("pid");
        var openId = document.getElementById("openId").value;
        var grade = objS.options[objS.selectedIndex].value;
        location.href="<%=path%>/wfw/ZsXskq/toZsXskqxxfdy?classname="+grade+"&openId="+openId;
   	} 
	
</script>

<style>
.New_main01 section {
	display: none;
}
.Home_btn{right: 6%;top: 10%;}
</style>
</head>
<body style="max-width: 640px; margin-left: auto; margin-right: auto; background-color: white;" onload="wx.hideOptionMenu();">
	<div class="iphone">
			<div class="WZY_top01">
			<div class="row">
				<div class="col-sm-12" style="position: relative;">
					<img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive">
					 <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}"> <img class="Home_btn"src="<%=path%>/resources/img/wzy/FH.png" width="40" height="40">
					</a>
				</div>
			</div>
		</div>
		<div class="New_main01">
			<div class="container">
				<div id="mainbox">
					<section id="list" style="display: block">
						<div class="container">
							<div class="row">
								<div class="col-sm-12" style="border-bottom: 1px solid #e5e5e5; height: 40px; padding-top: 8px;">
									<form action="<%=path%>/wfw/ZsXskq/toZsXskqxxfdy" method="post">
										<span style="width: 40%; margin-top: 12px;"> 班级查询:&nbsp;&nbsp; <select id="pid" style="width: 40%;">
												<c:if test="${empty bjs}">
													<option value="">无</option>
												</c:if>
												<c:if test="${!empty bjs}">
													<option value="">请选择...</option>
													<c:forEach var="data" items="${bjs}" varStatus="obj">
														<option value="${data.classname }" <c:if test="${bjmc==data.classname}">selected="selected"</c:if> >${data.classname }</option>
													</c:forEach>
												</c:if>
										</select></span> <input type="hidden" name="openId" id="openId" value="${openId }">
											 <input type="hidden" name="bjmc" id="bjmc" value="${bjmc }">  
										 <input type="hidden" name="size" id="size" value="${size }"> 
										 <span>
										  <a class="glyphicon glyphicon-search" href="#" style="margin-left: 14px; font-size: 14px;" onclick="query();">查询 </a>
										</span>
									</form>
								</div>
							</div>
						</div>
					<table class="table table-striped" style="text-align: center">
					<tr>
						<td class="col-xs-4 ziti" style="width: 25%"><small style="color: #3D9DF7">学生姓名</small></td>
						<td class="col-xs-4 ziti" style="width: 25%"><small style="color: #3D9DF7">班级名称</small></td>
						<td class="col-xs-4 ziti" style="width: 25%"><small style="color: #3D9DF7">缺勤率</small></td>
						<td class="col-xs-4 ziti" style="width: 25%"><small style="color: #3D9DF7">查看</small></td>
					</tr>
						<c:forEach var="list" items="${list }">
						<tr>
							<td><small> ${list.xsxm} </small></td>
							<td><small>${list.bjmc}</small></td>
							<td><small>${list.qql}%</small></td>
				            <td><small><a href="<%=path%>/wfw/ZsXskq/toZsxskqxxDetail?xsxh=${list.xsxh}&openId=${openId}">查看</a></small></td>
						</tr>
					</c:forEach>
					</table>
						<c:if test="${empty list}">
							<div class="text">
								<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
							</div>
						</c:if>
						<c:if test="${size>9 }">
							<tr>
								<td style="position: relative;">
									<div class="-ft" style="margin-bottom: 0rem; width: 100%;">
										<button class="btn btn-default btn-block btn-lg ng-binding more" onclick="loadMore('${pages}','${openId }','${bjmc}');" style="position: relative; bottom: 0px;">加载更多</button>
									</div>
								</td>
							</tr>
						</c:if>
					</section>
				</div>
			</div>
		</div>
		
		<div class="WZY_foot01">
			<div class="row">
				<div class="col-sm-12">
					<img src="<%=path%>/resources/css/wfw/image/banquan.png" class="img-responsive">
				</div>
			</div>
		</div>
</div>
</body>

<script type="text/javascript">

function loadMore(page,openId,bjmc){
	i=page;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wfw/ZsXskq/toFdyDate1";
	var html= "";
    $.ajax({
		url :url,
		dataType:'json',
		data : {
			pages:i,
			openId:openId,
			bjmc:bjmc
			//openId:openId
		},
		type : "post",
		success : function(data) {
			if(data.length!==0){
			var rst = eval(data);
			$.each(rst,function(i,value){
		    html +="<table class='table table-striped' style='text-align: center'>"
			    +"<tr>"
				+ "<td class='col-xs-4 ziti' style='width:20%'>"
				+ "<small>"
				 +value.xsxm
				 + "</small>"
				 + "</td>"
				 + "<td class='col-xs-4 ziti' style='width:20%'>"
				 + "<small>"
				 +value.bjmc
				 + "</small>"
				 + "</td>"
				 + "<td class='col-xs-4 ziti' style='width:20%'>"
				 + "<small>"
				 +value.qql+"%"
				 + "</small>"
				 + "</td>"
				 + "<td class='col-xs-4 ziti' style='width:20%'>"
				 + "<small>"		
				 +"<span class=''> <span class='' style='color: #F4C520'></span> &nbsp;"
				 +"<a href='<%=path%>/wfw/ZsXskq/toZsxskqxxDetail?xsxh="+value.xsxh+"&openId="+openId+"'>"
				 +"查看</a>"
				 + "</small>"
				 +"</span>"
				 +"</td>"
			     +"</tr>"
		         +"</table>";
			})
		    $('.-ft').before(html);
			$('.more').removeAttr("onclick");
			$('.more').attr("onclick","loadMore('"+i+"','"+openId+"','"+bjmc+"')");
			
	       }else{
				html="<div class='-ft' style='margin-top: -20px;width:90%;margin-left:5%;margin-bottom:20px;'>"
					+"<button class='btn btn-default btn-block btn-lg ng-binding' style='position:relative;'>已经是最后一页了</button>"
					+"</div>";
					$('#-ft').remove();
				    $('.-ft').before(html);
			}
			},
		   error : function() {
			alert("error");
		}
	  });
}

</script>
<script type="text/javascript">

	var appid = "${map.appid}";//$('#appid').val();
	var nonceStr = "${map.nonceStr}";//$('#nonceStr').val();
	var timestamp = "${map.timestamp}";//$('#timestamp').val();
	var signature = "${map.signature}";//$('#signature').val();
	wx.config({
		debug : false,
		appId : '${map.appid}',
		timestamp : '${map.timestamp}',
		nonceStr : '${map.nonceStr}',
		signature : '${map.signature}',
		jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
				'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo',
				'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem',
				'showAllNonBaseMenuItem', 'translateVoice', 'startRecord',
				'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice',
				'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage',
				'previewImage', 'uploadImage', 'downloadImage',
				'getNetworkType', 'openLocation', 'getLocation',
				'hideOptionMenu', 'showOptionMenu', 'closeWindow',
				'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
				'addCard', 'chooseCard', 'openCard' ]
	});

	wx.ready(function() {
		// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		wx.hideOptionMenu();
	});
</script>
<script type="text/javascript">
	var str = "";
</script>

<script type="text/javascript">

</script>
</html>
