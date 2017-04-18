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
    </style>
   </head> 
  <body>
  	<?xml version="1.0" encoding="iso-8859-1"?>  
  
  	
	<!-- 审批意见表单填写 -->
	<div class="panel panel-success">
	  <div id="opinionDiv" class="panel-heading">配置文档上传</div>
	  <div class="panel-body" >
			<form name="submitForm" id="optionForm" method="post" enctype="multipart/form-data">
				   <span style="color: red">* 请上传配置文档：</span> <input type="file" name="attachMentFile" id="attachMentFile" size="25">
				  
				<div align="center">
					<input type="submit" class="btn btn-primary" onclick="agree();" value="上传" />
				</div>
			</form>
	  </div>
	   <div class="panel-body" >
			<form name="submitForm" id="blackForm" method="post" enctype="multipart/form-data">
				   <span style="color: red">* 请上传黑名单导入配置文档：</span> <input type="file" name="attachMentFile" id="attachMentFile1" size="25">
				   
				<div align="center">
					<input type="submit" class="btn btn-primary" onclick="agreeToo();" value="上传" />
					
				</div>
			</form>
	  </div>
	</div>	
  </body>
</html>
<script type="text/javascript">
	var options = {
			//   target: '#output',          //把服务器返回的内容放入id为output的元素中      
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   url: '/cps/form/remindUpload',                 //默认是form的action， 如果申明，则会覆盖  
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
			// if(responseText.success == true) {
			   	alert('上传成功!');
			 //}else {
			//	   alert('任务处理失败!');
			// }
	};		
	//批准
	function agree() {
			
			var attachMentFile = $("#attachMentFile").val();
			if(attachMentFile == null || attachMentFile == "") {
				returnFlag = false;
				alert('审查报表单不能为空！');
				return;
			}
				$("#optionForm").ajaxForm(options);  
	}
	var optionsToo = {  
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   url: '/cps/form/blackRemindUpload',                 //默认是form的action， 如果申明，则会覆盖  
			   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型  
			   timeout: 10000               //限制请求的时间，当请求大于10秒后，跳出请求  
			};
	
	//批准
	function agreeToo() {
			
			var attachMentFile = $("#attachMentFile1").val();
			if(attachMentFile == null || attachMentFile == "") {
				returnFlag = false;
				alert('黑名单导入文件不能为空！');
				return;
			}
				$("#blackForm").ajaxForm(optionsToo);  
	}
</script>