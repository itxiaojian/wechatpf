<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% 
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="keywords" content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link href="http://csdnimg.cn/www/images/favicon.ico" rel="shortcut icon">

  <title>${setting['app'] }</title>

  <!--icheck-->
  <link href="<%=path%>/resources/newbootstrap/js/iCheck/skins/minimal/minimal.css" rel="stylesheet">
  <link href="<%=path%>/resources/newbootstrap/js/iCheck/skins/square/square.css" rel="stylesheet">
  <link href="<%=path%>/resources/newbootstrap/js/iCheck/skins/square/red.css" rel="stylesheet">
  <link href="<%=path%>/resources/newbootstrap/js/iCheck/skins/square/blue.css" rel="stylesheet">

  <!--dashboard calendar-->
  <link href="<%=path%>/resources/newbootstrap/css/clndr.css" rel="stylesheet">

  <!--Morris Chart CSS -->
  <link rel="stylesheet" href="<%=path%>/resources/newbootstrap/js/morris-chart/morris.css">

  <!--common-->
  <link href="<%=path%>/resources/newbootstrap/css/style.css" rel="stylesheet">
  <link href="<%=path%>/resources/newbootstrap/css/style-responsive.css" rel="stylesheet">
  
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
<link href="<%=path%>/system/layout/skin/style.css" rel="stylesheet" type="text/css" id="skin"  skinPath="system/layout/skin/"/>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/main.js"></script>
   
<!--引入弹窗组件start-->
<script type="text/javascript" src="<%=path%>/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/popup/dialog.js"></script>
<!--引入弹窗组件end-->


<!--分隔条start-->
<%-- <script type="text/javascript" src="<%=path%>/libs/js/nav/spliter.js"></script> --%>
<!--分隔条end-->


<script type="text/javascript">

function toRight(url,fname,sufname){
	$("#fname").html(fname);
	$("#sufname").html(sufname);
	top.content.location=url;
}
	
</script>
<style type="text/css">

#_Container_0 {
	background: white;
}

.page-heading {
    padding: 5px;
    position: relative;
    padding-top: 10px;
}

.wrapper {
    padding: 5px;
}

</style>


  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="<%=path%>/resources/newbootstrap/js/html5shiv.js"></script>
  <script src="<%=path%>/resources/newbootstrap/js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="sticky-header">

