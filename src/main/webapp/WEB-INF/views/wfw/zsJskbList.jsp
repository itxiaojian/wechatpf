<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html class="gecko firefox firefox40 win js">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=10">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js"
	type="text/javascript"></script>
<title>教师课表</title>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<link rel="stylesheet" href="<%=path%>/resources/css/wzy/style_02.css" type="text/css">
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	var appid = "${map.appid}";//$('#appid').val();
	var nonceStr = "${map.nonceStr}";//$('#nonceStr').val();
	var timestamp = "${map.timestamp}";//$('#timestamp').val();
	var signature = "${map.signature}";//$('#signature').val();
	wx.config({
		debug : false,
		appId : '${map.appid}',
		timestamp : '${map.timestamp}',
		nonceStr : '${map.nonceStr}',
		signature : '${map.signature}',
		jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
				'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo',
				'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem',
				'showAllNonBaseMenuItem', 'translateVoice', 'startRecord',
				'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice',
				'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage',
				'previewImage', 'uploadImage', 'downloadImage',
				'getNetworkType', 'openLocation', 'getLocation',
				'hideOptionMenu', 'showOptionMenu', 'closeWindow',
				'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
				'addCard', 'chooseCard', 'openCard' ]
	});

	wx.ready(function() {
		// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		wx.hideOptionMenu();
	});
	
	function getXskb(){
		if($("#zs").find("option:selected").val()!='' && $("#zs").find("option:selected").val()!='-1' ){
			var  zs=$("#zs").find("option:selected").val();
		 }
		var openId=$('#openId').val();
		window.location.href="<%=path%>/wfw/ZsKb/toXskb?openId="+openId+"&zs="+zs;
	}
	
	function tankuang(kcmc,jsmc){
		var jsmc = jsmc;
		var kcmc = kcmc;
		//alert("您在教室"jsmc"上"kcmc);
		Alert.showMsg("您的"+kcmc+"课程，上课地点在"+jsmc+"教室");
		//confirm("您的"+kcmc+"课程，上课地点在"+jsmc+"教室");
	}
</script>
<script>
//JavaScript Document
var time=3; 
var stop;
var Alert={	  	
	//确认
	showMsg:function(obj){
		var subhtml='<div id="alert_dialog_show_msg_box" style=" overflow:hidden;height:100px;width:200px;margin:15% auto;"><div class="sweet-overlay" tabIndex="-1" style=" background-color:#000; opacity:0.4; position: fixed; left: 0; right: 0; top: 0; bottom: 0; z-index:1000;"></div><div id="alert_show_3" style="height:250px; width:800px; background-color:#fff; color:#000; border-radius:8px;font-size:40px; text-align:center;z-index:4000;left:10%;top:40%;position:absolute;"><p style="font-size:38px; margin-top:50px;margin-left:5%;margin-right:5%;">'+obj+'</p>'
		+'<input name="button" onclick="closedShowMsg()" type="button" value="确认" style=" width:150px; height:60px; line-height:40px;font-size:38px;position:absolute;bottom:2%;left:40%;"  /></div></div>';
		$("body").append(subhtml);
	}	
}
//关闭确认弹出框
function rec(callback){
	$("#alert_dialog_show_confirm_box").remove();
	callback();
}

//关闭弹出框
function closedShowMsg(){
		$("#alert_dialog_show_msg_box").remove();
	}
