<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>

<#global validateJS="${rc.contextPath}${setting['validatejspath']}"/>
<#global base=rc.contextPath/>
<#macro page>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<meta content="no-cache" http-equiv="Pragma" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="0" http-equiv="Expires" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta charset="utf-8" />
	<title>${setting['app']}</title>
	<script type="text/javascript">
	var contextPath = "${base}";
	</script>
	<script type="text/javascript" src="${base}/libs/js/jquery.js"></script>
	<script type="text/javascript" src="${base}/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="${base}/resources/js/app.js"></SCRIPT>
  	<script src="${base}/libs/js/framework.js" type="text/javascript"></script>
	<link href="${base}/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="${base}/resources/css/base.css" rel="stylesheet" />
	<link href="${base}/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="${base}/"/>
	<script type="text/javascript" src="${base}/libs/js/nav/pageNumber.js"></script>
	<script type="text/javascript" src="${base}/libs/js/form/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${base}/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${base}/resources/js/jquery.cookie.js"></script>
	<script type="text/javascript">	
	$(document).ready(function(){
 	<#if (SPRING_SECURITY_CONTEXT.authentication.principal.msgCount)?default(0) == 0>
		if(top && top.showMsg)
		 top.showMsg('');
	<#else>
		if(top && top.showMsg)
		 top.showMsg('${(SPRING_SECURITY_CONTEXT.authentication.principal.msgCount)?default(0)}');
	</#if>
	});
	
	function getList(num){
		$("#startId").val("0");
		$("#limitId").val(num);
		$("#searchForm").submit();
	}
</script>
<style>
.hide{display:none;}
.nodata{color:red;text-align:center;}
#scrollContent{border:1px solid #cccccc;}
</style>
</head>
<body style="background-color:#ffffff;">
  <#nested>
</body>
</html>
</#macro>

<#macro pagination pager>
	<div class="pager" style="height:33px;margin-top:10px;border:1pt solid #cccccc;">
	<div class="float_left padding5">
		数据共${pager.rowCount}条
	</div>
	<div class="float_left padding5">分页大小
		<select id="pageSizeId" name="pageSize" val="${(pager.pageSize)!}" style="width:50px;" onchange="getList(this.value);">
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="50">50</option>
		</select>
	</div>
	
	<div class="float_right padding5">
		<div class="pageNumber" total="${(pager.rowCount)!}" pageSize="${(pager.pageSize)!}" page="${(pager.page)?default(1)-1}" showInput="true" id="pageContent"></div>
	</div>
	<div class="clear"></div>
</div>
</#macro>

<#macro warning>
	<#if errormsgs?exists && errormsgs?has_content>
		<div class="errorblock">
		<#if errormsgs?is_string>
			${errormsgs}<br/>
		<#else>
		<#list errormsgs as msg>
			${msg}<br/>
		</#list>
		</#if>
		</div>
	</#if>
</#macro>