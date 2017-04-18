<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html class="fixed js flexbox flexboxlegacy csstransforms csstransforms3d no-overflowscrolling no-mobile-device custom-scroll">
<head>
<title>失物招领</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="HandheldFriendly" content="true">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="msapplication-tap-highlight" content="no">
    
    <link rel="stylesheet" href="<%=path%>/resources/js/text/loading.css">
    <!-- Vendor CSS -->
    <link rel="stylesheet" href="<%=path%>/resources/js/text/user-vendor.css">
    <link rel="stylesheet" href="<%=path%>/resources/js/text/font-awesome.css">
    
    <!-- 
    <link rel="stylesheet" href="/assets/vendor/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/assets/vendor/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/assets/vendor/magnific-popup/magnific-popup.css" />
    <link rel="stylesheet" href="/assets/vendor/pnotify/pnotify.custom.css" />
     -->
    <!-- Specific Page Vendor CSS -->
    <!-- should change -->
    
    <link rel="stylesheet" href="<%=path%>/resources/js/text/datepicker3.css">
    <link rel="stylesheet" href="<%=path%>/resources/js/text/bootstrap-multiselect.css"> 
    
    <!-- Theme CSS -->
    <link rel="stylesheet" href="<%=path%>/resources/js/text/theme.css">

    <!-- Skin CSS -->
    <link rel="stylesheet" href="<%=path%>/resources/js/text/default.css">

    <!-- Theme Custom CSS -->
    <link rel="stylesheet" href="<%=path%>/resources/js/text/theme-custom.css">

    <!-- Head Libs -->
    <script src="<%=path%>/resources/js/text/modernizr.js"></script>
    
    <link rel="stylesheet" href="<%=path%>/resources/js/text/index.css">
    <style type="text/css"> 
      .tab-r {
        display: table-row!important;
      }
      .fa-map-marker {
        margin-left: 2px!important;
      }
    </style>
  <style class="firebugResetStyles" type="text/css" charset="utf-8">/* See license.txt for terms of usage */

/** reset styling **/

.firebugResetStyles {

    z-index: 2147483646 !important;

    top: 0 !important;

    left: 0 !important;

    display: block !important;

    border: 0 none !important;

    margin: 0 !important;

    padding: 0 !important;

    outline: 0 !important;

    min-width: 0 !important;

    max-width: none !important;

    min-height: 0 !important;

    max-height: none !important;

    position: fixed !important;

    transform: rotate(0deg) !important;

    transform-origin: 50% 50% !important;

    border-radius: 0 !important;

    box-shadow: none !important;

    background: transparent none !important;

    pointer-events: none !important;

    white-space: normal !important;

}

style.firebugResetStyles {

    display: none !important;

}



.firebugBlockBackgroundColor {

    background-color: transparent !important;

}



.firebugResetStyles:before, .firebugResetStyles:after {

    content: "" !important;

}

/**actual styling to be modified by firebug theme**/

.firebugCanvas {

    display: none !important;

}



/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

.firebugLayoutBox {

    width: auto !important;

    position: static !important;

}



.firebugLayoutBoxOffset {

    opacity: 0.8 !important;

    position: fixed !important;

}



.firebugLayoutLine {

    opacity: 0.4 !important;

    background-color: #000000 !important;

}



.firebugLayoutLineLeft, .firebugLayoutLineRight {

    width: 1px !important;

    height: 100% !important;

}



.firebugLayoutLineTop, .firebugLayoutLineBottom {

    width: 100% !important;

    height: 1px !important;

}



.firebugLayoutLineTop {

    margin-top: -1px !important;

    border-top: 1px solid #999999 !important;

}



.firebugLayoutLineRight {

    border-right: 1px solid #999999 !important;

}



.firebugLayoutLineBottom {

    border-bottom: 1px solid #999999 !important;

}



.firebugLayoutLineLeft {

    margin-left: -1px !important;

    border-left: 1px solid #999999 !important;

}



/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

.firebugLayoutBoxParent {

    border-top: 0 none !important;

    border-right: 1px dashed #E00 !important;

    border-bottom: 1px dashed #E00 !important;

    border-left: 0 none !important;

    position: fixed !important;

    width: auto !important;

}



.firebugRuler{

    position: absolute !important;

}



