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
	<style type="text/css">
	th {
		padding: 6px 12px;
		width:15%;
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
	       		<th>抵质押合同号</th>
	       		<td>${guarantyDetailMap.pledgeBargainId}</td>
	       		<th>客户号</th>
	       		<td>${guarantyDetailMap.customerId}</td>
	       	</tr>
       		<tr>
	       		<th>抵押类型</th>
	       		<td>
	       			<c:if test="${guarantyDetailMap.pledgeFromWhere==1}">
					       运输工具、设备信息
					</c:if>
					<c:if test="${guarantyDetailMap.pledgeFromWhere==2}">
						房地产明细信息
					</c:if>
					<c:if test="${guarantyDetailMap.pledgeFromWhere==3}">
						其它抵押物信息
					</c:if></td>
	       		<th>抵押物名称</th>
	       		<td>${guarantyDetailMap.pleName}</td>
	       	</tr>
       		<tr>
	       		<th>抵押物价值</th>
	       		<td>${guarantyDetailMap.evaMoney}</td>
	       		<th>权证号（或车牌号）</th>
	       		<td>${guarantyDetailMap.licenceCar}</td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>