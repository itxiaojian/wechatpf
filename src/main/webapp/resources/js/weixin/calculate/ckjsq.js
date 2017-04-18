//计算天数差的函数
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
    var  aDate,  oDate1,  oDate2,  iDays;  
    aDate  =  sDate1.split("-");
	aDate[1]=parseFloat(aDate[1])-1;
    oDate1  =  new  Date(aDate[0],aDate[1],aDate[2]);    
    aDate  =  sDate2.split("-");
	aDate[1]=parseFloat(aDate[1])-1;
    oDate2  =  new  Date(aDate[0],aDate[1],aDate[2]);  
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数
    return  iDays;  
}
function duibi(a, b) {
    var arr = a.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = b.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes > lktimes) {
        return false;
    }
    else
        return true;

}

function trim(value) {
	if (value.substr(0,1) == " " || value.substr(value.length-1,1) == " ") {
		return false;
	} else {
		return true;
	}		
}
function format(money,precision)
{
	var str = ""+Math.round(money*Math.pow(10,precision));
	while (str.length<=precision) {
		str="0"+str;
	}
	var decpoint=str.length-precision;
	str=str.substring(0,decpoint)+"."+str.substring(decpoint,str.length);
	return str;
}
function isNumber(str) {
	var pattern = /^[0-9\.]+$/;	
	return pattern.test(str);		
}
function calculate(){
	var money=document.getElementById("money1").value;
	var rate=document.getElementById("rate").value;
	if((trim(money)=='')||(!isNumber(money))){
		alert("请输入正确存款金额");
		document.getElementById("money1").focus();
		return;
	}
	if((trim(rate)=='')||(!isNumber(rate))){
		alert("请输入正确存款利率");
		document.getElementById("rate").focus();
		return;
	} 
	  var ele=document.getElementById("comtabselect1");
	  var options=ele.getElementsByTagName('option');
	  var selectedindex;//当前选中的option的下标
	  /* for (n=0;n<options.length;n++){
	      if(options[n].selected){
	         selectedindex=n;
	      }
	    }
	    */
	    selectedindex=ele.selectedIndex;
	  if(options[selectedindex].text=='通知存款'){
/* 			  if(money<50000){
				  alert("必须50000元起存");
				  document.stocksb.money1.focus();
				  return;
			  } */
		  if(document.getElementById("cunrurqi").value==null||document.getElementById("cunrurqi").value==''){

			alert("请输入存入日期");
			return;
		  }
		  if(document.getElementById("tiqurqi").value==null||document.getElementById("tiqurqi").value==''){

			alert("请输入提取日期");
			return;
		  }
		 var days=DateDiff(document.getElementById("cunrurqi").value,document.getElementById("tiqurqi").value);
		 if(!duibi(document.getElementById("cunrurqi").value,document.getElementById("tiqurqi").value)){
			 alert("存入日期大于提取日期，请输入正确时间");
			 return;
		 }
		  var eles=document.getElementById("comtabselect2");
		  var options1=eles.getElementsByTagName('option');
		  var selectedindex1;//当前选中的option的下标
		  /* for (n=0;n<options1.length;n++){
		      if(options1[n].selected){
		         selectedindex1=n;
		      }
		    }
		    */
		     selectedindex1=eles.selectedIndex;
		  if(parseFloat(days)<parseFloat(options1[selectedindex1].value)){	
			  alert("提取日期与存储日期间隔必须大于"+options1[selectedindex1].text+"才能享受通知存款利率");
			  return;
		  }
		  var lixi=format(parseFloat(money)*parseFloat(rate)/36000*(parseFloat(days)+parseFloat(options1[selectedindex1].value)),2);
		  //document.stocksb.lixi.value=lixi;
		  document.getElementById("lixi").firstChild.nodeValue=lixi;
		  document.getElementById("benxi").firstChild.nodeValue=format(parseFloat(money)+parseFloat(lixi),2);
		  //document.stocksb.benxi.value=format(parseFloat(money)+parseFloat(lixi),2);
	  }
	  if(options[selectedindex].text=='活期存款'){
		  if(document.getElementById("cunrurqi").value==null||document.getElementById("cunrurqi").value==''){

			alert("请输入存入日期");
			return;
		  }
		  if(document.getElementById("tiqurqi").value==null||document.getElementById("tiqurqi").value==''){

			alert("请输入提取日期");
			return;
		  }
		  
		  var days=DateDiff(document.getElementById("cunrurqi").value,document.getElementById("tiqurqi").value);
			 if(!duibi(document.getElementById("cunrurqi").value,document.getElementById("tiqurqi").value)){
				 alert("存入日期大于提取时间，请输入正确时间");
				 return;
			 }
		  var lixi=format(parseFloat(money)*parseFloat(rate)/36000*parseFloat(days),2);	
		  document.getElementById("lixi").firstChild.nodeValue=lixi;
		  document.getElementById("benxi").firstChild.nodeValue=format(parseFloat(money)+parseFloat(lixi),2);
	  }
	  if(options[selectedindex].text=='整存整取'){
		  var eles=document.getElementById("comtabselect2");
		  var options1=eles.getElementsByTagName('option');
		  var selectedindex1;//当前选中的option的下标
		   for (n=0;n<options1.length;n++){
		      if(options1[n].selected){
		         selectedindex1=n;
		      }
		    }
		  var lixi=0;
		  if(options1[selectedindex1].text=='三个月'){
			  lixi=format(parseFloat(money)*parseFloat(rate)/400,2);
		  }else if(options1[selectedindex1].text=='半年'){
			  lixi=format(parseFloat(money)*parseFloat(rate)/200,2);
		  }else if(options1[selectedindex1].text=='一年'){
			  lixi=format(parseFloat(money)*parseFloat(rate)/100,2);
		  }else if(options1[selectedindex1].text=='两年'){
			  lixi=format(parseFloat(money)*parseFloat(rate)/50,2);
		  }else if(options1[selectedindex1].text=='三年'){
			  lixi=format(3*parseFloat(money)*parseFloat(rate)/100,2);
		  }else if(options1[selectedindex1].text=='五年'){
			  lixi=format(5*parseFloat(money)*parseFloat(rate)/100,2);
		  }
		  document.getElementById("lixi").firstChild.nodeValue=lixi;
		  document.getElementById("benxi").firstChild.nodeValue=format(parseFloat(money)+parseFloat(lixi),2);
	  }
	  if(options[selectedindex].text=='定活两便'){
		  var eles=document.getElementById("comtabselect2");
		  var options1=eles.getElementsByTagName('option');
		  var selectedindex1;//当前选中的option的下标
		   for (n=0;n<options1.length;n++){
		      if(options1[n].selected){
		         selectedindex1=n;
		      }
		    }
		  var lixi=0;
		  if(options1[selectedindex1].text=='一年'){
			  lixi=format(parseFloat(money)*parseFloat(rate)/100,2);
		  }else if(options1[selectedindex1].text=='三年'){
			  lixi=format(3*parseFloat(money)*parseFloat(rate)/100,2);
		  }else if(options1[selectedindex1].text=='五年'){
			  lixi=format(5*parseFloat(money)*parseFloat(rate)/100,2);
		  }
		  document.getElementById("lixi").firstChild.nodeValue=lixi;
		  document.getElementById("benxi").firstChild.nodeValue=format(parseFloat(money)+parseFloat(lixi),2);
		  
	  }
	  if(options[selectedindex].text=='整存零取'){
		  document.getElementById('mczq').style.display="";
		  var eles=document.getElementById("comtabselect2");
		  var options1=eles.getElementsByTagName('option');
		  var selectedindex1;//当前选中的option的下标
		   for (n=0;n<options1.length;n++){
		      if(options1[n].selected){
		         selectedindex1=n;
		      }
		    }
		  var lixi=0;
		  var nianxian=1;
		  if(options1[selectedindex1].text=='一年'){
			  lixi=format(parseFloat(money)*parseFloat(rate)/100,2);
		  }else if(options1[selectedindex1].text=='三年'){
			  lixi=format(3*parseFloat(money)*parseFloat(rate)/100,2);
			  nianxian=3;
		  }else if(options1[selectedindex1].text=='五年'){
			  lixi=format(5*parseFloat(money)*parseFloat(rate)/100,2);
			  nianxian=5;
		  }
		  document.getElementById("lixi").firstChild.nodeValue=lixi;
		  document.getElementById("benxi").firstChild.nodeValue=format(parseFloat(money)+parseFloat(lixi),2);
			var radio=document.getElementsByName("use_choice");
			var value="";
			  for(var j=0;j<radio.length;j++){
				  if(radio[j].checked==true){
				  	  value=radio[j].value;	 
				  	   break;	
				  	  }
				  }
			 
		  if(value=="yue"){
			 
			  document.getElementById("mczqje").firstChild.nodeValue=format((parseFloat(money)+parseFloat(lixi))/(12*parseFloat(nianxian)),2);
		  }else if(value=="ji"){
			  document.getElementById("mczqje").firstChild.nodeValue=format((parseFloat(money)+parseFloat(lixi))/(4*parseFloat(nianxian)),2);
		  }else if(value=="nian"){
			  document.getElementById("mczqje").firstChild.nodeValue=format((parseFloat(money)+parseFloat(lixi))/(2*parseFloat(nianxian)),2);
		  }
		  
	  }else{
		  document.getElementById('mczq').style.display="none";
	  }
	  if(options[selectedindex].text=='零存整取'){
		  document.getElementById('ljcr').style.display="";
		  var eles=document.getElementById("comtabselect2");
		  var options1=eles.getElementsByTagName('option');
		  var selectedindex1;//当前选中的option的下标
		   for (n=0;n<options1.length;n++){
		      if(options1[n].selected){
		         selectedindex1=n;
		      }
		    }
		  var lixi=0;
		  var ljckje=0;
		  if(options1[selectedindex1].text=='一年'){
			  ljckje=format(parseFloat(money)*12,2);
			  lixi=format(78*parseFloat(money)*parseFloat(rate)/1200,2);
			  
		  }else if(options1[selectedindex1].text=='三年'){
			  ljckje=format(36*parseFloat(money),2);
			  lixi=format(666*parseFloat(money)*parseFloat(rate)/1200,2);
		  }else if(options1[selectedindex1].text=='五年'){
			  ljckje=format(60*parseFloat(money),2);
			  lixi=format(1830*parseFloat(money)*parseFloat(rate)/1200,2);
		  }
		  document.getElementById("ljckje").firstChild.nodeValue=ljckje;
		  document.getElementById("lixi").firstChild.nodeValue=lixi;
		  document.getElementById("benxi").firstChild.nodeValue=format(parseFloat(ljckje)+parseFloat(lixi),2);
	  }else{
		  document.getElementById('ljcr').style.display="none";
	  }
	  document.getElementById('commendtab2').style.display="";
}
	function reset2(){
		document.getElementById('commendtab2').style.display="none";
		document.stocksb.reset();
		change('comtabselect1');
	}
	 function change(id){
		 var cunqi=["三个月","半年","一年","两年","三年","五年"];
		 var cunqi2=["一年","三年","五年"];
	     //得到当前被选中的option
		  var ele=document.getElementById(id);
		  var options=ele.getElementsByTagName('option');
		  var selectedindex;//当前选中的option的下标
		  /* for (n=0;n<options.length;n++){
		      if(options[n].selected){
		         selectedindex=n;
		      }
		    }
		    */
		    selectedindex=ele.selectedIndex;
		   document.getElementById('commendtab2').style.display="none";
		  if(options[selectedindex].text=='通知存款'){
			  document.getElementById('ckye').firstChild.nodeValue="存款金额";
			  document.getElementById('qxck').firstChild.nodeValue="通知类型";
			  var ele1=document.getElementById('comtabselect2');
			  document.getElementById('rqi').style.display="";
			  document.getElementById('pindu').style.display="none";
			  ele1.style.display="";
			  for(var i=(ele1.options.length-1);i>=0;i--){
				  ele1.options[i]=null;
			  }
			  var ops1 = new Option();
			  ops1.text ="一天";
			  ops1.value="1";
			  ele1.options[0]=ops1;
			  var ops2 = new Option();
			  ops2.text ="七天";
			  ops2.value="7";
			  ele1.options[1]=ops2;
		  }else if(options[selectedindex].text=='活期存款'){
			  document.getElementById('ckye').firstChild.nodeValue="存款金额";
			  document.getElementById('qxck').firstChild.nodeValue="存款期限";
			  document.getElementById('rqi').style.display="";
			  document.getElementById('comtabselect2').style.display="none";
			  document.getElementById('pindu').style.display="none";
		  }else if(options[selectedindex].text=='整存整取'){
			  document.getElementById('ckye').firstChild.nodeValue="存款金额";
			  var ele1=document.getElementById('comtabselect2');
			  document.getElementById('rqi').style.display="none";
			  document.getElementById('pindu').style.display="none";
			  ele1.style.display="";
			  for(var i=0;i<cunqi.length;i++){
				  var ops = new Option();
				  ops.text =cunqi[i];
				  ele1.options[i]=ops;
			  }
		  }else if(options[selectedindex].text=='定活两便'){
			  document.getElementById('ckye').firstChild.nodeValue="存款金额";
			  document.getElementById('qxck').firstChild.nodeValue="存款期限";
			  var ele1=document.getElementById('comtabselect2');
			  document.getElementById('rqi').style.display="none";
			  document.getElementById('pindu').style.display="none";
			  ele1.style.display="";
			  for(var i=(ele1.options.length-1);i>=0;i--){
				  ele1.options[i]=null;
			  }
			  for(var i=0;i<cunqi2.length;i++){
				  var ops = new Option();
				  ops.text =cunqi2[i];
				  ele1.options[i]=ops;
			  }
		  }else if(options[selectedindex].text=='整存零取'){
			  document.getElementById('ckye').firstChild.nodeValue="存款金额";
			  document.getElementById('qxck').firstChild.nodeValue="存款期限";
			  var ele1=document.getElementById('comtabselect2');
			  document.getElementById('rqi').style.display="none";
			  ele1.style.display="";
			  document.getElementById('pindu').style.display="";
			  for(var i=(ele1.options.length-1);i>=0;i--){
				  ele1.options[i]=null;
			  }
			  for(var i=0;i<cunqi2.length;i++){
				  var ops = new Option();
				  ops.text =cunqi2[i];
				  ele1.options[i]=ops;
			  }
			  
		  }else if(options[selectedindex].text=='零存整取'){
			  var ele1=document.getElementById('comtabselect2');
			  document.getElementById('pindu').style.display="none";
			  document.getElementById('rqi').style.display="none";
			  ele1.style.display="";
			  document.getElementById('ckye').firstChild.nodeValue="每次存款";
			  document.getElementById('qxck').firstChild.nodeValue="存款期限";
			  for(var i=(ele1.options.length-1);i>=0;i--){
				  ele1.options[i]=null;
			  }
			  for(var i=0;i<cunqi2.length;i++){
				  var ops = new Option();
				  ops.text =cunqi2[i];
				  ele1.options[i]=ops;
			  }
		  }
	 }