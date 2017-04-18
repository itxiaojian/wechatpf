var TLR_DG_ST_TEST_MODEL_URL = 'getXscjList';
var PAGESIZE=10;

/****************TlrDgStModelGrid***********************/
TlrDgStModelGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			proxy: new Ext.data.HttpProxy({url:TLR_DG_ST_TEST_MODEL_URL,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
			         {name:'id'},{name:'ksxn'},{name:'ksxq'},{name:'xh'},{name:'kskm'},
			         {name:'kcbh'},{name:'kscj'},{name:'sftg'},{name:'bkcj'},{name:'xf'},
			         {name:'bz'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'添加',iconCls:'add',handler:this.onAddClick,scope:this}, 
			       '-',{xtype:'button',text:'修改',iconCls:'edit',handler:this.onModifyClick,scope:this}, 
			       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this},
			       '-',{xtype:'label',text:'考核标准名称'},{xtype:'textfield',id:'khdl'},
			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
			    	   			var khdl = Ext.getCmp('khdl').getValue();
			    	   			tlrDgStModelGrid.store.baseParams= {khdl:khdl};
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
		            {header:'考试科目',dataIndex:'kskm',width:120,sortable:true},
		            {header:'考试成绩',dataIndex:'kscj',width:100,sortable:true},
		            {header:'是否通过',dataIndex:'sftg',width:100,sortable:true,
		            	renderer: function(value){
		            		if(value=='1'){
		            			return '通过';
		            		}else{
		            			return '不通过';
		            		}
						}
		            }
					]);
		TlrDgStModelGrid.superclass.constructor.call(this,{
			region: 'center',
			title: '管理',
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
    onAddClick: function(){
    	var win = this.tlrDgStModelAddWindow;
    		win.show();
    		win.tlrDgStModelForm.getForm().reset();
    },
    onModifyClick: function() {
    	var records = this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.tlrDgStModelUpdateWindow;
   		    	win.tlrDgStModelForm.getForm().loadRecord(vrecord);
   		    	win.tlrDgStModelForm.khdlbh.setValue(vrecord.data.khdlbh);
   		    	win.tlrDgStModelForm.khbz.setValue(vrecord.data.khbz);
   		    	win.tlrDgStModelForm.dxdx.setValue(vrecord.data.dxdx);
   		    	win.show();
   			}else{
   				Ext.Msg.alert('系统提示','不能修改多条记录！');
   			}
   		}else{
   			Ext.Msg.alert('系统提示','请选择一条记录！');
   		}    	
    },
    onUploadClick:function(){
    	this.tranWin.show();
    },
    onDowloadClick:function(){
    	this.downWin.show();
    },
    onDeleteClick: function(){
    	var records = this.getSelectionModel().getSelections();
    	var valueStr = [];
    	for(var i=0;i<records.length;i++){
    		valueStr.push(records[i].get('bh'));
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
    
    tlrDgStModelGrid = new TlrDgStModelGrid(Ext.getBody().getViewSize().height);
    tlrDgStModelGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        tlrDgStModelGrid   
		]
	});
});