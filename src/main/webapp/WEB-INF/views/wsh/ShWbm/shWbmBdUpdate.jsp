<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
<title>报名功能-微校</title>
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

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery-ui-1.8.22.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/filter.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/global.js"></script>

</head>
	
	<body>
		<div class="container">
			<div class="row home-container">
				<input type="hidden" name="wbmid" id="wbmid" value="${id }"/>
				<div class="span12">
					<div class="right-content">
						<script type="text/javascript" src="<%=path%>/resources/js/wbm/timepicker.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap-tagsinput.min.js"></script>

<h3>编辑报名</h3>
<div>
	<div class="apply-name-wrap">
		<div class="apply-logo-wrap">
			<img src="<%=path%>/resources/js/wbm/${map.zbflogo }" alt="" width="60">
		</div>
		<div class="apply-detail-wrap">
			<h4 class="apply-name" id="apply-name">${map.bmbt }</h4>
			<div>${map.bmjs }</div>
			<div>截止时间：${time }</div>
		</div>
		<a href="javascript:void(0);" class="edit-config" id="edit_apply_button">编辑</a>
	</div>
	<form id="apply-form" action="" method="post">
		<div class="apply-main">
			<div class="apply-preview-area">
				<h4 class="apply-form-title">表单编辑</h4>
				<div class="entry">
				</div>
				<div class="add-field">
					<div class="toggle"><span class="plus-large">+</span>添加新字段</div>
					<div class="fields hide">
						<div class="new-field" data-field-type="text">单行文字</div>
						<div class="new-field" data-field-type="textarea">多行文字</div>
						<div class="new-field" data-field-type="checkbox">多项选择</div>
						<div class="new-field" data-field-type="select">下拉框</div>
					</div>
				</div>
			</div>
			<div class="apply-edit-area">
				<div class="apply-editor hide" id="apply-editor">
					<i class="arrow arrow_out" style="margin-top: 0px;"></i>
					<i class="arrow arrow_in" style="margin-top: 0px;"></i>
					<div class="inner">
						<label>未命名</label>
						<div class="input-wrp">
							<input type="text" placeholder="">
						</div>
						<label></label>
						<div class="input-wrp">
							<input type="text" placeholder="">
						</div>
						<label class="checkbox">
							<input type="checkbox">必填
						</label>
					</div>
				</div>
			</div>
			<div class="apply-tool-area">
			 <div class="apply-back-btn"><a class="back-btn" href="#">返回列表</a></div>
				<div class="tool-bar save-tool-bar">
					<input id="submit_config" type="button" class="btn btn-success" value="保存表单">
				</div>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
