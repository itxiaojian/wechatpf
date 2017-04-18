<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>  
    <link href="/resources/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/resources/js/jquery/jquery.min.js"></script>
    
    <script type="text/javascript" src="/resources/js/cps/webSign/webSign.js"></script>
  
    <style type="text/css">
		.thbg {
				padding: 6px 12px;
				background-color: #eeeeee;	
		}   
		.optionContentSignClass{
			height:165px;
			width:250px;
		} 
		.optionContentClass{
			width:250px;
		}	
    </style>
   </head> 
  <body onload="showWebSign()">
  	<object id=DWebSignSeal
		classid='CLSID:77709A87-71F9-41AE-904F-886976F99E3E'
		style='position: absolute; width: 0px; height: 0px; left: 0px; top: 0px;'
		codebase='/resources/webSign/WebSign.dll#version=4,1,0,0'> 
	</object>

	  <!-- 贷款业务数据 -->
	<div class="panel panel-success">
	  <div class="panel-heading">申请信息:</div>
		  <table class="table table-bordered">
		  		<tr>
		  			<th class="thbg">客户号</th><td>${mapBusi.customerId}</td><th  class="thbg">客户名称</th><td><a onclick="viewCreditPdf();" title="点击查看授信档案">${mapBusi.custName}</a></td><th  class="thbg">所属支行</th><td>${mapBusi.orgName}</td>
		  		</tr>
		  		<tr>
		  			<th  class="thbg">授信形式</th><td>${mapBusi.creditModeName}</td><th  class="thbg">授信产品</th><td>${mapBusi.productName}</td><th  class="thbg">申请授信总额(元)</th><td>${mapBusi.applySum}</td>  		
		  		</tr>
		  		<tr>
		  			<th  class="thbg">申请时间</th><td>${mapBusi.createTime}</td><th  class="thbg">申请利率浮动(%)</th><td>${mapBusi.applyRate}</td><th  class="thbg">申请授信年限(年)</th><td>${mapBusi.applyYear}</td>
		  		</tr>	
		  		<tr>
		  			<th  class="thbg">贷款用途</th><td colspan='5'>${mapBusi.creditPurpose}</td>
		  		</tr>	
		  		<tr>
		  			<th  class="thbg">备注</th><td colspan='5'>${mapBusi.remark}</td>
		  		</tr>		  		
		  </table>
	</div>	  
	
  	<!--历史审批意见-->
	<div class="panel panel-success">
	  <!-- Default panel contents -->
	  <div class="panel-heading">历史审批意见:</div>	
	  <!-- Table -->
		<table class="table table-striped table-bordered">
			<tr>
					<th style="width:120px">环节</th>
					<th width="80">审批人</th>
					<th width="120">审批动作</th>
					<th width="120">审批意见</th>
					<th width="100">同意授信金额</th>
					<th width="100">同意授信利率</th>
					<th width="100">同意授信年限</th>
					<th width="140">审批时间</th>
			</tr>			
			<c:forEach var="data" items="${listZHSCZXOption}" varStatus="status"> 
				<tr>
					<td>${data.actName}</td>
					<td>${data.userName}</td>
					<td>${data.optionName}</td>
					<td id="optionContentId${data.opinionId}"                 	 	
						<c:if test="${data.signData == ''}">
							class="optionContentClass"
						</c:if>
						<c:if test="${data.signData != ''}">
							class="optionContentSignClass"
						</c:if>
					>${data.optionContent}</td>
					<td>${data.extra1}</td>
					<td>${data.extra2}</td>
					<td>${data.extra3}</td>
					<td>${data.approveTime}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
  </body>
</html>
<script type="text/javascript">
	var userCode = "${userCode}";
	var taskId = "${taskId}";
	var creditId = ${businessKey};
	var optionJson = ${optionJson};
	
	//点击客户姓名打开授信PDF档案显示页面
	function viewCreditPdf() {
		window.open("/cps/pdf/viewPdf?flag=0&creditId="+creditId);
	}
</script>