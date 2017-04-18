var DICT_GRID_STORE_URL = '/sys/SysUser/getList';
var DICT_JG_URL = '/sys/SysArea/orgOrMebTree';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.yhbh = this.createTextField('<font color="red">*</font>用户编号:','yhbh','95%','',null,100,'长度超过不能100');
    	this.yhbh.allowBlank = true;
    	this.yhbh.hidden= true;
    	
		this.yhzh = this.createTextField('<font color="red">*</font>用户帐号:','username','95%','',null,100,'长度超过不能100');
        this.yhzh.allowBlank = false;
        this.yhzh.readOnly = false;
        
        this.xm = this.createTextField('<font color="red">*</font>用户姓名:','xm','95%','',null,100,'长度超过不能100');
        this.xm.allowBlank = false;
        
        this.yx = this.createTextField('个人邮箱:','yx','95%','',null,100,'长度超过不能100');
        this.yx.allowBlank = true;
        
        this.sjh = this.createTextField('手机号码:','sjh','95%','',null,100,'长度超过不能100');
        this.sjh.allowBlank = true;
        
    	var bmbh1 = this.createTextField('<font color="red">*</font>部门编号:','bmbh','95%','',null,100,'长度超过不能100');
    	bmbh1.allowBlank = false;
    	bmbh1.hidden= true;
    	
        this.jgmc =  new Ext.ux.TreeCombo({
    		fieldLabel:'<font color="red">*</font> 选择机构:',
    		rootVisible: false,
    		url: PROJECT_NAME+""+DICT_JG_URL,
    		allowBlank: false,
    		hiddenName: 'jg',
    		name:'jgmc',
    		rootName: 'jgmc',
    		anchor: '95%',
            displayField:'jg',
            valueField:id,
            listeners : {
            	'select':function(combo){  
                	var jgId = combo.getValue();  
                	bmbh1.setValue(jgId);
            	} 
            }
		});
        
        this.bmbh = bmbh1;
        
        this.yhzt = this.createCombo('<font color="red">*</font>用户状态:','zdz', 'zdmc', 'yhzt', '95%',PROJECT_NAME+'/sys/SysUser/getDicByLx');
        this.yhzt.store.load();
        this.yhzt.allowBlank = false;
        
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
                this.yhbh,
            	this.yhzh,
            	this.xm,
            	this.yx,
            	this.sjh,
            	this.jgmc,
            	this.yhzt,
            	this.bmbh
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
                      {text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/SysUser/saveUser',   
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
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});

