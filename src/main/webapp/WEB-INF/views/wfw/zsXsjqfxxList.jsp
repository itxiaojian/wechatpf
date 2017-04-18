<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>

<html eiiebffcjbffiheggaebebgceaeccbia_b="1" bdgjjgagedbdaebhbjbcabcdgeeebecf_b="1" idceifdedfeiefjgfcjfbchejgbcbeid_b="1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>学生缴欠费信息</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=3,user-scalable=yes;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script><style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function query(){
		$("#myForm").submit();
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
<script type="text/javascript">
var i=0;
function loadMore(pages,openId){
	i=pages;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wfw/ZsXsjqfxx/toXsjqfxxZj";
	var html= "";
	var html1= "";
	var html2= "";
	var html3="</ul></div>";
    $.ajax({
		url :url,
		data : {
			pages:i,
			openId:openId
		},
		type : "post",
		success : function(data) {
			if(data[0].length>0 && data[1].length>0){
				var rst = eval(data[0]);
				var rst1 = eval(data[1]);
				$.each(rst,function(i,value0){
					
		                $.each(rst1,function(j,value1){
			                   if(value0.xn==value1.xn && value0.xh==value1.xh){
			                	  html2 +="<li>"
		                			+"<span style='width:25%'>"+value1.jfxm+"</span>"
		                			+"<span style='width:25%'>"+value1.yjje+"</span>"
			                		+"<span style='width:25%'>"+value1.sjje+"</span>"	                		
			                		+"<span style='width:25%'>"+value1.qfje+"</span>"
				                    +"</li>";
			                  	   }
			                   });
		                
		                html1+="<div class='main_01_msg' style='padding-bottom: 10px;'>"
			        		+"<div class='CX_title_01'>"
			        	    +"学生缴欠费信息"
			            	+"</div>"
			        		+"<ul class='GG_msg_01'>"
			            	+"<li>"
			                +"<img src='<%=path%>/resources/img/wzy/cha_11-05.png'>"
			                +"&nbsp;学年:<span style='color:#2e87d3;'>&nbsp;&nbsp;"+value0.xn+"</span>"
			                +"</li>"
			                +"<li>"
			                +"<img src='<%=path%>/resources/img/wzy/cha_11.png'>"
			                +"&nbsp;学号:<span style='color:#2e87d3;'>&nbsp;&nbsp;"+value0.xh+"</span>"
			                +"</li><li>"
			                +"<img src='<%=path%>/resources/img/wzy/c11-04.png'>"
			                +"&nbsp;姓名:<span style='color:#2e87d3;'>&nbsp;&nbsp;"+value0.xm+"</span>"
			                +"</li><li>"
			                +"<img src='<%=path%>/resources/img/wzy/cha_11-06.png'>"
			                +"&nbsp;备注:<span style='color:#2e87d3;'>&nbsp;&nbsp;"+value0.bz+"</span>"
			                +"</li></ul>"
			            	+"<div style='width:100%; height:3px; background-color:#e5e5e5; clear:both;'></div>"
			           		+"<ul class='CJ_msg_01'>"
			            	+"<li style='width:25%'>"
			                +"<img style='margin-top:5%; margin-left:10%' src='<%=path%>/resources/img/wzy/cha1_11.png'>"
			                	 +"&nbsp;缴费项目"		                
			                +"</li>"
			               +"<li style='width:25%'>"
			                	+"<img src='<%=path%>/resources/img/wzy/cha1_11-02.png'>"
			                	+"应缴金额"
			                +"</li>"
			                +"<li style='width:25%'>"
			                +"<img src='<%=path%>/resources/img/wzy/cha1_11-03.png'>"
			                	+"实缴金额"
			                +"</li>"
			                +"<li style='width:25%'>"
			                +"<img src='<%=path%>/resources/img/wzy/cha1_11-03.png'>"
			                +"欠费金额"
			                +"</li>"
			                +"</ul>"
			                +"<ul class='CJ_msg_02'>" 
			                +html2
			                +html3;
			                
			                html2="";
		              
			})
		    $('.jzgd').append(html1);
		   // $('.xxdiv').before(html);
			$('.btn-block').removeAttr("onclick");
			$('.btn-block').attr("onclick","loadMore('"+i+"','"+openId+"')");
			}else{
				$(".btn-block").text("已经是最后一页了");
			}
			},
		   error : function() {
			alert("error");
		}
	  });
}    
</script>
 <style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;
 line-height:38px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.span_hz{ float:left; font-size:11px;margin-top:0.1%;}
/* .top_01 img{ margin-top:15%; display:block; float:left;} */
.fanhui{margin-top:3%;float:right;margin-right:4%;width:5%;}
.span_input{margin-top:-14%;width:65%;height:50%;margin-left:35%;}


