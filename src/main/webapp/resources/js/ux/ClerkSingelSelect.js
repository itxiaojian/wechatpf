Ext.ns('wxpt');
var clerkNameWindow;
var isdeselect = true;
/******************* 行员选择组件 *****************/
wxpt.ClerkSingelSelect = Ext.extend(Ext.form.Field,  {
	constructor: function(fieldLabel,name,hiddenName,anchor) {
		this.name = name;
		this.allowBlank = false;
		this.anchor= '95%';
		new Ext.form.TextField({id:hiddenName});
		var PARA_ORG_URL = PATHNAME+'/sys/SysArea/findAttacheArea';
		var ORG_ARCHIVE_URL = PATHNAME+'/sys/SysArea/findOrgArchive';
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
		var namedArray;
		var isparentId2=true;
		var panelSwitch2=true;
		var addNum2=0;
		var orgNum2=0;
		var element2=0;
		var name2 = [];
		var telrName2 = [];
		var codeName2 = [];
		var code2 = '';
		var namedArray2 = [];
		
		/*******
		 *柜员业务量明细查询中的柜员姓名查询条件，在以下弹出窗口中选择
		 * @author fanhua
		 *
		 */
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
				namedArray = namedArray2;
		    	this.clerkNameTree = new ClerkNameTree(270,360);
		    	
		    	this.vtbar=new Ext.Toolbar({
		    		items: [
		    		        	'->',{xtype:'button',text:'确认',iconCls:'add',handler:function(){
		    		        		this.hide();
		    		        	},scope:this},
		    		        	'-',{xtype:'button',text:'清空',iconCls:'refresh',handler:function(){
		    		        		isparentId=true;
		    		    			panelSwitch=true;
		    		    			addNum=0;
		    		    			orgNum=0;
		    		    			element=0;
		    		    			name = [];
		    		    			telrName = [];
		    		    			codeName = [];
		    		    			code = [];
		    		    			namedArray = [];
		    		    			telrNameText.setValue("");
		    		    			ClerkGrid.getSelectionModel().clearSelections();
		    		        	},scope:this}
		    		        ]
		    	});
		        
		    	ClerkNameWindow.superclass.constructor.call(this, {
		        	width: 530,
					anchor: '100%',
					height: 390,
					resizable: false,
					plain: true,
					modal: true,
					tbar:this.vtbar,
					closable:true,
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
				namedArray2 = namedArray;
		    	isparentId=true;
		    	panelSwitch=true;
		    }
		});
		this.getClerkCode = function(){//获取行员代号数组
			if(code==null){
				return null;
			}else{
				return code;
			}
		};
		this.getClerkName = function(){//获取行员名称数组
			if(namedArray==null){
				return null;
			}else{
				return namedArray[namedArray.length-1];
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
			namedArray2 = [];
			if(clerkNameWindow!=null){
				clerkNameWindow.get(2).get(0).getEl().update('',true);
				clerkNameWindow.get(2).get(1).getEl().update('',true);
				document.getElementById('check').checked=false;
				vsm.clearSelections();
			}
		};
		/***********************AreaOrgTree组件***********************************/
		var ClerkNameTree  = Ext.extend(Ext.tree.TreePanel, {
			constructor : function(width, height) {

				ClerkNameTree.superclass.constructor.call(this, {
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
						dataUrl : PATHNAME+'/sys/SysArea/orgTree'
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
				singleSelect : true
			});
		this.userStore =  new Ext.data.Store({
	    	proxy : new Ext.data.HttpProxy({
				url : ORG_ARCHIVE_URL,
				method : 'POST'
			}),
			reader : new Ext.data.JsonReader({},
					new Ext.data.Record.create([{name:'id'},{name : 'userCode'},{name : 'userName'}]))
		});
		var ClerkGrid = new Ext.grid.GridPanel({
		    store:this.userStore,
		     sm: vsm,
		     cm:new Ext.grid.ColumnModel([ 
              new Ext.grid.RowNumberer(),
		                                   vsm,
           {header:'用户编号',dataIndex:'id',width:80,sortable:true,hidden:true},
            {header:'登陆账户',dataIndex:'userCode',width:80,sortable:true},
            {header:'用户姓名',dataIndex:'userName',width:90,sortable:true}                       
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
				var userName = ClerkGrid.getSelectionModel().getSelections()[0].data.userName;
				telrNameText.setValue(userName);
				//code = ClerkGrid.getSelectionModel().getSelections()[0].data.id;
				code = ClerkGrid.getSelectionModel().getSelections()[0].data.userCode;
				namedArray = ClerkGrid.getSelectionModel().getSelections()[0].data.userName;
				if (Ext.getCmp(name))
					Ext.getCmp(name).setValue(ClerkGrid.getSelectionModel().getSelections()[0].data.userCode);
//				Ext.getCmp(hiddenName).setValue(ClerkGrid.getSelectionModel().getSelections()[0].data.id);
		});
		wxpt.ClerkSingelSelect.superclass.constructor.call(this, {
		 	fieldLabel :fieldLabel,
		 	layout:'form',
		 	anchor:anchor,
			listeners : {
				'focus' : {//获取焦点时触发
					fn : function() {
						if (!this.readOnly) {
						clerkNameWindow = new ClerkNameWindow('',this);
//						clerkNameWindow.setPosition(this.el.getX(), this.el.getY()+25);
						clerkNameWindow.show();
					}
					},
					scope : this
				}
			}
	 })
		}
	});