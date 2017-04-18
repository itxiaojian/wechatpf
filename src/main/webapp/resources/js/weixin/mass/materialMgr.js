var USER_GRID_STORE_URL = '/wxauth/mass/getWxUserList';
var GROUP_COMBO_STORE_URL = '/wxauth/mass/getWxGroupList';
var PAGESIZE=20;

/*******************设置备注名表单**********************/
MassInfoForm = Ext.extend(Ext.ux.Form,{
	constructor:function(){
		this.shuoming = new Ext.form.DisplayField({
			value:'<span  style="color:blue;font-weight:bold;">为保障用户体验，微信公众平台严禁恶意营销以及诱导分享朋友圈，严禁发布色情低俗、暴力血腥、政治谣言等各类违反法律法规及相关政策规定的信息。一旦发现，我们将严厉打击和处理。</span>'
		});
		this.toObject = this.createCombo('群发对象:', 'groupId', 'groupName', 'toObject', '50%', PROJECT_NAME+'/wxauth/mass/getGroupCombo');
		this.toObject.store.load();
		this.toObject.allowBlank = false;
        this.content = this.createTextArea('','',100, '100%');
		this.fieldset1 = new Ext.form.FieldSet({
			title:'',
			tbar :new Ext.Toolbar({
	            items:[
	                   {text:'文字',iconCls: 'pencil',handler:this.onWzClick,scope:this},
	                   '-',{text:'图片',iconCls: 'image',handler:this.onTpClick,scope:this},
	                   '-',{text:'语音',iconCls: 'sound',handler:this.onYyClick,scope:this},
	                   '-',{text:'视频',iconCls: 'television',handler:this.onSpClick,scope:this},
	                   '-',{text:'图文消息',iconCls: 'images',handler:this.onTwxxClick,scope:this},
	                   '->',{xtype:'displayfield', id:'toolbarDisplayID',value:'当前模式: <span  style="color:red;font-weight:bold;">发送文字</span>'}
	                   
	               ]
	           }),
			items:[this.content]
		});
		MassInfoForm.superclass.constructor.call(this, {
			title:'<div>新建群发消息</div><div align="right"><a href="http://kf.qq.com/faq/120911VrYVrA131025QniAfu.html" target="_blank">群发消息规则说明</a></div>',
        	anchor: '100%',
            autoHeight:true,
            labelWidth: 100,
            labelAlign :'right',
            frame: true,
            bodyStyle:"padding: 5px 5px 0",
            layout: 'tableform',
            layoutConfig: {columns: 1},
			items:[
			       this.shuoming,
			       this.toObject,
			       this.fieldset1
			       
			],
	        buttons:[
			         {text:'群发', width: 20,iconCls: 'save', hidden: false,handler:this.massFormClick,scope:this}
	        ],
			buttonAlign : 'center'
		});
	},
	onWzClick: function(){//点击文字
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送文字</span>');
		massInfoForm.content.setReadOnly(false);
	},
	onTpClick: function(){//点击图片
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送图片</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
	},
	onYyClick: function(){//点击语音
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送语音</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
	},
	onSpClick: function(){//点击视频
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送视频</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
	},
	onTwxxClick: function(){//点击图文消息
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送图文信息</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
	},
	massFormClick: function() {//群发
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: PROJECT_NAME+'/wxauth/mass/massMessage',
			//	params:{openids:openids},
				method: 'POST',
				success: function(form,action){
				},
				failure: function(form,action){
				}
			});
		}
	},
	closeClick:function() {
		this.ownerCt.hide();
	}
	
});
/*****************SetRemarkWindow**********************/
SetRemarkWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.setRemarkForm = new SetRemarkForm(); 	
    	SetRemarkWindow.superclass.constructor.call(this, {
    		title:'设置微信用户备注名',
    		width: 400,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.setRemarkForm]
        });
    }
});


/***********************SetGroupForm组件**************************
 *author        ： zhu.zengpeng
 *description   : SetGroupForm设置分组FORM组件
 *date          : 2014-11-7
****************************************************************/
SetGroupForm = Ext.extend(Ext.ux.Form,{
	constructor:function(){
		this.openids;
		this.groupId = this.createCombo('<font color="red">*</font>分组名:', 'groupId', 'name', 'groupId', '95%', PROJECT_NAME+""+GROUP_COMBO_STORE_URL);
		
		this.groupId.allowBlank = false;
		this.groupId.store.load();
		SetGroupForm.superclass.constructor.call(this, {
        	anchor: '100%',
            autoHeight:true,
            labelWidth: 100,
            labelAlign :'right',
            frame: true,
            bodyStyle:"padding: 5px 5px 0",
            layout: 'tableform',
            layoutConfig: {columns: 1},
			items:[
			       this.groupId
			],
	        buttons:[
			         {text:'保存', width: 20,iconCls: 'save', hidden: false,handler:this.addFormClick,scope:this},
			         {text:'关闭',width: 20,iconCls: 'delete', handler:this.closeClick, scope:this}
	        ],
			buttonAlign : 'center'
		});
	},
	addFormClick: function(){
		var openids = this.openids;
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: PROJECT_NAME+'/wxauth/user/setUserGroup',
				params:{openids:openids},
				method: 'POST',
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"保存成功！"+BLANKSTR);
					wxUserGrid.store.load({params:{start:0,limit:PAGESIZE}});
					wxUserGrid.setGroupWindow.hide();
				},
				failure: function(form,action){
					wxUserGrid.setRemarkWindow.hide();
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"保存失败！"+BLANKSTR);
				}
			});
		}
	},
	closeClick:function() {
		this.ownerCt.hide();
	}
	
});

