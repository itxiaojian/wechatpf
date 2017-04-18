<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,initial-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>发布商品</title>
        <link rel="stylesheet" href="<%=path%>/resources/js/wsh/essc/addCommon.css">
        <link rel="stylesheet" href="<%=path%>/resources/js/wsh/essc/addMain.css">
    <style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/mobiscroll/mobiscroll.custom-2.13.2.min.css">  
 <!--时间控件mobiscroll-->  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>  
  
  
   <link href="<%=path%>/resources/mobiscroll/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />  
   <link href="<%=path%>/resources/mobiscroll/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>
   
    <script type="text/javascript">
    	var j=0;
    	function addOptions(v) { 
    		var openId='${openId}';
    		alert(v);
    		var sel = document.getElementById('bz');
    		sel.options.length = 1; //清空原来的option        
    		$.ajax({  
                    url: "getSkdd",    //后台webservice里的方法名称  
                    type: "POST",  
                    dataType: "json",  
                    data:{kcbh : v,openId:openId}, 
                    traditional: true,  
                    success: function (data) { 
                        for (var i = 0; i < data.length; i++) {  
                            var jsonObj =data[i];
    						sel.options.add(new Option(data[i].skdd, data[i].skdd));        
                        }  
                    },  
                    error: function (msg) {  
                        alert("出错了！");  
                    }  
                });            
    	}
    	function toSave(){
    		j++;
    		if(j>1){
    			alert("点名已经发布，请不要重复发布！");
    			location.href ="<%=path%>/wsh/ZsSkkqb/toEsscList";
    			return false;
    		}
    		var kcmc=$('#kcmc').val();
    		var appDateTime=$('#appDateTime').val();
    		var sxsj=$('#sxsj').val();
    		var bz=$('#bz').val();
    		if(kcmc==null || kcmc==''){
    			alert("请选择课程！");
    			return false;
    		}
    		if(bz==null || bz==''){
    			alert("请选择上课地点！");
    			return false;
    		}
    		if(appDateTime==null || appDateTime==''){
    			alert("请填写上课时间！");
    			return false;
    		}
    		if(sxsj==null || sxsj==''){
    			alert("请填写有效时长！");
    			return false;
    		}
    		$('#publish-form').submit();
    	}
    	$(function () {  
            var currYear = (new Date()).getFullYear();    
            var opt={};  
            opt.date = {preset : 'date',maxDate:new Date()};  
            //opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };  
            opt.datetime = {preset : 'datetime'};  
            opt.time = {preset : 'time'};  
            opt.default = {  
                theme: 'jqm', //皮肤样式  
                display: 'modal', //显示方式   
                mode: 'scroller', //日期选择模式  
//                 dateFormat : 'yy-mm-dd HH:ii', // 日期格式 
//                 dateOrder : 'yymmdd HHii', //面板中日期排列格式  
                lang:'zh',  
                startYear:currYear - 20, //开始年份  
                endYear:currYear + 20 //结束年份  
            };  

            $("#appDateTime").val('').scroller('destroy').scroller($.extend(opt['datetime'], opt['default']));  
            var optDateTime = $.extend(opt['datetime'], opt['default']);  
//             $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
        });
//         function setValue(){
//         	var time=$('#date').val();
//         	$("#appDateTime").val(time);
//         }
    </script>
</head>
<body>
    <div class="wrap-loading hide">
    <div class="loading-box hide">
        <div class="loader">
            <div class="ball"></div>
            <div class="ball"></div>
            <div class="ball"></div>
        </div>
    </div>
</div>
<div class="page bg-white">
    <div class="login-header header bg-green">
        <a href="javascript:;" class="head-right fr" id="publish-finish" onclick="toSave();">完成</a>
        <span class="publish-mid">发布</span>
        <a href="<%=path%>/wsh/ZsSkkqb/toEsscList?openId=${openId}" class="head-left fl" id="go-prev"></a>
    </div>
    <div class="content border-box">
        <div class="wrap-tb">
            <form id="publish-form" action="<%=path%>/wsh/ZsSkkqb/save" method="post">
            <input type="hidden" id="openId" name="openId" value="${openId }">
                <table class="tb-publish">
                    <colgroup>
                        <col width="80">
                    </colgroup>
                    <tbody><tr>
                        <td><span class="wrap-td-txt">课程名称</span></td>
                        <td>
                        	<select id="kcmc" name="kcmc" onchange="addOptions(this.value);">
                        		<option value="">请选择</option>
                        		<c:forEach var="data" items="${kcList }" varStatus="obj">
                        		<option value="${data.kcbh }_${data.kcmc }">${data.kcmc }</option>
                        		</c:forEach>
                        	</select>
                        </td>
                    </tr>
                    <tr>
                        <td><span class="wrap-td-txt">上课地点</span></td>
                        <td>
                        	<select id="bz" name="bz">
                        		<option value="">请选择</option>
                        	</select>
                        </td>
                    </tr>
                    <tr>
                        <td><span class="wrap-td-txt product-info-left">上课时间</span></td>
                        <td>
                        	<input class="span_input" id="appDateTime" name="time" /> 
                        </td>
                    </tr>
                    <tr>
                        <td><span class="wrap-td-txt">签到时限</span></td>
                        <td><input type="text" require="" id="sxsj" name="sxsj" placeholder="请填写时长，单位分钟。" onblur = "this.value = this.value.replace(/\D+/g, '')"></td>
                    </tr>
                </tbody></table>
            </form>
        </div>
    </div>
</div>
        <script src="<%=path%>/resources/js/wsh/essc/jquery.validateform.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/exif.min.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/plupload.full.min.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/zh_CN.js"></script>
<%--         <script src="<%=path%>/resources/js/wsh/essc/qiniu.min.js"></script> --%>
        <script src="<%=path%>/resources/js/wsh/essc/publish.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/main.js"></script>
</body></html>