.firebugRulerH {

    top: -15px !important;

    left: 0 !important;

    width: 100% !important;

    height: 14px !important;

    border-top: 1px solid #BBBBBB !important;

    border-right: 1px dashed #BBBBBB !important;

    border-bottom: 1px solid #000000 !important;

}



.firebugRulerV {

    top: 0 !important;

    left: -15px !important;

    width: 14px !important;

    height: 100% !important;

    border-left: 1px solid #BBBBBB !important;

    border-right: 1px solid #000000 !important;

    border-bottom: 1px dashed #BBBBBB !important;

}



.overflowRulerX > .firebugRulerV {

    left: 0 !important;

}



.overflowRulerY > .firebugRulerH {

    top: 0 !important;

}



/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

.fbProxyElement {

    position: fixed !important;

    pointer-events: auto !important;

}

</style></head>
  <body>
    <div class="row fix-mg">
    <input id="openId" value="${openId}" type="hidden" />
      <div class="panel panel-primary mb-none">
        <header class="panel-heading center no-radius p-md fix-mg">
          <a href="<%=path%>/wfw/ZsText/toText?openId=${openId}" class="pull-left text-primary-inverse text-xl"><i class="fa">
           <img src="<%=path%>/resources/img/wzy/back.png" style="width:25px;height:25px"/>
          </i></a>
          <a href="#" id="add_lost" onclick="addshow()" class="pull-right text-primary-inverse text-xl"><i class="fa">
           <img src="<%=path%>/resources/img/wzy/swzladd.png" style="width:20px;height:20px"/>
          </i></a>
          <h2 class="panel-title">我发布的</h2>
        </header>
        <div class="tabs m-xs">
          <ul class="nav nav-tabs nav-justify tabs-primary mt-xs">
            <li class="active" onclick="changeType(1)">
              <a href="#lost_info" id="a1" data-toggle="tab" class="text-center  text-bold">遗失信息</a>
            </li>
            <li onclick="changeType(2)">
              <a href="#found_info" id="a2" data-toggle="tab" class="text-center text-bold">招领信息</a>
            </li>
          </ul>
          <div class="tab-content p-none">
            <div id="found_info" class="tab-pane">
              <div class="timeline timeline-simple mb-md">
                <div class="tm-body pt-xs pr-xs">
                  <ol id="found_ol" class="tm-items"></ol>
                </div>
              </div>
            </div>
            <div id="lost_info" class="tab-pane active">
              <div class="timeline timeline-simple mb-md">
                <div class="tm-body pt-xs pr-xs">
                  <ol id="lost_ol" class="tm-items"></ol>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xs-12">
        <button id="pullLoadingBtn" type="button" class="m-none btn btn-primary success btn-md btn-block no-radius">下拉加载</button>
      </div>
    </div>
    
    <div id="formModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
      <form id="add-form" class="form-horizontal mb-xlg" novalidate="novalidate">
        <section class="panel panel-primary m-md">
          <header class="panel-heading bg-primary p-sm">
            <h2 id="formTitle" class="panel-title">新增</h2>           
          </header>
          <div class="panel-body">
          <input id="lostId" name="lostId" value="" type="hidden">
            <div class="form-group">
              <div class="col-sm-12">
                <select style="display: none;" id="lostType" name="lostType" class="form-control" data-plugin-multiselect="">
                  <option value="2" selected="selected">招领信息</option>
                  <option value="1">遗失信息</option>
                </select>
     <!--       <div class="btn-group">
                <button title="招领信息" type="button" class="multiselect dropdown-toggle btn btn-default" data-toggle="dropdown">招领信息
                    <b class="caret"></b>
                 </button>
                	<ul class="multiselect-container dropdown-menu">
                		<li class="active">
                			<a href="javascript:void(0);">
                				<label class="radio"><input checked="checked" value="2" name="multiselect" type="radio"> 招领信息</label>
                			</a>
                		</li>
                		<li>
                			<a href="javascript:void(0);">
                			<label class="radio"><input value="1" name="multiselect" type="radio"> 遗失信息</label>
                			</a>
                		</li>
                	  </ul>
                </div> -->
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-12">
                <input id="description" name="description" class="form-control" placeholder="信息标题..." data-plugin-maxlength="" maxlength="20" required="" type="text">
              </div>
            </div>
            <div class="form-group">
               <div class="col-sm-12">
                <textarea rows="2" id="details" name="details" class="form-control" placeholder="详情描述..." data-plugin-maxlength="" maxlength="50" required=""></textarea>
              </div>
            </div>
            <div class="form-group">
               <div class="col-sm-12">
                <input id="place" name="place" class="form-control" placeholder="发生地点..." required="" type="text">
              </div>
            </div>
            <div class="form-group">
               <div class="col-sm-12">
                <div class="input-group" required="">
                  <span class="input-group-addon">
                    <i class="fa">
                    <img src="<%=path%>/resources/img/wzy/calendar.png" style="width:20px;height:20px"/>
                    </i>
                  </span>
                  <input id="lostTime" name="lostTime" data-plugin-datepicker="" 
                    data-plugin-options="{&quot;format&quot;:&quot;yyyy-mm-dd&quot;,&quot;language&quot;:&quot;zh-CN&quot;,&quot;autoclose&quot;:&quot;true&quot;}"
                  class="form-control" type="text">
                </div>
              </div>
            </div>
