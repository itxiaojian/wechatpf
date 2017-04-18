
var $mb=null; var timer=null;

(function (config) {
    config['lock'] = true;
    config['fixed'] = true;
    config['opacity']=0.4;
    config['background']="#fff";
    config['ok'] = true;
    config['width']="auto";
    config['height']="auto";
    config['padding']='20px 25px';
    config['title']='消息';
    config['okVal'] = '确定';
    config['cancelVal'] = '取消';
	config['resize']=false;
})($.dialog.defaults);

function setcontent(c,n){
   var cstr='<font style="font-size:14px;';
   switch(n)
   {
       case "error":
           cstr+='color:#FF0000">';
           break;
       case "question":
           cstr+='color:#00BAFF">';
           break;
       case "succeed":
           cstr+='color:#00A100">';
           break;
       case "warning":
           cstr+='color:#FA9F00">';
           break;
       default:
           cstr+='color:#333333">';
   }
   return cstr+=c+"</font>";
}
function message(ncontent,nicon,ntime,ac){
    return $.dialog({
    content: setcontent(ncontent,nicon),
    title:ntime?ntime+"秒后关闭":"消息",
    icon:nicon?nicon:null,
    time:ntime?ntime:null,
    ok:function(){eval(ac);}
    });
}

function messagehref(ncontent,nhref,nicon) {
    return $.dialog({
    cancel:false,
    esc:false,
    dblclick:false,
    content: setcontent(ncontent,nicon),
    icon:nicon?nicon:null,
    ok:function(){location.href = nhref;}
    });
}

function messageback(ncontent,nicon,n) {
    return $.dialog({
    content: setcontent(ncontent,nicon),
    icon:nicon?nicon:null,
    ok:function(){history.go(-(n?n:1));}
    });
}
function messageimg(strsrc,width){
    var img = new Image();
    img.src =strsrc;
    var w = img.width;
    width=w>width?width:w;
    if(strsrc.substring(strsrc.lastIndexOf("/")+1)=="upload-pic.png"){return false;}
    art.dialog({padding: 0,title: '照片',content: '<img src=\"'+strsrc+'\" width="'+width+'"/>'});
}
function msconfirm(nhref){ $.dialog.confirm('你确认删除操作？', function(){location.href = nhref;}, function(){this.close();});}


function setTab(m,obj){
    if($(this).attr("class")=="now"){return;}
    obj.parent('.tab_title').find("li").each(function(){
        $(this).removeClass("now");
    });
    obj.addClass("now");
    var i=0;
    $("#list"+obj.parent('.tab_title').attr("id")).find(".tabbox").each(function(){
         $(this).removeClass("none");$(this).removeClass("block");
         if(m==i){$(this).addClass("block");}
         else{$(this).addClass("none");}
         i++;
    });
}

function crop_cut_thumb(pic,thumb,thumb_preview){
	if (pic=='') { message('请先上传缩略图',"warning");return false;}
	$.dialog.open(
    'public_crop.aspx?a=public_crop&picurl='+encodeURIComponent(pic)+'&input='+thumb+'&preview='+thumb_preview,
    {id:'crop',title: '裁切图片',width:680,height:480,
    ok:function(){
        var d =  $.dialog({id:'crop'}).iframe.contentWindow;
        if (!d.document.body) {alert('iframe还没加载完毕呢');return false;}
        d.uploadfile();return false;},
    cancel:false
    });
};

//upload_type, dialog_title,input,file_type,filetype_post,file_size_limit,file_upload_limit,isadmin
function flashupload(upload_type,dialog_title,input,thumb_preview,file_type,filetype_post,file_size_limit,file_upload_limit,isadmin,oldfile,did){
	$.dialog.open(
    'fileupload.aspx?a='+upload_type+'&file_type='+file_type+'&input='+input+'&thumb_preview='+thumb_preview+'&filetype_post='+filetype_post+'&file_size_limit='+file_size_limit+'&file_upload_limit='+file_upload_limit+'&isadmin='+isadmin+"&oldfile="+encodeURIComponent(oldfile),
    {id:'file_upload'+did,title:dialog_title,width:480,height:400,
    ok:function(){
        var d = $.dialog({id:'file_upload'+did}).iframe.contentWindow;
        if (!d.document.body) {alert('iframe还没加载完毕呢');return false;}
        d.thumb_imagebg(input,thumb_preview,did);return false;},
    cancel:true
    });

}

