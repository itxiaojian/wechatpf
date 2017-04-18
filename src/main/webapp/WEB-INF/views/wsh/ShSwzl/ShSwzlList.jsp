<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide{display:none !important;}ng\:form{display:block;}.ng-animate-block-transitions{transition:0s all!important;-webkit-transition:0s all!important;}.ng-hide-add-active,.ng-hide-remove{display:block!important;}</style>
	<meta charset="utf-8">
	<title>失物招领</title>
	<meta name="viewport" content="user-scalable=no,width=device-width">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<link href="<%=path%>/resources/css/common.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=path%>/resources/js/wsh/styles.css">
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript">
		var i=0;
		function loadMore(page,lx,openId){
			i=page;
			i++;
			location.href ="<%=path%>/wsh/ShSwzl/toSwzlList?lx="+lx+"&pages="+i+"&openId="+openId;
		}
		function query(){
			var keyWord = $("#bt").val();
			if(keyWord == null || keyWord == ""){
				alert("请输入关键词查询!");
				return false;
			}else{
				$("#myForm").submit();
			}
		}
		function deleteWp(id,openId){
			if(confirm("您确定要删除吗？")){
				location.href ="<%=path%>/wsh/ShSwzl/delete?id="+id+"&openId="+openId;
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
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.span_hz{ float:left; font-size:11px;margin-top:0.1%;}
/* .top_01 img{ margin-top:15%; display:block; float:left;} */
.fanhui{margin-top: 10px; float: right; margin-right: 5px; width: 25px;}
.span_input{margin-top:-14%;width:65%;height:50%;margin-left:35%;}
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ width:32%; height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_01 img{ float:left; margin-top:10%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
</style>
</head>

<body class="s-app ng-scope s-app--inApp" ng-controller="MainCtrl as app" ng-class="{
      &#39;s-app--deskTop&#39;: app.isDeskTop,
      &#39;s-app--inApp&#39;: app.isInnerApp,
      &#39;s-app--ios&#39;: app.isIos7 }" onload="ScrollDiv();">
<input type="hidden" name="size" id="size" value="${size }">
	<!-- 启动页 -->
<!-- <div class="bg1" > -->
<!-- 	<div class="style1"> -->
<!-- 		<div class="wwx_f_l" style="position: relative;top: 4px;"> -->
<%-- 			<a class="font1" href="<%=path%>/wsh/ShSwzl/toSwzlList?openId=${openId}">失物招领</a> --%>
<!-- 		</div> -->
<%-- 		<form action="<%=path%>/wsh/ShSwzl/toSwzlList" id="myForm" class="wwx_f_l" style="width: 55%;margin-right: 10px;" method="post"> --%>
<!-- 			<input type="text" class="input1" name="bt" id="bt" style="margin-left: 10px;width: 80px;"> -->
<%-- 			<input type="hidden" name="openId" id="openId" value="${openId }"> --%>
<%-- 			<input type="hidden" name="lx" id="lx" value="${lx }"> --%>
<!-- 			<span><a class="font1" href="javascript:;" style="margin-left: 10px;" onclick="query();">查询</a></span> -->
<!-- 			</form> -->
<!-- 				<div class="wwx_clear"></div> -->
<!-- 				<div class="anniu" style="left:90%;top:15%;" > -->
<%-- 				<a style="float:right;width:40px;height:50px;" href="<%=path%>/wsh/zy/zhuye?openId=${openId}" > --%>
<%-- 			   <img style="width:70%" src="<%=path%>/resources/img/wfwzy.png" > --%>
<!-- 			   </a> -->
<!-- 			      </div> -->
<!-- 	</div> -->
<!-- </div> -->

<div class="top_01">
		<span class="span_hz" style="width:30%; padding-left:5%;margin-top: 12px;">
        	<a class="font1" href="<%=path%>/wsh/ShSwzl/toSwzlList?openId=${openId}" style="font-size: 14px;">失物招领</a>&nbsp;&nbsp;
        </span>
		<form action="<%=path%>/wsh/ShSwzl/toSwzlList" id="myForm" class="wwx_f_a"  method="post" style="display: inline;">
			<input type="text" class="inputhaha" name="code" id="code" style="margin-top: 10px;height: 23px;" >
			<input type="hidden" name="openId" id="openId" value="${openId }">
			<input type="hidden" name="size" id="size" value="${size }">
		</form>
		<span><a class="font1" href="#" style="margin-left: 10px;font-size: 14px;" onclick="query();">查询</a></span>
		<span style="width:10%">
        	<a href="<%=path%>/wsh/zy/zhuye?openId=${openId}">
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fanhui.png">
            </a>
        </span>
</div>

	<div class="s-app__hd action-bar ng-scope" ng-hide="actionBar.hide" ng-controller="ActionBarCtrl as actionBar" style="margin-top: 37px;background-color: white;">


		<h1 class="action-bar__tie" ng-switch="actionBar.viewList.length">
			<c:if test="${lx=='2'}">
				<span class="action-bar__tie__norm ng-binding ng-scope" ng-switch-when="1" ng-bind="actionBar.viewList[0].name">招领</span>
			</c:if>	
			<c:if test="${lx=='1'}">
				<span class="action-bar__tie__norm ng-binding ng-scope" ng-switch-when="1" ng-bind="actionBar.viewList[0].name">失物</span>
			</c:if>
			<c:if test="${lx==''||lx==null}">
				<span class="action-bar__tie__norm ng-binding ng-scope" ng-switch-when="1" ng-bind="actionBar.viewList[0].name">全部</span>
			</c:if>	
		</h1>

	</div>

	<div class="s-app__bd ng-scope" ng-view="" ng-class="{ &#39;app-animate&#39;: app.isEnabledAnimation }"><div class="dock-fill p-lostFound ng-scope" ng-class="{&#39;p-gHelp-mini&#39;: helpList.bMiniList}" ng-switch="helpList.listStateType">

	<div class="-hd ng-scope" ng-switch-default="" style="margin-top: 37px;">

		<ul class="-list01">
			<li class="-list01-item" ng-class="{
			        &#39;active&#39;: helpList.isFound
			    }">
				<a href="<%=path%>/wsh/ShSwzl/toSwzlList?openId=${openId}">
					<span class="-list01-item__main" <c:if test="${lx=='' }">style="background: #f06b6b none repeat scroll 0 0;"</c:if> >
<!-- 						<i class="-list01-item__icon fa fa-smile-o"></i> -->
						<img alt="" src="<%=path%>/resources/js/wsh/qb.png" width="50px" height="50px">
					</span>
					<span class="-list01-item__intro">
						全部
					</span>
				</a>
			</li>
			<li class="-list01-item" ng-class="{
			        &#39;active&#39;: helpList.isLost
			    }">
				<a href="<%=path%>/wsh/ShSwzl/toSwzlList?lx=2&openId=${openId}">
					<span class="-list01-item__main" <c:if test="${lx=='2' }">style="background: #f06b6b none repeat scroll 0 0;"</c:if> >
<!-- 						<i class="-list01-item__icon fa fa-heart"></i> -->
						<img alt="" src="<%=path%>/resources/js/wsh/getwp.png" width="50px" height="50px">
					</span>
					<span class="-list01-item__intro">
						招领
					</span>
				</a>
			</li>
			<li class="-list01-item">
				<a href="<%=path%>/wsh/ShSwzl/toSwzlList?lx=1&openId=${openId}">
					<span class="-list01-item__main" <c:if test="${lx=='1' }">style="background: #f06b6b none repeat scroll 0 0;"</c:if> >
<!-- 						<i class="-list01-item__icon fa fa-plus-circle"></i> -->
						<img alt="" src="<%=path%>/resources/js/wsh/lose.png" width="50px" height="50px">
					</span>
					<span class="-list01-item__intro">
						失物
					</span>
				</a>
			</li>
			<li class="-list01-item">
				<a href="<%=path%>/wsh/ShSwzl/toSwzlAdd?openId=${openId}">
					<span class="-list01-item__main" >
<!-- 						<i class="-list01-item__icon fa fa-search"></i> -->
						<img alt="" src="<%=path%>/resources/js/wsh/addswzl.png" width="50px" height="50px">
					</span>
					<span class="-list01-item__intro">
						发布
					</span>
				</a>
			</li>

		</ul>

	</div>

	<!-- ngSwitchWhen: mineList -->

	<div class="-bd scroll-y" id="panelBodyWrapper-5" scroll-full="changedPos" style="padding: 160px 0 0;">

		<ul class="-list02">
			<c:forEach var="data" items="${list}" varStatus="obj">
			<li id="lidw${obj.count }" class="-list02-item ng-scope" bindonce="" ng-click="helpList.viewRecord(record)" ng-repeat="record in helpList.recordList">
					<div class="-list02-item__hd" style="color: #333;">
						<strong bo-bind="record.questUserName">${data.fbrxm }</strong>
						发表于 <span bo-bind="record.questTime">${data.fqsj }</span>
						<c:if test="${data.fbr==openId }">
							<a href="javascript:;" onclick="deleteWp('${data.id }','${openId}');">
								<span  style="text-align: right;" >
									<img src="<%=path%>/resources/images/iconfont-shanchu.png" alt="删除" height="25px" width="25px" style="margin-bottom: 5px;"/>
								</span>
							</a>
						</c:if>
					</div>
			<a href="<%=path%>/wsh/ShSwzl/toSwzlDetail?id=${data.id }&openId=${openId}" style="text-decoration:none;color:black;">
					<h3 class="-list02-item__tie ng-binding">
						“${data.bt }”
					</h3>
					<div class="-list02-item__bd">
						<span class="-list02-item__sub ng-binding ng-hide" ng-show="record.state" ng-bind="record.state"></span>
						<div class="-list02-item__main">

							<span class="-label-location ng-binding" ng-show="record.location" ng-bind="record.location">${data.dd }</span>
							<span ng-bind="record.context" class="ng-binding" style="color: #666;">${data.xwzs }</span>
							
					   </div>
					</div>
					<div class="-list02-item__ft">
						
					</div>
			</a>
			</li>
			</c:forEach>
		</ul>

		<c:if test="${empty list}">
		<p class="text-center" ng-hide="helpList.recordList.length &gt; 0 || helpList.isFetching">
			暂无记录
		</p>
		</c:if>
		<div class="-ft">

			<button class="btn btn-default btn-block btn-lg ng-binding" onclick="loadMore('${pages}','${lx }','${openId }');">加载更多</button>

		</div>
	</div>

</div>

	
</body></html>