<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class=" js ">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<meta name="viewport"
		content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta http-equiv="Cache-Control" content="no-cache"></meta>
<meta http-equiv="Cache-Control" content="no-store"></meta>
<meta http-equiv="Expires" content="0"></meta>
<title>报名信息</title>
<link href="<%=path%>/resources/skin/base.css" rel="stylesheet"></link>
	<link href="<%=path%>/resources/skin/wjend.css" rel="stylesheet"></link>
		<link href="<%=path%>/resources/skin/darkblue.css" rel="stylesheet"></link>
			<link href="<%=path%>/resources/skin/hcheckbox.css" rel="stylesheet"></link>
<style type="text/css">
.ma_auto {
	width: none;
}

.instructions div {
	float: none;
}

.wjtext {
	text-align: center;
}

.wjtext a {
	display: inline;
}


.control-group::before, .form-horizontal .control-group::after {
    content: "";
    display: table;
}
.control-group::after {
    clear: both;
}
.control-group::before, .form-horizontal .control-group::after {
    content: "";
    display: table;
}
.control-group {
    margin-bottom: 18px;
}
fieldset{
    padding: 0;
}
.control-label {
    float: left;
/*     padding-top: 5px; */
    text-align: right;
    width: 80px;
}
label {
    display: block;
    margin-bottom: 5px;
    margin-top: 10px;
}
label, input, button, select, textarea {
/*     font-size: 13px; */
    font-weight: normal;
    line-height: 18px;
    margin-top: 10px;
}
.control-label .required {
    color: #f00;
}

.btn-success {
    background-color: #5bb75b;
    background-image: -moz-linear-gradient(center top , #62c462, #51a351);
    background-repeat: repeat-x;
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
    cursor: pointer;
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-image: none;
    border-radius: 4px;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05);
    font-size: 13px;
    line-height: 18px;
    padding: 4px 10px;
    text-align: center;
    vertical-align: middle;
    color: #fff;
}
</style>

	<script type="text/javascript"
		src="<%=path%>/resources/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript">
		function back(openId){
			location.href ='<%=path%>/wsh/ShWbm/toWbmSjList?openId='+openId;
		}
	</script>
	<!--加载提示-->
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/skin/load.css"></link>
		<script type="text/javascript" src="<%=path%>/resources/skin/load.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<p style="display: none;" class="hei_cq" _hei="1323" id="body"></p>

	<div class="wjContent clear" id="survey_page"
		style="width: 85%;">
		<div class="content" id="begin_content">


			<div class="wjtitle mtop project_title">
				<h1>报名信息</h1>
			</div>
			<div class="wjintro mtop desc_begin">
				<p>报名详细信息</p>
			</div>

			<div class="wjhr mtop" ></div>

		</div>
    <fieldset style="border: 0 none;margin: 0;padding: 0;">
    <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01"><span class="required">*</span>报名标题：</label>
          <div class="controls">
            <input type="text" name="activity_name" style="width: 40%;" placeholder="" class="input-xlarge" value="${map.bmbt }" disabled="disabled"/>
            <p class="help-block"></p>
          </div>
     </div>
        
        
      <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">报名介绍：</label>
          <div class="controls">
            <div class="textarea">
                  <textarea type="" class="" name="description" cols="10" rows="4" style="width: 40%;margin-left: 0px;" disabled="disabled">${map.bmjs }</textarea>
            </div>
          </div>
      </div>
      
		<div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01"><span class="required">*</span>报名截止时间：</label>
          <div class="controls end-at">
          	<input id="date11" type="text" style="width: 40%;" name="end_date" value="${time }" disabled="disabled"/>
            <p class="help-block"></p>
          </div>
      </div>
      
      <div class="control-group">

     </div>

    <div class="control-group">
          <label class="control-label"></label>

          <!-- Button -->
          <div class="controls">
             <input id="cancel_button" type="button" class="btn-success" value="返回" onclick="back('${openId}');"/>
          </div>
     </div>
    </fieldset>
	</div>
	<!--wjContent end-->
</body>
</html>