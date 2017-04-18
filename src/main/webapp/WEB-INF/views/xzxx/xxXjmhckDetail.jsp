<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>信件详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1"></meta>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<style type="text/css">
.song12 {
/* 	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color: #0000FF;
	margin-bottom:1px; */
	font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color: #0000FF;
	LINE-HEIGHT: 20px;
}

.aaaa {
    font-size: 12px;
	font-family: "宋体", Arial;
	font-weight: normal;
	color: #0000FF;
}

.tu {
	BACKGROUND-IMAGE: url(images/l2n.gif);
	BACKGROUND-REPEAT: no-repeat;
	BACKGROUND-POSITION: center center
}

.song12a {
	FONT-SIZE: 12px;
	FONT-FAMILY: "宋体", Arial;
	FONT-WEIGHT: normal;
	COLOR:
}

.tu2 {
	BACKGROUND-IMAGE: url(images/l3.gif);
	BACKGROUND-REPEAT: no-repeat;
	BACKGROUND-POSITION: center center
}

.song13 {
	FONT-SIZE: 12pt;
	FONT-FAMILY: "宋体", Arial;
	FONT-WEIGHT: bold;
	COLOR:;
	LINE-HEIGHT: 20px
}

.song12bule {
	FONT-SIZE: 12px;
	FONT-FAMILY: "宋体", Arial;
	FONT-WEIGHT: normal;
	COLOR:;
	LINE-HEIGHT: 20px
}

.zikang {
	FONT-SIZE: 12px;
	FONT-FAMILY: "宋体", Arial;
	FONT-WEIGHT: normal;
	COLOR: #005f9a;
	TEXT-ALIGN: left;
	LINE-HEIGHT: 20px
}

BODY {
	MARGIN-TOP: 0px;
}

.aa06 {
	color: #002d71;
}

A {
	color: #0062a0;
	text-decoration: none;
}

.list01 {
	background-color: expression(( this.sectionRowIndex % 2 == 0)? "#E6EEFF":
		 "#F5F5F5");
	height: 25px;
}

.pagestyle {
	font-weight: bold;
}

.pagestyle a {
	border: 1px solid #D8EFFA;
	background-color: #EDF7FD;
	padding: 5px 6px;
}

