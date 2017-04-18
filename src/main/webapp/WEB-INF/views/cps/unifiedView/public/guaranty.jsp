<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>    
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap.min.css" />  
    <link rel="stylesheet" type="text/css" href="/resources/js/ext/resources/css/ext-all.css" />
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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>抵押物信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
			<th width="20%">序列号</th>
			<th>客户号</th>
			<th>抵质押合同号</th>
			<th>操作</th>
		</tr>	
		<c:if test="${empty guarantyList}">
			<tr>
				<td colspan="4" align="center">没有记录</td>
			</tr>
		</c:if>  
		<c:forEach var="data" items="${guarantyList}" varStatus="status"> 
				<tr>
					<td>${status.count}</td>
					<td>${data.customerId}</td>
					<td>${data.pledgeBargainId }</td>
					<td align="center"><a href="javascript:void(0)" onclick="parent.showGuarantyInfoDetails('${data.customerId}','${data.pleName}')">查看</a></td>
				</tr>
			</c:forEach>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>