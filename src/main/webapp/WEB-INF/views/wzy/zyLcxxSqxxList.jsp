<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>  
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link href="<%=path%>/resources/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
<style type="text/css">
	.anniu{top:1.1%;right:1%; }
    .anniu img{display:block;width:100%;height:6.9%;} 
</style>
<script type="text/javascript">
    function return_prepage()  
    {  
    if(window.document.referrer==""||window.document.referrer==window.location.href)  
    {  
    window.location.href="{dede:type}[field:typelink /]{/dede:type}";  
    }else  
    {  
    window.location.href=window.document.referrer;  
    }  
      
    }  
</script>
<title>审批信息</title>
</head>
<body style="overflow: auto;">
	<div class="main">
		<div class="DYtop">
			<img style="width: 100%;" src="<%=path%>/resources/img/wzy/logo.png" />
			<div class="anniu">
					<a href="#" onclick="return_prepage()"
					> <img
					src="<%=path%>/resources/img/wzy/FH.png" />
				</a>
			</div>
		</div>

		<div class="middle" style="padding-bottom: 2px;position: relative;">
			<h1 style="color: #0D91DE"><b>审批信息</b></h1>
			<%-- <div class="anniu">
					<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}"
					style="float: left; width: 40px; height: 50px;"> <img
					style="width: 60%;heght: 20px;" src="<%=path%>/resources/img/zyan.png" />
				</a>
			</div> --%>
		<%-- 	<div class="return">
				<a href="<%=path%>/wzy/ZyLcxx/toJsxxPage?openId=${openId}"
					style="float: left; width: 40px; height: 50px;"> <img
					style="width: 60%;heght: 20px;position:relative;bottom: 120%; left: 300%;" 
					src="<%=path%>/resources/img/return.png" />
				</a>
			</div> --%>
			
		</div>

		<div class="bottom" style="position: relative;">
			<div class="h"></div>
			<div>
			<div style="position: relative;" align="center">
			<p></p>
			<table border="2" style="width: 100%;height: 30px;" bordercolor="#E0E0E0" class="table table-striped table-hover table-bordered">
			<tr>
			   <td align="center" style="height: 37px;"><B>序号</B></td>
			    <td align="center"><B>申请人</B></td>
			     <td align="center"><B>申请人班级</B></td>
			      <td align="center"><B>请假日期</B></td>
			        <td align="center"><B>操作</B></td>
			</tr>
		<c:forEach var="data" items="${list}" varStatus="status">
			<tr>
			   <td align="center" style="height: 37px;">${status.count}</td>
			   <td align="center">${data.fqrxm}</td>
			   <td align="center">${data.bmmc}</td>
			   <td align="center">${data.kssj}</td>
			   <td align="center"><a class="edit" 
			    href="<%=path%>/wzy/ZyLcxx/toSqxxXq?id=${data.id}&openId=${openId}">详情</a></td>
			</tr>
		    </c:forEach>
			</table>
			</div>
		</div>
	</div>
	
</body>
</html>