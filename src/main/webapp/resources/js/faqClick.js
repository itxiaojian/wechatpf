var lastFaqClick=null;
window.onload=function(){
  var faq=document.getElementById("faq");
  var dls=faq.getElementsByTagName("dl");
  //初始化显示第一个
  if(lastFaqClick == null)
  {
   var dd =dls[0].getElementsByTagName("dd")[0];
   dd.style.display='block';
   var dt=dls[0].getElementsByTagName("dt")[0];
   lastFaqClick = dt;
   dt.getElementsByTagName("p")[0].innerHTML="▲";
   }
   
  for (var i=0,dl;dl=dls[i];i++){
    var dt=dl.getElementsByTagName("dt")[0];//取得标题
     dt.id = "faq_dt_"+(Math.random()*100);
     dt.onclick=function(){
       var p=this.parentNode;//取得父节点
        if (lastFaqClick!=null&&lastFaqClick.id!=this.id){//点其他
          var dds=lastFaqClick.parentNode.getElementsByTagName("dd");
          lastFaqClick.parentNode.getElementsByTagName("p")[0].innerHTML="▼";
          for (var i=0,dd;dd=dds[i];i++)
            dd.style.display='none';
         
        }
        lastFaqClick=this;
        var dds=p.getElementsByTagName("dd");//取得对应子节点，也就是说明部分
        var tmpDisplay='none';
        var flagImg="▼";
        if (gs(dds[0],'display')=='none')
        {
           tmpDisplay='block';
           flagImg="▲";
        }
        p.getElementsByTagName("dt")[0].getElementsByTagName("p")[0].innerHTML=flagImg;
        for (var i=0;i<dds.length;i++)
        {
          dds[i].style.display=tmpDisplay;
        }
      }
  }
}

function gs(d,a){
  if (d.currentStyle){
    var curVal=d.currentStyle[a]
  }else{
    var curVal=document.defaultView.getComputedStyle(d, null)[a]
  }
  return curVal;
}