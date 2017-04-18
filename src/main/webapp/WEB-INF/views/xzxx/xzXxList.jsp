<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0,user-scalable=no;">


    <title> 校长信箱</title>
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
		function loadMore(page,lx,openId){
			i=page;
			i++;
			location.href ="<%=path%>/xzxx/XxXjxxb/toXzxxList?lx="+lx+"&pages="+i+"&openId="+openId;
		}
		function query(){
			var keyWord = $("#code").val();
			if(keyWord == null || keyWord == ""){
				alert("请输入关键词查询!");
				$("#code").focus();
				return false;
			}else{
				$("#myForm").submit();
			}
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
		
		//验证新建信息是否有未评价信息
		function xjxxbtn(){
			var openId = $('#openId').val();
			//var yhbh = $('#yhbhInput').val();
			var url = "<%=path%>/xzxx/XxXjxxb/getPj";
			$.ajax({
				cache : true,
				type : "post",
				url : url,
				data : {
					openId:openId
				},// 你的formid
				async : false,
				error : function(request) {
					alert("提交失败，请联系管理员。");
				},
				success : function(data) {
				if(data=='1'){
					window.location.href = "<%=path%>/xzxx/XxXjxxb/toXzxxAddNew?openId="+openId;
				}else if(data=='0'){
					alert("你有未评价信息!");
				 	}
				}
			});
		}
	</script> 
	
   <style>
   table.table-mail tr td {padding: 5px;}
   .fa {font-size:180%;}
   </style>

</head>

<body class="gray-bg">
<input type="hidden" name="openId" id="openId" value="${openId }">
<input type="hidden" name="size" id="size" value="${size }">
<input type="hidden" name="lx" id="lx" value="${lx }">
<input type="hidden" name="tj" id="tj" value="${tj }">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-content mailbox-content">
                        <div class="file-manager">
                            <a class="btn btn-block btn-primary compose-mail" href="javascript:void(0)" onclick="xjxxbtn()"><i class="glyphicon glyphicon-pencil"></i>我要写信</a>
                            <div class="space-25"></div>
                            <h5>校长信箱</h5>
                            <ul class="folder-list m-b-md" style="padding: 0">
                                <li>
                                    <a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}"> <i class="fa fa-inbox "></i> 全部信件<span class="label label-warning pull-right">${acount }</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?lx=1&openId=${openId}"> <i class="fa fa-envelope-o"></i> 我的信件 <span class="label label-danger pull-right">${myacount }</span></a>
                                </li>
<!--                                 <li> -->
<!--                                     <a href="mailbox.html"> <i class="fa fa-certificate"></i> 重要</a> -->
<!--                                 </li> -->
<!--                                 <li> -->
<!--                                     <a href="mailbox.html"> <i class="fa fa-file-text-o"></i> 草稿 <span class="label label-danger pull-right">2</span> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li> -->
<!--                                     <a href="mailbox.html"> <i class="fa fa-trash-o"></i> 垃圾箱</a> -->
<!--                                 </li> -->
                            </ul>

                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9 animated fadeInRight">
                <div class="mail-box-header">

                    <form method="get" action="<%=path%>/xzxx/XxXjxxb/toXzxxList" id="myForm" class="pull-right mail-search">
                        <div class="input-group">
                            <input type="text" class="form-control input-sm" name="code" id="code" value="${tj }" placeholder="搜索信件标题，内容">
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-sm btn-primary"  onclick="return query();">
                                    搜索
                                </button>
                            </div>
                        </div>
                        <input type="hidden" name="openId" id="openId" value="${openId }">
						<input type="hidden" name="lx" id="lx" value="${lx }">
                    </form>
<!--                     <h2> -->
<%--                    信件 (${size }) --%>
<!--                 </h2> -->
                    <div class="mail-tools tooltip-demo m-t-md">
                        
<!--                         <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" title="刷新邮件列表"><i class="fa fa-refresh"></i> 刷新</button> -->
<!--                         <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为已读"><i class="fa fa-eye"></i> -->
<!--                         </button> -->
<!--                         <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为重要"><i class="fa fa-exclamation"></i> -->
<!--                         </button> -->
<!--                         <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为垃圾邮件"><i class="fa fa-trash-o"></i> -->
<!--                         </button> -->

                    </div>
                </div>
                <div class="mail-box">

                    <table class="table table-hover table-mail">
	                    <thead>
		                    <tr class="unread">
		                    <td class="mail-subject" style="color:#7266ba;width:25%;">受理部门</td>
		                    <td class="mail-ontact" style="color:#7266ba;width:75%;">信件标题</td>
