var ENTITY_URL_LIST = "/wxauth/wangdian/getWangdianPagination";
var ENTITY_URL_SAVE = "/wxauth/wangdian/saveWangdianInfo";
var ENTITY_URL_UPDATE = "/wxauth/wangdian/updateWangdianInfo";
var ENTITY_URL_DELETE = "/wxauth/wangdian/deleteWangdianInfo";
var PAGESIZE=50;
/*
 * WX_WANGDIAN_INFO 网点信息维护页面
 * @author zhangdongdong
 * @date 2014-11-19
 */
WxWangdianInfo = Ext.extend(Ext.ux.Form,{
	constructor: function(){
		this.wdId = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>网点编号',
            name: 'wdId',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:true
        });
		this.wdName = this.createTextField('<font color="red">*</font>网点名称','wdName','95%','',null,100,'长度超过不能100');
		this.wdAddress = this.createTextField('<font color="red">*</font>网点地址','wdAddress','95%','',null,200,'长度超过不能200');
		this.phone = this.createTextField('联系方式','phone','95%','',null,50,'长度超过不能50');
//		this.phone = new Ext.form.TextField({
//		    fieldLabel: '联系方式',
//		    anchor: '95%',
//		    name: 'phone',
//		    regex: /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/,
//		    regexText: '请输入有效手机号！'
//
//		});
		
		this.yysj = this.createTextField('营业时间','yysj','95%','',null,50,'长度超过不能50');
//		this.yysj = this.createDateField('营业时间','yysj','Ymd','95%');
//		this.yysj.value = new Date().format('Ymd');
//		this.yysj.allowBlank = false;
		this.bd09Lng = this.createTextField('经度','bd09Lng','95%','',null,50,'长度超过不能50');
		
//		this.bd09Lng = new Ext.form.TextField({
//		    fieldLabel: '经度',
//		    anchor: '95%',
//		    name: 'bd09Lng',
//		    regex: /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,8}$/,
//		    regexText: '请输入有效的经度！'
//
//		});
		
		this.bd09Lat = this.createTextField('纬度','bd09Lat','95%','',null,50,'长度超过不能50');
//		this.bd09Lat = new Ext.form.TextField({
//		    fieldLabel: '纬度',
//		    anchor: '95%',
//		    name: 'bd09Lat',
//		    regex: /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,8}$/,
//		    regexText: '请输入有效的纬度！'
//
//		});
		
		this.remark = this.createTextArea('备注','remark','50','95%',null);
//		this.createUser = new Ext.form.NumberField({
//            fieldLabel: '创建人',
//            name: 'createUser',
//            allowBlank: false,
//            allowNegative :false,
//            maxLength:10,
//            maxLengthText:'长度超过不能10', 
//            anchor: '95%',
//            cls:'forbiddenZH',
//            blankText: '该选项为必填项,请输入内容...'
//        });
//		this.createTime = this.createDateField('创建时间','createTime','Y-m-d','95%');
//		this.createTime.value = new Date().format('Y-m-d');
//		this.delFlag = new Ext.form.NumberField({
//            fieldLabel: '默认0 流程删除时,状态置为1',
//            name: 'delFlag',
//            allowBlank: false,
//            allowNegative :false,
//            maxLength:10,
//            maxLengthText:'长度超过不能10', 
//            anchor: '95%',
//            cls:'forbiddenZH',
//            blankText: '该选项为必填项,请输入内容...'
//        });
//		this.delTime = this.createDateField('DEL_TIME','delTime','Y-m-d','95%');
//		this.delTime.value = new Date().format('Y-m-d');
		
		this.wdName.allowBlank = false;
		this.wdAddress.allowBlank = false;
		this.phone.allowBlank = true;
		this.yysj.allowBlank = true;
		this.bd09Lng.allowBlank = true;
		this.bd09Lat.allowBlank = true;
		this.remark.allowBlank = true;
//		this.createUser.allowBlank = true;
//		this.createTime.allowBlank = true;
//		this.delFlag.allowBlank = true;
//		this.delTime.allowBlank = true;
        
        WxWangdianInfo.superclass.constructor.call(this, {
	            anchor: '100%',
	            autoHeight:true,
	            labelWidth: 90,
	            labelAlign :'right',
	            frame: true,
	            bodyStyle:"padding: 5px 5px 0",
	            layout: 'tableform',
	            layoutConfig: {columns: 2},
	            items:[
					this.wdName,
					this.wdAddress,
					this.phone,
					this.yysj,
					this.bd09Lng,
					this.bd09Lat,
					this.remark,
					this.wdId
//					this.createUser,
//					this.createTime,
//					this.delFlag,
//					this.delTime
	            ],
	            buttonAlign :'center',
	            buttons: [
	               {text: '保存', width: 20,iconCls: 'save', hidden: false,handler:this.addFormClick,scope:this},
	               {text: '修改', width: 20,iconCls:'edit', hidden: true, handler: this.updateFormClick, scope: this},
	               {text: '重置', width: 20,iconCls:'redo', hidden: true, handler: this.onResumeClick, scope: this},               
	               {text: '清空', width: 20, iconCls:'redo',  handler: this.resetFormClick, scope: this},
	               {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
	            ]
	        });
	},
	addFormClick: function(){
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: ENTITY_URL_SAVE,
				method: 'POST',
				success: function(form,action){
					Ext.Msg.alert("系统提示：","添加成功！");
					wxWangdianInfoGrid.store.load({params:{start:0,limit:PAGESIZE}});
					wxWangdianInfoGrid.wxWangdianInfoWindow.hide();
					
				},
				failure: function(form,action){
					console.info(action);
					Ext.MessageBox.alert("系统提示：",action.result.message);
				}
			});
		}
	},
	updateFormClick: function(){
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交，请稍后...',
				url: ENTITY_URL_UPDATE,
				method: 'POST',
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：","修改成功！");
					wxWangdianInfoGrid.wxWangdianInfoUpdateWindow.hide();
					wxWangdianInfoGrid.vbbar.doLoad(wxWangdianInfoGrid.vbbar.cursor);
				},
				failure: function(form,action){
					Ext.MessageBox.alert("系统提示：",BLANKSTR + "修改失败!" + BLANKSTR);
				}
			});
		}
	},
	//关闭
    onCloseClick: function(){
    	if(wxWangdianInfoGrid.wxWangdianInfoUpdateWindow)
    	wxWangdianInfoGrid.wxWangdianInfoUpdateWindow.wxWangdianInfo.getForm().reset();
        this.ownerCt.hide();
    },
  //清空
    resetFormClick: function() {        
        this.getForm().reset();
    }, 
  //重置
    onResumeClick: function() {        
    	wxWangdianInfoGrid.onModifyClick();
    }
	
});

