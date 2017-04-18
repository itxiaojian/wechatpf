<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html bdgjjgagedbdaebhbjbcabcdgeeebecf_ba="1" idceifdedfeiefjgfcjfbchejgbcbeid_ba="1" eiiebffcjbffiheggaebebgceaeccbia_ba="1"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,initial-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>二手市场</title>
        <link rel="stylesheet" href="<%=path%>/resources/js/wsh/essc/list/listInit.css">
        <link rel="stylesheet" href="<%=path%>/resources/js/wsh/essc/list/listIndex.css">
    <style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
	<script type="text/javascript">
		function toSub(){
			$('#cxForm').submit();
		}
	</script>    
</head>
<body>

    <div class="container" style="min-height: 579px;">
    <div class="header">
        <div class="h-logo"><a href="<%=path%>/wsh/ShEssc/toEsscList?openId=${openId}"><span>logo</span><h1>二手市场</h1></a></div>
            </div>
    <form class="search-box" id="cxForm" action="<%=path%>/wsh/ShEssc/toEsscSpList" method="post">
        <div class="input-wr clearfix">
            <div class="search-input">
                <img class="search-icon" src="<%=path%>/resources/js/wsh/essc/list/search-icon.png">
                <input name="keyword" id="keyword" type="text" placeholder="搜索你想要的商品" onchange="toSub();">
                <INPUT name="openId" id="openId" type="hidden" value="${openId}"> 
            </div>
        </div>
    </form>
    <nav class="rank-rules">
        <ul class="clearfix">
            <li class="catg synthe active">
                <div class="rank-icon"></div>
                <h2><a href="<%=path%>/wsh/ShEssc/toEsscSpList?order_by=1&splx=${splx}">时间</a></h2>
            </li>
            <li class="catg i-price ">
                <div class="rank-icon"></div>
                <h2><a href="<%=path%>/wsh/ShEssc/toEsscSpList?order_by=2&splx=${splx}">价格</a></h2>
            </li>
        </ul>
    </nav>
    <div class="item-list">
        <ul class="items clearfix">
        <c:forEach var="data" items="${list}" varStatus="obj">
           <li class="item">
                <a class="img" href="<%=path%>/wsh/ShEssc/toEsscDetail?id=${data.id}&openId=${openId}">
                    <img src="${data.spzp }" alt="${data.spmc }">
                </a>
                <div class="info">
                    <div class="name">
                        <a href="<%=path%>/wsh/ShEssc/toEsscDetail?id=${data.id}&openId=${openId}">${data.spmc }</a>
                    </div>
                    <div class="price"><span>￥${data.jg }</span></div>
                </div>
            </li>
        </c:forEach>
        </ul></div>
    <footer class="clearfix copyright">
				<br>
    <span class="power">
    			<c:choose>
					<c:when test="${pages > 1}">
						<a href="<%=path%>/wsh/ShEssc/toEsscSpList?pages=${pages - 1}&openId=${openId}">上一页</a>
					</c:when>
					<c:otherwise>
						上一页
					</c:otherwise>
				</c:choose>
				第${pages}页
				<c:choose>
					<c:when test="${pages < count}">
						<a href="<%=path%>/wsh/ShEssc/toEsscSpList?pages=${pages + 1}&openId=${openId}">下一页</a>
					</c:when>
					<c:otherwise>
					下一页
				</c:otherwise>
				</c:choose>
				总共${count}页
    	   	</span>
</footer>
</div>
        <script src="<%=path%>/resources/js/wsh/essc/list/util.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/list/index.js"></script>
        <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?141dfb73d31a5c8909afe43264b7c38f";
            var s = document.getElementsByTagName("script")[0]; 
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</body></html>