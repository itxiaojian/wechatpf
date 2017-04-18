<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0,user-scalable=no;">


    <title> 修改信件</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="<%=path%>/resources/css/xzxx/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/custom.css" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/summernote.css" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/summernote-bs3.css" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/animate.css" rel="stylesheet">
    <link href="<%=path%>/resources/css/xzxx/style.css?v=4.1.0" rel="stylesheet">
    <script type="text/javascript" src="<%=path%>/resources/js/formValidator/validateEngine.js"></SCRIPT>

<script type="text/javascript">
	function query(){
		var keyWord = $("#code").val();
		if(keyWord == null || keyWord == ""){
			alert("请输入关键词查询!");
			return false;
		}else{
			$("#myForm").submit();
		}
	}
	
	function Submit(){
	  	var xjbt=document.getElementById("xjbt").value;
		if(xjbt==''){
			alert("请填写信件标题！");
			return false;
		}
	  	var slbmbh=document.getElementById("slbmbh").value;
		if(slbmbh=='-1'){
			alert("请选择受理部门！");
			return false;
		}
	  	/*var txdz=document.getElementById("txdz").value;
		if(txdz==''){
			alert("请填写通讯地址！");
			return false;
		}
	  	var lxdh=document.getElementById("lxdh").value;
		if(lxdh==''){
			alert("请填写联系电话！");
			return false;
		}*/
		
		//富文本框的内容填进input框中
// 		$('#xjnr').val(UM.getEditor('myEditor').getContent());
		
	  	var xjnr=document.getElementById("xjnr").value;
		if(xjnr==''){
			alert("请填写信件内容！");
			return false;
		}
		$('#addInput').hide();
		var url = '<%=path%>/xzxx/XxXjxxb/updateXj';
		var openId = document.getElementById("openId").value;
		var lx = document.getElementById("typeId").value;
		$.ajax({
			url: url,
			type : 'post',
			dataType : 'json',
			data: $('#mForm').serialize(),
			success: function(data){
				if(data != null || data != ''){
						location.href ="<%=path%>/xzxx/XxXjxxb/toXzxxList?lx="+lx+"&openId="+openId;
				}else{
					alert("提交失败");
				}
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
    }
	//返回
	function return_prepage()  
    {  
    if(window.document.referrer==""||window.document.referrer==window.location.href)  
    {  
    window.location.href="{dede:type}[field:typelink /]{/dede:type}";  
    }else  
    {  
    window.location.href=window.document.referrer;  
    }  
    }  
	
	/* //验证电子邮箱的格式
	function valiteyx(){
	var txdz = $('#txdz').val();
	reg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]

{2,3}$/gi;
		    if(!reg.test(txdz)){
		        alert("非法的电子邮件");
		        $('#txdz').focus();
		        return false;
		    }
	} */
	
	//获得内容
	  function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getContent());
        alert(arr.join("\n"));
    }
</script>
</head>
<style>
   .fa {font-size:180%;}
   </style>
<body class="gray-bg">

    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-3">
<!--                 <div class="ibox float-e-margins"> -->
<!--                     <div class="ibox-content mailbox-content"> -->
<!--                         <div class="file-manager"> -->
<!--                             <a class="btn btn-block btn-primary compose-mail" href="mail_compose.html">写信</a> -->
<!--                             <div class="space-25"></div> -->
<!--                             <h5>校长信箱</h5> -->
<!--                             <ul class="folder-list m-b-md" style="padding: 0"> -->
<!--                                  <li> -->
<%--                                     <a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}"> <i class="fa fa-inbox "></i> 全部信件<span class="label label-warning pull-right">16</span> --%>
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li> -->
<%--                                     <a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?lx=1&openId=${openId}"> <i class="fa fa-envelope-o"></i> 我的信件</a> --%>
<!--                                 </li> -->
<!--                                 <li> -->
<!--                                     <a href="mailbox.html"> <i class="fa fa-certificate"></i> 重要</a> -->
<!--                                 </li> -->
<!--                                 <li> -->
<!--                                     <a href="mailbox.html"> <i class="fa fa-file-text-o"></i> 草稿 <span class="label label-danger pull-right">2</span> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li> -->
<!--                                     <a href="mailbox.html"> <i class="fa fa-trash-o"></i> 垃圾箱</a> -->
<!--                                 </li> -->
<!--                             </ul> -->