/**************WxWangdianInfoWindow*********************/
WxWangdianInfoWindow = Ext.extend(Ext.Window,{
	
	constructor: function(grid){
		this.wxWangdianInfo = new WxWangdianInfo();
		WxWangdianInfoWindow.superclass.constructor.call(this,{
			title: '新增',
			 width: 700,
			 anchor: '100%',
			autoHeight:true,
			resizable : false, //可变尺寸的；可调整大小的
			 plain: true,
			 modal: true,
			closeAction: 'hide',
			items:[this.wxWangdianInfo]
		});
	}
});

/********************WxWangdianInfoUpdateWindow组件*************************/
WxWangdianInfoUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.wxWangdianInfo = new WxWangdianInfo();
    	this.wxWangdianInfo.buttons[0].hide();   //隐藏添加按钮
    	this.wxWangdianInfo.buttons[1].show();   //显示修改按钮
    	this.wxWangdianInfo.buttons[2].show();   //显示重置按钮
    	this.wxWangdianInfo.buttons[3].hide();   //隐藏清空按钮
    	
    	//this.wxWangdianInfo.wdId.readOnly = true;  //设置为不可修改
    	
    	WxWangdianInfoUpdateWindow.superclass.constructor.call(this, {
			title: '修改',
			width: 700,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.wxWangdianInfo]
        });
    }
});

