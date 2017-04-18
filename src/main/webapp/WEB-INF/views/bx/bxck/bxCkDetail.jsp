<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>报修信息查看</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script language="Javascript"
	src="<%=path%>/resources/js/xzxx/Calendar.js"
	type="text/javascript"></script>
<script type="text/javascript">
jQuery(function(){
  if($('#imgli').has('img').length==1){
	  $('#imgli img').attr("style","width:20%;height:20%");
  }
});
</script>
</head>
<link href="<%=path%>/resources/js/bx/style.css" type="text/css" rel="stylesheet">
<body>
<%-- <div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="报修信息查看"><a
				href="<%=path%>/bx/bxck/cklist" target="content"
				onfocus="this.blur()"><span>报修信息查看</span></a></li>
		</ul>
	</div> --%>
<div class="top" style="width:100%;">
		<img src="<%=path%>/resources/img/top.png">
</div>


	<script src="<%=path%>/resources/js/xzxx/main.js"
		type="text/javascript"></script>
		
<div class="main">
		<div class="main_box">
				<div class="title" style="width: 100%;height: 30px;background-color: #b7daff;margin-top: 30px;color: #fff;line-height: 30px;text-align: center;border: #b7daff 1px solid;">详细信息</div>
		
		
		<div class="main_text" style="width: 100%;border: #ccc 1px solid;padding-bottom: 30px;float: left;">
				<ul>
				 	<c:forEach var="data" items="${detaillist}" varStatus="status">
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">主题名称:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.sbztmc }&nbsp;</span>
					</li>
					<li>
						<span  style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">申报人:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.sqrxm}&nbsp;</span>
					</li>
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">预约时间:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.yysj}&nbsp; </span>
					</li>
					<li>
						<span  style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">楼宇:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.ly}&nbsp;</span>
					</li>
					<li>
						<span  style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">楼号:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.lh}&nbsp; </span>
					</li>
						<li>
						<span  style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">地址:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.dz}&nbsp; </span>
					</li>
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">报修内容:</span>
							<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">
								${data.nr }&nbsp;
							</span>
					</li>
					<li>
						<span  style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">评价:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.pj}&nbsp;</span>
					</li>
					<li>
						<span  style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">满意度:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.myd}&nbsp; </span>
					</li>
					<li  id="imgli">
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">备注:</span>
							 <span style="width:100%;float: left;text-indent: 18px;display: block;line-height: 60px;">
							 <c:if test="${fn:length(fn:split(data.bz,';'))!=1 }">
										 <c:forEach var="item" varStatus="status" begin="0" end="${fn:length(fn:split(data.bz,';'))-1}">
 												 <img style="width:15%;height:15%;"   src="${fn:split(data.bz,';')[status.index]}"/>
										</c:forEach>
							  </c:if>
										 <c:if test="${fn:length(fn:split(data.bz,';'))==1 && data.bz==''}">
											 ${data.bz}
										</c:if>
							 <c:if test="${fn:length(fn:split(data.bz,';'))==1 && data.bz!=''}">
										<img style="width:15%;height:15%;"   src="${data.bz}"/>
							 </c:if>
							</span>
					</li>
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">审核人:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.sprxm }&nbsp; </span>
					</li>
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">审核时间:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.spsj}&nbsp; </span>
					</li>
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">派单人:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.sprxm}&nbsp; </span>
					</li>

					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">派出人:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.wxrxm}&nbsp;</span>
					</li>
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">派单时间:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.pdsj}&nbsp; </span>
					</li>
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">维修用时:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.pdsj}&nbsp; </span>
					</li>
					<li>
						<span style="width:10%;float: left;text-indent: 18px;display: block;line-height: 60px;">耗材用料:</span>
						<span style="width:90%;float: left;text-indent: 18px;display: block;line-height: 60px;">${data.hcyl}&nbsp; </span>
					</li>
					</c:forEach>	
				</ul>
		</div>

		
		
		
		
		
	<%-- <div style="align:center;text-align:center;height:100%;">
	<table style="height:100%;width:100%"  border="0" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<th scope="row" style="height:90px;background-color:white;">
				<img alt="" src="<%=path%>/resources/js/xzxx/bxlogo.gif" width="80%">
				</th>
			</tr>
			<tr>
				<td>
					<table style="height: 100%" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td style="width: 10%"></td>
								<td style="width: 80%">
									<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
										<tbody>
											<tr>
												<td valign="top" width="100%">
													<table background="<%=path%>/resources/js/xzxx/main_32.gif" border="0" cellpadding="0" cellspacing="0" width="100%">
														<tbody>
															<tr>
																<td background="<%=path%>/resources/js/xzxx/main_31.gif" height="28" width="139">
																	<table border="0" cellpadding="0" cellspacing="0"
																		width="139">
																		<tbody>
																			<tr>
																				<td align="right" width="29"><img alt=""
																					src="<%=path%>/resources/js/xzxx/ico_04.gif"
																					height="16" width="17"></td>
																				<td class="aa09" align="left">详细信息</td>
																			</tr>
																		</tbody>
																	</table>
																</td>
																<td  class="tdr" align="right" colspan="2" width="100%">&nbsp;</td>
															 <td colspan="2" width="25" background="<%=path%>/resources/js/xzxx/main_33.gif"
																	height="28"></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td valign="top">
													<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
														<tbody>
															<tr>
																<td class="aa07" align="left" valign="top" >
																		<table id="FormView1"
																			style="width: 100%;height:100%; border-collapse: collapse;" border="0" cellspacing="0">
																			<tbody>
																				<tr>
																					<td colspan="2">
																						<div class="ManageLine">
																							<span >主题名称:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.sbztmc }</span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span  style="margin-left: 12px;">申报人:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.sqrxm}</span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span >预约时间:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.yysj} </span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span  style="margin-left: 24px;">楼宇:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.ly}</span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span  style="margin-left: 24px;">楼号:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.lh} </span>
																							<div class="clear"></div>
																						</div>
																							<div class="ManageLine">
																							<span  style="margin-left: 24px;">地址:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.dz} </span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span >报修内容:</span>
																								<span class="aa06" style="margin-left: 12px;">
																									${data.nr }
																								</span>
																						</div>
																						<div class="ManageLine">
																							<span  style="margin-left: 24px;">评价:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.pj} </span>
																							<div class="clear"></div>
																						</div>
																							<div class="ManageLine">
																							<span  style="margin-left: 12px;">满意度:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.myd}&nbsp; </span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span style="margin-left: 24px;">备注:</span>
																								 <span class="aa060" style="margin-left: 12px;">
																									${data.bz}
																								</span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span style="margin-left: 12px;">审核人:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.sprxm } </span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span>审核时间:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.spsj} </span>
																							<div class="clear"></div>
																						</div>
																							<div class="ManageLine">
																							<span style="margin-left: 12px;">派单人:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.sprxm} </span>
																							<div class="clear"></div>
																						</div>

																						<div class="ManageLine">
																							<span style="margin-left: 12px;">派出人:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.wxrxm}</span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span >派单时间:</span>
																							<span class="aa06">${data.pdsj} </span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span >维修用时:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.pdsj} </span>
																							<div class="clear"></div>
																						</div>
																						<div class="ManageLine">
																							<span >耗材用料:</span>
																							<span class="aa06" style="margin-left: 12px;">${data.hcyl} </span>
																							<div class="clear"></div>
																						</div>
																						<br>
																					</td>
																				</tr>
																			</tbody>
																		</table>
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td style="width: 10%"></td>
								<td width="80%"></td>
								<td style="width: 10%"></td>
							</tr> --%>
							<div class="FH">
								<a href="<%=path%>/bx/bxck/cklist">
								<input style="width: 400px;height: 80px;float: left;margin-left: 300px;border-radius: 10px;margin-top: 50px;font-size: 24px;color: #fff;background-color: #ed9340;" type="button" value="返回首页">
								</a>
							</div>
						</div>
					</div>	
				<div class="foot">
						<div class="foot_box">CopyRight © 2016 All Rights Reserved 合肥智圣</div>
				</div>
	<!-- 					</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	</div> -->
	
</body>
</html>