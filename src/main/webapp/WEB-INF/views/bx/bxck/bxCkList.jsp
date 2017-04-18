<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<title>报修查看</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript" src="<%=path%>/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/My97DatePicker/lang/zh-cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<link href="<%=path%>/resources/js/bx/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="查询"><a
				href="<%=path%>/bx/bxck/cklist" target="content"
				onfocus="this.blur()"><span>查询</span></a></li>
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
				 var zt=$('#ztInput').val();
				 var time =$('#txtDate').val();
					if($("select option:selected").val()!='' && $("select option:selected").val()!='-1' ){
						zt=$("select option:selected").val();
					}
				 window.location.href = "<%=path%>/bx/bxck/cklist?zt="+zt+"&nr="+nr+"&time="+time;
				});
			
			if($("#xjbtTd").text().length>8){
				$("#xjbtTd").text($('#xjbtTd').text().substring(0, 11) + "...");
			}
			for(var i =0;i<$(".tdfirst").size();i++){
			if($(".xjbtTd"+i).text().length>15){
				$(".xjbtTd"+i).text($('.xjbtTd'+i).text().substring(0, 11) + "...");
			}
			}
		});
		
			 function getBm(value){
				 $('#ztInput').val(value);
					//window.location.href="";
			 } 
			  
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
			 }
			 
			  function getZt(value){
				 $('#ztInput').val(value);
					//window.location.href="";
			 }
		    
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
			
			
<div class="top" style="width:100%;">
		<img src="<%=path%>/resources/img/top.png">
</div>
<input  id="ztInput" value="" type="hidden"/>		
<div class="banner" style="width:100%;background:url('<%=path%>/resources/img/banner.png');" >
	<div class="banner_box">
		<div class="search_box" style="background:url('<%=path%>/resources/img/search.png');">
				<ul>
						<li>
								按预约时间:
								<input name="txtDate" id="txtDate" onClick="WdatePicker()" value="${time}">
						</li>
						<li>
								按预约内容:
								<input value="${nr}" type="text"  id="nrcx">
						</li>
						<li>
								按报修状态:
								<select style="margin-bottom: 12px;position:relative;" name="ddlRDepart" id="ddlRDepart" onChange="getBm(this.value)">
                                   <option value=''>请选择---</option>
                                   	 <c:forEach var="_zt" items="${listZt}" varStatus="status">
                                   	 <option <c:if test="${zt==_zt.zt}">selected="selected"</c:if> value="${_zt.zt}">${_zt.ztmc}</option>
                                   	 </c:forEach>
                                 </select>
						</li>
						<li>
								<div class="search_btn" name="Image6" id="cx" >
								<a href="#">
										<img class="cha" src="<%=path%>/resources/img/tu_s.png">
										查询
								</a>
								</div>
						</li>
				</ul>
				<ul>
						<li style="float:left">
							<div class="GN_btn">
								<a href="<%=path%>/wx/Bxspyjb/toBxsqlcjl">
									<img class="cha" src="<%=path%>/resources/img/GN_btn01.png">
									我要报修
								</a>
							</div>
						</li>
						<li style="float:left">
							<div class="GN_btn">
							<a href="<%=path%>/system/login/login.jsp">
								<img class="cha" src="<%=path%>/resources/img/GN_btn02.png">
								我要管理
							</a>
							</div>
						</li>
				</ul>
		</div>
	</div>
