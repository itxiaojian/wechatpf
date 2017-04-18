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
	       		<th>币种</th>
	       		<td>
	       		<c:choose>  
					   <c:when test="${details.BZ =='01'}">人民币(元)</c:when>  
					   <c:when test="${details.BZ =='02'}">英镑</c:when>
					   <c:when test="${details.BZ =='03'}">港币(元)</c:when>
					   <c:when test="${details.BZ =='04'}">美元</c:when>  
					   <c:when test="${details.BZ =='05'}">瑞士法郎</c:when>
					   <c:when test="${details.BZ =='06'}">日元</c:when>
					   <c:when test="${details.BZ =='07'}">加拿大元</c:when>  
					   <c:when test="${details.BZ =='08'}">澳元</c:when>
					   <c:when test="${details.BZ =='09'}">欧元</c:when>
					   <c:otherwise>${details.BZ}</c:otherwise>
					   
					</c:choose> 
	       		</td>
	       	</tr>
       		<tr>
	       		<th>产品号</th>
	       		<td>${details.CPH}</td>
	       		<th>机构代号</th>
	       		<td>${details.JGDH}</td>
	       	</tr>
	       	<tr>
	       		<th>科目名称</th>
	       		<td>${details.KMMC}</td>
	       		<th>科目号</th>
	       		<td>${details.KMH}</td>
	       	</tr>
	       	<tr>
	       		<th>客户号</th>
	       		<td>${details.KHH}</td>
	       		<th>客户名称</th>
	       		<td>${details.KHMC}</td>
	       	</tr>
	       	<tr>
	       		<th>开户日期</th>
	       		<td>${details.KHRQ}</td>
	       		<th> 销户日期</th>
	       		<td>${details.XHRQ}</td>
	       	</tr>
	       	<tr>
	       		<th>账户状态</th>
	       		<td>${details.ZHZT}</td>
	       		<th>账户属性</th>
	       		<td>${details.ZHSX}</td>
	       	</tr>
       		<tr>
	       		<th>账户余额</th>
	       		<td >${details.ZHYE}</td>
	       	</tr>
	       	<tr>
	       		<th>柜员号</th>
	       		<td>${details.GYH}</td>
	       		<th>柜员名称</th>
	       		<td>${details.GYMC}</td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>