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
// 	function query(){
// 		$("#myForm").submit();
// 	}
	
	 function query() {
		 var code = document.getElementById("code").value;
		 var obBj = document.getElementById("bj");
	   	 var bjmc = obBj.options[obBj.selectedIndex].value;
	     var openId = document.getElementById("openId").value;
	     location.href="<%=path%>/wfw/ZsXsrzxx/toXsrzxxList?code="+code+"&bjmc="+bjmc+"&openId="+openId;
		};
</script>
<script type="text/javascript">

function loadMore(page,openId,code,bjmc){
	i=page;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wfw/ZsXsrzxx/sgjzgd";
	var html= "";
    $.ajax({
		url :url,
		data : {
			pages:i,
			openId:openId,
			code:code,
			bjmc:bjmc
			//openId:openId
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
               	 +value.xh
                 +"</span>"
                 +"<span class='msg_main04' style='width:25%;'>"
               	 +value.bjmc
                 +"</span>"
                 +"<span class='msg_main04' style='width:25%;'>"
                 +"<a href='<%=path%>/wfw/ZsXsrzxx/toXsrzxxDetail?id="+value.id+"&openId="+openId+"'>查看</a>"
                 +"</span></li>";
			})
		    $('.LsMore').before(html);
			$('.zjgd').removeAttr("onclick");
			$('.zjgd').attr("onclick","loadMore('"+i+"','"+openId+"','"+code+"','"+bjmc+"')");
			}else{
				html="<div class='-ft' style='margin-top: -20px;width:90%;margin-left:5%;margin-bottom:20px;'>"
					+"<button class='btn btn-default btn-block btn-lg ng-binding' style='position:relative;'>已经是最后一页了</button>"
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
button,  optgroup, select, textarea {
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
<style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%;  overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;line-height:38px;}
.top_01 select{ font-size:16px; width:53%; height:44%; border-radius:2px;margin-top:-12%;color:#2e87d3;margin-left:1%;}
.span_hz{ float:left; font-size:15px;margin-top:0.1%;}
.top_01 img{  display:block; float:left;margin-left:3%;} 
.fanhui{margin-top:4%;float:right;margin-right:-2rem;width:5%;}
.span_input{line-height: normal; }
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
/**页面表格的行高*/
th,td{padding-bottom:2px;padding-top:2px;line-height:2px;}

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
<title>宿管信息</title>
</head>

<body id="cardunion" class="mode_webapp2" style="background-color: white; padding-bottom: 0;" >
<input type="hidden" name="openId" id="openId" value="${openId }">
<input type="hidden" name="size" id="size" value="${size }">
<%-- <input type="hidden" name="pages" id="pages" value="${pages }"> --%>
<input type="hidden" name="bjmc" id="bjmc" value="${bjmc }">
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="宿管信息">
	<a href="#" target="content" onfocus="this.blur()"><span>宿管信息</span></a>
	</li>
</ul>
</div>
<div class="phone_01">
<!-- <div class="top_01" style="height:50px;"> -->
<!-- 		<span class="span_hz" style="width:38%; padding-left:5%;margin-top: 5.375;"> -->
<!--         	学号&姓名&班级: -->
<!--         </span> -->
<%-- 		<form action="<%=path%>/wfw/ZsXsrzxx/toXsrzxxList" id="myForm" class="wwx_f_a"  method="post" style="display: inline;"> --%>
<!-- 			<input type="text" class="inputhaha" name="code" id="code" style="margin-top: 15px;" > -->
<%-- 			<input type="hidden" name="openId" id="openId" value="${openId }"> --%>
<%-- 			<input type="hidden" name="size" id="size" value="${size }"> --%>
<%-- 			<input type="hidden" name="tj" id="tj" value="${tj }"> --%>
<!-- 		</form> -->
<!-- 		<span><a class="font1" href="#" style="margin-left: 10px;font-size: 14px;" onclick="query();">查询</a></span> -->
<!-- 		<span > -->
<%--         	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}" > --%>
<%--         		<img class="fanhui" src="<%=path%>/resources/img/wzy/fh1.png" style="width:5%;margin-top: 4%;"> --%>
<!--             </a> -->
<!--         </span> -->
<!-- </div> -->
<div class="top_01">
      <span style="width:60%; ">
             <img src="<%=path%>/resources/img/wzy/c11-04.png" style=" margin-top:1%" width="30" height="30"> 宿管信息&nbsp;&nbsp;
      </span>
      <span style="width:20%;">
              &nbsp;
      </span>
      <span style="width:5%">
          <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
              <img src="<%=path %>/resources/img/wzy/fh1.png" style="width:5%;margin-top:10px;float:right;margin-right:5%;">
          </a>
      </span>
</div>
<div style="margin-top:40px;">
      <span style="padding-left:3%;font-size:14px;">学号&姓名&nbsp;&nbsp;
            <input type="text" class="inputhaha" name="code" id="code" value="${code }" style="margin-top: 15px;width:40%;" >
			
      </span>
      <span>
            <button  onclick="query();" style="background-color:#c5e0fa;border-radius: 5px;margin-left:5%;width:14%;">
                  <span ></span> 查询
            </button>
     </span>
      <br>
      <span style="padding-left:3%;font-size:14px;margin-top:3%;">班级查询&nbsp;&nbsp;&nbsp;&nbsp;
            <select id="bj" style="font-size:13px;color:#2e87d3;width:40%;" >
            	<c:if test="${empty bjxxlist}">
					<option style="font-size:13px;"  value="" >无</option>
				</c:if>
				<option style="font-size:13px;" selected="selected" value="">请选择--</option>
				  	<c:forEach var="bjxxlist" items="${bjxxlist}" varStatus="s">
						<option style="font-size:13px;" value="${bjxxlist.classname }" <c:if test="${bjxxlist.classname==bjmc }">selected="selected"</c:if>>${bjxxlist.classname }</option>
					</c:forEach>
            </select>
            
     </span>
     
</div>
<div class="main_02">
    	<div class="M_box">
        	<div class="M_msg">
                <div class="M_title">
                	<img  style=" margin-top:1%; margin-left:2%" src="<%=path%>/resources/img/wzy/tsjy.png" width="40" height="40">
                  		  学生基本信息
                </div>
                
                <div class="M_main">
                <ul style="width:100%;">
                	<li>
                		<span class="msg_main04" style="color:#999;width:25%">姓名</span>
                		<span class="msg_main04" style="color:#999;width:25%">学号</span>
                		<span class="msg_main04" style="color:#999;width:25%">班级</span>
                		<span class="msg_main04" style="color:#999;width:25%">详情</span>
                	</li>
                	<c:forEach  var="data" items="${list}" varStatus="obj">
		                		<li>
		                		<span class="msg_main04" style="width:25%">${data.xm }</span>
		                		<span class="msg_main04" style="width:25%">${data.xh}</span>
		                		<span class="msg_main04" style="width:25%">${data.bjmc}</span>
		                		<span class="msg_main04" style="width:25%"><a href="<%=path%>/wfw/ZsXsrzxx/toXsrzxxDetail?id=${data.id}&openId=${openId}">查看</a></span>
		                	</li>
                	</c:forEach>	
                	<li class="LsMore"></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<c:choose>  
   <c:when test="${empty list}">
        <div class="text">
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>  
   </c:when>  
   <c:otherwise>
        <div class="-ft" style="margin-top: -20px;width:90%;margin-left:5%;margin-bottom:10px;">
			<button id="-ft" class="btn btn-default btn-block btn-lg ng-binding zjgd" onclick="loadMore('${pages}','${openId }','${code }','${bjmc }');" style="position:relative;bottom:0px;">加载更多</button>
		</div>  
   </c:otherwise>  
</c:choose>
        
</div>
</body>
<script type="text/javascript">var str="";</script>
<script type="text/javascript" src="<%=path%>/resources/js/wysy.js"></script>
</html>