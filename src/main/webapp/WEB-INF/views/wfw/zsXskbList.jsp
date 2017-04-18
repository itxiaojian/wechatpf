<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html class="gecko firefox firefox40 win js">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=10">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/resources/js/jquery/jquery.min.js"
	type="text/javascript"></script>
<title>学生课表</title>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1,">
<link rel="stylesheet" href="<%=path%>/resources/css/wfw/1570a6.css"
	type="text/css" id="cssfile">
<link href="<%=path%>/resources/css/wfw/custom.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/resources/css/wfw/modern.css" rel="stylesheet"
	type="text/css">
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
var appid="${map.appid}";//$('#appid').val();
var nonceStr="${map.nonceStr}";//$('#nonceStr').val();
var timestamp="${map.timestamp}";//$('#timestamp').val();
var signature="${map.signature}";//$('#signature').val();
wx.config({
  debug: false,
  appId: '${map.appid}',
  timestamp: '${map.timestamp}',
  nonceStr: '${map.nonceStr}',
  signature: '${map.signature}',
  jsApiList: [
    'checkJsApi',
    'onMenuShareTimeline',
    'onMenuShareAppMessage',
    'onMenuShareQQ',
    'onMenuShareWeibo',
    'hideMenuItems',
    'showMenuItems',
    'hideAllNonBaseMenuItem',
    'showAllNonBaseMenuItem',
    'translateVoice',
    'startRecord',
    'stopRecord',
    'onRecordEnd',
    'playVoice',
    'pauseVoice',
    'stopVoice',
    'uploadVoice',
    'downloadVoice',
    'chooseImage',
    'previewImage',
    'uploadImage',
    'downloadImage',
    'getNetworkType',
    'openLocation',
    'getLocation',
    'hideOptionMenu',
    'showOptionMenu',
    'closeWindow',
    'scanQRCode',
    'chooseWXPay',
    'openProductSpecificView',
    'addCard',
    'chooseCard',
    'openCard'
  ]
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	wx.hideOptionMenu();
});
</script>
<style type="text/css">
.fanhui{margin-top:12px;width:20px;}
</style>
</head>
<body class="controls-visible"
	style="background-repeat: no-repeat; overflow-y: hidden;" onload="wx.hideOptionMenu();">
	<div id="outter_panel"
		style="position: absolute; overflow-y: auto; top: 0px; bottom: 0px; width: 100%; overflow-x: hidden;">
		<div class="main">
			<div id="real-content">
				<div class="columns-max" id="content-wrapper">
					<div class="lfr-column" id="column-1">
						<div
							id="p_p_id_schoolTimetable_WAR_portal_schoolTimetable_cczydx_INSTANCE_Dhy3_"
							class="portlet-boundary portlet-boundary_schoolTimetable_WAR_portal_schoolTimetable_cczydx_  ">
							<a
								id="p_schoolTimetable_WAR_portal_schoolTimetable_cczydx_INSTANCE_Dhy3"></a>
							<div class="portlet"
								id="portlet-wrapper-schoolTimetable_WAR_portal_schoolTimetable_cczydx_INSTANCE_Dhy3">
								<div class="portlet-topper metrouicss"
									id="portlet-topper-schoolTimetable_WAR_portal_schoolTimetable_cczydx_INSTANCE_Dhy3"
									style="height: auto; background-position: right top; background-repeat: no-repeat;">
									<div onclick=""
										class="icon-arrow-left-311 place-left"
										style="cursor: pointer; font-size: 35px !important; margin-top: -10px; margin-left: 5px; color: #999999; font-weight: 100"></div>
									<div class="portlet-icons"
										id="portlet-small-icon-bar_schoolTimetable_WAR_portal_schoolTimetable_cczydx_INSTANCE_Dhy3">
									</div>
									<div style="clear: both; height: 10px;"></div>
								</div>
								<div
									style="position: absolute; top: 0px; right: 5px; height: 32px; line-height: 30px; width: 50px;">
								</div>
								<div class="portlet-content"
									style="margin-top: 0px;">
									<div class="portlet-content-container" style="">
										<div>


											<div class="metrouicss">
												<div style="height:40px;background-color: #e5e5e5;text-align: center;">
													<span style="font-size: 15px;float: left;color: white;margin-top: 12px;">我的课表</span>
													<a style="float:right;cursor: pointer; font-size: 15px;" href="<%=path%>/wfw/zy/zhuye?openId=${openId}" >
													   <img class="fanhui" src="<%=path%>/resources/img/wzy/fanhui.png">
													</a>