<!-- 		                    <td class="text-right mail-date" style="color:#7266ba;width:30%;">写信时间</td> -->
		                    </tr>
	                    </thead>
                        <tbody>
                        <c:forEach var="data" items="${list}" varStatus="obj">
                       
                            <tr class="read">
<!--                                 <td class="check-mail"> -->
<!--                                     <input type="checkbox" class="i-checks"> -->
<!--                                 </td> -->
                                <td class="mail-subject" style="width:25%;">${data.slbmmc }
                                </td>
                                <td class="mail-ontact" style="width:75%;"> 
                                <a href="<%=path%>/xzxx/XxXjxxb/toXzxxDetail?id=${data.id }&openId=${openId}&lx=${lx}&Currentpage=${Currentpage}">${data.xjbt }</a>
                                 <c:if test="${data.clzt=='1' }">
                                	<span class="label label-info pull-right">申请中</span>
                                	<c:if test="${yhbh==data.fjrbh && lx!='' }">
										<span>
											<a href="javascript:;" onclick="deleteWp('${data.id }','${openId}');">
												<img src="<%=path%>/resources/images/iconfont-shanchu.png" alt="删除" height="25px" width="25px"/>
											</a>
										</span>
									</c:if>
                                </c:if>
                                <c:if test="${data.clzt=='2' }">
                                	<span class="label label-warning pull-right">
                                		<c:if test="${yhbh==data.fjrbh && lx!='' }">
                                	  		<a href="<%=path%>/xzxx/XxXjxxb/toXzxxUpdateNew?openId=${openId}&id=${data.id}">驳回修改</a>
                                		</c:if>
                                		<c:if test="${lx=='' }">申请驳回</c:if>
                                	</span>
                                </c:if>
                                <c:if test="${data.clzt=='3' }">
                                	<span class="label label-success pull-right">审批通过</span>
                                </c:if>
                                <c:if test="${data.clzt=='4' }">
                                	<span class="label label-danger pull-right">未回复</span>
                                </c:if>
                                <c:if test="${data.clzt=='5' }">
                                	<span class="label label-danger pull-right">
                                		<c:if test="${yhbh==data.fjrbh && lx!='' }">
											<a href="<%=path%>/xzxx/XxXjxxb/toXzxxFk?openId=${openId}&id=${data.id}&lx=${lx}">回复评价</a>
										</c:if>
										<c:if test="${lx=='' }">
											回复评价
										</c:if>
                                	</span>
                                </c:if>
                                <c:if test="${data.clzt=='6' }">
                                	<span class="label label-primary pull-right">已结束</span>
                                </c:if>
                                <c:if test="${data.clzt=='7' }">
                                	<a href="javascript:;" onclick="deleteWp('${data.id }','${openId}');">
                                		<span>
                                		   <img src="<%=path%>/resources/images/iconfont-shanchu.png" alt="删除" height="25px" width="25px">
                                		</span>
                                	</a>
                                </c:if>
                                <br>
                                <span>${data.xxsj }</span>
                               
                                </td>
                                
<%--                                 <td class="text-right mail-date" style="width:30%;">${data.xxsj }</td> --%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${empty list }">
                        	<p align="center" style="width:100%;color:red;">暂无数据！</p>
                    </c:if>


                </div>
                <div class="btn-group pull-right">
                        	<button class="btn btn-white btn-sm">第${Currentpage }页</button>
                            <button class="btn btn-white btn-sm">共${pagecount }页</button>
                <c:choose>
					<c:when test="${Currentpage > 1}">
						<a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?Currentpage=${Currentpage - 1}&lx=${lx}&code=${tj}&openId=${openId}">
                            <button class="btn btn-white btn-sm">上一页</button>
                        </a>
                        </c:when>
					<c:otherwise>
	                    	<button class="btn btn-white btn-sm">上一页</button>
					</c:otherwise>
				</c:choose>
                        	
                <c:choose>
                     <c:when test="${Currentpage <pagecount}">
						<a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?Currentpage=${Currentpage +1}&lx=${lx}&code=${tj}&openId=${openId}">
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
