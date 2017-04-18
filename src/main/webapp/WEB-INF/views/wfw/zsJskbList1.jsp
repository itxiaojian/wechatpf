<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>	教师课表 </title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"></meta>
    <meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5" />
    <link href="<%=path%>/resources/css/dailog.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/resources/css/calendar.css" rel="stylesheet" type="text/css" /> 
    <link href="<%=path%>/resources/css/dp.css" rel="stylesheet" type="text/css" />   
    <link href="<%=path%>/resources/css/alert.css" rel="stylesheet" type="text/css" /> 
    <link href="<%=path%>/resources/css/main.css" rel="stylesheet" type="text/css" /> 
    

    <script src="<%=path%>/resources/src/jquery.js" type="text/javascript"></script>  
    
    <script src="<%=path%>/resources/src/Plugins/Common.js" type="text/javascript"></script>    
    <script src="<%=path%>/resources/src/Plugins/datepicker_lang_HK.js" type="text/javascript"></script>     
    <script src="<%=path%>/resources/src/Plugins/jquery.datepicker.js" type="text/javascript"></script>

    <script src="<%=path%>/resources/src/Plugins/jquery.alert.js" type="text/javascript"></script>    
    <script src="<%=path%>/resources/src/Plugins/jquery.ifrmdailog.js" defer="defer" type="text/javascript"></script>
    <script src="<%=path%>/resources/src/Plugins/wdCalendar_lang_HK.js" type="text/javascript"></script>    
    <script src="<%=path%>/resources/src/Plugins/jquery.calendar.js" type="text/javascript"></script>  
    
	<script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
	<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<%--   	<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script> --%>
	<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="lightBlue"/>
	<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/> 
    
    <script type="text/javascript">
        var sxz=0;
        var rq=new Date();
        var showda='';
        var openId='';
        if(document.getElementById("openId")!=null){
        	openId=document.getElementById("openId").value;
        }
        if((rq.getMonth()+1)<10){
        	showda=rq.getFullYear()+"-0"+(rq.getMonth()+1)+"-"+rq.getDate();
        }else{
        	showda=rq.getFullYear()+"-"+(rq.getMonth()+1)+"-"+rq.getDate();
        }
        $(document).ready(function() {     
           var view="week";          
           
            var DATA_FEED_URL = "<%=path%>/wfw/ZsKb/getJskb";
            var op = {
                view: view,
                theme:3,
                showday: new Date(),
                ViewCmdhandler:View,
                onWeekOrMonthToDay:wtd,
                onBeforeRequestData: cal_beforerequest,
                onAfterRequestData: cal_afterrequest,
                onRequestDataError: cal_onerror, 
                autoload:true,
                url: DATA_FEED_URL + "?showdate="+showda+"&viewtype="+view+"&timezone=8&sxz="+sxz+"&openId="+openId  
            };
            var $dv = $("#calhead");
            var _MH = document.documentElement.clientHeight;
            var dvH = $dv.height() + 2;
            op.height = _MH - dvH;
            op.eventItems =[];

            var p = $("#gridcontainer").bcalendar(op).BcalGetOp();
            if (p && p.datestrshow) {
                $("#txtdatetimeshow").text(p.datestrshow);
            }
            $("#caltoolbar").noSelect();
            
            $("#hdtxtshow").datepicker({ picker: "#txtdatetimeshow", showtarget: $("#txtdatetimeshow"),
            onReturn:function(r){                          
                            var p = $("#gridcontainer").gotoDate(r).BcalGetOp();
                            if (p && p.datestrshow) {
                                $("#txtdatetimeshow").text(p.datestrshow);
                            }
                     } 
            });
            function cal_beforerequest(type)
            {
                var t="Loading data...";
                switch(type)
                {
                    case 1:
                        t="Loading data...";
                        break;
                    case 2:                      
                    case 3:  
                    case 4:    
                        t="The request is being processed ...";                                   
                        break;
                }
                $("#errorpannel").hide();
                $("#loadingpannel").html(t).show();    
            }
            function cal_afterrequest(type)
            {
                switch(type)
                {
                    case 1:
                        $("#loadingpannel").hide();
                        break;
                    case 2:
                    case 3:
                    case 4:
                        $("#loadingpannel").html("Success!");
                        window.setTimeout(function(){ $("#loadingpannel").hide();},2000);
                    break;
                }              
               
            }
            function cal_onerror(type,data)
            {
                $("#errorpannel").show();
            }
            function View(data)
            {
                var str = "";
                $.each(data, function(i, item){
                    str += "[" + i + "]: " + item + "\n";
                });
                //alert(str);               
            } 
            function wtd(p)
            {
               if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }
                $("#caltoolbar div.fcurrent").each(function() {
                    $(this).removeClass("fcurrent");
                })
                $("#showdaybtn").addClass("fcurrent");
            }
            //to show day view
            $("#showdaybtn").click(function(e) {
                //document.location.href="#day";
                $("#caltoolbar div.fcurrent").each(function() {
                    $(this).removeClass("fcurrent");
                })
                $(this).addClass("fcurrent");
                var p = $("#gridcontainer").swtichView("day").BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }
            });
            //to show week view
            $("#showweekbtn").click(function(e) {
                //document.location.href="#week";
                $("#caltoolbar div.fcurrent").each(function() {
                    $(this).removeClass("fcurrent");
                })
                $(this).addClass("fcurrent");
                var p = $("#gridcontainer").swtichView("week").BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }

            });
            //to show month view
            $("#showmonthbtn").click(function(e) {
                //document.location.href="#month";
                $("#caltoolbar div.fcurrent").each(function() {
                    $(this).removeClass("fcurrent");
                })
                $(this).addClass("fcurrent");
                var p = $("#gridcontainer").swtichView("month").BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }
            });
            
            $("#showreflashbtn").click(function(e){
                $("#gridcontainer").reload();
            });
            //go to today
            $("#showtodaybtn").click(function(e) {
                var p = $("#gridcontainer").gotoDate().BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }


            });
            //previous date range
            $("#sfprevbtn").click(function(e) {
            	sxz=-1;
                var p = $("#gridcontainer").previousRange().BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }

            });
            //next date range
            $("#sfnextbtn").click(function(e) {
            	sxz=1;
                var p = $("#gridcontainer").nextRange().BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }
            });
            
        });
        function todayxxx(){
    		var p = $("#gridcontainer").gotoDate().BcalGetOp();
	        if (p && p.datestrshow) {
	            $("#txtdatetimeshow").text(p.datestrshow);
	        }
		}
    </script>    
