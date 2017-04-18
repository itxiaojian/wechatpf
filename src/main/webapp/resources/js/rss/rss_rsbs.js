//点击标题出现正文
var html='';
var path='';
$(function(){ 
		$.ajax({
			url :path+ '/rss/mainList',
			data : {
				url : $('#links').val(),
			},
			type : "get",
			success : function(data) {
				var rst = eval(data);
			  $("table", rst.message).find('p').each(
						function(index, element) {
							console.info(element);
							if($(this).find('img').length !=0){ 
								var c = $(this).find('img')[0].src;
							    var f= c.substring( c.indexOf('/')).substring(c.substring( c.indexOf('/')).indexOf('/')+2);
							    var g = f.indexOf('/');
							    var d = f.substring(g+1);
							html+=" <p align='center'><img border='0' alt='' src=  'http://www.hsu.edu.cn/" +d +"'/></p>";
							console.info("html"+html);
							}else{
								html+="<p>"+$(this).text()+"</p><br/>";
							}
						});
				$("#main").append(html);//绑定数据到table 
			},
			error : function() {
				//alert("error");
			}
		});
});