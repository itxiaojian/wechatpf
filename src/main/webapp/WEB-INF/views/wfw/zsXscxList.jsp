<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <title>学生查询</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="<%=path%>/resources/css/xzxx/favicon.ico"> 
    <link href="<%=path%>/resources/css/xzxx/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/custom.css" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/animate.css" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/style.css?v=4.1.0" rel="stylesheet">
    
   <script type="text/javascript">
		var i=0;
		
		
		function query(){
			//var keyWord = $("#code").val();
			//if(keyWord == null || keyWord == ""){
				$("#myForm").submit();
			//}else{
				//$("#myForm").submit();
			//}
		}
		
		function deleteWp(id,openId){
			if(confirm("您确定要删除吗？")){
				$.ajax({
					url: "<%=path%>/xzxx/XxXjxxb/deleteXj",
					type : 'post',
					dataType : 'json',
					data: {id:id},
					success: function(data){
						if(data != null || data != ''){
								location.href ="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId="+openId;
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
		
		//点击“加载更多之后（加载十条记录）”定位到第11、21、31条记录
		function ScrollDiv() { 
			var size=$("#size").val();
  			//alert(size);
			if(size!=null&&size!=''){
				if(size<=10){
//					document.getElementById('lidw1').scrollIntoView(true);
			}else if(size%10==0){
				var num = (parseInt(size/10-1)*10-1);
				//alert(num);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}else{
				var num = (parseInt(size/10)*10-1);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}
			}
		}
		
	</script> 
	
   <style>
   table.table-mail tr td {padding: 5px;}
   </style>

</head>

<body class="gray-bg">
<input type="hidden" name="openId" id="openId" value="${openId }">
<input type="hidden" name="size" id="size" value="${size }">
<input type="hidden" name="tj" id="tj" value="${tj }">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-3">
            </div>
            <div class="col-sm-9 animated fadeInRight">
                <div class="mail-box-header">

                    <form method="get" action="<%=path%>/wfw/ZsXscx/toXscx" id="myForm" class="pull-right mail-search">
                        <div class="input-group">
                            <input type="text" class="form-control input-sm" name="code" id="code" value="${tj }" placeholder="搜索学工号，姓名">
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-sm btn-primary"  onclick="return query();">
                                	    搜索
                                </button>
                            </div>
                        </div>
                        <input type="hidden" name="openId" id="openId" value="${openId}">
						<%-- <input type="hidden" name="lx" id="lx" value="${lx }"> --%>
                    </form>
                    <div class="mail-tools tooltip-demo m-t-md">
                        
                    </div>
                </div>
                <div class="mail-box">

                    <table class="table table-hover table-mail">
	                    <thead>
		                    <tr class="unread">
		                    <td class="mail-subject" style="color:#7266ba;width:30;">学工号</td>
		                    <td class="mail-ontact" style="color:#7266ba;width:20%;">姓名</td>
		                    <td class="text-right mail-date" style="color:#7266ba;width:40%;">院系</td>
		                    </tr>
	                    </thead>
                        <tbody>
                        <c:forEach var="data" items="${list}" varStatus="obj">
                            <tr class="read">
                                <td class="mail-subject" style="width:30%;">
                                	<c:choose>
									  <c:when test="${fn:length(data.yhbh) > 15}">
										 <c:out value="${fn:substring(data.yhbh,0,15)}..." />
									  </c:when>
									  <c:otherwise>
										 <c:out value="${data.yhbh}" />
									  </c:otherwise>
									 </c:choose>
                                 </td>
                                <td class="mail-ontact" style="width:20%;">${data.xm}</td>
                                <td class="text-right mail-date" style="width:40%;">${data.bmmc}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${empty list }">
                        	<p align="center" style="width:100%;color:red;">暂无数据！</p>
                    </c:if>

                </div>
                <div class="btn-group pull-right">
                        	<button class="btn btn-white btn-sm">第${pages}页</button>
                            <button class="btn btn-white btn-sm">共${pagecount }页</button>
                <c:choose>
					<c:when test="${pages > 1}">
						<a href="<%=path%>/wfw/ZsXscx/toXscx?pages=${pages - 1}&code=${tj}&openId=${openId}">
                            <button class="btn btn-white btn-sm">上一页</button>
                        </a>
                        </c:when>
					<c:otherwise>
	                    	<button class="btn btn-white btn-sm">上一页</button>
					</c:otherwise>
				</c:choose>
                        	
                <c:choose>
                     <c:when test="${pages <pagecount}">
						<a href="<%=path%>/wfw/ZsXscx/toXscx?pages=${pages +1}&code=${tj}&openId=${openId}">
                            <button class="btn btn-white btn-sm">下一页</button>
                        </a>
                     </c:when>
					 <c:otherwise>
	                        <button class="btn btn-white btn-sm">下一页</button>
					 </c:otherwise>
				 </c:choose>

               	</div>
            </div>
        </div>
    </div>

	<div class="gohome"><a class="animated bounceInUp" href="<%=path%>/wfw/zy/zhuye?openId=${openId}" title="返回首页"><i class="fa fa-home"></i></a></div>
    <!-- 全局js -->
    <script src="<%=path%>/resources/css/xzxx/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=path%>/resources/css/xzxx/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="<%=path%>/resources/css/xzxx/js/content.js?v=1.0.0"></script>


    <!-- iCheck -->
    <script src="<%=path%>/resources/css/xzxx/js/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>
    
</body>

</html>
