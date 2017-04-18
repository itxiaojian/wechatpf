<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
<!--     <meta http-equiv="refresh" content="15"> -->
    <title>信息发送</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mqq-bottom-ad" content="no">
    <script src="<%=path%>/resources/js/wsh/wsq/cdn_djl.js" type="text/javascript" async=""></script>
    <script type="text/javascript">
	function toSend(){
		var fbxx=$('#fbxx').val();
		if(fbxx==null||fbxx==''){
			alert("请输入发布信息");
			return false;
		}
		$('#Form').submit();
	}
	function ScrollDiv() {    
	    var ex = document.getElementById("panelBodyWrapper-5");  
	        ex.scrollTop = ex.scrollHeight;   
	 } 
</script>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/wsh/wsq/mq.css">
</head>
<body class="black" onload="ScrollDiv()">
	<form id="context-data-form" class="none">
			<input name="id" id="hdid" type="hidden" value="${hdid }" />
			<input name="openId" id="openId" value="${openId}" type="hidden">
	</form>
    <div id="bgAllImage"><img class="bgAllImage" src="<%=path%>/resources/js/wsh/wsq/1.jpg"></div>
    <div class="wrap" style="width:98%;">
        <div id="container" class="container" style="display: block;width:100%;margin-left: 0px;">
        <div class="panel chat-panel" id="panel-5" cmd="void" style="transition: -webkit-transform 0.4s cubic-bezier(0, 1, 0, 1); -webkit-transition: -webkit-transform 0.4s cubic-bezier(0, 1, 0, 1); transform: translate3d(0px, 0px, 0px); -webkit-transform: translate3d(0px, 0px, 0px); display: block;">
<header id="panelHeader-5" class="panel_header">
    
        <h1 id="panelTitle-5" class="text_ellipsis padding_20" style="padding-left: 0px; padding-right: 0px;">微上墙信息发布</h1>
    
</header>

<div id="panelBodyWrapper-5" class="panel_body_container" style="top: 45px; bottom: 50px; overflow: auto;">
    <div id="panelBody-5" class="panel_body chat_container" style="-webkit-transition-property: -webkit-transform; transition-property: -webkit-transform; -webkit-transform-origin: 0px 0px 0px; -webkit-transform: translate(0px, 0px) scale(1) translateZ(0px);">
    
<!-- <div class="chat_time"><span>09:54</span></div> -->
    
<c:forEach var="data" items="${fbxx}" varStatus="obj">
	<c:if test="${data.openid==openId }">
		<div class="chat_content_group self  " >
		    
		    <img class="chat_content_avatar" src="${data.bz }" width="40px" height="40px">
		    
		        <p class="chat_nick">${data.cyrxm }</p>
		    
		    
		    <p class="chat_content ">${data.fbnr }</p>
		</div>
	</c:if>
	<c:if test="${data.openid!=openId }">
		<div class="chat_content_group buddy  " >
		    
		    <img class="chat_content_avatar" src="${data.bz }" width="40px" height="40px">
		    
		        <p class="chat_nick">${data.cyrxm }</p>
		    
		    
		    <p class="chat_content ">${data.fbnr }</p>
		</div>
	</c:if>
</c:forEach>

</div>
</div>

<footer id="panelFooter-5" class="chat_toolbar_footer">
<form id="Form" action="<%=path%>/wsh/ShWsq/toSend" method="post">
			<input name="openId" id="openId" type="hidden" value="${openId }" />
			<input name="cyrid" id="cyrid" type="hidden" value="${cyrid }" />
			<input name="id" id="hdid" type="hidden" value="${hdid }" />
    <div class="chat_toolbar">
<!--     <div id="add_app_btn" class="btn btn_add_grey">
        <span class="btn_img"></span>
    </div> 
    <div id="add_face_btn" class="btn btn_face">
        <span class="btn_img"></span>
    </div>-->
    <textarea id="fbxx" name="fbxx" class="input input_white chat_textarea"></textarea>
    <button id="send_chat_btn" class="btn btn_small btn_blue" cmd="sendMsg" onclick="toSend();">
        <span class="btn_text">发送</span>
    </button>
