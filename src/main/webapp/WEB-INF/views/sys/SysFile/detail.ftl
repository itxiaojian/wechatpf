<#-----author:lxt------->
<#-----date:2014-11-10 14:25:23------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
$(document).ready(function(){
	top.title("详细");
});
</script>
 <#if sysFile?exists>
 <div class="box1" id="formContent" whiteBg="true">
	<table class="tableStyle" formMode="view">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		 	
	 		<tr>
		    	<td>文件名</td>
		    	<td><input type="text" name="fileName" value="${(sysFile.fileName)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>文件路径</td>
		    	<td><input type="text" name="path" value="${(sysFile.path)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>创建时间</td>
		    	<td><input name="createTime" type="text" id="createTimeUpdateDate" value="${(sysFile.createTime?string('yyyy-MM-dd HH:mm:ss'))!}" class="date readonly" keepDefaultStyle="true" datefmt="yyyy-MM-dd HH:mm:ss" readonly/></td>
		 	
		    	<td>文件类型</td>
		    	<td><input type="text" name="type" value="${(sysFile.type)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>扩展名</td>
		    	<td><input type="text" name="extension" value="${(sysFile.extension)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>文件大小</td>
		    	<td><input type="text" name="size" class="textInput readonly" value="${(sysFile.size)!}" keepDefaultStyle="true" readonly/></td>
		 	
	 		</tr>
		    <tr>
		    	<td>对象主键</td>
		    	<td><input type="text" name="key" value="${(sysFile.key)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>引用对象</td>
		    	<td><input type="text" name="refObj" value="${(sysFile.refObj)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>上传人ID</td>
		    	<td><input type="text" name="userId" value="${(sysFile.userId)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>上传人名称</td>
		    	<td><input type="text" name="username" value="${(sysFile.username)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
	 		</tr>
		 </table>
	</div>
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
