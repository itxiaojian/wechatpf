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
		width:20%;
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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>其他客户信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center">
	       	<tr>
	       		<th>客户号</th>
	       		<td>${details.CUSTOMER_ID}</td>
	       		<th>贷款证状态</th>
	       		<td>${details.LOANPAPER_STATE}</td>
	       	</tr>
	       	<tr>
	       		<th>贷款证注销日期</th>
	       		<td>${details.CLEAR_DATE}</td>
	       		<th>职业</th>
	       		<td>${details.JOB}</td>
	       	</tr>
       		<tr>
	       		<th>人员编制</th>
	       		<td>${details.PERSON_NUMMBER}</td>
	       		<th>职业资格证号码</th>
	       		<td>${details.PAPER_ID}</td>
	       	</tr>
       		<tr>
	       		<th>工作单位名称</th>
	       		<td>${details.JOB_UNIT_NAME}</td>
	       		<th>本单位工作起始年份</th>
	       		<td>${details.START_YEAR}</td>
	       	</tr>
       		<tr>
	       		<th>职务</th>
	       		<td>${details.DUTY}</td>
	       		<th>职称</th>
	       		<td>${details.PLACE}</td>
	       	</tr>
       		<tr>
	       		<th>工资账号</th>
	       		<td>${details.PAY_ACCOUNT}</td>
	       		<th>工资账户开户银行</th>
	       		<td>${details.PAY_BANK}</td>
	       	</tr>
	       	<tr>
	       		<th>单位地址</th>
	       		<td>${details.UNIT_ADDR}</td>
	       		<th>单位地址邮政编码</th>
	       		<td>${details.BRANCH_POST_CODE}</td>
	       	</tr>
	       	<tr>
	       		<th>单位电话</th>
	       		<td>${details.TEL_CONTACT}</td>
	       		<th>单位所属行业</th>
	       		<td>${details.TRADE_TYPE}</td>
	       	</tr>
	       	<tr>
	       		<th>单位效益评价</th>
	       		<td colspan="3">${details.BENEFIT_VALUE}</td>
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