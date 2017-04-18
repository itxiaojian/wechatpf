<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
   <%	String customerId = request.getParameter("customerId"); //客户号
   		String path = request.getContextPath();
  %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>    
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap.min.css" />  
    <script type="text/javascript">var PATHNAME="<%=path%>"</script>
    <link rel="stylesheet" type="text/css" href="/resources/js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="/resources/js/ext/ext-base.js"></script>
    <script type="text/javascript" src="/resources/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="/resources/js/ext/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resources/js/jquery/jquery.min.js"></script>
    
	<link rel="stylesheet" type="text/css" href="/resources/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/index.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/cps_icons.css" />
	<script type="text/javascript" src="/resources/js/ux/Ext.ux.TreeCombo.js"></script>
	<script type="text/javascript" src="/resources/js/ux/ST.ux.util.js"></script>
	<script type="text/javascript" src="/resources/js/ux/ST.ux.ExtField.js"></script>	
	<script type="text/javascript" src="/resources/js/ux/Ext.ux.PagePlugins.js"></script>
	<script type="text/javascript" src="/resources/js/ux/uxForm.js"></script>
	<script type="text/javascript" src="/resources/js/ux/uxGrid.js"></script>
	<script type="text/javascript" src="/resources/js/ux/uxVtypes.js"></script>
	<script type="text/javascript" src="/resources/js/cps/staticData.js"></script>
    
  	<script type="text/javascript">
	  var customerId = '<%=customerId%>';
	  $(document).ready(function(){
		  $.post('/cps/unifiedView/private/familyMainMemberInfo',{'customerId':customerId},function(data){
			  var tb = document.getElementById("table1");
			  if(data ==null || data.length ==0){
				  var tr = tb.insertRow(-1);
				  var td0 = tr.insertCell(0);
				  var td1 = tr.insertCell(1);
				  var td2 = tr.insertCell(2);
				  var td3 = tr.insertCell(3);
				  var td4 = tr.insertCell(4);
				  var td5 = tr.insertCell(5);
				  var td6 = tr.insertCell(6);
				  td3.innerHTML = "<div align=\"center\">没有记录</div>";
			  }else{
				  for(var i=0;i<data.length;i++){
					  var d = data[i];
					  var tr = tb.insertRow(-1);
					  var td0 = tr.insertCell(0);
					  var td1 = tr.insertCell(1);
					  var td2 = tr.insertCell(2);
					  var td3 = tr.insertCell(3);
					  var td4 = tr.insertCell(4);
					  var td5 = tr.insertCell(5);
					  var td6 = tr.insertCell(6);
					  td6.style.align = "center";
					   td0.innerHTML = Number(1)+Number(i);
					   td1.innerHTML = d.customerId;
					   td2.innerHTML = d.custName;
					   td3.innerHTML = d.sex;
					   td4.innerHTML = d.relationToCustomer;
					   td5.innerHTML = d.jobUnitName;
					   td6.innerHTML = "<div align=\"center\"><a align=\"center\" href=\"javascript:void(0)\" onclick=\"showMemberDetails('"+d.rowId+"','"+d.custName+"')\">查看</a></div>";
				  }
			  }
		  });
	  });
	  PrivateViewWindow = Ext.extend(Ext.Window,{
		    constructor: function(grid) {
		    	PrivateViewWindow.superclass.constructor.call(this, {
		            width: 800,
		            anchor: '100%',
		            maximized :true,
		            height: 400,
		            resizable : false,
		            plain: true,
		            modal: true,
		            autoScroll: true,
		            closeAction: 'close',
		            buttonAlign: 'center',
		            buttons: [
		                      { text: '关闭', handler:function(){
		                    	  PRIVATE_VIEW_WINDOW.close();
		                      } }
		                  ],
		            html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src=""></iframe>'
		        });
		    }
		});
	//家庭主要成员情况
	  function showMemberDetails(rowId,custName){
	  	var url ='/cps/unifiedView/private/familyMemberDetails?rowId='+ rowId ;
	  	var html = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>';    		
	  	PRIVATE_VIEW_WINDOW = new PrivateViewWindow();
	  	PRIVATE_VIEW_WINDOW.setTitle("家庭主要成员情况--"+rowId);
	  	PRIVATE_VIEW_WINDOW.html = html;
	  	PRIVATE_VIEW_WINDOW.show();
	  }

  	</script>
  	
	<style type="text/css">
	th {
		padding: 6px 12px;
		width:15%;
		background-color: #eeeeee;
	}
	td {
		padding: 6px 12px;
	}
	</style>
  </head>
  <body style=" overflow:scroll">
  
  	 <div class="container" height="100%" width="100%">

      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>个人基本信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" height="100%" width="100%">
	       	<tr>
	       		<th>客户号</th>
	       		<td>${person.customerId}</td>
	       		<th>客户名称</th>
	       		<td>${person.custName}</td>
	       	</tr>
       	 	<tr>
	       		<th>英文名称</th>
	       		<td>${person.foreignName}</td>
	       		<th>性别</th>
	       		<td>
		       		<c:choose>  
					   <c:when test="${person.sex =='0'}">男</c:when>  
					   <c:when test="${person.sex =='1'}">女</c:when>
					   <c:when test="${person.sex =='2'}">未知</c:when>
					   <c:otherwise>${person.sex}</c:otherwise>
					</c:choose> 
	       		</td>
	       	</tr>
       		<tr>
	       		<th>证件类型</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${person.paperType =='01'}">身份证</c:when>
	       				<c:when test="${person.paperType =='02'}">户口簿</c:when>
	       				<c:when test="${person.paperType =='03'}">军官证</c:when>
	       				<c:when test="${person.paperType =='04'}">警官证</c:when>
	       				<c:when test="${person.paperType =='05'}">士兵证</c:when>
	       				<c:when test="${person.paperType =='06'}">文职干部证</c:when>
	       				<c:when test="${person.paperType =='07'}">护照</c:when>
	       				<c:when test="${person.paperType =='08'}">港澳台居民来往内地通行证</c:when>
	       				<c:when test="${person.paperType =='09'}">其他个人有效证件</c:when>
	       				<c:when test="${person.paperType =='60'}">营业执照</c:when>
	       				<c:when test="${person.paperType =='66'}">营业执照</c:when>
	       				<c:otherwise>${person.paperType}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>证件号码</th>
	       		<td>${person.idCard}</td>
	       	</tr>
       		<tr>
	       		<th>出生日期</th>
	       		<td>${person.birthday}</td>
	       		<th>民族</th>
	       		<td>${person.nation}</td>
	       	</tr>
	       		<tr>
	       		<th>最高学历</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${person.eduLev =='10'}">研究生</c:when>
	       				<c:when test="${person.eduLev =='20'}">大学本科（简称大学）</c:when>
	       				<c:when test="${person.eduLev =='30'}">大学专科和专科学校（简称大专）</c:when>
	       				<c:when test="${person.eduLev =='40'}">中等专业学校或中等技术学校</c:when>
	       				<c:when test="${person.eduLev =='50'}">技术学校</c:when>
	       				<c:when test="${person.eduLev =='60'}">高中</c:when>
	       				<c:when test="${person.eduLev =='70'}">初中</c:when>
	       				<c:when test="${person.eduLev =='80'}">小学</c:when>
	       				<c:when test="${person.eduLev =='90'}">文盲或半文盲</c:when>
	       				<c:when test="${person.eduLev =='99'}">未知</c:when>
	       				<c:otherwise>${person.eduLev}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>最高学位</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${person.topDegree =='2'}">博士</c:when>
	       				<c:when test="${person.topDegree =='3'}">硕士</c:when>
	       				<c:when test="${person.topDegree =='4'}">学士</c:when>
	       				<c:when test="${person.topDegree =='9'}">无</c:when>
	       				<c:otherwise>${person.topDegree}</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
	       		<tr>
	       		<th>通讯地址</th>
	       		<td colspan="3">${person.homeAddr}</td>
	       	</tr>
	       		<tr>
	       		<th>通讯地址邮政编码</th>
	       		<td>${person.homePostcode}</td>
	       		<th>电子邮箱</th>
	       		<td>${person.email}</td>
	       	</tr>
       		<tr>
	       		<th>户籍地址</th>
	       		<td>${person.nativeAddr}</td>
	       		<th>居住状况</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${person.houses =='1'}">自置</c:when>
	       				<c:when test="${person.houses =='2'}">按揭</c:when>
	       				<c:when test="${person.houses =='3'}">亲属楼宇</c:when>
	       				<c:when test="${person.houses =='4'}">集体宿舍</c:when>
	       				<c:when test="${person.houses =='5'}">租房</c:when>
	       				<c:when test="${person.houses =='6'}">共有住宅</c:when>
	       				<c:when test="${person.houses =='7'}">其他</c:when>
	       				<c:when test="${person.houses =='9'}">未知</c:when>
	       				<c:otherwise>${person.houses}</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
	       	<tr>
	       		<th>年收入</th>
	       		<td>${person.moneyPerYear eq null ?"0.00":person.moneyPerYear} 元</td>
	       		<th>入股金额</th>
	       		<td>${person.buyStockMoney eq null ?"0.00":person.buyStockMoney} 元</td>
	       	</tr>
	       	<tr>
	       		<th>固定电话</th>
	       		<td>${person.telHome}</td>
	       		<th>政治面貌</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${person.polity =='01'}">中共党员</c:when>
	       				<c:when test="${person.polity =='02'}">共青团员</c:when>
	       				<c:when test="${person.polity =='03'}">民主党派</c:when>
	       				<c:when test="${person.polity =='04'}">其他</c:when>
	       				<c:otherwise>${person.polity}</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
	       	<tr>
	       		<th>身体状况</th>
	       		<td>${person.health}</td>
	       		<th>婚姻状况</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${person.isMarried =='10'}">未婚</c:when>
	       				<c:when test="${person.isMarried =='20'}">已婚</c:when>
	       				<c:when test="${person.isMarried =='30'}">丧偶</c:when>
	       				<c:when test="${person.isMarried =='40'}">离婚</c:when>
	       				<c:when test="${person.isMarried =='90'}">未说明婚姻状况</c:when>
	       				<c:otherwise>${person.isMarried}</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
	       	<tr>
	       		<th>评级到期日</th>
	       		<td></td>
	       		<th>信用等级</th>
	       		<td>${person.creditRating}</td>
	       	</tr>
	       	<tr>
	       		<th>借款人性质</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${person.customerType =='06'}">农户</c:when>
	       				<c:when test="${person.customerType =='07'}">个体工商户</c:when>
	       				<c:when test="${person.customerType =='08'}">助学客户</c:when>
	       				<c:when test="${person.customerType =='09'}">其他个人客户</c:when>
	       				<c:otherwise>${person.customerType}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>移动电话</th>
	       		<td>${person.mobil}</td>
	       	</tr>
	       	<tr>
	       		<th>行业分类</th>
	       		<td>${person.tradeType}</td>
	       		<th>本行员工标志</th>
	       		<td>
	       			<c:choose>  
					   <c:when test="${person.benHangFlag =='0'}">是</c:when>  
					   <c:when test="${person.benHangFlag =='1'}">否</c:when>
					   <c:otherwise>${person.benHangFlag}</c:otherwise>
					</c:choose> 
	       		</td>
	       	</tr>
	       	<tr>
	       		<th>征信取数标准</th>
	       		<td></td>
	       		<th></th>
	       		<td></td>
	       	</tr>
       </table>
      </form>

      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"  size=4><b>家庭主要成员情况</b></font></h4>
       <table class="table-striped table-bordered" id="table1" align="center" height="100%" width="100%">
       	<tr>
			<th>序列号</th>
			<th>客户号</th>
			<th>客户名称</th>
			<th>性别</th>
			<th>与客户关系</th>
			<th>工作单位名称</th>
			<th>操作</th>
		</tr>	
       </table>
      </form>
	
	<c:choose>  
		<c:when test="${person.customerType =='06'}">
			<form class="navbar-form">
		       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>农户信息</b></font></h4>
		       <table class="table-striped table-bordered"  align="center" width="100%">
		       	<tr>
					<th width="20%">序列号</th>
					<th>流水号</th>
					<th>客户号</th>
					<th>家庭人口数</th>
					<th>劳动力人数</th>
					<th>操作</th>
				</tr>	
				<c:if test="${empty farmerInfos}">
					<tr>
						<td colspan="6" align="center">没有记录</td>
					</tr>
				</c:if>  
				<c:forEach var="data" items="${farmerInfos}" varStatus="status"> 
						<tr>
							<td>${status.count}</td>
							<td>${data.rowId}</td>
							<td>${data.customerId}</td>
							<td>${data.personNumber }</td>
							<td>${data.powerPersonNumber}</td>
							<td align="center"><a href="javascript:void(0)" onclick="parent.showFarmerInfoDetails('${data.rowId}')">查看</a></td>
						</tr>
					</c:forEach>
		       </table>
		      </form>
		</c:when>
		
		<c:when test="${person.customerType =='07'}">
			<form class="navbar-form">
		       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>个体工商户客户信息</b></font></h4>
		      </form>
		</c:when>
		
		
		<c:when test="${person.customerType =='08'}">
			<form class="navbar-form">
		       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>助学客户信息</b></font></h4>
		       <table class="table-striped table-bordered"  align="center" width="100%">
		       	<tr>
					<th width="20%">序列号</th>
					<th>流水号</th>
					<th>客户号</th>
					<th>学生姓名</th>
					<th>学校名称</th>
					<th>学生编号</th>
					<th>操作</th>
				</tr>	
				<c:if test="${empty studentInfos}">
					<tr>
						<td colspan="6" align="center">没有记录</td>
					</tr>
				</c:if>  
				<c:forEach var="data" items="${studentInfos}" varStatus="status"> 
						<tr>
							<td>${status.count}</td>
							<td>${data.ROW_ID}</td>
							<td>${data.CUSTOMER_ID}</td>
							<td>${data.STUDENT_NAME }</td>
							<td>${data.SCHOOL_NAME }</td>
							<td>${data.STUDENT_ID}</td>
							<td align="center"><a href="javascript:void(0)" onclick="parent.showStudentInfoDetails('${data.ROW_ID}','${data.STUDENT_NAME}')">查看</a></td>
						</tr>
					</c:forEach>
		       </table>
		      </form>
		</c:when>
		<c:when test="${person.customerType =='09'}">
			<form class="navbar-form">
		       <h4 class="form-signin-heading" align="center"><font color="#27408B" size=4><b>其他个人客户信息</b></font></h4>
		       <table class="table-striped table-bordered"  align="center" width="100%">
		       	<tr>
					<th width="20%">序列号</th>
					<th>流水号</th>
					<th>客户号</th>
					<th>工作单位名称</th>
					<th>单位地址</th>
					<th>操作</th>
				</tr>	
				<c:if test="${empty othersInfos}">
					<tr>
						<td colspan="6" align="center">没有记录</td>
					</tr>
				</c:if>  
				<c:forEach var="data" items="${othersInfos}" varStatus="status"> 
						<tr>
							<td>${status.count}</td>
							<td>${data.ROW_ID}</td>
							<td>${data.CUSTOMER_ID}</td>
							<td>${data.JOB_UNIT_NAME }</td>
							<td>${data.UNIT_ADDR }</td>
							<td align="center"><a href="javascript:void(0)" onclick="parent.showOtherCustInfoDetails('${data.ROW_ID}','${data.CUSTOMER_ID}')">查看</a></td>
						</tr>
					</c:forEach>
		       </table>
		      </form>
		</c:when>
	</c:choose>
    </div> <!-- /container -->
    
  </body>
</html>