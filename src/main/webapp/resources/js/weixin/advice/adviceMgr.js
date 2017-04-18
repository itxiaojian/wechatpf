var ADVICE_GRID_STORE_URL = '/wxauth/advice/getAdvicePagination';
var PAGESIZE=20;

/***********************SendTextMessageForm组件**************************
 *author        ： zhu.zengpeng
 *description   : 给用户回复文本消息,FORM组件
 *date          : 2014-11-7
****************************************************************/
SendTextMessageForm = Ext.extend(Ext.ux.Form,{
	constructor:function(){
		this.openid = this.createHidden('openid', 'openid');
		this.toUser = this.createTextField('发送目标用户:', 'toUser', '100%');
		this.message = this.createTextArea('<font color="red">*</font>消息文本:', 'message', 100, '100%');
		
		this.message.allowBlank = false;

		SendTextMessageForm.superclass.constructor.call(this, {
        	anchor: '100%',
            autoHeight:true,
            labelWidth: 100,
            labelAlign :'right',
            frame: true,
            bodyStyle:"padding: 5px 5px 0",
            layout: 'tableform',
            layoutConfig: {columns: 1},
			items:[
			       this.toUser,
			       this.message,this.openid
			],
	        buttons:[
			         {text:'发送', width: 20,iconCls: 'save', hidden: false,handler:this.sendFormClick,scope:this},
			         {text:'关闭',width: 20,iconCls: 'delete', handler:this.closeClick, scope:this}
	        ],
			buttonAlign : 'center'
		});
	},
	sendFormClick: function(){
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: '/wxauth/advice/sendTextMessage',
				method: 'POST',
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"回复成功！"+BLANKSTR);
					adviceGrid.sendTextMessageWindow.hide();
				},
				failure: function(form,action){
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"回复失败！"+BLANKSTR);
				}
			});
		}
	},
	closeClick:function() {
		this.ownerCt.hide();
	}
	
});

/***********************SendTextMessageWindow组件*******************
 *author        ： zhu.zengpeng
 *description   : 
 *date          : 2014-11-7
******************************************************************/
SendTextMessageWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.sendTextMessageForm = new SendTextMessageForm(); 	
    	SendTextMessageWindow.superclass.constructor.call(this, {
    		title:'发送文本客户消息',
    		width: 600,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.sendTextMessageForm]
        });
    }
});

