<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% 
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">  
<HTML>  
<HEAD>  
<META NAME="Author" CONTENT="oscar999">  
<link href="http://csdnimg.cn/www/images/favicon.ico" rel="shortcut icon">
</HEAD>  
  
<BODY>  
<script>  
window.location="<%=path%>/sys/SysMenu/main";  
</script>  
</BODY>  
</HTML>