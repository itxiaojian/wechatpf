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
<title>奖助贷</title>
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
<body style="max-width:640px;margin-left: auto;margin-right: auto;background-color:white;" onload="ScrollDiv();wx.hideOptionMenu();">
	<div class="iphone">
		<div class="WZY_top01">
			<div class="row">
				<div class="col-sm-12" style="position: relative;">
					<img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive">
					 <a href="javascript:;" onclick="javascript:history.go(-1);"> <img class="Home_btn"src="<%=path%>/resources/img/wzy/FH.png" width="40" height="40"></a>

				</div>
			</div>
		</div>
		<div class="New_main01">
			<div class="container">
				<div class="row">
					<div class="col-sm-12" style="padding-top: 8px;" id="KBCX_Title">
						<ul class="nav nav-tabs">
							<li role="presentation"><a>奖学金</a></li>
							<li role="presentation"><a>助学金</a></li>
							<li role="presentation"><a>贷款</a></li>
						</ul>
					</div>
				</div>
				<div class="container">
					<%-- 			<div class="row">
				<div class="col-sm-12"
							style="border-bottom: 1px solid #e5e5e5; height: 40px; padding-top: 8px;">
							<span>学号查询:&nbsp;&nbsp;&nbsp;</span>
							<form action="<%=path%>/wfw/ZsXsjzdxx/toZsxsjzdxx" id="myForm"
								class="wwx_f_a" method="post" style="display: inline;">
								<input type="text" class="jiangjinCX" style="margin-top: 10px;">
								<input type="hidden" name="openId" id="openId"
									value="${openId }"> <input type="hidden" name="size"
									id="size" value="${size }">
							</form>
							<span class="btn btn-info btn-xs"><a
								class="glyphicon glyphicon-search" href="#"
								style="margin-left: 10px; font-size: 14px;" onclick="query();">查询</a></span>
						</div>
					</div>
					--%>
				</div>
				<div id="mainbox">
					<section style="display: block">
						<div>
							<table class="table table-striped" style="margin-top: 20px;">
									<c:forEach var="data1" items="${xslist}" varStatus="obj">							
										<tr>
											<td>
												<div class="col-sm-12">
													<span class="glyphicon glyphicon-user" style="color: #3D9DF7"></span> &nbsp;<span style="color: #3D9DF7">${data1.xm }&nbsp;&nbsp;&nbsp;&nbsp;学号：${data1.xh } </span>
												</div>
											</td>
										</tr>
										<tr style="background-color: #f9f9f9;">
											<td style="border-top: 0px">
												<div class="col-sm-12">
													<span class="glyphicon glyphicon-education" style="color: #3D9DF7"></span> &nbsp; <span style="color: #3D9DF7">${data1.yx }&nbsp;&nbsp;&nbsp;${data1.zy }
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${data1.bjmc } </span>
												</div>
											</td>
										</tr>

										<tr style="background-color: #ECFFFF">
											<td>
												<div class="col-sm-12">
													<div class="row">
														<div class="col-xs-6" align="center">
															<span class="glyphicon glyphicon-time" style="color: #3D9DF7"> &nbsp;时间</span>
														</div>
														<div class="col-xs-6" align="center">
															<span class="glyphicon glyphicon-jpy" style="color: #3D9DF7">&nbsp;金额</span>
														</div>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								<c:if test="${empty xslist}">
									<div class="text">
										<!-- 			<p>贫困生信息暂无...</p> -->
										<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
									</div>
								</c:if>									
								<c:forEach var="data1" items="${list}" varStatus="obj">
									<tr>
										<td>
											<div class="col-sm-12">
												<div class="row">
													<div class="col-xs-6" align="center">
														<span class="" style="color: #3D9DF7"></span> &nbsp;${data1.sj }
													</div>
													<div class="col-xs-6" align="center">
														<span class="">${data1.je }</span>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</section>
					<section>
						<div>
							<table class="table table-striped" style="margin-top: 20px;">													
								<c:forEach var="data1" items="${xslist}" varStatus="obj">
										<tr>
											<td>
												<div class="col-sm-12">
													<span class="glyphicon glyphicon-user" style="color: #3D9DF7"></span> &nbsp;<span style="color: #3D9DF7">${data1.xm }&nbsp;&nbsp;&nbsp;&nbsp;学号：${data1.xh } </span>
												</div>
											</td>
										</tr>
										<tr style="background-color: #f9f9f9;">
											<td style="border-top: 0px">
												<div class="col-sm-12">
													<span class="glyphicon glyphicon-education" style="color: #3D9DF7"></span>&nbsp; <span style="color: #3D9DF7">${data1.yx }&nbsp;&nbsp;&nbsp;${data1.zy }
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${data1.bjmc } </span>
												</div>
											</td>
										</tr>

										<tr style="background-color: #ECFFFF">
											<td>
												<div class="col-sm-12">
													<div class="row">
														<div class="col-xs-6" align="center">
															<span class="glyphicon glyphicon-time" style="color: #3D9DF7"> &nbsp;时间</span>
														</div>
														<div class="col-xs-6" align="center">
															<span class="glyphicon glyphicon-jpy" style="color: #3D9DF7">&nbsp;金额</span>
														</div>
													</div>
												</div>
											</td>
										</tr>
								</c:forEach>
								<c:if test="${empty xslist}">
									<div class="text">
										<!-- 			<p>贫困生信息暂无...</p> -->
										<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
									</div>
								</c:if>								
								<c:forEach var="data1" items="${zxlist}" varStatus="obj">
									<tr>
										<td>
											<div class="col-sm-12">
												<div class="row">
													<div class="col-xs-6" align="center">
														<span class="" style="color: #3D9DF7"></span> &nbsp;${data1.sj }
													</div>
													<div class="col-xs-6" align="center">
														<span class="">${data1.je }</span>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</section>
					<section>
						<div>
							<table class="table table-striped" style="margin-top: 20px;">
									<c:forEach var="data1" items="${xslist}" varStatus="obj">							
										<tr>
											<td>
												<div class="col-sm-12">
													<span class="glyphicon glyphicon-user" style="color: #3D9DF7"></span> &nbsp;<span style="color: #3D9DF7">${data1.xm }&nbsp;&nbsp;&nbsp;&nbsp;学号：${data1.xh } </span>
												</div>
											</td>
										</tr>
										<tr style="background-color: #f9f9f9;">
											<td style="border-top: 0px">
												<div class="col-sm-12">
													<span class="glyphicon glyphicon-education" style="color: #3D9DF7"></span> &nbsp; <span style="color: #3D9DF7">${data1.yx }&nbsp;&nbsp;&nbsp;${data1.zy }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${data1.bjmc }
													</span>
												</div>
											</td>
										</tr>

										<tr style="background-color: #ECFFFF">
											<td>
												<div class="col-sm-12">
													<div class="row">
														<div class="col-xs-6" align="center">
															<span class="glyphicon glyphicon-time" style="color: #3D9DF7"> &nbsp;时间</span>
														</div>
														<div class="col-xs-6" align="center">
															<span class="glyphicon glyphicon-jpy" style="color: #3D9DF7">&nbsp;金额</span>
														</div>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								<c:if test="${empty xslist}">
									<div class="text">
										<!-- 			<p>贫困生信息暂无...</p> -->
										<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
									</div>
								</c:if>
								<c:forEach var="data1" items="${dklist}" varStatus="obj">
									<tr>
										<td>
											<div class="col-sm-12">
												<div class="row">
													<div class="col-xs-6" align="center">
														<span class="" style="color: #3D9DF7"></span> &nbsp;${data1.sj }
													</div>
													<div class="col-xs-6" align="center">
														<span class="">${data1.je }</span>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
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
	<input type="hidden" name="lx" id="lx" value="${lx}">
