var IMAGE_GRID_STORE_URL = '/wxauth/mass/getImageList';//图片素材
var VOICE_GRID_STORE_URL = '/wxauth/mass/getVoiceList';//语音素材
var VIDEO_GRID_STORE_URL = '/wxauth/mass/getVideoList';//视频素材
var MPNEWS_GRID_STORE_URL = '/weixin/twxx/getTwxxztList';//图文消息
var MBXX_GRID_STORE_URL = '/weixin/template/getTemplateNewsList';//模版消息
var DICT_JG_URL = '/sys/SysArea/orgOrMebTree';

var DICT_YHFZ_URL = '/wxutil/yhfz/getowerlist';
var PAGESIZE=20;

/*******************群发FORM**********************/
MassInfoForm = Ext.extend(Ext.ux.Form,{
	constructor:function(){
		this.msgType = this.createHidden('msgtype', 'msgtype');
		this.msgType.setValue("text");
		this.shuoming = new Ext.form.DisplayField({
			value:'<span  style="color:red;font-weight:bold;">注意：为保障用户体验，微信公众平台严禁恶意营销以及诱导分享朋友圈，严禁发布色情低俗、暴力血腥、政治谣言等各类违反法律法规及相关政策规定的信息。一旦发现，我们将严厉打击和处理。</br></br></span>'
		});
		this.shuoming1 = new Ext.form.DisplayField({
			value:'<span  style="color:red;font-weight:bold;"><br></span>'
		});
		this.shuoming2 = new Ext.form.DisplayField({
			value:'<span  style="color:red;font-weight:bold;"><br></span>'
		});
//		this.toObject = this.createCombo('群发对象:', 'groupId', 'groupName', 'toObject', '20%', PROJECT_NAME+'/wxauth/mass/getGroupCombo');
//		this.toObject.store.load();
//		this.toObject.allowBlank = false;
		
//        var toObject = new Ext.form.ComboBox({
//        	colspan:'',
//        	autoLoad: true,
//            fieldLabel: '群发分组',
//            emptyText: '请选择...',
//            isFormField: true,
//            anchor: '20%',
//            mode: 'local',
//            hiddenName :'toObject',
//            name:'toObject',
//            allowBlank: false,
//            blankText:'请选择...',
//            forceSelection: true,
//            lastQuery: '',
//            triggerAction: 'all',
//            displayField:'groupName',
//            valueField:'groupId',
//            store: new Ext.data.Store({
//                proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/wxauth/mass/getGroupCombo', method: 'POST'}),
//                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'groupId'},{name:'groupName'},{name:''},{name:''}]))
//            }),
//            editable : false,
//            listeners: {
//                'select': function(toObject, record, index){  
//                    /*获取选中省份ID*/  
//                    var groupId = toObject.value;  
//                    toUser.store.baseParams = {
//    			    		groupId:groupId
//    			    	};
//                    toUser.store.load();
//                }
//            }  
//        });
//        toObject.store.load();
//        toObject.allowBlank = false;
//        
//		var toUser = new Ext.ux.form.LovCombo({ 
//			id:'LUser',
//        	colspan:'',
//        	autoLoad: true,
//            fieldLabel: '分组用户',
//            emptyText: '请选择...',
//            isFormField: true,
//            anchor: '95%',
//            mode: 'local',
//            hiddenName :'toUser',
//            name:'toUsers',
//            allowBlank: false,
//            blankText:'请选择...',
//            forceSelection: true,
//            lastQuery: '',
//            triggerAction: 'all',
//            showSelectAll   : true,
//            displayField:'nickname',
//            valueField:'openid',
//			store: new Ext.data.Store({
//                proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/wxauth/mass/getUserByGroup', method: 'POST'}),
//                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'openid'},{name:'nickname'},{name:''},{name:''}]))
//			}) 
//        });
//		toUser.allowBlank = false;
		
//        var toObject =  new Ext.ux.TreeCombo({
//        	id:'LUser',
//    		fieldLabel:'<font color="red">*</font> 选择分组：',
//    		rootVisible: false,
//    		url: PROJECT_NAME+""+DICT_JG_URL,
//    		allowBlank: false,
//    		hiddenName: 'jg',
//    		name:'jgmc',
//    		rootName: 'jgmc',
//    		anchor: '40%',
//            displayField:'jg',
//            valueField:id,
//            listeners : {
//            	'select':function(combo){  
//                	var jgId = combo.getValue();  
//                    toUser.store.baseParams = {
//    			    		jgId:jgId
//    			    	};
//                    toUser.store.load();
//            	} 
//            }
//		});
        
        var toObject = new Ext.form.ComboBox({
        	colspan:'',
        	autoLoad: true,
        	fieldLabel:'选择分组：',
            emptyText: '请选择...',
            isFormField: true,
            anchor: "40%",
            mode: 'local',
            hiddenName :'fzid',
            name:'fzmc',
            allowBlank: false,
            blankText:'请选择...',//0130608403
            forceSelection: true,
            lastQuery: '',
            triggerAction: 'all',
            displayField:'name',
            valueField:'id',
            store: new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+DICT_YHFZ_URL, method: 'POST'}),
                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'id'},{name:'name'}]))
            }),
            listeners : {
            	'select':function(combo){  
                	var fzid = combo.getValue();  
                    toUser.store.baseParams = {
    			    		fzid:fzid
    			    	};
                    toUser.store.load();
            	} 
            },
            editable : false
        });
        
        toObject.store.load();
        toObject.allowBlank = true;
        
		var toUser = new Ext.ux.form.LovCombo({
			//id:'LUser',
        	colspan:'',
        	autoLoad: true,
            fieldLabel: '分组用户：',
            emptyText: '请选择...',
            isFormField: true,
            anchor: '100%',
            mode: 'local',
            hiddenName :'toUser',
            name:'toUsers',
            allowBlank: false,
            blankText:'请选择...',
            forceSelection: true,
            lastQuery: '',
            triggerAction: 'all',
            showSelectAll   : true,
            displayField:'text',
            valueField:'id',
			store: new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/wxutil/yhfz/getmenberlist', method: 'POST'}),
                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'id'},{name:'text'},{name:''},{name:''}]))
			}) 
        });
		toUser.allowBlank = true;
		
		var isAll = this.createHidden('isAll', 'isAll');
		isAll.setValue(1);
		
		var myCheckboxGroup = new Ext.form.CheckboxGroup({   
		    xtype: 'checkboxgroup',   
		    name: 'checkboxgroup',   
		    width: 80,  //宽度220   
		    columns: 1,  //在上面定义的宽度上展示3列   
		    fieldLabel: '全体群发：',   
		    items: [   
		        {boxLabel: '全体群发', name: '1', checked: true,listeners : {
					'check' : function(){
						if(!this.checked){
							toObject.hidden = false;
							toUser.hidden = false;
							isAll.setValue(0);
						}else{
							toObject.hidden = true;
							toUser.hidden = true;
							isAll.setValue(1);
						}
					}}
		        }
		    ]   
		}); 
		
		
