<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=3,user-scalable=yes;">
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/heeh.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/bootstrap.min.css" /> 

	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/mobiscroll/mobiscroll.custom-2.13.2.min.css">  
 <!--时间控件mobiscroll-->  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>  
  
  
   <link href="<%=path%>/resources/mobiscroll/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />  
   <link href="<%=path%>/resources/mobiscroll/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>

	<script type="text/javascript">
	function preview_onClick2(id,status,openId){
		   var topicCode = id;
		   if(status==1 || status==2)
		   {
			   preview(topicCode,openId);
		   }
		   else
		   {
			alert("该问卷尚未发布，不能参与!");
		   }
	}
	function preview(id,openId){
		window.self.location="<%=path%>/wsh/WjQuestion/toDcwj?id="+id+"&openId="+openId;
	}
	function query(){
		$('#Form1').submit();
	}
	function preview_onClick3(id,openId){
		window.self.location="<%=path%>/wsh/WjObject/toWjDcjgSj?id="+id+"&openId="+openId;
	}
	</script>
	<link href="<%=path%>/resources/css/tab-import.css" rel="stylesheet" style="text/css" />
	<link href="<%=path%>/resources/css/css.css" rel="stylesheet" style="text/css" />
	<title>问卷调查</title>
 <style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:50px; background-color:#e5e5e5; position:absolute; top:10%; left:0;overflow:hidden; font-size:13px;
}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.span_hz{ float:left; font-size:11px;margin-top:0.1%;}
/* .top_01 img{ margin-top:15%; display:block; float:left;} */
.fanhui{margin-top:3%;float:right;margin-right:4%;width:5%;}
.span_input{line-height: normal; }
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ width:32%; height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_01 img{ float:left; margin-top:10%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}

