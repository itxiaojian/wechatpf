<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
  
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3"></meta>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
   <script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    function query() {
   	 var objS = document.getElementById("pid");
   	 var grade = objS.options[objS.selectedIndex].value;
	 var obBj = document.getElementById("bj");
   	 var bjbh = obBj.options[obBj.selectedIndex].value;
     var openId = document.getElementById("openId").value;
     location.href="<%=path%>/wfw/ZsXscj/toCjyjcx?bjbh="+bjbh+"&ksqh="+grade+"&openId="+openId;
	};
	
   function look(code){
	   var objS = document.getElementById("pid");
	   var grade = objS.options[objS.selectedIndex].value;
	   var openId = document.getElementById("openId").value;
	   location.href="<%=path%>/wfw/ZsXscj/tobjcj?code="+code+"&ksqh="+grade+"&openId="+openId;
   }
	
// 	var i=0;
// 	function loadMore(page,openId){
// 		i=page;
// 		i++;
// 		var objS = document.getElementById("pid");
// 	   	var grade = objS.options[objS.selectedIndex].value;
<%-- 		location.href ="<%=path%>/wfw/ZsXscj/toCjyj?pages="+i+"&openId="+openId+"&ksqh="+grade; --%>
// 	}
	
	function loadMore(pages,openId,qh,bjbh){
		i=pages;
		i++;
		var size = (i-1)*10;
		var url ="<%=path%>/wfw/ZsXscj/cjjzgd";
		var html= "";
	    $.ajax({
			url :url,
			data : {
				pages:i,
				openId:openId,
				qh:qh,
				bjbh:bjbh
			},
			type : "post",
			success : function(data) {
				if(data.length>0){
				var rst = eval(data);
				$.each(rst,function(i,value){ 
			    html += "<tr onclick='look("+value.bjbh+","+value.kskm+","+value.pjf+")'>"
				     +"<td style='width:20%'>"
	               	 +value.bjbh
	                 +"</td>"
	                 +"<td style='width:30%;'>"
	               	 +value.kskm
	                 +"</td>"
	                 +"<td style='width:15%;'>"
	                 +value.zgf
	                 +"</td>"
	                 +"<td style='width:15%;'>"
	                 +value.zdf
	                 +"<td style='width:20%;color:blue'>"
	                 +value.pjf
	                 +"</td></tr>";
				})
			    $('.LsMore').before(html);
				$('.jzgd').removeAttr("onclick");
				$('.jzgd').attr("onclick","loadMore('"+i+"','"+openId+"','"+qh+"','"+bjbh+"')");
				}else{
					html="<div class='-ft' style='margin-top: 10px;width:100%;margin-bottom:10px;'>"
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
	<title>学生成绩预警</title>
  </head>
 <style>
 body {
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
}
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; overflow:hidden;}
.top_01{ width:100%; height:50px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:16px; line-height:50px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.top_01 span{ float:left;}
.top_01 img{ margin-top:15%; display:block; float:left;}
.main_02{width:100%; position:absolute; top:120px; left:0; bottom:38px; background-color:#fff; overflow:auto;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.foot_01{width:100%; height:38px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}

.CJCX_people{ width:100%; height:100px; border-bottom:1px solid #b8d6fa; font-size:36px; text-align:center; line-height:100px;}
.CJCX_people span{ width:33%; float:left;}
.CJCX_biao{ margin-top:10px;}
.CJCX_biao table{border-bottom:1px solid #b8d6fa;box-sizing:border-box;}
.CJCX_biao td{ border-top:1px solid #b8d6fa;border-right:1px solid #b8d6fa; line-height:30px;font-size:13px; text-align:center; width:25%; box-sizing:border-box;}
.CJCX_biao_BG{ background-color:#b8d6fa; color:#fff;}
.CJCX_HJ{ width:100%; height:100px; background-color:#b8d6fa; font-size:36px; text-align:center; line-height:100px; color:#fff;}
.CJCX_HJ span{width:33%; float:left;}
.CJCX_title{ width:100%; height:40px; background-color:#b8d6fa; font-size:15px; text-align:center; line-height:40px; color:#fff;}
.CJCX_title span{width:25%; float:left;}
</style>
 
<body style="max-width:640px;margin-left: auto;margin-right: auto;background-color:white;" onload="">
<input type="hidden" name="openId" id="openId" value="${openId }"> 
<input type="hidden" name="size" id="size" value="${size }">
<input type="hidden" name="qh" id="qh" value="${qh }">
<input type="hidden" name="bjbh" id="bjbh" value="${bjbh }">

<div class="phone_01">
      <div class="top_01">
      <span style="width:60%; padding-left:5%">
             <img src="<%=path%>/resources/img/wzy/c11-04.png" style=" margin-top:4%" width="30" height="30"> 学生成绩预警&nbsp;&nbsp;
          </span>
          <span style="width:20%;">
              &nbsp;
          </span>
          <span style="width:5%">
            <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
              <img src="<%=path %>/resources/img/wzy/fh1.png" style="width:100%;margin-top:16px;">
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
						<option style="font-size:13px;" value="${bjxxlist.classname }" <c:if test="${bjxxlist.classname==bjbh }">selected="selected"</c:if>>${bjxxlist.classname }</option>
					</c:forEach>
            </select>
            <input type="hidden" name="openId" id="openId" value="${openId }">
            <button  onclick="query();" style="background-color:#c5e0fa;border-radius: 5px;margin-left:5%;width:14%;">
                  <span ></span> 查询
            </button>
      </span>
      <br>
      <div style="height:5px;"></div>
      <span style="width:80%; padding-left:3%;margin-top:3%;font-size:14px;">
               学年学期&nbsp;&nbsp; 
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
 		<div class="CJCX_biao">
        	<table width="100%" id="table">
                <div class="CJCX_title">
                    <span style=" width:20%">班级</span>
                    <span style=" width:30%">考试科目</span>
                    <span style=" width:15%">最高分</span>
                    <span style=" width:15%">最低分</span>
                    <span style=" width:20%">平均成绩</span>
                </div>
              <tbody>
              <c:forEach items="${cjlist }" var="cjlist">
<%--               <a href="<%=path %>/wfw/ZsXscj/tobjcj?bjbh=${cjlist.bjbh}&&kskm=${cjlist.kskm}&&qh=${qh }openId=${openId}"> --%>
                <tr onclick="look('${cjlist.bjbh},${cjlist.kskm},${cjlist.pjf }')">
                  <td style=" width:20%">${cjlist.bjbh }</td>
                  <td style=" width:30%">${cjlist.kskm }</td>
                  <td style=" width:15%">${cjlist.zgf }</td>
                  <td style=" width:15%">${cjlist.zdf }</td>
                  <td style=" width:20%;color:blue">${cjlist.pjf }</td>
                </tr>
<!--                </a> -->
                </c:forEach>
                <tr class="LsMore"></tr>
            
              </tbody>
            </table>
        </div>
        <c:choose>  
   <c:when test="${empty cjlist}">
        <div class="text">
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>  
   </c:when>  
   <c:otherwise>
        <div class="-ft" style="margin-top: 10px;width:100%;margin-bottom:10px;">
			<button id="-ft" class="btn btn-default btn-block btn-lg ng-binding jzgd" onclick="loadMore('${pages}','${openId }','${qh }','${bjbh }');" style="position:relative;bottom:0px;">加载更多</button>
		</div>  
   </c:otherwise>  
</c:choose>
    </div>
   
    <div class="foot_01">
    	<img src="<%=path %>/resources/img/wzy/BQ.png" width="100%" height="100%">
    </div>
</div>
		
</body>

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
