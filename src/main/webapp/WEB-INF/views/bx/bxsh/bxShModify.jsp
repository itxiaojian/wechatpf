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
jQuery(function() {
	//审核选中
	$("#Button1").click(function() {
		if(confirm("您确定通过吗？")){
		$.ajax({
			url:'<%=path%>/bx/bxsh/moditg',
			type : 'get',
			//dataType : 'json',
			data:{
				ztbh:$('#shId').text(),
				shyj:$('#aa').val(),
				shId:$('#shId').text(),
			},
			success: function(){
			window.location.href="<%=path%>/bx/bxsh/shlist";
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
		 }
 });
	
	//驳回
	$("#Button2").click(function() {
		if(confirm("您确定驳回吗？")){
		$.ajax({
			url:'<%=path%>/bx/bxsh/modibh',
			type : 'get',
			//dataType : 'json',
			data:{
				ztbh:$('#Label1').text(),
				shyj:$('#aa').val(),
				shId:$('#shId').text(),
			},
			success: function(){
			window.location.href="<%=path%>/bx/bxsh/shlist";
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
		 }
 });
});
</script>
</head>
<body>
<div class="main_div">
 </div>
    <div class="main_div">
        <div class="main_div">
            <div class="b1">
            <table cellspacing="1" cellpadding="0" border="0" class="add_table" width="100%">
              <c:forEach var="data" items="${list}" varStatus="status">
                <tr>
                <td style="width:10%;display:none;" class="table_titles"><font>Id：</font></td>
                <td style="width:23%; display:none;" id="shId" class="tl"><span>${data.id}</span></td>
                <td style="width:10%" class="table_titles"><font>申请单号：</font></td>
                <td style="width:23%"  class="tl"><span id="Label1">${data.bxbh}</span></td>
                <td style="width:10%" class="table_titles"><font>状态：</font></td>
              <!--   <td style="width:23%" class="tl"><font color="FFAE00"></font></td> -->
                <c:choose>
								<c:when test="${data.ztbh == 1}">
									<td style="width: 23%;"><font size="2px"style="color:#00FF1E">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 2}">
									<td style="width: 23%;"><font size="2px"style="color:#FFAE00">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 3}">
									<td style="width: 23%;"><font size="2px"style="color:#FFAE00">${data.zt}</font></td>
								</c:when>
								<c:when test="${data.ztbh == 4}">
									<td style="width: 23%;"><font size="2px">${data.zt}</font></td>
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
                <td class="tl" colspan="3"><span id="Label17">${data.nr}</span></td>  
                <td class="table_titles"><font>主题名称：</font></td>
                <td class="tl"><span id="Label2">${data.sbztmc}</span></td>                       
                </tr>  
                <tr>
                <td class="table_titles"><font>楼宇：</font></td>
                <td class="tl"><span id="Label4">${data.lh}</span></td>
                <td class="table_titles"><font>楼号：</font></td>
                <td class="tl"><span id="Label5">${data.lh}</span></td>
                <td class="table_titles"><font>服务类型：</font></td>
                <td class="tl"><span id="Label6">${data.fw}</span></td>            
                </tr>   
                <tr>
                <td class="table_titles"><font>报修地址：</font></td>
                <td class="tl"><span id="Label7">${data.dz}</span></td>         
                <td class="table_titles"><font>预约时间：</font></td>
                <td class="tl"><span id="Label10">${data.yysj}</span></td>
                <td class="table_titles" style="height:20px"><font>评价：</font></td>
                <td class="tl"><span id="Label11">${data.pj}</span></td>
               </tr> 
                <tr>
                <td class="table_titles"><font>备注：</font></td>
                <td class="tl" colspan="5" height="40px"><span id="Label12">${data.bz}</span></td>
                </tr>  
                </c:forEach> 
            </table>
            <table>
             <tr>
                 <td class="table_titles"><font>审批意见：</font></td>
                 <td class="tl">
                 	<label>
						<textarea name="message" cols="120" rows="5" class=autosave id="aa"></textarea>
					</label>
				</td>
                </tr>
            </table>
            </div>
        <div class="actiondiv">
          <!--   <input id="Button1" class="Button" style="background-color:#009FD7"type="button" value="通过" />
            &nbsp; &nbsp; 
             <input id="Button2" class="Button" style="background-color:#009FD7"type="button" value="驳回"/>
            &nbsp; &nbsp;  -->
             <a href="###" onclick="javascript:history.go(-1);"><input id="Button3" class="Button" style="background-color:#009FD7"type="button" value="返回"/></a>
        </div>
    </div>
    </div>
    <div class="split"></div>
</body>
</html>