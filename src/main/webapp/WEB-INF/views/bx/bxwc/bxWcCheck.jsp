<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>报修完成</title>
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
	src="<%=path%>/resources/js/bx/bxsh/jquery.artDialog.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/public.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script type="text/javascript">
jQuery(function() {
		if($(".nrtd").text().length>15){
			$(".nrtd").text($('.nrtd').text().substring(0, 10) + "...");
		}
		if($(".pjtd").text().length>15){
			$(".pjtd").text($('.pjtd').text().substring(0, 10) + "...");
		}
		/* if($(".bztd").text().length>15){
			$(".bztd").text($('.bztd').text().substring(0, 10) + "...");
		} */
		if($(".bxbh").text().length>18){
			$(".bxbh").text($('.bxbh').text().substring(0, 18) + "...");
		}
});
</script>

</head>
<body id="mainbody" style="overflow-y: scroll;" class="objbody1">
	<div class="aui_state_focus"
		style="position: absolute; left: -9999em; top: 50px; width: auto; z-index: 1987;">
		<div class="aui_outer">
			<table class="aui_border">
				<tbody>
					<tr>
						<td class="aui_nw"></td>
						<td class="aui_n"></td>
						<td class="aui_ne"></td>
					</tr>
					<tr>
						<td class="aui_w"></td>
						<td class="aui_c"><div class="aui_inner">
								<table class="aui_dialog">
									<tbody>
										<tr>
											<td colspan="2" class="aui_header"><div
													class="aui_titleBar">
													<div style="cursor: move;" class="aui_title">消息</div>
													<a class="aui_close" href="javascript:/*artDialog*/;">×</a>
												</div></td>
										</tr>
										<tr>
											<td style="display: none;" class="aui_icon"><div
													style="background: transparent none repeat scroll 0% 0%;"
													class="aui_iconBg"></div></td>
											<td style="width: auto; height: auto;" class="aui_main"><div
													style="padding: 20px 25px;" class="aui_content">
													<div class="aui_loading">
														<span>loading..</span>
													</div>
												</div></td>
										</tr>
										<tr>
											<td colspan="2" class="aui_footer"><div
													class="aui_buttons">
													<button type="button" class="aui_state_highlight">确定</button>
												</div></td>
										</tr>
									</tbody>
								</table>
							</div></td>
						<td class="aui_e"></td>
					</tr>
					<tr>
						<td class="aui_sw"></td>
						<td class="aui_s"></td>
						<td style="cursor: auto;" class="aui_se"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="maindiv">
		<div class="b1">
			<table class="add_table" border="0" cellspacing="1" width="100%">
				<tbody>
				 <c:forEach var="data" items="${list}" varStatus="status">
					<tr>
						<td style="width: 10%" class="table_titles"><font>申请单号：</font></td>
						<td style="width: 23%" class="tl"><span class ="bxbh" title="${data.bxbh}"id="Label1">${data.bxbh}</span></td>
						<td style="width: 10%" class="table_titles"><font>状态：</font></td>
						<td style="width: 23%" class="tl"><font color="">${data.zt}</font></td>
						<td style="width: 10%" class="table_titles"><font>申报人：</font></td>
						<td style="width: 24%" class="tl"><span id="Label3">${data.sqrxm}</span></td>
					</tr>
					<tr>
						<td class="table_titles"><font>报修主题：</font></td>
						<td class="tl" colspan="3"><span id="Label17">${data.sbztmc}</span></td>
					</tr>
					<tr>
						<td class="table_titles"><font>电话：</font></td>
						<td class="tl"><span id="Label4">${data.lxhm}</span></td>
						<td class="table_titles"><font>预约时间：</font></td>
						<td class="tl"><span id="Label5">${data.yysj}</span></td>
					</tr>
					<tr>
						<td class="table_titles"><font>报修地址：</font></td>
						<td class="tl" colspan="5"><span id="Label7">${data.dz}</span></td>
					</tr>
					<tr>
						<td class="table_titles"><font>申报内容：</font></td>
						<td class="tl"><span title="${data.nr}" id="Label11" class="nrtd">${data.nr}</span></td>
						<td class="table_titles"><font>评价：</font></td>
						<td class="tl"><span title="${data.pj}" class="pjtd" id="Label5">${data.pj}</span></td>
						<td class="table_titles"><font>满意度：</font></td>
						<td class="tl"><span id="Label15">${data.myd}</span></td>
					</tr>
					<tr>
						<td class="table_titles"><font>备注：</font></td>
						<td class="tl" colspan="5"><span class="bztd"  id="Label12"></span>${data.bz}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<DIV class="tl fwb pl20">处理信息</DIV>
			<DIV class="b1">
				<TABLE width="100%" class="add_table" border="0" cellspacing="1">
					<TBODY>
					 <c:forEach var="data" items="${list}" varStatus="status">
						<TR>
							<TD class="table_titles" style="width: 10%;"><FONT>审核人：</FONT></TD>
							<TD class="tl" style="width: 23%;">${data.sprxm }</TD>
							<TD class="table_titles" style="width: 10%;"><FONT>审核时间：</FONT></TD>
							<TD class="tl" style="width: 23%;">${data.spsj }</TD>
							<TD class="table_titles" style="width: 10%;"><FONT></FONT></TD>
							<TD class="tl" style="width: 24%;"></TD>
						</TR>
						<TR>
							<TD class="table_titles"><FONT>派出人：</FONT></TD>
							<TD class="tl">${data.wxrxm }</TD>
						    <TD class="table_titles"><FONT>派单人：</FONT></TD>
							<TD class="tl">${data.sprxm}</TD>
							<TD class="table_titles"><FONT>派单时间：</FONT></TD>
							<TD class="tl">${data.pdsj }</TD>
						</TR>
						
						<TR>
							<TD class="table_titles"><FONT>受理人：</FONT></TD>
							<TD class="tl">${data.wxrxm }</TD>
							<!-- <TD class="table_titles"><FONT>排出人：</FONT></TD>
							<TD class="tl">洪少明(水)</TD> -->
							<TD class="table_titles"><FONT>受理时间：</FONT></TD>
							<TD class="tl">${data.pdsj }</TD>
						</TR>
						<TR>
							<TD class="table_titles"><FONT>维修用时：</FONT></TD>
							<TD class="tl">${data.wxys }</TD>
							<!-- <TD class="table_titles"><FONT>排出人：</FONT></TD>
							<TD class="tl">洪少明(水)</TD> -->
							<TD class="table_titles"><FONT>耗材用料：</FONT></TD>
							<TD class="tl">${data.hcyl }</TD>
						</TR>
						
						</c:forEach>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>
	
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function thisclose(s) {
			var win = $.dialog.open.origin;
			win.location.reload();
			window.top.$.dialog({
				id : 'RepairSee'
			}).close();
		}
	</script>
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>