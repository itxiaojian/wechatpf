<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>

<html eiiebffcjbffiheggaebebgceaeccbia_b="1" bdgjjgagedbdaebhbjbcabcdgeeebecf_b="1" idceifdedfeiefjgfcjfbchejgbcbeid_b="1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>校车信息</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script><style type="text/css"></style><style type="text/css"></style><style 

type="text/css"></style>
<script type="text/javascript">
	function query(){
		var keyWord = $("#bt").val();
		if(keyWord == null || keyWord == ""){
			alert("请输入关键词查询!");
			return false;
		}else{
			$("#myForm").submit();
		}
	}
	
	function ApplyStyle(s){
		  document.getElementById("mytab").className=s.innerText;
		 }
		 
		 $(function(){
		       addHover('mytab');
		 });
		 
		    /**
		  * 在鼠标悬停时突出显示行--jQuery处理表格  
		  *
		  * @tab table id
		  */
		 function addHover(tab){
		    $('#'+tab+' tr').hover(
		       function(){
		          $(this).find('td').addClass('hover');
		       },
		    function(){
		       $(this).find('td').removeClass('hover');
		    }
		    );
		 }
</script>
<style type="text/css"> 
.hover{
   background-color: #53AB38;
   color: #fcc;
}
	.style1{
		margin: 0px 5%;
		padding-top: 10px;
		padding-bottom: 5px;
	}
	.style2{
		text-align: center;
		margin: 10px 0px;
	}
	.style3{
		background:url("<%=path%>/resources/images/bg1.png") repeat-x;
		height: 30px;
		padding-top: 7px;
	}
	.style3 a{
		margin-right: 10%;
	}
	.style3 .selected{
		color: #37b0c9;
	}
	.style4{
		margin: 20px 5%;
	}
	.maring1{
		margin-bottom: 30px;
	}
	.style4 p{
		margin-bottom: 5px;
		font-size: 14px;
		color: #323e48;
	}
	.style4 .title{
		font-size: 16px;
	}
	.bg1{
		background-color: #37b0c9;
	}
	.font1{
		font-weight: bold;
		color: white;
		font-size: 16px;
	}
	.input1{
		width:60%;
		margin-left:5%;
		height: 30px;
	}
	.color1{
		color: #37b0c9;
	}
	
	.tr_css{
	    background-color: #DDF9F9;
        font-weight: lighter;
        padding: 12px 0;
        font-size: 10px;
	}
	
	td{
	font-size: 14px;
	width:130;
	text-align: center;
	}
</style>
</head>

<body id="cardunion" class="mode_webapp2" style="background-color: white;">

<c:if test="${!empty list}"> 
   <table   class="tab_css_1" id="mytab"  border="0" cellspacing="1">
              <thead>
                <tr class="tr_css">
                  <td height="40">车次/编号</td>
                  <td height="40">车牌号</td>
                <!--   <td height="40">出发站/目的地</td> -->
                  <td height="40">停靠站</td>
                  <td height="40">出发时间/到达时间</td>
                  <!-- <td width="130">目的地</td> -->
                <!--   <td width="40">状态</td> -->
               	 </tr>
              </thead>
              <tbody>
               <c:forEach var="map" items="${list}" varStatus="status">
                <tr class="tr_css1">
                  <td style="color:blue;" bgcolor="#FFFFFF"	width="60" >${status.index+1}</td>
                  <td bgcolor="#FFFFFF">${map.cph}</td>
                  <%-- <td bgcolor="#FFFFFF"><img src="<%=path%>/resources/img/startup.png"/>${map.cfd} 
                  	<br/> <img src="<%=path%>/resources/img/ended.png"/>${map.mdd}</td> --%>
                  <c:choose>
   						<c:when test="${status.index ==0}">   
   								<td style="text-align:left;"bgcolor="#FFFFFF"><img src="<%=path%>/resources/img/startup.png"/>${map.cfd}</td>
   						</c:when>  
   						<c:when test="${status.last == true}">   
   								<td style="text-align:left;"bgcolor="#FFFFFF"><img src="<%=path%>/resources/img/ended.png"/>${map.mdd}</td>
   						</c:when>  
   						<c:otherwise>  
   						 		<td style="text-align:left;"bgcolor="#FFFFFF"><img src="<%=path%>/resources/img/luguo.png"/>${map.mdd}</td>
   					</c:otherwise> 
                </c:choose>
                  <td bgcolor="#FFFFFF" style="font-weight:bolder;">${map.cfsj}<br/><font style="font-weight:lighter;">${map.ddsj}</font></td>
                  <%-- <td width="130" bgcolor="#FFFFFF">${map.mdd}</td> --%>
                 <%--  <td width="40" bgcolor="#FFFFFF">${map.zt}</td> --%>
                </tr>
                </c:forEach>	 
              </tbody>
            </table>
</c:if>
</body></html>