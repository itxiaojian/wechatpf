<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<@com.page>
<#escape x as x?html> 
<script type="text/javascript">
    function onSjtbClick(num) {
    	if(confirm("确定要同步数据吗？")){
    		$.ajax({
				url: '${base}/tjsj/tb/sjtb',
				type : 'post',
				dataType : 'json',
				data:{type:num},
				success: function(data){
					if(data == '1'){
						alert("同步成功!");
					}else{
						alert("提交失败");
					}
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});	
		}
    }
    
    function onAdtbClick(num) {//同步ad服务器用户信息
    	if(confirm("确定要同步数据吗？")){
    		$.ajax({
				url: '${base}/tjsj/tb/adsjtb',
				type : 'post',
				dataType : 'json',
				data:{type:num},
				success: function(data){
					if(data == '1'){
						alert("ad用户同步成功!");
					}else if(data == '0'){
						alert("同步失败，请检查ad服务器配置是否正确!");
					}
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});	
		}
    }
    
    function onXstbClick() {
    	if(confirm("确定要同步数据吗？")){
    		$.ajax({
				url: '${base}/tjsj/tb/xssjtb',
				type : 'post',
				dataType : 'json',
				//data:{type:num},
				success: function(data){
					if(data == '1'){
						alert("同步成功!");
					}else{
						alert("提交失败");
					}
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});	
		}
    }
</script> 
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="用户">
	<a href="${base}/sys/SysUser/list" target="content" onfocus="this.blur()"><span>用户</span></a>
	</li>
</ul>
</div>
	<form method="post" id="searchForm" action="${base}/sys/SysUser/list">
	<input type="hidden" id="startId" name="start" value="${(pager.start?default(0))!}"/>	
	<input type="hidden" id="limitId" name="limit" value="${(pager.pageSize)?default(0)}"/>
	<table>
		<tr>
		<td>姓名</td><td><input type="text" name="Q_xm_S_LK" value="${params['Q_xm_S_LK']!}" class="textinput"/></td>
		<td>用户名</td><td><input type="text" name="Q_username_S_LK" value="${params['Q_username_S_LK']!}" class="textinput"/></td>
		<td>状态</td>
		<td>
			<select name="Q_yhzt_N_EQ" value="${params['Q_yhzt_N_EQ']!}">
				<option value="">---</option>
				<option value="2" <#if (params['Q_yhzt_N_EQ'])?if_exists =="2">selected</#if>>禁用</option>
				<option value="1" <#if (params['Q_yhzt_N_EQ'])?if_exists =="1">selected</#if>>启用</option>
			</select>
		</td>
		<td><input type="button" class="submitForm button" value="查询"/></td>
		<td><input type="button" class="clearForm button" value="清空"/></td>
		</tr>
	</table>
 	</form>
	<div class="box_tool_min">
	<div class="center">
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysUser/add'})"><span class="icon_add">新增</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysUser/detail',mutiple:false})"><span class="icon_view">查看</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysUser/edit',mutiple:false})"><span class="icon_edit">编辑</span></a>
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysUser/resetPassword',confirm:true,mutiple:true})"><span class="icon_edit">重置密码</span></a>
		<!-- <a href="javascript:;" onclick="onSjtbClick(0)"><span class="icon_add">全量同步</span></a>  -->
		<a href="javascript:;" onclick="onSjtbClick(1)"><span class="icon_add">增量同步</span></a>
		<a href="javascript:;" onclick="onXstbClick()"><span class="icon_add">迎新新生同步</span></a>
		<!-- <a href="javascript:;" onclick="onAdtbClick(1)"><span class="icon_add">AD用户同步</span></a> -->
		<a href="javascript:;" onclick="onAction({url:'${base}/sys/SysUser/delete',confirm:true,mutiple:true})"><span class="icon_delete">删除</span></a>
	</div>
	</div> 
	<div id="scrollContent">
		<table class="tableStyle" mode="list" style="width:730px;">
			<thead>
				<tr>
				<th></th>
			  	<th class="hide">id</th>
			  	<th>姓名</th>
			  	<th>用户名</th>
			  	<th class="pk hide">密码</th>
			  	<th>部门</th>
			  	<th>手机</th>
			  	<th>邮箱</th>
			  	<th>角色</th>
			  	
			  	<th>状态</th><#assign statuss={'2':'禁用','1':'启用','0':'已删除'}>
				</tr>
			</thead>
			<#if (pager.items)?exists && ((pager.items)?size != 0)>
			<#list pager.items as entity>
			<tbody>
			<tr>
			<td><input type="checkbox"/></td>
				<td class="pk hide" id="id" val="${(entity.yhbh)!}">${(entity.yhbh)!}</td>
				<td>${(entity.xm)!}</td>
				<td>${(entity.username)!}</td>
				<td class="hide">${(entity.password)!}</td>
				<td>${(entity.bmmc)!}</td>
				<td>${(entity.sjh)!}</td>
				<td>${(entity.yx)!}</td>
				<td>${(entity.js)!}</td>
				
				<td>${statuss[''+entity.yhzt]!}</td>
			</tr>
		 	</#list>
		 	<#else>
		 		<tr><td><input type="checkbox"/></td><td colspan="10" class="nodata">无记录</td></tr>
		 	</#if>
		 	</tbody>
		</table>
	</div>
	<#if pager?exists>
		<@com.pagination pager=pager/>
	</#if>
</#escape>
</@com.page>
