<!--  
*****************************************************************
 *description   : 授信申请信息(包括客户基本信息、家庭信息、担保人信息、抵押物信息)
******************************************************************
-->
<%@page contentType="text/html;charset=UTF-8"%>
<% String path = request.getContextPath();%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <script type="text/javascript">var PATHNAME="<%=path%>"</script>
    <link rel="stylesheet" type="text/css" href="/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="/resources/js/ext/ext-lang-zh_CN.js"></script>
    
	<link rel="stylesheet" type="text/css" href="/resources/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/index.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/cps_icons.css" />
	<script type="text/javascript" src="/resources/js/ux/Ext.ux.TreeCombo.js"></script>
	<script type="text/javascript" src="/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="/resources/js/ux/ST.ux.ExtField.js"></script>	
	<script type="text/javascript" src="/resources/js/ux/Ext.ux.PagePlugins.js"></script>
	<script type="text/javascript" src="/resources/js/ux/uxForm.js"></script>
	<script type="text/javascript" src="/resources/js/ux/uxGrid.js"></script>
	<script type="text/javascript" src="/resources/js/ux/uxVtypes.js"></script>
	<script type="text/javascript" src="/resources/js/cps/staticData.js"></script>
	<script type="text/javascript" src="/resources/js/cps/form/creditForm.js"></script>
	<style type="text/css">
		.override_label{
			margin:2 0 0 5px;
		}
		
		a {
			cursor:pointer;
		}
	</style>
  </head>
   <%	String creditId = request.getParameter("creditId"); //用户ID
    String customerId = request.getParameter("customerId"); 
  %>
  <body>
  <input type="hidden" value="<%=creditId%>" name="creditId">
  <input type="hidden" value="<%=customerId%>" name="customerId">
  <div id="mainDiv"></div>
  </body>
</html>