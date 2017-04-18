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
 <#if sysDict?exists>
 <div class="box1" id="formContent" whiteBg="true">
	<table class="tableStyle" formMode="view">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		 	
	 		<tr>
		    	<td>种类</td>
		    	<td><input type="text" name="zl" value="${(sysDict.zl)!}" class="textInput readonly" keepDefaultStyle="true" readonly /><span style="color:red">*必须为英文字母</span></td>
		 	
		    	<td>名称</td>
		    	<td><input type="text" name="zdmc" value="${(sysDict.zdmc)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
	 		</tr>
		    <tr>
		    	<td>代码</td>
		    	<td><input type="text" name="zdbm" value="${(sysDict.zdbm)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
		 	
		    	<td>字典值</td>
		    	<td><input type="text" name="zdz" value="${(sysDict.zdz)!}" class="textInput readonly" keepDefaultStyle="true" readonly /></td>
	 		</tr>
		    <tr>
		    	<td>级别</td>
		    	<td><input type="text" name="jb" class="textInput readonly" value="${(sysDict.jb)!}" keepDefaultStyle="true" readonly/></td>
		 	
		    	<td>排序</td>
		    	<td><input type="text" name="px" class="textInput readonly" value="${(sysDict.px)!}" keepDefaultStyle="true" readonly/></td>
	 		</tr>
		 </table>
	</div>
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
