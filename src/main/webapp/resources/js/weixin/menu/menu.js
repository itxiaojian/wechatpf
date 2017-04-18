var DICT_GRID_STORE_URL = '/wxauth/menu/getFirstMenu';
var DICT_ENTRY_GRID_STORE_URL = '/wxauth/menu/getSonMenu';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.cdid = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>菜单id',
            name: 'id',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:true
        });
    	
		this.cdMc = this.createTextField('<font color="red">*</font>菜单名称','mc','95%','',null,100,'长度超过不能100');
        this.cdMc.allowBlank = false;
        
        
        this.lj = new Ext.form.TextArea({
            fieldLabel: 'url:',
            name: 'lj',
            readOnly: false,
            anchor: '95%',
            height:100,
            maxLength: 500,
            maxLengthText: '最大字符数500！'
        });
        
        
        this.dqjb = this.createTextField('<font color="red">*</font>菜单级别','dqjb','95%','1',null,100,'长度超过不能100');
        this.dqjb.setValue(1);
        this.dqjb.hidden=true;
        
        this.px = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>排序',
            name: 'px',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        
        this.bz = new Ext.form.TextArea({
            fieldLabel: '备注:',
            name: 'bz',
            readOnly: false,
            anchor: '95%',
            height:60,
            maxLength: 256,
            maxLengthText: '最大字符数256！'
        });
        
		this.lx = this.createCombo('<span style="color:red">*</span>菜单类型:', 'zdz', 'zdmc', 'lx', '95%', PROJECT_NAME+'/wxauth/menu/getDicByLx');
//		this.lx.params.zdzl="cdlx";
		this.lx.store.load();
		this.lx.allowBlank = false;
        

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
            	this.cdMc,
            	this.lx,
            	this.px,
            	this.lj,
            	this.bz,
            	this.dqjb,
            	this.cdid 
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
                 url: PROJECT_NAME+'/wxauth/menu/save',   
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
                 url: PROJECT_NAME+'/wxauth/menu/update', 
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
            width: 700,
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
        	title: "修改一级菜单消息",
            width: 700,
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
            		{name:'id'}, {name:'mc'}, {name:'lx'}, {name:'sjid'}, {name:'dqjb'},
		            {name:'lj'}, {name:'cjsj'}, {name:'px'}, {name:'zt'}, {name:'bz'}, {name:'kz'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
            	{xtype:'button',text:'同步微信',iconCls:'db-icn-upload',handler:this.onUploadWx,scope:this}
//            	,'-',
//            	{xtype:'button',text:'发送图文',iconCls:'db-icn-upload',handler:this.onSendTw,scope:this}
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '添加微信一级菜单',
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
	            {header: '菜单名称', width: 100, dataIndex: 'mc', sortable: true,hidden: false},
	            {header:'菜单类型',dataIndex:'lx',width:100,sortable:true,hidden:false},
	            {header:'链接',dataIndex:'lj',width:350,sortable:true,hidden:false},
	            {header:'创建时间',dataIndex:'cjsj',width:100,sortable:true,hidden:true}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            	'click':function(){
            		var records=this.getSelectionModel().getSelections();
            		var sjid = records[0].get('id');
            		var name = records[0].get('mc');
            		var cdlx = records[0].get('lx');
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
        });
    },