jQuery(function($){
	$("#edit_apply_button").click(function(e){
		location.href = "<%=path%>/wsh/ShWbm/toWbmUpdate?id="+($("#wbmid").val());
	});

	$(".apply-back-btn").bind('mouseover',function(){
		$('.back-btn').css('color','#34bd6b');
		//$(this).css('background','url(/img/home/back-icon-highlight.png) no-repeat 0 center');
		$(this).css("cursor","pointer");
		});
	$(".apply-back-btn").bind('mouseout',function(){
		$('.back-btn').css('color','#979797');
		//$(this).css('background','url(/img/home/back-icon.png) no-repeat 0 center');
		});
	$(".apply-back-btn").click(function(){
		location.href = "<%=path%>/wsh/ShWbm/toWbmList";
		});

	var templates = {
			checkboxItem :  '<div class="checkbox-add">' +
			'<input type="text" id="default_value" class="js-checkbox-name" placeholder="">&nbsp;<span class="plus-middle js-add-option">+</span><span class="minus-middle js-del-option">-</span>' +
			'</div>'
	};

	applyForm = {
		typeNum :{text:1,textarea:2,select:3,checkbox:4},
		editingIndex : 0,
		data : [],

		templates : {
			field : {
				text : '<a href="javascript:void(0);" class="field-del">删除</a>' +
						'<span class="required-indicate hide">*</span>' +
						'<label class="js-title">未命名</label>' +
						'<input class="js-default" type="text" placeholder="" disabled="disabled">',
				textarea : '<a href="javascript:void(0);" class="field-del">删除</a>' +
						'<span class="required-indicate hide">*</span>' +
						'<label class="js-title">未命名</label>' +
						'<textarea class="js-default" rows="3" placeholder="" disabled="disabled"></textarea>',
				select : '<a href="javascript:void(0);" class="field-del">删除</a>' +
						'<span class="required-indicate hide">*</span>' +
						'<label class="js-title">未命名</label>' +
						'<select class="js-default"></select>',
				checkbox:   '<a href="javascript:void(0);" class="field-del">删除</a>' +
							'<span class="required-indicate hide">*</span>' +
							'<label class="js-title">未命名</label>' +
							'<div class="js-default">' + 
							'<div><input  type="checkbox" />&nbsp;<span>未命名</span><br/></div>'+
							'<div><input  type="checkbox" />&nbsp;<span>未命名</span><br/></div>'+
							'</div>' 
			},
			editor : {
				text : '<h4 class="field-title">添加单行文字字段</h4>' +
						'<label>标题</label>' +
						'<div class="input-wrp">' +
							'<input type="text" id="field_title" placeholder="未命名">' +
						'</div>' +
						'<label>默认值</label>' +
						'<div class="input-wrp">' +
							'<input type="text" id="default_value" placeholder="">' +
						'</div>' +
						'<label class="checkbox">' +
							'<input id="required" type="checkbox">必填' +
						'</label>',
				textarea : '<h4 class="field-title">添加多行文字字段</h4>' +
						'<label>标题</label>' +
						'<div class="input-wrp">' +
							'<input type="text" id="field_title" placeholder="未命名">' +
						'</div>' +
						'<label>默认值</label>' +
						'<div class="input-wrp">' +
							'<input type="text" id="default_value" placeholder="">' +
						'</div>' +
						'<label class="checkbox">' +
							'<input id="required" type="checkbox">必填' +
						'</label>',
				select : '<h4 class="field-title">添加下拉框字段</h4>' +
						'<label>标题</label>' +
						'<div class="input-wrp" style="margin-bottom: 9px;" >' +
							'<input class="checkbox-title" type="text" id="field_title" placeholder="未命名">' +
							'<label class="checkbox" style="display: inline; margin-left: 8px; ">' +
								'<input id="required" type="checkbox" style="width: 20px;vertical-align: middle; float: none;"><span style="vertical-align: middle;">必填</span>' +
							'</label>'+
						'</div>' +
						'<label>选项</label>' +
						'<div id="check_option">' + 
						templates.checkboxItem +
						templates.checkboxItem +
						'</div>',
						
				checkbox:'<h4 class="field-title">添加多项选择</h4>' +
						'<label>标题</label>' +
						'<div class="input-wrp" style="margin-bottom: 9px;" >' +
							'<input class="checkbox-title" type="text" id="field_title" placeholder="未命名">' +
							'<label class="checkbox" style="display: inline; margin-left: 8px; ">' +
 							'<input id="required" type="checkbox" style="width: 20px;vertical-align: middle; float: none;"><span style="vertical-align: middle;">必填</span>' +
							'</label>'+
						'</div>' +
						'<div style="margin-bottom: 15px;"><span style="vertical-align: middle;">选项上限：</span>&nbsp; <select name="max_select" id="max_select_num" style="padding:2px; height:26px; width:45px;vertical-align: middle; margin-bottom: 0px;">'+
						'<option>1</option><option selected="true">2</option>'+
						'</select></div>'+
						'<label>选项</label>' +
						'<div id="check_option">' + 
						templates.checkboxItem +
						templates.checkboxItem +
						'</div>'
			}
		},
		initEditor : function (type) {
			var _this = this;
			var $inner = $('.apply-edit-area .inner').empty();
			var default_title = '未命名';
			switch (type) {
				case 'text' :
					$(this.templates.editor.text).appendTo($inner);
					$('.inner #field_title').keyup(function(e) {
						$('.field.editing .js-title').text(this.value);
						if($.trim(this.value) == '') $('.field.editing .js-title').text(default_title);
						//jianhao
						applyForm.data[applyForm.editingIndex].field_name = this.value;
					});
					$('.inner #default_value').keyup(function(e) {
						$('.field.editing .js-default').val(this.value);
						//jianhao
						applyForm.data[applyForm.editingIndex].field_value = this.value;
					});
					$('.inner #required').change(function(e) {
						//jianhao
						var isChecked = $(this).is(':checked');
						applyForm.data[applyForm.editingIndex].field_nullable = isChecked ? 0 : 1;
						isChecked ? $('.field.editing .required-indicate').removeClass('hide') : $('.field.editing .required-indicate').addClass('hide');
					});
					break;
				case 'textarea' :
					$(this.templates.editor.textarea).appendTo($inner);
					$('.inner #field_title').keyup(function(e) {
						$('.field.editing .js-title').text(this.value);
						if($.trim(this.value) == '') $('.field.editing .js-title').text(default_title);
						//jianhao
						applyForm.data[applyForm.editingIndex].field_name = this.value;
					});
					$('.inner #default_value').keyup(function(e) {
						$('.field.editing .js-default').val(this.value);
						//jianhao
						applyForm.data[applyForm.editingIndex].field_value = this.value;
					});
					$('.inner #required').change(function(e) {
						var isChecked = $(this).is(':checked');
						applyForm.data[applyForm.editingIndex].field_nullable = isChecked ? 0 : 1;
						isChecked ? $('.field.editing .required-indicate').removeClass('hide') : $('.field.editing .required-indicate').addClass('hide');
					});
					break;
				case 'select' :
					$(this.templates.editor.select).appendTo($inner);
					var update_option_data = function (){
						var items = [];
						$('.inner .js-checkbox-name').each(function(index, el) {
							items.push($(this).val());
						});
// 						console.log(items.join("|"));
						applyForm.data[applyForm.editingIndex].field_value = items.join("|");

						var options = applyForm.data[applyForm.editingIndex].field_value.split('|');
						$('.field.editing .js-default').empty();
						for (var i = 0; i < options.length; i++) {
							if($.trim(options[i]) == '') {
								options[i] = "未命名";
							}
							$('.field.editing .js-default').append('<option value='+ i +'>'+ options[i] +'</option>');
						}
					}

					//添加选项
					$(".js-add-option").die('click').live('click', function(event) {
						event.stopPropagation();
						var pos = $(".js-add-option").index(this);
// 						console.log('add pos '  + pos);
						$(this).parent().after(templates.checkboxItem);
						if($(".js-add-option").length > 2){
							$(".js-del-option").show();
						}
						//预览区添加
						update_option_data();
						return false;
					});
					//删除选项
					$(".js-del-option").die('click').live('click', function(event) {
						event.stopPropagation();
						var pos = $(".js-del-option").index(this);
						$(this).parent().remove();
						if($(".js-add-option").length == 2){
							$(".js-del-option").hide();
						}
						//预览区删除
						$('.field.editing .js-default input:eq('+ pos + ')').parent().remove();
						update_option_data();
					});

					$('.inner #field_title').keyup(function(e) {
						$('.field.editing .js-title').text(this.value);
						if($.trim(this.value) == '') $('.field.editing .js-title').text(default_title);
						//jianhao
						applyForm.data[applyForm.editingIndex].field_name = this.value;
					});

					$('.inner .js-checkbox-name').die().live('blur', function(event) {
						if($.trim($(this).val()).length <= 30 ){
							update_option_data();
						}else{
							show_msg('选项长度不能超过30个字符');
							$(this).focus();
						}
					});

					$('.inner #required').change(function(e) {
						var isChecked = $(this).is(':checked');
						applyForm.data[applyForm.editingIndex].field_nullable = isChecked ? 0 : 1;
						isChecked ? $('.field.editing .required-indicate').removeClass('hide') : $('.field.editing .required-indicate').addClass('hide');
					});

					var options = applyForm.data[applyForm.editingIndex].field_value.split('|');
					if(options.length > 1){
						$(".checkbox-add").remove();
						for (var j = 0; j < options.length; j++) {
							var $checkboxItem = $(templates.checkboxItem);
							$checkboxItem.find('input').val(options[j]);
							$checkboxItem.appendTo('#check_option');
						}
					}
					if(options.length == 2){
						$(".js-del-option").hide();
					}
					break;
					
				case 'checkbox' :
					$(this.templates.editor.checkbox).appendTo($inner);
					$(".js-del-option").hide();
					//新建多选项时候，默认两个
					if(applyForm.data[applyForm.editingIndex].max_select == 0){
						applyForm.data[applyForm.editingIndex].max_select = 2;
					}
					var update_option_data = function (){
						var items = [];
						$('.inner .js-checkbox-name').each(function(index, el) {
							items.push($(this).val());
						});
// 						console.log(items.join("|"));
						applyForm.data[applyForm.editingIndex].field_value = items.join("|");
					}

					var fill_max_select_option = function(total, max_num){
// 						console.log('max num ' + max_num);
						$("#max_select_num").empty();
						for(var i=0; i< total ; i++){
							$('<option value="'+ (i+1) +'" >'+ (i+1) +'</option>').appendTo('#max_select_num');
						}
						if(max_num == 0){
							max_num = total;
						}
						var pos = max_num - 1;
						applyForm.data[applyForm.editingIndex].max_select = max_num;
						$("#max_select_num option:eq(" + pos +")").attr('selected',true);
					}

					$("#max_select_num").change(function(event) {
// 						console.log("max selecet change");
						applyForm.data[applyForm.editingIndex].max_select = $(this).val();
// 						console.log("max selecet change " + applyForm.data[applyForm.editingIndex].max_select);
					});

					//添加选项
					$(".js-add-option").die('click').live('click', function(event) {
						event.stopPropagation();
						var pos = $(".js-add-option").index(this);
// 						console.log('add pos '  + pos);
						$(this).parent().after(templates.checkboxItem);
						if($(".js-add-option").length > 2){
							$(".js-del-option").show();
						}
						//预览区添加
						$('.field.editing .js-default input:eq('+ pos + ')').parent().after('<div><input type="checkbox" >&nbsp;<span>未命名</span><br/><div>');
						update_option_data();
						fill_max_select_option($(".js-add-option").length, 0);
						return false;
					});
					//删除选项
					$(".js-del-option").die('click').live('click', function(event) {
						event.stopPropagation();
						var pos = $(".js-del-option").index(this);
						$(this).parent().remove();
						if($(".js-add-option").length == 2){
							$(".js-del-option").hide();
						}
						//预览区删除
						$('.field.editing .js-default input:eq('+ pos + ')').parent().remove();
						update_option_data();
						fill_max_select_option($(".js-add-option").length, 0);
					});

					$('.inner #field_title').keyup(function(e) {
						$('.field.editing .js-title').text(this.value);
						if($.trim(this.value) == '') $('.field.editing .js-title').text(default_title);
						applyForm.data[applyForm.editingIndex].field_name = this.value;
					});
					$('.inner .js-checkbox-name').die().live('blur', function(event) {
						var pos = $('.inner .js-checkbox-name').index(this);
						// console.log(' len ' + $.trim(this.value).length);
						if($.trim(this.value).length <= 30){
							$('.field.editing .js-default span:eq('+ pos + ')').html(this.value);
							update_option_data();
						}else{
							show_msg('选项长度不能超过30个字符');
							$(this).focus();
						}

					});

					$('.inner #required').change(function(e) {
						var isChecked = $(this).is(':checked');
						applyForm.data[applyForm.editingIndex].field_nullable = isChecked ? 0 : 1;
						isChecked ? $('.field.editing .required-indicate').removeClass('hide') : $('.field.editing .required-indicate').addClass('hide');
					});
					
					var options = applyForm.data[applyForm.editingIndex].field_value.split('|');
					if(options.length > 1){
						$(".checkbox-add").remove();
						fill_max_select_option(options.length, applyForm.data[applyForm.editingIndex].max_select);
						for (var j = 0; j < options.length; j++) {
							var $checkboxItem = $(templates.checkboxItem);
							$checkboxItem.find('input').val(options[j]);
							$checkboxItem.appendTo('#check_option');
						}
					}
					if(options.length == 2){
						$(".js-del-option").hide();
					}
					break;
				default:
			}
			$('.inner #field_title').val(applyForm.data[applyForm.editingIndex].field_name);
			if(type != 'checkbox' && type !='select'){
				$('.inner #default_value').val(applyForm.data[applyForm.editingIndex].field_value);
			}
			$('.inner #required').prop('checked', applyForm.data[applyForm.editingIndex].field_nullable == 0 ? true : false);
			$('#apply-editor').removeClass('hide');
		},
		addField : function (type) {
			var $entry = $('.apply-preview-area .entry');
			//jianhao
			applyForm.data.push({field_config_id : 0, field_name:'', field_type : applyForm.typeNum[type], field_nullable:1, field_value: '', max_select : 0});
			switch (type) {
				case 'text' :
					$('<div class="field" data-field-type="text"></div>').wrapInner(this.templates.field.text).appendTo($entry).click();
					break;
				case 'textarea' :
					$('<div class="field" data-field-type="textarea"></div>').wrapInner(this.templates.field.textarea).appendTo($entry).click();
					break;
				case 'select' :
					$('<div class="field" data-field-type="select"></div>').wrapInner(this.templates.field.select).appendTo($entry).click();
					break;
				case 'checkbox':
					$('<div class="field" data-field-type="checkbox"></div>').wrapInner(this.templates.field.checkbox).appendTo($entry).click();
					break;
				default :
			}
		},
		moveEditor : function (top) {
			if(!top && $('.entry .field.editing').length == 0) return;
			var top = top || $('.entry .field.editing').position().top;
			$('#apply-editor').animate({
				'marginTop': top
			}, 'fast', function() {
				/* stuff to do after animation is complete */
			});
		},
		bindEvent : function () {
			var _this = this;
			$('.apply-main .add-field').click(function(e) {
				$('.apply-main .toggle').toggleClass('hide');
				$('.apply-main .fields').toggleClass('hide');
			});

			$('.add-field .new-field').click(function(e) {
				var field_type = $(this).attr('data-field-type');
				applyForm.addField(field_type);
			});
			$('.entry .field').live('click', function(event) {
				_this.editingIndex = $('.entry .field').index(this);
				var field_type = $(this).attr('data-field-type');
				$(this).addClass('editing').siblings().removeClass('editing');
				_this.initEditor(field_type);
				var top = $(this).position().top;
				_this.moveEditor(top);
			});
			$('.field .field-del').live('click', function(event) {
				//jianhao
				var index = $('.field .field-del').index(this);
				applyForm.data.splice(index, 1);
				
				$(this).parent().remove();
				if($(this).parent().hasClass('editing')) {
					$('#apply-editor').addClass('hide');
				}
				_this.moveEditor();
				return false;
			});
		},
		init : function () {
			this.bindEvent();
		}
	}

	//初次进来渲染
	//var configs = [{name:1,type:'text',value:'ss'},{name:2,type:'text',value:'s'}];
	var configs = [];
	var type_num = {1:'text',2:'textarea', 3:'select', 4 : 'checkbox'};
	for(i=0;i<configs.length;i++){
		applyForm.addField(type_num[configs[i].type]);
		$(".entry .field:eq("+ i +") .js-title").html(configs[i].name);
		configs[i].nullable == 0 ? $(".entry .field:eq("+ i +") .required-indicate").removeClass('hide') : $(".entry .field:eq("+ i +") .required-indicate").addClass('hide');
		if(type_num[configs[i].type] == 'select'){
			var options = configs[i].value.split('|');
			for (var j = 0; j < options.length; j++) {
				if($.trim(options[j]) != '') {
					$(".entry .field:eq("+ i +") .js-default").append('<option value='+ j +'>'+ options[j] +'</option>');
				}
			}
		}else if(type_num[configs[i].type] == 'checkbox'){
			var options = configs[i].value.split('|');
			$(".entry .field:eq("+ i +") .js-default").empty();
			for (var j = 0; j < options.length; j++) {
				if($.trim(options[j]) != '') {
					$(".entry .field:eq("+ i +") .js-default").append('<div><input type="checkbox" >&nbsp;<span>'+ $.trim(options[j]) + '</span><br/><div>');
				}
			}
		}else{
			$(".entry .field:eq("+ i +") .js-default").val(configs[i].value);
		}
	}
	
	applyForm.data = [];
	for(i=0;i<configs.length;i++){
		applyForm.data.push({field_config_id:configs[i].id, field_name:configs[i].name, field_type : configs[i].type , field_value : configs[i].value, field_nullable : configs[i].nullable, max_select : configs[i].max_select});
	}
	
	applyForm.init();

	//保存表单
	$("#submit_config").click(function(){
		var data_post ={};
		if(applyForm.data.length == 0){
			show_msg("请填写字段");
			return false;
		}
		for(i=0; i<applyForm.data.length; i++){
			if($.trim(applyForm.data[i].field_name) == ""){
				show_msg("标题不能为空");
				$(".entry .field:eq("+ i +")").click();
				return false;
			}
			// if(applyForm.data[i].field_type == applyForm.typeNum.select && $.trim(applyForm.data[i].field_value) == ""){
			// 	show_msg("选项不能为空");
			// 	$(".entry .field:eq("+ i +")").click();
			// 	return false;
			// }
			if(applyForm.data[i].field_type == applyForm.typeNum.checkbox || applyForm.data[i].field_type == applyForm.typeNum.select){
				var options = applyForm.data[i].field_value.split("|");
				for (var j = 0; j < options.length; j++) {
					if($.trim(options[j]) == ""){
						show_msg("选项不能为空");
						$(".entry .field:eq("+ i +")").click();
						return false;
					}
				}
			}
			data_post['field_config_id_'+ i] = applyForm.data[i].field_config_id;
			data_post['field_type_'+ i] = applyForm.data[i].field_type;
			data_post['field_name_'+ i] = applyForm.data[i].field_name;
			data_post['field_value_'+ i] = applyForm.data[i].field_value;
			data_post['field_nullable_'+ i] = applyForm.data[i].field_nullable;
			data_post['field_max_select_'+ i] = applyForm.data[i].max_select;
		}
		data_post['field_count'] = applyForm.data.length;
		data_post['activity_id'] = $("#wbmid").val();
		$.ajax({
			url: "<%=path%>/wsh/ShWbm/updateBd",
			type: 'post',
			dataType: 'json',
			data: data_post,
			success: function(data){
				if(data =="1"){
					show_msg("保存成功","success");
					location.href="<%=path%>/wsh/ShWbm/toWbmList";
				}
			},
			error: function(){
				alert("网络错误");
			}
		});
		
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

<div class="modal version-modal hide">
			
</div>
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
			url : "",
			success : function(data){
				$('.version-modal').modal('hide');
			},
		});
	});
	
});
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
	
</body></html>