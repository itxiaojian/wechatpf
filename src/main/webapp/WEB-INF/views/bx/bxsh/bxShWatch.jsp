<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<meta content="no-cache" http-equiv="Pragma" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="0" http-equiv="Expires" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1"></meta>
	<meta charset="utf-8" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript">var PATHNAME="<%=path%>"</script>
      <script type="text/javascript">var path="<%=path%>"</script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ux/fileuploadfield/css/fileuploadfield.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ux/fileuploadfield/FileUploadField.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ux/gridCopy/gridCopy.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/index.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ux/datetime/Spinner.css" />
	<script type="text/javascript" src="<%=path%>/resources/js/ux/gridCopy/gridCopy.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.ExtField.js"></script>	
	<script type="text/javascript" src="<%=path%>/resources/js/ux/Ext.ux.PagePlugins.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/datetime/DateTimeField.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/datetime/Spinner.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/datetime/SpinnerField.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxForm.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxGrid.js"></script>
	
	<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/bxshwatch.js"></script>
	
    <script type="text/javascript" src="<%=path%>/resources/js/ux/htmleditor.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript">var path="<%=path%>";</script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/jquery_002.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/jquery_003.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/artDialog.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/public.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></script>
    <!-- <script type="text/javascript"> -->
	<%-- <script type="text/javascript" src="<%=path%>/resources/js/app.js"></script> --%>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></script> 

<style>
.hide{display:none;}
.nodata{color:red;text-align:center;}
#scrollContent{border:1px solid #cccccc;}
</style>
</head>
<body style="background-color:#ffffff;">
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="报修审核">
	<a href="<%=path%>/bx/bxgl/bxglmobilelist" target="content" onfocus="this.blur()"><span>报修审核</span></a>
	</li>
</ul>
</div>
<script type="text/javascript">
function plpdCheck(dm){
	 if(confirm("您确认审核并派单吗？")){
		art.dialog.open('<%=path%>/bx/bxsh/send?id='+dm,{
			//id:'detailDialog',
			//title:'申请查看',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			title:'报修审核派单信息',
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 230,
	        width:$(document.body).width() - 700,
	        top:100,
	       // skin:'aero',
	        top:20,
	        ok:function(){
	        	var sendBh = this.iframe.contentWindow.$('#pdrvalue').val();
	        	var sender = this.iframe.contentWindow.$('#pdrvalue').text();
	        	if(sendBh=='' || sendBh ==null){
	        		alert('请选择维修工！');
	        		return false;
	        	}else{
	         	$.ajax({
					url:"<%=path%>/bx/bxsh/checkBoxSend",
					type : 'get',
					//dataType : 'json',
					data:{
						sendBh:sendBh,
						sender:sender,
						idList:dm,
						
					},
					success: function(){
					window.location.href="<%=path%>/bx/bxsh/shlist";
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
					}
				}); 
	        	}
	        },
	        cancel:true
	    }); 
	 }
}
</script>
<script type="text/javascript">
		onSendClick :function send(dm,wxdl,wxxl){
			art.dialog.open('<%=path%>/bx/bxsh/send?id='+dm,{
				//id:'detailDialog',
				//title:'申请查看',
				//titleClass: 'dialog_diy',
				//content: document.getElementById('detail'),
				title:'报修审核派单信息',
				lock:true, 
				padding: '4px 0px',
		        height:$(document.body).height() - 230,
		        width:$(document.body).width() - 700,
		        top:100,
		        top:20,
		        okVal:'确定',
		        ok:function(){
		        	var sendBh = this.iframe.contentWindow.$('#pdrvalue').val();
		        	var sender = this.iframe.contentWindow.$('#pdrvalue').text();
		        	if(sendBh=='' || sendBh ==null){
		        		alert('请选择维修工！');
		        		return false;
		        	}else{
		        		$.ajax({
							url:"<%=path%>/bx/bxsh/sendCon",
							type : 'get',
							//dataType : 'json',
							data:{
								sendBh:sendBh,
								sender:sender,
								bxId:dm,
								wxdl:wxdl,
								wxxl:wxxl
							},
							success: function(){
							 window.location.href="<%=path%>/bx/bxsh/shlist";
							},
							error: function(XMLHttpRequest){
									alert("网络错误");
							}
						}); 
		    		}
		        },
		        cancel:true
		    }); 
		}
</script>
<script type="text/javascript">
bh:	function rejec(dm){
	 		art.dialog.open('<%=path%>/bx/bxsh/rejec?id='+dm,{
				//id:'detailDialog',
				//title:'申请查看',
				//titleClass: 'dialog_diy',
				//content: document.getElementById('detail'),
				title:'报修审核驳回信息',
				lock:true, 
				padding: '4px 0px',
		        height:$(document.body).height() - 230,
		        width:$(document.body).width() - 700,
		        top:100,
		        top:20,
		        ok:function(){
		        	var str = this.iframe.contentWindow.$('#areadropboxvalue').val();
		        	$.ajax({
						url:"<%=path%>/bx/bxsh/rejecCon",
						type : 'get',
						//dataType : 'json',
						data:{
							id:dm,
							str:str,
						},
						success: function(){
						window.location.href="<%=path%>/bx/bxsh/shlist";
						},
						error: function(XMLHttpRequest){
								alert("网络错误");
						}
					});
		        },
		        cancel:true
		    }); 
		}
		</script>
<script type="text/javascript">
	//驳回
		   function plbhClick(dm){
				 if(confirm("您确认批量驳回吗？")){
					art.dialog.open('<%=path%>/bx/bxsh/rejec?id='+dm,{
						//id:'detailDialog',
						//title:'申请查看',
						//titleClass: 'dialog_diy',
						//content: document.getElementById('detail'),
						title:'报修审核驳回信息',
						lock:true, 
						padding: '4px 0px',
				        height:$(document.body).height() - 230,
				        width:$(document.body).width() - 700,
				        top:100,
				        top:20,
				        ok:function(){
				        	var str = this.iframe.contentWindow.$('#areadropboxvalue').val();
				        	$.ajax({
								url:"<%=path%>/bx/bxsh/rejecChecBox",
								type : 'get',
								//dataType : 'json',
								data:{
									idList:dm,
									str:str,
								},
								success: function(){
								window.location.href="<%=path%>/bx/bxsh/shlist";
								},
								error: function(XMLHttpRequest){
										alert("网络错误");
								}
							});
				        },
				        cancel:true
				    }); 
				  }
				}
	</script>
</body>
</html>