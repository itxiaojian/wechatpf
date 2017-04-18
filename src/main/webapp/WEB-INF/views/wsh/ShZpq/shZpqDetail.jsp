<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js" />
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js" />
<script type="text/javascript" src="<%=path%>/resources/js/app.js" />
<script src="<%=path%>/libs/js/framework.js" type="text/javascript" />
<script src="<%=path%>/resources/js/wzy/jquery.js"></script>
<script src="<%=path%>/resources/js/wzy/json2.js"></script>
<link type="text/css" href="<%=path%>/resources/css/tucao.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/bbq.css" />

<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/resources/css/wzy/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
	<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload.js"></script>
<title>照片墙</title>
<style>
body {-webkit-overflow-scrolling: touch;overflow-scrolling: touch;}
/*全局*/
*,li,ul,a{ margin:0px; padding:0px; font-family:'微软雅黑'; list-style:none; text-decoration:none;}
/*微主页*/
.WZY_top{ width:100%; height:120px; position:fixed; left:0px; top:0px;background:url(<%=path%>/resources/img/wzy/logo.png) center no-repeat; background-size:cover; overflow:hidden;}
.WZY_top img{ float:right; margin-right:5%; margin-top:1%;}
.WZY_main{ width:100%;position:absolute; top:100px; left:0px; bottom:40px;overflow:auto; background: url(<%=path%>/resources/img/wzy/chatu02.png) no-repeat bottom right; background-size: contain;}
.WZY_banner{width:100%; height:400px; }
.WZY_icon img{ display:block; margin-left:20%;}
.WZY_icon span{ width:25%; text-align:center; float:left; font-size:36px; color:#666; line-height:50px; margin-top:6%;}
.WZY_foot{width:100%; height:40px; position:absolute; left:0px; bottom:0px;}
/*照片墙*/
.WZY_top01{ width:100%; height:54px; position:fixed; left:0px; top:0px;overflow:hidden;}
.New_main01{ width:100%;position:absolute; top:54px; left:0px; bottom:20px;overflow:auto;}
.WZY_foot01{width:100%; height:20px; position:absolute; left:0px; bottom:0px; background-color:#000000;overflow:hidden;}
.Home_btn{ position:absolute; right:40px; top:24%; z-index:2}
.Photo_news{ width:290px; margin:auto; margin-top:10px; padding-bottom:30px; position:relative;}
.people_name{ font-size:16px; color:#719db8; padding-left:50px;}
.Photo_news p{font-size:13px; color:#000; margin-top:4px;padding-left:50px;}
.news_photo{ padding-bottom:10px;float:left;}
.photo01{ width:140px; height:140px; background-color:#000000;float:left; }
.photo02{ width:90px; height:90px; background-color:#000000;float:left; margin-bottom:10px; }
.new_bottom{ clear:both;}
.new_bottom span{ float:right; line-height:30px; margin-left:10px;font-size:12px; color:#999;}
.zan{ width:30px; height:30px; background:url(<%=path%>/resources/img/wzy/BB_icon02.png) center no-repeat; background-size:cover; float:left}
.time{width:30px; height:30px; background: url(<%=path%>/resources/img/wzy/time.png) center no-repeat;background-size:cover; float:left}
.people_tou{ position:absolute; top:0; left:0px;}
.zhaopian_news{ padding-bottom:30px; border-bottom:1px solid #719db8;}
.new_add{ position:absolute; bottom:10%; left:0; z-index:999;}
.add_msg{ width:100%; height:260px; background-color:#000; position:absolute; bottom:40px; left:0px; z-index:1000; border-top:1px solid #719db8;overflow: scroll; opacity:0.9; display:none; }
.add_msg textarea{ color:#ccc;}
.photo_add { width:300px; margin:auto;}
.photo_add span{ background:url(<%=path%>/resources/img/wzy/add_photo.png) center no-repeat; width:90px; height:90px; float:left;margin-top:10px;}
.add_quxiao{ float:left; margin-top:10px; margin-bottom:10px;}
.add_fabu{ float:right; margin-top:10px; margin-bottom:10px;}
.delete{width:60px; height:40px; text-align:center; border-radius:50px; color:#ccc; float:left}
.delete s{font-size:16px; line-height:32px;}
</style>
<style>
#replay {
	z-index: 1000;
	display: none;
}
.btn-block {
    display: block;
    width: 100%;
}
.btn-lg, .btn-group-lg > .btn {
    border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
    border-top-left-radius: 6px;
    border-top-right-radius: 6px;
    font-size: 18px;
    line-height: 1.33;
    padding-bottom: 10px;
    padding-left: 16px;
    padding-right: 16px;
    padding-top: 10px;
}
.btn-default {
    background-color: #fff;
    border-bottom-color: #ccc;
    border-left-color: #ccc;
    border-right-color: #ccc;
    border-top-color: #ccc;
    color: #333;
}
.btn {
border-bottom-style: solid;
    border-bottom-width: 1px;
    border-image-outset: 0 0 0 0;
    border-image-repeat: stretch stretch;
    border-image-slice: 100% 100% 100% 100%;
    border-image-source: none;
    border-image-width: 1 1 1 1;
    border-left-style: solid;
    border-left-width: 1px;
    border-right-style: solid;
    border-right-width: 1px;
    border-top-style: solid;
    border-top-width: 1px;
    cursor: pointer;
     text-align: center;
    vertical-align: middle;
    white-space: nowrap;
}
button, input, optgroup, select, textarea {
    -x-system-font: none;
    color: inherit;
    font-family: inherit;
    font-feature-settings: inherit;
    font-kerning: inherit;
    font-language-override: inherit;
    font-size: inherit;
    font-size-adjust: inherit;
    font-stretch: inherit;
    font-style: inherit;
    font-synthesis: inherit;
    font-variant-alternates: inherit;
    font-variant-caps: inherit;
    font-variant-east-asian: inherit;
    font-variant-ligatures: inherit;
    font-variant-numeric: inherit;
    font-variant-position: inherit;
    font-weight: inherit;
    line-height: inherit;
    margin-bottom: 0;
    margin-left: 0;
    margin-right: 0;
    margin-top: 0;
}
</style>
    <script type="text/javascript">
    var i=0;
    var j=1;
    var s="1";
    var src="";
    function toAddImg(j){
    	if(i>=0){
    		if ($("#file"+j).val().length > 0) {
    			$("#file"+j).val("");
    		}
    	}
    	$("#file"+j).click();
    }
    function selectFile(j,openId){
    	if ($("#file"+j).val().length > 0) {
            ajaxFileUpload(j,openId);
            i++;
        } else {
            alert("请选择图片");
        }
    }
    function ajaxFileUpload(j) {
        $.ajaxFileUpload({
            url : "<%=path%>/wsh/ShZpq/fileUload?wjlx="+s+"&lxid=1&openId="+openId, //用于文件上传的服务器端请求地址
            secureuri : false, //一般设置为false
            fileElementId : 'file'+j, //文件上传空间的id属性  <input type="file" id="file" name="file" />
            type : 'post',
            dataType : 'HTML', //返回值类型 一般设置为json
            success : function(data, status) //服务器成功响应处理函数 
            {
            	$.ajax({
    				url:'<%=path%>/wsh/ShZpq/getImgId',
    				type : 'post',
    				//dataType : 'json',
    				data:{
    					fname:data
    				},
    				success: function(data){
    				 	src="<%=path%>/wsh/ShZpq/getFile?imgId="+data;
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
</head>


<body>
	<div class="iphone">
		<div class="new_add" id="add">
			<img src="<%=path%>/resources/img/wzy/add.png" width="60" height="60">
		</div>
		<form class="center" id="new_entry" action="" accept-charset="UTF-8"
		method="post">
		<div class="add_msg" id="add_msg">
			<div class="container">
				<div class="add_quxiao">
					<button type="button" class="btn btn-danger btn-xs">
						取消
					</button>
				</div>
				<div class="add_fabu">
					<button type="button" class="btn btn-success btn-xs" onclick="niming('${openId}');">
						发布
					</button>
				</div>
				<br>
				<textarea id="t_tucao_text" maxlength="140" rows="4" style="width:100%;"></textarea>
				<div clsss="photo_add">
					<span>
						<label class="control-label field_title" onClick=""
								data-role="collapse_toggle" for="bz">可上传六张张图片(不支持动态图片)</label>
							<div class="field-content">
                            <div class="field-content uploadDiv1">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(1);"style="">
								<img id="img1" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 200px; left: 35px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
	        	               <input id="file1"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 200px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(1,'${openId}');">
	                        </div> 
	                        <input type='hidden' id='fileName1' name='bz1' value=''>
							</div>
							
						   <div class="field-content uploadDiv2" style="display:none;">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(2);">
								<img id="img2" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 200px; left: 35px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
	        	               <input id="file2"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 25px; left: 35px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(2,'${openId}');">
	                        </div> 
	                        <input type='hidden' id='fileName2' name='bz2' value=''>
							</div>
							
						    <div class="field-content uploadDiv3" style="display:none;">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(3);">
								<img id="img3" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 200px; left: 35px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
	        	               <input id="file3"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 25px; left: 35px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(3,'${openId}');">
	                        </div> 
	                        <input type='hidden' id='fileName3' name='bz3' value=''>
							</div>
							
							 <div class="field-content uploadDiv4" style="display:none;">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(4);">
								<img id="img4" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 200px; left: 35px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
	        	               <input id="file4"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 25px; left: 35px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(4,'${openId}');">
	                        </div> 
	                        <input type='hidden' id='fileName4' name='bz4' value=''>
							</div>
							
							 <div class="field-content uploadDiv5" style="display:none;">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(5);">
								<img id="img5" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 200px; left: 35px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
	        	               <input id="file5"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 25px; left: 35px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(5,'${openId}');">
	                        </div> 
	                        <input type='hidden' id='fileName5' name='bz5' value=''>
							</div>
							
							 <div class="field-content uploadDiv6" style="display:none;">
							<div id="add-product-image"  style="z-index: 1;" onClick="toAddImg(6);">
								<img id="img6" alt="点击"  align="center" style="height: 160px;width:160px; text-align: center;"/>
						    </div>
                            <div id="uploadDiv"  class="controls" style="position: absolute; top: 200px; left: 35px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
	        	               <input id="file6"  name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 25px; left: 35px; width: 100%; height: 100%;" accept="image/*" capture="camera" onChange="selectFile(6,'${openId}');">
	                        </div> 
	                        <input type='hidden' id='fileName6' name='bz6' value=''>
							</div>
							</div>
					</span>
					<span style="margin-left:10px"></span>
				</div>
			</div>	
		</div>
		</form>
		<script>
		    $("#add").click(function(){
				$("#add_msg").fadeIn("slow");
			})
			$(".add_quxiao").click(function(){
				window.location.reload();
				$("#add_msg").fadeOut("slow");
			})
			$(".add_fabu").click(function(){
				$("#add_msg").fadeOut("slow");
			})
		</script>
		<div class="WZY_top01" style="border-bottom:1px solid #719db8">
			<div class="row" style="height:100%;">
				<div class="col-sm-12" style="position:relative;height:100%;">
					<img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive" style="height:100%;">
					<a href="<%=path%>/wsh/zy/zhuye?openId=${openId}">
						<img class="Home_btn" src="<%=path%>/resources/img/wzy/FH.png" style="width:10%;right:30px;">
					</a>		
				</div>
			</div>
		</div>
		<div class="New_main01" style="margin-bottom:10px;">
			<div class="container">
			<c:forEach items="${list}" var="list" varStatus="obj">
				<div class="zhaopian_news">
					<div class="Photo_news" style="height:46px;">
						<c:if test="${list.tcrxm=='***'}">
							<div class="people_tou">
								<img src="<%=path%>/resources/img/wzy/wsh_nmtx.png" width="46" height="46">
							</div>
						</c:if>
						<c:if test="${list.tcrxm!='***' }">
							<div class="people_tou">
									<img src="${list.txdz}" width="46" height="46">
							</div>
						</c:if>
						<div>
							<span class="people_name">${list.tcrxm}</span>
							
						</div>
						<p>${list.tcnr}</p>
						</div>
						<div class="new_photo">
							<c:forEach items="${list1}" var="list1" varStatus="obj">
									<c:if test="${list.tcr==list1.wxh }">
										<c:if test="${list.bz1==list1.id||list.bz2==list1.id||list.bz3==list1.id||list.bz4==list1.id||list.bz5==list1.id||list.bz6==list1.id}">
										<div class="photo01" style="width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;" >
											<img src="<%=path%>/wsh/ShZpq/getFile?imgId=${list1.id}" width="120" height="120"  onclick="fangda('${list1.id}')">
										</div>
										</c:if>
									</c:if>
							</c:forEach>
						</div>
					<div class="new_bottom">
						<%-- <c:if test="${list.tcr==openId}">
							<a href="javascript:void(0);" onclick="DisplayDelete('${list.id }','${openId}')" style="color:#fff;">
							<span class="delete">
								<s class="glyphicon glyphicon-trash"></s>
							</span>
							</a>
						</c:if> --%>
						<c:forEach items="${dlr}" var="dlr" varStatus="obj" begin="0" end="0">
						<c:if test="${dlr.yhid=='1'||dlr.dlm=='admin'||list.tcr==openId}">
							<a href="javascript:void(0);" onclick="DisplayDelete('${list.id }','${openId}')" style="color:#fff;">
							<span class="delete">
								<s class="glyphicon glyphicon-trash"></s>
							</span>
							</a>
						</c:if>
						</c:forEach>
						<span>
							<i class="time" style="margin-rignt:30%;"></i>
							${fn:substring(list.tcsj,0,10)}
						</span>
						<a class="S_txt2" href="javascript:void(0);" onclick="DoTCZhan('${list.id}','${openId}')">
						<i class="zan" style="margin-left:20%;float:left;"></i>
						<span id="t_tucao_zhan_${list.id}" style="margin-left:0px;float:left;">
							<c:if test="${list.bzcs <=99 }">
							${list.bzcs}
							</c:if>
							<c:if test="${list.bzcs >99 }">
							99+
							</c:if>
						</span>
						</a>
					</div>
				</div>
				</c:forEach>
		   		<div class="LsMore"></div>
			</div>
			<c:if test="${!empty list}">
		            <div class="-ft" style="margin-top: 20px;">
						<button id="-ft" class="btn btn-default btn-block btn-lg ng-binding jzgd" onclick="loadMore('${pages}','${openId }');" style="position:relative;bottom:10px;">加载更多</button>
					</div>
			    </c:if>
		</div>
		<div class="WZY_foot" style="width:100%;">
			<img src="<%=path%>/resources/img/BQ.png" style="width:100%;"/>
		</div>
	</div>
	<form method="post"
		action="tucao.aspx?OpenID=oRvupjoWhCKA3zufAtcpx0XnFtI8" id="form1">
		<div class="aspNetHidden">
			<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"
				value="/wEPDwUKMTY0MDc2Nzg2N2Rk9Cyx3Iii1rsx0f/do2egB6ykfZPbyTNcn1e+64jQGu8=" />
		</div>

		<div class="aspNetHidden">

			<input type="hidden" name="__VIEWSTATEGENERATOR"
				id="__VIEWSTATEGENERATOR" value="ADBD8E75" />
		</div>
	</form>

<script>
//JavaScript Document
var time=3;
var openId = '${openId}';
var stop;
var Alert={	  	
	//确认
		showConfirmMsg:function(obj){
			var subhtml='<div id="alert_dialog_show_msg_box" style=" overflow:hidden;height:100px;width:200px;margin:15% auto;">'
			+'<div class="sweet-overlay" tabIndex="-1" style=" background-color:#000; opacity:0.4; position: fixed; left: 0; right: 0; top: 0; bottom: 0; z-index:1000;"></div>'
			+'<div id="alert_show_3" style="height:100px; width:280px; background-color:#fff; color:#000; border-radius:8px;font-size:14px; text-align:center;z-index:4000;left:10%;top:40%;position:absolute;">'
			+'<p style="font-size:14px; margin-top:10px;margin-left:10%;margin-right:5%;">'+obj+'</p>'
			+'<input name="button" onclick="DoBiaoBai1(openId)" type="button" value="是" style=" width:60px; height:30px; line-height:30px;font-size:14px;position:absolute;bottom:10%;left:25%;"  />'
			+'<input name="button" onclick="DoBiaoBai(openId)" type="button" value="否" style=" width:60px; height:30px; line-height:30px;font-size:14px;position:absolute;bottom:10%;left:60%;"  /></div></div>';
				$("body").append(subhtml);
			 callback = callback || function(){};
			}	
}
//关闭确认弹出框
function rec(callback){
	$("#alert_dialog_show_confirm_box").remove();
	callback();
}

//关闭弹出框
function closedShowMsg(){
		$("#alert_dialog_show_msg_box").remove();
	}
	
function niming(openId){
	Alert.showConfirmMsg("是否匿名发送照片信息？");
}
</script>

<script>
//JavaScript Document
var time=3;
var openId = '${openId}';
var stop;
var Alert1={	  	
	//确认
		showConfirmMsg1:function(id){
			var subhtml='<div id="alert_dialog_show_msg_box" style=" overflow:hidden;height:100px;width:200px;">'
			+'<div class="sweet-overlay" tabIndex="-1" style=" background-color:#000; opacity:0.4; position: fixed; left: 0; right: 0; top: 0; bottom: 0; z-index:1000;" onclick="closedShowMsg()"></div>'
			+'<div id="alert_show_3" style="display: flex;	justify-content:center;	align-items:Center;z-index:4000;position: fixed;width:100%;height:100%; "  onclick="closedShowMsg()">'
			+'<img src="<%=path%>/wsh/ShZpq/getFile?imgId='+id+'" style="max-width:100%;max-height:100%;" >'
			+'</div>'
			+'</div>';
				$("body").append(subhtml);
			 callback = callback || function(){};
			}	
}
//关闭确认弹出框
function rec(callback){
	$("#alert_dialog_show_confirm_box").remove();
	callback();
}

//关闭弹出框
function closedShowMsg(){
		$("#alert_dialog_show_msg_box").remove();
	}
	
function fangda(id){
	Alert1.showConfirmMsg1(id);
}
</script>

<script>
var i=0;
function loadMore(pages,openId){
	i=pages;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wsh/ShZpq/ZpqLoadmore?";
	var html="";
    $.ajax({
		url :url,
		data : {
			pages:i,
			openId:openId
		},
		type : "post",
		success : function(data) {
			if(data.length>0){
			var rst = eval(data);
			$.each(rst,function(i,value){
			if(value.tcrxm!=null){
				 html10 = "<div><span class='people_name'>"
			  	     	+ value.tcrxm
			  	     	+ "</span><p>"
			  	     	+ value.tcnr
			  	     	+ "</p></div>";
			 }
			if(value.tcrxm=='***'){
		  	     html1 = "<div class='Photo_news' style='height:46px;'>"
		  	     +"<div class='people_tou'><img src='<%=path%>/resources/img/wzy/wsh_nmtx.png' width='46' height='46'></div>"
		  	     +html10
		  	     +"</div>";
	  	   	}
	  	    if(value.tcrxm!='***'){
	  	    	 html1 = "<div class='Photo_news' style='height:46px;'>"
	  	    	 +"<div class='people_tou'><img src='"+value.txdz+"' width='46' height='46'></div>"
	  	    	 +html10
	  	    	 +"</div>";
	  	    }
	  	    if(value.bz1!=null&&value.bz2!=null&&value.bz3!=null&&value.bz4!=null&&value.bz5!=null&&value.bz6!=null){
	  	   	html2 = "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
		  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz1+"' width='120' height='120'  onclick='fangda("+value.bz1+")'>"
		  	     + "</div>"
		  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
		  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz2+"' width='120' height='120'  onclick='fangda("+value.bz2+")'>"
		  	     + "</div>"
		  	   	 + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
		  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz3+"' width='120' height='120'  onclick='fangda("+value.bz3+")'>"
		  	     + "</div>"
		  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
		  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz4+"' width='120' height='120'  onclick='fangda("+value.bz4+")'>"
		  	     + "</div>"
		  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
		  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz5+"' width='120' height='120'  onclick='fangda("+value.bz5+")'>"
		  	     + "</div>"
		  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
		  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz6+"' width='120' height='120'  onclick='fangda("+value.bz6+")'>"
		  	     + "</div>";
	  	     }
	  	     else if(value.bz1!=null&&value.bz2!=null&&value.bz3!=null&&value.bz4!=null&&value.bz5!=null&&value.bz6==null){
				 html2 = "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz1+"' width='120' height='120'  onclick='fangda("+value.bz1+")'>"
			  	     + "</div>"
			  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz2+"' width='120' height='120'  onclick='fangda("+value.bz2+")'>"
			  	     + "</div>"
			  	   	 + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz3+"' width='120' height='120'  onclick='fangda("+value.bz3+")'>"
			  	     + "</div>"
			  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz4+"' width='120' height='120'  onclick='fangda("+value.bz4+")'>"
			  	     + "</div>"
			  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz5+"' width='120' height='120'  onclick='fangda("+value.bz5+")'>"
			  	     + "</div>";
			 }
	  	     else if(value.bz1!=null&&value.bz2!=null&&value.bz3!=null&&value.bz4!=null&&value.bz5==null&&value.bz6==null){
				 html2 = "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz1+"' width='120' height='120'  onclick='fangda("+value.bz1+")'>"
			  	     + "</div>"
			  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz2+"' width='120' height='120'  onclick='fangda("+value.bz2+")'>"
			  	     + "</div>"
			  	   	 + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz3+"' width='120' height='120'  onclick='fangda("+value.bz3+")'>"
			  	     + "</div>"
			  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz4+"' width='120' height='120'  onclick='fangda("+value.bz4+")'>"
			  	     + "</div>";
			 }
	  	     else if(value.bz1!=null&&value.bz2!=null&&value.bz3!=null&&value.bz4==null&&value.bz5==null&&value.bz6==null){
				 html2 = "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz1+"' width='120' height='120'  onclick='fangda("+value.bz1+")'>"
			  	     + "</div>"
			  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz2+"' width='120' height='120'  onclick='fangda("+value.bz2+")'>"
			  	     + "</div>"
			  	   	 + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz3+"' width='120' height='120'  onclick='fangda("+value.bz3+")'>"
			  	     + "</div>";
			 }
	  	     else if(value.bz1!=null&&value.bz2!=null&&value.bz3==null&&value.bz4==null&&value.bz5==null&&value.bz6==null){
				 html2 = "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz1+"' width='120' height='120'  onclick='fangda("+value.bz1+")'>"
			  	     + "</div>"
			  	     + "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz2+"' width='120' height='120'  onclick='fangda("+value.bz2+")'>"
			  	     + "</div>";
			 }
	  	     else if(value.bz1!=null&&value.bz2==null&&value.bz3==null&&value.bz4==null&&value.bz5==null&&value.bz6==null){
				 html2 = "<div class='photo01' style='width:130px;height:120px;margin-top:10px;margin-left:10px;background-color:#FFF;' >"
			  	     + "<img src='<%=path%>/wsh/ShZpq/getFile?imgId="+value.bz1+"' width='120' height='120'  onclick='fangda("+value.bz1+")'>"
			  	     + "</div>";
			 }else{
				 html2 = "";
			 }
			 if(value.yhid=='1'||value.dlm=='admin'||value.tcr==value.wxh){
			 	 html8 = "<a href='javascript:void(0);' onclick='DisplayDelete("+value.id+","+value.tcr+")' style='color:#fff;'><span class='delete'><s class='glyphicon glyphicon-trash'></s></span></a>";
			 }else{
				 html8 =""
			 }
			 if(value.bzcs <=99){
			 	 html9 = value.bzcs;
			 }
			 if(value.bzcs >99){
			 	 html9 = "99+";
			 }
		  	html += "<div class='zhaopian_news'>"
		  	     + html1
		  	     +"<div class='new_photo'>"
		  	     + html2
		  	     + "</div>"
				 + "<div class='new_bottom'>"
				 + html8
				 + "<span><i class='time'></i>"
				 + value.tcsj
				 + "</span><a class='S_txt2' href='javascript:void(0);' onclick='DoTCZhan("+value.id+","+value.tcr+")'>"
				 + "<i class='zan' style='margin-left:20%;float:left;'></i>"
				 + "<span id='t_tucao_zhan_"+value.id+"' style='margin-left:0px;float:left;'>"
				 + html9
				 + "</span>"
				 + "</a></div></div>";
			})
		    $('.LsMore').before(html);
			$('.jzgd').removeAttr("onclick");
			$('.jzgd').attr("onclick","loadMore('"+i+"','"+openId+"')");
			}else{
				html="<div class='-ft' style='margin-top: 20px;'>"
					+"<button class='btn btn-default btn-block btn-lg ng-binding' style='position:relative;bottom:0px;'>已经是最后一页了</button>"
					+"</div>";
					$('#-ft').remove();
				    $('.-ft').before(html);
			}
			},
		   error : function() {
			alert("error");
		}
	  });
} 

//点击“加载更多之后”定位到最后一条数据
function ScrollDiv() { 
	var size=$("#size").val();
		//alert(size);
	if(size!=null&&size!=''){
		
		 if(size<=10){
			//	document.getElementById('lidw1').scrollIntoView(true);
		}else if(size%10==0){
			var num = (parseInt(size/10-1)*10+1);
			//alert(num);
			var id='lidw'+num;
			document.getElementById(id).scrollIntoView(true); 
		}else{
			var num = (parseInt(size/10)*10+1);
			var id='lidw'+num;
			document.getElementById(id).scrollIntoView(true); 
		}
	}
}
	function DoTCZhan(id,openId) {    //祝福
		//var openId = $("#openid").val();
		var openId = openId;
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "<%=path%>/wsh/ShZpq/doTczhan",
						data : {id:id,openId:openId},
						//contentType : "application/json; charset=utf-8",
						dataType : "json",
						success : function(msg) {
							if(msg.success){
								$("#t_tucao_zhan_" + id).text(
										parseInt($("#t_tucao_zhan_" + id)
												.text()) + 1);
								alert("谢谢您的支持^0^");
							}else{
								alert("您已祝福过，请勿重复^0^");
							}
						}
						
					});
				});
	}
	function DoBiaoBai(openId) {   //发布
		var tcnr = $("#t_tucao_text").val();
		var bz1 = $("#fileName1").val();
		var bz2 = $("#fileName2").val();
		var bz3 = $("#fileName3").val();
		var bz4 = $("#fileName4").val();
		var bz5 = $("#fileName5").val();
		var bz6 = $("#fileName6").val();
		var openId = openId;
		var nm = "";
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "<%=path%>/wsh/ShZpq/ZpqFb",
						data : {tcnr:tcnr,openId:openId,nm:"1",bz1:bz1,bz2:bz2,bz3:bz3,bz4:bz4,bz5:bz5,bz6:bz6},
						//contentType : "text/plain",
						dataType : "json",
						success : function(msg) {
							if (msg.success) {
								document.location.reload();
							} else {
								alert(msg.message);
							}
						}
					});

				});
		}
	
	function DoBiaoBai1(openId) {   //发布表白墙
		var tcnr = $("#t_tucao_text").val();
		var bz1 = $("#fileName1").val();
		var bz2 = $("#fileName2").val();
		var bz3 = $("#fileName3").val();
		var bz4 = $("#fileName4").val();
		var bz5 = $("#fileName5").val();
		var bz6 = $("#fileName6").val();
		
		//var openId = $("#openid").val();
		var openId = openId;
		var nm = "";
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : "<%=path%>/wsh/ShZpq/ZpqFb",
						data : {tcnr:tcnr,openId:openId,nm:"0",bz1:bz1,bz2:bz2,bz3:bz3,bz4:bz4,bz5:bz5,bz6:bz6},
						//contentType : "text/plain",
						dataType : "json",
						success : function(msg) {
							if (msg.success) {
								document.location.reload();
							} else {
								alert(msg.message);
							}
						}
					});

				});
		}
	//删除  DisplayDelete
	function DisplayDelete(id,openId) {    //删除
		//var openId = 'ocFFwuHOpoUHVQQGNgcRsMFbYVGg';
		//var openId = $("#openid").val();
				 if(confirm("您确定要删除吗？")){
					$.ajax({
						type : "POST",
						url : 'deleteZpqList',
						data : {id:id,openId:openId},
						dataType : "json",
						success : function(msg) {
							if(msg.success){
								alert("删除成功！");
								 document.location.reload(); 
							}else{
								alert("删除失败！请联系管理员");
							}
						}
						
					});
				}
	}; 
	
	/* 返回首页 */
	function shouye() {
		WeixinJSBridge.call("closeWindow");
	}
</script>
</body>
</html>