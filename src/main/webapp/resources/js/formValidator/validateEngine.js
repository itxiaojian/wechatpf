/*
 * form validate 
 * version 1.0
 * Requires jQuery v1.3.2 or later
 * Author: liuxiantao 20141227
 * 
 * notBlank
 * date
 * email
 * phone
 * integer
 * length
 * same
 * after
 * regex 
 */

var Map = function() {
	this.elements = {};
}

Map.prototype.put = function(key, value) {
	this.elements[key] = value;
}

Map.prototype.get = function(key) {
	return this.elements[key];
}

Map.prototype.getClass = function() {
	return "javascript.Map";
}

Map.prototype.keys = function() {
	var k = new Array();
	for(var key in this.elements)
		k.push(key);
	return k;
}


$.fn.validate = function(config,errorsize)
{
	var map = $(this).form2Map();
	var paramMap = map.keys();
	
	//给不能为空元素加红*
	var $error = $('<font class="required" color="red">*</font>');
	for ( var name in config) {
		var f = false;
		for(var i = 0; i < paramMap.length;i++) {
			if (RegExp("^"+name+"$").test(paramMap[i])) {
				for ( var j = 0; j < config[name].length; j++) {
					var validateConfig = config[name][j];
					if (validateConfig.rule == notBlank) {
						if($(":input[name='"+paramMap[i]+"']").is(":hidden")) {
							$("[id='"+paramMap[i]+"']").after($error.clone());	
						} else {
							$(":input[name='"+paramMap[i]+"']").after($error.clone());	
						}
						
						f = true;
						break;
					}
				}
			}
			if (f)
				break;
			
		}
	}
	
	if (errorsize != 0) {
		validateForm(config);
	}
	$(this).submit(function(event){
		if(validateForm(config)) {
			return true;
		} else {
			event.stopPropagation();
			return false;
		}
	});
};

$.fn.form2Map = function()
{
    var map = new Map();
    var a = this.serializeArray();
    $.each(a, function() {
        /*if (map.get(this.name) != undefined) {
            map.get(this.name).push($.trim(this.value || ''));
        } else {
        	var values = new Array();
        	values.push($.trim(this.value || ''));*/
        	map.put(this.name, $.trim(this.value || ''));
        //}
    });
    return map;
};

function validateForm(config, formId) {
	var params;
	var data= '';
	if (formId)
	{
		params = $("#"+formId).form2Map();
		data = $("#"+formId).serialize();
	}
	else 
	{
		params = $("form").form2Map();
		data = $("form").serialize();
	} 
		
	
	var errors = validate(config,params,data);
	$(":input",$("#"+formId)).not(':button, :submit, :reset, :hidden').css("border","1px solid #A2B3BD");
	var $error = $('<span class="error" style="margin-left:15px;color:red;height: 18px;line-height: 18px;position: absolute;z-index:999;"></span>');
	for (var name in errors) {
		var $span = $error.clone().text(name);
			if ((typeof errors[name]) != "string") {
				for(var i = 0; i < errors[name].length; i++) {
					if($(":input[name='"+errors[name][i]+"']").is(":hidden")) {
						$("[id="+errors[name][i]+"]").css("border","1px solid red");
						$("[id="+errors[name][i]+"]").after($span);
						$("[id="+errors[name][i]+"]").focus(function(){
							$(this).css("border","1px solid #A2B3BD");
							$(this).parent().find(".error").remove();
						});
					} else {
						$(":input[name='"+errors[name][i]+"']").css("border","1px solid red");
						$(":input[name='"+errors[name][i]+"']").after($span);
						$(":input[name='"+errors[name][i]+"']").focus(function(){
							$(this).css("border","1px solid #A2B3BD");
							$(this).parent().find(".error").remove();
						});
					}
				}
			} else {
				if($(":input[name='"+errors[name]+"']").is(":hidden")) {
					if ($("#"+errors[name]).size() == 0 || $("#error-"+errors[name]).size() != 0) {
						continue;
					}
					
					$span.attr("id","error-"+errors[name]);
					$("#"+errors[name]).css("border","1px solid red");
					$("#"+errors[name]).after($span);
					$("#"+errors[name]).focus(function(){
						$(this).css("border","1px solid #A2B3BD");
						$(this).parent().find(".error").remove();
					});
				} else {
					if ($("#error-"+errors[name]).size() != 0) {
						continue;
					}
					$span.attr("id","error-"+errors[name]);
					$(":input[name='"+errors[name]+"']:first").css("border","1px solid red");
					$(":input[name='"+errors[name]+"']:first").after($span);
					$(":input[name='"+errors[name]+"']:first").focus(function(){
						$(this).css("border","1px solid #A2B3BD");
						$(this).parent().find(".error").remove();
					});
				}
			}
	}
	if ($('span.error').length == 0) {
		return true;
	}
	return false;
}



