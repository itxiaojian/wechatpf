<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<title>校长信箱</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/My97DatePicker/lang/zh-cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>

<style type="text/css">
.song12 {
	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color: #0000FF;
	LINE-HEIGHT: 20px;
}

.tu {
	background-image: url(../l2n.gif);
	background-repeat: no-repeat;
	background-position: center center;
}

.song12a {
	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color:
}

}
.tu2 {
	background-image: url(../l3.gif);
	background-repeat: no-repeat;
	background-position: center center;
}

.style1 {
	font-weight: bold;
	color: #ff0000;
}

.song13 {
	font-size: 12pt;
	font-family: "宋体", Arial;
	font-weight: bold;
	color:;;;
	LINE-HEIGHT: 20px
}

.song12bule {
	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color:;;;
	LINE-HEIGHT: 20px
}

.zikang {
	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color: #005f9a;
	text-align: left;
	line-height: 20px;
}

BODY {
	margin-left: 0px;
	margin-top: 5px;
	background-color: #eff3e5;
}

.aa06 {
	color: #002d71;
}

A {
	color: #0062a0;
	text-decoration: none;
}

.list01 {
	background-color: expression(( this.sectionRowIndex % 2 == 0)?   "#E6EEFF":
		 "#F5F5F5");
	height: 25px;
}

.pagestyle {
	font-weight: bold;
}

.pagestyle a {
	border: 1px solid #D8EFFA;
	background-color: #EDF7FD;
	padding: 5px 6px;
}

.style5 {
	width: 160px;
}

.style6 {
	width: 8px;
}

.style8 {
	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color:;;;
	width: 120px;
}

.style9 {
	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color:;;;
	LINE-HEIGHT: 20px;
	height: 16px;
}

.style10 {
	width: 671px;
}

.style12 {
	height: 41px;
}

.style13 {
	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color: #0000FF;;;
	LINE-HEIGHT: 20px;
	height: 21px;
}

.Atablefont{
color: #2e3192;
font-size: 12px;
margin: 0;
padding: 0;
outline: medium none;
line-height: 24px;
}

