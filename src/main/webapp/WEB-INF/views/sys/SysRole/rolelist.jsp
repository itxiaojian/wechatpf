<!--  
*****************************************************************
 *description   : 自定义菜单管理
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
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxForm.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.ExtField.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/Ext.ux.PagePlugins.js"></script>
	
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxGrid.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/RowExpander.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxVtypes.js"></script>
		
	<script type="text/javascript" src="<%=path%>/resources/js/sys/rolelist.js"></script>

 	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
 	
    <script src="<%=path%>/resources/js/app.js" type="text/javascript" ></script>
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

	<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="菜单管理"><a
				href="<%=path%>/wxauth/menu/menuPage" target="content"
				onfocus="this.blur()"><span>系统用户管理</span></a></li>
		</ul>
	</div>

	<input type="hidden" name="projectName" id="projectName" value="${pageContext.request.contextPath}"/>

	<div class="clear"></div>
	</div>
	
	<script type="text/javascript">
	
	var PROJECT_NAME=$("#projectName").val();//获取跟目录
	var mycdbhs = "${mycdbhs}";
	var root = new Ext.tree.TreeNode({expanded:true,text:'系统菜单',id:'0'});
	
	<c:forEach var="fun" items="${context['resources.functionList']}" varStatus="status">
		<c:if test="${fun.parentId=='0'}">
			var cdbh = ','+${fun.id}+',';
			if(cdbh.indexOf(mycdbhs)!=-1){
				var node = new Ext.tree.TreeNode({expanded:true,checked:true,text:'${fun.name}',id:${fun.id}});
			}else{
				var node = new Ext.tree.TreeNode({expanded:true,checked:false,text:'${fun.name}',id:${fun.id}});
			}
			
			<c:forEach var="funchild" items="${context['resources.functionList']}" varStatus="status">
				<c:if test="${funchild.parentId!='0'}">
					<c:if test="${funchild.parentId == fun.id}">
						var childcdbh = ','+${funchild.id}+',';
						if(cdbh.indexOf(mycdbhs)!=-1){
							var nodechild = new Ext.tree.TreeNode({leaf:true,checked:true,text:'${funchild.name}',id:${funchild.id}});
						}else{
							var nodechild = new Ext.tree.TreeNode({leaf:true,checked:false,text:'${funchild.name}',id:${funchild.id}});
						}
						node.appendChild(nodechild)
					</c:if>
				</c:if>
			</c:forEach>
			root.appendChild(node);
		</c:if>
	</c:forEach>
	
	
	
<%-- 	var root = new Ext.tree.TreeNode({text:'菜单'},id:'0');
	var i==1;
	<c:forEach var="fun" items="${context['resources.functionList']}" varStatus="status">
		<c:if test="${fun.parentId=='0'}">
			var thisid = ${fun.id};
			var node+i = new Ext.tree.TreeNode({})
		</c:if>
	</c:forEach>
	
	var zNodes =[
		<c:forEach var="fun" items="${context['resources.functionList']}" varStatus="status">
		<c:if test="${fun.parentId=='0'}">{ id:${fun.id}, parentId:${fun.parentId}, name:"${fun.name}",iconOpen:"<%=path%>/system/layout/skin/titlebar_open.jpg",iconClose:"<%=path%>/system/layout/skin/titlebar_close.jpg"},</c:if>
		<c:if test="${fun.parentId!='0'}">{ id:${fun.id}, parentId:${fun.parentId}, name:"${fun.name}",url:"<%=path%>${fun.url}", target:"content",title:"${fun.name}",iconOpen:"<%=path%>/libs/icons/tree_close.gif",iconClose:"<%=path%>/libs/icons/tree_open.gif",icon:"<%=path%>/system/layout/<c:if test="${fun.iconcls !=null}">skin/${fun.iconcls}.png</c:if><c:if test="${fun.iconcls ==null}">skin/default.png</c:if>"},</c:if>
		</c:forEach>
	]; --%>
	 
	</script>
</body>
</html>
