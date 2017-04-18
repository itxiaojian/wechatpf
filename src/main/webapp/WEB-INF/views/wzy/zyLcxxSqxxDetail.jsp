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
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
<title>申请详情</title>
<script type="text/javascript">
  function save(){
	var id = $('#idInput').val();
	var shyj=$('#shyj').val();
	if(shyj==null || shyj==""){
		alert("请选择审核意见!");
		return false;
	}
	var openId = $('#openId').val();
	var url="";
	var msg="";
		url="<%=path%>/wzy/ZyLcxx/shtg";
		msg="确定审核通过吗？";
	if (confirm(msg)) {
		//getMrjh();
		$.ajax({
			cache : true,
			type : "POST",
			url : url,
			data : {
				id:id,
			    shyj:shyj,
			    openId:openId
			},
			async : false,
			error : function(request) {
				alert("保存失败，请联系管理员。");
			},
			success : function(data) {
				if(data=='1'){
					alert('保存成功！'); 
					location.href="<%=path%>/wzy/ZyLcxx/toSqxx?openId="+openId;
				}else{
					alert("保存失败，请联系管理员。");
					return false;
				}
			}
		});
	}
}
  
function bh(){
		var id = $('#idInput').val();
		var shyj=$('#shyj').val();
		if(shyj==null || shyj==""){
			alert("请选择审核意见!");
			return false;
		}
		var openId = $('#openId').val();
		var url="";
		var msg="";
			url="<%=path%>/wzy/ZyLcxx/bh";
			msg="确定驳回吗？";
		if (confirm(msg)) {
			//getMrjh();
			$.ajax({
				cache : true,
				type : "POST",
				url : url,
				data : {
					id:id,
				    shyj:shyj,
				    openId:openId
				},
				async : false,
				error : function(request) {
					alert("保存失败，请联系管理员。");
				},
				success : function(data) {
					if(data=='1'){
						alert('保存成功！'); 
						location.href="<%=path%>/wzy/ZyLcxx/toSqxx?openId="+openId;
					}else{
						alert("保存失败，请联系管理员。");
						return false;
					}
				}
			});
		}
	}
</script>
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
</head>
<body style="overflow: auto;">
	<div class="main">
		<div class="DYtop">
			<img style="width: 100%;" src="<%=path%>/resources/img/BT.jpg" />
			<div class="anniu">
					<a href="#" onclick="return_prepage()"
					> <img
					 src="<%=path%>/resources/img/wzy/FH.png" />
				</a>
			</div>
		</div>

		<div class="middle" style="padding-bottom: 2px;position: relative;">
			<h1 style="color: #0D91DE"><b>申请详情</b></h1>
			<%-- <div class="anniu">
				<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}"
					style="float: left; width: 40px; height: 50px;"> <img
					style="width: 60%;heght: 20px;" src="<%=path%>/resources/img/zyan.png" />
				</a>
			</div> --%>
			<%-- <div class="return">
				<a href="<%=path%>/wzy/ZyLcxx/toJsxxPage?openId=${openId}"
					style="float: left; width: 40px; height: 50px;"> <img
					style="width: 60%;heght: 20px;" src="<%=path%>/resources/img/return.png" /> 
				</a>
			</div> --%>
			
		</div>

		<div class="bottom" style="position:relative;height:350px;">
			<div class="h"></div>
			<div>
			<p></p>
			<div style="position: relative;" align="center">
			<p></p>
			<table border="2" style="width: 100%;height:250px;" bordercolor="#E0E0E0">
			<c:forEach var="data" items="${list}" varStatus="status">
			<input type="hidden" value="${data.id}" id="idInput" />
			<input type="hidden" value="${openId}" id="openId" />
			<tr>
			   <td align="center" style="">申请类型：</td>
			    <td align="center">${data.lcmc}</td>
			     <td align="center" style="">班级名称：</td>
			    <td align="center">${data.bmmc}</td>
			</tr>
			<tr>
			    <td align="center" style="">申请人：</td>
			     <td align="center">${data.fqrxm}</td>
			       <td align="center">申请状态：</td>
			    <td align="center">${data.lcztmc}</td>
			</tr>
			<tr>
			     <td align="center">开始时间：</td>
			     <td align="center">${data.kssj}</td>
				 <td align="center">结束时间：</td>
			     <td align="center">${data.jssj}</td>
			     <td align="center"></td>
			     <td align="center"></td>
			</tr>
			<tr>
			  	<td align="center">申请理由：</td>
			  	<td  align="left" colspan="3">${data.lcnr}</td>
			</tr>
			</c:forEach>
			<tr>
		     	<td align="center">审核意见：</td><td colspan="5">
		     	<textarea style="resize:none;width:100%;"rows="2" cols="20" name="shyj" id="shyj">通过</textarea>
			    </td>
			</tr>
			</table>
			
		    <div style="text-align: center">
			<div class="panel-body">
			<input class="btn btn-success" value="通过" type="button" onclick="save();">
            <input class="btn btn-success" type="button" onclick="bh();" value="驳回">
				</div>
			</div>
		</div>
		</div>
	</div>
	
</body>
</html>


