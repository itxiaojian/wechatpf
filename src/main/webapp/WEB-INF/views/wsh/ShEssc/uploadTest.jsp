<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html bdgjjgagedbdaebhbjbcabcdgeeebecf_db="1"
	idceifdedfeiefjgfcjfbchejgbcbeid_db="1"
	eiiebffcjbffiheggaebebgceaeccbia_db="1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="edge">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,maximum-scale=1,minimum-scale=1,initial-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="<%=path%>/system/login/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    $(function() {
        $(":button").click(function() {
            if ($("#file1").val().length > 0) {
                ajaxFileUpload();
            } else {
                alert("请选择图片");
            }
        });
    });
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

<title>图片上传</title>
</head>
<body class="detail">
	 <p>
        <input type="file" id="file1" name="file" />
    </p>
    <input type="button" value="上传" />
    <p>
        <img id="img1" alt="XX" src="" />
    </p>
	
</body>

</html>