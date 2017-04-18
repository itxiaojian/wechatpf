<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
﻿
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>校园在线报修申请</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=path%>/resources/src/favicon.ico">
<link rel="stylesheet" media="screen"
	href="<%=path%>/resources/src/published.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/mobiscroll/mobiscroll.custom-2.13.2.min.css"> 
<link href="<%=path%>/resources/js/xzxx/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"> 
<!--时间控件mobiscroll-->  
<script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>  
<script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>  
<link href="<%=path%>/resources/mobiscroll/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />  
<link href="<%=path%>/resources/mobiscroll/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />  
<script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>  
<script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>
<!--富文本 -->
<script type="text/javascript" charset="utf-8" src="<%=path%>/resources/js/xzxx/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/resources/js/xzxx/umeditor.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/xzxx/lang/zh-cn/zh-cn.js"></script>
<!--富文本 --> 
<script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<%-- <script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT> --%>
<style id="mailListAppendCss" type="text/css">

</style>
<script type="text/javascript">
	$(function () {  
	    var currYear = (new Date()).getFullYear();    
	    var opt={};  
	    opt.date = {preset : 'date',minDate:new Date()};  
	    //opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };  
	    opt.datetime = {preset : 'datetime'};  
	    opt.time = {preset : 'time'};  
	    opt.default = {  
	        theme: 'jqm', //皮肤样式  
	        display: 'modal', //显示方式   
	        mode: 'scroller', //日期选择模式  
	        dateFormat : 'yy-mm-dd', // 日期格式 
	        dateOrder : 'yymmdd', //面板中日期排列格式  
	        lang:'zh',  
	        startYear:currYear - 20, //开始年份  
	        endYear:currYear + 20 //结束年份  
	    };  
	
	    $("#yysj").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));  
	    var optDateTime = $.extend(opt['datetime'], opt['default']);  
	    
	});
	
	function getWxxl(value){
		$.ajax({
			cache : true,
			type : "POST",
			url : "<%=path%>/wx/Bxsq/getWxxl",
			data : {sjzt:value},
			async : false,
			error : function(request) {
				alert("连接数据库失败，请联系管理员。");
			},
			success : function(data) {
				$("#wxxl").empty();
                $("<option value='-1'>请选择</option>").appendTo($("#wxxl"));  
                $.each(eval(data), function(i, item) {
                    $("<option value='" + item.id+"_"+item.ztmc + "'>" + item.ztmc + "</option>").appendTo($("#wxxl")); 
                });
			}
		});
	}
	
	function isMobile()
    {
    var s =$('#lxhm').val(); 
   // var reg0 = /^13\d{5,9}$/;
    //var reg1 = /^153\d{4,8}$/;
    //var reg2 = /^159\d{4,8}$/;
    //var reg3 = /^0\d{10,11}$/;
     var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
     var isMob=/^((\+?86)|(\(\+86\)))?(17[0123456789][0-9]{8}|13[0123456789][0-9]{8}|15[0123456789][0-9]{8}|18[0123456789][0-9]{8}|14[0123456789][0-9]{8}|1349[0-9]{7})$/;
     var my = false;
   // if (reg0.test(s))my=true;
    //if (reg1.test(s))my=true;
    //if (reg2.test(s))my=true;
    //if (reg3.test(s))my=true;
   if(isMob.test(s)||isPhone.test(s)){my=true}
        if(s!="")
        {
            if (!my)
            {
               alert('请输入正确的手机号码');
               $('#lxhm').value="";
               $('#lxhm').focus();
               return false;
            }
        }
    }
	
	function toSubmit(){
		var openId=$('#openId').val();
		if($('#ly').val()==null||$('#ly').val()=='' || $('#ly').val()=='-1'){
			alert("请选择楼宇信息！");
			return false;
		}
		if($('#lh').val()==null||$('#lh').val()==''){
			alert("请填写门楼号！");
			return false;
		}
		if($('#fw').val()==null||$('#fw').val()=='' || $('#fw').val()=='-1'){
			alert("请选择服务类型！");
			return false;
		}
		if($('#wxdl').val()==null||$('#wxdl').val()==''|| $('#wxdl').val()=='-1'){
			alert("请选择维修大类！");
			return false;
		}
		if($('#wxxl').val()=='-1'||$('#wxxl').val()==''){
			alert("请选择维修小类！");
			return false;
		}
		if($('#nr').val()==null||$('#nr').val()==''){
			alert("请填写具体内容！");
			return false;
		}
		if($('#yysj').val()==null||$('#yysj').val()==''){
			alert("请选择预约时间！");
			return false;
		}
		if($('#lxhm').val()==null||$('#lxhm').val()==''){
			alert("请填写联系方式！");
			return false;
		}
		
		/****/
		 var s =$('#lxhm').val(); 
		   // var reg0 = /^13\d{5,9}$/;
		    //var reg1 = /^153\d{4,8}$/;
		    //var reg2 = /^159\d{4,8}$/;
		    //var reg3 = /^0\d{10,11}$/;
		     var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
		     var isMob=/^((\+?86)|(\(\+86\)))?(17[0123456789][0-9]{8}|13[0123456789][0-9]{8}|15[0123456789][0-9]{8}|18[0123456789][0-9]{8}|14[0123456789][0-9]{8}|1349[0-9]{7})$/;
		     var my = false;
		   // if (reg0.test(s))my=true;
		    //if (reg1.test(s))my=true;
		    //if (reg2.test(s))my=true;
		    //if (reg3.test(s))my=true;
		   if(isMob.test(s)||isPhone.test(s)){my=true}
		        if(s!="")
		        {
		            if (!my)
		            {
		               alert('请输入正确的手机号码');
		               $('#lxhm').value="";
		               $('#lxhm').focus();
		               return false;
		            }
		        }
		/***/
	    //富文本框的内容填进input框中
		//$('#bz').val(UM.getEditor('myEditor').getContent());
		$('#tjInput').hide();
		var url="";
		if(openId==null||openId==''){
			url="<%=path%>/wx/Bxspyjb/save";
		}else{
			url="<%=path%>/wx/Bxsq/save";
		}
		$.ajax({
			cache : true,
			type : "POST",
			url : url,
			data : $('#new_entry').serialize(),// 你的formid
			async : false,
			error : function(request) {
				alert("提交失败，请联系管理员。");
			},
			success : function(data) {
				if(data=='1'){
					alert('提交成功！');
					if(openId!=''&&openId!=null){
						location.href ="<%=path%>/wx/Bxsq/toBxsqlcjl?openId="+openId;
					}else{
						location.href ="<%=path%>/wx/Bxspyjb/toBxsqlcjl";
					}
				}else if(data=='2'){
					alert("该申请已存在，请不要重复申请。");
				}else if(data=='0'){
					alert('您的报修正在处理中,请等待报修处理结束后再申请!');
			}else if(data=='3'){
				alert('您有未评价报修,请评价后再申请!');
			}else{
					alert("提交失败，请联系管理员。");
				}
			}
		});
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
    	                $(".uploadDiv"+(j+1)).show();
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
</script>
<style type="text/css">
.entry-container form fieldset .field {
    margin-bottom: 1px;
    padding-bottom: 1px;
}
</style>
<script type="text/javascript">

var appid="${map1.appid}";//$('#appid').val();
var nonceStr="${map1.nonceStr}";//$('#nonceStr').val();
var timestamp="${map1.timestamp}";//$('#timestamp').val();
var signature="${map1.signature}";//$('#signature').val();
wx.config({
  debug: false,
  appId: '${map1.appid}',
  timestamp: '${map1.timestamp}',
  nonceStr: '${map1.nonceStr}',
  signature: '${map1.signature}',
  jsApiList: [
    'checkJsApi',
    'onMenuShareTimeline',
    'onMenuShareAppMessage',
    'onMenuShareQQ',
    'onMenuShareWeibo',
    'hideMenuItems',
    'showMenuItems',
    'hideAllNonBaseMenuItem',
    'showAllNonBaseMenuItem',
    'translateVoice',
    'startRecord',
    'stopRecord',
    'onRecordEnd',
    'playVoice',
    'pauseVoice',
    'stopVoice',
    'uploadVoice',
    'downloadVoice',
    'chooseImage',
    'previewImage',
    'uploadImage',
    'downloadImage',
    'getNetworkType',
    'openLocation',
    'getLocation',
    'hideOptionMenu',
    'showOptionMenu',
    'closeWindow',
    'scanQRCode',
    'chooseWXPay',
    'openProductSpecificView',
    'addCard',
    'chooseCard',
    'openCard'
  ]
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	wx.hideOptionMenu();
});
</script>
</head>
<body class="entry-container bg-image" onload="wx.hideOptionMenu();">
<div style="display:none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="报修申请">
				<a href="<%=path%>/wx/Bxsq/toBxsq" target="content" onFocus="this.blur()"><span>报修申请</span></a>
			</li>
		</ul>
	</div>

	<form class="center" id="new_entry" action="" accept-charset="UTF-8"
		method="post">
		<input type="hidden" id="openId" name="openId" value="${openId }">
		<div class="banner">
			<span class="banner-text banner-content"></span>


		</div>

		<h1 class="form-name">校园在线报修</h1>
		<div class="form-description">
			<p>欢迎进入校园在线报修系统，为认真做好校园维修服务工作，请大家如实填写，“*”号为必填项目。如遇紧急维修，请联系宿舍管理员或校园管理服务中心，请同学们认真填好报修单，切勿虚报和谎报，谢谢您的支持与配合!</p>
			<p><span style="color:red;">报修完成的时候,同学们需要对这次报修进行评价才可以再次进行新的报修.</span></p>
		</div>

		<fieldset>
			<div class="form-content">


				<div class="form-message gd-hide"></div>


				<div class="form-fields-container">
					<div class="field" data-api-code="field_13">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="ly"> 所在楼宇 <span
								style="color: red;">*</span>
							</label>
							<div class="field-content">

								<div class="controls">
									<select id="ly" name="ly">
										<option value="-1">请选择</option>
										<c:forEach var="data" items="${ly}" varStatus="obj">
											<option value="${data.zdz }">${data.zdz}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>


					</div>
					<div class="field" data-api-code="field_14">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="lh"> 楼号<span
								style="color: red;">*</span>
							</label>
							<div class="field-content">
								<div class="help-block">
									<p>例如：201/2楼</p>
								</div>

								<div class="controls">
									<!-- <input type="text" value="" name="lh"  onkeyup="this.value=this.value.replace(/\D/g,'')" 
									onafterpaste="this.value=this.value.replace(/\D/g,'')"
										id="lh" maxlength="4"> -->
									<input type="text" value="" name="lh" id="lh" />
								</div>

							</div>
						</div>


					</div>
					<div class="field" data-api-code="field_15">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="fw"> 服务 <span
								style="color: red;">*</span>
							</label>
							<div class="field-content">

								<div class="controls">
									<select id="fw" name="fw">
										<option value="-1">请选择</option>
										<option value="2">维修</option>
									</select>
								</div>

							</div>
						</div>


					</div>
					<div class="field" data-api-code="field_16">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="wxdl"> 维修大类 <span>（必选项）</span>
								<span style="color: red;">*</span>
							</label>
							<div class="field-content">

								<div class="controls">
									<select id="wxdl" name="wxdl" onChange="getWxxl(this.value);">
										<option value="-1">请选择</option>
										<c:forEach var="data" items="${wxdl}" varStatus="obj">
											<option value="${data.id }_${data.ztmc}">${data.ztmc}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
					</div>
					<div class="field" data-api-code="field_17">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="wxxl"> 维修小类 </label>
							<div class="field-content">

								<div class="controls">
									<select id="wxxl" name="wxxl">
										<option value="-1">请选择</option>
									</select>
								</div>

							</div>
						</div>

					</div>
					
					<div class="field" data-api-code="field_18">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="nr"> 具体原因 <span
								style="color: red;">*</span>
							</label>
							<div class="field-content">
								<div class="help-block">
									<p>为方便维修人员工作，请详细填写</p>
								</div>

								<div class="controls">
									<textarea style="resize: none;" rows="5" cols="10" name="nr" id="nr"></textarea>
								</div>

							</div>
						</div>


					</div>
					<div class="field" data-api-code="field_19">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="yysj"> 预约时间 <span
								style="color: red;">*</span>
							</label>
							<div class="field-content">
								<div class="help-block">
									<p>为提高维修效率，请如实填写</p>
								</div>

								<div class="controls">
<!-- 									<input type="text" value="" name="entry[field_19]" -->
<!-- 										id="entry_field_19"> -->
									<input id="yysj" name="yysj" 
										style="background: #fff none repeat scroll 0 0;
											   border: 1px solid #ccc;
											   border-radius: 2px;
											   box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
											   color: #555;
											   font-size: 12px;
											   font-weight: normal;
											   height: 30px;
											   line-height: 20px;
											   margin-bottom: 10px;
											   padding: 4px 6px;
											   width: 216px;"/>
								</div>

							</div>
						</div>


					</div>
					<div class="field" data-api-code="field_20">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="lxhm"> 联系方式<span
								style="color: red;"> *</span> </label>
							<div class="field-content">
								<div class="help-block">
									<p>为方便沟通，请提供联系电话！</p>
								</div>

								<div class="controls">
									<input type="tel" value="" name="lxhm"  onkeyup="this.value=this.value.replace(/\D/g,'')" 
									onafterpaste="this.value=this.value.replace(/\D/g,'')"
										id="lxhm" >

								</div>

							</div>
						</div>


					</div>
					<div class="field" data-api-code="field_21">
						<div class="form-group ">
							<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="bz">其它说明 (可上传三张图片)</label>
							<div class="field-content">

								<!-- <div class="controls">
									<textarea style="resize: none;" rows="5" cols="10" name="bz" id="bz"></textarea>
								
								 	<script type="text/plain" id="myEditor" style="width:216px;height:100px;align:left;"></script>
									<input style="display:none;" id="bz" name="bz" value=""/>
								</div>
								<script type="text/javascript">
    							//实例化编辑器
        						//实例化编辑器
    							// var um = UE.getEditor('myEditor');
   							   //本来是这样的:UE.getEditor('editor');  传入参数后就是下面那样子了，toolbars里的就是工具的图标
       							UM.getEditor('myEditor', {
    	  						 toolbar:[ 'source | undo redo | bold italic underline | strikethrough image']}); 
                               $('#myEditor').attr("align","left");
                               </script>  -->
                               
                            <div class="field-content uploadDiv1">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(1);">
								<img id="img1" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 25px; left: 35px; width: 163px; height: 161px; overflow: hidden; z-index: 0;">
	        	               <input id="file1"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(1);">
	                        </div> 
	                        <input type='hidden' id='fileName1' name='bz1' value=''>
							</div>
							
						   <div class="field-content uploadDiv2" style="display:none;">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(2);">
								<img id="img2" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 25px; left: 35px; width: 163px; height: 161px; overflow: hidden; z-index: 0;">
	        	               <input id="file2"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(2);">
	                        </div> 
	                        <input type='hidden' id='fileName2' name='bz2' value=''>
							</div>
							
						    <div class="field-content uploadDiv3" style="display:none;">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(3);">
								<img id="img3" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 25px; left: 35px; width: 163px; height: 161px; overflow: hidden; z-index: 0;">
	        	               <input id="file3"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(3);">
	                        </div> 
	                        <input type='hidden' id='fileName3' name='bz3' value=''>
							</div>
			
							</div>
						</div>
					</div>
				</div>

				<div class="field submit-field ">
					<div class="value">
						<input type="button" id="tjInput" name="commit" value="提交" onClick="toSubmit();"
							data-disable-with="提交中..." class="submit btn btn-primary">
					</div>
				</div>
			</div>
		</fieldset>
	</form>

	<div id="form_page_error_messages_modal" class="modal warning  "
		tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>×</span>
					</button>
					<h4 class="modal-title">当前页面填写有错误！</h4>
				</div>
				<div class="modal-body clearfix">
					<div class="error-explanation">
						<h5>错误提醒：</h5>
						<ul>
						</ul>
					</div>
				</div>
				<div class="modal-footer">
					<a data-dismiss="modal" class="btn btn-info"
						href="javascript:void(0)">返回修改</a>
				</div>
			</div>
		</div>
	</div>
	
	<div id="lightboxOverlay" class="lightboxOverlay"
		style="display: none;"></div>
</body>
</html>