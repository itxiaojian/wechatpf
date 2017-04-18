<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1">
	<script type="text/javascript">var PATHNAME="<%=path%>"</script>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<title>投票结果</title>
<script src="<%=path%>/resources/js/wzy/jquery.js"></script>
<script src="<%=path%>/resources/js/wzy/json2.js"></script>
<link type="text/css" href="<%=path%>/resources/css/tucao.css" rel="stylesheet" />
<link type="text/css" href="<%=path%>/resources/css/page.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/xyxw.css" />
<link rel="stylesheet" href="<%=path%>/resources/js/wsh/styles.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/bootstrap.min.css"/>

<style type="text/css">
  .anniu{position: absolute;top: 0.4rem;width: 2.5rem;height: 2.5rem;}
  .anniu img{width: 3.2rem;height: 3.2rem;display: block;}
  .New_main01{ width:100%;position:absolute; top:50px; left:0px; bottom:20px;overflow:auto;}
  .WZY_foot{width:100%; height:38px; position:fixed; left:0px;bottom:0;overflow:hidden;}
</style>
</head>
<body>
  <div id="header" class="header">
		<img src="<%=path%>/resources/img/wzy/logo.png" style="width: 100%;" />
		<div class="anniu">
			<a href="<%=path%>/wsh/ShTpzl/shtpzlPage?openId=${openId}">
			   <img 
			    src="<%=path%>/resources/img/wzy/FH.png"/>
			 </a>
		</div>
  </div>
  <input type="hidden" name="sum" id="sum" value="${sum }"/>
  <input type="hidden" name="xxnr" id="xxnr" value="${xxnr }"/>
  <input type="hidden" name="openId" id="openId" value="${openId }"/>
  <div class="New_main01">
    	<div class="container">
            <div class="row">
              <div class="col-sm-12" style=" border-bottom:1px solid #e5e5e5;">
                <img src="<%=path%>/resources/img/wzy/wsh-06.png" width="40" height="40">
                <span>投票结果</span>
              </div>
            </div>
            <c:forEach items="${tplist }" var="tplist">
            <div class="row">
              <div class="col-sm-12">
                <h4 style="text-align:center; color:#2991e6;">
                	${tplist.tpnr }<br><br>
                    <small>单选（匿名投票）&nbsp;&nbsp;截止时间：${tplist.jssj }</small>
                </h4>
              </div>
            </div>
            </c:forEach>
            <table class="table table-striped">
              <c:forEach items="${xxlist }" var="xxlist">
                <tr>
                    <td>
                    <c:choose>
                    <c:when test="${xxnr==xxlist.xxnr }">
                   		<div class="col-sm-12">
                			<span class="glyphicon glyphicon-ok" style="color:#1CBF0F"></span>
                        	${xxlist.xxnr }
                             <span class="pull-right"><small>${xxlist.num }票</small></span>
              			</div>
              		</c:when>
              		<c:otherwise>
              		    <div class="col-sm-12">
                			<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        	${xxlist.xxnr }
                             <span class="pull-right"><small>${xxlist.num }票</small></span>
              			</div>
              		</c:otherwise>
              		</c:choose>
                    </td>
                </tr>
              </c:forEach>
               <tr>
                    <td>
                   		 <div class="col-sm-12">
                             <span class="pull-right" style=" color:#2991e6;"><small>已投${sum }票</small></span>
              			</div>
                    </td>
                </tr>
            </table>
            <div class="row">
              <div class="col-sm-12" style="text-align:center">
               <a href="<%=path%>/wsh/ShTpzl/shtpzlPage?openId=${openId}">
                <button class="btn btn-success">
                                                           确认返回
                </button>
                </a>
              </div>
            </div>
        </div>
    </div>
  
  <div class="WZY_foot">
	  <img src="<%=path%>/resources/img/wzy/BQ.png" style="width:100%;">
  </div>

</body>
</html>