<#-----author:lxt------->
<#-----date:2014-11-20 09:40:56------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
$(document).ready(function(){
	top.title("详细");
});
</script>
 <#if sysLog?exists>
 <div class="box1" id="formContent" whiteBg="true">
	<table class="tableStyle" formMode="view">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		 	
	 		<tr>
		    	<td>操作</td>
		    	<td><input type="text" name="operation" value="${(sysLog.operation)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>操作内容</td>
		    	<td><textarea>${(sysLog.operContent)!}</textarea></td>
		 	
	 		</tr>
		    <tr>
		    	<td>操作人账号</td>
		    	<td><input type="text" name="userId" value="${(sysLog.userId)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>操作人</td>
		    	<td><input type="text" name="username" value="${(sysLog.username)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>客户端</td>
		    	<td><input type="text" name="userAgent" value="${(sysLog.userAgent)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>IP</td>
		    	<td><input type="text" name="ip" value="${(sysLog.ip)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>操作日期</td>
		    	<td><input name="operDate" type="text" value="${(sysLog.operDate?string('yyyy-MM-dd HH:mm:ss'))!}" class="date readonly" keepDefaultStyle="true" datefmt="yyyy-MM-dd HH:mm:ss" readonly/></td>
			<td></td><td></td>
	 		</tr>
		 </table>
	</div>
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
