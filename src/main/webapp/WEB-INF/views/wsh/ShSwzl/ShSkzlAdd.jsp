<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!-- saved from url=(0082)http://www.weiweixiao.net/index.php?g=Wap&m=LostFound&a=add&token=tojzqq1409283656 -->
<html bdgjjgagedbdaebhbjbcabcdgeeebecf_bb="1" eiiebffcjbffiheggaebebgceaeccbia_bb="1" idceifdedfeiefjgfcjfbchejgbcbeid_bb="1"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>拾卡招领</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
<script src="<%=path%>/resources/js/jquery-2.0.min.js" type="text/javascript"></script>
<style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	function query(){
		var keyWord = $("#bt").val();
		if(keyWord == null || keyWord == ""){
			alert("请输入关键词查询!");
			return false;
		}else{
			$("#myForm").submit();
		}
	}
	
	function Submit(){
	  	var title=document.getElementById("title").value;
		if(title=='' || $.trim(title)==""){
			alert("请填写标题！");
			return false;
		}
		var bjdd=document.getElementById("bjdd").value;
		if(bjdd=='' ||$.trim(bjdd)==""){
			alert("请填写地点！");
			return false;
		}
	  	var contact=document.getElementById("contact").value;
		if(contact=='' ||$.trim(contact)==""){
			alert("请填写联系人！");
			return false;
		}
		var content=document.getElementById("content").value;
		if(content=='请在详情中留下联系方式' || $.trim(content)==""){
			alert("请填写详情！");
			return false;
		}
		$('#addInput').hide();
    }
	//返回
	function return_prepage()  
    {  
    if(window.document.referrer==""||window.document.referrer==window.location.href)  
    {  
    window.location.href="{dede:type}[field:typelink /]{/dede:type}";  
    }else  
    {  
    window.location.href=window.document.referrer;  
    }  
      
    }  
</script>

