<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>    
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap.min.css" />    	
	<style type="text/css">
	th {
		padding: 6px 12px;
		background-color: #eeeeee;
	}
	td {
		padding: 6px 12px;
	}
	</style>
  </head>
  <body>
  
  	 <div class="container">

      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>家庭主要成员情况</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
			<th>序列号</th>
			<th>客户号</th>
			<th>客户名称</th>
			<th>性别</th>
			<th>与客户关系</th>
			<th>工作单位名称</th>
			<th>操作</th>
		</tr>	
		<c:if test="${empty members}">
			<tr>
				<td colspan="6" align="center">没有记录</td>
			</tr>
		</c:if>  
		<c:forEach var="data" items="${members}" varStatus="status"> 
				<tr>
					<td>${status.count}</td>
					<td>${data.customerId}</td>
					<td>${data.custName}</td>
					<td>
						<c:choose>  
						   <c:when test="${data.sex ==1}">    
						   			男
						   </c:when>  
						    <c:when test="${data.sex ==2}">    
						   			女
						   </c:when>
						   <c:otherwise>
						   		${data.sex}
						   </c:otherwise>
						</c:choose> 
	       			</td>
					<td>${data.relationToCustomer }</td>
					<td>${data.jobUnitName}</td>
					<td align="center"><a href="javascript:void(0)" onclick="parent.showMemberDetails('${data.rowId}','${data.custName}')">查看</a></td>
				</tr>
			</c:forEach>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>