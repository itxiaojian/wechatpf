<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript" src="${validateJS}/SysUser.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	top.title("编辑");
	$("#myFormId").validate(saveSysYhConfig,${(paramErrors?size)?default(0)});
});

	function formTj(){
		var re = /^1\d{10}$/;
		var sjh = document.getElementById('sjh').value;
		var yx = document.getElementById('yx').value;
	    if(sjh!=null&&sjh!=''){
		    if (!re.test(sjh)) {
		        alert("手机号码格式不正确");
		        return false;
		    }
	    }
		var res = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	    if(yx!=null&&yx!=''){
		    if(!res.test(yx)){
		        alert("邮箱格式不正确");
		        return false;
		    }
		}
		$("#myFormId").submit();
	}
</script>
 <#if sysUser?exists>
		<@form.form id="myFormId" action="${base}/sys/SysUser/update" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="yhbh" value="${(sysUser.yhbh)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" formMode="view">
	 		<tr>
		    	<td>姓名</td>
		    	<td><input type="text" name="xm" value="${(sysUser.xm)!}" class="textInput"/></td>
		    	<td>用户名</td>
		    	<td><input type="text" name="username" value="${(sysUser.username)!}" class="textInput readonly" keepDefaultStyle="true" readonly/></td>
	 		</tr>
		    <tr>
		        <td>邮箱</td>
		    	<td><input type="text" name="yx" id="yx" value="${(sysUser.yx)!}" class="textInput"/></td>
		    	<td>手机号</td>
		    	<td><input type="text" name="sjh" id="sjh" value="${(sysUser.sjh)!}" class="textInput"/></td>
	 		</tr>
		    <tr>
		        <td>部门</td>
		    	<td>
		    		<select id="selBig" name="bmbh">
		    			<option value="${(sysUser.bmbh)!}">请选择...</option>
		    			<#list zzjg?if_exists as doc>
		    			<option value="${doc.bmbh}" <#if sysUser ?? && sysUser.bmbh ?? && sysUser.bmbh ==doc.bmbh>selected</#if>>${doc.bmmc}</option>
		    			</#list>
		    		</select>
		    	</td>
		    	<td>状态</td>
		    	<td>					
		    	<select name="yhzt" val="${(sysUser.yhzt)!}">
						<option value="2" <#if sysUser ?? && sysUser.yhzt ?? && sysUser.yhzt ==2>selected</#if>>禁用</option>
						<option value="1" <#if sysUser ?? && sysUser.yhzt ?? && sysUser.yhzt ==1>selected</#if>>启用</option>
			    </select>
                </td>
	 		</tr>
		   
		 <tr>
			<td colspan="4">
				<input type="button" value="提交" onclick="formTj();"/>
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
