<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
  
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3"></meta>
	<title>班级成绩</title>
  </head>
 <style>
 body {
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
}
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; overflow:hidden;}
.top_01{ width:100%; height:50px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:16px; line-height:50px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.top_01 span{ float:left;}
.top_01 img{ margin-top:15%; display:block; float:left;}
.main_02{width:100%; position:absolute; top:13%;margin-top:2%; left:0; bottom:38px; background-color:#fff; overflow:auto;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.foot_01{width:100%; height:38px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}

.CJCX_people{ width:100%; height:100px; border-bottom:1px solid #b8d6fa; font-size:36px; text-align:center; line-height:100px;}
.CJCX_people span{ width:33%; float:left;}
.CJCX_biao{ margin-top:10px;}
.CJCX_biao table{border-bottom:1px solid #b8d6fa;box-sizing:border-box;}
.CJCX_biao td{ border-top:1px solid #b8d6fa;border-right:1px solid #b8d6fa; line-height:30px;font-size:13px; text-align:center; width:25%; box-sizing:border-box;}
.CJCX_biao_BG{ background-color:#b8d6fa; color:#fff;}
.CJCX_HJ{ width:100%; height:100px; background-color:#b8d6fa; font-size:36px; text-align:center; line-height:100px; color:#fff;}
.CJCX_HJ span{width:33%; float:left;}
.CJCX_title{ width:100%; height:40px; background-color:#b8d6fa; font-size:15px; text-align:center; line-height:40px; color:#fff;}
.CJCX_title span{width:25%; float:left;}
</style>
 
<body>
<input type="hidden" name="pjf" id="pjf" value="${pjf }">
<input type="hidden" name="kskm" id="kskm" value="${kskm }">
<div class="phone_01">
      <div class="top_01">
         <span style="width:60%; ">
             <img src="<%=path%>/resources/img/wzy/c11-04.png" style=" margin-top:4%" width="30" height="30">班级成绩&nbsp;&nbsp;
         </span>
         <span style="width:20%;">
           &nbsp;
         </span>
          <a href="javascript:;" onclick="javascript:history.go(-1)">
          <span style="width:5%;float:right;margin-right:5%;">
              <img src="<%=path %>/resources/img/wzy/fh1.png" style="margin-top:70%;width:100%;">
          </span>
          </a>
      </div>
    <div style="height: 10%;margin-top: 17%;font-size: 14px;width: 100%;text-align: center;">
                                  考试科目：<span style="color:red;">${kskm }</span></div>
    <div class="main_02">
 		<div class="CJCX_biao">
        	<table width="100%">
                <div class="CJCX_title">
<!--                     <span style=" width:30%">考试科目</span> -->
                    <span style=" width:35%">姓名</span>
                    <span style=" width:35%">学号</span>
                    <span style=" width:30%">成绩</span>
                </div>
              <tbody>
              <c:forEach items="${bjcjlist }" var="bjcjlist">
<!--                <span > -->
                <tr>
<%--                   <td style=" width:30%">${bjcjlist.kskm }</td> --%>
                  <td style=" width:35%">${bjcjlist.xm }</td>
                  <td style=" width:35%">${bjcjlist.xh }</td>
                  <c:if test="${bjcjlist.kscj>=pjf }">
                  <td style=" width:30%;color:green;">${bjcjlist.kscj }</td>
                  </c:if>
                  <c:if test="${bjcjlist.kscj<pjf }">
                  <td style=" width:30%;color:red;">${bjcjlist.kscj }</td>
                  </c:if>
                </tr>
               </c:forEach>
              </tbody>
            </table>
        </div>
    </div>
    <div class="foot_01">
    	<img src="<%=path%>/resources/img/wzy/BQ.png" width="100%" height="100%">
    </div>
</div>