<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
   <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>  
    <link href="<%=path%>/resources/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/cps/checkData.js"></script>
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
		
		a{
			cursor:pointer;
		}
    </style>
   </head> 
  <body>
  	<?xml version="1.0" encoding="iso-8859-1"?>  
	<!-- 贷款业务数据 -->
	<div class="panel panel-success">
		<div class="panel-heading">流程信息:</div>
		<table class="table table-bordered">
			<tr>
				<th class="thbg">流程编码</th>
				<td>${mapBusi.processCode}</td>
				<th class="thbg">流程名称</th>
				<td>${mapBusi.name}</td>
			</tr>
		</table>
	</div>

	<!-- 审批意见表单填写 -->
	<div class="panel panel-success">
	  <div id="opinionDiv" class="panel-heading"><font color="red">*</font> 填写审批意见:</div>
	  <div class="panel-body" >
			<form name="submitForm" id="optionForm" method="post">
				<!--使用一个隐藏框来显示：${pageContext.request.contextPath}-->
				<input type="hidden" name="projectName" id="projectName" value="${pageContext.request.contextPath}"/>
				<input type="hidden" name="businessKey" value="${businessKey}" />
				<input type="hidden" name="taskId" value="${taskId}" />	
				<input type="hidden" name="userCode" value="${userCode}" />	
				<input type="hidden" name = "lineVariable" value="${lineVar}" />	
				<input type="hidden" name = "value" id="valueInputId" />
				<input type="text" class="form-control" placeholder="请输入审批意见" name="optionContent"
						id="optinoContentId" />
						<br>
				<div align="center">
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
	var creditId = "${businessKey}";
	var url=$("#projectName").val();//获取跟目录
	var options = {  
			//   target: '#output',          //把服务器返回的内容放入id为output的元素中     
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   url: url+'/cps/test/dealJSZJAct',   //默认是form的action， 如果申明，则会覆盖  
			   //type: type,               //默认是form的method（get or post），如果申明，则会覆盖  
			   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型  
			   //clearForm: true,          //成功提交后，清除所有表单元素的值  
			   //resetForm: true,          //成功提交后，重置所有表单元素的值  
			   timeout: 10000              //限制请求的时间，当请求大于10秒后，跳出请求  
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
		if(returnFlag) {
			$("#valueInputId").val(1);
			$("#optionForm").ajaxForm(options);  
		}else{
			window.onbeforeunload = function(){ return '将丢失未保存的数据!'; } 
			return false;
		}
	}
	//不批准
	function disagree() {
		$("#valueInputId").val(0);
		$("#optionForm").ajaxForm(options); 
	}
	
	//点击客户姓名打开授信PDF档案显示页面
	function viewCreditPdf() {
		window.open("/cps/pdf/viewPdf?flag=0&creditId="+creditId);
	}
	
	//点击查看授信日志记录
	function viewCreditLog() {
		window.open("/cps/cust/creditLogPage?creditId="+creditId);
	}
	
	//校验输入的审批意见
	function checkApproveData() {
		var returnFlag = true;
		var optinoContent = $("#optinoContentId").val();
		if (optinoContent == null ||  optinoContent=="" ) {
			returnFlag = false;
			alert('审查意见不能为空!');
			return returnFlag;
		}
		
		return returnFlag;
	}
</script>