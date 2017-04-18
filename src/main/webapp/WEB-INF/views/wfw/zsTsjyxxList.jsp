<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<html>
  <head>
   <link href="<%=path%>/resources/css/tsjyxx.css" rel="stylesheet" type="text/css">
  
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,user-scalable=yes, initial-scale=1,maximum-scale=3"></meta>
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icons.css" />
  <link href="<%=path%>/resources/css/tsjyxx.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<%--   	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script> --%>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	
	<link rel="stylesheet" type="text/css" href="<%=path%>/resources/mobiscroll/mobiscroll.custom-2.13.2.min.css">  
 <!--时间控件mobiscroll-->  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>  
  
  
   <link href="<%=path%>/resources/mobiscroll/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />  
   <link href="<%=path%>/resources/mobiscroll/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>  
   <script src="<%=path%>/resources/mobiscroll/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>
  
   
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	//上月 下月
	/*function change(obj){
		document.getElementById("addflag").value=obj;
	  	var time=document.getElementById("date").value;
	  	var arr = time.split("-");
	    var date = new Date(arr[0], (parseFloat(arr[1])+parseFloat(obj)), 1);
		var d = new Date();
		d = new Date(d.getFullYear(), (d.getMonth()+1), d.getDate());
		if(date>d){
			alert("只能查询当前月以前的数据！");
			return false;
		}else{
	  		return true;
		}
    }*/
    $(function () {  
        var currYear = (new Date()).getFullYear();    
        var opt={};  
        opt.date = {preset : 'date'};  
        //opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };  
        opt.datetime = {preset : 'datetime'};  
        opt.time = {preset : 'time'};  
        opt.default = {  
            theme: 'jqm', //皮肤样式  
            display: 'modal', //显示方式   
            mode: 'scroller', //日期选择模式  
            dateFormat : 'yy-mm', // 日期格式 
            dateOrder : 'yymm', //面板中日期排列格式  
            lang:'zh',  
            startYear:currYear - 20, //开始年份  
            endYear:currYear + 20 //结束年份  
        };  

        $("#appDateTime").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));  
        var optDateTime = $.extend(opt['datetime'], opt['default']);  
        
    });
    function getValue(){
    	//alert($('#appDateTime').val());
    	$('#Form1').submit();
    }
    function setValue(){
    	var time=$('#date').val();
    	$("#appDateTime").val(time);
    	
    	var size=$("#size").val();
		if(size!=null&&size!=''){
			 if(size<=10){
			}else if(size%10==0){
				var num = (parseInt(size/10-1)*10+1);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}else{
				var num = (parseInt(size/10)*10);
				var id='lidw'+num;
				document.getElementById(id).scrollIntoView(true); 
			}
		}
    }
    
    
    var i=0;
	function loadMore(pages,openId){
		var timename = $("#date").val();
		i=pages;
		i++;
		var size = (i-1)*10;
		var url ="<%=path%>/wfw/ZsTsjyxx/toTsjyxxZj";
		var html= "";
		var html1= "";
	    $.ajax({
			url :url,
			data : {
				pages:i,
				openId:openId,
				time:timename
			},
			type : "post",
			success : function(data) {
				if(data[0].length>0 && data[1].length>0){
					var rst = eval(data[0]);
					var rst1 = eval(data[1]);
					var rst2 = eval(data[2]);
					$.each(rst,function(i,value0){
							 $.each(rst1,function(j,value1){
				                 if(value1.bh==value0.bh){
				                  html1+=
						                  "<ul><li>"
						                  +"<span class='msg_main01'>书名</span>"
						                  +"<span class='msg_main02' style='color:#fc4312'>"+value1.tsmc+"</span>"
						                  +"</li><li>"
						                  +"<span class='msg_main01'>图书编号</span>"
						                  +"<span class='msg_main02'>"+value1.tsbh+"</span>"
						                  +"</li><li>"
						                  +"<span class='msg_main01'>借出时间</span>"
						                  +"<span class='msg_main02'>"+value1.jcsj+"</span>"
						                  +"</li><li>"
						                  +"<span class='msg_main01'>归还时间</span>"
						                  +"<span class='msg_main02'></span>"
						                  +"</li><li>"
						                  +"<span class='msg_main01'>应归还时间</span>"
						                  +"<span class='msg_main02'>"+value1.yghsj+"</span>"
						                  +"</li></ul>";	                
				                	   }
				                 });
							
					     <%--    html+="<div class='M_title'>"
					             +"<img style='margin-top:1%;margin-left:2%;width:40px;height:40px;' src='<%=path%>/resources/img/wzy/tsjy.png'>"+value0.xm
					             +"<span>&nbsp;&nbsp;学号/工号:"+value0.bh+"</span>"
					             +"<img style='float:right; margin-top:1%; margin-right:2%;width:40px;height:40px;' src='<%=path%>/resources/img/wzy/tsdian.png'>"
					             +"</div>"
					         	 +"<div class='M_tab'>"
				                 +"<span style='border-bottom:thick solid #fc4312; color:#fc4312' id='M_btn01"+i+"' onclick=show1('"+i+"');>未归还</span>"
				                 +"<span style='border-bottom:none;color:#2e87d3' id='M_btn02"+i+" onclick='show('"+i+"');>已归还</span>"
				                 +"</div>"
				                 +"<div class='M_main' id='TAB01"+i+"' style='display: block;'>" 
				                 +html1
					             +"</div>"; --%>
					         
				})
			    $('.jzgd').before(html1);
			   // $('.xxdiv').before(html);
				$('.wghbtn').removeAttr("onclick");
				$('.wghbtn').attr("onclick","loadMore('"+i+"','"+openId+"')");
				}else{
					$('.wghbtn').text("已经是最后一页了");
				}
				},
			   error : function() {
				alert("error");
			}
		  });
	}
	
	var j=0;
	function loadMore1(pages,openId){
		var timename = $("#date").val();
		j=pages;
		j++;
		var size = (i-1)*10;
		var url ="<%=path%>/wfw/ZsTsjyxx/toTsjyxxZj";
		var html= "";
		var html1= "";
	    $.ajax({
			url :url,
			data : {
				pages:j,
				openId:openId,
				time:timename
			},
			type : "post",
			success : function(data) {
				if(data[0].length>0 && data[2].length>0){
					var rst = eval(data[0]);
					var rst1 = eval(data[1]);
					var rst2 = eval(data[2]);
					$.each(rst,function(i,value0){
			
							 $.each(rst2,function(j,value2){
				                 if(value2.bh==value0.bh){
				                  html1+=
						                  "<ul><li>"
						                  +"<span class='msg_main01'>书名</span>"
						                  +"<span class='msg_main02' style='color:#fc4312'>"+value2.tsmc+"</span>"
						                  +"</li><li>"
						                  +"<span class='msg_main01'>图书编号</span>"
						                  +"<span class='msg_main02'>"+value2.tsbh+"</span>"
						                  +"</li><li>"
						                  +"<span class='msg_main01'>借出时间</span>"
						                  +"<span class='msg_main02'>"+value2.jcsj+"</span>"
						                  +"</li><li>"
						                  +"<span class='msg_main01'>归还时间</span>"
						                  +"<span class='msg_main02'>"+value2.ghsj+"</span>"
						                  +"</li><li>"
						                  +"<span class='msg_main01'>应归还时间</span>"
						                  +"<span class='msg_main02'>"+value2.yghsj+"</span>"
						                  +"</li></ul>";
						                  
				                	   }
				                 });
							
					     <%--    html+="<div class='M_title'>"
					             +"<img style='margin-top:1%;margin-left:2%;width:40px;height:40px;' src='<%=path%>/resources/img/wzy/tsjy.png'>"+value0.xm
					             +"<span>&nbsp;&nbsp;学号/工号:"+value0.bh+"</span>"
					             +"<img style='float:right; margin-top:1%; margin-right:2%;width:40px;height:40px;' src='<%=path%>/resources/img/wzy/tsdian.png'>"
					             +"</div>"
					         	 +"<div class='M_tab'>"
				                 +"<span style='border-bottom:thick solid #fc4312; color:#fc4312' id='M_btn01"+i+"' onclick=show1('"+i+"');>未归还</span>"
				                 +"<span style='border-bottom:none;color:#2e87d3' id='M_btn02"+i+" onclick='show('"+i+"');>已归还</span>"
				                 +"</div>"
				                 +"<div class='M_main' id='TAB01"+i+"' style='display: block;'>" 
				                 +html1
					             +"</div>"; --%>
					         
				})
			    $('.jzgd1').before(html1);
			   // $('.xxdiv').before(html);
				$('.yghbtn').removeAttr("onclick");
				$('.yghbtn').attr("onclick","loadMore1('"+j+"','"+openId+"')");
				}else{
					$('.yghbtn').text("已经是最后一页了");
				}
				},
			   error : function() {
				alert("error");
			}
		  });
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
    
	</script>
    <%-- <link href="<%=path%>/resources/css/tab-import.css" rel="stylesheet" style="text/css" />
	<link href="<%=path%>/resources/css/css.css" rel="stylesheet" style="text/css" /> --%>
	<title>图书借阅信息</title>
  </head>
 <style>
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.top_01{ width:100%; height:38px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:15px;
 line-height:38px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.span_hz{ float:left; font-size:15px;margin-top:0.1%;}
.top_01 img{  display:block; float:left;} 
.fanhui{margin-top:4%;float:right;margin-right:-2rem;width:5%;}
.span_input{line-height: normal; }
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ width:32%; height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_01 img{ float:left; margin-top:10%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
/**页面表格的行高*/
th,td{padding-bottom:2px;padding-top:2px;line-height:2px;}

.M_box{ width:90%; margin:auto;}
.M_msg{ width:100%;border:1px solid #f2f2f2; border-radius:20px; margin-top:20%; float:left;}
.M_title{ width:100%; height:50px; background-color:#8bc0f2; color:#fff; line-height:50px; font-size:18px; float:left;border-radius:10px 10px 0 0 ;}
.M_title span{font-size:10px;}
.M_title img{ float:left;}
.M_main{ clear:both; font-size:10px;}
.M_main li{ float:left; padding-top:2%; width:100%;}
.M_main li span{ float:left;}
.M_main ul{ float:left;border-bottom:1px solid #8bc0f2; padding-bottom:20px;}
.msg_main01{ text-indent:30px;width:30%;}
.msg_main02{ text-indent:45px;width:70%;}
.M_tab{ width:100%; border-bottom:1px solid #c5e0fa; float:left;}
.M_tab span{ width:50%; line-height:40px; font-size:15px; text-align:center; float:left; color:#2e87d3;}
</style>
 
<body style="max-width:640px;margin-left: auto;margin-right: auto;background-color:white;" onload="setValue();wx.hideOptionMenu();">
<div style="display:none;">
    <ul class="tab-menu tab" id="tabMenuID_">
	    <li class="tab-selected" title="图书借阅信息">
	       <a href="#" target="content" onfocus="this.blur()"><span>图书借阅信息</span></a>
	    </li>
   </ul>
</div>
<form action="<%=path%>/wfw/ZsTsjyxx/toTsjyxxBysj" method="POST" id="Form1"> 
   <input type="hidden" id="date" name="date" value="${time}">
   <input type="hidden" name="openId" id="openId" value="${openId }"> 
   <input type="hidden" name="size" id="size" value="${size }"> 
   <div class="top_01" style="height:50px;">
    	<span class="span_hz" style="width:80%; padding-left:5%;margin-top: 5.375;">
        	借出时间:&nbsp;&nbsp;
              <input class="span_input" id="appDateTime" name="time" onchange="getValue();" 
		       style="border:1px solid #dddddd;width: 38%;" /> 
		</span>
        <span style="width:10%">
        	<a href="<%=path%>/wfw/zy/zhuye?openId=${openId}">
        		<img class="fanhui" src="<%=path%>/resources/img/wzy/fh1.png">
            </a>
        </span>
  </div>
</form>		
<div class="main_02">
    <c:if test="${not empty cjlist }">
	<div class="M_box">
	    <div class="M_msg">
	    <c:forEach var="data" items="${cjlist}" varStatus="obj">
           <c:if test="${!empty wghlist}">                                    
	         <div class="M_title">
	             <img style="margin-top:1%; margin-left:2%;width:40px;height:40px;" src="<%=path%>/resources/img/wzy/tsjy.png" >${data.xm}
	             <span>&nbsp;&nbsp;学号/工号:${data.bh}</span>
	               <img style="float:right; margin-top:1%; margin-right:2%;width:40px;height:40px;" src="<%=path%>/resources/img/wzy/tsdian.png">
	         </div>
	         <div class="M_tab">
                	<span style=" border-bottom:thick solid #fc4312; color:#fc4312" id="M_btn01${obj.count}" onclick="show1('${obj.count}');">未归还</span>
                    <span style=" border-bottom:none; color:#2e87d3" id="M_btn02${obj.count}" onclick="show('${obj.count}');">已归还</span>
             </div>
            <div class="M_main" id="TAB01${obj.count}" style="display: block;"> 
                 <c:forEach var="data1" items="${wghlist}" > 
                 <c:if test="${data1.bh==data.bh }"> 
	             <ul>
	                <li>
	                  <span class="msg_main01">书名</span>
	                  <span class="msg_main02" style="color:#fc4312">${data1.TSMC}</span>
	                </li>
	                <li>
	                  <span class="msg_main01">图书编号</span>
	                  <span class="msg_main02">${data1.TSBH}</span>
	                </li>
	                <li>
	                  <span class="msg_main01">借出时间</span>
	                  <span class="msg_main02">${data1.JCSJ}</span>
	                </li>
	                <li>
	                  <span class="msg_main01" >归还时间</span>
	                  <span class="msg_main02"></span>
	                </li>
	                <li>
	                  <span class="msg_main01">应归还时间</span>
	                  <span class="msg_main02">${data1.YGHSJ}</span>
	                </li>	                
	             </ul>	
	             </c:if>  
	             </c:forEach>
	             <div class="jzgd"></div>    
	           </div>
               <c:if test="${empty yghlist}">
               <div class="M_main" id="TAB02${obj.count}" style="display: none;"> 
	               <p style="margin-bottom: 105%;font-size:15px;text-align: center;margin-top: 50%;">您无已归还信息！</p>
			    </div>
               </c:if>
               <c:if test="${!empty yghlist}">
	          <div class="M_main" id="TAB02${obj.count}" style="display: none;">
		         <c:forEach var="data2" items="${yghlist}" >
		         <c:if test="${data2.bh==data.bh }">
		            <ul>
		               <li>
		                 <span class="msg_main01">书名</span>
		                 <span class="msg_main02" style="color:#fc4312">${data2.TSMC}</span>
		               </li>
		               <li>
		                 <span class="msg_main01">图书编号</span>
		                 <span class="msg_main02">${data2.TSBH}</span>
		               </li>
		               <li>
		                 <span class="msg_main01">借出时间</span>
		                 <span class="msg_main02">${data2.JCSJ}</span>
		               </li>
		               <li>
		                 <span class="msg_main01" >归还时间</span>
		                 <span class="msg_main02">${data2.GHSJ}</span>
		               </li>
		               <li>
		                 <span class="msg_main01">应归还时间</span>
		                 <span class="msg_main02">${data2.YGHSJ}</span>
		               </li>
		            </ul>	
		            </c:if>	                    
		       </c:forEach>
		        <div class="jzgd1"></div>  
	            </div>
               </c:if> 
	      </c:if>
	      <c:if test="${empty wghlist}">
   	         <div class="M_title">
	             <img style="margin-top:1%; margin-left:2%;width:40px;height:40px;" src="<%=path%>/resources/img/wzy/tsjy.png" >${data.xm}
	             <span>&nbsp;&nbsp;学号/工号:${data.bh}</span>
	               <img style="float:right; margin-top:1%; margin-right:2%;width:40px;height:40px;" src="<%=path%>/resources/img/wzy/tsdian.png">
	         </div>
	         <div class="M_tab">
                	<span style=" border-bottom:thick solid #fc4312; color:#2e87d3" id="M_btn01${obj.count}" onclick="show1('${obj.count}');">未归还</span>
                    <span style=" border-bottom:nnone; color:#fc4312" id="M_btn02${obj.count}" onclick="show('${obj.count}');">已归还</span>
             </div>
	            <div class="M_main" id="TAB01${obj.count}" style="display: block;"> 
	               <p style="margin-bottom: 105%;font-size:15px;text-align: center;margin-top: 50%;">您暂无借阅记录！</p>
			    </div>
			    <c:if test="${empty yghlist}">
               <div class="M_main" id="TAB02${obj.count}" style="display: none;"> 
	               <p style="margin-bottom: 105%;font-size:15px;text-align: center;margin-top: 50%;">您无已归还信息！</p>
			    </div>
               </c:if>
               <c:if test="${!empty yghlist}">
	          <div class="M_main" id="TAB02${obj.count}" style="display: none;">
		         <c:forEach var="data2" items="${yghlist}" >
		         <c:if test="${data2.bh==data.bh }">
		            <ul>
		               <li>
		                 <span class="msg_main01">书名</span>
		                 <span class="msg_main02" style="color:#fc4312">${data2.TSMC}</span>
		               </li>
		               <li>
		                 <span class="msg_main01">图书编号</span>
		                 <span class="msg_main02">${data2.TSBH}</span>
		               </li>
		               <li>
		                 <span class="msg_main01">借出时间</span>
		                 <span class="msg_main02">${data2.JCSJ}</span>
		               </li>
		               <li>
		                 <span class="msg_main01" >归还时间</span>
		                 <span class="msg_main02">${data2.GHSJ}</span>
		               </li>
		               <li>
		                 <span class="msg_main01">应归还时间</span>
		                 <span class="msg_main02">${data2.YGHSJ}</span>
		               </li>
		            </ul>	
		            </c:if>	                    
		       </c:forEach>
	            </div>
               </c:if> 
	      </c:if>
      </c:forEach>
	           <div class="-ft" style="margin-bottom:0rem;width:100%;">
	               <button class="btn btn-default btn-block btn-lg ng-binding wghbtn" onclick="loadMore('${pages}','${openId }');" style="position:relative;bottom:0px;">加载更多</button>
               </div> 
                <div class="-ft1" style="margin-bottom:0rem;width:100%;display:none;">
	               <button class="btn btn-default btn-block btn-lg ng-binding yghbtn" onclick="loadMore1('${pages}','${openId }');" style="position:relative;bottom:0px;">加载更多</button>
                </div> 
                
	 </div>  
   </div>
   </c:if>
   <c:if test="${empty cjlist }">
    <div class="text" style="margin-top:10%;">
		<!-- 			<p>教师监考信息暂无...</p> -->
    	<img alt="数据对接中..." src="<%=path%>/resources/images/djt.png" width="100%">
	</div>  
   </c:if>
</div>
		
</body>
<!--   <script type="text/javascript">
$(function() {
    $("table tr:nth-child(odd)").addClass("odd-row");
	$("table td:first-child, table th:first-child").addClass("first");
	$("table td:last-child, table th:last-child").addClass("last");
});
</script> -->

<script type="text/javascript">
	function show(count){
// 		alert("显示已归还");
		var Obtn01 = document.getElementById('M_btn01'+count);
	 	var Obtn02 = document.getElementById('M_btn02'+count);
	 	var tab01 = document.getElementById('TAB01'+count);
	 	var tab02 = document.getElementById('TAB02'+count);
		tab01.style.display='none';
 		tab02.style.display='block';
 		Obtn01.style.color='#2e87d3';
 		Obtn02.style.color='#fc4312';
 		Obtn01.style.borderBottom='none';
 		Obtn02.style.borderBottom='thick solid #fc4312';
 		$('.-ft1').show();
 		$('.-ft').hide();
	}
	
	function show1(count){
// 		alert("显示未归还");
		var Obtn01 = document.getElementById('M_btn01'+count);
	 	var Obtn02 = document.getElementById('M_btn02'+count);
	 	var tab01 = document.getElementById('TAB01'+count);
	 	var tab02 = document.getElementById('TAB02'+count);
	 	tab01.style.display='block';
		tab02.style.display='none';
		Obtn02.style.color='#2e87d3';
		Obtn01.style.color='#fc4312';
		Obtn01.style.borderBottom='thick solid #fc4312';
		Obtn02.style.borderBottom='none';
		$('.-ft1').hide();
 		$('.-ft').show();
	}
	
</script>
<style type="text/css">
.btn-block {
    display: block;
    width: 100%;
}
.btn-lg, .btn-group-lg > .btn {
    border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
    border-top-left-radius: 6px;
    border-top-right-radius: 6px;
    font-size: 18px;
    line-height: 1.33;
    padding-bottom: 10px;
    padding-left: 16px;
    padding-right: 16px;
    padding-top: 10px;
}
.btn-default {
    background-color: #fff;
    border-bottom-color: #ccc;
    border-left-color: #ccc;
    border-right-color: #ccc;
    border-top-color: #ccc;
    color: #333;
}
.btn {
border-bottom-style: solid;
    border-bottom-width: 1px;
    border-image-outset: 0 0 0 0;
    border-image-repeat: stretch stretch;
    border-image-slice: 100% 100% 100% 100%;
    border-image-source: none;
    border-image-width: 1 1 1 1;
    border-left-style: solid;
    border-left-width: 1px;
    border-right-style: solid;
    border-right-width: 1px;
    border-top-style: solid;
    border-top-width: 1px;
    cursor: pointer;
     text-align: center;
    vertical-align: middle;
    white-space: nowrap;
}
button, input, optgroup, select, textarea {
    -x-system-font: none;
    color: inherit;
    font-family: inherit;
    font-feature-settings: inherit;
    font-kerning: inherit;
    font-language-override: inherit;
    font-size: inherit;
    font-size-adjust: inherit;
    font-stretch: inherit;
    font-style: inherit;
    font-synthesis: inherit;
    font-variant-alternates: inherit;
    font-variant-caps: inherit;
    font-variant-east-asian: inherit;
    font-variant-ligatures: inherit;
    font-variant-numeric: inherit;
    font-variant-position: inherit;
    font-weight: inherit;
    line-height: inherit;
    margin-bottom: 0;
    margin-left: 0;
    margin-right: 0;
    margin-top: 0;
}
</style>
</html>
