
/**************************************************************
 *author        ： zhangdongdong
 *description   :  贷款申请
 *date          : 2014-11-13
****************************************************************/

/**提交申请*/
function xdsq(){
		var openid = document.getElementById("openid").value;
		var username = document.getElementById("username").value;
		var sfz = document.getElementById("sfz").value;
		var phone = document.getElementById("Phone").value;
		var dkje = document.getElementById("je").value;
		var dkyt = document.getElementById("yt").value;
		if(username == null || username == ""){
			alert('请输入贷款人姓名');
			/*
			var current = document.getElementById("tsxx");  
			//current.style.display="";  
			current.style.display="block";
			document.getElementById('tsnr').innerHTML='请输入贷款人姓名';
			var obj = document.getElementById("qr");
			obj.onclick= function (){
				var current = document.getElementById("tsxx");  
				current.style.display="none";
				document.getElementById("username").focus();
			};*/
			
			document.getElementById("username").focus();
			return false;
		}
		if(sfz == null || sfz ==""){
			alert('请输入身份证号');
			document.getElementById("sfz").focus();
			return false;
		}else{
			 // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
			   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			   if(reg.test(sfz) === false)  
			   {  
			       alert("身份证输入不合法"); 
			       document.getElementById("sfz").focus();
			       return  false;  
			   }  
		}

		if(phone == null || phone ==""){
			alert('请输入手机号');
			document.getElementById("Phone").focus();
			return false;
		}else{
			
			 	if(phone.length!=11)
			    {
			 		alert("请输入有效的手机号码！");
			        document.getElementById("Phone").focus();
			        return false;
			    }
			    
			    var myreg=/^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
			    if(!myreg.test(phone))
			    {
			    	alert("请输入有效的手机号码！");
			        document.getElementById("Phone").focus();
			        return false;
			    }
			
			
		}
		if(dkje == null || dkje == ""){
			alert("请输入贷款金额！");
			document.getElementById("je").focus();
			return false;
		}else{
			
			var  isPriceNumber = /^[0-9]*(\.[0-9]{1,2})?$/;
			if(!isPriceNumber.test(dkje))
			{
				 alert("请输入有效金额！");
				 document.getElementById("je").focus();
				 return false;
			}
		}
		if(dkyt == null || dkyt == ""){
			alert("请输入贷款用途！");
			document.getElementById("yt").focus();
			return false;
		}
		
//		jConfirm('是否确认提交申请?', '系统提示', function(r) {
//			if(r){

				//1.$.ajax带json数据的异步请求  
				$.ajax( {    
				    url:'/weixin/xdsq/save',// 跳转到 Controller    
				    data:{  openid : openid,
				    		username : username,  
				    		sfz : sfz ,
				    		phone : phone,    
				    		dkje : dkje,    
				    		dkyt : dkyt    
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
							
							alert(data.message);
				        	
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

/**取消申请*/
function qx (){
	 window.location.reload();
}

/**验证手机号*/
function validatemobile(mobile)
{
     
    if(mobile.length!=11)
    {
        alert('请输入有效的手机号码！');
        document.getElementById("Phone").focus();
        return false;
    }
    
    //var myreg = /^(((13[0-9]{1})|159|153)+\d{8})$/;
    var myreg=/^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
    if(!myreg.test(mobile))
    {
        alert('请输入有效的手机号码！');
        document.getElementById("Phone").focus();
        return false;
    }
}

/**验证身份证*/
function isCardNo(card)  
{  
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(card) === false)  
   {  
       alert("身份证输入不合法");  
       return  false;  
   }  
}  

/**隐藏div, 
 * id为tsxx
 * */
function yc(){
	
	var current = document.getElementById("tsxx");  
	current.style.display="none";
}
