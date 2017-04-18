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
		$("#myForm").submit();
	}
</script>
<script type="text/javascript">
// var i=0;
// function loadMore(page,openId){
// 	i=page;
// 	i++;
<%-- 	location.href ="<%=path%>/wfw/ZsPyfa/toPyfaZj?pages="+i+"&openId="+openId; --%>
// }

function loadMore(pages,openId,tj){
	i=pages;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wfw/ZsPyfa/pyjzgd";
	var html= "";
    $.ajax({
		url :url,
		data : {
			pages:i,
			tj:tj
			//openId:openId
		},
		type : "post",
		success : function(data) {
			if(data.length >0){
			var rst = eval(data);
			$.each(rst,function(i,value){
		    html += "<div class='M_title'><img style='margin-top:3%; margin-left:2%;width:10%;' src='<%=path%>/resources/img/wzy/tsjy.png'>"
		         +value.xm
		    	 +"<span>&nbsp;&nbsp;教师工号:"
               	 +value.jsgh
                 +"</span><img style='float:right; margin-top:3%; margin-right:2%;width:10%;' src='<%=path%>/resources/img/wzy/tsdian.png'>"
                 +"</div>"
                 +"<div class='M_main' style='display: block;'><ul><li>"
                 +"<span class='msg_main01'>方案标题</span>"
                 +"<span class='msg_main02' style='color:#fc4312'>"
                 +value.fabt
                 +"</span></li><li>"
                 +"<span class='msg_main01'>添加时间</span>"
                 +"<span class='msg_main02'>"
                 +value.tjsj
                 +"</span></li><li>"
                 +"<span class='msg_main01'>备注</span>"
                 +"<span class='msg_main02'>"
                 +Null(value.bz)
                 +"</span></li><li>"
                 +"<span class='msg_main01'>操作</span>"
                 +"<span class='msg_main02'>"
                 +"<a onclick='toMx("+value.id+")'<span style='color:blue'>查看</span></a>"
                 +"</span></li></ul></div>";
			})
		    $('.LsMore').before(html);
			$('.jzgd').removeAttr("onclick");
			$('.jzgd').attr("onclick","loadMore('"+i+"','"+openId+"','"+tj+"')");
			}else{
				html="<div class='-ft' style='margin-top: -20px;'>"
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

 function toMx(id){
	location.href="<%=path%>/wfw/ZsPyfa/detail?id="+id;
   }
 function Null(str){
		if(str == null){
			str="";
			return str;
		}else{
			return str;
		}
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
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;line-height:38px;}
.top_01 select{ font-size:16px; width:53%; height:44%; border-radius:2px;margin-top:-12%;color:#2e87d3;margin-left:1%;}
.span_hz{ float:left; font-size:15px;margin-top:0.1%;}
.top_01 img{  display:block; float:right;margin-right:6%;} 
.fanhui{margin-top:4%;float:right;margin-right:-2rem;width:5%;}
.span_input{line-height: normal; }
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ width:32%; height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_01 img{ float:left; margin-top:10%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
/**页面表格的行高*/
th,td{padding-bottom:2px;padding-top:2px;line-height:2px;}

.M_box{ width:90%; margin:auto;}
.M_msg{ width:100%;border:1px solid #f2f2f2; border-radius:20px; margin-top:20%; float:left;}
.M_title{ width:100%; height:50px; background-color:#8bc0f2; color:#fff; line-height:50px; font-size:18px; float:left;border-radius:10px 10px 0 0 ;}
.M_title span{font-size:10px;}
.M_title img{ float:left;}
.M_main{ clear:both; font-size:10px;}
.M_main li{ float:left; padding-top:2%; width:100%;}
.M_main li span{ float:left;}
.M_main ul{ float:left;border-bottom:1px solid #8bc0f2; padding-bottom:20px;}
.msg_main01{ text-indent:30px;width:30%;}
.msg_main02{ text-indent:45px;width:70%;}
.M_tab{ width:100%; border-bottom:1px solid #c5e0fa; float:left;}
.M_tab span{ width:50%; line-height:40px; font-size:15px; text-align:center; float:left; color:#2e87d3;}
</style>
<title>培养方案信息</title>
</head>

<body id="cardunion" class="mode_webapp2" style="background-color: white; padding-bottom: 0;" >
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="培养方案信息">
	<a href="#" target="content" onfocus="this.blur()"><span>培养方案</span></a>
	</li>
</ul>
</div>
<div class="top_01" style="height:50px;">
		<span class="span_hz" style="width:35%; padding-left:5%;margin-top: 5.375;">
        	教师工号&姓名:
        </span>
		<form action="<%=path%>/wfw/ZsPyfa/toPyfa" id="myForm" class="wwx_f_a"  method="post" style="display: inline;">
			<input type="text" class="inputhaha" name="code" id="code" style="margin-top: 15px;" >
			<input type="hidden" name="openId" id="openId" value="${openId }">
			<input type="hidden" name="size" id="size" value="${size }">
			<input type="hidden" name="tj" id="tj" value="${tj }">
		</form>
		<span><a class="font1" href="#" style="margin-left: 10px;font-size: 14px;" onclick="query();">查询</a></span>
		<span >
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}" >
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fh1.png" style="width:5%;margin-top: 4%;">
            </a>
        </span>
</div>

<div class="main_02">
	<div class="M_box">
	    <div class="M_msg">
	    <c:forEach var="data" items="${list}" varStatus="obj">
           <c:if test="${!empty list}">                                    
	         <div class="M_title">
	             <img style="margin-top:3%; margin-left:2%;width:10%" src="<%=path%>/resources/img/wzy/tsjy.png" >${data.xm}
	             <span>&nbsp;&nbsp;教师工号:${data.jsgh }</span>
	               <img style="float:right; margin-top:3%; margin-right:2%;width:10%" src="<%=path%>/resources/img/wzy/tsdian.png">
	         </div>
            <div class="M_main" id="TAB01${obj.count}" style="display: block;"> 
	             <ul>
	                <li>
	                  <span class="msg_main01">方案标题</span>
	                  <span class="msg_main02" style="color:#fc4312">${data.fabt}</span>
	                </li>
	                 <li>
	                  <span class="msg_main01" >添加时间</span>
	                  <span class="msg_main02">${data.tjsj}</span>
	                </li>
	                <li>
	                  <span class="msg_main01">备注</span>
	                  <span class="msg_main02">${data.bz}</span>
	                </li>
	                <li>
	                  <span class="msg_main01">操作</span>
	                  <span class="msg_main02">
	                      <a href="javascript:;" onclick="toMx(${data.id})"><span style="color:blue">查看</span></a>
	                  </span>
	                </li>
	             </ul>	
	           </div>
	      </c:if>
      </c:forEach>
      <div class="LsMore"></div>
      <c:choose>  
   <c:when test="${empty list}">
        <div class="text">
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>  
   </c:when>  
   <c:otherwise>
        <div class="-ft" style="margin-top: -20px;">  
			<button id="-ft" class="btn btn-default btn-block btn-lg ng-binding jzgd" onclick="loadMore('${pages}','${openId }','${tj }');" style="position:relative;bottom:0px;">加载更多</button>
		</div>  
   </c:otherwise>  
</c:choose>
	 </div>  
   </div>
</div>

</body>
<script type="text/javascript">var str="";</script>
</html>