.logo {
    height: 4.9rem;
    width: 100%;
    background-color: #000000;
    position: absolute;
    top: 0rem;
    left: 0rem;
}
.search_box{ width:100%; height:50px;background-color:#dbfeea;}
.search_box input{ height:28px; width:220px; color:#999; margin-left:10px; padding-left:5px; border-radius:5px; border:#00923f 1px solid;}
.search_msg{ text-align:center;}
.tab-container table tr.bgcolor03 td {background: #26b865;}
.tab-container table tr.bgcolor02 td {background: #ebfdf3;}
</style>
  </head>
 
<body style="overflow: auto;">
<div style="display:none;">
  <ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="调查问卷信息">
	<a href="#" target="content" onfocus="this.blur()"><span>微问卷信息</span></a>
	</li>
  </ul>
</div>
<div class="logo">
        <img src="<%=path%>/resources/img/wzy/logo.png" style="width:100%;height:4.9rem;">
        <div class="anniu" style="left:85%;">
			<a href="<%=path%>/wsh/zy/zhuye?openId=${openId}">
			   <img  
			    src="<%=path%>/resources/img/wzy/FH.png" style="width:70%;"/>
			    </a>
			</div>
</div>
   <form action="<%=path%>/wsh/WjObject/toDcwj" method="POST" id="Form1">  
<%-- <div class="bg1">
	<div class="style1">
		<div class="wwx_f_l" style="position: relative;">
		<a class="font1" style="color:white;" href="#">问卷查询</a>
		<span><input id="bt" name="bt" onblur="query();"
				style="border:1px solid #dddddd;margin-left: 10px; margin-bottom: 4px;"/></span>
		</div>
				<div class="wwx_clear"></div>
				<div class="anniu" style="left:90%;top:15%;" >
				<a style="float:right;width:40px;height:50px;" href="<%=path%>/wsh/zy/zhuye?openId=${openId}" >
			   <img style="width:70%" src="<%=path%>/resources/img/wfwzy.png" >
			   </a>
			      </div>
	</div>
</div> --%>

    
<div class="top_01">
       <div class="search_box">
        	<div class="search_msg">
                <span class="glyphicon glyphicon-stats" style="color:#00923f; margin-left:10px; margin-top:18px;"></span>
                <input type="text" id="bt" name="bt" onblur="query();" placeholder="请输入查询的问卷...">
                <span class="glyphicon glyphicon-search" style="color:#00923f; margin-left:10px;  margin-top:18px;"></span>
            </div>
        </div>
<!--     	<span class="span_hz" style="width:60%; padding-left:5%"> -->
<!--         	问卷查询:&nbsp;&nbsp; -->
<!--               <input class="span_input" id="bt" name="bt" onblur="query();" -->
<!-- 		       style="border:1px solid #dddddd;width: 50%;" />  -->
<!--         </span> -->
<!--         <span style="width:10%"> -->
<%--         	<a href="<%=path%>/wsh/zy/zhuye?openId=${openId}"> --%>
<%--         		<img class="fanhui" src="<%=path%>/resources/img/wzy/fanhui.png"> --%>
<!--             </a> -->
<!--         </span> -->
</div>

   <div class="tab-container">
				<!--我的工资-->
				<input type="hidden" id="date" name="date" value="${time}">
				<input type="hidden" name="openId" id="openId" value="${openId }"> 
					<table border="0" align="center" cellpadding="0" style="margin-top:130px;margin-bottom: 10px;width:100%; "
						cellspacing="0" class="content02">
						<c:if test="${empty list}">
								
										<div class="text" style="margin-top:130px;margin-bottom: 10px;width:100%; ">
											<p>问卷信息暂无...</p>
										</div>
								
								
						</c:if>  
						<c:if test="${!empty list}">
						<tr class="bgcolor03">
						    <td width="15%" align="center" style="font-weight:bold;height:1%;">序号</td>
						    <td width="30%" align="center" style="font-weight:bold;height:1%;">标题</td>
						    <td width="35%" align="center" style="font-weight:bold;height:1%;">更新时间</td>
						    <td width="20%" align="center" style="font-weight:bold;height:1%;">操作</td>
					 	</tr>
					  
						<c:forEach var="data" items="${list}" varStatus="obj"> 
							
						<!-- 判断偶数行 -->
						<c:if test="${obj.count%2 == '0'}">
							<tr class="bgcolor01">
							    <td align="center">${obj.count}</td>
							    <td align="center">
							    	<c:if test="${data.state==2 }"><span style="color: #fff;padding: 2px 5px;background-color: #b2b2b2;">已结束</span></c:if>
									<c:if test="${data.state==1 }"><span style="color: #fff;padding: 2px 5px;background-color: #fd9e0d;">进行中</span></c:if>
									<c:if test="${data.state==0 }"><span style="color: #fff;padding: 2px 5px;background-color: #fd9e0d;">未开始</span></c:if>
							    	${data.title}
							    </td>
							    <td align="center">${data.createtime}</td>
							    <td align="center">
							    <c:if test="${data.sfcy eq 0 }">
							    	<a href="javascript:;" onclick="preview_onClick2('${data.oid}','${data.state}','${openId }');">点击进入</a>
							    </c:if>
							    <c:if test="${data.sfcy eq 1 }">
							    	<a href="javascript:;" onclick="preview_onClick3('${data.oid}','${openId }');">查看结果</a>
							    </c:if>
							    </td>
						  </tr>
					 </c:if>
					 <!-- 判断奇数行 -->
					<c:if test="${obj.count%2 != '0'}">
					 	<tr class="bgcolor02">
							    <td align="center">${obj.count}</td>
							    <td align="center">
							    	<c:if test="${data.state==2 }"><span style="color: #fff;padding: 2px 5px;background-color: #b2b2b2;">已结束</span></c:if>
									<c:if test="${data.state==1 }"><span style="color: #fff;padding: 2px 5px;background-color: #fd9e0d;">进行中</span></c:if>
									<c:if test="${data.state==0 }"><span style="color: #fff;padding: 2px 5px;background-color: #fd9e0d;">未开始</span></c:if>
							    	${data.title}
							    </td>
							    <td align="center">${data.createtime}</td>
							    <td align="center">
							    <c:if test="${data.sfcy eq 0 }">
							    	<a href="javascript:;" onclick="preview_onClick2('${data.oid}','${data.state}','${openId }');">点击进入</a>
							    </c:if>
							    <c:if test="${data.sfcy eq 1 }">
							    	<a href="javascript:;" onclick="preview_onClick3('${data.oid}','${openId }');">查看结果</a>
							    </c:if>
							    </td>
					  </tr>
					</c:if>
					  
					  
			 </c:forEach>
			 </c:if>
					</table> </div>
		</form>		
  </body>
</html>