<section>    

	    <!-- left side start-->
    <div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            <a href="javascript:;"><img src="<%=path%>/resources/images/logo.png" alt=""></a>
        </div>

        <div class="logo-icon text-center">
            <a href="javascript:;"><img src="<%=path%>/resources/images/logo_icon.png" alt=""></a>
        </div>
        <!--logo and iconic logo end-->

        <div class="left-side-inner">

            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li class="active"><a href="<%=path%>/system/layout/main.jsp"><i class="fa fa-home"></i> <span>主页</span></a></li>
                
                <c:forEach var="fun" items="${CurrentUserMenu}" varStatus="status">
 					<c:if test="${fun.sjcd=='0'}">
	                   	<sec:authorizeUrl id="${fun.cdbh}">
 						<li class="menu-list">
                      		<a href="javascript:;" >
	                          		<i class="fa ${fun.bz}"></i>
	                          		<span>${fun.cdmc}</span>
                      		</a>
	                      	<ul class="sub-menu-list">
	                      		<c:forEach var="subFun" items="${CurrentUserMenu}" varStatus="sub">
			                      		<c:if test="${subFun.sjcd==fun.cdbh}">
			                      			<sec:authorizeUrl id="${subFun.cdbh}">
			                      			<c:if test="${subFun.cdurl!=''&&subFun.cdurl != null}">
			                      				<li>
			                      					<a href="javascript:;" onclick="toRight('<%=path%>${subFun.cdurl}','${fun.cdmc}','${subFun.cdmc}');">
			                      						<i class="fa ${subFun.bz}"></i>
			                      						<span>${subFun.cdmc}</span>
			                      					</a>
			                      				</li>
			                      			</c:if>
			                      			</sec:authorizeUrl>
			                      		</c:if>
	                      		</c:forEach>
				            </ul>
                      	</li>
	                    </sec:authorizeUrl>
 					</c:if>
 				</c:forEach>
             </ul>
            <!--sidebar nav end-->
        </div>
    </div>
    <!-- left side end-->
	
    <!-- main content start-->
    <div class="main-content" >

        <!-- header section start-->
        <div class="header-section">

            <!--toggle button start-->
            <a class="toggle-btn"><i class="fa fa-bars"></i></a>
            <!--toggle button end-->

            <!--search start-->
            <form class="searchform" action="#" method="post">
               <!--  <input type="text" class="form-control" name="keyword" placeholder="检索..." /> -->
            </form>
            <!--search end-->

            <!--notification menu start -->
            <div class="menu-right">
                <ul class="notification-menu">
                	<li style="background-color:#FFFFFF;margin-bottom:2px; margin-top:8px;" >
							<i>
								<div style="text-align:center;padding:4px;font-style: normal;" >
									<script>
										var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
										var now = new Date();
										var year=now.getFullYear();
										var month=now.getMonth()+1;
										var day=now.getDate()
										var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
										document.write(currentime)
									</script>
								</div>
							</i>
					</li>
					<li style="background-color:#FFFFFF;margin-bottom:5px; margin-top:4px;" >
							<i>
								<iframe allowtransparency="true" frameborder="0" width="145px" height="30px" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=3&z=3&t=0&v=0&d=3&bd=0&k=000000&f=&q=1&e=1&a=1&c=54511&w=180&h=36&align=center"></iframe>
							</i>
					</li>
                    <li>
                        <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                            <i class="fa fa-tasks"></i>
                            <span class="badge">0</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-head pull-right">
                            <h5 class="title">您有 0 条任务未完成</h5>
                            <ul class="dropdown-list user-list">
                               <!--  <li class="new">
                                    <a href="#">
                                        <div class="task-info">
                                            <div>界面优化</div>
                                        </div>
                                        <div class="progress progress-striped">
                                            <div style="width: 40%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-warning">
                                                <span class="">40%</span>
                                            </div>
                                        </div>
                                    </a>
                                </li> -->
                                <li class="new"><a href="">查看所有未完成任务</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                            <i class="fa fa-envelope-o"></i>
                            <span class="badge">0</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-head pull-right">
                            <h5 class="title">您有 0 条邮件信息 </h5>
                            <ul class="dropdown-list normal-list">
                                <li class="new"><a href="">读取所有邮件</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                            <i class="fa fa-bell-o"></i>
                            <span class="badge">0</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-head pull-right">
                            <h5 class="title">您有 0 条提醒信息</h5>
                            <ul class="dropdown-list normal-list">
                                <li class="new"><a href="">查看 所有 提醒信息</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <img src="<%=path%>/resources/images/icon11.png" alt="" />
                            <sec:authentication property="principal.xm"/>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                            <li><a href="javascript:;" onclick='top.Dialog.confirm("确定要退出系统吗?",function(){window.location="<%=path%>/j_spring_security_logout"});'><i class="fa fa-sign-out"></i>退出系统</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
            <!--notification menu end -->

        </div>
        <!-- header section end-->

        <!-- page heading start-->
        <div class="page-heading">
            <ul class="breadcrumb">
                <li>
                    <a href="javascript:;" ><span id="fname" style="font-size: 14px;font-family:微软雅黑 ">主页</span></a>
                </li>
                <li class="active">
                	<span id="sufname" style="font-size: 14px;font-family:微软雅黑 ">我的主页</span>
                </li>
            </ul>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper" style="height: 615px" >
            <IFRAME width="100%" height="100%" id="content" name="content" frameborder="0" contenteditable="true" src="<%=path%>/sys/main/mainpage"></IFRAME>
        </div>
        <!--body wrapper end-->

        <!--footer section start-->
        <footer>
          	 版权所有：${setting['copyright'] }
        </footer>
        <!--footer section end-->


    </div>
    <!-- main content end-->
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="<%=path%>/resources/newbootstrap/js/jquery-1.10.2.min.js"></script>
<script src="<%=path%>/resources/newbootstrap/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="<%=path%>/resources/newbootstrap/js/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=path%>/resources/newbootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/resources/newbootstrap/js/modernizr.min.js"></script>
<script src="<%=path%>/resources/newbootstrap/js/jquery.nicescroll.js"></script>

<!--common scripts for all pages-->
<script src="<%=path%>/resources/newbootstrap/js/scripts.js"></script>

<!--Dashboard Charts-->
<script src="<%=path%>/resources/newbootstrap/js/dashboard-chart-init.js"></script>


</body>
</html>
