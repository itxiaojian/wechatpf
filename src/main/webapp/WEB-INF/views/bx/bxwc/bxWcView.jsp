<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>报修结束</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/css/aero.css" rel="stylesheet" />
<link href="<%=path%>/resources/css/bxsh.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>

<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/jquery_002.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/jquery_003.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/iframeTools.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/artDialog.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bx/bxsh/public.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script type="text/javascript">
 jQuery(function() {
	 var length =20;
		//alert($(".bxbh").size());
		//alert($(".nrtd0").text().length);
		for(var i =0;i<$(".bxbh").size();i++){
			if($(".nrtd"+i).text().length>10){
				$(".nrtd"+i).text($('.nrtd'+i).text().substring(0, 5) + "...");
			}
			if($(".pjtd"+i).text().length>10){
				$(".pjtd"+i).text($('.pjtd'+i).text().substring(0, 5) + "...");
			}
			if($(".bxbhtd"+i).text().length>18){
				$(".bxbhtd"+i).text($('.bxbhtd'+i).text().substring(0, 18) + "...");
			}
		}
	 
	/*  $("#F_XQDM").change(function(){
		 //alert(1);
		 alert($("#F_LYDM").val());
		});
	 var a = $('#F_LYDM option:selected').text(); */
	 
	 $("#Button1").click(function() {
		 var bm;
		 bm= $('#SearchStr').val();
		 if(bm=="单据号/用户名/联系电话")
		 {bm="";}
		 var bxzt= $('#bxztInput').val();
		 var wxdl = $('#wxdlInput').val();
	window.location.href = "<%=path%>/bx/bxwc/wclist?bm=" + bm + "&bxzt="+bxzt+"&wxdl="+wxdl;
	});
	
	//审核选中
	$("#Button3").click(function() {
		var dmlist = getidlist();
		if (dmlist == "") {
			return;
		}
		succCheckBox(dmlist);
	});
	
	//驳回选中
	$("#Button4").click(function() {
		var dmlist = getidlist();
		if (dmlist == "") {
			return;
		}
		rejecCheckBox(dmlist);
	});
 });
 
