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
        <div class="h-logo"><a href="<%=path%>/wsh/ShEssc/toEsscList?openId=${openId}"><span>logo</span><h1>二手市场</h1></a></div>
        <div class="anniu" style="left:90%;top:15%;" >
				<a style="float:right;width:40px;height:35px;" href="<%=path%>/wsh/zy/zhuye?openId=${openId}" >
			   <img style="width:60%" src="<%=path%>/resources/img/wfwzy.png" >
			   </a>
			      </div>
            </div>
<FORM class="search-box" id="cxForm" action="<%=path%>/wsh/ShEssc/toEsscSpList" method="post">
<DIV class="input-wr clearfix">
<DIV class="search-input"><IMG class="search-icon" src="<%=path%>/resources/js/wsh/essc/search-icon.png"> 
                <INPUT name="keyword" id="keyword" type="text" placeholder="搜索你想要的商品" value="" onchange="toSub();"> 
                <INPUT name="openId" id="openId" type="hidden" value="${openId}"> 
            </DIV>         
</DIV></FORM><NAV class="categories">
<UL class="clearfix">
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=1&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="校园代步" src="<%=path%>/resources/js/wsh/essc/1.png"> 
                      </DIV>
  <H2>校园代步</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=2&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="手机" src="<%=path%>/resources/js/wsh/essc/2.png"> 
                      </DIV>
  <H2>手机</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=3&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="电脑" src="<%=path%>/resources/js/wsh/essc/3.png"> 
                      </DIV>
  <H2>电脑</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=4&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="数码配件" src="<%=path%>/resources/js/wsh/essc/4.png"> 
                      </DIV>
  <H2>数码配件</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=5&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="数码" src="<%=path%>/resources/js/wsh/essc/5.png"> 
                      </DIV>
  <H2>数码</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=6&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="电器" src="<%=path%>/resources/js/wsh/essc/6.png"> 
                      </DIV>
  <H2>电器</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=7&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="运动健身" src="<%=path%>/resources/js/wsh/essc/7.png"> 
                      </DIV>
  <H2>运动健身</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=8&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="衣物伞帽" src="<%=path%>/resources/js/wsh/essc/8.png"> 
                      </DIV>
  <H2>衣物伞帽</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=9&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="图书教材" src="<%=path%>/resources/js/wsh/essc/9.png"> 
                      </DIV>
  <H2>图书教材</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=10&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="租赁" src="<%=path%>/resources/js/wsh/essc/10.png"> 
                      </DIV>
  <H2>租赁</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=11&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="生活娱乐" src="<%=path%>/resources/js/wsh/essc/11.png"> 
                      </DIV>
  <H2>生活娱乐</H2></A></LI>
  <LI class="catg"><A href="<%=path%>/wsh/ShEssc/toEsscSpList?splx=12&openId=${openId}">
  <DIV class="nav-icons"><IMG alt="其他" src="<%=path%>/resources/js/wsh/essc/12.png"> 
                      </DIV>
  <H2>其他</H2></A></LI></UL></NAV>

<DIV class="wrap-btn" style="width:100%;"><A class="btn btn-publish" style="width:100%;" href="${newurl}"><IMG 
src="<%=path%>/resources/js/wsh/essc/btn_publish.png">   
      </A></DIV>
<DIV class="wrap-goods">
<UL class="goods-list group" id="goods_list">
<c:forEach var="data" items="${list}" varStatus="obj">
	<li class="goods-box">
		<a href="<%=path%>/wsh/ShEssc/toEsscDetail?id=${data.id}&openId=${openId}">
			<div class="wrap-img">	
				<img src="${data.spzp }" alt="${data.spmc }"/>
			</div>
	        <div class="goods-attr">
		        <p class="goods-name">
		        	${data.spmc }
		        </p>
		        <span class="goods-price">¥${data.jg }</span>
		    </div>
		</a>
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