/**************************AdviceGrid*******************************************/
AdviceGrid = Ext.extend(UxGrid, {
	setRemarkWindow:null,
	vtbar:null,				//面板顶部的工具条
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.sendTextMessageWindow = new SendTextMessageWindow();
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: ADVICE_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},[
                   {name:'tsjyId'},{name:'openid'},{name:'tslr'},{name:'lxfs'},
                   {name:'createTime'},{name:'tslx'},{name:'dealFlag'},{name:'nickname'}
            ])
        });
    	this.vbbar= new Ext.PagingToolbar({ 
            pageSize: PAGESIZE, 
            store: this.store, 
            displayInfo: true, 
            displayMsg: '显示第 {0} 条到 {1} 条记录，一共 {2} 条', 
            emptyMsg: "没有记录" 
    	});
    	
    	var query_tslx = this.createMemoryCombo('投诉类型', 'code', 'name', '100', QUERY_TSLX);
    	query_tslx.store.load();
    	query_tslx.setValue(-1);
    	var query_dealflag = this.createMemoryCombo('处理状态', 'code', 'name', '100', QUERY_DEALFLAG);
    	query_dealflag.store.load();
    	query_dealflag.setValue(0);
    	this.vtbar = new Ext.Toolbar({
            items:[
               	{xtype:'label',text:'投诉类型:'},query_tslx,
            	'-',{xtype:'label',text:'处理状态:'},query_dealflag,
            	'-',{text:'查询',iconCls: 'query',
            		handler: function() {
            			var tslx = query_tslx.getValue();
            			var dealflag = query_dealflag.getValue();
            		    adviceGrid.store.baseParams = {tslx:tslx, dealFlag:dealflag};
            		    adviceGrid.store.load({params:{start:0, limit:PAGESIZE}});
            		},scope:this}
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        AdviceGrid.superclass.constructor.call(this, {
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
                {header:'tsjyId',dataIndex:'tsjyId',width:80,sortable: true, hidden:true},
                {header:'openid',dataIndex:'openid',width:100,sortable: true, hidden:true},
                {header:'类型',dataIndex:'tslx',width:60,sortable: true,
                	renderer:function(value, cellmeta, record){
                		if(value == 1) {
            			  return "<span style='color:red;font-weight:bold;'>投诉</span>";
                		}else if(value == 2) {
            			  return "<span style='color:green;font-weight:bold;'>建议</span>";
                		}
                	}
                },
                {header:'联系方式',dataIndex:'lxfs',width:120,sortable: true},
                {header:'微信昵称',dataIndex:'nickname',width:120,sortable: true},
                {header:'创建时间',dataIndex:'createTime',width:100,sortable: true},
                {header:'处理标志',dataIndex:'dealFlag',width:60,sortable: true,
                	renderer:function(value, cellmeta, record){
                		if(value == 0) {
            			  return "<span style='color:red;font-weight:bold;'>未处理</span>";
                		}else if(value == 1) {
            			  return "<span style='color:green;font-weight:bold;'>已处理</span>";
                		}
                	}
                },
                {header:'操作',dataIndex:'opt',width:200,sortable: true,
                	renderer:function(value, cellmeta, record){
                		if(record.data.dealFlag == 0) {
                			return "<a style='color:green;font-weight:bold;' href='javascript:void(0)' onclick='adviceGrid.onReplyWxUser()'>回复用户</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='color:#DB70DB;font-weight:bold;' href='javascript:void(0)' onclick='adviceGrid.resetDealflag()'>状态置为已处理</a>";
                		}else if(record.data.dealFlag == 1) {
                			return "<a href='javascript:void(0)' onclick='adviceGrid.onReplyWxUser()'>回复用户</a>";
                		}
                		
                	}
                },
                {header:'投诉/建议内容',dataIndex:'tslr',width:400,sortable: true,
                	renderer:function(value,metadata){ 
	                    var svalue = value.replace(/\s+/g,""); 
	                    metadata.attr = 'ext:qtitle="" ext:qtip=' + svalue; 
	                    return value; 
                   }
                }
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    onReplyWxUser: function() {//对选中的微信用户回复文本消息
    	var record = this.selectedRecord();
    	this.sendTextMessageWindow.sendTextMessageForm.getForm().reset();
    	this.sendTextMessageWindow.sendTextMessageForm.openid.setValue(record.data.openid);
    	this.sendTextMessageWindow.sendTextMessageForm.toUser.setValue(record.data.nickname);
    	this.sendTextMessageWindow.show();
    },
    resetDealflag: function() {//把选中的投诉建议处理状态更新为已处理
    	var record = this.selectedRecord();
    	Ext.Msg.confirm("提醒信息", "确定要把处理状态置为已处理？",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url:'/wxauth/advice/updateDealflag',
			       	   method : 'POST', 
			       	   params: { tsjyId: record.data.tsjyId},
		               success: function(form, action) {
			               Ext.MessageBox.alert("系统提示:", BLANKSTR + "更新成功!" + BLANKSTR);
			               adviceGrid.store.load({params:{start:0, limit:PAGESIZE}});
		               },
		               failure: function(form, action) {
		            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除失败!" + BLANKSTR);
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
    adviceGrid = new AdviceGrid(Ext.getBody().getViewSize().height);
    adviceGrid.store.baseParams = {tslx:-1, dealFlag:0};
    adviceGrid.store.load({params:{start:0, limit:PAGESIZE}});
    new Ext.Viewport({
    	layout: 'border',
    	items:[{
				region: "center",
				items:[
				       adviceGrid
				]
			}
    	]
    });
});