function user_select(user_list,ac,singer,roleid,rolepower,xkid,did){
	$.dialog.open(
    'userselect.aspx?singer='+singer+'&roleid='+roleid+'&rolepower='+rolepower+'&xkid='+xkid+'&userlist='+user_list,
    {id:'userselect'+did,title: '用户选者',width:420,height:420,padding:'0px 0px',
    ok:function(){
        var d = $.dialog({id:'userselect'+did}).iframe.contentWindow;
        if (!d.document.body) {alert('iframe还没加载完毕呢');return false;}
        d.action(ac,did);return false;},
    cancel:true
    });
}

function node_select(nodeid,ac,singer,nodetype,xkid,did){
	$.dialog.open(
    'node_select.aspx?singer='+singer+'&nodetype='+nodetype+'&xkid='+xkid+'&nodeid='+nodeid,
    {id:'node_select'+did,title: '栏目选者',width:450,height:420,padding:'0px 0px',
    ok:function(){
        var d = $.dialog({id:'node_select'+did}).iframe.contentWindow;
        if (!d.document.body) {alert('iframe还没加载完毕呢');return false;}
        d.action(ac,did);return false;},
    cancel:true
    });
}



function isEmpty(string) { if (string.length == 0) { return false; } return true; }
function isSame(str1, str2) { if (str1 != str12) { return false; } return true; }
function isPhone(String) { var Letters = "1234567890-"; var i; var c; if (String.charAt(0) == '-') return false; if (String.charAt(String.length - 1) == '-') return false; for (i = 0; i < String.length; i++) { c = String.charAt(i); if (Letters.indexOf(c) < 0) return false; } return true; }
//字符长度限制
function lenCheck(string, max, min) { if (string.length > max || string.length < min) { return false; } return true; }
//英文值检测
function isEnglish(name) { if (name.length == 0) return false; for (i = 0; i < name.length; i++) { if (name.charCodeAt(i) > 128) return false; } return true; }
//中文值检测
function isChinese(name) { if (name.length == 0) return false; for (i = 0; i < name.length; i++) { if (name.charCodeAt(i) > 128) return true; } return false; }
// E-mail值检测
function isMail(name) { if (!isEnglish(name)) return false; i = name.indexOf("@"); j = name.lastIndexOf("@"); if (i == -1) return false; if (i != j) return false; if (i == name.length) return false; return true; }
//数值检测
function isNum(String) { var Letters = "1234567890."; var i; var c; if (String.charAt(0) == '.') return false; if (String.charAt(String.length - 1) == '.') return false; for (i = 0; i < String.length; i++) { c = String.charAt(i); if (Letters.indexOf(c) < 0) return false; } return true; }
function isNumber(name) { if (name.length == 0) return false; for (i = 0; i < name.length; i++) { if (name.charAt(i) < "0" || name.charAt(i) > "9") return false; } return true; }
//字符串包含测试函数
function contain(str, charset) { var i; for (i = 0; i < charset.length; i++) if (str.indexOf(charset.charAt(i)) >= 0) return true; return false; }
function isAllow(string) { if ((contain(string, "%\(\)><")) || (contain(string, "%\(\)><"))) { return false; } return true; }
//日期验证
function isDate(theDate) { var reg = /^\d{4}-((0{0,1}[1-9]{1})|(1[0-2]{1}))-((0{0,1}[1-9]{1})|([1-2]{1}[0-9]{1})|(3[0-1]{1}))$/; var result = true; if (!reg.test(theDate)) result = false; else { var arr_hd = theDate.split("-"); var dateTmp; dateTmp = new Date(arr_hd[0], parseFloat(arr_hd[1]) - 1, parseFloat(arr_hd[2])); if (dateTmp.getFullYear() != parseFloat(arr_hd[0]) || dateTmp.getMonth() != parseFloat(arr_hd[1]) - 1 || dateTmp.getDate() != parseFloat(arr_hd[2])) { result = false } } return result; }

//html编码
function HTMLEncode(input) { var converter = document.createElement("DIV"); converter.innerText = input; var output = converter.innerHTML; converter = null; return output; }
//html解码
function HTMLDecode(input) { var converter = document.createElement("DIV"); converter.innerHTML = input; var output = converter.innerText; converter = null; return output; }

function getAbsPointW(e) { var x = e.offsetLeft; while (e = e.offsetParent) { x += e.offsetLeft; } return x; }
function getAbsPointH(e) { y = e.offsetTop; while (e = e.offsetParent) { y += e.offsetTop; } return y; }
//过滤前后字符
function trim(str) {
    for (var i = 0; i < str.length && str.charAt(i) == "  "; i++);
    for (var j = str.length; j > 0 && str.charAt(j - 1) == "  "; j--);
    if (i > j) return "";
    return str.substring(i, j);
}