/***********************SetGroupWindow组件**************************
 *author        ： zhu.zengpeng
 *description   : 
 *date          : 2014-11-7
******************************************************************/
SetGroupWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.setGroupForm = new SetGroupForm(); 	
    	SetGroupWindow.superclass.constructor.call(this, {
    		title:'设置微信用户分组',
    		width: 400,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.setGroupForm]
        });
    }
});

/**************************WxUserGrid*******************************************/
WxUserGrid = Ext.extend(UxGrid, {
	setRemarkWindow:null,
	vtbar:null,				//面板顶部的工具条
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.setRemarkWindow = new SetRemarkWindow();
    	this.setGroupWindow = new SetGroupWindow();
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+USER_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},[
                   {name:'userId'},{name:'groupId'},{name:'subscribe'},{name:'openid'},{name:'nickname'},
                   {name:'sex'},{name:'ULanguage'},{name:'city'},{name:'province'},{name:'country'},{name:'headimgurl'}
                   ,{name:'subscribeTime'},{name:'unionid'},{name:'remark'},{name:'memo'},{name:'groupName'}
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
                {text:'设置备注名',iconCls: 'add',handler:this.onSetRemarkClick,scope:this},
                '-',{text:'分组',iconCls: 'edit',handler:this.onSetGruopClick,scope:this},
                '-',{text:'与微信平台同步',iconCls: 'refresh',handler:this.onRefreshClick,scope:this}
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        WxUserGrid.superclass.constructor.call(this, {
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
                {header:'userId',dataIndex:'userId',width:80,sortable: true, hidden:true},
                {header:'用户的昵称',dataIndex:'nickname',width:180,sortable: true,
            		renderer:function(data, metadata, record){
            			var headimgurl = record.data.headimgurl;
            			return "<img height=49 width=49 src = '" + headimgurl + "' onclick=\"window.open('" + headimgurl + "')\">" + data + "</img>";
            		}
                },
                {header:'备注名',dataIndex:'remark',width:120,sortable: true},
                {header:'所属分组',dataIndex:'groupName',width:120,sortable: true},
                {header:'性别',dataIndex:'sex',width:50,sortable: true,
            		renderer:function(data, metadata, record){
	            		if(data ==1){
	            			return '男';
	            		}else if(data ==2){
	            			return '女';
	            		}else if(data ==0){
	            			return '未知';
	            		}else{
	            			return data;
	            		}
            		}
            	},
            	{header:'用户所在国家',dataIndex:'country',width:100,sortable: true},
            	{header:'用户所在省份',dataIndex:'province',width:100,sortable: true},
            	{header:'用户所在城市',dataIndex:'city',width:100,sortable: true},
            	{header:'是否关注本公众号',dataIndex:'subscribe',width:150,sortable: true,
                	renderer:function(data, metadata, record){
	            		if(data ==0){
	            			return "<span style='color:red;font-weight:bold;'>未关注</span>";
	            		}else if(data ==1){
	            			return "<span style='color:green;font-weight:bold;'>关注</span>";
	            		}else{
	            			return data;
	            		}
                	}
                },
            	{header:'OPENID',dataIndex:'openid',width:200,sortable: true},
                {header:'性别',dataIndex:'sex',width:50,sortable: true,
            		renderer:function(data, metadata, record){
	            		if(data ==1){
	            			return '男';
	            		}else if(data ==2){
	            			return '女';
	            		}else if(data ==0){
	            			return '未知';
	            		}else{
	            			return data;
	            		}
            		}
            	},
            	{header:'用户的语言',dataIndex:'ULanguage',width:100,sortable: true},
            	{header:'用户关注时间',dataIndex:'subscribeTime',width:140,sortable: true}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    onSetRemarkClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				var openid = records[0].data.openid;
   				this.setRemarkWindow.setRemarkForm.getForm().reset();
   				this.setRemarkWindow.setRemarkForm.openid.setValue(openid);
   				this.setRemarkWindow.show();
   			}else{
   				Ext.Msg.alert('系统提示', '不允许同时设置多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}      	
    },
    onSetGruopClick: function() {
    	var records=this.getSelectionModel().getSelections();
    	if(records.length == 0) {
    		Ext.Msg.alert('系统提示', '请至少选择一条记录');
    		return;
    	}
    	var openids = new Array();
    	for(var i=0;i<records.length;i++) {
    		var openid = records[i].data.openid;
    		openids.push(openid);
    	}
    	this.setGroupWindow.setGroupForm.getForm().reset();
    	this.setGroupWindow.setGroupForm.openids = openids;
    	this.setGroupWindow.show();
    },
    onRefreshClick:function() {
    	Ext.Msg.confirm("提醒信息", "1、与微信平台同步请谨慎使用！确定要同步请点是  2、同步时间过长会提示同步失败,此时不要在次点击同步 ",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url:PROJECT_NAME+'/wxauth/user/refreshWxUser',
			       	   method : 'POST', 
		               success: function(form, action) {
			               Ext.MessageBox.alert("系统提示:", BLANKSTR + "同步成功!" + BLANKSTR);
			               wxUserGrid.store.load({params:{start:0, limit:PAGESIZE}});
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
    massInfoForm = new MassInfoForm(Ext.getBody().getViewSize().height);
    new Ext.Viewport({
    	layout: 'border',
    	items:[{
				region: "center",
				items:[
				       massInfoForm
				]
			}
    	]
    });
});