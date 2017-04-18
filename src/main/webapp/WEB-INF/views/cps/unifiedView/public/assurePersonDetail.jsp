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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>高管人员信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
	       	<tr>
	       		<th>客户号</th>
	       		<td>${assurePersonDetails.CUSTOMER_ID}</td>
	       		<th>客户名称</th>
	       		<td>${assurePersonDetails.CUST_NAME}</td>
	       	</tr>
       		<tr>
	       		<th>保证人客户号</th>
	       		<td>${assurePersonDetails.ASSURE_NO}</td>
	       		<th>借款合同号</th>
	       		<td>${assurePersonDetails.BORROW_NO}</td>
	       	</tr>
       		<tr>
	       		<th>保证人证件类型</th>
	       		<td>${assurePersonDetails.CERTIFICATE_TYPE}</td>
	       		<th>保证人证件号码</th>
	       		<td>${assurePersonDetails.CERTIFICATE_NO}</td>
	       	</tr>
       		<tr>
	       		<th>保证人名称</th>
	       		<td>${assurePersonDetails.ASSURE_NAME}</td>
	       		<th>保证人与客户关系</th>
	       		<td>${assurePersonDetails.ASSURE_RELATION}</td>
	       	</tr>
       		<tr>
	       		<th>保证人客户类型</th>
	       		<td>${assurePersonDetails.ASSURE_TYPE}</td>
	       		<th>最高额保证合同或协议号</th>
	       		<td>${assurePersonDetails.CONTRACT_NO}</td>
	       	</tr>
       		<tr>
	       		<th>最高额保证贷款金额</th>
	       		<td>${assurePersonDetails.LOAN_HIGHEST}</td>
	       		<th>合同签定日期</th>
	       		<td>${assurePersonDetails.CONTRACT_DATE}</td>
	       	</tr>
       		<tr>
	       		<th>创建人</th>
	       		<td>${assurePersonDetails.CREATE_USER}</td>
	       		<th>创建时间</th>
	       		<td>${assurePersonDetails.CREATE_TIME}</td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>