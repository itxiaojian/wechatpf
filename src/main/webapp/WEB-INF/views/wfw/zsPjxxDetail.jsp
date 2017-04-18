
<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link href="<%=path%>/resources/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function return_prepage() {
		if (window.document.referrer == ""
				|| window.document.referrer == window.location.href) {
			window.location.href = "{dede:type}[field:typelink /]{/dede:type}";
		} else {
			window.location.href = window.document.referrer;
		}

	}
</script>
<style type="text/css">
.N {
	width: 99%;
}

.S {
	border: 1.5px solid #b5b5b5;
}

.S table {
	color: 字体颜色;
	font-size: 18px;
	font-family: 字体(黑体，粗体，楷体);
	background-color: #98F5FF
}

</style>

<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:150%/180% Arial, Helvetica, sans-serif, "新宋体";color:#5e5e5e;}
ul{font:120%/180% Arial, Helvetica, sans-serif, "黑体";color:#5e5e5e;}

.clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden}
.clearfix{display:inline-table}
*html .clearfix{height:1%}
.clearfix{display:block}
*+html .clearfix{min-height:1%}
.fl{float:left;}

.gradecon{width:100%;margin-top:5%;}
.rev_pro li{line-height:20px;height:20px;}
.rev_pro li .revtit{text-align:right;display:block;float:left;margin-right:4%;width:40%;}
.revinp{float:left;display:inline;}
.level .level_solid,.level .level_hollow{/* float:left; */background-image:url(<%=path%>/resources/img/icon2.png);background-repeat:no-repeat;display:inline-block;width:20px;height:20px;}
.level .level_solid{background-position:0px 0px;}
.level .level_hollow{background-position:-21px 0px;}
.revgrade{margin-left:20px;}
</style>

<script type="text/javascript">
var degree = ['','很差','差','中','良','优','未评分'];

$(function(){
	//点星星
	$(document).on('mouseover','i[cjmark]',function(){
		var num = $(this).index();
		var pmark = $(this).parents('.revinp');
		var mark = pmark.prevAll('input');
	
		if(mark.prop('checked')) return false;
		
		var list = $(this).parent().find('i');
		for(var i=0;i<=num;i++){
			list.eq(i).attr('class','level_solid');
		}
		for(var i=num+1,len=list.length-1;i<=len;i++){
			list.eq(i).attr('class','level_hollow');
		}
		$(this).parent().next().html(degree[num+1]);

	});
	//点击星星
	$(document).on('click','i[cjmark]',function(){
		var num = $(this).index();
		var pmark = $(this).parents('.revinp');
		var mark = pmark.prevAll('input');
		
		if(mark.prop('checked')){
			mark.val('');
			mark.prop('checked',false);mark.prop('disabled',true);	
		}else{
			mark.val(num);
			mark.prop('checked',true);mark.prop('disabled',false);	
		}
	});
})

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

<title>评教信息</title>
</head>
<body style="overflow: auto;" onload="wx.hideOptionMenu();">
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="教学质量评价">
	<a href="#" target="content" onfocus="this.blur()"><span>教学质量评价</span></a>
	</li>
</ul>
</div>
	<div class="main">
		<div class="DYtop">
			<img style="height: 100%; width: 100%;"
				src="<%=path%>/resources/img/JXPC.png" /> 
				<div style="position: absolute; top:3%; left: 90%;">
				<a style=" float:right;width:30px;height:30px;" href="#" onclick="return_prepage();">
					<img style="width: 70%" src="<%=path%>/resources/img/fanhui.png" />
					</a>
				</div>
		</div>
		<div class="N">
		<c:forEach var="data" items="${list}" varStatus="obj">
			<div class="S" style="width: 100%; height: 150px; margin-top: 2%;">
				
					<table style="width: 100%; height: 150px;">
						<tr>
							<td>教师工号：${data.jsgh }</td>
							<td>教师姓名：${data.jsxm }</td>
						</tr>
						<tr>
							<td>任教科目：${data.rjkm }</td>
							<td>评教学年：${data.ksxn }</td>
						</tr>
						<tr>
							<td>评教学期：${data.ksxq }</td>
						</tr>
					</table>
				
			</div>
			<div class="gradecon" id="Addnewskill_119">
			<form method="post" action="" name="myForm" id="myForm">
	<ul class="rev_pro clearfix">
		
		<li>
		<input class="fl" type="hidden" style="margin-top:2px;" name="InterviewCommentInfoSub[expAuth]" value="3" />
			<span class="revtit">准时上课情况</span>
			<div class="revinp" id="zsskqk" >
				<input type="radio" name="sk" checked="checked" value="是"/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="sk"  value="否"/>否
			</div>
		</li>
		<br />
		
		<li>
		<input class="fl" type="hidden" style="margin-top:2px;" name="InterviewCommentInfoSub[killAuth]" value="3" />
			<span class="revtit">课堂气氛评价</span>
			<div class="revinp">
				<span class="level" >
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_hollow" cjmark=""></i>
				</span>
				<span class="revgrade" id="ktqfpj">良</span>
			</div>
		</li>
		<br />
		
		<li>
		<input class="fl" type="hidden" style="margin-top:2px;" name="InterviewCommentInfoSub[followTime]" value="3" />
			<span class="revtit">师生互动评价</span>
			<div class="revinp">
				<span class="level" >
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_hollow" cjmark=""></i>
				</span>
				<span class="revgrade" id="sshdpj">良</span>
			</div>
		</li>
		<br />
		
		<li>
		<input class="fl" type="hidden" style="margin-top:2px;" name="InterviewCommentInfoSub[formality]" value="3" />
			<span class="revtit">教师态度评价</span>
			<div class="revinp">
				<span class="level" >
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_hollow" cjmark=""></i>
				</span>
				<span class="revgrade" id="jstdpj">良</span>
			</div>
		</li>
		<br />
		
		<li>
		<input class="fl" type="hidden" style="margin-top:2px;" name="InterviewCommentInfoSub[appReact]" value="3" />
			<span class="revtit">专业水平评价</span>
			<div class="revinp">
				<span class="level" >
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_hollow" cjmark=""></i>
				</span>
				<span class="revgrade" id="zysppj">良</span>
			</div>
		</li>
	</ul>
	</form>
</div>
       <div style="text-align: center;margin-top: 5%;">
			<input type="submit"  class="btn btn-primary" onclick="btnOK_onclick(${data.id});" value="评价" />
		</div>
		</c:forEach>
		</div>
	</div>
</body>
<script type="text/javascript">
	   function btnOK_onclick(id){
		   var zsskqk = $('#zsskqk input[name="sk"]:checked ').val(); 
		   var ktqfpj = $("#ktqfpj").html();
		   var sshdpj = $("#sshdpj").html();
		   var jstdpj = $("#jstdpj").html();
		   var zysppj = $("#zysppj").html();
		   //alert(zsskqk);
			   if(confirm("您确定要提交评价吗？")){
				   $.ajax({
						cache : true,
						type : "POST",
						url : "<%=path%>/wfw/ZsPjxx/saveJxzlpj",
						data: {id:id,zsskqk:zsskqk,ktqfpj:ktqfpj,
							   sshdpj:sshdpj,jstdpj:jstdpj,zysppj:zysppj},
						dataType : "json",
						success : function(request) {
							if(request.success){
							alert("评价成功");
							location.href = "<%=path%>/wfw/ZsPjxx/toPjxx";
							}else{
								alert("您已评价过,请勿重复评价！");
							}
						},
					});
				} else{
			   
		   	   return false;
		   }
	   }
</script>
</html>