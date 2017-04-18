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
    <script type="text/javascript" src="/resources/js/jquery/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/resources/js/cps/webSign/webSign.js"></script>
    <script type="text/javascript" src="/resources/js/cps/checkData.js"></script>
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
		
		a {
			cursor:pointer;
		}	
    </style>
   </head> 
  <body onload="showWebSign()">
  	<?xml version="1.0" encoding="iso-8859-1"?>  
	<SCRIPT language=javaScript src="/resources/js/cps/webSign/webSignObject.js"> </SCRIPT>
	<!-- 贷款业务数据 -->
	<div class="panel panel-success">
		<div class="panel-heading">申请信息:</div>
		<table class="table table-bordered">
			<tr>
				<th class="thbg">客户号</th>
				<td>${mapBusi.customerId}</td>
				<th class="thbg">客户名称</th>
				<td><a onclick="viewCreditPdf();" title="点击查看授信档案">${mapBusi.custName}</a></td>
				<th class="thbg">所属支行</th>
				<td>${mapBusi.orgName}</td>
			</tr>
			<tr>
				<th class="thbg">担保方式</th>
				<td>${mapBusi.creditModeName}</td>
				<th class="thbg">授信产品</th>
				<td>${mapBusi.productName}</td>
				<th class="thbg">申请授信总额(元)</th>
				<td>${mapBusi.applySum}</td>
			</tr>
			<tr>
				<th class="thbg">申请时间</th>
				<td>${mapBusi.createTime}</td>
				<th class="thbg">申请利率浮动(%)</th>
				<td>${mapBusi.applyRate}</td>
				<th class="thbg">申请授信年限(年)</th>
				<td>${mapBusi.applyYear}</td>
			</tr>
			<tr>
				<th class="thbg">贷款用途</th>
				<td colspan='5'>${mapBusi.creditPurpose}</td>
			</tr>
			<tr>
				<th class="thbg">备注</th>
				<td colspan='5'>${mapBusi.remark}</td>
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
					<th style="width:100px">环节</th>
					<th width="100">审批人</th>
					<th width="100">审批动作</th>
					<th width="120">审批意见</th>
					<th width="100">授信金额(元)</th>
					<th width="90">利率浮动</th>
					<th width="90">授信年限</th>
					<th width="150">审批时间</th>
					<th width="90">附件下载</th>
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
					<td>
						<c:choose>  
						   <c:when test="${data.actName =='总行审查中心'}">
						   		<a href="/cps/form/previewAttachmentFile?opinionId=${data.opinionId}" title="下载审查报告">审查报告</a>
						   </c:when>  
						   <c:otherwise>
						   		<span style="color: grey"> -- </span>
						   </c:otherwise>  
						</c:choose> 
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
  	
	<!-- 审批意见表单填写 -->
	<div class="panel panel-success">
	  <div id="opinionDiv" class="panel-heading">填写审批意见:</div>
	  <div class="panel-body" >
			<form name="submitForm" id="optionForm" method="post">
				<input type="hidden" name="businessKey" value="${businessKey}" />	
				<input type="hidden" name="taskId" value="${taskId}" />	
				<input type="hidden" name="userCode" value="${userCode}" />	
				<input type="hidden" name = "lineVariable" value="${lineVar}" />	
				<input type="hidden" name = "value" id="valueInputId" />
				<textarea class="form-control" rows="3" name = "optionContent"></textarea>
				<textarea name="signData" hidden=true cols="60" rows="6" id="signDataID"></textarea>
				<div class="input-group">
					<span class="input-group-addon"><font color="red">*</font> 同意授信金额 ( 元 )</span>
					<input type="text" class="form-control" placeholder="请输入允许的授信金额" value="${mapBusi.creditSumW}" name="creditSumW" id="creditSumWInputId" />
				</div>
				<div class="input-group">
					<span class="input-group-addon"><font color="red">*</font> 同意利率浮动 ( % )&nbsp;</span>
					<input type="text" class="form-control" placeholder="请输入同意利率浮动"
						value="${mapBusi.creditRate}" name="creditRate"
						id="creditRateInputId" />
				</div>
				<div class="input-group">
					<span class="input-group-addon"><font color="red">*</font> 同意授信年限 ( 年 ) </span>
					<input type="text" class="form-control" placeholder="请输入同意授信年限"
						value="${mapBusi.creditYear}" name="creditYear"
						id="creditYearInputId" />
				</div>	
				<div align="center">
					<input type="button" class="btn btn-danger" name="seal_optionContent" onclick="WebSign_AddSeal('${userCode}');" value="盖章" />
					<input type="button" class="btn btn-primary" name="write_optionContent" onClick="HandWritePop_onclick2('${userCode}')" value="手写" />
					<input type="submit" class="btn btn-primary" onclick="agree();" value="批准" />
					<input type="submit" class="btn btn-danger" onclick="disagree();" value="不批准" />
				</div>
				
			</form>
	  </div>
	</div>	
  </body>
