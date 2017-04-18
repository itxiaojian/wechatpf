<!--  
*****************************************************************
 *description   : 图片素材
******************************************************************
-->
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
	<meta charset="utf-8" />
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-lang-zh_CN.js"></script>

    <!-- 添加这2个文件用来支持GRID复制 -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ux/gridCopy/gridCopy.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ux/gridCopy/gridCopy.js"></script>
        
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/weixin_icons.css" />
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxForm.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxGrid.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxVtypes.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/weixin/staticData.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ux/fileuploadfield/css/fileuploadfield.css" />
	<script type="text/javascript" src="<%=path%>/resources/js/ux/fileuploadfield/FileUploadField.js"></script>
		
	<script type="text/javascript" src="<%=path%>/resources/js/weixin/mass/imageMaterial.js"></script>
	
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	
	
	<style>
	#nritem {
		text-align: center;
		border: #cbcbcc solid 1px;
	}
	#nritem ul {
		margin: 0px;
		padding: 0px;
		list-style-type: none;
	}
	#nritem ul li {
		float:left;
		display: inline;
		margin-left:5px;
		margin-right:5px;
	}
	#nritem ul img
	{
		display:block;
		margin:0 auto; 
		border:0;
	}
	#break{
		clear:both;
	}
	</style>
</head>
<body style="background-color: #ffffff;">


	<input type="hidden" name="projectName" id="projectName" value="${pageContext.request.contextPath}"/>
	

	<div class="clear"></div>
	</div>
	
	<script type="text/javascript">
		var PROJECT_NAME=$("#projectName").val();//获取跟目录
	</script>
</body>
</html>