</style>

<style type="text/css">
.btn-block {
    display: block;
    width: 100%;
}
.btn-lg, .btn-group-lg > .btn {
    border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
    border-top-left-radius: 6px;
    border-top-right-radius: 6px;
    font-size: 18px;
    line-height: 1.33;
    padding-bottom: 10px;
    padding-left: 16px;
    padding-right: 16px;
    padding-top: 10px;
}
.btn-default {
    background-color: #fff;
    border-bottom-color: #ccc;
    border-left-color: #ccc;
    border-right-color: #ccc;
    border-top-color: #ccc;
    color: #333;
}
.btn {
border-bottom-style: solid;
    border-bottom-width: 1px;
    border-image-outset: 0 0 0 0;
    border-image-repeat: stretch stretch;
    border-image-slice: 100% 100% 100% 100%;
    border-image-source: none;
    border-image-width: 1 1 1 1;
    border-left-style: solid;
    border-left-width: 1px;
    border-right-style: solid;
    border-right-width: 1px;
    border-top-style: solid;
    border-top-width: 1px;
    cursor: pointer;
     text-align: center;
    vertical-align: middle;
    white-space: nowrap;
}
button, input, optgroup, select, textarea {
    -x-system-font: none;
    color: inherit;
    font-family: inherit;
    font-feature-settings: inherit;
    font-kerning: inherit;
    font-language-override: inherit;
    font-size: inherit;
    font-size-adjust: inherit;
    font-stretch: inherit;
    font-style: inherit;
    font-synthesis: inherit;
    font-variant-alternates: inherit;
    font-variant-caps: inherit;
    font-variant-east-asian: inherit;
    font-variant-ligatures: inherit;
    font-variant-numeric: inherit;
    font-variant-position: inherit;
    font-weight: inherit;
    line-height: inherit;
    margin-bottom: 0;
    margin-left: 0;
    margin-right: 0;
    margin-top: 0;
}