//获取选中项ID列表
	function getidlist() {
		var v = "";
		$("#GridView1 tr:gt(0)").each(
				function() {
					check = $(this).find("td:nth-child(1) input:checkbox")
							.attr("checked");
					id = $(this).find("td:nth-child(4)").text();
					if (check) {
						v += $.trim(id) + ",";
					}
				});
		return $.trim(v);
	}
	
	//全选
	function checkall(checked) {
		$("#GridView1 input:checkbox").each(function() {
			$(this).attr("checked", checked ? true : false);
		});
	}
	
	//审核
	function review(dm) {
		window.location.href = "<%=path%>/bx/bxwc/makesh?dm="+dm;
	}
	

	function see(dm){
		art.dialog.open('<%=path%>/bx/bxwc/see?id='+dm,{
			//id:'detailDialog',
			title:'报修结束查看信息',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 120,
	        width:$(document.body).width() - 300,
	        top:100,
	        ok:false,
	        top:20,
	        cancel:true
	    }); 
	} 
	
	function succ(dm){
		art.dialog.open('<%=path%>/bx/bxwc/succ?id='+dm,{
			//id:'detailDialog',
			//title:'申请查看',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			title:'报修结束信息',
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 250,
	        width:$(document.body).width() - 600,
	        top:100,
	        top:20,
	        ok:function(){
	        	var time = this.iframe.contentWindow.$('#F_WXYS').val();
	        	var qc = this.iframe.contentWindow.$('#F_WXHC').val();
	        	$.ajax({
					url:"<%=path%>/bx/bxwc/succCon",
					type : 'get',
					//dataType : 'json',
					data:{
						id:dm,
						time:time,
						qc:qc,
					},
					success: function(){
					window.location.href="<%=path%>/bx/bxwc/cllist";
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
					}
				});
	        },
	        cancel:true
	    }); 
	}
	
	function succCheckBox(dm){
		if(confirm("您确定结束吗？")){
			$.ajax({
				url:'<%=path%>/bx/bxwc/succCheckBox',
				type : 'get',
				//dataType : 'json',
				data:{
					idList:dm,
				},
				success: function(){
				window.location.href="<%=path%>/bx/bxwc/wclist";
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
			 }
	}
	

	function deal(dm){
		if(confirm("您确定结束 吗？")){
			$.ajax({
				url:'<%=path%>/bx/bxwc/deal',
				type : 'get',
				//dataType : 'json',
				data:{
					id:dm,
				},
				success: function(){
				window.location.href="<%=path%>/bx/bxwc/wclist";
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
			 }
	}
	
	function rejec(dm){
		art.dialog.open('<%=path%>/bx/bxwc/rejec?id='+dm,{
			//id:'detailDialog',
			//title:'申请查看',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			title:'报修结束驳回信息',
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 200,
	        width:$(document.body).width() - 600,
	        top:100,
	        top:20,
	        ok:function(){
	        	var str = this.iframe.contentWindow.$('#areadropboxvalue').val();
	        	$.ajax({
					url:"<%=path%>/bx/bxwc/rejecCon",
					type : 'get',
					//dataType : 'json',
					data:{
						id:dm,
						str:str,
					},
					success: function(){
					window.location.href="<%=path%>/bx/bxwc/wclist";
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
					}
				});
	        },
	        cancel:true
	    }); 
	}

	function rejecCheckBox(dm){
		art.dialog.open('<%=path%>/bx/bxwc/rejec?id='+dm,{
			//id:'detailDialog',
			//title:'申请查看',
			//titleClass: 'dialog_diy',
			//content: document.getElementById('detail'),
			title:'报修结束驳回信息',
			lock:true, 
			padding: '4px 0px',
	        height:$(document.body).height() - 200,
	        width:$(document.body).width() - 600,
	        top:100,
	        top:20,
	        ok:function(){
	        	var str = this.iframe.contentWindow.$('#areadropboxvalue').val();
	        	$.ajax({
					url:"<%=path%>/bx/bxwc/rejecCheckBox",
					type : 'get',
					//dataType : 'json',
					data:{
						idList:dm,
						str:str,
					},
					success: function(){
					window.location.href="<%=path%>/bx/bxwc/wclist";
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
					}
				});
	        },
	        cancel:true
	    }); 
	}
	function getWxdl(value){
		$.ajax({
			cache : true,
			type : "get",
			url : "<%=path%>/bx/bxwc/getBxzt",
			data : {id:value},
			async : false,
			error : function(request) {
				alert("连接数据库失败，请联系管理员。");
			},
			success : function(data) {
				$("#wxxl").empty();
                $("<option value='-1'>请选择---</option>").appendTo($("#wxxl"));  
                $.each(eval(data), function(i, item) {
                   /*  $("<option value='" + item.yhbh+ "'>" + item.xm + "</option>").appendTo($("#wxxl")); */ 
                	  $("<option value='"+item.sbztmc+"'>" + item.sbztmc + "</option>").appendTo($("#wxxl"));
                	  $('#wxdlInput').val();
              		  $('#wxdlInput').val(value);
                });
			}
		}); 
	}
	
	function getBxzt(value){
		$('#bxztInput').val();
		$('#bxztInput').val(value);
	}
</script>
</head>
<body id="mainbody" style="overflow-y: scroll;" class="objbody1">
		<div style="display:none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="报修结束">
				<a href="<%=path%>/bx/bxwc/wclist" target="content" onfocus="this.blur()"><span>报修结束</span></a>
			</li>
		</ul>
	</div>
	<div class="maindiv">
	<div class="tl pl10">
            <div class="fl">
            <label>维修大类:</label>
             <input type="text" value="" id="wxdlInput"style="display:none;">
                <select name="F_XQDM" id="F_XQDM" onChange="getWxdl(this.value)" class="TextAreaDrop" style="width:100px;">
					<option value="-1" >请选择---</option>
					<c:forEach var="data" items="${wxlist}" varStatus="obj">
					<option value="${data.ztmc}">${data.ztmc}</option>
					</c:forEach>
				</select>
				</div>
			</div>
			<div class="tl pl10">
            <div class="fl" style="margin-left:8px;">
            <label>报修主题:</label>
            <input type="text" value="" id="bxztInput"style="display:none;">
                <select name="F_XQDM" id="wxxl"  onChange="getBxzt(this.value)" class="TextAreaDrop" style="width:100px;">
					<option value="-1">请选择---</option>
				</select>
				</div>
			</div>
		<div class="tl pl10">
			<div class="fl pl10">
				<input name="SearchStr" value="单据号/用户名/联系电话" id="SearchStr"
				 onfocus="this.value=''" onblur="if(this.value==''){this.value='单据号/用户名/联系电话'}" 
					class="InputNormal" style="width: 180px;" type="text">
			</div>
			<div class="fl pl10 aui_button" style="padding: 0; margin: 0;">
				<input name="Button1" value="检 索" id="Button1"
					class="button aui_state_highlight" type="button">
			</div>
			<div class="clear"></div>
		</div>
		<div class="acbodydiv">
			<div>
							<table class="gridview_table" id="GridView1" style="width: 100%;"
					border="0" cellspacing="1">
					<tbody>
						<tr>
							  <th scope="col"><input id="GridView1_ctl01_allcheck"
								name="GridView1$ctl01$allcheck"
								onclick='javascript:checkall($(this).attr("checked"));'
								type="checkbox"></th> 
								<th scope="col">序号</th>
								<th scope="col">申请单号</th>
						  <th scope="col" style="display:none;">Id</th>
							<th scope="col">申请人</th>
							<th scope="col">联系电话</th>
							<th scope="col">楼宇名称</th>
							<th scope="col">维修大类</th>
							<th scope="col">报修主题</th>
							<th scope="col">服务</th>
							<th scope="col">满意度</th>
							<th scope="col">评论</th>
							<th scope="col">预约时间</th>
							<th scope="col">状态</th>
							<th scope="col">管理操作</th>
						</tr>
						 <c:if test="${!empty list }">
						 <c:forEach var="data" items="${list}" varStatus="status">
						 <tr>
							<td style="width:2%;">
							<input id="GridView1_ctl02_CheckBox1" 
								name="GridView1$ctl02$CheckBox1" type="checkbox"></td>  
						    <td style="width:3%;text-align:center;">${status.count }</td>
						    <td style="width:10%;" class="bxbh bxbhtd${status.index}" title="${data.bxbh}">${data.bxbh}</td>
						    <td style="width:10%;display:none;">${data.id}</td>
							<td style="width: 5%;">${data.sqrxm}</td>
							<td style="width: 6%;">${data.lxhm}</td>
							<td style="width: 12%;">${data.ly }</td>
							<td style="width: 8%;">${data.wxdl }</td>
							<td style="width: 6%;">${data.sbztmc}</td>
							<td style="width: 4%;">${data.fw }</td>
						    <c:choose>
							<c:when test="${data.myd==3}">
						    <td style="width: 8%;color:red;">${data.mydmc}</td>
							</c:when>
							<c:otherwise>
							 <td style="width: 8%;">${data.mydmc}</td>
							</c:otherwise>
							</c:choose>
							<c:choose>
							<c:when test="${!empty data.pj}">
							<td style="width: 10%;" class="pjtd${status.index }" title="${data.pj }">${data.pj}</td>
							</c:when>
							<c:otherwise>
							<td style="width: 10%;">否</td>
							</c:otherwise>
							</c:choose>
							<td style="width: 7%;">${data.yysj }</td>
							<td style="width:5%;"><font color="#00FF1E">${data.zt }</font></td>
							<td style="width: 17%;text-align:center;"> <a id="GridView1_ctl02_HyperLink4"
								onclick='javascript:see($(this).attr("alt"))'
								alt="${data.id}" href="#">查看</a>
								<a id="GridView1_ctl02_HyperLink4"
								onclick='javascript:deal($(this).attr("alt"))'
								alt="${data.id}" href="#">结束</a>
								<a id="GridView1_ctl02_HyperLink4"
								onclick='javascript:rejec($(this).attr("alt"))'
								alt="${data.id}" href="#">驳回</a>
								</td>
								</tr>
								</c:forEach>
								</c:if>
					</tbody>
				</table>
			</div>
				<c:if test="${empty list }">无记录！</c:if>
				<div class="aui_button"  style="margin-top:0px;">
            <input id="Button3" class="button aui_state_highlight" value="结束" type="button">&nbsp;
            <input id="Button4" class="button" value="驳回" type="button">
        </div>
		</div>
    </div>
    <div id="footer" style="text-align:center;">
	<c:choose>
					<c:when test="${pages > 1}">
						<a href="<%=path%>/bx/bxwc/wclist?pages=${pages - 1}">上一页</a>
					</c:when>
					<c:otherwise>
						上一页
					</c:otherwise>
				</c:choose>
				第${pages}页
				<c:choose>
					<c:when test="${pages < count}">
						<a href="<%=path%>/bx/bxwc/wclist?pages=${pages + 1}">下一页</a>
					</c:when>
					<c:otherwise>
					下一页
				</c:otherwise>
				</c:choose>
				总共${count}页
</div>
</body>
</html>