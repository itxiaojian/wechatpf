<#-----author:lxt------->
<#-----date:2014-11-17 14:01:11------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
$(document).ready(function(){
	top.title("详细");
});
</script>
 <#if sysFolder?exists>
 <div class="box1" id="formContent" whiteBg="true">
	<table class="tableStyle" formMode="view">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		 	
	 		<tr>
		    	<td>文件夹名称</td>
		    	<td><input type="text" name="name" value="${(sysFolder.name)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>描述</td>
		    	<td><input type="text" name="descn" value="${(sysFolder.descn)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>上级文件夹</td>
		    	<td><input type="text" name="parentId" value="${(sysFolder.parentId)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
			<td></td><td></td>
	 		</tr>
		 </table>
	</div>
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
