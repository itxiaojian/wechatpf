<#-----author:lxt------->
<#-----date:2014-11-10 14:25:23------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysFile.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("编辑");
	$("#myFormId").validate(saveSysFileConfig,${(paramErrors?size)?default(0)});
});
</script>
 <#if sysFile?exists>
		<@form.form id="myFormId" action="${base}/sys/SysFile/update" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="id" value="${(sysFile.id)!}"/>
		<input type="hidden" name="refObj" value="${(sysFile.refObj)!}"/>
		<input type="hidden" name="refId" value="${(sysFile.refId)!}"/>
		<input type="hidden" name="folderId" value="${(sysFile.folderId)!}"/>
		<input type="hidden" name="username" value="${(sysFile.username)!}"/>
		<input type="hidden" name="userId" value="${(sysFile.userId)!}"/>
		<input type="hidden" name="size" value="${(sysFile.size)!}"/>
		<input type="hidden" name="extension" value="${(sysFile.extension)!}"/>
		<input type="hidden" name="type" value="${(sysFile.type)!}"/>
		<input type="hidden" name="path" value="${(sysFile.path)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>文件名</td>
		    	<td><input type="text" name="fileName" value="${(sysFile.fileName)!}" class="textInput"/></td>
		    	<td>描述</td>
		    	<td><input type="text" name="descn" value="${(sysFile.descn)!}" class="textInput" style="width:300px;"/></td>
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
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
