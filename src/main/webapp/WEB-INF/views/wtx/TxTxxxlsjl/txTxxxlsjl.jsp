<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String path = request.getContextPath();%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>提醒列表</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/GJSW_style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css335/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript">
var i=0;
function loadMore(page,openId,qh){
	i=page;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wtx/TxTxxxlsjl/getTxlsjlZj";
	var html="";
    $.ajax({
		url :url,
		data : {
			page:i,
			openId:openId,
			ksqh:qh
		},
		type : "post",
		success : function(data) {
			if(data==''||data==null){
				$(".btn-block").text("已经是最后一页了");
			}else{
			var rst = eval(data);
			$.each(rst,function(i,value){
				html  +=
					 "<td><a href='javascript:void(0);' style='color:#333;'>"
                 	  +"<div class='col-sm-12'>"
                 	  +"<button type='submit' class='btn btn-warning btn-xs'>"+value.zdmc+"</button>"
                 	  +"<span class='pull-right'><small class='cl_H'>"+value.txsj+"</small></span><br>"
                 	  +"<div class='biao_h'>"
                 	  +"<small>人员编号："+value.txdx+"</small>"
                 	  +" </div>"
                 	  +"<div class='biao_h'>"
                      +"<small>人员姓名："+value.xm+"</small>"
                      +"</div>"
                      +"<div class='biao_h'>"
                      +"<small>提醒内容："+value.txnr+"</small>"
                      +"</div></div></a> </td> </tr>";
			})
			}
		    $('.ft').append(html);
			$('.btn-block').removeAttr("onclick");
			$('.btn-block').attr("onclick","loadMore('"+i+"','"+openId+"','"+qh+"')");
			},
		   error : function() {
			alert("error");
		}
	  });
}

jQuery(function(){
	$("#span02").click(function() {
		$("#kgtab").show();
		$("#jltab").hide();
		$('#emptyList').hide();
		if($('#size').val()!='0'){$('.-ft').hide();}
	});
	
    $("#span01").click(function() {
    	$("#kgtab").hide();
		$("#jltab").show();
		if($('#size').val()=='0'){$('#emptyList').show();}
		if($('#size').val()!='0'){$('.-ft').show();}
	});
});	

function sfkq(i){
	var openId=$("#openId").val();
	var zt="";
	if($("#"+i+"tx").hasClass("switchOn")){
		zt="1";
	}else{
		zt="0";
	}
	var url="<%=path%>/wtx/TxTxxxlsjl/updateTxkg";
	$.ajax({
		cache : true,
		type : "post",
		url : url,
		data:{
			openId:openId,
			lx:i,
			zt:zt
		},
		async : false,
		error : function(request) {
			location.href="<%=path%>/wtx/TxTxxxlsjl/getTxlsjl?openId="+openId;
		},
		success : function(data) {
		if(data=='1'){
			$("#"+i+"tx").toggleClass("switchOn");
		}else if(data=='0'){
			alert("msg:openId为空!");
		 	}
		}
	});
}
</script>
<style type="text/css">
.switch {
width: 62px;
height: 32px;
background: #e5e5e5;
z-index: 0;
margin: 0;
padding: 0;
appearance: none;
border: none;
cursor: pointer;
position: relative;
border-radius:16px;
-moz-border-radius:16px;
-webkit-border-radius:16px;
}
 
.switch:before {
content: ' ';
position: absolute;
left: 1px;
top: 1px;
width: 60px;
height: 30px;
background: #fff;
z-index: 1;
border-radius:16px;
-moz-border-radius:16px;
-webkit-border-radius:16px;
}
 
.switch:after {
content: ' ';
height: 29px;
width: 29px;
border-radius: 28px;
background: #fff;
position: absolute;
z-index: 2;
top: 1px;
left: 1px;
-webkit-transition-duration: 300ms;
transition-duration: 300ms;
-webkit-box-shadow: 0 2px 5px #999999;
box-shadow: 0 2px 5px #999999;
}
.switchOn,.switchOn:before
{
background: #4cd964 !important;
}
 
