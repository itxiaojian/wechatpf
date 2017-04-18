var PARA_ORG_URL = '/ipas/org/findAttacheArea';
var ORG_ARCHIVE_URL = '/ipas/org/findOrgArchive';
OrgMemberWindow = Ext.extend(Ext.Window,{
	constructor: function(callbackFun,single){
		var orgData = {};
		var orgMemberWindow = this;
		var OrgTree  = Ext.extend(Ext.tree.TreePanel, {
			constructor : function(width, height) {
				OrgTree.superclass.constructor.call(this, {
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
						dataUrl : '/ipas/org/orgTree'
					}),
					root:new Ext.tree.AsyncTreeNode({
				          id : "treeContact",
				         // text: '机构树',
				          draggable:false,                                 
				          expanded: true   // 展开根节点下的节点
				    }),
					listeners : {
						'click': {fn: this.onOrgNodeClick, scope: this}
					},
					tbar : new Ext.Toolbar({
						items : [
						{
							iconCls : "refresh",
							text : "刷新",
							handler : function() {
								isparentId = true;
								orgMemberWindow.orgTree.root.reload();
								memberGrid.store.load({params:{start:0,limit:50,OrgId:'9999999999'}});
							}
						}, {
							text : "展开",
							iconCls : "expand",
							handler : function() {
								orgMemberWindow.orgTree.expandAll();
								memberGrid.store.load({params:{start:0,limit:50,OrgId:'9999999999'}});
							}
						}, {
							text : "收起",
							iconCls : "collapse",
							handler : function() {
								orgMemberWindow.orgTree.collapseAll();
								memberGrid.store.load({params:{start:0,limit:50,OrgId:'9999999999'}});
							}
						}
						]
					})
				});
			},
			onOrgNodeClick : function(node,e) {
				var OrgId = node.attributes.id;
				orgData['id'] = OrgId;
				orgData['name'] = node.attributes.text;
				Ext.Ajax.request({
					url: ORG_ARCHIVE_URL,
					method: 'POST',
					success: function(){
						memberGrid.store.load({params:{start:0,limit:50,OrgId:OrgId}});
					},
					failure: function(){
						Ext.Msg.alert('系统提示','查询机构失败！');
					}
				});
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
		var orgTree = new OrgTree(270,360);
		this.orgTree = orgTree;
		
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
		var memberGrid = new Ext.grid.GridPanel({
		    store:this.userStore,
		     sm: vsm,
		     cm:new Ext.grid.ColumnModel([ 
              new Ext.grid.RowNumberer(),
		                                   vsm,
           {header:'行员ID',dataIndex:'id',width:80,sortable:true,hidden:true},
            {header:'行员代号',dataIndex:'userCode',width:80,sortable:true},
            {header:'行员名称',dataIndex:'userName',width:90,sortable:true}                       
		     ]),
		    width: 260,
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
		
		this.vtbar=new Ext.Toolbar({
    		items: [
    		        	'->',{xtype:'button',text:'确认',iconCls:'add',handler:function(){
    		        		this.hide();
    		        	},scope:this},
    		        	'-',{xtype:'button',text:'清空',iconCls:'refresh',handler:function(){
    		        		memberGrid.getSelectionModel().clearSelections();
    		        	},scope:this}
    		        ]
    	});
		
		OrgMemberWindow.superclass.constructor.call(this,{
			width: 550,
			anchor: '100%',
			height: 370,
			resizable: false,
			plain: true,
			modal: true,
			layout: 'column',
			closeAction: 'hide',
			tbar:this.vtbar,
			listeners:{'hide':{fn:function(){
				var records = memberGrid.getSelectionModel().getSelections();
		   		if(callbackFun && records.length ==  1){
					callbackFun(orgData,memberGrid.getSelectionModel().getSelections()[0].data);
		   		}    
			}}},
			items:[this.orgTree,memberGrid]
		});
	}
});