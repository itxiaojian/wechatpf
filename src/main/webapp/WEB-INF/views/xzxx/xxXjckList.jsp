<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>信件查看</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/aero.css" rel="stylesheet" />
<link href="<%=path%>/resources/css/xjck.css" rel="stylesheet" />
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>

<script type="text/javascript">
jQuery(function() {
	
	$("#Button1").click(function() {
		 var bm;
		 bm= $('#SearchStr').val();
		 var yhbh=$('#yhbh').val();
		 if(bm=="信件标题/发件人姓名/信件内容")
		 {bm="";}
		 var clzt= $('#clztInput').val();
		 var bmmc = $('#bmmcInput').val();
		 if($("#pid").find("option:selected").val()!='' && $("#pid").find("option:selected").val()!='-1' ){
			 bmmc=$("#pid").find("option:selected").val();
		 }
		 if($("#aid").find("option:selected").val()!='' && $("#aid").find("option:selected").val()!='-1' ){
			 clzt=$("#aid").find("option:selected").val();
		 }
		window.location.href = "<%=path%>/xzxx/XxXjxxb/getXjckList?bm=" + bm+"&yhbh="+yhbh+"&bmmc="+bmmc+"&clzt="+clzt;
	});
});

function Change(value){
    var objS = document.getElementById("pid");
	$('#bmmcInput').val(value);
   <%--  var bmmc = objS.options[objS.selectedIndex].value;
    var yhbh=$('#yhbh').val();
    if(bmmc == "请选择---"){
    	 location.href="<%=path%>/xzxx/XxXjxxb/getXjckList?yhbh="+yhbh;
    }else{
         location.href="<%=path%>/xzxx/XxXjxxb/getXjckList?bmmc=" + bmmc+"&yhbh="+yhbh;
    } --%>
	}
	
function Changetwo(value){
    var objS = document.getElementById("aid");
	$('#clztInput').val(value);
   // var clzt = objS.options[objS.selectedIndex].value;
   // var yhbh=$('#yhbh').val();
   <%--  if(clzt == "请选择---"){
    	 location.href="<%=path%>/xzxx/XxXjxxb/getXjckList?yhbh="+yhbh;
    }else{
         location.href="<%=path%>/xzxx/XxXjxxb/getXjckList?clzt=" + clzt+"&yhbh="+yhbh;
		} --%>
	}
</script>

