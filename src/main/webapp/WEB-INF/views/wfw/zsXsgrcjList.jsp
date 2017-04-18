<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<title>我的成绩</title>
</head>
<style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:10px;}
.top_01 select{ font-size:12px; width:60% ; height:17px; border-radius:2px; color:#2e87d3;
border-width:0px;
border-top-style: none; 
border-right-style: none; 
border-left-style: none; 
border-bottom-style: none;}
.top_01 span{ float:left;}
.span_hz{float:left;font-size:11px;margin-top:3%;}
.fanhui{margin-left:70%;float:right;width:50%;}
.top_01 img{ margin-top:5%; display:block;}
.main_01{width:100%; position:absolute; top:38px; left:0; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:25px; background-color:#23bcfc; color:#fff; line-height:25px; text-align:center;font-size: 14px;}
.GG_msg_01 li{ width:100%; height:25px; line-height:25px;font-size: 12px; }
.GG_msg_01 img{ float:left; margin-top:0; margin-left:5%;width: 25px;height: 25px;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ height:25px; line-height:25px; float:left;font-size: 12px;}
.CJ_msg_02 li{float:left; width:100%; text-align:center; line-height:25px;font-size: 12px;}
.CJ_msg_02 span{ float:left; color:#2e87d3;}
.CJ_msg_01 img{ float:left; margin-top:0; margin-left:0%;width: 25px;height: 25px;}
.foot_01{width:100%; height:38px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
/**页面表格的行高*/
th,td{padding-bottom:2px;padding-top:2px;}
</style>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
		function Change(){
	        var objS = document.getElementById("pid");
	        var openId = document.getElementById("openId").value;
	        var grade = objS.options[objS.selectedIndex].value;
	        location.href="<%=path%>/wfw/ZsXscj/toXscj?ksqh="+grade+"&openId="+openId;
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
<body onload="wx.hideOptionMenu();">
<input type="hidden" name="openId" id="openId" value="${openId }">
<input type="hidden" name="size" id="size" value="${size }"> 
<div class="phone_01">
<!-- 	<div class="top_01"> -->
<!--     	<span style="width:50%; padding-left:5%;margin-top: 14px;"> -->
<!--         	学年学期&nbsp;&nbsp; -->
<!--             <select id="pid"> -->
<%--             	<c:if test="${empty qhlist}"> --%>
<!-- 					<option value="" >无</option> -->
<%-- 				</c:if> --%>
<%-- 				<c:forEach var="list" items="${qhlist}" varStatus="s"> --%>
<%-- 					<option value="${list.ksqh }" <c:if test="${list.ksqh==qh }">selected="selected"</c:if>>${list.ksqh }</option> --%>
<%-- 				</c:forEach> --%>
<!--             </select> -->
<!--         </span> -->
<!--         <span style="width:20%;margin-left: 30px;margin-top: 14px;"> -->
<!--         	<a href="javascript:;" onclick="Change();" style="color: white;"> -->
<!--                                                    查询 -->
<!--             </a> -->
<!--         </span> -->
<!--         <span style="width:10%"> -->
<%--         	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}"> --%>
<%--         		<img src="<%=path%>/resources/img/wzy/fanhui.png" style="width: 100%;"> --%>
<!--             </a> -->
<!--         </span> -->
<!--     </div> -->

<div class="top_01">
    	<span  class="span_hz" style="width:65%; padding-left:5%;">
                                        学年学期:&nbsp;&nbsp;
            <select id="pid" onchange="Change()">
            	<%-- <c:if test="${empty qhlist}">
					<option value="" >无</option>
				</c:if> --%>
				<option value="">请选择---</option>
				  	<c:forEach var="list" items="${qhlist}" varStatus="s">
				  		<c:forEach var="i" begin="1" end="2">
				  		<c:if test="${i%2!=0}">
							<option value="${list.ksqh}" <c:if test="${list.ksqh==qh}">selected="selected"</c:if>>${list.ksqh}</option>
						</c:if>
						<c:if test="${i%2==0}">
							<option value="${list.ksqh1}" <c:if test="${list.ksqh1==qh}">selected="selected"</c:if>>${list.ksqh1}</option>
						</c:if>
						</c:forEach>
				    </c:forEach>
				
            </select>
        </span>
<!--         <span style="width:20%;margin-top:2.5%;margin-left:1%;"> -->
<!--         	<a href="javascript:;" onclick="Change();" style="color: white;font-size: 14px;"> -->
<!--                                                    查询 -->
<!--             </a> -->
<!--         </span> -->
        <span style="width:9%;margin-top: 1%;">
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fh1.png" style="width: 60%;float:left;margin-left:90%;">
            </a>
        </span>
    </div>

    <div class="main_01">
<%--     	<c:if test="${empty xslist}"> --%>
<%--     		<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%"> --%>
<%--     	</c:if> --%>
    	<c:forEach var="data1" items="${xslist}" varStatus="obj1">
    		<div class="main_01_msg">
        	<div class="CX_title_01">
            	查询结果
            </div>
        	<ul class="GG_msg_01">
            	<li>
                	<img src="<%=path%>/resources/img/wzy/cha_11.png">
                    &nbsp;学生学号:<span style="color:#2e87d3;">&nbsp;&nbsp;${data1.xh}</span>
                </li>
                <li>
                	<img src="<%=path%>/resources/img/wzy/c11-04.png">
                    &nbsp;学生姓名:<span style="color:#2e87d3;">&nbsp;&nbsp;${data1.xm}</span>
                </li>
                <li>
                	<img src="<%=path%>/resources/img/wzy/cha_11-05.png">
                    &nbsp;考试学年:<span style="color:#2e87d3;">&nbsp;&nbsp;${data1.ksxn}</span>
                </li>
                <li>
                	<img src="<%=path%>/resources/img/wzy/cha_11-06.png">
                    &nbsp;考试学期:<span style="color:#2e87d3;">&nbsp;&nbsp;${xq}</span>
                </li>
            </ul>
            <div style=" width:100%; height:2px; background-color:#e5e5e5; clear:both;"></div>
            <ul class="CJ_msg_01">
            	<li style="width:40%">
                	<img style=" margin-top:0; margin-left:10%" src="<%=path%>/resources/img/wzy/cha1_11.png">
                	 &nbsp;科目
                </li>
                <li style="width:30%">
                	<img src="<%=path%>/resources/img/wzy/cha1_11-02.png">
                	成绩
                </li>
                <li style="width:30%">
                	<img src="<%=path%>/resources/img/wzy/cha1_11-03.png">
                	学分
                </li>
                </ul>
               <ul class="CJ_msg_02">
                <c:forEach var="data" items="${cjlist}" varStatus="obj">
                	<c:if test="${data1.xh == data.xh && data1.ksxn == data.ksxn && data1.ksxq == data1.ksxq}">
	                	<li>
                			<span style="width:40%">${data.KSKM }</span>
                			<span style="width:30%">${data.KSCJ }</span>
	                		<span style="width:30%">${data.XF }</span> 
		                </li>
                	</c:if>
                </c:forEach>
                <li class="jzgd"></li>
            </ul>
            <div style=" width:100%; height:2px; background-color:#23bcfc; clear:both;"></div>
        </div>
    	</c:forEach>
   	   <c:if test="${!empty cjlist}">  
   			<div class="-ft" style="margin-bottom:0rem;width:100%;height:15%;">
	           <button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${openId }','${qh}');" 
	               style="position:relative;bottom:0px;width:80%;height:40px;font-size:15px;margin-left:10%;">加载更多</button>
            </div> 
        </c:if>
        </div>
    </div>
<!--     <div class="foot_01"> -->
<%--     	<img src="<%=path%>/resources/img/wzy/BQ.png" width="100%" height="100%"> --%>
<!--     </div> -->
	<c:if test="${empty cjlist}">  
       		 <div class="text">
				<img style="margin-top:25px;" alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
			</div>  
     </c:if>  
</body>
<script type="text/javascript">
var i=0;
function loadMore(page,openId,qh){
	i=page;
	i++;
	var url ="<%=path%>/wfw/ZsXscj/toXscjByQh";
	var html="";
	var html1="";
	var html2="";
	var html3="";
    $.ajax({
		url :url,
		data : {
			pages:i,
			openId:openId,
			ksqh:qh
		},
		type : "post",
		success : function(data) {
			if(data[0].length=='0' && data[1].length=='0' ){
				$(".btn-block").text("已经是最后一页了");
			}else{
			var rst = eval(data[0]);
			var rst1 = eval(data[1]);
			$.each(rst,function(i,value0){
			 if(value0.jsmc!='ROLE_STUDENT'){
			     $.each(rst1,function(j,value1){
	            	   if(value0.xh==value1.xh && value0.ksxn==value1.ksxn && value0.ksxq==value1.ksxq){
	               html2+="<li>"
              			+"<span style='width:40%'>"+value1.kskm+"</span>"
              			+"<span style='width:30%'>"+value1.kscj+"</span>"
	                	+"<span style='width:30%'>"+value1.xf+"</span>" 
		                +"</li>";
	            	   }
					})
				html+=
			     "<div class='main_01_msg'>"
	        		+"<div class='CX_title_01'>"
	            	+"查询结果"
	           	    +"</div>"
	        	    +"<ul class='GG_msg_01'>"
	            	+"<li>"
	                +"<img src='<%=path%>/resources/img/wzy/cha_11.png'>"
	                +"&nbsp;学生学号:<span style='color:#2e87d3;'>&nbsp;&nbsp;"+value0.xh+"</span>"
	                +"</li>"
	                +"<li>"
	                +"<img src='<%=path%>/resources/img/wzy/c11-04.png'>"
	                +"&nbsp;学生姓名:<span style='color:#2e87d3;'>&nbsp;&nbsp;"+value0.xm+"</span>"
	                +"</li>"
	                +"<li>"
	                +"<img src='<%=path%>/resources/img/wzy/cha_11-05.png'>"
	                +"&nbsp;考试学年:<span style='color:#2e87d3;'>&nbsp;&nbsp;"+value0.ksxn+"</span>"
	                +"</li>"
	                +"<li>"
	                +"<img src='<%=path%>/resources/img/wzy/cha_11-06.png'>"
	                +"&nbsp;考试学期:<span style='color:#2e87d3;'>&nbsp;&nbsp;"+value0.xq+"</span>"
	                +"</li>"
	                +"</ul>"
	                +"<div style='width:100%; height:2px; background-color:#e5e5e5; clear:both;'></div>"
	                +"<ul class='CJ_msg_01'>"
	            	+"<li style='width:40%'>"
	                +"<img style='margin-top:0; margin-left:10%' src='<%=path%>/resources/img/wzy/cha1_11.png'>"
	                +"&nbsp;科目"
	                +"</li>"
	                +"<li style='width:30%'>"
	                +"<img src='<%=path%>/resources/img/wzy/cha1_11-02.png'>"
	                +"成绩"
	                +"</li>"
	                +"<li style='width:30%'>"
	                +"<img src='<%=path%>/resources/img/wzy/cha1_11-03.png'>"
	                +"学分"
	                +"</li>"
	                +"</ul>"
	                +"<ul class='CJ_msg_02'>"
	                +html2
	                +"</ul><div style='width:100%; height:2px; background-color:#23bcfc; clear:both;'></div></div>";
	                html2="";
	           //	  +"</ul>"
	         //  +" <div style='width:100%; height:2px; background-color:#23bcfc; clear:both;'></div></div>";
			}else{
				 $.each(rst1,function(j,value1){
	            	   if(value0.xh==value1.xh && value0.ksxn==value1.ksxn && value0.ksxq==value1.ksxq){
	            		   html1+="<li>"
	                			+"<span style='width:40%'>"+value1.kskm+"</span>"
	                			+"<span style='width:30%'>"+value1.kscj+"</span>"
		                		+"<span style='width:30%'>"+value1.xf+"</span>" 
			                	+"</li>";  
	            	   }
				  });
			   }
			});
			}
		    $('.-ft').before(html);
		    $('.jzgd').before(html1);
			$('.btn-block').removeAttr("onclick");
			$('.btn-block').attr("onclick","loadMore('"+i+"','"+openId+"','"+qh+"')");
			},
		   error : function() {
			alert("error");
		}
	  });
	
}
</script>
<script type="text/javascript">var str="";</script>
<script type="text/javascript" src="<%=path%>/resources/js/wysy.js"></script>
<!-- <script type="text/javascript"> -->
<!-- var cn=1; -->
<!-- //onload时触发水印绘制 -->
<!-- window.onload=function(){ -->
<%-- 	watermark({ watermark_txt: "${text}" }); --%>
<%-- 	for(var i=0;i<cn;i++){ --%>
<%-- 		var my=document.getElementById('mask_div0'+i); --%>
<%-- 		if(my!=null){ --%>
<%-- 			var p=my.parentNode; --%>
<%-- 			p.removeChild(my); --%>
<%-- 			//my.remove(); --%>
<%-- 		} --%>
<%-- 	} --%>
<%-- }; --%>

<%-- //onresize时触发水印绘制 --%>
<%-- window.onresize = function () { --%>
<%-- 	deleteWatermark(); --%>
<%-- 	watermark({ watermark_txt: "${text}" }); --%>
<%-- 	for(var i=0;i<cn;i++){ --%>
<%-- 		var my=document.getElementById('mask_div0'+i); --%>
<%-- 		if(my!=null){ --%>
<%-- 			var p=my.parentNode; --%>
<%-- 			p.removeChild(my); --%>
<%-- 			//my.remove(); --%>
<%-- 		} --%>
<%-- 	} --%>
<%-- }; --%>
<!-- </script> -->
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
</html>
