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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>企业客户统计信息登记表</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
	       	<tr>
	       		<th>客户号</th>
	       		<td>${company.customerId}</td>
	       		<th>客户名称</th>
	       		<td>${company.custName}</td>
	       	</tr>
	       	<tr>
	       		<th>行业分类</th>
	       		<td>${company.tradeType}</td>
	       		<th>企业状态</th>
	       		<td>${company.chargeUnits}</td>
	       	</tr>
	       	<tr>
	       		<th>企业控股类型</th>
	       		<td>${company.corporation}</td>
	       		<th>从业人数</th>
	       		<td>${company.baseAccountBank}</td>
	       	</tr>
       		<tr>
	       		<th>报表日期</th>
	       		<td>${company.postcode}</td>
	       		<th>维护日期</th>
	       		<td>${company.email}</td>
	       	</tr>
       		<tr>
	       		<th>资产总额</th>
	       		<td>${company.adminArea}</td>
	       		<th>营业收入</th>
	       		<td>${company.urlUnits}</td>
	       	</tr>
       		<tr>
       			<th>实收资本</th>
	       		<td>${company.telWrok}</td>
	       		<th>柜员号</th>
	       		<td>${company.telContact}</td>
	       	</tr>
	       	<tr>
	       		<th>机构号</th>
	       		<td>${company.fax}</td>
	       	</tr>
       </table>
      </form>
      
    </div> <!-- /container -->
  </body>
</html>