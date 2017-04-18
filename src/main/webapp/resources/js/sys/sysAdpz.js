var DICT_GRID_STORE_URL = '/sys/sysAdpz/getList';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.id = this.createTextField('<font color="red">*</font>id','id','95%','',null,100,'长度超过不能100');
    	this.id.allowBlank = true;
    	this.id.hidden= true;
		this.fwqdz = this.createTextField('<font color="red">*</font>服务器地址','fwqdz','95%','',null,100,'长度超过不能100');
        this.fwqdz.allowBlank = false;
        this.dkh = this.createTextField('<font color="red">*</font>端口号','dkh','95%','',null,100,'长度超过不能100');
        this.dkh.allowBlank = false;
        this.dlqz = this.createTextField('<font color="red">*</font>ad域','dlqz','95%','',null,100,'长度超过不能100');
        this.dlqz.allowBlank = false;
        this.yjd = this.createTextField('<font color="red">*</font>域节点','yjd','95%','',null,100,'长度超过不能100');
        this.yjd.allowBlank = false;
        this.tbyhm = this.createTextField('<font color="red">*</font>默认登录账户','tbyhm','95%','',null,100,'长度超过不能100');
        this.tbyhm.allowBlank = false;
        this.tbmm = this.createTextField('<font color="red">*</font>默认账户密码','tbmm','95%','',null,100,'长度超过不能100');
        this.tbmm.allowBlank = false;
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
                this.id,
            	this.fwqdz,
            	this.dkh,
            	this.dlqz,
            	this.yjd,
            	this.tbyhm,
            	this.tbmm
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
                      {text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
                      {text: '测试连接',iconCls: 'edit',handler:this.testConnect,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/sysAdpz/save',   
                 method: 'POST',
                 success: function(form, action) {
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.constructionInsertWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
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
                 url: PROJECT_NAME+'/sys/sysAdpz/update', 
                 method: 'POST',
                 success: function(form, action) { 
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictTypeGrid.constructionUpdateWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", action.result.message);
                 }
         	});
         }
     },
     testConnect: function() {       //测试连接
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/sysAdpz/testConnect', 
                 method: 'POST',
                 success: function(form, action) { 
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "连接成功!" + BLANKSTR);
//                 	dictTypeGrid.constructionUpdateWindow.hide();
//                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
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


/***************************************DictTypeInsertWindow组件**************************************************/
DictTypeInsertWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new DictTypeForm();
        this.constructionForm.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //显示修改按钮
        DictTypeInsertWindow.superclass.constructor.call(this, {
            title: "添加AD服务器配置",
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
        	title: "修改AD服务器配置",
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
            		{name:'id'}, {name:'fwqdz'}, {name:'dkh'}, {name:'tbyhm'}, {name:'tbmm'},
		            {name:'xgsj'}, {name:'xgr'},{name:'dlqz'}, {name:'yjd'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this}
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '添加AD服务器配置',
        	stripeRows: true,
            frame: true,
            height: height,
            width :width,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header: 'id', width: 150, dataIndex: 'id', hidden: true},
	            {header: '服务器地址', width: 200, dataIndex: 'fwqdz', sortable: true,hidden: false},
	            {header:'端口号',dataIndex:'dkh',width:100,sortable:true,hidden:false},
	            {header:'默认服务器账户',dataIndex:'tbyhm',width:200,sortable:true,hidden:false},
	            {header:'域名',dataIndex:'dlqz',width:200,sortable:true,hidden:false},
	            {header:'域节点',dataIndex:'yjd',width:200,sortable:true,hidden:false},
	            {header:'默认服务器密码',dataIndex:'tbmm',width:200,sortable:true,hidden:true}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },

    onAddClick: function() {
    	var count = this.getStore().getTotalCount();
    	if(count>=1){
    		Ext.Msg.alert('系统提示', BLANKSTR + '配置信息不能多于1条，请执行修改!' + BLANKSTR);
    	}else{
    		var win = this.constructionInsertWindow;
    		win.show();
    		win.constructionForm.getForm().reset();
    	}
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				var win = this.constructionUpdateWindow;
   		    	win.show();
   		    	var winForm = win.constructionForm;
   		    	winForm.fwqdz.setValue(vrecord.data.fwqdz);
   		    	winForm.dkh.setValue(vrecord.data.dkh);
				winForm.tbyhm.setValue(vrecord.data.tbyhm);
				winForm.tbmm.setValue("");
				winForm.dlqz.setValue(vrecord.data.dlqz);
				winForm.yjd.setValue(vrecord.data.yjd);
				winForm.id.setValue(vrecord.data.id);
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
	       		valueStr.push(records[i].get('id'));
    	 	}
	    	Ext.Msg.confirm("提醒信息", "确定要删除这 " + records.length + " 条信息吗",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/sys/sysAdpz/delete',   
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
//    var param={};
//    param['sclx'] = "4";
//    dictTypeGrid.store.baseParams = param;
    dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
    
    new Ext.Viewport({
    	layout: 'border',
    	items:[
			dictTypeGrid
    	]
    });
//    dictTypeGrid.constructionInsertWindow.show();
//    dictTypeGrid.constructionInsertWindow.hide();
   
});