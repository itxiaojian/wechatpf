var ENTITY_URL_LIST = "pager";
var ENTITY_URL_DELETE = "delete";
var PAGESIZE=50;
/*
 * 提醒信息历史记录
 * @author liujiansen
 * @date 2015-07-29
 */
/****************TxGlpzGrid***********************/
TxGlpzGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			
			proxy: new Ext.data.HttpProxy({url:ENTITY_URL_LIST,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
		            {name:'id'},
		            {name:'txnr'},
		            {name:'txsj'},
		            {name:'txlx'},
		            {name:'zdmc'},
		            {name:'txdx'},
		            {name:'openid'},
		            {name:'nickname'},
		            {name:'bz'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this},
					'-',{xtype:'label',text:'提醒类型'},{xtype:'combo',id:'txlx',
				    	   editable: false,
				    	   store: new Ext.data.Store({
				                proxy: new Ext.data.HttpProxy({url: 'getLx', method: 'POST'}),
				                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'zdmc'},{name:'zdz'}])),
				                autoLoad:true,
				                listeners:{  
				                    load : function(store, records, options ){      
				                        var data ={ "zdz": "", "zdmc": "全部"};      
				                        var rs = [new Ext.data.Record(data)];      
				                        store.insert(0,rs);  
				                    }  
				                }
				            }),
	                       displayField: 'zdmc', //显示文本字段
	                       valueField: 'zdz',//value值字段id
	                       mode: 'local',
	                       blankText:'请选择....',  
	                       emptyText:'请选择....', 
	                       triggerAction: 'all',
	                       selectOnFocus: true,
	                       typeAhead: true
			       		},
			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
			       			var params = {};
			       			params['txlx']=Ext.getCmp('txlx').getValue();
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
						Ext.getCmp('txlx').setValue('');
    	   			}}
			]
		});
		this.vbbar = this.createPagingToolbar(PAGESIZE);
		
		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'主键',dataIndex:'id',width:100,sortable:true,hidden:true},
		            {header:'提醒对象',dataIndex:'txdx',width:100,sortable:true,hidden:false},
		            {header:'微信昵称',dataIndex:'nickname',width:140,sortable:true,hidden:false},
		            {header:'提醒对象openId',dataIndex:'openid',width:140,sortable:true,hidden:false},
		            {header:'提醒内容',dataIndex:'txnr',width:200,sortable:true,hidden:false},
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

