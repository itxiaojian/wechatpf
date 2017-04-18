<#-----author:lxt------->
<#-----date:2014-11-19 17:16:17------->
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html> 
<script type="text/javascript" src="${validateJS}/SysUser.js"></script> 
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="用户">
	<a href="${base}/sys/SysUser/changePwd" target="content" onfocus="this.blur()"><span>修改密码</span></a>
	</li>
</ul>
</div>
	<@form.form id="myFormId" action="${base}/sys/SysUser/passwordChange" method="post"   autocomplete="off">
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
		<input type="hidden" name="id" value="${(sysUser.id)!}"/>
		<div class="box1" id="formContent" whiteBg="true">
	     <table class="tableStyle" mode="list">
	 		<tr>
		    	<td align="right">当前密码</td>
		    	<td><input type="password" name="oldPwd"  class="textInput"/></td>
	 		</tr>
		    <tr>
		    	<td align="right">新密码</td>
		    	<td><input type="password"  name="newPwd" class="textInput"/></td>
	 		</tr>
	 		<tr>
	 		  <td align="right">确认新密码</td>
	 		  <td><input type="password" name="newPwd2"  class="textInput"/></td>
	 		</tr>
		 <tr>
			<td colspan="4" align="center">
				<input type="submit" value="提交"/>
				<input type="button" value="取消" onclick="top.Dialog.close()"/>
			</td>
		</tr>
		</table>
		</div>
		</@form.form>
	

	<#if pager?exists>
		<@com.pagination pager=pager/>
	</#if>
</#escape>
</@com.page>
