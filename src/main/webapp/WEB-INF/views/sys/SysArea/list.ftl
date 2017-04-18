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
<script type="text/javascript" src="${base}/resources/js/app.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.cookie.js"></script>

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
			{ id:'0', name:"中国",open:true},
			<#list area?if_exists as doc>
			{ id:'${doc.id}', <#if doc.sjbh?has_content>pId:"${doc.sjbh}",</#if> name:"${(doc.bmmc)!}",icon:"${base}/libs/images/icons/folder.gif",open:true}<#if doc_index != (area?size -1 )>,</#if>
			</#list>
		];
		var log, className = "dark";
		
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
					onAction({url:'${base}/sys/SysArea/delete?ids='+treeNode.id,callback:refresh,confirm:true});
					return false;
				});
				var editStr = "<span class='button edit' id='editBtn_" + treeNode.id
					+ "' title='修改' onfocus='this.blur();'></span>";
				sObj.after(editStr);
				var editBtn = $("#editBtn_"+treeNode.id);
				if (editBtn) editBtn.bind("click", function(){
					onAction({url:'${base}/sys/SysArea/edit?id='+treeNode.id,callback:refresh});
					return false;
				});
			}
			var addStr = "<span class='button add' id='addBtn_" + treeNode.id
				+ "' title='添加' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var addBtn = $("#addBtn_"+treeNode.id);
			if (addBtn) addBtn.bind("click", function(){
				onAction({url:'${base}/sys/SysArea/add?pid='+treeNode.id,callback:refresh});
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
		
		
    function refresh() {
		location= '${base}/sys/SysArea/list';
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
		});
	</SCRIPT>
	<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</HEAD>

<BODY>
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="组织机构">
	<a href="${base}/sys/SysArea/list" target="content" onfocus="this.blur()"><span>组织机构</span></a>
	</li>
</ul>
</div>
	<div class="left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>

</#escape>
</BODY>
</HTML>
