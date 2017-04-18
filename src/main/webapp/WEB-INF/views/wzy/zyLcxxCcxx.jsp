<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3">
<script type="text/javascript"
	src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></script>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>  
<script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>  
<link href="<%=path%>/resources/mobiscroll/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />  
<link href="<%=path%>/resources/mobiscroll/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />  
<script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>  
<script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/css/style.css">

<!-- 树组件start -->
<script type="text/javascript" src="<%=path%>/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/libs/js/tree/ztree/ztree.css"></link>
<!-- 树组件end -->

<!-- 树形下拉框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/selectTree.js"></script>
<!-- 树形下拉框end -->

</head>

<script type="text/javascript">
$(function () {  
    var currYear = (new Date()).getFullYear();    
    var opt={};  
    opt.date = {preset : 'date',minDate:new Date()};  
    //opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };  
    opt.datetime = {preset : 'datetime'};  
    opt.time = {preset : 'time'};  
    opt.default = {  
        theme: 'jqm', //皮肤样式  
        display: 'modal', //显示方式   
        mode: 'scroller', //日期选择模式  
        dateFormat : 'yy-mm-dd', // 日期格式 
        dateOrder : 'yymmdd', //面板中日期排列格式  
        lang:'zh',  
        startYear:currYear - 20, //开始年份  
        endYear:currYear + 20 //结束年份  
    };  

    $("#starttime").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));  
    var optDateTime = $.extend(opt['datetime'], opt['default']);  
    
    $("#endtime").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));  
    var optDateTime = $.extend(opt['datetime'], opt['default']);  
    
});

function getShr(value){
	$('#shr').val(value);
}

function  getCsr(value){
	$('#csr').val(value);
}

function close(){
	var PAGESIZE = 10;
	window.parent.constructionGrid.store.load({params:{start:0,limit:PAGESIZE}});
    window.parent.ACT_DEAL_WINDOW.close();
}
  function save(){
	var sqly = $('#sqly').val();
	var starttime=$('#starttime').val();
    var endtime =$('#endtime').val();
	var shr=$('#shr').val();
    if(sqly=="" || sqly==null){
    	alert("请输入申请理由!");
    	return false;
    }
	if(starttime==""||starttime==null){
		alert("请输入开始时间！");
		return false;
	}
	if(endtime==null  || endtime ==""){
		alert("请输入结束时间!");
		return false;
	}
	if(starttime >endtime){
		alert("开始时间大于结束时间!");
		return false;
	}
	if(shr==null || shr==""){
		alert("请选择审核人!");
		return false;
	}
	$('#tjbutton').hide();
	var openId = $('#openId').val();
	var url="";
	var msg="";
		url="<%=path%>/wzy/ZyLcxx/saveCcxx";
		msg="确定要提交申请信息吗？";
	if (confirm(msg)) {
		//getMrjh();
		$.ajax({
			cache : true,
			type : "POST",
			url : url,
			data : $('#myForm').serialize(),// 你的formid
			async : false,
			error : function(request) {
				alert("保存失败，请联系管理员。");
			},
			success : function(data) {
				if(data=='1'){
					alert('保存成功！'); 
					location.href="<%=path%>/wzy/ZyLcxx/toCcjl?openId="+openId;
				}else{
					alert("保存失败，请联系管理员。");
					return false;
				}
			}
		});
	}
}
</script>
<body>
<form action="" class="form-horizontal tasi-form" name="myForm" id="myForm" method="post">
<input type="hidden" value="${openId}" name="openId" id="openId"/>
<input type="hidden" value="" name="shr" id="shr"/>
	<div id="add_apply_modal">
	  <div class="modal-header">
		<h3 id="modal_label">申请信息</h3>
	  </div>
	  <p class="help-block"></p>
		<div class="control-group">
      		<div class="control-group">

          <!-- Textarea -->
          <label class="control-label">出差理由:</label>
          <div class="controls">
            <div class="textarea">
                <textarea style="resize: none;margin-left:2px;" rows="5" cols="41" name="sqly" id="sqly"></textarea>
            </div>
          </div>
      </div>
      
          <!-- Text input-->
          <label class="control-label" for="input01">开始时间:</label>
          <div class="controls">
          		<input id="starttime" name="starttime" 
										style="background: #fff none repeat scroll 0 0;
											   border: 1px solid #ccc;
											   border-radius: 2px;
											   box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
											   color: #555;
											   font-size: 12px;
											   font-weight: normal;
											   height: 30px;
											   line-height: 20px;
											   margin-bottom: 10px;
											   margin-left:2px;
											   padding: 4px 6px;
											   width: 252px;"/>
            <p class="help-block"></p>
          </div>
          
           <label class="control-label" for="input01">结束时间:</label>
           <div class="controls">
           <input id="endtime" name="endtime" 
										style="background: #fff none repeat scroll 0 0;
											   border: 1px solid #ccc;
											   border-radius: 2px;
											   box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
											   color: #555;
											   font-size: 12px;
											   font-weight: normal;
											   height: 30px;
											   line-height: 20px;
											   margin-bottom: 10px;
											   margin-left:2px;
											   padding: 4px 6px;
											   width: 252px;"/>
            <p class="help-block"></p>
          </div>
          
	           <label class="control-label" for="input01">审核人:</label>
	          	<div class="controls">
		            <select style="width:252px;height:30px;margin-left:2px;" onchange="getShr(this.value)">
		               <option value="">请选择</option>
		            <c:forEach var="data" items="${shrlist}" varStatus="obj"> 
		            	<option value="${data.yhbh}">${data.xm}</option>
		            	</c:forEach>
		            </select>
		            <p class="help-block"></p>
		        </div>
     </div>
     
			<div style="text-align: left">
				<div class="panel-body">
					<input id="tjbutton" class="btn btn-success" value="提交" type="button" onclick="save();">
                    <input class="btn btn-success" type="button" onclick="javascript:history.go(-1);" value="返回">
				</div>
			</div>
			</div>
	</form>
</body>
</html>