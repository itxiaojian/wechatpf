var ENTITY_URL_LIST = "pager";
var ENTITY_URL_DELETE = "deleteByHt";
var PAGESIZE=50;
/*
 * 失物招领 
 * @author liujiansen
 * @date 2015-12-07
 */
/****************ShSwzlGrid***********************/
ShSwzlGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			
			proxy: new Ext.data.HttpProxy({url:ENTITY_URL_LIST,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
		            {name:'id'},
		            {name:'lx'},
		            {name:'bt'},
		            {name:'xwzs'},
		            {name:'fqsj'},
		            {name:'jssj'},
		            {name:'fbr'},
		            {name:'fbrxm'},
		            {name:'xwzt'},
		            {name:'dd'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this},
					'-',{xtype:'label',text:'关键字'},{xtype:'textfield',id:'code'},
					'-',{xtype:'label',text:'发布人姓名'},{xtype:'textfield',id:'fbrxm'},
			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
			       			var params = {};
			       			shSwzlGrid.vtbar.items.each(function(item,index,length){ 
		       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
		       						if (item.getXType() == 'datefield') {
		       							params[item.getId()] = item.getValue().format(item.format);
		       						} else {
		       							params[item.getId()] = item.getValue();
		       						}
		       					}
							});
	    	   				shSwzlGrid.store.baseParams= params;
	    	   				shSwzlGrid.store.load({params:{start:0,limit:PAGESIZE}});
	    	   			}
	       			},
					'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
						shSwzlGrid.vtbar.items.each(function(item,index,length){   
							if((""+item.getXType()).indexOf("field") != -1) {
								item.setValue('');
							}
						  });  
    	   			}}
			]
		});
		this.vbbar = this.createPagingToolbar(PAGESIZE);

		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'主键',dataIndex:'id',width:100,sortable:true,hidden:true},
		            {header:'类型',dataIndex:'lx',width:100,sortable:true,hidden:false, 
	            		renderer:function(value){
		                    if(value == '1') {
		                        return "<span style='color:blue;'>遗失</span>";
		                    }else{
		                    	return "<span style='color:orange;'>招领</span>";
		                    }
	            		}
		            },
		            {header:'标题',dataIndex:'bt',width:100,sortable:true,hidden:false},
		            {header:'物品详细描述',dataIndex:'xwzs',width:200,sortable:true,hidden:false},
		            {header:'发起时间',dataIndex:'fqsj',width:130,sortable:true,hidden:false},
		            {header:'结束时间',dataIndex:'jssj',width:130,sortable:true,hidden:false},
		            {header:'发布人姓名',dataIndex:'fbrxm',width:100,sortable:true,hidden:false},
		            {header:'状态',dataIndex:'xwzt',width:100,sortable:true,hidden:false, 
	            		renderer:function(value){
		                    if(value == '1') {
		                        return "<span style='color:blue;'>正常</span>";
		                    }else{
		                    	return "<span style='color:red;'>结束</span>";
		                    }
	            		}
		            	
		            },
		            {header:'地址',dataIndex:'dd',width:100,sortable:true,hidden:false}
		           ]);
		ShSwzlGrid.superclass.constructor.call(this,{
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
    						shSwzlGrid.vbbar.doLoad(shSwzlGrid.vbbar.cursor);
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
    
    shSwzlGrid = new ShSwzlGrid(Ext.getBody().getViewSize().height);
    shSwzlGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        shSwzlGrid   
		]
	});
});

