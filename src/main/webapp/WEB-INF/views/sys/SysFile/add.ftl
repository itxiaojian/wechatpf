<#--
$("#yearReportSBtn").click(function () {
                top.onView("${base}/sys/SysFile/add?clazz=com.jincai.app.jianguan.org.model.OrgDirAppraise&single=true",setFileS);
            });
            
            function setFileS() {
                var params = top.get("params");
				if (params) {
					var data = params[0];
					$("input[name=yearReportS]").val(data.name);
					$("input[name=yearReportSFileId]").val(data.id);
					$("#attacheFileS").empty();
					$("#attacheFileS").prepend('<a href="${base}/sys/SysFile/downloadFile/'+data.id+'">'+data.name+'</a>'+
					'&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:" onclick="if(confirm(\'确认删除?\')) {top.onView(\'${base}/sys/SysFile/delete_'+data.id+'\',removeFileS);} else { return false;}">删除</a>');
	            }
            }
     
     function removeFileS() {
	$("#attacheFileS").empty();
	$("#attacheFileS").prepend('<img class="searchBtn" id="yearReportSBtn" src="${base}/resources/images/upload.gif"/>');
}
-->
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@com.page>
<#escape x as x?html>  
<script type="text/javascript">
$(document).ready(function(){
	top.title("文件上传");
	<#if !params["single"]?exists>
	$("#addUpload").click(function(){
		var length = $("input[type=file]").size() + 1;
		if (length == 11) {
			alert("一次至多可以上传10个文件");
			return;
		}
		var c = "viewModeEven";
		var ul = '<tr><td class="'+ c +'">文件'+length+'</td><td><input type="file" name="fileData['+ length +
				']" /><br/><input type="text" name="comments['+ length +']" style="width:300px;"/></td></tr>';
		$(this).parent().parent().before(ul);
	});
	$("#removeUpload").click(function(){
		if ($("input[type=file]").size()== 1) {
			return;
		}
		$(this).parent().parent().prev().remove();
	});
	</#if>
});
</script>
	<div class="form">
		<@form.form action="${base}/sys/SysFile/uploadFile" commandName="sysFileForm"  method="post"   autocomplete="off" enctype="multipart/form-data" >
		<@com.warning/>
		<@form.errors path="*" cssClass="errorblock" element="div"/>
			    <input type="hidden" name="refId" value="${(params['refId'])!}"/>
			    <input type="hidden" name="refObj" value="${(params['refObj'])!}"/>
			    <input type="hidden" name="folderId" value="${(params['folderId'])!}"/>
			   <div class="box1" id="formContent" whiteBg="true">
	     		<table class="tableStyle" formMode="view">
		    	<tr>
	    			<td>文件1</td>
			    	<td>
			    	<input type="file" name="fileData[0]" keepDefaultStyle="true"/><br/>
			    	<input type="text" name="comments[0]" style="width:300px;" keepDefaultStyle="true"/>
			    	</td>
			    </tr>
			    <#if !params["single"]?exists>
			    <tr>
	    			<td colspan="2">
			    	<input type="button" id="addUpload" value="增加上传文件"/>&nbsp;<input type="button" id="removeUpload" value="减少上传文件"/>
			    	</td>
			    </tr>
			    </#if>
		  <tr>
			<td colspan="2">
				<input type="submit" value="提交"/>
				<input type="button" value="取消" onclick="top.Dialog.close()"/>
			</td>
		</tr>
		</table>
		</div>
		</@form.form>
	</div>
</#escape>
</@com.page>
