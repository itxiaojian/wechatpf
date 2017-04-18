<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
   <link href="<%=path%>/resources/css/tsjyxx.css" rel="stylesheet" type="text/css">
  
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3"></meta>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
  <link href="<%=path%>/resources/css/tsjyxx.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
   <script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function query(){
   	 var objS = document.getElementById("pid");
   	 var grade = objS.options[objS.selectedIndex].value;
   	 var obbj = document.getElementById("bj");
   	 var bjmc = obbj.options[obbj.selectedIndex].value;
     var openId = document.getElementById("openId").value;
     location.href="<%=path%>/wfw/ZsXsgkxx/toGkxxByQh?ksqh="+grade+"&bjmc="+bjmc+"&openId="+openId;
	}
	
	function look(xh){
	   var objS = document.getElementById("pid");
	   var grade = objS.options[objS.selectedIndex].value;
	   var openId = document.getElementById("openId").value;
	   location.href="<%=path%>/wfw/ZsXsgkxx/toGkxxDetail?xh="+xh+"&ksqh="+grade+"&openId="+openId;
	}
	

	function loadMore(pages,openId,qh,bjmc){
		i=pages;
		i++;
		var size = (i-1)*10;
		var url ="<%=path%>/wfw/ZsXsgkxx/gkjzgd";
		var html= "";
	    $.ajax({
			url :url,
			data : {
				pages:i,
				openId:openId,
				qh:qh,
				bjmc:bjmc
			},
			type : "post",
			success : function(data) {
				if(data.length>0){
				var rst = eval(data);
				$.each(rst,function(i,value){
			    html += "<li><span class='msg_main04' style='width:25%;'>"
			         +value.xm
			    	 +"</span>"
				     +"<span class='msg_main04' style='width:25%;'>"
	               	 +value.classname
	                 +"</span>"
	                 +"<span class='msg_main04' style='width:25%;'>"
	               	 +value.gks
	                 +"</span>"
	                 +"<span class='msg_main04' style='width:25%;color:blue'>"
	                 +"<a onclick='look('"+value.xh+"')'>查看</a>"
	                 +"</span></li>";
				})
			    $('.LsMore').before(html);
				$('.jzgd').removeAttr("onclick");
				$('.jzgd').attr("onclick","loadMore('"+i+"','"+openId+"','"+qh+"','"+bjmc+"')");
				}else{
					html="<div class='-ft' style='margin-top: -20px;margin-left:5%;margin-right:5%;'>"
						+"<button class='btn btn-default btn-block btn-lg ng-binding' style='position:relative;bottom:0px;'>已经是最后一页了</button>"
						+"</div>";
						$('#-ft').remove();
					    $('.-ft').before(html);
				}
				},
			   error : function() {
				alert("error");
			}
		  });
	}    
	</script>
	<title>学生挂科信息</title>
  </head>
 <style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
