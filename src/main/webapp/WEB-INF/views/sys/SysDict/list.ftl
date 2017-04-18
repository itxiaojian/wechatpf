<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html>  
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="数据字典">
	<a href="${base}/sys/SysDict/list" target="content" onfocus="this.blur()"><span>数据字典</span></a>
	</li>
</ul>
</div>
	<form method="post" id="searchForm" action="${base}/sys/SysDict/list">
	<input type="hidden" id="startId" name="start" value="${(pager.start?default(0))!}"/>	
	<input type="hidden" id="limitId" name="limit" value="${(pager.pageSize)?default(0)}"/>
	<table>
		<tr>
		<td>种类</td><td><input type="text" name="Q_zl_S_LK" value="${params['Q_zl_S_LK']!}" class="textinput"/></td>
		<td>名称</td><td><input type="text" name="Q_zdmc_S_LK" value="${params['Q_zdmc_S_LK']!}" class="textinput"/></td>
		
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
	<div class="box_tool_min">
	<div class="center">
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysDict/add'})"><span class="icon_add">新增</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysDict/detail',mutiple:false})"><span class="icon_view">查看</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysDict/edit',mutiple:false})"><span class="icon_edit">编辑</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysDict/delete',confirm:true,mutiple:true})"><span class="icon_delete">删除</span></a>
	</div>
	</div> 
	<div id="scrollContent">
		<table class="tableStyle" mode="list" style="width:530px;">
			<thead>
				<tr>
				<th></th>
			  	<th class="hide">id</th>
			  	<th>种类</th>
			  	<th>字典名称</th>
			  	<th>字典代码</th>
			  	<th>字典值</th>
			  	<th>级别</th>
			  	<th>排序</th>
				</tr>
			</thead>
			<#if (pager.items)?exists && ((pager.items)?size != 0)>
			<#list pager.items as entity>
			<tbody>
			<tr>
			<td><input type="checkbox"/></td>
				<td class="pk hide" id="id" val="${(entity.id)!}">${(entity.id)!}</td>
				<td>${(entity.zl)!}</td>
				<td>${(entity.zdmc)!}</td>
				<td>${(entity.zdbm)!}</td>
				<td>${(entity.zdz)!}</td>
				<td>${(entity.jb)!}</td>
				<td>${(entity.px)!}</td>
			</tr>
		 	</#list>
		 	<#else>
		 		<tr><td><input type="checkbox"/></td><td colspan="7" class="nodata">无记录</td></tr>
		 	</#if>
		 	</tbody>
		</table>
	</div>
	<#if pager?exists>
		<@com.pagination pager=pager/>
	</#if>
</#escape>
</@com.page>
