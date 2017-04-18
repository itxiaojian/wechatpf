<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>问卷管理系统</title>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/css/main.css" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
//新建题目
function newQues(id) {
	window.self.location="<%=path%>/wsh/WjQuestion/toAddQues?id="+id;
}

//新建题目
function AddQues(id,seq) {
	window.self.location="<%=path%>/wsh/WjQuestion/toAdd?id="+id+"&seq="+seq;
}

//新建题目
function UpdateQues(id,seq) {
	window.self.location="<%=path%>/wsh/WjQuestion/toUpdate?id="+id+"&seq="+seq;
}

//新建题目
function delQues(seq,id) {
	if(confirm("您确实要删除该问题吗？删除后，不能再恢复。"))
	{
		window.self.location="<%=path%>/wsh/WjQuestion/delete?id="+id+"&seq="+seq;
	}
}
	
//返回
function back() {
	window.location.href="<%=path%>/wsh/WjObject/toWjList";
}
</script>
<style type="text/css">
.container{
	background-color: white;
    height: 500px;
    margin-left: 30px;
    margin-right: 30px;
    margin-top: 30px;
    overflow: hidden;
}
</style>
</head>
<body topmargin="2">
	<div class="container">
	<table class="table">
		<tr>
			<td valign="top">
				<table class="table">
					<tr>
						<td width="35" rowspan="2" valign="top"></td>
						<td>
							<table class="table">
								<tr>
									<td colspan="2">
										<div align="center" style="font-size: 14px;">
											<strong>${ob.title}</strong>
										</div> <br />
									</td>
								</tr>
								<tr>
									<td>
										<table class="table">
											<tr>
												<td>${ob.discribe}</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="10"><span> <input type="button"
											name="sbmQues" onclick="newQues(${oid});" value=" 新建题目 " />
									</span></td>
								</tr>
								<tr>
									<td colspan="5">
										<form method="post" action="" name="myForm">
											<table class="table2">
												<c:forEach var="data" items="${quesList}" varStatus="obj">
													<tr>
														<td bgcolor="#CDE2FD" colspan=4><span
															id="title${obj.count}">${data.seq}.${data.content}</span>
															<br /></td>
													</tr>
													<tr>
														<c:if test="${data.qtype eq '0'}">
															<td>
																<table class="table2">
																	<c:forEach var="dat" items="${selList}" varStatus="ob">
																		<c:if test="${selList!=null&&data.seq == dat.qseq }">
																			<tr>
																				<td width="25%" valign="top"><input
																					type="radio" id="radio_${dat.qseq}"
																					name="radio_${dat.qseq}" value="${dat.selseq}" />${dat.content}</td>
																			</tr>
																			<tr>
																			</tr>
																		</c:if>
																	</c:forEach>
																	<tr>
																		<td><br /> <a href="#"
																			onclick="AddQues(${oid},${data.seq});">插入题目</a> <a
																			href="#" onclick="UpdateQues(${oid },${data.seq});">编辑题目</a>
																			<a onclick="delQues(${data.seq},${oid});" href="#">删除题目</a>
																			<br /></td>
																	</tr>
																</table>
															</td>
														</c:if>
														<c:if test="${data.qtype eq '1'}">
															<tr>
																<td>
																	<table class="table2">
																		<c:forEach var="dat" items="${selList}" varStatus="ob">
																			<c:if test="${selList!=null&&data.seq == dat.qseq }">
																				<tr>
																					<td width="25%" style="word-break: break-all;"
																						valign="top"><input type="checkbox"
																						name="check_${dat.qseq}" value="${dat.selseq}" />${dat.content}
																						<br /></td>
																				</tr>
																			</c:if>
																		</c:forEach>
																		<tr>
																		</tr>
																		<tr>
																			<td><br /> <a href="#"
																				onclick="AddQues(${oid},${data.seq});">插入题目</a> <a
																				href="#" onclick="UpdateQues(${oid },${data.seq});">编辑题目</a>
																				<a onclick="delQues(${data.seq},${oid});" href="#">删除题目</a>
																				<br /></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
														<c:if test="${data.qtype eq '2'}">
															<td colspan=4><select name="select_${data.seq}">
																	<option value="-1">请选择</option>
																	<c:forEach var="dat" items="${selList}" varStatus="ob">
																		<c:if test="${selList!=null&&data.seq == dat.qseq }">
																			<option value="${dat.selseq}">
																				${dat.content}</option>
																		</c:if>
																	</c:forEach>
															</select></td>
															<tr>
																<td><br /> <a href="#"
																	onclick="AddQues(${oid},${data.seq});">插入题目</a> <a
																	href="#" onclick="UpdateQues(${oid },${data.seq});">编辑题目</a>
																	<a onclick="delQues(${data.seq},${oid});" href="#">删除题目</a>
																	<br /></td>
															</tr>
														</c:if>
														<c:if test="${data.qtype eq '3'}">
															<c:forEach var="dat" items="${selList}" varStatus="ob">
																<c:if test="${selList!=null&&data.seq == dat.qseq }">
																	<td colspan=4>&nbsp;&nbsp;&nbsp; <span>${dat.content }</span>
																		<br /> <textarea name="txt_${data.seq }" rows="3"
																			style="width: 100%"></textarea>
																	</td>
																	<tr>
																		<td><br /> <a href="#"
																			onclick="AddQues(${oid},${data.seq});">插入题目</a> <a
																			href="#" onclick="UpdateQues(${oid },${data.seq});">编辑题目</a>
																			<a onclick="delQues(${data.seq},${oid});" href="#">删除题目</a>
																			<br /></td>
																	</tr>
																</c:if>
															</c:forEach>
														</c:if>
												</c:forEach>
											</table>
										</form>
									</td>
								</tr>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td>
										<div id="buttonArea">
											<input type="button" name="btnOK" value=" 返  回   "
												onclick="back();" /> <br />
										</div>
									</td>
								</tr>
							</table> <input type="hidden" name="subCnt" id="subCnt" value="${size }" />
						</td>
					</tr>
					<tr>
						<td height="20"></td>
					</tr>
				</table> <br />
			</td>
			<td width="23" rowspan="2">&nbsp;</td>
		</tr>
	</table>
</div>
</body>
</html>
