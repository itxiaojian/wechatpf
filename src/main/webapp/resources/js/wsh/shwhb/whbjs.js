	var sum=0;
	function hbfb(id,url){
		if(confirm("发布后不可在进行编辑，是否提交发布？")){
			$.ajax({
				url: "whbZtfb",
				type : 'post',
				dataType : 'json',
				data: {id:id},
				success: function(data){
					if(data == '1'){
						alert("发布成功！");
						window.self.location=url;
					}else{
						alert("连接数据库出错，请联系管理员！");
					}
				},
				error: function(XMLHttpRequest){
						alert("网络错误");
				}
			});
		}
	}
	function updateYm(lx,bgtp){
		var text1=$('#text1').val();
		var hbid=$('#hbid').val();
		var zw="";
		if(lx==2||lx==3||lx==4){
			var text0=$('#text0').val();
			zw =""+text0+"<br/><br/><br/>"+text1+"<br/>";
		}else if(lx==5||lx==8){
			var text0=$('#text0').val();
			zw =""+text0+"<br/>"+text1+"";
		}else if(lx==6||lx==7||lx==9||lx==10||lx==11||lx==12){
			zw =""+text1+"";
		}
		$.ajax({
			url: "updateYm",
			type : 'post',
			dataType : 'json',
			data: {hbid:hbid,bgtp:bgtp,zw:zw,zt:lx},
			success: function(data){
				if(data == '1'){
					alert("页面保存成功！");
				}else{
					alert("连接数据库出错，请联系管理员！");
				}
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
	}
	function ggxssj(lx){
		var text1=$('#text1').val();
		var xgylp= document.getElementById("xgylp");
		if(lx==2||lx==3||lx==4){
			var text0=$('#text0').val();
			xgylp.innerHTML =""+text0+"<br/><br/><br/>"+text1+"<br/>";
		}else if(lx==5||lx==8){
			var text0=$('#text0').val();
			xgylp.innerHTML =""+text0+"<br/>"+text1+"";
		}else if(lx==6||lx==7||lx==9||lx==10||lx==11||lx==12){
			xgylp.innerHTML =""+text1+"";
		}
	}
	function deleteData(xh,lx){
		if(confirm("您确定要删除吗？")){
			if((xh+1)==sum){
				var xgyltp = $("#xgyltp");
				xgyltp.remove();
				var del = $("#del");
				del.remove();
				var editForm = $("#editForm");
				editForm.remove();
				var saveData = $("#saveData");
				saveData.remove();
				var notifyPage= document.getElementById("notifyPage");
				notifyPage.setAttribute("class",'notify_create');
				notifyPage.innerHTML ="点击上方加号添加新页面";
				var page = $("#page"+xh);
				page.remove();
				var hbid=$('#hbid').val();
				$.ajax({
					url: "deleteYm",
					type : 'post',
					dataType : 'json',
					data: {hbid:hbid,zt:lx},
					success: function(data){
						if(data == '1'){
							
						}else{
							alert("连接数据库出错，请联系管理员！");
						}
					},
					error: function(XMLHttpRequest){
							alert("网络错误");
					}
				});
				sum--;
			}else{
				alert("请删除最新添加的页面！");
			}
		}
	}
	function clickPage(sx,lx,bgtp){
		var xgyltp = $("#xgyltp");
		xgyltp.remove();
		var xgyltp = $("#xgyltp");
		xgyltp.remove();
		var del = $("#del");
		del.remove();
		var editForm = $("#editForm");
		editForm.remove();
		var saveData = $("#saveData");
		saveData.remove();
		var div=document.getElementById("page"+sx);
		var flag = true;
		if (flag) {
			for(var i=0;i<sum;i++){
				if(i==sx){
					div.setAttribute("class",'box box_active');
				}else{
					var divs = document.getElementById("page"+i);
					divs.setAttribute("class",'box');
				}
			}
		} else {
			div.setAttribute("class",'box');
		}
		flag = !flag;
		if(lx==1){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
		}else if(lx==2){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_large white_space fade_from_down');
			newNode4.innerHTML ="<br/><br/>微校大学校园十大歌手<br/><br/><br/>科技大礼堂      2014.12.7日晚<br/>";
			newNode4.style.color="red";
			newNode4.style.marginTop="121%";
			newNode4.style.height="182px";
			newNode4.style.background="white none repeat scroll 0 0";
			newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section2_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section2_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="科技大礼堂     2014.12.7日晚";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'微校大学校园十大歌手');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==3){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_large white_space fade_from_down');
			newNode4.innerHTML ="<br/><br/>微校大学校园十大歌手<br/><br/><br/>科技大礼堂       2014.12.7日晚<br/>";
			newNode4.style.color="white";
			newNode4.style.marginTop="121%";
			newNode4.style.height="182px";
			newNode4.style.background="red none repeat scroll 0 0";
			newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section2_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section2_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="科技大礼堂     2014.12.7日晚";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'微校大学校园十大歌手');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==4){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_large white_space fade_from_down');
			newNode4.innerHTML ="<br/><br/><br/>唱出你心中最美的声音<br/><br/>4位导师，12名学员现场角逐<br/><br/>";
			newNode4.style.background="rgba(255, 255, 255, 0.8) none repeat scroll 0 0";
			newNode4.style.color="red";
			newNode4.style.marginTop="65%";
			newNode4.style.height="150px";
			newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section4_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section4_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="4位导师，12名学员现场角逐";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'唱出你心中最美的声音');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==5){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.style.background="rgba(24, 18, 18, 0.8) none repeat scroll 0 0";
			newNode2.style.height="100%";
			newNode2.style.width="100%";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle fade_from_down');
			newNode4.innerHTML ="本次比赛赛制介绍<br/>第一轮：歌曲串烧。按照选手编号的顺序依次上场，演唱形式为歌曲串烧。每位选手的演唱时长在1.5分钟以内，演唱内容为自选歌曲的副歌高潮部分，主办方可以代为切歌。" +
					"第二轮：由现场给定的三个关键字，参赛选手必须以此关键字适当发挥进行若干时长的歌曲清唱。在本轮所有选手的比赛结束后，由专业评委现场打分。  " +
					"第三轮：巅峰之战。由第二轮决出的4位选手和复活赛决出的2位选手，共计6位选手进行第四轮的比赛。";
			newNode4.style.color="white";
			newNode4.style.marginTop="60%";
			newNode4.style.fontWeight="bolder";
			newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section5_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section5_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="第一轮：歌曲串烧。按照选手编号的顺序依次上场，演唱形式为歌曲串烧。每位选手的演唱时长在1.5分钟以内，演唱内容为自选歌曲的副歌高潮部分，主办方可以代为切歌。" +
					"第二轮：由现场给定的三个关键字，参赛选手必须以此关键字适当发挥进行若干时长的歌曲清唱。在本轮所有选手的比赛结束后，由专业评委现场打分。  " +
					"第三轮：巅峰之战。由第二轮决出的4位选手和复活赛决出的2位选手，共计6位选手进行第四轮的比赛。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==6){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.style.background="rgba(24, 18, 18, 0.8) none repeat scroll 0 0";
			newNode2.style.height="85px";
			newNode2.style.width="60%";
			newNode2.style.bottom="0";
			newNode2.style.right="0";
			newNode2.style.top="0";
			newNode2.style.color="white";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("h1");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'h1_title font_xlarge fade');
			newNode4.innerHTML ="第十四届校园科技文化展";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section6_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section6_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="第十四届校园科技文化展";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==7){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text fade_from_down');
			newNode2.style.background="rgba(24, 18, 18, 0.8) none repeat scroll 0 0";
			newNode2.style.height="80%";
			newNode2.style.width="80%";
			newNode2.style.bottom="0";
			newNode2.style.right="32px";
			newNode2.style.left="32px";
			newNode2.style.top="0";
			newNode2.style.color="white";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="30px 20px";
			newNode4.style.fontSize="14px";
			newNode4.style.lineHeight="2";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="本次比赛共有来自不同学院的32件作品参赛。经过初赛和复赛选拔后成功晋级的决赛的作品有 19 件，共分为A,B两类。" +
					"A类以“创意设计伴我行”为主题的富有创意的实体作品，B类以“创意策划我最行”为主题有关校园文明建设的策划案。鼓励同学们积极参加类似活动，养成长动手、爱思考、善总结的好习惯，努力学习专业知识，提高个人能力。";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section7_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section7_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="本次比赛共有来自不同学院的32件作品参赛。经过初赛和复赛选拔后成功晋级的决赛的作品有 19 件，共分为A,B两类。" +
					"A类以“创意设计伴我行”为主题的富有创意的实体作品，B类以“创意策划我最行”为主题有关校园文明建设的策划案。鼓励同学们积极参加类似活动，养成长动手、爱思考、善总结的好习惯，努力学习专业知识，提高个人能力。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==8){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.style.background="rgba(255, 255, 255, 0.8) none repeat scroll 0 0";
			newNode2.style.height="100%";
			newNode2.style.width="100%";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle fade_from_down');
			newNode4.style.marginTop="160px";
			newNode4.style.lineHeight="1.8";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="本次比赛赛制介绍<br/>第一轮：歌曲串烧。按照选手编号的顺序依次上场，演唱形式为歌曲串烧。每位选手的演唱时长在1.5分钟以内，演唱内容为自选歌曲的副歌高潮部分，主办方可以代为切歌。" +
					"第二轮：由现场给定的三个关键字，参赛选手必须以此关键字适当发挥进行若干时长的歌曲清唱。在本轮所有选手的比赛结束后，由专业评委现场打分。  " +
					"第三轮：巅峰之战。由第二轮决出的4位选手和复活赛决出的2位选手，共计6位选手进行第四轮的比赛。";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section8_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section8_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="第一轮：歌曲串烧。按照选手编号的顺序依次上场，演唱形式为歌曲串烧。每位选手的演唱时长在1.5分钟以内，演唱内容为自选歌曲的副歌高潮部分，主办方可以代为切歌。" +
					"第二轮：由现场给定的三个关键字，参赛选手必须以此关键字适当发挥进行若干时长的歌曲清唱。在本轮所有选手的比赛结束后，由专业评委现场打分。  " +
					"第三轮：巅峰之战。由第二轮决出的4位选手和复活赛决出的2位选手，共计6位选手进行第四轮的比赛。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==9){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text fade_from_down');
			newNode2.style.background="rgba(255, 255, 255, 0.8) none repeat scroll 0 0";
			newNode2.style.height="80%";
			newNode2.style.width="80%";
			newNode2.style.bottom="0";
			newNode2.style.right="32px";
			newNode2.style.left="32px";
			newNode2.style.top="0";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="30px 20px";
			newNode4.style.fontSize="14px";
			newNode4.style.lineHeight="2";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="本次比赛共有来自不同学院的32件作品参赛。经过初赛和复赛选拔后成功晋级的决赛的作品有 19 件，共分为A,B两类。" +
					"A类以“创意设计伴我行”为主题的富有创意的实体作品，B类以“创意策划我最行”为主题有关校园文明建设的策划案。鼓励同学们积极参加类似活动，养成长动手、爱思考、善总结的好习惯，努力学习专业知识，提高个人能力。";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section9_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section9_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="本次比赛共有来自不同学院的32件作品参赛。经过初赛和复赛选拔后成功晋级的决赛的作品有 19 件，共分为A,B两类。" +
					"A类以“创意设计伴我行”为主题的富有创意的实体作品，B类以“创意策划我最行”为主题有关校园文明建设的策划案。鼓励同学们积极参加类似活动，养成长动手、爱思考、善总结的好习惯，努力学习专业知识，提高个人能力。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==10){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text fade_from_down');
			newNode2.style.background="rgba(255, 255, 255, 0.8) none repeat scroll 0 0";
			newNode2.style.height="90px";
			newNode2.style.width="100%";
			newNode2.style.bottom="75px";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="24px 20px";
			newNode4.style.fontSize="14px";
			newNode4.style.lineHeight="25px";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="#午安，路途#时间是最好的布景，而我将是他生命里最炫的主演，谁也无可替代。";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section11_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section11_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="#午安，路途#时间是最好的布景，而我将是他生命里最炫的主演，谁也无可替代。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==11){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text fade_from_down');
			newNode2.style.background="rgba(0, 0, 0, 0) url('../../resources/js/wsh/shwhb/img_section12.png') repeat scroll 0 0 / cover ";
			newNode2.style.height="155.6px";
			newNode2.style.width="100%";
			newNode2.style.bottom="75px";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="36px 20px";
			newNode4.style.fontSize="14px";
			newNode4.style.lineHeight="25px";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="#晚安，落叶# 曾经有许多地方，许多气味，是不可以回忆的。一挨着就会有隐痛，会发怔。而如今却像隔着玻璃匣子看它们，觉得有情，依然是美的。";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section12_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section12_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="#晚安，落叶# 曾经有许多地方，许多气味，是不可以回忆的。一挨着就会有隐痛，会发怔。而如今却像隔着玻璃匣子看它们，觉得有情，依然是美的。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==12){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.style.background="rgba(24, 18, 18, 0.5) none repeat scroll 0 0";
			newNode2.style.height="100%";
			newNode2.style.width="100%";
			newNode2.style.color="white";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild01 = document.getElementById("lish");
			var newNode04 = document.createElement("a");
			newNode04.setAttribute('id','div_share');
			newNode04.setAttribute("class",'div_share font_large');
			newNode04.style.border="2px solid white";
			newNode04.style.borderRadius="80px";
			newNode04.style.bottom="90px";
			newNode04.style.width="177.5px";
			
			newNode04.style.color="white";
			newNode04.style.fontWeight="bolder";
			newNode04.style.left="0";
			newNode04.style.margin="0 auto";
			newNode04.style.padding="20px 8px";
			newNode04.style.right="0";
			newNode04.style.position="absolute";
			newNode04.style.textAlign="center";
			newNode04.innerHTML ="分享给朋友";
			divtext.insertBefore(newNode04,refChild01);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("div_share");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="105px 40px 0";
			newNode4.style.fontSize="18px";
			newNode4.style.lineHeight="1.4";
			newNode4.style.textAlign="center";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="回首，留不住岁月 凝眸，牵不住时光 不要忘记在认清所有真相后依然热爱生活";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section13_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section13_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="回首，留不住岁月 凝眸，牵不住时光 不要忘记在认清所有真相后依然热爱生活";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sx+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}
	}
	function addModel(){
		var size=12;
		var num=0;
		var lx=0;
		var hbid=$('#hbid').val();
		var bgtp="";
		for(var i=1;i<=size;i++){
			var div= document.getElementById("click_cancel"+i);
			var a=div.style.display;
			if(a=='block'){
				num=num+1;
				bgtp=$("#click_bgtp"+i).val();
				lx=i;
			}
		}
		if(num==0){
			alert('请选择模板!');
			return false;
		}
		if(sum>0){
			for(var i=0;i<sum;i++){
				var page = document.getElementById("page"+i);
				page.setAttribute("class",'box');
			}
		}
		var oTeset = document.getElementById("page_choose");
		var refChild = document.getElementById("add_page");
		var newNode = document.createElement("div");
		newNode.setAttribute("class",'box box_active');
		newNode.setAttribute('id','page'+sum);
		newNode.setAttribute('onclick','clickPage('+sum+','+lx+',"'+bgtp+'")');
		newNode.innerHTML ="页面"+sum;
		//oTest.appendChild(newNode);
		oTeset.insertBefore(newNode,refChild); // 这两种方法均可实现
		$("#divHidden").hide();
		if(sum!=0){
// 			var xgylp = $("#xgylp");
// 			xgylp.remove();
// 			var lish = $("#lish");
// 			lish.remove();
// 			var xgyltext = $("#xgyltext");
// 			xgyltext.remove();
			var xgyltp = $("#xgyltp");
			xgyltp.remove();
		}
		if(lx==1){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
		}else if(lx==2){
			//页面预览区
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_large white_space fade_from_down');
			newNode4.innerHTML ="<br/><br/>微校大学校园十大歌手<br/><br/><br/>科技大礼堂       2014.12.7日晚<br/>";
			newNode4.style.color="red";
			newNode4.style.marginTop="121%";
			newNode4.style.height="182px";
			newNode4.style.background="white none repeat scroll 0 0";
			newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section2_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section2_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="科技大礼堂     2014.12.7日晚";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'微校大学校园十大歌手');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
			
		}else if(lx==3){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_large white_space fade_from_down');
			newNode4.innerHTML ="<br/><br/>微校大学校园十大歌手<br/><br/><br/>科技大礼堂     2014.12.7日晚<br/>";
			newNode4.style.color="white";
			newNode4.style.marginTop="121%";
			newNode4.style.height="182px";
			newNode4.style.background="red none repeat scroll 0 0";
			newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section2_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section2_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="科技大礼堂     2014.12.7日晚";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'微校大学校园十大歌手');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==4){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_large white_space fade_from_down');
			newNode4.innerHTML ="<br/><br/><br/>唱出你心中最美的声音<br/><br/>4位导师，12名学员现场角逐<br/><br/>";
			newNode4.style.background="rgba(255, 255, 255, 0.8) none repeat scroll 0 0";
			newNode4.style.color="red";
			newNode4.style.marginTop="65%";
			newNode4.style.height="150px";
			newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section4_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section4_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="4位导师，12名学员现场角逐";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'唱出你心中最美的声音');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==5){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.style.background="rgba(24, 18, 18, 0.8) none repeat scroll 0 0";
			newNode2.style.height="100%";
			newNode2.style.width="100%";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle fade_from_down');
			newNode4.innerHTML ="本次比赛赛制介绍<br/>第一轮：歌曲串烧。按照选手编号的顺序依次上场，演唱形式为歌曲串烧。每位选手的演唱时长在1.5分钟以内，演唱内容为自选歌曲的副歌高潮部分，主办方可以代为切歌。" +
					"第二轮：由现场给定的三个关键字，参赛选手必须以此关键字适当发挥进行若干时长的歌曲清唱。在本轮所有选手的比赛结束后，由专业评委现场打分。  " +
					"第三轮：巅峰之战。由第二轮决出的4位选手和复活赛决出的2位选手，共计6位选手进行第四轮的比赛。";
			newNode4.style.color="white";
			newNode4.style.marginTop="60%";
			newNode4.style.fontWeight="bolder";
			newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section5_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section5_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="第一轮：歌曲串烧。按照选手编号的顺序依次上场，演唱形式为歌曲串烧。每位选手的演唱时长在1.5分钟以内，演唱内容为自选歌曲的副歌高潮部分，主办方可以代为切歌。" +
					"第二轮：由现场给定的三个关键字，参赛选手必须以此关键字适当发挥进行若干时长的歌曲清唱。在本轮所有选手的比赛结束后，由专业评委现场打分。  " +
					"第三轮：巅峰之战。由第二轮决出的4位选手和复活赛决出的2位选手，共计6位选手进行第四轮的比赛。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==6){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.style.background="rgba(24, 18, 18, 0.8) none repeat scroll 0 0";
			newNode2.style.height="85px";
			newNode2.style.width="60%";
			newNode2.style.bottom="0";
			newNode2.style.right="0";
			newNode2.style.top="0";
			newNode2.style.color="white";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("h1");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'h1_title font_xlarge fade');
			newNode4.innerHTML ="第十四届校园科技文化展";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section6_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section6_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="第十四届校园科技文化展";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==7){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text fade_from_down');
			newNode2.style.background="rgba(24, 18, 18, 0.8) none repeat scroll 0 0";
			newNode2.style.height="80%";
			newNode2.style.width="80%";
			newNode2.style.bottom="0";
			newNode2.style.right="32px";
			newNode2.style.left="32px";
			newNode2.style.top="0";
			newNode2.style.color="white";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="30px 20px";
			newNode4.style.fontSize="14px";
			newNode4.style.lineHeight="2";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="本次比赛共有来自不同学院的32件作品参赛。经过初赛和复赛选拔后成功晋级的决赛的作品有 19 件，共分为A,B两类。" +
					"A类以“创意设计伴我行”为主题的富有创意的实体作品，B类以“创意策划我最行”为主题有关校园文明建设的策划案。鼓励同学们积极参加类似活动，养成长动手、爱思考、善总结的好习惯，努力学习专业知识，提高个人能力。";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section7_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section7_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="本次比赛共有来自不同学院的32件作品参赛。经过初赛和复赛选拔后成功晋级的决赛的作品有 19 件，共分为A,B两类。" +
					"A类以“创意设计伴我行”为主题的富有创意的实体作品，B类以“创意策划我最行”为主题有关校园文明建设的策划案。鼓励同学们积极参加类似活动，养成长动手、爱思考、善总结的好习惯，努力学习专业知识，提高个人能力。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==8){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.style.background="rgba(255, 255, 255, 0.8) none repeat scroll 0 0";
			newNode2.style.height="100%";
			newNode2.style.width="100%";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle fade_from_down');
			newNode4.style.marginTop="160px";
			newNode4.style.lineHeight="1.8";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="本次比赛赛制介绍<br/>第一轮：歌曲串烧。按照选手编号的顺序依次上场，演唱形式为歌曲串烧。每位选手的演唱时长在1.5分钟以内，演唱内容为自选歌曲的副歌高潮部分，主办方可以代为切歌。" +
					"第二轮：由现场给定的三个关键字，参赛选手必须以此关键字适当发挥进行若干时长的歌曲清唱。在本轮所有选手的比赛结束后，由专业评委现场打分。  " +
					"第三轮：巅峰之战。由第二轮决出的4位选手和复活赛决出的2位选手，共计6位选手进行第四轮的比赛。";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section8_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section8_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="第一轮：歌曲串烧。按照选手编号的顺序依次上场，演唱形式为歌曲串烧。每位选手的演唱时长在1.5分钟以内，演唱内容为自选歌曲的副歌高潮部分，主办方可以代为切歌。" +
					"第二轮：由现场给定的三个关键字，参赛选手必须以此关键字适当发挥进行若干时长的歌曲清唱。在本轮所有选手的比赛结束后，由专业评委现场打分。  " +
					"第三轮：巅峰之战。由第二轮决出的4位选手和复活赛决出的2位选手，共计6位选手进行第四轮的比赛。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj('+lx+')');
			divText0.insertBefore(newNode23,null);
			
			var refChild9 = document.getElementById("editText0");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==9){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text fade_from_down');
			newNode2.style.background="rgba(255, 255, 255, 0.8) none repeat scroll 0 0";
			newNode2.style.height="80%";
			newNode2.style.width="80%";
			newNode2.style.bottom="0";
			newNode2.style.right="32px";
			newNode2.style.left="32px";
			newNode2.style.top="0";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="30px 20px";
			newNode4.style.fontSize="14px";
			newNode4.style.lineHeight="2";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="本次比赛共有来自不同学院的32件作品参赛。经过初赛和复赛选拔后成功晋级的决赛的作品有 19 件，共分为A,B两类。" +
					"A类以“创意设计伴我行”为主题的富有创意的实体作品，B类以“创意策划我最行”为主题有关校园文明建设的策划案。鼓励同学们积极参加类似活动，养成长动手、爱思考、善总结的好习惯，努力学习专业知识，提高个人能力。";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section9_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section9_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="本次比赛共有来自不同学院的32件作品参赛。经过初赛和复赛选拔后成功晋级的决赛的作品有 19 件，共分为A,B两类。" +
					"A类以“创意设计伴我行”为主题的富有创意的实体作品，B类以“创意策划我最行”为主题有关校园文明建设的策划案。鼓励同学们积极参加类似活动，养成长动手、爱思考、善总结的好习惯，努力学习专业知识，提高个人能力。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==10){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text fade_from_down');
			newNode2.style.background="rgba(255, 255, 255, 0.8) none repeat scroll 0 0";
			newNode2.style.height="90px";
			newNode2.style.width="100%";
			newNode2.style.bottom="75px";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="24px 20px";
			newNode4.style.fontSize="14px";
			newNode4.style.lineHeight="25px";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="#午安，路途#时间是最好的布景，而我将是他生命里最炫的主演，谁也无可替代。";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section11_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section11_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="#午安，路途#时间是最好的布景，而我将是他生命里最炫的主演，谁也无可替代。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==11){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text fade_from_down');
			newNode2.style.background="rgba(0, 0, 0, 0) url('../../resources/js/wsh/shwhb/img_section12.png') repeat scroll 0 0 / cover ";
			newNode2.style.height="155.6px";
			newNode2.style.width="100%";
			newNode2.style.bottom="75px";
			newNode2.style.margin="auto";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("lish");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="36px 20px";
			newNode4.style.fontSize="14px";
			newNode4.style.lineHeight="25px";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="#晚安，落叶# 曾经有许多地方，许多气味，是不可以回忆的。一挨着就会有隐痛，会发怔。而如今却像隔着玻璃匣子看它们，觉得有情，依然是美的。";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section12_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section12_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="#晚安，落叶# 曾经有许多地方，许多气味，是不可以回忆的。一挨着就会有隐痛，会发怔。而如今却像隔着玻璃匣子看它们，觉得有情，依然是美的。";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}else if(lx==12){
			var bg= document.getElementById("xgyl");
			bg.setAttribute("class",'section-drag phone-page');
			bg.style.display="block";
			var newNode1 = document.createElement("section");
			newNode1.style.backgroundImage="url('"+bgtp+"')";
			newNode1.setAttribute('id','xgyltp');
			newNode1.setAttribute("class",'full_drag section_drag');
			bg.insertBefore(newNode1,null);
			
			var divtext= document.getElementById("xgyltp");
			var newNode2 = document.createElement("div");
			newNode2.setAttribute('id','xgyltext');
			newNode2.setAttribute("class",'div_text');
			newNode2.style.background="rgba(24, 18, 18, 0.5) none repeat scroll 0 0";
			newNode2.style.height="100%";
			newNode2.style.width="100%";
			newNode2.style.color="white";
			newNode2.style.position="absolute";
			newNode2.innerHTML ="";
			divtext.insertBefore(newNode2,null);
			
			var divtext= document.getElementById("xgyltext");
			var newNode3 = document.createElement("li");
			newNode3.setAttribute('id','lish');
			newNode3.setAttribute("class",'iconcommon slide');
			newNode3.style.display="block";
			divtext.insertBefore(newNode3,null);
			
			var divtext= document.getElementById("xgyltext");
			var refChild01 = document.getElementById("lish");
			var newNode04 = document.createElement("a");
			newNode04.setAttribute('id','div_share');
			newNode04.setAttribute("class",'div_share font_large');
			newNode04.style.border="2px solid white";
			newNode04.style.borderRadius="80px";
			newNode04.style.bottom="90px";
			newNode04.style.width="177.5px";
			
			newNode04.style.color="white";
			newNode04.style.fontWeight="bolder";
			newNode04.style.left="0";
			newNode04.style.margin="0 auto";
			newNode04.style.padding="20px 8px";
			newNode04.style.right="0";
			newNode04.style.position="absolute";
			newNode04.style.textAlign="center";
			newNode04.innerHTML ="分享给朋友";
			divtext.insertBefore(newNode04,refChild01);
			
			var divtext= document.getElementById("xgyltext");
			var refChild1 = document.getElementById("div_share");
			var newNode4 = document.createElement("p");
			newNode4.setAttribute('id','xgylp');
			newNode4.setAttribute("class",'p_title1 font_middle');
			newNode4.style.padding="105px 40px 0";
			newNode4.style.fontSize="18px";
			newNode4.style.lineHeight="1.4";
			newNode4.style.textAlign="center";
			newNode4.style.wordBreak="break-all";
			newNode4.innerHTML ="回首，留不住岁月 凝眸，牵不住时光 不要忘记在认清所有真相后依然热爱生活";
			//newNode4.style.color="white";
			//newNode4.style.marginTop="60%";
			//newNode4.style.fontWeight="bolder";
			//newNode4.style.textAlign="center";
			divtext.insertBefore(newNode4,refChild1);
			
			//文字输入图片上传
			var notifyPage= document.getElementById("notifyPage");
			notifyPage.setAttribute("class",'page_edit');
			notifyPage.style.display="block";
			notifyPage.innerHTML ="";
			var newNode5 = document.createElement("section");
			newNode5.setAttribute('id','saveData');
			newNode5.setAttribute("class",'save_data');
			notifyPage.insertBefore(newNode5,null);
			
			var saveData= document.getElementById("saveData");
			var newNode6 = document.createElement("button");
			newNode6.setAttribute('id','saveBtn');
			newNode6.setAttribute("class",'save_btn btn btn-success');
			newNode6.setAttribute("onclick",'updateYm('+lx+',"'+bgtp+'")');
			newNode6.innerHTML ="保存";
			saveData.insertBefore(newNode6,null);
			
			var refChild2 = document.getElementById("saveData");
			var newNode7 = document.createElement("section");
			newNode7.setAttribute('id','editForm');
			newNode7.setAttribute("class",'edit_boxs');
			notifyPage.insertBefore(newNode7,refChild2);
			
			var editForm= document.getElementById("editForm");
			var newNode8 = document.createElement("div");
			newNode8.setAttribute('id','editImg');
			newNode8.setAttribute("class",'edit');
			editForm.insertBefore(newNode8,null);
			
			var editImg= document.getElementById("editImg");
			var newNode9 = document.createElement("input");
			newNode9.setAttribute('type','hidden');
			newNode9.setAttribute('id','ImgName');
			newNode9.setAttribute("value",'../../resources/js/wsh/shwhb/gn_section13_bg.jpg');
			editImg.insertBefore(newNode9,null);
			
			var refChild3 = document.getElementById("ImgName");
			var newNode10 = document.createElement("section");
			newNode10.setAttribute('id','imgForm');
			newNode10.setAttribute("class",'upload_image');
			editImg.insertBefore(newNode10,refChild3);
			
			var imgForm= document.getElementById("imgForm");
			var newNode11 = document.createElement("div");
			newNode11.setAttribute('id','notifyText');
			newNode11.setAttribute("class",'notify_text');
			newNode11.innerHTML ="建议图片尺寸：640*1136";
			imgForm.insertBefore(newNode11,null);
			
			var refChild4 = document.getElementById("notifyText");
			var newNode12 = document.createElement("div");
			newNode12.setAttribute('id','uploader');
			newNode12.setAttribute("class",'uploader');
			imgForm.insertBefore(newNode12,refChild4);
			
			var refChild5 = document.getElementById("uploader");
			var newNode13 = document.createElement("div");
			newNode13.setAttribute('id','image');
			newNode13.setAttribute("class",'notify_image');
			imgForm.insertBefore(newNode13,refChild5);
			
			var image= document.getElementById("image");
			var newNode14 = document.createElement("img");
			newNode14.setAttribute('id','bgyt');
			newNode14.setAttribute("src",'../../resources/js/wsh/shwhb/gn_section13_bg.jpg');
			image.insertBefore(newNode14,null);
			
			var refChild6 = document.getElementById("image");
			var newNode15 = document.createElement("div");
			newNode15.setAttribute('id','notifyImg');
			newNode15.setAttribute("class",'notify_img');
			newNode15.innerHTML ="图片";
			imgForm.insertBefore(newNode15,refChild6);
			
			var notifyImg= document.getElementById("notifyImg");
			var newNode17 = document.createElement("span");
			newNode17.innerHTML ="*";
			notifyImg.insertBefore(newNode17,null);
			
			var refChild7 = document.getElementById("editImg");
			var newNode18 = document.createElement("div");
			newNode18.setAttribute('id','editText1');
			newNode18.setAttribute("class",'edit');
			editForm.insertBefore(newNode18,refChild7);
			
			var editText1= document.getElementById("editText1");
			var newNode19 = document.createElement("div");
			newNode19.setAttribute('id','divText1');
			newNode19.setAttribute("class",'edit');
			editText1.insertBefore(newNode19,null);
			
			var divText1= document.getElementById("divText1");
			var newNode20 = document.createElement("textarea");
			newNode20.setAttribute('id','text1');
			newNode20.setAttribute("class",'edit_text');
			newNode20.innerHTML ="回首，留不住岁月 凝眸，牵不住时光 不要忘记在认清所有真相后依然热爱生活";
			newNode20.setAttribute("onchange",'ggxssj('+lx+')');
			divText1.insertBefore(newNode20,null);
			
			/*var refChild8 = document.getElementById("editText1");
			var newNode21 = document.createElement("div");
			newNode21.setAttribute('id','editText0');
			newNode21.setAttribute("class",'edit');
			editForm.insertBefore(newNode21,refChild8);
			
			var editText0= document.getElementById("editText0");
			var newNode22 = document.createElement("div");
			newNode22.setAttribute('id','divText0');
			newNode22.setAttribute("class",'edit');
			editText0.insertBefore(newNode22,null);
			
			var divText0= document.getElementById("divText0");
			var newNode23 = document.createElement("input");
			newNode23.setAttribute('id','text0');
			newNode23.setAttribute("type",'text');
			newNode23.setAttribute("value",'本次比赛赛制介绍');
			newNode23.setAttribute("onchange",'ggxssj()');
			divText0.insertBefore(newNode23,null);*/
			
			var refChild9 = document.getElementById("editText1");
			var newNode24 = document.createElement("span");
			newNode24.setAttribute('id','spit');
			newNode24.setAttribute("class",'edit_type');
			newNode24.innerHTML ="文字";
			editForm.insertBefore(newNode24,refChild9);
			
			var refChild10 = document.getElementById("editForm");
			var newNode25= document.createElement("a");
			newNode25.setAttribute('id','del');
			newNode25.setAttribute("class",'delete_data');
			newNode25.setAttribute("onclick",'deleteData('+sum+','+lx+')');
			newNode25.innerHTML ="删除";
			notifyPage.insertBefore(newNode25,refChild10);
			
			var del= document.getElementById("del");
			var newNode26 = document.createElement("img");
			newNode26.setAttribute("src",'../../resources/images/iconfont-shanchu.png');
			newNode26.setAttribute("height",'25px');
			newNode26.setAttribute("width",'25px');
			newNode26.setAttribute("alt",'删除');
			del.insertBefore(newNode26,null);
		}
		var text1=$('#text1').val();
		var hbid=$('#hbid').val();
		var zw="";
		if(lx==2||lx==3||lx==4){
			var text0=$('#text0').val();
			zw =""+text0+"<br/><br/><br/>"+text1+"<br/>";
		}else if(lx==5||lx==8){
			var text0=$('#text0').val();
			zw =""+text0+"<br/>"+text1+"";
		}else if(lx==6||lx==7||lx==9||lx==10||lx==11||lx==12){
			zw =""+text1+"";
		}
		$.ajax({
			url: "saveYm",
			type : 'post',
			dataType : 'json',
			data: {hbid:hbid,bgtp:bgtp,zw:zw,zt:lx},
			success: function(data){
				if(data == '1'){
					
				}else{
					alert("连接数据库出错，请联系管理员！");
				}
			},
			error: function(XMLHttpRequest){
					alert("网络错误");
			}
		});
		sum++;
	}
	function xzmb(num){
		var flag = true;
		var size=12;
		var div = document.getElementById("click_cancel"+num);
		if (flag) {
			for(var i=1;i<=size;i++){
				if(i==num){
					div.style.display = "block";
				}else{
					var divs = document.getElementById("click_cancel"+i);
					divs.style.display = "none";
				}
			}
		} else {
			div.style.display = "none";
		}
		flag = !flag;
	}