*,ul,li,input,a{ margin:0px; padding:0px; list-style:none; font-family:'微软雅黑'; text-decoration:none; }
.top{ width:100%; height:60px; position:relative; overflow:hidden; border-bottom:1px solid #ccc;}
.top img{ position:absolute; left:50%; margin-left:-950px;}
.banner{ width:100%; height:300px; background:url(image/banner.png) no-repeat center -300%;}
.banner_box{ width:980px; margin-left:auto; margin-right:auto;}
.search_box{ width:263px; height:322px; background:url(image/search.png) no-repeat center; float:right;}
.cha{ padding-top:6px;float:left; margin-left:65px;}
.GN_btn{ width:70px; height:70px; background-color:#f8912d; clear:both; border-radius:50%; clear:both; margin-left:20px; text-align:center; line-height:40px;cursor:pointer;}
.GN_btn img{ margin-left:3px; margin-top:-5px; float:left}
.GN_btn:hover{color:#fff}
.main{ width:100%;overflow:hidden;}
.main_box{width:980px; margin-left:auto; margin-right:auto;}
.XZ_wxts{ border:1px solid #f8f400; background-color:#fefcd8; border-radius:3px; margin-top:20px; color:#666; line-height:30px; padding:10px; float:left;}
.biao{ margin-top:20px;float:left}
.biao li{ width:100%;line-height:30px; text-align:center; font-size:14px; float:left}
.biao span{ float:left;}
.biao a:hover{ color:#ed9340}
.tab{ margin-top:20px; line-height:30px; clear:both; float:right}
.tab span{ float:right; margin-left:10px;}
.fanye{ width:70px; height:30px; background-color:#ecf4fe; text-align:center; line-height:30px;}
.fanye:hover{ background-color:#b2cdec;color:#fff;cursor:pointer; }
.foot{ width:100%;overflow:hidden; background-color:#ccc;margin-top:30px;}
.foot_box{width:980px; height:30px; margin-left:auto; margin-right:auto; color:#fff; line-height:30px; text-align:center; font-size:12px;  }

.title{ width:100%; height:30px; background-color:#115ebe; margin-top:30px; color:#fff; line-height:30px; text-align:center;border:#b7daff 1px solid;}
.main_text{width:100%; border:#ccc 1px solid; padding-bottom:30px;float:left;}
.main_text span{ float:left; text-indent:18px; display:block;  line-height:40px;}
.FH input{ width:150px; height:60px; float:left; margin-left:400px; border-radius:10px; margin-top:50px; font-size:24px; color:#fff; background-color:#ed9340;}
.text_main{ color:#2785dd; font-size:14px}
.main_text textarea{ width:820px; height:200px;}
</style>
<link href="<%=path%>/resources/js/xzxx/style.css"
	type="text/css" rel="stylesheet">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script language="Javascript"
	src="<%=path%>/resources/js/xzxx/Calendar.js"
	type="text/javascript"></script>
<script type="text/javascript">
jQuery(function(){
  if($('#imgli').has('img').length==1){
	  $('#imgli img').attr("style","width:30%;height:30%");
  }
});
</script>
</head>
<body style="height: 100%">
<div style="display: none;">
		<ul class="tab-menu tab" id="tabMenuID_">
			<li class="tab-selected" title="信件查看"><a
				href="<%=path%>/xzxx/XxXjxxb/getXjckList" target="content"
				onfocus="this.blur()"><span>信件查看</span></a></li>
		</ul>
	</div>
 <div class="top">
	<img src="<%=path%>/resources/img/wzy/top.png">
</div>
<div class="main">
	<div class="main_box">
    	<div class="title">详细信息</div>
    	 <c:forEach var="data" items="${list}" varStatus="status">
        <div class="main_text">
        	<ul>
            	<li>
                	<span style="width:10%">信件标题:  </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">${data.xjbt}</span>
                </li>
                <li>
                	<span style="width:10%">处理部门:  </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">${data.slbmmc }</span>
                </li>
                <li>
                	<span style="width:10%">处理状态:  </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">${data.zt }</span>
                </li>
                <li >
                	<span style="width:10%">写信时间:  </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">${fn:substring(data.xxsj, 0, 10)} </span>
                </li>
                <li id="imgli">
                	<span style="width:10%">信件内容: </span>
                    <span class="text_main" style="width:90%;border-bottom:1px solid #b7daff">${data.xjnr }</span>
                </li>
                <li>
                	<span style="width:10%">审核时间 </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">${fn:substring(data.shsj, 0, 10)} </span>
                </li>
                <li>
                	<span style="width:10%">处理结果: </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">
                    	<textarea style="width:97%;resize:none;overflow-x:hidden;overflow-y:auto;background:transparent;border-style:none;"
                    	 readOnly="readonly">${data.cljg}</textarea>
                    </span>
                </li>
                <li>
                	<span style="width:10%">处理时间:  </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">${fn:substring(data.clsj, 0, 10)}&nbsp;</span>
                </li>
                <li>
                	<span style="width:10%">满意度: </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">${data.myd}&nbsp;</span>
                </li>
                <li>
                	<span style="width:10%">评价结果:   </span>
                    <span style="width:90%;border-bottom:1px solid #b7daff">${data.pj}&nbsp;</span>
                </li>
            </ul>
            <div class="FH">
            	<a href="javascript:;history.back()"><input type="button" value="返回首页"></a>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
<div class="foot">
	<div class="foot_box">CopyRight © 2016 All Rights Reserved 合肥智圣</div>
</div>
</body>
</html>