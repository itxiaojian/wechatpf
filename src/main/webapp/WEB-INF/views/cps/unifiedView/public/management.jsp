<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
   <%	String customerId = request.getParameter("customerId"); //客户号
  %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>    
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap.min.css" />  
    <link rel="stylesheet" type="text/css" href="/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="/resources/js/jquery/jquery.min.js"></script>
  	<script type="text/javascript">
  	var customerId = '<%=customerId%>';
	  $(document).ready(function(){
		  $.post('/cps/unifiedView/public/getManagement',{'customerId':customerId},function(data){
			  var tb = document.getElementById("table1");
			  if(data ==null || data.length ==0){
				  var tr = tb.insertRow(-1);
				  var td0 = tr.insertCell(0);
				  var td1 = tr.insertCell(1);
				  var td2 = tr.insertCell(2);
				  var td3 = tr.insertCell(3);
				  var td4 = tr.insertCell(4);
				  td2.innerHTML = "<div align=\"center\">没有记录</div>";
			  }else{
				  for(var i=0;i<data.length;i++){
					  var d = data[i];
					  var tr = tb.insertRow(-1);
					  var td0 = tr.insertCell(0);
					  var td1 = tr.insertCell(1);
					  var td2 = tr.insertCell(2);
					  var td3 = tr.insertCell(3);
					  var td4 = tr.insertCell(4);
					   td0.innerHTML = Number(1)+Number(i);
					   td1.innerHTML = d.PERSON_CUSTOMER_ID;
					   td2.innerHTML = d.CUST_NAME;
					   td3.innerHTML = d.ID_CARD;
					   td4.innerHTML = "<div align=\"center\"><a align=\"center\" href=\"javascript:void(0)\" onclick=\"parent.showManagementInfoDetails('"+d.ROW_ID+"','"+d.CUST_NAME+"')\">查看</a></div>";
				  }
			  }
		  });
	  });
  	</script>
  	
	<style type="text/css">
	th {
		padding: 6px 12px;
		background-color: #eeeeee;
	}
	td {
		padding: 6px 12px;
	}
	</style>
  </head>
  <body>
  
  	 <div class="container">

      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>高管人员信息</b></font></h4>
       <table class="table-striped table-bordered" id="table1" align="center" width="100%">
       	<tr>
			<th>序号</th>
			<th>高管人员客户号</th>
			<th>高管人员名称</th>
			<th>证件号码</th>
			<th>操作</th>
		</tr>	
       </table>
      </form>

    </div> <!-- /container -->
  </body>
</html>