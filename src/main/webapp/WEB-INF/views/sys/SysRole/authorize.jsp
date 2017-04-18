<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
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

$(function(){
	var roleId = $("#sysRoleId").val();
	initSourceTree();
	showAll();
	$("#button1").click(function(){
		 var resourceIds  = getSelectValue();
		 if(!resourceIds){
			 top.Dialog.alert("没有选择数据");
		 }else{
			 $("#role_id").val(roleId);
			 $("#resource_id").val(resourceIds);
			 $("#MyForm").submit();
		 }
	})
	
})



var setting = {
		
		check: {
   			 enable: true
		},
		chkboxType:{
			"Y":"p",
			"N":"p"
		}


	};
	
	var zNodes =[<c:forEach var="fun" items="${alllist}" varStatus="status">
	<c:if test="${fun.sjcd=='0'}">{ id:${fun.cdbh}, parentId:${fun.sjcd}, name:"${fun.cdmc}",iconOpen:"<%=path%>/system/layout/skin/titlebar_open.jpg",iconClose:"<%=path%>/system/layout/skin/titlebar_close.jpg"},</c:if>
	<c:if test="${fun.sjcd!='0'}">{ id:${fun.cdbh}, parentId:${fun.sjcd}, name:"${fun.cdmc}", target:"content",title:"${fun.cdmc}",iconOpen:"<%=path%>/libs/icons/tree_close.gif",iconClose:"<%=path%>/libs/icons/tree_open.gif",class:"<c:if test="${fun.bz !=null}">${fun.bz}</c:if><c:if test="${fun.bz ==null}">skin/default.png</c:if>"},</c:if>
	</c:forEach>];
	
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
				}else{
					top.positionType="none";
				}
				top.frmright.location=node.url;
			}
		}
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
		$("#scrollContent").height(contentHeight-40);
	}
	
	function getSelectValue(){
	    //获取zTree对象
	    var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
	    //得到选中的数据集
	    var checkedNodes = treeObj.getCheckedNodes(true);
	    var msg = "";
	    if($(checkedNodes).size()>0){
	    for(var i = 0; i < $(checkedNodes).size(); i++){
	    	msg += "," + $(checkedNodes).get(i).id;
	    }
	    }
	    if(msg == ""){
	    	
	    }else{
	        msg = msg.substring(1);
	    }
	    return msg;
	}
	
	//初始化SourceTree
	function initSourceTree(){
	    //获取zTree对象
	    var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
	    var roleResourceArray = $("#roleResourceIds").val().split(",");
	    //得到选中的数据集
	    if(roleResourceArray){
	    var unCheckedNodes = treeObj.getCheckedNodes(false);
	    for(var i = 0; i < unCheckedNodes.length; i++){
	    	for(var j = 0; j < roleResourceArray.length; j++){
	    		if(unCheckedNodes[i].id==roleResourceArray[j]){
	    			var node = treeObj.getNodeByParam("id", unCheckedNodes[i].id);
	    			if(unCheckedNodes[i].parentId){
	    				treeObj.checkNode(node,"checked","true");
	    			}
	    			
		    	}
	    	}
	    }
	    }
	}
	
	
</script>
</head>

<body leftFrame="true">
<%
     String roleResourceIds = (String)request.getAttribute("roleResourceIds");
	 String sysRoleId = (String)request.getParameter("roleId");
%>
<input type="hidden" id="sysRoleId" value="<%=sysRoleId%>"/>
<input type="hidden" id="roleResourceIds" value="<%=roleResourceIds%>"/>
<div id="scrollContent" style="overflow-x:hidden;">
	<div>
		<ul id="treeDemo" class="ztree ztree_accordition" style="background-color: rgb(255, 255, 255);"></ul>
	</div>
</div>	
<div style="position:absolute;left:0;bottom:0;width:100%;height:100;"> 
	<form action="<%=path%>/sys/SysRole/submitResource" id="MyForm" method="post">
	<table class="tableStyle" formMode="view">
	<input type="hidden" name="roleId" id="role_id" value=" "/>
	<input type="hidden" name="resourceIds" id="resource_id" value=" "/>
	<tr>
			<td class="viewModeEven" colspan="4" style="text-align: center; background-color: rgb(255, 255, 255); padding-top: 6px; padding-bottom: 6px;">
				<input class="button" type="button" id="button1" value="提交" style="width: 60px;">
				<input class="button" type="button" onclick="window.parent.ACT_DEAL_WINDOW.close()" value="取消" style="width: 60px;">
			</td>
	</tr>
	</table>
	</form>
</div>
</body>
</html>