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
.STYLE1 {font-family: "宋体"}
.STYLE2 {font-size: x-large}
</style>
</head>

<body>
<div class="STYLE1">
  <div align="center" class="STYLE2">公寓管理服务中心报修单</div>
</div>
<c:forEach var="data" items="${list}" varStatus="obj">
<table border="1" cellspacing="0" cellpadding="0" width="80%" align="center">
  <tr>
    <td colspan="4" valign="top"><p align="center"><strong>维修信息</strong><strong>&nbsp;</strong></p></td>
  </tr>
  <tr>
    <td width="107" valign="top"><p align="center">报修日期 </p></td>
    <td width="197" valign="top"><p align="center">${data.yysj}</p></td>
    <td width="107" valign="top"><p align="center">维修人 </p></td>
    <td width="199" valign="top"><p align="center">${data.wxrxm}</p></td>
  </tr>
  <tr>
    <td width="107" valign="top"><p align="center">报修人 </p></td>
    <td width="197" valign="top"><p align="center">${data.sqrxm}</p></td>
    <td width="107" valign="top"><p align="center">服务类型 </p></td>
    <td width="199" valign="top"><p align="center">${data.sbztmc} </p></td>
  </tr>
  <tr>
    <td width="107" valign="top"><p align="center">联系电话 </p></td>
    <td width="197" valign="top"><p align="center">${data.lxhm}</p></td>
    <td width="107" valign="top"><p align="center">报修地点 </p></td>
    <td width="199" valign="top"><p align="center">${data.dz}</p></td>
  </tr>
  <tr>
    <td width="107" valign="top"><p align="center">报修内容 </p></td>
    <td colspan="3" valign="top"><p align="left">${data.nr}</p><p align="left">&nbsp;</p><p align="left">&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="4" valign="top" style="height:50px;border-right:0px;border-left:0px;"></td>
  </tr>
  <tr>
    <td colspan="4" valign="top"><p align="center"><strong>反馈信息 </strong></p></td>
  </tr>
  <tr>
    <td width="107" valign="top"><p align="center">完成日期 </p></td>
    <td width="197" valign="top"><p align="center">&nbsp;</p></td>
    <td width="107" valign="top"><p align="center">报修人签名 </p></td>
    <td width="199" valign="top"><p align="center">&nbsp;</p></td>
  </tr>
  <tr>
    <td width="107" valign="top"><p align="center">维修人签名 </p></td>
    <td width="197" valign="top"><p align="center">&nbsp;</p></td>
    <td width="107" valign="top"><p align="center">负责人签名 </p></td>
    <td width="199" valign="top"><p align="center">&nbsp;</p></td>
  </tr>
</table>
</c:forEach>
</body>

</html>
