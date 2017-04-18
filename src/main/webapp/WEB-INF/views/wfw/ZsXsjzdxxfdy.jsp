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
<title>奖助贷</title>
<link href="<%=path%>/resources/css/wfw/jzdstyle.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/wfw/bootstrap.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function query1(lx){
        var objS = document.getElementById("pid");
        var openId = document.getElementById("openId").value;
        var grade = objS.options[objS.selectedIndex].value;
        location.href="<%=path%>/wfw/ZsXsjzdxx/toCxjzdxx?classname="+grade+"&openId="+openId+"&lx="+lx;
   	} 
	function query2(lx){
        var objS = document.getElementById("pid1");
        var openId = document.getElementById("openId").value;
        var grade = objS.options[objS.selectedIndex].value;
        location.href="<%=path%>/wfw/ZsXsjzdxx/toCxjzdxx?classname="+grade+"&openId="+openId+"&lx="+lx;
   	} 
	function query3(lx){
        var objS = document.getElementById("pid2");
        var openId = document.getElementById("openId").value;
        var grade = objS.options[objS.selectedIndex].value;
        location.href="<%=path%>/wfw/ZsXsjzdxx/toCxjzdxx?classname="+grade+"&openId="+openId+"&lx="+lx;
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
				<div class="row">
					<div class="col-sm-12" style="padding-top: 8px;" id="KBCX_Title">
						<ul class="nav nav-tabs">
							<li role="presentation" class="active"><a>奖学金</a></li>
							<li role="presentation"><a>助学金</a></li>
							<li role="presentation"><a>贷款</a></li>
						</ul>
					</div>
					</div>

				<div id="mainbox">
					<section id="list" style="display: block">
						<div class="container">
							<div class="row">
								<div class="col-sm-12" style="border-bottom: 1px solid #e5e5e5; height: 40px; padding-top: 8px;">
									<form action="<%=path%>/wfw/ZsXsjzdxx/toCxjzdxx" method="post">
										<span style="width: 40%; margin-top: 12px;"> 班级查询:&nbsp;&nbsp; <select id="pid" style="width: 40%;">
												<c:if test="${empty bjs}">
													<option value="">无</option>
												</c:if>
												<c:if test="${!empty bjs}">
													<option value="">请选择...</option>
													<c:forEach var="data" items="${bjs}" varStatus="obj">
														<option value="${data.classname }" <c:if test="${myclassname==data.classname}">selected="selected"</c:if> >${data.classname }</option>
													</c:forEach>
												</c:if>
										</select></span> <input type="hidden" name="openId" id="openId" value="${openId }"> <input type="hidden" name="size" id="size" value="${size }"> <span> <a
											class="glyphicon glyphicon-search" href="#" style="margin-left: 14px; font-size: 14px;" onclick="query1('1');">查询 </a>
										</span>
									</form>
								</div>
							</div>
						</div>
						<c:forEach var="data" items="${list}" varStatus="obj">
							<div id="lidw${obj.count }" style="display: block;" class="maring1">
								<table class="table table-striped" style="margin-top: 20px;">
									<tr class="odd">
										<td>
											<div class="col-sm-12">
												<div class="row">
													<div class="col-xs-4">
														<c:if test="${data.xb=='男' }">
															<span class="glyphicon glyphicon-user" style="color: #3D9DF7"></span>
														</c:if>
														<c:if test="${data.xb=='女' }">
															<span class="glyphicon glyphicon-user" style="color: #F54FCB"></span>
														</c:if>
														&nbsp; ${data.xm }
													</div>
													<div class="col-xs-4">${data.bjmc }</div>
													<div class="col-xs-4">
														<span class=""> <span class="" style="color: #F4C520"></span> &nbsp; <a href="<%=path%>/wfw/ZsXsjzdxx/toZsxsjzdxxDetail?xh=${data.xh}&lx=${data.lx}&openId=${openId}">查看</a>
														</span>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</c:forEach>
						<c:if test="${empty list}">
							<div class="text">
								<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
							</div>
						</c:if>
						<c:if test="${size>9 }">
							<tr>
								<td style="position: relative;">
									<div class="-ft1" style="margin-bottom: 0rem; width: 100%;">
										<button class="btn btn-default btn-block btn-lg ng-binding more1" onclick="loadMore('${pages}','${openId }','1');" style="position: relative; bottom: 0px;">加载更多</button>
									</div>
								</td>
							</tr>
						</c:if>
					</section>
					<section>
						<div class="container">
							<div class="row">
								<div class="col-sm-12" style="border-bottom: 1px solid #e5e5e5; height: 40px; padding-top: 8px;">
									<form action="<%=path%>/wfw/ZsXsjzdxx/toCxjzdxx" method="post">
										<span style="width: 40%; margin-top: 12px;"> 班级查询:&nbsp;&nbsp; 
										<select id="pid1" name="jxj" style="width: 40%;">
										
												<c:if test="${empty bjs}">
													<option value="">无</option>
												</c:if>
												<c:if test="${!empty bjs}">
													<option value="">请选择...</option>
													<c:forEach var="data" items="${bjs}" varStatus="obj">
														<option <c:if test="${myclassname==data.classname}">selected="selected"</c:if> value="${data.classname }">${data.classname }</option>
													</c:forEach>
												</c:if>
										</select></span>
									    <input type="hidden" name="openId" id="openId" value="${openId }"> 
										<input type="hidden" name="zxsize" id="zxsize" value="${zxsize }"> 
										<input type="hidden" name="myclassname" id="myclassname" value="${myclassname }"> 
										<span> 
										<a class="glyphicon glyphicon-search" href="#" style="margin-left: 14px; font-size: 14px;" onclick="query2('2');">查询 </a>
										</span>
									</form>
								</div>
							</div>
						</div>
						<c:forEach var="data" items="${zxlist}" varStatus="obj">
							<div id="lidw${obj.count }" style="display: block;">
								<table class="table table-striped" style="margin-top: 20px;">
									<tr>
										<td>
											<div class="col-sm-12">
												<div class="row">
													<div class="col-xs-4">
														<c:if test="${data.xb=='男' }">
															<span class="glyphicon glyphicon-user" style="color: #3D9DF7"></span>
														</c:if>
														<c:if test="${data.xb=='女' }">
															<span class="glyphicon glyphicon-user" style="color: #F54FCB"></span>
														</c:if>
														&nbsp;${data.xm }
													</div>
													<div class="col-xs-4">${data.bjmc }</div>
													<div class="col-xs-4">
														<span class=""> <span class="" style="color: #F4C520"></span> &nbsp; <a href="<%=path%>/wfw/ZsXsjzdxx/toZsxsjzdxxDetail?xh=${data.xh}&lx=${data.lx}&openId=${openId}">查看</a>
														</span>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</c:forEach>
						<c:if test="${empty zxlist}">
							<div class="text">
								<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
							</div>
						</c:if>
						<c:if test="${zxsize>9 }">
							<tr>
								<td style="position: relative;">
									<div class="-ft2" style="margin-bottom: 0rem; width: 100%;">
										<button class="btn btn-default btn-block btn-lg ng-binding more2" onclick="loadMore('${pages}','${openId }','2');" style="position: relative; bottom: 1px;">加载更多</button>
									</div>
								</td>
							</tr>
						</c:if>
					</section>
					<section>
						<div class="container">
							<div class="row">
								<div class="col-sm-12" style="border-bottom: 1px solid #e5e5e5; height: 40px; padding-top: 8px;">
									<form action="<%=path%>/wfw/ZsXsjzdxx/toCxjzdxx" method="post">
										<span style="width: 40%; margin-top: 12px;"> 班级查询:&nbsp;&nbsp; <select id="pid2" style="width: 40%;">
												<c:if test="${empty bjs}">
													<option value="">无</option>
												</c:if>
												<c:if test="${!empty bjs}">
													<option value="">请选择...</option>
													<c:forEach var="data" items="${bjs}" varStatus="obj">
														<option value="${data.classname }" <c:if test="${myclassname==data.classname}">selected="selected"</c:if>>${data.classname }</option>
													</c:forEach>
												</c:if>
										</select></span> <input type="hidden" name="openId" id="openId" value="${openId }" > <input type="hidden" name="dksize" id="dksize" value="${dksize }">
										 <span> <a class="glyphicon glyphicon-search" href="#" style="margin-left: 14px; font-size: 14px;" onclick="query3('3');">查询 </a>
										</span>
									</form>
								</div>
							</div>
						</div>
						<c:if test="${!empty dklist}">
							<c:forEach var="data" items="${dklist}" varStatus="obj">
								<div id="lidw${obj.count }" style="display: block;">
									<table class="table table-striped" style="margin-top: 20px;">
										<tr>
											<td>
												<div class="col-sm-12">
													<div class="row">
														<div class="col-xs-4">
															<c:if test="${data.xb=='男' }">
																<span class="glyphicon glyphicon-user" style="color: #3D9DF7"></span>
															</c:if>
															<c:if test="${data.xb=='女' }">
																<span class="glyphicon glyphicon-user" style="color: #F54FCB"></span>
															</c:if>
															&nbsp;${data.xm }
														</div>
														<div class="col-xs-4">${data.bjmc }</div>
														<div class="col-xs-4">
															<span class=""> <span class="" style="color: #F4C520"></span> &nbsp; <a href="<%=path%>/wfw/ZsXsjzdxx/toZsxsjzdxxDetail?xh=${data.xh}&lx=${data.lx}&openId=${openId}">查看</a>
															</span>
														</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty dklist}">
							<div class="text">
								<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
							</div>
						</c:if>
						<c:if test="${dksize>9 }">
							<tr>
								<td style="position: relative;">
									<div class="-ft3" style="margin-bottom: 0rem; width: 100%;">
										<button class="btn btn-default btn-block btn-lg ng-binding more3" onclick="loadMore('${pages}','${openId }','3');" style="position: relative; bottom: 0px;">加载更多</button>
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
<input type="hidden" name="lx" id="lx" value="${lx}">
</body>
<script type="text/javascript">

	var lx = document.getElementById("lx").value;
	if (lx == null || '' == lx) {
		lx = 1;
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
				Oli[i].className = ""
				Osection[i].style.display = 'none'
			}
			this.className = "active"
			Osection[this.index].style.display = 'block'
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
</script>
<script type="text/javascript">
	var str = "";
