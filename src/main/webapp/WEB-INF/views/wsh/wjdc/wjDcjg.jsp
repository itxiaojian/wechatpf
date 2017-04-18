<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
﻿
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>


	<title>问卷调查</title>

	<link href="<%=path%>/resources/js/wsh/wjdc/public.css"
		rel="stylesheet"/>

		<link href="<%=path%>/resources/js/wsh/wjdc/result.css"
			rel="stylesheet"/>
			<link href="<%=path%>/resources/js/wsh/wjdc/jsbox.css"
				rel="stylesheet"/>
				<link href="<%=path%>/resources/js/wsh/wjdc/form.css"
					rel="stylesheet"/>
					<script charset="utf-8" src="<%=path%>/resources/js/wsh/wjdc/v.js"></script>
					<script src="<%=path%>/resources/js/wsh/wjdc/hm.js"></script>
					<script async="" src="<%=path%>/resources/js/wsh/wjdc/analytics.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/wjdc/base_utils.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/wjdc/report_base.js"></script>
<link href="<%=path%>/resources/js/wsh/wjdc/load.css" rel="stylesheet"
	type="text/css"/>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/wjdc/load.js"></script>
<link href="<%=path%>/resources/js/wsh/wjdc/hcheckbox.css"
	rel="stylesheet"/>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/wjdc/jquery-hcheckbox.js"></script>
<!--气泡弹框-->
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/js/wsh/wjdc/JsBubble.css"/>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/wjdc/JsBubble.js"></script>
<!--日期组件-->
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/js/wsh/wjdc/zebra_datepicker_metallic.css"/>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/wjdc/zebra_datepicker.js"></script>