<!--                             <div class="clearfix"></div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
            <div class="col-sm-9 animated fadeInRight">
                <div class="mail-box-header">
                    <div class="pull-right tooltip-demo">
<!--                         <a href="mailbox.html" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="存为草稿"><i class="fa fa-pencil"></i> 存为草稿</a> -->
                        <a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}" class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="top" title="放弃"><i class="fa fa-times"></i> 放弃</a>
                    </div>
                    <h2>
                   修改信件
                </h2>
                </div>
                <div class="mail-box">


                    <div class="mail-body">

                        <form class="form-horizontal" id="mForm" method="post">
                        	<input type="hidden" value="${openId}" id="openId" name="openId"> 
							<input type="hidden" value="1" id="typeId" name="type"> 
							<input type="hidden" value="${id }" id="xjid" name="xjid"> 
                            <div class="form-group">
                                <label class="col-sm-2 control-label">信件标题：</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="xjbt" id="xjbt" validate="require;maxlength(32)" value="${map.xjbt }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">受理部门：</label>

                                <div class="col-sm-10">
<!--                                     <input type="text" class="form-control" value=""> -->
                                    <select id="slbmbh" name="slbmbh" class="form-control" >
										<option value="-1">请选择...</option>
										<c:forEach var="data" items="${list}" varStatus="obj">
										<option value="${data.bmbh }" <c:if test="${map.slbmbh==data.bmbh }">selected</c:if>>${data.bmmc }</option>
										</c:forEach>
									</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电子邮箱：</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="txdz" id="txdz" validate="require;maxlength(30)" value="${map.txdz }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话：</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="lxdh" id="lxdh" validate="require;maxlength(11)" value="${map.lxdh }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">信件内容：</label>
                                <br>
                                <div class="col-sm-10">
                                    <textarea  class="form-control" id="xjnr" name="xjnr" cols="10" rows="4" >${map.xjnr} </textarea>
                                </div>
                            </div>
                        </form>

                    </div>

                    <div class="mail-body text-right tooltip-demo">
                        <a href="javascript:void(0)" onClick="Submit();" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="top" title="Send"><i class="fa fa-reply"></i> 发送</a>
                        <a href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Discard email"><i class="fa fa-times"></i> 放弃</a>
<!--                         <a href="mailbox.html" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Move to draft folder"><i class="fa fa-pencil"></i> 存为草稿</a> -->
                    </div>
                    <div class="clearfix"></div>


                </div>
            </div>
        </div>
    </div>
    <div class="gohome"><a class="animated bounceInUp" href="<%=path%>/xzxx/XxXjxxb/toXzxxList?openId=${openId}" title="返回"><i class="fa fa-home"></i></a></div>

    <!-- 全局js -->
    <script src="<%=path%>/resources/css/xzxx/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=path%>/resources/css/xzxx/js/bootstrap.min.js?v=3.3.6"></script>



    <!-- 自定义js -->
    <script src="<%=path%>/resources/css/xzxx/js/content.js?v=1.0.0"></script>


    <!-- iCheck -->
    <script src="<%=path%>/resources/css/xzxx/js/icheck.min.js"></script>

    <!-- SUMMERNOTE -->
<!--     <script src="js/plugins/summernote/summernote.min.js"></script> -->
<!--     <script src="js/plugins/summernote/summernote-zh-CN.js"></script> -->
    <script>
//         $(document).ready(function () {
//             $('.i-checks').iCheck({
//                 checkboxClass: 'icheckbox_square-green',
//                 radioClass: 'iradio_square-green',
//             });


//             $('.summernote').summernote({
//                 lang: 'zh-CN'
//             });

//         });
//         var edit = function () {
//             $('.click2edit').summernote({
//                 focus: true
//             });
//         };
//         var save = function () {
//             var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
//             $('.click2edit').destroy();
//         };
    </script>

</body>

</html>
