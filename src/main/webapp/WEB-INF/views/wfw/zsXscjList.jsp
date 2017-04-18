<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<meta content="no-cache" http-equiv="Pragma" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="0" http-equiv="Expires" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1"></meta>
	<meta charset="utf-8" />
    <script type="text/javascript">var PATHNAME="<%=path%>"</script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ux/fileuploadfield/css/fileuploadfield.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ux/fileuploadfield/FileUploadField.js"></script>
    
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/index.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/cps_icons.css" />
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.ExtField.js"></script>	
	<script type="text/javascript" src="<%=path%>/resources/js/ux/Ext.ux.PagePlugins.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxForm.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxGrid.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wfw/ZsXscj.js"></script>
	
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></script>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>



<style>
.hide{display:none;}
.nodata{color:red;text-align:center;}
#scrollContent{border:1px solid #cccccc;}
</style>
</head>
<body style="background-color:#ffffff;">
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="学生成绩">
	<a href="<%=path%>/wfw/ZsXscj/xscjPage" target="content" onfocus="this.blur()"><span>学生成绩</span></a>
	</li>
</ul>
</div>
</body>
</html>