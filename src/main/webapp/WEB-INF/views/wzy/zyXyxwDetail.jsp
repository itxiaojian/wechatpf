<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
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
    <script type="text/javascript">var PATHNAME="<%=path%>"</script>
    <script type="text/javascript">
    function return_prepage() {  
    if(window.document.referrer==""||window.document.referrer==window.location.href) {  
    	window.location.href="{dede:type}[field:typelink /]{/dede:type}";  
    }else {  
    	window.location.href=window.document.referrer;  
    }  
      
    }  
    </script>
<title>学院新闻</title>
</head>
<body style="overflow: auto;">
   <div class="main">
      <div  class="DYtop" >
               <img style="width:100%; "  src="<%=path%>/resources/img/BT.jpg" />
               <div class="anniu">
             <a href="#" onclick="return_prepage();" style="float:right;width:40px;height:50px;" >
			   <img  style="width:70%"
			    src="<%=path%>/resources/img/fanhui.png" />
			    </a>
			</div>
       </div>
       
       <div class="middle">
			<c:choose>
				<c:when test="${map.xwlx =='1'}"><h1>院部新闻</h1></c:when>
				<c:otherwise><h1>校内新闻</h1></c:otherwise>
			</c:choose>
       </div>
       
       <div class="bottom">
             <div class="h">
                <h2>${map.xwbt}</h2>
             </div>
             <div  style="font-size:80%;text-align:center;margin-top: -7px;"> 来源单位：${map.bmbh }&nbsp;&nbsp;&nbsp;发布时间：<fmt:formatDate value="${map.sxsj }" type="date" dateStyle="medium"/> </div>
             <div >
                 ${map.xwnr}
             </div>
       </div>
   </div>
</body>
</html>