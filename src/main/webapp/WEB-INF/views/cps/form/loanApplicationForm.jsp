<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8" />
    <title>贷款申请表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />

	
    <!-- Le styles -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"/>
	<link href="/resources/bootstrap/css/default.css" rel="stylesheet"/>
	<script type="text/javascript" src="/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/plugins/jquery.form.js"></script>
    
    <script type="text/javascript" src="/resources/js/jquery/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/jquery-validation/jquery.metadata.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/jquery-validation/messages_cn.js"></script>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	
	<link href="/resources/css/table-style.css" rel="stylesheet" />
	
    <!--[if lte IE 6]>  <![endif]-->
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap-ie6.min.css" /> 
  	<!--[if lte IE 7]><![endif]-->
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/ie.css" />
	
	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="/resources/bootstrap/js/html5.js"></script>
    <![endif]-->
  </head>
  
<script type="text/javascript">
	var creditId = '${creditId}';
	var customerId = '${customerId}';
	var Validator = null;
	var options = {
			//   target: '#output',        //把服务器返回的内容放入id为output的元素中      
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   url: '/cps/form/saveLoanApplicationForm',                 //默认是form的action， 如果申明，则会覆盖  
			  //type: post,               //默认是form的method（get or post），如果申明，则会覆盖  
			   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型  
			   //clearForm: true,          //成功提交后，清除所有表单元素的值  
			   //resetForm: true,          //成功提交后，重置所有表单元素的值  
			   timeout: 10000              //限制请求的时间，当请求大于10秒后，跳出请求  
			};
	
	function showRequest(formData, jqForm, options){  
		   //formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]
		   //jqForm:   jQuery对象，封装了表单的元素
		   //options:  options对象
		   //var queryString = $.param(formData);   //name=1&address=2
		   //var formElement = jqForm[0];              //将jqForm转换为DOM对象
		   //var address = formElement.address.value;  //访问jqForm的DOM元素
		   return true;  //只要不返回false，表单都会提交,在这里可以对表单元素进行验证
	};  
		
	function showResponse(responseText, statusText){
			if(responseText.success == true) {
				   
			$.ajax({
				url:'/cps/form/generatePdf',
				type : 'post',
				data : {
					op : "ajax",
					creditId : creditId,
					pdfType:1
				},
				success: function(form, action) {
				},
				failure: function(form, action) {
				}
			});
				alert('任务处理成功!');
	            //window.parent.pendPoolGrid.store.load();
	            //window.parent.ACT_DEAL_WINDOW.close();
				window.close();
			}else {
			   alert('任务处理失败!');
			}
	};		
	//批准
	function save() {
		
		if (!Validator.form()){
			return;
		}
		$("#optionForm").ajaxForm(options);  	
	}
	
	//点击客户姓名打开授信PDF档案显示页面
	function viewCreditPdf() {
		window.open("/cps/pdf/viewPdf?flag=0&cerditId="+creditId);
	}
	
	$(function() {
		//$.metadata.setType("attr", "validate");
		//deafultValidate($("#optionForm"));
		Validator = $("#optionForm").validate();
	});
	//增加一行表格
	function add()
	{ 
		var newtr = addtr.insertRow();
		var newTd0 = newtr.insertCell();
		var newTd1 = newtr.insertCell();
		var newTd2 = newtr.insertCell();
		var newTd3 = newtr.insertCell();
		var newTd4 = newtr.insertCell();
		newTd0.innerHTML = '<input type="text" style="height:100%; width:99%; border:1px"/>';
		
		newTd1.innerHTML = '<input type="text" style="width:100%; height:99%; border:0px"/>';
		
		newTd2.innerHTML = '<input type="text" style="width:100%; height:99%; border:0px"/>';
		
		newTd3.innerHTML = '<input type="text" style="width:100%; height:99%; border:0px"/>';
		
		newTd4.innerHTML = '<input type="text" style="width:100%; height:98%; border:0px"/>';
	
		
		
		
		}
</script>

