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
       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>贷款信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
	       	<tr>
	       		<th>账号</th>
	       		<td>${details.ZH}</td>
	       		<th>账号属性</th>
	       		<td>${details.ZHSX}</td>
	       	</tr>
       		<tr>
	       		<th>客户号</th>
	       		<td>${details.KHH}</td>
	       		<th>客户名称</th>
	       		<td>${details.KHMC}</td>
	       	</tr>
	      
	       	<tr>
	       		<th>账号状态</th>
	       		<td>${details.JLZT}</td>
	       		<th>机构代号</th>
	       		<td>${details.JGDH}</td>
	       	</tr>
	       	 <tr>
	       		<th>贷款账号</th>
	       		<td>${details.DKZH}</td>
	       		<th>还本金额</th>
	       		<td>${details.HBJE}</td>
	       	</tr>
	       	<tr>
	       		<th>还息金额</th>
	       		<td>${details.HXJE}</td>
	       		<th>交易日期</th>
	       		<td>${details.JYRQ}</td>
	       	</tr>
	       	<tr>
	       		<th>上次计息日</th>
	       		<td>${details.SCJXR}</td>
	       		<th>柜员流水号</th>
	       		<td colspan=2>${details.GYLSH}</td>
	       		
	       	</tr>
	       	<tr>
	       		<th>柜员号</th>
	       		<td>${details.GYH}</td>
	       		<th>柜员名称</th>
	       		<td colspan=2>${details.GYMC}</td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>