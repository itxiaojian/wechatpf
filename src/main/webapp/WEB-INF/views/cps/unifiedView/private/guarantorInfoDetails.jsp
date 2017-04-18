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
		width:25%;
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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>保证人信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center">
	       	<tr>
	       		<th>保证人客户号</th>
	       		<td>${details.assureCustomerId}</td>
	       		<th>申请编号</th>
	       		<td>${details.applyId}</td>
	       	</tr>
       		<tr>
	       		<th>客户号</th>
	       		<td>${details.customerId}</td>
	       		<th>保证人证件类型</th>
	       		<td>${details.paperType}</td>
	       	</tr>
      		<tr>
	       		<th>保证人证件号码</th>
	       		<td>${details.idCard}</td>
	       		<th>保证人名称</th>
	       		<td>${details.assurerName}</td>
	       	</tr>
       		<tr>
	       		<th>保证人与客户关系</th>
	       		<td>${details.releationAssurerCust}</td>
	       		<th>保证人客户类型</th>
	       		<td>${details.assureCustType}</td>
	       	</tr>
       		<tr>
	       		<th>最高额保证合同或协议号</th>
	       		<td>${details.mostConitractId}</td>
	       		<th>最高额保证贷款金额</th>
	       		<td>${details.mostAssureAmount}</td>
	       	</tr>
       		<tr>
	       		<th>机构号</th>
	       		<td>${details.unit}</td>
	       		<th>柜员号</th>
	       		<td>${details.userId}</td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>