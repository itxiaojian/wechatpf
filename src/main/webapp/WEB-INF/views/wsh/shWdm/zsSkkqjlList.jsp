<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE HTML>
<HTML><HEAD><META content="IE=11.0000" 
http-equiv="X-UA-Compatible">
     
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">     
<META http-equiv="X-UA-Compatible" content="edge">          
<META name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,initial-scale=1,user-scalable=no"> 
    
<META name="apple-mobile-web-app-capable" content="yes">     
<META name="apple-mobile-web-app-status-bar-style" content="black">     
<META name="apple-mobile-web-app-status-bar-style" content="black">     
<META name="format-detection" content="telephone=no">     
<TITLE>二手市场</TITLE>         
        <LINK href="<%=path%>/resources/js/wsh/essc/init.css" rel="stylesheet">         
        <LINK href="<%=path%>/resources/js/wsh/essc/index.css" rel="stylesheet">         
        <LINK href="<%=path%>/resources/js/wsh/essc/compatible.css" rel="stylesheet">     
<META name="GENERATOR" content="MSHTML 11.00.9600.17801">
<script type="text/javascript">
	function toSub(){
		$('#cxForm').submit();
	}
</script>
</HEAD> 
<BODY>
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="二手市场">
	<a href="#" target="content" onfocus="this.blur()"><span>二手市场</span></a>
	</li>
</ul>
</div>
<DIV class="container">
<div class="header">
        <div class="h-logo"><a href="<%=path%>/wsh/ZsSkkqb/toEsscList?openId=${openId}"><span>logo</span><h1>二手市场</h1></a></div>
        <div class="anniu" style="left:90%;top:15%;" >
				<a style="float:right;width:40px;height:35px;" href="<%=path%>/wsh/zy/zhuye?openId=${openId}" >
			   <img style="width:60%" src="<%=path%>/resources/img/wfwzy.png" >
			   </a>
			      </div>
            </div>
<FORM class="search-box" id="cxForm" action="<%=path%>/wsh/ZsSkkqb/toEsscSpList" method="post">
<DIV class="input-wr clearfix">
<DIV class="search-input"><IMG class="search-icon" src="<%=path%>/resources/js/wsh/essc/search-icon.png"> 
                <INPUT name="keyword" id="keyword" type="text" placeholder="搜索" value="" onchange="toSub();"> 
                <INPUT name="openId" id="openId" type="hidden" value="${openId}"> 
            </DIV>         
</DIV></FORM>

<DIV class="wrap-goods">
<UL class="goods-list group" id="goods_list" style="padding-left: 0;">
	<li class="goods-box" style="width: 100%;padding-right: 0;">
	        <div class="goods-attr">
		        <span style="width: 50%;margin-left: 10%;">
		        	学生姓名
		        </span>
		        <span style="float: right;margin-right: 10%;">是否签到</span>
		    </div>
	</li>
<c:forEach var="data" items="${list}" varStatus="obj">
	<li class="goods-box" style="width: 100%;padding-right: 0;">
	     <div class="goods-attr">
		    <span class="goods-name" style="color: rgb(36, 177, 156);margin-left: 10%;">
		        ${data.xm }
		    </span>
			<c:if test="${data.yqxh!=null&&data.yqxh!='' }">
			    <span style="color: green;float: right;margin-right: 10%;">已签到</span>
			</c:if>
			<c:if test="${data.yqxh==null||data.yqxh=='' }">
			    <span style="color: red;float: right;margin-right: 10%;">未签到</span>
			</c:if>
		 </div>
	</li>
</c:forEach>
</UL>
</DIV><FOOTER class="clearfix copyright"><BR><SPAN 
class="power"></SPAN> 
</FOOTER></DIV>
<%-- <SCRIPT src="<%=path%>/resources/js/wsh/essc/jquery2.1.1.min.js"></SCRIPT> --%>

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
         
<SCRIPT src="<%=path%>/resources/js/wsh/essc/util.js"></SCRIPT>
         
<SCRIPT src="<%=path%>/resources/js/wsh/essc/index.js"></SCRIPT>
         
<%-- <SCRIPT src="<%=path%>/resources/js/wsh/essc/index_goods.js"></SCRIPT> --%>
         
<SCRIPT>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?141dfb73d31a5c8909afe43264b7c38f";
            var s = document.getElementsByTagName("script")[0]; 
            s.parentNode.insertBefore(hm, s);
        })();
    </SCRIPT>
 </BODY></HTML>
