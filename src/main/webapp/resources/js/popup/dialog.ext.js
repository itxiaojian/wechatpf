/**
 * 
 * 297504559 刘贤涛 2013-6-6
 */

$(document).ready(function(){
	$("body",document).append('<div sytle="display:none;"><form id="postForm" action="" method="post"></form></div>');
});

var callbacks = new Array();
var params = {};
var documents = {};
var inputType = "radio"; 
var currentDoc = null;

jQuery.onView = function(url,callback,config) {
	config = config ||{};
	inputType = config.inputType || "radio";
	
	var iframeName = "InnerFrameName" + Dialog._dialogArray.length;
	Dialog.open({InnerFrameName:iframeName,URL:"about:blank",Title:"",Width:1200,Height:600,OnLoad:addCheckbox,ShowOkButton:false,ShowCancelButton:false,CancelEvent:closeView,ButtonAlign:"center"}); 
	callbacks[Dialog._dialogArray.length - 1] = callback;
	
	$("#postForm").empty();
	$("#postForm").attr("action",url);
	$("#postForm").attr("target",iframeName);
	for(p in config) {
		if (p != "inputType")
			$("#postForm").append('<input type="hidden" name="' + p + '" value="' + config[p] + '"/>');				
	}
	$("#postForm").submit();
}

function addCheckbox(doc){
	try{
		if (doc) {
    		currentDoc = doc;
    		documents["_DialogFrame_" + (Dialog._dialogArray.length - 1)] = doc;
		} else {
    		currentDoc = document.getElementById("_DialogFrame_" + (Dialog._dialogArray.length - 1)).contentWindow.document;
		}
		if ($(".ulistheader", currentDoc).hasClass("check")){
			$(".ulistheader", currentDoc).prepend('<li class="check"></li>');
			$(".ulist", currentDoc).each(function() {
				var checked = "";
				if (getPagerChecked($(".pager",currentDoc).attr("val"))[$(this).attr("val")]) {
					checked = ' checked="checked" ';
				}
				$(this).prepend('<li class="check"><input type="'+inputType+'" name="c" value="" '+checked+'/></li>');
			});
			
			$(".ulist input", currentDoc).click(function(event){
				event.stopPropagation();
			});
			
			if (inputType == "checkbox") {
				$(".ulist", currentDoc).click(function(){
					if ($("input[name=c]",this).attr("checked") == "checked") {
						$("input[name=c]",this).removeAttr("checked");
						$(this).removeClass("selectUL");
					} else {
						$("input[name=c]",this).attr("checked", "checked");
						$(this).addClass("selectUL");
					}
				});
			} else if(inputType == "radio") {
				$(".ulist", currentDoc).click(function(){
					$("input[name=c]",currentDoc).removeAttr("checked");
					$(".ulist",currentDoc).removeClass("selectUL");
					$("input[name=c]",this).attr("checked", "checked");
					$(this).addClass("selectUL");
				});
			}
			
			$(".pager a", currentDoc).click(function(){
				confirmChecked();
			});
			
			if (!$(".tools", currentDoc).hasClass("keep")) {
				$(".tools", currentDoc).remove();
			}
			if (!$(".operationH", currentDoc).hasClass("keep")) {
				$(".operationH", currentDoc).remove();
			}
			if (!$(".operation", currentDoc).hasClass("keep")) {
				$(".operation", currentDoc).remove();
			}
			
			if(!$("#_ButtonOK_" + (Dialog._dialogArray.length - 1)).hasClass("bind")) {
				var height = $("#_ButtonRow_" + (Dialog._dialogArray.length - 1)).prev().find("td div").height();
				$("#_ButtonRow_" + (Dialog._dialogArray.length - 1)).show();
				$("#_ButtonOK_" + (Dialog._dialogArray.length - 1)).show();
				$("#_ButtonRow_" + (Dialog._dialogArray.length - 1)).prev().find("td div").css("height", height - 40);
				$("#_ButtonOK_" + (Dialog._dialogArray.length - 1)).addClass("bind");
				$("#_ButtonOK_" + (Dialog._dialogArray.length - 1)).click(function(){funCall();Dialog._dialogArray[Dialog._dialogArray.length - 1].close();});
			}
		}
	} catch(e){alert(e);}
}