<script type="text/javascript">
    var i=0;
    var j=1;
    var s="1 ";
    var src;
    function toAddImg(j){
    	if(i>=0){
    		if ($("#file"+j).val().length > 0) {
    			$("#file"+j).val("");
    		}
    	}
    	$("#file"+j).click();
    }
    function selectFile(j){
    	if ($("#file"+j).val().length > 0) {
            ajaxFileUpload(j);
            i++;
        } else {
            alert("请选择图片");
        }
    }
    
    function hide(j){
    	 if(j=='1'){
		    	$('#firstDiv').show();
		    	setTimeout("$('#firstDiv').hide();",500); 
		    }
//     	 else if(j=='2'){
// 		    	$('#secondDiv').show();
// 		    	setTimeout("$('#secondDiv').hide();",500);
// 		    }else if(j=='3'){
// 		    	$('#thirdDiv').show();
// 		    	setTimeout("$('#thirdDiv').hide();",500);
// 		    }
    }
    function ajaxFileUpload(j) {
        $.ajaxFileUpload({
            url : "<%=path%>/util/file/fileUload?wjlx="+s+"&lxid=1", //用于文件上传的服务器端请求地址
            secureuri : false, //一般设置为false
            fileElementId : 'file'+j, //文件上传空间的id属性  <input type="file" id="file" name="file" />
            type : 'post',
            dataType : 'HTML', //返回值类型 一般设置为json
            success : function(data, status) //服务器成功响应处理函数 
            {
            	$.ajax({
    				url:'<%=path%>/util/file/getImgId',
    				type : 'post',
    				//dataType : 'json',
    				data:{
    					fname:data
    				},
    				success: function(data){
    					if(data=='1'){
    						alert("上传图片太大,请重新上传!");
    						return false;
    					}else{
    				    hide(j);
    				 	src="<%=path%>/util/file/getFile?imgId="+data;
    	                $("#img"+j).attr("src", src);
    	                $("#fileName"+j).val(src);
    	                $("#file"+j).val("");
    	                if (typeof (data.error) != 'undefined') {
    	                    if (data.error != '') {
    	                        alert(data.error);
    	                    } else {
    	                        alert(data.msg);
    	                    }
    	                };
//     	                $(".uploadDiv"+(j+1)).show();
    					}
    				},
    				error: function(XMLHttpRequest){
    						alert("网络错误");
    				}
    			});
            },
            error : function(data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        })
        return false;
    }
    
    function ignoreSpaces(string) {
    	var temp = "";
    	string = '' + string;
    	splitstring = string.split(" ");
    	for(i = 0; i < splitstring.length; i++)
    	temp += splitstring[i];
    	return temp;
    	} 
</script>
<style type="text/css">
	.style1{
		margin: 0px 5%;
		padding-top: 10px;
		padding-bottom: 5px;
	}
	.style2{
	    margin-bottom: 0;
        margin-left: 5%;
        margin-right: 5%;
        margin-top: 40px;
		padding-top: 20px;
	}
	.bg1{
		background-color: #37b0c9;
	}
	.font1{
		font-weight: bold;
		color: white;
		font-size: 16px;
	}
	.input1{
		margin-left:5%;
		width:60%;
		height: 30px;
	}
	.input2 {
		margin-left: 10px;
		width: 65%;
		padding: 5px 5px;
		font-size: 16px;
	}
	.input3 {
		background-color: #37b0c9;
		padding: 0px 40px;
		border: none;
		color: white;
		font-size: 16px;
		line-height: 30px;
		font-weight: bold;
	}
	.color1{
		color: #37b0c9;
	}
	.radioicon{
		margin-left: 20px;
		margin-right: 5px;
		position: relative;
		top:2px;
	}
	.margin1{
		margin-bottom: 10px;
	}
	.text1{
		float: left;
		width: 80px;
		text-align: right;
		line-height: 32px;
		height: 32px;
	}
	.submit1{
		margin-left: 10px;
		width: 65%;
		float: left;
		text-align: center;
	}
	.input4 {
		background-color: #e7838b;
		padding: 0px 14px;
		color: white;
	}
	.img1{
		width: 60px;
		height: 60px;
		margin-right: 5px;
		margin-bottom: 5px;
	}
	.on {
		background: #d9d9d9 url(./tpl/Wap/default/common/hs/img/sprBg.png) no-repeat -244px -4px;
		margin-right: 0;
		cursor: pointer;
		width: 60px;
		height: 60px;
	}
	.server_id_input{
	
	}
</style>
</head>

<body id="cardunion" class="mode_webapp2" style="background-color: white;">
<div class="bg1">
	<div class="style1">
		<div class="wwx_f_l" style="position: relative;top: 4px;">
		<a class="font1" href="<%=path%>/wsh/ShSwzl/toSkzlList?openId=${openId}">拾卡招领</a></div>
<%-- 		<form action="<%=path%>/wsh/ShSwzl/toSkzlList" id="myForm" class="wwx_f_l" style="width: 55%;margin-right: 10px;" method="post"> --%>
<!-- 			<input type="text" class="input1" name="bt" id="bt"> -->
<!-- 			<span><a class="font1" href="javascript:;" style="margin-left: 10px;" onclick="query();">查询</a></span> -->
<!-- 		</form> -->
		<div class="wwx_clear"></div>
		<a href="#" onclick="return_prepage();">
                  <div class="anniu">
			   <img  style="width:70%" src="<%=path%>/resources/img/fh.png" />
			      </div>
		    </a>
	</div>
</div>
<div class="style2">
	<form action="<%=path%>/wsh/ShSwzl/saveskzl" id="mForm" method="post">
		<input type="hidden" value="${openId}" id="openId" name="openId"> 
		<input type="hidden" value="3" id="typeId" name="type"> 
		<div class="margin1" style="clear:both">
<%-- 			<img alt="" src="<%=path%>/resources/images/icon11.png" class="radioicon" name="typeImg" val="2">招领 --%>
<%-- 			<img alt="" src="<%=path%>/resources/images/icon10.png" class="radioicon" name="typeImg" val="1">寻物 --%>
<%-- 			<img alt="" src="<%=path%>/resources/images/icon10.png" class="radioicon" name="typeImg" val="3">拾卡 --%>
		</div>
		<div class="margin1">
			<div class="text1">标题：</div>
			<input type="text" class="input2 wwx_f_l" name="title" value="拾到校园卡一张" id="title" readonly="readonly"  validatelabel="标题" validate="require;maxlength(100)">
			<div class="wwx_clear"></div>
		</div>
		<div class="margin1" id="kh">
			<div class="text1">学工号：</div>
			<input type="text" class="input2 wwx_f_l" name="kh" id="kh" validatelabel="学号/工号" validate="require;maxlength(100)" onBlur="this.value=ignoreSpaces(this.value);">
			<div class="wwx_clear"></div>
		</div>
		<div class="margin1">
			<div class="text1">地点：</div>
			<input type="text" class="input2 wwx_f_l" name="bjdd" id="bjdd" validatelabel="地点" validate="require;maxlength(200)">
			<div class="wwx_clear"></div>
		</div>
		<div class="margin1">
			<div class="text1">联系人：</div>
			<input type="text" class="input2 wwx_f_l" name="contact" id="contact" validatelabel="联系人" validate="require;maxlength(20)">
			<div class="wwx_clear"></div>
		</div>
		<div class="margin1">
			<div class="text1">详情：</div>
			<textarea class="input2 wwx_f_l" style="resize:none;height: 120px;margin-top: 0px; margin-left: 10px;" name="content" id="content"
			onfocus="if(value=='请在详情中留下联系方式'){value=''};" onblur="if (value ==''){value='请在详情中留下联系方式'};">
请在详情中留下联系方式</textarea>
			<div class="wwx_clear"></div>
		</div>
		<div class="margin1">
		    <div class="text1">图片：</div>
		    <div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(1);">
				<img id="img1" alt="点击上传图片"  align="left" style="height: 160px;width:160px;margin-left: 10px;text-align: center;margin-bottom: 10px"/>
				<span id="firstDiv" style="display:none;color:green;border:1px solid green;font-size:6px;">上传成功！</span>
		    </div>
            <div id="uploadDiv"  class="controls" style="position: absolute; left: 30%;  overflow: hidden; z-index: 0;">
              	<input id="file1"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(1);">
            </div> 
            <input type='hidden' id='fileName1' name='bz1' value=''>
		</div>
		<div class="margin1">
			<div class="text1"></div>
			<div class="submit1" style="width:100%;">
				<input type="submit" value="发布" class="input3" id="addInput" style="cursor: pointer;" onClick="return Submit();" >
			</div>
			<div class="wwx_clear"></div>
		</div>
	</form>
</div>
</body></html>