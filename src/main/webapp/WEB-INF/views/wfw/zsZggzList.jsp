<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3"></meta>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/heeh.css" />
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<%--   	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script> --%>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/mobiscroll/mobiscroll.custom-2.13.2.min.css">  
 <!--时间控件mobiscroll-->  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>  
  
  
   <link href="<%=path%>/resources/mobiscroll/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />  
   <link href="<%=path%>/resources/mobiscroll/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
	//上月 下月
	/*function change(obj){
		document.getElementById("addflag").value=obj;
	  	var time=document.getElementById("date").value;
	  	var arr = time.split("-");
	    var date = new Date(arr[0], (parseFloat(arr[1])+parseFloat(obj)), 1);
		var d = new Date();
		d = new Date(d.getFullYear(), (d.getMonth()+1), d.getDate());
		if(date>d){
			alert("只能查询当前月以前的数据！");
			return false;
		}else{
	  		return true;
		}
    }*/
    $(function () {  
        var currYear = (new Date()).getFullYear();    
        var opt={};  
        opt.date = {preset : 'date',maxDate:new Date()};  
        //opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };  
        opt.datetime = {preset : 'datetime'};  
        opt.time = {preset : 'time'};  
        opt.default = {  
            theme: 'jqm', //皮肤样式  
            display: 'modal', //显示方式   
            mode: 'scroller', //日期选择模式  
            dateFormat : 'yy-m', // 日期格式 
            dateOrder : 'yym', //面板中日期排列格式  
            lang:'zh',  
            startYear:currYear - 20, //开始年份  
            endYear:currYear + 20 //结束年份  
        };  

        $("#appDateTime").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));  
        var optDateTime = $.extend(opt['datetime'], opt['default']);  
        
    });
    function getValue(){
    	//alert($('#appDateTime').val());
    	$('#Form1').submit();
    }
    function setValue(){
    	var time=$('#date').val();
    	$("#appDateTime").val(time);
    }
    
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
	<link href="<%=path%>/resources/css/tab-import.css" rel="stylesheet" style="text/css" />
	<link href="<%=path%>/resources/css/css.css" rel="stylesheet" style="text/css" />
	<title>我的工资</title>
	<style>
		.td_left {
			text-align: center;
			padding-left: 5px;
			width: 10%;
		}
		.td_right {
			text-align: center;
			padding-right: 5px;
			width: 10%;
		}
	</style>

	 <style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; overflow:hidden;}