</script>

<script type="text/javascript">
var i=0;
var lx = document.getElementById("lx").value;
if(lx==null||''==lx){
	lx=1;
};
function loadMore(page,openId,lx) {
	var url="";
	i=page;
	i++;
	var size = (i-1)*10;
	if(lx=='1'){
		url ="<%=path%>/wfw/ZsXsjzdxx/toFdyDate1";
	}else if(lx=='2'){
		url ="<%=path%>/wfw/ZsXsjzdxx/toFdyDate2";
	}else if(lx=='3'){
		url ="<%=path%>/wfw/ZsXsjzdxx/toFdyDate3";
	}
	var html= "";
	 $.ajax({
			url :url,
			data : {
				pages:i,
				openId:openId
			},
					type : "get",
					success : function(data) {
					if(data.length!==0){
					var rst = eval(data);
					$.each(rst,function(i,value) {
					if(value.xb=='男'){
				    html+="<div id='lidw' style='display: block;' class='maring1'>"
					+"<table class='table table-striped' style='margin-top: 20px;'>"
					+"<tr class='odd'>"
					+"<td>"	
					+"<div class='col-sm-12'>"		
					+"<div class='row'>"			
					+"<div class='col-xs-4'>"	
					+"<span class='glyphicon glyphicon-user' style='color: #3D9DF7'></span>&nbsp;&nbsp;&nbsp;"
					+value.xm
					+"</div>"			
					+"<div class='col-xs-4'>"+value.BJMC
					+"</div>"
					+"<div class='col-xs-4'>"
					+"<span class=''>"
					+"<span class='' style='color: #F4C520'>"
					+"</span> &nbsp;"
					+"<a href='<%=path%>/wfw/ZsXsjzdxx/toZsxsjzdxxDetail?xh="+value.xh+"&lx="+value.lx+"&openId="+openId+"'>查看</a>"
					+"</span>"					
					+"</div>"				
					+"</div>"
					+"</div>"
					+"</td>"
					+"</tr>"
					+"</table>"
					+"</div>";
					}else{
						 html+="<div id='lidw' style='display: block;' class='maring1'>"
								+"<table class='table table-striped' style='margin-top: 20px;'>"
								+"<tr class='odd'>"
								+"<td>"	
								+"<div class='col-sm-12'>"		
								+"<div class='row'>"			
								+"<div class='col-xs-4'>"	
								+"<span class='glyphicon glyphicon-user'style='color: #F54FCB'></span>&nbsp;&nbsp;&nbsp;"
								+value.xm
								+"</div>"			
								+"<div class='col-xs-4'>"+value.BJMC
								+"</div>"
								+"<div class='col-xs-4'>"
								+"<span class=''>"
								+"<span class='' style='color: #F4C520'>"
								+"</span> &nbsp;"
								+"<a href='<%=path%>/wfw/ZsXsjzdxx/toZsxsjzdxxDetail?xh="+value.xh+"&lx="+value.lx+"&openId="+openId+"'>查看</a>"
								+"</a>"
								+"</span>"					
								+"</div>"				
								+"</div>"
								+"</div>"
								+"</td>"
								+"</tr>"
								+"</table>"
								+"</div>";
					};
				})

					}else{
						alert("没有更多数据了！");
					}
				if(lx==1){
					$('.-ft1').before(html);
				 	$('.more1').removeAttr("onclick");
				 	$('.more1').attr("onclick","loadMore('"+i+"','"+openId+"','1')");
				}else if(lx==2){
						$('.-ft2').before(html);
					 	$('.more2').removeAttr("onclick");
					 	$('.more2').attr("onclick","loadMore('"+i+"','"+openId+"','2')");
					}else if(lx==3){
					$('.-ft3').before(html);
				 	$('.more3').removeAttr("onclick");
				 	$('.more3').attr("onclick","loadMore('"+i+"','"+openId+"','3')");
					}
					},
				error : function() {
					alert("error");
				}
	 });
}
</script>
</html>
