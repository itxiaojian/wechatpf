<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
function doDelete(){
	var ids = {};
	var valueStr = "";
	$('input:checked').each(function(){
		valueStr=$(this).val()+";"+valueStr;
	}); 
	ids['ids'] = valueStr;
	$.ajax({
		url: "${base}/sys/SysRole/check",
		type : 'post',
		dataType : 'json',
		data: ids,
		success: function(data){
			if(data == 0){
				onAction({url:'${base}/sys/SysRole/delete',confirm:true,mutiple:true});
			}else{
				alert("选择的角色下存在已授权用户，请取消授权后再进行删除操作！");
			}
		},
		error: function(XMLHttpRequest){
			alert("网络错误");
		}
	});  
}
</script>
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="系统角色">
	<a href="${base}/sys/SysRole/list" target="content" onfocus="this.blur()"><span>系统角色</span></a>
	</li>
</ul>
</div>
	<form method="post" id="searchForm" action="${base}/sys/SysRole/list">
	<input type="hidden" id="startId" name="start" value="${(pager.start?default(0))!}"/>	
	<input type="hidden" id="limitId" name="limit" value="${(pager.pageSize)?default(0)}"/>
	<table>
		<tr>
		<td>角色名称</td><td><input type="text" name="Q_jsmc_S_LK" value="${params['Q_jsmc_S_LK']!}" class="textinput"/></td>
		<td>描述</td><td><input type="text" name="Q_bz_S_LK" value="${params['Q_bz_S_LK']!}" class="textinput"/></td>
		<td>是否生效</td>
		<td>
			<select name="Q_jszt_N_EQ" value="${params['Q_jszt_N_EQ']!}">
				<option value="">---</option>
				<option value="1" <#if (params['Q_jszt_N_EQ'])?if_exists =="1">selected</#if>>有效</option>
				<option value="0" <#if (params['Q_jszt_N_EQ'])?if_exists =="0">selected</#if>>无效</option>
			</select>
		</td>
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
	<div class="box_tool_min">
	<div class="center">
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysRole/add'})"><span class="icon_add">新增</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysRole/detail',mutiple:false})"><span class="icon_view">查看</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysRole/edit',mutiple:false})"><span class="icon_edit">编辑</span></a>
		<a href="javascript:;" onclick="doDelete()"><span class="icon_delete">删除</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysRole/authorize',mutiple:false})"><span class="icon_edit">权限配置</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysRole/authorizedUser',mutiple:false})"><span class="icon_edit">用户授权</span></a>
	</div>
	</div> 
	<div id="scrollContent">
		<table class="tableStyle" mode="list" style="width:430px;">
			<thead>
				<tr>
				<th></th>
			  	<th class="hide">role_id</th>
			  	<th>角色名称</th>
			  	<th>描述</th>
			  	<th>是否生效</th><#assign enableds={'1':'有效','0':'无效'}>
				</tr>
			</thead>
			<#if (pager.items)?exists && ((pager.items)?size != 0)>
			<#list pager.items as entity>
			<tbody>
			<tr>
			<td><input type="checkbox" value="${(entity.jsbh)!}" /></td>
				<td class="pk hide" id="roleId" val="${(entity.jsbh)!}">${(entity.jsbh)!}</td>
				<td>${(entity.jsmc)!}</td>
				<td>${(entity.bz)!}</td>
				<td>${enableds[''+(entity.jszt)?default(0)]!}</td>
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
