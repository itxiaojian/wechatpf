<%@page contentType="text/html;charset=UTF-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
   <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>  
    <link href="<%=path%>/resources/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
    <link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/xyxw.css" />
   	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
    /* 返回首页 */
	function shouye() {
		WeixinJSBridge.call("closeWindow");
	} 
    </script>
<title>公司荣誉</title>
</head>
<body style="overflow: auto;">
   <div class="main">
      <div  class="DYtop" >
             <img style="width:100%; "  src="<%=path%>/resources/img/gslogo.png" />
             <div class="anniu" style="position: absolute; top:3%; left: 87%;">
			<a href="#" style="float:right;width:40px;height:50px;" onclick="shouye()" >
			   <img  style="width:70%"
			    src="<%=path%>/resources/img/syan.png" />
			    </a>
			</div>
       </div>
       
       <div class="middle">
<%-- 			<c:choose>
				<c:when test="${map.xwlx =='1'}"><h1>院部新闻</h1></c:when>
				<c:otherwise><h1>校内新闻</h1></c:otherwise>
			</c:choose> --%>
       </div>
       
       <div class="bottom">
             <div class="h">
                <h2>${map.ClassName}</h2>
             </div>
             <div  style="font-size:80%;text-align:center;margin-top: -7px;"> &nbsp;&nbsp;&nbsp;发布时间：${map.uptime} </div>
             <div >
                 ${map.content}
             </div>
       </div>
   </div>
</body>
</html>