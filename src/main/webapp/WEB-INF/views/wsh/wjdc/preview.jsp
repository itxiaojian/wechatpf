<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class=" js ">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport"
		content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Expires" content="0">
<title>调查问卷</title>
<link href="<%=path%>/resources/skin/base.css" rel="stylesheet"></link>
	<link href="<%=path%>/resources/skin/wjend.css" rel="stylesheet"></link>
		<link href="<%=path%>/resources/skin/darkblue.css" rel="stylesheet"></link>
			<link href="<%=path%>/resources/skin/hcheckbox.css" rel="stylesheet"></link>
<script type="text/javascript">
	var oid="${oid }";
	var openId="${openId }";
	function btnOK_onclick(myForm,id){
		   //myForm.action="./voteSuccess.jsp?id="+id;
		   if(validCheck(myForm)){
			   //myForm.submit();
			   if(confirm("您确定要提交吗？")){
				   $.ajax({
						cache : true,
						type : "POST",
						url : "<%=path%>/wsh/WjReplay/saveWj",
						data : $('#myForm').serialize(),// 你的formid
						async : false,
						error : function(request) {
							alert("提交失败，请联系管理员。");
						},
						success : function(data) {
							if(data=='1'){
								alert("您的答案已提交，不能重复提交!");
								window.self.location="<%=path%>/wsh/WjObject/toWjDcjgSj?id="+oid;
							}else if(data=='2'){
								alert("该问卷已终止评议，不能提交!");
								window.self.location="<%=path%>/wsh/WjObject/toDcwj?openId="+openId;
							}else if(data==''){
								alert("提交成功。");
								window.self.location="<%=path%>/wsh/WjObject/toWjDcjgSj?id="+oid;
							}else{
								alert("提交失败，请联系管理员。");
							}
						}
					});
				} 
		   }else{
			   
		   	   return false;
		   }
	   }
	//校验
	function validCheck(myForm)
	{
		var i = 0;
		var subCnt = document.getElementById("subCnt").value;
		var subIndex = 1;
		var index = 0;
	for (i = 0; i < myForm.elements.length && subIndex < subCnt; i++) {
		var title = document.getElementById("title" + subIndex).innerHTML;
		var element = myForm.elements[i];
		var eType = element.type;
		//var eName = element.name;
		//var eValue = element.value;
		if (eType == "radio") {
			var v = "";
			var subs = document.getElementsByName("radio_" + subIndex);
			var j = 0;
			for (j = 0; j < subs.length; j++) {
				if (subs[j].checked == true) {
					v = subs[j].value;
				}
				i++;
			}
			if (subs.length > 0) {
				i--;
			}
			if (v == "") {
				alert("“" + title + "” 必须选择一个选项!");
				return false;
			}
		} else if (eType == "select") {
			var v = element.value;
			if (v == "-1") {
				alert("“" + title + "” 请选择下拉菜单的值！");
				return false;
			}
		} else if (eType == "checkbox") {
			var v = "";
			var chkName = document.getElementsByName("check_" + subIndex);
			var c = 0;
			for (c = 0; c < chkName.length; c++) {
				if (chkName[c].checked == true) {
					v = chkName[c].value;
				}
				i++;
			}
			if (chkName.length > 0) {
				i--;
			}
			if (v == "") {
				alert("“" + title + "” 复选框至少得选一个选项!");
				return false;
			}
		}
		subIndex++;
	}
	return true;
}
</script>
<style>
.wjhr {background-color: #26b865;}
.wj_color {background: #26b865;}
</style>
</head>
<body>
	<p style="display: none;" class="hei_cq" _hei="1323" id="body"></p>

	<div class="wjContent clear" id="survey_page"
		style="width: 85%;">
		<div class="content" id="begin_content">


			<div class="wjtitle mtop project_title">
				<h1>微问卷</h1>
			</div>
			<div class="wjintro mtop desc_begin">
				<p>请填写以下资料</p>
			</div>

			<div class="wjhr mtop" ></div>

		</div>
		<div id="question_box">
			<div class="maxtop btns title" id="loader_div_1"
				style="display: none; text-align: center;width: 100%;">
				<img src="<%=path%>/resources/skin/loader.gif" alt=""> <br>
				加载中...
			</div>
		<form method="post" action="" name="myForm" id="myForm">
			<c:forEach var="data" items="${quesList}" varStatus="obj">
				<!-- Append question's html code here. -->
				<c:if test="${data.qtype eq '3'}">
					<div class="wjques maxtop question jqtransformdone"
						 questiontype="${data.qtype}">
						<div class="title" id="title${obj.count}">${data.seq}.${data.content}</div>
						<div id="tip_${obj.count}" class="red"></div>
						<div class="matrix">
							<table>
								<tbody>
									<c:forEach var="dat" items="${selList}" varStatus="ob">
										<c:if test="${selList!=null&&data.seq == dat.qseq }">
											<tr>
												<td name="option" align="left" colspan="2"
													class="T_edit_td option_label label_53b1238af7405b77e2befdf6">&nbsp;&nbsp;${dat.content }
												</td>
											</tr>
											<tr>
												<td></td>
												<td><div class="grade_text">
														<textarea class="blank option" name="txt_${data.seq }_${dat.selseq }"
															id="txt_${data.seq }_${dat.selseq }" rows="5"
															option_id="txt_${data.seq }_${dat.selseq }"></textarea>
														<span class="red" id="option_tip"
															style="display: inline-block;"></span>
													</div></td>
											</tr>
											<tr>
												<td colspan="2"><span class="red unique_tip"
													style="display: none;" id="tip_${data.seq }">填写的内容已存在</span></td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>
				<c:if test="${data.qtype eq '0'}">
					<div class="wjques maxtop question jqtransformdone"
						 questiontype="${data.qtype}">
						<div class="title" id="title${obj.count}">${data.seq}.${data.content}</div>
						<div class="matrix">
							<div id="tip_${obj.count}" class="red"></div>
							<c:forEach var="dat" items="${selList}" varStatus="ob">
								<c:if test="${selList!=null&&data.seq == dat.qseq }">
									<div class="icheckbox_div ">
										<span class="jqTransformRadioWrapper"> <input
											type="radio" name="radio_${dat.qseq}" value="${dat.selseq}"
											id="radio_${dat.qseq}" class="option jqTransformHidden" style="margin-bottom: 4px;"></span><label
											for="option_53b0fcddf7405b5a1eaa188a"
											class="option_label label_53b0fcddf7405b5a1eaa188a"
											style="cursor: pointer;">${dat.content}</label>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${data.qtype eq '1'}">
					<div class="wjques maxtop question jqtransformdone"
						 questiontype="${data.qtype}">
						<div class="title" id="title${obj.count}">${data.seq}.${data.content}</div>
						<div class="matrix">
							<div id="tip_${obj.count}" class="red"></div>
							<c:forEach var="dat" items="${selList}" varStatus="ob">
								<c:if test="${selList!=null&&data.seq == dat.qseq }">
									<div class="icheckbox_div ">
										<span class="jqTransformRadioWrapper"> <input
											type="checkbox" name="check_${dat.qseq}"
											value="${dat.selseq}" id="check_${dat.qseq}"
											class="option jqTransformHidden" style="margin-bottom: 4px;"></span><label
											for="option_53b0fcddf7405b5a1eaa188a"
											class="option_label label_53b0fcddf7405b5a1eaa188a"
											style="cursor: pointer;">${dat.content}</label>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${data.qtype eq '2'}">
					<div class="wjques maxtop question jqtransformdone"
						 questiontype="${data.qtype}">
						<div class="title" id="title${obj.count}">${data.seq}.${data.content}</div>
						<div class="matrix">
							<div id="tip_${obj.count}" class="red"></div>
							<div class="selectTransverse">
								<select id="select_${data.seq}" name="select_${data.seq}"
									class="option" rel="53b0fcd3f7405b5a1eaa1884">
									<option value="-1">请选择</option>
									<c:forEach var="dat" items="${selList}" varStatus="ob">
										<c:if test="${selList!=null&&data.seq == dat.qseq }">
											<option value="${dat.selseq}">${dat.content}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		<input type="hidden" name="subCnt" id="subCnt" value="${size }" />
		<input type="hidden" name="id" id="id" value="${oid }" />
		<input type="hidden" name="openId" id="openId" value="${openId }" />
		</form>
		</div>
		<div class="maxtop btns" id="control_panel" >
			<div class="WJButton wj_color" id="next_button" style="" onclick="btnOK_onclick(myForm,${oid });">提 交</div>
			<div class="WJButton wj_color" id="next_button" style="">
			   <a href="<%=path%>/wsh/WjObject/toDcwj?openId=${openId}" style="color:#f7f7f7;">返&nbsp;回</a></div>
			<div class="red fL" id="err_msg"></div>
			<div class="wjprogress" id="progress_bar" style="display: none;">
				<span>20%</span>
				<div class="bar">
					<div class="barbox"></div>
				</div>
			</div>
		</div>
		<div class="maxtop btns title" id="loader_div" 
			style="display: none; text-align: center;">
			<img src="<%=path%>/resources/skin/loader.gif" alt=""> <br>
					<div id="loader_text">正在保存...</div>
		</div>
		<div class="conthank" id="survey_end" style="display: none;" >
			<p id="seq_content"
				style="text-align: center; color: #999; margin-bottom: 60px; margin-top: -60px;">&nbsp;</p>
			<p id="end_desc"></p>
			<div class="show_results" id="show_results">

				<!-- Baidu Button BEGIN -->

			</div>
		</div>
		<div class="Error_message" id="error_msg_box" style="display: none;">
			<span class="error_text"></span>
			<div href="javascript:;" class="WJButton wj_color"
				style="display: none;" id="reupload_answer">&nbsp;&nbsp;重新提交&nbsp;&nbsp;</div>
			<a href=""
				class="WJButton wj_color" style="display: none;" id="fankui">&nbsp;&nbsp;马上联系我们&nbsp;&nbsp;</a>
		</div>
	</div>
	<!--wjContent end-->
	<style type="text/css">
.ma_auto {
	width: none;
}

.instructions div {
	float: none;
}

.wjtext {
	text-align: center;
}

.wjtext a {
	display: inline;
}
</style>

	<script type="text/javascript"
		src="<%=path%>/resources/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript">
		
	</script>
	<!--加载提示-->
	<link type="text/css" rel="stylesheet"
		href="<%=path%>/resources/skin/load.css">
		<script type="text/javascript" src="<%=path%>/resources/skin/load.js"></script>
</body>
</html>