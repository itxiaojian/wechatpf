var ENTITY_URL_LIST = "pager";
var ENTITY_URL_SAVE = "save";
var ENTITY_URL_UPDATE = "update";
var ENTITY_URL_DELETE = "delete";
var PAGESIZE=50;
/*
 * 提醒管理配置 
 * @author liujiansen
 * @date 2015-07-29
 */
TxGlpz = Ext.extend(Ext.ux.Form,{
	constructor: function(){
		this.sjgjsj = this.createDateTimeField('数据归集时间','sjgjsj','H:i','95%');
		this.txsj = this.createDateTimeField('提醒时间','txsj','H:i','95%');
		//this.txlx = this.createTextField('提醒类型','txlx','95%','',null,10,'长度超过不能10');
		this.txlx = this.createCombo('提醒类型','zdz','zdmc','txlx','95%','getLx','','',null);
		this.txlx.store.load();
		this.bz = this.createTextField('备注','bz','95%','',null,512,'长度超过不能512');
		
		this.sjgjsj.allowBlank = true;
		this.txsj.allowBlank = true;
		this.txlx.allowBlank = true;
		this.bz.allowBlank = true;
        
        TxGlpz.superclass.constructor.call(this, {
	            anchor: '100%',
	            autoHeight:true,
	            labelWidth: 90,
	            labelAlign :'right',
	            frame: true,
	            bodyStyle:"padding: 5px 5px 0",
	            layout: 'tableform',
	            layoutConfig: {columns: 2},
	            items:[
					this.sjgjsj,
					this.txsj,
					this.txlx,
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
	createDateTimeField: function(fieldLabel, name, format, anchor) {
    	var df = new Ext.ux.form.DateTimeField({
			fieldLabel: fieldLabel,
			name: name,
			format: format,
			anchor: anchor,
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
		return df;
    },
	addFormClick: function(){
		var sjgjsj=this.sjgjsj.getValue().format('Y-m-d H:i:s');
		var gjTimes = sjgjsj.substring(0,10).split('-');
		var gjTime=new Date(gjTimes[0],(gjTimes[1]-1),gjTimes[2],sjgjsj.substring(11,13),sjgjsj.substring(14,16),sjgjsj.substring(17,19));
		var txsj=this.txsj.getValue().format('Y-m-d H:i:s');
		var txTimes = txsj.substring(0,10).split('-');
		var txTime=new Date(txTimes[0],(txTimes[1]-1),txTimes[2],txsj.substring(11,13),txsj.substring(14,16),txsj.substring(17,19));
		var a =(Date.parse(txTime)-Date.parse(gjTime))/3600/1000;
		if(a<4){
			Ext.MessageBox.alert("系统提示：","数据归集要在提醒前四个小时进行。");
			return false;
		}
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: ENTITY_URL_SAVE,
				method: 'POST',
				success: function(form,action){
					Ext.Msg.alert("系统提示：","添加成功！");
					txGlpzGrid.store.load({params:{start:0,limit:PAGESIZE}});
					txGlpzGrid.txGlpzWindow.hide();
					
				},
				failure: function(form,action){
					Ext.MessageBox.alert("系统提示：","添加成功");
				}
			});
		}
	},
	updateFormClick: function(){
		var sjgjsj=this.sjgjsj.getValue().format('Y-m-d H:i:s');
		var gjTimes = sjgjsj.substring(0,10).split('-');
		var gjTime=new Date(gjTimes[0],(gjTimes[1]-1),gjTimes[2],sjgjsj.substring(11,13),sjgjsj.substring(14,16),sjgjsj.substring(17,19));
		var txsj=this.txsj.getValue().format('Y-m-d H:i:s');
		var txTimes = txsj.substring(0,10).split('-');
		var txTime=new Date(txTimes[0],(txTimes[1]-1),txTimes[2],txsj.substring(11,13),txsj.substring(14,16),txsj.substring(17,19));
		var a =(Date.parse(txTime)-Date.parse(gjTime))/3600/1000;
		if(a<4){
			Ext.MessageBox.alert("系统提示：","数据归集要在提醒前四个小时进行。");
			return false;
		}
		var records = txGlpzGrid.getSelectionModel().getSelections();
		var id = records[0].get('id');
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交，请稍后...',
				url: ENTITY_URL_UPDATE,
				params: {id:id},
				method: 'POST',
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：","修改成功！");
					txGlpzGrid.txGlpzUpdateWindow.hide();
					txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
				},
				failure: function(form,action){
					Ext.MessageBox.alert("系统提示：","修改成功");
				}
			});
		}
	},
	//关闭
    onCloseClick: function(){
    	if(txGlpzGrid.txGlpzUpdateWindow)
    	txGlpzGrid.txGlpzUpdateWindow.txGlpz.getForm().reset();
        this.ownerCt.hide();
    },
  //清空
    resetFormClick: function() {        
        this.getForm().reset();
    }, 
  //重置
    onResumeClick: function() {        
    	txGlpzGrid.onModifyClick();
    }
	
});