.main_01{width:100%; position:absolute; top:30px; left:0; bottom:98px; overflow-y:scroll;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:7%; float:left; overflow-y:scroll; border:1px solid #999;}
.CX_title_01{ width:100%; height:20px; background-color:#23bcfc; color:#fff; line-height:17px; text-align:center; font-size:15px;}
.GG_msg_01 li{ width:100%; height:30px; font-size:10px; line-height:30px; }
.GG_msg_01 img{ float:left; margin-top:2%; margin-left:5%;width: 6%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ height:20px; font-size:10px; line-height:20px; float:left; }
.CJ_msg_02 li{font-size:10px; float:left; width:100%; text-align:center; line-height:20px;}
.CJ_msg_02 span{ float:left; color:#2e87d3;}
.CJ_msg_01 img{ float:left; margin-top:3%; margin-left:1%;width: 18%;}
/**页面表格的行高*/
th,td{padding-bottom:2px;padding-top:2px;}
</style>
</head>

<body id="cardunion" class="mode_webapp2" style="padding-bottom: 0;background-color: white;overflow-y: scroll;"onload="wx.hideOptionMenu();">
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="学生缴欠费信息 ">
	<a href="#" target="content" onfocus="this.blur()"><span>学生缴欠费信息</span></a>
	</li>
</ul>
</div>
<%-- <div  class="bg1">
	<div class="style1">
		<div class="wwx_f_l" style="position: relative;">
		<a class="font1" href="<%=path%>/wfw/ZsXsjqfxx/toXsjqfxx?openId=${openId }">学号&姓名</a>
		&nbsp;&nbsp;&nbsp;
			<!-- <span class="font1" >学号</span> -->
		</div>
		<form action="<%=path%>/wfw/ZsXsjqfxx/toXsjqfxx" id="myForm" class="wwx_f_a" method="post">
			<input type="text" class="inputhaha" name="code" id="code">
			<input type="hidden" name="openId" id="openId" value="${openId }">
			<input type="hidden" name="size" id="size" value="${size }">
			<span><a class="font1" href="#" style="margin-left: 10px;" onclick="query();">查询</a></span>
			</form>
				<div class="wwx_clear"></div>
	</div>
	<div class="anniu" style="left:85%;">
	    <a style="float:right;width:40px;height:50px;" href="<%=path%>/wfw/zy/zhuye?openId=${openId}" >
			   <img style="width:80%" src="<%=path%>/resources/img/wfwzy.png" >
		    </a>
	 </div>
</div> --%>

	<div class="top_01" style="background-color:#C5E0FA">
    	<span class="span_hz" style="width:60%;padding-left:3%">
        	学号&姓名&nbsp;&nbsp;
            <!--   <input class="span_input" id="appDateTime" name="time" onchange="getValue();" 
		       style="border:1px solid #dddddd;" />  -->
        </span>
        <form action="<%=path%>/wfw/ZsXsjqfxx/toXsjqfxx" id="myForm" class="wwx_f_a" method="post" style="display: inline;">
			<input type="text" class="inputhaha" style="margin-left: -155px;line-height: normal;" name="code" id="code">
			<input type="hidden" name="openId" id="openId" value="${openId}">
			<input type="hidden" name="size" id="size" value="${size }">
			<span><a class="font1" href="#" style="margin-left: 15px;font-size:14px;" onclick="query();">查询</a></span>
		</form>
      <%--   <span style="width:20%;">
        	<img src="<%=path%>/resources/img/wzy/search.png">
                                                   查询
        </span> --%>
        <span style="width:10%;background-color:#C5E0FA">
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fh1.png">
            </a>
        </span>
    </div>
    
 <div class="main_01" style="top: 38px;bottom: 0px;">
    	
    	<c:forEach var="data" items="${list}" varStatus="obj"> 
    	 	 
    		<div class="main_01_msg" style="padding-bottom: 10px;">
        	<div class="CX_title_01">
        	     学生缴欠费信息
            </div>
        	<ul class="GG_msg_01">
            	<li>
                	<img src="<%=path%>/resources/img/wzy/cha_11-05.png">
                    &nbsp;学年:<span style="color:#2e87d3;">&nbsp;&nbsp;${data.xn}</span>
                </li>
                <li>
                	<img src="<%=path%>/resources/img/wzy/cha_11.png">
                    &nbsp;学号:<span style="color:#2e87d3;">&nbsp;&nbsp;${data.xh}</span>
                </li>
                <li>
                	<img src="<%=path%>/resources/img/wzy/c11-04.png">
                    &nbsp;姓名:<span style="color:#2e87d3;">&nbsp;&nbsp;${data.xm}</span>
                </li>
                <li>
                	<img src="<%=path%>/resources/img/wzy/cha_11-06.png">
                    &nbsp;备注:<span style="color:#2e87d3;">&nbsp;&nbsp;${data.bz}</span>
                </li>
            </ul>
            <div style=" width:100%; height:3px; background-color:#e5e5e5; clear:both;"></div>
            
            <ul class="CJ_msg_01">
          
            	<li style="width:25%">
                	<img style=" margin-top:5%; margin-left:10%" src="<%=path%>/resources/img/wzy/cha1_11.png">
                	 &nbsp;缴费项目
                </li>
                <li style="width:25%">
                	<img src="<%=path%>/resources/img/wzy/cha1_11-02.png">
                	应缴金额
                </li>
                <li style="width:25%">
                	<img src="<%=path%>/resources/img/wzy/cha1_11-03.png">
                	实缴金额
                </li>
                <li style="width:25%">
                	<img src="<%=path%>/resources/img/wzy/cha1_11-03.png">
                	欠费金额
<%--                 	<c:choose> --%>
<%--             	<c:when test="${data.lx =='1'}">   --%>
<!-- 					    <p class="title">缴费金额</p> -->
<%-- 					    </c:when> --%>
<%-- 					<c:when test="${data.lx =='0'}"> --%>
<!-- 					<p class="title">欠费金额</p> -->
<%-- 					</c:when> --%>
<%-- 					</c:choose> --%>
                </li>
                </ul>
                         
               <ul class="CJ_msg_02">  
                <c:forEach var="data1" items="${jqflist}" varStatus="obj"> 
                  <c:if test="${data.xn ==data1.xn && data.xh == data1.xh}">
					   <li>
                			<span style="width:25%">${data1.jfxm }</span>
                			<span style="width:25%">${data1.yjje }</span>
	                		<span style="width:25%">${data1.sjje }</span>	                		
	                		<span style="width:25%">${data1.qfje }</span>
		                </li> 
		                   </c:if> 
		       </c:forEach>    	                                      
            </ul>
              
          <!--   <div style=" width:100%; height:20px; background-color:#23bcfc; clear:both;"></div> -->
        </div>              
    </c:forEach>
    <div class="jzgd"></div>
  <c:choose>  
   <c:when test="${empty list}">
        <div class="text">
<!-- 			<p>缴欠费信息暂无...</p> -->
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>  
		<div class="-ft" style="display:none;">
			<button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${openId }');">加载更多</button>
		</div>
   </c:when>  
   <c:otherwise>
        <div class="-ft" style="margin-left:10%;margin-right:10%;folat:left;width:80%;">
			<button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${openId }');">加载更多</button>
		</div>  
   </c:otherwise>  
</c:choose>
        </div>
</body>
</html>