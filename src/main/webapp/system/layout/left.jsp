<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String path = request.getContextPath();response.addHeader("X-Frame-OPTIONS", "SAMEORIGIN");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->

<!-- 树型抽屉导航start-->
<script type="text/javascript" src="<%=path%>/libs/js/tree/ztree/ztree.js"></script>
<link href="<%=path%>/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/libs/js/nav/treeAccordion_normal.js"></script>
<!-- 树型抽屉导航end -->
 <style>
	.ztree li span.zbutton.diy01_ico_open, .ztree li span.zbutton.diy01_ico_close{width:24px!important;height:24px!important;padding-top:0;}
</style>
<script type="text/javascript">

var setting = {
		callback: {
			onClick: onClick
		}
	};
	
	var zNodes =[<c:forEach var="fun" items="${context['resources.functionList']}" varStatus="status">
				<c:if test="${fun.parentId=='0'}"><sec:authorizeUrl id="${fun.id}">{ id:${fun.id}, parentId:${fun.parentId}, name:"${fun.name}",iconOpen:"skin/titlebar_open.jpg",iconClose:"skin/titlebar_close.jpg"},</sec:authorizeUrl></c:if>
				<c:if test="${fun.parentId!='0'}"><sec:authorizeUrl id="${fun.id}">{ id:${fun.id}, parentId:${fun.parentId}, name:"${fun.name}",url:"<%=path%>${fun.url}", target:"content",title:"${fun.name}",iconOpen:"<%=path%>/libs/icons/tree_close.gif",iconClose:"<%=path%>/libs/icons/tree_open.gif",icon:"<c:if test="${fun.iconcls !=null}">skin/${fun.iconcls}.png</c:if><c:if test="${fun.iconcls ==null}">skin/default.png</c:if>"},</sec:authorizeUrl></c:if>
				</c:forEach>
				];
	function initComplete(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//每次刷新时保持上次打开的
		showContent();
	}
	function showContent(){
		var treeNodeId=jQuery.jCookie('leftTreeNodeId');
		if(treeNodeId==false||treeNodeId=="false"){}
		else{
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("id", treeNodeId);
			zTree.selectNode(node);
			if(node.url){
				//每次刷新时设置当前位置内容
				if(node.name=="当前位置"){
					top.positionType="normal";
					top.positionContent="当前位置："+node.getParentNode().name+">>"+node.name;
				}
				else{
					top.positionType="none";
				}
				top.content.location=node.url;
			}
		}
	}
		
	function onClick(e,treeId, treeNode){
		//单击展开
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandNode(treeNode);
		//出现进度条
		if(treeNode.url!=null&&treeNode.showProgess!=false){
			showProgressBar();
		}
		//可以设置某些页面出现或者某些页面不出现当前位置组件
		if(treeNode.name=="当前位置"){
			//每次点击时设置当前位置内容
			top.positionContent="当前位置："+treeNode.getParentNode().name+">>"+treeNode.name;
			top.positionType="normal";
		}
		else{
			top.positionType="none";
		}
		//存储点击节点id
		jQuery.jCookie('leftTreeNodeId',treeNode.id.toString());
	}

	function  showAll(){
		var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
		treeObj.expandAll(true);
	}
	function  hideAll(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo")
		treeObj.expandAll(false);
	}
	function customHeightSet(contentHeight){
		var windowWidth=document.documentElement.clientWidth;
		$("#scrollContent").width(windowWidth-4);
		$("#scrollContent").height(contentHeight);
	}
	 
</script>
</head>

<body leftFrame="true">
<div id="scrollContent" style="overflow-x:hidden;">
	<div>
		<ul id="treeDemo" class="ztree ztree_accordition"></ul>
	</div>
</div>				
</body>
</html>