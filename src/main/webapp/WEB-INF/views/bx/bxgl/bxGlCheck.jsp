<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/aero.css" rel="stylesheet" />
<link href="<%=path%>/resources/css/bxgl.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script type="text/javascript">
    function acback(){
        var win=$.dialog.open.origin;
        win.location.reload();
        window.top.$.dialog({id:'RepairSee'}).close();
    }
    </script>
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
<body>
<div class="split"></div>
    <div class="main_div">
        <div class="main_div">
            <div class="b1">
            <table cellspacing="1" cellpadding="0" border="0" class="add_table" width="100%">
              <c:forEach var="data" items="${list}" varStatus="status">
                <tr>
                <td style="width:10%" class="table_titles"><font>申请单号：</font></td>
                <td style="width:23%"  class="tl"><span class="bxbh" title="${data.bxbh}" id="Label1">${data.bxbh}</span></td>
                <td style="width:10%" class="table_titles"><font>状态：</font></td>
              <!--   <td style="width:23%" class="tl"><font color="FFAE00"></font></td> -->
                <c:choose>
								<c:when test="${data.ztbh == 1}">
									<td style="width: 23%;"><font size="2px"style="color:#00FF1E">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 2}">
									<td style="width: 23%;"><font size="2px"style="color:#BB3D00;">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 3}">
									<td style="width: 23%;"><font size="2px"style="color:red;">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 4}">
									<td style="width: 23%;"><font size="2px"style="color:#00FF1E;">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 5}">
									<td style="width: 23%;"><font size="2px"style="color:#DC143C;">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 6}">
									<td style="width: 23%;"><font size="2px"style="color:#FFAE00;">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 7}">
									<td style="width: 23%;"><font size="2px"style="color:red;">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 8}">
									<td style="width: 23%;"><font size="2px"style="opacity:0.8;">${data.zt}</font></td>
								</c:when>
								<c:otherwise>
								<td style="width: 23%;"><font size="2px"style="color:#FF0000">${data.zt}</font></td>
								</c:otherwise>
							</c:choose>
                <td style="width:10%" class="table_titles"><font>申报人：</font></td>
                <td style="width:24%" class="tl"><span id="Label3">${data.sqrxm}</span></td>            
                </tr>
                <tr>
                <td class="table_titles"><font>申请主题：</font></td>
                <td class="tl" ><span class="nrtd" title="${data.nr}" id="Label17">${data.nr}</span></td> 
               
                <td class="table_titles"><font>主题名称：</font></td>
                <td class="tl"><span id="Label2">${data.sbztmc}</span></td>  
                <td class="table_titles"><font>预约时间：</font></td>
                <td class="tl" colspan="3"><span id="Label3">${data.yysj}</span></td>                      
                </tr>  
                <tr>
                <td class="table_titles"><font>楼宇：</font></td>
                <td class="tl"><span id="Label4">${data.ly}</span></td>
                <td class="table_titles"><font>楼号：</font></td>
                <td class="tl"><span id="Label5">${data.lh}</span></td>
                <td class="table_titles"><font>服务类型：</font></td>
                <td class="tl"><span id="Label6">${data.fw}</span></td>            
                </tr>   
                <tr>
                <td class="table_titles"><font>报修地址：</font></td>
                <td class="tl"><span id="Label7">${data.dz}</span></td>         
                <td class="table_titles" style="height:20px"><font>评价：</font></td>
                <td class="tl"><span class="pjtd" title="${data.pj}" id="Label11">${data.pj}</span></td>
                <td class="table_titles" style="height:20px"><font>满意度：</font></td>
                <td class="tl"><span   id="Label8">${data.myd}</span></td>
               </tr> 
                <tr>
                <td class="table_titles"><font>备注：</font></td>
                <td class="tl" colspan="5" height="40px"><span class="bztd"  id="Label12">
                   <c:if test="${fn:length(fn:split(data.bz,';'))!=1 }">
						<c:forEach var="item" varStatus="status" begin="0" end="${fn:length(fn:split(data.bz,';'))-1}">
 							<img style="width:20%;height:20%;"   src="${fn:split(data.bz,';')[status.index]}"/>
						</c:forEach>
						</c:if>
						<c:if test="${fn:length(fn:split(data.bz,';'))==1 && data.bz==''}">
						 ${data.bz}
						</c:if>
						<c:if test="${fn:length(fn:split(data.bz,';'))==1 && data.bz!=''}">
						 <img style="width:20%;height:20%;"   src="${data.bz}"/>
				  </c:if>
                </span></td>
                </tr>  
                </c:forEach> 
            </table>
            </div>
            <DIV class="tl fwb pl20">受理信息</DIV>
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
						<%-- <TR>
							<TD class="table_titles"><FONT>维修用时：</FONT></TD>
							<TD class="tl">${data.wxys }</TD>
							<!-- <TD class="table_titles"><FONT>排出人：</FONT></TD>
							<TD class="tl">洪少明(水)</TD> -->
							<TD class="table_titles"><FONT>耗材用料：</FONT></TD>
							<TD class="tl">${data.hcyl }</TD>
						</TR> --%>
						</c:forEach>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>
        <div class="actiondiv">
            <input id="Button2" class="Button" style="background-color:#009FD7"type="button" value="返 回" onclick="javascript:history.go(-1);"/>
        </div>
    </div>
    </div>
    <div class="split"></div>
</body>
</html>