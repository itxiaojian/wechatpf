<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html class="gecko firefox firefox40 win js">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=10">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js"
	type="text/javascript"></script>
<title>图文信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"  href="<%=path%>/resources/css/GJSW.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/bootstrap.min.css">
</head>
<style type="text/css">
.WZY_main01{ width:100%;position:absolute; top:50px; left:0px; bottom:32px;overflow:auto; background-color:#F0F0F0;}
.msg_banner { width:100%;height:140px;no-repeat center; background-size:cover; position:relative; margin-bottom:20px;float:left}
.msg_banner img{width:100%;height:140px; }
.msg_banner_title{ position:absolute; bottom:0; left:0; width:100%; height:30px; background-color:#4F4F4F; opacity:0.8; line-height:30px; color:#fff; padding-left:10px;}
.msg_banner_title a{color:#FFFFFF;}
.msg_Dome{ width:100%; border:1px solid #ccc; border-radius:10px; padding:6px; margin:auto; margin-top:30px; float:left; background-color:#fff;}
.msg_Dome_news li{ width:100%;padding-bottom:10px; border-bottom:#ccc 1px solid; float:left; padding-top:10px;}

.new_text{ width:80%; float:left;}
.new_photo{ width:20%; float:right; text-align:right}
.new_main{ margin-top:10px;}

</style>
<body>
<div class="iphone">
	<div class="WZY_top">
    </div>
    <div class="WZY_main01">
        <div class="container">
        	<div class="msg_Dome">
                    <div class="msg_banner">
                    <c:forEach var="data" items="${bts}" varStatus="obj">
                        <c:if test="${data.px==1}">
                        <img src="<%=path%>/util/file/getFile?imgId=${data.sltid}">
                        </c:if>
                        </c:forEach>
                        <span class="msg_banner_title" >
                        <c:forEach var="data" items="${bts}" varStatus="obj">
                        <c:if test="${data.px==1}">
                        <a href="<%=path%>/weixin/twxx/twxxnr?id=${data.id }">
                        ${data.xxbt}
                        </a>
						</c:if>
						</c:forEach>	
                        </span>
                    </div>
                <div class="msg_Dome_news">
                	<ul>
                	 <c:forEach var="data" items="${bts}" varStatus="obj">
                	  <c:if test="${data.px!=1}">
                    	<li>
                    	<a href="<%=path%>/weixin/twxx/twxxnr?id=${data.id }">
                    	<span class="new_text"> 
                    	${data.xxbt }
						</span>
						<span class="new_photo"><img width="40" height="30" src="<%=path%>/util/file/getFile?imgId=${data.sltid}">
						</span>
						</a> 
						</li>
						</c:if>
                         </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="WZY_foot">
    </div>
</div>
</body>
<script type="text/javascript">
function toMx(id){
	location.href="<%=path%>/weixin/twxx/Twxxnr?id="+id;
}
</script>
</html>