<!--             <div class="form-group">
               <div class="col-sm-12">
                <input id="contactName" name="contactName" class="form-control" placeholder="联系人..." required="" type="text">
              </div>
            </div>
            <div class="form-group">
               
              <div class="col-sm-12">
                <input id="contactPhone" name="contactPhone" class="form-control" placeholder="电话..." data-plugin-maxlength="" maxlength="11" type="text">
              </div>
            </div>
            <div class="form-group">
              
              <div class="col-sm-12">
                <input id="contactQQ" name="contactQQ" class="form-control" placeholder="QQ..." data-plugin-maxlength="" maxlength="10" type="text">
              </div>
            </div> -->
          </div>
          <input  id="openId_add" value="${openId}" name="openId" type="hidden"/>
          <footer class="panel-footer">
            <div class="row">
              <div class="col-md-12 text-right">
                <button type="submit" class="btn btn-primary">提 交</button>
                <button class="btn btn-default" data-dismiss="modal">取 消</button>
              </div>
            </div>
          </footer>
        </section><!-- /.modal-dialog -->
      </form>
    </div><!-- /.modal -->
    
    <div id="editformModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
      <form id="edit-form" class="form-horizontal mb-xlg" novalidate="novalidate">
        <section class="panel panel-primary m-md">
          <header class="panel-heading bg-primary p-sm">
            <h2 id="formTitle" class="panel-title">编辑</h2>           
          </header>
          <div class="panel-body">
          <input id="editlostId" name="lostId" value="" type="hidden">
            <div class="form-group">
              <div class="col-sm-12">
                <select style="display: none;" id="editlostType" name="lostType" class="form-control" data-plugin-multiselect="">
                  <option value="2" selected="selected">招领信息</option>
                  <option value="1">遗失信息</option>
                </select>
               <!--  <div class="btn-group"><button title="招领信息" type="button"
                 class="multiselect dropdown-toggle btn btn-default" data-toggle="dropdown">招领信息 
                 <b class="caret"></b></button><ul class="multiselect-container dropdown-menu">
                 <li class="active"><a href="javascript:void(0);"><label class="radio">
                 <input checked="checked" value="2" name="multiselect" type="radio"> 招领信息</label></a></li>
                 <li><a href="javascript:void(0);">
                 <label class="radio"><input value="1" name="multiselect" type="radio"> 遗失信息</label></a></li></ul></div> -->
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-12">
                <input id="editdescription" name="description" class="form-control" placeholder="信息标题..." data-plugin-maxlength="" maxlength="20" required="" type="text">
              </div>
            </div>
            <div class="form-group">
               <div class="col-sm-12">
                <textarea rows="2" id="editdetails" name="details" class="form-control" placeholder="详情描述..." data-plugin-maxlength="" maxlength="50" required=""></textarea>
              </div>
            </div>
            <div class="form-group">
               <div class="col-sm-12">
                <input id="editplace" name="place" class="form-control" placeholder="发生地点..." required="" type="text">
              </div>
            </div>
            <div class="form-group">
               <div class="col-sm-12">
                <div class="input-group" required="">
                  <span class="input-group-addon">
                    <i class="fa">
                     <img src="<%=path%>/resources/img/wzy/calendar.png" style="width:20px;height:20px"/>
                    </i>
                  </span>
                  <input id="editlostTime" name="lostTime" data-plugin-datepicker="" 
                  data-plugin-options="{&quot;format&quot;:&quot;yyyy-mm-dd&quot;,&quot;language&quot;:&quot;zh-CN&quot;,&quot;autoclose&quot;:&quot;true&quot;}"
                   class="form-control"  type="text">
                </div>
              </div>
            </div>
