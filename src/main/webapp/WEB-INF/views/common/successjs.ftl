<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta content="no-cache" http-equiv="Pragma" />
<meta content="no-cache" http-equiv="Cache-Control" />
<meta content="0" http-equiv="Expires" />
</head>
 <script type="text/javascript">
<#if callbackParams?exists>
	top.put("params",${callbackParams!});
</#if>
<#if params["callback"]?exists>
	${params["callback"]}();
</#if>

var sec = 1;
timerID = setInterval("count()",1000);

function count() {
    time.innerHTML = --sec;
    if(sec == 0) {
    	top.closeView();
    }
}
</script>
<style>
* {padding:0px;margin:0px;}
</style>
<body style="padding:100px;text-align:center;overflow-x:hidden;background-color:#F5F9FA;" oncontextmenu="return false">
	<font color="red"><strong>操作成功</strong></font><br/><br/><span id="time">1</span>秒后自动<a href="javascript:" onclick="top.closeView()">关闭</a>
</body>
</html>