</html>
<script type="text/javascript">
	var userCode = "${userCode}";
	var taskId = "${taskId}";
	var creditId = ${businessKey};
	var optionJson = ${optionJson};
	var originalCreditSumW = "${mapBusi.creditSumW}";
	var originalCreditRate = "${mapBusi.creditRate}";
	var originalCreditYear = "${mapBusi.creditYear}";
	var options = {  
			//   target: '#output',          //把服务器返回的内容放入id为output的元素中      
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   url: '/cps/form/dealActDSXZ',                 //默认是form的action， 如果申明，则会覆盖  
			   //type: type,               //默认是form的method（get or post），如果申明，则会覆盖  
			   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型  
			   //clearForm: true,          //成功提交后，清除所有表单元素的值  
			   //resetForm: true,          //成功提交后，重置所有表单元素的值  
			   timeout: 10000               //限制请求的时间，当请求大于10秒后，跳出请求  
			};
	
	function showRequest(formData, jqForm, options){  
		   //formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]  
		   //jqForm:   jQuery对象，封装了表单的元素     
		   //options:  options对象  
		  // var queryString = $.param(formData);   //name=1&address=2  
		  // var formElement = jqForm[0];              //将jqForm转换为DOM对象  
		   //var address = formElement.address.value;  //访问jqForm的DOM元素  
		   return true;  //只要不返回false，表单都会提交,在这里可以对表单元素进行验证  
	};  
		
	function showResponse(responseText, statusText){  
			 if(responseText.success == true) {
				   alert('任务处理成功!');
	                window.parent.pendPoolGrid.store.load();
	                window.parent.ACT_DEAL_WINDOW.close();
			 }else {
				   alert('任务处理失败!');
			 }
	};		
	//批准
	function agree() {
		var returnFlag = checkApproveData();
		if(!returnFlag) {
			return;
		}
		try {
			var sealData = signIsExist(userCode+"Seal"+taskId);
			//var writerData = signIsExist(userCode+"Write"+taskId);
			if(sealData != null && sealData!=""){
				var signData = document.all.DWebSignSeal.GetStoreData();
				$("#valueInputId").val(1);
				$("#signDataID").val(signData);
				$("#optionForm").ajaxForm(options);
			}else{
				alert("必须盖章后才可以提交");
				return;
			}
		} catch (e) {
			alert("控件没有安装，请刷新本页面。");
			return false;
		}
			
	}
	//不批准
	function disagree() {
		try {
		var signData = document.all.DWebSignSeal.GetStoreData();
		if(signData != null && signData!=""){
			$("#signDataID").val(signData);
		}
		$("#valueInputId").val(0);
		$("#optionForm").ajaxForm(options); 
		} catch (e) {
			alert("控件没有安装，请刷新本页面。");
			return false;
		}
	}
	
	//点击客户姓名打开授信PDF档案显示页面
	function viewCreditPdf() {
		window.open("/cps/pdf/viewPdf?flag=0&creditId="+creditId);
	}
	
	//校验输入的审批意见
	function checkApproveData() {
		var returnFlag = true;
		var newCreditSumW = $("#creditSumWInputId").val();
		var newCreditRate = $("#creditRateInputId").val();
		var newCreditYear = $("#creditYearInputId").val();
		if (newCreditSumW == null || newCreditRate == null
				|| newCreditYear == null || newCreditSumW == ""
				|| newCreditRate == "" || newCreditYear == "") {
			returnFlag = false;
			alert('同意授信金额、同意利率浮动、同意授信年限不能为空!');
			return returnFlag;
		}
		var flag = zfloatCheck(newCreditSumW);
		if (!flag) {
			returnFlag = false;
			alert('同意授信金额只能输入正数！');
			return returnFlag;
		}
		var flag = floatCheck(newCreditRate);
		if (!flag) {
			returnFlag = false;
			alert('同意利率浮动只能输入数字');
			return returnFlag;
		}
		var flag = isIntegerCheck(newCreditYear);
		if (!flag) {
			returnFlag = false;
			alert('同意授信年限只能输入正整数!');
			return returnFlag;
		}
		newCreditSumW = parseFloat(newCreditSumW);
		newCreditRate = parseFloat(newCreditRate);
		newCreditYear = parseInt(newCreditYear);
		if (newCreditSumW > originalCreditSumW) {
			returnFlag = false;
			alert('同意授信金额不能大于上一环节同意授信金额!');
			return returnFlag;
		} else if (newCreditRate < originalCreditRate) {
			returnFlag = false;
			alert('同意利率浮动不能低于上一环节同意利率浮动!');
			return returnFlag;
		} else if (newCreditYear > originalCreditYear) {
			returnFlag = false;
			alert('同意授信年限不能大于上一环节同意授信年限!');
			return returnFlag;
		}
		return returnFlag;
	}
</script>