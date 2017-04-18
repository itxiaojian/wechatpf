<#-----author:lxt------->
<#-----date:2014-12-08 10:35:36------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysBackup.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("新增");
	
	$("#myFormId").validate(saveSysBackupConfig,${(paramErrors?size)?default(0)});
});
</script>
		<@form.form id="myFormId" action="${base}/sys/SysBackup/save" method="post" >
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="id" value="${(sysBackup.id)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>文件备份名</td>
		    	<td><input type="text" name="fileName" value="${(sysBackup.fileName)!}" class="textInput"/></td>
		    	<td>文件备份时间</td>
		    	<td><input name="backupTime" type="text" value="${(sysBackup.backupTime?string('yyyy-MM-dd HH:mm:ss'))!}" class="date" datefmt="yyyy-MM-dd HH:mm:ss"/></td>
	 		</tr>
		    <tr>
		    	<td>备份文件路径</td>
		    	<td><input type="text" name="path" value="${(sysBackup.path)!}" class="textInput"/></td>
					<td></td><td></td>
		 		</tr>
		 <tr>
			<td colspan="4">
				<input type="submit" value="提交"/>
				<input type="button" value="取消" onclick="top.Dialog.close()"/>
			</td>
		</tr>
		</table>
		</div>
		</@form.form>
</#escape>
</@com.page>
