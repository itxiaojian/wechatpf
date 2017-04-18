<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看投票选项</title>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/css/main.css" />
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
<style type="text/css">
.container{
	background-color: white;
    height: 500px;
    margin-left: 30px;
    margin-right: 30px;
    margin-top: 30px;
    overflow: hidden;
}
</style>
</head>
<body topmargin="2">
	<div class="container">
	<table class="table">
		<tr>
			<td valign="top">
				<table class="table">
					<tr>
						<td width="35" rowspan="2" valign="top"></td>
						<td>
							<table class="table">
								<tr>
									<td colspan="2">
										<div align="center" style="font-size: 14px;">
											<strong>查看选项</strong>
										</div> <br />
									</td>
								</tr>
								<tr>
									<td colspan="5">
											<table class="table2">
												 <tr>
													 <td>
														 <table class="table2">
															  <c:forEach var="date" items="${xxlist}" varStatus="ob">
																  <tr>
																	  <td width="25%" valign="top"><input
																			type="radio"  value="${date.id}" />${date.xxnr}</td>
																  </tr>
																  <tr>
																  </tr>
															  </c:forEach>
														 </table>
													</td>
												 </tr>
											</table>
									</td>
								</tr>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td>
										<div id="buttonArea">
											<a href="javascript:;history.back()"><button type="button" class="btn" class="btn"> 返     回 </button>
										    </a><br />
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="20"></td>
					</tr>
				</table> <br />
			</td>
			<td width="23" rowspan="2">&nbsp;</td>
		</tr>
	</table>
</div>
</body>
</html>
