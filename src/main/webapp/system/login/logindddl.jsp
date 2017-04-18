<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.*"%>
<%@page  import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page  import="org.jasig.cas.client.validation.Assertion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<% 
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="no-cache" http-equiv="Pragma" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="0" http-equiv="Expires" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta charset="utf-8" />
<title>${setting['app']}</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/main.css" />

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.4.2.min.js"></script>
<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}

/*提示信息*/
.login_info{
	color:red;
}
</style>
</head>
<body onload="login()" style="opacity:0">
	<%Assertion assertion=
	    (Assertion)
	    session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
	/*获取用户扩展信息
	 *扩展信息由UIA的SSO配置决定
	 *其中，由于用户可能拥有多个角色，岗位，部门
	 *所以角色，岗位，部门需要被转换为List<Map<String,String>类型
	*/
	Map<String, Object> map = assertion.getPrincipal().getAttributes();
	String loginname = "";
	String teacheing = decode((String)map.get("comsys_teaching_number"));
	String student = decode((String)map.get("comsys_student_number"));
	if(teacheing!=null && !"".equalsIgnoreCase(teacheing)){
		loginname = teacheing+"dddlcs";
	}
	if(student!=null && !"".equalsIgnoreCase(student)){
		loginname = student+"dddlcs";
	}
	%>
<div class="container PNGFIX">
	  <div class="login_container PNGFIX">
	    <div class="login_panel PNGFIX">
	       <div class="login_logo"><img src="images/logo.png" class="PNGFIX" style="margin-top: -50px"></div>
	       <div class="login_box">
	       	<form id="loginForm" action="<%=path%>/j_spring_security_check" class="login_form" method="post">
		         <div class="login_input fl">
		           <div class="t01"><b class="t02"></b><input type="hidden" id="username" name="j_username" class="input_txt PNGFIX" value='<%= loginname%>'></div>
		           <div class="t01"><b class="t03"></b><input type="hidden" id="password" name="j_password" class="input_txt PNGFIX" value='123456'></div>
		           <div class="clear"></div>
		         </div>
		         <div class="login_btn fl">
		           <button type="submit" onclick="login()" value="" class="PNGFIX"></button>
		         </div>
		         <div class="clr"></div>
	         </form>
	         <div class="login_info" > ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}  </div>
	       </div>
	    </div>
	     <div class="footer">版权所有：${setting['copyright'] }</div>
	  </div>
	</div>
<script>
if(window.parent != window) {
	window.parent.location.href = "<%=request.getContextPath()%>/system/login/login.jsp";		
}
	$(document).ready(function(){
		//居中
		 document.getElementById("username").focus();
		 $("#username").keydown(function(event){
		 	if(event.keyCode==13){
				login()
			}
		 })
		 $("#password").keydown(function(event){
		 	if(event.keyCode==13){
				login()
			}
		 })
		 
	})


    function validateEmpty(str){
    	if (str == "") {
    	return true;
    	}
    	return false;
    }
function login(){
	//var comCode = document.getElementById("comCode").value;
	var loginName = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	if(validateEmpty(loginName)){
		   return false;
		}
	//if(validateEmpty(comCode)){
	//	   return false;
	//	}
	if(loginName.length<1 || loginName.length> 20){
		   return false;
		}
 	if(validateEmpty(password)){
	     return false;
	} 
  	document.getElementById("loginForm").submit();
  }
  
  
</script>
	<%! 
	private List<Map<String,String>> parseStringToList(String str) throws Exception {
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        if(str == null || str.equals("")){
           return list;
        }
	    str = decode(str);
        String[] array = str.split("-");
        for (String subArray : array) {
            String[] keyResult = subArray.split(",");
         	Map<String,String> map = new HashMap<String, String>();
            for (String subResult : keyResult) {
                String[] value = subResult.split(":");
                map.put(value[0], value[1]);
            }
            list.add(map);
        }
        return list;
    } 
	private String decode(String str) throws Exception{
	    if(str != null){
		    str = URLDecoder.decode(str,"UTF-8");
		}
	    return str;
	}
    %>
</body>
</html>