/*        var userValue = this.createTextField('<font color="red">*</font>菜单级别','dqjb','95%','1',null,100,'长度超过不能100');
        userValue.setValue(Ext.getCmp('LUser').value);*/
		
        this.content = this.createTextArea('','content',200, '100%');
		this.fieldset1 = new Ext.form.FieldSet({
			title:'',
			tbar :new Ext.Toolbar({
	            items:[
	                   {text:'文字',iconCls: 'pencil',handler:this.onWzClick,scope:this},
	                   '-',{text:'图片',iconCls: 'image',handler:this.onTpClick,scope:this},
	                   '-',{text:'语音',iconCls: 'sound',handler:this.onYyClick,scope:this},
	                   '-',{text:'视频',iconCls: 'television',handler:this.onSpClick,scope:this},
	                   '-',{text:'图文消息',iconCls: 'images',handler:this.onTwxxClick,scope:this},
	                   '-',{text:'模版消息',iconCls: 'template',handler:this.onMbxxClick,scope:this},
	                   '->',{xtype:'displayfield', id:'toolbarDisplayID',value:'当前模式: <span  style="color:red;font-weight:bold;">发送文字</span>'},
	                   {xtype:'displayfield', value:'----'},
	                   {xtype:'displayfield', id:'toolbarDisplay222ID',value:''}     
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
			       myCheckboxGroup,
			       toObject,
			       this.shuoming1,
			       toUser,
			       this.shuoming2,
			       this.fieldset1,
			       this.msgType,
			       isAll
			       
			],
	        buttons:[
			         {text:'确认群发', width: 20,iconCls: 'save', hidden: false,handler:this.massFormClick,scope:this}
//			         {text:'效果测试(预览)', width: 20,iconCls: 'save', hidden: false,handler:this.massTestClick,scope:this}
	        ],
			buttonAlign : 'center'
		});
	},
	onWzClick: function(){//点击文字
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送文字</span>');
		massInfoForm.content.setReadOnly(false);
		this.msgType.setValue("text");
		Ext.getCmp("toolbarDisplay222ID").setValue("");
	},
	onTpClick: function(){//点击图片
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送图片</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
		imageSelectWindow.show();
		imageSelectWindow.imageSelectGrid.store.load();
		this.msgType.setValue("image");
		Ext.getCmp("toolbarDisplay222ID").setValue('<span  style="color:green;font-weight:bold;">未选择</span>');
	},
	onYyClick: function(){//点击语音
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送语音</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
		this.msgType.setValue("voice");
		voiceSelectWindow.show();
		voiceSelectWindow.voiceSelectGrid.store.load();
		Ext.getCmp("toolbarDisplay222ID").setValue('<span  style="color:green;font-weight:bold;">未选择</span>');
	},
	onSpClick: function(){//点击视频
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送视频</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
		this.msgType.setValue("mpvideo");
		videoSelectWindow.show();
		videoSelectWindow.videoSelectGrid.store.load();
		Ext.getCmp("toolbarDisplay222ID").setValue('<span  style="color:green;font-weight:bold;">未选择</span>');
	},
	onTwxxClick: function(){//点击图文消息
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送图文信息</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
		this.msgType.setValue("twxx");
		mpnewsSelectWindow.show();
		//mpnewsSelectWindow.mpnewsSelectGrid.store.load();
		mpnewsSelectWindow.mpnewsSelectGrid.store.baseParams = {
	    		limit:PAGESIZE
	    };
		mpnewsSelectWindow.mpnewsSelectGrid.store.load({params:{start:0}});
		Ext.getCmp("toolbarDisplay222ID").setValue('<span  style="color:green;font-weight:bold;">未选择</span>');
	},
	onMbxxClick: function(){//点击模版消息
		Ext.getCmp("toolbarDisplayID").setValue('当前模式: <span  style="color:red;font-weight:bold;">发送模版消息</span>');
		massInfoForm.content.setValue("");
		massInfoForm.content.setReadOnly(true);
		this.msgType.setValue("templatenews");
		mbxxSelectWindow.show();
		mbxxSelectWindow.mbxxSelectGrid.store.baseParams = {
	    		limit:PAGESIZE
	    };
		mbxxSelectWindow.mbxxSelectGrid.store.load({params:{start:0}});
		Ext.getCmp("toolbarDisplay222ID").setValue('<span  style="color:green;font-weight:bold;">未选择</span>');
	},
	massFormClick: function() {//群发	
		var thiz = this;
//		alert(Ext.getCmp('LUser').value);
		if(thiz.getForm().isValid()){
	    	Ext.Msg.confirm("提醒信息", "确定开始群发?",function(btn){
				if (btn == 'yes') {
					thiz.getForm().submit({
						waitMsg: '正在提交数据...',
						url: PROJECT_NAME+'/wxauth/mass/massMessageOneByOne',
						method: 'POST',
						success: function(form,action){
							Ext.MessageBox.alert("系统提示:", BLANKSTR + "群发成功!" + BLANKSTR);
						},
						failure: function(form,action){
							Ext.MessageBox.alert("系统提示:", BLANKSTR + "群发失败!" + BLANKSTR);
						}
					});					
				}
	    	});
		}
	},
	massTestClick: function() {//群发消息预览
		yulanWindow.show();
	},
	closeClick:function() {
		this.ownerCt.hide();
	}
	
});

