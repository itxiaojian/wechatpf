<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>报修受理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/aero.css" rel="stylesheet" />
<link href="<%=path%>/resources/css/bxsh.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript">var path="<%=path%>";</script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/jquery_002.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/jquery_003.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/iframeTools.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/artDialog.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/public.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/lodop/LodopFuncs.js"
	type="text/javascript"></script>
<script type="text/javascript">
 jQuery(function() {
	 var length =20;
		//alert($(".bxbh").size());
		//alert($(".nrtd0").text().length);
/* 		for(var i =0;i<$(".bxbh").size();i++){
			if($(".nrtd"+i).text().length>10){
				$(".nrtd"+i).text($('.nrtd'+i).text().substring(0, 5) + "...");
			}
			if($(".pjtd"+i).text().length>5){
				$(".pjtd"+i).text($('.pjtd'+i).text().substring(0, 5) + "...");
			}
			if($(".bxbhtd"+i).text().length>18){
				$(".bxbhtd"+i).text($('.bxbhtd'+i).text().substring(0, 15) + "...");
			}
		}	  */
	/*  $("#F_XQDM").change(function(){
		 //alert(1);
		 alert($("#F_LYDM").val());
		});
	 var a = $('#F_LYDM option:selected').text(); */
	 
	 $("#Button1").click(function() {
		 var bm;
		 bm= $('#SearchStr').val();
		 if(bm=="单据号/用户名/联系电话")
		 {bm="";}
		 var bxzt= $('#bxztInput').val();
		 var wxdl = $('#wxdlInput').val();
		 if($("#F_XQDM").find("option:selected").val()!='' && $("#F_XQDM").find("option:selected").val()!='-1' ){
			 wxdl=$("#F_XQDM").find("option:selected").val();
		 }
		 if($("#wxxl").find("option:selected").val()!='' && $("#wxxl").find("option:selected").val()!='-1' ){
			 bxzt=$("#wxxl").find("option:selected").val();
		 }
	window.location.href = "<%=path%>/bx/bxsl/sllist?bm=" + bm + "&bxzt="+bxzt+"&wxdl="+wxdl;
	});
	
	//审核选中
	$("#Button3").click(function() {
		var dmlist = getidlist();
		var dmlistZt = getidlistZt();
		if (dmlist == "") {
			return;
		}
	   var a = [];
	   for (var i = 0;i<dmlistZt.split(',').length-1; i++) {
		   a.push(dmlistZt.split(',')[i]);
	   }
	    if(contains(a, "4")){
	    	alert("选择的报修有回访信息,请重新选择!");
	    }else{
	    	dealCheckBox(dmlist);
	    }
	});
	
/* //驳回选中
	$("#Button4").click(function() {
		var dmlist = getidlist();
		if (dmlist == "") {
			return;
		}
		rejecCheckBox(dmlist);
	});  */
	
	//审核选中
	$("#Button4").click(function() {
		var dmlist = getidlist();
		var dmlistZt = getidlistZt();
		if (dmlist == "") {
			return;
		}
	   var a = [];
	   for (var i = 0;i<dmlistZt.split(',').length-1; i++) {
		   a.push(dmlistZt.split(',')[i]);
	   }
	    if(contains(a, "4") && !contains(a, "2") && !contains(a, "7")){
	    	succCheckBox(dmlist);
	    }else{
	   		alert("选择的报修有未打印,请重新选择!");
	    }
	});
 });
 
 function contains(a, obj) {
     for (var i = 0; i < a.length; i++) {
         if (a[i] === obj) {
             return true;
         }
     }
     return false;
 }
 
