<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysDict.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("新增");
	
	$("#myFormId").validate(saveSysSjzdConfig,${(paramErrors?size)?default(0)});
});
</script>
		<@form.form id="myFormId" action="${base}/sys/SysDict/save" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="id" value="${(sysDict.id)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>种类</td>
		    	<td><input type="text" name="zl" value="${(sysDict.zl)!}" class="textInput" placeholder="必须为英文字母"/></td>
		    	<td>名称</td>
		    	<td><input type="text" name="zdmc" value="${(sysDict.zdmc)!}" class="textInput"/></td>
	 		</tr>
		    <tr>
		    	<td>代码</td>
		    	<td><input type="text" name="zdbm" value="${(sysDict.zdbm)!}" class="textInput"/></td>
		    	<td>字典值</td>
		    	<td><input type="text" name="zdz" value="${(sysDict.zdz)!}" class="textInput"/></td>
		 	</tr>
		    <tr>
		    	<td>级别</td>
		    	<td><select name="jb" val="1">
			    		<#list 1..2 as i>
							<option value="${i}">${i}</option>
						</#list>
					</select>
				</td>
		    	<td>排序</td>
		    	<td>
		    	<select name="px" val="50">
		    		<#list 1..100 as i>
						<option value="${i}">${i}</option>
					</#list>
				</select>
		    	</td>
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
