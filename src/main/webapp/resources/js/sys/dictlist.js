var USER_GRID_STORE_URL = '/sys/SysDict/getFirstDict';
var PAGESIZE = 20;
var rowId;
var zl;//字典种类

/**************DictAddForm**************************/
DictAddForm = Ext.extend(Ext.ux.Form,{
	constructor: function(){
		this.zdid = this.createHidden('ID', 'id', '95%');
		this.zdjb = this.createHidden('级别', 'jb', '95%');
		this.zdbm = this.createTextField('<font color="red">*</font>字典编码','zdbm','95%','',null,100,'长度超过不能100');
		this.zdbm.allowBlank = false;
		this.zdmc = this.createTextField('<font color="red">*</font>字典名称','zdmc','95%','',null,100,'长度超过不能100');
		this.zdmc.allowBlank = false;
		this.zdz = this.createTextField('<font color="red">*</font>字 典 值','zdz','95%','',null,100,'长度超过不能100');
		this.zdz.allowBlank = false;
		this.zdzl = this.createTextField('<font color="red">*</font>种     类','zl','95%','',null,100,'长度超过不能100');
		this.zdzl.allowBlank = false;
		this.px = this.createNumberField('<font color="red">*</font>排    序','px','95%','',null,100,'长度超过不能100');
		this.px.allowBlank = false;
        
        DictAddForm.superclass.constructor.call(this, {
	            anchor: '100%',
	            autoHeight:true,
	            labelWidth: 90,
	            labelAlign :'right',
	            frame: true,
	            bodyStyle:"padding: 5px 5px 0",
	            layout: 'tableform',
	            layoutConfig: {columns: 1},
	            items:[
	                   this.zdid,
	                   this.zdjb,
	                   this.zdzl,
	                   this.zdmc,
	                   this.zdbm,
	                   this.zdz,
	                   this.px
	            ],
	            buttonAlign :'center',
	            buttons: [
	               {text: '保存', width: 20,iconCls: 'save', hidden: false,handler:this.addFormClick,scope:this},
	               {text: '修改', width: 20,iconCls:'edit', hidden: true, handler: this.updateFormClick, scope: this},
	               {text: '重置', width: 20,iconCls:'redo', hidden: true, handler: this.onResumeClick, scope: this},               
	               {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
	            ]
	        });
	},
	addFormClick: function(){
		var thiz = this;
		var gridRecord = constructionGrid.selectedRecord();
		if(this.getForm().isValid()){
			var jb = this.zdjb.getValue();
			var zdzl = this.zdzl.getValue();
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: PROJECT_NAME+'/sys/SysDict/save',
				method: 'POST',
					success: function(form,action){
						Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
					if(jb==1){
						constructionGrid.useCreditInsertWindow.hide();
						constructionGrid.store.reload();
					}else{
						constructionGrid.useCreditInsertWindow.hide();
					    Ext.getCmp(""+zdzl+"").store.load({params:{zl:zdzl,start : 0,limit : 1000}});
					}
				},
				failure: function(form,action){
					 Ext.MessageBox.alert("系统提示:",action.result.message);
				}
			});
		}
	},
	
	//关闭
    onCloseClick: function(){
        this.ownerCt.hide();
    },
  //清空
    resetFormClick: function() {        
        this.getForm().reset();
    } 
});

