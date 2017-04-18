<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>    
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap.min.css" />  
  	<script type="text/javascript">
	  var customerId = '${customerId}';
  	</script>
  	
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
       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>关联人信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
			<th>序列号</th>
			<th>流水号</th>
			<th>客户号</th>
			<th>关联人名称</th>
			<th>操作</th>
		</tr>	
		<c:if test="${empty infos}">
			<tr>
				<td colspan="6" align="center">没有记录</td>
			</tr>
		</c:if>  
		<c:forEach var="data" items="${infos}" varStatus="status"> 
				<tr>
					<td>${status.count}</td>
					<td>${data.ROW_ID}</td>
					<td>${data.CUSTOMER_ID}</td>
					<td>${data.RELATIONSHIP_NAME}</td>
					<td align="center"><a href="javascript:void(0)" onclick="parent.showRelatedPeopleInfoDetails('${data.ROW_ID}','${data.RELATIONSHIP_NAME}')">查看</a></td>
				</tr>
			</c:forEach>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>