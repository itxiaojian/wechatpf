
/**************************************************************
 *author        ： zhangdongdong
 *description   :  投诉建议
 *date          : 2014-11-13
****************************************************************/

/**提交投诉建议信息*/
function tsjy(){
		var openid = document.getElementById("openid").value;
		var xz = document.getElementById("xz").value;
		var phone = document.getElementById("phone").value;
		var myText = document.getElementById("myText").value;
		
		if(phone == null || phone ==""){
			/*
			var current = document.getElementById("tsxx");  
			current.style.display="";  
			document.getElementById('tsnr').innerHTML='请输入手机号';
			var obj = document.getElementById("qr");
			obj.onclick= function (){
				var current = document.getElementById("tsxx");  
				current.style.display="none";
				document.getElementById("phone").focus();
			};*/
			alert("请输入手机号");
			document.getElementById("phone").focus();
			return false;
		}else{
			
			 	if(phone.length != 11)
			    {
			 		alert("请输入有效的手机号码！");
			        document.getElementById("phone").focus();
			        return false;
			    }
			    
			    var myreg=/^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
			    if(!myreg.test(phone))
			    {
			    	alert("请输入有效的手机号码！");
			        document.getElementById("phone").focus();
			        return false;
			    }
			
		}
		
		if(myText == null || myText == ""){
			alert("请输入内容");
			document.getElementById("myText").focus();
			return false;
		}else{
			
			if(myText.length > 300 ){
				alert("请输入少于300个字");
		        document.getElementById("myText").focus();
		        return false;
			}
		}
		
		//var gnl=confirm("确定要提交申请?");
//		jConfirm('是否确认提交投诉、建议信息?', '系统提示', function(r) {
//			if(r){

				//1.$.ajax带json数据的异步请求  
				$.ajax( {    
				    url:'/weixin/tsjy/save',// 跳转到 Controller    
				    data:{    
				    		openid : openid,
				    		xz : xz,    
				    		phone : phone,    
				    		myText : myText   
				    },    
				    type:'post',    
				    cache:false,    
				    dataType:'json',    
				    success:function(data) {   
				        if(data.success == true ){    
				            // view("修改成功！");   
				        	alert(data.message);
				        	//关闭当前网页窗口
							WeixinJSBridge.invoke('closeWindow',{},function(res){
					            //alert(res.err_msg);
					        });
				            //window.location.reload();    
				        }else{   
							
							alert("异常");
				        }    
				     },    
				    error : function() {   
				          // view("异常！");  
				    	
				    	alert("异常");
				    	
				     }    
				});     
//			}else{
//				  window.location.reload();    
//			}
//		});
		
	}