<!-- 													<a style="float: right; cursor: pointer; font-size: 15px; font-weight: bold;" -->
<%-- 														href="<%=path%>/wfw/zy/zhuye?openId=${openId}">返回</a> --%>
												</div>
												<div id="top_message"
													style="clear: both; border: 1px solid #cccccc; padding: 20px;">
												<form action="<%=path%>/wfw/ZsKb/toXskb" method="post">
													<div style="width: 100%; text-align: center; margin-top: 5%;">
													<span style="font-size: 15px; font-weight: bold;width: 100%;">学&nbsp;&nbsp;&nbsp;年
														<select id="xn" name="xn" style="width: 80%;">
														<c:if test="${empty xns}">
															<option value="${xn}">${xn}</option>
														</c:if>
														<c:if test="${!empty xns}">
															<option value="">请选择...</option>
														<c:forEach var="data" items="${xns}" varStatus="obj">
															<option <c:if test="${xn==data.xn}">selected="selected"</c:if> value="${data.xn }">${data.xn }</option>
														</c:forEach>
														</c:if>
														</select></span><br/><br/>
													<span style="font-size: 15px; font-weight: bold;width: 100%;">学&nbsp;&nbsp;&nbsp;期
														<select id="xq" name="xq" style="width: 80%;">
															<option <c:if test="${xq==1}">selected="selected"</c:if> value="1">第1学期</option>
															<option <c:if test="${xq==2}">selected="selected"</c:if> value="2">第2学期</option>
														</select> </span><br/><br/>
													<span style="font-size: 15px; font-weight: bold;width: 100%;">教学周
														<select id="zs" name="zs" style="width: 80%;">
															<option value="">全部</option>
														<c:forEach var="data" items="${zss}" varStatus="obj">
															<option <c:if test="${zs==data.zs}">selected="selected"</c:if> value="${data.zs }">第${data.zs }周</option>
														</c:forEach>
														</select> </span>
													</div>
													<input type="hidden" name="openId" id="openId" value="${openId }">
													<div style="width: 100%; text-align: center; margin-top: 5%;">
														<input style="margin-left: 45px; width: 30%;" value="查询" type="submit">
													</div>
												</form>
												</div>
												<div id="content_id" style="width:100%; margin-top: 20px;">
													<table id="content_table_id" class="bordered stripe"
														style="text-align: center; table-layout: fixed; word-break: break-all; word-wrap: break-word; font-size: 10px; margin-bottom: 5px;">
														<caption id="tableCaption">
															<h2>${xn}学年第${xq}学期学生课表</h2>
