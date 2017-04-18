var ENTITY_URL_LIST = "getDzxqList";
var ENTITY_URL_SAVE = "save";
var ENTITY_URL_UPDATE = "update";
var ENTITY_URL_DELETE = "delete";
var PAGESIZE=50;
/*
 * 提醒管理配置 
 * @author liujiansen
 * @date 2015-07-29
 */
Dzxq = Ext.extend(Ext.ux.Form,{
	constructor: function(){
		 this.xn = this.createTextField('学年','xn','95%','',null,9,'长度不能超过9!');
		 //this.xn.emptyText = '2016-2017';
		 this.xn.allowBlank=false;
		
		/*this.xn = new Ext.form.NumberField({
            fieldLabel: '学年',
            name: 'xn',
            allowBlank: false,
            allowNegative :false,
            maxLength:9,
            maxLengthText:'长度超过不能9', 
            anchor: '93.8%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });*/
			
		 //创建数据源[数组数据源]
		 var combostore = new Ext.data.ArrayStore({
			 fields: ['id', 'name'],
			  data: [[1, '1'], [2, '2']]
	           });
		 //创建Combobox
		 this.xq= new Ext.form.ComboBox({
		       fieldLabel: '学期',
		       store: combostore,
		       displayField: 'name',
	           valueField: 'id',
	           width:555,
	           name:"xq",
		       triggerAction: 'all',
	           emptyText: '请选择...',
		       allowBlank: false,
		       blankText: '请选择...',
		       editable: false,
		       mode: 'local'
		       });
		this.xn.allowBlank = false;
        
		 this.ksrq =  new Ext.form.DateField({
				fieldLabel: '开始时间',
				name: "ksrq",
				format: "Y-m-d",
				anchor: '95%',
				emptyText: '请选择...',
				allowBlank: false,
				editable:true,//不能手动输入
				blankText: '请选择时间...'
			});
	    
		 this.jsrq =  new Ext.form.DateField({
			 	fieldLabel: '结束时间',
			 	name: "jsrq",
			 	format: "Y-m-d",
			 	anchor: '95%',
			 	allowBlank: false,
			 	emptyText: '请选择...',
			 	editable:false,//不能手动输入
			 	blankText: '请选择时间...'
		});
	    
	    this.dqzc = new Ext.form.NumberField({
	    		fieldLabel: '当前周次',
	    		name: 'dqzc',
	    		allowBlank: true,
	    		//allowNegative :false,
	    		maxLength:5,
	    		//emptyText: '请输入...',
	    		maxLengthText:'长度不能超过5', 
	    		anchor: '95%',
	    		regex: /^\d+(\.\d+)?$/,
	    		//blankText: '请输入内容...'
    });
	    this.dqzc.allowBlank=true;
        Dzxq.superclass.constructor.call(this, {
	            anchor: '100%',
	            autoHeight:true,
	            labelWidth:60,
	            height:120,
	            labelAlign :'right',
	            frame: true,
	            bodyStyle:"padding: 5px 5px 0",
	            layout: 'tableform',
	            layoutConfig: {columns:1},
	            items:[
					this.xn,
					this.xq,
					this.ksrq,
					this.jsrq,
					this.dqzc
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
					dzxqGrid.store.load({params:{start:0,limit:PAGESIZE}});
					dzxqGrid.dzxqWindow.hide();
					
				},
				failure: function(form,action){
					Ext.MessageBox.alert("系统提示：","添加成功");
				}
			});
		}
	},
	updateFormClick: function(){
		var records = dzxqGrid.getSelectionModel().getSelections();
		var id = records[0].get('id');
		if(this.getForm().isValid()){
			this.getForm().submit({
				waitMsg: '正在提交，请稍后...',
				url: ENTITY_URL_UPDATE,
				params: {id:id},
				method: 'POST',
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：","修改成功！");
					dzxqGrid.dzxqUpdateWindow.hide();
					dzxqGrid.vbbar.doLoad(dzxqGrid.vbbar.cursor);
				},
				failure: function(form,action){
					Ext.MessageBox.alert("系统提示：","修改成功");
				}
			});
		}
	},
	//关闭
    onCloseClick: function(){
    	if(dzxqGrid.dzxqUpdateWindow)
    	dzxqGrid.dzxqUpdateWindow.dzxq.getForm().reset();
        this.ownerCt.hide();
    },
  //清空
    resetFormClick: function() {        
        this.getForm().reset();
    }, 
  //重置
    onResumeClick: function() {        
    	dzxqGrid.onModifyClick();
    }
	
});