/**************UpdateForm**************************/
UpdateForm = Ext.extend(Ext.ux.Form,{
	constructor: function(){
		this.zdid = this.createHidden('ID', 'id', '95%');
		this.zdjb = this.createHidden('级别', 'jb', '95%');
		this.zdbm = this.createTextField('<font color="red">*</font>字典编码','zdbm','95%','',null,100,'长度超过不能100');
		this.zdbm.allowBlank = false;
		this.zdmc = this.createTextField('<font color="red">*</font>字典名称','zdmc','95%','',null,100,'长度超过不能100');
		this.zdmc.allowBlank = false;
		this.zdz = this.createTextField('<font color="red">*</font>字 典 值','zdz','95%','',null,100,'长度超过不能100');
		this.zdz.allowBlank = false;
		this.zdzl = this.createTextField('<font color="red">*</font>种     类','zl','95%','',null,100,'长度超过不能100');
		this.zdzl.allowBlank = false;
		this.zdzl.readOnly=true,
		this.zdzl.style = 'background:#E6E6E6';
		this.px = this.createNumberField('<font color="red">*</font>排    序','px','95%','',null,100,'长度超过不能100');
		this.px.allowBlank = false;
		
        
		UpdateForm.superclass.constructor.call(this, {
	            anchor: '100%',
	            autoHeight:true,
	            labelWidth: 90,
	            labelAlign :'right',
	            frame: true,
	            bodyStyle:"padding: 5px 5px 0",
	            layout: 'tableform',
	            layoutConfig: {columns: 1},
	            items:[
	                   this.zdid,
	                   this.zdjb,
	                   this.zdzl,
	                   this.zdmc,
	                   this.zdbm,
	                   this.zdz,
	                   this.px
	            ],
	            buttonAlign :'center',
	            buttons: [
	               {text: '保存', width: 20,iconCls: 'save', hidden: true,handler:this.addFormClick,scope:this},
	               {text: '修改', width: 20,iconCls:'edit', hidden: false, handler: this.updateFormClick, scope: this},
	               {text: '重置', width: 20,iconCls:'redo', hidden: true, handler: this.onResumeClick, scope: this},               
	               {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
	            ]
	        });
	},
	updateFormClick: function(){
		var thiz = this;
		var gridRecord = constructionGrid.selectedRecord();
		if(this.getForm().isValid()){
			var jb = this.zdjb.getValue();
			var zdzl = this.zdzl.getValue();
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: PROJECT_NAME+'/sys/SysDict/update',
				method: 'POST',
					success: function(form,action){
						Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
					if(jb==1){
						constructionGrid.constructionUpdateWindow.hide();
						constructionGrid.store.reload();
					}else{
						constructionGrid.constructionUpdateWindow.hide();
					    Ext.getCmp(""+zdzl+"").store.load({params:{zl:zdzl,start : 0,limit : 1000}});
					}
				},
				failure: function(form,action){
					 Ext.MessageBox.alert("系统提示:",action.result.message);
				}
			});
		}
	},
	
	//关闭
    onCloseClick: function(){
    	//constructionGrid.blackLawsuitUpdateWindow.dictAddForm.getForm().reset();
        this.ownerCt.hide();
    },
  //清空
    resetFormClick: function() {        
        this.getForm().reset();
    } 
});

/**************UseCreditInsertWindow*********************/
UseCreditInsertWindow = Ext.extend(Ext.Window,{
	
	constructor: function(grid){
		this.dictAddForm = new DictAddForm();
		UseCreditInsertWindow.superclass.constructor.call(this,{
			title: '添加字典',
			 width: 500,
			 anchor: '100%',
//			height:400,
			autoHeight:true,
			resizable : false, //可变尺寸的；可调整大小的
			 plain: true,
			 modal: true,
			closeAction: 'hide',
			items:[this.dictAddForm]
		});
	}
});

/***************************************DictTypeUpdateWindow组件**************************************************/
DictTypeUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new UpdateForm();
    	DictTypeUpdateWindow.superclass.constructor.call(this, {
        	title: "修改字典",
            width: 600,
            autoHeight:true,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.constructionForm]
        });
    }
});

