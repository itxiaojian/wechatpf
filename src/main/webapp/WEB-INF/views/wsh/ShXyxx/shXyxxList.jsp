<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>

<html eiiebffcjbffiheggaebebgceaeccbia_b="1" bdgjjgagedbdaebhbjbcabcdgeeebecf_b="1" idceifdedfeiefjgfcjfbchejgbcbeid_b="1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>校友信息</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=3,user-scalable=yes;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>

<link rel="stylesheet" href="<%=path%>/resources/bootstrap/bootstrap.min.css" type="text/css" />

<script type="text/javascript">
	function query(){
// 		var keyWord = $("#code").val(); 
// 		if(keyWord == null || keyWord == ""){
// 			alert("请输入关键词查询!");
// 			return false;
// 		}else{
			$('#myForm').submit();
// 		}
	}
	
</script>

<style>
body {
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
}
/*全局*/
*,li,ul,a,input{ margin:0px; padding:0px; font-family:'微软雅黑'; list-style:none; text-decoration:none;}
/*微主页*/
  .WZY_top{ width:100%; height:50px; position:fixed; left:0px; top:0px; background:url(<%=path%>/resources/img/wzy/logo.png) center no-repeat; background-size:cover; overflow:hidden;}
  .WZY_top img{ float:right; margin-right:5%; margin-top:2%;}
 .WZY_main02{ width:100%;position:absolute; top:50px; left:0px; bottom:32px;overflow:auto;}
 .WZY_icon img{ display:block; margin-left:28%;}
 .WZY_icon span{ width:25%; text-align:center; float:left; font-size:14x; color:#666; line-height:20px; margin-top:6%;}
 .WZY_foot{width:100%; height:32px; position:absolute; left:0px; bottom:0px;background-color:#EBA2A3;background: url(<%=path%>/resources/img/wzy/BQ.png) center no-repeat; background-size:cover;overflow:hidden;}
 .search_box{ width:100%; height:50px;background-color:#dbfeea;}
 .search_box input{ height:28px; width:220px; color:#999;margin-top:11px; margin-left:10px; padding-left:5px; border-radius:5px; border:#00923f 1px solid;}
 .search_msg{ text-align:center;}
 .xyxx{ float:left; width:100%; height:70px; line-height:70px; border-bottom:#f2f2f2 solid 1px;}
 .msg_name{ float:left;width:55%;}
.msg_sex{ width:45%;color:#999;}
</style>
</head>

<body>
<div style="display:none;">
 <input type="hidden" name="size" id="size" value="${size }">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="校友信息 ">
	<a href="#" target="content" onfocus="this.blur()"><span>校友信息</span></a>
	</li>
</ul>
</div>
<div class="iphone">
	<div class="WZY_top">
	   <a href="<%=path%>/wsh/zy/zhuye?openId=${openId}">
    	<img src="<%=path %>/resources/img/wzy/FH.png" width="33" height="33">
    	</a>
    </div>
    <div class="WZY_main02">
       <form action="<%=path%>/wsh/ShXyxx/toXyxx" id="myForm"   method="post"> 
    	<div class="search_box">
        	<div class="search_msg">
                <span class="glyphicon glyphicon-stats" style="color:#00923f; margin-left:10px; margin-top:18px;"></span>
                <input type="text" id="code" name="code" value="${tj}" placeholder="请输入查询的城市或昵称..." onblur="query();">
                <input type="hidden" name="openId" id="openId" value="${openId }">
                <input type="hidden" name="tj" id="tj" value="${tj }">
			   
                <span class="glyphicon glyphicon-search" style="color:#00923f; margin-left:10px;  margin-top:18px;"></span>
            </div>
        </div>
        </form>
        <div class="container">
        	<div class="xiaoyou_box">
            	<ul>
            	  <c:forEach var="data" items="${list}" varStatus="obj">
                	<li class="xyxx">
                    	<span class="msg_name">
                        	<img src="${data.headimgurl }" width="50">&nbsp;&nbsp;
                         <span class="nickname">${data.nickname }</span>
                        </span>
                        
                         <c:if test="${data.sex == '0' }">
                        <span class="msg_sex">
<%--                         	<img src="<>image/girl.png" width="20"> --%>未知
                            &nbsp;${data.COUNTRY }&nbsp;${data.PROVINCE }&nbsp;${data.CITY }
                        </span>
                        </c:if>
                        <c:if test="${data.sex == '1' }">
                         <span class="msg_sex">
                                <img src="<%=path %>/resources/img/wzy/boy.png" width="20">
                                &nbsp;${data.COUNTRY }&nbsp;${data.PROVINCE }&nbsp;${data.CITY }
                        </span>
                        </c:if>
                        <c:if test="${data.sex == '2' }">
                        <span class="msg_sex">
                                <img src="<%=path %>/resources/img/wzy/girl.png" width="20">
                                &nbsp;${data.COUNTRY }&nbsp;${data.PROVINCE }&nbsp;${data.CITY }
                        </span>
                        </c:if>    
                    </li>
                  </c:forEach>
                  <li class="LsMore"></li>
                </ul>
            </div>
        	<c:choose>  
                <c:when test="${empty list}">
                   <div class="text">
			           <p>校友信息暂无...</p>
		           </div>  
                </c:when>  
                <c:otherwise>
                   <div class="-ft" style="margin-left:1%;width:98%;margin-top: -20px;">
			          <button id="-ft" class="btn btn-default btn-block btn-lg ng-binding jzgd" style="background: #fff" onclick="loadMore('${pages}','${openId }','${tj }');">加载更多</button>
		           </div>  
                </c:otherwise>  
           </c:choose>
        </div>
    </div>
    <div class="WZY_foot"></div>
</div>
</body>
<script type="text/javascript">
// var i=0;
// function loadMore(page,openId,tj){
// 	i=page;
// 	i++;
<%-- 	location.href ="<%=path%>/wsh/ShXyxx/toXyxx?pages="+i+"&openId="+openId+"&code="+tj; --%>
// }

$(document).ready(function(){
	
	//限制字符个数
	$(".nickname").each(function(){
	var maxwidth=6;
	if($(this).text().length>maxwidth){
		$(this).text($(this).text().substring(0,maxwidth)+'...');
	}
	});
	});

function loadMore(pages,openId,tj){
	i=pages;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wsh/ShXyxx/xyjzgd";
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
				if(value.sex=='0'){
					 html += "<li class='xyxx'><span class='msg_name'><img src='"+value.headimgurl+"' width='50'>&nbsp;&nbsp;<span class='nickname'>"
			         +value.nickname.substring(0,6)
			    	 +"</span></span>"
	               	 +"<span class='msg_sex'>未知"
	               	 +"&nbsp;" +Null(value.country)+"&nbsp;"+value.province+"&nbsp;"+value.city
	                 +"</span></li>";
				}else if(value.sex=='1'){
					 html += "<li class='xyxx'><span class='msg_name'><img src='"+value.headimgurl+"' width='50'>&nbsp;&nbsp;<span class='nickname'>"
			         +value.nickname.substring(0,6)
			    	 +"</span></span>"
	               	 +"<span class='msg_sex'><img src='<%=path%>/resources/img/wzy/boy.png' width='20'>"
	               	 +"&nbsp;" +Null(value.country)+"&nbsp;"+value.province+"&nbsp;"+value.city
	                 +"</span></li>";
				}else{
					 html += "<li class='xyxx'><span class='msg_name'><img src='"+value.headimgurl+"' width='50'>&nbsp;&nbsp;<span class='nickname'>"
			         +value.nickname.substring(0,6)
			    	 +"</span></span>"
	               	 +"<span class='msg_sex'><img src='<%=path%>/resources/img/wzy/girl.png' width='20'>"
	               	 +"&nbsp;" +Null(value.country)+"&nbsp;"+value.province+"&nbsp;"+value.city
	                 +"</span></li>";
				}
		   
			})
		    $('.LsMore').before(html);
			$('.jzgd').removeAttr("onclick");
			$('.jzgd').attr("onclick","loadMore('"+i+"','"+openId+"','"+tj+"')");
			}else{
				html="<div class='-ft' style='margin-left:1%;width:98%;margin-top: -20px;'>"
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

function Null(str){
	if(str == null){
		str="";
		return str;
	}else{
		return str.substring(0,5);
	}
}

</script>
<style type="text/css">
td.first, th.first {
    width: 30%;
}
th, td {
    padding-bottom: 5px;
    padding-top: 5px;
}
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