<%-- 															<h3>教学周:第${zs}周</h3> --%>
														</caption>
														<thead>
															<tr>
																<th colspan="2" width="18%;">上课时间</th>
																<th>星期一</th>
																<th>星期二</th>
																<th>星期三</th>
																<th>星期四</th>
																<th>星期五</th>
																<th class="lessonweek">星期六</th>
																<th class="lessonweek">星期日</th>
															</tr>
														</thead>
														<tbody id="tbody_id">
															<tr>
																<td rowspan="4">上午</td>
																<td class="courseNumTd">1</td>
																<c:forEach var="data" items="${xskb1}" varStatus="obj">
																	<td <c:if test="${data.jc=='第1节' }">rowspan="2"</c:if>
																		<%-- <c:if test="${data.jc=='第1-2节;第3-4节' }">rowspan="4"</c:if> --%>
																	    style="font-size: 11px; text-align: left;"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_1#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		教室:<span	padding="10px">${data.jsmc}</span>
																		<%-- <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td class="courseNumTd">2</td>
																<c:forEach var="data" items="${xskb2}" varStatus="obj">
																	<td <c:if test="${data.jc=='第1节' }">rowspan="2"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第1-2节' || data.jc=='第1-2节;第3-4节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_2#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		 教室:<span	padding="10px">${data.jsmc}</span>
																		<%-- <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td class="courseNumTd">3</td>
																<c:forEach var="data" items="${xskb3}" varStatus="obj">
																	<td <c:if test="${data.jc=='第3节' }">rowspan="2"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第1-2节;第3-4节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_3#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		    教室:<span padding="10px">${data.jsmc}</span>
																		<%--  <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td class="courseNumTd">4</td>
																<c:forEach var="data" items="${xskb4}" varStatus="obj">
																	<td <c:if test="${data.jc=='第4节' }">rowspan="2"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第3-4节' || data.jc=='第1-2节;第3-4节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_4#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																			 教室:<span padding="10px">${data.jsmc}</span>
																		<%-- <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td rowspan="4">下午</td>
																<td class="courseNumTd">5</td>
																<c:forEach var="data" items="${xskb5}" varStatus="obj">
																	<td <c:if test="${data.jc=='第5节' }">rowspan="2"</c:if>
																		<c:if test="${data.jc=='第5-6节;第7-8节' }">rowspan="4"</c:if>
																	    style="font-size: 11px; text-align: left;"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_5#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																			 教室:<span	padding="10px">${data.jsmc}</span>
																		<%-- <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td class="courseNumTd">6</td>
																<c:forEach var="data" items="${xskb6}" varStatus="obj">
																	<td <c:if test="${data.jc=='第6节' }">rowspan="2"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第5-6节' || data.jc=='第5-6节;第7-8节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_6#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		 教室:<span padding="10px">${data.jsmc}</span>
																		<%-- <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td class="courseNumTd">7</td>
																<c:forEach var="data" items="${xskb7}" varStatus="obj">
																	<td <c:if test="${data.jc=='第7节' }">rowspan="2"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第5-6节;第7-8节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_7#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		 教室:<span padding="10px">${data.jsmc}</span>
																		<%-- <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td class="courseNumTd">8</td>
																<c:forEach var="data" items="${xskb8}" varStatus="obj">
																	<td <c:if test="${data.jc=='第8节' }">rowspan="2"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第7-8节' || data.jc=='第5-6节;第7-8节' || data.jc=='第7-8节;第9-10节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_8#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		教室:<span	padding="10px">${data.jsmc}</span>
																		<%-- <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td rowspan="3">晚上</td>
																<td class="courseNumTd">9</td>
																<c:forEach var="data" items="${xskb9}" varStatus="obj">
																	<td <c:if test="${data.jc=='第9节' }">rowspan="2"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第7-8节;第9-10节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_9#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		 教室:<span padding="10px">${data.jsmc}</span>
																		<%-- <br> 周次:<span	padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td class="courseNumTd">10</td>
																<c:forEach var="data" items="${xskb10}" varStatus="obj">
																	<td <c:if test="${data.jc=='第10节' }">rowspan="2"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第9-10节' || data.jc=='第9-10节;第11节' || data.jc=='第7-8节;第9-10节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_10#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		 教室:<span padding="10px">${data.jsmc}</span>
								                                        <%-- <br> 周次:<span padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
															<tr>
																<td class="courseNumTd">11</td>
																<c:forEach var="data" items="${xskb11}" varStatus="obj">
																	<td <c:if test="${data.jc=='第11节' }">rowspan="3"</c:if>
																	    style="font-size: 11px; text-align: left;<c:if test="${data.jc=='第9-10节;第11节' }">display: none;</c:if>"
																		<c:if test="${data.xq == '星期六' || data.xq == '星期日' }">class="lesson lessonweek" </c:if>
																		<c:if test="${data.xq != '星期六' && data.xq != '星期日' }">class="lesson" </c:if>
																		id="${obj.count }_11#" align="center">
																		<c:if test="${data.kcmc != '' && data.kcmc != null }">
																		<div><span padding="10px" class="tdTileSpan">${data.kcmc}</span><br>
																		 教室:<span	padding="10px">${data.jsmc}</span>
																		<%--  <br> 周次:<span padding="10px">第${data.dyzs}周</span> --%>
																		</div>
																		</c:if></td>
																</c:forEach>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
											<div id="lessonDiv"
												style="padding: 10px; display: none; width: 200px; height: 150px; position: absolute; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.5; z-index: 100; background-color: #cccccc;">
												<span padding="10px"
													style="font-size: 15px; font-weight: bold; text-align: left;"
													id="lessonName"></span><br> 班级:<span padding="10px"
													id="lessonClassName"></span><br> 教室:<span
													padding="10px" id="lessonAddress"></span><br> 周次:<span
													padding="10px" id="lessonWeek"></span>
											</div>
<style>
.topInfoClass {
	float: left;
	margin-left: 40px;
}

.topInfoClass  select {
	margin-left: 15px;
	width: 150px;
}

.topInfoClass span {
	font-size: 15px;
	font-weight: bold;
}

table td {
	padding: 0px !important;
	text-align: center;
}

.tdTileSpan {
	font-size: 12px;
	font-weight: normal;
	text-align: left;
}

.courseNumTd {
	word-wrap: normal !important;
	word-break: keep-all !important;
}

thead    th {
	text-align: center !important;
}
</style>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
					</div>
					</div>
</body>
</html>