
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>

<@com.page>

<link rel="stylesheet" href="${base}/resources/js/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${base}/resources/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<#escape x as x?html>  
<script type="text/javascript">
	$(function(){
		var roleId = $("input[name='roleId']").val();
		parent.put("roleId",roleId);
	})

</script>

<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="系统角色">
	<a href="${base}/sys/SysRole/list" target="content" onfocus="this.blur()"><span>系统角色</span></a>
	</li>
</ul>
</div>	
	<input type="hidden" name="roleId" id="roleId" value="${(sysRole.roleId)!}"/>
			<IFRAME id=treeFrame name=treeFrame src="${base}/resource.jsp"
							style="float:left;scrolling:auto;allowTransparency:true;width:100%;height:480px;border:1px solid #80c0e7;"  frameBorder=0 scrolling="yes"></IFRAME>
</#escape>
</@com.page>