.logo{ width:100%; height:120px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01{ width:100%; height:40px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.top_01 span{ float:left;}
.top_01 img{ margin-top:15%; display:block; float:left;}
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_02{width:100%; position:absolute; top:120px; left:0; background-color:#fff; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_02 li{font-size:34px; float:left; width:100%; text-align:center; line-height:100px;}
.CJ_msg_02 span{ float:left; color:#2e87d3;}
.CJ_msg_01 img{ float:left; margin-top:12%; margin-left:16%;}
.foot_01{width:100%; height:30px; position:fixed; bottom:0; left:0; overflow:hidden}

.M_box{ width:90%; margin:auto;}
.M_msg{ width:100%; border:1px solid #f2f2f2; margin-top:6%; float:left;}
.M_title{ width:100%; height:120px; background-color:#8bc0f2; color:#fff; line-height:120px; font-size:45px; float:left;border-radius:20px 20px 0 0 ;}
.M_title span{font-size:15px; width:30%; height:30px; color:#fff; line-height:120px; float:left;border-radius:20px 20px 0 0 ;}
.M_title img{ float:left;}
.M_main{ clear:both; font-size:36px;}
.M_main li{ float:left; width:100%}
.M_main li span{ float:left;}
.M_main ul{ float:left;border-bottom:1px solid #8bc0f2; padding-bottom:10px;}
.msg_main01{text-indent: 20px;width:30%;font-size:12px;}
.msg_main02{text-indent: 60px;width:70%;font-size:12px;}
.GZSF{ font-size:16px;color:#fd922a;}
</style>
  </head>
 
  <body onload="wx.hideOptionMenu();">
  <div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="个人工资信息">
	<a href="#" target="content" onfocus="this.blur()"><span>个人工资信息</span></a>
	</li>
</ul>
</div>
   <form action="<%=path%>/wfw/ZsZggz/toZggzxxBysj" method="POST" id="Form1" style="margin-top: 0px;">  
<%-- <div class="bg1">
	<div class="style1">
		<div class="wwx_f_l" style="position: relative;">
		<a class="font1" style="color:white;" href="<%=path%>/wfw/ZsZggz/toZggzxx?openId=${openId }">工资发放时间</a>
		<input id="appDateTime" name="time" onchange="getValue();" 
		       style="border:1px solid #dddddd;margin-left: 10px; margin-bottom: 4px;"/> 
		</div>
				<div class="wwx_clear"></div>
				<div class="anniu" style="left:90%;top:15%;" >
				<a style="float:right;width:40px;height:50px;" href="<%=path%>/wfw/zy/zhuye?openId=${openId}" >
			   <img style="width:70%" src="<%=path%>/resources/img/wfwzy.png" >
			   </a>
			      </div>
	</div>
</div> --%>
<div class="phone_01">
	<div class="top_01">
    	<span style="width:60%;font-size:10px; padding-left:5%;margin-top:-40px;">
        	工资发放时间:&nbsp;&nbsp;
              <input class="text" id="appDateTime" name="time" onchange="getValue();" 
		       style="font-size:15px;float:center; width:100px; height:20px; border-radius:5px; color:#2e87d3;margin-top:0px;" /> 
        </span>
      <%--   <span style="width:20%;">
        	<img src="<%=path%>/resources/img/wzy/search.png">
                                                   查询
        </span> --%>
        <span style="width:10%">
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fh1.png" style="width:50%;margin-top:10px;margin-left:200%;">
            </a>
        </span>
    </div>
   <input type="hidden" name="openId" id="openId" value="${openId }"> 
   <div class="main_02" style="top:40px;">
				<!--我的工资-->
<!-- 				<input type="hidden" id="addflag" name="addflag" value=""> -->
				<%-- <input type="hidden" id="date" name="date" value="${time}"> --%>
<%-- 				<input type="hidden" id="time" name="time" value="${time}"> --%>
				<!-- <label style="font-size: 15pt;color:#58cbfa;">工资查询</label><br /> -->
				<!-- <input id="appDateTime" name="time" style="border: 1px solid #dddddd;" onchange="getValue();"/>  -->
<!-- 				<table  border="0" align="center" cellpadding="0" style="margin-top: 30px;border-width: 1px 2px 2px 1px;border-style: solid;width:100%;" -->
<!-- 						cellspacing="0"> -->
<!-- 						<tr> -->
<!-- 							<td align="center" width="20%"><input type="submit" title="上一月" style="width:17px;height:20px;cursor: pointer;" onClick="return change('-1')" id="log" class="me_f" name="log" value=" ">&nbsp;</td> -->
<%-- 							<td align="center" width="60%"><label style="font-size: 15pt;">微服务-工资查询${date}</label></td> --%>
<!-- 							<td align="center" width="20%"><input type="submit" title="下一月" style="width:15px;height:18px;cursor: pointer;" onClick="return change('1')" id="log" name="log"  class="me_b" value=" ">&nbsp;</td> -->
<!-- 						</tr> -->
<!-- 				</table> -->
			<div class="M_box">
				<div class="M_msg">
					
                        <c:if test="${empty map1}">
								
										<div class="text" style="width:100%;height:480px;">
<!-- 											<p>工资信息暂无...</p> -->
											<img style="width:100%;" alt="数据对接中..." src="<%=path%>/resources/images/djt.png">
										</div>
						</c:if>
						<c:if test="${not empty map1}">
						<div class="M_title" style="height:50px;">
						<c:forEach var="map" items="${map1}" varStatus="obj">		
								
						<img style="width:13%;margin-top:6px;margin-left:8px;" src="<%=path%>/resources/img/wzy/mp.png">
								
						   		<span style="margin-left:16px;margin-top:-33px;font-size:16px;">${map.xm}</span>
						   		<%-- <span style="width:130px;margin-top:-33px;margin-left:70px;font-size:11px;">工号:${map.gh}</span> --%>
						   		
						<img style=" float:right; margin-top:6px; margin-right:8px;width:13%;" src="<%=path%>/resources/img/wzy/dian.png" >

						</c:forEach>

					 </div>
					 <div class="M_main">
					 <c:forEach var="map" items="${map1}" varStatus="obj">
					 	<ul style="width:100%;">
					 		<li>
					 			<span>
					 				<img style="margin-left:60px;width:18%;margin-top:2px;" src="<%=path%>/resources/img/wzy/GZ_logo.png">
					 			</span>
				 				<span>
					 				<span class="GZSF">
					 					<span style="font-size:13px;color:#333;margin-left:-40px;margin-top:2px;">总工资</span>
					 					<span  style="margin-left:20px;margin-top:2px;">${map.sfhj+map.jkbz+map.zbbz+map.skbz+map.lwbz}</span>
					 				</span>
					 			</span>
					 		</li>
					 	</ul>
					 	<ul>
					 		<li>
					 			<span class="msg_main01">年份月份</span>
					 			<span class="msg_main01">${map.nf}年${map.yf}月</span>
					 		</li>
					 		<li>
					 			<span class="msg_main01">应发合计</span>
					 			<span class="msg_main01">${map.yfhj}</span>
					 		</li>
					 		<li>
					 			<span class="msg_main01">扣款合计</span>
					 			<span class="msg_main01">${map.kkhj}</span>
					 		</li>
					 		<li>
					 			<span class="msg_main01">实发工资</span>
					 			<span class="msg_main01">${map.sfhj}</span>
					 		</li>
					 	</ul>
					 	<c:if test="${map.jkbz > 0||map.zbbz > 0||map.skbz >0||map.lwbz>0}">
					 	<ul style="width:100%;">
					 		<%-- <li>
					 			<span class="msg_main01">实发工资</span>
					 			<span class="msg_main01">${map.sfhj}</span>
					 		</li>
					 		<li>
					 			<span class="msg_main01">基本工资</span>
					 			<span class="msg_main01">${map.jbgz}</span>
					 		</li>
					 		<li>
					 			<span class="msg_main01">基础性绩效</span>
					 			<span class="msg_main01">${map.jcxjx}</span>
					 		</li> --%>
					 		<c:if test="${map.jkbz > 0}">
					 		<li>
					 			<span class="msg_main01">监考补助</span>
					 			<span class="msg_main01">${map.jkbz}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.zbbz > 0}">
					 		<li>
					 			<span class="msg_main01">坐班补助</span>
					 			<span class="msg_main01">${map.zbbz}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.skbz > 0}">
					 		<li>
					 			<span class="msg_main01">上课补助</span>
					 			<span class="msg_main01">${map.skbz}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.lwbz > 0}">
					 		<li>
					 			<span class="msg_main01">论文补助</span>
					 			<span class="msg_main01">${map.lwbz}</span>
					 		</li>
					 		</c:if>
					 	</ul>
					 	</c:if>
					 	<ul style="width:100%;">
					 		<c:if test="${map.gjjkc > 0}">
					 		<li>
					 			<span class="msg_main01">公积金扣除</span>
					 			<span class="msg_main01">${map.gjjkc}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.sbkc > 0}">
					 		<li>
					 			<span class="msg_main01">社保扣除</span>
					 			<span class="msg_main01">${map.sbkc}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.grsds > 0}">
					 		<li>
					 			<span class="msg_main01">个人所得税</span>
					 			<span class="msg_main01">${map.grsds}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.yb > 0}">
					 		<li>
					 			<span class="msg_main01">医保</span>
					 			<span class="msg_main01">${map.yb}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.ghf > 0}">
					 		<li>
					 			<span class="msg_main01">公会费</span>
					 			<span class="msg_main01">${map.ghf}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.gjj > 0}">
					 		<li>
					 			<span class="msg_main01">公积金</span>
					 			<span class="msg_main01">${map.gjj}</span>
					 		</li>
					 		</c:if>
					 		<c:if test="${map.ykylj > 0}">
					 		<li>
					 			<span class="msg_main01">预扣养老金</span>
					 			<span class="msg_main01">${map.ykylj}</span>
					 		</li>
					 		</c:if>
					 	</ul>
				 	 </c:forEach>
				 	 
					 </div>
					 </c:if>

					</div>
					
				</div>
			 	
					
		
				
		</div>
		<div class="foot_01" style="width:100%;">
				<img src="<%=path%>/resources/img/wzy/BQ.png" style="width:100%;position:relative;bottom:0;left: 0;overflow: hidden;">
		</div>	
		</div>

		</form>		
  </body>
  				
</html>
