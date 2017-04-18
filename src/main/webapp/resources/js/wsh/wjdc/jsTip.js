function JsTip(){
				this.Tinfo = {};
		  }
		  JsTip.prototype ={
			  
			     show:function(Obj){

					 var Dx = Obj.obj || $('body');
					 					 
					 Dx.css({'position':'relative'});
					 
					 switch(Obj.type || "top"){
						 case "top":
							 var MaT = parseInt(Dx.css('paddingTop'));
							 var MaB = parseInt(Dx.css('paddingBottom'));
							 var Hc =  Dx.height();
							 var Tops = MaT+MaB+Hc+12;
							 var Lefts = 0;
							 var types = "T";
						 break;
						 case "left":
							 var Wc =  Dx.width();
							 var Tops = 0;
							 var Lefts = Wc+10;
							 var types = "L";
						 break;
						 case "bottom":
							 var Hc =  Dx.height();
							 var Tops = -10;
							 var Lefts = 0;
						     var types = "B";  
						 break;
						 case "right":
							 var Tops = 0;
							 var Lefts = -10;
							 var types = "R";
						 break;
					 }
					 
					 
					 this.Tinfo = {
						 obj:Dx,
						 data:Obj.data || "无内容！", //内容
						 type:types, //类型
						 width:Obj.width || "auto",
						 Close:Obj.Close || false, //删除按钮
						 BoColor:Obj.BoColor || "#FEBE8F",   //边框颜色
						 BaColor:Obj.BaColor || "#FFF8D9",  //背景颜色
						 TColor:Obj.TColor || "#FF6600", //文字颜色
						 top:Tops + (Obj.pytop || 0), //Y坐标
						 left:Lefts + (Obj.pyleft || 0) //X坐标
				     };
					 
					 //alert(Tinfo.top+"<< >>"+Tinfo.left);
					 this.addCon(this.Tinfo); 
				 },
				 addCon:function(Tinfo){
					 
					 var _this = this;
					 switch(Tinfo.type){
						 
						 case "T":
							 var styleT = "border-color:transparent transparent "+Tinfo.BaColor;
							 var styleB = "border-color:transparent transparent "+Tinfo.BoColor;
						 break;
						 case "L":
							 var styleT = "border-color:transparent "+Tinfo.BaColor+" transparent transparent ";
							 var styleB = "border-color:transparent "+Tinfo.BoColor+" transparent transparent ";  
						 break;
						 case "B":
						     var styleT = "border-color:"+Tinfo.BaColor+" transparent transparent transparent";
							 var styleB = "border-color:"+Tinfo.BoColor+" transparent transparent transparent";  
						 break;
						 case "R":
						     var styleT = "border-color:transparent transparent transparent "+Tinfo.BaColor;
							 var styleB = "border-color:transparent transparent transparent "+Tinfo.BoColor;
						 break;
					 }
                     
	                 this.Tinfo.id = parseInt(100*Math.random()); //随机ID

					 var Close ={};
					 var Cpar = 0;
					 
					 if(Tinfo.Close){
					     $Close = $('<a href="javascript:;" style="color:'+Tinfo.TColor+';" class="jsTip_close">X</a>');
						 Cpar = 20;
					 }
					 
					 var $con = $('<div id="'+this.Tinfo.id+'" class="jsTip_s" style="width:'+Tinfo.width+';left:'+Tinfo.left+'px; top:'+Tinfo.top+'px;">'+
								   '<div class="arrow'+Tinfo.type+'"><div class="arrowt" style="'+styleT+'"></div><div class="arrowb" style="'+styleB+'"></div></div>'+
								   '<div class="tipCon" style="background:'+Tinfo.BaColor+'; border-color:'+Tinfo.BoColor+'; padding-right:'+Cpar+'px;">'+
										'<div class="tipCon_t" style="color:'+Tinfo.TColor+';">'+Tinfo.data+'</div>'+
								   '</div>'+
								   //Close+
							 '</div>');
					 this.con = $con;
					 Tinfo.obj.append($con);//输出内容
					 
					 
					 //删除事件
					 if(Tinfo.Close){
						 
                         $con.append($Close);
						 
						 var _this = this;
						 $Close.bind('click',function(){
							 $con.remove();
						 });
						 
					 }
					 
					 //高度处理
					 if(Tinfo.type == "B"){
						var h = Tinfo.top - $("#"+this.Tinfo.id).height();
					    $("#"+this.Tinfo.id).css({top:h+"px"});
					 }
					 //宽度处理
					 if(Tinfo.type == "R"){
						var w = Tinfo.left - $("#"+this.Tinfo.id).width();
					    $("#"+this.Tinfo.id).css({left:w+"px"});
					 }
					 
					 		 
				 },
				 del:function(){
				 	this.con.remove();
				 }
		  }