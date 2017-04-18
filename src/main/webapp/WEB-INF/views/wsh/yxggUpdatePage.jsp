<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
   <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>  
    <script type="text/javascript">var PATHNAME="<%=path%>"</script>
    <link href="<%=path%>/resources/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=path%>/resources/umeditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/resources/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/resources/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/umeditor/lang/zh-cn/zh-cn.js"></script>
    
    <script type="text/javascript" src="<%=path%>/resources/js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/cps/checkData.js"></script>
      <style type="text/css">
        h1{
            font-family: "微软雅黑";
            font-weight: normal;
        }
        .btn {
            display: inline-block;
            *display: inline;
            padding: 4px 12px;
            margin-bottom: 0;
            *margin-left: .3em;
            font-size: 14px;
            line-height: 20px;
            color: #333333;
            text-align: center;
            text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
            vertical-align: middle;
            cursor: pointer;
            background-color: #f5f5f5;
            *background-color: #e6e6e6;
            background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
            background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
            background-repeat: repeat-x;
            border: 1px solid #cccccc;
            *border: 0;
            border-color: #e6e6e6 #e6e6e6 #bfbfbf;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
            border-bottom-color: #b3b3b3;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
            filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
            *zoom: 1;
            -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn:hover,
        .btn:focus,
        .btn:active,
        .btn.active,
        .btn.disabled,
        .btn[disabled] {
            color: #333333;
            background-color: #e6e6e6;
            *background-color: #d9d9d9;
        }

        .btn:active,
        .btn.active {
            background-color: #cccccc \9;
        }

        .btn:first-child {
            *margin-left: 0;
        }

        .btn:hover,
        .btn:focus {
            color: #333333;
            text-decoration: none;
            background-position: 0 -15px;
            -webkit-transition: background-position 0.1s linear;
            -moz-transition: background-position 0.1s linear;
            -o-transition: background-position 0.1s linear;
            transition: background-position 0.1s linear;
        }

        .btn:focus {
            outline: thin dotted #333;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px;
        }

        .btn.active,
        .btn:active {
            background-image: none;
            outline: 0;
            -webkit-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn.disabled,
        .btn[disabled] {
            cursor: default;
            background-image: none;
            opacity: 0.65;
            filter: alpha(opacity=65);
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }
        
        .edui-modal edui-dialog-image{
        	display: none;
		    margin-left: -350px;
		    width: 400px;
		    height:200;
		    z-index: 1001
        }
    </style>
   </head> 
<body>

	<div class="panel panel-success">
		<div id="opinionDiv" class="panel-heading"><font color="red">*</font> 信息发布:</div>
		<div class="panel-body">
			<form name="submitForm" id="optionForm" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="projectName" id="projectName" value="${pageContext.request.contextPath}"/>
				<input type="hidden" name="content" id="content"/>
				<input type="hidden" name="id" id="id" value="${map.id}"/>
				<div class="input-group">
					<span class="input-group-addon"><font color="red">*</font> 信息标题</span>
					<input type="text" class="form-control" placeholder="请输入信息标题"
						value="${map.title}" name="title"
						id="title" />
				</div>
				<br>
<!-- 				<div class="input-group"> -->
<!-- 					<span class="input-group-addon"><font color="red">*</font> 信息注释</span> -->
<!-- 					<input type="text" class="form-control" placeholder="请输入信息注释" -->
<%-- 						value="${map.xwzs}" name="xwzs" --%>
<!-- 						id="xwzs" /> -->
<!-- 				</div> -->
<!-- 				<br> -->
				<div class="input-group">
					<span class="input-group-addon"><font color="red">*</font> 信息类型</span>
					<select class="form-control input-sm" name="type" value="" >
<%-- 				    	<c:forEach items="${list}" var="list"> --%>
			    			<option value="0" selected="selected">办理指南</option>
						    <option value="1">迎新公告</option>
<%-- 						</c:forEach> --%>
					</select>
				</div>
				<br>
				<!--style给定宽度可以影响编辑器的最终宽度-->
				<script type="text/plain" id="myEditor" style="width:1358px;height:240px;">
						${map.content}
				</script>
				<br>
				<div align="center">
 					<input type="submit" class="btn btn-primary" onclick="agree();" value="修改" />
				</div>
			</form>
		</div>
	</div>



<div class="clear"></div>
<!-- <div id="btns">
    <table>
        <tr>
            <td>
                <button class="btn" onclick="getContent()">获得内容</button>&nbsp;
                <button class="btn" onclick="hasContent()">判断是否有内容</button>
            </td>
        </tr>

    </table>
</div> -->

<script type="text/javascript">
    //实例化编辑器
     var um = UM.getEditor('myEditor');
     /*    
    um.addListener('blur',function(){
        $('#focush2').html('编辑器失去焦点了');
    });
    um.addListener('focus',function(){
        $('#focush2').html('');
    }); */
    //按钮的操作
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getContent());
        alert(arr.join("\n"));
    }
    
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UM.getEditor('myEditor').hasContents());
        alert(arr.join("\n"));
    }
    