/*****************************ImageSelectGrid*****************
 *description   : 群发时选择图片素材列表
*************************************************************/
ImageSelectGrid = Ext.extend(UxGrid, {
    store:null,
    constructor: function(height, width){

    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+IMAGE_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({fields:[
                   {name:'scName'},{name:'fileCode'},{name:'mediaId'},{name:'createTime'}
            ]})
        });
    	
        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        ImageSelectGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: true,
            height: 300,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header:'图片',dataIndex:'scName',width:220,sortable: true,
            		renderer:function(data, metadata, record){
            			var srcurl = PROJECT_NAME+'/wxauth/mass/' + record.data.fileCode +'/show';
            			return "<img height=49 width=49 src = '" + srcurl + "'>" + data + "</img>";
            		}
                },
                {header:'查看大图',dataIndex:'scName',width:100,sortable: true,
            		renderer:function(data, metadata, record){
            			var srcurl = PROJECT_NAME+'/wxauth/mass/' + record.data.fileCode +'/show';
            			return "<a href='" +srcurl + "'>查看大图</a>";
            		}
                }
            ]),
            ds: this.store
        });
    },
    selectedRecord: function() {
        var record = this.getSelectionModel().getSelected();
        return record;
    }
});
/*****************************ImageSelectWindow***************
 *description   : 群发时选择图片素材窗口
*************************************************************/
ImageSelectWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.imageSelectGrid = new ImageSelectGrid(); 	
    	ImageSelectWindow.superclass.constructor.call(this, {
    		title:'选择图片',
    		width: 450,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.imageSelectGrid],
        	buttonAlign:'center',
	        buttons:[
			         {text:'确定', width: 20,iconCls: 'save', handler:this.addFormClick,scope:this},
			         {text:'取消',width: 20,iconCls: 'delete', handler:this.closeClick, scope:this}
	        ]	
    	});
    },
    addFormClick: function() {
    	var record = this.imageSelectGrid.selectedRecord();
    	massInfoForm.content.setValue(record.data.mediaId);
    	imageSelectWindow.hide();
    	Ext.getCmp("toolbarDisplay222ID").setValue(record.data.scName);
    },
    closeClick: function() {
    	this.hide();
    }
    
});


