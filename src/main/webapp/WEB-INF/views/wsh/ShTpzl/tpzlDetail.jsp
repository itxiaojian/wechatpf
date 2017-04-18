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
<title>我要投票</title>
<script src="<%=path%>/resources/js/wzy/jquery.js"></script>
<script src="<%=path%>/resources/js/wzy/json2.js"></script>
<link type="text/css" href="<%=path%>/resources/css/tucao.css" rel="stylesheet" />
<link type="text/css" href="<%=path%>/resources/css/page.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/xyxw.css" />
<link rel="stylesheet" href="<%=path%>/resources/js/wsh/styles.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/bootstrap.min.css"/>	

<script type="text/javascript">
	var id="${id }";
	var openId="${openId }";
	
	function btnOK_onclick(myForm){
		   //myForm.action="./voteSuccess.jsp?id="+id;
		   if(validCheck(myForm)){
			   //myForm.submit();
			   var chkRadio = $('input:radio[name="optionsRadios"]:checked').val();
			   if(confirm("您确定要提交吗？")){
				   $.ajax({
						cache : true,
						type : "POST",
						url : "<%=path%>/wsh/ShTpzl/saveTp",
						data :{chkRadio:chkRadio,openId:openId},// 你的formid
						async : false,
						error : function(request) {
							alert("提交失败，请联系管理员。");
						},
						success : function(data) {
							if(data=='1'){
								alert("您的答案已提交，不能重复提交!");
								window.self.location="<%=path%>/wsh/ShTpzl/tpjgDetail?id=${id}&openId=${openId}";
							}else if(data=='2'){
								alert("该问卷已终止评议，不能提交!");
								window.self.location="<%=path%>/wsh/ShTpzl/tpjgDetail?id=${id}&openId=${openId}";
							}else if(data==''){
								alert("提交成功。");
								window.self.location="<%=path%>/wsh/ShTpzl/tpjgDetail?id=${id}&openId=${openId}";
							}else{
								alert("提交失败，请联系管理员。");
							}
						}
					});
				} 
		   }else{
			   
		   	   return false;
		   }
	   }
	//校验
	function validCheck(myForm)
	{
		 var chkRadio = $('input:radio[name="optionsRadios"]:checked').val();
         if (chkRadio == null) {
             alert("没有选中项");
             return false;
         } else {
             return chkRadio;
         }
}
</script>
	
<style type="text/css">
  .anniu{position: absolute;top: 0.4rem;width: 2.5rem;height: 2.5rem;}
  .anniu img{width: 3.2rem;height: 3.2rem;display: block;}
  .New_main01{ width:100%;position:absolute; top:50px; left:0px; bottom:20px;overflow:auto;}
  .WZY_foot{width:100%; height:38px; position:fixed; left:0px;bottom:0;overflow:hidden;}
  
</style>
</head>
<body>
  <div id="header" class="header">
		<img src="<%=path%>/resources/img/wzy/logo.png" style="width: 100%;height:50px;" />
		<div class="anniu">
			<a href="javascript:history.go(-1);">
			   <img 
			    src="<%=path%>/resources/img/wzy/FH.png"/>
			 </a>
		</div>
  </div>
  <div class="New_main01">
      <div class="container">
           <div class="row">
              <div class="col-sm-12" style=" border-bottom:1px solid #e5e5e5;">
                <img src="<%=path%>/resources/img/wzy/wzy08.png" width="40" height="40">
                <span>我要投票</span>
              </div>
           </div>
          
           <c:forEach items="${tplist }" var="tplist">
           <div class="row">
              <div class="col-sm-12">
                <h4 style="text-align:center; color:#2991e6;">
                	${tplist.tpnr}<br><br>
                    <small>单选（匿名投票）&nbsp;&nbsp;发起时间：${tplist.fbsj }</small>
                </h4>
              </div>
           </div>
           </c:forEach>
           <Form name="myForm" id="myForm" method="post" action="">
           <table class="table table-striped table-condensed">
            <c:forEach items="${xxnrlist }" var="xxnrlist">
<%--             <c:if test="${tplist.id==xxlist.tpid }"> --%>
                <tr>
                    <td>
                    	<div class="radio">
                          <label>
                            <input type="radio"  name="optionsRadios" value="${xxnrlist.id }" >
                           	${xxnrlist.xxnr}
                          </label> 
                        </div>
                    </td>
                </tr>
<%--             </c:if> --%>
            </c:forEach>
            </table>
               <input type="hidden" name="subCnt" id="subCnt" value="${size }" />
		       <input type="hidden" name="id" id="id" value="${id }" />
		       <input type="hidden" name="openId" id="openId" value="${openId }" />
		       <input type="hidden" name="tpr" id="tpr" value="${tpr }"/>
            </Form>
            
            <div class="row">
              <div class="col-sm-12" style="text-align:center">
                <button class="btn btn-info" onclick="btnOK_onclick(myForm);">
                 确认提交
                </button>
              </div>
            </div>
        </div>
    </div>
  <div class="WZY_foot">
	  <img src="<%=path%>/resources/img/wzy/BQ.png" style="width:100%;">
  </div>
</body>
</html>