<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>    
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap.min.css" />  
    <link rel="stylesheet" type="text/css" href="/resources/js/ext/resources/css/ext-all.css" />
  	<script type="text/javascript">
	  var customerId = '${customerId}';
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
  <body>
  
  	 <div class="container">

      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>企业客户信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center">
	       	<tr>
	       		<th>客户号</th>
	       		<td>${company.customerId}</td>
	       		<th>客户名称</th>
	       		<td>${company.custName}</td>
	       	</tr>
       	 	<tr>
       	 		<th>英文名称</th>
	       		<td>${company.foreignName}</td>
	       		<th>证件类型</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${company.paperType =='01'}">身份证</c:when>
	       				<c:when test="${company.paperType =='02'}">户口簿</c:when>
	       				<c:when test="${company.paperType =='03'}">军官证</c:when>
	       				<c:when test="${company.paperType =='04'}">警官证</c:when>
	       				<c:when test="${company.paperType =='05'}">士兵证</c:when>
	       				<c:when test="${company.paperType =='06'}">文职干部证</c:when>
	       				<c:when test="${company.paperType =='07'}">护照</c:when>
	       				<c:when test="${company.paperType =='08'}">港澳台居民来往内地通行证</c:when>
	       				<c:when test="${company.paperType =='09'}">其他个人有效证件</c:when>
	       				<c:when test="${company.paperType =='60'}">营业执照</c:when>
	       				<c:when test="${company.paperType =='66'}">营业执照</c:when>
	       				<c:otherwise>${company.paperType}</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
       		<tr>
       			<th>证件号码</th>
	       		<td>${company.idCard}</td>
	       		<th>证件起始日期</th>
	       		<td>${company.beginDate}</td>
	       	</tr>
       		<tr>
       			<th>机构成立日期</th>
	       		<td>${company.createDate}</td>
	       		<th>借款人性质</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${company.customerType =='01'}">企业</c:when>
	       				<c:when test="${company.customerType =='02'}">事业</c:when>
	       				<c:when test="${company.customerType =='04'}">其他经济组织</c:when>
	       				<c:when test="${company.customerType =='05'}">党政机关或村级组织</c:when>
	       				<c:otherwise>${company.customerType}</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
	       	<tr>
	       		<th>行业类型</th>
	       		<td>${company.tradeType}</td>
	       		<th>主管部门</th>
	       		<td>${company.chargeUnits}</td>
	       	</tr>
	       	<tr>
	       		<th>法定代表人或负责人</th>
	       		<td>${company.corporation}</td>
	       		<th>基本账户开户行</th>
	       		<td>${company.baseAccountBank}</td>
	       	</tr>
	       	<tr>
	       		<th>注册地址</th>
	       		<td colspan="3">${company.unitAddr}</td>
	       	</tr>
       		<tr>
	       		<th>邮政编码</th>
	       		<td>${company.postcode}</td>
	       		<th>电子邮箱</th>
	       		<td>${company.email}</td>
	       	</tr>
       		<tr>
	       		<th>所在行政区域</th>
	       		<td>${company.adminArea}</td>
	       		<th>单位网址</th>
	       		<td>${company.urlUnits}</td>
	       	</tr>
       		<tr>
       			<th>财务部联系电话</th>
	       		<td>${company.telWrok}</td>
	       		<th>联系电话</th>
	       		<td>${company.telContact}</td>
	       	</tr>
	       	<tr>
	       		<th>传真号码</th>
	       		<td>${company.fax}</td>
	       		<th>在册人数</th>
	       		<td>${company.mans}</td>
	       	</tr>
	       	<tr>
	       		<th>贷款卡状态</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${company.loanCardState =='01'}">正常</c:when>
	       				<c:when test="${company.loanCardState =='02'}">注销</c:when>
	       				<c:when test="${company.loanCardState =='03'}">未年检</c:when>
	       				<c:when test="${company.loanCardState =='04'}">不存在</c:when>
	       				<c:otherwise>${company.loanCardState}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>贷款卡卡号</th>
	       		<td>${company.loancardCode}</td>
	       	</tr>
	       	<tr>
	       		<th>入股金额</th>
	       		<td>${company.buyStockMoney}</td>
	       		<th>本行社信用等级</th>
	       		<td>${company.ourbankCreditLevel}</td>
	       	</tr>
	       	<tr>
	       		<th>红黑名单</th>
	       		<td>${company.redBlackList}</td>
	       		<th>借款人国别</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${company.country =='01'}">中国</c:when>
	       				<c:when test="${company.country =='02'}">美国</c:when>
	       				<c:when test="${company.country =='03'}">日本</c:when>
	       				<c:when test="${company.country =='04'}">法国</c:when>
	       				<c:when test="${company.country =='05'}">德国</c:when>
	       				<c:when test="${company.country =='06'}">英国</c:when>
	       				<c:when test="${company.country =='07'}">俄罗斯</c:when>
	       				<c:when test="${company.country =='08'}">韩国</c:when>
	       				<c:when test="${company.country =='09'}">其他</c:when>
	       				<c:otherwise>${company.country}</c:otherwise>
	       			</c:choose>
	       		</td>
	       	</tr>
	       	<tr>
	       		<th>借款人通讯地址</th>
	       		<td colspan="3">${company.crediterAddr}</td>
	       	</tr>
	       	<tr>
	       		<th>法人代表或负责人证件号</th>
	       		<td>${company.corporationId}</td>
	       		<th>微型企业标志</th>
	       		<td>${company.microFlg}</td>
	       	</tr>
	       	<tr>
	       		<th>机构号</th>
	       		<td>${company.unit}</td>
	       		<th>柜员号</th>
	       		<td>${company.userId}</td>
	       	</tr>
	       	<tr>
	       		<th>法人证件种类</th>
	       		<td>
	       			<c:choose>
	       				<c:when test="${company.corporationPaperType =='0'}">身份证</c:when>
	       				<c:when test="${company.corporationPaperType =='1'}">户口簿</c:when>
	       				<c:when test="${company.corporationPaperType =='2'}">护照</c:when>
	       				<c:when test="${company.corporationPaperType =='3'}">军官证</c:when>
	       				<c:when test="${company.corporationPaperType =='4'}">士兵证</c:when>
	       				<c:when test="${company.corporationPaperType =='5'}">港澳居民来往内地通行证</c:when>
	       				<c:when test="${company.corporationPaperType =='6'}">台湾同胞来往内地通行证</c:when>
	       				<c:when test="${company.corporationPaperType =='7'}">临时身份证</c:when>
	       				<c:when test="${company.corporationPaperType =='8'}">外国人居留证</c:when>
	       				<c:when test="${company.corporationPaperType =='9'}">警官证</c:when>
	       				<c:when test="${company.corporationPaperType =='X'}">其他证件</c:when>
	       				<c:otherwise>${company.corporationPaperType}</c:otherwise>
	       			</c:choose>
	       		</td>
	       		<th>标记</th>
	       		<td>${company.lastFlag}</td>
       		</tr>
       		<tr>
       			<th>修改日期</th>
	       		<td>${company.lastDate}</td>
	       		<th>标志</th>
	       		<td>${company.flag}</td>
       		</tr>
       </table>
      </form>
      <br>
      <br>
      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>单位许可证管理信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
			<th>序号</th>
			<th>许可证或合格证名称</th>
			<th>生产经营许可证号码</th>
			<th>经营许可证颁发机构</th>
			<th>操作</th>
		</tr>	
       </table>
      </form>
      <br>
      <br>
      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>单位上市信息</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
			<th>序号</th>
			<th>股票代码</th>
			<th>股票名称</th>
			<th>上市时间</th>
			<th>交易所名称</th>
			<th>操作</th>
		</tr>	
       </table>
      </form>
      <br>
      <br>
      <form class="navbar-form">
       <h4 class="form-signin-heading" align="center"><font color="#27408B"><b>按揭协议</b></font></h4>
       <table class="table-striped table-bordered"  align="center" width="100%">
       	<tr>
			<th>序号</th>
			<th>客户号</th>
			<th>合作项目协议编号</th>
			<th>合作项目名称</th>
			<th>操作</th>
		</tr>	
       </table>
      </form>
    </div> <!-- /container -->
  </body>
</html>