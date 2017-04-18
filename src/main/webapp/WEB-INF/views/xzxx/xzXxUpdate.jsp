<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<html bdgjjgagedbdaebhbjbcabcdgeeebecf_bb="1" eiiebffcjbffiheggaebebgceaeccbia_bb="1" idceifdedfeiefjgfcjfbchejgbcbeid_bb="1"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>校长信箱</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/js/xzxx/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script src="<%=path%>/resources/js/jquery-2.0.min.js" type="text/javascript"></script>
<style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<!--富文本 -->
<script type="text/javascript" charset="utf-8" src="<%=path%>/resources/js/xzxx/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/resources/js/xzxx/umeditor.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/xzxx/lang/zh-cn/zh-cn.js"></script>
<!--富文本 --> 
<script type="text/javascript">
	function query(){
		var keyWord = $("#code").val();
		if(keyWord == null || keyWord == ""){
			alert("请输入关键词查询!");
			return false;
		}else{
			$("#myForm").submit();
		}
	}
	
	function Submit(){
		
		//富文本框的内容填进input框中
		$('#xjnr').val(UM.getEditor('myEditor').getContent());
		
	  	var xjbt=document.getElementById("xjbt").value;
		if(xjbt==''){
			alert("请填写信件标题！");
			return false;
		}
	  	var slbmbh=document.getElementById("slbmbh").value;
		if(slbmbh=='-1'){
			alert("请选择受理部门！");
			return false;
		}
	  	/*var txdz=document.getElementById("txdz").value;
		if(txdz==''){
			alert("请填写通讯地址！");
			return false;
		}
	  	var lxdh=document.getElementById("lxdh").value;
		if(lxdh==''){
			alert("请填写联系电话！");
			return false;
		}*/
	  	var xjnr=document.getElementById("xjnr").value;
		if(xjnr==''){
			alert("请填写信件内容！");
			return false;
		}
		var url = '<%=path%>/xzxx/XxXjxxb/updateXj';
		var openId = document.getElementById("openId").value;
		$.ajax({
			url: url,
			type : 'post',
			dataType : 'json',
			data: $('#mForm').serialize(),
			success: function(data){
				if(data != null || data != ''){
						location.href ="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId="+openId;
				}else{
					alert("提交失败");
				}
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
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
	function clearVal(_this){
		jQuery(_this).val("");
	};
	$(function(){
		$("[name='typeImg']").click(function(){
			var val = parseInt(jQuery(this).attr("val"));
			if(val == 2){
				$(this).attr("src","<%=path%>/resources/images/icon10.png");
				$("[val='1']").attr("src","<%=path%>/resources/images/icon11.png");
			}else if(val == 1){
				$(this).attr("src","<%=path%>/resources/images/icon10.png");
				$("[val='2']").attr("src","<%=path%>/resources/images/icon11.png");
			}
			
			jQuery("#typeId").val(val);
		});
	});
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
		width: 100px;
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
		<a class="font1" href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}">校长信箱</a></div>
		<form action="<%=path%>/xzxx/XxXjxxb/toXzxxList" id="myForm" class="wwx_f_l" style="width: 55%;margin-right: 10px;" method="post">
			<input type="text" class="input1" name="code" id="code">
			<input type="hidden" name="openId" id="openId" value="${openId }">
			<span><a class="font1" href="javascript:;" style="margin-left: 10px;" onclick="query();">查询</a></span>
		</form>
		<div class="wwx_clear"></div>
		<a href="#" onclick="return_prepage();">
                  <div class="anniu">
			   <img  style="width:70%" src="<%=path%>/resources/img/fh.png" />
			      </div>
		    </a>
	</div>
</div>
<div class="style2">
	<form action="" id="mForm" method="post">
		<input type="hidden" value="${openId}" id="openId" name="openId"> 
		<input type="hidden" value="1" id="typeId" name="type"> 
		<input type="hidden" value="${id }" id="xjid" name="xjid"> 
		<div class="margin1" style="clear:both">
			<span>修改信件</span>
		</div>
		<div class="margin1">
			<div class="text1">信件标题：</div>
			<input type="text" class="input2 wwx_f_l" name="xjbt" id="xjbt" validatelabel="信件标题" validate="require;maxlength(32)" value="${map.xjbt }">
			<div class="wwx_clear"></div>
		</div>
		<div class="margin1">
			<div class="text1">受理部门：</div>
			<select id="slbmbh" name="slbmbh" class="input2 wwx_f_l" >
				<option value="-1">请选择...</option>
				<c:forEach var="data" items="${list}" varStatus="obj">
				<option value="${data.bmbh }" <c:if test="${map.slbmbh==data.bmbh }">selected</c:if>>${data.bmmc }</option>
				</c:forEach>
			</select>
			<div class="wwx_clear"></div>
		</div>
		<div class="margin1">
			<div class="text1">电子邮箱：</div>
			<input type="text" class="input2 wwx_f_l" name="txdz" id="txdz" validatelabel="通讯地址" validate="require;maxlength(256)" value="${map.txdz }">
			<div class="wwx_clear"></div>
		</div>
		<div class="margin1">
			<div class="text1">联系电话：</div>
			<input type="text" class="input2 wwx_f_l" name="lxdh" id="lxdh" validatelabel="联系电话" validate="require;maxlength(20)" value="${map.lxdh }">
			<div class="wwx_clear"></div>
		</div>
		<%-- <div class="margin1">
			<div class="text1">信件内容：</div>
			<textarea class="input2 wwx_f_l" style="resize:none;height: 120px;margin-top: 0px; margin-left: 10px;" name="xjnr" id="xjnr">${map.xjnr }</textarea>
			<div class="wwx_clear"></div>
		</div> --%>
	    <div class="margin1">
		<script type="text/plain" id="myEditor" style="width:100%;height:50%;align:left;">${map.xjnr}</script>
		<div class="clear"></div>
		<input style="display:none;" id="xjnr" name="xjnr" value=""/>
		</div>
		<%-- <div class="margin1">
			<div class="text1">备注：</div>
			<textarea class="input2 wwx_f_l" style="resize:none;height: 120px;margin-top: 0px; margin-left: 10px;" name="bz" id="bz">${map.bz }</textarea>
			<div class="wwx_clear"></div>
		</div> --%>
		<div class="margin1">
			<div class="text1"></div>
			<div class="submit1">
				<input type="button" value="发送" class="input3" id="addInput" style="cursor: pointer;" onClick="Submit();" >
			</div>
			<div class="wwx_clear"></div>
		</div>
	</form>
<script type="text/javascript">
      //实例化编辑器
     //实例化编辑器
    // var um = UE.getEditor('myEditor');
   //本来是这样的:UE.getEditor('editor');  传入参数后就是下面那样子了，toolbars里的就是工具的图标
       UM.getEditor('myEditor', {
    	   toolbar:[
                    'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
                    'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
                    '| justifyleft justifycenter justifyright justifyjustify |',
                    'link unlink | image',
                    ' horizontal  preview '
                ]
      
}); 
       $('#myEditor').attr("align","left");
</script>
</div>
</body></html>