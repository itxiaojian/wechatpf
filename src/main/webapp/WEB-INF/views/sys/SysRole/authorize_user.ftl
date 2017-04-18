
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>

<@com.page>

<link rel="stylesheet" href="${base}/resources/js/ztree/css/demo.css" type="text/css">
<#escape x as x?html>  
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="系统角色">
	<a href="${base}/sys/SysRole/list" target="content" onfocus="this.blur()"><span>系统角色</span></a>
	</li>
</ul>
</div>
<IFRAME id="documentFrame" name="documentFrame" src='${base}/sys/SysUser/Userlist?searchRoleId=${(sysRole.jsbh)!}&&start=0&&limit=10'
							style="float:left;scrolling:auto;allowTransparency:true;border:0px;margin-left:0px;height:500px;width:740px;"  frameBorder=0 scrolling="yes" ></IFRAME>
</#escape>
</@com.page>
