<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="no-cache" http-equiv="Pragma" />
<meta content="no-cache" http-equiv="Cache-Control" />
<meta content="0" http-equiv="Expires" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8" />
<meta name="viewport"content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3" />
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/zhuye.css" />

<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/swipe.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>

<style type="text/css">
        .swipe {overflow: hidden;visibility: hidden;position: relative;width:100%;height:200px;}
        .swipe-wrap {overflow: hidden;position: relative;}
        .swipe-wrap > div {float:left;width:100%;position: relative;cursor:move;}
        .swipe-wrap a img {border:0px;}
        .newstext {color:#fff;background:#000;font-size:16px;height:25px;line-height:25px;padding-left:.5em;filter:alpha(opacity=60);-moz-opacity:0.6;-khtml-opacity: 0.6;opacity: 0.6;}
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
</script>

<script type="text/javascript">
	/* 返回首页 */
	function shouye() {
		WeixinJSBridge.call("closeWindow");
	}
</script>
<script type="text/javascript">
// var arr=new Array();
<%-- arr[1]="<%=path%>/resources/img/zhuye1.png";//放图片地址 --%>
<%-- arr[2]="<%=path%>/resources/img/zhuye2.png"; --%>
<%-- arr[3]="<%=path%>/resources/img/zhuye3.png"; --%>
<%-- arr[4]="<%=path%>/resources/img/FJT.png"; --%>
// var num=0;
// var tid;
// if(tid!=null){
// 	clearInterval(tid);
// }
// tid=setInterval(turnpic,2000); //每隔4秒转换图片
//  function turnpic(){
//    idsrc=document.getElementById("id1");
//    if(num==arr.length-1){
//         num=1;
//    }else{
//         num+=1;
//    }
//    idsrc.src=arr[num];
// }
 </script>
<title>微主页</title>
</head>

<body class="body">
	<header>
	<div class="banner">
	<div class="top">
			<img style="width:100%;" src="<%=path%>/resources/img/BT.jpg" />
			<div class="anniu" style="position: absolute; top:3%; left: 87%;">
			<a href="#" style="float:right;width:40px;height:50px;" onclick="shouye()" >
			   <img  style="width:70%"
			    src="<%=path%>/resources/img/syan.png" />
			    </a>
			</div>
		</div>
<!-- 		<div id="wrapper" style="overflow: hidden; width: 100%;margin-top: 3%;"> -->
<%-- 			<a href="#"><img  id="id1" style="width:96%;" src="<%=path%>/resources/img/FJT.png" /></a> --%>
<!-- 		</div> -->
		<div id='slider' class='swipe'>
	        <div class='swipe-wrap'>
			    <div class="wrap"><div style="background:url(<%=path%>/resources/img/FJT.png) center no-repeat;background-size: cover;height:200px;"></div></div>
			    <div class="wrap"><div style="background:url(<%=path%>/resources/img/zhuye1.png) center no-repeat;background-size: cover;height:200px;"></div></div>
			    <div class="wrap"><div style="background:url(<%=path%>/resources/img/zhuye2.png) center no-repeat;background-size: cover;height:200px;"></div></div>
			    <div class="wrap"><div style="background:url(<%=path%>/resources/img/zhuye3.png) center no-repeat;background-size: cover;height:200px;"></div></div>
			    <div class="wrap"><div style="background:url(<%=path%>/resources/img/FJT.png) center no-repeat;background-size: cover;height:200px;"></div></div>
	        </div>
    	</div>
	</div>
	<script type="text/javascript">
		window.mySwipe = new Swipe(document.getElementById('slider'), {
		        startSlide: 0,
		        speed: 400,
		        auto: 3000,
		        continuous: true,
		        disableScroll: false,
		        stopPropagation: false,
		        callback: function (index, elem) { },
		        transitionEnd: function (index, elem) { }
	    	});
	 </script>
	<div class="clr"></div>
	</header>
	
	<div class="main" style="width: 96%; height: 69%; margin-top: 3%;">
		<div style="width: 57%; height: 45%;">
<!-- 学院新闻 --> <%-- <%=path%>/wzy/ZyXyxw/zyXwlbDetail?openId=${openId} --%>
             <%--   <%=path%>/rss/rssList?openId=${openId} --%>
			<a href="<%=path%>/rss/torsspage?openId=${openId}"
				style="width: 60%; height: 48%; margin-left: 0;">
				<div class="img" style="background: #067CFF; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/xyxw.png"
						style="width: 105px; height: 68px;" />
				</div>
			</a>
<!-- 学校简介 -->
			<a href="<%=path%>/wzy/ZyXyxgxx/zyXyjjDetail?zdbm=xyjj&openId=${openId}"
				style="width: 38%; height: 48.4%; margin-right: 0; left: -0.1%; top: -48%;">
				<div class="img"
					style="background: #3D99FE; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/xyjj.png"
						style="width: 62px; height: 65px;" />
				</div>
			</a> 
<!-- 规章制度 -->
			<a href="<%=path%>/wzy/ZyXyxgxx/zyXyjjDetail?zdbm=xyxl&openId=${openId}"
				style="width: 60%; height: 47%; float: left;bottom:46%;">
				<div class="img"
					style="background: #0065D7; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/xyxl.png"
						style="width: 105px; height: 68px;" />
				</div>
			</a>
<!-- 办事流程 -->
			<a href="<%=path%>/wzy/ZyYjjy/zyYjjyDetail?openId=${openId}"
				style="width: 38%;height: 47%;float: right; bottom: 46%;"> 
				<div class="img"
					style="background: #3E86FF; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/yjjy.png"
						style="width: 62px; height: 64px;" />
				</div>
			</a> 
		</div>
		<div style="width: 42%; height: 42%; ">
<!-- 学院风光 -->
			<a href="<%=path%>/wzy/ZyXyxgxx/zyXyjjDetail?zdbm=xyfg&openId=${openId}" 
			style="width: 49%; height: 51.8%; margin-left: 0;"> 
				<div class="img"
					style="background: #8697FF; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/xyfg.png"
						style="width: 62px; height: 64px;" />
				</div>
			</a> 
<!-- 校园导航 -->			
			<a href="http://map.baidu.com/?newmap=1&s=con%26wd%3D%E7%9A%96%E8%A5%BF%E5%AD%A6%E9%99%A2%26c%3D298&from=alamap&tpl=mapdots" 
			        style="width: 49%; height: 52%; left: 25.5%; top: -52%;">
				<div class="img"
					style="background: #8FAFFC; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/xydh.png"
						style="width: 62px; height: 64px;" />
				</div>
			</a> 
<!-- 组织架构 -->			
			<a href="<%=path%>/wzy/ZyXyxgxx/zyXyjjDetail?zdbm=zzjg&openId=${openId}"
				style="width: 49%; height: 50%; top: -50%;float:left;"> 
				<div class="img" style="background: #83A8FF; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/zzjg.png"
						style="width: 62px; height: 64px;" />
				</div>
			</a> 
  <!-- 流程申请 -->			
			<a href="<%=path%>/wzy/ZyLcxx/zyLcxxBd?openId=${openId}"
			 style="width: 49%; height: 50%; float: right; bottom: 50%">
				<div class="img"
					style="background: #9EBAF9; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/ccsq.png"
						style="width: 62px; height: 65px;" />
				</div>
			</a>
		</div>
		<div style="width: 57%; height: 43%; margin-top: 2px;">
<!-- 学院校历 -->		
		<%-- 	<a href="<%=path%>/wzy/ZyXyxgxx/zyXyjjDetail?zdbm=xyxl&openId=${openId}"
			 style="width: 60%; height: 49%; top: -1.5%;"> 
				<div class="img"
					style="background: #65C501; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/xyxl.png"
						style="width: 105px; height: 68px;" />
				</div>
			</a>  --%>
<!-- 我要吐槽 -->			
			<a href="<%=path%>/wzy/ZyXyxgxx/zyXyjjDetail?zdbm=gywm&openId=${openId}"
				style="width: 38%; height:70%; top:-1.5% ;float:right;">
				<div class="img"
					style="background: #85CF18; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/gy.png"
						style="width: 62px; height: 65px;margin-top:9px;" />
				</div>
			</a>  
<!-- 电话黄历 -->			
			<a href="<%=path%>/wzy/ZyDhhl/zyDhhlDetail?openId=${openId}"
				style="width: 60%; height:70%;float: left;top:-1%;">
				<div class="img"
					style="background: #22AD38; width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/dhhl.png"
						style="width: 105px; height: 68px;margin-top:9px;"  />
				</div>
			</a>	
<!-- 宿管动态 -->
			<%-- <a href="<%=path%>/wzy/ZyXyxgxx/zyXyjjDetail?zdbm=sgdt&openId=${openId}"
				style="width: 38%; height: 49%; float: right;top:1%;"> 
				<div class="img" 
				    style="background:#60BF01 ;width: 100%; height: 100%;">
					<img src="<%=path%>/resources/img/ssdt.png"
					style="width: 62px; height: 65px;" />
				</div>
			</a> --%>
			</div>
		<div style="width: 87.5%; height: 43.4%; margin-top: -67px;">
<!-- 一键救援 -->			
			<%-- <a href="<%=path%>/wzy/ZyYjjy/zyYjjyDetail?openId=${openId}"
				style="width: 49%; height: 49%; right:3.1%;top: -1.5%">
				<div class="img"
					style="background: #FF8B45; width: 100%; height: 100%;margin-left: 2px;">
					<img src="<%=path%>/resources/img/yjjy.png"
						style="width: 62px; height: 65px;" />
				</div>
			</a>  --%>
<!-- 关于我们 -->			
		<%-- 	<a href="<%=path%>/wzy/ZyXyxgxx/zyGywm"
				style="width: 49%; height: 48.4%; margin-left: 0; top: 50%; left: -52%;">
				<div class="img"
					style="background: #FFA008; width: 100%; height: 100%;margin-left: 2px;">
					<img src="<%=path%>/resources/img/gy.png"
						style="width: 62px; height: 65px;" />
				</div>
			</a>  --%>
<!-- 服务帮助 -->			
			<a href="<%=path%>/wzy/ZyXyxgxx/zyXyjjDetail?zdbm=fwbz&openId=${openId}"
				style="width: 48.5%; height: 101%; top: -51%; float: right;right:1%">
				<div class="img"
					style="background: #FFB619; width: 100%; height:69%;margin-left: 5px;">
					<img src="<%=path%>/resources/img/fwbz.png"
						style="width: 62px; height: 65px;margin-top:10px;" />
				</div>
			</a>
		</div>

	</div>
	<!-- <div class="footer" style="margin-top: -40px;">
		<div class="footer-bottom" data-role="none" style="width: 100%;font-size:50%;">
			copyright 2015皖西学院</div>
	</div> -->
</body>
</html>