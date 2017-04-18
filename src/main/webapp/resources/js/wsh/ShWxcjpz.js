var ENTITY_URL_LIST = "pager";
var ENTITY_URL_SAVE = "save";
var ENTITY_URL_UPDATE = "update";
var ENTITY_URL_DELETE = "delete";
var PAGESIZE=50;
/*
 * 微信抽奖配置 
 * @author liujiansen
 * @date 2015-06-18
 */
ShWxcjpz = Ext.extend(Ext.ux.Form,{
	constructor: function(){
		this.qssj = this.createDateField('起始时间','qssj','Y-m-d','95%');
		this.qssj.value = new Date().format('Y-m-d');
		this.jssj = this.createDateField('截止时间','jssj','Y-m-d','95%');
		this.jssj.value = new Date().format('Y-m-d');
		this.cjcs = new Ext.form.NumberField({
            fieldLabel: '抽奖次数',
            name: 'cjcs',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...'
        });
		this.ydjmc = this.createTextField('一等奖名称','ydjmc','95%','',null,200,'长度超过不能200');
		this.ydjsl = new Ext.form.NumberField({
            fieldLabel: '一等奖数量',
            name: 'ydjsl',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...'
        });
		this.edjmc = this.createTextField('二等奖名称','edjmc','95%','',null,200,'长度超过不能200');
		this.rdjsl = new Ext.form.NumberField({
            fieldLabel: '二等奖数量',
            name: 'rdjsl',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...'
        });
		this.sdjmc = this.createTextField('三等奖名称','sdjmc','95%','',null,200,'长度超过不能200');
		this.sdjsl = new Ext.form.NumberField({
            fieldLabel: '三等奖数量',
            name: 'sdjsl',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...'
        });
		this.bz = this.createTextField('备注','bz','95%','',null,500,'长度超过不能500');
		
		this.qssj.allowBlank = true;
		this.jssj.allowBlank = true;
		this.cjcs.allowBlank = true;
		this.ydjmc.allowBlank = true;
		this.ydjsl.allowBlank = true;
		this.edjmc.allowBlank = true;
		this.rdjsl.allowBlank = true;
		this.sdjmc.allowBlank = true;
		this.sdjsl.allowBlank = true;
		this.bz.allowBlank = true;
        
        ShWxcjpz.superclass.constructor.call(this, {
	            anchor: '100%',
	            autoHeight:true,
	            labelWidth: 90,
	            labelAlign :'right',
	            frame: true,
	            bodyStyle:"padding: 5px 5px 0",
	            layout: 'tableform',
	            layoutConfig: {columns: 2},
	            items:[
					this.qssj,
					this.jssj,
					this.cjcs,
					this.ydjmc,
					this.ydjsl,
					this.edjmc,
					this.rdjsl,
					this.sdjmc,
					this.sdjsl,
					this.bz
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
		if(this.jssj.value<=this.qssj.value){
			Ext.Msg.alert("系统提示：","截止时间应大于起始时间！");
			return false;
		}
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: ENTITY_URL_SAVE,
				method: 'POST',
				success: function(form,action){
					Ext.Msg.alert("系统提示：","添加成功！");
					shWxcjpzGrid.store.load({params:{start:0,limit:PAGESIZE}});
					shWxcjpzGrid.shWxcjpzWindow.hide();
					
				},
				failure: function(form,action){
					Ext.MessageBox.alert("系统提示：","添加失败！");
				}
			});
		}
	},
	updateFormClick: function(){
		var records = shWxcjpzGrid.getSelectionModel().getSelections();
		vrecord = records[0];
		var id=vrecord.get('id');
		if(this.jssj.value<=this.qssj.value){
			Ext.Msg.alert("系统提示：","截止时间应大于起始时间！");
			return false;
		}
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交，请稍后...',
				url: ENTITY_URL_UPDATE,
				method: 'POST',
				params:{id:id},
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：","修改成功！");
					shWxcjpzGrid.shWxcjpzUpdateWindow.hide();
					shWxcjpzGrid.vbbar.doLoad(shWxcjpzGrid.vbbar.cursor);
				},
				failure: function(form,action){
					Ext.MessageBox.alert("系统提示：","修改失败！");
				}
			});
		}
	},
	//关闭
    onCloseClick: function(){
    	if(shWxcjpzGrid.shWxcjpzUpdateWindow)
    	shWxcjpzGrid.shWxcjpzUpdateWindow.shWxcjpz.getForm().reset();
        this.ownerCt.hide();
    },
  //清空
    resetFormClick: function() {        
        this.getForm().reset();
    }, 
  //重置
    onResumeClick: function() {        
    	shWxcjpzGrid.onModifyClick();
    }
	
});

