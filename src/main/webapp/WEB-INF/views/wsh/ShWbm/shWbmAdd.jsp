<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
<title>报名功能</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<script type="text/javascript">
	(function(w){
		w._config = {
			SYS_PATH: 'http://weixiao.qq.com'
		};

		w.get_config = function(name, default_value) {
			default_value = default_value ? default_value : null;
			return _config[name] ? _config[name] : default_value;
		};

		w.add_config = function(name, value) {
			_config[name] = value;
		}
	})(window);
</script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/jquery-ui-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/core.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/home.css">

	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/activity.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/uploadify.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-tagsinput.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/datePicker/skin/WdatePicker.css">

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery-ui-1.8.22.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/filter.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/global.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/datePicker/WdatePicker.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="row home-container">
				<div class="span12">
					<div class="right-content">
						<script type="text/javascript" src="<%=path%>/resources/js/wbm/timepicker.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap-tagsinput.min.js"></script>


<div id="add_apply_modal">
	<div class="modal-header">
		<h3 id="modal_label">添加报名</h3>
	</div>
	 <p class="help-block"></p>
  <form id="apply_form" class="form-horizontal" method="post" action="" novalidate="novalidate">
    <fieldset>
    <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01"><span class="required">*</span>报名标题：</label>
          <div class="controls">
            <input type="text" name="activity_name" placeholder="" class="input-xlarge" value="">
            <p class="help-block"></p>
          </div>
     </div>
        
        
      <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">报名介绍：</label>
          <div class="controls">
            <div class="textarea">
                  <textarea type="" class="" name="description" cols="10" rows="4" style="width: 280px"> </textarea>
            </div>
          </div>
      </div>
      
      <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01"><span class="required"></span>报名成功提示：</label>
          <div class="controls" style="position: relative;">
            <input type="text" name="success_tip" placeholder="" class="input-xlarge" value="">
            </p>
            <div class="apply_popover suc_demo_popover hide">
				<div class="apply_arrow"></div>
				<img src="<%=path%>/resources/js/wbm/suc_demo_popover.jpg" alt="" width="200">
			</div>
          </div>
     </div>
      
		<div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01"><span class="required">*</span>报名截止时间：</label>
          <div class="controls end-at">
          	<input id="date11" type="text" name="end_date" onClick="WdatePicker()"/>
<!--             <input id="apply_end_at" name="end_date" type="text" placeholder="" value=""> -->
            <div class="hour-minute">
            <select class="target-hour" name="end_hour" style="width: 55px">
												<option>00</option>
												<option>01</option>
												<option>02</option>
												<option>03</option>
												<option>04</option>
												<option>05</option>
												<option>06</option>
												<option>07</option>
												<option>08</option>
												<option>09</option>
												<option>10</option>
												<option>11</option>
												<option>12</option>
												<option>13</option>
												<option>14</option>
												<option>15</option>
												<option>16</option>
												<option>17</option>
												<option>18</option>
												<option>19</option>
												<option>20</option>
												<option>21</option>
												<option>22</option>
												<option>23</option>
									</select>&nbsp;<span>时</span>
			<select class="target-minute" name="end_minute" style="width: 55px">
												<option>00</option>
												<option>01</option>
												<option>02</option>
												<option>03</option>
												<option>04</option>
												<option>05</option>
												<option>06</option>
												<option>07</option>
												<option>08</option>
												<option>09</option>
												<option>10</option>
												<option>11</option>
												<option>12</option>
												<option>13</option>
												<option>14</option>
												<option>15</option>
												<option>16</option>
												<option>17</option>
												<option>18</option>
												<option>19</option>
												<option>20</option>
												<option>21</option>
												<option>22</option>
												<option>23</option>
												<option>24</option>
												<option>25</option>
												<option>26</option>
												<option>27</option>
												<option>28</option>
												<option>29</option>
												<option>30</option>
												<option>31</option>
												<option>32</option>
												<option>33</option>
												<option>34</option>
												<option>35</option>
												<option>36</option>
												<option>37</option>
												<option>38</option>
												<option>39</option>
												<option>40</option>
												<option>41</option>
												<option>42</option>
												<option>43</option>
												<option>44</option>
												<option>45</option>
												<option>46</option>
												<option>47</option>
												<option>48</option>
												<option>49</option>
												<option>50</option>
												<option>51</option>
												<option>52</option>
												<option>53</option>
												<option>54</option>
												<option>55</option>
												<option>56</option>
												<option>57</option>
												<option>58</option>
												<option>59</option>
									</select>&nbsp;<span>分</span>
			</div>
            <p class="help-block"></p>
          </div>
      </div>
      
      <div class="control-group">

