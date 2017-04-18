<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
String path = request.getContextPath();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
String date= format.format(new Date());
Calendar   cal_1=Calendar.getInstance(); 
cal_1.add(Calendar.MONTH, 0);
cal_1.set(Calendar.DAY_OF_MONTH,1); 
String firstDay = format.format(cal_1.getTime());
%>
<!DOCTYPE html>
<html>
<head>
<title>校长邮箱统计</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<link href="<%=path%>/resources/js/stat/css/chosen.css" rel="stylesheet" />
<link href="<%=path%>/resources/js/stat/css/prism.css" rel="stylesheet" />
<link href="<%=path%>/resources/js/stat/css/style.css" rel="stylesheet" />
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/stat/js/chosen.jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/stat/js/prism.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/My97DatePicker/lang/zh-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<style type="text/css">
.even {
	background: white;
}

.odd {
	background: #F9F9F9;
}

table {
	*border-collapse: collapse; /* IE7 and lower */
	border-spacing: 0;
	width: 100%;
}

.bordered {
	border: solid #ccc 1px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 0 1px 1px #ccc;
	-moz-box-shadow: 0 1px 1px #ccc;
	box-shadow: 0 1px 1px #ccc;
}

#thead {
	background-color: #dce9f9;
	background-image: -moz-linear-gradient(center top, #ebf3fc, #dce9f9);
	border-top: medium none;
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}

tbody tr:hover {
	background: #fbf8e9;
	-o-transition: all 0.1s ease-in-out;
	-webkit-transition: all 0.1s ease-in-out;
	-moz-transition: all 0.1s ease-in-out;
	-ms-transition: all 0.1s ease-in-out;
	transition: all 0.1s ease-in-out;
}

.bordered td,.bordered th {
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc;
	padding: 3px;
	text-align: left;
	
}

