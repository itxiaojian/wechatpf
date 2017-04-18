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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>农户信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center">
       
	       	<tr>
	       		<th>客户号</th>
	       		<td>${details.CUSTOMER_ID}</td>
	       		<th>贷款证号码</th>
	       		<td>${details.LOAN_CARD}</td>
	       	</tr>
	       	<tr>
	       		<th>核定贷款额度</th>
	       		<td>${details.LOAN_LINE}</td>
	       		<th>家庭人口数</th>
	       		<td>${details.PERSON_NUMBER}</td>
	       	</tr>
	       	<tr>
	       		<th>劳动力人数</th>
	       		<td>${details.POWER_PERSON_NUMBER}</td>
	       		<th>农户经营类型</th>
	       		<td>${details.OPERATION_TYPE}</td>
	       	</tr>
	       	<tr>
	       		<th>兼营产业名称</th>
	       		<td>${details.OTHER_OPER_NAME}</td>
	       		<th>行业分类</th>
	       		<td>${details.TRADE_NAME}</td>
	       	</tr>
	       	<tr>
	       		<th>个人品行</th>
	       		<td>${details.PERSON_MORAL}</td>
	       		<th>备注</th>
	       		<td>${details.REMARK}</td>
	       	</tr>
       		<tr>
	       		<th>机构号</th>
	       		<td>${details.UNIT}</td>
	       		<th>柜员号</th>
	       		<td>${details.USER_ID}</td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>