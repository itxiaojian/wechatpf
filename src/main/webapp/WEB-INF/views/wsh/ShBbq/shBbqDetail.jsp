<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js" />
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js" />
<script type="text/javascript" src="<%=path%>/resources/js/app.js" />
<script src="<%=path%>/libs/js/framework.js" type="text/javascript" />
<script src="<%=path%>/resources/js/wzy/jquery.js"></script>
<script src="<%=path%>/resources/js/wzy/json2.js"></script>
<link type="text/css" href="<%=path%>/resources/css/tucao.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/bbq.css" />

<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<title>表白墙</title>
<style>
/*全局*/
*,li,ul,a{ margin:0px; padding:0px; font-family:'微软雅黑'; list-style:none; text-decoration:none;}
/*微主页*/
.WZY_top{ width:100%; height:100px; position:fixed; left:0px; top:0px;background:url(../image/logo.png) center no-repeat; background-size:cover; overflow:hidden;}
.WZY_top img{ float:right; margin-right:5%; margin-top:1%;}
.WZY_main{ width:100%;position:absolute; top:100px; left:0px; bottom:40px;overflow:auto; background: url(../image/chatu02.png) no-repeat bottom right; background-size: contain;}
.WZY_banner{width:100%; height:400px; }
.WZY_icon img{ display:block; margin-left:20%;}
.WZY_icon span{ width:25%; text-align:center; float:left; font-size:36px; color:#666; line-height:50px; margin-top:6%;}
.WZY_foot{width:100%; height:40px; position:absolute; left:0px; bottom:0px;background-color:#EBA2A3;background: url(../image/banquan.png) center no-repeat; background-size:cover;overflow:hidden;}
/*学校新闻*/
.New_main{ width:100%;position:absolute; top:100px; left:0px; bottom:40px;overflow:auto;background-color:#e5e5e5;}
.NEW_msg{ width:90%;}
.msg_img01{ margin-right:30px; margin-top:30px; float:right; width:140px; height:140px; background:url(../image/new1.png) no-repeat center; background-size:cover; line-height:120px; text-align:center; font-size:50px; color:#fff;}
.msg_img02{ margin-right:30px; margin-top:30px; float:right; width:140px; height:140px;background:url(../image/new2.png) no-repeat center; background-size:cover;background-size:cover; line-height:120px; text-align:center; font-size:50px; color:#fff;}
.msg_img03{ margin-right:30px; margin-top:30px; float:right; width:140px; height:140px;background:url(../image/new3.png) no-repeat center; background-size:cover;background-size:cover; line-height:120px; text-align:center; font-size:50px; color:#fff;}
.msg_img04{ margin-right:30px; margin-top:30px; float:right; width:140px; height:140px;background:url(../image/new4.png) no-repeat center; background-size:cover;background-size:cover; line-height:120px; text-align:center; font-size:50px; color:#fff;}
.msg_img05{ margin-right:30px; margin-top:30px; float:right; width:140px; height:140px;background:url(../image/new5.png) no-repeat center; background-size:cover;background-size:cover; line-height:120px; text-align:center; font-size:50px; color:#fff;}

.NEW_msg{ width:94%;background-color:#fff;border-radius:10px; margin-top:40px; float:left; margin-left:3%;}
.NEW_msg li,ul{ width:100%; float:left;}
.NEW_msg li{ border-bottom:1px solid #ccc; padding-bottom:30px;}
.new_msg01{ font-size:40px; color:#333; margin-top:30px; margin-left:20px; float:left}
.new_msg02{ font-size:32px; color:#999; margin-top:30px;margin-left:5px;float:left}
/*专题评论*/
.zhanti_main{ width:100%;position:absolute; top:100px; left:0px; bottom:40px;overflow:auto;background-color:#fff;}
.zhanti_title{ width:100%; height:100px;font-size:36px; line-height:100px; color:#333; float:left}
.zhanti_title img{ float:left;}
.zhanti_mainBox h1{ text-align:center; font-size:40px; margin-top:20px; color:#333; float:left; width:100%; padding-bottom:40px;}
.zhanti_mainBox h2{ text-align:center; font-size:32px; margin-top:20px; color:#2991e6; float:left; width:100%; padding-bottom:40px;}
.zhanti_mainBox p{font-size:32px;text-align:center;color:#333; float:left; width:90%; padding-left:5%;padding-right:5%;}
.zhanti_mainBox img{ margin-top:40px;padding-bottom:40px;}
.zhanti_pinglun img{ float:left; margin-left:60px;}
.zhanti_pinglun ul{ margin-top:20px;}
.zhanti_pinglun li{ width:100%; float:left; margin-top:20px;}
.pL_name{ line-height:70px; font-size:36px; color:#2991e6;float: left;}
.shijian{ float:right; color:#999;line-height:80px; font-size:28px; margin-right:60px;}
.pL_main{ line-height:50px; font-size:32px; color:#333; clear:both; padding-left:18%; border-bottom:1px #ccc solid;padding-right:10%;padding-bottom:10px; }
.PL_box input{ float:left; width:250px; height:80px; font-size:36px; border:none; border-radius:10px; background-color:#e68f29; color:#fff; margin-top:30px; margin-left:40%}
.PL_box{ border-bottom:1px solid #ccc; float:left; width:100%; padding-bottom:50px; display:none;}
/*我要投票*/
.WZY_top01{ width:100%; position:fixed; left:0px; top:0px;overflow:hidden;}
.New_main01{ width:100%; margin-top:38px; left:0px;overflow:auto;}
.WZY_foot01{width:100%; height:20px; position:absolute; left:0px; bottom:0px; background-color:#000000;overflow:hidden;}
.Home_btn{ position:absolute; right:20px; top:24%; z-index:2;width:10%}
.WZY_foot02{width:100%; height:35px; position:absolute; left:0px; bottom:0px; background-color:#ec9dfd;overflow:hidden;}
.biaobai_box{ line-height:38px; color:#fff;}
.biaobai_box a{ height:30px;}
.biaobai_msg{ width:100%; border:#ec9dfd 1px solid; background-color:#fff; border-radius:4px; margin-bottom:10px; margin-top:10px;float:left}
.biaobai_title{ width:100%; height:40px;background-color:#ec9dfd;border-radius:4px 4px 0 0;float:left}
.biaobai_touxiang{line-height:35px; color:#fff; float:left}
.biaobai_time{line-height:35px; color:#fff; float:right;}
.biaobai_pinglun span{ width:50%; height:35px; line-height:35px; text-align:center; color:#ec9dfd; box-sizing:border-box;}
.biaobai_pinglun img{ margin-top:-3px;}
</style>
<style>
#replay {
	z-index: 1000;
	display: none;
}
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
</head>


<body>
	<div class="iphone">
		<div class="WZY_top01" style="height:50px;">
				<div class="logo" style="height:50px;">
					<img src="<%=path%>/resources/img/wzy/logo.png" style="width:100%;height:100%;">
					<a href="<%=path%>/wsh/zy/zhuye?openId=${openId}">
						<img class="Home_btn" src="<%=path%>/resources/img/wzy/FH.png">
					</a>
				</div>
		</div>
		<div class="New_main01" style="background-color:#f7dffc;">
			<div class="container_fluid">
				<img src="<%=path%>/resources/img/wzy/banner_biaobaiqiang.png" style="width:100%;">
			</div>
		</div>
		<div class="container" style="width:90%;margin-left:5%;">
			<c:forEach items="${list}" var="list" varStatus="obj">
			<div class="biaobai_msg">
				<div class="biaobai_title">
					<c:if test="${list.tcrxm=='***'}">
						<div class="biaobai_touxiang" style="width:20%;">
						&nbsp;&nbsp;
							<img src="<%=path%>/resources/img/wzy/wsh_nmtx.png" style="width:50%;margin-top:5%;">
						</div>
					</c:if>
					<c:if test="${list.tcrxm!='***' }">
					<div class="biaobai_touxiang" style="width:20%;">
						&nbsp;&nbsp;
							<img src="${list.txdz}" style="width:50%;margin-top:5%;">
					</div>
					</c:if>
					<div class="biaobai_touxiang" style="margin-left:-5%;">
						&nbsp;
						${list.tcrxm}
					</div>
					<c:forEach items="${yhid}" var="yhid" varStatus="obj" begin="0" end="0">
					<c:if test="${yhid.yhid=='1'||yhid.dlm=='admin'||list.tcr==openId}">
						<div class="biaobai_time">
							<a class="S_txt2" href="javascript:void(0);" onclick="DisplayDelete('${list.id }','${openId}')" style="color:#fff;">
							&nbsp;&nbsp;
							<span>删除</span>
							&nbsp;&nbsp;
							</a>
						</div>
					</c:if>
					</c:forEach>
					<div class="biaobai_time">
						<span>${list.tcsj}</span>
						&nbsp;&nbsp;
					</div>
				</div>
				<div class="container" style="padding-top:10px; padding-bottom:10px; border-bottom:1px #ec9dfd solid; float:left; width:100%">
					&nbsp;&nbsp;&nbsp;&nbsp;${list.tcnr}
				</div>
				<div class="biaobai_pinglun">
					<a class="S_txt2" href="javascript:void(0);" onclick="DisplayReply(${list.id})">
						<span class="line S_line1">
							<span style="width:10%;margin-left:10%;float:left;">
								<img src="<%=path%>/resources/img/wzy/BB_icon01.png" style="width:100%;margin-top:3%;">
							</span>
							<span style="float:left;width:15%;">评论(${list.hfcs})</span>
						</span>
					</a>
					<a class="S_txt2" href="javascript:void(0);" onclick="DoTCZhan('${list.id}','${openId}')">
						<span class="pos">
							<span style="width:10%;margin-left:20%;float:left;">
								<img src="<%=path%>/resources/img/wzy/BB_icon02.png" style="width:100%;margin-top:3%;">
							</span>
							<span class="line S_line1" style="border-right-width: 0px;">
								祝福(<span id="t_tucao_zhan_${list.id}">${list.bzcs}</span>)
							</span>
						</span>
					</a>
					<div class="tcreply" style="padding-top: 0em;">
						<c:forEach items="${list.listHF}" var="listHF">
							<div id="t_tucao_morereply_">
								<div class="tcreplaylist" style="margin-top: 0.3em;">
									<span class="tcreplayname" style="width:20%;"> ${listHF.hfrxm }:</span> 
									<span class="tcreplaymsg" style="width:70%;text-align:left;"> ${listHF.hfnr} </span>
									<br/>
									<span class="tctime" style="width:50%;margin-top:0%;">${listHF.hfsj} </span>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				</div>
		   </c:forEach>
		   <div class="LsMore"></div>
     	</div>
     	<c:if test="${!empty list}">
           <div class="-ft" style=" padding-bottom: 20%;">
			<button id="-ft" class="btn btn-default btn-block btn-lg ng-binding jzgd" style="width: 100%;font-size:13px;color:#ec9dfd"
			onclick="loadMore('${pages}','${openId }');">加载更多</button>
		   </div>
	    </c:if>
	</div>
   	<div class="tcsubmit" style="background-color:#ec9dfd;">
   		<div class="biaobai_box" style="font-size:13px;">
    		<a href="#" onclick="DisplaySubmit();" style="color:#fff;">
    			<img src="<%=path%>/resources/img/wzy/BB_icon03.png" style="width:25px;height:25px;float:left;padding-left:40%;padding-top:1%;">&nbsp;&nbsp;我要表白</a>
   		</div>
   		<div class="tcsubmitbar">
		<p>
			<textarea id="t_tucao_text" maxlength="140" rows="4"></textarea>
		</p>
		<div class="tcsubmitbutton">
			<span class="tcsubmitbutton_num">140字</span> <a class="tcsubmitb"
				href="#" onclick="niming('${openId}');">发送</a> <a class="tcsubmitb" href="#"
				onclick="DisplaySubmit();">取消</a>
		</div>
		</div>
   	</div>
	<div id="replay" class="tcsubmit">
		<div class="biaobai_box">
			<a href="#"><img
				src="<%=path%>/resources/img/write.png" style="width:25px;height:25px;float:left;padding-left:40%;padding-top:1%;"/> 回复表白</a>
		</div>
		<p>
			<textarea id="t_tucao_reply" maxlength="140" rows="4"></textarea>
		</p>
		<div class="tcsubmitbutton">
			<span class="tcsubmitbutton_num">140字</span> <a class="tcsubmitb"
				href="#" onclick="DoTCReply('${openId}');">回复</a> <a class="tcsubmitb" href="#"
				onclick="DisplayReply();">取消</a>
		</div>
	</div>

	<form method="post"
		action="tucao.aspx?OpenID=oRvupjoWhCKA3zufAtcpx0XnFtI8" id="form1">
		<div class="aspNetHidden">
			<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"
				value="/wEPDwUKMTY0MDc2Nzg2N2Rk9Cyx3Iii1rsx0f/do2egB6ykfZPbyTNcn1e+64jQGu8=" />
		</div>

		<div class="aspNetHidden">

			<input type="hidden" name="__VIEWSTATEGENERATOR"
				id="__VIEWSTATEGENERATOR" value="ADBD8E75" />
		</div>
	</form>

<script>
//JavaScript Document
var time=3;
var openId = '${openId}';
var stop;
var Alert={	  	
	//确认
		showConfirmMsg:function(obj){
			var subhtml='<div id="alert_dialog_show_msg_box" style=" overflow:hidden;height:100px;width:200px;margin:15% auto;">'
			+'<div class="sweet-overlay" tabIndex="-1" style=" background-color:#000; opacity:0.4; position: fixed; left: 0; right: 0; top: 0; bottom: 0; z-index:1000;"></div>'
			+'<div id="alert_show_3" style="height:100px; width:280px; background-color:#fff; color:#000; border-radius:8px;font-size:14px; text-align:center;z-index:4000;left:10%;top:40%;position:absolute;">'
			+'<p style="font-size:14px; margin-top:10px;margin-left:10%;margin-right:5%;">'+obj+'</p>'
			+'<input name="button" onclick="DoBiaoBai1(openId)" type="button" value="是" style=" width:60px; height:30px; line-height:30px;font-size:14px;position:absolute;bottom:10%;left:25%;"  />'
			+'<input name="button" onclick="DoBiaoBai(openId)" type="button" value="否" style=" width:60px; height:30px; line-height:30px;font-size:14px;position:absolute;bottom:10%;left:60%;"  /></div></div>';
				$("body").append(subhtml);
			 callback = callback || function(){};
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
	
function niming(openId){
	Alert.showConfirmMsg("是否匿名发送表白信息？");
}
</script>

<script>
var i=0;
function loadMore(page,openId){
		i=page;
		i++;
		var size = (i-1)*10;
		var url ="<%=path%>/wzy/ZyWdtc/ShBbqLoadmore?";
		var html="";
		var html1="";
		var html2="";
		var html3="";
		$.ajax({
			url :url,
			data : {
				pages:i,
				openId:openId
			},
			type : "post",
			success : function(data) {
				if(data[0].length>0){
				var rst = eval(data[0]);
				var rst1 = eval(data[1]);
				$.each(rst,function(i,value){
					if(value.tcrxm=='***'){
				  	     html1 = "<div class='biaobai_touxiang' style='width:20%;'>"
				  	     +"&nbsp;&nbsp;"
				  	     +"<img src='<%=path%>/resources/img/wzy/wsh_nmtx.png' width='36' height='36'>"
				  	     +"&nbsp;&nbsp;"
				  	     +"</div>";
			  	   	}
			  	    if(value.tcrxm!='***'){
			  	    	 html1 = "<div class='biaobai_touxiang' style='width:20%;'>"
			  	    		   +"&nbsp;&nbsp;"
			  	    	       +"<img src='"+value.txdz+"' width='36' height='36'>"
			  	    	       +"&nbsp;&nbsp;"
			  	    	       +"</div>";
			  	    }
			  	  if(value.yhid=='1'||value.dlm=='admin'||value.tcr==value.wxh){
					 	 html2 = "<a class='S_txt2' href='javascript:void(0);' onclick='DisplayDelete("+value.id+","+value.tcr+")' style='color:#fff;'>"
					 	 +"&nbsp;&nbsp;"
					 	 +"<span>删除</span>"
					 	 +"&nbsp;&nbsp;"
					 	 +"</a>";
					 }else{
						 html2 =""
					 }
			  	$.each(rst1,function(j,value2){
			  	  if(value.id==value2.tczj){
			  		  html3 +="<div id='t_tucao_morereply_'>"
					        +"<div class='tcreplaylist' style='margin-top: 0.3em;'>"
					        +"<span class='tcreplayname' style='width:20%;'>"+value2.hfrxm+":</span>"
					        +"<span class='tcreplaymsg' style='width:70%;text-align:left;'>"+value2.hfnr+"</span>"
					        +"<br/>"
					        +"<span class='tctime' style='width:50%;margin-top:0%;'>"+value2.hfsj+"</span>"
					        +"</div>"
					        +"</div>"; 
			  	  }else{
			  		  html3+="";
			  	  }
			  	  })
					html += "<div class='biaobai_msg'>"
						 +"<div class='biaobai_title'>"
						 +html1
						 +"<div class='biaobai_touxiang' style='margin-left:-5%;'>"
						 +"&nbsp;"
						 +value.tcrxm
						 +"</div>"
						 +"<div class='biaobai_time'>"
						 +html2
						 +"</div>"
						 +"<div class='biaobai_time'>"
						 +"<span>"
						 +value.tcsj
						 +"</span>"
						 +"&nbsp;&nbsp;"
						 +"</div>"
					     +"</div>"
					     +"<div class='container' style='padding-top:10px; padding-bottom:10px; border-bottom:1px #ec9dfd solid; float:left; width:100%'>"
					     +"&nbsp;&nbsp;&nbsp;&nbsp;"
					     +value.tcnr
					     +"</div>"
					     +"<div class='biaobai_pinglun'>"
					     +"<a class='S_txt2' href='javascript:void(0);' onclick='DisplayReply("+value.id+")'>"
					     +"<span class='line S_line1'>"
					     +"<span style='width:10%;margin-left:10%;float:left;'>"
					     +"<img src='<%=path%>/resources/img/wzy/BB_icon01.png' style='width:100%;margin-top:3%;'>"
					     +"</span>"
					     +"<span style='float:left;width:15%;'>评论("+value.hfcs+")</span>"
					     +"</span>"
					     +"</a>"
					     +"<a class='S_txt2' href='javascript:void(0);' onclick='DoTCZhan("+value.id+","+value.wxh+")'>"
					     +"<span class='pos'>"
					     +"<span style='width:10%;margin-left:10%;float:left;'>"
					     +"<img src='<%=path%>/resources/img/wzy/BB_icon02.png' style='width:100%;margin-top:3%;'>"
					     +"</span>"
					     +"<span class='line_S_line1' style='border-right-width: 0px;'>祝福(<span id='t_tucao_zhan_"+value.id+"'>"+value.bzcs+"</span>)</span>"
					     +"</span>"
					     +"</a>"
					     +"<div class='tcreply' style='padding-top: 0em;'>"
					     +html3
					     +"</div>"
					     +"</div>"
					     +"</div>"
					     +"</div>";
					     html3="";
				})
	    $('.LsMore').before(html);
		$('.jzgd').removeAttr("onclick");
		$('.jzgd').attr("onclick","loadMore('"+i+"','"+openId+"')");
		}else{
			html="<div class='-ft' style='margin-top: 20px;'>"
				+"<button class='btn btn-default btn-block btn-lg ng-binding' style='position:relative;bottom:0px;'>已经是最后一页了</button>"
				+"</div>";
				$('#-ft').remove();
			    $('.-ft').before(html);
		}
		},
	   error : function() {
		alert("error");
	}
  });
} 



function DisplaySubmit() {   //发话题
	if ($(".tcsubmitbar").css("display") == "block") {
		$(".tcsubmitbar").css("display", "none");
	} else {
		$(".tcsubmitbar").css("display", "block");
	}
}


	
	function DoTCZhan(id,openId) {    //祝福
		//var openId = $("#openid").val();
		var openId = openId;
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "<%=path%>/wzy/ZyWdtc/doTczhan",
						data : {id:id,openId:openId},
						//contentType : "application/json; charset=utf-8",
						dataType : "json",
						success : function(msg) {
							if(msg.success){
								$("#t_tucao_zhan_" + id).text(
										parseInt($("#t_tucao_zhan_" + id)
												.text()) + 1);
								alert("谢谢您的支持^0^");
							}else{
								alert("您已祝福过，请勿重复^0^");
							}
						}
						
					});
				});
	}
	
	var RTid = "";
	function DisplayReply(id) { //我要回复
		if ($("#replay").css("display") == "block") {
			$("#replay").css("display", "none");
			RTid = "";
		} else {
			$("#replay").css("display", "block");
			RTid = id;
		}
	}
	
	function DoTCReply(openId) {   //表白回复
		if ($("#t_tucao_reply").val() == "") {
			return;
		}
		var hfnr = $("#t_tucao_reply").val();
		//var openId = $("#openid").val();
		//var openId = 'ocFFwuHOpoUHVQQGNgcRsMFbYVGg';
		var openId = openId;
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "<%=path%>/wzy/ZyWdtc/doTcreply",
						data : {hfnr:hfnr, id:RTid, openId:openId},
						//contentType : "application/json; charset=utf-8",
						dataType : "json",
						success : function(msg) {
							 document.location.reload();
						}
					});
				});
	};
	function DoBiaoBai(openId) {   //发布表白墙
		if ($("#t_tucao_text").val() == "") {
			return;
		}
		var tcnr = $("#t_tucao_text").val();
		//var openId = $("#openid").val();
		var openId = openId;
		var nm = "";
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "<%=path%>/wzy/ZyWdtc/doBiaoBai",
						data : {tcnr:tcnr,openId:openId,nm:"1"},
						//contentType : "text/plain",
						dataType : "json",
						success : function(msg) {
							if (msg.success) {
								document.location.reload();
							} else {
								alert(msg.message);
							}
						}
					});

				});
		}
	
	function DoBiaoBai1(openId) {   //发布表白墙
		if ($("#t_tucao_text").val() == "") {
			return;
		}
		var tcnr = $("#t_tucao_text").val();
		//var openId = $("#openid").val();
		var openId = openId;
		var nm = "";
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "<%=path%>/wzy/ZyWdtc/doBiaoBai",
						data : {tcnr:tcnr,openId:openId,nm:"0"},
						//contentType : "text/plain",
						dataType : "json",
						success : function(msg) {
							if (msg.success) {
								document.location.reload();
							} else {
								alert(msg.message);
							}
						}
					});

				});
		}
	//删除  DisplayDelete
	function DisplayDelete(id,openId) {    //删除
		//var openId = 'ocFFwuHOpoUHVQQGNgcRsMFbYVGg';
		//var openId = $("#openid").val();
				 if(confirm("您确定要删除吗？")){
					$.ajax({
						type : "POST",
						url : 'deleteBbqList',
						data : {id:id,openId:openId},
						dataType : "json",
						success : function(msg) {
							if(msg.success){
								alert("删除成功！");
								 document.location.reload(); 
							}else{
								alert("删除失败！请联系管理员");
							}
						}
						
					});
				}
	}; 
	
	/* 返回首页 */
	function shouye() {
		WeixinJSBridge.call("closeWindow");
	}
</script>
</body>
</html>