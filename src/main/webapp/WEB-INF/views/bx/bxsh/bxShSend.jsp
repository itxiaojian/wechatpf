<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>报修审核</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/aero.css" rel="stylesheet" />
<link href="<%=path%>/resources/css/bxsh.css" rel="stylesheet" />
<link rel="shortcut icon" type="image/x-icon"
	href="<%=path%>/resources/src/favicon.ico">
<link rel="stylesheet" media="screen"
	href="<%=path%>/resources/src/published.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/jquery_002.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/jquery_003.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/artDialog.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/public.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
   <script type="text/javascript">
  
    	function getWxzl(value){
    		$.ajax({
    			cache : true,
    			type : "POST",
    			url : "<%=path%>/bx/bxsh/getsender",
    			data : {id:value},
    			async : false,
    			error : function(request) {
    				alert("连接数据库失败，请联系管理员。");
    			},
    			success : function(data) {
    				$("#wxxl").empty();
                    $("<option value='-1'>请选择---</option>").appendTo($("#wxxl"));  
                    $.each(eval(data), function(i, item) {
                       /*  $("<option value='" + item.yhbh+ "'>" + item.xm + "</option>").appendTo($("#wxxl")); */ 
                    	  $("<option value='" + item.yhbh+"_"+item.xm + "'>" + item.xm + "</option>").appendTo($("#wxxl"));
                    });
    			}
    		}); 
    	}
    
    	function getWxg(value){
    		var i = value.split('_');
    		//alert(i[1]);
    		$('#pdrvalue').val(i[0]);
    		//alert($('#pdrvalue').val(i[0]));
    		$('#pdrtext').val(i[1]);
    		//alert($('#pdrvalue').text());
    	}
    </script>
</head>
<body id="mainbody" style="overflow-y:scroll;" class="objbody1"><div class="" style="display: none; position: absolute;"><div class="aui_outer"><table class="aui_border"><tbody><tr><td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr><tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner"><table class="aui_dialog"><tbody><tr><td colspan="2" class="aui_header"><div class="aui_titleBar"><div style="cursor: move;" class="aui_title"></div><a class="aui_close" href="javascript:/*artDialog*/;">×</a></div></td></tr><tr><td style="display: none;" class="aui_icon"><div style="background: transparent none repeat scroll 0% 0%;" class="aui_iconBg"></div></td><td style="width: auto; height: auto;" class="aui_main"><div style="padding: 20px 25px;" class="aui_content"></div></td></tr><tr><td colspan="2" class="aui_footer"><div class="aui_buttons"></div></td></tr></tbody></table></div></td><td class="aui_e"></td></tr><tr><td class="aui_sw"></td><td class="aui_s"></td><td style="cursor: auto;" class="aui_se"></td></tr></tbody></table></div></div>
    <div class="maindiv">
        <div style="height:20px;">&nbsp;</div>
        	<div class="field" data-api-code="field_16">
						<div class="form-group ">
							<!-- <label class="control-label field_title" onclick=""
								data-role="collapse_toggle" for="wxdl">维修种类:<span></span>
								<span style="color: red;">*</span> -->
								<%-- <span>
									<c:forEach var="data" items="${listWxdl}" varStatus="obj">
									${data.wxdl}
									</c:forEach>
									</span> --%>
							<!-- </label> -->
							&nbsp;&nbsp;
							<div class="field-content">
								<div class="controls">
									<%-- <select  name="wxdl" onchange="getWxzl(this.value);">
										<option value="-1">请选择---</option>
										<c:forEach var="data" items="${list}" varStatus="obj">
											<option value="${data.zdz }">${data.zdbm }</option>
											</c:forEach>
									</select> --%>
								</div>
							</div>
						</div>
					</div>
					<div class="field" data-api-code="field_17">
						<div class="form-group ">
							<label class="control-label field_title" onclick=""
								data-role="collapse_toggle" for="wxdl">维修工:<span>（必选项）</span>
								<span style="color: red;">*</span>
							</label>
							<div class="field-content">
								<div class="controls">
									<select id="wxxl" name="wxxl" onchange="getWxg(this.value);">
										<option value="-1">请选择---</option>
										<c:forEach var="data" items="${list}" varStatus="obj">
											<option value="${data.yhbh}_${data.xm}">${data.xm}</option>
											</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<input style="display:none;" type="text" value="" id="pdrvalue"/>
						<input style="display:none;" type="text" value="" id="pdrtext"/>
					</div>
    </div>
<div style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div></body></html>