</div>
<div class="main">
		<div class="main_box">
				<div class="wxts">
					<span style="color:#f83b10">温馨提示</span>
					报修完成的时候，同学们需要对这次报修系统进行评价才可以再次进行新的报修.
				</div>
				<div class="biao">
					<ul>
							<li style="background-color:#b2cdec; color:#fff; font-size:16px">
									<span style=" width:30%">报修内容</span>
									<span style=" width:30%">申报主题名称 </span>
									<span style=" width:15%">预约时间 </span>
									<span style=" width:10%">状态</span>
									<span style=" width:15%">操作</span>
							</li>
							<li>
									<c:forEach var="data" items="${list}" varStatus="status">
										 	<%-- <span style="width:30%;">${data.sbztmc}</span> --%>
									 		<%-- <c:if test="${status.count %2 ==0 }">
												 <tr class="tds" onmouseover="mouseover(this,'#EDEDED');" onmouseout="mouseout(this,'#E6EEFF','#F5F5F5');" style="height: 25px;" align="center">
										 	</c:if>
										  	<c:if test="${status.count %2 !=0 }">
												 <tr  onmouseover="mouseover(this,'#EDEDED');" onmouseout="mouseout(this,'#E6EEFF','#F5F5F5');" style="height: 25px;" align="center" class="list01 tds ">
										 	</c:if> --%>
											<span style="width:30%;" title="${data.nr}">
												<c:choose>  
													<c:when test="${fn:length(data.nr) > 10}">  
	     												<c:out value="${fn:substring(data.nr,0,10)}..." />  
													</c:when>  
													<c:otherwise>  
	     												<c:out value="${data.nr}" />  
													</c:otherwise>  
												</c:choose>
											</span>
											<span style="width:30%;">
											<c:choose>  
												<c:when test="${fn:length(data.sbztmc) > 8}">  
     												<c:out value="${fn:substring(data.sbztmc,0,8)}..." />  
												</c:when>  
												<c:otherwise>  
     												<c:out value="${data.sbztmc}" />  
												</c:otherwise>  
											</c:choose>
											</span>
											<span style="width:15%;">${data.yysj}</span>
											<span style="width:10%;">${data.zt}</span>
											<span style="width:15%;"><a href="<%=path%>/bx/bxck/ckdetail?id=${data.id}">详情查看</a> &nbsp;&nbsp;</span>
									</c:forEach>
							</li>
					</ul>
				</div>
				<div class="tab">
						<span style="padding-left: 101px;">
								<c:choose>
					    			 <c:when test="${pages > 1}">
					 				 	<a href="<%=path%>/bx/bxck/cklist?pages=${pages - 1}&zt=${zt}&nr=${nr}&time=${time}">上一页</a>
					     			 </c:when>
					     			 <c:otherwise>上一页</c:otherwise>
				         		</c:choose>
				         		第${pages}页
								<c:choose>
					    			<c:when test="${pages <count}">
						 				<a href="<%=path%>/bx/bxck/cklist?pages=${pages +1}&zt=${zt}&nr=${nr}&time=${time}">下一页</a>
					      			</c:when>
					      			<c:otherwise>下一页</c:otherwise>
				         		</c:choose>总共${count}页
				         </span>
				</div>
		</div>
</div>
<div class="foot">
		<div class="foot_box">CopyRight © 2016 All Rights Reserved 合肥智圣</div>
