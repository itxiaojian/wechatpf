var DICT_GRID_STORE_URL = '/sys/SysMenu/getFirstMenu';
var DICT_ENTRY_GRID_STORE_URL = '/sys/SysMenu/getSonMenu';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.cdbh = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>菜单编号',
            name: 'cdbh',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:true
        });
    	
		this.cdmc = this.createTextField('<font color="red">*</font>菜单名称','cdmc','95%','',null,100,'长度超过不能100');
        this.cdmc.allowBlank = false;
        
        this.px = this.createTextField('<font color="red">*</font>排序','px','95%','',null,100,'长度超过不能100');
        this.px.allowBlank = false;
        
        this.cdurl = new Ext.form.TextArea({
            fieldLabel: '菜单url:',
            name: 'cdurl',
            readOnly: false,
            anchor: '95%',
            height:50,
            maxLength: 500,
            maxLengthText: '最大字符数500！'
        });
        
        
        this.cdjb = this.createTextField('<font color="red">*</font>菜单级别','cdjb','95%','1',null,100,'长度超过不能100');
        this.cdjb.setValue(1);
        this.cdjb.hidden=true;
        
        this.sjcd = this.createTextField('<font color="red">*</font>上级菜单','sjcd','95%','1',null,100,'长度超过不能100');
        this.sjcd.setValue(0);
        this.sjcd.hidden=true;
        
        this.bz = new Ext.form.TextArea({
            fieldLabel: '备注:',
            name: 'bz',
            readOnly: false,
            anchor: '95%',
            height:60,
            maxLength: 256,
            maxLengthText: '最大字符数256！'
        });
        
        DictTypeForm.superclass.constructor.call(this, {
        	anchor: '100%',
        	autoHeight:true,
        	layout:"tableform",
        	layoutConfig: {columns: 1},
        	labelWidth: 90,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [
                this.cdbh,
            	this.cdmc,
//            	this.lx,
            	this.px,
            	this.cdurl,
            	this.cdjb,
            	this.sjcd, 
            	this.bz
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
                      {text:'修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/SysMenu/save',   
                 method: 'POST',
                 success: function(form, action) {
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.constructionInsertWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:", action.result.message);
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/SysMenu/update', 
                 method: 'POST',
//                 params:{
//                 	scid:record[0].get('scid')
//                 },
                 success: function(form, action) { 
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictTypeGrid.constructionUpdateWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:", action.result.message);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});


/***************************************DictTypeInsertWindow组件**************************************************/
DictTypeInsertWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new DictTypeForm();
        this.constructionForm.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //显示修改按钮
        DictTypeInsertWindow.superclass.constructor.call(this, {
            title: "添加一级菜单",
            width: 400,
            anchor: '100%',
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

/***************************************DictTypeUpdateWindow组件**************************************************/
DictTypeUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new DictTypeForm();
    	this.constructionForm.buttons[0].hide();   //隐藏添加按钮
    	this.constructionForm.buttons[1].show();   //显示修改按钮
    	DictTypeUpdateWindow.superclass.constructor.call(this, {
        	title: "修改一级菜单配置",
            width: 400,
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
/**************************DictTypeGrid*******************************************/
DictTypeGrid = Ext.extend(UxGrid, {
	pageSizeCombo: PAGESIZE,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    module_name:null,
    type_name:null,
    constructor: function(height, width){
    	var thiz = this;
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+DICT_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
            		{name:'cdbh'}, {name:'cdmc'}, {name:'cdurl'}, {name:'sjcd'}, {name:'cdjb'},{name:'bz'},{name:'px'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '一级菜单',
        	stripeRows: true,
            frame: true,
            height: height,
            width :100,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header: '菜单编号', width: 100, dataIndex: 'cdbh', hidden: true},
	            {header: '菜单名称', width: 150, dataIndex: 'cdmc', sortable: true,hidden: false},
	            {header:'菜单级别',dataIndex:'cdjb',width:80,sortable:true,hidden:false},
	            {header:'链接',dataIndex:'cdurl',width:350,sortable:true,hidden:true},
	            {header:'备注',dataIndex:'bz',width:160,sortable:true,hidden:false},
	            {header:'排序',dataIndex:'px',width:80,sortable:true,hidden:false}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
            	'click':function(){
            		var records=this.getSelectionModel().getSelections();
            		if(records.length == 1){
            			var sjid = records[0].get('cdbh');
            			var name = records[0].get('cdmc');
            			var cdlx = records[0].get('cdjb');
            			dictEntryGrid.setTitle('<span style="color:red">'+name+'</span> 对应的子级菜单');
            			dictEntryGrid.sjid = sjid;
            			dictEntryGrid.cdlx = cdlx;
            			dictEntryGrid.store.baseParams = {
            					limit:PAGESIZE,
            					sjid:sjid
            			};
            			dictEntryGrid.store.load({params:{start:0}});
            		}
            	}
            }
        });
    },
    onAddClick: function() {
    		var win = this.constructionInsertWindow;
    		win.show();
    		win.constructionForm.getForm().reset();
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				var win = this.constructionUpdateWindow;
   		    	win.show();
   		    	var winForm = win.constructionForm;
   		    	winForm.cdmc.setValue(vrecord.data.cdmc);
   		    	winForm.cdbh.setValue(vrecord.data.cdbh);
				winForm.cdurl.setValue(vrecord.data.cdurl);
				winForm.sjcd.setValue(vrecord.data.sjcd);
				winForm.cdjb.setValue(vrecord.data.cdjb);
				winForm.bz.setValue(vrecord.data.bz);
				winForm.px.setValue(vrecord.data.px);
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onDeleteClick: function() {
    	var records=this.getSelectionModel().getSelections();
    	var valueStr=[];
   		if(records.length>0) {
	       	for(var i=0;i<records.length;i++){
	       		valueStr.push(records[i].get('cdbh'));
    	 	}
	    	Ext.Msg.confirm("提醒信息", "确定要删除这 " + records.length + " 条信息吗",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/sys/SysMenu/delete',   
				       	   method : 'POST', 
				       	   params: { ids: valueStr},
			               success: function(form, action) { 
				               	var obj = Ext.util.JSON.decode(form.responseText);
				               	if(obj.success == true){   //true / false
				               		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除成功!" + BLANKSTR);
					               	 dictTypeGrid.store.reload();
				               	}else{
				               		 Ext.MessageBox.alert("系统提示:", obj.message);
					               	 dictTypeGrid.store.reload();
				               	}
				              
			               },
			               failure: function(form, action) {
			               		Ext.MessageBox.alert("系统提示：",action.result.failure);
			               }
				       	});					
				}
	    	});	
    	}else{
    		 Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
         	return;
    	}
    },
    clickSheetNo: function() {
    	alert(11);
    },
    selectedRecord: function() {
        var record = this.getSelectionModel().getSelected();
        return record;
    },
    refresh: function(){
        this.getView().refresh();
    },
    remove:function(record){
        this.getStore().remove(record);
    }
});

/***************************************DictEntryForm组件**************************************************/

DictEntryForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.cdbh = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>菜单编号',
            name: 'cdbh',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:true
        });
    	
		this.cdmc = this.createTextField('<font color="red">*</font>菜单名称','cdmc','95%','',null,100,'长度超过不能100');
        this.cdmc.allowBlank = false;
        
        this.px = this.createTextField('<font color="red">*</font>排序','px','95%','',null,100,'长度超过不能100');
        this.px.allowBlank = false;
        
        this.cdurl = new Ext.form.TextArea({
            fieldLabel: '菜单url:',
            name: 'cdurl',
            readOnly: false,
            anchor: '95%',
            height:50,
            maxLength: 500,
            maxLengthText: '最大字符数500！'
        });
        
        this.cdjb = this.createTextField('<font color="red">*</font>菜单级别','cdjb','95%','1',null,100,'长度超过不能100');
        this.cdjb.setValue("2");
        this.cdjb.hidden=true;
        
        this.sjcd = this.createTextField('<font color="red">*</font>上级菜单','sjcd','95%','1',null,100,'长度超过不能100');
        this.sjcd.hidden=true;
        
        this.bz = new Ext.form.TextArea({
            fieldLabel: '备注:',
            name: 'bz',
            readOnly: false,
            anchor: '95%',
            height:60,
            maxLength: 256,
            maxLengthText: '最大字符数256！'
        });
        
        DictEntryForm.superclass.constructor.call(this, {
        	anchor: '100%',
        	autoHeight:true,
        	layout:"tableform",
        	layoutConfig: {columns: 1},
        	labelWidth: 90,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [
					this.cdbh,
					this.cdmc,
					this.px,
					this.cdurl,
					this.cdjb,
					this.sjcd, 
					this.bz				
			
            	
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
                      {text:'修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     addFormClick: function() {
     	
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/SysMenu/save', 
                 method: 'POST',
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictEntryGrid.constructionInsertWindow.hide();
                 	dictEntryGrid.store.reload();
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", action.result.message);
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/SysMenu/update', 
                 method: 'POST',
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictEntryGrid.constructionUpdateWindow.hide();
                 	dictEntryGrid.store.reload();
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", action.result.message);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});


/***************************************DictEntryInsertWindow组件**************************************************/
DictEntryInsertWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new DictEntryForm();
        this.constructionForm.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //显示修改按钮
        DictEntryInsertWindow.superclass.constructor.call(this, {
            title: "添加子级菜单",
            width: 600,
            anchor: '100%',
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

/***************************************DictEntryUpdateWindow组件**************************************************/
DictEntryUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new DictEntryForm();
    	this.constructionForm.buttons[0].hide();   //隐藏添加按钮
    	this.constructionForm.buttons[1].show();   //显示修改按钮
    	DictEntryUpdateWindow.superclass.constructor.call(this, {
        	title: "修改子级菜单",
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
/**************************DictEntryGrid*******************************************/
DictEntryGrid = Ext.extend(UxGrid, {
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    sjid:'',
    cdlx:'',
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+DICT_ENTRY_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
	     		{name:'cdbh'}, {name:'cdmc'}, {name:'cdurl'}, {name:'sjcd'}, {name:'cdjb'},{name:'bz'},{name:'px'}
            ])
        });

    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this}
            ]
        });	
        this.constructionInsertWindow = new DictEntryInsertWindow();       
        this.constructionUpdateWindow = new DictEntryUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictEntryGrid.superclass.constructor.call(this, {
        	id : 'gird_id',
        	region:'east',
        	title: '添加子级菜单',
        	collapsible: true,
        	stripeRows: true,
            frame: true,
            //width:470,
            width : 780,
            height: height,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
  	            {header:'菜单编号', width: 80, dataIndex: 'cdbh', hidden: true},
	            {header:'菜单名称', width: 130, dataIndex: 'cdmc', sortable: true,hidden: false},
	            {header:'菜单级别',dataIndex:'cdjb',width:70,sortable:true,hidden:false},
	            {header:'上级菜单',dataIndex:'sjcd',width:100,sortable:true,hidden:true},
	            {header:'链接',dataIndex:'cdurl',width:300,sortable:true,hidden:false},
	            {header:'备注',dataIndex:'bz',width:140,sortable:true,hidden:false},
	            {header:'排序',dataIndex:'px',width:80,sortable:true,hidden:false}
				
            ]),
            tbar: this.vtbar,
            ds: this.store,
            listeners: {
            }
        });
    },
    onAddClick: function() {
    	if(this.cdlx != '1'){
    		Ext.MessageBox.alert("提示", "请先选择一级菜单！！");
    		return false;    		
    	}
    	if(this.sjid ==null || this.sjid ==''){
    		Ext.MessageBox.alert("提示", "请选择一级菜单！");
    		return false;
    	}
    	var count = this.getStore().getTotalCount();
	    	var win = this.constructionInsertWindow;
	    	var winForm = win.constructionForm;
	    	win.show();
	    	win.constructionForm.getForm().reset();
	    	winForm.sjcd.setValue(this.sjid);
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.constructionUpdateWindow;
   		    	win.constructionForm.getForm().reset();
   		    	var winForm = win.constructionForm;
   		    	win.show();
   		    	winForm.cdmc.setValue(vrecord.data.cdmc);
   		    	winForm.cdbh.setValue(vrecord.data.cdbh);
				winForm.cdurl.setValue(vrecord.data.cdurl);
				winForm.sjcd.setValue(vrecord.data.sjcd);
				winForm.cdjb.setValue(vrecord.data.cdjb);
				winForm.bz.setValue(vrecord.data.bz);
				winForm.px.setValue(vrecord.data.px);
   		    	
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onDeleteClick: function() {
    	var records=this.getSelectionModel().getSelections();
    	var valueStr=[];
   		if(records.length>0) {
	       	for(var i=0;i<records.length;i++){
	       		valueStr.push(records[i].get('cdbh'));
    	 	}
	    	Ext.Msg.confirm("提醒信息", "确定要删除这 " + records.length + " 条信息吗",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/sys/SysMenu/delete',
				       	   method : 'POST', 
				       	   params: { ids: valueStr},
			               success: function(form, action) { 
				               Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除成功!" + BLANKSTR);
				               dictEntryGrid.store.reload();
			               },
			               failure: function(form, action) {
			            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除失败!" + BLANKSTR);
			               }
				       	});					
				}
	    	});	
    	}else{
    		 Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
         	return;
    	}
    },
    sheetNoChange: function(value) {
    	return '<a href="javascript:void(0)" onclick=javascript:clickSheetNo('+value+')><b><font color=red>'+ value + '</font></b></a>';
    },
    clickSheetNo: function() {
    	alert(11);
    },
    selectedRecord: function() {
        var record = this.getSelectionModel().getSelected();
        return record;
    },
    refresh: function(){
        this.getView().refresh();
    },
    remove:function(record){
        this.getStore().remove(record);
    }
});

/*********************onReady 组件渲染及处理 **********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    dictTypeGrid = new DictTypeGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
    
    dictEntryGrid = new DictEntryGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    new Ext.Viewport({
    	layout: 'border',
    	items:[
			dictTypeGrid,
			dictEntryGrid
    	]
    });
});