.switchOn:after
{
left: 32px !important;
}
.btn-group-lg>.btn, .btn-lg{
padding:6px 1px;
}
.WZY_foot01{
background-color:white;
position: relative;
}
</style>
</head>
<body>
<input style="display:none;" id="size" value="${size}"/>
<input style="display:none;" id="openId" value="${openId}"/>
<div class="iphone">
	<div class="WZY_top01">
    	<div class="row">
          <div class="col-sm-12" style="position:relative;">
            <img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive">
            <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}"><img style="top:18%;right:20px;" class="Home_btn" 
            	src="<%=path%>/resources/img/wzy/FH.png" width="34" height="34"></a>
          </div>
    	</div>
    </div>
    <div class="New_main01" style="bottom:0px;">
    	<div class="container">
            <div class="row">
              <div class="col-sm-12" style=" border-bottom:1px solid #e5e5e5;">
                <img src="<%=path%>/resources/img/wzy/oadbxx.png" width="40" height="40">
                <span id="span01">提醒列表</span>
                
                <img src="<%=path%>/resources/img/wzy/txkg.png" width="40" height="40">
                <span id="span02">提醒开关</span>
              </div>
            <c:if test="${empty list}">
          		<div id="emptyList" align="center"><img src="<%=path%>/resources/images/djt.png" style="width:100%;" /></div>
            </c:if> 
            <table class="table table-striped" style="margin-top:10px;display:block;" id="jltab">
            <c:forEach items="${list}" var="data">
            	<tr>
                    <td><a href="javascript:void(0);" style="color:#333;">
                    	<div class="col-sm-12">
                			<button type="submit" class="btn btn-warning btn-xs">${data.zdmc}</button>
                       		<!--  <small>&nbsp;&nbsp;提醒列表</small> -->
                             <span class="pull-right"><small class="cl_H">${data.txsj}</small></span><br>
                             <div class="biao_h">
                                 <small>人员编号：${data.txdx}</small>
                             </div>
                             <div class="biao_h">
                                 <small>人员姓名：${data.xm}</small>
                             </div>
                             <div class="biao_h">
                                 <small>提醒内容：${data.txnr}</small>
                             </div>
              			</div></a>
                    </td>
                </tr>
               </c:forEach>
               <tr class="ft"></tr>
            </table>
          
           <table class="table table-striped" style="margin-top:10px;display:none" id="kgtab">
              <c:forEach items="${txlist}" var="data" varStatus="status">
              	<c:choose>
              		<c:when test="${data.zdz=='7' }">
              			<tr style="line-height:50px;height:50px;background-color:#f9f9f9;">
             	  			<td><span>${data.zdmc}</span>
             	  				<span class="switch switchOn" id="${data.zdz}tx" onclick="sfkq('${data.zdz}')" style="position:absolute;pull-right;right:10%;"></span>
             	  			</td>
              			</tr>
              		</c:when>
              	<c:otherwise>
              			<tr style="line-height:50px;height:50px;background-color:#f9f9f9;">
             	  			<td><span>${data.zdmc}</span>
             	  				<span class="switch switchOn" id="${data.zdz}tx" onclick="sfkq('${data.zdz}')" style="position:absolute;pull-right;right:10%;"></span>
             	  			</td>
              			</tr>
              	</c:otherwise>
              	</c:choose>
              	</c:forEach>
              </table>
        </div>
    </div>
    <div class="WZY_foot01" style="height:40px;">
    	<%-- <div class="row">
          <div class="col-sm-12">
            <img src="<%=path%>/resources/img/wzy/BQ.png" class="img-responsive">
          </div>
    	</div> --%>
    	<c:if test="${!empty list}">
    	<div class="-ft" style="margin-bottom:0rem;width:100%;">
	      <button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${page}','${openId }','${qh}');" style="position:relative;bottom:0px;">加载更多</button>
         </div> 
        </c:if>
    </div>
</div>
</div>
</html>
