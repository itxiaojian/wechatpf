<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1"></meta>
		
<title>上墙信息</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/jquery-ui-custom.min.css">
<%-- 	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/core.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/home.css"> --%>


<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
<script type="text/javascript">
	function toWsq(id,openId){
		var url = '<%=path%>/wsh/ShWsq/saveCyr';
		$.ajax({
			url: url,
			type : 'post',
			dataType : 'json',
			data:{id:id,openId:openId},
			success: function(data){
				if(data.message != null || data.message != ''){
					location.href ="<%=path%>/wsh/ShWsq/toWsqYhfb?id="+id+"&openId="+openId+"&cyrid="+data.message;
				}else{
				}
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
	}
</script>
<style type="text/css">
	body {
	    background-color: #e6e6e6;
	    color: #333;
	    font-family: "Microsoft Yahei","宋体","helvetica","Arial";
	    font-size: 14px;
	    margin: 0;
    	padding: 0;
    	line-height: 18px;
	}
	.container::before, .container::after {
	    content: "";
	    display: table;
	}
	.container::after {
	    clear: both;
	}
	.container::before, .container::after {
	    content: "";
	    display: table;
	}
	.container {
	    background-color: white;
	    height: 80%;
	    margin-left: auto;
	    margin-right: auto;
	    margin-top: 20px;
	    padding: 20px 0;
	    width: 80%;
	}
	.container .right-content {
	    background-color: #fff;
	    padding: 25px 20px;
	}
	
/* 	:link, *:visited, ins { */
/* 	    text-decoration: none; */
/* 	} */
	a {
	    cursor: pointer;
	}
	a {
	    color: #0866ae;
	}
</style>
</head>
	
	<body style="height: 500px;">
		<div class="container">
			<div class="right-content" style="text-align: center;">
				欢迎参加【<span style="color: red;">${wsq.hdmc}</span>】上墙现场，请<span><a href="javascript:void(0)" onclick="toWsq('${wsq.id}','${openId}');">点击签到</a></span>进行签到，签到成功后即可参与上墙。
			</div>
		</div>
		
	
	</body>
</html>