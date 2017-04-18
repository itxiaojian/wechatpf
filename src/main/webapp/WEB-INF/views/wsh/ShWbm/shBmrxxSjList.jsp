<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3"></meta>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/heeh.css" />

	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<%--   	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script> --%>
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
	function Sc(id){
		var ret = window.confirm("确定要删除这项报名吗？");
		if (!ret) {
			return false;
		}
		var url = '<%=path%>/wsh/ShWbm/delete';
		$.ajax({
			url: url,
			type : 'post',
			dataType : 'json',
			data:{id:id},
			success: function(data){
				if(data == '1'){
						location.href ="<%=path%>/wsh/ShWbm/toWbmList";
				}else{
					alert("提交失败");
				}
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
	}
	function toView(id,time,openId){
		var endTimes   =  time.substring(0,10).split('-');
		var endTime=new Date(endTimes[0],(endTimes[1]-1),endTimes[2],time.substring(11,13),time.substring(14,16),0);
		var today=new Date();               //获取当前日期  
		var year=today.getFullYear();       //获取当前年份  
		var month=today.getMonth();         //获取月份  
		var day=today.getDate();            //获取日期  
		var hour=today.getHours();          //获取小时  
		var min=today.getMinutes();         //获取分钟  
		var second=today.getSeconds();      //获取秒
		var beginTime=new Date(year,month,day,hour,min,second);
		
		var a =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;
		if(a>0){
			location.href ='<%=path%>/wsh/ShWbm/toWbmView?id='+id+'&openId='+openId;
		}else{
			alert("报名活动已结束！");
		}
	}
	function query(){
		$('#Form1').submit();
	}
	</script>
	<link href="<%=path%>/resources/css/tab-import.css" rel="stylesheet" style="text/css" />
	<link href="<%=path%>/resources/css/css.css" rel="stylesheet" style="text/css" />
	<title>报名信息</title>
	<style>
		
	</style>
  </head>
 
  <body style="overflow: auto;">
  <div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="报名信息">
	<a href="#" target="content" onfocus="this.blur()"><span>报名信息</span></a>
	</li>
</ul>
</div>
   <form action="<%=path%>/wsh/ShWbm/toWbmSjList" method="POST" id="Form1">  
<div class="bg1">
	<div class="style1">
		<div class="wwx_f_l" style="position: relative;">
		<a class="font1" style="color:white;" href="#">报名活动</a>
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
</div>
   <div class="tab-container">
				<!--我的工资-->
				<input type="hidden" id="date" name="date" value="${time}">
				<input type="hidden" name="openId" id="openId" value="${openId }"> 
					<table border="0" align="center" cellpadding="0" style="margin-top:80px;margin-bottom: 10px;width:100%; "
						cellspacing="0" class="content02">
						<c:if test="${empty list}">
								
										<div class="text" style="margin-top:80px;margin-bottom: 10px;width:100%; ">
											<p>暂无报名人...</p>
										</div>
								
								
							</c:if>  
						<c:if test="${!empty list}">
						<tr class="bgcolor03">
						    <td width="20%" align="center" style="font-weight:bold;height:1%;">序号</td>
						    <td width="80%" align="center" style="font-weight:bold;height:1%;">报名人微信昵称</td>
					 	</tr>
					  </c:if>
							<c:forEach var="data" items="${list}" varStatus="obj">  <!-- varStatus="status" -->
							
						<!-- 判断偶数行 -->
						<c:if test="${obj.count%2 == '0'}">
							<tr class="bgcolor01">
							    <td align="center">${obj.count}</td>
							    <td class="config-value-0">${data.nickname }</td>
						  </tr>
					 </c:if>
					 <!-- 判断奇数行 -->
					<c:if test="${obj.count%2 != '0'}">
					 	<tr class="bgcolor02">
							    <td align="center">${obj.count}</td>
							    <td class="config-value-0">${data.nickname }</td>
					  </tr>
					</c:if>
					  
					  
			 </c:forEach>
			 <c:if test="${!empty list}">
			 		<tr>
			 			<td align="center" colspan="4">
			 				<c:choose>
								<c:when test="${pages > 1}">
									<a href="<%=path%>/wsh/ShWbm/toBmrsSj?pages=${pages - 1}">上一页</a>
								</c:when>
								<c:otherwise>
									上一页
								</c:otherwise>
							</c:choose>
							第${pages}页
							<c:choose>
								<c:when test="${pages < count}">
									<a href="<%=path%>/wsh/ShWbm/toBmrsSj?pages=${pages + 1}">下一页</a>
								</c:when>
								<c:otherwise>
								下一页
							</c:otherwise>
							</c:choose>
							总共${count}页
			 			</td>
			 		</tr>
			 	</c:if>
					</table> </div>
		</form>		
  </body>
</html>
