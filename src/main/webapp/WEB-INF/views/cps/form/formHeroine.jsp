<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8" />
    <title>城乡妇女创业小额担保贷款审批表</title>
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
			   url: '/cps/form/saveFormHeroine',                 //默认是form的action， 如果申明，则会覆盖  
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
	
</script>



<body style="position: relative;">
<section class="info-section" id="info" data-title="XXXX<small>金农易贷·福农卡”贷款调查、审查、审批表</small>" />
<form class="form-table" id="optionForm" method="post">
	<div class="well" >
        <legend align="center">城乡妇女创业小额担保贷款审批表</legend>
        <table width="100%" border="1" align="center" cellspacing="0" >
        	<tr class="title">
            	<td width="15%" class="tdtitle">申请人姓名:</td>
                <td width="13%" class="inputtext">
                	<input type="text" name="applyId"  value="" style="display:none;"/>
                	<input type="text" name="customerId"  value="${customerId}" style="display:none;"/>
                	<input type="text" name="creditId"  value="${creditId}" style="display:none;"/>
                	<input type="text" name="custName"  value="${map.custName}" style="display:none;"/>
                	<input type="text"  value="${map.custName}" style="width: 80%" disabled="disabled"/>
                </td>
                <td width="5%" class="tdtitle">性别:</td>
                <td width="5%" class="inputtext">
                	 <select class="form-control input-sm" name="custSex" value="${map.sex}" style="width:60px;">
                	 	<c:if test="${map.sex==0}">
						    <option value="0" selected="selected">男</option>
							<option value="1" >女</option>
							<option value="2" >未知</option>
						</c:if>
						<c:if test="${map.sex==1}">
						    <option value="0" >男</option>
							<option value="1" selected="selected">女</option>
							<option value="2" >未知</option>
						</c:if>
						<c:if test="${map.sex==2}">
						    <option value="0" >男</option>
							<option value="1">女</option>
							<option value="2" selected="selected">未知</option>
						</c:if>
					</select>
               	</td>
                <td width="13%" class="tdtitle">年龄:</td>
                <td width="18%" class="inputtext"><input type="text" name="custAge"  value="${map.age }" style="width: 100px"/></td>
                <td width="11%" class="tdtitle">文化程度:</td>
                <td width="30%" class="inputtext"><input type="text" name="custEduLev"  value="${map.eduLev }" style="width: 92%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">户口所在乡镇:</td>
                <td colspan="8" class="inputtext"><input type="text" name="accountAddress"  value="" class="{maxlength:128}"  style="width: 98%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">家庭住址:</td>
                <td colspan="8" class="inputtext"><input type="text" name="custHomeaddr"  value="${map.addressX }" class="{maxlength:128}"  style="width: 98%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">身份证号码:</td>
                <td colspan="3" class="inputtext"> 
                	<input type="text" name="custIdCard"  value="${map.idCard }" style="display:none;"/>
                	<input type="text"  value="${map.idCard }" style="width: 92%" disabled="disabled"/>
                </td>
                <td class="tdtitle"><span style="color:red">*</span> 营业执照编号:</td>
                <td class="inputtext"> <input type="text" name="businessLicense" class="{required:true}" value="" style="width: 92%"/> </td>
                <td class="tdtitle"><span style="color:red">*</span> 就失业登记号:</td>
                <td class="inputtext"><input type="text" name="employUnemployNo" class="{required:true}" value="" style="width: 92%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">注册地址:</td>
                <td colspan="8" class="inputtext"><input type="text" name="registeredAddress"  value="" class="{maxlength:128}"  style="width: 98%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">申请贷款经营项目:</td>
                <td colspan="8" class="inputtext"><input type="text" name="operatingItems"  value="" class="{maxlength:128}"  style="width: 98%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">经营地点:</td>
                <td colspan="8" class="inputtext"><input type="text" name="operatingAddress"  value="" class="{maxlength:128}"  style="width: 98%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 申请借款金额:</td>
                <td colspan="3" class="inputtext"><span class="input-group-addon">( 万元 )</span><input type="text" name="loanAmount" class="{required:true,number:true}" style="width: 73%"/> </td>
                <td class="tdtitle"><span style="color:red">*</span> 自有资金:</td>
                <td class="inputtext"><span class="input-group-addon">( 万元 )</span><input type="text" name="ownFunds"  value="" class="{required:true,digits:true}" style="width: 62%"/> </td>
                <td class="tdtitle"><span style="color:red">*</span>  申请贷款期限:</td>
                <td class="inputtext"><input type="text" name="loanTerm" class="{required:true}" value="" style="width: 70%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 申请借款用途:</td>
                <td colspan="8" class="inputtext"><input type="text" name="loanPurpose" class="{required:true,maxlength:64}" value="" style="width: 98%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">原工作单位:</td>
                <td colspan="3" class="inputtext"><input type="text" name="originalWorkUnit"  value="" style="width: 92%"/> </td>
                <td class="tdtitle">联系电话:</td>
                <td class="inputtext"><input type="text" name="originalPhone"  value="" style="width: 92%"/> </td>
                <td class="tdtitle">联系人:</td>
                <td class="inputtext"><input type="text" name="originalContactPerson"  value="" style="width: 92%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 反担保措施:</td>
                <td colspan="8" class="inputtext"><input type="text" name="counterGuaranteeMeasures"  value="" class="{required:true,maxlength:128}" style="width: 98%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 还款计划:</td>
                <td colspan="8" class="inputtext"><input type="text" name="repaymentPlan"  value="" class="{required:true,maxlength:128}" style="width: 98%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 前景分析及经营计划:</td>
            	<td colspan="8" class="inputtext"><input type="text" name="custOperatingPlan"  value="" class="{required:true,maxlength:128}" style="width: 98%"/></td>
            </tr>
            
        </table>
        <br />
        <!--家庭成员信息-->
		<div class="panel panel-success">
		  <!-- Default panel contents -->
		  <div class="panel-heading">家庭成员信息</div>	
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
		  <div class="panel-heading">抵押物信息</div>	
		  <!-- Table -->
			<table class="table table-striped table-bordered">
				<tr>
						<th width="20">序号</th>
						<th width="120">抵押类型</th>
						<th width="120">抵押物名称</th>
						<th width="120">抵押物价值</th>
						<th width="120">权证号（或车牌号）</th>
				</tr>			
				<c:forEach var="x" items="${listPledge}" varStatus="status"> 
					<tr style="height: 20px">
						<td class="tdseq">${ status.index + 1}</td>  
		            	<td >
		            		<c:if test="${x.pledgeFromWhere==1}">
							       运输工具、设备信息
							</c:if>
							<c:if test="${x.pledgeFromWhere==2}">
									房地产明细信息
							</c:if>
							<c:if test="${x.pledgeFromWhere==3}">
									其它抵押物信息
							</c:if>
		            	</td>
		                <td >${x.pleName}</td>
		                <td >${x.evaMoney} </td>
		                <td >${x.licenceCar} </td>
	            	</tr>
				</c:forEach>		
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