</head>
<body onload="todayxxx();return true">
    <div>
      <input type="hidden" name="openId" id="openId" value="${openId }"></input>
      <div id="calhead" style="padding-left:1px;padding-right:1px;">          
            <div class="cHead"><div class="ftitle">教师课表</div>
            <div id="loadingpannel" class="ptogtitle loadicon" style="display: none;">正在加载...</div>
             <div id="errorpannel" class="ptogtitle loaderror" style="display: none;">没有数据。</div>
            </div>          
            
            <div id="caltoolbar" class="ctoolbar">
            <div class="btnseparator"></div>
              <div  id="showweekbtn" class="fbutton fcurrent">
                <div><span title='Week' class="showweekview">星期</span></div>
            </div>
             <div class="btnseparator"></div>
            <div id="sfprevbtn" title="Prev"  class="fbutton">
              <span class="fprev"></span>

            </div>
            
            <div class="btnseparator"></div>
            <div class="fshowdatep fbutton">
                    <div>
                        <input type="hidden" name="txtshow" id="hdtxtshow" />
                        <span id="txtdatetimeshow">加载中...</span>
                     </div> 
            </div>
            <div class="btnseparator"></div>
            <div id="sfnextbtn" title="Next" class="fbutton">
                <span class="fnext"></span>
            </div>
            <div class="clear"></div>
            </div>
      </div>
      <div style="padding:1px;">

        <div class="t1 chromeColor">
            &nbsp;</div>
        <div class="t2 chromeColor">
            &nbsp;</div>
        <div id="dvCalMain" class="calmain printborder">
            <div id="gridcontainer" style="overflow-y: visible;">
            </div>
        </div>
        <div class="t2 chromeColor">

            &nbsp;</div>
        <div class="t1 chromeColor">
            &nbsp;
        </div>   
        </div>
     
  </div>
    
</body>
</html>
