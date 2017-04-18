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
	       		<td>${managementDetails.CUSTOMER_ID}</td>
	       		<th>证件类型</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${managementDetails.PAPER_TYPE =='0'}">身份证</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='1'}">户口簿</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='2'}">护照</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='3'}">军官证</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='4'}">士兵证</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='5'}">港澳居民来往内地通行证</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='6'}">台湾同胞来往内地通行证</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='7'}">临时身份证</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='8'}">外国人居留证</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='9'}">警官证</c:when>
	       				<c:when test="${managementDetails.PAPER_TYPE =='X'}">其他证件</c:when>
	       				<c:otherwise>${managementDetails.PAPER_TYPE}</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
       		<tr>
	       		<th>证件号码</th>
	       		<td>${managementDetails.ID_CARD}</td>
	       		<th>高管人员客户号</th>
	       		<td>${managementDetails.PERSON_CUSTOMER_ID}</td>
	       	</tr>
       		<tr>
	       		<th>名称</th>
	       		<td>${managementDetails.CUST_NAME}</td>
	       		<th>性别</th>
	       		<td>
	       			<c:choose>  
					   <c:when test="${managementDetails.SEX =='0'}">男</c:when>  
					   <c:when test="${managementDetails.SEX =='1'}">女</c:when>
					   <c:when test="${managementDetails.SEX =='2'}">未知</c:when>
					   <c:otherwise>${managementDetails.SEX}</c:otherwise>
					</c:choose> 
	       		</td>
	       	</tr>
       		<tr>
	       		<th>职务</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${managementDetails.DUTY =='1'}">高级领导</c:when>  
					   	<c:when test="${managementDetails.DUTY =='2'}">中级领导</c:when>
					   	<c:when test="${managementDetails.DUTY =='3'}">一般员工</c:when>
					   	<c:when test="${managementDetails.DUTY =='4'}">其他</c:when>
					   	<c:when test="${managementDetails.DUTY =='X'}">未知</c:when>
					   	<c:otherwise>${managementDetails.DUTY}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>任职年份</th>
	       		<td>${managementDetails.DUTY_YEAR}</td>
	       	</tr>
       		<tr>
	       		<th>高管人员类别</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${managementDetails.CUST_PERSON_TYPE =='1'}">董事长</c:when>
	       				<c:when test="${managementDetails.CUST_PERSON_TYPE =='2'}">总经理</c:when>
	       				<c:when test="${managementDetails.CUST_PERSON_TYPE =='3'}">财务负责人</c:when>
	       				<c:when test="${managementDetails.CUST_PERSON_TYPE =='X'}">其他</c:when>
	       				<c:otherwise>${managementDetails.CUST_PERSON_TYPE}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>高管生日</th>
	       		<td>${managementDetails.PERSON_BIRTHDAY}</td>
	       	</tr>
       		<tr>
	       		<th>高管最高学历</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${managementDetails.EDU_LEV =='10'}">研究生</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='20'}">大学本科（简称大学）</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='30'}">大学专科和专科学校（简称大专）</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='40'}">中等专业学校或中等技术学校</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='50'}">技术学校</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='60'}">高中</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='70'}">初中</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='80'}">小学</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='90'}">文盲或半文盲</c:when>
	       				<c:when test="${managementDetails.EDU_LEV =='99'}">未知</c:when>
	       				<c:otherwise>${managementDetails.EDU_LEV}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>机构号</th>
	       		<td>${managementDetails.UNIT}</td>
	       	</tr>
       		<tr>
	       		<th>柜员号</th>
	       		<td>${managementDetails.USER_ID}</td>
	       	</tr>
       </table>
      </form>
      <br>
      <br>
      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>高管人员履历信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
			<th>序号</th>
			<th>任职单位名称</th>
			<th>任职单位地址</th>
			<th>所任职务</th>
			<th>操作</th>
		</tr>	
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>