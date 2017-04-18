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
    <script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload.js"></script>
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
		$("#file1").show();
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
			if (startTime >= endTime) {
				alert("结束时间不能小于开始时间");
				return false;
			}
			$.ajax({
				url: "<%=path%>/wsh/ShWsq/check",
				type : 'post',
				dataType : 'json',
				data: {start:startTime,end:endTime},
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
//var i=0;
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

<h3>新建上墙</h3>
<div>
	<form novalidate="novalidate" id="wall-form" class="form-horizontal" method="post" action="<%=path%>/wsh/ShWsq/save">
		<fieldset>
			<input id="wall-id" value="0" type="hidden">
			
			<div class="control-group">
		        <label class="control-label" for="input01"><span class="required">*</span>活动名称：</label>
		        <div class="controls">
		            <input name="name" placeholder="" class="input-xlarge" type="text">
		            <p class="help-block"></p>
		        </div>
		    </div>
		    
	    	<div class="control-group">
	          	<label class="control-label" for="input01">LOGO：</label>
	          	<div class="controls upload">
		          	<input id="file1" name="file" multiple="false" type="file" accept="image/*" capture="camera" onchange="selectFile();">
		          	<input id="logo" name="logo" value="" type="hidden">
		            <p class="logo_preview hide">
						<img id="logo_image" src="" style="width:120px">
						<a id="js_removeLogo" href="javascript:void(0);" onclick="return false;">删除</a>
					</p>            
		            <p class="help-block"></p>
	        	</div>
	    	</div>

       		<!-- <div class="modal hide" id="upload_bg_modal">
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
		            <input id="start-at" type="text" name="startdate" onClick="WdatePicker()"/>
		            <div class="hour-minute">
			            <select id="starthour" class="target-hour" name="starthour" style="width: 55px">
																		<option selected="selected">00</option>
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
						<select id="startminute" class="target-minute" name="startminute" style="width: 55px">
																		<option selected="selected">00</option>
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
	          	<label class="control-label" for="input01"><span class="required">*</span>截止时间：</label>
	          	<div class="controls end-at">
		            <input id="end-at" type="text" name="enddate" onClick="WdatePicker()"/>
		            <div class="hour-minute">
			            <select id="endhour" class="target-hour" name="endhour" style="width: 55px">
																		<option selected="selected">00</option>
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
						<select id="endminute" class="target-minute" name="endminute" style="width: 55px">
																		<option selected="selected">00</option>
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