//保存当前选中的记录
function confirmChecked(){
	try{
		if (documents["_DialogFrame_" + (Dialog._dialogArray.length - 1)]) {
			currentDoc = documents["_DialogFrame_" + (Dialog._dialogArray.length - 1)];
		} else {
			currentDoc = document.getElementById("_DialogFrame_" + (Dialog._dialogArray.length - 1)).contentWindow.document;
		}
		
		if ($(".ulistheader",currentDoc).hasClass("check")){
	    	var datas = {};
			$('.ulist input:checked',currentDoc).each(function(index) {
				var dataRow = $(this).parent().parent();
				var data = {};
				$(".ulistheader li",currentDoc).each(function(index){
					var val = $(this).attr("val");
					if (val != "") {
						data[val] = $("li:nth-child("+(index+1)+")", dataRow).attr("val");
					}
				});
				data[$(".ulistheader",currentDoc).attr("val")] = $(dataRow).attr("val");
				datas[$(dataRow).attr("val")] = data;
			});
			putPagerCheckedList($(".pager",currentDoc).attr("val"),datas);
		}
	} catch(e){alert(e);}
}

function funCall() {
	try{
		if (documents["_DialogFrame_" + (Dialog._dialogArray.length - 1)]) {
			currentDoc = documents["_DialogFrame_" + (Dialog._dialogArray.length - 1)];
		} else {
			currentDoc = document.getElementById("_DialogFrame_" + (Dialog._dialogArray.length - 1)).contentWindow.document;
		}
		if ($(".ulistheader", currentDoc).hasClass("check")){
			confirmChecked();
		}
		if (callbacks[Dialog._dialogArray.length - 1] != null) {
			if ($(".ulistheader", currentDoc).hasClass("check")){
				if (inputType == "checkbox" && getCheckedList().length != 0) {
					callbacks[Dialog._dialogArray.length - 1](getCheckedList());
				} else if (getSingleChecked() != null){
					callbacks[Dialog._dialogArray.length - 1](getSingleChecked());
				}
			} else {
				callbacks[Dialog._dialogArray.length - 1].call();
			}
			callbacks[Dialog._dialogArray.length - 1] = null;
		}
	}catch(e){
		//alert(e.message);
	}
}

function closeView() {
//	if (Dialog._dialogArray.length == 1) {
		funCall();
//	}
	clearChecked();
	documents["_DialogFrame_" + (Dialog._dialogArray.length - 1)] = null;
	
	if (Dialog._dialogArray.length == 0) {
		params = {};
	}
	pagers = {};
	
	Dialog._dialogArray[Dialog._dialogArray.length - 1].close();
}

function put(name, value) {
	params[name] = value;
}

function get(name) {
	var val = params[name];
	params[name] = null;
	return val;
}

/*
{页码:{记录唯一标识:{属性名:属性值}}}
*/
var pagers = {};


function clearChecked() {
	pagers = {};
}
function putPagerCheckedList(currentPage, list){
	if (inputType == "checkbox")
		pagers[currentPage] = list;
	else {
		pagers = {};
		pagers[currentPage] = list;
	} 
}

function getCheckedList() {
	var data = new Array();
	for(p in pagers) {
		for(d in pagers[p]) {
			data.push(pagers[p][d]);
		}
	}
	return data;
}
function getSingleChecked() {
	if (getCheckedList().length != 0)
		return getCheckedList()[0];
	else 
		return null;
}

function getPagerChecked(currentPage) {
	return pagers[currentPage] ||{};
}