</script>
<style>
.KBGD_L{ position:absolute; left:0px; top:0px; bottom:0px;width:80px; background-color:#E36F71;overflow:auto; z-index:10;}
.KBGD_L span{ width:100%; float:left; font-size:36px; height:7.75%; color:#fff; text-align:center;}
.KBGD_R{ position:absolute; left:0px; top:0px; bottom:0px;width:100%;overflow:auto;overflow-y:hidden}
.KBGD_main{ width:1250px; height:100%; }
.KBGD_R span{ margin-left:80px;width:1250px;float:left; font-size:24px; height:7.75%; color:#000; border-bottom:1px solid #F18629;box-sizing:border-box; background-color:#F9DEBF;}
.KBGD_R li{ float:left; width:14.2%; text-align:center; padding:1%; box-sizing:border-box;height:117.5px; line-height:30px;border-right:1px solid #E36F71; color:#333;}
.title_BG{font-size:36px;line-height:80px;background-color:#F38645; color:#fff;}
</style>
<body>
<div class="phone_01">
<input id="openId" value="${openId}" type="hidden" />
      <div class="top_01">
          <span style="width:60%; padding-left:5%">
             个人课表&nbsp;&nbsp;
              <!-- <input type="text" style="font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;"> -->
                <select  id="zs" name="zs" onChange="getXskb()">
              	<c:forEach var="data" items="${zss}" varStatus="obj">
					<option <c:if test="${zs==data.zs}">selected="selected"</c:if> value="${data.zs }">第${data.zs }周</option>
				</c:forEach>
               </select> 
          </span>
          <span style="width:20%;">
              &nbsp;
          </span>
          <span style="width:10%"><a href="<%=path%>/wfw/zy/zhuye?openId=${openId}" >
              <img src="<%=path%>/resources/img/wzy/fanhui.png" style="margin-top:30%"></a>
          </span>
      </div>
      <div class="main_02">
    		<div class="KBGD_L">
            	<span>&nbsp;</span>
            	<span>1</span>
                <span>2</span>
                <span>3</span>
                <span>4</span>
                <span>5</span>
                <span>6</span>
                <span>7</span>
                <span>8</span>
                <span>9</span>
                <span>10</span>
                <span>11</span>
                <span>12</span>
            </div>
            <div class="KBGD_R">
                <span>
                	<ul>
                    	<li class="title_BG" style=" line-height:80px;color:#fff">周一</li>
                        <li class="title_BG" style=" line-height:80px;color:#fff">周二</li>
                        <li class="title_BG" style=" line-height:80px;color:#fff">周三</li>
                        <li class="title_BG" style=" line-height:80px;color:#fff">周四</li>
                        <li class="title_BG" style=" line-height:80px;color:#fff">周五</li>
                        <li class="title_BG" style=" line-height:80px;color:#fff">周六</li>
                        <li class="title_BG" style=" line-height:80px;color:#fff">周日</li>
                    </ul>
                </span>
                <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb1}" varStatus="obj">
                      	<c:choose>
                      	<c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		     <c:choose>
		                     		 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
                     		 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>  
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb2}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		 	 <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb3}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		 	 <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb4}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		     <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb5}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		 	 <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb6}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		 	 <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb7}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		    <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb8}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		 	<c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
							 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb9}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		     <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb10}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		     <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>   
                 <span>
                     <ul>
                      	<c:forEach var="data" items="${jskb11}" varStatus="obj">
                      	<c:choose><c:when test="${data.kcmc!=null && data.kcmc !=''}">
                     		 <li onclick="tankuang('${data.kcmc}','${data.jsmc }')">
                     		     <c:choose>
	                     		 	 <c:when test="${fn:length(data.kcmc)>10}">
										${fn:substring(data.kcmc, 0, 10)}...
									 </c:when>
								 
								 <c:otherwise >
									${data.kcmc}
								 </c:otherwise>
								 </c:choose>
								 </li>
                      	    </c:when>
                     	 <c:otherwise><li>&nbsp;</li></c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </ul>
                </span>    
                <span>
                	<ul>
                    	<li>&nbsp;</li>
                        <li>&nbsp;</li>
                        <li>&nbsp;</li>
                        <li>&nbsp;</li>
                        <li>&nbsp;</li>
                        <li>&nbsp;</li>
                        <li>&nbsp;</li>
                    </ul> 
                </span>        
            </div>
      </div>
      <div class="foot_01">
      		<img src="<%=path%>/resources/img/wzy/BQ.png" width="100%" height="100%">
      </div>
</div>
</body>
</html>
