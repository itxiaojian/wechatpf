var TLR_DG_ST_TEST_MODEL_URL = 'getWxyhList';
var PAGESIZE=20;
/****************TlrDgStModelGrid***********************/
TlrDgStModelGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			proxy: new Ext.data.HttpProxy({url:TLR_DG_ST_TEST_MODEL_URL,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
			         {name:'id'},{name:'wxh'},{name:'wxnc'},{name:'cjsj'},{name:'zt'},{name:'bz'},
			         {name:'dlm'},{name:'xm'},{name:'bmbh'},{name:'bmmc'},{name:'gwbh'},{name:'gwmc'},
			         {name:'sjh'},{name:'yx'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'启用停用',iconCls:'edit',handler:this.onModifyClick,scope:this}, 
			       '-',{xtype:'button',text:'解除绑定',iconCls:'delete',handler:this.onDeleteClick,scope:this},
			       '-',{xtype:'label',text:'用户姓名'},{xtype:'textfield',id:'xm'},
			       '-',{xtype:'label',text:'微信号'},{xtype:'textfield',id:'wxh'},
			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
			    	   			var xm = Ext.getCmp('xm').getValue();
			    	   			var wxh = Ext.getCmp('wxh').getValue();
			    	   			tlrDgStModelGrid.store.baseParams= {xm:xm,wxh:wxh};
			    	   			tlrDgStModelGrid.store.load({params:{start:0,limit:PAGESIZE}});
			       			}
			       
			       		}
			]
		});
		this.vbbar = this.createPagingToolbar(PAGESIZE);
		
		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'id',dataIndex:'id',sortable:true,hidden:true},
		            {header:'微信号',dataIndex:'wxh',width:200,sortable:true,hidden:true},
		            {header:'微信昵称',dataIndex:'wxnc',width:100,sortable:true},
		            {header:'校园账户名',dataIndex:'dlm',width:100,sortable:true},
		            {header:'姓名',dataIndex:'xm',width:100,sortable:true},
		            {header:'所属院系',dataIndex:'bmmc',width:150,sortable:true},
		            {header:'手机号',dataIndex:'sjh',width:100,sortable:true},
		            {header:'邮箱',dataIndex:'yx',width:150,sortable:true},
		            {header:'创建时间',dataIndex:'cjsj',width:100,sortable:true},
		            {header:'状态',dataIndex:'zt',width:60,sortable:true,
	            		renderer:function(value, cellmeta, record){
	            			if(value == "1") {
	            				return '<span style="color:blue;">启用</span>';
	            			}else {
	            				return '<span style="color:red;">停用</span>';
	            			}
	            		}
		            }
					]);
		TlrDgStModelGrid.superclass.constructor.call(this,{
			region: 'center',
			title: '微信用户管理',
			frame: true,
			height: height,
			autoWidth : true,
//			autoExpandColumn: 'aa',
            viewConfig: {
                forceFit: false
            },
			loadMask: {
				msg: '正在载入数据，请稍后...'
			},
			sm: this.vsm,
			cm: this.vcm,
			tbar: this.vtbar,
			bbar: this.vbbar,
			ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}//, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            }
		});
	},
    onModifyClick: function() {
    	var records = this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				var zt = records[0].get('zt');
   				var id = records[0].get('id');
   				if(zt=='1'){
   					Ext.Msg.confirm('系统提示：',"确定停用此微信号？",function(btn){
   	   	    			if(btn == 'yes'){
   	   	    				Ext.Ajax.request({
   	   	    					url: 'update',
   	   	    					method: 'POST',
   	   	    					params: {id: id,zt:zt},
   	   	    					success: function(){
   	   	    						Ext.Msg.alert('系统提示','操作成功！');
   	   	    						tlrDgStModelGrid.store.load({params:{start:0,limit:PAGESIZE}});
   	   	    					},
   	   	    					failure: function(){
   	   	    						Ext.Msg.alert('系统提示','操作失败！');
   	   	    					}
   	   	    				});
   	   	    			}
   	   	    		});
   				}else{
   					Ext.Msg.confirm('系统提示：',"确定启用此微信号？",function(btn){
   	   	    			if(btn == 'yes'){
   	   	    				Ext.Ajax.request({
   	   	    					url: 'update',
   	   	    					method: 'POST',
   	   	    					params: {id: id,zt:zt},
   	   	    					success: function(){
   	   	    						Ext.Msg.alert('系统提示','操作成功！');
   	   	    						tlrDgStModelGrid.store.load({params:{start:0,limit:PAGESIZE}});
   	   	    					},
   	   	    					failure: function(){
   	   	    						Ext.Msg.alert('系统提示','操作失败！');
   	   	    					}
   	   	    				});
   	   	    			}
   	   	    		});
   				}
   			}else{
   				Ext.Msg.alert('系统提示','不能同时选择多条记录！');
   			}
   		}else{
   			Ext.Msg.alert('系统提示','请选择一条记录！');
   		}    	
    },
    onDeleteClick: function(){
    	var records = this.getSelectionModel().getSelections();
    	var valueStr = [];
    	for(var i=0;i<records.length;i++){
    		valueStr.push(records[i].get('id'));
    	}
    	if(records.length>0){
    		Ext.Msg.confirm('系统提示：',"确定删除这"+records.length+"条信息吗？",function(btn){
    			if(btn == 'yes'){
    				Ext.Ajax.request({
    					url: 'delete',
    					method: 'POST',
    					params: {ids: valueStr},
    					success: function(){
    						Ext.Msg.alert('系统提示','删除成功！');
    						tlrDgStModelGrid.store.load({params:{start:0,limit:PAGESIZE}});
    					},
    					failure: function(){
    						Ext.Msg.alert('系统提示','删除失败！');
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
    Ext.Ajax.timeout = 180000; //3分钟超时  
    
    tlrDgStModelGrid = new TlrDgStModelGrid(Ext.getBody().getViewSize().height);
    tlrDgStModelGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        tlrDgStModelGrid   
		]
	});
});