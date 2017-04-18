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
	<script src="<%=path%>/resources/js/wzy/json2.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<title>我要吐槽</title>
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
</head>
<body style="background:#efefef;" onload="ScrollDiv();">
	<div id="test"></div>
	<div id="header" class="header">
		<img src="<%=path%>/resources/img/wzy/logo.png" style="width: 100%;" />
		<div class="anniu" style="left: 85%;top:2%;">
			<a href="<%=path%>/wsh/zy/zhuye?openId=${openId}" style="float:right;width:40px;height:50px;" >
			   <img  style="width:90%"
			    src="<%=path%>/resources/img/wzy/FH.png" />
			    </a>
			</div>
	</div>
	<div class="tctotal">
		<div class="tctotal_left">
		<c:forEach items="${list2}" var="data">
			<span id="tc_ListCount" class="tctotalnum">${data.size }</span> 话题
		</c:forEach>
		</div>
		<div class="tctotal_right">
		<c:forEach items="${list3}" var="data">
			<span id="tc_AccessCount" class="tctotalnum">${data.size }</span> 赞
		</c:forEach>
		</div>
		<input type="hidden" id="openid" value="${openId}">
		<input type="hidden" name="size" id="size" value="${size }">
	</div>

	<div class="content" style="padding-bottom:0.5rem;">
		<c:forEach items="${list}" var="list" varStatus="obj">
			<div id="lidw${obj.count }" class="tclist">
				<div class="tcheader">
					<img src="${list.txdz}" class="tcheadimg" />
				</div>
				<div class="tccontent">
					<div class="tctitle">${list.tcrxm}</div>
					<div class="tctime">${list.tcsj}</div>
					<div class="tctext">${list.tcnr}</div>
					<div class="tcbutton">
						<a href="javascript:void(0);" onclick="DoTCZhan(${list.id})">
							<img src="<%=path%>/resources/img/zan.png" style="width:20px;height:20px;"/> 赞 (<span
							id="t_tucao_zhan_${list.id}">${list.bzcs}</span>)
						</a> <a href="javascript:void(0);" onclick="DisplayReply(${list.id})">
							<img src="<%=path%>/resources/img/huifu.png" style="width:20px;height:20px;" /> 回复 (<span
							id="t_tucao_reply_">${list.hfcs}</span>)
						</a>
							<c:if test="${list.tcr==openId }">
						<a href="javascript:void(0);" onclick="DisplayDelete('${list.id }','${openId}')">
							<img src="<%=path%>/resources/images/iconfont-shanchu.png" style="width:20px;height:20px;" /> 删除 <span
							id="t_tucao_delete_"></span>
						</a>
						</c:if>
					</div>
					<div class="tcreply">
						<c:forEach items="${list.listHF}" var="listHF">
							<div id="t_tucao_morereply_">
								<div class="tcreplaylist">
									<span class="tcreplayname"> ${listHF.hfrxm }:</span> 
									<span class="tcreplaymsg"> ${listHF.hfnr} </span>
									<br/>
									<span class="tctime">${listHF.hfsj} </span>
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
			<button id="-ft" class="btn btn-default btn-block btn-lg ng-binding jzgd"
			onclick="loadMore('${pages}','${openId }');">加载更多</button>
		   </div>
   	</c:if>
	<!-- <div style="height: 200px;"></div> -->
	<div class="tcsubmit">
		<div class="tcsubmittitle">
			<a href="#" class="tcsubmita" onclick="DisplaySubmit();"><img
				src="<%=path%>/resources/img/write.png" /> 发话题</a>
		</div>
		<div class="tcsubmitbar">
			<p>
				<textarea id="t_tucao_text" maxlength="140" rows="4"></textarea>
			</p>
			<div class="tcsubmitbutton">
				<span class="tcsubmitbutton_num">140字</span> <a class="tcsubmitb"
					href="#" onclick="DoTuCao();">发表</a> <a class="tcsubmitb" href="#"
					onclick="DisplaySubmit();">取消</a>
			</div>
		</div>
	</div>
	<div id="replay" class="tcsubmit">
		<div class="tcsubmittitle">
			<a href="#" class="tcsubmita"><img
				src="<%=path%>/resources/img/write.png" /> 我要回复</a>
		</div>
		<p>
			<textarea id="t_tucao_reply" maxlength="140" rows="4"></textarea>
		</p>
		<div class="tcsubmitbutton">
			<span class="tcsubmitbutton_num">140字</span> <a class="tcsubmitb"
				href="#" onclick="DoTCReply();">回复</a> <a class="tcsubmitb" href="#"
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
var i=0;
function loadMore(page,openId){
	i=page;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wzy/ZyWdtc/zyWdtcLoadmore?";
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
				if(value.yhid=='1'||value.dlm=='admin'||value.tcr==value.wxh){
				 	 html1 = "<a href='javascript:void(0);' onclick='DisplayDelete("+value.id+","+value.wxh+")'>"
						   +"<img src='<%=path%>/resources/images/iconfont-shanchu.png' style='width:20px;height:20px;' />"
						   +" 删除 <span 't_tucao_delete_'></span>"
					       +"</a>";
				 }else{
					 html1 =""
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
				html += "<div class='tclist'>"
					 +"<div class='tcheader'>"
					 +"<img src='"+value.txdz+"' class='tcheadimg' />"
					 +"</div>"
					 +"<div class='tccontent'>"
					 +"<div class='tctitle'>"+value.tcrxm+"</div>"
					 +"<div class='tctime'>"+value.tcsj+"</div>"
					 +"<div class='tctext'>"+value.tcnr+"</div>"
					 +"<div class='tcbutton'>"
					 +"<a href='javascript:void(0);' onclick='DoTCZhan("+value.id+")'>"
					 +"<img src='<%=path%>/resources/img/zan.png' style='width:20px;height:20px;'/>"
					 +"赞 (<span id='t_tucao_zhan_"+value.id+"'>"+value.bzcs+"</span>)"
					 +"</a>"
					 +"<a href='javascript:void(0);' onclick='DisplayReply("+value.id+")'>"
					 +"<img src='<%=path%>/resources/img/huifu.png' style='width:20px;height:20px;'/>"
					 +"回复(<span id='t_tucao_reply_"+value.id+"'>"+value.bzcs+"</span>)"
					 +"</a>"
					 +html1
					 +"</div>"
					 +"<div class='tcreply' style='padding-top: 0em;'>"
				     +html3
				     +"</div>"
					 +"</div>";
					 html3="";
			})
    $('.LsMore').before(html);
	$('.jzgd').removeAttr("onclick");
	$('.jzgd').attr("onclick","loadMore('"+i+"','"+openId+"')");
	}else{
		$('.jzgd').text("已经是最后一页了");
	}
	},
   error : function() {
	alert("error");
}
});
} 

//点击“加载更多之后”定位到最后一条数据
function ScrollDiv() { 
var size=$("#size").val();
//alert(size);
if(size!=null&&size!=''){

 if(size<=10){
	//	document.getElementById('lidw1').scrollIntoView(true);
}else if(size%10==0){
	var num = (parseInt(size/10-1)*10+1);
	//alert(num);
	var id='lidw'+num;
	document.getElementById(id).scrollIntoView(true); 
}else{
	var num = (parseInt(size/10)*10+1);
	var id='lidw'+num;
	document.getElementById(id).scrollIntoView(true); 
}
}
}


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
		var hfnr = $("#t_tucao_reply").val();
		//var openId = 'ocFFwuHOpoUHVQQGNgcRsMFbYVGg';
		var openId = $("#openid").val();
		openId
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "<%=path%>/wzy/ZyWdtc/doTcreply",
						data : {hfnr:hfnr, id:RTid,openId:openId},
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
						url : 'deleteWdtcList',
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