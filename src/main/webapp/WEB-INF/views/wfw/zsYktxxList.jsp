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
// 	function change(obj){
// 		document.getElementById("addflag").value=obj;
// 	  	var time=document.getElementById("date").value;
// 	  	var arr = time.split("-");
// 	    var date = new Date(arr[0], (parseFloat(arr[1])+parseFloat(obj)), 1);
// 		var d = new Date();
// 		d = new Date(d.getFullYear(), (d.getMonth()+1), d.getDate());
// 		if(date>d){
// 			alert("只能查询当前月以前的数据！");
// 			return false;
// 		}else{
// 	  		return true;
// 		}
//     }
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
            dateFormat : 'yy-mm', // 日期格式 
            dateOrder : 'yymm', //面板中日期排列格式  
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
	<title>一卡通信息</title>
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
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;
 line-height:38px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.span_hz{ float:left; font-size:11px;margin-top:0.1%;}
/* .top_01 img{ margin-top:15%; display:block; float:left;} */
.fanhui{margin-top:3%;float:right;margin-right:4%;width:5%;}
.span_input{line-height: normal; }
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ width:32%; height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_01 img{ float:left; margin-top:10%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
/**页面表格的行高*/
th,td{padding-bottom:2px;padding-top:2px;}
</style>

  </head>
 
  <body style="overflow: auto;" onload="setValue();">
  <div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="一卡通信息">
	<a href="#" target="content" onfocus="this.blur()"><span>一卡通信息</span></a>
	</li>
</ul>
</div>
   <form action="<%=path%>/wfw/ZsYktxx/toYktxxBysj" method="POST" id="Form1">  
<%-- <div class="bg1">
	<div class="style1">
		<div class="wwx_f_l" style="position: relative;">
		<a class="font1" style="color:white;" href="<%=path%>/wfw/ZsYktxx/toYktxx?openId=${openId }">查询时间</a>
		<span><input id="appDateTime" name="time" onchange="getValue();"
				style="border:1px solid #dddddd;margin-left: 10px; margin-bottom: 4px;"/></span>
		</div>
				<div class="wwx_clear"></div>
				<div class="anniu" style="left:90%;top:15%;" >
				<a style="float:right;width:40px;height:50px;" href="<%=path%>/wfw/zy/zhuye?openId=${openId}" >
			   <img style="width:70%" src="<%=path%>/resources/img/wfwzy.png" >
			   </a>
			      </div>
	</div>
</div> --%>
	<div class="top_01">
    	<span class="span_hz" style="width:60%; padding-left:5%">
        	查询时间:&nbsp;&nbsp;
              <input class="span_input" id="appDateTime" name="time" onchange="getValue();" 
		       style="border:1px solid #dddddd;width:50%;border-radius:5px;color:#2e87d3" /> 
        </span>
      <%--   <span style="width:20%;">
        	<img src="<%=path%>/resources/img/wzy/search.png">
                                                   查询
        </span> --%>
        <span style="width:10%">
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fh1.png">
            </a>
        </span>
    </div>
   <div class="tab-container">
				<!--我的工资-->
<!-- 				<input type="hidden" id="addflag" name="addflag" value=""> -->
				<input type="hidden" id="date" name="date" value="${time}">
<%-- 				<input type="hidden" id="time" name="time" value="${time}"> --%>
				<input type="hidden" name="openId" id="openId" value="${openId }"> 
<!-- 				<table  border="0" align="center" cellpadding="0" style="margin-top: 30px;border-width: 1px 2px 2px 1px;border-style: solid;width:100%;" -->
<!-- 						cellspacing="0"> -->
<!-- 						<tr> -->
<!-- 							<td align="center" width="20%"><input type="submit" title="上一月" style="width:17px;height:20px;cursor: pointer;" onClick="return change('-1')" id="log" class="me_f" name="log" value=" ">&nbsp;</td> -->
<%-- 							<td align="center" width="60%"><label style="font-size: 15pt;">微服务-一卡通查询${date}</label></td> --%>
<!-- 							<td align="center" width="20%"><input type="submit" title="下一月" style="width:15px;height:18px;cursor: pointer;" onClick="return change('1')" id="log" name="log"  class="me_b" value=" ">&nbsp;</td> -->
<!-- 						</tr> -->
<!-- 				</table> -->
				<table  border="1" align="center" cellpadding="0" style="margin-top: 60px;border-width: 0px 0px 0px 0px;border-style: solid;width:99%;"
						cellspacing="0">
						
						<tr>
							<td style="width:30%;font-weight:bold;" align="right" >
								学号\工号：
							</td>
							<td style="width:70%;" align="center" >
								${map1.bh }
							</td>
						</tr>
						<tr>
							<td style="width:30%;font-weight:bold;" align="right" >
								姓名：
							</td>
							<td style="width:70%;" align="center" >
								${map1.xm }
							</td>
						</tr>
						<tr>
							<td style="width:30%;font-weight:bold;" align="right" >
								卡号：
							</td>
							<td style="width:70%;" align="center" >
								${map1.kh }
							</td>
						</tr>
						<tr>
							<td style="width:30%;font-weight:bold;" align="right" >
								当前余额：
							</td>
							<td style="width:70%;" align="center" >
								${map1.ye }
							</td>
						</tr>
				</table>
				
	
					<table border="0" align="center" cellpadding="0" style="margin-top: 5px;margin-bottom: 10px;width:99%; "
						cellspacing="0" class="content02">
						<c:if test="${empty Xflist}">
								
										<div class="text">
<!-- 											<p>消费信息暂无...</p> -->
											<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
										</div>
						</c:if>
						  
						<c:if test="${!empty Xflist}">  
					  <tr class="bgcolor04">
					    <td width="30%" align="center" style="font-weight:bold;height:1%;">地点</td>
					    <td width="15%" align="center" style="font-weight:bold;height:1%;">金额</td>
					    <td width="40%" align="center" style="font-weight:bold;height:1%;">时间</td>
					    <td width="15%" align="center" style="font-weight:bold;height:1%;">余额</td>
					  </tr>
					   </c:if>
						
						<c:forEach var="data" items="${Xflist}" varStatus="obj">  <!-- varStatus="status" -->
							
						<!-- 判断偶数行 -->
						<c:if test="${obj.count%2 == '0'}">
							<tr class="bgcolor01">
							    <td align="center">${data.xfdd}</td>
							    <td align="center">${data.xfje}</td>
							    <td align="center">${data.xfsj}</td>
							    <td align="center">${data.ye}</td>
						  </tr>
					 </c:if>
					 <!-- 判断奇数行 -->
					<c:if test="${obj.count%2 != '0'}">
					 	<tr class="bgcolor02">
							    <td align="center">${data.xfdd}</td>
							    <td align="center">${data.xfje}</td>
							    <td align="center">${data.xfsj}</td>
							    <td align="center">${data.ye}</td>
					  </tr>
					</c:if>
					</c:forEach>
					</table> 
					</div>
		</form>		
  </body>
</html>