//    onRightMenuClick: function(grid, rowIndex, e) {//右键菜单
//        this.getSelectionModel().selectRow(rowIndex);
//        e.preventDefault();
//        this.rightMenu.showAt(e.getXY());
//    },
    onAddClick: function() {
    	var count = this.getStore().getTotalCount();
//    	Ext.Msg.alert('系统提示', BLANKSTR + '一级菜单不能多于3条' + BLANKSTR);
    	if(count>=3){
    		Ext.Msg.alert('系统提示', BLANKSTR + '一级菜单不能多于3条' + BLANKSTR);
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
//   		    	var win = new DictTypeUpdateWindow();
   				var win = this.constructionUpdateWindow;
   		    	//win.constructionForm.getForm().reset();
   		    	win.show();
   		    	var winForm = win.constructionForm;
   		    	winForm.cdMc.setValue(vrecord.data.mc);
   		    	winForm.cdid.setValue(vrecord.data.id);
				winForm.bz.setValue(vrecord.data.bz);
				winForm.px.setValue(vrecord.data.px);
				winForm.lx.setValue(vrecord.data.lx);
				winForm.lj.setValue(vrecord.data.lj);
   		    	//win.constructionForm.getForm().loadRecord(vrecord);
   		    	//alert(vrecord.data.scid+';'+vrecord.data.scname+";"+vrecord.data.scpath);

//				winForm.mc.setValue(vrecord.data.mc);

   		    	
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
				       	   url:PROJECT_NAME+'/wxauth/menu/delete',   
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
			            	   //Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
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
    onUploadWx : function(){    //上传到微信服务器
		Ext.Msg.confirm("提醒信息", "确定要同步菜单到微信",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url:PROJECT_NAME+'/wxauth/menu/refreshWxZdycd',   
			       	   method : 'POST', 
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
		            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "同步失败!" + BLANKSTR);
		               		//Ext.MessageBox.alert("系统提示：",action.result.failure);
		               }
			       	});	
			 }
		 });
    },
    onSendTw : function(){    //上传到微信服务器
		Ext.Msg.confirm("提醒信息", "确定要发送图文信息",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url:PROJECT_NAME+'/wxauth/materialmgr/dsfsXwtxfs',   
			       	   method : 'POST', 
		               success: function(form, action) { 
			               var obj = Ext.util.JSON.decode(form.responseText);
			               	if(obj.success == true){   //true / false
			               		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "发送成功!" + BLANKSTR);
			               		 dictTypeGrid.store.reload();
			               	}else{
			               		 Ext.MessageBox.alert("系统提示:", obj.message);
				               	dictTypeGrid.store.reload();
			               	}
		               },
		               failure: function(form, action) {
		            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "发送失败!" + BLANKSTR);
		               		//Ext.MessageBox.alert("系统提示：",action.result.failure);
		               }
			       	});	
			 }
		 });
    },
//    onQueryClick:function(){
//    	dictTypeGrid.store.baseParams = {
//    		limit:dictTypeGrid.vbbar.pageSize,
//    		//module:this.module_name.getValue(),
//    		scmc:this.type_name.getValue(),
//    		sclx : "4"
//    	};
//    	dictTypeGrid.store.load({params:{start:0}});
//    },
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

/***************************************DictEntryForm组件**************************************************/

DictEntryForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.cdid = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>菜单id',
            name: 'id',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:true
        });
    	
		this.cdMc = this.createTextField('<font color="red">*</font>菜单名称','mc','95%','',null,100,'长度超过不能100');
        this.cdMc.allowBlank = false;
        
        
        this.lj = new Ext.form.TextArea({
            fieldLabel: 'url:',
            name: 'lj',
            readOnly: false,
            anchor: '95%',
            height:100,
            maxLength: 500,
            maxLengthText: '最大字符数500！'
        });
        
        
        this.dqjb = this.createTextField('<font color="red">*</font>菜单级别','dqjb','95%','1',null,100,'长度超过不能100');
        this.dqjb.setValue("2");
        this.dqjb.hidden=true;
        
        this.sjId = this.createTextField('<font color="red">*</font>上级ID:','sjid','95%','1',null,100,'长度超过不能100');
        this.sjId.hidden=true;
        
        this.px = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>排序',
            name: 'px',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        
        this.bz = new Ext.form.TextArea({
            fieldLabel: '备注:',
            name: 'bz',
            readOnly: false,
            anchor: '95%',
            height:60,
            maxLength: 256,
            maxLengthText: '最大字符数256！'
        });
        
		this.lx = this.createCombo('<span style="color:red">*</span>菜单类型:', 'zdz', 'zdmc', 'lx', '95%', PROJECT_NAME+'/wxauth/menu/getDicByLx');
