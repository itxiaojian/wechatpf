<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html class="fixed js flexbox flexboxlegacy csstransforms csstransforms3d no-overflowscrolling no-mobile-device custom-scroll"><head>
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
    
    <!-- Web Fonts  -->
    <!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css"> -->
    
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
    
    <!-- Theme CSS -->
    <link rel="stylesheet" href="<%=path%>/resources/js/text/theme.css">

    <!-- Skin CSS -->
    <link rel="stylesheet" href="<%=path%>/resources/js/text/default.css">

    <!-- Theme Custom CSS -->
    <link rel="stylesheet" href="<%=path%>/resources/js/text/theme-custom.css">
    <script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
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
  </head>
  <body>
    <div class="row fix-mg">
      <div class="panel panel-primary mb-none">
        <header class="panel-heading center no-radius p-md fix-mg">
         <a href="<%=path%>/wsh/zy/zhuye?openId=${openId}" class="pull-left text-primary-inverse text-xl"><i class="fa">
           <img src="<%=path%>/resources/img/wzy/back.png" style="width:25px;height:25px"/>
          </i></a>
          <a href="#" class="pull-left text-primary-inverse text-xl"><!-- <i class="fa fa-home"> --></a>
          <a href="<%=path%>/wfw/ZsText/mylost?openId=${openId}" class="pull-right text-primary-inverse text-xl"><i class="fa">
          <img src="<%=path%>/resources/img/wzy/user.png" style="width:25px;height:25px"/>
          </i></a>
          <h2 class="panel-title">失物招领</h2>
        </header>
        <div class="tabs m-xs">
          <ul class="nav nav-tabs nav-justify tabs-primary mt-xs">
            <li class="active" onclick="changeType(1)">
              <a href="#lost_info" data-toggle="tab" class="text-center  text-bold">遗失信息</a>
            </li>
            <li onclick="changeType(2)">
              <a href="#found_info" data-toggle="tab" class="text-center text-bold">招领信息</a>
            </li>
          </ul>
				<div class="tab-content p-none">
					<div id="found_info" class="tab-pane">
						<div class="timeline timeline-simple mb-md">
							<div class="tm-body pt-xs pr-xs">
								<ol id="found_ol" class="tm-items">
								</ol>
							</div>
						</div>
					</div>
					<div id="lost_info" class="tab-pane active">
					<div class="timeline timeline-simple mb-md">
							<div class="tm-body pt-xs pr-xs">
					 	<ol id="lost_ol" class="tm-items">
					 	</ol>
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
    
    <script src="<%=path%>/resources/js/text/common.js"></script>
    <script src="<%=path%>/resources/js/text/config.js"></script>
    <script src="<%=path%>/resources/js/text/WeixinApi.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function() {
      $(window).load(function(){
        //$('#loadingWrapperBase').fadeOut('slow');
        --LOADINGSTATUS;
        if(LOADINGSTATUS == 0) {
          $('#loadingWrapperBase').fadeOut('slow');
        }
      });
    });
    </script>
    
    <!-- Theme Base, Components and Settings -->
    <script src="<%=path%>/resources/js/text/theme.js"></script>
    
    <!-- Theme Custom -->
    <script src="<%=path%>/resources/js/text/theme_003.js"></script>
    
    <!-- Theme Initialization Files -->
    <script src="<%=path%>/resources/js/text/theme_002.js"></script>
    
    
    <div id="loadingWrapper" style="display: none;">
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
    <script src="<%=path%>/resources/js/text/common_002.js"></script>
    <script type="text/javascript">
      var glotype =2;//2是招领信息；1是遗失信息
      var pn2 = 1;//记录招领信息的当前页码
      var pn1 = 1;//记录遗失信息的当前页码
      var pageAmount2 = 1;//招领信息总页数
      var pageAmount1 = 1;//遗失信息总页数
      var universityAppId = 0; //公众号ID
      $(function(){ 
        $("#found_ol").empty();
        $("#lost_ol").empty();
        universityAppId = '86';
        if(universityAppId==null||universityAppId==""){
          $(".fa-user").hide();
        } 
        getLost (pn2,2);
        getLost (pn1,1);
        changeType(1);//刚开始加载使得glotype为1,默认初始打开是遗失的
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
      function drawer(obj){
        var $obj = $(obj).children("i");
        if($obj.hasClass("down")){
          $obj.next().removeAttr("src").attr("src","<%=path%>/resources/img/wzy/up.png");
          $obj.removeClass("down").addClass("up");
          $obj.next().parent().next().attr("style","display: block");
        } else {
          $obj.next().removeAttr("img").attr("src","<%=path%>/resources/img/wzy/down.png");
     	  $obj.removeClass("up").addClass("down");
          $obj.next().parent().next().attr("style","display: none");
        }
      }
      function changeType(num) {
        glotype = num;
      }
      
      function getLost (pn,lostType){
          var postData = {};
          postData.universityAppId = universityAppId;
          postData._url = "<%=path%>"+"/wfw/ZsText/search";
          postData.DESC = [];
          postData.DESC.push("createTime");
          postData.pageNum = pn;
          postData.pageSize = 5;
          postData.lostType = lostType;
          postData["_noLoading"] = true;
          postData.status = 1;
          request(postData,false,function(data) {
           // if (checkDataStatus(data)) {
              //var dataResult = data.value;
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
              pageNoteError(data.message);
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
          $("#loadingWrapper").css("display","none");
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
          var $figure1 = $("<figure/>").addClass("image  text-primary text-lg");
           var $img1 = $("<img style='width:19px;height:19px;position:absolute;'/>").attr("src","<%=path%>/resources/img/wzy/detail.png");
          var $span1 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(data.xwzs);
          $li1.append($figure1).append($img1).append($span1);
          $ul.append($li1);
          var $li2 = $("<li/>").addClass("mb-xs");
          var $figure2 = $("<figure/>").addClass("image  text-primary text-xl");
          var $span2 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(ifNull(data.dd,"无"));
          var $img2 = $("<img style='width:20px;height:20px;position:absolute;'/>").attr("src","<%=path%>/resources/img/wzy/place.png");
          $li2.append($figure2).append($img2).append($span2);
          $ul.append($li2);
          var $li3 = $("<li/>").addClass("mb-xs");
          var $figure3 = $("<figure/>").addClass("image  text-primary text-lg");
          var $span3 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(Long2TimeString(data.fqsj,'yyyy-MM-dd'));
          var $img3 = $("<img style='width:22px;height:22px;position:absolute;'/>").attr("src","<%=path%>/resources/img/wzy/clock.png");
          $li3.append($figure3).append($img3).append($span3);
          $ul.append($li3);
          var $li4 = $("<li onclick='drawer(this);' style='cursor: pointer'/>").addClass("mb-none");
          var $i = $("<i/>").addClass("fa down mr-sm text-lg  text-primary pull-right");
          var $img4_1 = $("<img style='width:15px;height:15px;float:right;margin-right:10px !important;margin-top:7px;position:relative;'/>").attr("src","<%=path%>/resources/img/wzy/down.png");
          var $figure4 = $("<figure/>").addClass("image text-primary text-lg");
           var $img4 = $("<img style='width:23px;height:23px;position:absolute;'/>").attr("src","<%=path%>/resources/img/wzy/photo.png");
          var $span4 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(ifNull(data.fbrxm,"无"));
          $li4.append($i).append($img4_1).append($figure4).append($img4).append($span4);
          $ul.append($li4);
          var $divnone = $("<div style='display:none'/>");
            var $li5 = $("<li/>").addClass("mb-xs mt-xs");
            var $figure5 = $("<figure/>").addClass("image text-primary text-lg");
             var $img5 = $("<img style='width:20px;height:20px;position:absolute;'/>").attr("src","<%=path%>/resources/img/wzy/type.png");
            var $span5 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(ifNull(data.lxmc,"无"));
            $li5.append($figure5).append($img5).append($span5);
            $divnone.append($li5);
            var $li6 = $("<li/>").addClass("mb-none");
            var $figure6 = $("<figure/>").addClass("image text-primary text-md");
            var $img6 = $("<img style='width:24px;height:24px;position:absolute;'/>").attr("src","<%=path%>/resources/img/wzy/zt.png");
            var $span6 = $("<span style='text-indent:3em'/>").addClass("text-dark tab-r").text(ifNull(data.ztmc,"无"));
            $li6.append($figure6).append($img6).append($span6);
            $divnone.append($li6);
          $ul.append($divnone);
          if(type == 2 ){
             $("#found_ol").append($outli); 
          }else{
             $("#lost_ol").append($outli); 
          }
        }  
        function getShare(){
            wxShare({
              appId: 'wx4d9f6c21dc326084',
              timestamp: '1468216466',
              nonceStr: '37d10f8c-1006-4701-8737-ef74a7c0d39a',
              signature: '8070dfb29d5108b03a2514c6b94676e1cd854c25',
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