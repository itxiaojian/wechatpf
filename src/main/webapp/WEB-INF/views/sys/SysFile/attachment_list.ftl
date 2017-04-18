<#-----author:lxt------->
<#-----date:2014-11-10 13:59:31------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
function doSelect(){
	var length = $(".tableStyle input:checked").length;
	if (length == 0) {
		alert("请选择!");
		return;
	<#if params['single']?default('')==''>
	} else if (length > 1) {		
		alert("请选择一条记录!");
		return;
	</#if>
	} else if (($(".nodata",$("input:checked").parent().parent()).length > 0)) {
		alert("选择无效!");
		return;
	}
	
	<#if params['single']?default('')==''>
	var file = {};
	var tr =  $(".tableStyle input:checked").parent().parent();
	 file['id'] =$(tr).attr("id"); 
	 file['fileName'] =$(tr).attr("fileName"); 
	 file['path'] =$(tr).attr("path"); 
	 file['url'] =$(tr).attr("url"); 
	 file['type'] =$(tr).attr("type"); 
	 file['extension'] =$(tr).attr("extension"); 
	 file['size'] =$(tr).attr("size"); 
	 top.put("file",file);
	<#else>
	var files = [];
	$(".tableStyle input:checked").each(function(index){
		var tr =  $(this).parent().parent();
		var file = {};
		 file['id'] =$(tr).attr("id"); 
		 file['fileName'] =$(tr).attr("fileName"); 
		 file['path'] =$(tr).attr("path"); 
		 file['url'] =$(tr).attr("url"); 
		 file['type'] =$(tr).attr("type"); 
		 file['extension'] =$(tr).attr("extension"); 
		 file['size'] =$(tr).attr("size"); 
		 files[index]=file;
	});
	 top.put("files",files);
	</#if>
	
	 top.closeView();
}
</script>
	<form method="post" id="searchForm" action="${base}/sys/SysFile/attachmentList">
	<input type="hidden" id="startId" name="start" value="${(pager.start?default(0))!}"/>	
	<input type="hidden" id="limitId" name="limit" value="${(pager.pageSize)?default(0)}"/>
	<input type="hidden" name="refObj" value="${(params['Q_refObj_S_EQ'])?default(params['refObj'])!}"/>
	<input type="hidden" name="refId" value="${(params['Q_refId_S_EQ'])?default(params['refId'])!}"/>
	<input type="hidden" name="single" value="${(params['single'])!}"/>
	<table>
		<tr>
		<td>文件名</td><td><input type="text" name="Q_fileName_S_LK" value="${params['Q_fileName_S_LK']!}" class="textinput"/></td>
		<td>描述</td><td><input type="text" name="Q_descn_S_LK" value="${params['Q_descn_S_LK']!}" class="textinput"/></td>
		<td><input type="checkbox" name="all" value="1" <#if params['all']?default('0') = '1'>checked="true"</#if>/>查询所有</td>
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
 	<div class="box_tool_min">
	<div class="center">
		
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysFile/add',params:{refObj:'${(params["Q_refObj_S_EQ"])?default(params["refObj"])!}',refId:'${(params["Q_refId_S_EQ"])?default(params["refId"])!}'},callback:refreshList})"><span class="icon_ok">附件上传</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysFile/edit',mutiple:false})"><span class="icon_edit">编辑</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysFile/delete',confirm:true,mutiple:true})"><span class="icon_delete">删除</span></a>
		<a href="javascript:;" onclick="doSelect()"><span class="icon_ok">确认选择</span></a>
	</div>
	</div> 
	<div id="scrollContent" style="height:380px;">
		<table class="tableStyle" mode="list" style="width:600px;">
			<thead>
				<tr>
				<th></th>
			  	<th class="hide">id</th>
			  	<th>文件名</th>
			  	<th>描述</th>
			  	<th>文件类型</th>
			  	<th>扩展名</th>
			  	<th>文件大小</th>
			  	<th>上传人名称</th>
				</tr>
			</thead>
			<#if (pager.items)?exists && ((pager.items)?size != 0)>
			<#list pager.items as entity>
			<tbody>
			<tr id="${(entity.id)!}" url="${(entity.path)!}" path="${setting['files.contextpath']!}/${(entity.path)!}" type="${(entity.type)!}" extension="${(entity.extension)!}" size="${(entity.size)!}" fileName="${(entity.fileName)!}">
			<td><input type="checkbox"/></td>
				<td class="pk hide" id="id" val="${(entity.id)!}">${(entity.id)!}</td>
				<td><a href="${setting['files.contextpath']!}/${(entity.path)!}" target="_blank">${(entity.fileName)!}</a></td>
				<td>${(entity.descn)!}</td>
				<td>${(entity.type)!}</td>
				<td>${(entity.extension)!}</td>
				<td>${(entity.size)!}</td>
				<td>${(entity.username)!}</td>
			</tr>
		 	</#list>
		 	<#else>
		 		<tr><td><input type="checkbox"/></td><td colspan="9" class="nodata">无记录</td></tr>
		 	</#if>
		 	</tbody>
		</table>
	</div>
	<#if pager?exists>
		<@com.pagination pager=pager/>
	</#if>
</#escape>
</@com.page>
