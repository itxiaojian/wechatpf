<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
  
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1,maximum-scale=3"></meta>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.min.css"/>
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
   <script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function query(){
		$("#myForm").submit();
	}
</script>
<script type="text/javascript">
var i=0;
function loadMore(page,openId){
	i=page;
	i++;
	location.href ="<%=path%>/wfw/ZsXsrzxx/toXsrzxxList?pages="+i+"&openId="+openId;
}

//点击“加载更多之后”定位到最后一条数据
function ScrollDiv() { 
	var size=$("#size").val();
		if(size!=null&&size!=''){
			 if(size<=10){
	//				document.getElementById('lidw1').scrollIntoView(true);
			}else if(size%10==0){
				var num = (parseInt(size/10-1)*10+1);
				//alert(num);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}else{
				var num = (parseInt(size/10)*10);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}
		}
}

var appid="${map.appid}";//$('#appid').val();
var nonceStr="${map.nonceStr}";//$('#nonceStr').val();
var timestamp="${map.timestamp}";//$('#timestamp').val();
var signature="${map.signature}";//$('#signature').val();
wx.config({
  debug: false,
  appId: '${map.appid}',
  timestamp: '${map.timestamp}',
  nonceStr: '${map.nonceStr}',
  signature: '${map.signature}',
  jsApiList: [
    'checkJsApi',
    'onMenuShareTimeline',
    'onMenuShareAppMessage',
    'onMenuShareQQ',
    'onMenuShareWeibo',
    'hideMenuItems',
    'showMenuItems',
    'hideAllNonBaseMenuItem',
    'showAllNonBaseMenuItem',
    'translateVoice',
    'startRecord',
    'stopRecord',
    'onRecordEnd',
    'playVoice',
    'pauseVoice',
    'stopVoice',
    'uploadVoice',
    'downloadVoice',
    'chooseImage',
    'previewImage',
    'uploadImage',
    'downloadImage',
    'getNetworkType',
    'openLocation',
    'getLocation',
    'hideOptionMenu',
    'showOptionMenu',
    'closeWindow',
    'scanQRCode',
    'chooseWXPay',
    'openProductSpecificView',
    'addCard',
    'chooseCard',
    'openCard'
  ]
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	wx.hideOptionMenu();
});

   function toMx(id){
	location.href="<%=path%>/wfw/ZsPyfa/detail?id="+id;
   }
	$(function() {
	    $("table tr:nth-child(odd)").addClass("odd-row");
		$("table td:first-child, table th:first-child").addClass("first");
		$("table td:last-child, table th:last-child").addClass("last");
	});
