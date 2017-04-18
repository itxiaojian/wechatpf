<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="no-cache" http-equiv="Pragma" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="0" http-equiv="Expires" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
<link type="text/css" rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/iscroll.js"></script>
<script type="text/javascript" src="js/alert.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
    var myScroll;
    function loaded() {
        myScroll = new iScroll('wrapper', {
            snap: true,
            momentum: false,
            hScrollbar: false,
            onScrollEnd: function() {
                document.querySelector('#indicator > li.active').className = '';
                document.querySelector('#indicator > li:nth-child(' + (this.currPageX + 1) + ')').className = 'active';
            }
        });
    }
    document.addEventListener('DOMContentLoaded', loaded, false);
</script>
</head>

<body>
<header>
    <div class="banner">
        <div id="wrapper" style="overflow: hidden; width:100% ">
            <div id="scroller">
                <ul id="thelist">
                    <li><p></p><a href="#"><img src="<%=path%>/resources/img/FJT.jpg"/></a></li>              </ul>
            </div>
        </div>
    </div>
    <div class="clr"></div>
</header>
<script>
    var count = document.getElementById("thelist").getElementsByTagName("img").length;
    for (i = 0; i < count; i++) {
        document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:" + document.body.clientWidth + "px";
    }
    document.getElementById("scroller").style.cssText = " width:" + document.body.clientWidth * count + "px";
    setInterval(function() {
        myScroll.scrollToPage('next', 0, 400, count);
    }, 3500);
    window.onresize = function() {
        for (i = 0; i < count; i++) {
            document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:" + document.body.clientWidth + "px";
        }
        document.getElementById("scroller").style.cssText = " width:" + document.body.clientWidth * count + "px";
    };
</script>
<p >校园微主页</p>
<div class="main"  style="width: 98%;height: 70%;margin-top: 3%;">
    <div style="width: 57%; height: 45%;">
        <a href="<%=path%>/jsp/xysy.jsp" style="width: 62%; height: 48%;margin-left:0;"><!-- 学院新闻 -->
            <div class="img" style="background:url(<%=path%>/resources/img/xyxw.png) center no-repeat;background-size:100% 100% ;width: 100%; height: 100%;"></div>
        </a>    
        <a href="liebiao.html" style="width:36%; height: 50%; margin-right: 0; left: -0.5%; top: -48%;"><!-- 学校简介 -->
                <div class="img" style="background:url(<%=path%>/resources/img/xyjj.png) center no-repeat;background-size:100% 100% ;  width: 100%; height: 95%;"></div>
            </a>
            <a href="#"  style="width: 33%; margin-left: 0; top: -47%; height: 46%;"><!-- 办事流程 -->
                <div class="img" style="background:url(<%=path%>/resources/img/bslc.png) center no-repeat;background-size:100% 100% ;  width: 100%; height: 100%;"></div>
      		</a>
      		<a href="#" style="width: 65%; height: 46.3%; margin-right: 0px; top: -93.1%;left: -0.5%;"><!-- 规章制度 -->
                <div class="img" style="background:url(<%=path%>/resources/img/gzzd.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%; "></div>
            </a>
    </div>
    <div style="width: 42%; height: 42%; margin-right: 0.5%;">            
            <a href="#" style="width: 49%; height:51%; margin-left:0 ;"><!-- 学院风光 -->
                <div class="img" style="background:url(<%=path%>/resources/img/xyfg.png) center no-repeat;background-size:100% 100% ;  width: 100%; height: 100%; padding-bottom: 0.5%;"></div>
      		</a>
      		<a href="#" style="width: 49%; height: 52%; left:26%; top: -52%;"><!-- 校园导航 -->
                <div class="img" style="background:url(<%=path%>/resources/img/xydh.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%; "></div>
            </a>
            <a href="#" style="width: 49%; height: 50%; top: -49%;margin-left: 0"><!-- 组织架构 -->
                <div class="img" style="background:url(<%=path%>/resources/img/zzjg.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%;"></div>
      		</a>
      		<a href="#"  style="width: 49%; height: 50%; top: -99%; left: 26%;"><!-- 出差申请 -->
                <div class="img" style="background:url(<%=path%>/resources/img/ccsq.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%; "></div>
            </a>
  	</div>
    <div style="width: 57%; height: 43%;">            
             <a href="#"  style="width: 63%; height: 49%; top: -0.5%;"><!-- 学院校历 -->
                <div class="img" style="background:url(<%=path%>/resources/img/xyxl.png) center no-repeat;background-size:100% 100% ;width: 100%; height: 100%;"></div>
      		</a>
      		<a href="#" style="width: 35%; height: 48%;left: 60.5%;top:-49%"><!-- 我要吐槽 -->
                <div class="img" style="background:url(<%=path%>/resources/img/wytc.png) center no-repeat;background-size:100% 100% ;width: 100%; height: 100%;"></div>
            </a>
            <a href="#" style="width: 63%; height:49%; margin-left: 0; top:-46%;float: left"><!-- 电话黄历 -->
                 <img src="<%=path%>/resources/img/dhhl.png" style="background-size:100% 100% ; width: 100%; height: 100%;"  />
                 <!-- <div class="img" style="background:url(img/dhhl.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%; margin-top: -82%;"></div>-->
      		</a>
      		<a href="#" style="width: 35.8%; height: 48.6%; margin-right:0;top:-46%;float: right;"><!-- 请假申请 -->
      		     <img src="<%=path%>/resources/img/qjsq.png" style="background-size:100% 100% ; width: 100%; height: 100%;"  />
                <!--  <div class="img" style="background:url(img/qjsq.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%;"></div>-->
            </a>
    </div>
    <div style="width: 42%; height: 43%;">            
    		<a href="#" style="width: 49%; height: 49%; margin-left: 0; top:-0.7%;left: -1%"><!-- 一键救援 -->
                <div class="img" style="background:url(<%=path%>/resources/img/yjjy.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%;"></div>
            </a>
            <a href="#" style="width: 49%; height: 49%; margin-left: 0; top: 50.6%; left: -50%;"><!-- 宿管动态 -->
                <div class="img" style="background:url(<%=path%>/resources/img/ssdt.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%;"></div>
      		</a>
      		<a href="#" style="width: 49%; height: 101%; top:-50%;left:25%; "><!-- 服务帮助 -->
      		     <img src="<%=path%>/resources/img/fwbz.png" style="background-size:100% 100% ; width: 100%; height: 100%;"  />
                  <!--  <div class="img" style="background:url(<%=path%>/resources/img/fwbz.png) center no-repeat;background-size:100% 100% ; width: 100%; height: 100%; "></div> -->
            </a>
    </div> 
    
</div>
<div class="footer" style="margin-top: -21%;">
	<br>
	<br>
	<div class="footer-bottom" data-role="none" style="height: 65px;margin-top: -10%;">
	  copyright 2015 黄山学院版权所有
	</div>
</div>
</body>
</html>