<body style="position: relative;">
<section class="info-section" id="info" data-title="XXXX<small>贷款申请表</small>" />
<form class="form-table" id="optionForm" method="post">
	<div class="well" >
        <legend align="center">${map.custName}：${productName}申请表</legend>
        <table width="100%" border="1" align="center" cellspacing="0" >
        	<tr class="title">
            	<td width="15%" class="tdtitle">借款人姓名:</td>
                <td colspan="3" class="inputtext">
               		<input type="text" name="applyId"  value="" style="display:none;"/>
                	<input type="text" name="customerId"  value="${customerId}" style="display:none;"/>
                	<input type="text" name="productId"  value="${productId}" style="display:none;"/>
                	<input type="text" name="productType"  value="${productType}" style="display:none;"/>
                	<input type="text" name="creditId"  value="${creditId}" style="display:none;"/>
           			<input type="text" name="custType"  value="${map.custType}" style="display:none;"/>
           			<input type="text" name="custName"  value="${map.custName}" style="display:none;"/>
           			<input type="text" name="loanAmount"  value="${map.applySum}" style="display:none;"/>
                	<input type="text"   value="${map.custName}" style="width: 90%" disabled="disabled"/>
                </td>
               <td class="tdtitle">手续费方式:</td>
                <td colspan="3" class="inputtext"> 
                	<input type="text" name="feeWay" style="width: 90%" />
                </td>
            </tr>
            <tr class="title">
                <td class="tdtitle">担保方式:</td>
           		 <td colspan="3" class="inputtext">
	                <select class="form-control input-sm" name="guaranteeWay"  style="width: 90% ">
						<option value="1" >抵押</option>
						<option value="2" >质押</option>
						<option value="3" >保证</option>
						<option value="4" >信用</option>
					</select>
				
           		 </td>
                <td class="tdtitle"><span style="color:red">*</span> 申请借款金额:</td>
                <td colspan="3" class="inputtext"><span class="input-group-addon">( 元 )</span><input type="text" name="loanAmount" 
                	value="${map.applySum}" class="{required:true,number:true}" style="width: 82%" disabled="disabled"/> </td>
              </tr>
            <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 申请借款用途:</td>
                <td colspan="8" class="inputtext"><input type="text" name="collateral" class="{required:true,maxlength:64}" value="" style="width: 97%"/></td>
            </tr>
           <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 担保物:</td>
                <td colspan="8" class="inputtext"><input type="text" name="loanPurpose" class="{required:true,maxlength:64}" value="" style="width: 97%"/></td>
            </tr>
             <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 备注:</td>
                <td colspan="8" class="inputtext"><input type="text" name="remarkNote" class="{required:true,maxlength:64}" value="" style="width: 97%"/></td>
            </tr>
        </table>
        <br />
        <!--家庭成员信息-->
		<div class="panel panel-success">
		  <!-- Default panel contents -->
		  <div class="panel-heading">家庭成员信息:</div>	
		  <!-- Table -->
			<table class="table table-striped table-bordered">
				<tr>
						<th width="20">序号</th>
						<th width="120">姓名</th>
						<th width="120">和客户关系</th>
						<th width="120">职业</th>
						<th width="120">身份证号码</th>
				</tr>			
				<c:forEach var="x" items="${listFamilyMember}" varStatus="status"> 
					<tr style="height: 20px">
						<td class="tdseq">${ status.index + 1}</td>  
		            	<td >${x.custName}</td>
		                <td >${x.relationToCustomer}</td>
		                <td >${x.duty} </td>
		                <td >${x.idCard} </td>
	            	</tr>
				</c:forEach>		
			</table>
		</div>
		<br />
        <!--抵押物信息-->
		<div class="panel panel-success">
		  <!-- Default panel contents -->
		  <div class="panel-heading">抵押物信息:</div>	
		  <!-- Table -->
			<table class="table table-striped table-bordered">
				<tr>
						<th width="20">序号</th>
						<th width="120">抵押类型</th>
						<th width="120">抵押物名称</th>
						<th width="120">抵押物价值</th>
						<th width="120">权证号（或车牌号）</th>
				</tr>			
					
			</table>
		</div>
        
        <div class="form-actions" align="center">
            <button type="submit" class="btn btn-primary" onclick="save();">保存</button>
            <button type="reset" class="btn btn-warning">重置</button>
    	</div>
        
    </div>
    	
</form>	
</body>
</html>
