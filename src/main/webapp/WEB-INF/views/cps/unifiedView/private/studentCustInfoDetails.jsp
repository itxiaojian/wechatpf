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
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>助学客户信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
	       	<tr>
	       		<th>客户号</th>
	       		<td>${details.CUSTOMER_ID}</td>
	       		<th>证件类型</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${details.PAPER_TYPE =='01'}">身份证</c:when>
	       				<c:when test="${details.PAPER_TYPE =='02'}">户口簿</c:when>
	       				<c:when test="${details.PAPER_TYPE =='03'}">军官证</c:when>
	       				<c:when test="${details.PAPER_TYPE =='04'}">警官证</c:when>
	       				<c:when test="${details.PAPER_TYPE =='05'}">士兵证</c:when>
	       				<c:when test="${details.PAPER_TYPE =='06'}">文职干部证</c:when>
	       				<c:when test="${details.PAPER_TYPE =='07'}">护照</c:when>
	       				<c:when test="${details.PAPER_TYPE =='08'}">港澳台居民来往内地通行证</c:when>
	       				<c:when test="${details.PAPER_TYPE =='09'}">其他个人有效证件</c:when>
	       				<c:when test="${details.PAPER_TYPE =='60'}">营业执照</c:when>
	       				<c:when test="${details.PAPER_TYPE =='66'}">营业执照</c:when>
	       				<c:otherwise>${details.PAPER_TYPE}</c:otherwise>
	       			</c:choose>
	       		
	       		</td>
	       	</tr>
       		<tr>
	       		<th>证件号码</th>
	       		<td>${details.ID_CARD}</td>
	       		<th>学生姓名</th>
	       		<td>${details.STUDENT_NAME}</td>
	       	</tr>
       		<tr>
	       		<th>英文名称</th>
	       		<td>${details.FOREIGN_NAME}</td>
	       		<th>存款帐号</th>
	       		<td>${details.ACCOUNT}</td>
	       	</tr>
       		<tr>
	       		<th>存款开户行</th>
	       		<td>${details.ACCOUNT_BANK}</td>
	       		<th>学生与客户关系</th>
	       		<td>${details.RELATION_TO_CUSTOMER}</td>
	       	</tr>
       		<tr>
	       		<th>录取通知书编号</th>
	       		<td>${details.MATR_NOTICE_CODE}</td>
	       		<th>学校名称</th>
	       		<td>${details.SCHOOL_NAME}</td>
	       	</tr>
       		<tr>
	       		<th>学校地址</th>
	       		<td>${details.ADDR_SCHOOL}</td>
	       		<th>学校性质</th>
	       		<td>${details.SCHOOL_TYPE}</td>
	       	</tr>
       		<tr>
	       		<th>学制</th>
	       		<td>${details.LENGTH_OF_SCHOOLING}</td>
	       		<th>专业</th>
	       		<td>${details.SPECIALTY}</td>
	       	</tr>
       		<tr>
	       		<th>在读学历</th>
	       		<td>${details.SCHOOL_AGE}</td>
	       		<th>在读学位</th>
	       		<td>${details.DEGREE}</td>
	       	</tr>
       		<tr>
	       		<th>学号</th>
	       		<td>${details.STUDENT_ID}</td>
	       		<th>班级</th>
	       		<td>${details.CLASS_ID}</td>
	       	</tr>
       		<tr>
	       		<th>学生证号码</th>
	       		<td>${details.STUDENT_PAPER}</td>
	       		<th>联系电话</th>
	       		<td>${details.TEL_CONTACT}</td>
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