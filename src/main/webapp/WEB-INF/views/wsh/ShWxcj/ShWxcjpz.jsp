<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <script type="text/javascript">var PATHNAME="<%=path%>"</script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/index.css" />
    
    <script type="text/javascript" src="<%=path%>/resources/js/ux/Ext.ux.TreeCombo.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.ExtField.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/Ext.ux.PagePlugins.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.ViewGrid.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxForm.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxGrid.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ClerkSingelSelect.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wsh/ShWxcjpz.js"></script>
  </head>
  <body>
  	<div style="display:none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="抽奖配置">
			<a href="<%=path%>/wsh/ShWxcjpz/index" target="content" onfocus="this.blur()"><span>抽奖配置</span></a>
			</li>
		</ul>
	</div>
  </body>
</html>