<textarea id="" class="input input_white chat_textarea hidden_textarea" style="height: 32px; width: 564px;"></textarea></div>
</form>
<div id="face_images" class="qq_face_area" style="display: none;">
    <ul class="wrap" style="width: 0px; -webkit-transform: translate3d(0px, 0px, 0px);">
        
        <li class="faceIteam faceIteam1" cmd="chooseFace" style="width: 0px;">
            
        <i title="微笑" href="javascript:;"></i>
            
        <i title="撇嘴" href="javascript:;"></i>
            
        <i title="色" href="javascript:;"></i>
            
        <i title="发呆" href="javascript:;"></i>
            
        <i title="得意" href="javascript:;"></i>
            
        <i title="流泪" href="javascript:;"></i>
            
        <i title="害羞" href="javascript:;"></i>
            
        <i title="闭嘴" href="javascript:;"></i>
            
        <i title="睡" href="javascript:;"></i>
            
        <i title="大哭" href="javascript:;"></i>
            
        <i title="尴尬" href="javascript:;"></i>
            
        <i title="发怒" href="javascript:;"></i>
            
        <i title="调皮" href="javascript:;"></i>
            
        <i title="呲牙" href="javascript:;"></i>
            
        <i title="惊讶" href="javascript:;"></i>
            
        <i title="难过" href="javascript:;"></i>
            
        <i title="酷" href="javascript:;"></i>
            
        <i title="冷汗" href="javascript:;"></i>
            
        <i title="抓狂" href="javascript:;"></i>
            
        <i title="吐" href="javascript:;"></i>
            
            <i title="delKey" href="javascript:;"></i>
        </li>
        
        <li class="faceIteam faceIteam2" cmd="chooseFace" style="width: 0px;">
            
        <i title="偷笑" href="javascript:;"></i>
            
        <i title="可爱" href="javascript:;"></i>
            
        <i title="白眼" href="javascript:;"></i>
            
        <i title="傲慢" href="javascript:;"></i>
            
        <i title="饥饿" href="javascript:;"></i>
            
        <i title="困" href="javascript:;"></i>
            
        <i title="惊恐" href="javascript:;"></i>
            
        <i title="流汗" href="javascript:;"></i>
            
        <i title="憨笑" href="javascript:;"></i>
            
        <i title="大兵" href="javascript:;"></i>
            
        <i title="奋斗" href="javascript:;"></i>
            
        <i title="咒骂" href="javascript:;"></i>
            
        <i title="疑问" href="javascript:;"></i>
            
        <i title="嘘" href="javascript:;"></i>
            
        <i title="晕" href="javascript:;"></i>
            
        <i title="折磨" href="javascript:;"></i>
            
        <i title="衰" href="javascript:;"></i>
            
        <i title="骷髅" href="javascript:;"></i>
            
        <i title="敲打" href="javascript:;"></i>
            
        <i title="再见" href="javascript:;"></i>
            
            <i title="delKey" href="javascript:;"></i>
        </li>
        
        <li class="faceIteam faceIteam3" cmd="chooseFace" style="width: 0px;">
            
        <i title="擦汗" href="javascript:;"></i>
            
        <i title="抠鼻" href="javascript:;"></i>
            
        <i title="鼓掌" href="javascript:;"></i>
            
        <i title="糗大了" href="javascript:;"></i>
            
        <i title="坏笑" href="javascript:;"></i>
            
        <i title="左哼哼" href="javascript:;"></i>
            
        <i title="右哼哼" href="javascript:;"></i>
            
        <i title="哈欠" href="javascript:;"></i>
            
        <i title="鄙视" href="javascript:;"></i>
            
        <i title="委屈" href="javascript:;"></i>
            
        <i title="快哭了" href="javascript:;"></i>
            
        <i title="阴险" href="javascript:;"></i>
            
        <i title="亲亲" href="javascript:;"></i>
            
        <i title="吓" href="javascript:;"></i>
            
        <i title="可怜" href="javascript:;"></i>
            
        <i title="菜刀" href="javascript:;"></i>
            
        <i title="西瓜" href="javascript:;"></i>
            
        <i title="啤酒" href="javascript:;"></i>
            
        <i title="篮球" href="javascript:;"></i>
            
        <i title="乒乓" href="javascript:;"></i>
            
            <i title="delKey" href="javascript:;"></i>
        </li>
        
        <li class="faceIteam faceIteam4" cmd="chooseFace" style="width: 0px;">
            
        <i title="咖啡" href="javascript:;"></i>
            
        <i title="饭" href="javascript:;"></i>
            
        <i title="猪头" href="javascript:;"></i>
            
        <i title="玫瑰" href="javascript:;"></i>
            
        <i title="凋谢" href="javascript:;"></i>
            
        <i title="示爱" href="javascript:;"></i>
            
        <i title="爱心" href="javascript:;"></i>
            
        <i title="心碎" href="javascript:;"></i>
            
        <i title="蛋糕" href="javascript:;"></i>
            
        <i title="闪电" href="javascript:;"></i>
            
        <i title="炸弹" href="javascript:;"></i>
            
        <i title="刀" href="javascript:;"></i>
            
        <i title="足球" href="javascript:;"></i>
            
        <i title="瓢虫" href="javascript:;"></i>
            
        <i title="便便" href="javascript:;"></i>
            
        <i title="月亮" href="javascript:;"></i>
            
        <i title="太阳" href="javascript:;"></i>
            
        <i title="礼物" href="javascript:;"></i>
            
        <i title="拥抱" href="javascript:;"></i>
            
        <i title="强" href="javascript:;"></i>
            
            <i title="delKey" href="javascript:;"></i>
        </li>
        
        <li class="faceIteam faceIteam5" cmd="chooseFace" style="width: 0px;">
            
        <i title="弱" href="javascript:;"></i>
            
        <i title="握手" href="javascript:;"></i>
            
        <i title="胜利" href="javascript:;"></i>
            
        <i title="抱拳" href="javascript:;"></i>
            
        <i title="勾引" href="javascript:;"></i>
            
        <i title="拳头" href="javascript:;"></i>
            
        <i title="差劲" href="javascript:;"></i>
            
        <i title="爱你" href="javascript:;"></i>
            
        <i title="NO" href="javascript:;"></i>
            
        <i title="OK" href="javascript:;"></i>
            
        <i title="爱情" href="javascript:;"></i>
            
        <i title="飞吻" href="javascript:;"></i>
            
        <i title="跳跳" href="javascript:;"></i>
            
        <i title="发抖" href="javascript:;"></i>
            
        <i title="怄火" href="javascript:;"></i>
            
        <i title="转圈" href="javascript:;"></i>
            
        <i title="磕头" href="javascript:;"></i>
            
        <i title="回头" href="javascript:;"></i>
            
        <i title="跳绳" href="javascript:;"></i>
            
        <i title="挥手" href="javascript:;"></i>
            
            <i title="delKey" href="javascript:;"></i>
        </li>
        
        <li class="faceIteam faceIteam6" cmd="chooseFace" style="width: 0px;">
            
        <i title="激动" href="javascript:;"></i>
            
        <i title="街舞" href="javascript:;"></i>
            
        <i title="献吻" href="javascript:;"></i>
            
        <i title="左太极" href="javascript:;"></i>
            
        <i title="右太极" href="javascript:;"></i>
            
        <i title="双喜" href="javascript:;"></i>
            
        <i title="鞭炮" href="javascript:;"></i>
            
        <i title="灯笼" href="javascript:;"></i>
            
        <i title="发财" href="javascript:;"></i>
            
        <i title="K歌" href="javascript:;"></i>
            
        <i title="购物" href="javascript:;"></i>
            
        <i title="邮件" href="javascript:;"></i>
            
        <i title="帅" href="javascript:;"></i>
            
        <i title="喝彩" href="javascript:;"></i>
            
        <i title="祈祷" href="javascript:;"></i>
            
        <i title="爆筋" href="javascript:;"></i>
            
        <i title="棒棒糖" href="javascript:;"></i>
            
        <i title="喝奶" href="javascript:;"></i>
            
        <i title="下面" href="javascript:;"></i>
            
        <i title="香蕉" href="javascript:;"></i>
            
            <i title="delKey" href="javascript:;"></i>
        </li>
        
    </ul>
    <ul class="btnsWrap"><li class="" _index="0"></li><li _index="1" class=""></li><li _index="2" class=""></li><li _index="3" class=""></li><li _index="4" class=""></li><li _index="5" class="selected"></li></ul>
</div>
<iframe id="panel_uploadFilIframe" name="panel_uploadFilIframe" style="display:none"></iframe>

</footer>

</div></div>

        
    </div>
</body>
</html>