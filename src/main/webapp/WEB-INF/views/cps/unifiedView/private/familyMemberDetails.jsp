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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>家庭主要成员情况</b></font></h4>
       <table class="table-striped table-bordered"  align="center">
	       	<tr>
	       		<th>客户号</th>
	       		<td>${memberDetails.customerId}</td>
	       		<th>家庭主要成员客户号</th>
	       		<td>${memberDetails.familyCustomerId}</td>
	       	</tr>
	       	<tr>
	       		<th>姓名</th>
	       		<td>${memberDetails.custName}</td>
	       		<th>英文名称</th>
	       		<td>${memberDetails.foreignName}</td>
	       	</tr>
	       	<tr>
	       		<th>证件类型</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${memberDetails.paperType =='01'}">身份证</c:when>
	       				<c:when test="${memberDetails.paperType =='02'}">户口簿</c:when>
	       				<c:when test="${memberDetails.paperType =='03'}">军官证</c:when>
	       				<c:when test="${memberDetails.paperType =='04'}">警官证</c:when>
	       				<c:when test="${memberDetails.paperType =='05'}">士兵证</c:when>
	       				<c:when test="${memberDetails.paperType =='06'}">文职干部证</c:when>
	       				<c:when test="${memberDetails.paperType =='07'}">护照</c:when>
	       				<c:when test="${memberDetails.paperType =='08'}">港澳台居民来往内地通行证</c:when>
	       				<c:when test="${memberDetails.paperType =='09'}">其他个人有效证件</c:when>
	       				<c:when test="${memberDetails.paperType =='60'}">营业执照</c:when>
	       				<c:when test="${memberDetails.paperType =='66'}">营业执照</c:when>
	       				<c:otherwise>${memberDetails.paperType}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>证件号码</th>
	       		<td>${memberDetails.idCard}</td>
	       	</tr>
	       	<tr>
	       		<th>性别</th>
	       		<td>
	       			<c:choose>  
					   <c:when test="${memberDetails.sex =='0'}">男</c:when>  
					   <c:when test="${memberDetails.sex =='1'}">女</c:when>
					   <c:when test="${memberDetails.sex =='2'}">未知</c:when>
					   <c:otherwise>${memberDetails.sex}</c:otherwise>
					</c:choose> 
	       		</td>
	       		<th>居住地址</th>
	       		<td>${memberDetails.homeAddr}</td>
	       	</tr>
	       	<tr>
	       		<th>职业</th>
	       		<td>
	       			<c:choose>  
					   <c:when test="${memberDetails.job =='0'}">国家机关、党群组织、企业、事业单位负责人</c:when>  
					   <c:when test="${memberDetails.job =='1'}">专业技术人员</c:when>
					   <c:when test="${memberDetails.job =='3'}">办事人员和有关人员</c:when>  
					   <c:when test="${memberDetails.job =='4'}">商业、服务业人员</c:when>
					   <c:when test="${memberDetails.job =='5'}">农、林、牧、渔、水利业生产人员</c:when>
					   <c:when test="${memberDetails.job =='6'}">生产、运输设备操作人员及有关人员</c:when>  
					   <c:when test="${memberDetails.job =='X'}">军人</c:when>
					   <c:when test="${memberDetails.job =='Y'}">不便分类的其他从业人员</c:when>
					   <c:when test="${memberDetails.job =='Z'}">未知。</c:when>
					   <c:otherwise>${memberDetails.job}</c:otherwise>
					</c:choose> 
	       		</td>
	       		<th>人员编制</th>
	       		<td>
	       			<c:choose>  
					   <c:when test="${memberDetails.personNumber =='01'}">正式员工</c:when>  
					   <c:when test="${memberDetails.personNumber =='02'}">临时工</c:when>
					   <c:when test="${memberDetails.personNumber =='03'}">工勤人员</c:when>  
					   <c:when test="${memberDetails.personNumber =='04'}">待岗</c:when>
					   <c:when test="${memberDetails.personNumber =='05'}">下岗</c:when>
					   <c:when test="${memberDetails.personNumber =='06'}">其他</c:when>  
					   <c:otherwise>${memberDetails.personNumber}</c:otherwise>
					</c:choose> 
	       		</td>
	       	</tr>
	       	
	       	<tr>
	       		<th>工作单位名称</th>
	       		<td>${memberDetails.jobUnitName}</td>
	       		<th>工作单位地址</th>
	       		<td>${memberDetails.addrUnit}</td>
	       	</tr>
	       	<tr>
	       		<th>职务</th>
	       		<td>${memberDetails.duty}</td>
	       		<th>联系电话</th>
	       		<td>${memberDetails.telContact}</td>
	       	</tr>
       		<tr>
	       		<th>与客户关系</th>
	       		<td colspan="3">
	       			<c:choose>  
					   <c:when test="${memberDetails.relationToCustomer =='01'}">夫妻</c:when>  
					   <c:when test="${memberDetails.relationToCustomer =='02'}">子女</c:when>
					   <c:when test="${memberDetails.relationToCustomer =='03'}">兄弟姐妹</c:when>  
					   <c:when test="${memberDetails.relationToCustomer =='04'}">其他</c:when>
					   <c:when test="${memberDetails.relationToCustomer =='05'}">父母</c:when>
					   <c:when test="${memberDetails.relationToCustomer =='06'}">其他血缘</c:when>  
					   <c:when test="${memberDetails.relationToCustomer =='07'}">其他姻亲</c:when>  
					   <c:otherwise>${memberDetails.relationToCustomer}</c:otherwise>
					</c:choose> 
	       		</td>
	       	</tr>
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>