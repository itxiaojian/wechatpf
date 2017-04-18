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
       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>黑名单信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
       	    <th>序列号</th>
			<th>黑名单类型</th>
			<th>姓名</th>
			<th>证件号</th>
			<th>经办支行</th>
			<th>校验标识</th>
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
					<td>
						<c:choose>  
						   <c:when test="${data.BLACKTYPE=='1'}">欠息黑名单</c:when>  
						   <c:when test="${data.BLACKTYPE=='2'}">打官司黑名单</c:when>
						   <c:when test="${data.BLACKTYPE=='3'}">表内不良黑名单</c:when>
						   <c:when test="${data.BLACKTYPE=='4'}">表外不良黑名单</c:when>
						   <c:when test="${data.BLACKTYPE=='5'}">其他风险客户</c:when>
						   <c:otherwise>${data.BLACKTYPE}</c:otherwise>
						</c:choose> 
					</td>
					<td>${data.CUST_NAME}</td>
					<td>${data.CERTIFICATE_NO}</td>
					<td>${data.HANDLE_ORG}</td>
					<th>
						<c:choose>
						  <c:when test="${data.CHECK_FLAG=='1'}"><font color="red">需要校验</font></c:when>  
						   <c:when test="${data.CHECK_FLAG=='2'}"><font color="green">无需校验</font></c:when>
						   <c:otherwise>${data.CHECK_FLAG}</c:otherwise>
						</c:choose> 
					</th>
					<td align="center"><a href="javascript:void(0)" onclick="parent.showBlackInfoDetails('${data.ID}','${data.BLACKTYPE}','${data.CUST_NAME }')">查看</a></td>
				</tr>
			</c:forEach>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>