<!--  
*****************************************************************
 *description   : 素材管理功能,包括图文消息、图片、语音、视频
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
	<script type="text/javascript" src="<%=path%>/resources/js/login/jquery.js"></script>	
	
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	
	<style type="text/css"> .ext-strict{height:100%;} </style>
	<script type="text/javascript">
		
		Ext.onReady(function(){
			  // second tabs built from JS
		var height = $(window).height();
		var width = $(window).width();
		    var tabs = new Ext.TabPanel({
		        renderTo: document.body,
		        activeTab: 0,
		        width:width,
		        height:height,
		        viewConfig: {
	                forceFit: false
	            },
		        plain:true,
		        defaults:{autoScroll: true},
		        items:[ {
		                title: '微主页', 
		                 html:'<iframe src="<%=path%>/wzy/ZyCdpt/zyCdptViewAll?mklx=1" width="100%" height="100%" style="border:0;"></iframe>',
		                 id : 'tp_tab'
		            },
		            {
		                title: '招生专栏',  
		                 html:'<iframe src="<%=path%>/wzy/ZyCdpt/zyCdptViewAll?mklx=0" width="100%" height="100%" style="border:0;"></iframe>',
		                 id : 'zs_tab'
		            } ,
		             {
		                title: '微服务', 
		                html:'<iframe src="<%=path%>/wzy/ZyCdpt/zyCdptViewAll?mklx=2" width="100%" height="100%" style="border:0;"></iframe>' ,
		                id : 'yy_tab'
		            } ,
		            {
		                title: '微生活', 
		                html:'<iframe src="<%=path%>/wzy/ZyCdpt/zyCdptViewAll?mklx=3" width="100%" height="100%" style="border:0;"></iframe>' ,
		                id : 'sp_tab'
		            } 
		        ]
		    });
			});
	
	
	</script>
	
	
  </head>
  <body>
  		<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="微信主页菜单配置"><a
				href="<%=path%>/wzy/ZyCdpt/cdptPageAll" target="content"
				onfocus="this.blur()"><span>微信主页菜单配置</span></a></li>
		</ul>
	</div>

	<input type="hidden" name="projectName" id="projectName" value="${pageContext.request.contextPath}"/>
	

	<div class="clear"></div>
	</div>
	
		<script type="text/javascript">
			var PROJECT_NAME=$("#projectName").val();//获取跟目录
		</script>
  </body>
</html>

