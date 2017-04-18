<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8" />
    <title>“农家乐”贷款申请表</title>
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
	<script type="text/javascript" src="/resources/js/cps/form/editTable.js"></script>
	
	<link href="/resources/css/table-style.css" rel="stylesheet" />
	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="/resources/bootstrap/js/html5.js"></script>
    <![endif]-->
  </head>
  
<script type="text/javascript">
	var creditId = '${creditId}';
	var customerId = '${customerId}';
	var custName = '${map.custName}';
	var productId = '${productId}';
	var Validator = null;
	//var bondsmanArray = new Array();
	function showRequest(formData, jqForm, options){ 
	   //formData:[{name:bondsmanArray,value:bondsmanArray},{name:creditId,value:creditId},{name:customerId,value:"321"},{name:custName,value:custName}];
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
					pdfType:1,
					productId:productId
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
		var bondsmanArray = new Array();
		var table = document.getElementById("bondsmanTableId");
        var tableCount = table.rows.length;
        for (var i = 0; i < tableCount-1; i++) {
            var eveRow = table.rows[i + 1];
            var allname="";
            var textInputs = eveRow.getElementsByTagName('input');
            for (var j = 0; j < textInputs.length; j++) {
            	var name = textInputs[j].name;
                var val = textInputs[j].value;
                if(name != null && name != ""){
                	allname += name+"="+val+";";
                }
            }
            bondsmanArray[i] = allname;
        }
		if (!Validator.form()){
			return;
		}
		$("#optionForm").ajaxForm({
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   url: '/cps/form/saveFormFarm',                 //默认是form的action， 如果申明，则会覆盖  
			   //type: type,    \           //默认是form的method（get or post），如果申明，则会覆盖  
			   data: { bondsmanArray: bondsmanArray+""},
			   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型 
			   timeout: 10000  
		});  	
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
	
	//保存担保人信息
	function saveBondsman(){
		
	}
	
</script>



<body style="position: relative;">
<section class="info-section" id="info" data-title="XXXX<small>“农家乐”贷款申请表</small>" />
<form class="form-table" id="optionForm" method="post">
	<div class="well" >
        <legend align="center">“农家乐”贷款申请表</legend>
        <table width="100%" border="1" align="center" cellspacing="0" >
        	<tr class="title">
            	<td width="12%" class="tdtitle">借款人姓名:</td>
                <td width="18%" class="inputtext">
                	<input type="text" name="applyId"  value="" style="display:none;"/>
                	<input type="text" name="customerId"  value="${customerId}" style="display:none;"/>
                	<input type="text" name="creditId"  value="${creditId}" style="display:none;"/>
                	<input type="text" name="custName"  value="${map.custName}" style="display:none;"/>
                	<input type="text"  value="${map.custName}" style="width: 80%" disabled="disabled"/>
                </td>
                <td width="8%" class="tdtitle">性别:</td>
                <td width="5%" class="inputtext">
                	 <select class="form-control input-sm" name="custSex" value="${map.sex}" style="width:50px;">
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
                <td width="10%" class="tdtitle">年龄:</td>
                <td width="18%" class="inputtext"><input type="text" name="custAge"  value="${map.age }" style="width: 100px"/></td>
                <td width="13%" class="tdtitle">文化:</td>
                <td width="26%" class="inputtext"><input type="text" name="custEduLev"  value="${map.eduLev }" style="width: 84%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">身份证号码:</td>
                <td class="inputtext"> 
                	<input type="text" name="custIdcard"  value="${map.idCard }" style="display:none;"/>
                	<input type="text"  value="${map.idCard }" style="width: 87%" disabled="disabled"/>
                </td>
                <td class="tdtitle">家庭人口:</td>
                <td class="inputtext"> <input type="text" name="borrowersHousehold"  value="${map.familyNum }" class="{digits:true}" style="width: 60px"/> </td>
                <td class="tdtitle">劳动力:</td>
                <td class="inputtext"> <input type="text" name="borrowersLaborForce"  value="" class="{digits:true,maxlength:4}" style="width: 100px"/> </td>
                <td class="tdtitle">联系电话:</td>
                <td class="inputtext"><input type="text" name="custPhone"  value="${map.mobil }" style="width: 84%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">共同借款人姓名:</td>
                <td colspan="3" class="inputtext"> 
                	<input type="text" name="coBorrower" value="" style="width: 84%"/>
                </td>
                <td class="tdtitle">身份证号码:</td>
                <td class="inputtext"> <input type="text" name="coBorrowerCardno"  value="" class="{maxlength:20}" style="width: 87%"/> </td>
                <td class="tdtitle">联系电话:</td>
                <td class="inputtext"><input type="text" name="coBorrowerPhone"  value="" style="width: 84%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">家庭住址:</td>
                <td colspan="5" class="inputtext"><input type="text" name="custHomeaddr"  value="${map.addressX }" class="{maxlength:128}"  style="width: 97%"/></td>
            	<td class="tdtitle">承包土地(亩):</td>
            	<td class="inputtext"><input type="text" name="contractedLand" class="{digits:true,maxlength:6}"  value="" style="width: 84%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">上年收支情况:</td>
                <td colspan="7" class="inputtext"><input type="text" name="lastYearBalance"  value="" class="{maxlength:128}"  style="width: 97%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">本年收支情况:</td>
                <td colspan="7" class="inputtext"><input type="text" name="yearBalabce"  value="" class="{maxlength:128}"  style="width: 97%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle">家庭债务情况:</td>
                <td colspan="7" class="inputtext"><input type="text" name="householdDebt"  value="" class="{maxlength:256}"  style="width: 97%"/></td>
            </tr>
            <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 申请借款金额:</td>
                <td class="inputtext"><span class="input-group-addon">( 万元 )</span><input type="text" name="loanAmount" class="{required:true,number:true}" style="width: 62%"/> </td>
                <td class="tdtitle"><span style="color:red">*</span> 还款来源:</td>
                <td class="inputtext" colspan="3">
                	<input type="text" name="repaymentSource" class="{required:true,maxlength:32}" value="" style="width: 95%"/>
				</td>
                <td class="tdtitle">信用等级:</td>
                <td class="inputtext"> <input type="text" name="custCreditRating"  value="" style="width: 84%"/> </td>
            </tr>
            <tr class="title">
            	<td class="tdtitle"><span style="color:red">*</span> 申请借款用途:</td>
                <td colspan="7" class="inputtext"><input type="text" name="loanPurpose" class="{required:true,maxlength:64}" value="" style="width: 97%"/></td>
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
		<div class="panel panel-success" id="bondsmanFormId">
		  <!-- Default panel contents -->
		  <div class="panel-heading" id=parentTextId>担保人信息:
		  	<button type="button" class="btn btn-primary" onclick="addBondsman();">增加</button>	
		  </div>
		  <!-- Table -->
			<table class="table table-striped table-bordered" id="bondsmanTableId">
				<tr>
						<th width="120">担保人客户号</th>
						<th width="120">担保人名称</th>
						<th width="120">担保人证件号码</th>
						<th width="120">担保人与客户关系</th>
						<th width="110">借款合同号</th>
						<th width="110">操作</th>
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
