<#-----author:lxt------->
<#-----date:2014-11-17 14:01:11------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysFolder.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("新增");
	
	$("#myFormId").validate(saveSysFolderConfig,${(paramErrors?size)?default(0)});
});
</script>
		<@form.form id="myFormId" action="${base}/sys/SysFolder/save" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="parentId" value="${(params['pid'])!}"/>
		<input type="hidden" name="id" value="${(sysFolder.id)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>文件夹名称</td>
		    	<td><input type="text" name="name" value="${(sysFolder.name)!}" class="textInput"/></td>
		    	<td>描述</td>
		    	<td><input type="text" name="descn" value="${(sysFolder.descn)!}" class="textInput"/></td>
	 		</tr>
		    <tr>
		    	<td>排序</td>
		    	<td>
		    	<select name="sn" val="25">
		    		<#list 1..50 as i>
						<option value="${i}">${i}</option>
					</#list>
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
</#escape>
</@com.page>
