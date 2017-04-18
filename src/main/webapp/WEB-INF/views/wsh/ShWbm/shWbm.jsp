<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class=" js ">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<meta name="viewport"
		content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta http-equiv="Cache-Control" content="no-cache"></meta>
<meta http-equiv="Cache-Control" content="no-store"></meta>
<meta http-equiv="Expires" content="0"></meta>
<title>报名信息</title>
<link href="<%=path%>/resources/skin/base.css" rel="stylesheet"></link>
	<link href="<%=path%>/resources/skin/wjend.css" rel="stylesheet"></link>
		<link href="<%=path%>/resources/skin/darkblue.css" rel="stylesheet"></link>
			<link href="<%=path%>/resources/skin/hcheckbox.css" rel="stylesheet"></link>
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
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/skin/load.css"></link>
		<script type="text/javascript" src="<%=path%>/resources/skin/load.js"></script>
<script type="text/javascript">
	function btnOK_onclick(){
		var url = '<%=path%>/wsh/ShWbm/saveBm';
		var cgts=$('#cgts').val();
			   if(confirm("您确定要提交吗？")){
				   $.ajax({
					    url: url,
						type : 'post',
						dataType : 'json',
						data: $('#myForm').serialize(),
						success: function(data){
							if(data == '1'){
								alert(cgts);
								if(WeixinJSBridge){
									WeixinJSBridge.call('closeWindow');
								}else{
									location.href ="<%=path%>/wsh/ShWbm/toWbmList";
								}
							}else{
								alert("提交失败");
							}
						},
						error: function(XMLHttpRequest){
								alert("网络错误");
						}
					});
				} 
	   }