/**************TxGlpzWindow*********************/
TxGlpzWindow = Ext.extend(Ext.Window,{
	
	constructor: function(grid){
		this.txGlpz = new TxGlpz();
		TxGlpzWindow.superclass.constructor.call(this,{
			title: '新增',
			 width: 700,
			 anchor: '100%',
			autoHeight:true,
			resizable : false, //可变尺寸的；可调整大小的
			 plain: true,
			 modal: true,
			closeAction: 'hide',
			items:[this.txGlpz]
		});
	}
});

/********************TxGlpzUpdateWindow组件*************************/
TxGlpzUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.txGlpz = new TxGlpz();
    	this.txGlpz.buttons[0].hide();   //隐藏添加按钮
    	this.txGlpz.buttons[1].show();   //显示修改按钮
    	this.txGlpz.buttons[2].show();   //显示重置按钮
    	this.txGlpz.buttons[3].hide();   //隐藏清空按钮
    	
    	TxGlpzUpdateWindow.superclass.constructor.call(this, {
			title: '修改',
			width: 700,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.txGlpz]
        });
    }
});

/****************TxGlpzGrid***********************/
TxGlpzGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			
			proxy: new Ext.data.HttpProxy({url:ENTITY_URL_LIST,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
		            {name:'id'},
		            {name:'sjgjsj'},
		            {name:'txsj'},
		            {name:'txlx'},
		            {name:'zdmc'},
		            {name:'bz'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'添加',iconCls:'add',handler:this.onAddClick,scope:this}, 
			       '-',{xtype:'button',text:'修改',iconCls:'edit',handler:this.onModifyClick,scope:this}, 
			       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this},
					'-',{xtype:'label',text:'提醒类型'},{xtype:'textfield',id:'txlx'},
			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
			       			var params = {};
			       			txGlpzGrid.vtbar.items.each(function(item,index,length){ 
		       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
		       						if (item.getXType() == 'datefield') {
		       							params[item.getId()] = item.getValue().format(item.format);
		       						} else {
		       							params[item.getId()] = item.getValue();
		       						}
		       					}
							});
	    	   				txGlpzGrid.store.baseParams= params;
	    	   				txGlpzGrid.store.load({params:{start:0,limit:PAGESIZE}});
	    	   			}
	       			},
					'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
						txGlpzGrid.vtbar.items.each(function(item,index,length){   
							if((""+item.getXType()).indexOf("field") != -1) {
								item.setValue('');
							}
						  });  
    	   			}}
			]
		});
		this.vbbar = this.createPagingToolbar(PAGESIZE);
		this.txGlpzWindow = new TxGlpzWindow();
		this.txGlpzUpdateWindow = new TxGlpzUpdateWindow();
		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'主键',dataIndex:'id',width:100,sortable:true,hidden:true},
		            {header:'数据归集时间',dataIndex:'sjgjsj',width:140,sortable:true,hidden:false},
		            {header:'提醒时间',dataIndex:'txsj',width:140,sortable:true,hidden:false},
		            {header:'提醒类型',dataIndex:'zdmc',width:150,sortable:true,hidden:false},
		            {header:'备注',dataIndex:'bz',width:100,sortable:true,hidden:false}
		           ]);
		TxGlpzGrid.superclass.constructor.call(this,{
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
    	var win = this.txGlpzWindow;
		win.show();
		win.txGlpz.getForm().reset();
    },
    onModifyClick: function() {
    	var records = this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				
   		    	var win = this.txGlpzUpdateWindow;
				var winForm = win.txGlpz;
				
				win.show();
							
				winForm.sjgjsj.setValue(vrecord.data.sjgjsj);
				winForm.txsj.setValue(vrecord.data.txsj);
				winForm.txlx.setValue(vrecord.data.txlx);
				winForm.bz.setValue(vrecord.data.bz);
   		    	
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
    		valueStr.push(records[i].get('id'));
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
    						txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
    					},
    					failure: function(form,action){
							Ext.MessageBox.alert("系统提示：","删除成功");
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
    
    txGlpzGrid = new TxGlpzGrid(Ext.getBody().getViewSize().height);
    txGlpzGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        txGlpzGrid   
		]
	});
});