//		this.lx.params.zdzl="cdlx";
		this.lx.store.load();
		this.lx.allowBlank = false;
		
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
                	this.cdMc,
                	this.lx,
                	this.px,
                	this.lj,
                	this.bz,
                	this.dqjb,
                	this.sjId,
                	this.cdid 
            	
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
                 url: PROJECT_NAME+'/wxauth/menu/save', 
                 method: 'POST',
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictEntryGrid.constructionInsertWindow.hide();
                 	dictEntryGrid.store.reload();
                 	//dictEntryGrid.vbbar.doLoad(dictEntryGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR);
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	//this.scid.setValue(dictEntryGrid.scid);
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/wxauth/menu/update', 
                 method: 'POST',
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictEntryGrid.constructionUpdateWindow.hide();
                 	dictEntryGrid.store.reload();
                 	
                 	//dictEntryGrid.vbbar.doLoad(dictEntryGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
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
            width: 700,
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
            width: 700,
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
	     		{name:'id'}, {name:'mc'}, {name:'lx'}, {name:'sjid'}, {name:'dqjb'},
	            {name:'lj'}, {name:'cjsj'}, {name:'px'}, {name:'zt'}, {name:'bz'}, {name:'kz'}
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
            width : 560,
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
                {header: 'id', width: 150, dataIndex: 'id', hidden: true},
  	            {header: '菜单名称', width: 100, dataIndex: 'mc', sortable: true,hidden: false},
  	            {header:'菜单类型',dataIndex:'lx',width:100,sortable:true,hidden:false},
  	            {header:'链接',dataIndex:'lj',width:350,sortable:true,hidden:false},
  	            {header:'上级id',dataIndex:'sjid',width:100,sortable:true,hidden:true},
  	            {header:'创建时间',dataIndex:'cjsj',width:100,sortable:true,hidden:true},
  	            {header:'当前级别',dataIndex:'dqjb',width:100,sortable:true,hidden:true}
				
            ]),
            tbar: this.vtbar,
            //bbar: this.vbbar,
            ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            }
        });
    },
//    onRightMenuClick: function(grid, rowIndex, e) {//右键菜单
//        this.getSelectionModel().selectRow(rowIndex);
//        e.preventDefault();
//        this.rightMenu.showAt(e.getXY());
//    },
    onAddClick: function() {
    	if(this.cdlx != 'parent'){
    		Ext.MessageBox.alert("提示", "上级菜单不允许二级菜单！");
    		return false;    		
    	}
    	if(this.sjid ==null || this.sjid ==''){
    		Ext.MessageBox.alert("提示", "请选择一级菜单！");
    		return false;
    	}
    	var count = this.getStore().getTotalCount();
//    	Ext.Msg.alert('系统提示', BLANKSTR + '一级菜单不能多于3条' + BLANKSTR);
    	if(count>=5){
    		Ext.Msg.alert('系统提示', BLANKSTR + '一级菜单不能多于5条' + BLANKSTR);
    	}else{
	    	var win = this.constructionInsertWindow;
	    	var winForm = win.constructionForm;
	    	win.show();
	    	
	    	//winForm.thumbMediaId.setValue(mediaid);
	    	win.constructionForm.getForm().reset();
	    	winForm.sjId.setValue(this.sjid);
    	}
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
   		    	//win.constructionForm.getForm().loadRecord(vrecord);
   		    	
   		    	winForm.cdMc.setValue(vrecord.data.mc);
   		    	winForm.cdid.setValue(vrecord.data.id);
				winForm.bz.setValue(vrecord.data.bz);
				winForm.px.setValue(vrecord.data.px);
				winForm.lx.setValue(vrecord.data.lx);
				winForm.sjId.setValue(vrecord.data.sjid);
				winForm.lj.setValue(vrecord.data.lj);
				winForm.dqjb.setValue(vrecord.data.dqjb);
   		    	
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
				       	   url:PROJECT_NAME+'/wxauth/menu/delete',
				       	   method : 'POST', 
				       	   params: { ids: valueStr},
			               success: function(form, action) { 
				               Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除成功!" + BLANKSTR);
				               dictEntryGrid.store.reload();
			               },
			               failure: function(form, action) {
			            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
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
    
    dictEntryGrid = new DictEntryGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    //dictEntryGrid.store.load({params:{start:0,limit:PAGESIZE}});
    new Ext.Viewport({
    	layout: 'border',
    	items:[
			dictTypeGrid,
			dictEntryGrid
    	]
    });
//    dictTypeGrid.constructionInsertWindow.show();
//    dictTypeGrid.constructionInsertWindow.hide();
   
});