/****************WxWangdianInfoGrid***********************/
WxWangdianInfoGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			
			proxy: new Ext.data.HttpProxy({url:ENTITY_URL_LIST,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
		            {name:'wdid'},
		            {name:'wdmc'},
		            {name:'wddz'},
		            {name:'lxfs'},
		            {name:'yysj'},
		            {name:'bzjd'},
		            {name:'bzwd'},
		            {name:'jd'},
		            {name:'wd'},
		            {name:'bz'},
		            {name:'cjr'},
		            {name:'cjsj'},
		            {name:'scbz'},
		            {name:'scsj'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'添加',iconCls:'add',handler:this.onAddClick,scope:this}, 
			       '-',{xtype:'button',text:'修改',iconCls:'edit',handler:this.onModifyClick,scope:this}, 
			       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this},
				   '-',{xtype:'label',text:'网点名称'},{xtype:'textfield',id:'wdmc'},
			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
	    	   				var params = {};
			       			wxWangdianInfoGrid.vtbar.items.each(function(item,index,length){ 
		       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
		       						if (item.getXType() == 'datefield') {
		       							params[item.getId()] = item.getValue().format(item.format);
		       						} else {
		       							params[item.getId()] = item.getValue();
		       						}
		       					}
							});
			       			var wdmc = Ext.getCmp('wdmc').getValue();
			       			wxWangdianInfoGrid.store.baseParams = {wdmc:wdmc};
	    	   				//wxWangdianInfoGrid.store.baseParams= params;
	    	   				wxWangdianInfoGrid.store.load({params:{start:0,limit:PAGESIZE}});
	    	   			}
	       			},
					'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
						wxWangdianInfoGrid.vtbar.items.each(function(item,index,length){   
							if((""+item.getXType()).indexOf("field") != -1) {
								item.setValue('');
							}
						  });  
    	   			}}
			]
		});
		//this.vbbar = this.createPagingToolbar(PAGESIZE);
		this.vbbar= new Ext.PagingToolbar({ 
            pageSize: PAGESIZE, 
            store: this.store, 
            displayInfo: true, 
            displayMsg: '显示第 {0} 条到 {1} 条记录，一共 {2} 条', 
            emptyMsg: "没有记录" 
    	});

		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'ID',dataIndex:'wdid',width:100,sortable:true,hidden:true},
		            {header:'网点名称',dataIndex:'wdmc',width:120,sortable:true,hidden:false},
		            {header:'网点地址',dataIndex:'wddz',width:200,sortable:true,hidden:false},
		            {header:'联系方式',dataIndex:'lxfs',width:110,sortable:true,hidden:false},
		            {header:'营业时间',dataIndex:'yysj',width:100,sortable:true,hidden:false},
		            {header:'标准经度',dataIndex:'bzjd',width:100,sortable:true,hidden:true},
		            {header:'标准纬度',dataIndex:'bzwd',width:100,sortable:true,hidden:true},
		            {header:'百度经度',dataIndex:'jd',width:100,sortable:true,hidden:false},
		            {header:'百度纬度',dataIndex:'wd',width:100,sortable:true,hidden:false},
		            {header:'备注',dataIndex:'bz',width:150,sortable:true,hidden:false},
		            {header:'创建人',dataIndex:'cjr',width:100,sortable:true,hidden:true},
		            {header:'创建时间',dataIndex:'cjsj',width:150,sortable:true,hidden:true},
		            {header:'删除标志',dataIndex:'scbz',width:100,sortable:true,hidden:true},
		            {header:'删除时间',dataIndex:'scsj',width:100,sortable:true,hidden:true}
		           ]);
		WxWangdianInfoGrid.superclass.constructor.call(this,{
			region: 'center',
			frame: true,
			height: height,
            viewConfig: {
                forceFit: false
            },
            loadMask: new Ext.LoadMask(document.body,{ 
				msg: '正在载入数据，请稍后...',
				store   : this.store
			}),
			sm: this.vsm,
			cm: this.vcm,
			tbar: this.vtbar,
			bbar: this.vbbar,
			ds: this.store
		});
	},
    onAddClick: function(){
    	if(!this.wxWangdianInfoWindow)
    		this.wxWangdianInfoWindow = new WxWangdianInfoWindow();
    	var win = this.wxWangdianInfoWindow;
		win.show();
		win.wxWangdianInfo.getForm().reset();
    },
    onModifyClick: function() {
    	var records = this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				
   				if(!this.wxWangdianInfoUpdateWindow)
					this.wxWangdianInfoUpdateWindow = new WxWangdianInfoUpdateWindow();
   				
   		    	var win = this.wxWangdianInfoUpdateWindow;
				var winForm = win.wxWangdianInfo;
				
				win.show();
							
   		    	winForm.wdId.setValue(vrecord.data.wdid);
				winForm.wdName.setValue(vrecord.data.wdmc);
				winForm.wdAddress.setValue(vrecord.data.wddz);
				winForm.phone.setValue(vrecord.data.lxfs);
				winForm.yysj.setValue(vrecord.data.yysj);
				winForm.bd09Lng.setValue(vrecord.data.jd);
				winForm.bd09Lat.setValue(vrecord.data.wd);
				winForm.remark.setValue(vrecord.data.bz);
//				winForm.createUser.setValue(vrecord.data.createUser);
//				winForm.createTime.setValue(vrecord.data.createTime);
//				winForm.delFlag.setValue(vrecord.data.delFlag);
//				winForm.delTime.setValue(vrecord.data.delTime);
   		    	
   			}else{
   				Ext.Msg.alert('系统提示','不能修改多条记录！');
   			}
   		}else{
   			Ext.Msg.alert('系统提示','请选择一条记录！');
   		}    	
    },
    onDeleteClick: function(){
    	var records = this.getSelectionModel().getSelections();
    	var ids = {};
		var valueStr = [];
    	for(var i=0;i<records.length;i++){
    		valueStr.push(records[i].get('wdid'));
    	}
		ids['ids'] = valueStr;
		
    	if(records.length>0){
    		Ext.Msg.confirm('系统提示：',"确定删除这"+records.length+"条信息吗？",function(btn){
    			if(btn == 'yes'){
    				Ext.Ajax.request({
    					url: ENTITY_URL_DELETE,
    					method: 'POST',
    					params: ids,
    					success: function(form,action){
    						Ext.Msg.alert('系统提示','删除成功！');
    						wxWangdianInfoGrid.vbbar.doLoad(wxWangdianInfoGrid.vbbar.cursor);
    					},
    					failure: function(form,action){
							Ext.MessageBox.alert("系统提示：",action.result.message);
						}
    				});
    			}
    		});
    	}else{
    		Ext.Msg.alert('系统提示','请选择一条记录！');
    	}
    }
});

/*************onReady组件渲染处理***********************/
Ext.onReady(function(){
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';        //提示方式"side"
    
    wxWangdianInfoGrid = new WxWangdianInfoGrid(Ext.getBody().getViewSize().height);
    
    wxWangdianInfoGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        wxWangdianInfoGrid   
		]
	});
});

