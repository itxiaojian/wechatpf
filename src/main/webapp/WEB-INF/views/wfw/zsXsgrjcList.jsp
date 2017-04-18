<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3"></meta>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
    <link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-lang-zh_CN.js"></script>
    
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/index.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/cps_icons.css" />
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.ExtField.js"></script>	
	<script type="text/javascript" src="<%=path%>/resources/js/ux/Ext.ux.PagePlugins.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxGrid.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
	
		ActDealWindow = Ext.extend(Ext.Window,{
		    constructor: function(grid) {
		    	ActDealWindow.superclass.constructor.call(this, {
		            width: 800,
		            anchor: '100%',
		            maximized :true,
		            height: 400,
		            resizable : false,
		            plain: true,
		            modal: true,
		            autoScroll: true,
		            closeAction: 'close',
		            html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src=""></iframe>'
		        });
		    }
		});	
	
		function Change(){
	        var objS = document.getElementById("pid");
	        var grade = objS.options[objS.selectedIndex].value;
	        var openId = document.getElementById("openId").value;
	        location.href="<%=path%>/wfw/ZsXsjc/toXsjcByQh?ksqh="+grade+"&openId="+openId;
       	}
		
		function toMx(id){
<%-- 	        //location.href="<%=path%>/wfw/ZsXsjc/toXsjcById?id="+id; --%>
	        
	        var url = "<%=path%>/wfw/ZsXsjc/toXsjcById?id=" + id;   	
        	var html = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>';    		
        	var ACT_DEAL_WINDOW = new ActDealWindow();
        	ACT_DEAL_WINDOW.setTitle("奖惩详细信息");
        	ACT_DEAL_WINDOW.html = html;
        	ACT_DEAL_WINDOW.show();
       	}
	</script>
	<script type="text/javascript">
var i=0;
function loadMore(page,openId,qh){
	i=page;
	i++;
	var objS = document.getElementById("pid");
    var grade = objS.options[objS.selectedIndex].value;
	var url ="<%=path%>/wfw/ZsXsjc/toXsjcByQhZj";
	var html= "";
    $.ajax({
		url :url,
		data : {
			pages:i,
			openId:openId,
			ksqh:grade
		},
		type : "post",
		success : function(data) {
			var rst = eval(data);
			$.each(rst,function(i,value){             
		    html+="<div id='lidw"+i+"' class='maring1'>"
				+"<div class='wwx_f_l' style='width:100%'>"
				+"<table id='tableJyxm' style='width:100%;'>"
				+"<tr class='odd-row'>"
			    +"<td class='first'>学号</td>"
			    +"<td class='last'>"+value.xh+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>姓名</td>"
			    +"<td class='last'>"+value.xm+"</td>"
			    +"</tr>"
			    +"<tr class='odd-row'>"
			    +"<td class='first'>奖惩类型</td>"
			    +"<td class='last'>"+value.jclx+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>奖惩结果</td>"
			    +"<td class='last'>"+value.jcjg+"</td>"
			    +"</tr>"
			    +"<tr class='odd-row'>"
			    +"<td class='first'>奖惩事由</td>"
			    +"<td class='last'>"+value.jcsy+"</td>"
			    +"</tr>"
			    +"<tr class=''>"
			    +"<td class='first'>操作</td>"
			    +"<td class='last'><a href='javascript:;' onclick='toMx("+value.id+")'><span style='color:blue'>详细</span></a>"
			    +"</td>"
			    +"</tr>"
                +"</table>"
            	+"</div>"
				+"<div class='wwx_clear'></div>"
			    +"</div>";	
			})
		    $('.-ft').before(html);
			$('.btn-block').removeAttr("onclick");
			$('.btn-block').attr("onclick","loadMore('"+i+"','"+openId+"','"+qh+"')");
			},
		   error : function() {
			alert("error");
		}
	  });
}

