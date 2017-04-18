<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<html>
	<head>
		<title>投票专栏选项</title>
		<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/css/main.css" />
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/resources/js/app.js"></SCRIPT>
<script src="<%=path%>/libs/js/framework.js" type="text/javascript"></script>
<link href="<%=path%>/libs/skins/lightBlue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="lightBlue" />
<link href="<%=path%>/resources/css/base.css" rel="stylesheet" />
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
	
<script type="text/javascript">
    var textNumber = 1;
    function addTextBox(form, afterElement) {
        textNumber++;
        var label = document.createElement("label");
		label.setAttribute("class", "m_left");
        label.appendChild(document.createTextNode("选项"+textNumber+"："));
		var myTable = document.getElementById("myTable");
		var rowCnt = myTable.rows.length;
		var nextRow = myTable.insertRow(rowCnt - 1);
		var cellTitle = nextRow.insertCell(0);
		var cellText = nextRow.insertCell(1);
		cellTitle.className = "m_left";
		cellTitle.setAttribute("valign", "top");
		cellTitle.appendChild(label);
		var txtName = "txt" + textNumber;
		var txtId = "txt" + textNumber;
		cellText.innerHTML = "<input type='text' name='" + txtName + "' id='" + txtId + "' style=\"width:260px;\"/>";
    }
    function removeTextBox(form) {
		var myTable = document.getElementById("myTable");
		var rowCnt = myTable.rows.length;
        if (rowCnt > 2) {
			myTable.deleteRow(rowCnt-2);
            textNumber--;
        }
    }
    //提交
	function submit(){
 				var listCnt = document.getElementById("listCnt");
				listCnt.value = textNumber;
 		  	 	document.myForm.action="<%=path%>/wsh/ShTpzl/saveXx";
 		  	    document.myForm.submit();
 				return true;
	}
	
</script>
<style type="text/css">
.container{
	background-color: white;
    height: 500px;
    margin-left: 30px;
    margin-right: 30px;
    margin-top: 30px;
    overflow: hidden;
}
</style>
</head>
<body topmargin="2">
<div class="container">
		<table cellspacing="0" cellpadding="0" width="500" align="center" class="tab">
			<tbody>
				<tr>
					<td height="30"></td>
				</tr>
				<tr>
					<td align="center">
						添加选项
					</td>
				</tr>
				<tr>
					<td align="left">
						<button type="button" class="btn" class="btn" onclick="submit();"> 保     存 </button>
						<a href="javascript:;history.back()"> <button type="button" class="btn" class="btn"> 返     回 </button></a>
					</td>
				</tr>
			</tbody>
		</table>
		<form action="" name="myForm" method="post">
			<table width="500" border="0" align="center" cellpadding="2"  cellspacing="1" id="myTable" class="tab">
				<tr>
					<td valign="top">
						<label>
							选项1：
						</label>
					</td>
					<td>
						<input type="text" name="txt1" id="txt1" style="width:260px;"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="listCnt" id="listCnt" value="" />
						<input type="hidden" name="id" value="${id }" />
<%-- 						<input type="hidden" name="seq" value="${seq }" /> --%>
					</td>
					<td id="td1">
						<input type="button" value="添加选项 " name="add" onclick="addTextBox(this.form,this.parentNode)" class="btn"/>
						<input type="button" value="删除选项 " onclick="removeTextBox(this.form)" class="btn"/>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</body>
</html>