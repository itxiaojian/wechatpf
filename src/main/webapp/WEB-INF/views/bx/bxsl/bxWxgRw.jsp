<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String path = request.getContextPath();%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name = "format-detection" content = "telephone=no">
<title>任务列表</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/GJSW_style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css335/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript">
function fh(){
	location.href="<%=path%>/wfw/zy/zhuye?openId=${openId}"
}

var i=0;
function loadMore(page,openId){
	i=page;
	i++;
	var url ="<%=path%>/bx/bxrw/getrwZj";
	var html="";
    $.ajax({
		url :url,
		data : {
			page:i,
			openId:openId
		},
		type : "post",
		success : function(data) {
			if(data==''||data==null){
				$(".btn-block").text("已经是最后一页了");
			}else{
			var rst = eval(data);
			$.each(rst,function(i,value){
				if(value.zt=='1'){
					html  +=
						 "<td><a href='<%=path%>/bx/bxsl/toWxgPage?id="+value.bxid+"' style='color:#333;'>"
	                 	  +"<div class='col-sm-12'>"
	                 	  +"<button type='submit' class='btn btn-warning btn-xs'>"+进行中+"</button>"
	                 	  +"<span class='pull-right'><small class='cl_H'>"+value.cjsj+"</small></span><br>"
	                 	  +"<div class='biao_h'>"
	                 	  +"<small>人员编号："+value.wxrbh+"</small>"
	                 	  +" </div>"
	                 	  +"<div class='biao_h'>"
	                      +"<small>人员姓名："+value.xm+"</small>"
	                      +"</div>"
	                      +"<div class='biao_h'>"
	                      +"<small>报修种类："+value.bxzl+"</small>"
	                      +"</div>"
	                      +"<div class='biao_h'>"
	                      +"<small>报修地址："+value.bxdz+"</small>"
	                      +"</div>"
	                      +"<div class='biao_h'>"
	                      +"<small>预约时间："+value.yysj+"</small>"
	                      +"</div>"
	                      +"</div></a></td></tr>";
				}else{
					html  +=
						 "<td><a href='<%=path%>/bx/bxsl/toWxgPage?id="+value.bxid+"' style='color:#333;'>"
	                 	  +"<div class='col-sm-12'>"
	                 	  +"<button type='submit' class='btn btn-warning btn-xs'>"+结束+"</button>"
	                 	  +"<button onclick='sc("+value.bxid+")' style='margin-left:4px;' class='btn error btn-xs'>"
          				  +"删除</button>"
	                 	  +"<span class='pull-right'><small class='cl_H'>"+value.cjsj+"</small></span><br>"
	                 	  +"<div class='biao_h'>"
	                 	  +"<small>人员编号："+value.wxrbh+"</small>"
	                 	  +" </div>"
	                 	  +"<div class='biao_h'>"
	                      +"<small>人员姓名："+value.xm+"</small>"
	                      +"</div>"
	                      +"<div class='biao_h'>"
	                      +"<small>报修种类："+value.bxzl+"</small>"
	                      +"</div>"
	                      +"<div class='biao_h'>"
	                      +"<small>报修地址："+value.bxdz+"</small>"
	                      +"</div>"
	                      +"<div class='biao_h'>"
	                      +"<small>预约时间："+value.yysj+"</small>"
	                      +"</div>"
	                      +"</div></a></td></tr>";
				}
				
			})
			}
		    $('.ft').append(html);
			$('.btn-block').removeAttr("onclick");
			$('.btn-block').attr("onclick","loadMore('"+i+"','"+openId+"')");
			},
		   error : function() {
			alert("error");
		}
	  });
}

function sc(id,openId){
	var url="<%=path%>/bx/bxrw/sc";
		 $.ajax({
				url :url,
				data : {
					id:id
				},
				type : "post",
				success : function(data) {
					location.href="<%=path%>/bx/bxrw/getrw?openId="+$('#openId').val();
					},
				 error : function() {
					alert("error");
				  }
			  });
}

function   reloadOnce(){ 
    var flag= '<%=request.getParameter("flag")%>'; 
    if(flag==null || flag=="" || flag=="null"){ 
            var strURL = window.location; 
            strURL +="&flag=1";   
            document.location.href = strURL; 
    }else{ 
        return  false; 
    } 
  } 
   function   reloadThisPage(){ 
  setTimeout("reloadOnce()",100);//0.001秒后刷新 
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
img{ pointer-events: none; }
a{-webkit-touch-callout:none;}

body {-webkit-overflow-scrolling: touch;overflow-scrolling: touch;}

</style>
</head>
<body onload="reloadOnce()">
<input style="display:none;" id="openId" value="${openId}"/>
<div class="iphone">
	<div class="WZY_top01" onclick="fh()">
    	<div class="row">
          <div class="col-sm-12" style="position:relative;">
            <img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive" >
            <img onclick="fh()"style="margin-top:-23%;margin-left:88%;"src="<%=path%>/resources/img/wzy/FH.png" width="35" height="35" />
          </div>
    	</div>
    </div>
    <div class="New_main01" style="bottom:0px;top:55px;">
    	<div class="container">
            <div class="row">
              <div class="col-sm-12" style=" border-bottom:1px solid #e5e5e5;">
                <img src="<%=path%>/resources/img/wzy/oadbxx.png" width="40" height="40">
                <span id="span01">任务列表</span>
              </div>
        
            <table class="table table-striped" style="margin-top:10px;" id="jltab">
            <c:forEach items="${list}" var="data">
            	<tr>
                    <td>
                    	<div class="col-sm-12">
                			<button type="submit" class="btn btn-warning btn-xs">
                			<c:if test="${data.bxzt=='1'}">进行中</c:if>
                			<c:if test="${data.bxzt=='2'}">结束</c:if>
                			</button>
                			<c:if test="${data.bxzt=='2'}">
                				<button onclick="sc(${data.id},${openId})" style="margin-left:4px;" class="btn error btn-xs">
                				删除
                				</button>
                			</c:if>
                             <span class="pull-right"><small class="cl_H">${data.cjsj}</small></span><br>
                             <a href="<%=path%>/bx/bxsl/toWxgPage?id=${data.bxid}&openId=${openId}" style="color:#333;">
                             <div class="biao_h">
                                 <small>人员编号：${data.wxrbh}</small>
                             </div>
                             <div class="biao_h">
                                 <small>人员姓名：${data.xm}</small>
                             </div>
                             <div class="biao_h">
                                 <small>报修种类：${data.bxzl}</small>
                             </div>
                              <div class="biao_h">
                                 <small>报修地址：${data.bxdz}</small>
                             </div>
                              <div class="biao_h">
                                 <small>预约时间：${data.yysj}</small>
                             </div>
                             </a>
              			</div>
                    </td>
                </tr>
               </c:forEach>
               <tr class="ft"></tr>
            </table>
        </div>
      <c:if test="${empty list }">
          <div align="center"><img src="<%=path%>/resources/images/djt.png" style="width:100%;" /></div>
      </c:if> 
    </div>
    <c:if test="${!empty list }">
    <div class="WZY_foot01" style="height:40px;position:relative;">
    	<%-- <div class="row">
          <div class="col-sm-12">
            <img src="<%=path%>/resources/img/wzy/BQ.png" class="img-responsive">
          </div>
    	</div> --%>
    	<div class="-ft" style="background-color:white;margin-bottom:0rem;width:100%;">
	      <button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${page}','${openId }','${qh}');" 
	      	style="position:relative;bottom:0px;height:39.5px;">加载更多</button>
         </div> 
    </div>
    </c:if>
</div>
</div>
</html>
