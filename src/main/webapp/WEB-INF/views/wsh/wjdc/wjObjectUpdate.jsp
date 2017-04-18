<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
<title>问卷管理系统</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<script type="text/javascript">
//提交
function sbmOK(){
	if (chkForm()) {
		document.fm.submit();
	}
 }

//校验
function chkForm() {
	var title = document.all("title").value;
	var discribe = document.all("discribe").value;

	if (title == "" || title.length == 0) {
		alert("输入域 主题 不能为空。");
		document.all("title").focus();
		return false;
	} else if (discribe == "" || discribe.length == 0) {
		alert("输入域 描述 不能为空。");
		document.all("discribe").focus();
		return false;
	}
	return true;
}

//取消
function cancel(){
	window.self.location="<%=path%>/wsh/WjObject/toWjList";
 }
</script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/jquery-ui-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/core.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/home.css">

	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/activity.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/uploadify.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-tagsinput.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/datePicker/skin/WdatePicker.css">

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery-ui-1.8.22.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/filter.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/global.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/datePicker/WdatePicker.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="row home-container">
				<div class="span12">
					<div class="right-content">
						<script type="text/javascript" src="<%=path%>/resources/js/wbm/timepicker.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap-tagsinput.min.js"></script>


<div id="add_apply_modal">
	<div class="modal-header">
		<h3 id="modal_label">编辑问卷</h3>
	</div>
	 <p class="help-block"></p>
  <form name="fm" class="form-horizontal" method="post" action="<%=path%>/wsh/WjObject/update" novalidate="novalidate">
  	<input type="hidden" name="oid" value="${map.oid}" />
    <fieldset>
    <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01"><span class="required">*</span>问卷主题：</label>
          <div class="controls">
            <input type="text" name="title" placeholder="" class="input-xlarge" value="${map.title }">
            <p class="help-block"></p>
          </div>
     </div>
        
        
      <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">问卷描述：</label>
          <div class="controls">
            <div class="textarea">
                  <textarea type="" class="" name="discribe" cols="10" rows="4" style="width: 280px">${map.discribe }</textarea>
            </div>
          </div>
      </div>
      
      <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01"><span class="required"></span>是否匿名：</label>
          <div class="controls" style="position: relative;">
            	        是<input name="anonymousFlag" type="radio" value="1" <c:if test="${map.anonymousFlag=='1' }">checked="checked"</c:if>/>&nbsp;&nbsp;
                                                                否<input name="anonymousFlag" type="radio" value="0" <c:if test="${map.anonymousFlag=='0' }">checked="checked"</c:if>/>
            </p>
          </div>
     </div>
      
    <div class="control-group">
          <label class="control-label"></label>

          <!-- Button -->
          <div class="controls">
            <input type="button" id="save_apply" class="btn btn-success" value="保存" onClick="sbmOK();">
             <input id="cancel_button" type="button" class="btn btn-default" value="取消" onclick="cancel();">
          </div>
     </div>
    </fieldset>
  </form>
	
</div>
					</div>
				</div>
			</div>
		</div>
		
<style>
.version-modal{
	padding-bottom:20px; 
}
.version-modal .version-model-body{
	padding: 20px;
	margin-bottom: 20px;
	max-height: 100%;
}
.version-modal .version-model-body ul,
.version-modal .version-model-body ol {
	margin: 14px 0 14px 0;
	padding: 0 0 0 40px;
}
.version-modal .version-model-body ul,
.version-modal .version-model-body ul li{
	list-style: disc;
}
.version-modal .version-model-body ol,
.version-modal .version-model-body ol li{
	list-style: decimal;
}

.version-header{
	height: 70px;
	text-align: center;
	background-color: #f3f3f3;
}
.version-publish-date{
	color: #ac7b53;
	margin-bottom: 10px;
}
.version-sure-btn{
	margin-left:40%;
	margin-right:40%;
	background-color: #ff900c;
	color: #fff;
	font-size: 13px;
	padding: 10px;
	border-radius:5px;
	cursor: pointer;
	
}
</style>
		<div id="footer">
</div>
</body></html>