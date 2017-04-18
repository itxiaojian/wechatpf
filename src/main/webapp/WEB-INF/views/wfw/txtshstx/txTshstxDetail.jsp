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
<style type="text/css">
	.anniu{top:1.2%;right:1%; }
    .anniu img{display:block;width:100%;height:30%;} 
    
	 img{ pointer-events: none; }
</style>
<title>图书还书提醒</title>
</head>
<body style="overflow: auto;">
   <div class="main">
<!--       <div  class="DYtop" > -->
<%--                <img style="width:100%; "  src="<%=path%>/resources/img/wfw.png" /> --%>
<!--                <div class="anniu"> -->
<!--              <a href="#" onclick="return_prepage();" style="float:right;width:40px;height:50px;" > -->
<!-- 			   <img  style="width:70%" -->
<%-- 			    src="<%=path%>/resources/img/fanhui.png" /> --%>
<!-- 			    </a> -->
<!-- 			</div> -->
<!--        </div> -->
       <div class="top">
			<img style="width: 100%;" src="<%=path%>/resources/img/wzy/logo.png" />
	
			<div class="anniu" style="position: absolute; top:1%; left: 87%;">
				<a href="#" onclick="return_prepage();"> <img 
					 src="<%=path%>/resources/img/wzy/fanhui.png" />
				</a>
			</div>
		</div>
       <div  style="font-size:80%;text-align:center;margin-top: 10px;"> 提醒时间：<fmt:formatDate value="${map.txsj }" type="date" dateStyle="medium"/> </div>
       <div class="bottom" style="margin-top: 5px;">
             <div >
                 ${map.txnr}
             </div>
       </div>
   </div>
</body>
</html>