<#-----author:lxt------->
<#-----date:2014-11-10 14:25:23------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html>  
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="文件管理">
	<a href="${base}/sys/SysFile/list" target="content" onfocus="this.blur()"><span>文件管理</span></a>
	</li>
</ul>
</div>
	<form method="post" id="searchForm" action="${base}/sys/SysFile/list">
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
		<#--<td>扩展名</td><td><input type="text" name="Q_extension_S_LK" value="${params['Q_extension_S_LK']!}" class="textinput"/></td>-->
		<#--<td>文件大小</td><td><input type="text" name="Q_size_L_EQ" value="${params['Q_size_L_EQ']!}" class="textinput"/></td>-->
		<#--<td>对象主键</td><td><input type="text" name="Q_key_S_LK" value="${params['Q_key_S_LK']!}" class="textinput"/></td>-->
		<#--<td>引用对象</td><td><input type="text" name="Q_refObj_S_LK" value="${params['Q_refObj_S_LK']!}" class="textinput"/></td>-->
		<#--<td>上传人ID</td><td><input type="text" name="Q_userId_S_LK" value="${params['Q_userId_S_LK']!}" class="textinput"/></td>-->
		<#--<td>上传人名称</td><td><input type="text" name="Q_username_S_LK" value="${params['Q_username_S_LK']!}" class="textinput"/></td>-->
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
	<div class="box_tool_min">
	<div class="center">
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysFile/add',params:{'folderId':'${params['Q_folderId_S_EQ']!}'}})"><span class="icon_add">新增</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysFile/edit',mutiple:false})"><span class="icon_edit">编辑</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysFile/delete',confirm:true,mutiple:true})"><span class="icon_delete">删除</span></a>
	</div>
	</div> 
	<div id="scrollContent">
		<table class="tableStyle" mode="list" style="width:1130px;">
			<thead>
				<tr>
				<th></th>
			  	<th class="hide">id</th>
			  	<th>文件名</th>
			  	<th width="200">描述</th>
			  	<th>文件路径</th>
			  	<th width="150">创建时间</th>
			  	<th>文件扩展名</th>
			  	<th>文件大小</th>
			  	<th>载体</th>
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
				<td>${(entity.descn)!}</td>
				<td>${(entity.path)!}</td>
				<td>${(entity.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td>${(entity.extension)!}</td>
				<td>${(entity.size)!}</td>
				<td>${(entity.refObj)!}${("#"+entity.refId)!}</td>
				<td>${(entity.username)!}</td>
			</tr>
		 	</#list>
		 	<#else>
		 		<tr><td><input type="checkbox"/></td><td colspan="11" class="nodata">无记录</td></tr>
		 	</#if>
		 	</tbody>
		</table>
	</div>
	<#if pager?exists>
		<@com.pagination pager=pager/>
	</#if>
</#escape>
</@com.page>
