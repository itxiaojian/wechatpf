<#-----author:lxt------->
<#-----date:2014-11-20 09:40:56------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysLog.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("新增");
	
	$("#myFormId").validate(saveSysLogConfig,${(paramErrors?size)?default(0)});
});
</script>
		<@form.form id="myFormId" action="${base}/sys/SysLog/save" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="id" value="${(sysLog.id)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>操作</td>
		    	<td><input type="text" name="operation" value="${(sysLog.operation)!}" class="textInput"/></td>
		    	<td>操作内容</td>
		    	<td><input type="text" name="operContent" value="${(sysLog.operContent)!}" class="textInput"/></td>
	 		</tr>
		    <tr>
		    	<td>操作人账号</td>
		    	<td><input type="text" name="userId" value="${(sysLog.userId)!}" class="textInput"/></td>
		    	<td>操作人</td>
		    	<td><input type="text" name="username" value="${(sysLog.username)!}" class="textInput"/></td>
	 		</tr>
		    <tr>
		    	<td>客户端</td>
		    	<td><input type="text" name="userAgent" value="${(sysLog.userAgent)!}" class="textInput"/></td>
		    	<td>IP</td>
		    	<td><input type="text" name="ip" value="${(sysLog.ip)!}" class="textInput"/></td>
	 		</tr>
		    <tr>
		    	<td>操作日期</td>
		    	<td><input name="operDate" type="text" value="${(sysLog.operDate?string('yyyy-MM-dd HH:mm:ss'))!}" class="date" datefmt="yyyy-MM-dd HH:mm:ss"/></td>
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
