<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
    int i=1;
%>
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,target-densitydpi=device-dpi" /> 
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/wzy/tpzl.css">
<%-- <script type="text/javascript">var PATHNAME="<%=path%>"</script> --%>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>

<style type="text/css">
  .New_main{ width:100%;position:absolute; top:100px; left:0px; bottom:10px;overflow:auto;background-color:#e5e5e5;}
  .NEW_msg{ width:90%;}
  .msg_img01{ margin-right:20px; margin-top:10px; float:right; width:50px; height:50px; background:no-repeat center; background-size:cover; line-height:50px; text-align:center; font-size:16px; color:#fff;}
  .NEW_msg{ width:94%;background-color:#fff;border-radius:2px; margin-top:10px; float:left; margin-left:3%;}
  .NEW_msg li,ul{ width:100%; float:left;}
  .NEW_msg li{ border-bottom:1px solid #ccc; padding-bottom:5px;font-size:16px;}
  .new_msg01{ font-size:16px; color:#333; margin-top:10px; margin-left:10px; float:left}
  .new_msg02{ font-size:12px; color:#999; margin-top:10px;margin-left:5px;float:left}
</style>
<title>专题讨论</title>
</head>
<script>
// 	var browser={
// 	    versions:function(){
// 	            var u = navigator.userAgent, app = navigator.appVersion;
// 	            return {         //移动终端浏览器版本信息
// 	                 trident: u.indexOf('Trident') > -1, //IE内核
// 	                presto: u.indexOf('Presto') > -1, //opera内核
// 	                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
// 	                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
// 	                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
// 	                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
// 	                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
// 	                iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
// 	                iPad: u.indexOf('iPad') > -1, //是否iPad
// 	                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
// 	            };
// 	         }(),
// 	         language:(navigator.browserLanguage || navigator.language).toLowerCase()
// 	}
// 	alert("语言版本: "+browser.language);
// 	alert(" 是否为移动终端: "+browser.versions.mobile);
// 	alert(" ios终端: "+browser.versions.ios);
// 	alert(" android终端: "+browser.versions.android);
// 	alert(" 是否为iPhone: "+browser.versions.iPhone);
// 	alert(" 是否iPad: "+browser.versions.iPad);
// 	alert(navigator.userAgent);
	document.getElementsByTagName('html')[0].style.fontSize=window.screen.width/10+'px';
	function doSub(num){
		if(num!=''&&num!=null){
			document.getElementById('zhuti').style.fontSize=window.screen.width/num+"px";
			var oA = document.getElementsByTagName("a");
			for(var  i = 0; i < oA.length; i++){
		        oA[i].style.fontSize=window.screen.width/num+"px";
		    }
			var oP = document.getElementsByTagName("span");
			for(var  i = 0; i < oP.length; i++){
		        oP[i].style.fontSize=window.screen.width/num+"px";
		    }
		}
	}
	
</script>
<body id="zhuti" >
	<div class="phone">
		<div class="logo">
			<img src="<%=path%>/resources/img/wzy/logo.png" style="height:50px;">
		</div>
		<div class="main" style="background-color: #e5e5e5;bottom: 0.5rem;">

			<div id="banner">
				<nav id="TuList">
					<a href="#1"> <img
						src="<%=path%>/resources/img/wzy/banner_01.png">
					</a> 
				</nav>
			</div>
			<div class="NEW_main">
			  <div class="NEW_msg">
				<ul id="tbl1">
				 <c:forEach items="${list}" var="list" varStatus="obj">
				 <a href="<%=path%>/wsh/ShZttl/shZttlpageDetail?id=${list.id}&openId=${openId}">
			   			 <li><span class='new_msg01'>
			    	
			    	<c:choose>
					 	<c:when test="${fn:length(list.ztmc) > 12}">
							<c:out value="${fn:substring(list.ztmc,0,12)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${list.ztmc}" />
						</c:otherwise>
					</c:choose>
			        <br><span class='new_msg02'>
			        <c:choose>
					 	<c:when test="${fn:length(list.ztbt) > 15}">
							<c:out value="${fn:substring(list.ztbt,0,15)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${list.ztbt}" />
						</c:otherwise>
					</c:choose>
			        </span> </span>
			        <%if(i<=4){i++;}else{i=1;}%>
			        <span class='msg_img01' id='msg_img01' style='background:url(<%=path%>/resources/img/wzy/<%=i%>.png)'>${obj.count}</span>
			    </li>
			    </a>
			     </c:forEach>
			     </ul>
			 </div>
			 <c:if test="${empty list}"><div><span style="color:red;font-size:13px;margin-left:16px;">暂无数据!</span></div></c:if>
			</div>
		</div>
<!-- 		<div class="-ft" style=" padding-bottom: 10px;"> -->
<%-- 			<button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${openId }');">加载更多</button> --%>
<!-- 	   </div> -->
		<div class="footer" >
			<img src="<%=path%>/resources/img/wzy/BQ.png" />
		</div>
		<div class="SY_icon">
			<a href="javascript:history.go(-1);"><img src="<%=path%>/resources/img/wzy/FH.png"></a>
		</div>
	</div>
</body>

<script>
var i=0;
function loadMore(page,openId){
	i=page;
	i++;
	location.href ="<%=path%>/wsh/ShZttl/shZttlPage?pages="+i+"&openId="+openId;
}

</script>
</html>
