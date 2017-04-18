/**
 * Created by liujiasen on 2015/6/29.
 */
$(document).ready(function(){
    var h = $("#add-product-image").width();
    if($("#upload-img-list").children("li").size()>1){//编辑
        $(".image").height(h);
        $("#goods_detail").height($("#temp-product-info").height());
    }
    
    setTimeout(function(){
        $(".moxie-shim").children("input").attr("capture","camera").attr("accept","image/*").removeAttr("multiple");
    },500);
    $(".first-list").delegate("li","click",function(){
        $(".first-list li").removeClass("selected");
        $(this).addClass("selected");
        $("#first-id").val($(this).attr("value"));
        $("#second-id").val("");
        var cate = $(this).attr("pk");
        $(".category-ipt").val($(this).text());
        if($(".second-list").children("."+cate).size()>0){
            $(".first-list").addClass("hide");
            $(".second-list").children("."+cate).removeClass("hide");
            $(".second-list").removeClass("hide");
            $(".wrap-category .head-txt").html("选择二级分类");
        }else{
            $(".category-box").addClass("hide");
        }
    })
    $(".second-list").delegate("li","click",function(){
        $(".second-list li").removeClass("selected");
        $(this).addClass("selected");
        $("#second-id").val($(this).attr("value"));
        $(".category-ipt").val($(".category-ipt").val()+"-"+$(this).text());
        $(".category-box").addClass("hide");
    })
    $(".category-td").click(function(){
        $(".wrap-category .head-txt").html("选择分类");
        $(".first-list").removeClass("hide");
        $(".second-list li").addClass("hide");
        $(".second-list").addClass("hide");
        $(".category-box").removeClass("hide");
    })
}).on("click",".icon-del",function(){
    $(this).parent("li").remove();
    if($("#add-product-image").closest("li").hasClass("hide")){
        $("#add-product-image").closest("li").removeClass("hide");
        $(".moxie-shim").removeClass("hide");
    }
    $(".moxie-shim").css({width:$("#add-product-image").width(),height:$("#add-product-image").width(),left:$("#add-product-image").closest("li").position().left+15,top:$("#add-product-image").closest("li").position().top+15});//调整按钮的位置
}).on("click",".cate-close",function(){
    $(".category-box").addClass("hide");
}).on("keydown keyup",".product-info",function(){   //商品描述
    $(".temp-product-info").html($(this).val());
    if($(this).val()==""){
        $(".temp-product-info").html("");
    }
    $(".product-info").height($(".temp-product-info").height());
}).on("click","#go-prev",function(){    //返回上一页
    window.history.go(-1);
}).on("click","#edit-publish",function(){
    var imgArr = $(".upload-img-list").find(".image");
    if(imgArr.size()==0){
        alert("请至少上传一张图片");
        return;
    }
    var flag = beforeSubmit($("#publish-form"));
    if(flag){
        $(".wrap-loading").removeClass("hide");
        var goods_id = $("#goods_id").val();
        var goods_name = $("#goods_name").val();
        var goods_detail = $("#goods_detail").val();
        var goods_price = $("#goods_price").val();
        var goods_trade_place = $("#goods_trade_place").val();
        var goods_class_id = 0;
        if($("#second-id").val()==""){
            goods_class_id = $("#first-id").val();
        }else{
            goods_class_id = $("#second-id").val();
        }
        var school_id = getCookie("school_id");
        var qq = $("#qqnum").val();
        var tel = $("#tel").val();
        var images = new Array();
        imgArr.each(function(){
            images.push($(this).attr("url"));
        });
        var imagesUrls = images.toString();
        $.ajax({
            url:"/goods/modify",
            data:{
                goods_id:goods_id,
                goods_name : goods_name,
                goods_detail : goods_detail,
                goods_price : goods_price,
                goods_trade_place : goods_trade_place,
                goods_is_discount : "1",
                goods_class_id : goods_class_id,
                goods_image : imagesUrls,
                goods_school_id : school_id,
                user_qq : qq,
                user_phone_number : tel
            },
            type:"post",
            success:function(data){
                var res = $.parseJSON(data);
                $(".wrap-loading").addClass("hide");
                if (res.code == 0) {
                    //跳转到首页
                    window.location.href=res.data.goods_url;
                } else {
                    alert(res.msg);
                }
            }
        });
    }
});

//获取cookie
function getCookie(name){
    //cookie中的数据都是以分号加空格区分开
    var arr = document.cookie.split("; ");
    for(var i=0; i<arr.length; i++){
        if(arr[i].split("=")[0] == name){
            return arr[i].split("=")[1];
        }
    }
    //未找到对应的cookie则返回空字符串
    return '';
}
/*转化图片为base64*/
function previewImage(file,callback){//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
    if(!file || !/image\//.test(file.type)) return; //确保文件是图片
    if(file.type=='image/gif'){//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
        var fr = new mOxie.FileReader();
        fr.onload = function(){
            callback(fr.result);
            fr.destroy();
            fr = null;
        }
        fr.readAsDataURL(file.getSource());
    }else{
        var preloader = new mOxie.Image();
        preloader.onload = function() {
            preloader.downsize( 140, 140 ,true);//先压缩一下要预览的图片,宽，高
            var imgsrc = preloader.type=='image/jpeg' ? preloader.getAsDataURL('image/jpeg',70) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
            callback && callback(imgsrc); //callback传入的参数为预览图片的url
            preloader.destroy();
            preloader = null;
        };
        preloader.load( file.getSource() );
    }
}
