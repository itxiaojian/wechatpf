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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>业务汇总信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
	       	<tr>
	       		<th>客户号</th>
	       		<td></td>
	       		<th>信用贷款余额</th>
	       		<td></td>
	       		<th>信用贷款笔数</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>抵押贷款余额</th>
	       		<td></td>
	       		<th>抵押贷款笔数</th>
	       		<td></td>
	       		<th>保证贷款余额</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>保证贷款笔数</th>
	       		<td></td>
	       		<th>质押贷款余额</th>
	       		<td></td>
	       		<th>质押贷款笔数</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>贷款四级分类正常状态余额</th>
	       		<td></td>
	       		<th>贷款四级分类正常状态笔数</th>
	       		<td></td>
	       		<th>贷款四级分类逾期状态余额</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>贷款四级分类逾期状态笔数</th>
	       		<td></td>
	       		<th>贷款四级分类呆滞状态余额</th>
	       		<td></td>
	       		<th>贷款四级分类呆滞状态笔数</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>四级分类呆账贷款余额</th>
	       		<td></td>
	       		<th>四级分类呆账贷款笔数</th>
	       		<td></td>
	       		<th>五级分类正常贷款余额</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>五级分类正常贷款笔数</th>
	       		<td></td>
	       		<th>五级分类关注贷款余额</th>
	       		<td></td>
	       		<th>五级分类关注贷款笔数</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>五级分类次级贷款余额</th>
	       		<td></td>
	       		<th>五级分类次级贷款笔数</th>
	       		<td></td>
	       		<th>五级分类可疑贷款余额</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>五级分类可疑贷款笔数</th>
	       		<td></td>
	       		<th>五级分类损失贷款余额</th>
	       		<td></td>
	       		<th>五级分类损失贷款笔数</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>累计承兑余额</th>
	       		<td></td>
	       		<th>累计承兑笔数</th>
	       		<td></td>
	       		<th>贴现金额</th>
	       		<td></td>
	       	</tr>
       	  	<tr>
	       		<th>贴现笔数</th>
	       		<td></td>
	       		<th>统计日期</th>
	       		<td></td>
	       		<th>存款余额</th>
	       		<td></td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>