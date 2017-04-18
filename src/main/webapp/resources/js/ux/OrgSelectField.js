Ext.ns('Ipas');
var orgNameWindow;
/******************* 机构选择组件 *****************/
Ipas.OrgSelectField = Ext.extend(Ext.form.Field,  {
	constructor: function(fieldLabel,name,hiddenName,anchor) {
		this.name = name;
		this.allowBlank = false;
		new Ext.form.TextField({id:hiddenName});
		var PARA_ORG_URL = '/ipas/org/findAttacheArea';//查询行员所属机构
		var ORG_ARCHIVE_URL = '/ipas/org/findOrgArchive';
		var areaIdStr;//当前所属机构Id，数据权限
		var orgNameText;//输入框对象，方便给输入框设置值
		var nodeOrgId;//放入机构ID
		var organizationName;
		var panelSwitch;//两个panel相互切换
		var addNum;//添加的总数
		var orgNum;//添加显示的数量
		var NODES;
		var element;
		var orgName;//存放机构名称
		var isparentId;//判断是否第一次进来

		var nodeOrgId2 = [];
		var organizationName2 = [];
		var panelSwitch2 = true;
		var addNum2 = 0;
		var orgNum2 = 0;
		var NODES2 = null;
		var element2 = 0;
		var orgName2 = [];
		var isparentId2 = true;
		/*******
		 *柜员业务量明细查询中的柜员姓名查询条件，在以下弹出窗口中选择
		 * 
		 *
		 */
		var panel2 = new Ext.Panel({  
			resizable: false,
			plain: true,
			width: 260,
			height: 345,
			border:false,
			bodyStyle :'overflow-x:hidden;overflow-y:scroll', //隐藏水平滚动条,显示用overflow-x:visible
			layout: 'column',
		     layoutConfig: {columns: 2},
			items:[
			       {
			    	   width: 100,
			    	   border : false
			       },{
			    	   width: 100,
			    	   border : false
			       }
			       ]

		});
		
		var OrgNameWindow = Ext.extend(Ext.Window, {
		    constructor: function(areaId,ogrName) {
		    	orgNameText = ogrName;
		    	areaIdStr=areaId;
		    	nodeOrgId = nodeOrgId2;
		    	organizationName = organizationName2;
		    	addNum = addNum2;
		    	orgNum = orgNum2;
		    	NODES = NODES2;
		    	element = element2;
		    	orgName = orgName2;
		    	this.orgNameTree = new OrgNameTree(320,345);
		    	
		    	this.vtbar=new Ext.Toolbar({
		    		items: [
		    		        	'->',{xtype:'button',text:'确认',iconCls:'add',handler:function(){
		    		        		this.hide();
		    		        	},scope:this},
		    		        	'-',{xtype:'button',text:'清空',iconCls:'refresh',handler:function(){
		    		        		isparentId=true;
		    		    			nodeOrgId = [];
		    		    			organizationName = [];
		    		    			panelSwitch = true;
		    		    			addNum = 0;
		    		    			orgNum = 0;
		    		    			NODES = null;
		    		    			element = 0;
		    		    			orgName = [];
		    		    			if(orgNameWindow!=null){
		    		    				orgNameWindow.get(1).get(0).getEl().update('',true);
		    		    				orgNameWindow.get(1).get(1).getEl().update('',true);
		    		    			}
		    		    			orgNameText.setValue("");
		    		        	},scope:this}
		    		        ]
		    	});
		        
		        OrgNameWindow.superclass.constructor.call(this, {
					width: 597,
					anchor: '100%',
					height: 410,
					resizable: false,
					plain: true,
					modal: true,
					tbar:this.vtbar,
					closable:true,
					layout: 'column',
					closeAction: 'hide',
					listeners:{'hide':{fn:this.onCheckHide}},
		            items: [
		                    this.orgNameTree,
		                    panel2
		                    ]
		        });
		    },
		    onCheckHide:function(){
		    	nodeOrgId2 = nodeOrgId;
		    	organizationName2 = organizationName;
		    	addNum2 = addNum;
		    	orgNum2 = orgNum;
		    	NODES2 = NODES;
		    	element2 = element;
		    	orgName2 = orgName;
		    	isparentId=true;
		    	panelSwitch=true;
		    }
		});
		/***********************AreaOrgTree组件***********************************/
		var OrgNameTree  = Ext.extend(Ext.tree.TreePanel, {
			constructor : function(width,height) {
				OrgNameTree.superclass.constructor.call(this, {
//					title : '区域机构名称',
					region:'center',
					collapsible :false,
					autoScroll : true,
					enableDD : false,//是否支持拖拽效果
					containerScroll : true,//是否支持滚动条
					width : width,
					height : height,
					expanded : true,
					border : true,
					frame : true,
					rootVisible : false,//是否显示根节点
					margins : '0 0 0 0',
					loader : new Ext.tree.TreeLoader({
						dataUrl : '/ipas/org/orgTree2'
					}),
					root:new Ext.tree.AsyncTreeNode({
				          id : "treeContact",
				         // text: '机构树',
				          draggable:false,                                 
				          expanded: true   // 展开根节点下的节点
				    }),
					listeners : {
						'contextmenu': {fn: function(node,e){
							var nodeLength = node.childNodes;
							if(node.getUI().isChecked() == true){
								node.getUI().toggleCheck(false);
								for(var a=0;a<node.childNodes.length;a++){
									node.childNodes[a].getUI().toggleCheck(false);
								}
							}else{
								node.getUI().toggleCheck(true);
								childNode(node,nodeLength);
							}
						}, scope: this},
						'checkchange': {fn: function(node,checked){
//							alert(node.attributes.id+"==="+checked);
							if(checked == true){
								onCheckClick(node,checked);
							}else{
								for(var j=0;j<nodeOrgId.length;j++){
									if(nodeOrgId[j] == node.attributes.id){
										document.orgDeleteClick(j+1,nodeOrgId[j]);
									}
								}
							}
						}, scope: this},
						'load' : {//在节点加载之前触发，返回false取消操作
							fn : function(node){
								orgNameWindow.orgNameTree.expandAll();
								var nodeLength = node.childNodes;
								for(var i=0;i<nodeLength.length;i++){
									for(var j=0;j<nodeOrgId.length;j++){
										if(node.childNodes[i].id == nodeOrgId[j]){
											node.childNodes[i].getUI().toggleCheck(true);
										}
									}
								}
							},
							scope : this
						}
					},
					tbar : new Ext.Toolbar({
						items : [
						{
							iconCls : "refresh",
							text : "刷新",
							handler : function() {
								isparentId = true;
							orgNameWindow.orgNameTree.root.reload();
							orgNameWindow.orgNameTree.expandAll();
							}
						}, {
							text : "展开",
							iconCls : "expand",
							handler : function() {
							orgNameWindow.orgNameTree.expandAll();
							}
						}, {
							text : "收起",
							iconCls : "collapse",
							handler : function() {
							orgNameWindow.orgNameTree.collapseAll();
							}
						}
						]
					})
				});
			},
			onBeforeLoad : function(node, e) {
				var pid = node.attributes.id;
				if(isparentId == true){
					pid = areaIdStr;
					this.loader.dataUrl = PARA_ORG_URL+"?id="+pid+"&isparentId=true";
					isparentId = false;
				}else{
					if(pid==null){
						pid=areaIdStr;
					}
					this.loader.dataUrl = PARA_ORG_URL+"?id="+pid; //定义子节点的Loader
				}
			}
		});
		var childNode = function(node,nodeLength){
			for(var i=0;i<nodeLength.length;i++){
				node.childNodes[i].getUI ().toggleCheck(true);
				childNode(node.childNodes[i],node.childNodes[i].childNodes);
			}
		};
		var onCheckClick = function(node,e) {
			var OrgId = node.attributes.id;
			var orgNM = node.attributes.text;
				if(orgName.length>0){
					 for(var y=0;y<orgName.length;y++){
						 if(orgNM == orgName[y]){
								break;
							}else if(orgName.length == y+1){
								if(panelSwitch == true){
									addNum++;
									orgNum++;
									orgName[addNum-1] = orgNM;
									nameStr = orgNM;
									nodeOrgId[addNum-1] = OrgId;
									orgNameWindow.get(1).get(0).getEl().insertHtml('beforeEnd','<div id = "orgNm'+addNum+'">'+orgNM.trim()+
											'<input type="image" src="/resources/images/icons/cross.png" onclick="document.orgDeleteClick('+addNum+","+OrgId+','+true+');" style="color: red" />'
											+'</div>',true);
									panelSwitch = false;
									element = orgNum;
									orgNameText.setValue(nameStr.trim()+"等("+element+")个");
									break;
								}else{
									addNum++;
									orgNum++;
									orgName[addNum-1] = orgNM;
									nameStr = orgNM;
									nodeOrgId[addNum-1] = OrgId;
									orgNameWindow.get(1).get(1).getEl().insertHtml('beforeEnd','<div id = "orgNm'+addNum+'">'+orgNM.trim()+
											'<input type="image" src="/resources/images/icons/cross.png" onclick="document.orgDeleteClick('+addNum+","+OrgId+','+true+');" style="color: red" />'
											+'</div>',true);
									panelSwitch = true;
									element = orgNum;
									orgNameText.setValue(nameStr.trim()+"等("+element+")个");
									break;
								}
							}
					 }
				 }
				if(orgName.length==0){
					if(panelSwitch == true){
						addNum++;
						orgNum++;
						orgName[addNum-1] = orgNM;
						nameStr = orgNM;
						nodeOrgId[addNum-1] = OrgId;//"("+OrgId+")"+在机构后面加机构号
						orgNameWindow.get(1).get(0).getEl().insertHtml('beforeEnd','<div id = "orgNm'+addNum+'">'+orgNM.trim()+
								'<input type="image" src="/resources/images/icons/cross.png" onclick="document.orgDeleteClick('+addNum+","+OrgId+','+true+');" style="color: red" />'
								+'</div>',true);
						panelSwitch = false;
						element = orgNum;
					}else{
						addNum++;
						orgNum++;
						orgName[addNum-1] = orgNM;
						nameStr = orgNM;
						nodeOrgId[addNum-1] = OrgId;
						orgNameWindow.get(1).get(1).getEl().insertHtml('beforeEnd','<div id = "orgNm'+addNum+'">'+orgNM.trim()+
								'<input type="image" src="/resources/images/icons/cross.png" onclick="document.orgDeleteClick('+addNum+","+OrgId+','+true+');" style="color: red" />'
								+'</div>',true);
						panelSwitch= true;
						element = orgNum;
					}
					orgNameText.setValue(nameStr.trim()+"等("+element+")个");
				}
			NODES = node;
		};
		this.getOrgId = function(){//获取机构ID数组
			if(nodeOrgId==null){
				return null;
			}else{
				var s="";
       			var arr=(nodeOrgId+"").split(",");
       			for(var i=0;i<arr.length;i++){
       				if(arr[i] != ""){
       					s+=arr[i]+",";
       				}
       			}
       			s=s.substring(0, s.lastIndexOf(","));
				return s;
			}
		};
		this.getOrgName = function(){//获取机构名称
			if(orgName==null){
				return null;
			}else{
				return orgName;
			}
		};
		this.empty = function(){//清空所选择的所有数据
			isparentId2=true;
			nodeOrgId2 = [];
			organizationName2 = [];
			panelSwitch2 = true;
			addNum2 = 0;
			orgNum2 = 0;
			NODES2 = null;
			element2 = 0;
			orgName2 = [];
			if(orgNameWindow!=null){
				orgNameWindow.get(1).get(0).getEl().update('',true);
				orgNameWindow.get(1).get(1).getEl().update('',true);
			}
		};
		document.orgDeleteClick = function(e,orgCode,isTrue){
//			alert(orgNameWindow.orgNameTree.getNodeById(orgCode));
			if(isTrue==true){
				orgNameWindow.orgNameTree.getNodeById(orgCode).getUI().toggleCheck(false);
			}else{
				element = element-1;
				Ext.get('orgNm'+e).remove();
			}
			orgName[e-1] = "";
			nodeOrgId[e-1] = "";
			for(var str=0;str<orgName.length;str++){
				if(orgName[str] != ""){
					nameStr = orgName[str];
				}
			}
			orgNameText.setValue(nameStr.trim()+"等（"+element+"）个");
			if(element == 0){
				orgNameText.setValue("");
			}
			orgNum = element;
		};
		Ipas.OrgSelectField.superclass.constructor.call(this, {
		 	fieldLabel :fieldLabel,
		 	layout:'form',
		 	anchor:anchor,
//		 	html : '<input type="button" '+
//				'value="清空" onclick="document.deleteAllClick();" style="color: red" />',
			listeners : {
				'focus' : {//获取焦点时触发
					fn : function() {
						if (!this.readOnly) {
							orgNameWindow = new OrgNameWindow('',this);
							orgNameWindow.setPosition(this.el.getX(), this.el.getY()+25);
							orgNameWindow.show();
						}
					},
					scope : this
				}
			}
	 })
	}
});