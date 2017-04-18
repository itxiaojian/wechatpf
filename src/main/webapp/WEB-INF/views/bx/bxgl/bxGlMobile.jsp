<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>报修查看</title>
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

<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/jquery_002.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/iframeTools.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/jquery_003.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/artDialog.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/public.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>

<script type="text/javascript">
	jQuery(function() {
		var length = 20;
		//alert($(".bxbh").size());
		//alert($(".nrtd0").text().length);
/* 		for (var i = 0; i < $(".bxbh").size(); i++) {
			if ($(".nrtd" + i).text().length > 10) {
				$(".nrtd" + i).text(
			    $('.nrtd' + i).text().substring(0, 5) + "...")
			}
			if($(".bxbhtd"+i).text().length>18){
				$(".bxbhtd"+i).text($('.bxbhtd'+i).text().substring(0, 15) + "...");
			}
		} */
		//$('#nrtd').text().length > length ? $('#nrtd').text().substring(0, length) + "..." : $('#nrtd').text();
		
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
		window.location.href = "<%=path%>/bx/bxgl/list?bm=" + bm + "&bxzt="+bxzt+"&wxdl="+wxdl;
		});
	});
	
	function getWxdl(value){
		$.ajax({
			cache : true,
			type : "get",
			url : "<%=path%>/bx/bxgl/getBxzt",
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
                	  $("<option   value='"+item.sbztmc+"'>" + item.sbztmc + "</option>").appendTo($("#wxxl"));
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
</script>
</head>
<body>
<input type="hidden" id="bxztHidden" value="${bxzt}"/>
	<div>
		<div style="display: none;">
			<ul class="tab-menu tab" id="tabMenuID_">
				<li class="tab-selected" title="报修查看"><a
					href="<%=path%>/bx/bxgl/list" target="content"
					onfocus="this.blur()"><span>报修查看</span></a></li>
			</ul>
		</div>
		<div class="maindiv">
			<div class="tl pl10">
				<div class="fl">
					<label>维修大类:</label> <input type="text" value="" id="wxdlInput"
						style="display: none;"> <select name="F_XQDM" id="F_XQDM"
						onChange="getWxdl(this.value)" class="TextAreaDrop"
						style="width: 100px;">
						<option value="">请选择---</option>
						<c:forEach var="data" items="${wxlist}" varStatus="obj">
							<option  <c:if test="${wxdl==data.ztmc}">selected="selected"</c:if> value="${data.ztmc}">${data.ztmc}</option>
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
						<option  value="">请选择---</option>
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
								<th scope="col">序号</th>
								<th scope="col">报修编号</th>
								<th scope="col">联系号码</th>
								<th scope="col">申请人姓名</th>
								<th scope="col">楼宇</th>
								<th scope="col">维修大类</th>
								<th scope="col">申报主题名称</th>
								<th scope="col">内容</th>
								<th scope="col">服务</th>
								<th scope="col">预约时间</th>
								<th scope="col">状态</th>
								<th scope="col">查看</th>
							</tr>
							<tr>
								<c:forEach var="data" items="${list}" varStatus="status">
									<tr>
										<td style="width: 3%; text-align: center;">${status.count}</td>
										<td  title="${data.bxbh}" style="width: 9%;">
										<c:choose>  
											<c:when test="${fn:length(data.bxbh) > 15}">  
     											<c:out value="${fn:substring(data.bxbh,0,15)}..." />  
											</c:when>  
											<c:otherwise>  
     											<c:out value="${data.bxbh}" />  
											</c:otherwise>  
									   </c:choose>
									   </td>
								        <td style="width: 6%;">${data.lxhm}</td>
										<td style="width: 6%;">${data.sqrxm}</td>
										<td style="width: 9%;">${data.ly}</td>
										<td style="width: 6%;">${data.wxdl}</td>
										<td style="width: 8%;">${data.sbztmc}</td>
										<td title="${data.nr}"  style="width:11%;">
										<c:choose>  
											<c:when test="${fn:length(data.nr) > 15}">  
     											<c:out value="${fn:substring(data.nr,0,12)}..." />  
											</c:when>  
											<c:otherwise>  
     											<c:out value="${data.nr}" />  
											</c:otherwise>  
									   </c:choose>
									   </td>
										<td style="width: 5%;">${data.fw}</td>
										<td style="width: 8%;">${data.yysj}</td>
										<c:choose>
											<c:when test="${data.ztbh == 1}">
												<td style="width: 8%;"><font size="2px"
													style="color: #00FF1E">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 2}">
												<td style="width: 8%;"><font size="2px"
													style="color: #BB3D00">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 3}">
												<td style="width: 8%;"><font size="2px"
													style="color:red">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 4}">
												<td style="width: 8%;color:#00FF1E"><font size="2px">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 5}">
												<td style="width: 8%;"><font size="2px"
													style="color: #DC143C">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 6}">
												<td style="width: 8%;"><font size="2px"
													style="color: #FFAE00">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 7}">
												<td style="width: 8%;"><font size="2px"
													style="color:red">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 8}">
												<td style="width: 8%;"><font size="2px"
													style="opacity:0.8">${data.zt}</font></td>
											</c:when>
											<c:otherwise>
												<td style="width: 8%;"><font size="2px"
													style="color: #FF0000">${data.zt}</font></td>
											</c:otherwise>
										</c:choose>

										<%-- <td style="width: 8%;"><font size="2px">${data.pj}</font></td>
							<td style="width: 8%;"><font size="2px">${data.bz}</font></td> --%>
										<td style="width: 5%; cursor: pointer;"><a
											id="GridView1_ctl02_HyperLink3" style="color:blue;"
											href='<%=path%>/bx/bxgl/check?bxbh=${data.id}'>查看</a></td>
									</tr>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="footer" style="text-align: center;">
		<c:choose>
			<c:when test="${pages > 1}">
				<a href="<%=path%>/bx/bxgl/list?pages=${pages - 1}&bm=${bm}&bxzt=${bxzt}&wxdl=${wxdl}">上一页</a>
			</c:when>
			<c:otherwise>
						上一页
					</c:otherwise>
		</c:choose>
		第${pages}页
		<c:choose>
			<c:when test="${pages < count}">
				<a href="<%=path%>/bx/bxgl/list?pages=${pages + 1}&bm=${bm}&bxzt=${bxzt}&wxdl=${wxdl}">下一页</a>
			</c:when>
			<c:otherwise>
					下一页
				</c:otherwise>
		</c:choose>
		总共${count}页
	</div>
</body>
</html>