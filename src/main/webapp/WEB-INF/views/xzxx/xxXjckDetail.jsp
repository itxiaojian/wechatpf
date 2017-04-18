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
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
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
    
    jQuery(function (){
    	if($(".xjnrtd").text().length>18){
			$(".xjnrtd").text($('.xjnrtd').text().substring(0, 18) + "...");
		}
    	if($(".cljgtd").text().length>18){
			$(".cljgtd").text($('.cljgtd').text().substring(0, 18) + "...");
		}
    	
    $('#Button3').click(function() {
    	$('#mydInput').val($("input[type='radio']:checked").val());
    	var pj  = $('#pjArea').val();
    	var myd = $('#mydInput').val();
    	var id  = $('#idInput').val();
    	var url ="<%=path%>/xzxx/XxXjxxb/savePj";
    	$.ajax({
    		cache : true,
    		type : "post",
    		url : url,
    		data : {
    			id:id,
    			myd:myd,
    			pj:pj,
    		},
    		async : false,
    		error : function(request) {
    			alert("提交失败，请联系管理员。");
    		},
    		success : function(data) {
    			if(data=='1'){
    				alert('提交成功！');
    				location.href ="<%=path%>/xzxx/XxXjxxb/getXjckList";
    			}else{
    				alert("提交失败，请联系管理员。");
    			}
    		}
    	});
    })
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
                <td style="display:none;"><input id="idInput"  value="${data.id}" style="display:none;" /></td>
                <td style="width:80px" class="table_titles"><font>登录名：</font></td>
                <td style="width:350px"  class="tl"><span class="bxbh" title="${data.fjrbh}" id="Label1">${data.fjrbh}</span></td>
               
                <td class="table_titles"><font>发件人姓名：</font></td>
                <td class="tl"><span id="Label3">${data.fjrxm}</span></td>            
                </tr>
                <tr>
                <td style="width:80px" class="table_titles"><font>信件标题：</font></td>
                <td class="tl"style="width:350px" ><span class="nrtd" title="${data.xjbt}" id="Label17">${data.xjbt}</span></td>  
                 <td class="table_titles"><font>处理状态：</font></td><td >
                <c:choose>
											<c:when test="${data.ztbh == 3}">
												<font size="2px"
													style="color: #00FF1E">${data.zt}</font>
											</c:when>
											<c:when test="${data.ztbh == 5}">
												<font size="2px"
													style="color: #FFAE00">${data.zt}</font>
											</c:when>
											<c:when test="${data.ztbh == 6}">
												<font size="2px"
													style="color: #FF0000">${data.zt}</font>
											</c:when>
										</c:choose></td>
                </tr>  
                <tr>
                <td class="table_titles" style="width:80px"><font>电子邮箱：</font></td>
                <td class="tl" style="width:350px"><span id="Label4">${data.txdz}</span></td>
                <td class="table_titles"><font>联系电话：</font></td>
                <td class="tl"><span id="Label6">${data.lxdh}</span></td>            
                </tr>   
                <tr>
                <td class="table_titles" style="width:80px"><font>写信时间：</font></td>
                <td class="tl" style="width:350px"><span id="Label7">${data.xxsj}</span></td>    
                <td class="table_titles"><font>IP地址：</font></td>
                <td class="tl" colspan="5"><span id="Label7">${data.ipdz}</span></td>       
               </tr> 
                <tr>
                <td class="table_titles" style="width:110px"><font>信件内容：</font></td>
                <td class="tl xjnrtd" title="${data.xjnr}" colspan="6" height="40px">${data.xjnr}</td>
                </tr>  
                </c:forEach> 
                <tr><td colspan="4"><font style="font-weight:bold;">处理信息</font></td></tr>
            
					<TBODY>
					 <c:forEach var="data" items="${list}" varStatus="status">
						<TR>
							<TD class="table_titles" style="width: 80px"><FONT>审核时间：</FONT></TD>
							<TD class="tl" style="width:350px">${data.shsj }</TD>
							<TD class="table_titles"><FONT>受理时间：</FONT></TD>
							<TD class="tl">${data.clsj }</TD>
						</TR>
						<TR>
						
							<TD class="table_titles"  style="width:80px"><FONT>受理部门：</FONT></TD>
							<TD class="tl" style="width:350px">${data.slbmmc }</TD>
							<TD class="table_titles"><FONT>处理结果：</FONT></TD>
							<TD class="tl cljgtd" title="${data.cljg}">${data.cljg }</TD>
						</TR>
						<TR>
						    <TD class="table_titles" style="width:80px"><FONT>评价：</FONT></TD>
						   <TD class="tl" > <c:if test="${data.ztbh==5 && data.fjrxm == xm}">
							<textarea id="pjArea"
							style="width: 350px; height: 45px;">${data.pj}</textarea>
							</c:if>
							 <c:if test="${data.ztbh!=5}">
							${data.pj}
							</c:if></TD>
							<TD class="table_titles"><FONT>满意度：</FONT></TD>
							 <c:if test="${data.ztbh!=5}">
							<td class="tl">
							<font size="2px" style="color: #00FF1E">
							<c:choose>
									<c:when test="${data.myd == '1'}">
												非常满意
									</c:when>
									 <c:when test="${data.myd == '2'}">
												满意
									</c:when>
									<c:when test="${data.myd == '3'}">
												不满意
									</c:when>
									</c:choose>
							</font></td>
							 </c:if>
							 
							<c:if test="${data.ztbh==5 && data.fjrxm == xm}">
							<td><input type="radio" id="radio-1-1" name="radio" value="1" checked="checked" class="radio" />
						    <label for="radio-1-1">非常满意</label> 
						    <input type="radio" id="radio-1-2" name="radio" value="2" class="radio" />
							<label for="radio-1-2">满意</label> 
							<input type="radio" id="radio-1-3" name="radio" value="3" class="radio" />
						   <label for="radio-1-3">不满意</label>
							<input type="text" value="" id="mydInput" name="myd" style="display:none;"/>
							</td>
							</c:if>
						</TR>
						</c:forEach>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>
        <div class="actiondiv">
         <c:forEach var="data" items="${list}" varStatus="status">
         <c:if test="${data.ztbh==5 && data.fjrxm == xm}">
          <input id="Button3" class="Button" style="background-color:#009FD7"type="button" value="评价" onclick=""/>
          </c:if>
          </c:forEach>
          <input id="Button2" class="Button" style="background-color:#009FD7"type="button" value="返 回" onclick="javascript:history.go(-1);"/>
        </div>
    </div>
    <div class="split"></div>
</body>
</html>