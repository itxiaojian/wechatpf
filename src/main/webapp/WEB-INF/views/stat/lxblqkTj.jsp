<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>离校办理情况统计</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/stat/css/sjtj.css">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css335/bootstrap.min.css">
</head>
<style>
@media(max-width:320px){.ziti{font-size:14px; padding:5px 5px};
}
</style>
<script type="text/javascript">
function sctp(){
	//alert(2);
	//var openId = ${openId};
	var yxmc1 = document.getElementById("yxmc");
	var yxmc = yxmc1.value;
	location.href="<%=path%>/stat/echarts/lxbl1?yxmc="+yxmc;
}

function loadMore(page,yxmc){
	i=page;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/stat/echarts/lxblfy";
	var html= "";
    $.ajax({
		url :url,
		data : {
			pages:i,
			//openId:openId,
			yxmc:yxmc
		},
		type : "post",
		success : function(data) {
			var rst = eval(data);
			$.each(rst,function(i,value){
		    html += "<tr><td><div class='col-sm-12'><div class='row' style='box-sizing:border-box;'><div class='col-xs-4' style='width:50%;font-size:12px;padding-right:2px;padding-left:2px;'>"
		    	 +"<span class='glyphicon glyphicon-user' style='color:#F54FCB;float:left;'></span>"	
		         +value.xsyx
		    	 +"</div>"
			     +"<div class='col-xs-4' style='width:25%;font-size:12px;padding-right:2px;padding-left:2px;'>"
               	 +value.tachename
                 +"</div>"
                 +"<div class='col-xs-4' style='width:12.5%;font-size:12px;padding-right:2px;padding-left:2px;'>"
               	 +value.sucess
                 +"</div>"
                 +"<div class='col-xs-4' style='width:12.5%;font-size:12px;padding-right:2px;padding-left:2px;'>"
               	 +value.fail
                 +"</div>"
			     +"</div></div></div></td></tr>";
			})
		    $('.LsMore').before(html);
			$('.zjgd').removeAttr("onclick");
			$('.zjgd').attr("onclick","loadMore('"+i+"','"+yxmc+"')");
			},
		   error : function() {
			alert("error");
		}
	  });
}
</script>
<body>
<div class="iphone">
	<div class="WZY_top01" style="height:51px;width:100%;">
    	<div class="row">
          <div class="col-sm-12" style="position:relative;">
            <img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive">
           <a href="<%=path%>/stat/zy/sjtjzy?openId=${openId}"><img style="top:18%;right:20px;" class="Home_btn" 
            	src="<%=path%>/resources/img/wzy/FH.png" width="34" height="34"></a>
          </div>
    	</div>
    </div>
    <div class="New_main01" style="overflow-x:hidden;overflow-y:auto;">
    	<div class="container-fluid">
            <div class="container" style="margin-bottom:0px;">
                <div class="row">
                  <div class="col-sm-12" style=" border-bottom:1px solid #e5e5e5; height:40px;">
                    <span class="jiangjinCX">&nbsp;学院查询
                    <input type="text" style="width:40%;" id="yxmc">&nbsp;
                    <button class="btn btn-info btn-xs" style="height:50%;" onclick="sctp();">
                     	 查询
                    </button></span>
                  </div>
                </div>
            </div>
            <section style=" display:block;margin-top:-20px;">
            <table class="table table-striped" style=" margin-top:20px; text-align:center;">
             <tr>
                    <td>
                    	<div class="col-sm-12">
                        	<div class="row" style="box-sizing:border-box;">
                              <div class="col-xs-4 ziti"  style="width:50%;">
                              	<span class="glyphicon glyphicon-list-alt"></span>
                       	     	院系
                              </div>
                              <div class="col-xs-4 ziti" style="width:25%;">
                              <span class="glyphicon glyphicon-align-justify"></span>
                       	     	流程
                              </div>
                              <div class="col-xs-4 ziti" style="width:12.5%;">
                                	<span class="glyphicon glyphicon-ok"></span>
                              </div>
                                <div class="col-xs-4 ziti" style="width:12.5%;">
                                	<span class="glyphicon glyphicon-remove"></span>
                              </div>
                            </div>
              			</div>
                    </td>
                </tr>
                <c:forEach var="data" items="${list}" varStatus="status">
                <tr>
                    <td>
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-4" style="width:50%;font-size:12px;padding-right:2px;padding-left:2px;">
                              	<span class="glyphicon glyphicon-user" style="color:#F54FCB;float:left;"></span>
                       	     ${data.xsyx}
                              </div>
                              <div class="col-xs-4" style="width:25%;font-size:12px;padding-right:2px;padding-left:2px;">
                              	${data.tachename}
                              </div>
                              <div class="col-xs-4" style="width:13%;font-size:12px;padding-right:2px;padding-left:2px;">
                                 ${data.sucess}
                              </div>
                              <div class="col-xs-4" style="width:12%;font-size:12px;padding-right:2px;padding-left:2px;">
                                 ${data.fail}
                              </div>
                            </div>
              			</div>
                    </td>
                </tr>
                </c:forEach>
                <tr class="LsMore"></tr>
                <c:if test="${size>9 }">
                <tr>
                <td  style="position:relative;">
                    <a class="zjgd" href="javascript:;" onclick="loadMore('${pages}','${yxmc}');">
					加载更多信息
					</a>
				</td>
				</tr>
				</c:if>
            </table>
            </section>
        </div>
    </div>
    
    <div class="WZY_foot01">
    	<div class="row">
          <div class="col-sm-12">
            <img src="<%=path%>/resources/img/wzy/BQ.png" class="img-responsive">
          </div>
    	</div>
    </div>
</div>
<script>
	
</script>
</html>