</body>
<script type="text/javascript">
	var lx = document.getElementById("lx").value;
	if(lx==null||''==lx){
		lx=1;
	};
	var Otop = document.getElementById('KBCX_Title');
	var Oli = Otop.getElementsByTagName('li');
	var Omb = document.getElementById('mainbox');
	var Osection = Omb.getElementsByTagName('section');
	for (var i = 0; i < Oli.length; i++) {
		Oli[i].index = i
		if (!Oli[i].onclick) {
			var flag = lx-1;
			if(i==flag){
				Osection[lx-1].style.display = 'block';
				Oli[lx-1].className = "active";
			}else{
				Oli[i].className = "";
				Osection[i].style.display = 'none';
			}
		}

		Oli[i].onclick = function() {
			for (var i = 0; i < Oli.length; i++) {
				Oli[i].className = "";
				Osection[i].style.display = 'none';
			}
			this.className = "active";
			Osection[this.index].style.display = 'block';
		}
	}

	//点击“加载更多之后”定位到最后一条数据
	function ScrollDiv() {
		var size = $("#size").val();
		if (size != null && size != '') {
			if (size <= 10) {
				//				document.getElementById('lidw1').scrollIntoView(true);
			} else if (size % 10 == 0) {
				var num = (parseInt(size / 10 - 1) * 10 + 1);
				//alert(num);
				var id = 'lidw' + num;
				document.getElementById(id).scrollIntoView(true);
			} else {
				var num = (parseInt(size / 10) * 10);
				var id = 'lidw' + num;
				document.getElementById(id).scrollIntoView(true);
			}
		}
	}

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

	$(function() {
		$("table tr:nth-child(odd)").addClass("odd-row");
		$("table td:first-child, table th:first-child").addClass("first");
		$("table td:last-child, table th:last-child").addClass("last");
	});
</script>
<script type="text/javascript">
	var str = "";
</script>
<script type="text/javascript">
	
</script>
</html>