/*****************************VoiceSelectGrid*****************
 *description   : 群发时选择语音素材列表
*************************************************************/
VoiceSelectGrid = Ext.extend(UxGrid, {
    store:null,
    constructor: function(height, width){

    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+VOICE_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({fields:[
                   {name:'scName'},{name:'fileCode'},{name:'mediaId'},{name:'createTime'}
            ]})
        });
    	
        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        VoiceSelectGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: true,
            height: 300,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header:'语音名称',dataIndex:'scName',width:180,sortable: true},
                {header:'创建时间',dataIndex:'createTime',width:140,sortable: true}
            ]),
            ds: this.store
        });
    },
    selectedRecord: function() {
        var record = this.getSelectionModel().getSelected();
        return record;
    }
});
/*****************************VoiceSelectWindow***************
 *description   : 群发时选择语音素材窗口
*************************************************************/
VoiceSelectWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.voiceSelectGrid = new VoiceSelectGrid(); 	
    	VoiceSelectWindow.superclass.constructor.call(this, {
    		title:'选择语音',
    		width: 450,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.voiceSelectGrid],
        	buttonAlign:'center',
	        buttons:[
			         {text:'确定', width: 20,iconCls: 'save', handler:this.addFormClick,scope:this},
			         {text:'取消',width: 20,iconCls: 'delete', handler:this.closeClick, scope:this}
	        ]	
    	});
    },
    addFormClick: function() {
    	var record = this.voiceSelectGrid.selectedRecord();
    	massInfoForm.content.setValue(record.data.mediaId);
    	voiceSelectWindow.hide();
    	Ext.getCmp("toolbarDisplay222ID").setValue(record.data.scName);
    },
    closeClick: function() {
    	this.hide();
    }
    
});

