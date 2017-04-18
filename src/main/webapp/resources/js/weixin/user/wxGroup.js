var GROUP_GRID_STORE_URL = '/wxauth/group/getWxGroupPaging';
var PAGESIZE=20;

/***********************GroupForm组件**************************
 *author        ： zhangyi
 *description   : 
 *date          : 2015-05-27
****************************************************************/
GroupForm = Ext.extend(Ext.ux.Form,{
	constructor:function(){
		this.groupId = this.createHidden('groupId', 'groupId', '95%');
		this.groupName = this.createTextField('<font color="red">*</font>分组名:', 'groupName', '95%');
		
		this.groupName.allowBlank = false;
        
		GroupForm.superclass.constructor.call(this, {
        	anchor: '100%',
            autoHeight:true,
            labelWidth: 100,
            labelAlign :'right',
            frame: true,
            bodyStyle:"padding: 5px 5px 0",
            layout: 'tableform',
            layoutConfig: {columns: 1},
			items:[
			       this.groupName,
			       this.groupId
			],
	        buttons:[
				{text: '保存', width: 20,iconCls: 'save', hidden: false,handler:this.addFormClick,scope:this},
				{text: '修改', width: 20,iconCls:'edit', hidden: true, handler: this.updateFormClick, scope: this},
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
	        ],
			buttonAlign : 'center'
		});
	},
	addFormClick: function(){
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: PROJECT_NAME+'/wxauth/group/addGroup',
				method: 'POST',
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"保存成功！"+BLANKSTR);
					wxGroupGrid.store.load({params:{start:0,limit:PAGESIZE}});
					wxGroupGrid.groupInsertWindow.hide();
					
				},
				failure: function(form,action){
					wxGroupGrid.groupInsertWindow.hide();
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"保存失败！"+BLANKSTR);
				}
			});
		}
	},
	updateFormClick: function() {
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: PROJECT_NAME+'/wxauth/group/updateGroup',
				method: 'POST',
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"修改成功！"+BLANKSTR);
					wxGroupGrid.store.load({params:{start:0,limit:PAGESIZE}});
					wxGroupGrid.groupUpdateWindow.hide();
					
				},
				failure: function(form,action){
					wxGroupGrid.groupUpdateWindow.hide();
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"修改失败！"+BLANKSTR);
				}
			});
		}		
	},
	onCloseClick:function() {
		this.ownerCt.hide();
	}
	
});

/***********************GroupInsertWindow组件**************************
 *author        ： zhangyi
 *description   : 
 *date          : 2014-11-7
*********************************************************************/
GroupInsertWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.groupForm = new GroupForm(); 	
    	GroupInsertWindow.superclass.constructor.call(this, {
    		title:'添加微信分组信息',
    		width: 400,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.groupForm]
        });
    }
});

/***********************GroupUpdateWindow组件**************************
 *author        ： zhangyi
 *description   : 
 *date          : 2014-11-7
******************************************************************/
GroupUpdateWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.groupForm = new GroupForm(); 
    	this.groupForm.buttons[0].hide();   //隐藏添加按钮
    	this.groupForm.buttons[1].show();   //显示修改按钮
    	GroupUpdateWindow.superclass.constructor.call(this, {
    		title:'修改微信分组信息',
    		width: 400,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.groupForm]
        });
    }
});

/***********************WxGroupGrid组件**************************
 *author        ： zhangyi
 *description   : 
 *date          : 2015-05-27
****************************************************************/
WxGroupGrid = Ext.extend(UxGrid, {
	setRemarkWindow:null,
	vtbar:null,				//面板顶部的工具条
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.groupInsertWindow = new GroupInsertWindow();
    	this.groupUpdateWindow = new GroupUpdateWindow();
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+GROUP_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},[
                   {name:'groupId'},{name:'name'},{name:'gcount'},{name:'memo'}
            ])
        });
    	this.vbbar= new Ext.PagingToolbar({ 
            pageSize: PAGESIZE, 
            store: this.store, 
            displayInfo: true, 
            displayMsg: '显示第 {0} 条到 {1} 条记录，一共 {2} 条', 
            emptyMsg: "没有记录" 
    	});
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},
                '-',{text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},
                '-',{text:'与微信平台同步',iconCls: 'refresh',handler:this.onRefreshClick,scope:this}
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        WxGroupGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: true,
            height: height,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header:'groupId',dataIndex:'groupId',width:100,sortable: true},
                {header:'分组名',dataIndex:'name',width:180,sortable: true},
                {header:'用户数量',dataIndex:'gcount',width:160,sortable: true}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    onAddClick: function() {
    	this.groupInsertWindow.groupForm.getForm().reset();
    	this.groupInsertWindow.show();
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
    	if(records.length == 0) {
    		Ext.Msg.alert('系统提示', '请至少选择一条记录');
    		return;
    	}else if(records.length >1) {
    		Ext.Msg.alert('系统提示', '不能同时修改多条记录..');
    		return;
    	}
    	var record = records[0];
    	this.groupUpdateWindow.groupForm.getForm().reset();
    	this.groupUpdateWindow.groupForm.groupId.setValue(record.data.groupId);
    	this.groupUpdateWindow.groupForm.groupName.setValue(record.data.name);
    	this.groupUpdateWindow.show();
    },
    onRefreshClick: function() {
    	Ext.Msg.confirm("提醒信息", "1、与微信平台同步请谨慎使用！确定要同步请点是 ",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url: PROJECT_NAME+'/wxauth/group/refreshWxGroup',
			       	   method : 'POST', 
		               success: function(form, action) {
			               Ext.MessageBox.alert("系统提示:", BLANKSTR + "同步成功!" + BLANKSTR);
			               wxGroupGrid.store.load({params:{start:0, limit:PAGESIZE}});
		               },
		               failure: function(form, action) {
		            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "同步失败!" + BLANKSTR);
		               }
			       	});					
			}
    	});	
    },
    selectedRecord: function() {
        var record = this.getSelectionModel().getSelected();
        return record;
    },
    refresh: function(){
        this.getView().refresh();
    },
    remove:function(record){
        this.getStore().remove(record);
    }
});

/********************* onReady 组件渲染及处理 *******************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    wxGroupGrid = new WxGroupGrid(Ext.getBody().getViewSize().height);
    wxGroupGrid.store.load({params:{start:0, limit:PAGESIZE}});
    new Ext.Viewport({
    	layout: 'border',
    	items:[{
				region: "center",
				items:[
				       wxGroupGrid
				]
			}
    	]
    });
});