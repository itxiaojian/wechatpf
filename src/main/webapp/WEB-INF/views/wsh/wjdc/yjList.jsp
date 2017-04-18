<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/css/main.css" />
		<script type="text/javascript">
		//返回
		function back() {
<%-- 			window.location.href="<%=path%>/wsh/WjObject/toWjList"; --%>
			history.go(-1);
		}
		
		//导出Word
		function exportWord() {
			return;
		}
		</script>
		<title>问卷管理系统</title>
	</head>
	<body>
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px;font-size:12px;">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="skin/images/tbg.gif">&nbsp;内容列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="6%">序号</td>
	<td width="94%">内容</td>
</tr>
<c:if test="${empty list}">
<tr align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td height="24" colspan="10" background="<%=path%>/resources/skin/images/tbg.gif">no data&nbsp;</td>
</tr>
</c:if>
<c:forEach var="data" items="${list}" varStatus="obj">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td>${obj.count }</td>
	<td align="left">${data.sevalue }</td>
</tr>
</c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="10">
</td>
</tr>
</table>
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" style="margin-top:8px;font-size:12px;">
		<tr>
			<td align="left">
				<!-- 
				<input type="button" name="back" value="导出Word"  onclick="exportWord();" class="btn2"/>&nbsp;&nbsp;&nbsp;
				 -->
				<input type="button" name="back" value="返  回 "  onclick="back();" class="btn2"/>
			</td>
		</tr>
</table>
</body>
</html>