/**************ShWxcjpzWindow*********************/
ShWxcjpzWindow = Ext.extend(Ext.Window,{
	
	constructor: function(grid){
		this.shWxcjpz = new ShWxcjpz();
		ShWxcjpzWindow.superclass.constructor.call(this,{
			title: '新增',
			 width: 700,
			 anchor: '100%',
			autoHeight:true,
			resizable : false, //可变尺寸的；可调整大小的
			 plain: true,
			 modal: true,
			closeAction: 'hide',
			items:[this.shWxcjpz]
		});
	}
});

/********************ShWxcjpzUpdateWindow组件*************************/
ShWxcjpzUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.shWxcjpz = new ShWxcjpz();
    	this.shWxcjpz.buttons[0].hide();   //隐藏添加按钮
    	this.shWxcjpz.buttons[1].show();   //显示修改按钮
    	this.shWxcjpz.buttons[2].show();   //显示重置按钮
    	this.shWxcjpz.buttons[3].hide();   //隐藏清空按钮
    	
    	this.shWxcjpz.qssj.readOnly = true;
    	
    	ShWxcjpzUpdateWindow.superclass.constructor.call(this, {
			title: '修改',
			width: 700,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.shWxcjpz]
        });
    }
});

/****************ShWxcjpzGrid***********************/
ShWxcjpzGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			
			proxy: new Ext.data.HttpProxy({url:ENTITY_URL_LIST,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
		            {name:'id'},
		            {name:'qssj'},
		            {name:'jssj'},
		            {name:'cjcs'},
		            {name:'ydjmc'},
		            {name:'ydjsl'},
		            {name:'edjmc'},
		            {name:'rdjsl'},
		            {name:'sdjmc'},
		            {name:'sdjsl'},
		            {name:'bz'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'添加',iconCls:'add',handler:this.onAddClick,scope:this}, 
			       '-',{xtype:'button',text:'修改',iconCls:'edit',handler:this.onModifyClick,scope:this}, 
			       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this},
					'-',{xtype:'label',text:'起始时间'},{xtype:'datefield',format:'Y-m-d',id:'Q_qssj_D_GT',editable : false,value:new Date().format('Y-m-d')},
			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
			       			var params = {};
			       			shWxcjpzGrid.vtbar.items.each(function(item,index,length){ 
		       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
		       						if (item.getXType() == 'datefield') {
		       							params[item.getId()] = item.getValue().format(item.format);
		       						} else {
		       							params[item.getId()] = item.getValue();
		       						}
		       					}
							});
	    	   				shWxcjpzGrid.store.baseParams= params;
	    	   				shWxcjpzGrid.store.load({params:{start:0,limit:PAGESIZE}});
	    	   			}
	       			},
					'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
						shWxcjpzGrid.vtbar.items.each(function(item,index,length){   
							if((""+item.getXType()).indexOf("field") != -1) {
								item.setValue('');
							}
						  });  
    	   			}}
			]
		});
		this.vbbar = this.createPagingToolbar(PAGESIZE);

		this.shWxcjpzUpdateWindow = new ShWxcjpzUpdateWindow();
		this.shWxcjpzWindow = new ShWxcjpzWindow();
		
		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'ID',dataIndex:'id',width:100,sortable:true,hidden:true},
		            {header:'起始时间',dataIndex:'qssj',width:100,sortable:true,hidden:false},
		            {header:'结束时间',dataIndex:'jssj',width:100,sortable:true,hidden:false},
		            {header:'抽奖次数',dataIndex:'cjcs',width:100,sortable:true,hidden:false},
		            {header:'一等奖名称',dataIndex:'ydjmc',width:100,sortable:true,hidden:false},
		            {header:'一等奖数量',dataIndex:'ydjsl',width:100,sortable:true,hidden:false},
		            {header:'二等奖名称',dataIndex:'edjmc',width:100,sortable:true,hidden:false},
		            {header:'二等奖数量',dataIndex:'rdjsl',width:100,sortable:true,hidden:false},
		            {header:'三等奖名称',dataIndex:'sdjmc',width:100,sortable:true,hidden:false},
		            {header:'三等奖数量',dataIndex:'sdjsl',width:100,sortable:true,hidden:false},
		            {header:'备注',dataIndex:'bz',width:100,sortable:true,hidden:false}
		           ]);
		ShWxcjpzGrid.superclass.constructor.call(this,{
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
    	var win = this.shWxcjpzWindow;
		win.show();
		win.shWxcjpz.getForm().reset();
    },
    onModifyClick: function() {
    	Ext.Ajax.request({
			url: 'getMaxJssj',
			method: 'POST',
			success: function(data){
				var records = shWxcjpzGrid.getSelectionModel().getSelections();
		   		if(records.length > 0) {
		   			if(records.length == 1){
		   				vrecord = records[0];
						if(vrecord.get('jssj')!=data.responseText){
			    			Ext.Msg.alert('系统提示','只能修改结束日期最大的数据！');
			    			return false;
			    		}
		   				
		   		    	var win = shWxcjpzGrid.shWxcjpzUpdateWindow;
						var winForm = win.shWxcjpz;
						
						win.show();
									
						winForm.qssj.setValue(vrecord.data.qssj);
						winForm.jssj.setValue(vrecord.data.jssj);
						winForm.cjcs.setValue(vrecord.data.cjcs);
						winForm.ydjmc.setValue(vrecord.data.ydjmc);
						winForm.ydjsl.setValue(vrecord.data.ydjsl);
						winForm.edjmc.setValue(vrecord.data.edjmc);
						winForm.rdjsl.setValue(vrecord.data.rdjsl);
						winForm.sdjmc.setValue(vrecord.data.sdjmc);
						winForm.sdjsl.setValue(vrecord.data.sdjsl);
						winForm.bz.setValue(vrecord.data.bz);
		   		    	
		   			}else{
		   				Ext.Msg.alert('系统提示','不能修改多条记录！');
		   			}
		   		}else{
		   			Ext.Msg.alert('系统提示','请选择一条记录！');
		   		}  
			},
			failure: function(form,action){
				Ext.MessageBox.alert("系统提示：",'查询失败，请联系管理员！');
			}
		});
    },
    onDeleteClick: function(){
    	Ext.Ajax.request({
			url: 'getMaxJssj',
			method: 'POST',
			success: function(data){
				var records = shWxcjpzGrid.getSelectionModel().getSelections();
				if(records.length > 0) {
					if(records.length==1){
						vrecord = records[0];
						var id=vrecord.get('id');
						var qssj=vrecord.get('qssj');
						if(vrecord.get('jssj')!=data.responseText){
			    			Ext.Msg.alert('系统提示','只能删除结束日期最大的数据！');
			    			return false;
			    		}
			    		Ext.Msg.confirm('系统提示：',"确定删除这条信息吗？",function(btn){
			    			if(btn == 'yes'){
			    				Ext.Ajax.request({
			    					url: ENTITY_URL_DELETE,
			    					method: 'POST',
			    					params:{id:id,qssj:qssj},
			    					success: function(form,action){
			    						Ext.Msg.alert('系统提示','删除成功！');
			    						shWxcjpzGrid.vbbar.doLoad(shWxcjpzGrid.vbbar.cursor);
			    					},
			    					failure: function(form,action){
										Ext.MessageBox.alert("系统提示：",'删除失败！');
									}
			    				});
			    			}
			    		});
			    	}else{
			    		Ext.Msg.alert('系统提示','不能删除多条记录！');
			    	}
				}else{
		   			Ext.Msg.alert('系统提示','请选择一条记录！');
		   		}  
			},
			failure: function(form,action){
				Ext.MessageBox.alert("系统提示：",'查询失败，请联系管理员！');
			}
		});
    }
});

/*************onReady组件渲染处理***********************/
Ext.onReady(function(){
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';        //提示方式"side"
    
    shWxcjpzGrid = new ShWxcjpzGrid(Ext.getBody().getViewSize().height);
    shWxcjpzGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        shWxcjpzGrid   
		]
	});
});