</script>
</head>
<body>
	<c:if test="${!empty bmr}">
		<span>您已报名！</span>
	</c:if>
	<c:if test="${empty bmr}">
	<p style="display: none;" class="hei_cq" _hei="1323" id="body"></p>

	<div class="wjContent clear" id="survey_page"
		style="width: 85%;">
		<div class="content" id="begin_content">


			<div class="wjtitle mtop project_title">
				<h1>报名信息</h1>
			</div>
			<div class="wjintro mtop desc_begin">
				<p>请填写以下资料</p>
			</div>

			<div class="wjhr mtop" ></div>

		</div>
		<div id="question_box">
			<div class="maxtop btns title" id="loader_div_1"
				style="display: none; text-align: center;width: 100%;">
				<img src="<%=path%>/resources/skin/loader.gif" alt=""/> <br/>
				加载中...
			</div>
		<form method="post" action="" name="myForm" id="myForm">
			<c:forEach var="data" items="${bd}" varStatus="obj">
				<!-- Append question's html code here. -->
				<c:if test="${data.bdlx eq '1'}">
					<div class="wjques maxtop question jqtransformdone"
						 questiontype="${data.bdlx}">
						<div class="title" id="title${obj.count}"><c:if test="${data.sfbt=='0' }"><span>*</span> </c:if>${data.bt}</div>
						<div id="tip_${obj.count}" class="red"></div>
						<input type="hidden" name="dhwbbt_${obj.count }" id="dhwbbt_${obj.count }" value="${data.bt }"/>
						<div class="matrix">
							<div class="grade_text">
								<input class="blank option" type="text" placeholder="" name="dhwb_${obj.count }" 
								style="border-width: 1px; padding: 2px 5px; margin: 5px;width: 166px;"/>
								<span class="red" id="option_tip"style="display: inline-block;"></span>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${data.bdlx eq '2'}">
					<div class="wjques maxtop question jqtransformdone"
						 questiontype="${data.bdlx}">
						<div class="title" id="title${obj.count}"><c:if test="${data.sfbt=='0' }"><span>*</span> </c:if>${data.bt}</div>
						<div id="tip_${obj.count}" class="red"></div>
						<input type="hidden" name="wbkbt_${obj.count }" id="wbkbt_${obj.count }" value="${data.bt }"/>
						<div class="matrix">
							<div class="grade_text">
								<textarea class="blank option" name="wbk_${obj.count }"
  									id="wbk_${obj.count }" rows="5"
 									option_id="wbk_${obj.count }"></textarea> 
 								<span class="red" id="option_tip" style="display: inline-block;"></span>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${data.bdlx eq '0'}">
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
											id="radio_${dat.qseq}" class="option jqTransformHidden" style="margin-bottom: 4px;"/></span><label
											for="option_53b0fcddf7405b5a1eaa188a"
											class="option_label label_53b0fcddf7405b5a1eaa188a"
											style="cursor: pointer;">${dat.content}</label>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${data.bdlx eq '4'}">
					<div class="wjques maxtop question jqtransformdone"
						 questiontype="${data.bdlx}">
						<div class="title" id="title${obj.count}"><c:if test="${data.sfbt=='0' }"><span>*</span> </c:if>${data.bt}
						&nbsp;&nbsp;<span style="color: red;">最多选${data.zdxzz }项</span></div>
						<input type="hidden" name="dxbt_${obj.count }" id="dxbt_${obj.count }" value="${data.bt }"/>
						<input type="hidden" name="dxs_${obj.count }" id="dxs_${obj.count }" value="${dxs }"/>
						<div class="matrix">
							<div id="tip_${obj.count}" class="red"></div>
							<c:forEach var="dat" items="${list1}" varStatus="ob">
									<div class="icheckbox_div ">
										<span class="jqTransformRadioWrapper"> <input
											type="checkbox" name="dx_${obj.count }_${ob.count }"
											value="${dat.select }" id="dx_${obj.count }_${ob.count }"
											class="option jqTransformHidden" style="margin-bottom: 4px;"/></span><label
											for="option_53b0fcddf7405b5a1eaa188a"
											class="option_label label_53b0fcddf7405b5a1eaa188a"
											style="cursor: pointer;">${dat.select }</label>
									</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${data.bdlx eq '3'}">
					<div class="wjques maxtop question jqtransformdone"
						 questiontype="${data.bdlx}">
						<div class="title" id="title${obj.count}"><c:if test="${data.sfbt=='0' }"><span>*</span> </c:if>${data.bt}
						</div>
						<input type="hidden" name="xlbt_${obj.count }" id="xlbt_${obj.count }" value="${data.bt }"/>
						<div class="matrix">
							<div id="tip_${obj.count}" class="red"></div>
							<div class="selectTransverse">
								<select id="xlk_${obj.count }" name="xlk_${obj.count }"
									class="option" rel="53b0fcd3f7405b5a1eaa1884">
									<option value="-1">请选择</option>
									<c:forEach var="dat" items="${list}" varStatus="ob">
										<option value="${dat.select }">${dat.select }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</c:if>
				<input type="hidden" name="bdlx_${obj.count }" id="bdlx_${obj.count }" value="${data.bdlx }"/>
			</c:forEach>
		<input type="hidden" name="cgts" id="cgts" value="${map.bmcgts }"/>
		<input type="hidden" name="wbmid" id="wbmid" value="${id }"/>
		<input type="hidden" name="openId" id="openId" value="${openId }"/>
		<input type="hidden" name="size" id="size" value="${size }"/>
		</form>
		</div>
		<c:if test="${openId != null && openId != '' }">
		<div class="maxtop btns" id="control_panel" >
			<div class="WJButton wj_color" id="next_button" style="" onclick="btnOK_onclick();">提 交</div>
			<div class="red fL" id="err_msg"></div>
			<div class="wjprogress" id="progress_bar" style="display: none;">
				<span>20%</span>
				<div class="bar">
					<div class="barbox"></div>
				</div>
			</div>
		</div>
		</c:if>
		<div class="maxtop btns title" id="loader_div" 
			style="display: none; text-align: center;">
			<img src="<%=path%>/resources/skin/loader.gif" alt=""/> <br/>
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
</c:if>
	<!--wjContent end-->
</body>
</html>