</script>
<style>
*,select,option,body,ul,li,a,input{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
body{ margin:0; padding:0px; overflow-x:hidden}
.Home_btn{ position:absolute; right:8%; top:20%;}
.WZY_top01{ width:100%; height:60px; position:fixed; left:0px; top:0px;overflow:hidden;}
.New_main01{ width:100%;position:absolute; top:60px; left:0px; bottom:40px;overflow:auto;}
.WZY_foot01{width:100%;  position:absolute; left:0px; bottom:0px; background-color:#000000;overflow:hidden;}
.table>tbody>tr>td{padding:1%;}

@media(max-width:300px){.ziti{font-size:14px; padding:5px 5px};
</style>
<title>贫困生消费信息</title>
</head>

<body id="cardunion" class="mode_webapp2" style="background-color: white; padding-bottom: 0;" >
<div style="display:none;">
<ul class="tab-menu tab" id="tabMenuID_">
	<li class="tab-selected" title="贫困生消费信息">
	<a href="#" target="content" onfocus="this.blur()"><span>贫困生消费信息</span></a>
	</li>
</ul>
</div>

<div class="iphone">
	<div class="WZY_top01">
    	<div class="row">
          <div class="col-sm-12" style="position:relative;">
            <img src="<%=path %>/resources/img/wzy/logo.png" class="img-responsive">
            <a href="javascript:;" onclick="javascript:history.go(-1)">
            <img class="Home_btn" src="<%=path %>/resources/img/wzy/FH.png" width="40" height="40">
            </a>
          </div>
    	</div>
    </div>
    <div class="New_main01">
        <div class=" container-fluid">
<!--     	<div class="container"> -->
<!--             <section style=" display:block"> -->
            <c:forEach var="pksxxlist" items="${pksxxlist }">
            <table class="table table-striped" style=" margin-top:20px;">
            	<tr>
                    <td>
                    	<div class="col-sm-12">
                        	<span class="glyphicon glyphicon-user" style="color:#F54FCB"></span>
                       	     &nbsp;<span style="color:#3D9DF7">${pksxxlist.xm }&nbsp;&nbsp;&nbsp;学号：${pksxxlist.xh }</span>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-info btn-xs">${pksxxlist.pksdj }</button>
              			</div>
                    </td>
                </tr>
               <tr>
                    <td>
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-6">
                              	<span class="glyphicon glyphicon-education" style="color:#3D9DF7"></span>
                       	     &nbsp;院系
                              </div>
                              <div class="col-xs-6">
                              	<span class=""><small>${pksxxlist.xy }</small></span>
                              </div>
                            </div>
              			</div>
                    </td>
                </tr>
               <tr>
                    <td>
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-6">
                              	<span class="glyphicon glyphicon-duplicate" style="color:#3D9DF7"></span>
                       	     &nbsp;专业
                              </div>
                              <div class="col-xs-6">
                              	<span class=""><small>${pksxxlist.zy}</small></span>
                              </div>
                            </div>
              			</div>
                    </td>
                </tr>
                 <tr>
                    <td>
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-6">
                              	<span class="glyphicon glyphicon-stats" style="color:#3D9DF7"></span>
                       	     &nbsp;班级名称
                              </div>
                              <div class="col-xs-6">
                              	<span class=""><small>${pksxxlist.bj }</small></span>
                              </div>
                            </div>
              			</div>
                    </td>
                </tr>
            </table>
            </c:forEach>
<!--             </section> -->
<!--             <div class="container-fluid"> -->
            
                <table class="table table-striped" style=" text-align:center">
                <c:forEach items="${khlist }" var="khlist" begin="0" end="0">
                   <tr> 
                       <td colspan="4"><span class="glyphicon glyphicon-credit-card" style="color:#E7D217"></span>&nbsp;卡号：${khlist.kh }</td>
                   </tr>
                 </c:forEach>
                 <c:if test="${empty khlist}">
                   <tr> 
                       <td colspan="4"><span class="glyphicon glyphicon-credit-card" style="color:#E7D217"></span>&nbsp;卡号：暂无</td>
                   </tr> 
                  </c:if>  
                   <tr>
                        <td class="col-xs-4 ziti" style="width:25%">
                          <small style="color:#3D9DF7">消费时间</small>
                        </td>
                        <td class="col-xs-4 ziti" style="width:25%">
                          <small style="color:#3D9DF7">消费地点</small>
                        </td>
                        <td class="col-xs-4 ziti" style="width:25%">
                          <small style="color:#3D9DF7">消费金额</small>
                        </td>
                        <td class="col-xs-4 ziti" style="width:25%">
                          <small style="color:#3D9DF7;padding-left:5px;">卡内余额</small>
                        </td>
                    </tr>
                    <c:forEach items="${xfxxlist }" var="xfxxlist">
                    <tr>
                        <td>
                          <small>
                          ${xfxxlist.xfsj }</small>
                        </td>
                        <td>
                          <small>${xfxxlist.xfdd }</small>
                        </td>
                        <td>
                          <small>${xfxxlist.xfje }</small>
                        </td>
                        <td>
                          <small>${xfxxlist.ye }</small>
                        </td>
                    </tr>
                    </c:forEach>
                   
                </table>
                <c:if test="${empty xfxxlist}">
                   <div class="text">
			            <p style="margin-left:30px;color:red;">消费信息暂无...</p>
		           </div>  
                 </c:if>  
             
        </div>
    </div>
    
    <div class="WZY_foot01">
    	<div class="row">
          <div class="col-sm-12">
            <img src="<%=path %>/resources/img/wzy/BQ.png" class="img-responsive">
          </div>
    	</div>
    </div>
</div>

</body>
</html>