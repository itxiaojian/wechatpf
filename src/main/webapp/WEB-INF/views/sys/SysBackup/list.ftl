<#-----author:lxt------->
<#-----date:2014-12-08 10:35:36------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html>
<script type="text/javascript">
	$(document).ready(function(){
		/**/
	});
	function backUp(){
		$.ajax({
			url:"${base}/sys/SysBackup/backUp",
			dataType:"json",
			type:"get",
			beforeSend:ajaxLoading,
			success	:function(data){
				if(data.code=='1'){
					top.Dialog.alert("备份成功!");
				} else {
					top.Dialog.alert(data.msg);
				}
				ajaxLoadEnd();
			}
		});
	}
	function regain(){
		var length = $("input:checked").length;
		if (length == 0) {
			top.Dialog.alert("请选择!");
			return;
		} else if (length > 1) {		
			top.Dialog.alert("请选择一条记录!");
			return;
		} else if (($(".nodata",$("input:checked").parent().parent()).length > 0)) {
			top.Dialog.alert("选择无效!");
			return;
		}
		top.Dialog.confirm("确认还原数据库吗？",function(){
			var fileName=$("input:checked").parent().parent().find(".pk").attr("val");
			$.ajax({
				url:"${base}/sys/SysBackup/regain?fileName="+fileName,
				dataType:"json",
				type:"get",
				beforeSend:ajaxRegaining,
				success	:function(data){
					if(data.code=='1'){
						top.Dialog.alert("还原成功!");
					} else {
						top.Dialog.alert(data.msg);
					}
					ajaxLoadEnd();
				}
			});
		});
	}
	function ajaxLoading(){
		try{
			showProgressBack();
		}catch(e) {
		
		} 
	}
	function ajaxRegaining(){
		try{
			showProgressRegain();
		}catch(e) {
		
		} 
	}  
	function ajaxLoadEnd(){ 
		refreshList();              
	} 
</script>  
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="sys_backup">
	<a href="${base}/sys/SysBackup/list" target="content" onfocus="this.blur()"><span>数据备份</span></a>
	</li>
</ul>
</div>
	<form method="post" id="searchForm" action="${base}/sys/SysBackup/list">
	<input type="hidden" id="startId" name="start" value="${(pager.start?default(0))!}"/>	
	<input type="hidden" id="limitId" name="limit" value="${(pager.pageSize)?default(0)}"/>
	<table>
		<tr>
		<td>文件备份名</td><td><input type="text" name="Q_fileName_S_LK" value="${params['Q_fileName_S_LK']!}" class="textinput"/></td>
		<td>文件备份时间</td><td><input type="text" name="Q_backupTime_D_GT" value="${params['Q_backupTime_D_GT']!}" class="date" datefmt="yyyyMMdd"/></td>
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
	<div class="box_tool_min">
	<div class="center">
		<a href="javascript:;" onclick="backUp()"><span class="icon_add">备份</span></a>
		<a href="javascript:;" onclick="regain()"><span class="icon_edit">还原</span></a>
	</div>
	</div> 
	<div id="scrollContent">
		<table class="tableStyle" mode="list" style="width:430px;">
			<thead>
				<tr>
				<th></th>
			  	<th class="hide">id</th>
			  	<th width="500">文件备份名</th>
			  	<th width="500">文件备份时间</th>
				</tr>
			</thead>
			<#if (pager.items)?exists && ((pager.items)?size != 0)>
			<#list pager.items as entity>
			<tbody>
			<tr>
				<td><input type="checkbox"/></td>
				<td class="pk hide" id="id" val="${(entity.fileName)!}">${(entity.fileName)!}</td>
				<td>${(entity.fileName)!}</td>
				<td>${(entity.backTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
			</tr>
		 	</#list>
		 	<#else>
		 		<tr><td><input type="checkbox"/></td><td colspan="4" class="nodata">无记录</td></tr>
		 	</#if>
		 	</tbody>
		</table>
	</div>
	<#if pager?exists>
		<@com.pagination pager=pager/>
	</#if>
</#escape>
</@com.page>
