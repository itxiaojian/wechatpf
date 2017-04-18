<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>报修驳回</title>
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
	src="<%=path%>/resources/js/bx/bxsh/jquery_003.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/jquery.artDialog.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bx/bxsh/public.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
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
	<DIV class="maindiv">
			<DIV class="fwb">驳回理由：</DIV>
			<DIV class="pt10">
				<TEXTAREA name="areadropboxvalue" class="InputNormal"
					id="areadropboxvalue" style="width: 470px; height: 140px;" rows="3"
					cols="40"></TEXTAREA>
			</DIV>
		</DIV>
		<DIV class="splits"></DIV>
		<DIV id="mg"></DIV>
		<DIV class="none" style="margin: 0px; padding: 0px;">
			<INPUT name="Button1" class="button aui_state_highlight" id="Button1"
				type="submit" value="提 交">
		</DIV>
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>