<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script src="<%=path%>/resources/echarts/esl.js"></script>
	<script src="<%=path%>/resources/echarts/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">

	  var d1 = [];//问题选项
	  var d2 = [];//选择数
	  var oid="${oid}";
	  var qseq="${qseq}";
    function showECharts(){
        $.ajax({
			  type: 'POST',
			  url: '<%=path%>/wsh/WjObject/getWjXzBt',
			  data: {"oid":oid,"qseq":qseq},
			  async: false,  
			  dataType: 'json',
			  success: function(data){
			     $.each(data,function(i,value) {  
			         //问题选项
					 d1.push(value.content);
			         //选择数
			         d2.push({value:value.bttx,name:value.content});
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
			         [
			             'echarts',
			             'echarts/chart/bar',
			             'echarts/chart/line',
			             'echarts/chart/map'
			         ],
			         function(ec) {
			             //--- 折柱 ---
			             var myChart = ec.init(document.getElementById('main'));
			             myChart.setOption({
			            	 tooltip : {
			            	        trigger: 'item',
			            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
			            	 },
			            	 legend: {
			            	        orient : 'vertical',
			            	        x : 'left',
			            	        data:d1
			            	 },
			            	 toolbox: {
			            	        show : true,
			            	        feature : {
			            	            //mark : {show: true},
			            	            dataView : {show: true, readOnly: false},
			            	            restore : {show: true},
			            	            saveAsImage : {show: true}
			            	        }
			            	 },
			                 calculable : true,
			                 series : [
			                           {
			                               name:'选择数',
			                               type:'pie',
			                               radius : '50%',
			                               center: ['50%', 200],
			                               data:d2
			                           }
			                 ]
			             });
			         }
			     );
			  }
			});

	}

    
    </script>
  </head>
  
 <body onload="showECharts()">
    <div id="main" style="height:305px;border:1px solid #ccc;padding:10px;"></div>
</body>
</html>