<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/security.tld" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String path = request.getContextPath();%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>待办事项</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/GJSW_style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css335/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript">
	var i=0;
	function loadMore(page,openId){
		i=page;
		i++;
		var url ="<%=path%>/wtx/oadbsx/getaOaDataByIdZj";
		var html="";
	    $.ajax({
			url :url,
			data : {
				page:i,
				openId:openId
			},
			type : "post",
			success : function(data) {
				var rst = eval(data);
				$.each(rst,function(i,value){
					html  +=  
                   "<tr><td><a href='javascript:void(0);' style='color:#333;'>"
                   +"<div class='col-sm-12'>"
                   +"<button type='submit' class='btn btn-warning btn-xs'>未阅读</button>"
                   +"<small>&nbsp;&nbsp;信息列表</small>"
				   +"<span class='pull-right'><small class='cl_H'>"+value.time+"</small></span><br>"
				   +"<div class='biao_h'>"
				   +"<small>事项编号："+value.id+"</small>"
                   +"</div>"
                   +"<div class='biao_h'>"
                   +"<small>人员姓名："+value.xm+"</small>"
                   +"</div>"
                   +"<div class='biao_h'>"
                   +"<small>事项标题："+value.title+"</small>"
                   +"</div></div></a></td></tr>";
				})
			    $('.trnr').before(html);
				$('.btn-block').removeAttr("onclick");
				$('.btn-block').attr("onclick","loadMore('"+i+"','"+openId+"')");
				},
			   error : function() {
				alert("error");
			}
		  });
	}
</script>
</head>
<body>
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
    <div class="New_main01" style="bottom:40px;">
    	<div class="container">
            <div class="row">
              <div class="col-sm-12" style=" border-bottom:1px solid #e5e5e5;">
                <img src="<%=path%>/resources/img/wzy/oadbxx.png" width="40" height="40">
                <span>待办事项列表<%-- <span style="color:red">(${totalnum})</span> --%></span>
              </div>
            </div>
            <table class="table table-striped" style="margin-top:10px;">
            <c:forEach items="${list}" var="data">
            	<tr>
                    <td><a href="javascript:void(0);" style="color:#333;">
                    	<div class="col-sm-12">
                			 <button type="submit" class="btn btn-warning btn-xs">未阅读</button>
                       		 <small>&nbsp;&nbsp;信息列表</small>
                             <span class="pull-right"><small class="cl_H">${data.time}</small></span><br>
                             <div class="biao_h">
                                 <small>事项编号：${data.id}</small>
                             </div>
                             <div class="biao_h">
                                 <small>人员姓名：${data.xm}</small>
                             </div>
                             <div class="biao_h">
                                 <small>事项标题：${data.title}</small>
                             </div>
              			</div></a>
                    </td>
                </tr>
                <tr class="trnr"></tr>
               </c:forEach>
            </table>
            <c:if test="${empty list}">
            <div align="center"><img src="<%=path%>/resources/images/djt.png" style="width:100%;" /></div>
            </c:if>
        </div>
    </div>
    <div class="WZY_foot01" style="height:40px;">
    	<%-- <div class="row">
          <div class="col-sm-12">
            <img src="<%=path%>/resources/img/wzy/BQ.png" class="img-responsive">
          </div>
    	</div> --%>
    	<div class="-ft" style="margin-bottom:0rem;width:100%;">
	      <button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${page}','${openId }');" style="position:relative;bottom:0px;">加载更多</button>
         </div> 
    </div>
</div>
</html>
