<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修派单信息</title>
<style type="text/css">
.STYLE1,.STYLE2 {font-family: "微软细黑"}
.STYLE2 {font-size: x-large}
table {
	border-collapse: collapse;
	border: none;
}

td {
	border: solid #000 1px;
}
</style>
</head>

<body>
<div class="STYLE1">
  <div align="center" class="STYLE2">公寓管理服务中心报修单</div>
</div>
<c:forEach var="data" items="${list}" varStatus="obj">
<div style="font-size:11px;margin-left:75%;">流程号:</div>
<table class="myTb"  width="90%" height="100%" align="center">
  <tr>
    <td height="30" colspan="4" valign="middle"><p align="center"><strong>维修信息</strong><strong>&nbsp;</strong></p></td>
  </tr>
  <tr>
    <td height="40" width="87" valign="middle"><p align="center">报修日期 </p></td>
    <td height="40" width="100" valign="middle"><p align="left">${data.yysj}</p></td>
    <td height="40" width="87" valign="middle"><p align="center">维修人 </p></td>
    <td height="40" width="220" valign="middle"><p align="left">${data.wxrxm}</p></td>
  </tr>
  <tr>
    <td height="40" width="87" valign="middle"><p align="center">报修人 </p></td>
    <td height="40" width="100" valign="middle"><p align="left">${data.sqrxm}</p></td>
    <td height="40" width="87" valign="middle"><p align="center">服务类型 </p></td>
    <td height="40" width="220" valign="middle"><p align="left">${data.sbztmc} </p></td>
  </tr>
  <tr>
    <td height="40" width="87" valign="middle"><p align="center">联系电话 </p></td>
    <td height="40" width="100" valign="middle"><p align="left">${data.lxhm}</p></td>
    <td height="40" width="87" valign="middle"><p align="center">报修地点 </p></td>
    <td height="40" width="220" valign="middle"><p align="left">${data.dz}</p></td>
  </tr>
   <c:choose>
   <c:when test="${data.myd =='3'}">
    <tr>
    <td height="40" width="87" valign="middle"><p align="center">报修内容 </p></td>
    <td height="40" colspan="3" valign="top"><p align="left"  style="word-wrap:break-word;font-size:13px;">${data.nr}</p><p align="left">&nbsp;</p><p align="left">&nbsp;</p></td>
  </tr>
  </c:when>
  <c:otherwise>
   <tr>
    <td height="150" width="87" valign="middle"><p align="center">报修内容 </p></td>
    <td height="150" colspan="3" valign="top"><p align="left"  style="word-wrap:break-word;font-size:13px;">${data.nr}</p><p align="left">&nbsp;</p><p align="left">&nbsp;</p></td>
  </tr>
  </c:otherwise>
  </c:choose>
  <c:if test="${data.myd =='3'}">
    <tr>
    <td height="40" width="87" valign="middle"><p align="center">评价 </p></td>
    <td height="40" colspan="3" valign="top"><p align="left"style="word-wrap:break-word;font-size:13px;">${data.pj}</p><p align="left">&nbsp;</p></td>
  </tr>
  </c:if>
  </table>
  <div style="height:20px;"></div>
  <table width="90%" align="center" >
  <tr>
    <td height="30" colspan="4" valign="middle"><p align="center"><strong>反馈信息 </strong></p></td>
  </tr>
  <tr>
    <td height="40" width="87" valign="middle"><p align="center">完成日期 </p></td>
    <td height="40" width="197" valign="middle"><p align="left">&nbsp;</p></td>
    <td height="40" width="87" valign="middle"><p align="center">报修人签名 </p></td>
    <td height="40" width="199" valign="middle"><p align="center">&nbsp;</p></td>
  </tr>
  <tr>
    <td height="40" width="87" valign="middle"><p align="center">维修人签名 </p></td>
    <td height="40" width="197" valign="middle"><p align="left">&nbsp;</p></td>
    <td height="40" width="87" valign="middle"><p align="center">负责人签名 </p></td>
    <td height="40" width="199" valign="middle"><p align="left">&nbsp;</p></td>
  </tr>
</table>
</c:forEach>
</body>

</html>