function isBlank(value) {
	if (value == undefined || value == null || value.replace(/(^\s*)|(\s*$)/g, '') == "")
		return true;
	return false;
} 
function notBlank(config, parameters, value) {
     return !isBlank(value);
} 


function regex(config, parameters, value) {
	if (isBlank(value)) return true;
	var a = RegExp(config);
	return !a.test(value);
}

function length(config, parameters, value) {
	var min = config.min||0;
    var max = config.max||100;
    
    return isBlank(value) || (value.length >= min) && (value.length <= max);
}

function select(config, parameters, value) {
	for(var i = 0; i < config.length; i++) {
		if (value == config[i]) {
			return true;
		}
	}
	return false;
}

function same(config, parameters, value) {
	var ref = config.ref;
    var refVal = parameters.get(ref);
    return refVal == value;
}

function after(config, parameters, value) {
	var ref = config.ref;
    
    var refVal = parameters.get(ref);

    if (isNumber(value)&&isNumber(refVal)) {
        return parseFloat(value) >  parseFloat(refVal);
    } else {
        return (new Date(value) - new Date(refVal)) > 0;
    }
    return false;
}

function phone(config, parameters, value) {
	 if (!isBlank(value) && !(/^0?[1][358][0-9]{9}$/).test(value))
       	return false;
    return true;
}

function date(config, parameters, value) {
	if (!isBlank(value) && !(/^(\d{4})(-|\/)(\d{2})(-|\/)(\d{2})(( )(\d{2})(:)(\d{2})(:)(\d{2}))?$/).test(value))
		return false;
	return true;
}

function email(config, parameters, value) {
	 if (!isBlank(value) && !(/^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/).test(value))
       	return false;
    return true;
}

function url(config, parameters, value) {
	 if (!isBlank(value) && !(/^(([\w]+:)?\/\/)?(([\d\w]|%[a-fA-f\d]{2,2})+(:([\d\w]|%[a-fA-f\d]{2,2})+)?@)?([\d\w][-\d\w]{0,253}[\d\w]\.)+[\w]{2,4}(:[\d]+)?(\/([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)*(\?(&?([-+_~.\d\w]|%[a-fA-f\d]{2,2})=?)*)?(#([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)?$/).test(value))
       	return false;
    return true;
}
function integer(config, parameters, value) {
	 if(!isBlank(value) && (isNaN(value) || value.match(/[\.-]/) || parseInt(value)>2147483648)){
		 return false;
	 }
    return true;
}
function number(config, parameters, value) {
	if (!isBlank(value) && !(/^((-)?([0-9]{1,3})?([,][0-9]{3}){0,4}([.][0-9]{0,4})?)$|^(-)?([0-9]{1,14})?([.][0-9]{1,4})$|^(-)?[0-9]{1,14}$/).test(value))
		return false;
	return true;
}

function validate(config, map,data) {
	var result = {};
	var paramMap = map.keys();
	for (var name in config) {
		var exist = false;
		for(var i = 0; i < paramMap.length;i++) {	
			if (RegExp("^"+name+"$").test(paramMap[i])) {
				exist = true;
				var val = map.get(paramMap[i]);
				for ( var j = 0; j < config[name].length; j++) {
					var validateConfig = config[name][j];
					
					if (typeof(validateConfig.rule) == "function"){
						if(!validateConfig.rule(validateConfig.params, map,val)) {
							putError(result, paramMap[i], validateConfig.msg);
							break;
						}
					} else if(typeof(validateConfig.rule) == "string"){
						if( typeof formValidatorServletPath == "undefined")
						{
							alert("formValidatorServletPath未设置");
							return;
						}
						
						var flag = true;
						data += '&rule_value='+val+'&rule='+validateConfig.rule+'&rule_name='+paramMap[i];
						$.ajax({ 
					       type: "post", 
					       url: formValidatorServletPath +"/formValidator", 
					       cache:false, 
					       async:false, 
					       data: data, 
					       dataType: 'json', 
					       success: function(data){ 
					    	   $.each(data.paramErrors, function(key, value) {  
					    		  flag = false;
				    		  	  putError(result, key,value);
		                        });  
					       } 
						});
						if(!flag) {
							break;
						}
					} else {
						alert("数据验证规则未设置");
					}
				}
				if(name == paramMap[i]) {
					break;
				}
			}
		}
		if(!exist){
			for ( var j = 0; j < config[name].length; j++) {
				var validateConfig = config[name][j];
				if ((""+validateConfig.rule).indexOf("notBlank(") != -1) {
					putError(result, name, validateConfig.msg);
					break;
				}
			}
		}
	}
	return result;
}

function putError(result, param, msg){
	var _paramnames = result[msg];
	if (_paramnames == null) {
		result[msg] = param;
	} else {
		if ((typeof _paramnames) != "object"){
			_paramnames = new Array();
			_paramnames.push(result[msg]);
			result[msg] = _paramnames;		
		}
		_paramnames.push(param);
	}
}