//生成历史数据时间轴
function DateData(data){
  
  var Dl = data.length;
  var timeArr=[];
  var nowtime = new Date();
  var MaxMargin =600;
  var Con ="";
  var Focus="";
  var mz=40;
  var FocusM=[];
  
  //if(Dl>8){$('.DateDataN').width(Dl*120+60);}
  if(Dl==0)return false;
  
  var marginL= new spacingFn();
  for(var i=0;i<Dl;i++){
    if(Dl>11){
       var pjz = 790/(Dl-1);
       mz = i*pjz;
    }else{
       var pjz = 796/(Dl-1);
       mz = i*pjz;
    }
    
    if(data[i].focus==1){
      Focus="Pf";
      FocusM.push(i);
      for(var mi=i+1;mi<Dl;mi++){
       if(data[mi].is_merge==0){
        FocusM.push(mi);
        break;   
       }; 
      }

    }else{Focus=""};
    
    
    var rspd_count = "第 "+(Dl-i-1)+" 次修改";
    var time_count = wbtime(data[i].time)+" "+data[i].rspd_count+ "份";
    
    if(i==0){
      var rspd_counts = "最新数据 "+ data[i].rspd_count +'份';
      Con +='<div class="dateP Now_p"><a href="'+data[i].url+'" name="'+rspd_counts+'" class="point '+Focus+'"></a><p>现在</p></div>';
    }else if(i==Dl-1){
      var rspd_count = "首次发布";
      
      if(Dl<11){
       Con+='<div class="dateP history_p Dstart"><a href="javascript:;" name="'+wbtime(data[i].time)+'" class="point '+Focus+'"></a><p>'+rspd_count+'</p></div>';
      }else{
       Con+='<div class="dateP history_p Dstart" style="width:120px;"><a href="javascript:;" name="'+wbtime(data[i].time)+'" class="point '+Focus+'"></a><p style="textAlign:right;">'+rspd_count+'</p></div>';
      }
      
    }else{
      
      if(Dl<11){
       Con+='<div class="dateP history_p" style=" left:'+mz+'px;"><a href="'+data[i].url+'" name="'+time_count+'" class="point '+Focus+'"></a><p>'+rspd_count+'</p></div>';
      }else{
       
       var dn = 'display:none;';
       Focus==""?dn:dn="width:120px;position:absolute;zIndex:999;"; 
       
       Con+='<div class="dateP history_p" style="left:'+mz+'px;"><a href="'+data[i].url+'" name="'+time_count+'" class="point '+Focus+'"></a><p class="Tdp" style="'+dn+'">'+rspd_count+'</p></div>'; 
      }
      
      
    }
    
  }
  $('.DateDataN').append(Con);
  
  var BarL = $('.DateDataN .dateP:eq('+FocusM[0]+') .point').offset().left-$('.DateDataN').offset().left;
  var BarW = $('.DateDataN .dateP:eq('+FocusM[1]+') .point').offset().left-$('.DateDataN').offset().left-BarL;
  $('.topBar').css({left:BarL+'px',width:BarW+'px'});  
  
  function spacingFn(){
  this.outM=0;
  this.Fn=function(m){
    if(m==1){return}
    this.outM = parseInt(this.outM/2);
    m--;
    this.Fn(m); 
  }
  this.onFn=function(Max,m){
    this.outM=Max;
    this.Fn(m);
    return this.outM;
  }
}

var tjj = new Timetjj();
tjj.event('.point');

var tl = $('.DateDataN .Pf').offset().left-3;
var tt = $('.DateDataN .Pf').offset().top-36;

tjj.addimg(tt,tl,$('.DateDataN .Pf').attr('name'),$('.DateDataN .Pf').width());
}
    
//时间处理
function wbtime(t){
  //时间格式2011-12-22 15:10:54 处理
  var ntime = new Date(t.replace(/\-/g,"/"));
  var nowtime = new Date();
  var leftsecond=parseInt((nowtime.getTime()-ntime.getTime())/1000);
  var d=parseInt(leftsecond/3600/24);
  var h=parseInt((leftsecond/3600)%24);
  var m=parseInt((leftsecond/60)%60);
  var s=parseInt(leftsecond%60);
  
  var toTime = new ToTime(t);
  var todayTime = new ToTime(nowtime.format("yyyy-MM-dd hh:mm:ss"));
  var cd = todayTime.d-toTime.d;
  
    return "<span style='color:#999'>"+toTime.y+"."+toTime.m+"."+toTime.d +"  "+toTime.h+":"+toTime.mm+"</span>";
}
//时间输出
function ToTime(timeString) {
  this.y = timeString.substring(0,4);
  this.m = timeString.substring(5,7);
  this.d = timeString.substring(8,10);
  this.h = timeString.substring(11,13);
  this.mm = timeString.substring(14,16);
  this.ss = timeString.substring(17,19);
}