//获取选中项ID列表
	function getidlist() {
		var v = "";
		$("#GridView1 tr:gt(0)").each(
				function() {
					check = $(this).find("td:nth-child(1) input:checkbox")
							.attr("checked");
					id = $(this).find("td:nth-child(4)").text();
					if (check) {
						v += $.trim(id) + ",";
					}
				});
		return $.trim(v);
	}
	
	//获取选中项ID列表
	function getidlistZt() {
		var v = "";
		$("#GridView1 tr:gt(0)").each(
				function() {
					check = $(this).find("td:nth-child(1) input:checkbox")
							.attr("checked");
					id = $(this).find("td:nth-child(5)").text();
					if (check) {
						v += $.trim(id) + ",";
					}
				});
		return $.trim(v);
	}
	
	//全选
	function checkall(checked) {
		$("#GridView1 input:checkbox").each(function() {
			$(this).attr("checked", checked ? true : false);
		});
	}
	
	//审核
	function review(dm) {
		window.location.href = "<%=path%>/bx/bxsh/makesh?dm="+dm;
	}
	

	function see(dm){
		art.dialog.open('<%=path%>/bx/bxsl/see?id='+dm,{
			//id:'detailDialog',
			title:'报修受理查看信息',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 170,
	        width:$(document.body).width() - 300,
	        top:100,
	        ok:false,
	        top:20,
	        cancel:true
	    }); 
	} 
	
	function see1(dm){
		art.dialog.open('<%=path%>/bx/bxcl/see?id='+dm,{
			//id:'detailDialog',
			title:'报修处理查看信息',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 160,
	        width:$(document.body).width() - 300,
	        top:100,
	        ok:false,
	        top:20,
	        cancel:true
	    }); 
	} 
	
	function deal(dm){
		if(confirm("您确定打印吗？")){
			   CreateOneFormPage(dm);
			   if (LODOP.CVERSION) CLODOP.On_Return=function(TaskID,Value){document.getElementById('S1').value=Value;};
				document.getElementById('S1').value=LODOP.PREVIEW();
		 		if(document.getElementById('S1').value==1){
					$.ajax({
						url:'<%=path%>/bx/bxsl/deal',
						type : 'get',
						//dataType : 'json',
						data:{
							id:dm
						},
						success: function(){
							window.location.href="<%=path%>/bx/bxsl/sllist";
						},
						error: function(XMLHttpRequest){
								alert("网络错误");
							}
						}); 
				} 
			 }
	}
	
	function succCheckBox(dm){
		if(confirm("您确认批量对报修结束进行回访吗？")){
			$.ajax({
				url:'<%=path%>/bx/bxcl/succCon',
				type : 'get',
				//dataType : 'json',
				data:{
					idList:dm
				},
				success: function(){
				window.location.href="<%=path%>/bx/bxcl/cllist";
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
		}
	}
	
	function dealCheckBox(dm){
		if(confirm("您确定批量打印吗？")){
			for(var i =0;i<(dm.split(',').length)-1;i++){
		    	 CreateOneFormPage(dm.split(',')[i]);
				 if (LODOP.CVERSION) CLODOP.On_Return=function(TaskID,Value){document.getElementById('S1').value=Value;};
				 document.getElementById('S1').value=LODOP.PREVIEW();
			     if(document.getElementById('S1').value>=1){ 
			    	// $("#S1").val("");
			    	  $.ajax({
							url:'<%=path%>/bx/bxsl/dealCheckBox',
							type : 'get',
							//dataType : 'json',
							data:{
								idList:dm.split(',')[i]
							},
							success: function(){
							window.location.href="<%=path%>/bx/bxsl/sllist";
							},
							error: function(XMLHttpRequest){
									alert("网络错误");
							}
						}); 
			     } 
			
			}
            <%--$.ajax({
				url:'<%=path%>/bx/bxsl/dealCheckBox',
				type : 'get',
				//dataType : 'json',
				data:{
					idList:dm
				},
				success: function(){
				window.location.href="<%=path%>/bx/bxsl/sllist";
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			}); --%>
			 }
	}
	
	//回访
		function hf(dm){
		if(confirm("您确认对报修结束进行回访吗？")){
			$.ajax({
				url:'<%=path%>/bx/bxcl/succCon',
				type : 'get',
				//dataType : 'json',
				data:{
					id:dm
				},
				success: function(){
				window.location.href="<%=path%>/bx/bxsl/sllist";
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
		}
	}
	//用戶未評價,結束
		function js(dm){
			if(confirm("您确定结束 吗？")){
				$.ajax({
					url:'<%=path%>/bx/bxsl/js',
					type : 'get',
					//dataType : 'json',
					data:{
						id:dm,
					},
					success: function(){
					window.location.href="<%=path%>/bx/bxsl/sllist";
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
					}
				});
				 }
		}
	
	function rejec(dm){
		art.dialog.open('<%=path%>/bx/bxsl/rejec?id='+dm,{
			//id:'detailDialog',
			//title:'申请查看',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 200,
	        width:$(document.body).width() - 600,
	        top:100,
	        top:20,
	        ok:function(){
	        	var str = this.iframe.contentWindow.$('#areadropboxvalue').val();
	        	$.ajax({
					url:"<%=path%>/bx/bxsl/rejecCon",
					type : 'get',
					//dataType : 'json',
					data:{
						id:dm,
						str:str,
					},
					success: function(){
					window.location.href="<%=path%>/bx/bxsl/sllist";
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
					}
				});
	        },
	        cancel:true
	    }); 
	}

	function rejecCheckBox(dm){
		art.dialog.open('<%=path%>/bx/bxsl/rejec?id='+dm,{
			//id:'detailDialog',
			//title:'申请查看',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 200,
	        width:$(document.body).width() - 600,
	        top:100,
	        top:20,
	        ok:function(){
	        	var str = this.iframe.contentWindow.$('#areadropboxvalue').val();
	        	$.ajax({
					url:"<%=path%>/bx/bxsl/rejecCheckBox",
					type : 'get',
					//dataType : 'json',
					data:{
						idList:dm,
						str:str,
					},
					success: function(){
					window.location.href="<%=path%>/bx/bxsl/sllist";
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
					}
				});
	        },
	        cancel:true
	    }); 
	}
	function getWxdl(value){
		$.ajax({
			cache : true,
			type : "get",
			url : "<%=path%>/bx/bxsl/getBxzt",
			data : {id:value},
			async : false,
			error : function(request) {
				alert("连接数据库失败，请联系管理员。");
			},
			success : function(data) {
				$("#wxxl").empty();
                $("<option value='-1'>请选择---</option>").appendTo($("#wxxl"));  
                $.each(eval(data), function(i, item) {
                   /*  $("<option value='" + item.yhbh+ "'>" + item.xm + "</option>").appendTo($("#wxxl")); */ 
                	  $("<option value='"+item.sbztmc+"'>" + item.sbztmc + "</option>").appendTo($("#wxxl"));
                	  $('#wxdlInput').val();
              		  $('#wxdlInput').val(value);
                });
			}
		}); 
	}
	
	function getBxzt(value){
		$('#bxztInput').val();
		$('#bxztInput').val(value);
	}
	
	function CreateOneFormPage(dm){	
		LODOP=getLodop();                 
		LODOP.PRINT_INIT("派单表"+dm);
		LODOP.ADD_PRINT_URL(30,20,746,"95%",'openPdPage?bxId='+dm);
		//LODOP.SET_PRINT_MODE("PROGRAM_CONTENT_BYVAR",true);//生成程序时，内容参数有变量用变量，无变量用具体值
		LODOP.SET_PRINT_STYLEA(0,"HOrient",3);
		LODOP.SET_PRINT_STYLEA(0,"VOrient",3);
		//LODOP.PREVIEW();
		//LODOP.SET_SHOW_MODE("PREVIEW_NO_MINIMIZE",true);
	    LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",true);
	};
</script>
</head>
<body id="mainbody" style="overflow-y: scroll;" class="objbody1">
<input type="hidden" id="bxztHidden" value="${bxzt}"/>
<input value="" id="S1" type="hidden">
	<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="报修受理"><a
				href="<%=path%>/bx/bxsl/sllist" target="content"
				onfocus="this.blur()"><span>报修受理</span></a></li>
		</ul>
	</div>
	<div class="maindiv">
		<div class="tl pl10">
			<div class="fl">
				<label>维修大类:</label> <input type="text" value="" id="wxdlInput"
					style="display: none;"> <select name="F_XQDM" id="F_XQDM"
					onChange="getWxdl(this.value)" class="TextAreaDrop"
					style="width: 100px;">
					<option value="-1">请选择---</option>
					<c:forEach var="data" items="${wxlist}" varStatus="obj">
						<option <c:if test="${wxdl==data.ztmc}">selected="selected"</c:if> value="${data.ztmc}">${data.ztmc}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="tl pl10">
			<div class="fl" style="margin-left: 8px;">
				<label>报修主题:</label> <input type="text" value="" id="bxztInput"
					style="display: none;"> <select name="F_XQDM" id="wxxl"
					onChange="getBxzt(this.value)" class="TextAreaDrop"
					style="width: 100px;">
					<option value="-1">请选择---</option>
					<c:if test="${bxzt !=''}">
						<option selected="selected" value="${bxzt}">${bxzt}</option>
					</c:if>
				</select>
			</div>
		</div>
		<div class="tl pl10">
			<div class="fl pl10">
				<c:if test="${bm!=''}">
						<input name="SearchStr" value="${bm}" id="SearchStr"
							onfocus="this.value=''"
							onblur="if(this.value==''){this.value='单据号/用户名/联系电话'}"
							class="InputNormal" style="width: 180px;" type="text">
					</c:if>
					<c:if test="${bm ==''}">
						<input name="SearchStr" value="单据号/用户名/联系电话" id="SearchStr"
							onfocus="this.value=''"
							onblur="if(this.value==''){this.value='单据号/用户名/联系电话'}"
							class="InputNormal" style="width: 180px;" type="text">
					</c:if>
			</div>
			<div class="fl pl10 aui_button" style="padding: 0; margin: 0;">
				<input name="Button1" value="检 索" id="Button1"
					class="button aui_state_highlight" type="button">
			</div>
			<div class="clear"></div>
		</div>
		<div class="acbodydiv">
			<div>
				<table class="gridview_table" id="GridView1" style="width: 100%;"
					border="0" cellspacing="1">
					<tbody>
						<tr>
							<th scope="col"><input id="GridView1_ctl01_allcheck"
								name="GridView1$ctl01$allcheck"
								onclick='javascript:checkall($(this).attr("checked"));'
								type="checkbox"></th>
							<th scope="col">序号</th>
							<th scope="col">申请单号</th>
							<th scope="col" style="display: none;">Id</th>
							<th scope="col" style="display: none;">状态编号</th>
							<th scope="col">申请人</th>
							<th scope="col">联系电话</th>
							<th scope="col">楼宇名称</th>
							<th scope="col">维修大类</th>
							<th scope="col">报修主题</th>
							<th scope="col">项目</th>
							<th scope="col">服务</th>
							<th scope="col">评论</th>
							<th scope="col">预约时间</th>
							<th scope="col">状态</th>
							<th scope="col">管理操作</th>
						</tr>
						<c:if test="${!empty list }">
							<c:forEach var="data" items="${list}" varStatus="status">
								<c:choose>
									<c:when test="${data.ztbh =='5' && fn:substring(data.spsj,0,10) == time}">
										<tr>
											<td style="width: 2%;">
											<input id="GridView1_ctl02_CheckBox1"
												 name="GridView1$ctl02$CheckBox1"
												type="checkbox"></td>
											<td style="width: 4%; text-align: center;">${status.count }</td>
											<%-- <td style="width: 10%;" class="bxbh bxbhtd${status.index}"
												title="${data.bxbh}">${data.bxbh}</td> --%>
											<td style="width: 10%;" class="bxbh bxbhtd${status.index}" title="${data.bxbh}">
												<c:choose>
												<c:when test="${fn:length(data.bxbh) > 15}">
													<c:out value="${fn:substring(data.bxbh,0,15)}..." />
												</c:when>
												<c:otherwise>
													<c:out value="${data.bxbh}" />
												</c:otherwise>
											</c:choose>
											</td>
											<td style="width: 10%; display: none;">${data.id}</td>
											<td style="width: 10%; display: none;"
												class="ztbh${status.index}">${data.ztbh}</td>
											<td style="width: 7%;">${data.sqrxm}</td>
											<td style="width: 6%;">${data.lxhm}</td>
											<td style="width: 12%;">${data.ly }</td>
											<td style="width: 9%;">${data.wxdl }</td>
											<td style="width: 9%;">${data.sbztmc}</td>
											<%-- <td style="width: 10%;" class="nrtd${status.index}"
												title="${data.nr}">${data.nr}</td> --%>
										    <td style="width: 11%;" class="nrtd${status.index}"
											title="${data.nr}">
											<c:choose>
												<c:when test="${fn:length(data.nr) > 9}">
													<c:out value="${fn:substring(data.nr,0,9)}..." />
												</c:when>
												<c:otherwise>
													<c:out value="${data.nr}" />
												</c:otherwise>
											</c:choose>
                                            </td>
											<td style="width: 4%;">${data.fw }</td>
											<c:choose>
												<c:when test="${!empty data.pj}">
													<%-- <td style="width: 10%;" class="pjtd${status.index }"
														title="${data.pj}">${data.pj}</td> --%>
												<td style="width: 10%;"  class="pjtd${status.index }"
													title="${data.pj}">
													<c:choose>
														<c:when test="${fn:length(data.pj) > 5}">
															<c:out value="${fn:substring(data.pj,0,5)}..." />
														</c:when>
														<c:otherwise>
															<c:out value="${data.pj}" />
														</c:otherwise>
													</c:choose>
                                        		 </td>
												</c:when>
												<c:otherwise>
													<td style="width: 7%;color:red">长时间未反馈</td>
												</c:otherwise>
											</c:choose>
											<td style="width: 8%;">${data.yysj }</td>
											<td style="width: 6%;"><font color="#DC143C">${data.zt }</font></td>
											<td style="width: 11%; text-align: center;"><a
												id="GridView1_ctl02_HyperLink4" style="color: blue;"
												onclick='javascript:see($(this).attr("alt"))'
												alt="${data.id}" href="#">查看&nbsp;</a><a
												id="GridView1_ctl02_HyperLink4" style="color: #FF00FF;"
												class="dy" onclick='javascript:js($(this).attr("alt"))'
												alt="${data.id}" href="#">结束</a></td> 
									</c:when>
									<c:when test="${data.ztbh !='5'}">
									<tr>
										<td style="width: 2%;">
										<input id="GridView1_ctl02_CheckBox1"
											 name="GridView1$ctl02$CheckBox1"
											type="checkbox"></td>
										<td style="width: 4%; text-align: center;">${status.count }</td>
										<%-- <td style="width: 10%;" class="bxbh bxbhtd${status.index}"
												title="${data.bxbh}">${data.bxbh}</td> --%>
										<td style="width: 10%;" class="bxbh bxbhtd${status.index}" title="${data.bxbh}">
												<c:choose>
												<c:when test="${fn:length(data.bxbh) > 15}">
													<c:out value="${fn:substring(data.bxbh,0,15)}..." />
												</c:when>
												<c:otherwise>
													<c:out value="${data.bxbh}" />
												</c:otherwise>
											</c:choose>
										</td>
										<td style="width: 10%; display: none;">${data.id}</td>
										<td style="width: 10%; display: none;"
											class="ztbh${status.index}">${data.ztbh}</td>
										<td style="width: 7%;">${data.sqrxm}</td>
										<td style="width: 6%;">${data.lxhm}</td>
										<td style="width: 12%;">${data.ly }</td>
										<td style="width: 9%;">${data.wxdl }</td>
										<td style="width: 9%;">${data.sbztmc}</td>
										
										<td style="width: 10%;" class="nrtd${status.index}"
											title="${data.nr}">
											<c:choose>
												<c:when test="${fn:length(data.nr) > 9}">
													<c:out value="${fn:substring(data.nr,0,9)}..." />
												</c:when>
												<c:otherwise>
													<c:out value="${data.nr}" />
												</c:otherwise>
											</c:choose>
                                         </td>
											<td style="width: 4%;">${data.fw }</td>
										<c:choose>
											<c:when test="${!empty data.pj}">
												<%-- <td style="width: 7%;" class="pjtd${status.index }"
													title="${data.pj}">${data.pj}</td> --%>
												<td style="width: 10%;"  class="pjtd${status.index }"
													title="${data.pj}">
													<c:choose>
														<c:when test="${fn:length(data.pj) > 5}">
															<c:out value="${fn:substring(data.pj,0,5)}..." />
														</c:when>
														<c:otherwise>
															<c:out value="${data.pj}" />
														</c:otherwise>
													</c:choose>
                                        		 </td>
											</c:when>
											<c:otherwise>
												<td style="width: 7%;">否</td>
											</c:otherwise>
										</c:choose>
										<td style="width: 8%;">${data.yysj }</td>
										<c:if test="${data.ztbh=='2'}">
											<td style="width: 6%;"><font color="#BB3D00">${data.zt }</font></td>
										</c:if>
										<c:if test="${data.ztbh=='4'}">
											<td style="width: 6%;"><font color="#00FF1E">${data.zt }</font></td>
										</c:if>
										<c:if test="${data.ztbh=='7'}">
											<td style="width: 7%;"><font color="red">${data.zt }</font></td>
										</c:if>
										<td style="width: 11%; text-align: center;"><c:if
												test="${data.ztbh=='4'}">
												<a id="GridView1_ctl02_HyperLink4" style="color: blue;"
													onclick='javascript:see1($(this).attr("alt"))'
													alt="${data.id}" href="#">查看</a>
											</c:if> <c:if test="${data.ztbh=='2' || data.ztbh=='7'}">
												<a id="GridView1_ctl02_HyperLink4" style="color: blue;"
													onclick='javascript:see($(this).attr("alt"))'
													alt="${data.id}" href="#">查看</a>
											</c:if> <c:if test="${data.ztbh=='2' || data.ztbh=='7'}">
												<a id="GridView1_ctl02_HyperLink4"
													style="display: inline; color: #ff9a00;" class="dy"
													onclick='javascript:deal($(this).attr("alt"))'
													alt="${data.id}" href="#">打印</a>
												<a id="GridView1_ctl02_HyperLink4"
													style="display: none; color: #ADAD5A;" class="hf"
													onclick='javascript:hf($(this).attr("alt"))'
													alt="${data.id}" href="#">回访</a>
											</c:if> <c:if test="${data.ztbh=='4'}">
												<a id="GridView1_ctl02_HyperLink4"
													style="display: none; color: #ff9a00;" class="dy"
													onclick='javascript:deal($(this).attr("alt"))'
													alt="${data.id}" href="#">打印</a>
												<a id="GridView1_ctl02_HyperLink4"
													style="display: inline; color: #ADAD5A;" class="hf"
													onclick='javascript:hf($(this).attr("alt"))'
													alt="${data.id}" href="#">回访</a>
											</c:if> 
									</tr>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<c:if test="${empty list }">无记录！</c:if>
			<div class="aui_button" style="margin-top:0px;">
				<!-- <input id="Button3" class="button aui_state_highlight" value="打印"
					type="button">&nbsp;  --><input id="Button4"
					class="button aui_state_highlight" value="回访" type="button">&nbsp;
			</div>

		</div>
	</div>
	<div id="footer" style="text-align: center;">
		<c:choose>
			<c:when test="${pages > 1}">
				<a href="<%=path%>/bx/bxsl/sllist?pages=${pages - 1}&bm=${bm}&bxzt=${bxzt}&wxdl=${wxdl}">上一页</a>
			</c:when>
			<c:otherwise>
						上一页
					</c:otherwise>
		</c:choose>
		第${pages}页
		<c:choose>
			<c:when test="${pages < count}">
				<a href="<%=path%>/bx/bxsl/sllist?pages=${pages + 1}&bm=${bm}&bxzt=${bxzt}&wxdl=${wxdl}">下一页</a>
			</c:when>
			<c:otherwise>
					下一页
				</c:otherwise>
		</c:choose>
		总共${count}页
	</div>
</body>
</html>