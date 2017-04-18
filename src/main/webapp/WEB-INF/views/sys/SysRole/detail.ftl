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
 <#if sysRole?exists>
 <div class="box1" id="formContent" whiteBg="true">
	<table class="tableStyle" formMode="view">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		 	
	 		<tr>
		    	<td>角色名称</td>
		    	<td><input type="text" name="jsmc" value="${(sysRole.jsmc)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>描述</td>
		    	<td><input type="text" name="bz" value="${(sysRole.bz)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	<#assign enableds={'1':'有效','0':'无效'}>
	 		</tr>
		    <tr>
		    	<td>是否生效</td>
		    	<td><input type="text" name="jszt" value="${enableds[''+sysRole.jszt]!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
			<td></td><td></td>
	 		</tr>
		 </table>
	</div>
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
