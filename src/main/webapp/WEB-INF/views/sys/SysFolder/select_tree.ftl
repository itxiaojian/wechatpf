<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
 	<meta content="no-cache" http-equiv="Pragma" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="0" http-equiv="Expires" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta charset="utf-8" />
	<title></title>

<link rel="stylesheet" href="${base}/resources/js/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${base}/resources/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${base}/resources/js/app.js"></script>

<#escape x as x?html>  
<script type="text/javascript">

		var setting = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
            	onClick:onClick
			}
		};

		var zNodes =[
			{ id:'0', name:"全部文件",url:"${base}/sys/SysFile/selectList", target:"documentFrame",open:true},
			<#list folders?if_exists as doc>
			{ id:'${doc.id}', <#if doc.parentId?has_content>pId:"${doc.parentId}",</#if> name:"${(doc.name)!}",url:"${base}/sys/SysFile/selectList?Q_folderId_S_EQ=${doc.id}", target:"documentFrame",icon:"${base}/libs/images/icons/folder.gif",open:true},
			</#list>
			{ id:'-1', pId:'0',name:"未分类",url:"${base}/sys/SysFile/selectList?Q_folderId_S_NULL=null", target:"documentFrame",icon:"${base}/libs/images/icons/folder.gif",open:true},
		];
		var log, className = "dark";
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0|| treeNode.id == "-1") return;
			
			if (treeNode.id != "0"){
				var removeStr = "<span class='button remove' id='removeBtn_" + treeNode.id
					+ "' title='删除' onfocus='this.blur();'></span>";
				sObj.after(removeStr);
				var removeBtn = $("#removeBtn_"+treeNode.id);
				if (removeBtn) removeBtn.bind("click", function(){
					onAction({url:'${base}/sys/SysFolder/delete',confirm:true,params:{ids:treeNode.id},callback:refresh});
					return false;
				});
				var editStr = "<span class='button edit' id='editBtn_" + treeNode.id
					+ "' title='增加子文件夹' onfocus='this.blur();'></span>";
				sObj.after(editStr);
				var editBtn = $("#editBtn_"+treeNode.id);
				if (editBtn) editBtn.bind("click", function(){
					onAction({url:'${base}/sys/SysFolder/edit?id='+treeNode.id,callback:refresh});
					return false;
				});
			}
			var addStr = "<span class='button add' id='addBtn_" + treeNode.id
				+ "' title='增加子文件夹' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var addBtn = $("#addBtn_"+treeNode.id);
			if (addBtn) addBtn.bind("click", function(){
				onAction({url:'${base}/sys/SysFolder/add?pid='+treeNode.id,callback:refresh});
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.id).unbind().remove();
			$("#removeBtn_"+treeNode.id).unbind().remove();
			$("#editBtn_"+treeNode.id).unbind().remove();
		};
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		}
		
		 function showContent() {
		        var treeNodeId = $.cookie('nodeId');
		        if (treeNodeId == false || treeNodeId == "false") {
		        }
		        else {
		            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		            var node = zTree.getNodeByParam("id", treeNodeId);
		            zTree.selectNode(node);
		            if (treeNodeId == "-1") {
			           parent.documentFrame.location= "${base}/sys/SysFile/selectList?Q_folderId_S_NULL=null";
		            } else if (treeNodeId == "0"){
			           parent.documentFrame.location= "${base}/sys/SysFile/selectList";
		            } else {
			           parent.documentFrame.location= "${base}/sys/SysFile/selectList?Q_folderId_S_EQ="+treeNodeId;
		            }
		        }
		    }
    
    function refresh() {
		location= '${base}/sys/SysFolder/tree';
    }
    function onClick(e, treeId, treeNode) {
        //存储点击节点id
        if (top.put){
        	top.put("node", treeNode);
        }
        
        $.cookie('nodeId', treeNode.id.toString());
    }
    
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			showContent();
		});
	</SCRIPT>
	<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</HEAD>

<BODY>
	<div class="left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>

</#escape>
</BODY>
</HTML>
