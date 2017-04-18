//点击标题出现正文
var html='';
var path='';
var ym="http://www.ahbvc.cn";
String.prototype.replaceAll  = function(s1,s2){     
    return this.replace(new RegExp(s1,"gm"),s2);     
};
$(function(){ 
		$.ajax({
			url :path+ '/rss/mainList',
			data : {
				url : $('#links').val(),
			},
			type : "get",
			success : function(data) {
				var rst = eval(data);
				var src ='';
			/*  $("#container_infobody", rst.message).each(	function(index, element) {
					if($(element).find('#infocontent').length != 0){
						$(element).find('#infocontent').each(function(ind1, ele1){
							html+=$(ele1).html();
				   	});
				            }
						});*/
				  $(".content", rst.message).each(function(index, element) {
						if($(element).find('#foot').length != 0){
							$(element).find('#foot').attr("style","display:none");
						}
						if($(element).find('img').length != 0){
							 $(element).find('img').addClass("imgClass");
							 $(element).find('.imgClass').each(function(ind, ele){
								  src = $(ele).attr('src');
								  if(src.substring(0,4)!='http'){
									  src =ym+src;
									}
									  if($(ele).attr('style')=='display:none'){
											$(ele).attr("style","display:none");
										    }else{
											$(ele).removeAttr("src");
											$(ele).attr("src",src);
											$(ele).removeAttr("style");
											$(ele).removeAttr("width");
											$(ele).removeAttr("height");
											if(src.indexOf(".gif")!='-1'){
												$(ele).attr("style","width:6%;margin:auto;");
											  }
										}
							});
						}
						if($(element).find('a').length != 0){
							 $(element).find('a').addClass("aClass");
							 $(element).find('.aClass').each(function(ind, ele){
								 var  href = $(ele).attr('href');
								if(href !='' && typeof(href)!= 'undefined' && href !=null){
								if(href.substring(0,4)!='http'){
									href =ym+href;
								  }
								 }
									$(ele).removeAttr("href");
									$(ele).attr("href",href);
							});
						}
						if($(element).find('embed').length != 0){
							 $(element).find('embed').addClass("embedClass");
							 $(element).find('.embedClass').each(function(ind, ele){
								 var  embed = $(ele).attr('src');
									if(embed.substring(0,4)!='http'){
										embed =ym+embed;
									}
										$(ele).removeAttr("src");
										$(ele).attr("src",embed);
										$(ele).removeAttr("width");
										$(ele).attr("width","100%");
										$(ele).removeAttr("height");
										$(ele).attr("height","100%");
										 
							});
						}
						if($(element).find('table').length != 0){
							 $(element).find('table').addClass("tableClass");
							 $(element).find('.tableClass').each(function(ind, ele){
								 $(ele).attr("width",'98%');
							     $(ele).attr("cellpadding",'0');
								 $(ele).attr("cellspacing",'0');
							});
						}
						$(element).find("p").removeAttr("style");
						$(element).find("p").removeAttr("class");
						$(element).find("p").find("span").removeAttr("style");
						$(element).find("p").find("font").removeAttr("size");
						$(element).find("p").find("font").removeAttr("face");
						$(element).find("tr").removeAttr("style");
						$(element).find("td").removeAttr("style");
						$(element).find("table").removeAttr("style");
						$(element).find("table").removeAttr("class");
						$(element).find("table").attr("border","1");
						//p = /\<.*?\>/g;
						p=/<\/?span[^>]*>/ig;
				        html+=$(element).html().replace(p,"");
						});
				$("#main").append(html);//绑定数据到table 
			},
			error : function() {
				//alert("error");
			}
		});
});