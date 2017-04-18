<#-----author:lxt------->
<#-----date:2014-11-10 13:59:31------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html>  
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="附件管理">
	<a href="${base}/sys/SysFile/list" target="content" onfocus="this.blur()"><span>附件管理</span></a>
	</li>
</ul>
</div>
	<form method="post" id="searchForm" action="${base}/sys/SysFile/attachments">
	<input type="hidden" id="startId" name="start" value="${(pager.start?default(0))!}"/>	
	<input type="hidden" id="limitId" name="limit" value="${(pager.pageSize)?default(0)}"/>
	<table>
		<tr>
		<td>文件名</td><td><input type="text" name="Q_fileName_S_LK" value="${params['Q_fileName_S_LK']!}" class="textinput"/></td>
		<td>上传人</td><td><input type="text" name="Q_username_S_LK" value="${params['Q_username_S_LK']!}" class="textinput"/></td>
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
 	<div class="box_tool_min">
	<div class="center">
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysFile/delete',confirm:true,mutiple:true})"><span class="icon_delete">删除</span></a>
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
			  	<th>载体</th>
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
				<td>${(entity.refObj)!}${("#"+entity.refId)!}</td>
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
