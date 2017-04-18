<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<title>幸运大转盘抽奖</title>
<link href="<%=path%>/resources/files/activity-style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/files/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="<%=path%>/resources/files/jquery.easing.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script type="text/javascript">
	$(function() {
		$("#inner").click(function() {
			lottery();
		});
	});
	function lottery() {
		var num = $("#number").val();
		if(num == 0){
			var cc = confirm('亲，您的机会已用完~');
			return false;
		}
		$.ajax({
			type : 'POST',
			url : '<%=path%>/wsh/ShWxcjjg/doWxcj',
			dataType : 'json',
			data: 'user='+$("#userName").val()+'&num='+$("#number").val(),
			cache : false,
			error : function() {
				alert('出错了！');
				return false;
			},

			success : function(json) {
				$("#inner").unbind('click').css("cursor", "default");
				var angle = parseInt(json.angle); //角度 
				var msg = json.msg; //提示信息
				$("#outer").rotate({ //inner内部指针转动，outer外部转盘转动
					duration : 5000, //转动时间 
					angle : 0, //开始角度 
					animateTo : 360 + angle, //转动角度 
					easing : $.easing.easeOutSine, //动画扩展 
					callback : function() {
						var number = parseInt(json.num);	//剩余次数
						$("#number").val(number);
						$("#num").val(number);
						var con = confirm(msg + '\n还要再来一次吗？');
						if (con) {
							lottery();
						} else {
							$("#inner").click(function() {
								lottery();
							});
							return false;
						}
					}
				});
			}
		});
	}
</script>
</head>

<body class="activity-lottery-winning">
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="微信抽奖">
	<a href="#" target="content" onfocus="this.blur()"><span>微信抽奖</span></a>
	</li>
</ul>
</div>
	<div class="main">
		<div id="outercont">
			<div id="outer-cont" style="overflow:hidden;">
				<div id="outer">
					<img src="<%=path%>/resources/files/activity-lottery-1.png" width="310px">
				</div>
			</div>
			<div id="inner-cont">
				<div id="inner">
					<img src="<%=path%>/resources/files/activity-lottery-2.png">
				</div>
			</div>
		</div>
		<input type="text" id="userName" value="${openId }" class="hiddenInput" />
		<input type="text" id="number" value="${num }" class="hiddenInput" />
		<div class="boxcontent boxyellow">
		<div class="box">
		<div class="title-green"><span>奖项设置：</span></div>
		<div class="Detail">
		<p>一等奖：${map.ydjmc }。奖品数量：${map.ydjsl } </p>
		<p>二等奖：${map.edjmc }。奖品数量：${map.rdjsl } </p>
		<p>三等奖：${map.sdjmc }。奖品数量：${map.sdjsl } </p>
		</div>
		</div>
		</div>
		<div class="boxcontent boxyellow">
		<div class="box">
		<div class="title-green">活动说明：</div>
		<div class="Detail">
			   <p>本次活动每人可以转 ${map.cjcs } 次 </p>
			   <p>还可以转 <input id="num" type="text" value="${num }" /> 次 </p>
		</div>
		</div>
		</div>
		<div class="boxcontent boxyellow">
		<div class="box">
		<div class="title-green">抽奖结果：</div>
		<div class="Detail">
			<c:forEach var="data" items="${list}" varStatus="obj">
			   <p>${obj.count }.${data.czjx }</p>
			</c:forEach>
		</div>
		</div>
		</div>
	</div>

</body>
</html>