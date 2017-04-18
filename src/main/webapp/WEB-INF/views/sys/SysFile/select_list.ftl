<#-----author:lxt------->
<#-----date:2014-11-10 13:59:31------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
function doSelect(){
	var length = $("input:checked").length;
	if (length == 0) {
		alert("请选择!");
		return;
	} else if (length > 1) {		
		alert("请选择一条记录!");
		return;
	} else if (($(".nodata",$("input:checked").parent().parent()).length > 0)) {
		alert("选择无效!");
		return;
	}
	
	 var href = $("input:checked").parent().parent().find("a").attr("href");
	 var name = $("input:checked").parent().parent().find("a").text();
	 parent.doSelect(href,name);
}
</script>
	<form method="post" id="searchForm" action="${base}/sys/SysFile/selectList">
	<input type="hidden" id="startId" name="start" value="${(pager.start?default(0))!}"/>	
	<input type="hidden" id="limitId" name="limit" value="${(pager.pageSize)?default(0)}"/>
	<#if params['Q_folderId_S_EQ']?exists>
	<input type="hidden" name="Q_folderId_S_EQ" value="${(params['Q_folderId_S_EQ'])!}"/>
	<#elseif params['Q_folderId_S_NULL']?exists>
	<input type="hidden" name="Q_folderId_S_NULL" value="NULL"/>
	</#if>
	<table>
		<tr>
		<td>文件名</td><td><input type="text" name="Q_fileName_S_LK" value="${params['Q_fileName_S_LK']!}" class="textinput"/></td>
		<td>创建时间</td><td><input type="text" name="Q_id_S_LFK" value="${params['Q_id_S_LFK']!}" class="date" datefmt="yyyyMMdd"/></td>
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
 	<div class="box_tool_min">
	<div class="center">
		<a href="javascript:;" onclick="doSelect()"><span class="icon_ok">确认</span></a>
	</div>
	</div> 
	<div id="scrollContent" style="height:380px;">
		<table class="tableStyle" mode="list" style="width:600px;">
			<thead>
				<tr>
				<th></th>
			  	<th class="hide">id</th>
			  	<th>文件名</th>
			  	<th>文件类型</th>
			  	<th>扩展名</th>
			  	<th>文件大小</th>
			  	<th>上传人名称</th>
				</tr>
			</thead>
			<#if (pager.items)?exists && ((pager.items)?size != 0)>
			<#list pager.items as entity>
			<tbody>
			<tr>
			<td><input type="checkbox"/></td>
				<td class="pk hide" id="id" val="${(entity.id)!}">${(entity.id)!}</td>
				<td><a href="${setting['files.contextpath']!}/${(entity.path)!}" target="_blank">${(entity.fileName)!}</a></td>
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