/*****************************VideoSelectGrid*****************
 *description   : 群发时选择视频素材列表
*************************************************************/
VideoSelectGrid = Ext.extend(UxGrid, {
    store:null,
    constructor: function(height, width){

    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+VIDEO_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({fields:[
                   {name:'scName'},{name:'fileCode'},{name:'mediaId'},{name:'createTime'}
            ]})
        });
    	
        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        VideoSelectGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: true,
            height: 300,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header:'视频名称',dataIndex:'scName',width:180,sortable: true},
                {header:'创建时间',dataIndex:'createTime',width:140,sortable: true}
            ]),
            ds: this.store
        });
    },
    selectedRecord: function() {
        var record = this.getSelectionModel().getSelected();
        return record;
    }
});
/*****************************VideoSelectWindow***************
 *description   : 群发时选择视频素材窗口
*************************************************************/
VideoSelectWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.videoSelectGrid = new VideoSelectGrid(); 	
    	VideoSelectWindow.superclass.constructor.call(this, {
    		title:'选择视频',
    		width: 450,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.videoSelectGrid],
        	buttonAlign:'center',
	        buttons:[
			         {text:'确定', width: 20,iconCls: 'save', handler:this.addFormClick,scope:this},
			         {text:'取消',width: 20,iconCls: 'delete', handler:this.closeClick, scope:this}
	        ]	
    	});
    },
    addFormClick: function() {
    	var record = this.videoSelectGrid.selectedRecord();
    	massInfoForm.content.setValue(record.data.mediaId);
    	videoSelectWindow.hide();
    	Ext.getCmp("toolbarDisplay222ID").setValue(record.data.scName);
    },
    closeClick: function() {
    	this.hide();
    }
    
});

/*****************************MpnewsSelectGrid*****************
 *description   : 群发时选择图文消息素材列表
*************************************************************/
MpnewsSelectGrid = Ext.extend(UxGrid, {
//    store:null,
//    constructor: function(height, width){
//
//    	this.store = new Ext.data.Store({          //Grid Store
//            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+MPNEWS_GRID_STORE_URL, method: 'POST'}),
//            reader: new Ext.data.JsonReader({fields:[
//                   {name:'xxbt'},{name:'id'},{name:'tjsj'},{name:'tjrxm'},{name:'tjrbh'}
//            ]})
//        });
//    	
//        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
//        MpnewsSelectGrid.superclass.constructor.call(this, {
//        	region:'center',
//        	stripeRows: true,
//            frame: true,
//            height: 300,
//            viewConfig: {
//                forceFit: false
//            },
//            loadMask: {
//                msg : '正在载入数据,请稍候...'
//            },
//            sm: sm,
//            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
//                                          sm,
//                {header:'图文消息标题',dataIndex:'xxbt',width:180,sortable: true},                           
//                {header:'创建时间',dataIndex:'tjsj',width:140,sortable: true}
//            ]),
//            ds: this.store
//        });
//    },
//    selectedRecord: function() {
//        var record = this.getSelectionModel().getSelected();
//        return record;
//    }
    store:null,
    constructor: function(height, width){

    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+MPNEWS_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
            	{name:'xxbt'},{name:'id'},{name:'tjsj'},{name:'tjrxm'},{name:'tjrbh'}
            ])
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        MpnewsSelectGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: true,
            height: 300,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header:'id',dataIndex:'id',width:180,sortable: true,hidden:true},
	            {header:'图文消息标题',dataIndex:'xxbt',width:180,sortable: true},                           
	            {header:'创建时间',dataIndex:'tjsj',width:140,sortable: true}
            ]),
            bbar: this.vbbar,
            ds: this.store
        });
    },
    selectedRecord: function() {
        var record = this.getSelectionModel().getSelected();
        return record;
    }
});
/*****************************MpnewsSelectWindow***************
 *description   : 群发时选择图文消息素材窗口
*************************************************************/
MpnewsSelectWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.mpnewsSelectGrid = new MpnewsSelectGrid(); 	
    	MpnewsSelectWindow.superclass.constructor.call(this, {
    		title:'选择图文消息',
    		width: 450,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.mpnewsSelectGrid],
        	buttonAlign:'center',
	        buttons:[
			         {text:'确定', width: 20,iconCls: 'save', handler:this.addFormClick,scope:this},
			         {text:'取消',width: 20,iconCls: 'delete', handler:this.closeClick, scope:this}
	        ]	
    	});
    },
    addFormClick: function() {
    	var record = this.mpnewsSelectGrid.selectedRecord();
    	massInfoForm.content.setValue(record.data.id);
    	mpnewsSelectWindow.hide();
    },
    closeClick: function() {
    	this.hide();
    }
    
});

