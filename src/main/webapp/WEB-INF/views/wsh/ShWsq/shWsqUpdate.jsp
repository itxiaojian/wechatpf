<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>微信上墙</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<script type="text/javascript">
	
</script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/jquery-ui-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/core.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/home.css">

	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/activity.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/uploadify.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/lightbox.css">

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
<%-- 	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery-ui-1.js"></script> --%>
	<script type="text/javascript" src="<%=path%>/resources/js/wsh/shwhb/jquery_002.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/filter.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/global.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/datePicker/WdatePicker.js"></script>
<%-- 	<script type="text/javascript" src="<%=path%>/resources/js/wbm/home.js"></script> --%>
<script type="text/javascript">
jQuery(function($) {
	$("#js_removeCover").click(function(){
		$(".cover_preview").hide();
		$("#cover_upload").show();
		$("#cover_input").val("");
		$('#add-bg-img').attr('src', '');
		$('.add-bg').removeClass('has-bg');
	});

	$("#js_removeLogo").click(function(){
		$(".logo_preview").hide();
		$("#logo_upload").show();
		$("#logo").val("");
	});

	$('.js-bg-selected').click(function(e) {
		e.preventDefault();
		$('#skin_id').val($(this).data('skinid'));
		if ($(this).parents('.skin-img-pa').length > 0) {
			$('.icon-select').appendTo($(this).parents('.skin-img-pa'));
		} else {
			$('.icon-select').appendTo($(this).parents('.add-bg'));
		}
	});

	$('.add-bg .plus-large').click(function(e) {
		$('#upload_bg_modal').modal('show');
	});

	$('.add-bg-pa .show-edit-bg').click(function(e) {
		$('#upload_bg_modal').modal('show');
	});

	$('#upload_bg_modal').modal({
		show: false
	});

	/*
	 * 提交表单的检测
	 */
	$("#wall-form").validate({
		rules: {
			name:{
				required: true,
				minlength:1,
				maxlength:24
			},
			startdate: {
				required: true
			},
			enddate :{
				required: true
			}
		},
		messages: {
			name:{
				required:"请填写上墙活动名称",
				maxlength:"不能超过24个字"
			},
			startdate: {
				required: "请填写上墙开始时间"
			},
			enddate :{
				required: "请填写上墙截止时间"
			}
		},
		submitHandler: function(form) {
			// 检查时间是否冲突
			var startTime = $("#start-at").val() + " " + $("#starthour").val() + ":" + $("#startminute").val() + ":" + "00";
			var endTime = $("#end-at").val() + " " + $("#endhour").val() + ":" + $("#endminute").val() + ":" + "00";
			var id = $("#wsqid").val();
			if (startTime >= endTime) {
				alert("结束时间不能小于开始时间");
				return false;
			}
			$.ajax({
				url: "<%=path%>/wsh/ShWsq/check",
				type : 'post',
				dataType : 'json',
				data: {start:startTime,end:endTime,id:id},
				success: function(data){
					if(data.message == null || data.message == ''){
						form.submit();
					}else{
						alert("开始结束时间与[" +data.message + "]活动冲突，同一时间只允许有一场上墙活动。");
					}
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
		}
	});
});

function selectFile(){
	if ($("#file1").val().length > 0) {
        ajaxFileUpload();
        //i++;
    } else {
        alert("请选择图片");
    }
}
function ajaxFileUpload() {
    $.ajaxFileUpload({
        url : '<%=path%>/wsh/upload/tempimg', //用于文件上传的服务器端请求地址
        secureuri : false, //一般设置为false
        fileElementId : 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
        type : 'post',
        dataType : 'HTML', //返回值类型 一般设置为json
        success : function(data, status) //服务器成功响应处理函数
        {
            $("#logo_image").attr("src", data);
            $(".logo_preview").show();
            $("#logo").val(data);
            $("#file1").val("");
            if (typeof (data.error) != 'undefined') {
                if (data.error != '') {
                    alert(data.error);
                } else {
                    alert(data.msg);
                }
            }
        },
        error : function(data, status, e)//服务器响应失败处理函数
        {
            alert(e);
        }
    })
    return false;
}
</script>
	</head>
	
	<body>
		<div class="container">
			<div class="row home-container">
				<div class="span2">
				</div>
				<div class="span12">
					<div class="right-content">
						
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/lightbox.js"></script>

<h3>编辑上墙</h3>
<div>
	<form novalidate="novalidate" id="wall-form" class="form-horizontal" method="post" action="<%=path%>/wsh/ShWsq/update">
		<fieldset>
			<input id="wall-id" value="0" type="hidden">
			<input id="wsqid" name="wsqid" value="${wsq.id }" type="hidden">
			
			<div class="control-group">
		        <label class="control-label" for="input01"><span class="required">*</span>活动名称：</label>
		        <div class="controls">
		            <input name="name" placeholder="" class="input-xlarge" type="text" value="${wsq.hdmc }">
		            <p class="help-block"></p>
		        </div>
		    </div>
		    
	    	<div class="control-group">
	          	<label class="control-label" for="input01">LOGO：</label>
	          	<div class="controls upload">
		          	<input id="file1" name="file" multiple="false" type="file" accept="image/*" capture="camera" onchange="selectFile();">
		          	<input id="logo" name="logo" value="${wsq.logo }" type="hidden">
		          	<c:if test="${wsq.logo eq null }">
			            <p class="logo_preview hide">
							<img id="logo_image" src="${wsq.logo }" style="width:120px">
							<a id="js_removeLogo" href="javascript:void(0);" onclick="return false;">删除</a>
						</p>
					</c:if>
					<c:if test="${wsq.logo != null }">
			            <p class="logo_preview">
							<img id="logo_image" src="${wsq.logo }" style="width:120px">
							<a id="js_removeLogo" href="javascript:void(0);" onclick="return false;">删除</a>
						</p>
					</c:if>            
		            <p class="help-block"></p>
	        	</div>
	    	</div>

       		<!--<div class="modal hide" id="upload_bg_modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3>上传自定义背景</h3>
				</div>
				<div class="modal-body" style="">
					<div class="form-horizontal">
						<div class="control-group">
				          	<label class="control-label" for="input01">背景图：</label>
				          	<div class="controls upload">
					          	<input id="cover_upload" name="background" multiple="false" type="file">
					          	<input id="cover_input" name="cover" value="" type="hidden">
					            <p class="cover_preview hide">
								<img id="cover_image" src="" style="width:120px">
								<a id="js_removeCover" href="javascript:void(0);" onclick="return false;">删除</a>
								</p>
					            <p class="help-block"></p>
				          	</div>
			       		</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:void(0)" class="btn" data-dismiss="modal">确定</a>
				</div>
			</div>

       		<div class="control-group">
	          	<label class="control-label" for="input01">上墙皮肤：</label>
	          	<div class="controls upload">
		          	<input id="skin_id" name="skin_id" value="1" type="hidden">
		            <ul class="skin-list">
		            			            	<li>
		            		<div class="skin-img-pa" data-skinid="1">
		            			<div class="manipulate">
		            						            				<a href="<%=path%>/resources/js/wbm/default.jpg" data-lightbox="default" class="skin-preview">预览</a>
		            						            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="1">选择</a>
		            			</div>
		            			<img src="<%=path%>/resources/js/wbm/default.jpg" alt="default" title="default" height="60" width="85">
		            					            			<i class="icon-select"></i>
		            					            					            			<script type="text/javascript">
		            				document.getElementById('skin_id').value = 1		            			</script>
		            					            		</div>
		            	</li>
		            			            	<li>
		            		<div class="skin-img-pa" data-skinid="2">
		            			<div class="manipulate">
		            						            				<a href="<%=path%>/resources/js/wbm/happiness.jpg" data-lightbox="happiness" class="skin-preview">预览</a>
		            						            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="2">选择</a>
		            			</div>
		            			<img src="<%=path%>/resources/js/wbm/happiness.jpg" alt="happiness" title="happiness" height="60" width="85">
		            					            					            		</div>
		            	</li>
		            			            	<li>
		            		<div class="skin-img-pa" data-skinid="3">
		            			<div class="manipulate">
		            						            				<a href="<%=path%>/resources/js/wbm/cartoon.jpg" data-lightbox="cartoon" class="skin-preview">预览</a>
		            						            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="3">选择</a>
		            			</div>
		            			<img src="<%=path%>/resources/js/wbm/cartoon.jpg" alt="cartoon" title="cartoon" height="60" width="85">
		            					            					            		</div>
		            	</li>
		            			            	<li>
		            		<div class="skin-img-pa" data-skinid="4">
		            			<div class="manipulate">
		            						            				<a href="<%=path%>/resources/js/wbm/cosmos.jpg" data-lightbox="cosmos" class="skin-preview">预览</a>
		            						            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="4">选择</a>
		            			</div>
		            			<img src="<%=path%>/resources/js/wbm/cosmos.jpg" alt="cosmos" title="cosmos" height="60" width="85">
		            					            					            		</div>
		            	</li>
		            			            	<li>
		            		<div class="skin-img-pa" data-skinid="5">
		            			<div class="manipulate">
		            						            				<a href="<%=path%>/resources/js/wbm/darkred.jpg" data-lightbox="darkred" class="skin-preview">预览</a>
		            						            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="5">选择</a>
		            			</div>
		            			<img src="<%=path%>/resources/js/wbm/darkred.jpg" alt="darkred" title="darkred" height="60" width="85">
		            					            					            		</div>
		            	</li>
		            			            	<li>
		            		<div class="skin-img-pa" data-skinid="6">
		            			<div class="manipulate">
		            						            				<a href="<%=path%>/resources/js/wbm/field.jpg" data-lightbox="field" class="skin-preview">预览</a>
		            						            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="6">选择</a>
		            			</div>
		            			<img src="<%=path%>/resources/js/wbm/field.jpg" alt="field" title="field" height="60" width="85">
		            					            					            		</div>
		            	</li>
		            			            	<li>
		            		<div class="skin-img-pa" data-skinid="7">
		            			<div class="manipulate">
		            						            				<a href="<%=path%>/resources/js/wbm/horizon.jpg" data-lightbox="horizon" class="skin-preview">预览</a>
		            						            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="7">选择</a>
		            			</div>
		            			<img src="<%=path%>/resources/js/wbm/horizon.jpg" alt="horizon" title="horizon" height="60" width="85">
		            					            					            		</div>
		            	</li>
		            			            	<li>
		            		<div class="skin-img-pa" data-skinid="8">
		            			<div class="manipulate">
		            						            				<a href="<%=path%>/resources/js/wbm/spot.jpg" data-lightbox="spot" class="skin-preview">预览</a>
		            						            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="8">选择</a>
		            			</div>
		            			<img src="<%=path%>/resources/js/wbm/spot.jpg" alt="spot" title="spot" height="60" width="85">
		            					            					            		</div>
		            	</li>
		            	
		            	<li>
		            		<div class="add-bg-pa" data-skinid="0">
		            			<div class="add-bg ">
		            				<div class="manipulate">
			            				<a href="javascript:void(0);" class="show-edit-bg">编辑</a>
			            				<a href="javascript:void(0);" class="js-bg-selected" data-skinid="0">选择</a>
			            			</div>
			            			<span class="plus-large">＋</span>
			            			<img id="add-bg-img" src="" height="60" width="85">
			            						            		</div>
			            		<div class="upload-bg-tips">自定义背景</div>
		            		</div>
		            	</li>
		            </ul>
		            <p class="help-block"></p>
	          	</div>
       		</div>-->
	    	
	    	<div class="control-group">
	          	<label class="control-label" for="input01"><span class="required">*</span>开始时间：</label>
	          	<div class="controls end-at">
<!-- 		            <input id="start-at" name="startdate" class="input-xlarge time-pick hasDatepicker" type="text"> -->
		            <input id="start-at" type="text" name="startdate" onClick="WdatePicker()" value="${startdate }"/>
		            <div class="hour-minute">
			            <select id="starthour" class="target-hour" name="starthour" style="width: 55px">
												<option <c:if test="${starthour=='00' }">selected="selected"</c:if> >00</option>
												<option <c:if test="${starthour=='01' }">selected="selected"</c:if> >01</option>
												<option <c:if test="${starthour=='02' }">selected="selected"</c:if> >02</option>
												<option <c:if test="${starthour=='03' }">selected="selected"</c:if> >03</option>
												<option <c:if test="${starthour=='04' }">selected="selected"</c:if> >04</option>
												<option <c:if test="${starthour=='05' }">selected="selected"</c:if> >05</option>
												<option <c:if test="${starthour=='06' }">selected="selected"</c:if> >06</option>
												<option <c:if test="${starthour=='07' }">selected="selected"</c:if> >07</option>
												<option <c:if test="${starthour=='08' }">selected="selected"</c:if> >08</option>
												<option <c:if test="${starthour=='09' }">selected="selected"</c:if> >09</option>
												<option <c:if test="${starthour=='10' }">selected="selected"</c:if> >10</option>
												<option <c:if test="${starthour=='11' }">selected="selected"</c:if> >11</option>
												<option <c:if test="${starthour=='12' }">selected="selected"</c:if> >12</option>
												<option <c:if test="${starthour=='13' }">selected="selected"</c:if> >13</option>
												<option <c:if test="${starthour=='14' }">selected="selected"</c:if> >14</option>
												<option <c:if test="${starthour=='15' }">selected="selected"</c:if> >15</option>
												<option <c:if test="${starthour=='16' }">selected="selected"</c:if> >16</option>
												<option <c:if test="${starthour=='17' }">selected="selected"</c:if> >17</option>
												<option <c:if test="${starthour=='18' }">selected="selected"</c:if> >18</option>
												<option <c:if test="${starthour=='19' }">selected="selected"</c:if> >19</option>
												<option <c:if test="${starthour=='20' }">selected="selected"</c:if> >20</option>
												<option <c:if test="${starthour=='21' }">selected="selected"</c:if> >21</option>
												<option <c:if test="${starthour=='22' }">selected="selected"</c:if> >22</option>
												<option <c:if test="${starthour=='23' }">selected="selected"</c:if> >23</option>
															</select>&nbsp;<span>时</span>
						<select id="startminute" class="target-minute" name="startminute" style="width: 55px">
												<option <c:if test="${startmin=='00' }">selected="selected"</c:if> >00</option>
												<option <c:if test="${startmin=='01' }">selected="selected"</c:if> >01</option>
												<option <c:if test="${startmin=='02' }">selected="selected"</c:if> >02</option>
												<option <c:if test="${startmin=='03' }">selected="selected"</c:if> >03</option>
												<option <c:if test="${startmin=='04' }">selected="selected"</c:if> >04</option>
												<option <c:if test="${startmin=='05' }">selected="selected"</c:if> >05</option>
												<option <c:if test="${startmin=='06' }">selected="selected"</c:if> >06</option>
												<option <c:if test="${startmin=='07' }">selected="selected"</c:if> >07</option>
												<option <c:if test="${startmin=='08' }">selected="selected"</c:if> >08</option>
												<option <c:if test="${startmin=='09' }">selected="selected"</c:if> >09</option>
												<option <c:if test="${startmin=='10' }">selected="selected"</c:if> >10</option>
												<option <c:if test="${startmin=='11' }">selected="selected"</c:if> >11</option>
												<option <c:if test="${startmin=='12' }">selected="selected"</c:if> >12</option>
												<option <c:if test="${startmin=='13' }">selected="selected"</c:if> >13</option>
												<option <c:if test="${startmin=='14' }">selected="selected"</c:if> >14</option>
												<option <c:if test="${startmin=='15' }">selected="selected"</c:if> >15</option>
												<option <c:if test="${startmin=='16' }">selected="selected"</c:if> >16</option>
												<option <c:if test="${startmin=='17' }">selected="selected"</c:if> >17</option>
												<option <c:if test="${startmin=='18' }">selected="selected"</c:if> >18</option>
												<option <c:if test="${startmin=='19' }">selected="selected"</c:if> >19</option>
												<option <c:if test="${startmin=='20' }">selected="selected"</c:if> >20</option>
												<option <c:if test="${startmin=='21' }">selected="selected"</c:if> >21</option>
												<option <c:if test="${startmin=='22' }">selected="selected"</c:if> >22</option>
												<option <c:if test="${startmin=='23' }">selected="selected"</c:if> >23</option>
												<option <c:if test="${startmin=='24' }">selected="selected"</c:if> >24</option>
												<option <c:if test="${startmin=='25' }">selected="selected"</c:if> >25</option>
												<option <c:if test="${startmin=='26' }">selected="selected"</c:if> >26</option>
												<option <c:if test="${startmin=='27' }">selected="selected"</c:if> >27</option>
												<option <c:if test="${startmin=='28' }">selected="selected"</c:if> >28</option>
												<option <c:if test="${startmin=='29' }">selected="selected"</c:if> >29</option>
												<option <c:if test="${startmin=='30' }">selected="selected"</c:if> >30</option>
												<option <c:if test="${startmin=='31' }">selected="selected"</c:if> >31</option>
												<option <c:if test="${startmin=='32' }">selected="selected"</c:if> >32</option>
												<option <c:if test="${startmin=='33' }">selected="selected"</c:if> >33</option>
												<option <c:if test="${startmin=='34' }">selected="selected"</c:if> >34</option>
												<option <c:if test="${startmin=='35' }">selected="selected"</c:if> >35</option>
												<option <c:if test="${startmin=='36' }">selected="selected"</c:if> >36</option>
												<option <c:if test="${startmin=='37' }">selected="selected"</c:if> >37</option>
												<option <c:if test="${startmin=='38' }">selected="selected"</c:if> >38</option>
												<option <c:if test="${startmin=='39' }">selected="selected"</c:if> >39</option>
												<option <c:if test="${startmin=='40' }">selected="selected"</c:if> >40</option>
												<option <c:if test="${startmin=='41' }">selected="selected"</c:if> >41</option>
												<option <c:if test="${startmin=='42' }">selected="selected"</c:if> >42</option>
												<option <c:if test="${startmin=='43' }">selected="selected"</c:if> >43</option>
												<option <c:if test="${startmin=='44' }">selected="selected"</c:if> >44</option>
												<option <c:if test="${startmin=='45' }">selected="selected"</c:if> >45</option>
												<option <c:if test="${startmin=='46' }">selected="selected"</c:if> >46</option>
												<option <c:if test="${startmin=='47' }">selected="selected"</c:if> >47</option>
												<option <c:if test="${startmin=='48' }">selected="selected"</c:if> >48</option>
												<option <c:if test="${startmin=='49' }">selected="selected"</c:if> >49</option>
												<option <c:if test="${startmin=='50' }">selected="selected"</c:if> >50</option>
												<option <c:if test="${startmin=='51' }">selected="selected"</c:if> >51</option>
												<option <c:if test="${startmin=='52' }">selected="selected"</c:if> >52</option>
												<option <c:if test="${startmin=='53' }">selected="selected"</c:if> >53</option>
												<option <c:if test="${startmin=='54' }">selected="selected"</c:if> >54</option>
												<option <c:if test="${startmin=='55' }">selected="selected"</c:if> >55</option>
												<option <c:if test="${startmin=='56' }">selected="selected"</c:if> >56</option>
												<option <c:if test="${startmin=='57' }">selected="selected"</c:if> >57</option>
												<option <c:if test="${startmin=='58' }">selected="selected"</c:if> >58</option>
												<option <c:if test="${startmin=='59' }">selected="selected"</c:if> >59</option>
															</select>&nbsp;<span>分</span>
					</div>
		            <p class="help-block"></p>
		        </div>
	    	</div>
	    	
	    	<div class="control-group">
	          	<label class="control-label" for="input01"><span class="required">*</span>截止时间：</label>
	          	<div class="controls end-at">
		            <input id="end-at" type="text" name="enddate" onClick="WdatePicker()" value="${enddate }"/>
		            <div class="hour-minute">
			            <select id="endhour" class="target-hour" name="endhour" style="width: 55px">
												<option <c:if test="${endhour=='00' }">selected="selected"</c:if> >00</option>
												<option <c:if test="${endhour=='01' }">selected="selected"</c:if> >01</option>
												<option <c:if test="${endhour=='02' }">selected="selected"</c:if> >02</option>
												<option <c:if test="${endhour=='03' }">selected="selected"</c:if> >03</option>
												<option <c:if test="${endhour=='04' }">selected="selected"</c:if> >04</option>
												<option <c:if test="${endhour=='05' }">selected="selected"</c:if> >05</option>
												<option <c:if test="${endhour=='06' }">selected="selected"</c:if> >06</option>
												<option <c:if test="${endhour=='07' }">selected="selected"</c:if> >07</option>
												<option <c:if test="${endhour=='08' }">selected="selected"</c:if> >08</option>
												<option <c:if test="${endhour=='09' }">selected="selected"</c:if> >09</option>
												<option <c:if test="${endhour=='10' }">selected="selected"</c:if> >10</option>
												<option <c:if test="${endhour=='11' }">selected="selected"</c:if> >11</option>
												<option <c:if test="${endhour=='12' }">selected="selected"</c:if> >12</option>
												<option <c:if test="${endhour=='13' }">selected="selected"</c:if> >13</option>
												<option <c:if test="${endhour=='14' }">selected="selected"</c:if> >14</option>
												<option <c:if test="${endhour=='15' }">selected="selected"</c:if> >15</option>
												<option <c:if test="${endhour=='16' }">selected="selected"</c:if> >16</option>
												<option <c:if test="${endhour=='17' }">selected="selected"</c:if> >17</option>
												<option <c:if test="${endhour=='18' }">selected="selected"</c:if> >18</option>
												<option <c:if test="${endhour=='19' }">selected="selected"</c:if> >19</option>
												<option <c:if test="${endhour=='20' }">selected="selected"</c:if> >20</option>
												<option <c:if test="${endhour=='21' }">selected="selected"</c:if> >21</option>
												<option <c:if test="${endhour=='22' }">selected="selected"</c:if> >22</option>
												<option <c:if test="${endhour=='23' }">selected="selected"</c:if> >23</option>
															</select>&nbsp;<span>时</span>
						<select id="endminute" class="target-minute" name="endminute" style="width: 55px">
												<option <c:if test="${endmin=='00' }">selected="selected"</c:if> >00</option>
												<option <c:if test="${endmin=='01' }">selected="selected"</c:if> >01</option>
												<option <c:if test="${endmin=='02' }">selected="selected"</c:if> >02</option>
												<option <c:if test="${endmin=='03' }">selected="selected"</c:if> >03</option>
												<option <c:if test="${endmin=='04' }">selected="selected"</c:if> >04</option>
												<option <c:if test="${endmin=='05' }">selected="selected"</c:if> >05</option>
												<option <c:if test="${endmin=='06' }">selected="selected"</c:if> >06</option>
												<option <c:if test="${endmin=='07' }">selected="selected"</c:if> >07</option>
												<option <c:if test="${endmin=='08' }">selected="selected"</c:if> >08</option>
												<option <c:if test="${endmin=='09' }">selected="selected"</c:if> >09</option>
												<option <c:if test="${endmin=='10' }">selected="selected"</c:if> >10</option>
												<option <c:if test="${endmin=='11' }">selected="selected"</c:if> >11</option>
												<option <c:if test="${endmin=='12' }">selected="selected"</c:if> >12</option>
												<option <c:if test="${endmin=='13' }">selected="selected"</c:if> >13</option>
												<option <c:if test="${endmin=='14' }">selected="selected"</c:if> >14</option>
												<option <c:if test="${endmin=='15' }">selected="selected"</c:if> >15</option>
												<option <c:if test="${endmin=='16' }">selected="selected"</c:if> >16</option>
												<option <c:if test="${endmin=='17' }">selected="selected"</c:if> >17</option>
												<option <c:if test="${endmin=='18' }">selected="selected"</c:if> >18</option>
												<option <c:if test="${endmin=='19' }">selected="selected"</c:if> >19</option>
												<option <c:if test="${endmin=='20' }">selected="selected"</c:if> >20</option>
												<option <c:if test="${endmin=='21' }">selected="selected"</c:if> >21</option>
												<option <c:if test="${endmin=='22' }">selected="selected"</c:if> >22</option>
												<option <c:if test="${endmin=='23' }">selected="selected"</c:if> >23</option>
												<option <c:if test="${endmin=='24' }">selected="selected"</c:if> >24</option>
												<option <c:if test="${endmin=='25' }">selected="selected"</c:if> >25</option>
												<option <c:if test="${endmin=='26' }">selected="selected"</c:if> >26</option>
												<option <c:if test="${endmin=='27' }">selected="selected"</c:if> >27</option>
												<option <c:if test="${endmin=='28' }">selected="selected"</c:if> >28</option>
												<option <c:if test="${endmin=='29' }">selected="selected"</c:if> >29</option>
												<option <c:if test="${endmin=='30' }">selected="selected"</c:if> >30</option>
												<option <c:if test="${endmin=='31' }">selected="selected"</c:if> >31</option>
												<option <c:if test="${endmin=='32' }">selected="selected"</c:if> >32</option>
												<option <c:if test="${endmin=='33' }">selected="selected"</c:if> >33</option>
												<option <c:if test="${endmin=='34' }">selected="selected"</c:if> >34</option>
												<option <c:if test="${endmin=='35' }">selected="selected"</c:if> >35</option>
												<option <c:if test="${endmin=='36' }">selected="selected"</c:if> >36</option>
												<option <c:if test="${endmin=='37' }">selected="selected"</c:if> >37</option>
												<option <c:if test="${endmin=='38' }">selected="selected"</c:if> >38</option>
												<option <c:if test="${endmin=='39' }">selected="selected"</c:if> >39</option>
												<option <c:if test="${endmin=='40' }">selected="selected"</c:if> >40</option>
												<option <c:if test="${endmin=='41' }">selected="selected"</c:if> >41</option>
												<option <c:if test="${endmin=='42' }">selected="selected"</c:if> >42</option>
												<option <c:if test="${endmin=='43' }">selected="selected"</c:if> >43</option>
												<option <c:if test="${endmin=='44' }">selected="selected"</c:if> >44</option>
												<option <c:if test="${endmin=='45' }">selected="selected"</c:if> >45</option>
												<option <c:if test="${endmin=='46' }">selected="selected"</c:if> >46</option>
												<option <c:if test="${endmin=='47' }">selected="selected"</c:if> >47</option>
												<option <c:if test="${endmin=='48' }">selected="selected"</c:if> >48</option>
												<option <c:if test="${endmin=='49' }">selected="selected"</c:if> >49</option>
												<option <c:if test="${endmin=='50' }">selected="selected"</c:if> >50</option>
												<option <c:if test="${endmin=='51' }">selected="selected"</c:if> >51</option>
												<option <c:if test="${endmin=='52' }">selected="selected"</c:if> >52</option>
												<option <c:if test="${endmin=='53' }">selected="selected"</c:if> >53</option>
												<option <c:if test="${endmin=='54' }">selected="selected"</c:if> >54</option>
												<option <c:if test="${endmin=='55' }">selected="selected"</c:if> >55</option>
												<option <c:if test="${endmin=='56' }">selected="selected"</c:if> >56</option>
												<option <c:if test="${endmin=='57' }">selected="selected"</c:if> >57</option>
												<option <c:if test="${endmin=='58' }">selected="selected"</c:if> >58</option>
												<option <c:if test="${endmin=='59' }">selected="selected"</c:if> >59</option>
															</select>&nbsp;<span>分</span>
					</div>
		            <p class="help-block"></p>
		        </div>
	    	</div>
	    	
	    	<div class="control-group">
          	<label class="control-label"></label>
          	<div class="controls">
          		<input class="btn btn-success" value="保存" type="submit">
          	</div>
     	</div>
		</fieldset>
	</form>
</div>

					</div>
				</div>
			</div>
		</div>
		
<style>
.version-modal{
	padding-bottom:20px; 
}
.version-modal .version-model-body{
	padding: 20px;
	margin-bottom: 20px;
	max-height: 100%;
}
.version-modal .version-model-body ul,
.version-modal .version-model-body ol {
	margin: 14px 0 14px 0;
	padding: 0 0 0 40px;
}
.version-modal .version-model-body ul,
.version-modal .version-model-body ul li{
	list-style: disc;
}
.version-modal .version-model-body ol,
.version-modal .version-model-body ol li{
	list-style: decimal;
}

.version-header{
	height: 70px;
	text-align: center;
	background-color: #f3f3f3;
}
.version-publish-date{
	color: #ac7b53;
	margin-bottom: 10px;
}
.version-sure-btn{
	margin-left:40%;
	margin-right:40%;
	background-color: #ff900c;
	color: #fff;
	font-size: 13px;
	padding: 10px;
	border-radius:5px;
	cursor: pointer;
	
}
</style>


<script type="text/javascript">

</script>		<div id="footer">
	
</div>

<style>
.feedback-service {
	right: 10px;
	bottom: 45%;
	position: fixed;
	z-index: 1000;
}
.feedback { margin-bottom : 2px;}
</style>

	
		
		<script type="text/javascript">
		
		jQuery(function($) {
			var $navs = $("div.left-menu").find("a");
			$navs.each(function() {
				if ($(this).attr("data-menu") == 'activity-wall') {
					// 激活菜单显示
					$(this).parent().addClass("active");
				}
			});

			window.show_msg = function (msg, type) {
				$('#page_tips').remove();
				var	$msg = $('<div class="page_tips error" id="page_tips"><div class="inner"></div></div>');
				switch (type) {
					case 'error' :
						$msg.attr('class', 'page_tips error');
						break;
					case 'success' :
						$msg.attr('class', 'page_tips success');
						break;
					default :
				}
				if ($.trim(msg) !== '') {
					$msg.find('.inner').text(msg);
					$msg.hide().appendTo('body').fadeIn('fast', function() {
						setTimeout(function() {
							$msg.fadeOut('normal', function() {
								$(this).remove();
							});
						}, 2000);
					});
				}
			}
		});
		</script>
	
</body></html>