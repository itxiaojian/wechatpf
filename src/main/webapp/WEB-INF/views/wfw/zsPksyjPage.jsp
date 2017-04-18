<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
  
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3"></meta>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.min.css"/>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
   <script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function query(){
		$("#myForm").submit();
	}
</script>
<script type="text/javascript">
// var i=0;
// function loadMore(page,openId){
// 	i=page;
// 	i++;
<%-- 	location.href ="<%=path%>/wfw/ZsPksxx/toPksyjList?pages="+i+"&openId="+openId; --%>
// }

function loadMore(page,openId,tj){
	i=page;
	i++;
	var size = (i-1)*10;
	var url ="<%=path%>/wfw/ZsPksxx/pkjzgd";
	var html= "";
    $.ajax({
		url :url,
		data : {
			pages:i,
			//openId:openId,
			tj:tj
		},
		type : "post",
		success : function(data) {
			if(data.length>0){
			var rst = eval(data);
			$.each(rst,function(i,value){
		    html += "<tr><td><a href='<%=path %>/wfw/ZsPksxx/toPksxfxx?id="+value.id+"&openId="+openId+"'>"
		         +"<div class='col-sm-12'><div class='row'><div class='col-xs-4'>"
		    	 +"<span class='glyphicon glyphicon-user' style='color:#3D9DF7'></span>&nbsp;"	
		         +value.xm
		    	 +"</div>"
			     +"<div class='col-xs-4'>"
               	 +value.bj
                 +"</div>"
                 +"<div class='col-xs-4'>"
                 +"<span><button type='button' class='btn btn-info btn-xs'>"
               	 +value.pksdj
                 +"</button></span></div></div></div></a></td></tr>";
			})
		    $('.LsMore').before(html);
			$('.zjgd').removeAttr("onclick");
			$('.zjgd').attr("onclick","loadMore('"+i+"','"+openId+"','"+tj+"')");
			}else{
				html="<tr class='ft'>"
				    +"<td style='position:relative;'>"
				    +"<a href='javascript:;'>已经是最后一页了</a>"
					+"</td><tr>";
					$('#ft').remove();
				    $('.ft').before(html);
				    
			}
			},
		   error : function() {
			alert("error");
		}
	  });
}

</script>
<style>
body {
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
}
*,select,option,body,ul,li,a,input{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
body{ margin:0; padding:0px; overflow-x:hidden}
.Home_btn{ position:absolute; right:8%; top:20%;}
.WZY_top01{ width:100%; height:60px; position:fixed; left:0px; top:0px;overflow:hidden;}
.New_main01{ width:100%;position:absolute; top:60px; left:0px; bottom:40px;overflow:auto;}
.WZY_foot01{width:100%;  position:absolute; left:0px; bottom:0px; background-color:#000000;overflow:hidden;}

@media(max-width:320px){.ziti{font-size:14px; padding:5px 5px};
</style>
<title>贫困生预警</title>
</head>

<body id="cardunion" class="mode_webapp2" style="background-color: white; padding-bottom: 0;" >
<input type="hidden" name="tj" id="tj" value="${tj }">
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="贫困生预警">
	<a href="#" target="content" onfocus="this.blur()"><span>贫困生预警</span></a>
	</li>
</ul>
</div>

<div class="iphone">
	<div class="WZY_top01">
    	<div class="row">
          <div class="col-sm-12" style="position:relative;">
            <img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive">
            <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}" >
            <img class="Home_btn" src="<%=path%>/resources/img/wzy/FH.png" width="40" height="40">
            </a>
          </div>
    	</div>
    </div>
    <div class="New_main01">
        <div class="container-fluid">
    	    <div class="container" style="text-align:center;">
                <div class="row">
                  <div class="col-sm-12" style=" border-bottom:1px solid #e5e5e5; height:40px; padding-top:8px;">
                    <span>班级查询&nbsp;&nbsp;&nbsp;
                    <form action="<%=path%>/wfw/ZsPksxx/toPksyjList" id="myForm" class="wwx_f_a"  method="post" style="display: inline;">
                       <input class="jiangjinCX" type="text" name="code" id="code" style="width:40%">&nbsp;&nbsp;&nbsp;
                       <input type="hidden" name="openId" id="openId" value="${openId }">
                       <input type="hidden" name="tj" id="tj" value="${tj }">
                    </form>
                    <button class="btn btn-info btn-xs" onclick="query();">
                     <span class="glyphicon glyphicon-search"></span> 查询
                    </button></span>
                  </div>
                </div>
            </div>
            <section style=" display:block">
            <table id="table" class="table table-striped" style=" margin-top:20px; text-align:center;">
             <tr>
                    <td>
                    	<div class="col-sm-12" >
                        	<div class="row">
                              <div class="col-xs-4 ziti">
                              	<span class="glyphicon glyphicon-list-alt"></span>
                       	     &nbsp;姓名
                              </div>
                              <div class="col-xs-4 ziti">
                              <span class="glyphicon glyphicon-align-justify"></span>
                       	     &nbsp;班级
                              </div>
                              <div class="col-xs-4 ziti">
                              	<span class="">
                                	<span class="glyphicon glyphicon-lamp"></span>
                       	     &nbsp;程度
                                </span>
                              </div>
                            </div>
              			</div>
                    </td>
                </tr>
                <c:forEach var="yjlist" items="${yjlist }">
                <tr>
                    <td>
                    	<a href="<%=path %>/wfw/ZsPksxx/toPksxfxx?id=${yjlist.id}&openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-4">
                              	<span class="glyphicon glyphicon-user" style="color:#3D9DF7"></span>
                       	     &nbsp;${yjlist.xm }
                              </div>
                              <div class="col-xs-4">
                              	${yjlist.bj }
                              </div>
                              <div class="col-xs-4">
                              	<span>
                                	<button type="button" class="btn btn-info btn-xs">${yjlist.pksdj }</button>
                                </span>
                              </div>
                            </div>
              			</div>
              			</a>
                    </td>
                </tr>
                </c:forEach>
                <tr class="LsMore"></tr>
                <c:choose>  
                   <c:when test="${empty yjlist}">
                   <tr>
                     <td>
                       <div class="text">
<!-- 			<p>选修课信息暂无...</p> -->
			            <img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
		               </div> 
		              </td> 
		             </tr>
                   </c:when>  
                   <c:otherwise>
                      <tr class="ft">
                         <td  style="position:relative;">
                             <a class="zjgd" id="ft" href="javascript:;" onclick="loadMore('${pages}','${openId }','${tj}');">
					                                  更多贫困生信息
					         </a>
				         </td>
				      </tr>
				  </c:otherwise>
				</c:choose>
            </table>
            </section>
   </div>
</div>
    
</body>
<script type="text/javascript">var str="";</script>
<script type="text/javascript" src="<%=path%>/resources/js/wysy.js"></script>
</html>