<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3"></meta>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>


<title>通讯录</title>
<script type="text/javascript">
<%-- function getValue(openId,txlxx){
	alert("txlxx");
	location.href ="<%=path%>/wfw/ZsTxl/zsTxlDetail1?openId="+openId+"&txlxx="+txlxx;
} --%>
function getValue(){
	//alert($('#appDateTime').val());
	$('#Form1').submit();
}

</script>
<style type="text/css">
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;
 line-height:38px;}
.foot_01{width:100%; height:30px; background-color:#24CDD5;position:fixed; bottom:0; left:0; overflow:hidden} 
.L_box{ width:100%;overflow:hidden;}
.L_box li{ width:100%; border-bottom:1px solid #ccc; float:left;}
.L_box span{float:left;}
.L_box img{ float:left; margin-top:5px; margin-left:5px;}
.Biao01{ width:40%;float:left;}
.Biao02{ width:50%;margin-left:10%; color:#2e87d3}
.H_color{ background-color:#ecf5fd; border-left:2px solid #2e87d3;}
.B_color{border-left:5px solid #c5e0fa;}
.main_02{width:100%; position:absolute;top:38px;left:0; bottom:38px; background-color:#fff; overflow:auto;}
</style>
</head>
<body>
	<div class="phone_01">
		<div class="top_01">
			<form action="<%=path%>/wfw/ZsTxl/zsTxlDetail1?openId=${openId}" method="POST" id="Form1">
				<span style="width:60%;padding-left:5%;font-size:10px;margin-top:-40px;">
					搜索联系人		
					<input type="text" style="font-size:15px;width:100px;height:20px;border-redius:5px;color:#2e87d3;margin-top:0px;" 
						name="txlxx" onchange="getValue();">
				</span>
			
				<span style="width:10%;">
					<img src="<%=path%>/resources/img/wzy/fh1.png" style="margin-top:3%;width:5%;float:right;margin-right:10%;" onclick="javascript:history.go(-1);">
				</span>
			</form>
		</div>
		<div class="main_02">
			<div class="L_box">
			<c:forEach var="map" items="${map1}" varStatus="obj">
				<ul style="height:40px;">
						<li class="B_color" style="">
							<span class="Biao01" style="height:40px;font-size:16px;">
								<span style="width:50%;">
									<img src="<%=path%>/resources/img/wzy/TXL.png" style="width:30px;height:30px;">
								</span>
								<span style="width:50%;margin-top:10%;">
									${map.yhxm}
								</span>
							</span>
							<span class="Biao02" style="height:30px;font-size:14px;margin-top:4%;">${map.dhhm}</span>
						</li>
				</ul>
				</c:forEach>
			</div>
		</div>
		<div class="foot_01">
			<img src="<%=path%>/resources/img/BQ.png" style="width:100%;">
		</div>
	</div>
</body>
</html>