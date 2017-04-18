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
       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>关联人信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
	       	<tr>
	       		<th>客户号</th>
	       		<td>${details.CUSTOMER_ID}</td>
	       		<th>证件类型</th>
	       		<td>${details.PAPER_TYPE}</td>
	       	</tr>
	       	<tr>
	       		<th>证件号码</th>
	       		<td>${details.ID_CARD}</td>
	       		<th>关联人与客户关系</th>
	       		<td>${details.RELATIONSHIP_CUSTOMER_ID}</td>
	       	</tr>
       	 	<tr>
	       		<th>关联人名称</th>
	       		<td>${details.RELATIONSHIP_NAME}</td>
	       		<th>英文名称</th>
	       		<td>${details.FOREIGN_NAME}</td>
	       	</tr>
       	 	<tr>
	       		<th>机构号</th>
	       		<td>${details.UNIT_ID}</td>
	       		<th>柜员号</th>
	       		<td>${details.USER_ID}</td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>