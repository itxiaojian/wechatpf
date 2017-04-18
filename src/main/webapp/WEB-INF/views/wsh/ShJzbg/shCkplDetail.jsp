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
<script type="text/javascript" src="<%=path%>/resources/js/jquery/jquery.form.js"></script>
<title>查看评论</title>
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
	
<style type="text/css">	
   .zhanti_main{ width:100%;position:absolute; top:8%; left:0px; bottom:20px;overflow:auto;background-color:#fff;}
  .zhanti_title{ width:100%; height:7%;font-size:16px; line-height:40px; color:#333; float:left;}
  .zhanti_title img{ float:left;}
  .zhanti_mainBox h1{ text-align:center; font-size:16px; margin-top:2%; color:#333; float:left; width:100%; margin-bottom:2%;}
  .zhanti_mainBox h2{ text-align:center; font-size:10px; margin-top:2%; color:#2991e6; float:left; width:100%; padding-bottom:2%;}
  .zhanti_mainBox p{font-size:12px;text-align:center;color:#333; float:left; width:90%; padding-left:5%;padding-right:5%;}
  .zhanti_mainBox img{ margin-top:40px;padding-bottom:40px;}
  .zhanti_pinglun img{ float:left; margin-left:30px;}
  .zhanti_pinglun ul{ margin-top:20px;}
  .zhanti_pinglun li{ width:100%; float:left; margin-top:1%;}
  .pL_name{ line-height:20px; font-size:16px; color:#2991e6;float: left;}
  .shijian{ float:right; color:#999;line-height:20px; font-size:15px; margin-right:20px;}
  .pL_main{ line-height:30px; font-size:12px; color:#333; clear:both; padding-left:5%; border-bottom:1px #ccc solid;padding-right:10%;padding-bottom:5px; }
  .PL_box input{ float:left; width:250px; height:80px; font-size:36px; border:none; border-radius:10px; background-color:#e68f29; color:#fff; margin-top:30px; margin-left:40%}
 </style>
	
</head>
<body>
 
 <div class="zhanti_title" >
        <img src="<%=path%>/resources/img/wzy/zttlbt.png" style="width:3%;">讲座报告评论
 </div>
 <div class="tcreply">
		<c:forEach items="${ckpllist}" var="ckpllist">
		<div id="t_tucao_morereply_">
				<div class="tcreplaylist" style="margin-left:30px;">
					 <span class="tcreplayname"> ${ckpllist.plr }(姓名：${ckpllist.xm }&nbsp;&nbsp;账户：${ckpllist.yhid }):</span> 
					 <span class="tcreplaymsg"> ${ckpllist.plnr} </span>
					 
					 <span class="tctime" style="font-size:12px;float:right;margin-right:100px;">${ckpllist.plsj} </span>
				</div>
		</div>
		</c:forEach>
 </div>
 
 <form name="form1" method="post" action="Default.aspx" id="form1">
 <div style="float:right;margin-right:100px;margin-top:10px;">
			 <c:choose>
				<c:when test="${pages > 1}">
				   <a href="<%=path%>/wsh/ShJzbg/shCkplDetail?id=${id }&pages=${pages - 1}">上一页</a>
			    </c:when>
			    <c:otherwise>
				                     上一页
			    </c:otherwise>
			</c:choose>
				                   第${pages}页
			<c:choose>
				<c:when test="${pages <count}">
				   <a href="<%=path%>/wsh/ShJzbg/shCkplDetail?id=${id }&pages=${pages +1}">下一页</a>
				</c:when>
				<c:otherwise>
				                    下一页
				</c:otherwise>
			 </c:choose>总共${count}页					
 </div>
 </form>
 <br>
 
 <div  style="margin-top:10px;margin-left:35%;">
 		 <a href="javascript:;history.back()">
 		 <input style="width: 70px;height: 30px;float: left;margin-left: 100px;border-radius: 5px;margin-top: 50px;font-size: 16px;color: #fff;background-color:#B4CFED;" type="button" value="返回" /></a>
 </div>
<!--  </form> -->

</body>
</html>
<script type="text/javascript">
function agree(){
	$("form1").submit;
}


</script>