<!--           Text input -->
<!--           <label class="control-label" for="input01">主办方LOGO：</label> -->
<!--           <div class="controls upload"> -->
<!--            	 <label class="upload-tip"><span class="tips">（图片不能小于：200像素  * 200像素）</span></label> -->
<!--           	 <div id="logo_upload" class="uploadify" style="height: 28px; width: 48px;"> -->
<!--           	 	<object id="SWFUpload_0" type="application/x-shockwave-flash" data="http://weixiao.qq.com/js/uploadify/uploadify.swf?preventswfcaching=1435116220331" width="48" height="28" class="swfupload" style="position: absolute; z-index: 1;"> -->
<!--           	 		<param name="wmode" value="transparent"> -->
<!--           	 		<param name="movie" value="/js/uploadify/uploadify.swf?preventswfcaching=1435116220331"> -->
<!--           	 		<param name="quality" value="high"> -->
<!--           	 		<param name="menu" value="false"> -->
<!--           	 		<param name="allowScriptAccess" value="always"> -->
<!--           	 		<param name="flashvars" value="movieName=SWFUpload_0&amp;uploadURL=%2Fupload%2Fupload_img&amp;useQueryString=false&amp;requeueOnError=false&amp;httpSuccess=&amp;assumeSuccessTimeout=30&amp;params=width%3D200%26amp%3Bheight%3D200%26amp%3Btimestamp%3D%26amp%3Btoken%3Dc98226ab72302c9c5ab98306274de032&amp;filePostName=Filedata&amp;fileTypes=*.*&amp;fileTypesDescription=All%20Files&amp;fileSizeLimit=0&amp;fileUploadLimit=0&amp;fileQueueLimit=999&amp;debugEnabled=false&amp;buttonImageURL=%2Fhome%2F14284%2Factivity%2F&amp;buttonWidth=48&amp;buttonHeight=28&amp;buttonText=&amp;buttonTextTopPadding=0&amp;buttonTextLeftPadding=0&amp;buttonTextStyle=color%3A%20%23000000%3B%20font-size%3A%2016pt%3B&amp;buttonAction=-100&amp;buttonDisabled=false&amp;buttonCursor=-2"></object><div id="logo_upload-button" class="btn" style=""> -->
<!--           	 			<span class="uploadify-button-text">上传</span> -->
<!--           	 			</div></div> -->
<!--           	 			<div id="logo_upload-queue" class="uploadify-queue"></div> -->
<!--           	 <input id="logo" type="hidden" name="logo" value=""> -->
<!--             <p class="logo_preview hide"> -->
<!-- 				<img id="logo_image" src="" style="width:90px"> -->
<!-- 				<a id="js_removeLogo" href="javascript:void(0);" onclick="return false;">删除</a> -->
<!-- 			</p>             -->
<!--             <p class="help-block"></p> -->
<!--           </div> -->
     </div>

<!--     <div class="control-group"> -->
<!--         <label class="control-label">卡券门票 :<br></label> -->
<!--            <span class="switch "> -->
<!--                 <span class="switch_st"></span> -->
<!--             </span><span id="card_title" class="hide"></span><a id="change_card" class="hide" href="http://weixiao.qq.com/home/14284/activity/add_apply#" style="margin-left: 10px;">修改</a> -->
<!--           <input type="hidden" id="enable_card" name="enable_card" value=""> -->
<!--           <input type="hidden" id="choose_card" name="card" value=""> -->
<!--      </div> -->
    <div class="control-group">
          <label class="control-label"></label>

          <!-- Button -->
          <div class="controls">
            <input type="button" id="save_apply" class="btn btn-success" value="保存">
             <input id="cancel_button" type="button" class="btn btn-default" value="取消">
          </div>
     </div>
    </fieldset>
  </form>
	
</div>

<div class="modal  card-list-modal hide" id="card_modal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-labelledby="modal_label" aria-hidden="true">x</button>
		<h3>选择卡券</h3>
	</div>
	<div class="modal-body">
		<div class="controls-card ">
          <p class="card_tip ">您还没有可用的卡券门票,请到<a href="http://weixiao.qq.com/home/14284/WeixinCard/index">卡券管理</a>中添加</p>
    	 <table id="card_list" class="card-table table-hover hide">
			<thead>
				<tr>
					<th>卡券名称</th>
					<th>有效期</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
						</tbody>
		</table>
          </div>
           <div class="modal-button-group hide">
            <input type="button" id="save_card" class="btn btn-success" value="确定">
             <input id="card_cancel_button" type="button" class="btn btn-default" value="取消">
          </div>
	</div>
