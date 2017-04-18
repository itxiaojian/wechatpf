<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
<title>微调查</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<script type="text/javascript">
	//修改问卷
	function Modify_onClick(oid)
	{
		var topicCode = oid;
		window.self.location="<%=path%>/wsh/WjObject/toUpdate?id="+topicCode;
	}
	
	//删除问卷
	function Delete_onClick(oid)
	{
		if(confirm("您确实要删除该问卷吗？删除该问卷将删除与之相关的题目\n\n信息，回答信息等，删除后，不能再恢复，请慎重。"))
		 {
			window.self.location="<%=path%>/wsh/WjObject/delete?id="+oid;
		 }
	}
	
	//问卷内容编辑
	function QuestionEdit_onClick(oid,status)
	{
		   if(status==1 || status==2)
		   {
			  if(confirm("问卷发布后，修改问卷将造成以前的回复\n\n数据无效，您确定要编辑问卷内容吗？")){
				  window.self.location="<%=path%>/wsh/WjQuestion/toQuesList?id="+oid;
				}
		   }
		   if(status==0)
		   {
			  window.self.location="<%=path%>/wsh/WjQuestion/toQuesList?id="+oid;
		   }
	}
	
	//发布
	function publish_onClick(id,status){
			   var topicCode = id;
			   if(status==0)
			   {
				  if(confirm("您确定发布该问卷吗？")){
							$.ajax({
								cache : true,
								type : "POST",
								url : "<%=path%>/wsh/WjObject/objFb",
								data : {id:topicCode},
								async : false,
								error : function(request) {
									alert("操作失败，请联系管理员。");
								},
								success : function(data) {
									if(data=='1'){
										alert('发布成功！');
										location.reload();
									}else{
										alert("发布失败，请联系管理员。");
									}
								}
							});
					} 
			   }
			   if(status==1)
			   {
				  alert("该问卷已发布，不能重复发布！");
				  return;
			   }
			   if(status==2)
			   {
				  alert("该问卷已终止评议，不能发布！");
				  return;
			   }
	}
	
	//预览2
	function preview_onClick2(id,status){
			   var topicCode = id;
			   if(status==1 || status==2)
			   {
				   preview(topicCode);
			   }
			   else
			   {
				alert("该问卷尚未发布，不能预览!");
			   }
	}

	//预览
	function preview(id){
		window.self.location="<%=path%>/wsh/WjQuestion/toDcwj?id="+id;
	}
	
	//撤销
	function cancel_onClick(id,status){
			   var topicCode = id;
			   if(status==1)
			   {
				   if(confirm("撤销问卷将造成以前的回复\n\n数据无效，您确定要撤销吗？")){
					   $.ajax({
							cache : true,
							type : "POST",
							url : "<%=path%>/wsh/WjObject/objCx",
							data : {id:topicCode},
							async : false,
							error : function(request) {
								alert("操作失败，请联系管理员。");
							},
							success : function(data) {
								if(data=='1'){
									alert('撤销成功！');
									location.reload();
								}else{
									alert("撤销失败，请联系管理员。");
								}
							}
						});
					} 
			   }
			   if(status==0)
			   {
				  alert("该问卷尚未发布，无需撤销！");
				  return;
			   }
			   if(status==2)
			   {
				  alert("该问卷已终止评议，不能撤销！");
				  return;
			   }
	}
	
	//终止评议
	function term_onClick(id,status){
			   var topicCode = id;
			   if(status==1)
			   {
				   if(confirm("您确定要终止评议吗？")){
					   $.ajax({
							cache : true,
							type : "POST",
							url : "<%=path%>/wsh/WjObject/objZz",
							data : {id:topicCode},
							async : false,
							error : function(request) {
								alert("操作失败，请联系管理员。");
							},
							success : function(data) {
								if(data=='1'){
									alert('操作成功！');
									location.reload();
								}else{
									alert("操作失败，请联系管理员。");
								}
							}
						});
					} 
			   }
			   if(status==0)
			   {
				  alert("该问卷尚未发布！");
				  return;
			   }
			   if(status==2)
			   {
				  alert("该问卷已终止评议！");
				  return;
			   }
	}
	
	//查看结果
	function showResult_onClick(oid,status){
			  if(status==1 || status==2)
			   {
					window.location.href="<%=path%>/wsh/WjObject/toWjDcjg?id="+ oid;
				} else {
					alert("该问卷尚未发布，不能查看结果!");
				}
		}
</script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/bootstrap-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/jquery-ui-custom.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/core.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/home.css">

	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wbm/activity.css">

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery-ui-1.8.22.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/filter.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/wbm/global.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
	<style id="qb_dict_style" type="text/css">#dict__tip {position: absolute; visibility: hidden;z-index: 50000; overflow:visible;}#dict__tip {border: 3px solid #2e9dff;padding: 0 12px 10px 12px;font-size: 12px;min-width:215px; max-width: 393px;background-color:#ffffff;box-shadow:0px 1px 8px rgba(0,0,0,0.2)}#dict__tip_translate_result {display:block;text-align:left; border: none; background: none}#dict__tip_translate_result span {margin:0px;padding:0px;line-height: 22px;font-family:Microsoft YaHei;font-size: 14px;color: #333333}#dict__tip_dict_info {border: none;background: none;position: relative;height: 30px;line-height: 30px;font-family:Microsoft YaHei,Tahoma, NSimsun, Simsun, sans-serif}#dict__tip_dict_info .dict-copyright {color: #999999;position: absolute;left: 0px;top: 0px}#dict__tip_dict_info .dict-link {position: absolute;right: 0px;top: 0px;}#dict__tip_dict_info #dict__tip_translate_link {padding-left: 10px;}#dict__tip_dict_info .dict-link a{color: #0066cc;text-decoration: none;}#dict__tip_dict_info .dict-link a:hover{text-decoration: underline;}</style>
