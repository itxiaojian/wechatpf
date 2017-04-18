<#-----author:lxt------->
<#-----date:2014-12-08 10:35:36------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
$(document).ready(function(){
	top.title("详细");
});
</script>
 <#if sysBackup?exists>
 <div class="box1" id="formContent" whiteBg="true">
	<table class="tableStyle" formMode="view">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		 	
	 		<tr>
		    	<td>文件备份名</td>
		    	<td><input type="text" name="fileName" value="${(sysBackup.fileName)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>文件备份时间</td>
		    	<td><input name="backupTime" type="text" value="${(sysBackup.backupTime?string('yyyy-MM-dd HH:mm:ss'))!}" class="date readonly" keepDefaultStyle="true" datefmt="yyyy-MM-dd HH:mm:ss" readonly/></td>
		 	
	 		</tr>
		    <tr>
		    	<td>备份文件路径</td>
		    	<td><input type="text" name="path" value="${(sysBackup.path)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
			<td></td><td></td>
	 		</tr>
		 </table>
	</div>
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
