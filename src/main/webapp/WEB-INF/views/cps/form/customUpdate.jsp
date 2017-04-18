<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>    
    <link rel="stylesheet" type="text/css" href="/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="/resources/js/ext/ext-lang-zh_CN.js"></script>
        
    <link href="/resources/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/plugins/jquery.form.js"></script>
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
		  			<th class="thbg">客户号</th><td>${mapBusi.customerId}</td><th  class="thbg">客户名称</th><td><a onclick="editCreditPdf();" title="点击修改授信档案">${mapBusi.custName}</a></td><th  class="thbg">所属支行</th><td>${mapBusi.orgName}</td>
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
	<div class="panel panel-success">
	  <div class="panel-heading">总行中心审查意见:</div>	
		<table class="table table-striped table-bordered">
			<tr>
					<th style="width:120px">环节</th>
					<th width="80">审批人</th>
					<th width="120">审批动作</th>
					<th width="120">审批意见</th>
					<th width="100">同意授信金额</th>
					<th width="100">同意利率浮动</th>
					<th width="100">同意授信年限</th>
					<th width="120">审批时间</th>
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
  	
	<!-- 审批意见表单填写 -->
	<div class="panel panel-success">
	  <div id="opinionDiv" class="panel-heading">提交意见:</div>
	  <div class="panel-body" >
			<form name="submitForm" id="optionForm" method="post">
				<input type="hidden" name="businessKey" value="${businessKey}" />	
				<input type="hidden" name="taskId" value="${taskId}" />	
				<input type="hidden" name="userCode" value="${userCode}" />	
				<input type="hidden" name = "lineVariable" value="${lineVar}" />	
				<input type="hidden" name = "value" id="valueInputId" />
				<textarea class="form-control" rows="3" name = "optionContent"></textarea>
				<textarea name="signData" hidden=true cols="60" rows="6" id="signDataID"></textarea>				
				<div align="center">
					<input type="submit" class="btn btn-primary" onclick="agree();" value="提交" />
					<input type="button" class="btn btn-danger" onclick="invalidProcess();" value="作废" />
				</div>
			</form>
	  </div>
	</div>	
  </body>
</html>
<script type="text/javascript">
	var taskId = "${taskId}";
	var optionJson = ${optionJson};
	var creditId = "${businessKey}";
	var custNo = "${mapBusi.customerId}";
	var productId = "${mapBusi.productId}";
	var userCode = "${userCode}";
	var processInstanceId = "${mapBusi.processInstanceId}";
	var options = {  
			//   target: '#output',          //把服务器返回的内容放入id为output的元素中      
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   url: '/cps/form/dealCustomUpdateAct',                 //默认是form的action， 如果申明，则会覆盖  
			   //type: type,               //默认是form的method（get or post），如果申明，则会覆盖  
			   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型  
			   //clearForm: true,          //成功提交后，清除所有表单元素的值  
			   //resetForm: true,          //成功提交后，重置所有表单元素的值  
			   timeout: 10000               //限制请求的时间，当请求大于3秒后，跳出请求  
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
			   //客户经理修改资料提交后重新生成授信PDF
				Ext.Ajax.request({
					url: '/cps/pdf/reMakeCreditPdf',
					method: 'POST',
					params: {creditId:creditId}
				});
               window.parent.pendPoolGrid.store.load();
               window.parent.ACT_DEAL_WINDOW.close();
		 }else {
			   alert('任务处理失败!');
		 }
	};		
	//批准
	function agree() {
		$("#valueInputId").val(1);
		$("#optionForm").ajaxForm(options);  
	}
	
	//作废流程
	function invalidProcess() {
		Ext.Msg.confirm('提示窗口','确定要作废此条流程?',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
					url: '/cps/process/forceOverProcess',
					method: 'POST',
					params: {processInstanceId:processInstanceId, deleteReason:'客户经理作废流程'},
					success: function(){
						Ext.Msg.show({   
                            title : '系统提示',   
                            msg : '作废此流程成功！',   
                            buttons: Ext.Msg.OK,   
                            fn: function() {  
                                window.parent.pendPoolGrid.store.load();
                                window.parent.ACT_DEAL_WINDOW.close();
                            },   
                            closable: false   
                            }); 						
					},
					failure: function(){
						Ext.Msg.alert('系统提示','作废此流程失败！');
					}
				});
			}
		});	
	}
	
	//点击客户姓名打开授信PDF档案显示页面
	function editCreditPdf() {	
    	var url = "/cps/flex/flexPageEdit?userCode="+userCode+"&custNo="+custNo+"&productId="+productId+"&creditId="+creditId;
    	window.open(url, 'mywindow1', 'width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-10)+ ',top=0,left=0,resizable=yes,status=yes,menubar=no,scrollbars=no'); 
	}
</script>