//点击“加载更多之后”定位到最后一条数据
function ScrollDiv() { 
	var size=$("#size").val();
		if(size!=null&&size!=''){
			 if(size<=10){
	//				document.getElementById('lidw1').scrollIntoView(true);
			}else if(size%10==0){
				var num = (parseInt(size/10-1)*10+1);
				//alert(num);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}else{
				var num = (parseInt(size/10)*10);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}
		}
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

$(function() {
    $("table tr:nth-child(odd)").addClass("odd-row");
	$("table td:first-child, table th:first-child").addClass("first");
	$("table td:last-child, table th:last-child").addClass("last");
});
</script>
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
</style>
<style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:11px;}
.top_01 select{ font-size:12px; width:90px; height:17px; border-radius:2px; color:#2e87d3;margin-left:48%;margin-top:-9%;
border-width:0px;
border-top-style: none; 
border-right-style: none; 
border-left-style: none; 
border-bottom-style: none;}
.top_01 span{ float:left;}
.span_hz{float:left;font-size:11px;margin-top:3%;}
.fanhui{margin-left:70%;float:right;width:50%;}
.top_01 img{ margin-top:15%; display:block; float:left;}
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_02 li{font-size:34px; float:left; width:100%; text-align:center; line-height:100px;}
.CJ_msg_02 span{ float:left; color:#2e87d3;}
.CJ_msg_01 img{ float:left; margin-top:12%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
</style>
<style>
  th,td {
	padding-bottom: 13px;
	padding-top: 13px;
	text-align: center;
	width: 50%;
}
	</style>
	<title>我的奖惩</title>
  </head>
 
  <body style="max-width:640px;margin-left: auto;margin-right: auto;" onload="ScrollDiv();wx.hideOptionMenu();">
  <div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="奖惩查询信息">
	<a href="#" target="content" onfocus="this.blur()"><span>奖惩查询信息</span></a>
	</li>
</ul>
</div>
   <%-- <form action="<%=path%>/wfw/ZsXsjc/toXsjcByQh" method="POST" id="Form1">  --%>
   <input type="hidden" name="openId" id="openId" value="${openId }"> 
   <input type="hidden" name="size" id="size" value="${size }"> 
<!--    <div class="bg1"> -->
<!-- 	<div class="style1"> -->
<!-- 		<div class="wwx_f_l" style="position: relative;"> -->
<%-- 		<a class="font1" style="color:white;" href="<%=path%>/wfw/ZsXsjc/toXsjc?openId=${openId }">查询学年学期</a> --%>
<!-- 		<span> -->
<!-- 					<select class="SelectList width7" id="pid" onchange="Change()" style="top: -1px;left: 10px;"> -->
<%-- 						<c:if test="${empty qhlist}"> --%>
<!-- 							<option value="" >无</option> -->
<%-- 						</c:if> --%>
<%-- 						<c:forEach var="list" items="${qhlist}" varStatus="s"> --%>
<%-- 							<option value="${list.ksqh }" <c:if test="${list.ksqh==qh }">selected="selected"</c:if>>${list.ksqh }</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</span> -->
<!-- 		</div> -->
<!-- 				<div class="wwx_clear"></div> -->
<!-- 				<div class="anniu" style="left:90%;top:15%;" > -->
<%-- 				<a style="float:right;width:40px;height:50px;" href="<%=path%>/wfw/zy/zhuye?openId=${openId}" > --%>
<%-- 			   <img style="width:70%" src="<%=path%>/resources/img/wfwzy.png" > --%>
<!-- 			   </a> -->
<!-- 			      </div> -->
<!-- 	</div> -->
<!-- </div> -->

<div class="top_01">
    	<span  class="span_hz" style="width:55%; padding-left:5%;">
                                        查询学年学期:&nbsp;&nbsp;
            <select id="pid">
            	<c:if test="${empty qhlist}">
					<option value="" >无</option>
				</c:if>
				<c:forEach var="list" items="${qhlist}" varStatus="s">
					<option value="${list.ksqh }" <c:if test="${list.ksqh==qh }">selected="selected"</c:if>>${list.ksqh }</option>
				</c:forEach>
            </select>
        </span>
        <span style="width:20%;margin-top:2.5%;margin-left:4%;">
        	<a href="javascript:;" onclick="Change();" style="color: white;font-size: 14px;">
                                                   查询
            </a>
        </span>
        <span style="width:10%;">
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fanhui.png" style="width: 90%;margin-top: 15%;margin-left: 40%;">
            </a>
        </span>
    </div>

   <div class="tab-container">
				<%-- <!--我的奖惩-->
				<label style="font-size: 15pt;">微服务-奖惩查询</label><br /><br />
				<span>
					<select class="SelectList width7" id="pid" onchange="Change()">
						<c:forEach var="list" items="${qhlist}" varStatus="s">
							<option value="${list.ksqh }" <c:if test="${list.ksqh==qh }">selected="selected"</c:if>>${list.ksqh }</option>
						</c:forEach>
					</select>
				</span> --%>
				
<div>
	<div class="style4">
	<c:forEach var="data" items="${cjlist}" varStatus="obj">
		<div id="lidw${obj.count }" class="maring1">
				<div class="wwx_f_l" style="width:100%">
					<table style="width:100%;font-size: 17px;">
					    <tr>
					       <td>学号</td>
					       <td>${data.xh }</td>
					    </tr>
					    <tr>
					       <td>姓名</td>
					       <td>${data.xm }</td>
					    </tr>
					    <tr>
					       <td>奖惩类型</td>
					       <td>
					       <c:choose>
					       <c:when test="${data.JCLX =='0'}">奖励</c:when>
					       <c:when test="${data.JCLX =='1'}"><span style="color:red">惩罚</span></c:when>
					       <c:when test="${data.JCLX =='2'}">奖学金</c:when>
					       <c:when test="${data.JCLX =='3'}">助学金</c:when>
					       </c:choose>
					       </td>
					     </tr>
					     <tr>
					       <td>奖惩结果</td>
					       <td>${data.JCJG }</td>
					    </tr>
					    <tr>
					        <td>奖惩事由</td>
					        <td>${data.JCSY }</td>
					    </tr>
					    <tr>
					        <td>操作</td>
					        <td>
					        <a href="javascript:;" onclick="toMx(${data.id})"><span style="color:blue">详细</span></a>
					        </td>
					    </tr>
					</table>
				</div>
				<div class="wwx_clear"></div>
			</div>	
		</c:forEach>
<c:choose>  
   <c:when test="${empty cjlist}">
        <div class="text">
<!-- 			<p>奖惩信息暂无...</p> -->
			<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		</div>  
   </c:when>  
   <c:otherwise>
        <div class="-ft" style="margin-top: -20px;">
			<button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${openId }','${qh}');">加载更多</button>
		</div>  
   </c:otherwise>  
</c:choose>
		</div>		
		</div>
	</div>
		<!-- </form>	 -->	
  </body>
  <script type="text/javascript">var str="";</script>
<script type="text/javascript" src="<%=path%>/resources/js/wysy.js"></script>
<script type="text/javascript">
var cn=4;
//onload时触发水印绘制
window.onload=function(){
	watermark({ watermark_txt: "${text}" });
	for(var i=0;i<cn;i++){
		var my=document.getElementById('mask_div0'+i);
		if(my!=null){
			var p=my.parentNode;
			p.removeChild(my);
			//my.remove();
		}
	}
};

//onresize时触发水印绘制
window.onresize = function () {
	deleteWatermark();
	watermark({ watermark_txt: "${text}" });
	for(var i=0;i<cn;i++){
		var my=document.getElementById('mask_div0'+i);
		if(my!=null){
			var p=my.parentNode;
			p.removeChild(my);
			//my.remove();
		}
	}
};
</script>
</html>
