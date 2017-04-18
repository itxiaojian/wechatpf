<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,initial-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>发布商品</title>
        <link rel="stylesheet" href="<%=path%>/resources/js/wsh/essc/addCommon.css">
        <link rel="stylesheet" href="<%=path%>/resources/js/wsh/essc/addMain.css">
    <style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload.js"></script>
    <script type="text/javascript">
    	var j=0;
    	function toSave(){
    		j++;
    		if(i>1){
    			alert("您的商品已经发布，请不要重复发布！");
    			location.href ="<%=path%>/wsh/ShEssc/toEsscList";
    			return false;
    		}
    		var spzp=$('#fileName').val();
    		var spmc=$('#goods_name').val();
    		var spms=$('#goods_detail').val();
    		var jg=$('#goods_price').val();
    		var splx=$('#first-id').val();
    		var lxfs=$('#qqnum').val();
    		if(spzp==null || spzp==''){
    			alert("请上传商品图片！");
    			return false;
    		}
    		if(spmc==null || spmc==''){
    			alert("请填写商品名称！");
    			return false;
    		}
    		if(spms==null || spms==''){
    			alert("请填写商品描述！");
    			return false;
    		}
    		if(jg==null || jg==''){
    			alert("请填写商品价格！");
    			return false;
    		}
    		if(splx==null || splx==''){
    			alert("请填写商品类型！");
    			return false;
    		}
    		if(lxfs==null || lxfs==''){
    			alert("请填写联系方式！");
    			return false;
    		}
    		$('#publish-form').submit();
    	}
    </script>
    <script type="text/javascript">
    var i=0;
    function toAddImg(){
    	if(i>=0){
    		if ($("#file1").val().length > 0) {
    			$("#file1").val("");
    		}
    	}
    	$("#file1").click();
    }
    function selectFile(){
    	if ($("#file1").val().length > 0) {
            ajaxFileUpload();
            i++;
        } else {
            alert("请选择图片");
        }
    }
    function ajaxFileUpload() {
        $.ajaxFileUpload({
            url : '<%=path%>/wsh/upload/tempimg', //用于文件上传的服务器端请求地址
            secureuri : false, //一般设置为false
            fileElementId : 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
            type : 'post',
            dataType : 'HTML', //返回值类型 一般设置为json
            success : function(data, status) //服务器成功响应处理函数
            {
                $("#img1").attr("src", data);
                $("#fileName").val(data);
                $("#file1").val("");
                if (typeof (data.error) != 'undefined') {
                    if (data.error != '') {
                        alert(data.error);
                    } else {
                        alert(data.msg);
                    }
                }
            },
            error : function(data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        })
        return false;
    }
</script>
</head>
<body>
    <div class="wrap-loading hide">
    <div class="loading-box hide">
        <div class="loader">
            <div class="ball"></div>
            <div class="ball"></div>
            <div class="ball"></div>
        </div>
    </div>
</div>
<div class="page bg-white">
    <div class="login-header header bg-green">
        <a href="javascript:;" class="head-right fr" id="publish-finish" onclick="toSave();">完成</a>
        <span class="publish-mid">发布</span>
        <a href="<%=path%>/wsh/ShEssc/toEsscList?openId=${openId}" class="head-left fl" id="go-prev"></a>
    </div>
    <div class="upload-box bg-green">
        <ul id="upload-img-list" class="upload-img-list group border-box" style="position: relative;">
            <li>
                <div id="add-product-image" class="btn-add-img" style="z-index: 1;" onclick="toAddImg();"><img id="img1" alt="+" src="" align="center" style="height: 100%; text-align: center;"/></div>
            </li>
	        <div id="uploadDiv" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 25px; left: 35px; width: 163px; height: 161px; overflow: hidden; z-index: 0;">
	        	<input id="file1" name="file" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" onchange="selectFile();">
	        </div>
	      </ul>
        
    </div>
    <div class="content border-box">
        <div class="wrap-tb">
            <form id="publish-form" action="<%=path%>/wsh/ShEssc/save" method="post">
            <input type="hidden" id="openId" name="openId" value="${openId }">
            <input type="hidden" id="fileName" name="fileName" value="">
                <table class="tb-publish">
                    <colgroup>
                        <col width="80">
                    </colgroup>
                    <tbody><tr>
                        <td><span class="wrap-td-txt">商品名称</span></td>
                        <td><input type="text" require="" id="goods_name" name="goods_name" placeholder="最多25个字"></td>
                    </tr>
                    <tr>
                        <td><span class="wrap-td-txt product-info-left">商品描述</span></td>
                        <td><textarea require="" class="product-info" id="goods_detail" name="goods_detail" placeholder="建议填写商品用途、新旧程度、原价等信息，不少于15字"></textarea><div class="temp-product-info"></div></td>
                    </tr>
                    <tr>
                        <td><span class="wrap-td-txt">商品价格</span></td>
                        <td><input type="number" require="" id="goods_price" name="goods_price" placeholder="￥"></td>
                    </tr>
<!--                     <tr> -->
<!--                         <td><span class="wrap-td-txt">交易地点</span></td> -->
<!--                         <td><input type="text" require="" id="goods_trade_place" name="user-name" value=""></td> -->
<!--                     </tr> -->
                    <tr>
                        <td><span class="wrap-td-txt">选择分类</span></td>
                        <td class="pr">
                        	<input type="text" require="" class="category-ipt" name="category_ipt">
                        	<div class="category-td"></div>
                        	<input type="hidden" id="first-id" name="first_id">
                        	<input type="hidden" id="second-id" name="second_id">
                        </td>
                    </tr>
                    <tr>
                        <td><span class="wrap-td-txt">手机号</span></td>
                        <td><input type="tel" name="qqnum" id="qqnum" require="" data-type="number" value=""></td>
                    </tr>
<!--                     <tr> -->
<!--                         <td><span class="wrap-td-txt">手机号</span></td> -->
<!--                         <td><input type="tel" id="tel" name="user-email" placeholder="选填" value=""></td> -->
<!--                     </tr> -->
                </tbody></table>
            </form>
        </div>
    </div>
</div>
<div class="category-box hide">
    <div class="wrap-category">
        <div class="category-head"><span class="head-txt">选择分类</span><a href="javascript:;" class="cate-close">X</a></div>
        <ul class="first-list cate-list">
                        <li pk="item1" value="1"><span>校园代步</span></li>
                        <li pk="item2" value="2"><span>手机</span></li>
                        <li pk="item3" value="3"><span>电脑</span></li>
                        <li pk="item4" value="4"><span>数码配件</span></li>
                        <li pk="item5" value="5"><span>数码</span></li>
                        <li pk="item6" value="6"><span>电器</span></li>
                        <li pk="item7" value="7"><span>运动健身</span></li>
                        <li pk="item8" value="8"><span>衣物伞帽</span></li>
                        <li pk="item9" value="9"><span>图书教材</span></li>
                        <li pk="item10" value="10"><span>租赁</span></li>
                        <li pk="item11" value="11"><span>生活娱乐</span></li>
                        <li pk="item12" value="12"><span>其他</span></li>
                    </ul>
    </div>
</div>
        <script src="<%=path%>/resources/js/wsh/essc/jquery.validateform.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/exif.min.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/plupload.full.min.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/zh_CN.js"></script>
<%--         <script src="<%=path%>/resources/js/wsh/essc/qiniu.min.js"></script> --%>
        <script src="<%=path%>/resources/js/wsh/essc/publish.js"></script>
        <script src="<%=path%>/resources/js/wsh/essc/main.js"></script>
        <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?141dfb73d31a5c8909afe43264b7c38f";
            var s = document.getElementsByTagName("script")[0]; 
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</body></html>