*,ul,li,input,a{ margin:0px; padding:0px; list-style:none; font-family:'微软雅黑'; text-decoration:none; }
.top{ width:100%; height:60px; position:relative; overflow:hidden; border-bottom:1px solid #ccc;}
.top img{ position:absolute; left:50%; margin-left:-950px;}

.main{ width:100%;overflow:hidden;}
.main_box{width:980px; margin-left:auto; margin-right:auto;}
.XZ_wxts{ border:1px solid #f8f400; background-color:#fefcd8; border-radius:3px; margin-top:20px; color:#666; line-height:30px; padding:10px; float:left;}
.biao1{ margin-top:20px;float:left}
.biao1 li{ width:100%;line-height:30px; text-align:center; font-size:14px; float:left}
.biao1 span{ float:left;}
.biao1 a:hover{ color:#ed9340}
.biao2{ margin-top:20px;float:left}
.biao2 li{ width:100%;line-height:30px; text-align:center; font-size:14px; float:left}
.biao2 span{ float:left;}
.biao2 a:hover{ color:#ed9340}
.tab1{ margin-top:20px; line-height:30px; clear:both; float:right}
.tab1 span{ float:right; margin-left:10px;}
.tab2{ margin-top:20px; line-height:30px; clear:both; float:right}
.tab2 span{ float:right; margin-left:10px;}
.fanye{ width:70px; height:30px; background-color:#ecf4fe; text-align:center; line-height:30px;}
.fanye:hover{ background-color:#b2cdec;color:#fff;cursor:pointer; }
.foot{ width:100%;overflow:hidden; background-color:#ccc;margin-top:30px;}
.foot_box{width:980px; height:30px; margin-left:auto; margin-right:auto; color:#fff; line-height:30px; text-align:center; font-size:12px;  }

.banner{ width:100%; height:200px; }
.banner_box{ width:980px; margin-left:auto; margin-right:auto;}
.XZ_search_box{ width:980px; height:60px;float:right;}
.XZ_search_box li:hover{color:#f8983b}
.XZ_search_box li{ float:left; line-height:60px; color:#fff; margin-left:20px; cursor:pointer;}
.XZ_search_box li a{ float:left; line-height:60px; color:#fff; margin-left:20px; cursor:pointer; }
.XZ_search_box input{ border:1px solid #f8983b; border-radius:20px;text-indent:14px;}
.XZ_search_box img{ float:left; margin-top:10px;}
.XZ_gongneng{ float:left; padding:15px;color:#333; border-bottom:#2785dd 1px dashed;}
.XZ_gongneng span{ width:460px; float:left; height:60px; line-height:60px; cursor:pointer;font-size:14px;}
.XZ_gongneng span:hover{ color:#f8983b;}
.GN_icon{ width:60px; height:60px;background-color:#fff; border-radius:50%;float:left; margin-left:150px; }
.XZ_gongneng img { float:left; margin-left:17px; margin-top:15px;}
.XZ_wxts span{ float:left;font-size:14px;}
.biao1 a{ color:#f8983b;}
.biao2 a{ color:#f8983b;}
.duiqi{ text-align:left; box-sizing:border-box;}
.biao_title{ width: 980px;height:25px; border-left:5px solid #f8983b; border-bottom:1px solid #b2cdec; margin-bottom:20px;font-size:16px;}
</style>
<link href="<%=path%>/resources/js/xzxx/style.css"
type="text/css" rel="stylesheet">
</head>
<body style="height: 100%;background-color:#fff">
<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="信件查看"><a
				href="<%=path%>/xzxx/XxXjxxb/getXjmhckList" target="content"
				onfocus="this.blur()"><span>信件查看</span></a></li>
		</ul>
	</div>
	<script language="Javascript" src="<%=path%>/resources/js/xzxx/Calendar.js" type="text/javascript"></script>
	<div id="meizzCalendarLayer" name="meizzCalendarLayer" style="position: absolute; z-index: 9999; width: 144px; height: 193px; display: none">
		<iframe id="meizzCalendarIframe" scrolling="no" frameborder="0"
			height="100%" width="100%"></iframe>
	</div>
	<script src="<%=path%>/resources/js/xzxx/main.js" type="text/javascript"></script>
	<form name="form1" method="post" action="Default.aspx" id="form1">
		<div>
		</div>
		<script type="text/javascript">
			//<![CDATA[
			var theForm = document.forms['form1'];
			if (!theForm) {
				theForm = document.form1;
			}
			function __doPostBack(eventTarget, eventArgument) {
				if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
					theForm.__EVENTTARGET.value = eventTarget;
					theForm.__EVENTARGUMENT.value = eventArgument;
					theForm.submit();
				}
			}
			//]]>
		</script>
		<script type="text/javascript">
		jQuery(function() {
			$('#cx').click(function() {
				 var nr=$('#nrcx').val();
				 var bmbh=$('#bmInput').val();
				 var time =$('#txtDate').val();
				if($("select option:selected").val()!='' && $("select option:selected").val()!='-1' ){
					bmbh=$("select option:selected").val();
				}
				 window.location.href = "<%=path%>/xzxx/XxXjxxb/getXjmhckList?bmbh="+bmbh+"&nr="+nr+"&time="+time;
				});
			for(var i =0;i<$(".td2").size();i++){
			if($(".xjbtTd2"+i).text().length>25){
				$(".xjbtTd2"+i).text($('.xjbtTd2'+i).text().substring(0, 25) + "...");
			
			}
			}
			for(var i =0;i<$(".td1").size();i++){
			if($(".xjbtTd1"+i).text().length>25){
				$(".xjbtTd1"+i).text($('.xjbtTd1'+i).text().substring(0, 25) + "...");
			//var str=$('.xjbtTd1').text().substring(0, 25) + "...";
			//$('.xjbtTd1').text(str);
			}
			}
			
		});
		
			 function getBm(value){
				 $('#bmInput').val(value);
					//window.location.href="";
			 }
			<%--  
			 function wyxx(){
				 var url = "<%=path%>/xzxx/XxXjxxb/sfPj";
					$.ajax({
						cache : true,
						type : "get",
						url : url,
						async : false,
						error : function(request) {
							alert("提交失败，请联系管理员。");
						},
						success : function(data) {
						if(data=='1'){
							window.open("<%=path%>/xzxx/XxXjxxb/wyxx");
						}else if(data=='0'){
							alert("你有未评价信息,请评价后再申请!");
						 	}
						}
					});
			 } --%>
			 
			/*  function getZt(value){
				 $('#ztInput').val(value);
					//window.location.href="";
			 } */
		    
		</script>
		<script
			src="<%=path%>/resources/js/xzxx/WebResource.js"
			type="text/javascript"></script>
		<script
			src="<%=path%>/resources/js/xzxx/ScriptResource.js"
			type="text/javascript"></script>
		<script
			src="<%=path%>/resources/js/xzxx/ScriptResource_002.js"
			type="text/javascript"></script>

<div class="top">
	<img src="<%=path%>/resources/img/wzy/top.png">
</div>
<div class="banner" style="background:url(<%=path%>/resources/img/wzy/banner.png) no-repeat center 100%;">
	<div class="banner_box">
        &nbsp;
    </div>
</div>
<div class="main">
	<div class="main_box">
    	<div class="XZ_gongneng">
            <span>
                <a class="GN_icon" style="background:url(<%=path%>/resources/img/wzy/xiexin.png) center no-repeat"  href="<%=path%>/xzxx/XxXjxxb/wyxx.*">
                </a>&nbsp;&nbsp;我要写信
            </span>
            <span>
                <a class="GN_icon" style="background:url(<%=path%>/resources/img/wzy/guanli.png) center no-repeat" href="<%=path%>/system/login/login.jsp">
                </a>&nbsp;&nbsp;我要管理
            </span>
        </div>

    	<div class="XZ_wxts">
			<span style="color:#f83b10">管理员提示:</span><br><span>1、系统登录的用户名为工号或学号，密码同信息门户的密码(默认为身份证号后8位，有X时请大写）<br> 
2、有关选课、教务等相关问题，可直接咨询教务处，电话：3306290、3305320;有关学费等问题，可直接咨询财务处，电话：3306601;有关校园网络的所有问题，可直接找网络中心，电话：3305046，QQ服务群：543596178;学生宿舍的水电报修维护， 请使用 <a href="http://wx.wxc.edu.cn:8080/wxpt/bx/bxck/cklist">报修系统</a><br>
3、如果不能正常显示，建议使用 <a target="_blank" href="http://www.firefox.com.cn/download/"> 火狐浏览器 </a>进行访问<a target="_blank" href="http://www.firefox.com.cn/download/"> 下载</a><br> 
4、系统帮助手册 <a href="<%=path%>/system/1.pdf" download="校长信箱用户发信操作手册.pdf"> 下载  </a></span>
        </div>
        <div class="XZ_search_box" style="background:url(<%=path%>/resources/img/wzy/search_BG.png) no-repeat center;">
        	<ul>
            	<li>
                	按提问日期查询&nbsp;
                	<span>
                	 <input name="txtDate" value="${time}" id="txtDate" onClick="WdatePicker()" style="width: 170px; position: relative;" type="text"></span>
                </li>
                <li>
                	按信件内容查询&nbsp;
                	<span>
					<input  value="${nr}" type="text"  id="nrcx" style="position:relative;"  />                                  
                    </span>
                    <input style="display:none;" type="text" value="" id="ztInput">
                    <input style="display:none;" type="text" value="" id="bmInput">
                </li>
                <li>
                	按处理部门查询&nbsp;
                	<span>
                    <select   style="position:relative;" name="ddlRDepart" id="ddlRDepart" onChange="getBm(this.value)">
                          <option value=''>请选择---</option>
                          <c:forEach var="bm" items="${bmlist}" varStatus="status">
                             <option <c:if test="${bmbh==bm.bmbh}">selected="selected"</c:if> value="${bm.bmbh}">${bm.bmmc}</option>
                          </c:forEach>
                    </select>
                    </span>
                </li>
                <li>
                <a href="#" id="cx">
					<img src="<%=path%>/resources/img/wzy/search_icon.png">&nbsp;查询
				</a>                
                </li>
            </ul>
        </div>
         <div class="biao1">
        	<ul>
            	<div class="biao_title">
                	&nbsp;&nbsp;已复信件
                </div>
                <li style="background-color:#115ebe; color:#fff; font-size:16px">
                	<span style=" width:50%">主题</span>
                    <span style=" width:10%">回复时间 </span>
                    <span style=" width:20%">受理单位 </span>
                    <span style=" width:10%">状态</span>
                    <span style=" width:10%">操作</span>
                </li>
                <c:if test="${!empty list }">
                <c:forEach var="data" items="${list}" varStatus="status">
					<li <c:if test="${status.count %2==0}">style="background-color:#f1f1f1;"</c:if>>
<%-- 					    <td class="td1" style="display:none;">${status.count}</td> --%>
					    <span class="duiqi" style=" width:50%;">&nbsp;&nbsp;<a>•</a>&nbsp;${fn:substring(data.xjbt, 0, 25)}</span>
                        <span style=" width:10%">${fn:substring(data.clsj, 0, 10)}</span>
                        <span style=" width:20%">${data.slbmmc}</span>
                        <c:if test="${data.ztbh==1}">
					        <span style="width:10%;color:#f83b10">${data.zt}</span>
					    </c:if>
 				        <c:if test="${data.ztbh==2}">
 				            <span style="width:10%;color:green">${data.zt}</span>
 				        </c:if>
					    <c:if test="${data.ztbh==3}">
					        <span style="width:10%;color:#322DF8">${data.zt}</span>
					    </c:if>
				        <c:if test="${data.ztbh==4}">
				           <span style="width:10%;color:#2DEDF8">${data.zt}</span>
				        </c:if>
				        <c:if test="${data.ztbh==5}">
				          <span style="width:10%;color:#10EBF8">${data.zt}</span>
				        </c:if>
				        <c:if test="${data.ztbh==6}">
				           <span style="width:10%;color:#F81084">${data.zt}</span>
				        </c:if>
                        <span style=" width:10%; color:#7cade2"><a href="<%=path%>/xzxx/XxXjxxb/getXjmhckDetail?bxbh=${data.id}">详情查看</a></span>
                     </li>
			    </c:forEach>
			     </c:if>
			      <c:if test="${empty list }">
                    <li>暂无数据</li>
                    </c:if>
			    </ul>
            	<div class="tab1">
            	<c:choose>
					<c:when test="${pagesw > 1}">
					   <a href="<%=path%>/xzxx/XxXjxxb/getXjmhckList?pagesw=${pagesw - 1}&bmbh=${bmbh}&nr=${nr}&time=${time}">上一页</a>
					</c:when>
					<c:otherwise>
				                    上一页
					</c:otherwise>
				</c:choose>
				                    第${pagesw}页
				<c:choose>
					 <c:when test="${pagesw <countw}">
						<a href="<%=path%>/xzxx/XxXjxxb/getXjmhckList?pagesw=${pagesw +1}&bmbh=${bmbh}&nr=${nr}&time=${time}">下一页</a>
					 </c:when>
					 <c:otherwise>
				                          下一页
					 </c:otherwise>
				</c:choose>总共${countw}页
				</div>
            
         </div>
         <div class="biao2">
        	<ul>
            	<div class="biao_title">
                	&nbsp;&nbsp;待复信件
                </div>
            	<li style="background-color:#115ebe; color:#fff; font-size:16px">
                	<span style=" width:50%">主题</span>
                    <span style=" width:10%">写信时间 </span>
                    <span style=" width:20%">受理单位 </span>
                    <span style=" width:10%">状态</span>
                    <span style=" width:10%">操作</span>
                </li>
                <c:if test="${!empty map}">
                <c:forEach var="data" items="${map}" varStatus="status">
					<li <c:if test="${status.count %2==0}">style="background-color:#f1f1f1;"</c:if>>
					    <span class="duiqi" style=" width:50%">&nbsp;&nbsp;<a>•</a>&nbsp;${fn:substring(data.xjbt, 0, 25)}</span>
                        <span style=" width:10%">${fn:substring(data.xxsj, 0, 10)}</span>
                        <span style=" width:20%">${data.slbmmc}</span>
                        <c:if test="${data.ztbh==1}">
					        <span style="width:10%;color:#f83b10">${data.zt}</span>
					    </c:if>
 				        <c:if test="${data.ztbh==2}">
 				            <span style="width:10%;color:green">${data.zt}</span>
 				        </c:if>
					    <c:if test="${data.ztbh==3}">
					        <span style="width:10%;color:#322DF8">${data.zt}</span>
					    </c:if>
				        <c:if test="${data.ztbh==4}">
				           <span style="width:10%;color:#2DEDF8">${data.zt}</span>
				        </c:if>
				        <c:if test="${data.ztbh==5}">
				          <span style="width:10%;color:#10EBF8">${data.zt}</span>
				        </c:if>
				        <c:if test="${data.ztbh==6}">
				           <span style="width:10%;color:#F81084">${data.zt}</span>
				        </c:if>
                        <span style=" width:10%; color:#7cade2"><a href="<%=path%>/xzxx/XxXjxxb/getXjmhckDetail?bxbh=${data.id}">详情查看</a></span>
					</li>
                </c:forEach>
                </c:if>
                <c:if test="${empty map}">
                      <li>暂无数据</li>
                </c:if>
            </ul>
            <div class="tab2">
        	 	<c:choose>
					<c:when test="${pagesy > 1}">
						<a href="<%=path%>/xzxx/XxXjxxb/getXjmhckList?pagesy=${pagesy - 1}&bmbh=${bmbh}&nr=${nr}&time=${time}">上一页</a>
					</c:when>
					<c:otherwise>
				                           上一页
					</c:otherwise>
				    </c:choose>
				                        第${pagesy}页
					<c:choose>
					   <c:when test="${pagesy <county}">
						  <a href="<%=path%>/xzxx/XxXjxxb/getXjmhckList?pagesy=${pagesy +1}&bmbh=${bmbh}&nr=${nr}&time=${time}">下一页</a>
					   </c:when>
					   <c:otherwise>
				                            下一页
					   </c:otherwise>
				   </c:choose>总共${county}页
        	 </div>
       	 </div>
     </div>
</div>
<div class="foot">
	<div class="foot_box">CopyRight © 2016 All Rights Reserved 合肥智圣</div>
</div>

	</form>
</body>
</html>