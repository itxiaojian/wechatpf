<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/bx/style1.3.8.css">
<meta charset="utf-8">
<title>工单详情</title>
<style type="text/css">
*,select,option,body,ul,li,a{font-family:'微软雅黑';margin:0px; padding:0px; list-style:none; text-decoration:none;}
.phone_01{ width:100%; height:100%; overflow:hidden;}
.logo{ width:100%; height:120px; background-color:#e5e5e5; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01{ width:100%; height:120px; background-color:#c5e0fa; position:absolute; top:0; left:0;overflow:hidden; font-size:36px; line-height:120px;}
.top_01 select{ font-size:36px; width:300px; height:50px; border-radius:5px; color:#2e87d3;}
.top_01 span{ float:left;}
.top_01 img{ margin-top:15%; display:block; float:left;}
.main_01{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#f2f2f2; overflow:auto;}
.main_02{width:100%; position:absolute; top:120px; left:0; bottom:98px; background-color:#fff; overflow:auto;}
.main_01_msg{width:80%; margin-left:10%; margin-right:10%; background-color:#fff; margin-top:5%; float:left;}
.CX_title_01{ width:100%; height:60px; background-color:#23bcfc; color:#fff; line-height:60px; text-align:center; font-size:36px;}
.GG_msg_01 li{ width:100%; height:100px; font-size:34px; line-height:100px; }
.GG_msg_01 img{ float:left; margin-top:3%; margin-left:5%;}
.CJ_msg_01{ padding-top:10px;}
.CJ_msg_01 li{ height:100px; font-size:34px; line-height:100px; float:left; }
.CJ_msg_02 li{font-size:34px; float:left; width:100%; text-align:center; line-height:100px;}
.CJ_msg_02 span{ float:left; color:#2e87d3;}
.CJ_msg_01 img{ float:left; margin-top:12%; margin-left:16%;}
.foot_01{width:100%; height:98px; background-color:#24CDD5;position:absolute; bottom:0; left:0; overflow:hidden}
/*微主页*/
.banner{ width:100%; overflow:hidden;}
.YM_iocn{width:100%; overflow:hidden;}
.YM_iocn li{ width:25%; text-align:center; float:left; font-size:36px; color:#666; line-height:100px; margin-top:2%;}
.YM_iocn img{ display:block; margin-left:30%;}
.chahua{ position:absolute; bottom:5%; right:10%;}
.FH_btn{ position:absolute; top:1%; right:5%;}
/*图书查询*/
.M_box{ width:90%; margin:auto;}
.M_msg{ width:100%; height:100%; border:1px solid #f2f2f2; border-radius:20px; margin-top:6%; float:left;padding-bottom:50px; margin-bottom:50px;}
.M_title{ width:100%; height:120px; background-color:#8bc0f2; color:#fff; line-height:120px; font-size:45px; float:left;border-radius:20px 20px 0 0 ;}
.M_title span{font-size:36px}
.M_title img{ float:left;}
.M_main{ clear:both; font-size:36px;}
.M_main li{ float:left; padding-top:5%; width:100%}
.M_main li span{ float:left;}
.M_main ul{ float:left;border-bottom:1px solid #8bc0f2; padding-bottom:50px;}
.msg_main01{ text-indent:70px;width:30%}
.msg_main03{ padding-left:10px;width:65%}
.msg_main02{ text-indent:100px;width:70%}
/*维修订单*/
.paizhao_box span{ width:25%; float:left; text-align:center;}
.add_haocai{ margin-top:40px; float:left;}
.haocai_xuanxiang{ width:450px; height:100px; font-size:36px; padding-left:20px;}
.haocai_input,.zengshan{ float:left}
.shuliang{ width:100px; height:90px;font-size:36px; text-align:center;}
.zengshan{ margin-left:40px;}
.add_haocai_btn{ width:100%; height:90px; background-color:#8bc0f2; float:left; border:none; margin-top:40px; border-radius:10px; font-size:36px; color:#fff;}
.tijiao_haocai_btn{ width:48%; height:90px; float:left; margin-top:40px; margin-bottom:40px; border-radius:10px; font-size:36px; color:#fff;border:none; text-align:left;}
.quxiao_haocai_btn{ width:48%; height:90px; float:right;background-color:#f66e32; margin-top:40px; margin-bottom:40px;border-radius:10px; font-size:36px; color:#fff;border:none;text-align:left;}
.tijiao_btn_box img{ float:left; margin-left:36%; margin-top:4px;}
.add_haocai_btn_box{ padding-bottom:40px;}
body {-webkit-overflow-scrolling: touch;overflow-scrolling: touch;}
</style>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload.js"></script>
<script src="<%=path%>/resources/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
//加函数
    function jia(i){
    	var num = $('.num'+i).val();
    	$('.num'+i).val(parseInt(num)+1);
    }

//减函数
    function jian(i){
    	var num = $('.num'+i).val();
    	$('.num'+i).val(parseInt(num)-1);
    	if($('.num'+i).val()=='0'){
    		$('.num'+i).val("1");    		
    	}
    }

//删除
    function sc(i){
	if(i !='0'){
    	$('.haocai'+i).remove();
    	$('#numInput').val($('#numInput').val()+";-"+i);
    	}
	}
	
//添加耗材
    function tjhc(i){
	    var h=i+1;
    	var html1="";
		var html="";
		$.ajax({
			cache : true,
			type : "POST",
			url : "<%=path%>/bx/bxsl/getHc",
			async : false,
			error : function(request) {
				alert("连接数据库失败，请联系管理员。");
			},
			success : function(data) {
				 $.each(eval(data), function(j, item) {
					 html+="<option style='font-size:15px;' value='"+item.hcbh+"'>"+item.hcmc+"</option>"; 
	                });
				 html1+="<div class='add_haocai haocai"+h+"'>"
			        +"<div class='haocai_input'>"
			        +"<select class='haocai_xuanxiang' name='select"+h+"' id='select"+h+"'>"
			        +"<option style='font-size:15px;' value=''>请选择---</option>"
			        +html
			        +"</select>"
			        +"</div>"
			        +"<div class='zengshan'>"
			        +"<span><img  onclick='jian("+h+");return false;' src='<%=path%>/resources/img/wzy/jian.png' width='50' height='50'></span>&nbsp;&nbsp;"
			        +"<span><input type='text' value='1' name='num"+h+"' class='shuliang num"+h+"'></span>&nbsp;&nbsp;"
			        +"<span><img  onclick='jia("+h+");return false;' src='<%=path%>/resources/img/wzy/add.png' width='50' height='50'></span>"
			        +"</div>"
			        +"<div onclick='sc("+h+");return false;' style='float:right; font-size:36px; line-height:90px; margin-left:40px;'>删除</div>"
			        +"</div>";
			        
			        $('.add_haocai_btn_box').before(html1);
			        $('.add_haocai_btn').removeAttr("onclick");
			        $('.add_haocai_btn').attr("onclick","tjhc("+h+");return false;");
			        
			        $('#numInput').val($('#numInput').val()+";"+h);
			  }
		});
	
    }
    
    
    var i=0;
    var j=1;
    var src;
    function toAddImg(j){
    	if(i>=0){
    		if ($("#file"+j).val().length > 0) {
    			$("#file"+j).val("");
    		}
    	}
    	$("#file"+j).click();
    }
    function selectFile(j){
    	if ($("#file"+j).val().length > 0) {
            ajaxFileUpload(j);
            i++;
        } else {
            alert("请选择图片");
        }
    }
    
    function ajaxFileUpload(j) {
        $.ajaxFileUpload({
            url : "<%=path%>/util/file/fileUload?wjlx=1&lxid=2", //用于文件上传的服务器端请求地址
            secureuri : false, //一般设置为false
            fileElementId : 'file'+j, //文件上传空间的id属性  <input type="file" id="file" name="file" />
            type : 'post',
            dataType : 'HTML', //返回值类型 一般设置为json
            success : function(data, status) //服务器成功响应处理函数 
            {
            	$.ajax({
    				url:'<%=path%>/util/file/getImgId',
    				type : 'post',
    				//dataType : 'json',
    				data:{
    					fname:data
    				},
    				success: function(data){
    					if(data=='1'){
    						alert("上传图片太大,请重新上传!");
    						return false;
    					}else{
    				 	src="<%=path%>/util/file/getFile?imgId="+data;
    	                $("#img"+j).attr("src", src);
    	                $("#fileName"+j).val(src);
    	                $("#file"+j).val("");
    	                if (typeof (data.error) != 'undefined') {
    	                    if (data.error != '') {
    	                        alert(data.error);
    	                    } else {
    	                        alert(data.msg);
    	                    }
    	                };
    					}
    				},
    				error: function(XMLHttpRequest){
    						alert("网络错误");
    				}
    			});
            },
            error : function(data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        })
        return false;
    }
    
   <%--  //提交Btn
    function tjBtn(){
    	var bz1=$('#fileName1').val();
    	var bz2=$('#fileName2').val();
    	var bz3=$('#fileName3').val();
    	
		window.location.href="<%=path%>/bx/bxsl/wgxQr?bz1="+bz1+"&bz2="+bz2+"&bz3="+bz3;
    } --%>
    
   function checkform(frm){
	  if(frm.select0.value=='' || frm.select0.value==null){
		  alert("请选择器材!");
		  return false;
	  }else{
		  alert("提交成功!");
		 //WeixinJSBridge.call("closeWindow");
		 //location.href="<%=path%>/bx/bxrw/getrw?openId=${openId}"
		 //return false;
	  }
   }
   
   
   function tjBtn(){
 		if($('#select0').val()==null||$('#select0').val()=='' || $('#select0').val()=='-1'){
			  alert("请选择器材!");
			  return false;
	   }else{
		   $('#new_entry').submit();
		   alert("提交成功");
		   window.location.href="<%=path%>/bx/bxrw/getrw?openId=${openId}";
	   }
   }
   
   function fh(){
	   window.location.href="<%=path%>/bx/bxrw/getrw?openId=${openId}";
   }
   
   $(document).ready(function(){
	   if($('#ztInput').val()=='2'){
		   //$('.tijiao_haocai_btn').removeAttr("style");
		   $('.tijiao_haocai_btn').attr("style","background:grey")
		   $('.tijiao_haocai_btn').html("<img src='<%=path %>/resources/img/wzy/tijiao.png' width='40'>已提交");
		   $('.tijiao_haocai_btn').attr("disabled","true");
		   $('#img1').removeAttr("onclick");
		   $('#img2').removeAttr("onclick");
		   $('#img3').removeAttr("onclick");
		   $('.add_haocai_btn').attr("disabled","true");
	   }
   }); 
   
</script>
</head>
<body>
<input id="ztInput" value="${zt}"  type="hidden"/>
<div class="phone_01">
      <div class="top_01">
          <span style="width:60%; padding-left:5%; color:#fff">
             维修订单&nbsp;&nbsp;
          </span>
          <span style="width:20%;">
              &nbsp;
          </span>
          <span style="width:10%">
              <img onclick="fh()"src="<%=path %>/resources/img/wzy/fanhui.png" style="margin-top:30%">
          </span>
      </div>
    <form class="center" id="new_entry" action="<%=path%>/bx/bxsl/wxgQr" onsubmit="checkform(this)"
    	accept-charset="UTF-8" method="get">
    <div class="main_02">
    <c:forEach var="data" items="${list}" varStatus="status">
    	<div class="M_box">
        	<div class="M_msg">
            	<div class="M_title">
                	<img  style=" margin-top:1%; margin-left:2%" src="<%=path %>/resources/img/wzy/mp.png" width="100" height="100">
                  		  订单号
                	<span>${data.bxbh}</span>
                    <img style=" float:right; margin-top:1%; margin-right:2%" src="<%=path %>/resources/img/wzy/dian.png" width="90" height="90">
                </div>
                <div class="M_main">
                	<ul>
                    	<li>
                        	<span class="msg_main01">申报人</span>
                            <span class="msg_main03" style="color:#358cde">${data.sqrxm}</span>
                        </li>
                        <li>
                        	<span class="msg_main01">申报主题</span>
                            <span class="msg_main03" style="color:#fc4312">${data.sbztmc}</span>
                        </li>
                        <li>
                        	<span class="msg_main01">申报内容</span>
                            <span class="msg_main03">${data.nr}</span>
                        </li>
                        <li>
                        	<span class="msg_main01">预约时间</span>
                            <span class="msg_main03" style="color:#358cde">${data.yysj}</span>
                        </li>
                    </ul>
                    <ul>
                    	<li>
                        	<span class="msg_main01">预约楼宇</span>
                            <span class="msg_main03" style="color:#999">${data.ly}</span>
                        </li>
                        <li>
                        	<span class="msg_main01">服务类型</span>
                            <span class="msg_main03" style="color:#fc4312">${data.fw}</span>
                        </li>
                        <li>
                        	<span class="msg_main01">报修地址</span>
                            <span class="msg_main03" style="color:#999">${data.dz}</span>
                        </li>
                        <li>
                        	<span class="msg_main01">备注</span>
                            <span class="msg_main03">
                            	<c:if test="${fn:length(fn:split(data.bz,';'))!=1 }">
									<c:forEach var="item" varStatus="status" begin="0" end="${fn:length(fn:split(data.bz,';'))-1}">
 										<img style="width:150px;height:150px;"   src="${fn:split(data.bz,';')[status.index]}"/>
									</c:forEach>
							   </c:if>
							   <c:if test="${fn:length(fn:split(data.bz,';'))==1 && data.bz==''}">
						 				${data.bz}
							   </c:if>
							   <c:if test="${fn:length(fn:split(data.bz,';'))==1 && data.bz!=''}">
						 				<img style="width:150px;height:150px;"   src="${data.bz}"/>
						       </c:if>
                            </span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="paizhao_box">
                <span><img  src="<%=path %>/resources/img/wzy/zhaoxiang.png"></span>
                <span><img id="img1" onClick="toAddImg(1);" src="<%=path %>/resources/img/wzy/tianjia.png"></span>
                <div id="uploadDiv"  class="controls" style="position: absolute; top: 25px; left: 35px; width: 163px; height: 161px; overflow: hidden; z-index: 0;">
	            <input id="file1"  name="file" type="file" style="font-size: 999px;opacity:0;position:absolute;top:0px;
	            	left:0px;width:100%;height:100%;"accept="image/*" capture="camera" onChange="selectFile(1);">
	            </div>
	            <input type='hidden' id='fileName1' name='bz1' value=''>
	            <span><img id="img2" onClick="toAddImg(2);" src="<%=path %>/resources/img/wzy/tianjia.png"></span>
	            <div id="uploadDiv"  class="controls" style="position: absolute; top: 25px; left: 35px; width: 163px; height: 161px; overflow: hidden; z-index: 0;">
	            <input id="file2"  name="file" type="file" style="font-size: 999px;opacity:0;position:absolute;top:0px;
	            	left:0px;width:100%;height:100%;"accept="image/*" capture="camera" onChange="selectFile(2);">
	            </div>
	            <input type='hidden' id='fileName2' name='bz2' value=''>
	            <span><img id="img3" onClick="toAddImg(3);" src="<%=path %>/resources/img/wzy/tianjia.png"></span>
	            <div id="uploadDiv"  class="controls" style="position: absolute; top: 25px; left: 35px; width: 163px; height: 161px; overflow: hidden; z-index: 0;">
	            <input id="file3"  name="file" type="file" style="font-size: 999px;opacity:0;position:absolute;top:0px;
	            	left:0px;width:100%;height:100%;"accept="image/*" capture="camera" onChange="selectFile(3);">
	            </div>
	            <input type='hidden' id='fileName3' name='bz3' value=''>
            </div>
            <div class="add_haocai haocai0">
                <div class="haocai_input">
                	<select class="haocai_xuanxiang" name="select0" id='select0'>
                	<option style="font-size:15px;" value="">请选择---</option>
                	<c:forEach var="map" items="${hclist}" varStatus="obj">
                    	<option style="font-size:15px;" value="${map.hcbh}">${map.hcmc}</option> 
                    </c:forEach>
                    </select>
                </div>
                <div class="zengshan">
                	<span><img  onclick="jian(0)" src="<%=path %>/resources/img/wzy/jian.png" width="50" height="50"></span>&nbsp;&nbsp;
                    <span><input type="text" value="1" name="num0" class="shuliang num0"></span>&nbsp;&nbsp;
                    <span><img  onclick="jia(0)" src="<%=path %>/resources/img/wzy/add.png" width="50" height="50"></span>
                </div>
                <div  onclick="sc(0)" style="float:right; font-size:36px; line-height:90px; margin-left:40px;">删除</div>
            </div>
            <div class="add_haocai_btn_box">
            	<button class="add_haocai_btn" onclick="tjhc(0);return false;">添加耗材</button>
            </div>
            <div class="tijiao_btn_box">
            	<button class="tijiao_haocai_btn tjBtn" style="background-color:#63d120" type="submit"><img src="<%=path %>/resources/img/wzy/tijiao.png" width="40">&nbsp;提交</button>
                <button class="quxiao_haocai_btn" onclick="fh();return false;"><img src="<%=path %>/resources/img/wzy/quxiao.png" width="40">&nbsp;取消</button>
            </div>
        </div>	
        </c:forEach>
    </div>
    <input id="numInput" name="xh" value="0" type="hidden" />
    <input id="idInput" name="id" value="${id}" type="hidden" />
     <input id="openIdInput" name="openId" value="${openId}" type="hidden" />
    <input id="">
 </form>
    <div class="foot_01">
    	<img src="<%=path %>/resources/img/wzy/icon_39.png" width="100%" height="100%">
    </div>
</div>
</body>
</html>