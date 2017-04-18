<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html bdgjjgagedbdaebhbjbcabcdgeeebecf_db="1" idceifdedfeiefjgfcjfbchejgbcbeid_db="1" eiiebffcjbffiheggaebebgceaeccbia_db="1"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,initial-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>二手市场</title>
        <link rel="stylesheet" href="<%=path%>/resources/js/wsh/essc/detail/xqInit.css">
        <link rel="stylesheet" href="<%=path%>/resources/js/wsh/essc/detail/xqDetail.css">
    <style type="text/css">
    .input3 {
		background-color: #37b0c9;
		padding: 0px 30px;
		border: none;
		color: white;
		font-size: 16px;
		line-height: 30px;
		font-weight: bold;
		
	}
	.input4{
		background-color: #e7838b;
		padding: 0px 30px;
		border: none;
		color: white;
		font-size: 16px;
		line-height: 30px;
		font-weight: bold;
		
	}
	.margin1{
		margin-bottom: 10px;
	}
	.submit1{
		margin-left: 10px;
		margin-top:30px;
	}
    </style><style type="text/css"></style><style type="text/css"></style></head>
	<script type="text/javascript">
// 		function spScXj(id,openId,lx){
<%-- 			location.href ="<%=path%>/wsh/ShEssc/update?id="+id+"&openId="+openId+"&lx="+lx+""; --%>
// 		}
		function deleteWp(id,openId){
			if(confirm("您确定要删除吗？")){
				location.href ="<%=path%>/wsh/ShEssc/delete?id="+id+"&openId="+openId;
			}
		}
	</script>
<body class="detail">
    <div class="container">
    <div class="header">
        <div class="h-logo">
            <a href="<%=path%>/wsh/ShEssc/toEsscList?openId=${openId}"><span>二手市场</span><h1>二手市场</h1></a>
        </div>
            </div>
    <div class="ershou-details">
        <div class="ershou-photos">
            <div class="big-photos">
                <ul class="clearfix" style="width: 100%;">
                   <li style="width: 100%;">
                   	<img src="${map.spzp }" alt="${map.spmc }">
                   </li>
                </ul>
            </div>
            <div class="slide-dots">
            <ul></ul></div>
        </div>
        <div class="ershou-info">
            <div class="ershou-hd">
                <h2 class="ershou-title">${map.spmc }</h2>
                <div class="ershou-place">
                    <span>发布时间：${map.sjsj }</span>
                </div>
            </div>
            <div class="ershou-price">
                <span class="yen">￥</span><span class="price">${map.jg }</span>
            </div>

            <section class="ershou-detail">
                <ul>
                    <li class="ershou-seller">
                        <div class="name avatar">
                            <div><img src="<%=path%>/resources/js/wsh/essc/0.jpg"></div>
                        </div>
                        <div class="value seller-info">
                            <div class="seller-name">
                                <span>${map.wzxm }</span>
                            </div>
                        </div>
                    </li>
                    <li class="ershou-desc clearfix">
                        <div class="name"><div><span>详情</span></div></div>
                        <div class="value detail-desc"><p>${map.spms }<br>(联系我的时候，请说明是在校园二手街看见的噢！)</p></div>
                    </li>
                    <li class="ershou-contact">
                        <div class="name"><div><span>联系方式</span></div></div>
                        <div class="value telqq">
                         <p><span>手机</span>${map.lxfs }<a class="ope-attr" href="tel:${map.lxfs }">打电话</a><a class="ope-attr" href="sms:${map.lxfs }">发短信</a></p>
                        </div>
                    </li>
	                <c:if test="${map.wzbh==openId && map.xjsj==null && map.scsj==null }">
                    	<li class="ershou-contact">
	                    	<div class="margin1" style="width: 100%;">
								<div class="submit1" style="text-align: center;">
									<a href="<%=path%>/wsh/ShEssc/update?id=${map.id}&openId=${openId}&lx=2"><span class="input3">下架</span></a>
									<a href="<%=path%>/wsh/ShEssc/update?id=${map.id}&openId=${openId}&lx=3"><span class="input3">售出</span></a>
									<a href="javascript:;" onclick="deleteWp('${map.id }','${openId}');"><span class="input3">删除</span></a>
								</div>
								<div class="wwx_clear"></div>
							</div>
						</li>
					</c:if>
					<c:if test="${map.xjsj!=null}">
						<li class="ershou-contact">
	                    	<div class="margin1" style="width: 100%;">
								<div class="submit1" style="text-align: center;">
									<span class="input4">已下架</span>
									<c:if test="${map.wzbh==openId}">
										<a href="javascript:;" onclick="deleteWp('${map.id }','${openId}');"><span class="input4">删除</span></a>
									</c:if>
								</div>
								<div class="wwx_clear"></div>
							</div>
						</li>
					</c:if>
					<c:if test="${map.scsj!=null }">
						<li class="ershou-contact">
	                    	<div class="margin1" style="width: 100%;">
								<div class="submit1" style="text-align: center;">
									<span class="input4">已售出</span>
									<c:if test="${map.wzbh==openId}">
										<a href="javascript:;" onclick="deleteWp('${map.id }','${openId}');""><span class="input4">删除</span></a>
									</c:if>
								</div>
								<div class="wwx_clear"></div>
							</div>
						</li>
					</c:if>
                </ul>
            </section>
        </div>
    </div>
    <footer class="clearfix copyright">
				<br>
    <span class="power">
    	   	</span>
</footer>
</div>
        <script src="<%=path%>/resources/js/wsh/essc/detail/util.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/detail/index.js"></script>
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