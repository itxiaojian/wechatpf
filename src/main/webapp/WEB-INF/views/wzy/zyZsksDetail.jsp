<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,target-densitydpi=device-dpi" /> 
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/wzy/zszl.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<title>${listTitle}</title>
</head>
<script>
	document.getElementsByTagName('html')[0].style.fontSize=window.screen.width/10+'px';
	function doSub(num){
		if(num!=''&&num!=null){
			document.getElementById('zhuti').style.fontSize=window.screen.width/num+"px";
			var oA = document.getElementsByTagName("a");
			for(var  i = 0; i < oA.length; i++){
		        oA[i].style.fontSize=window.screen.width/num+"px";
		    }
			var oP = document.getElementsByTagName("span");
			for(var  i = 0; i < oP.length; i++){
		        oP[i].style.fontSize=window.screen.width/num+"px";
		    }
		}
	}
</script>
<style>

</style>
<body id="zhuti" onload="doSub(23);">
<div class="phone">
<div class="logo">
	<img src="<%=path%>/resources/img/wzy/logo.png" style="height:1.3rem;">
</div>
<div class="main">
 <c:forEach items="${list}" var="list" varStatus="obj">
   <div class="XY_new">
	<h1 class="new_title">${list.xwbt}</h1>
    <div class="XY_newtime">
        <span>发布时间:${list.sxsj}</span><br/>
        <span class="textchange" style="">字号：
			<a href="javascript:;" onclick="doSub(21)">大</a>
			<a href="javascript:;" onclick="doSub(23)">中</a>
			<a href="javascript:;" onclick="doSub(25)">小</a>
		</span>
    </div>
    <div>
         <div style="padding-left:1.5%;" id="main">${list.xwnr}</div>
    </div>
    </div>
</c:forEach>
</div>
	<div class="footer" style="height:0.5rem;">
			<img src="<%=path%>/resources/img/wzy/BQ.png">
		</div>
		<div class="SY_icon">
			<a href="javascript:history.go(-1);"><img src="<%=path%>/resources/img/wzy/FH.png"></a>
		</div>
</div>
</body>
</html>
