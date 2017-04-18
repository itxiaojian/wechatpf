<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysRole.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("新增");
	
	$("#myFormId").validate(saveSysJsConfig,${(paramErrors?size)?default(0)});
	
	  $("#serchMenuIcon").click(function() {
    	 onAction({url:'${base}/sys/SysRole/select'});
	});
	
	
	function setSchoolId(data) {
	var SchoolId = top.get("SchoolId");
	if (!SchoolId)return;
	$("input[name=schoolId]").val(SchoolId);
	}
});
</script>
		<@form.form id="myFormId" action="${base}/sys/SysRole/save" method="post"   autocomplete="off">
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
		    	<td>					<select name="jszt" val="${(sysRole.jszt)!}">
						<option value="1">有效</option>
						<option value="0">无效</option>
										</select></td>
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
