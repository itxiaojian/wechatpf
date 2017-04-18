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
<meta name="viewport" content="width=device-width,user-scalable=no">

<meta content="no-cache" http-equiv="Pragma" />
<meta content="no-cache" http-equiv="Cache-Control" />
<meta content="0" http-equiv="Expires" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3" />
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/wfw.css" />
<%-- <link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue" /> --%>
<%-- <link href="<%=path%>/resources/css/base.css" rel="stylesheet" /> --%>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<%-- <script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT> --%>
<%-- <script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT> --%>
<%-- <script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script> --%>

<script src="<%=path%>/resources/js/TouchSlide.1.1.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
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
			       //window.location.href="<%=path%>/wfw/zy/zhuye?openId="+openId;
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
<title>校领导微服务</title>
</head>
<body>
	<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="微服务主页"><a href="#"
				target="content" onfocus="this.blur()"><span>微服务主页</span></a></li>
		</ul>
	</div>
	<div class="top">
		<img style="width: 100%;" src="<%=path%>/resources/img/wfw.png" />

		<div class="anniu" style="position: absolute; top: 3%; left: 87%;">
			<a href="#" style="float: right; width: 40px; height: 50px;"
				onclick="shouye()"> <img style="width: 70%"
				src="<%=path%>/resources/img/syan.png" />
			</a>
		</div>
	</div>

	<div id="content">

		<!-- 主要代码 Start ================================ -->
		<div id="leftTabBox" class="tabBox">
			<div class="bd">
			
			<!-- 第一页 -->
				<ul class="main">
					<!-- 第一行 -->
					<div class="content1">
						<!-- 个人工资查询 -->
						<div class="yi">
							<a href="<%=path%>/wfw/ZsZggz/toZggzxx?openId=${openId}"
								class="img" style="width:50px;"> <img
								src="<%=path%>/resources/img/gz.png" />
								<p>个人工资</p>
							</a>
						</div>
						<div class="yi">
					 	 	<a  href="<%=path%>/wsh/ShSwzl/toSwzlList?openId=${openId}"
								style="width:50px" class="img"> <img
								src="<%=path%>/resources/img/megaphone2.png"/>
							 	<p>失物招领</p>
					       </a>
			            </div>
						<!-- 图书借阅  -->
						<div class="yi">
							<a href="<%=path%>/wfw/ZsTsjyxx/toTsjyxx?openId=${openId}"
								style="float: left;" class="img">
								<img src="<%=path%>/resources/img/tsjy.png" />
								<p>图书借阅</p>
							</a>
						</div>
						<!-- 缴欠费信息-->
						<div class="er">
							<a href="<%=path%>/wfw/ZsXsjqfxx/toXsjqfxx?openId=${openId}"
								style="width: 60px" class="img"> <img
								src="<%=path%>/resources/img/jqfxx.png" />
								<p>学费查询</p>
							</a>
						</div>
					</div>

					<!-- 第二行 -->
					<div class="content1">
						<!-- 校长信箱-->
						<div class="yi">
							<a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}"
								style="width:50px" class="img"> <img
								src="<%=path%>/resources/img/xzxx.png" />
								<p>校长信箱</p>
							</a>
						</div>
						<!-- 我要报修-->
						<div class="er">
							<a href="<%=path%>/wx/Bxsq/toBxsqlcjl?openId=${openId}"
								style="width:50px" class="img"> <img
								src="<%=path%>/resources/img/tools.png" />
								<p>我要报修</p>
							</a>
				        </div>
						<!-- 解除绑定-->
						<div class="er">
							<a href="#" onclick="jcbd('${openId}')"
								style="width:50px" class="img"> <img
								src="<%=path%>/resources/img/locked.png" />
								<p>解除绑定</p>
							</a>
						</div>
					</div>
				</ul>
			</div>
			
			<div class="hd">
					<ul class="section-btn">
						<li class="on"><a href="#"></a></li>
					</ul>
				</div>
		</div>
		<!-- 主要代码 End ================================ -->

	</div>
	<div class="bottom1" style="height:130px;">
    <!-- 滑动标点 
    <ul class="section-btn">
      <li class="on"></li>
	  <li></li>
    </ul>-->
       <div class="bottom-hd">
         <img class="aaa" src="<%=path%>/resources/img/laba.png"></img><span class="xytz" style="float:left;">校园通知</span>
        <!--  <a class="xytz" style="float:right;"  href=""> 更多</a> -->
       </div>
       <div class="bottom-bd" >
       <ul class="ul">
         <li class="li">
           <span class="-list01-i-prefix mute"  ></span>
     <c:choose>
      <c:when test="${tshstxsl == 0}">
           <span class="xytz" style="float:left;"><a href="<%=path%>/wtx/TxTshstx/totxTshstxList?openId=${openId}">图书还书提醒</a></span><br>
     </c:when>
     <c:otherwise>
           <span class="xytz" style="float:left;"><a href="<%=path%>/wtx/TxTshstx/totxTshstxList?openId=${openId}">
           			图书还书提醒&nbsp;&nbsp;<img src="<%=path%>/resources/img/new.gif"/></a></span><br>
     </c:otherwise>
     </c:choose>
     <span class="-list01-i-prefix mute"  ></span>
     <c:choose>
      <c:when test="${yktxfjltxsl == 0}">
           <span class="xytz" style="float:left;"><a href="<%=path%>/wtx/TxYktxfjltx/totxYktxfjltxList?openId=${openId}">一卡通消费记录提醒</a></span><br>
     </c:when>
     <c:otherwise>
           <span class="xytz" style="float:left;"><a href="<%=path%>/wtx/TxYktxfjltx/totxYktxfjltxList?openId=${openId}">
        		         一卡通消费记录提醒&nbsp;&nbsp;<img src="<%=path%>/resources/img/new.gif"/></a></span><br>
     </c:otherwise>
     </c:choose>
     <span class="-list01-i-prefix mute"  ></span>
     <c:choose>
      <c:when test="${txYktdexftxsl ==0 }">
           <span class="xytz" style="float:left;"><a href="<%=path%>/wtx/TxYktdexftx/totxYktdexftxList?openId=${openId}">一卡通大额消费提醒</a></span><br>
     </c:when>
     <c:otherwise>
           <span class="xytz" style="float:left;"><a href="<%=path%>/wtx/TxYktdexftx/totxYktdexftxList?openId=${openId}">
           			一卡通大额消费提醒&nbsp;&nbsp;<img src="<%=path%>/resources/img/new.gif"/></a></span><br>
     </c:otherwise>
     </c:choose>
         </li>
       </ul>
       </div>
    </div>
	<div class="bottom2">
       <div class="bottom-hd" >
         <img class="aaa" src="<%=path%>/resources/img/xiaoxi.png"></img><span class="xytz" style="float:left;">校长信箱</span>
         <a class="xytz" style="float:right;"  href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}"> 更多</a>
       </div>
     <div class="bottom-bd">
       <ul class="ul">
         <li class="li">
         <c:forEach var="data" items="${xjxxb}">
           <span class="-list01-i-prefix mute"></span>
           <span class="xjxxb" style="float:left;">
           <a href='<%=path%>/xzxx/XxXjxxb/toXzxxDetail?id= ${data.id}&opendId=${openId}'>${data.xjbt }</a></span>
           <c:choose>
           <c:when test="${data.clzt ==5 }">
           <span style="float:right;color:blue;">回复未评价</span>
           </c:when>
           <c:when test="${data.clzt ==6 }">
           <span style="float:right;color:red;">已评价结束</span>
           </c:when>
           </c:choose>
           <span class="xytz"  style="float:right;font-size: 12px">${data.xxsj }&nbsp;/&nbsp;</span><br />
           </c:forEach>
         </li>
       </ul>
       </div>
    </div> 
	
	<div class="footer">
<div class="footer-bottom" data-role="none" style="width: 100%;font-size:100%;margin-top: 295px;">
    copyright 2015皖西学院  (技术支持: 合肥智圣)
</div>
</div>

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
</body>
</html>
