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
<script type="text/javascript" src="<%=path%>/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/My97DatePicker/lang/zh-cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<style type="text/css">
	.anniu{top:1.1%;right:1%; }
    .anniu img{display:block;width:100%;height:6.9%;} 
</style>
<title>流程信息</title>
</head>
<body style="overflow: auto;">
	<div class="main" style="width: 100%;hight:100%">
		<div class="DYtop">
			<img style="width: 100%;" src="<%=path%>/resources/img/wzy/logo.png" />
			<div class="anniu">
					<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}"
					> <img
					src="<%=path%>/resources/img/wzy/FH.png" />
				</a>
			</div>
		</div>

<%-- 		<div class="middle" style="padding-bottom: 2px;position: relative;">
			<h1 >流程信息</h1>
			<div class="anniu">
				<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}"
					style="float: left; width: 40px; height: 50px;"> 
			    <img style="width: 60%;heght: 20px;" src="<%=path%>/resources/img/zyan.png" />
				</a>
			</div>
		</div> --%>

		<div class="bottom" style="width: 100%;border:0;">
			    <div class="1" style="width: 90%;hight:100%;float: left;">
			    <p></p>
                </div>
			    
			    <div>
			   		<HR width="100%" color=#987cb9 style="margin-top:12px;margin-bottom:10px;" >
			    </div>
			
			 <div  class="3" style="width: 100%;">
			        <div>
			        <a href="<%=path%>/wzy/ZyLcxx/toSqxx?openId=${openId}" style="text-decoration:none">
			          <div>
			            <div class="img" style="width: 50%; height: 80%;text-align:center;">
					    	<img src="<%=path%>/resources/img/wdsq1.gif" style="width:  55px; height: 55px;"/>
					    	<span style="width:100%;height:100%;color:black;margin-left:15px;"><b>需我审批</b></span>
				        </div>
			           </div>
				      </a>
			        </div>
			    </div>
			      
			    <div>
			    <HR width="100%" color=#987cb9 style="margin-top:10px;margin-bottom:10px;" >
			    </div>
			    
			    <div>
			    <HR width="100%" color=#987cb9 style="margin-top:10px;margin-bottom:10px;" >
			    </div>
			    
			  <div class="4" style="width: 100%;">
			        <div>
			        <a href="<%=path%>/wzy/ZyLcxx/toCcjl?openId=${openId}" style="text-decoration:none">
			          <div>
			            <div class="img" style="width: 50%; height: 80%;text-align:center;">
					    	<img src="<%=path%>/resources/img/spsq.gif" style="width:  55px; height: 55px;"/>
					    	<span style="width:100%;height:100%;color:black;margin-left:15px;"><b>出差申请</b></span>
				        </div>
			          </div>
				    </a>
			        </div>
			    </div>
			    
			    <div>
			   		<HR width="100%" color=#987cb9 style="margin-top:10px;margin-bottom:10px;" >
			    </div>
			    
		</div>
	</div>
</body>
</html>