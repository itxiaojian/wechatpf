<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" xmlns="http://www.w3.org/1999/xhtml"> <!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>  
<link href="<%=path%>/resources/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=1"/>
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/xyxw.css" />

<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
function query(){
// 	if($('#ksh').val()==null||$('#ksh').val()==''){
// 		alert("请输入考生号 ！");
// 		return false;
// 	}
// 	if($('#sfzh').val()==null||$('#sfzh').val()==''){
// 		alert("请输入身份证号 ！");
// 		return false;
// 	}
	$("#myForm").submit();
}
var appid="${map1.appid}";//$('#appid').val();
var nonceStr="${map1.nonceStr}";//$('#nonceStr').val();
var timestamp="${map1.timestamp}";//$('#timestamp').val();
var signature="${map1.signature}";//$('#signature').val();
wx.config({
  debug: false,
  appId: '${map1.appid}',
  timestamp: '${map1.timestamp}',
  nonceStr: '${map1.nonceStr}',
  signature: '${map1.signature}',
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

function ignoreSpaces(string) {
	var temp = "";
	string = '' + string;
	splitstring = string.split(" ");
	for(i = 0; i < splitstring.length; i++)
	temp += splitstring[i];
	return temp;
	} 
</script>
<style type="text/css">
  .anniu{top:1.1%;right:1%; }
  .anniu img{display:block;width:100%;height:6.9%;} 
   
  .main{ width:100%; height:auto; overflow:hidden;}
  .main_02{width:100%; position:absolute; top:60px; left:0; bottom:38px; background-color:#fff; overflow:auto;} 
  .BG_luqu{ width:100%; background: url(<%=path%>/resources/img/wzy/lqxx_BG.png) no-repeat center; background-size: cover; height:180px;}
  .input_luqu{width:100%;height:150px;  background-color:#E79193; background: url(<%=path%>/resources/img/wzy/input.png) no-repeat center;background-size: contain; margin:auto; margin-top:10px;}
  .input_luqu span{ font-size:14px; line-height:20px; width:100%; float:left; text-align:center; margin-top:5%; }
  .input_luqu input{ width:40%;  height:10%; border:1px solid #ccc;font-size:12px; color:#999;}
  .cx_btn{ width:25%; height:20% ; background-color:#ec6941; float:left; margin-left:39%; margin-top:6%;  font-size:14px; line-height:30px;color:#fff;  text-indent:2%; }
  .input_luqu img{ float:left; margin-top:6%; margin-left:20%;width:20%;}
  .CX_title{ width:100%; border-bottom:#5ec9f9 3px dashed;position:relative;}
  .CX_title span{ width:25%; height:25px; background:#5ec9f9; position:absolute; top:-10px; left:20px; border-radius:50px; line-height:25px; color:#fff; text-align:center; font-size:14px;}
  .CG_luqu{ width:100%; height:50%; background: url(<%=path%>/resources/img/wzy/luqu_BG.png) no-repeat center; background-size:contain ;}
  .CG_luqu span{ font-size:14px; color:#ff6c00; text-align:center; float:left; margin-left:30%; margin-top:26% ;}
  .CG_wlq{ width:100%; height:50%; background: url(<%=path%>/resources/img/wzy/weiluqu_BG.png) no-repeat center; background-size:contain;}
  .footer{ width:100%; height:6%; background-color:#ccc;position:absolute; bottom:0; left:0; overflow:hidden}
</style>
	<title>录取查询</title>
</head>
<body style="overflow: auto;" onload="wx.hideOptionMenu()">
   <div class="main">
      <div  class="DYtop" >
           <img style="width:100%;height:50px; "  src="<%=path%>/resources/img/wzy/logo.png" />
           <div class="anniu">
              <a href="<%=path%>/wzy/ZyXyxw/zhuye?openId=${openId}">
			   <img  
			    src="<%=path%>/resources/img/wzy/FH.png" />
			  </a>
		   </div>
      </div>
      
      <div class="main_02">
    	<div class="BG_luqu">
        	<div class="input_luqu">
        	  <form action="<%=path%>/wzy/ZyLqxx/zyLqxxDeatail" id="myForm" class="wwx_f_a" method="post" style="display: inline;">
            	<span>
                	&nbsp;&nbsp;&nbsp;考生号：
                    <input type="text" placeholder="请输入考生号" name="ksh" id="ksh" value="${ksh}" onBlur="this.value=ignoreSpaces(this.value);"/> 
                </span>
                <span>
                	身份证号：
                    <input type="text" placeholder="请输入身份证号" name="sfzh" id="sfzh" value="${sfzh}" onBlur="this.value=ignoreSpaces(this.value);"/>
                </span>
                <div class="cx_btn" id="CX_btn"><a href="#" onclick="query();">
                    <img src="<%=path%>/resources/img/wzy/search_icon.png"/>查询</a></div>
              </form>
            </div>
        </div>
        <div class="CX_title">
        	<span>查询结果</span>
        </div>
        <c:forEach var="data" items="${lqxxlist }" varStatus="obj">
        <c:if test="${!empty lqxxlist}">
           <div class="CG_luqu" id="LUQU">
        	<span style="text-align:left;line-height:25px;font-size:15px;margin-top:20%">
            	${data.xm}同学，总分：${data.zf }
                <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你已被我校${data.lqzy }专业录取！
            </span>
           </div>
        </c:if>
        </c:forEach>
<%--         <c:forEach var="data" items="${size }" varStatus="obj"> --%>
        <c:if test="${size =='0'}">
         <div class="CG_wlq" >
           &nbsp;
          </div>
          </c:if>
<%--         </c:forEach> --%>
    </div>
    
    <div class="footer">
			<img src="<%=path%>/resources/img/wzy/BQ.png" style="width:100%;"/>
    </div>
  </div>
</body>
</html>