//时间轴提示信息
function Timetjj(ldDiv){
  
  this.event=function(ldDiv){    

      var _this= this;
    $(ldDiv).live("mouseover",function(){
      
       $(".tccTx,.Tdp").hide();
       $('.DateDataN .Pf p').hide();
       
       xsimg = false;
       var tis = $(this);
       var id = tis.attr("id");
       var top= tis.offset().top-36;
       var left= tis.offset().left-3;
       var thiss =$(this);
      
      var uid = $(this).attr("id");
      var texts = $(this).attr("name");
      
      _this.addimg(top,left,texts,$(this).width());
      
      var Td = $(this).parent().find('p');
      if(Td.is(':hidden')){
         $(this).parent().find('p').addClass('Tdp').show();
         $(this).parent().find('.Tdp').css({width:'120px',position:'absolute',zIndex:'999'});
      }
      
    });
    
    $('.DateDataCon').mouseleave(function(){
      
      setTimeout(function(){
        $('.Tdp:not(:last)').hide();
        var bgtjj = new Timetjj();
        var tl = $('.DateDataN .Pf').offset().left-3;
        var tt = $('.DateDataN .Pf').offset().top-36;
        bgtjj.addimg(tt,tl,$('.DateDataN .Pf').attr('name'),$('.DateDataN .Pf').width());
        var Td = $('.DateDataN .Pf').parent().find('p');
        if(Td.is(':hidden')){
           Td.show().css({width:'120px',position:'absolute',zIndex:'999'});
        }
      },400);
      return false;
      
    });
      
    
  }
  
  this.addimg=function(top,left,texts,this_w,max_w){
         
      if(!max_w){max_w=100};
      var imgcom = $('.Tjj');
      var clz=this.removeHTMLTag(texts);
      var tw = this.getByteLen(clz)*16;
      
      var tw2 = tw/2;
      (tw2<19)?tw2=19:tw2;
      
      if(imgcom.length==0){
        var tcon =$('<div class="Tjj" style="width:'+tw+'px;">'+
                 '<div class="tjjcon"></div>'+
                 '<div class="tj"></div>'+
              '</div>');      
         tcon.find('.tjjcon').html(texts);
         $("body").append(tcon);
        
         var obj_h = tcon.height()-15;

         $('.Tjj').css({"top":top-obj_h+"px","left":(left-tw2+this_w/2-5)+"px"});

         $('.tj').css({"left":tw2-2+"px"});
         
      }else{  
         imgcom.find(".tjjcon").html(texts);
         var imgcom = $('.Tjj');
         imgcom.css({"width":tw+"px","left":(left-tw2+this_w/2-5)+"px"});
         
         var obj_h = imgcom.height()-15;
         imgcom.css({"top":top-obj_h+"px"});
         imgcom.find('.tj').css({"left":tw2-2+"px"});
         imgcom.show();
      }
    }
  
  this.getByteLen=function(val) {
      if(!val){val=0}
      var len = 0;
      for (var i = 0; i < val.length; i++) {
        if (val.charAt(i).match(/[^\x00-\xff]/ig) != null) //全角 
          len += 2; //如果是全角，占用两个字节
        else
          len += 1; //半角占用一个字节
      }
      return len/2;
  }
  this.removeHTMLTag=function(str){
            str = str.replace(/<[^>].*?>/g,''); //去除HTML tag
            str = str.replace(/[ | ]*n/g,'n'); //去除行尾空白
            return str;
    }
}
Date.prototype.format = function(format){
var o = {
"M+" : this.getMonth()+1, //month
"d+" : this.getDate(), //day
"h+" : this.getHours(), //hour
"m+" : this.getMinutes(), //minute
"s+" : this.getSeconds(), //second
"q+" : Math.floor((this.getMonth()+3)/3), //quarter
"S" : this.getMilliseconds() //millisecond
}

if(/(y+)/.test(format)) {
format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
}

for(var k in o) {
if(new RegExp("("+ k +")").test(format)) {
format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
}
}
return format;
}