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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>本行贷款业务信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
			<th>序号</th>
			<th>序列号</th>
			<th>机构名称</th>
			<th>放款金额</th>
			<th>贷款余额</th>
			<th>保证人信息</th>
			<th>抵押物信息</th>
			<th>质押物信息</th>
			<th>贷款信息</th>
		</tr>	
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>