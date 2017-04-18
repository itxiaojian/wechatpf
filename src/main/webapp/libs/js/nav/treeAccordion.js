var zTree_Menu = null;
	var setting = {
		view: {
			showIcon:false,
			showLine: false,
			selectedMulti: false,
			dblClickExpand: false,
			addDiyDom: addDiyDom
		},
		callback: {
			beforeClick:beforeClick,
			onClick: onClick
		}
	};
	
	
	
	function initComplete(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
		
		showContent();
	}
	
	//每次刷新时保持上次打开的
	function showContent(){
		var treeNodeId=jQuery.jCookie('leftTreeNodeId');
		if(treeNodeId==false||treeNodeId=="false"){}
		else{
			var node = zTree_Menu.getNodeByParam("id", treeNodeId);
			zTree_Menu.selectNode(node);
			//展开上次选中的
			//zTree_Menu.expandNode(node);
			
			//如果上次选中的为第一级节点，则添加选中样式
			if (node.isParent){
				if (node.level === 0) {
					//得到当前点击节点，添加选中样式
					var a = $("#" + node.tId + "_a");
					//a.addClass("cur");
					a.click()
				}
			}
			
			if(node.url){
				//每次刷新时设置当前位置内容
				top.positionType="simple";
				top.positionContent="当前位置："+node.getParentNode().name+">>"+node.name;
				
				//打开上次的链接
				top.frmright.location=node.url;
			}
		}
	}
	//每次点击切换状态和样式
	function beforeClick(treeId, treeNode) {
			//第一级点击项添加选中样式
			if (treeNode.level === 0) {
				//第一级移除选中样式
				$("#"+treeId).find("a").each(function(){
					if($(this).hasClass("cur")){
						$(this).removeClass("cur");
					}
				})
				
				var a = $("#" + treeNode.tId + "_a");
				a.addClass("cur");
			
				//单击展开或收缩
				if(treeNode.open){
					zTree_Menu.expandNode(treeNode,false);
					var a = $("#" + treeNode.tId + "_a");
					a.removeClass("cur");
				}
				else{
					zTree_Menu.expandAll(false);
					zTree_Menu.expandNode(treeNode);
				}
			}
			else{
				zTree_Menu.expandNode(treeNode);
			}
			
			
		}
		
	function onClick(e,treeId, treeNode){
		//出现进度条
		if(treeNode.url!=null){
			showProgressBar();
			
			//每次点击时设置当前位置内容
			if(treeNode.getParentNode()){
				top.positionContent="当前位置："+treeNode.getParentNode().name+">>"+treeNode.name;
			}
			else{
				top.positionContent="当前位置："+treeNode.name;
			}
			top.positionType="simple";
		}
		
		//存储点击节点id
		jQuery.jCookie('leftTreeNodeId',treeNode.id.toString());
	}
	//添加自定义图标（数据的icon属性无法满足图标自定义尺寸）
	 function addDiyDom(treeId, treeNode) {
	    var aObj = $("#" + treeNode.tId + "_a");
	    if ($("#infoBtn_"+treeNode.id).length>0) return;
		var iconSrc="skin/icons/child.gif";
		var iconWidth=16;
		var iconHeight=16;
		if(treeNode.navIcon){
			var iconSrc=treeNode.navIcon;
		}
		if(treeNode.navIconWidth){
			iconWidth=treeNode.navIconWidth;
		}
		if(treeNode.navIconHeight){
			iconHeight=treeNode.navIconHeight;
		}
		var $icon= $('<button style=":;background-position:100% 0%;"  title="" id="treeDemo_1_ico" type="button"></button>')
		$icon.css("background-image","url("+iconSrc+")");
		$icon.css("background:-repeat","no-repeat");
		$icon.width(iconWidth);
		$icon.height(iconHeight);
		aObj.prepend($icon);
		
	};

	function  showAll(){
		zTree_Menu.expandAll(true);
	}
	function  hideAll(){
		zTree_Menu.expandAll(false);
	}