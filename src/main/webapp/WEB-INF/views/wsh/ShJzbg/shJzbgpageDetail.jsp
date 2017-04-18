<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1">
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<title>讲座报告详情</title>
<script src="<%=path%>/resources/js/wzy/jquery.js"></script>
<script src="<%=path%>/resources/js/wzy/json2.js"></script>
<link type="text/css" href="<%=path%>/resources/css/tucao.css"
	rel="stylesheet" />
<link type="text/css" href="<%=path%>/resources/css/page.css"
	rel="stylesheet" />
	<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/xyxw.css" />
	<link rel="stylesheet" href="<%=path%>/resources/js/wsh/styles.css" />
<style>
#replay {
	z-index: 1000;
	display: none;
}
</style>
 <style type="text/css">
	.anniu{top:1.2%;right:1%; }
    .anniu img{display:block;width:100%;height:30%;} 
    
  .zhanti_main{ width:100%;position:absolute; top:8%; left:0px; bottom:20px;overflow:auto;background-color:#fff;}
  .zhanti_title{ width:100%; height:7%;font-size:16px; line-height:35px; color:#333; }
  .zhanti_title img{ float:left;}
  .zhanti_mainBox p{font-size:12px;text-align:center;color:#333; float:left; width:90%; padding-left:5%;padding-right:5%;}
  .zhanti_mainBox img{ margin-top:40px;padding-bottom:40px;}
  .zhanti_pinglun img{ float:left; margin-left:30px;}
  .zhanti_pinglun ul{ margin-top:20px;}
  .zhanti_pinglun li{ width:100%; float:left; margin-top:1%;}
  .pL_name{ line-height:20px; font-size:16px; color:#2991e6;float: left;}
  .shijian{ float:right; color:#999;line-height:20px; font-size:10px; margin-right:20px;}
  .pL_main{ line-height:30px; font-size:12px; color:#333; clear:both; padding-left:18%; border-bottom:1px #ccc solid;padding-right:10%;padding-bottom:5px; }
  .WZY_foot{width:100%;  position:fixed; left:0px;bottom:0;overflow:hidden;}
 </style>
</head>
<body >
	<div id="test"></div>
	<div id="header" class="header">
		<img src="<%=path%>/resources/img/wzy/logo.png" style="width: 100%;height:50px;" />
		<div class="anniu">
			<a href="javascript:history.go(-1);">
			   <img 
			    src="<%=path%>/resources/img/wzy/FH.png"/>
			    </a>
			</div>
	</div>
	
	<div class="content" style="padding-bottom: 35px;">
	<input type="hidden" id="openid" value="${openId}">
		<c:forEach items="${list}" var="list" varStatus="obj">
			<div id="lidw${obj.count }" class="tclist">
				<div class="tcheader">
				</div>
				<div class="tccontent" style="padding-left: 0px;">
				   <div class="zhanti_title">
        	          <img src="<%=path%>/resources/img/wzy/zttlbt.png" style="width:10%;"/>讲座报告
                   </div>
				   <h1 style="text-align:center; font-size:16px; margin-top:2%; color:#333; float:left; width:100%; margin-bottom:2%;border-bottom:1px #ccc solid;">${list.jzbt }</h1>
                   <h2 style="text-align:center; font-size:10px; margin-top:2%; color:#2991e6; float:left; width:100%; padding-bottom:2%;">发布者：${list.fbr }&nbsp;&nbsp;发布时间：${list.jzsj }</h2>
                   <p>${list.jznr }</p>
				   <div class="tcbutton">
					</div>
					<c:if test="${list.zt==1 }">
					<div class="zhanti_title">
        	           <img src="<%=path%>/resources/img/wzy/wypl.png" style="width:10%;">我要评论
        	           <a href="javascript:void(0);" onclick="DisplayReply(${list.id})" >
        	             <img id="ADD" src="<%=path%>/resources/img/wzy/new_add.png" width="20" height="20" style=" float:right; margin-top:2%; margin-right:2%;">
        	           </a>
                    </div>
                    </c:if>
                    <c:if test="${list.zt==2 }">
					<div class="zhanti_title">
        	           <img src="<%=path%>/resources/img/wzy/wypl.png" style="width:10%;"><span style="color:red;">该专题已结束，暂不能参与讨论！</span>
                    </div>
                    </c:if>
					<div class="tcreply">
						<c:forEach items="${pllist}" var="pllist">
							<div id="t_tucao_morereply_">
								<div class="tcreplaylist">
									<span class="tcreplayname"> ${pllist.plr }:</span> 
									<span class="tcreplaymsg"> ${pllist.plnr} </span>
									<c:if test="${pllist.plrwxh==openId }">
									<a href="javascript:void(0);" onclick="DisplayDelete('${pllist.id }','${openId}')" style="float: right;">
							         <img src="<%=path%>/resources/images/iconfont-shanchu.png" style="width:20px;height:20px;" /> 删除 
							         <span id="t_tucao_delete_"></span>
						            </a>
						            </c:if>
									<br/>
									<span class="tctime">${pllist.plsj} </span>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="replay" class="tcsubmit">
		<div class="tcsubmittitle">
			<a href="#" class="tcsubmita"><img
				src="<%=path%>/resources/img/write.png" /> 我要评论</a>
		</div>
		<p>
			<textarea id="t_tucao_reply" maxlength="140" rows="4"></textarea>
		</p>
		<div class="tcsubmitbutton">
			<span class="tcsubmitbutton_num">140字</span> <a class="tcsubmitb"
				 onclick="DoTCReply();">评论</a> <a class="tcsubmitb" href="#"
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
	
	<div class="WZY_foot">
	  <img src="<%=path%>/resources/img/wzy/BQ.png" style="width:100%;">
	</div>


<script>

function DisplaySubmit() {   //发话题
	if ($(".tcsubmitbar").css("display") == "block") {
		$(".tcsubmitbar").css("display", "none");
	} else {
		$(".tcsubmitbar").css("display", "block");
	}
}

function DoTuCao() {   //发布
	if ($("#t_tucao_text").val() == "") {
		return;
	}
	var tcnr = $("#t_tucao_text").val();
	var openId = $("#openid").val();
	//var openId = 'ocFFwuHOpoUHVQQGNgcRsMFbYVGg';
	$(document).ready(
			function() {
				$.ajax({
					type : "POST",
					url : "<%=path%>/wzy/ZyWdtc/doTucao",
					data : {tcnr:tcnr,openId:openId},
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
	
	function DoTCZhan(id,thisid) {    //赞
		var openId = $("#openid").val();
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
								/* document.location.reload(); */
							}else{
								alert("您已经赞过！");
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
	
	function DoTCReply(id) {   //回复
		if ($("#t_tucao_reply").val() == "") {
			return;
		}
		var plnr = $("#t_tucao_reply").val();
		//var openId = 'ocFFwuHOpoUHVQQGNgcRsMFbYVGg';
		var openId = $("#openid").val();
// 		openId
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "doPlreply",
						data : {plnr:plnr, id:RTid,openId:openId},
						//contentType : "application/json; charset=utf-8",
						dataType : "json",
						success : function(msg) {
							 document.location.reload();
						}
					});
				});
	}
	
	//删除  DisplayDelete
	function DisplayDelete(id,openId) {    //删除
		//var openId = 'ocFFwuHOpoUHVQQGNgcRsMFbYVGg';
		var openId = $("#openid").val();
				 if(confirm("您确定要删除吗？")){
					$.ajax({
						type : "POST",
						url : 'deletePj',
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
</script>
</body>
</html>