/* .phone_01{ width:100%; height:100%; overflow:hidden;} */
.top_01{ width:100%; height:50px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;line-height:50px;}
.top_01 select{ font-size:16px; width:53%; height:44%; border-radius:2px;margin-top:-12%;color:#2e87d3;margin-left:1%;}
.span_hz{ float:left; font-size:15px;margin-top:0.1%;}
.top_01 img{  display:block; float:left;} 
.fanhui{margin-top:4%;float:right;margin-right:-2rem;width:5%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
/**页面表格的行高*/
th,td{padding-bottom:2px;padding-top:2px;line-height:2px;}

.M_main ul{ float:left;border-bottom:1px solid #8bc0f2; padding-bottom:20px;}
.main_02{width:100%;  left:0; bottom:8px; background-color:#fff; overflow:auto;margin-bottom: 0;}
 .M_box{ width:90%;margin:auto;}
 .M_msg{ width:100%; border-radius:20px; margin-top:6%; float:left;padding-bottom:0px; margin-bottom:30px;border-left-color:1px solid #8bc0f2;border-right-color:1px solid #8bc0f2;}
 .M_title{ width:100%; height:50px; background-color:#8bc0f2; color:#fff; line-height:50px; font-size:15px; float:left;border-radius:10px 10px 0 0 ;}
 .M_title span{font-size:15px}
.M_title img{ float:left;}
.M_main{ clear:both; font-size:14px;}
.M_main li{ float:left; padding-top:5%; width:100%;}
.M_main li span{ float:left;}
.M_main ul{ float:left; padding-bottom:20px;border:#e5e5e5 1px solid;}
.msg_main04{ color:#333;text-align:center}
</style>
 
<body style="max-width:640px;margin-left: auto;margin-right: auto;background-color:white;" >
<input type="hidden" name="openId" id="openId" value="${openId }"> 
<input type="hidden" name="size" id="size" value="${size }">
<input type="hidden" name="qh" id="qh" value="${qh }">
<input type="hidden" name="bjmc" id="bjmc" value="${bjmc }">

 <div class="top_01">
      <span style="width:60%; ">
             <img src="<%=path%>/resources/img/wzy/c11-04.png" style=" margin-top:2%" width="30" height="30"> 学生挂科信息&nbsp;&nbsp;
      </span>
      <span style="width:20%;">
           &nbsp;
      </span>
      <span style="width:5%">
          <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
              <img src="<%=path %>/resources/img/wzy/fh1.png" style="width:5%;margin-top:16px;float:right;margin-right:5%;">
          </a>
      </span>
</div>
<div style="margin-top:60px;">
      <span style="padding-left:3%;font-size:14px;">班级查询&nbsp;&nbsp;
            <select id="bj" style="font-size:13px;color:#2e87d3;width:40%;" >
            	<c:if test="${empty bjxxlist}">
					<option style="font-size:13px;"  value="" >无</option>
				</c:if>
				<option style="font-size:13px;" selected="selected" value="">请选择--</option>
				  	<c:forEach var="bjxxlist" items="${bjxxlist}" varStatus="s">
						<option style="font-size:13px;" value="${bjxxlist.classname }" <c:if test="${bjxxlist.classname==bjmc }">selected="selected"</c:if>>${bjxxlist.classname }</option>
					</c:forEach>
            </select>
            <input type="hidden" name="openId" id="openId" value="${openId }">
            <button  onclick="query();" style="background-color:#c5e0fa;border-radius: 5px;margin-left:5%;width:14%;">
                <span ></span> 查询
            </button>
      </span>
      <br>
      <div style="height:5px;"></div>
      <span style="width:80%; padding-left:3%;margin-top:3%;font-size:14px;">学年学期&nbsp;&nbsp; 
            <select id="pid" style="font-size:13px;color:#2e87d3;width:40%;" >
            	<c:if test="${empty qhlist}">
					<option style="font-size:13px;"  value="" >无</option>
				</c:if>
				<c:forEach var="list" items="${qhlist}" varStatus="s">
					<option style="font-size:13px;" value="${list.ksqh }" <c:if test="${list.ksqh==qh }">selected="selected"</c:if>>${list.ksqh }</option>
				</c:forEach>
            </select>
	  </span>
</div>
<div class="main_02">
    	<div class="M_box">
        	<div class="M_msg">
                <div class="M_title">
                	<img  style=" margin-top:1%; margin-left:2%" src="<%=path%>/resources/img/wzy/tsjy.png" width="40" height="40">
                  		  学生挂科信息
                </div>
                
                <div class="M_main">
                <ul style="width:100%;">
                	<li>
                		<span class="msg_main04" style="color:#999;width:25%">姓名</span>
                		<span class="msg_main04" style="color:#999;width:25%">班级</span>
                		<span class="msg_main04" style="color:#999;width:25%">挂科数</span>
                		<span class="msg_main04" style="color:#999;width:25%">详情</span>
                	</li>
                	<c:forEach  var="data" items="${gklist}" varStatus="obj">
		                		<li>
		                		<span class="msg_main04" style="width:25%">${data.xm }</span>
		                		<span class="msg_main04" style="width:25%">${data.classname}</span>
		                		<span class="msg_main04" style="width:25%">${data.gks}</span>
		                		<span class="msg_main04" style="width:25%;color:blue"><a onclick="look('${data.xh}')">查看</a></span>
		                	</li>
                	</c:forEach>	
                	<li class="LsMore"></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<c:choose>
   <c:when test="${empty gklist}">
        <div class="text">
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>  
   </c:when>  
   <c:otherwise>
        <div class="-ft" style="margin-top: -20px;margin-left:5%;margin-right:5%;">
			<button id="-ft" class="btn btn-default btn-block btn-lg ng-binding jzgd" onclick="loadMore('${pages}','${openId }','${qh}','${bjmc }' );" style="position:relative;bottom:0px;">加载更多</button>
		</div>  
   </c:otherwise>  
</c:choose>
		
</body>
  <script type="text/javascript">
$(function() {
    $("table tr:nth-child(odd)").addClass("odd-row");
	$("table td:first-child, table th:first-child").addClass("first");
	$("table td:last-child, table th:last-child").addClass("last");
});
</script>

<style type="text/css">
.btn-block {
    display: block;
    width: 100%;
}
.btn-lg, .btn-group-lg > .btn {
    border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
    border-top-left-radius: 6px;
    border-top-right-radius: 6px;
    font-size: 18px;
    line-height: 1.33;
    padding-bottom: 10px;
    padding-left: 16px;
    padding-right: 16px;
    padding-top: 10px;
}
.btn-default {
    background-color: #fff;
    border-bottom-color: #ccc;
    border-left-color: #ccc;
    border-right-color: #ccc;
    border-top-color: #ccc;
    color: #333;
}
.btn {
border-bottom-style: solid;
    border-bottom-width: 1px;
    border-image-outset: 0 0 0 0;
    border-image-repeat: stretch stretch;
    border-image-slice: 100% 100% 100% 100%;
    border-image-source: none;
    border-image-width: 1 1 1 1;
    border-left-style: solid;
    border-left-width: 1px;
    border-right-style: solid;
    border-right-width: 1px;
    border-top-style: solid;
    border-top-width: 1px;
    cursor: pointer;
     text-align: center;
    vertical-align: middle;
    white-space: nowrap;
}
button, input, optgroup, select, textarea {
    -x-system-font: none;
    color: inherit;
    font-family: inherit;
    font-feature-settings: inherit;
    font-kerning: inherit;
    font-language-override: inherit;
    font-size: inherit;
    font-size-adjust: inherit;
    font-stretch: inherit;
    font-style: inherit;
    font-synthesis: inherit;
    font-variant-alternates: inherit;
    font-variant-caps: inherit;
    font-variant-east-asian: inherit;
    font-variant-ligatures: inherit;
    font-variant-numeric: inherit;
    font-variant-position: inherit;
    font-weight: inherit;
    line-height: inherit;
    margin-bottom: 0;
    margin-left: 0;
    margin-right: 0;
    margin-top: 0;
}
</style>
</html>
