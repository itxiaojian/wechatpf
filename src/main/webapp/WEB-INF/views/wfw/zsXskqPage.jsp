<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>我的考勤</title>
<link href="<%=path%>/resources/css/wfw/jzdstyle.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/wfw/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script>
<style type="text/css"></style>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>

</head>
<style>
.New_main01 section {
	display: none;
}
.Home_btn{right: 6%;top: 10%;}
</style>
<body style="max-width:640px;margin-left: auto;margin-right: auto;background-color:white;" onload="wx.hideOptionMenu();">
<div class="iphone">
	<div class="WZY_top01">
    	<div class="row">
          <div class="col-sm-12" style="position:relative;">
					<img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive">
            <a href="javascript:;" onclick="javascript:history.go(-1)">
            <img class="Home_btn" src="<%=path %>/resources/img/wzy/FH.png" width="40" height="40">
            </a>
          </div>
    	</div>
    </div>
    <div class="New_main01">
    	<div class="container">
					<table class="table table-striped" style="margin-top: 20px;">
					<c:forEach var="xslist" items="${xslist }">
						<tr>
							<td>
								<div class="col-sm-12">
									<span class="glyphicon glyphicon-user" style="color: #3D9DF7"></span> &nbsp;<span style="color: #3D9DF7"> ${xslist.xsxm }&nbsp;&nbsp;&nbsp;学号：${xslist.xsxh } </span>
								</div>
							</td>
						</tr>
						<tr style="background-color: #f9f9f9;">
							<td style="border-top: 0px">
								<div class="col-sm-12" style="color: #3D9DF7">
									<span style="color: #3D9DF7"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;缺勤次数：${xslist.qq}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;缺勤率：${xslist.qql}% </span>
								</div>
							</td>
						</tr>
						<tr style="background-color: #ECFFFF">
							<td>
								<div class="col-sm-12">
									<div class="row">
										<div class="col-xs-6">
											<span class="glyphicon glyphicon-education" style="color: #3D9DF7"></span> &nbsp;班级名称
										</div>
										<div class="col-xs-6">
											<span class=""><small>${xslist.bjmc }</small></span>
										</div>
									</div>
								</div>
							</td>
						</tr>
						</c:forEach>
				</table>
				<table class="table table-striped" style="text-align: center">
					<tr>
						<td class="col-xs-4 ziti" style="width: 20%"><small style="color: #3D9DF7">课程</small></td>
						<td class="col-xs-4 ziti" style="width: 25%"><small style="color: #3D9DF7">时间</small></td>
						<td class="col-xs-4 ziti" style="width: 20%"><small style="color: #3D9DF7">地点</small></td>
						<td class="col-xs-4 ziti" style="width: 15%"><small style="color: #3D9DF7">考勤</small></td>
						<td class="col-xs-4 ziti" style="width: 20%"><small style="color: #3D9DF7">备注</small></td>
					</tr>
					<c:forEach var="list" items="${list }">
						<tr>
							<td><small> ${list.kcmc} </small></td>
							<td><small>${list.skrq}</small></td>
							<td><small>${list.skdd}</small></td>
							<c:choose>
											<c:when test="${list.sfkq == 1}">
												<td><font size="2px"
													style="color: #0000FF">是</font></td>
											</c:when>
											<c:otherwise>
												<td ><font size="2px"
													style="color: #FF0000">否</font></td>
											</c:otherwise>
										</c:choose>
							<td><small>${list.bz}</small></td>
<%--					<td><small><a href="<%=path%>/wfw/ZsXskq/Query?id=${list.id}&openId=${openId}">查看</a></small></td>
						 --%>	
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
								<button class="btn btn-default btn-block btn-lg ng-binding more" onclick="loadMore('${pages}','${openId }');" style="position: relative; bottom: 0px;">加载更多</button>
							</div>
						</td>
					</tr> 
				</c:if>
			</div></div>
    
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
function loadMore(page,openId) {
	var url="";
	i=page;
	i++;
		url ="<%=path%>/wfw/ZsXskq/toFdyDate";
		var html = "";
		$
				.ajax({
					url : url,
					data : {
						pages : i,
						openId : openId
					},
					type : "get",
					success : function(data) {
						if (data.length !== 0) {
							var rst = eval(data);
							$.each(rst,function(i, value) {
								if(value.sfkq == 1){
												html +="<table class='table table-striped' style='text-align: center'>"
													    +"<tr>"
														+ "<td class='col-xs-4 ziti' style='width:20%'>"
														+ "<small>"
														+ value.kcmc
														+ "</small>"
														+ "</td>"
														+ "<td class='col-xs-4 ziti' style='width:25%'>"
														+ "<small>"
														+ value.skrq
														+ "</small>"
														+ "</td>"
														+ "<td class='col-xs-4 ziti' style='width:20%'>"
														+ "<small>"
														+ value.skdd
														+ "</small>"
														+ "</td>"
														+"<td><font size='2px'style='color: #0000FF'>是</font></td>"
														+ "<td class='col-xs-4 ziti' style='width:20%'>"
														+ "<small>"
														+ value.bz
														+ "</small>"
														+ "</td>"
														+ "</tr>"
														+"</table>";
								}else{
									html +="<table class='table table-striped' style='text-align: center'>"
									    +"<tr>"
										+ "<td class='col-xs-4 ziti' style='width:20%'>"
										+ "<small>"
										+ value.kcmc
										+ "</small>"
										+ "</td>"
										+ "<td class='col-xs-4 ziti' style='width:25%'>"
										+ "<small>"
										+ value.skrq
										+ "</small>"
										+ "</td>"
										+ "<td class='col-xs-4 ziti' style='width:20%'>"
										+ "<small>"
										+ value.skdd
										+ "</small>"
										+ "</td>"
										+"<td ><font size='2px'style='color: #FF0000'>否</font></td>"
										+ "<td class='col-xs-4 ziti' style='width:20%'>"
										+ "<small>"
										+ value.bz
										+ "</small>"
										+ "</td>"
										+ "</tr>"
										+"</table>";
								}
					})
						$('.-ft').before(html);
						$('.more').removeAttr("onclick");
						$('.more').attr("onclick",
								"loadMore('" + i + "','" + openId + "')");
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
</html>
