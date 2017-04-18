<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生选课统计</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<%=path%>/resources/js/stat/css/chosen.css" rel="stylesheet" />
<link href="<%=path%>/resources/js/stat/css/prism.css" rel="stylesheet" />
<link href="<%=path%>/resources/js/stat/css/style.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/stat/js/chosen.jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/stat/js/prism.js"></script>
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
</head>
<body>
	<!-- ECharts单文件引入 -->
	<script src="<%=path%>/resources/echarts/esl.js"></script>
	<script src="<%=path%>/resources/echarts/echarts.js"></script>
	<script type="text/javascript">
      var d1 = [];//x轴年月
	  var d2 = [];//学生数
	 // var oid="${oid}";
	 // var qseq="${qseq}";
    function showECharts(){
        $.ajax({
			  type: 'POST',
			  url: '<%=path%>/stat/echarts/tostulen',
			 // data: {"oid":oid,"qseq":qseq},
			  async: false,  
			  dataType: 'json',
			  success: function(data){
			     $.each(data,function(i,value) {  
			         //种类
					 d1.push(value.kcmc);
			         //数量
			         d2.push(value.sl);
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
													        text: '学生选课统计',
													    },
													
													tooltip : {
														trigger : 'axis'
													},
													/*  legend : {
													    x:'center',y:'bottom',
														data : [ '在校人数统计' ]
													},  */
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
															/*saveAsImage : {
																 show : true
															}*/
														}
													},

													calculable : true,
													yAxis : [ {
														type : 'value',
														boundaryGap : [ 0, 0.01 ],
													    name:"数量"
													} ],
													xAxis : [ {
														type : 'category',
														data : d1,
														boundaryGap : [ 0, 0.01 ],
														name:"种类"
														
													} ],
													series : [ {
														name : '学生选课统计',
														type : 'bar',
														data : d2,
														itemStyle : {
															normal : {
																color : function(value) {
																	return "#"	+ ("00000" + ((Math.random() * 16777215 + 0.5) >> 0)
																				.toString(16)).slice(-6);
																}
															}
														} 
													} ]
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
			     $.ajax({
					url :'<%=path%>/stat/echarts/getstulen',
					data : {
						id :test(),
							//$('.chzn-single').text(),
					},
					type : "get",
					success : function(data) {
                        $('#list').html('');
					var rst = eval(data);
					$.each(rst,function(i,value) { 
						if((i+1)%2==0){
                         html+="<tr class='even'><td>"+(i+1)+"</td>"+
                         "<td>"+value.kcmc+"</td>"+
                         "<td>"+value.sl+"</td></tr>";
						}else{
							  html+="<tr class='odd'><td>"+(i+1)+"</td>"+
		                         "<td>"+value.kcmc+"</td>"+
		                         "<td>"+value.sl+"</td></tr>";
						}				
						
					})
				     $('#list').append(html);
					},
					error : function() {
						alert("error");
					}
				  });
		        }); 
	    }); 
	</script>
</head>
<body onload="showECharts()">
	<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="学生选课统计"><a
				href="<%=path%>/stat/echarts/lession" target="content"
				onfocus="this.blur()"><span>学生选课统计</span></a></li>
		</ul>
	</div>
	<div id="table"
		style="height: 40%; border: 0px solid #ccc; padding: 10px;">
		<div>
		<select class="chzn-select" data-placeholder="课程名称"
			style="width:40%;" tabindex="1">
			<option value=""></option>
			<option value="">全部</option>
			<c:forEach var="data" items="${listBm}" varStatus="status">
			<option value="${data.kcbh}">${data.kcmc}</option>
			</c:forEach>
		</select>
	</div>
		<table class="bordered">
			<thead id="thead">
				<tr>
					<td height="20" style="width: 305px;">序号</td>
					<td height="20" style="width: 305px;">课程名称</td>
					<td height="20" style="width: 305px;">数量</td>
				</tr>
			</thead>
			<tbody id="list">
			<c:if test="${!empty list}">
				<c:forEach var="data" items="${list}" varStatus="status">
					<c:if test="${status.index % 2 == 0}">
						<tr class="even">
							<td><font size="2px">${status.count}</font></td>
							<td><font size="2px">${data.kcmc}</font></td>
							<td><font size="2px">${data.sl}</font></td>
						</tr>
					</c:if>
					<c:if test="${status.index % 2 != 0}">
						<tr class="odd">
							<td style=""><font size="2px">${status.count}</font></td>
							<td><font size="2px">${data.kcmc}</font></td>
							<td><font size="2px">${data.sl}</font></td>
						</tr>
					</c:if>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<br/>
	<br/>
	<div id="main"
		style="height: 305px; border: 0px solid #ccc;width:100%"></div>
</body>
</html>