</head>
	
	<body>
	<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="微调查">
	<a href="<%=path%>/wsh/WjObject/toWjList" target="content" onfocus="this.blur()"><span>微调查</span></a>
	</li>
</ul>
</div>
		<div class="container">
			<div class="row home-container">
				<div class="span12">
					<div class="right-content">
<h3>调查信息</h3>

<a href="<%=path%>/wsh/WjObject/toAdd" class="btn btn-success add-apply-btn">+添加新调查</a>
<div>
		<table class="table table-hover">
		<thead>
			<tr>
				<th class="span1">编号</th>
				<th>标题</th>
				<th class="span2">状态</th>
				<th class="span2">更新时间</th>
				<th class="span3" style="width: 244px;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="data" items="${list}" varStatus="obj">
						<tr>
				<td>${obj.count }</td>
				<td><a href="<%=path%>/wsh/WjObject/toWjDetail?id=${data.oid}">
					<c:if test="${data.state==2 }"><span style="color: #fff;padding: 2px 5px;background-color: #b2b2b2;">已结束</span></c:if>
					<c:if test="${data.state==1 }"><span style="color: #fff;padding: 2px 5px;background-color: #fd9e0d;">进行中</span></c:if>
					<c:if test="${data.state==0 }"><span style="color: #fff;padding: 2px 5px;background-color: #fd9e0d;">未开始</span></c:if>
					${data.title}
				</a></td>
					<c:if test="${data.state eq '1'}">
						<td>已发布</td>
						<td>${data.createtime}</td>
						<td>
							<a href="javascript:;" onclick="cancel_onClick(${data.oid},${data.state});">撤销</a>| 
							<a href="javascript:;" onclick="term_onClick(${data.oid},${data.state});">终止评议</a>|
							<a href="javascript:;" onclick="Modify_onClick(${data.oid});">修改</a>|
							<a href="javascript:;" onclick="Delete_onClick(${data.oid});">删除</a>|
							<a href="javascript:;" onclick="QuestionEdit_onClick(${data.oid},${data.state});">编辑表单</a>|
							<a href="javascript:;" onclick='preview_onClick2(${data.oid},${data.state})'>预览</a>|
							<a href="javascript:;" onclick='showResult_onClick(${data.oid},${data.state});'>查看结果</a>
						</td>
					</c:if>
					<c:if test="${data.state eq '0'}">
						<td>草稿</td>
						<td>${data.createtime}</td>
						<td>
							<a href="javascript:;" onclick="publish_onClick(${data.oid},${data.state});">发布</a>| 
							<a href="javascript:;" onclick="term_onClick(${data.oid},${data.state});">终止评议</a>|
							<a href="javascript:;" onclick="Modify_onClick(${data.oid});">修改</a>|
							<a href="javascript:;" onclick="Delete_onClick(${data.oid});">删除</a>|
							<a href="javascript:;" onclick="QuestionEdit_onClick(${data.oid},${data.state});">编辑表单</a>|
							<a href="javascript:;" onclick='preview_onClick2(${data.oid},${data.state})'>预览</a>
						</td>
					</c:if>
					<c:if test="${data.state eq '2'}">
						<td>已终止评议</td>
						<td>${data.createtime}</td>
						<td>
							<a href="javascript:;" onclick='preview_onClick2(${data.oid},${data.state})'>预览</a>|
							<a href="javascript:;" onclick='showResult_onClick(${data.oid},${data.state});'>查看结果</a>
						</td>
					</c:if>
			</tr>
			</c:forEach>
				</tbody>
	</table>
	<div>
		</div>
	</div>
					</div>
				</div>
			</div>
		</div>
		
<style>
.version-modal{
	padding-bottom:20px; 
}
.version-modal .version-model-body{
	padding: 20px;
	margin-bottom: 20px;
	max-height: 100%;
}
.version-modal .version-model-body ul,
.version-modal .version-model-body ol {
	margin: 14px 0 14px 0;
	padding: 0 0 0 40px;
}
.version-modal .version-model-body ul,
.version-modal .version-model-body ul li{
	list-style: disc;
}
.version-modal .version-model-body ol,
.version-modal .version-model-body ol li{
	list-style: decimal;
}

.version-header{
	height: 70px;
	text-align: center;
	background-color: #f3f3f3;
}
.version-publish-date{
	color: #ac7b53;
	margin-bottom: 10px;
}
.version-sure-btn{
	margin-left:40%;
	margin-right:40%;
	background-color: #ff900c;
	color: #fff;
	font-size: 13px;
	padding: 10px;
	border-radius:5px;
	cursor: pointer;
	
}
</style>

<div id="footer">
	<c:choose>
					<c:when test="${pages > 1}">
						<a href="<%=path%>/wsh/ShWbm/toWbmList?pages=${pages - 1}">上一页</a>
					</c:when>
					<c:otherwise>
						上一页
					</c:otherwise>
				</c:choose>
				第${pages}页
				<c:choose>
					<c:when test="${pages < count}">
						<a href="<%=path%>/wsh/ShWbm/toWbmList?pages=${pages + 1}">下一页</a>
					</c:when>
					<c:otherwise>
					下一页
				</c:otherwise>
				</c:choose>
				总共${count}页
</div>
</body>
</html>