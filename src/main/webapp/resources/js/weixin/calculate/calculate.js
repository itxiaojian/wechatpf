function calculate(){
		var dkje=document.getElementById("dkje").value;
		var dkqx=document.getElementById("dkqx").value;
		var dkll=document.getElementById("dkll").value;
		var ele=document.getElementById("hkfs");
		var options=ele.getElementsByTagName('option');
		var selectedindex;//当前选中的option的下标
		for (var n=0;n<options.length;n++){
		   if(options[n].selected){
		      selectedindex=n;
		   }
		 }
		if(dkje == null || dkje == ""){
			alert('请输入贷款金额');
			document.getElementById("dkje").focus();
			return false;
		}else{
			
			var  isPriceNumber = /^[0-9]*(\.[0-9]{1,2})?$/;
			if(!isPriceNumber.test(dkje))
			{
				  alert('请输入有效金额！');
				  document.getElementById("dkje").focus();
				  return false;
			}
		}
		if((trim(dkqx)=='')||(!isMouth(dkqx))){
			alert("请输入正确贷款期限");
			document.getElementById("dkqx").focus();
			return;
		}
//		if((trim(dkll)=='')||(!isNumber(dkll))){
//			alert("请输入正确贷款利率");
//			document.userdk.dkll.focus();
//			return;
//		}
		var list = [],C =0, B=0, D=0, I = 0, A = 0,totalInt = 0, totalAmt = 0;
		var R=parseFloat(dkll)/1200;
		if(options[selectedindex].text=='等额本金'){
			B = parseFloat(dkje) /parseFloat(dkqx);
			for (var i=0; i< parseFloat(dkqx) ; i++) {
				// Ai = M×（N－i+1）÷N×（R％÷12）
				D = i == 0 ? parseFloat(dkje) : list[ i-1 ][3]; 
				I = D * R;
				totalInt += I;
				totalAmt += B+I;
				list.push( [ B, I, B+I, D-B] );	
			}
		}else{
			C = parseFloat(dkje) * R * Math.pow( 1 + R, parseFloat(dkqx)) / ( Math.pow( 1+R,parseFloat(dkqx)) -1 );
			totalAmt = C * parseFloat(dkqx);
			for(var j=0;j<parseFloat(dkqx);j++){
				D = j==0?parseFloat(dkje):list[ j-1 ][3];
				I = D * R;
				totalInt += I;
				list.push( [ C-I,I,C,D-( C-I )] );
			}
		}
		
		document.getElementById('savcalculator3').style.display="";
		document.getElementById('dkjer').firstChild.nodeValue=dkje;
		document.getElementById('dkqxr').firstChild.nodeValue=dkqx;
		document.getElementById('dkllr').firstChild.nodeValue=dkll;
		document.getElementById('hkfsr').firstChild.nodeValue=options[selectedindex].text;
		document.getElementById('hkzer').firstChild.nodeValue=totalAmt.toFixed(2);
		document.getElementById('lxzer').firstChild.nodeValue=totalInt.toFixed(2);
	}
	function reset2(){
		document.getElementById('savcalculator3').style.display="none";
		document.getElementById("dkje").value="";
		document.getElementById("dkqx").value="";
		document.getElementById("dkll").value="";
		document.getElementById("hkfs").getElementsByTagName('option')[0].selected=true;
		//document.getElementById("hkfs").value;
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
	function isMouth(str) {
		var pattern = /^[0-9]+$/;
		return pattern.test(str);		
	}
	function test() {
		var dkqxValue = document.getElementById("dkqx").value;
		if(dkqxValue != null && dkqxValue != '') {
    		if(dkqxValue <= 6) {
    			document.getElementById("dkll").value=5.60;
        	} else if(6<dkqxValue && dkqxValue<=12) {
        		document.getElementById("dkll").value=6.00;
        	} else if(12<dkqxValue && dkqxValue<=36) {
        		document.getElementById("dkll").value=6.15;
        	} else if(36<dkqxValue && dkqxValue<=60) {
        		document.getElementById("dkll").value=6.40;
        	} else if(dkqxValue > 60) {
        		document.getElementById("dkll").value=6.55;
        	}
    	}
		
	}