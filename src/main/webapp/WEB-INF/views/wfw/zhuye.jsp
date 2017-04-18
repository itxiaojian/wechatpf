<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/wfw.css" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>

<script src="<%=path%>/resources/js/TouchSlide.1.1.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<style type="text/css">
.footer1 {
     height:1rem; width:100%; bottom:0; left:0; width:100%; overflow:hidden;
 	 position:fixed;  
     margin: 0px;
     padding: 0px;
     font-family: '微软雅黑';
}
     
.footer1 img {   
     width: 100%;
     }
     
.title_name {
    width: 100%;
    height: 1rem;
    background-color: #f2f2f2;
    border-bottom: #ccc 1px solid;
}
.title_nametext {
    float: left;
    line-height: 1rem;
    font-size: 0.45rem;
    border-left: #EC9F1F 0.1rem solid;
}

.New_list{ padding-left:25px; clear:both}
.New_list li{ border-bottom:#f2f2f2 1px solid; line-height:0.7rem; height:0.7rem;}
.New_list a{ font-size:0.4rem; color:#2e87d3; line-height:0.7rem; float:left;}
</style>
<style type="text/css">
	.anniu{top:1.2%;right:1%; }
    .anniu img{display:block;width:32px;height:32px;} 
    
	 img{ pointer-events: none; }
 	 a{-webkit-touch-callout:none;text-decoration:none;}
	 a,a:hover{ text-decoration:none; color:#333}
 	 a,a:hover{ text-decoration:none; color:#333}
</style>
<script type="text/javascript">

var appid="${map.appid}";//$('#appid').val();
var nonceStr="${map.nonceStr}";//$('#nonceStr').val();
var timestamp="${map.timestamp}";//$('#timestamp').val();
var signature="${map.signature}";//$('#signature').val();
wx.config({
  debug: false,
  appId: '${map.appid}',
  timestamp: '${map.timestamp}',
  nonceStr: '${map.nonceStr}',
  signature: '${map.signature}',
  jsApiList: [
    'checkJsApi',
    'onMenuShareTimeline',
    'onMenuShareAppMessage',
    'onMenuShareQQ',
    'onMenuShareWeibo',
    'hideMenuItems',
    'showMenuItems',
    'hideAllNonBaseMenuItem',
    'showAllNonBaseMenuItem',
    'translateVoice',
    'startRecord',
    'stopRecord',
    'onRecordEnd',
    'playVoice',
    'pauseVoice',
    'stopVoice',
    'uploadVoice',
    'downloadVoice',
    'chooseImage',
    'previewImage',
    'uploadImage',
    'downloadImage',
    'getNetworkType',
    'openLocation',
    'getLocation',
    'hideOptionMenu',
    'showOptionMenu',
    'closeWindow',
    'scanQRCode',
    'chooseWXPay',
    'openProductSpecificView',
    'addCard',
    'chooseCard',
    'openCard'
  ]
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	wx.hideOptionMenu();
});

function jcbd(openId){
	if(confirm("您确定要解除绑定吗？")){
		$.ajax({
			url:'<%=path%>/wfw/zy/jcbd',
			type : 'post',
			//dataType : 'json',
			data:{
				openId:openId
			},
			success: function(data){
				if(data=='1'){
					alert("解绑成功!");
<%-- 			       window.location.href="<%=path%>/wfw/zy/zhuye?openId="+openId; --%>
					//window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0519092dbe6d1d7&redirect_uri=http%3A%2F%2Fwx.zs-si.com%3A9080%2Fwxxywxpt%2Fwfw%2Fzy%2Fzhuye&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
WeixinJSBridge.call("closeWindow");
			   }else{
				   alert("解绑失败！");
			   }
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
				}
			});
		 }
}
</script>
<title>微服务</title>
</head>
<body style="width: 100%;" onload="wx.hideOptionMenu();">
	<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="微服务主页"><a href="<%=path%>/wfw/zy/zhuyeHt"
				target="content" onfocus="this.blur()"><span>微服务主页</span></a></li>
		</ul>
	</div>
	<div class="top">
		<img style="width: 100%;height:50px;" src="<%=path%>/resources/img/wzy/logo.png" />

		<div class="anniu" style="position: absolute; top:2%; left: 87%;width: 9%;">
			<a href="#" 
				onclick="shouye()"> <img 
				 src="<%=path%>/resources/img/wzy/fanhui.png" />
			</a>
		</div>
	</div>

	<div class="phone_banner">
        	<img src="<%=path %>/resources/img/wzy/banner_02.png" style="width:100%;"/>
        </div>
	<div id="content">

		<!-- 主要代码 Start ================================ -->
		<div id="leftTabBox" class="tabBox">
			<div class="bd">
			
			<c:forEach var="zycd" items="${listmenu}" varStatus="obj">
				<c:if test="${ obj.index+1 ==1}">
					<ul class="main">
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==5}">
					</div>
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==9}">
					</div>
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==13}">
					</div>
				</ul>
				<ul class="main">
					<!-- 第四行 -->
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==17}">
					</div>
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==21}">
					</div>
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==25}">
					</div>
				</ul>
				<ul class="main">
					<!-- 第四行 -->
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==29}">
					</div>
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==33}">
					</div>
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==37}">
					</div>
				</ul>
				<ul class="main">
					<!-- 第四行 -->
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==41}">
					</div>
					<div class="content1">
				</c:if>
				<c:if test="${ obj.index+1 ==45}">
					</div>
					<div class="content1">
				</c:if>
				<div class="yi">
					<c:choose>
						<c:when test="${zycd.cdtbmc =='wfw_sljks.png'}">
							<a href="${zycd.cdurl}"
								style="float: left" class="img">
								<img src="<%=path%>/resources/img/wzy/${zycd.cdtbmc}" />
								<p align="center">${zycd.cdmc}</p>
							</a>
						</c:when>
						<c:when test="${zycd.cdtbmc =='wfw_jcbd.png'}">
							<a href="#" onclick="jcbd('${openId}')"
									style="width:50px" class="img"> <img
									src="<%=path%>/resources/img/wzy/wfw_jcbd.png" />
									<p align="center">${zycd.cdmc}</p>
							</a>
						</c:when>
						<c:otherwise>
							<a href="<%=path%>/${zycd.cdurl}openId=${openId}"
								style="float: left" class="img">
								<img src="<%=path%>/resources/img/wzy/${zycd.cdtbmc}" />
								<p align="center">${zycd.cdmc}</p>
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
				</div>
			</ul>
			</div>
			
			<div class="hd">
				<ul class="section-btn">
					<li class="on"><a href="#"></a></li>
					<c:if test="${menusize > 12}">
						<li><a href="#"></a></li>
					</c:if>
					<c:if test="${menusize > 24}">
						<li><a href="#"></a></li>
					</c:if>
					<c:if test="${menusize > 36}">
						<li><a href="#"></a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<!-- 主要代码 End ================================ -->

	</div>
