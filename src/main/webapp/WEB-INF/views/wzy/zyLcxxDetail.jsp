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
<title>流程信息</title>
</head>
<body style="overflow: auto;">
   <div class="main">
      <div  class="top" >
               <img style="width:100%; "  src="<%=path%>/resources/img/BT.jpg" />
       </div>
       
       <div class="middle">
			<c:choose>
				<c:when test="${map.lczl =='1'}"><h1>出差申请</h1></c:when>
				<c:when test="${map.lczl =='2'}"><h1>请假申请</h1></c:when>
				<c:when test="${map.lczl =='3'}"><h1>调课申请</h1></c:when>
				<c:otherwise><h1>报修</h1></c:otherwise>
			</c:choose>
       </div>
       
       <div class="bottom">
             <div class="h">
                <h2>${map.lcmc}</h2>
             </div>
             <div  style="font-size:80%;text-align:center;margin-top: -7px;"> 来源单位：${map.bmbh }&nbsp;&nbsp;&nbsp;发布时间：<fmt:formatDate value="${map.kssj }" type="date" dateStyle="medium"/></div>
             <div >
                 ${map.lcnr}
             </div>
       </div>
   </div>
</body>
</html>