</head>
<body style="overflow-x: auto;">
	<input name="yhbh" value="${yhbh}" id="yhbh" type="hidden"/>
	<input  value="${clzt}" id="clztInput" type="hidden" />
	<input  value="${bmmc}" id="bmmcInput" type="hidden" />
	<div>
		<div style="display: none;">
			<ul class="tab-menu tab" id="tabMenuID_">
				<li class="tab-selected" title="信件查看"><a
					href="<%=path%>/xzxx/XxXjxxb/getXjckList" target="content"
					onfocus="this.blur()"><span>信件查看</span></a></li>
			</ul>
		</div>
		<div class="maindiv">
			<div class="tl pl10">
				<div class="fl">
					<label>受理部门:</label> <select id="pid" onchange="Change(this.value)"
						class="TextAreaDrop" style="width: 100px;">
						<option value="">请选择---</option>
						<c:forEach var="data" items="${bmlist}" varStatus="obj">
							<option <c:if test="${bmmc==data.bmmc}">selected="selected"</c:if> value="${data.bmmc}">${data.bmmc}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="tl pl10">
				<div class="fl">
					<label>处理状态:</label> <select id="aid" onchange="Changetwo(this.value)"
						class="TextAreaDrop" style="width: 100px;">
						<option value="">请选择---</option>
						<c:forEach var="data" items="${ztlist}" varStatus="obj">
							<option <c:if test="${clzt==data.zdz}">selected="selected"</c:if> value="${data.zdz}">${data.zdmc}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="tl pl10">
				<div class="fl pl10">
					<!-- <input name="SearchStr" value="信件标题/发件人姓名/信件内容" id="SearchStr"
						onfocus="this.value=''"
						onblur="if(this.value==''){this.value='信件标题/发件人姓名/信件内容'}"
						class="InputNormal" style="width: 180px;" type="text"> -->
					<c:if test="${bm!=''}">
						<input name="SearchStr" value="${bm}" id="SearchStr"
							onfocus="this.value=''"
							onblur="if(this.value==''){this.value='信件标题/发件人姓名/信件内容'}"
							class="InputNormal" style="width: 180px;" type="text">
					</c:if>
					<c:if test="${bm ==''}">
						<input name="SearchStr" value="信件标题/发件人姓名/信件内容" id="SearchStr"
							onfocus="this.value=''"
							onblur="if(this.value==''){this.value='信件标题/发件人姓名/信件内容'}"
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
								<!-- <th scope="col">序号</th> -->
								<th scope="col">查看</th>
								<th scope="col">信件标题</th>
								<th scope="col">发件人姓名</th>
								<th scope="col">登录名</th>
								<th scope="col">电子邮箱</th>
								<th scope="col">联系电话</th>
								<th scope="col">受理部门名称</th>
								<th scope="col">写信时间</th>
								<!-- <th scope="col">信件内容</th> -->
							    <th scope="col">IP地址</th>
								<!--<th scope="col">审核人</th>
								<th scope="col">处理结果</th>-->
								<th scope="col">处理状态</th>
								<th scope="col">满意度</th>
								<!-- <th scope="col">评价</th> -->
							</tr>
							<tr>
								<c:forEach var="data" items="${list}" varStatus="status">
									<tr>
										<%-- <td style="width: 3%; text-align: center;">${status.count}</td> --%>
										<td style="width: 5%; cursor: pointer;"><a
											id="GridView1_ctl02_HyperLink3"
											href='<%=path%>/xzxx/XxXjxxb/getXjckDetail?bxbh=${data.id}&xm=${xm}'>查看</a></td>
										<td style="width: 8%;" title="${data.xjbt}">${data.xjbt}</td>
										<td style="width: 8%;">${data.fjrxm}</td>
										<td style="width: 8%;"title="${data.fjrbh }">${data.fjrbh}</td>
										<td style="width: 8%;" title="${data.txdz}">${data.txdz}</td>
										<td style="width: 8%;">${data.lxdh}</td>
										<td style="width: 8%;">${data.slbmmc}</td>
										<td style="width: 8%;" title="${data.xxsj }">${data.xxsj}</td>
									<%-- 	<td style="width: 8%;">${data.xjnr}</td> --%>
										<td style="width: 8%;">${data.ipdz}</td>
										<%-- <td style="width: 8%;">${data.shsj}</td>
										<td style="width: 8%;">${data.shr}</td>
										<td style="width: 8%;">${data.cljg}</td>
										<td style="width: 8%;">${data.clsj}</td> --%>
										<c:choose>
											<c:when test="${data.ztbh == 3}">
												<td style="width: 8%;"><font size="2px"
													style="color: #00FF1E">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 5}">
												<td style="width: 8%;"><font size="2px"
													style="color: #FFAE00">${data.zt}</font></td>
											</c:when>
											<c:when test="${data.ztbh == 6}">
												<td style="width: 8%;"><font size="2px"
													style="color: #FF0000">${data.zt}</font></td>
											</c:when>
										</c:choose><td>
										<c:choose>
											<c:when test="${data.myd == '1'}">
												<font size="2px"
													style="color: #00FF1E">非常满意</font>
											</c:when>
											<c:when test="${data.myd == '2'}">
												<font size="2px"
													style="color: #FFAE00">满意</font>
											</c:when>
											<c:when test="${data.myd == '3'}">
												<font size="2px"
													style="color: #FF0000">不满意</font>
											</c:when>
										</c:choose></td>
										<%-- <td style="width: 8%;">${data.pj}</td> --%>
									</tr>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%-- 	<div id="footer" style="text-align: center;">
		<c:choose>
			<c:when test="${pages > 1}">
				<a href="<%=path%>/xzxx/XxXjxxb/getXjckList?pages=${pages - 1}">上一页</a>
			</c:when>
			<c:otherwise>
						上一页
					</c:otherwise>
		</c:choose>
		第${pages}页
		<c:choose>
			<c:when test="${pages < count}">
				<a href="<%=path%>/xzxx/XxXjxxb/getXjckList?pages=${pages + 1}">下一页</a>
			</c:when>
			<c:otherwise>
					下一页
				</c:otherwise>
		</c:choose>
		总共${count}页
	</div> --%>
</body>
</html>