</div>
<%-- 		<table style="height: 100%" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="width: 50%" scope="row">
					<script
							type="text/javascript">
						Sys.WebForms.PageRequestManager._initialize(
								'ScriptManager1', document.getElementById('form1'));
						Sys.WebForms.PageRequestManager.getInstance()._updateControls(
										[ 'tUpdatePanel1', 'tUpdatePanel2' ],
										[ 'ImageButton1', 'ImageButton2',
												'ImageButton3', 'Button1',
												'Button2' ], [], 90);
					</script>
					</td>
					<td style="height: 100%" width="800px">
						<table style="height: 100%" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr style="height: 100%">
									<td style="width: 800px">
										<table style="height: 100%" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="100%">
											<tbody>
												<tr>
													<th scope="row">&nbsp;</th>
													<td>
														<table style="height: 100%" border="0" cellpadding="0" cellspacing="0" width="100%">
                    										<tbody><tr>
                      <th scope="row">
                        <table  border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                          <tr>
                            <th class="tu" scope="row" valign="bottom" width="550px">
                              <table align="left" border="0" cellpadding="0" cellspacing="0" width="550px">
                                <tbody>
                                <tr>
                                <th scope="row" style="height:5px" colspan="7"></th>
                                    </tr>
                                <tr>
                                <th scope="row" height="20">&nbsp;</th>
                                <td class="style8" align="left" height="20" style="position: absolute;margin-bottom: 18px;" valign="middle">按预约时间查询：</td>
                                <td class="style5" align="left" height="30">
                                    <span class="ManageLineInput">
                                      <input name="txtDate" id="txtDate" onClick="WdatePicker()" value="${time}" style="width: 170px; position: relative; margin-bottom: 15px;" type="text"></span></td>
                                <td class="style6" align="left" height="30">&nbsp;</td>
                                <td align="left" height="30" width="28">
                                    <input name="ImageButton1" id="ImageButton1" src="<%=path%>/resources/js/xzxx/l7a.gif" style="border-width:0px;" type="image">
                                    </td>
                                <td class="song12a" align="left" height="30"> </td>
                                <td height="30"></td></tr>
                                <tr>
                                 <th scope="row" height="30">&nbsp;</th>
                                <td class="style8" align="left" height="30" valign="middle" style="position: absolute;margin-bottom: 10px;" width="50">按报修内容查询：</td>
                                <td class="style5" align="left" height="30" width="50">
                                  <span class="ManageLineInput1">
									<input  value="${nr}" type="text"  id="nrcx" style="position:relative;margin-bottom: 11px;"  />                                  
                                  </span>
                                  <input style="display:none;" type="text" value="" id="ztInput">
                                   <input style="display:none;" type="text" value="" id="bmInput">
                                 <span class="ManageLineInput1"></span>
                                </td>
              <td class="style6" align="left" height="30"></td>
              <td align="left" height="30">
              <input name="ImageButton3" id="ImageButton3" src="<%=path%>/resources/js/xzxx/l7a.gif" style="border-width:0px;" type="image">
              </td>
              <td class="song12a" align="left" height="30"></td>
                                </tr>
                                <tr>
                                <th scope="row" height="30">&nbsp;</th>
                                <td class="style8" align="left" height="30" style="margin-bottom: 10px; position: relative;
                                 border-bottom-width: 0px; border-top-width: 0px; padding-bottom: 11px;"
                                 valign="middle">按报修状态查询：</td>
                                <td class="style5" align="left" height="30">
                                  <span class="ManageLineInput2">
                                   <select   style="margin-bottom: 12px;position:relative;"
                                   name="ddlRDepart" id="ddlRDepart" onChange="getBm(this.value)">
                                   <option value=''>请选择---</option>
                                   	 <c:forEach var="_zt" items="${listZt}" varStatus="status">
                                   	 <option <c:if test="${zt==_zt.zt}">selected="selected"</c:if> value="${_zt.zt}">${_zt.ztmc}</option>
                                   	 </c:forEach>
                                   </select>
                                  </span>
                                 <span class="ManageLineInput2"></span>
                                </td>
              <td class="style6" align="left" height="30"></td>
              <td align="left" height="30">
              <input name="ImageButton3" id="ImageButton3" src="<%=path%>/resources/js/xzxx/l7a.gif" style="border-width:0px;" type="image">
              </td>
              <td class="song12a" align="left" height="30"></td>
              <td height="30">&nbsp;</td></tr>
              <tr>
              <th scope="row" colspan="7" height="10"></th>
                      </tr></tbody></table>
													<td width="20">&nbsp;</td>
													<td class="tu2" width="240px">
														<table border="0" cellpadding="0" cellspacing="0"
															width="240px">
															<tbody>
																<tr>
																	<th scope="row" class="style12"></th>
																	<td class="style12"></td>
																	<td class="style12" width="57"><a 
																		href="<%=path%>/bx/bxck/toBxsq" target="_blank">
																			<img name="Image5" style="margin-top: 24px;"
																			src="<%=path%>/resources/js/bx/wybx.gif"
																			height="30" border="0" width="120">
																	</a></td>
																	<td class="style12"></td>
																	<td class="style12"></td>
																</tr>
																<tr>
																	<th scope="row" class="style12"></th>
																	<td class="style12"></td>
																	<td class="style12" width="57"><a 
																		href="<%=path%>/system/login/login.jsp">
																			<img name="Image5"
																			src="<%=path%>/resources/js/xzxx/glydl.gif"
																			height="30" border="0" width="120">
																	</a></td>
																	<td class="style12"></td>
																	<td class="style12"></td>
																</tr>
																<tr>
																	<th scope="row" width="56">&nbsp;</th>
																	<td></td>
																	<td><a
																		href="#" >
																			<img name="Image6" id="cx"
																			src="<%=path%>/resources/js/xzxx/cx.gif"
																	style="padding-left:50;"height="30" border="0" width="120">
																	</a></td>
																	<td></td>
																	<td width="57">&nbsp;</td>
																</tr>
																
																<tr>
																	<th scope="row">&nbsp;</th>
																	<td width="80">&nbsp;</td>
																	<td>&nbsp;</td>
																	<td width="89">&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
									</th>
								</tr>
					<!-- 			<tr>
									<th scope="row" style="height: 24px">
										<table height="8" border="0" cellpadding="0" cellspacing="0" width="100%">
											<tbody>
												<tr>
												</tr>
											</tbody>
										</table>
									</th>
								</tr>
								<tr>
									<td>
									</td>
								</tr> -->
								<tr>
									<th scope="row">
										<table height="8" border="0" cellpadding="0" cellspacing="0"
											width="100%">
											<tbody>
												<tr>
													<td class="style10" align="center" >
													<p style="margin-left:-570px;"><span style="color:red;">报修完成的时候,同学们需要对这次报修进行评价才可以再次进行新的报修.</span></p>
													<img
														src="<%=path%>/resources/js/bx/bxxx.jpg"
														height="26" width="970"></td>
													<td>
													<!-- <td><input type="button" id="shtgInput" value="审核通过" style="width:60px;cursor:pointer;"/></td>
													<td>&nbsp;<td>
													<td><input type="button" id="ypjjsInput" value="已评价结束" style="width:70px;cursor:pointer;"/></td> -->
												</tr>
											</tbody>
										</table>
										
									</th>
								</tr>
								<tr>
									<td>
										<div id="UpdatePanel2" class="tds1">
											<table valign="top" style="height: 83%" bgcolor="#c3c3c3"
												border="0" cellpadding="0" cellspacing="1" width="100%">
												<tbody>
													<tr bgcolor="#ffffff" valign="top">
														<td class="song12" style="height: 100%" align="center"
															bgcolor="#ffffff" valign="top">
															<div>
																<table class="aa06" rules="rows" id="GridView2"
																	style="height: 100%; width: 100%; border-collapse: collapse;"
																	align="Center" border="1" cellspacing="0">
																	<tbody>
																		<tr class="listTitle" align="left">
																			<th scope="col" class="Atablefont" style="width:40%;" align="center">报修内容</th>
																			<th scope="col" align="center">申报主题名称</th>
																			<th scope="col" align="center">预约时间</th>
																			<th scope="col" align="center">状态</th>
																			<th scope="col" align="center">操作</th>
																		</tr>
																		 <c:forEach var="data" items="${list}" varStatus="status">
																		 <td style="display:none;" class="tdfirst">${data.sbztmc}</td>
																		 <c:if test="${status.count %2 ==0 }">
																		 <tr class="tds" onmouseover="mouseover(this,'#EDEDED');"
																			onmouseout="mouseout(this,'#E6EEFF','#F5F5F5');"
																			style="height: 25px;" align="center">
																		 </c:if>
																		  <c:if test="${status.count %2 !=0 }">
																		 <tr  onmouseover="mouseover(this,'#EDEDED');"
																			onmouseout="mouseout(this,'#E6EEFF','#F5F5F5');"
																			style="height: 25px;" align="center" class="list01 tds ">
																		 </c:if>
																			<td align="left" title="${data.nr}" class="xjbtTd${status.index}">${data.nr }</td>
																			<td style="color: #666666;font-size: 12px;line-height: 24px;">${data.sbztmc }</td>
																			<td>${data.yysj}</td>
																			<td>${data.zt}</td>
																			<td><a
																				href="<%=path%>/bx/bxck/ckdetail?id=${data.id}">
																					详情查看</a> &nbsp;&nbsp;</td>
																		</tr>
																		</c:forEach>
																		<tr class="pagestyle1" style="height: 30px;"
																			align="center" valign="middle">
																			<td colspan="4"><table border="0">
																					<tbody>
																						<tr>
																						<td style="padding-left: 101px;">
																								<c:choose>
					                                                                            <c:when test="${pages > 1}">
						                                                                       <a href="<%=path%>/bx/bxck/cklist?pages=${pages - 1}&zt=${zt}&nr=${nr}&time=${time}">上一页</a>
					                                                                             </c:when>
					                                                                              <c:otherwise>
				                                                                                    		上一页
					                                                                               </c:otherwise>
				                                                                                       </c:choose>
				                                                                                                                                                                                                                                                                                               第${pages}页
																								<c:choose>
					                                                                            <c:when test="${pages <count}">
						                                                                       <a href="<%=path%>/bx/bxck/cklist?pages=${pages +1}&zt=${zt}&nr=${nr}&time=${time}">下一页</a>
					                                                                             </c:when>
					                                                                              <c:otherwise>
				                                                                                    		下一页
					                                                                               </c:otherwise>
				                                                                                       </c:choose>总共${count}页</td>
																						</tr>
																					</tbody>
																				</table>
																				</td>
																			<td>共${zs}条记录</td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</td>
								<td>共${wzs}条记录</td>
								</tr>
							</tbody>
						</table>
					</td>
					<!-- <td width="8">
						<div align="center"></div>
					</td>
				</tr>
				<tr style="height: 100%">
					<th scope="row" style="height: 100%">&nbsp;</th>

					<td style="height: 50px" align="center" valign="bottom"><span
						class="song12">CopyRight © 2015 All Rights Reserved 皖西学院</span></td>
					<td>&nbsp;</td>
				</tr> -->
			</tbody>
		</table>
		<div></div>
		</td>
		</tr>
		</tbody>
		</table>
		</td>
		<td style="width: 50%">
			<div align="center"></div>
		</td>
		</tr>
		</tbody>
		</table>
	</form> --%>
</body>
</html>