</div>
<script type="text/javascript">
jQuery(function($){
	var is_edit = 0;
	
	$(".switch").click(function() {
			$('#card_title').toggleClass("hide");
		if($(".switch").hasClass("sw_open")) {
			$("#enable_card").val(0);
			$(".switch").removeClass("sw_open");
		}else {
			$('#card_modal').modal('show');
			$(".switch").addClass("sw_open");
			$("#enable_card").val(1);
		}
	});
	
	$("#cancel_button").click(function(){
		location.href = "<%=path%>/wsh/ShWbm/toWbmList";
	});
	$(".close").click(function(){
		if($("input[name='card_radio']:checked").val() == null) {
			$(".switch").removeClass("sw_open");
			$("#enable_card").val(0);
		}
		});
	$("#apply_end_at").datepicker({
		dateFormat: 'yy-mm-dd',
		minDate: new Date(),
		onClose: function(selectedDate){
			//$('#logo_upload').focus();
		}
	});
	$('#change_card').click(function(){
		$('#card_modal').modal('show');
	});
	
	$('#save_card').click(function(){
		if($("input[name='card_radio']:checked").val() == null) {
			alert("请选择卡券");
			return;
		}
		$('#choose_card').val($("input[name='card_radio']:checked").val());
		$(".switch").addClass("sw_open");
		$("#enable_card").val(1);
		$('#card_modal').modal('hide');
		$("#card_title").text($("input[name='card_radio']:checked").attr("data-title"));
		$("#card_title").removeClass("hide");
		$('#change_card').removeClass("hide");
	});
	
	$('#card_cancel_button').click(function(){
		$('#card_modal').modal('hide');
		 if( $("input[name='card_radio']:checked").val() == null ) {
		$('#card_title').toggleClass("hide");
			$(".switch").removeClass("sw_open");
			$("#enable_card").val(0);
		}
	});
	
	$('#logo_upload').uploadify({
		'width' : 48,
		'height' : 28,
		'buttonText' : '上传',
		'buttonClass' : '',
		'multi' : false,
		'formData' : {
			'width' : 200,
			'height' : 200,
			'timestamp' : '',
			'token' : 'c98226ab72302c9c5ab98306274de032'
		},
		'swf' : '/js/uploadify/uploadify.swf',
		'uploader' : '/upload/upload_img',
		'onInit' : function (instance) {
			instance.button.removeClass('uploadify-button').addClass('btn').attr('style', '');
		},
		'onUploadSuccess' : function (file, data, response) {
			data = jQuery.parseJSON(data);
			if(data.result[0].code == 0){
				$("#logo").val(data.result[0].img_url);
				$("#logo_image").attr("src",data.result[0].img_url);
				$(".logo_preview").show();
			}else{
				show_msg(data.result[0].message);
			}
		}
	});
	
	$("#save_apply").click(function(){
		if($("#apply_form").valid()){
			var url = '<%=path%>/wsh/ShWbm/save';
			$.ajax({
				url: url,
				type : 'post',
				dataType : 'json',
				data: $('#apply_form').serialize(),
				success: function(data){
					if(data != null || data != ''){
							location.href ="<%=path%>/wsh/ShWbm/toWbmBdAdd?id=" + data;
					}else{
						alert("提交失败");
					}
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
		}
	});

	$("#js_removeLogo").click(function(){
		$(".logo_preview").hide();
		$("#logo_upload").show();
		$("#logo").val("");
	});

	$("#apply_form").validate({
		ignore: [],
		onsubmit:false,
		rules: {
			activity_name:{
				required: true,
				minlength:1,
				maxlength:30
			},
			end_date :{
				required: true
			}
		},
		messages: {
			activity_name:{
				required:"请填写报名标题",
				maxlength:"不能超过30个字"
			},
			end_date :{
				required: "请填写报名截止时间"
			}
		}
	});

	$('#show_suc_demo').hover(function(e) {
		$('.suc_demo_popover').stop().fadeToggle();
	});

	

	$("#apply_end_at").change(function(){
		if($.trim($("#apply_end_at").val()) == ""){
			$("label[for=apply_end_at]").removeClass('hide');
			$("label[for=apply_end_at]").css('display','block');
		}else{
			$("label[for=apply_end_at]").addClass('hide');
			$("label[for=apply_end_at]").css('display','none');
		}
	});
});
</script>					</div>
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
jQuery(function($) {
	var is_show = 0;
	if(is_show){
		$('.version-modal').modal({
			backdrop: 'static',
			keyboard:false});
		$('.version-modal').modal('show');
	}
	$('#sure').click(function(){
		$.ajax({
			type : 'post',
			data:{"publish_date":$('.version-publish-date').attr("data-time")},
			dataType : 'json',
			url : "http://weixiao.qq.com/home/14284/index/set_version",
			success : function(data){
				$('.version-modal').modal('hide');
			},
		});
	});
	
});
</script>		<div id="footer">
</div>

		<script type="text/javascript">
		
		jQuery(function($) {
			var $navs = $("div.left-menu").find("a");
			$navs.each(function() {
				if ($(this).attr("data-menu") == 'acitvity-apply') {
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
	
<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div></body></html>