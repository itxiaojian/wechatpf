<#-----author:of------->
<#-----date:2015-08-06 14:01:11------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/Bxzt.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("新增");
	
	$("#myFormId").validate(saveBxztConfig,${(paramErrors?size)?default(0)});
});
</script>
		<@form.form id="myFormId" action="${base}/bx/bxzt/save" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="parentId" value="${(params['pid'])!}"/>
		<input type="hidden" name="id" value="${(bxzt.id)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>名称</td>
		    	<td><input type="text" name="ztmc" value="${(bxzt.ztmc)!}" class="textInput"/></td>
 		    	<td>代码</td>
		    	<td><input type="text" name="ztbh" value="${(bxzt.ztbh)!}" class="textInput"/></td>
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
					<td><input type="text" name="bz" value="${(bxzt.bz)!}" class="textInput"/></td>
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
