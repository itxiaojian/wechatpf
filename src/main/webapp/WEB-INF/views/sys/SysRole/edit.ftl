<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysRole.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("编辑");
	$("#myFormId").validate(saveSysJsConfig,${(paramErrors?size)?default(0)});
});
</script>
 <#if sysRole?exists>
		<@form.form id="myFormId" action="${base}/sys/SysRole/update" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="jsbh" value="${(sysRole.jsbh)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>角色名称</td>
		    	<td><input type="text" name="jsmc" value="${(sysRole.jsmc)!}" class="textInput"/></td>
		    	<td>描述</td>
		    	<td><input type="text" name="bz" value="${(sysRole.bz)!}" class="textInput"/></td>
	 		</tr>
		    <tr>
		    	<td>是否生效</td>
		    	<td>					
		    		<select name="jszt" val="${(sysRole.jszt)!}">
						<option value="1" <#if (sysRole.jszt)?if_exists ==1>selected</#if>>有效</option>
						<option value="0" <#if (sysRole.jszt)?if_exists ==0>selected</#if>>无效</option>
					</select>
				</td>
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
 <#else>
	<font color="red">记录不存在</font>
</#if>
</#escape>
</@com.page>
