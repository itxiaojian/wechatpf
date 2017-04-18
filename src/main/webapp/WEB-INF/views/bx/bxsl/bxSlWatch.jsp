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
    <script type="text/javascript">var PATHNAME="<%=path%>"</script>
    <script type="text/javascript">var path="<%=path%>"</script>
    <script type="text/javascript" >var TIME ="${time}"</script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ext/ext-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ux/fileuploadfield/css/fileuploadfield.css" />
    <script type="text/javascript" src="<%=path%>/resources/js/ux/fileuploadfield/FileUploadField.js"></script>
    
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/index.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/ux/datetime/Spinner.css" />
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/ST.ux.ExtField.js"></script>	
	<script type="text/javascript" src="<%=path%>/resources/js/ux/Ext.ux.PagePlugins.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/datetime/DateTimeField.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/datetime/Spinner.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/datetime/SpinnerField.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxForm.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/ux/uxGrid.js"></script>
	
	<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsl/bxslwatch.js"></script>
	
    <script type="text/javascript" src="<%=path%>/resources/js/ux/htmleditor.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></script>
    <!-- <script type="text/javascript"> -->
	<%-- <script type="text/javascript" src="<%=path%>/resources/js/app.js"></script> --%>
  	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
  	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/lodop/LodopFuncs.js" type="text/javascript"></script>
    
<style>
.hide{display:none;}
.nodata{color:red;text-align:center;}
#scrollContent{border:1px solid #cccccc;}
</style>
<script>
	//回访
		function hf(dm){
		if(confirm("您确认对报修结束进行回访吗？")){
			$.ajax({
				url:'<%=path%>/bx/bxcl/succCon',
				type : 'get',
				//dataType : 'json',
				data:{
					id:dm
				},
				success: function(){
					txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
		}
	};
	
	//用戶未評價,結束
	function js(dm){
		if(confirm("您确定结束 吗？")){
			$.ajax({
				url:'<%=path%>/bx/bxsl/js',
				type : 'get',
				//dataType : 'json',
				data:{
					id:dm,
				},
				success: function(){
					txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
			 }
	}

	</script>
</head>
<body style="background-color:#ffffff;">
<input value="" id="S1" type="hidden"/>
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="报修受理">
	<a href="<%=path%>/bx/bxgl/bxglmobilelist" target="content" onfocus="this.blur()"><span>报修受理</span></a>
	</li>
</ul>
</div>
<script type="text/javascript" >
function dyClick(dm){
	var start=0;
	var limit=20;
	if(confirm("您确定打印吗？")){
		   CreateOneFormPage(dm);
		   if (LODOP.CVERSION) CLODOP.On_Return=function(TaskID,Value){document.getElementById('S1').value=Value;};
		   document.getElementById('S1').value=LODOP.PREVIEW();		
	 		if(document.getElementById('S1').value==1){
				$.ajax({
					url:'<%=path%>/bx/bxsl/deal',
					type : 'get',
					//dataType : 'json',
					data:{
						id:dm
					},
					success: function(){
						txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
						}
					}); 
			} 
		 }
};
function CreateOneFormPage(dm){	
	LODOP=getLodop();                 
	LODOP.PRINT_INIT("派单表"+dm);
	LODOP.ADD_PRINT_URL(30,20,746,"95%",'openPdPage?bxId='+dm);
	//LODOP.SET_PRINT_MODE("PROGRAM_CONTENT_BYVAR",true);//生成程序时，内容参数有变量用变量，无变量用具体值
	LODOP.SET_PRINT_STYLEA(0,"HOrient",3);
	LODOP.SET_PRINT_STYLEA(0,"VOrient",3);
	//LODOP.PREVIEW();
	//LODOP.SET_SHOW_MODE("PREVIEW_NO_MINIMIZE",true);
    LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",true);
};
</script>
</body>
</html>