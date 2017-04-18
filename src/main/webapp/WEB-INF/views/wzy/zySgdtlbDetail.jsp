<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
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
	<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/dongtai.css" />
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />

<title>宿管动态列表</title>
</head>
<body style="overflow: auto;" >
 <form method="post" action="<%=path%>/wzy/ZyXyxgxx/zyXyxgxxDetail" id="form1">
	<div class="main">
		<div class="DYtop">
			<img style="width:100%; " src="<%=path%>/resources/img/BT.jpg" />
			<div class="anniu">
			<a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}" style="float:right;width:40px;height:50px;" >
			   <img  style="width:70%"
			    src="<%=path%>/resources/img/zyan.png" />
			    </a>
			</div>
		</div>

		<div class="middle">
			<h1>宿管动态</h1>
		</div>
<div class="bottom">
<div> <input type="hidden"  value="${num}" id="num">
		</div>
		<div class="content">
        
        <c:forEach items="${list}" var="list" varStatus="status">
            <div class="newslist" onclick="document.location='<%=path%>/wzy/ZyXyxgxx/zyXyxgxxDetail?id=${list.id}';">
            <div class="newstitle" style="text-align: center">${list.xwbt }</div>
            <div class="newstime">时间:${list.sxsj }</div>
            <div id="divid${status.count}">
                 ${list.xwnr}
			</div>
            <div class="newsimg"  id="newsid${status.count}" style="text-align: center"></div>
            <div class="newsurl">
            <span id="t_28">显示全文>></span>
            </div>
            </div>
        </c:forEach>
        
            </div>
 </div>           
		</div>
		</form>
</body>
</html>

<script type="text/javascript">
$(function (){
	var num = $("#num").val();
	for(i=1;i<=num;i++){
		$("#divid"+i).hide();
		var val=$("#divid"+i).find("img");
		$("#newsid"+i).html(val.get(0)); 
	}	
});



    function DisplayText(NewsID) {
        var AllText = document.getElementById("c_" + NewsID);
        var SubText = document.getElementById("n_" + NewsID);
        var ButtonText = document.getElementById("t_" + NewsID);

        if (AllText.style.display == "none") {
            AllText.style.display = "block";
            SubText.style.display = "none";
            ButtonText.innerText = "隐藏全文>>";
        }
        else {
            AllText.style.display = "none";
            SubText.style.display = "block";
            ButtonText.innerText = "显示全文>>";
        }
    }
</script>