/*****************************MbxxSelectGrid*****************
 *description   : 群发时选择模版消息素材列表
*************************************************************/
MbxxSelectGrid = Ext.extend(UxGrid, {
    store:null,
    constructor: function(height, width){

    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+MBXX_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
            	{name:'id'}, {name:'title'},{name:'templateid'}, {name:'url'}, {name:'topcolor'}, {name:'bz'},{name:'addtime'}
            ])
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        MbxxSelectGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: true,
            height: 300,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header:'id',dataIndex:'id',width:180,sortable: true,hidden:true},
                {header:'消息标题',dataIndex:'title',width:180,sortable: true},
                {header:'创建时间',dataIndex:'addtime',width:140,sortable: true}
            ]),
            bbar: this.vbbar,
            ds: this.store
        });
    },
    selectedRecord: function() {
        var record = this.getSelectionModel().getSelected();
        return record;
    }
});
/*****************************MbxxSelectWindow***************
 *description   : 群发时选择模版消息素材窗口
*************************************************************/
MbxxSelectWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.mbxxSelectGrid = new MbxxSelectGrid(); 	
    	MbxxSelectWindow.superclass.constructor.call(this, {
    		title:'选择模版消息',
    		width: 450,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.mbxxSelectGrid],
        	buttonAlign:'center',
	        buttons:[
			         {text:'确定', width: 20,iconCls: 'save', handler:this.addFormClick,scope:this},
			         {text:'取消',width: 20,iconCls: 'delete', handler:this.closeClick, scope:this}
	        ]	
    	});
    },
    addFormClick: function() {
    	var record = this.mbxxSelectGrid.selectedRecord();
    	massInfoForm.content.setValue(record.data.id);
    	mbxxSelectWindow.hide();
    },
    closeClick: function() {
    	this.hide();
    }
    
});

/***********************YulanForm组件**************************
 *author        ： zhangyi
 *description   : 
 *date          : 2015-06-01
****************************************************************/
YulanForm = Ext.extend(Ext.ux.Form,{
	constructor:function(){
		this.touser = this.createTextField('<font color="red">*</font>测试接收用户OPENID:', 'touser', '95%');		
		this.touser.allowBlank = false;
        
		YulanForm.superclass.constructor.call(this, {
        	anchor: '100%',
            autoHeight:true,
            labelWidth: 100,
            labelAlign :'right',
            frame: true,
            bodyStyle:"padding: 5px 5px 0",
            layout: 'tableform',
            layoutConfig: {columns: 1},
			items:[
			       this.touser
			],
	        buttons:[
				{text: '发送', width: 20,iconCls: 'save', hidden: false,handler:this.sendFormClick,scope:this},
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
	        ],
			buttonAlign : 'center'
		});
	},
	sendFormClick: function(){
		var msgtype = massInfoForm.msgType.getValue();
		var content = massInfoForm.content.getValue();
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: PROJECT_NAME+'/wxauth/mass/massPreview',
				params: {msgtype:msgtype, content:content},
				method: 'POST',
				success: function(form, action){
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"发送成功！"+BLANKSTR);		
					yulanWindow.hide();
				},
				failure: function(form, action){
					Ext.MessageBox.alert("系统提示：",BLANKSTR+"发送失败！原因："+ action.result.message + BLANKSTR);		
					yulanWindow.hide();
				}
			});
		}
	},
	onCloseClick:function() {
		this.ownerCt.hide();
	}
	
});

/*****************************YulanWindow********************
 *description   : 预览发送时选择窗口
*************************************************************/
YulanWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.yulanForm = new YulanForm(); 	
    	YulanWindow.superclass.constructor.call(this, {
    		title:'群发预览',
    		width: 450,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.yulanForm]	
    	});
    }    
});
/********************* onReady 组件渲染及处理 *******************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    massInfoForm = new MassInfoForm(Ext.getBody().getViewSize().height);
    imageSelectWindow = new ImageSelectWindow();
    voiceSelectWindow = new VoiceSelectWindow();
    videoSelectWindow = new VideoSelectWindow();
    mpnewsSelectWindow = new MpnewsSelectWindow();
    mbxxSelectWindow = new MbxxSelectWindow();
    yulanWindow = new YulanWindow();
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