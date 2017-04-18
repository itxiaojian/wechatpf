<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1">
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<title>会议详情</title>
<script src="<%=path%>/resources/js/wzy/jquery.js"></script>
<script src="<%=path%>/resources/js/wzy/json2.js"></script>
<link type="text/css" href="<%=path%>/resources/css/tucao.css"
	rel="stylesheet" />
<link type="text/css" href="<%=path%>/resources/css/page.css"
	rel="stylesheet" />
	<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
	<link rel="stylesheet" href="<%=path%>/resources/js/wsh/styles.css" />
<style>
#replay {
	z-index: 1000;
	display: none;
}
</style>
 <style type="text/css">
	.anniu{top:1.2%;right:1%; }
    .anniu img{display:block;width:100%;height:30%;} 
    
  .zhanti_main{ width:100%;position:absolute; top:8%; left:0px; bottom:20px;overflow:auto;background-color:#fff;}
  .zhanti_title{ width:100%; height:7%;font-size:16px; line-height:35px; color:#333; }
  .zhanti_title img{ float:left;}
  .zhanti_mainBox p{font-size:12px;text-align:center;color:#333; float:left; width:90%; padding-left:5%;padding-right:5%;}
  .zhanti_mainBox img{ margin-top:40px;padding-bottom:40px;}
  .zhanti_pinglun img{ float:left; margin-left:30px;}
  .zhanti_pinglun ul{ margin-top:20px;}
  .zhanti_pinglun li{ width:100%; float:left; margin-top:1%;}
  .pL_name{ line-height:20px; font-size:16px; color:#2991e6;float: left;}
  .shijian{ float:right; color:#999;line-height:20px; font-size:10px; margin-right:20px;}
  .pL_main{ line-height:30px; font-size:12px; color:#333; clear:both; padding-left:18%; border-bottom:1px #ccc solid;padding-right:10%;padding-bottom:5px; }
  .WZY_foot{width:100%; /**height:38px;*/ position:fixed; left:0px;bottom:0;overflow:hidden;}
 </style>
</head>
<body >
	<div id="test"></div>
	<div id="header" class="header">
		<img src="<%=path%>/resources/img/wzy/logo.png" style="width: 100%;height:50px;" />
		<div class="anniu">
			<a href="javascript:history.go(-1);">
			   <img 
			    src="<%=path%>/resources/img/wzy/FH.png"/>
			    </a>
			</div>
	</div>
	
	<div class="content" style="padding-bottom: 1em;">
	<input type="hidden" id="openid" value="${openId}">
		<c:forEach items="${list}" var="list" varStatus="obj">
			<div id="lidw${obj.count }" class="tclist">
				<div class="tcheader">
				</div>
				<div class="tccontent" style="padding-left: 0px;">
				   <div class="zhanti_title">
        	          <img src="<%=path%>/resources/img/wzy/zttlbt.png" style="width:10%;"/>我的历史会议
                   </div>
				   <h1 style="text-align:center; font-size:16px; margin-top:2%; color:#333; float:left; width:100%; margin-bottom:2%;border-bottom:1px #ccc solid;">${list.hybt }</h1>
                   <h2 style="text-align:center; font-size:10px; margin-top:2%; color:#2991e6; float:left; width:100%; padding-bottom:2%;">创建人：${list.cjr }&nbsp;&nbsp;开始时间：${list.kssj }</h2>
                   <p>${list.hynr }</p>
				   <div class="tcbutton">
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<div class="WZY_foot">
	  <img src="<%=path%>/resources/img/wzy/BQ.png" style="width:100%;">
	</div>

</body>
</html>