<!--             <div class="form-group">
               <div class="col-sm-12">
                <input id="editcontactName" name="contactName" class="form-control" placeholder="联系人..." required="" type="text">
              </div>
            </div>
            <div class="form-group">
               
              <div class="col-sm-12">
                <input id="editcontactPhone" name="contactPhone" class="form-control" placeholder="电话..." data-plugin-maxlength="" maxlength="11" type="text">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-12">
                <input id="editcontactQQ" name="contactQQ" class="form-control" placeholder="QQ..." data-plugin-maxlength="" maxlength="10" type="text">
              </div>
            </div> -->
          </div>
           <input  id="openId_edit" value="${openId}" name="openId" type="hidden"/>
          <footer class="panel-footer">
            <div class="row">
              <div class="col-md-12 text-right">
                <button type="submit" class="btn btn-primary">提 交</button>
                <button class="btn btn-default" data-dismiss="modal">取 消</button>
              </div>
            </div>
          </footer>
        </section><!-- /.modal-dialog -->
      </form>
    </div><!-- /.modal -->
    
    <!-- Vendor -->
    
    <!-- Vendor -->
    <!-- 
    <script src="/assets/vendor/jquery/jquery.min.js"></script>
    <script src="/assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
    <script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="/assets/vendor/pnotify/pnotify.custom.js"></script>
    
    
    <script src="/assets/vendor/nanoscroller/nanoscroller.js"></script>
    <script src="/assets/vendor/magnific-popup/magnific-popup.js"></script>
    <script src="/assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
     -->
     
    <script src="<%=path%>/resources/js/text/user-vendor.js"></script>
    
    <script src="<%=path%>/resources/js/text/common_002.js"></script>
    <script src="<%=path%>/resources/js/text/config.js"></script>
    <script src="<%=path%>/resources/js/text/WeixinApi.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function() {
      $(window).load(function(){
        //$('#loadingWrapperBase').fadeOut('slow');
        --LOADINGSTATUS;
        if(LOADINGSTATUS == 0) {
          //$('#loadingWrapperBase').fadeOut(1000);
          $('#loadingWrapper').hide();
        }
      });
    });
    </script>
    
    <!-- Specific Page Vendor -->
    <!-- should change -->
    <script src="<%=path%>/resources/js/text/bootstrap-datepicker.js"></script>
    <script src="<%=path%>/resources/js/text/bootstrap-datepicker_002.js"></script>
    <script src="<%=path%>/resources/js/text/bootstrap-maxlength.js"></script>
    <script src="<%=path%>/resources/js/text/jquery.js"></script>
    <script src="<%=path%>/resources/js/text/message_cn.js"></script>
    <script src="<%=path%>/resources/js/text/bootstrap-multiselect.js"></script>
    
    <!-- Theme Base, Components and Settings -->
    <script src="<%=path%>/resources/js/text/theme.js"></script>
    
    <!-- Theme Custom -->
    <script src="<%=path%>/resources/js/text/theme_003.js"></script>
    
    <!-- Theme Initialization Files -->
    <script src="<%=path%>/resources/js/text/theme_002.js"></script>
    
    
    <div style="width:120%;" id="loadingWrapper" style="display: none;">
      <div id="loading">
        <div class="bubblingG"><span class="fullbg words-xiao" id="bubblingG_1"></span><span class="fullbg words-yuan" id="bubblingG_2"></span><span class="fullbg words-plus" id="bubblingG_3"></span><!-- <span class="fullbg words-p" id="bubblingG_4"></span><span class="fullbg words-u" id="bubblingG_5"></span><span class="fullbg words-s" id="bubblingG_6"></span><span class="fullbg words-P" id="bubblingG_7"></span><span class="fullbg words-l" id="bubblingG_8"></span><span class="fullbg words-u" id="bubblingG_9"></span><span class="fullbg words-s" id="bubblingG_10"></span> --></div>
      </div>
    </div>
    
    <div style="display: none;" id="loadingWrapperBase">
      <div id="loadingBase">
        <div class="bubblingG"><span class="fullbg words-xiao" id="bubblingG_1Base"></span><span class="fullbg words-yuan" id="bubblingG_2Base"></span><span class="fullbg words-plus" id="bubblingG_3Base"></span><!-- <span class="fullbg words-p" id="bubblingG_4"></span><span class="fullbg words-u" id="bubblingG_5"></span><span class="fullbg words-s" id="bubblingG_6"></span><span class="fullbg words-P" id="bubblingG_7"></span><span class="fullbg words-l" id="bubblingG_8"></span><span class="fullbg words-u" id="bubblingG_9"></span><span class="fullbg words-s" id="bubblingG_10"></span> --></div>
      </div>
    </div>
    <script src="<%=path%>/resources/js/text/jweixin-1.js"></script>
    <!-- self js -->
    <script src="<%=path%>/resources/js/text/common.js"></script>
    <script type="text/javascript">
      var glotype =2;//2是招领信息；1是遗失信息
      var pn2 = 1;//记录招领信息的当前页码
      var pn1 = 1;//记录遗失信息的当前页码
      var pageAmount2 = 1;//招领信息总页数
      var pageAmount1 = 1;//遗失信息总页数
      /*form */
      var formUrl = "<%=path%>"+"/wfw/ZsText/creat";
      $(function(){
        $("#found_ol").empty();
        $("#lost_ol").empty();
        getLost(pn2, 2);
        getLost(pn1, 1);
        changeType(1);
          validateForm("#add-form", function(form){
          var postData = $(form).serializeJson();
          delete postData["lostId"];
          postData["_url"] = formUrl;
          postData["_noLoading"] = true;
          request(postData, false, function(data) {
            /* if (checkDataStatus(data)) { */
            if(data=='1'){
              pageNote({
                title: "提交成功！",
                text: "提交成功，请刷新查看！",
                type: "success"
              });
              $("#found_ol").empty();
              $("#lost_ol").empty();
              if(postData.lostType ==1){
            	  var e1=document.getElementById('a1');
				  e1.click();
              }else{
				  var e2=document.getElementById('a2');
				  e2.click();
              }
              getLost(pn2, 2);
              getLost(pn1, 1);
              $("#formModal").modal("hide");
              /* }else{
              pageNoteError("加载失败，请重试！");
              } */
             }
          });
        });
          
        validateForm("#edit-form", function(form){
              var postData = $(form).serializeJson();
              postData["_url"] =  "<%=path%>"+"/wfw/ZsText/update";
              postData["_noLoading"] = true;
              request(postData, false, function(data) {
               /*  if (checkDataStatus(data)) { */
                  pageNote({
                    title: "编辑成功！",
                    text: "编辑成功，请刷新查看！",
                    type: "success"
                  });
                  $("#found_ol").empty();
                  $("#lost_ol").empty();
                  getLost(pn2, 2);
                  getLost(pn1, 1);
                  $("#editformModal").modal("hide");
                /* }else{
                   pageNoteWarning("加载失败，请重试！");
                } */
              });
          });
       // getShare();
      });
  
      $(window).scroll(function(){
          var scrollTop = $(this).scrollTop()
          var scrollHeight = $(document).height();
          var windowHeight = $(this).height();
          if(scrollTop + windowHeight == scrollHeight){
            setTimeout(function() {
              $("#pullLoadingBtn").text("正在加载...");
               showLoading();
              if(glotype==2){
                if(pn2<pageAmount2){
                  getLost(++pn2,2);
                }else{
                  $("#pullLoadingBtn").text("已经最后一页了！");
                   hideLoading();
                }
              }else{
                if(pn1<pageAmount1){
                  getLost(++pn1,1);
                }else{
                  $("#pullLoadingBtn").text("已经最后一页了！");
                   hideLoading();
                }
              }
            }, 1000); //1秒
            $("#pullLoadingBtn").text("下拉加载");
            hideLoading();
          }
      });

      function changeType(num) {
        glotype = num;
      }
      
      function getLost (pn,lostType){
          var postData = {};
          postData._url = "<%=path%>"+"/wfw/ZsText/mine";
          postData.DESC = [];
          postData["_noLoading"] = true;
          postData.DESC.push("createTime");
          postData.pageNum = pn;
          postData.pageSize = 5;
          postData.lostType = lostType;
          postData.openId=$('#openId').val();
          request(postData,true,function(data) { 
           /*  if (checkDataStatus(data)) { */
            //  var dataResult = data.value;
              if(lostType == 2){
            	if(data=="" || data==null || data=='undefined'){
                  $("#found_ol").append($("<div>").addClass("text-center").text("暂无数据"));
                }    
            	  var rst = eval(data);
              	$.each(rst,function(i,value){ 
          		    pageAmount2 = value.pageAmount;
                  });
              	if(pageAmount2=='undefined' || pageAmount2==''){
              		pageAmount2= 1;
              	}
              }else{
            	  if(data=="" || data==null || data=='undefined'){
                  $("#lost_ol").append($("<div>").addClass("text-center").text("暂无数据"));
                }              	  
              	var rst = eval(data);
            	$.each(rst,function(i,value){ 
        			 pageAmount1 = value.pageAmount;
               });
            	if(pageAmount1=='undefined' || pageAmount1==''){
            		pageAmount1= 1;
            	}
              }
                showLostList(data,lostType);
              
           /*  } else {
              pageNoteWarning(data.message);
            } */
          });
         
        }
      function showLostList (data,type) {
    	   var rst = eval(data);
			$.each(rst,function(i,value){ 
			if(i<rst.length-1){
		      showOneLost(value,type);
			   }
		   });
        }
      function showOneLost (data,type) {
        var $outli = $("<li/>").addClass("top");
        var $divone = $("<div/>").addClass("tm-box p-sm");
        var $divtwo = $("<div/>").addClass("content");
        $outli.append($divone.append($divtwo));
        var $h5 = $("<h5/>").addClass("text-bold mt-xs text-dark").text(data.bt);
        $divtwo.append($h5);
        var $ul = $("<ul/>").addClass("simple-user-list");
        $divtwo.append($ul);
        var $li1 = $("<li/>").addClass("mb-xs");
        var $figure1 = $("<figure/>").addClass("image fa text-primary text-lg");
        var $span1 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(data.xwzs);
        var $img1 = $("<img style='width:19px;height:19px;position: absolute;'/>").attr("src","<%=path%>/resources/img/wzy/detail.png");
        $li1.append($figure1).append($img1).append($span1);
        $ul.append($li1);
        var $li2 = $("<li/>").addClass("mb-xs");
        var $figure2 = $("<figure/>").addClass("image fa  text-primary text-xl");
        var $img2 = $("<img style='width:20px;height:20px;position: absolute;'/>").attr("src","<%=path%>/resources/img/wzy/place.png");
        var $span2 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(ifNull(data.dd,"无"));
        $li2.append($figure2).append($img2).append($span2);
        $ul.append($li2);
        var $li3 = $("<li/>").addClass("mb-xs");
        var $figure3 = $("<figure/>").addClass("image fa  text-primary text-lg");
        var $span3 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(Long2TimeString(data.fqsj,'yyyy-MM-dd'));
        var $img3 = $("<img style='width:22px;height:22px;position: absolute;'/>").attr("src","<%=path%>/resources/img/wzy/clock.png");
        $li3.append($figure3).append($img3).append($span3);
        $ul.append($li3);
        var $li4 = $("<li/>").addClass("mb-none");
        var $figure4 = $("<figure/>").addClass("image fa  text-primary text-lg");
        var $img4 = $("<img style='width:23px;height:23px;position: absolute;'/>").attr("src","<%=path%>/resources/img/wzy/photo.png");
        var $span4 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(ifNull(data.fbrxm,"无"));
        $li4.append($figure4).append($img4).append($span4);
        var $img5 = $("<img style='width:25px;height:25px;position: absolute;'/>").attr("src","<%=path%>/resources/img/wzy/qq.png");
        $ul.append($li4);
        <%-- var $li5 = $("<li/>").addClass("mb-xs mt-xs");
        var $figure5 = $("<figure/>").addClass("image fa  text-primary text-lg");
        var $span5 = $("<span style='margin-left:42px;margin-top:-20px;position: absolute;'/>").addClass("text-dark tab-r").text(ifNull(data.contactPhone,"无"));
        $li5.append($figure5).append($img5).append($span5);
        $ul.append($li5);
        var $li6 = $("<li/>").addClass("mb-none");
        var $figure6 = $("<figure/>").addClass("image fa  text-primary text-md");
         var $img6 = $("<img style='width:25px;height:25px;'/>").attr("src","<%=path%>/resources/img/wzy/qq.png");
        var $span6 = $("<span style='margin-left:42px;margin-top:-20px;position: absolute;'/>").addClass("text-dark tab-r").text(ifNull(data.contactQQ,"无"));
        $li6.append($figure6).append($img6).append($span6);
        $ul.append($li6); --%>
        $divtwo.append("<hr class='dashed short'>");
        var $divop = $("<div/>").addClass("text-right m-none");
        if(data.xwzt == 1){//未认领
          var $aok = $("<a class='ml-sm fa  text-xl'></a>");
          var $img7 = $("<img style='width:20px;height:20px;cursor:pointer'/>").attr("src","<%=path%>/resources/img/wzy/check.png").attr("onclick","oklost("+data.id+",this)");
          var $aeidt = $("<a class='ml-sm fa  text-xl'></a>");
          var $img8 = $("<img style='width:20px;height:20px;cursor:pointer'/>").attr("src","<%=path%>/resources/img/wzy/edit.png").attr("onclick","detailslost("+data.id+")");
          $divop.append($aok).append($img7).append($aeidt).append($img8);
        }else if(data.xwzt == 2) {//已认领
          var $note = $("<div class='pull-left'>已认领</div>");
          $divop.append($note);
        }else if(data.xwzt == 4){ //已过期
          var $note = $(" <div class='pull-left'>已过期</div>");
          var $aundo = $("<a class='ml-sm fa  text-xl'></a>");
          var $img10 = $("<img style='width:20px;height:20px;cursor:pointer'/>").attr("src","<%=path%>/resources/img/wzy/resave.png").attr("onclick","republost("+data.id+",this)");
          $divop.append($note).append($aundo).append($img10);
        }
          $adel = $("<a class='ml-sm fa text-xl'></a>");
          var $img9 = $("<img style='width:20px;height:20px;cursor:pointer'/>").attr("src","<%=path%>/resources/img/wzy/del.png").attr("onclick","deletelost ("+data.id+",this)");
        $divop.append($adel).append($img9);
        $divtwo.append($divop);
        if(type == 2 ){
          $("#found_ol").append($outli); 
        }else{
          $("#lost_ol").append($outli); 
        }
      }  
      function deletelost (id,obj) { //删除信息
            if(confirm("确定要删除这条信息吗？")){
              var postData = {};
              postData._url ="<%=path%>"+"/wfw/ZsText/delete";
              postData.lostId = id;
              postData.lostType = 3;
              postData["_noLoading"] = true;
              request(postData,false,function (data){
            	  if(data=='1'){
                /* if(checkDataStatus(data)){ */
                  $(obj).parentsUntil("li").parent().remove();
                  alert("删除成功！");
                }else{
                  pageNoteError("服务器访问异常，请刷新重试！");
                }
              });
            }
        }  
        function oklost(id,obj) {//完成操作
            if(confirm("完成后就无法继续编辑此消息，你确定要这么做吗？")){
              var postData = {};
              postData._url = "<%=path%>"+"/wfw/ZsText/end";
              postData.lostId = id;
              postData.lostZt = 2;
              postData["_noLoading"] = true;
              request(postData,false,function (data){
               /*  if(checkDataStatus(data)){ */
            	   if(data=='1'){
                  $(obj).next().remove();
                  $(obj).parent().append("<div class='pull-left'>已认领</div>");
                  $(obj).remove();
                }else{
                  pageNoteError("服务器访问异常，请刷新重试！");
                }
              });
            }
        }

        function republost(id,obj) {//重新发布操作
            if(confirm("你确定要重新发布此消息吗？")){
              var postData = {};
              postData._url = "lost/update";
              postData.lostId = id;
              postData.status = 1;
              var myDate = Date.parse(new Date());
              myDate = Long2TimeString(myDate, 'yyyy-MM-dd hh:mm:ss');
              postData.cerateTime = myDate;
              postData.lastUpdTime = myDate;
              postData["_noLoading"] = true;
              request(postData,false,function (data){
                if(checkDataStatus(data)){
                  var pdiv =  $(obj).parent();
                  pdiv.empty();
                  var $aok = $("<a class='ml-sm fa fa-check text-xl'></a>").attr("onclick","oklost("+id+",this)");
                  var $aeidt = $("<a class='ml-sm fa fa-edit text-xl'></a>").attr("onclick","editlost("+id+")");
                  var $adel = $("<a class='ml-sm fa fa-trash-o text-xl'></a>").attr("onclick","deletelost ("+id+",this)");
                  pdiv.append($aok).append($aeidt).append($adel);
                }else{
                  pageNoteWarning("服务器访问异常，请刷新重试！");
                }
              });
            }
        }
        function detailslost(id) {
          formUrl = "<%=path%>"+"/wfw/ZsText/show";
          var postData = {};
          postData._url = "<%=path%>"+"/wfw/ZsText/show";
          postData.lostId = id;
          postData["_noLoading"] = true;
          request(postData,true,function(data){
            /*  if (checkDataStatus(data)) { */
              //执行将信息填入表单
              var rst = eval(data);
        	  $.each(rst,function(i,value){ 
              $("#editformTitle").text("编辑");
              $("#editlostId").val(id);
              if(value.lx == 1){
                $("#editlostType").empty().append("<option value='1' selected>遗失信息</option>")
                .append("<option value='2' >招领信息</option>");
                $(".dropdown-toggle").text("遗失信息");
                $(".dropdown-toggle").removeAttr("title");
                $(".dropdown-toggle").attr("title","遗失信息");
                $(".multiselect-container li:nth-child(1)").removeClass("active");
                $(".multiselect-container li:nth-child(2)").addClass("active");
              }else {
                $("#editlostType").empty().append("<option value='2' selected>招领信息</option>")
                .append("<option value='1'>遗失信息</option>");
                $(".dropdown-toggle").text("招领信息");
                $(".dropdown-toggle").removeAttr("title");
                $(".dropdown-toggle").attr("title","招领信息");
                $(".multiselect-container li:nth-child(2)").removeClass("active");
                $(".multiselect-container li:nth-child(1)").addClass("active");
              }
              $("#editdescription").val(value.bt);
              $("#editdetails").val(value.xwzs);
              $("#editplace").val(value.dd);
              var time = value.fqsj;
              time = Long2TimeString(time, 'yyyy-MM-dd');
              $("#editlostTime").val(time);
              $("#editcontactName").val(value.fbrxm);
             /*  $("#editcontactPhone").val(result.contactPhone);
              $("#editcontactQQ").val(result.contactQQ); */
              $("#editformModal").modal("show");
           /*  }else{
              pageNoteError("修改失败，请刷新重试！");
            } */
            });
          });
        } 
        function addshow(){
          $("#formTitle").text("新增");
          formUrl =  "<%=path%>"+"/wfw/ZsText/creat";
          $("#description").val("");
          $("#details").val("");
          $("#place").val("");
          var Time = new Date();
          var time = Time.getTime();
          time = Long2TimeString(time, 'yyyy-MM-dd');
          $("#lostTime").val(time);
          //$("#contactName").val("");
          //$("#contactPhone").val("");
          //$("#contactQQ").val("");
          $("#formModal").modal("show");
        }
        
        function getShare(){
            wxShare({
              appId: 'wx4d9f6c21dc326084',
              timestamp: '1468478326',
              nonceStr: '5cd00ef8-82be-4d66-b603-18195c3c1fa9',
              signature: 'fcbeb0910c1941588809215286c587d15134f012',
              title: '天津大学失物招领',
              desc: '失物招领',
              link: webRootPath+'tju'+'/lost/lists/share',
              imgUrl: 'http://weixin.campusplus.com/uploads/weixin/6a716d4e-5d68-418d-99a1-6f2ed4944f46.jpg',
              type: 'link',   //link, music, video
              dataUrl: '',
              success: function () {
              },
              cancel: function () {
              }
            });
          }
    </script>
  
</body></html>