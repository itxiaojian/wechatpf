<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
$(document).ready(function(){
	top.title("详细");
});
</script>
 <#if sysArea?exists>
 <div class="box1" id="formContent" whiteBg="true">
	<table class="tableStyle" formMode="view">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		 	
	 		<tr>
		    	<td>上级区域</td>
		    	<td><input type="text" name="parentId" value="${(sysArea.parentId)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>区域代码</td>
		    	<td><input type="text" name="areaCode" value="${(sysArea.areaCode)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>区域名称</td>
		    	<td><input type="text" name="areaName" value="${(sysArea.areaName)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
			<td></td><td></td>
	 		</tr>
		 </table>
	</div>
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
