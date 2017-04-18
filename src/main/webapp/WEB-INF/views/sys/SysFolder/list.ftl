
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>

<@com.page>

<link rel="stylesheet" href="${base}/resources/js/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${base}/resources/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${base}/resources/js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<#escape x as x?html>  
<script type="text/javascript">
$(document).ready(function(){
	top.title("列表");
	$("#treeFrame").height(top.getContentHeight()-25);
	$("#documentFrame").height(top.getContentHeight()-25);
	$("#documentFrame").width(top.getContentWidth() - 330);
});
</script>

<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="文件管理">
	<a href="${base}/sys/SysFile/list" target="content" onfocus="this.blur()"><span>文件管理</span></a>
	</li>
</ul>
</div>
<IFRAME id=treeFrame name=treeFrame src="${base}/sys/SysFolder/tree.html"
							style="float:left;scrolling:auto;allowTransparency:true;width:270px;border:1px solid #80c0e7;"  frameBorder=0 scrolling="yes"></IFRAME>

<IFRAME id="documentFrame" name="documentFrame" src="${base}/sys/SysFile/list"
							style="float:left;scrolling:auto;allowTransparency:true;border:0px;margin-left:20px;height:950px;"  frameBorder=0 scrolling="yes" ></IFRAME>
</#escape>
</@com.page>
