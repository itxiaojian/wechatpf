Date.prototype.Format=function(a){var c={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),S:this.getMilliseconds()};if(/(y+)/.test(a)){a=a.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length))}for(var b in c){if(new RegExp("("+b+")").test(a)){a=a.replace(RegExp.$1,(RegExp.$1.length==1)?(c[b]):(("00"+c[b]).substr((""+c[b]).length)))}}return a};var gsAgent=navigator.userAgent.toLowerCase();var gfAppVer=parseFloat(navigator.appVersion);var gsAppVer=navigator.appVersion.toLowerCase();var gIsWebKit=gsAgent.indexOf("applewebkit")>-1;var gIsOpera=gsAgent.indexOf("opera")>-1;var gIsKHTML=gsAgent.indexOf("khtml")>-1||gsAgent.indexOf("konqueror")>-1||gsAgent.indexOf("applewebkit")>-1;var gIsSafari=gsAgent.indexOf("applewebkit")>-1;var gIsIE=(gsAgent.indexOf("compatible")>-1&&!gIsOpera)||gsAgent.indexOf("msie")>-1;var gIsTT=gIsIE?(navigator.appVersion.indexOf("tencenttraveler")!=-1?1:0):0;var gIsFF=gsAgent.indexOf("gecko")>-1&&!gIsKHTML;var gIsQBWebKit=gIsWebKit?(gsAppVer.indexOf("qqbrowser")!=-1?1:0):0;var gIsChrome=gIsWebKit&&!gIsQBWebKit&&gsAgent.indexOf("chrome")>-1&&gsAgent.indexOf("se 2.x metasr 1.0")<0;var gIsNS=!gIsIE&&!gIsOpera&&!gIsKHTML&&(gsAgent.indexOf("mozilla")==0)&&(navigator.appName.toLowerCase()=="netscape");var gIsAgentErr=!(gIsOpera||gIsKHTML||gIsSafari||gIsIE||gIsTT||gIsFF||gIsNS);function build_query(d,g){var a=get_query_args(d);var e=d.split("?");var f=false;for(var c in g){a[c]=encodeURIComponent(g[c]);if(c=="page"){f=true}}d=e[0]+"?";for(var b in a){if(!(a[b]=="null"||a[b]=="")){if(b=="page"&&f==false){continue}d+=b+"="+a[b]+"&"}}return d.substr(0,d.length-1)}function get_query_args(e){var a=new Object();var f=e.split("?");if(f.length>=2){var b=f[1];var d=b.split("&");for(var c in d){$item=d[c].split("=");a[$item[0]]=$item[1]}}return a}function showLoginDialog(a){if(jQuery("#login_dialog").length<=0){jQuery("body").append("<div id='login_dialog'><iframe style='width: 620px; height: 380px;' class='login-iframe' id='login_frame' name='login_frame' src='http://ui.ptlogin2.qq.com/cgi-bin/login?appid=519018403&s_url="+global.context+"/user/auth/?ref_url="+global.context+"/user/dialog_login?ref_url="+encodeURIComponent(a)+"&style=11&target=self&hide_close_icon=0' frameborder='0' scrolling='no'> </iframe></div>");login_dialod=jQuery("#login_dialog");jQuery("#login_dialog").dialog({autoOpen:false,closeOnEscape:false,modal:true,draggable:true,resizable:false,width:625,height:380,dialogClass:"login-dialog",open:function(c,d){var b={opacity:"0.5",left:"0px",top:"0px",background:"#333"};jQuery(".ui-widget-overlay").css(b);var b={position:"relative","z-index":"2"};jQuery(".ui-dialog-titlebar").css(b);var b={display:"block",position:"absolute",top:"10px",right:"0px",width:"50px",height:"23px",background:"red","z-index":"1000",zoom:"1",opacity:0};jQuery(".ui-dialog-titlebar-close").css(b)}})}jQuery("#login_dialog").dialog("open")}jQuery(function(b){var a=b("#context-data-form");window.global={context:a.find("input[name='context']").val()};$query_args=get_query_args(location.href);if($query_args.locate!=undefined){location.hash=$query_args.locate.split("#")[0]}});jQuery.cookie=function(b,j,m){if(typeof j!="undefined"){m=m||{};if(j===null){j="";m.expires=-1}var e="";if(m.expires&&(typeof m.expires=="number"||m.expires.toUTCString)){var f;if(typeof m.expires=="number"){f=new Date();f.setTime(f.getTime()+(m.expires*24*60*60*1000))}else{f=m.expires}e="; expires="+f.toUTCString();var l=m.path?"; path="+m.path:"";var g=m.domain?"; domain="+m.domain:"";var a=m.secure?"; secure":"";document.cookie=[b,"=",encodeURIComponent(j),e,l,g,a].join("")}}else{var d=null;if(document.cookie&&document.cookie!=""){var k=document.cookie.split(";");for(var h=0;h<k.length;h++){var c=jQuery.trim(k[h]);if(c.substring(0,b.length+1)==(b+"=")){d=decodeURIComponent(c.substring(b.length+1));break}}}return d}};function htmlspecialchars(a){if(typeof(a)=="string"){a=a.replace(/&/g,"&amp;");a=a.replace(/"/g,"&quot;");a=a.replace(/'/g,"&#039;");a=a.replace(/</g,"&lt;");a=a.replace(/>/g,"&gt;")}return a}function get_rich_text_length(a){var b=document.createElement("DIV");b.innerHTML=a;var c=b.innerText;c=c.replace(/[\n\r]/g,"");return c.length}function ptlogin2_onClose(){jQuery("#login_dialog").dialog("close")}function app_redirect(a){try{if(window.tpai_redirect.redirect(a)){return}}catch(b){}window.location.href=a}function weixin_avatar_resize(a){if(a.length<2){return""}var c=arguments[1]?arguments[1]:132;var b=arguments[2]?arguments[2]:0;var d="/"+b;if(a.substring(a.length-d.length,a.length)==d){return a.substring(0,a.length-d.length)+"/"+c}return a}function weixiao_log(a){if(global.context=="http://weixiao.qq.com"||global.context=="http://ty.weixiao.qq.com"){return false}console.log(a)};