<script src="<%=path%>/resources/js/wsh/wjdc/highcharts.js"></script>
<script src="<%=path%>/resources/js/wsh/wjdc/highcharts-more.js"></script>
<script src="<%=path%>/resources/js/wsh/wjdc/exporting.src.js"></script>
<script src="<%=path%>/resources/js/wsh/wjdc/basic_chart.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/wjdc/jsbox.js"></script>
<script src="<%=path%>/resources/js/wsh/wjdc/modernizr.custom.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/wsh/wjdc/tools.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></script>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<link href="<%=path%>/resources/js/wsh/wjdc/jiathis_share.css"
	rel="stylesheet" type="text/css"></link>
	<%-- <link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/> --%>
	<%-- <link href="<%=path%>/resources/css/base.css" rel="stylesheet" /> --%>
	<%-- <link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>
	<iframe frameborder="0"
		style="position: absolute; display: none; opacity: 0;"></iframe>
	<div class="jiathis_style"
		style="position: absolute; z-index: 1000000000; display: none; top: 50%; left: 50%; overflow: auto;"></div>
	<div class="jiathis_style"
		style="position: absolute; z-index: 1000000000; display: none; overflow: auto;"></div>
	<iframe frameborder="0" src="" style="display: none;"></iframe>
	<div class="zon_v2 clear">

		<!--top end-->

		<div class="wjContent" style="padding: 0 0 0 0px;">
			<div class="content Tj">
				<div class="wjhead">
					<div class="wjtitle">
						<div>
							<span class="wjname" title="">${map.title }</span>

							<div class="cp_nav"></div>
						</div>
					</div>
					<div id="myTabContent" class="tab2-content">
						<div class="" id="design">
							<span style="padding-left: 20px;">问卷创建时间：${map.createtime }</span>
							<ul class="fr">
							<c:if test="${lx=='1' }">
								<li><a href="<%=path%>/wsh/WjObject/toWjDcjg?id=${oid}&lx=0" class="baiscc active"><i></i>柱状图表</a></li>
							</c:if>
							<c:if test="${lx=='0' }">
								<li><a href="<%=path%>/wsh/WjObject/toWjDcjg?id=${oid}&lx=1" class="baiscc active"><i></i>饼状图表</a></li>
							</c:if>
							</ul>
						</div>
					</div>
				</div>
				<div class="wjbbody">

					<div class="maxtop btns title" id="loader_div_1" width="100%"
						height="100%"
						style="display: none; text-align: center; background: #000; filter: alpha(opacity = 70); opacity: 0.7;">
						<img src="<%=path%>/resources/js/wsh/wjdc/loader.gif" alt=""/>
						<br/> 下载中...
					</div>
					<div class="WJcontent" style="margin: 0 auto;">
						<div class="charthead">
							<div></div>
						</div>


				<c:forEach var="data" items="${quesList}" varStatus="obj">
					<c:if test="${data.qtype eq '0' || data.qtype eq '2'}">
						<div id="question_content_${data.seq}" class="ques mtop">
							<div class="ansimg mtop">
								<div class="anshead">
									<span class="spanlh fL" title=""> ${data.content} </span>
								</div>
								<div class="showimg">
									<input type="hidden"
										id="record_chart_type_${data.seq}" value="2"></input>
									<div class="chartdiv">
										<div id="chart_div_${data.seq}">

											<div style="display: block; height: 305px;"
												id="container_${data.seq}"
												class="svg-container">
											<c:if test="${lx=='1' }">
												<iframe frameborder="0" src="<%=path%>/wsh/WjObject/toWjDcjgBt?oid=${map.oid }&qseq=${data.seq}" style="width: 850px; height: 350px;"></iframe>
											</c:if>
											<c:if test="${lx=='0' }">
												<iframe frameborder="0" src="<%=path%>/wsh/WjObject/toWjDcjgZzt?oid=${map.oid }&qseq=${data.seq}" style="width: 850px; height: 350px;"></iframe>
											</c:if>
											</div>

										</div>
										<br/> <br/>
										<div id="data_container_${data.seq}"
											class="chart_table"
											style="margin-top: 2px; display: block; overflow-x: auto">

										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>

					<c:if test="${data.qtype eq '1'}">
						<div id="question_content_${data.seq}" class="ques mtop">
							<div class="ansimg mtop">
								<div class="anshead">
									<span class="spanlh fL" title="${data.content}"> ${data.content} </span>
								</div>
								<div class="showimg">
									<input type="hidden"
										id="record_chart_type_${data.seq}" value="3"></input>
									<div class="chartdiv">
										<div id="chart_div_${data.seq}">

											<div style="display: block; height: 305px;"
												id="container_${data.seq}"
												class="svg-container">
											<c:if test="${lx=='1' }">
												<iframe frameborder="0" src="<%=path%>/wsh/WjObject/toWjDcjgBt?oid=${map.oid }&qseq=${data.seq}" style="width: 850px; height: 350px;"></iframe>
											</c:if>
											<c:if test="${lx=='0' }">
												<iframe frameborder="0" src="<%=path%>/wsh/WjObject/toWjDcjgZzt?oid=${map.oid }&qseq=${data.seq}" style="width: 850px; height: 350px;"></iframe>
											</c:if>
												</div>
										</div>
										<br/> <br/>
										<div id="data_container_${data.seq}"
											class="chart_table"
											style="margin-top: 2px; display: block; overflow-x: auto">

										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>


					</div>
					<div style="clear: both;"></div>
					<!--弹出框-->
					<link href="<%=path%>/resources/js/wsh/wjdc/jsbox.css"
						rel="stylesheet" type="text/css"/>
					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/wjdc/jsbox.js"></script>
					<!-- 帮助提示 -->
					<link type="text/css" rel="stylesheet"
						href="<%=path%>/resources/js/wsh/wjdc/jsTip.css"/>
					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/wjdc/jsTip.js"></script>
					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/wjdc/ZeroClipboard.js"></script>

					<%-- <script type="text/javascript" src="<%=path%>/resources/js/wsh/wjdc/jia.js" charset="utf-8"></script> --%>
					<script type="text/javascript"
						src="<%=path%>/resources/js/wsh/wjdc/plugin.client.js"
						charset="utf-8"></script>

				</div>
			</div>
		</div>
		<!--wjContent end-->

	</div>

	<script type="text/javascript"
		src="<%=path%>/resources/js/wsh/wjdc/third_party_stat.js"></script>


</body>
</html>