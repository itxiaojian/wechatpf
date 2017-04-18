<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <title>微上墙</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
  <link rel="stylesheet" href="<%=path%>/resources/js/style.css">
</head>
<body ontouchstart="" onload="wx.hideOptionMenu();">
<div class="wxapi_container">
    <div class="wxapi_index_container">
    </div>
    <div class="lbox_close wxapi_form" style="text-align: center;">
		<span>欢迎参加微上墙活动，请点击下面的按钮扫描发布的二维码，参与到上墙活动现场。</span>
      	<h3 id="menu-scan">微信扫一扫</h3>
      	<button class="btn btn_primary" id="scanQRCode0">点击扫描二维码</button>
    </div>
  </div>

<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script>
  /*
   * 注意：
   * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
   * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
   * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
   *
   * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
   * 邮箱地址：weixin-open@qq.com
   * 邮件主题：【微信JS-SDK反馈】具体问题
   * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
   */
   //alert(location.href.split('#')[0]);
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
</script>
<script src="<%=path%>/resources/js/demo.js"> </script>

</body></html>