</script>

</body>
</html>

<script type="text/javascript">
	var userCode = "${userCode}";
	var taskId = "${taskId}";
	var creditId = "${businessKey}";
	var url=$("#projectName").val();//获取跟目录
	var PAGESIZE=20;
	var options = {  
			//   target: '#output',          //把服务器返回的内容放入id为output的元素中     
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   url: url+'/wyx/WyxYxgg/updateYXGG',   //默认是form的action， 如果申明，则会覆盖  
			   //type: type,               //默认是form的method（get or post），如果申明，则会覆盖  
			   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型  
			   //clearForm: true,          //成功提交后，清除所有表单元素的值  
			   //resetForm: true,          //成功提交后，重置所有表单元素的值  
			   timeout: 10000              //限制请求的时间，当请求大于10秒后，跳出请求  
			};
	
	function showRequest(formData, jqForm, options){  
		   //formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]  
		   //jqForm:   jQuery对象，封装了表单的元素     
		   //options:  options对象  
		  // var queryString = $.param(formData);   //name=1&address=2  
		  // var formElement = jqForm[0];              //将jqForm转换为DOM对象  
		   //var address = formElement.address.value;  //访问jqForm的DOM元素  
		   return true;  //只要不返回false，表单都会提交,在这里可以对表单元素进行验证  
	};  
		
	function showResponse(responseText, statusText){  
			 if(responseText.success == true) {
				   alert('修改成功!');
	               window.parent.constructionGrid.store.load({params:{start:0,limit:PAGESIZE}});
	               window.parent.ACT_DEAL_WINDOW.close();
			 }else {
				   alert('修改失败，请联系管理员!');
			 }
	};		
	
	
	//批准
	function agree() {
		var returnFlag = checkApproveData();
		document.getElementById("content").value=UM.getEditor('myEditor').getContent();
		if(returnFlag) {
			$("#optionForm").ajaxForm(options);  
		}else{
			window.onbeforeunload = function(){ return '将丢失未保存的数据!'; } 
			return false;
		}
	}
	//不批准
	function disagree() {
		$("#valueInputId").val(0);
		$("#optionForm").ajaxForm(options); 
	}
	
	//点击客户姓名打开授信PDF档案显示页面
	function viewCreditPdf() {
		window.open("/cps/pdf/viewPdf?flag=0&creditId="+creditId);
	}
	
	//点击查看授信日志记录
	function viewCreditLog() {
		window.open("/cps/cust/creditLogPage?creditId="+creditId);
	}
	
	//校验输入的新闻内容
	function checkApproveData() {
		
		var returnFlag = UM.getEditor('myEditor').hasContents();
        
		return returnFlag;
	}
</script>