/***************************************DictTypeForm组件**************************************************/
UpdateForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.yhbh = this.createTextField('<font color="red">*</font>用户编号:','yhbh','95%','',null,100,'长度超过不能100');
    	this.yhbh.allowBlank = true;
    	this.yhbh.hidden= true;
    	
		this.yhzh = this.createTextField('<font color="red">*</font>用户帐号:','username','95%','',null,100,'长度超过不能100');
        this.yhzh.allowBlank = false;
        this.yhzh.readOnly = true;
        this.yhzh.style = 'background:#E6E6E6';
        
        this.xm = this.createTextField('<font color="red">*</font>用户姓名:','xm','95%','',null,100,'长度超过不能100');
        this.xm.allowBlank = false;
        
        this.yx = this.createTextField('个人邮箱:','yx','95%','',null,100,'长度超过不能100');
        this.yx.allowBlank = true;
        
        this.sjh = this.createTextField('手机号码:','sjh','95%','',null,100,'长度超过不能100');
        this.sjh.allowBlank = true;
        
    	var bmbh1 = this.createTextField('<font color="red">*</font>部门编号:','bmbh','95%','',null,100,'长度超过不能100');
    	bmbh1.allowBlank = false;
    	bmbh1.hidden= true;
    	
        this.jgmc =  new Ext.ux.TreeCombo({
    		fieldLabel:'<font color="red">*</font> 选择机构:',
    		rootVisible: false,
    		url: PROJECT_NAME+""+DICT_JG_URL,
    		allowBlank: false,
    		hiddenName: 'jg',
    		name:'jgmc',
    		rootName: 'jgmc',
    		anchor: '95%',
            displayField:'jg',
            valueField:id,
            listeners : {
            	'select':function(combo){  
                	var jgId = combo.getValue();  
                	bmbh1.setValue(jgId);
            	} 
            }
		});
        
        this.bmbh = bmbh1;
        
        this.yhzt = this.createCombo('<font color="red">*</font>用户状态:','zdz', 'zdmc', 'yhzt', '95%',PROJECT_NAME+'/sys/SysUser/getDicByLx');
        this.yhzt.store.load();
        this.yhzt.allowBlank = false;
        
        UpdateForm.superclass.constructor.call(this, {
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
                this.yhbh,
            	this.yhzh,
            	this.xm,
            	this.yx,
            	this.sjh,
            	this.jgmc,
            	this.yhzt,
            	this.bmbh
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/SysUser/updateUser', 
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
            title: "添加系统用户",
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

/***************************************DictTypeUpdateWindow组件**************************************************/
DictTypeUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new UpdateForm();
    	DictTypeUpdateWindow.superclass.constructor.call(this, {
        	title: "修改AD服务器配置",
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
/**************************DictTypeGrid*******************************************/
DictTypeGrid = Ext.extend(UxGrid, {
	pageSizeCombo: PAGESIZE,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    module_name:null,
    type_name:null,
    closable:true, 
    closeAction:'close', 
    constructor: function(height, width){
    	var thiz = this;
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+DICT_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
            		{name:'dlm'}, {name:'yhbh'}, {name:'xm'}, {name:'bmmc'},{name:'bmbh'}, {name:'jsmc'},
		            {name:'sjh'}, {name:'yhzt'},{name:'yx'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
                {text:'重置密码',iconCls: 'edit',handler:this.onResetClick,scope:this},'-',
                {text:'增量同步',iconCls: 'edit',handler:this.onZltbClick,scope:this},'-',
                {text:'迎新新生同步',iconCls: 'edit',handler:this.onXstbClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'->',
            	{xtype:'label',text:'用户姓名:'},'-',
				{xtype:'textfield',id:'yhxm_query'},'-',
				{xtype:'label',text:'用户帐号:'},'-',
				{xtype:'textfield',id:'yhzh_query'},'-',
            	{text:'查询',iconCls: 'query',handler:this.onQueryClick,scope:this}
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '系统用户',
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
                {header:'用户编号', width: 150, dataIndex: 'yhbh', hidden: true},
	            {header:'用户帐号', width: 150, dataIndex: 'dlm', sortable: true,hidden: false},
	            {header:'用户姓名',dataIndex:'xm',width:100,sortable:true,hidden:false},
	            {header:'部门名称',dataIndex:'bmmc',width:200,sortable:true,hidden:false},
	            {header:'角色名称',dataIndex:'jsmc',width:200,sortable:true,hidden:false},
	            {header:'手机号',dataIndex:'sjh',width:150,sortable:true,hidden:false},
	            {header:'个人邮箱',dataIndex:'yx',width:200,sortable:true,hidden:false},
	            {header:'用户状态',dataIndex:'yhzt',width:80,sortable:true,
            		renderer:function(value, cellmeta, record){
            			if(value == "1") {
            				return '<span style="color:blue;">启用</span>';
            			}else {
            				return '<span style="color:red;">禁用</span>';
            			}
            		}
	            },
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    onQueryClick: function() {
		var yhxm = Ext.getCmp("yhxm_query").getValue();
		var yhzh = Ext.getCmp("yhzh_query").getValue();
		dictTypeGrid.store.baseParams = {yhxm:yhxm, yhzh:yhzh};
		dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
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
   		    	winForm.yhbh.setValue(vrecord.data.yhbh);
   		    	winForm.yhzh.setValue(vrecord.data.dlm);
   		    	winForm.yhzh.style = 'background:#E6E6E6';
				winForm.xm.setValue(vrecord.data.xm);
				winForm.sjh.setValue(vrecord.data.sjh);
				winForm.jgmc.setValue(vrecord.data.bmmc);
				winForm.yx.setValue(vrecord.data.yx);
				winForm.yhzt.setValue(vrecord.data.yhzt);
				winForm.bmbh.setValue(vrecord.data.bmbh);
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
	       		valueStr.push(records[i].get('yhbh'));
    	 	}
	    	Ext.Msg.confirm("提醒信息", "确定要删除这 " + records.length + " 条信息吗",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/sys/SysUser/deleteUser',   
				       	   method : 'POST', 
				       	   params: { yhbhs: valueStr},
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
    onResetClick: function() {
    	var records=this.getSelectionModel().getSelections();
    	var valueStr=[];
   		if(records.length>0) {
	       	for(var i=0;i<records.length;i++){
	       		valueStr.push(records[i].get('yhbh'));
    	 	}
	    	Ext.Msg.confirm("提醒信息", "确定要重置这 " + records.length + " 条用户密码吗？",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/sys/SysUser/resetUser',   
				       	   method : 'POST', 
				       	   params: { yhbhs: valueStr},
			               success: function(form, action) {
				               	var obj = Ext.util.JSON.decode(form.responseText);
				               	if(obj.success == true){   //true / false
				               		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "重置成功!" + BLANKSTR);
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
    onZltbClick: function() {
	    	Ext.Msg.confirm("提醒信息", "确定要同步用户信息吗？",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/tjsj/tb/sjztb',   
				       	   method : 'POST', 
				       	   params: { type: 1},
			               success: function(form, action) {
				               	var obj = Ext.util.JSON.decode(form.responseText);
				               	if(obj.success == true){   //true / false
				               		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "同步成功!" + BLANKSTR);
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
    },
    onXstbClick: function() {
    	Ext.Msg.confirm("提醒信息", "确定要同步用户信息吗？",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url:PROJECT_NAME+'/tjsj/tb/xssjztb',  
			       	   method : 'POST', 
			       	   params: { type: 1},
		               success: function(form, action) {
			               	var obj = Ext.util.JSON.decode(form.responseText);
			               	if(obj.success == true){   //true / false
			               		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "同步成功!" + BLANKSTR);
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