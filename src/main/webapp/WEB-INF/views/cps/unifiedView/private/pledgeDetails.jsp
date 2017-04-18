<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>    
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap.min.css" /> 
  	<script type="text/javascript">
  		function showPledgehouse(rowId,pledgeFromWhere){
  			alert(rowId+":"+pledgeFromWhere);
  		}
  	</script>
  	
	<style type="text/css">
	th {
		padding: 6px 12px;
		width:25%;
		background-color: #eeeeee;
	}
	td {
		padding: 6px 12px;
		width:15%;
	}
	</style>
  </head>
  <body>
  
  	 <div class="container">

      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>抵质押物信息</b></font></h4>
      <table class="table-striped table-bordered"  align="center" width="90%">
       <c:choose>  
		   <c:when test="${not empty equip}">    
		   			<tr>
			       		<th>抵质押合同号</th>
			       		<td>${equip.pledgeBargainId}</td>
			       		<th>抵质押品编号</th>
			       		<td>${equip.pledgeId}</td>
			       	</tr>
			       	<tr>
			       		<th>客户号</th>
			       		<td>${equip.customerId}</td>
			       		<th>发动机号码或机身号</th>
			       		<td>${equip.engineId}</td>
			       	</tr>
			       	<tr>
			       		<th>抵押物种类</th>
			       		<td>${equip.pledgeType}</td>
			       		<th>抵押物名称</th>
			       		<td>${equip.pledgeName}</td>
			       	</tr>
			       	<tr>
			       		<th>所有权(使用权)证书号</th>
			       		<td>${equip.licenceId}</td>
			       		<th>车牌号</th>
			       		<td>${equip.licenceCar}</td>
			       	</tr>
			       	<tr>
			       		<th>原值</th>
			       		<td>${equip.pledgeFirstMoney}</td>
			       		<th>抵押物评估价值</th>
			       		<td>${equip.evaluateMoney}</td>
			       	</tr>
			       	<tr>
			       		<th>抵押人</th>
			       		<td>${equip.pledgorName}</td>
			       		<th>保险单编号</th>
			       		<td>${equip.insuranceId}</td>
			       	</tr>
		       	 	<tr>
			       		<th>抵押物共有人</th>
			       		<td>${equip.partOwner}</td>
			       		<th>价值评估时间</th>
			       		<td>${equip.evaluateDate}</td>
			       	</tr>
		       	 	<tr>
			       		<th>评估有效截止日期</th>
			       		<td>${equip.evaluateEndDate}</td>
			       		<th>机构号</th>
			       		<td>${equip.unit}</td>
			       	</tr>
		       	 	<tr>
			       		<th>柜员号</th>
			       		<td>${equip.userId}</td>
			       		<th></th>
			       		<td></td>
			       	</tr>
		   </c:when>  
		    <c:when test="${not empty house}">      
	   			 	<tr>
			       		<th>抵质押合同号</th>
			       		<td>${house.pledgeBargainId}</td>
			       		<th>抵质押品编号</th>
			       		<td>${house.pledgeId}</td>
			       	</tr>
			       	<tr>
			       		<th>客户号</th>
			       		<td>${house.customerId}</td>
			       		<th>借款合同号</th>
			       		<td>${house.loanBargainId}</td>
			       	</tr>
			       	<tr>
			       		<th>抵押物种类</th>
			       		<td>${house.pledgeType}</td>
			       		<th>抵押物名称</th>
			       		<td>${house.realtyName}</td>
			       	</tr>
			       	<tr>
			       		<th>所有权(使用权)证书号</th>
			       		<td>${house.ownershipId}</td>
			       		<th>他项专利证书号</th>
			       		<td>${house.houseCertificateId}</td>
			       	</tr>
			       	<tr>
			       		<th>原值</th>
			       		<td>${house.buyValue}</td>
			       		<th>抵押物评估价值</th>
			       		<td>${house.evaluateMoney}</td>
			       	</tr>
			       	<tr>
			       		<th>抵押人</th>
			       		<td>${house.pledgorName}</td>
			       		<th>保险单编号</th>
			       		<td>${house.insureId}</td>
			       	</tr>
		       	 	<tr>
			       		<th>抵押物共有人</th>
			       		<td>${house.partOwner}</td>
			       		<th>抵押登记日期</th>
			       		<td>${house.inputDate}</td>
			       	</tr>
		       	 	<tr>
			       		<th>抵押登记注销日期</th>
			       		<td>${house.clearDate}</td>
			       		<th>机构号</th>
			       		<td>${house.unit}</td>
			       	</tr>
		       	 	<tr>
			       		<th>柜员号</th>
			       		<td>${house.userId}</td>
			       		<th></th>
			       		<td></td>
			       	</tr>
		   </c:when>
		  <c:when test="${not empty other}">      
		   			<tr>
			       		<th>抵质押合同号</th>
			       		<td>${other.pledgeBargainId}</td>
			       		<th>抵质押品编号</th>
			       		<td>${other.pledgeId}</td>
			       	</tr>
			       	<tr>
			       		<th>客户号</th>
			       		<td>${other.customerId}</td>
			       		<th>借款合同号</th>
			       		<td>${other.loanBargainId}</td>
			       	</tr>
			       	<tr>
			       		<th>抵押物种类</th>
			       		<td>${other.pledgeType}</td>
			       		<th>抵押物名称</th>
			       		<td>${other.pledgeName}</td>
			       	</tr>
			       	<tr>
			       		<th>所有权(使用权)证书号</th>
			       		<td>${other.pledgeOwnerId}</td>
			       		<th>合格(许可)证号</th>
			       		<td>${other.licenceId}</td>
			       	</tr>
			       	<tr>
			       		<th>原值</th>
			       		<td>${other.pledgeFirstMoney}</td>
			       		<th>抵押物评估价值</th>
			       		<td>${other.evaluateMoney}</td>
			       	</tr>
			       	<tr>
			       		<th>抵押人</th>
			       		<td>${other.pledgorName}</td>
			       		<th>保险单编号</th>
			       		<td>${other.insuranceId}</td>
			       	</tr>
		       	 	<tr>
			       		<th>抵押物共有人</th>
			       		<td>${other.partOwner}</td>
			       		<th>合同签订日期</th>
			       		<td>${other.inputDate}</td>
			       	</tr>
		       	 	<tr>
			       		<th>注销日期</th>
			       		<td>${other.clearDate}</td>
			       		<th>机构号</th>
			       		<td>${other.unit}</td>
			       	</tr>
		       	 	<tr>
			       		<th>柜员号</th>
			       		<td>${other.userId}</td>
			       		<th></th>
			       		<td></td>
			       	</tr>
		   </c:when>
		</c:choose> 
	       	
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>