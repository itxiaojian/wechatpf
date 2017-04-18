function clearForm(myformId) {
    $(':input','#' +myformId)
    .not(':button, :submit, :reset, :hidden')  
    .val('')  
    .removeAttr('checked')  
    .removeAttr('selected');  
}

function clearFormAll(myformId) {
    $(':input','#' + myformId)
    .not(':button, :submit')
    .val('')
    .removeAttr('checked')
    .removeAttr('selected');
}

$(document).ready(function(){
	
	try{
		$("select").each(function(){
			$(this).attr("keepDefaultStyle", "true");
		});
		var doc = parent.window.document.getElementById("content").contentWindow.document;
		if (document == doc)
			$("#scrollContent").height(top.getContentHeight()-50 - ($("form").height()+$(".box_tool_min").height() + $(".pager").height()));
	}catch(e) {
		
	}
	
	if ($(".tableStyle").length != 0) {
		if($(".tableStyle").attr("mode") != "list")
			return;
			
		var tableListWidth = $(".tableStyle").width();
		var usedWidth = 0;
		var thCount = 0;
		$(".tableStyle tr th").each(function(i){
			thCount++;
			if ($(this).attr("width") != undefined)
				usedWidth += $(this).width(); 
		});
		
		$(".tableStyle tr th").each(function(i){
			if ($(this).attr("width") != undefined) {
				
			} else if(i == 0) {
				$(this).attr("width","30");
			} else {
				$(this).attr("width",parseInt((tableListWidth-usedWidth)/thCount));
			}
		});
		
		$(".tableStyle tbody tr").click(function(){
			if ($(".nodata",this).length > 0) {
				return;
			}
			var cb = $("input[type=checkbox]",this);
			
			if (cb.attr("checked") == 'checked') {
				cb.removeAttr("checked");
			} else {
				cb.attr("checked",true);
			}
		});
	}

	
	$(".submitForm").click(function(){
		try{
			top.showProgressBar();
		}catch(e) {
		
		}
		$("#limitId").val($("#pageSizeId").val());
		$("#searchForm").submit();
	});
	$(".clearForm").click(function(){
		clearForm("searchForm");
	});
	$(".refreshForm").click(function(){
		refreshList();
	});
	
	$("#pageContent").bind("pageChange",function(e,index){
		toPage(Number($(this).attr("pageSize"))*index);
	});
	
	$("select").each(function(){
		if ($(this).attr("val") != "undefined"){
			var opts = $("option", this);
			for(var i = 0; i <opts.length;i++) {
				if ($(this).attr("val") == opts[i].value) {
					$(opts[i]).attr("selected", true);
				}			
			}
		}
	});
	
	$("select").addClass("textinput");
	
	if (window.frameElement && window.frameElement.id == "content" && $("#tabMenuID_").size() != 0) {
		top.displayNav($("#tabMenuID_").html());
	}
	
	$(".cleanBtn").click(function() {
		var ids = $(this).attr("rel").split(",");
		for(var i = 0; i < ids.length; i++)
			$("#"+ids[i]).val('').removeAttr('checked').removeAttr('selected');
	});
	
	$(".date").focus(function(){
		var dateFormat="yyyy-MM-dd";
		if($(this).attr("dateFmt")!=null){
			dateFormat=$(this).attr("dateFmt");
		}
		var doubleCal=false;
		if($(this).attr("doubleCal")==true||$(this).attr("doubleCal")=="true"){
			doubleCal=true;
		}
		WdatePicker({
			lang:'zh-cn',skin:'blue',isShowClear:true,dateFmt:dateFormat,doubleCalendar:doubleCal,
			onpicked:function(dp){
				$(this).blur();
			}
		});
	});
	var str=top.location.pathname;
	if(str.indexOf("layout")>0){
		top.closeProgress();
	}
});



function toPage(page) {
	top.showProgressBar();
	document.getElementById("searchForm").reset();
	$("#startId").val(page);
	$("#limitId").val($("#pageSizeId").val());
	$("#searchForm").submit();
}
function refreshList() {
	if ($("#searchForm").length != 0) {
		top.showProgressBar();
		document.getElementById("searchForm").reset();
		$("#searchForm").submit();
	}
}

function onAction(conf){
	var args = conf;
	
	if (conf.url == undefined){
		alert("onAction 参数url未配置");
		return;
	}
	
	//////////////////////////////////////
	if(args.mutiple != undefined) {
		if (args.params == undefined)
			args.params = {};
		if (args.mutiple) {//多选
			var length = $("input:checked").length;
			if (length == 0) {
				top.Dialog.alert("请选择!");
				return;
			} else if (($(".nodata",$("input:checked").parent().parent()).length > 0)) {
				top.Dialog.alert("选择无效!");
				return;
			}
			
			var ids = [];
			$("input:checked").parent().parent().each(function(index){
				if($(this).find(".pk").length > 1) {
					$(this).find(".pk").each(function(){
						args.params['mapList['+index+']['+$(this).attr("id")+']'] = $(this).attr("val");
					});
				} else {
					$(this).find(".pk").each(function(){
						ids[index] = $(this).attr("val");
					});
				}
			});
			args.params['ids'] = ids;
		} else {//单选
			var length = $("input:checked").length;
			if (length == 0) {
				top.Dialog.alert("请选择!");
				return;
			} else if (length > 1) {		
				top.Dialog.alert("请选择一条记录!");
				return;
			} else if (($(".nodata",$("input:checked").parent().parent()).length > 0)) {
				top.Dialog.alert("选择无效!");
				return;
			}
			$("input:checked").parent().parent().find(".pk").each(function(){
				if (args.pname)
				{
					args.params[args.pname] = $(this).attr("val");
				} else 
				{
					args.params[$(this).attr("id")] = $(this).attr("val");
				}
			});
		}
	}
	
	if (args.callback == undefined && refreshList != undefined) {
		args.callback =refreshList;
	}
	
	if (args.confirm){
		if (args.message == undefined)
			args.message = '操作确认';
		top.Dialog.confirm(args.message,function(){
			top.onView(args);
		});
	} else {
		top.onView(args);
	}
}
