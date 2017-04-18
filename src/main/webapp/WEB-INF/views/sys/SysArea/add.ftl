<#-----author:lxt------->
<#-----date:2014-11-17 14:01:11------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysArea.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("新增");
	
	$("#myFormId").validate(saveSysZzjgConfig,${(paramErrors?size)?default(0)});
});
</script>
		<@form.form id="myFormId" action="${base}/sys/SysArea/save" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="parentId" value="${(params['pid'])!}"/>
		<input type="hidden" name="id" value="${(sysArea.id)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>名称</td>
		    	<td><input type="text" name="bmmc" value="${(sysArea.bmmc)!}" class="textInput"/></td>
		    	<td>代码</td>
		    	<td><input type="text" name="bmbh" value="${(sysArea.bmbh)!}" class="textInput"/></td>
	 		</tr>
		    
		     <tr>
		    	<td>排序</td>
		    	<td>
		    	<select name="px" val="100">
		    		<#list 1..200 as i>
						<option value="${i}">${i}</option>
					</#list>
				</select>
		    	</td>
					<td>备注</td>
					<td><input type="text" name="bz" value="${(sysArea.bz)!}" class="textInput"/></td>
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
