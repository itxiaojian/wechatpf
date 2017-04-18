Ext.ns('Ipas');
var clerkNameWindow;
var isdeselect = true;
/******************* 行员选择组件 *****************/
Ipas.ClerkSingelSelect2 = Ext.extend(Ext.form.Field,  {
	constructor: function(fieldLabel,name,hiddenName,anchor) {
		this.name = name;
		this.allowBlank = false;
		new Ext.form.TextField({id:hiddenName});
		var PARA_ORG_URL = '/ipas/org/findAttacheArea';
		var ORG_ARCHIVE_URL = '/ipas/org/findOrgArchive';
		var nodeOrgId = [];//放入机构ID
		var orgNum;
		var areaIdStr;//当前所属机构Id，数据权限
		var telrNameText;//输入框对象，方便给输入框设置值

		var isparentId;//判断是否第一次进来
		var panelSwitch;//两个panel相互切换
		var addNum;//添加的总数
		var orgNum;//添加显示的数量
		var element;
		var name;
		var telrName;
		var codeName;
		var code;
		var nameArray;

		var isparentId2=true;
		var panelSwitch2=true;
		var addNum2=0;
		var orgNum2=0;
		var element2=0;
		var name2 = [];
		var telrName2 = [];
		var codeName2 = [];
		var code2 = [];
		var nameArray2 = [];
		
		/*******
		 *柜员业务量明细查询中的柜员姓名查询条件，在以下弹出窗口中选择
		 * @author fanhua
		 *
		 */
		var panel2 = new Ext.Panel({  
			resizable: false,
			plain: true,
			width: 222,
			height: 360,
			autoScroll : true,
			layout: 'column',
		     layoutConfig: {columns: 2},
			bodyStyle :'overflow-x:hidden;overflow-y:scroll', //隐藏水平滚动条,显示用overflow-x:visible
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
		
		/********************clerkNameWindow组件*************************/
		var ClerkNameWindow = Ext.extend(Ext.Window, {
		    constructor: function(areaId,telrName) {
		    	telrNameText = telrName;
		    	areaIdStr=areaId;
		    	isparentId=isparentId2;
		    	panelSwitch=panelSwitch2;
		    	addNum=addNum2;
		    	orgNum=orgNum2;
		    	element=element2;
				name = name2;
				telrName = telrName2;
				codeName = codeName2;
				code = code2;
				nameArray = nameArray2;
		    	this.clerkNameTree = new ClerkNameTree(270,360);
		        
		    	ClerkNameWindow.superclass.constructor.call(this, {
		        	width: 750,
					anchor: '100%',
					height: 370,
					resizable: false,
					plain: true,
					modal: true,
					layout: 'column',
					closeAction: 'hide',
					listeners:{'hide':{fn:this.onCheckHide}},
		            items: [
		                    this.clerkNameTree,
		                    ClerkGrid
		                    ]
		        });
		    },
		    onCheckHide:function(){
		    	addNum2=addNum;
		    	orgNum2=orgNum;
		    	element2=element;
				name2 = name;
				telrName2 = telrName;
				codeName2 = codeName;
				code2 = code;
				nameArray2 = nameArray;
		    	isparentId=true;
		    	panelSwitch=true;
		    }
		});
		this.getClerkCode = function(){//获取行员代号数组
			if(code==null){
				return null;
			}else{
				return code[code.length-1];
			}
		};
		this.getClerkName = function(){//获取行员名称数组
			if(nameArray==null){
				return null;
			}else{
				return nameArray[nameArray.length-1];
			}
		};
		this.empty = function(){
			isparentId2=true;
			panelSwitch2=true;
			addNum2=0;
			orgNum2=0;
			element2=0;
			name2 = [];
			telrName2 = [];
			codeName2 = [];
			code2 = [];
			nameArray2 = [];
			nameArray = [];
			if(clerkNameWindow!=null){
				clerkNameWindow.get(2).get(0).getEl().update('',true);
				clerkNameWindow.get(2).get(1).getEl().update('',true);
			}
			document.getElementById('check').checked=false;
			vsm.clearSelections();
		};
		/***********************AreaOrgTree组件***********************************/
		var ClerkNameTree  = Ext.extend(Ext.tree.TreePanel, {
			constructor : function(width, height) {

				ClerkNameTree.superclass.constructor.call(this, {
					collapsible :false,
					autoScroll : true,
					enableDD : true,//是否支持拖拽效果
					containerScroll : true,//是否支持滚动条
					width : width,
					height : height,
					expanded : true,
					border : true,
					frame : true,
					rootVisible : false,//是否显示根节点
					margins : '0 0 0 0',
					loader : new Ext.tree.TreeLoader({
						dataUrl : '/ipas/org/orgTree'
					}),
					root:new Ext.tree.AsyncTreeNode({
				          id : "treeContact",
				         // text: '机构树',
				          draggable:false,                                 
				          expanded: true   // 展开根节点下的节点
				    }),
					listeners : {
						'click': {fn: this.onCheckClick, scope: this}
					},
					tbar : new Ext.Toolbar({
						items : [
						{
							iconCls : "refresh",
							text : "刷新",
							handler : function() {
								isparentId = true;
								clerkNameWindow.clerkNameTree.root.reload();
								clerkNameWindow.clerkNameTree.expandAll();
							}
						}, {
							text : "展开",
							iconCls : "expand",
							handler : function() {
								clerkNameWindow.clerkNameTree.expandAll();
							}
						}, {
							text : "收起",
							iconCls : "collapse",
							handler : function() {
								clerkNameWindow.clerkNameTree.collapseAll();
							}
						}
						]
					})
				});
			},
			onCheckClick : function(node,e) {
				var OrgId = node.attributes.id;
				nodeOrgId.push(OrgId);
				Ext.Ajax.request({
					url: ORG_ARCHIVE_URL,
					method: 'POST',
					success: function(){
						ClerkGrid.store.load({params:{start:0,limit:50,OrgId:nodeOrgId}});
					},
					failure: function(){
						Ext.Msg.alert('系统提示','添加失败！');
					}
				});
				NODES = node;
			},
			onBeforeLoad : function(node, e) {
				var pid = node.attributes.id;
				if(isparentId == true){
					pid = areaIdStr;
				this.loader.dataUrl = PARA_ORG_URL+"?id="+pid+"&isparentId=true"; //定义子节点的Loader
				isparentId = false;
				}else{
					if(pid==null){
						pid=areaIdStr;
					}
					this.loader.dataUrl = PARA_ORG_URL+"?id="+pid; //定义子节点的Loader
				}
			}
		});
		/****************AreaGrid***********************/
		var vsm = new Ext.grid.CheckboxSelectionModel({
				header : '',
				singleSelect : true,
				listeners : {
					'rowdeselect':{fn:function(selectMode,rowindex,rec){
						for(var i=0;i<code.length;i++){
							if(code[i] == rec.data.userCode){
//								document.deleteClick(i+1);
								element = element-1;
								name[i+1] = "";
								nameArray[i] = "";
								for(var str=1;str<nameArray.length;str++){
									if(nameArray[str] != ""){
										nameStr = nameArray[str];
									}
								}
								code[i]="";
								telrNameText.setValue(nameStr.trim());
								if(element == 0){
									telrNameText.setValue("");
								}
								orgNum = element;
								Ext.get('usNm'+(i+1)).remove();
							}
						}
					}}
				}
			});
		var ClerkGrid = new Ext.grid.GridPanel({
		    store: new Ext.data.Store({
		    	proxy : new Ext.data.HttpProxy({
					url : ORG_ARCHIVE_URL,
					method : 'POST'
				}),
				reader : new Ext.data.JsonReader({},
						new Ext.data.Record.create([{name : 'userCode'},{name : 'userName'}]))
		    }),
		     sm: vsm,
		     cm:new Ext.grid.ColumnModel([ 
              new Ext.grid.RowNumberer(),
		                                   vsm,
            {header:'行员代号',dataIndex:'userCode',width:80,sortable:true},
            {header:'行员名称',dataIndex:'userName',width:90,sortable:true}                       
		     ]),
		    width: 240,
		    //frame: true,
			height: 360,
			//autoScroll : true,
			bodyStyle :'overflow-x:hidden;overflow-y:scroll', //隐藏水平滚动条,显示用overflow-x:visible
			viewConfig: {
		        forceFit: false
		    },
			loadMask: {
				msg: '正在载入数据，请稍后...'
			}
		});
		ClerkGrid.on("click",function(e){
			var count = ClerkGrid.getSelectionModel().getCount();
			for(var namber=0;namber<count;namber++){
				var userName = ClerkGrid.getSelectionModel().getSelections()[namber].data.userName;
				var userCode = ClerkGrid.getSelectionModel().getSelections()[namber].data.userCode;
				if(name.length>0){
					for(var y=1;y<name.length;y++){
						if(userName+userCode == name[y]){
//							Ext.Msg.alert('提示','该用户已存在！');
							break;
						}else if(name.length == y+1){
							if(panelSwitch == true){
								addNum++;
								orgNum++;
								code[addNum-1]=userCode;
								name[addNum]=userName+userCode;
								nameArray[addNum-1] = userName;
								nameStr = userName;
								clerkNameWindow.get(2).get(0).getEl().insertHtml('beforeEnd','<div id = "usNm'+addNum+'">'+userName+'</div>'
								);
								panelSwitch = false;
								element = orgNum;
								telrNameText.setValue(nameStr.trim());
								break;
							}else{
								addNum++;
								orgNum++;
								code[addNum-1]=userCode;
								name[addNum]=userName+userCode;
								nameArray[addNum-1] = userName;
								nameStr = userName;
								clerkNameWindow.get(2).get(1).getEl().insertHtml('beforeEnd','<div id = "usNm'+addNum+'">'+userName
								+'</div>');
								panelSwitch = true;
								element = orgNum;
								telrNameText.setValue(nameStr.trim());
								break;
							}
						}
					}
				}
				if(name.length==0){
					if(panelSwitch == true){
						addNum++;
						orgNum++;
						code[addNum-1]=userCode;
						name[addNum]=userName+userCode;
						nameArray[addNum-1] = userName;
						nameStr = userName;
						clerkNameWindow.get(2).get(0).getEl().insertHtml('beforeEnd','<div id = "usNm'+addNum+'">'+userName
						+'</div>');
						panelSwitch = false;
						element = orgNum;
					}else{
						addNum++;
						orgNum++;
						code[addNum-1]=userCode;
						name[addNum]=userName+userCode;
						nameArray[addNum-1] = userName;
						nameStr = userName;
						clerkNameWindow.get(2).get(1).getEl().insertHtml('beforeEnd','<div id = "usNm'+addNum+'">'+userName
						+'</div>');
						panelSwitch = true;
						element = orgNum;
					}
					telrNameText.setValue(nameStr.trim());
				}
			}
		});
//		document.deleteClick = function(e){
//			
//		};
		Ipas.ClerkSingelSelect2.superclass.constructor.call(this, {
			fieldLabel :fieldLabel,
		 	layout:'form',
		 	anchor:anchor,
			listeners : {
				'focus' : {//获取焦点时触发
					fn : function() {
						clerkNameWindow = new ClerkNameWindow('',this);
						//clerkNameWindow.setPosition(this.el.getX(), this.el.getY()+25);
						clerkNameWindow.show();
					},
					scope : this
				}
			}
	 })
		}
	});