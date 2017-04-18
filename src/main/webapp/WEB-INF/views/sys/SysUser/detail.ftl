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
 <#if sysUser?exists>
 <div class="box1" id="formContent" whiteBg="true">
	<table class="tableStyle" formMode="view">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		 	
	 		<tr>
		    	<td>姓名</td>
		    	<td><input type="text" name="xm" value="${(sysUser.xm)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>用户名</td>
		    	<td><input type="text" name="username" value="${(sysUser.username)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>邮箱</td>
		    	<td><input type="text" name="yx" value="${(sysUser.yx)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 		<td>手机号</td>
		    	<td><input type="text" name="sjh" value="${(sysUser.sjh)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
	 		</tr>
	 		<tr>
		    	<td>部门</td>
		    	<td><input type="text" name="bmbh" value="${(sysUser.bmmc)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<#assign statuss={'2':'禁用','1':'启用','0':'已删除'}>
		    	<td>状态</td>
		    	<td><input type="text" name="yhzt" value="${statuss[''+sysUser.yhzt]!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		 </table>
	</div>
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