.bordered th {
	background-color: #dce9f9;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc),
		to(#dce9f9));
	background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -moz-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -ms-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -o-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: linear-gradient(top, #ebf3fc, #dce9f9);
	-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	border-top: none;
	text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
}

.bordered td:first-child,.bordered th:first-child {
	border-left: none;
}

.bordered th:first-child {
	-moz-border-radius: 6px 0 0 0;
	-webkit-border-radius: 6px 0 0 0;
	border-radius: 6px 0 0 0;
}

.bordered th:last-child {
	-moz-border-radius: 0 6px 0 0;
	-webkit-border-radius: 0 6px 0 0;
	border-radius: 0 6px 0 0;
}

.bordered th:only-child {
	-moz-border-radius: 6px 6px 0 0;
	-webkit-border-radius: 6px 6px 0 0;
	border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
	-moz-border-radius: 0 0 0 6px;
	-webkit-border-radius: 0 0 0 6px;
	border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
	-moz-border-radius: 0 0 6px 0;
	-webkit-border-radius: 0 0 6px 0;
	border-radius: 0 0 6px 0;
}
</style>
	<!-- ECharts单文件引入 -->
	<script src="<%=path%>/resources/echarts/esl.js"></script>
	<script src="<%=path%>/resources/echarts/echarts.js"></script>
	<script type="text/javascript">
      var d1 = [];//x轴类型
      var d2 = [];//y轴:数量
      var d3 = [];//y轴:数量
	  var d3 = [];//y轴:数量
	  var d4 = [];//y轴:数量
	  var d5 = [];//y轴:数量
	  var d6 = [];//y轴:数量
	  var d7 = [];//y轴:数量
	  var d8 = [];//y轴:数量
	  var d9 = [];//y轴:数量
	 // var oid="${oid}";
	 // var qseq="${qseq}";
    function showECharts(){
    	var starttime= $('#starttimeInput').val();
		 var endtime =  $('#endtimeInput').val();
		 var lx = $('#tabInput').val();
		 var html="";
		  $.ajax({
				url :'<%=path%>/xzxx/XxXjxxb/todataxztj',
				data : {
					     id:lx,
						 stime:starttime,
						 etime:endtime,
				},
				type : "get",
				success : function(data) {
                  $('#list').html('');
				var rst = eval(data);
				$.each(rst,function(i,value) { 
					if((i+1)%2==0){
                   html+="<tr class='even'><td>"+(i+1)+"</td>"+
                   "<td>"+value.bmmc+"</td>"+
                   "<td>"+value.bxsl1+"</td>"+
                   "<td>"+value.bxsl2+"</td>"+
                   "<td>"+value.bxsl3+"</td>"+
                   "<td>"+value.bxsl4+"</td>"+
                   "<td>"+value.bxsl5+"</td>"+
                   "<td>"+value.bxsl6+"</td>"+
                   "<td>"+value.bxsl7+"</td></tr>";
					}else{
						  html+="<tr class='odd'><td>"+(i+1)+"</td>"+
	                         "<td>"+value.bmmc+"</td>"+
	                         "<td>"+value.bxsl1+"</td>"+
	                         "<td>"+value.bxsl2+"</td>"+
	                         "<td>"+value.bxsl3+"</td>"+
	                         "<td>"+value.bxsl4+"</td>"+
	                         "<td>"+value.bxsl5+"</td>"+
	                         "<td>"+value.bxsl6+"</td>"+
	                         "<td>"+value.bxsl7+"</td></tr>";
					}				
					
				})
			     $('#list').append(html);
				},
				error : function() {
					alert("error");
				}
			  });
        $.ajax({
			  type: 'get',
			  url: '<%=path%>/xzxx/XxXjxxb/getXxXjxxtj',
			 // data: {"oid":oid,"qseq":qseq},
			  async: false,  
			  dataType: 'json',
			  data:{
				     id:lx,
					 stime:starttime,
					 etime:endtime,
				 },
			  success: function(data){
			     $.each(data,function(i,value) {  
			         //类型
					 d1.push(value.bmmc);
			         //数量
			         d2.push(value.bxsl1);
			         d3.push(value.bxsl2);
			         d4.push(value.bxsl3);
			         d5.push(value.bxsl4);
			         d6.push(value.bxsl5);
			         d7.push(value.bxsl6);
			         d8.push(value.bxsl7);
			     });
			     require.config({
			         paths:{ 
			             echarts:'<%=path%>/resources/echarts/echarts',
			             'echarts/chart/bar' : '<%=path%>/resources/echarts/echarts-map',
			             'echarts/chart/line': '<%=path%>/resources/echarts/echarts-map',
			             'echarts/chart/map' : '<%=path%>/resources/echarts/echarts-map'
										}
									});

							require(
									[ 'echarts', 'echarts/chart/bar',
											'echarts/chart/line',
											'echarts/chart/map' ],
									function(ec) {
										//--- 折柱 ---
										var myChart = ec.init(document
												.getElementById('main'));
										myChart
												.setOption({
													 title : {
													        text: '校长邮箱统计',
													    },
													
													tooltip : {
														trigger : 'axis'
													},
													  legend : {
													    x:'center',y:'bottom',
														data : ['审批中','审批不通过退回','审批通过','未回复','回复未评价','已评价结束','禁用']
													},   
													grid:{
														x:'8%'
													},
													toolbox : {
														orient: 'vertical',
													    x: 'right',
													    y: 'center',
													    padding:12,
														show : true,
														feature : {
															mark : {
																show : true
															},
															dataView : {
																show : true,
																readOnly : false,
																lang :['数据视图','关闭','刷新']
															},
															magicType : {
																show : true,
																type : [
																		'line',
																		'bar' ]
															},
															restore : {
																show : true
															}
															/*
															saveAsImage : {
																show : true
															}*/
														}
													},

													  dataZoom : {
													        show : true,
													        realtime : true,
													        //orient: 'vertical',   // 'horizontal'
													       // x:40,
													        y: 60,
													        //width: 400,
													        height: 20,
													        //backgroundColor: 'rgba(221,160,221,0.5)',
													        //dataBackgroundColor: 'rgba(138,43,226,0.5)',
													        //fillerColor: 'rgba(38,143,26,0.6)',
													        //handleColor: 'rgba(128,43,16,0.8)',
													        //xAxisIndex:[],
													        //yAxisIndex:[],
													        start : 1,
													        end : 60
													    },
													    
													calculable : true,
													xAxis : [ {
														type : 'category',
														data : d1,
														boundaryGap : [ 0, 0.01 ],
														name:"部门"
														
													} ],
													yAxis : [ {
														type : 'value',
														boundaryGap : [ 0, 0.01 ],
													    name:"数量"
													} ],
													series : [ {
														name : '审批中',
														type : 'bar',
														//itemStyle : { normal: {label : {show: true}}},
														data : d2,
													},
													//1、审批中；2、审批不通过退回；3‘、审批通过；4、未回复；5、回复未评价；6、已评价结束；7、禁用
													{
														name : '审批不通过退回',
														type : 'bar',
														//itemStyle : { normal: {label : {show: true}}},
														data : d3,
													},
													{
														name : '审批通过',
														type : 'bar',
														//itemStyle : { normal: {label : {show: true}}},
														data : d4,
													},
													{
														name : '未回复',
														type : 'bar',
														//itemStyle : { normal: {label : {show: true}}},
														data : d5,
													},
													{
														name : '回复未评价',
														type : 'bar',
														//itemStyle : { normal: {label : {show: true}}},
														data : d6,
													},
													{
														name : '已评价结束',
														type : 'bar',
														// itemStyle : { normal: {label : {show: true}}},
														data : d7,
													},
													{
														name : '禁用',
														type : 'bar',
														//itemStyle : { normal: {label : {show: true}}},
														data : d8,
													}
													]
												});
									});
						}
					});
		}
	</script>
	<script type="text/javascript">
	  $(function() {
		  $(".chzn-select").chosen().change(function(){
				var html='';
				var param=$('.chzn-single').text();
				function test(){
				var str="";
				$('.chzn-select').find('option').each(function(){
					if($(this).text()==param){
						//alert(this.value);
						str +=this.value;
						//alert("str:"+str);
						return false;
					}
				});
				return str;
				}
				$('#tabInput').val(test());
		        }); 
		  
		  $('#toSubmit').click(function(){
				 var starttime= $('#starttimeInput').val();
				 var endtime =  $('#endtimeInput').val();
				 if(starttime==""||starttime==null){
					 alert('请选择开始时间！')
					 return false;
				 }
				 if(endtime==""||endtime==null){
					 alert('请选择结束时间！');
					 return false;
				 }
				 if(starttime>endtime){
					 alert('开始时间大于结束时间,不正确！');
					 return false;
				 }
				 window.location.reload();
				  
			  });
	    }); 
	</script>
</head>
<body onload="showECharts()">
<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="校长邮箱统计"><a
				href="<%=path%>/xzxx/XxXjxxb/toXxXjxxbtjPage" target="content"
				onfocus="this.blur()"><span>校长邮箱统计</span></a></li>
		</ul>
	</div>
		<div id="table"
		style="height: 40%; border: 0px solid #ccc; padding: 10px;">
		<div>
		<select class="chzn-select" data-placeholder="部门名称"
			style="width:20%;" tabindex="1">
			<option value=""></option>
			<option value="">全部</option>
			<c:forEach var="data" items="${listBm}" varStatus="status">
			<option value="${data.bmbh}">${data.bmmc}</option>
			</c:forEach>
		</select>
		<input type="text" value="" id="tabInput" style="display:none;"/>
		<label style="margin-left:8px;position:relative;bottom:9px;">开始时间:</label>
		<input style="position:relative;bottom:9px;"type="text" value="<%=firstDay%>" id="starttimeInput" onClick="WdatePicker()"/>
	    <label style="margin-left:8px;position:relative;bottom:9px;">结束时间:</label>
	    <input type="text" style="position:relative;bottom:9px;" value="<%=date%>" id="endtimeInput" onClick="WdatePicker()"/>
	    <input type="button" style="margin-left:8px;position:relative;bottom:9px;" value="查询" id="toSubmit"/>
	</div>
		<table class="bordered">
			<thead id="thead">
				<tr>
					<td height="20" style="width: 105px;">序号</td>
					<td height="20" style="width: 305px;">部门名称</td>
					<td height="20" style="width: 305px;">审批中</td>
					<td height="20" style="width: 305px;">审批不通过</td>
					<td height="20" style="width: 305px;">审批通过</td>
					<td height="20" style="width: 305px;">未回回复</td>
					<td height="20" style="width: 305px;">回复未评价</td>
					<td height="20" style="width: 305px;">已评价结束</td>
					<td height="20" style="width: 305px;">禁用</td>
				</tr>
			</thead>
			<tbody id="list">
			<c:if test="${!empty list}">
				<c:forEach var="data" items="${list}" varStatus="status">
					<c:if test="${status.index % 2 == 0}">
						<tr class="even">
							<td>${status.count}</td>
							<td>${data.bmmc}</td>
				            <td>${data.bxsl1}</td>
							<td>${data.bxsl2}</td>
							<td>${data.bxsl3}</td>
							<td>${data.bxsl4}</td>
							<td>${data.bxsl5}</td>
							<td>${data.bxsl6}</td>
							<td>${data.bxsl7}</td>
						</tr>
					</c:if>
					<c:if test="${status.index % 2 != 0}">
						<tr class="odd">
							<td>${status.count}</td>
							<td>${data.bmmc}</td>
							<td>${data.bxsl1}</td>
							<td>${data.bxsl2}</td>
							<td>${data.bxsl3}</td>
							<td>${data.bxsl4}</td>
							<td>${data.bxsl5}</td>
							<td>${data.bxsl6}</td>
							<td>${data.bxsl7}</td>
						</tr>
					</c:if>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		<br/>
		<br/>
		<div id="main"
		style="height: 305px; border: 0px solid #ccc;width:100%"></div>
	</div>
</html>