/**************DzxqWindow*********************/
DzxqWindow = Ext.extend(Ext.Window,{
	
	constructor: function(grid){
		this.dzxq = new Dzxq();
		DzxqWindow.superclass.constructor.call(this,{
			title: '新增',
			 width: 700,
			 anchor: '100%',
			autoHeight:true,
			resizable : false, //可变尺寸的；可调整大小的
			 plain: true,
			 modal: true,
			closeAction: 'hide',
			items:[this.dzxq]
		});
	}
});

/********************DzxqUpdateWindow组件*************************/
DzxqUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.dzxq = new Dzxq();
    	this.dzxq.buttons[0].hide();   //隐藏添加按钮
    	this.dzxq.buttons[1].show();   //显示修改按钮
    	this.dzxq.buttons[2].show();   //显示重置按钮
    	this.dzxq.buttons[3].hide();   //隐藏清空按钮
    	
    	DzxqUpdateWindow.superclass.constructor.call(this, {
			title: '修改',
			width: 700,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.dzxq]
        });
    }
});

/****************DzxqGrid***********************/
DzxqGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			
			proxy: new Ext.data.HttpProxy({url:ENTITY_URL_LIST,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
                    {name:'id'},{name:'xn'}, {name:'xq'}, {name:'ksrq'}, {name:'jsrq'},{name:'dqzc'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'添加',iconCls:'add',handler:this.onAddClick,scope:this}, 
			       '-',{xtype:'button',text:'修改',iconCls:'edit',handler:this.onModifyClick,scope:this}, 
			       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this},
					'-',{xtype:'label',text:'学年'},{xtype:'textfield',id:'code'},
			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
			       			var params = {};
			       			dzxqGrid.vtbar.items.each(function(item,index,length){ 
		       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
		       						if (item.getXType() == 'datefield') {
		       							params[item.getId()] = item.getValue().format(item.format);
		       						} else {
		       							params[item.getId()] = item.getValue();
		       						}
		       					}
							});
	    	   				dzxqGrid.store.baseParams= params;
	    	   				dzxqGrid.store.load({params:{start:0,limit:PAGESIZE}});
	    	   			}
	       			},
					'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
						dzxqGrid.vtbar.items.each(function(item,index,length){   
							if((""+item.getXType()).indexOf("field") != -1) {
								item.setValue('');
							}
						  });  
    	   			}}
			]
		});
		this.vbbar = this.createPagingToolbar(PAGESIZE);
		this.dzxqWindow = new DzxqWindow();
		this.dzxqUpdateWindow = new DzxqUpdateWindow();
		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'ID',dataIndex:'id',width:100,sortable:true,hidden:true},
		            {header:'学年',dataIndex:'xn',width:100,sortable:true,hidden:false},
		            {header:'学期',dataIndex:'xq',width:100,sortable:true,hidden:false},
		            {header:'开始时间',dataIndex:'ksrq',width:100,sortable:true,hidden:false},
		            {header:'结束时间',dataIndex:'jsrq',width:100,sortable:true,hidden:false},
		            {header:'当前周次',dataIndex:'dqzc',width:100,sortable:true,hidden:false}
		           /* {header: '操作', width:100, dataIndex: '',align:"center",  sortable: true,hidden: false,
    	                renderer:function(value, cellmeta, record){
    	        			   return "<a href='#' onclick='dzxqGrid.onLookClick()'>查看</a>&nbsp;&nbsp;&nbsp;" 
    	                 }
		              }*/
		           ]);
		DzxqGrid.superclass.constructor.call(this,{
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
    	var win = this.dzxqWindow;
		win.show();
		win.dzxq.getForm().reset();
    },
    onModifyClick: function() {
    	var records = this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				
   		    	var win = this.dzxqUpdateWindow;
				var winForm = win.dzxq;
				win.show();
				winForm.xn.setValue(vrecord.data.xn);
				winForm.xq.setValue(vrecord.data.xq);
				winForm.ksrq.setValue(vrecord.data.ksrq);
				winForm.jsrq.setValue(vrecord.data.jsrq);
				winForm.dqzc.setValue(vrecord.data.dqzc);
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
    						dzxqGrid.vbbar.doLoad(dzxqGrid.vbbar.cursor);
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
    
    dzxqGrid = new DzxqGrid(Ext.getBody().getViewSize().height);
    dzxqGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        dzxqGrid   
		]
	});
});