/** ************************ConstructionGrid对私用信档案管理****************************************** */
ConstructionGrid = Ext.extend(UxGrid,{
	pageSizeCombo : null,
	vtbar : null, // 面板顶部的工具条
	vbbar : null, // 面板底部的工具条
	store : null,
	constructor : function(height, width) {
		this.store = new Ext.data.Store({ // Grid Store
			proxy : new Ext.data.HttpProxy({
				url : PROJECT_NAME+""+USER_GRID_STORE_URL,
				method : 'POST'
			}),
			reader : new Ext.data.JsonReader({
				totalProperty : 'total',root : 'rows'
			},[{name : 'id'}, {name : 'zdbm'}, {name : 'zdmc'}, {name : 'zdz'}, {name : 'jb'}, {name : 'zl'}, {name : 'px'}, {name : 'count'}
			])
		});
		this.vbbar = this.createPagingToolbar(PAGESIZE);
		this.useCreditInsertWindow = new UseCreditInsertWindow();
		this.constructionUpdateWindow = new DictTypeUpdateWindow();
		this.vtbar = new Ext.Toolbar({
			items : [
				'-',
				{
					xtype:'button',
					text:'添加一级字典',
					iconCls:'add',
					handler:function() {
						var i = 1;
						this.onAddClick(i);
					},
					scope:this
				},
				'-',
//				{
//					xtype:'button',
//					text:'添加二级字典',
//					iconCls:'add',
//					handler:this.onAddClick,
//					scope:this
//				},
//				'-',
				{
					text : '修改',
					iconCls : 'edit',
					handler : this.onUpdateSecondClick,
					scope : this
				},
				'->',
				{
					xtype : 'label',
					text : '字典种类：'
				},
				{
					xtype : 'textfield',
					id : 'zdzl'
				},
				'-',
				{
					xtype : 'label',
					text : '字典名称：'
				},
				{
					xtype : 'textfield',
					id : 'zdmc'
				},
				'-',
				{
					xtype : 'button',
					text : '查询',
					iconCls : 'query',
					handler : function() {
						var zdzl = Ext.getCmp("zdzl").getValue();
						var zdmc = Ext.getCmp("zdmc").getValue();
						constructionGrid.store.baseParams = {
							zdmc : zdmc,
							zdzl : zdzl
						};
						constructionGrid.store.load({params : {start : 0,limit : PAGESIZE}});
					}
			} ]
		});
		this.expander = new Ext.ux.grid.RowExpander({
		    tpl : new Ext.XTemplate( 
		     '<div class="detailData">', 
		     '', 
		     '</div>' 
		    ) 
         }); 
	    this.expander.on("collapse",function(expander,r,body,rowIndex){
			zl=r.get('zl');
			if(Ext.getCmp(zl)){
				Ext.getCmp(zl).destroy();
			}
	    });
	    this.expander.on("expand",function(expander,r,body,rowIndex){ 
//	    	Ext.DomQuery.select("div.detailData",body)[0]
	    	if(Ext.DomQuery.select("div.detailData",body).length >1){
		    	for(var i=0;i<Ext.DomQuery.select("div.detailData",body).length;i++){
			    	Ext.DomQuery.select("div.detailData",body)[i].collapse();
		    	}
	    	}
		   //查找 grid  
		   window.testEle=body; 
		   if (Ext.DomQuery.select("div.x-panel-bwrap",body).length==0){ 
		      zl=r.get('zl');
			  productId=r.get('productId');
			  creditId=r.get('creditId');
//			  Ext.DomQuery.select("div.detailData")[0]; 
//			  pledgeGrid = new PledgeGrid(zl,Ext.DomQuery.select("div.detailData")[0]); 
//			  pledgeGrid.store.load({params:{creditId:r.data.creditId}});
		      var store=new Ext.data.Store({ // Grid Store
				proxy:new Ext.data.HttpProxy({
					url : PROJECT_NAME+""+'/sys/SysDict/getSecondDict',
					method : 'POST'
				}),
				reader:new Ext.data.JsonReader({
					totalProperty : 'total',root : 'rows'
				},[{name : 'id'}, {name : 'zdbm'}, {name : 'zdmc'}, {name : 'zdz'}, {name : 'jb'}, {name : 'zl'}, {name : 'px'}
				])
			 });
		      var sm = new Ext.grid.CheckboxSelectionModel();
		      var cm = new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
		      	{header: "",align : 'center', dataIndex: 'id',width: 130,hidden:true,sortable:true},
		      	{header:'操作',align : 'center', dataIndex:'id',width:153,sortable:true,
            		renderer:function(value, cellMeta, record, rowIndex, columnIndex, store) {
            			//alert(record.data['zdbm']);
            			var id = record.data['id'];
            			var zdbm = record.data['zdbm'];
            			var zdmc = record.data['zdmc'];
            			var zdz = record.data['zdz'];
            			var jb = record.data['jb'];
            			var zl = record.data['zl'];
            			var px = record.data['px'];	
            			//var returnStr = '<INPUT style='font-size:11px' type='button' value='修改' onclick='constructionGrid.onUpdateSecondClick("+record+");'> <INPUT type='button' value='删除'>';
            			var returnStr = '<a style="color:blue;cursor: pointer;" onclick="constructionGrid.onUpdateSecondClick(\''+id+'\',\''+zdbm+'\',\''+zdmc+'\',\''+zdz+'\',\''+jb+'\',\''+zl+'\',\''+px+'\')" title="点击修改">修改</a>'
            			+'&nbsp;&nbsp;&nbsp;<a style="color:red;cursor: pointer;" onclick="constructionGrid.onDeleteClick(\''+id+'\',\''+jb+'\',\''+zl+'\')" title="点击删除">删除</a>';
                        return returnStr;
            		}
            	},
		      	{header: "",align : 'center', dataIndex: 'zdbm',width: 100,sortable:true,color:'red'},
		      	{header: "",align : 'center', dataIndex: 'zdmc',width: 100,sortable:true},
		      	{header: "",align : 'center', dataIndex: 'zdz',width: 100,sortable:true},
		      	{header: "",align : 'center', dataIndex: 'jb',width: 100,hidden:true,sortable:true},
		      	{header: "",align : 'center', dataIndex: 'px',width: 100,sortable:true},
		      	{header: "",align : 'center', dataIndex: 'zl',width: 100,sortable:true}
		       ]); 
		       Ext.DomQuery.select("div.detailData")[0]; 
			   new Ext.grid.GridPanel({ 
			     store:store, 
			     id:zl,
			     cm:cm, 
			     stateful: false,
			     frame:false,
			     viewConfig : {
					forceFit : false,
					getRowClass : function(record,rowIndex,rowParams,store){  
	                    //禁用数据显示红色  
						  return 'x-grid-back-red';   
	                }
				},
				loadMask : {
					msg : '正在载入数据,请稍候...'
				},
			     renderTo:Ext.DomQuery.select("div.detailData",body)[0], 
			     autoWidth:true, 
			     autoHeight:true ,
			     listeners:{
			     	dblclick:function(e){
				        e.stopEvent();
			     	},
					mouseover: function(e){
				        e.stopEvent();
					},
					rowmousedown: function(g, r, e){
				        e.stopEvent();
					}
			     }
			  }); 
			  store.load({params:{zl:r.data.zl,start : 0,
					limit : 1000}});
		   } 
		 }); 
		var sm = new Ext.grid.CheckboxSelectionModel();
		ConstructionGrid.superclass.constructor.call(this,{
			region : 'center',
			stripeRows : true,
			frame : false,
			height : height,
			viewConfig : {
				forceFit : false
			},
			loadMask : {
				msg : '正在载入数据,请稍候...'
			},
			sm : sm,
			plugins:this.expander,
			cm : new Ext.grid.ColumnModel([this.expander,new Ext.grid.RowNumberer(),sm,
                {header: "字典id",align : 'center',dataIndex: 'id',width: 130,hidden:true,sortable:true},
                {header: "",align : 'center',dataIndex: 'count',width: 130,hidden:true,sortable:true},
//                {header:'操作',align : 'center', dataIndex:'id',width:150,sortable:true,
//            		renderer:function(value){
//            			var returnStr = "<INPUT type='button' value='添加'> <INPUT type='button' value='修改'> <INPUT type='button' value='删除'>";
//                        return returnStr;
//            		}
//            	},
            	{header:'操作',align : 'center', dataIndex:'id',width:140,sortable:true,
            		renderer:function(value, cellMeta, record, rowIndex, columnIndex, store) {
            			//alert(record.data['zdbm']);
            			var id = record.data['id'];
            			var zdbm = record.data['zdbm'];
            			var zdmc = record.data['zdmc'];
            			var zdz = record.data['zdz'];
            			var jb = record.data['jb'];
            			var zl = record.data['zl'];
            			var px = record.data['count'];	
            			var returnStr = '<a style="color:blue;cursor: pointer;" onclick="constructionGrid.onAddClick(\''+zl+'\',\''+zdmc+'\',\''+px+'\')" title="点击添加子项">添加二级字典</a>'
            				+'&nbsp;&nbsp;&nbsp;<a style="color:red;cursor: pointer;" onclick="constructionGrid.onDeleteClick(\''+id+'\',\''+jb+'\',\''+zl+'\')" title="点击删除">删除</a>';
                        return returnStr;
            		}
            	},
		      	{header: "字典编码",align : 'center',dataIndex: 'zdbm',width: 100,sortable:true},
		      	{header: "字典名称",align : 'center',dataIndex: 'zdmc',width: 100,sortable:true},
		      	{header: "字典值",align : 'center',dataIndex: 'zdz',width: 100,sortable:true},
		      	{header: "级别",align : 'center',dataIndex: 'jb',width: 100,hidden:true,sortable:true},
		      	{header: "排序",align : 'center',dataIndex: 'px',width: 100,sortable:true},
		      	{header: "种类",align : 'center',dataIndex: 'zl',width: 100,sortable:true} 
			]),
			tbar : this.vtbar,
			bbar : this.vbbar,
			ds : this.store,
			listeners : {
				cellclick:function(grid, rowIndex, columnIndex, e){
					rowId=rowIndex;
				}
			}
		});
	},
	onAddClick: function(zl,zdmc,px){ 
		var win = this.useCreditInsertWindow;
		    	win.show();
		    	win.dictAddForm.getForm().reset();
		    	var winForm = win.dictAddForm;
		    	if(zl != 1){
//		    		alert(px);
		    		winForm.zdzl.setValue(zl);
		    		winForm.zdjb.setValue(2);
//		    		winForm.px.setValue(parseInt(px)+1);
		    	}else{
		    		winForm.zdjb.setValue(1);
		    		winForm.px.setValue(1);
		    	}
    },
    onUpdateSecondClick : function (id,zdbm,zdmc,zdz,jb,zl,px){
        	var records=this.getSelectionModel().getSelections();
        	if(jb == null ){
        		i=records.length;
    		}else{
    			i=1;
    		}
       		if(i > 0) {
       			if(i == 1){
       				var win = this.constructionUpdateWindow;
       		    	win.show();
       		    	var winForm = win.constructionForm;
       		    	if(jb == null ){
       		    		vrecord = records[0];
       		    		winForm.zdid.setValue(vrecord.data.id);
       		    		winForm.zdbm.setValue(vrecord.data.zdbm);
       		    		winForm.zdjb.setValue(vrecord.data.jb);
       		    		winForm.zdzl.setValue(vrecord.data.zl);
       		    		winForm.zdmc.setValue(vrecord.data.zdmc);
       		    		winForm.px.setValue(vrecord.data.px);
       		    		winForm.zdz.setValue(vrecord.data.zdz);
       		    	}else{
       		    		winForm.zdid.setValue(id);
       		    		winForm.zdbm.setValue(zdbm);
       		    		winForm.zdjb.setValue(jb);
       		    		winForm.zdzl.setValue(zl);
       		    		winForm.zdmc.setValue(zdmc);
       		    		winForm.px.setValue(px);
       		    		winForm.zdz.setValue(zdz);
       		    	}
       			}else{
       				Ext.Msg.alert('系统提示', '不能修改多条记录..');
       			}
       		}else{
       			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
       		}    	
    },
	onModifyClick : function() {
	},
	onDeleteClick : function(id,jb,zl) {
		var str="";
		var records = this.getSelectionModel().getSelections();
		var i = "";
		if(jb == 1 ){
			str="确定要删除这条数据字典及以下的子项吗？";
			i=records.length;
		}else{
			str="确定要删除这条数据字典吗？";
			i=1;
		}
//		alert(i);
		if (i > 0) {
			if (i == 1) {
				Ext.Msg.confirm("提醒信息", str,function(btn){
					if (btn == 'yes') {
						Ext.Ajax.request({
							url:'delete',
							method : 'POST', 
							params: { id:id,jb:jb,zl:zl},
							success: function(form, action) {
								Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除成功!" + BLANKSTR);
								if(jb == 1){
									constructionGrid.store.reload();
								}else{
									Ext.getCmp(""+zl+"").store.load({params:{zl:zl,start : 0,limit : 1000}});
								}
							},
							failure: function(form, action) {
								Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除失败!" + BLANKSTR);
							}
						});					
					}
				});	
			} else {
				Ext.Msg.alert('系统提示', BLANKSTR + '不能处理多条记录.'+ BLANKSTR);
			}
		} else {
			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录'+ BLANKSTR);
		}
		
	},
	sheetNoChange : function(value) {
		return '<a href="javascript:void(0)" onclick=javascript:clickSheetNo('+ value+ ')><b><font color=red>'+ value+ '</font></b></a>';
	},
	selectedRecord : function() {
		var record = this.getSelectionModel().getSelected();
		return record;
	},
	refresh : function() {
		this.getView().refresh();
	},
	remove : function(record) {
		this.getStore().remove(record);
	}
});

/**
 * *******************onReady
 * 组件渲染及处理*********************************************
 */
Ext.onReady(function() {
	Ext.QuickTips.init(); // 开启快速提示
	Ext.form.Field.prototype.msgTarget = 'side';

	constructionGrid = new ConstructionGrid(
			Ext.getBody().getViewSize().height - 160, Ext.getBody()
					.getViewSize().width);
	constructionGrid.store.load({
		params : {
			start : 0,
			limit : PAGESIZE
		}
	});
	new Ext.Viewport({
		layout : 'border',
		items : [
		// conditionForm,
		constructionGrid ]
	});

});