<!-- 	<div class="bottom1"> -->
<!--        <div class="title_name"> -->
<!--        <div class="title_name"> -->
<!--          <span class="title_nametext">&nbsp;&nbsp;校园通知</span> -->
<!--        </div> -->
<!--        </div> -->
       
      
<!--        <div class="New_list"> -->
<!--             <ul> -->
<%--             <c:if test="${jsmc=='ROLE_STUDENT' || jsmc=='ROLE_ADMIN' }"> --%>
<%--             <c:if test="${fn:contains(jsmc, 'ROLE_STUDENT') || fn:contains(jsmc, 'ROLE_ADMIN')}"> --%>
<%--       		   <c:choose> --%>
<%--       			<c:when test="${xssktxsl == 0}"> --%>
<!--       				<span class="-list01-i-prefix mute"></span> -->
<%--            			<li><a href="<%=path%>/wtx/TxXssktx/totxXssktxList?openId=${openId}">上课提醒</a></li> --%>
<%--     			 </c:when> --%>
<%--      			 <c:otherwise> --%>
<!--      			 	<span class="-list01-i-prefix mute"></span> -->
<%--             		<li><a href="<%=path%>/wtx/TxXssktx/totxXssktxList?openId=${openId}"> --%>
<%--             		上课提醒&nbsp;&nbsp;<img src="<%=path%>/resources/img/new.gif"/></a></li> --%>
<%--     			 </c:otherwise>  --%>
<%--      			</c:choose>   --%>
<%--      	    </c:if> --%>
<%--      	    <c:if test="${jsmc=='ROLE_TEACHER' || jsmc=='ROLE_ADMIN'}"> --%>
<%--      	    <c:if test="${fn:contains(jsmc, 'ROLE_TEACHER') || fn:contains(jsmc, 'ROLE_ADMIN')}"> --%>
<%--      			<c:choose> --%>
<%--       			<c:when test="${lssktxsl == 0}"> --%>
<!--       				<span class="-list01-i-prefix mute"></span> -->
<%--            			<li><a href="<%=path%>/wtx/TxLssktx/totxLssktxList?openId=${openId}">老师上课提醒</a></li> --%>
<%--     			 </c:when> --%>
<%--      			 <c:otherwise> --%>
<!--      			 	<span class="-list01-i-prefix mute"></span> -->
<%--             		<li><a href="<%=path%>/wtx/TxLssktx/totxLssktxList?openId=${openId}"> --%>
<%--             		老师上课提醒&nbsp;&nbsp;<img src="<%=path%>/resources/img/new.gif"/></a></li> --%>
<%--     			 </c:otherwise>  --%>
<%--      			</c:choose>   --%>
<%--      			</c:if> --%>
<%--      			<c:if test="${jsmc=='ROLE_STUDENT' || jsmc=='ROLE_ADMIN' || jsmc=='ROLE_TEACHER' || jsmc=='ROLE_INSTRUCTOR' || jsmc=='ROLE_LEADER' }"> --%>
<%--      			<c:if test="${fn:contains(jsmc, 'ROLE_STUDENT') || fn:contains(jsmc, 'ROLE_ADMIN') || fn:contains(jsmc, 'ROLE_TEACHER') || fn:contains(jsmc, 'ROLE_INSTRUCTOR') || fn:contains(jsmc, 'ROLE_LEADER')}"> --%>
<%--      			<c:choose> --%>
<%--       			<c:when test="${tshstxsl == 0}"> --%>
<!--       				<span class="-list01-i-prefix mute"></span> -->
<%--            			<li><a href="<%=path%>/wtx/TxTshstx/totxTshstxList?openId=${openId}">图书还书提醒</a></li> --%>
<%--     			 </c:when> --%>
<%--      			 <c:otherwise> --%>
<!--      			 	<span class="-list01-i-prefix mute"></span> -->
<%--             		<li><a href="<%=path%>/wtx/TxTshstx/totxTshstxList?openId=${openId}"> --%>
<%--             		图书还书提醒&nbsp;&nbsp;<img src="<%=path%>/resources/img/new.gif"/></a></li> --%>
<%--     			 </c:otherwise>  --%>
<%--      			</c:choose>   --%>
<%--      			</c:if> --%>
<%--      			<c:if test="${jsmc=='ROLE_STUDENT' || jsmc=='ROLE_ADMIN' || jsmc=='ROLE_TEACHER' || jsmc=='ROLE_INSTRUCTOR' || jsmc=='ROLE_LEADER' }"> --%>
<%--      			<c:if test="${fn:contains(jsmc, 'ROLE_STUDENT') || fn:contains(jsmc, 'ROLE_ADMIN') || fn:contains(jsmc, 'ROLE_TEACHER') || fn:contains(jsmc, 'ROLE_INSTRUCTOR') || fn:contains(jsmc, 'ROLE_LEADER')}"> --%>
<%--      			<c:choose> --%>
<%--       			<c:when test="${yktxfjltxsl  == 0}"> --%>
<!--       				<span class="-list01-i-prefix mute"></span> -->
<%--            			<li><a href="<%=path%>/wtx/TxYktxfjltx/totxYktxfjltxList?openId=${openId}">一卡通消费记录提醒</a></li> --%>
<%--     			 </c:when> --%>
<%--      			 <c:otherwise> --%>
<!--      			 	<span class="-list01-i-prefix mute"></span> -->
<%--             		<li><a href="<%=path%>/wtx/TxYktxfjltx/totxYktxfjltxList?openId=${openId}"> --%>
<%--             		一卡通消费记录提醒&nbsp;&nbsp;<img src="<%=path%>/resources/img/new.gif"/></a></li> --%>
<%--     			 </c:otherwise>  --%>
<%--      			</c:choose>   --%>
<%--      			</c:if> --%>
<%--      			<c:if test="${jsmc=='ROLE_STUDENT' || jsmc=='ROLE_ADMIN' || jsmc=='ROLE_TEACHER' || jsmc=='ROLE_INSTRUCTOR' || jsmc=='ROLE_LEADER' }"> --%>
<%--      			<c:if test="${fn:contains(jsmc, 'ROLE_STUDENT') || fn:contains(jsmc, 'ROLE_ADMIN') || fn:contains(jsmc, 'ROLE_TEACHER') || fn:contains(jsmc, 'ROLE_INSTRUCTOR') || fn:contains(jsmc, 'ROLE_LEADER')}"> --%>
<%--      			<c:choose> --%>
<%--       			 <c:when test="${txYktdexftxsl == 0}"> --%>
<!--       				<span class="-list01-i-prefix mute"></span> -->
<%--            			<li><a href="<%=path%>/wtx/TxYktdexftx/totxYktdexftxList?openId=${openId}">一卡通大额消费提醒</a></li> --%>
<%--     			 </c:when> --%>
<%--      			 <c:otherwise> --%>
<!--      			 	<span class="-list01-i-prefix mute"></span> -->
<%--             		<li><a href="<%=path%>/wtx/TxYktdexftx/totxYktdexftxList?openId=${openId}"> --%>
<%--             		一卡通大额消费提醒&nbsp;&nbsp;<img src="<%=path%>/resources/img/new.gif"/></a></li> --%>
<%--     			 </c:otherwise>  --%>
<%--      			</c:choose>  --%>
<%--      			</c:if>  --%>
<!--             </ul> -->
<!--         </div>  -->
       
