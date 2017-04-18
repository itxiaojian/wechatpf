<#-----author:lxt------->
<#-----date:2014-11-20 09:40:56------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html>  
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="系统日志">
	<a href="${base}/sys/SysLog/list" target="content" onfocus="this.blur()"><span>系统日志</span></a>
	</li>
</ul>
</div>
	<form method="post" id="searchForm" action="${base}/sys/SysLog/list">
	<input type="hidden" id="startId" name="start" value="${(pager.start?default(0))!}"/>	
	<input type="hidden" id="limitId" name="limit" value="${(pager.pageSize)?default(0)}"/>
	<table>
		<tr>
		<td>操作人账号</td><td><input type="text" name="Q_userId_S_LK" value="${params['Q_userId_S_LK']!}" class="textinput"/></td>
		<td>操作人</td><td><input type="text" name="Q_username_S_LK" value="${params['Q_username_S_LK']!}" class="textinput"/></td>
		<td>操作日期</td>
		<td>
		<input type="text" name="Q_operDate_D_GT" value="${params['Q_operDate_D_GT']!}" class="date" style="width:160px" datefmt="yyyy-MM-dd HH:mm:ss"/>
		到
		<input type="text" name="Q_operDate_D_LT" value="${params['Q_operDate_D_LT']!}" class="date" style="width:160px" datefmt="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
	<div class="box_tool_min">
	<div class="center">
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysLog/detail',mutiple:false})"><span class="icon_view">查看</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysLog/delete',confirm:true,mutiple:true})"><span class="icon_delete">删除</span></a>
	</div>
	</div> 
	<div id="scrollContent">
		<table class="tableStyle" mode="list" style="width:930px;">
			<thead>
				<tr>
				<th></th>
			  	<th class="hide">id</th>
			  	<th>操作</th>
			  	<th>操作人账号</th>
			  	<th>操作人</th>
			  	<th  width="300">客户端</th>
			  	<th>IP</th>
			  	<th width="200">操作日期</th>
				</tr>
			</thead>
			<#if (pager.items)?exists && ((pager.items)?size != 0)>
			<#list pager.items as entity>
			<tbody>
			<tr>
			<td><input type="checkbox"/></td>
				<td class="pk hide" id="id" val="${(entity.id)!}">${(entity.id)!}</td>
				<td>${(entity.operation)!}</td>
				<td>${(entity.userId)!}</td>
				<td>${(entity.username)!}</td>
				<td>${(entity.userAgent)!}</td>
				<td>${(entity.ip)!}</td>
				<td>${(entity.operDate?string('yyyy-MM-dd HH:mm:ss'))!}</td>
			</tr>
		 	</#list>
		 	<#else>
		 		<tr><td><input type="checkbox"/></td><td colspan="8" class="nodata">无记录</td></tr>
		 	</#if>
		 	</tbody>
		</table>
	</div>
	<#if pager?exists>
		<@com.pagination pager=pager/>
	</#if>
</#escape>
</@com.page>