<!--     </div> -->
    
<!--     <div class="bottom2" id="divDD"> -->
<!--        <div class="title_name"> -->
<!--        <div class="title_name"> -->
<!--          </img><span class="title_nametext">&nbsp;&nbsp;校长信箱</span> -->
<%--          <a class="xytz" style="float:right;margin-right: 10px;margin-top: 5px;"  href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}"> 更多</a> --%>
<!--        </div> -->
<!--        </div> -->
<!--      <div class="bottom-bd"> -->
<!--        <ul class="ul" style="padding-left: 25px;"> -->
<!--          <li class="li"> -->
<%--          <c:forEach var="data" items="${xjxxb}"> --%>
<!--            <span class="-list01-i-prefix mute"></span> -->
<!--            <span class="xjxxb" style="float:left;font-size: 0.4rem;line-height: 0.7rem;width: 100px;margin-left: 0px;"> -->
<%--            <a href='<%=path%>/xzxx/XxXjxxb/toXzxxDetail?id= ${data.id}&opendId=${openId}' style="color: #2e87d3;">${data.xjbt }</a></span> --%>
<%--            <c:choose> --%>
<%--            <c:when test="${data.clzt ==5 }"> --%>
<!--            <span style="float:right;color:blue;font-size: 0.4rem;line-height: 0.7rem;">回复未评价</span> -->
<%--            </c:when> --%>
<%--            <c:when test="${data.clzt ==6 }"> --%>
<!--            <span style="float:right;color:red;font-size: 0.4rem;line-height: 0.7rem;">已评价结束</span> -->
<%--            </c:when> --%>
<%--            </c:choose> --%>
<%--            <span class="xytz"  style="float:right;font-size: 0.4rem;line-height: 0.7rem;">${data.xxsj }&nbsp;/&nbsp;</span><br /> --%>
<%--            </c:forEach> --%>
<!--          </li> -->
<!--        </ul> -->

	   <div class="Phone_chatu">
            <img src="<%=path %>/resources/img/wzy/chatu02.png" style="width:100%;"/>
       </div>
       <div class="footer1">
        <img src="<%=path%>/resources/img/wzy/BQ.png" style="width:100%;height:1rem;">
     </div> 
<!--        </div> -->
<!--   </div> -->
    
     
  
<script type="text/javascript">
         //滑动翻页
         TouchSlide({
	         slideCell : "#leftTabBox",
	         effect : "leftLoop"
           });

	//翻页小点点
	var i=0;
    var $btn = $('.section-btn li');
    i++;if(i==$btn.length){i=0};
    i--;if(i<0){i=$btn.length-1};
    
    /* 返回首页 */
    function shouye() {
        WeixinJSBridge.call("closeWindow");
    }
</script>
<script>
// 	alert(window.screen.height);
	var u = navigator.userAgent, app = navigator.appVersion;
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if(isiOS){
		document.getElementsByTagName('html')[0].style.fontSize=window.screen.width/10+'px';
		document.getElementById('divDD').style.marginBottom=window.screen.height/4+'px';
	}else{
		document.getElementsByTagName('html')[0].style.fontSize='38px';
		document.getElementById('divDD').style.marginBottom=window.screen.height/8+'px';
	}
// 	alert('是否是Android：'+isAndroid);
// 	alert('是否是iOS：'+isiOS);
</script>
</body>
</html>
