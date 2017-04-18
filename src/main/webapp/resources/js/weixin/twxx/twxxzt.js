var DICT_GRID_STORE_URL = '/weixin/twxx/getTwxxztList';
var DICT_ENTRY_GRID_STORE_URL = '/weixin/twxx/getTwxxnrList';
var PAGESIZE=20;
 
/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.ids = new Ext.form.NumberField({
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
    	
		this.xxbt = this.createTextField('<font color="red">*</font>主体标题','xxbt','95%','',null,100,'长度超过不能100');
        this.xxbt.allowBlank = false;
        

        this.xxsm = new Ext.form.TextArea({
            fieldLabel: '主体说明:',
            name: 'xxsm',
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
            	this.xxbt,
            	this.xxsm,
            	this.ids 
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
                 url: PROJECT_NAME+'/weixin/twxx/save',   
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
                 url: PROJECT_NAME+'/weixin/twxx/update', 
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
            title: "添加主体消息",
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
    	this.constructionForm = new DictTypeForm();
    	this.constructionForm.buttons[0].hide();   //隐藏添加按钮
    	this.constructionForm.buttons[1].show();   //显示修改按钮
    	DictTypeUpdateWindow.superclass.constructor.call(this, {
        	title: "修改主体消息",
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
    constructor: function(height, width){
    	var thiz = this;
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+DICT_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
            	{name:'id'}, {name:'xxbt'},{name:'tjsj'}, {name:'tjrbh'}, {name:'tjrxm'}, {name:'xxsm'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this}
//              ,'-',
//            	{xtype:'button',text:'同步微信',iconCls:'db-icn-upload',handler:this.onUploadWx,scope:this}
//            	,'-',
//            	{xtype:'button',text:'发送图文',iconCls:'db-icn-upload',handler:this.onSendTw,scope:this}
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '图文消息主体',
        	stripeRows: true,
            frame: true,
            height: height,
            width :500,
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
	            {header: '消息标题', width: 150, dataIndex: 'xxbt', sortable: true,hidden: false, 
                    renderer:function(value, cellmeta, record){
                    	var id = record.data.id;
                    	return '<a target="_blank" href="twxxDetail?id=' + id + '" title="点击预览图文消息">' + value + '</a>';
                    }
                },
	            {header: '添加时间',dataIndex:'tjsj',width:150,sortable:true,hidden:false},
	            {header: '消息说明',dataIndex:'xxsm',width:100,sortable:true,hidden:true},
	            {header: '添加人编号',dataIndex:'tjrbh',width:100,sortable:true,hidden:true},
	            {header: '添加人',dataIndex:'tjrxm',width:200,sortable:true,hidden:false}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            	'click':function(){
            		var records=this.getSelectionModel().getSelections();
            		if(records.length>0){
	            		var sjid = records[0].get('id');
	            		var name = records[0].get('xxbt');
	            		dictEntryGrid.setTitle('<span style="color:red">'+name+'</span> 对应的消息内容');
	            		dictEntryGrid.sjid = sjid;
	            		dictEntryGrid.store.baseParams = {
				    		limit:PAGESIZE,
				    		ztid:sjid
				    	};
				    	dictEntryGrid.store.load({params:{start:0}});
            		}
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
    		var win = this.constructionInsertWindow;
    		win.show();
    		win.constructionForm.getForm().reset();
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
   		    	winForm.bt.setValue(vrecord.data.title);
   		    	winForm.ids.setValue(vrecord.data.id);
				winForm.bz.setValue(vrecord.data.bz);
				winForm.templateId.setValue(vrecord.data.templateid);
				winForm.lj.setValue(vrecord.data.url);
				winForm.topcolor.setValue(vrecord.data.topcolor);
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
				       	   url:PROJECT_NAME+'/weixin/twxx/delete',   
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
    	this.ids = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>消息id',
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
        
        this.sjId = this.createTextField('<font color="red">*</font>上级ID:','ztid','95%','1',null,100,'长度不能超过100');
        this.sjId.hidden=true;
        
        this.sltid = this.createTextField('<font color="red">*</font>上级ID:','sltid','95%','1',null,100,'长度不能超过100');
        this.sltid.allowBlank = true;
        this.sltid.hidden=true;

		this.xxbt = this.createTextField('<font color="red">*</font>消息标题','xxbt','95%','',null,100,'长度超过不能100');
        this.xxbt.allowBlank = false;
        
//        this.xxnr = new Ext.form.TextArea({
//            fieldLabel: '消息内容:',
//            name: 'xxnr',
//            readOnly: false,
//            anchor: '95%',
//            height:280,
//            maxLength: 4000,
//            maxLengthText: '最大字符数4000！'
//        });
        
        this.xxnr = new Ext.form.HtmlEditor({
    		fieldLabel :'<font color="red">*</font> 消息体内容',
    	    anchor: '95%',
    	    height: 280,
    	    name:'xxnr',
    	    allowBlank:false
    	});
        
        this.px = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>消息排序',
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
        
//        this.bz = new Ext.form.TextArea({
//            fieldLabel: '消息:',
//            name: 'bz',
//            allowBlank: true,
//            readOnly: false,
//            anchor: '95%',
//            height:60,
//            maxLength: 256,
//            maxLengthText: '最大字符数256！'
//        });
        
		this.tp = new Ext.ux.form.FileUploadField({
			anchor:'96%',
			name: 'file',
            emptyText: '请选择...',
            labelWidth: 67,
            fieldLabel: '缩略图',
            buttonCfg: {
                text: '浏览'
            }
	    });
		this.tp.allowBlank = false;
		
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
                	this.xxbt,
                	this.tp,
                	this.px,
                	this.xxnr,
                	this.sjId,
                	this.sltid,
                	this.ids 
            	
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
    	 //设置表单enctype属性
		  $("#ext-gen15").attr("enctype","multipart/form-data");
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/weixin/twxx/saveNews', 
                 method: 'POST',
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictEntryGrid.constructionInsertWindow.hide();
                 	dictEntryGrid.store.reload();
                 	//dictEntryGrid.vbbar.doLoad(dictEntryGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                	 if(action.result=='1'){
                		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
    						dictEntryGrid.constructionInsertWindow.hide();
    	                 	dictEntryGrid.store.reload();
    					}else{
    						Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR);
    					}
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	//this.scid.setValue(dictEntryGrid.scid);
        	 $("#ext-gen17").attr("enctype","multipart/form-data");
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/weixin/twxx/updateNews', 
                 method: 'POST',
                 success: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictEntryGrid.constructionUpdateWindow.hide();
                 	dictEntryGrid.store.reload();
                 	
                 	//dictEntryGrid.vbbar.doLoad(dictEntryGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                	if(action.result=='1'){
   						Ext.Msg.alert("系统提示：","修改成功！");
   						dictEntryGrid.constructionUpdateWindow.hide();
   	                 	dictEntryGrid.store.reload();
   					}else{
   						Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
   					}
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
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
            title: "添加消息内容",
            width: 800,
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
        	title: "修改消息内容",
            width: 800,
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
	     		{name:'id'}, {name:'ztid'},{name:'xxbt'}, {name:'xxnr'}, {name:'tjsj'}, {name:'sltid'}, {name:'px'}
            ])
        });

    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
                {text:'预览',iconCls: 'edit',handler:this.onLookClick,scope:this}
            ]
        });	
        this.constructionInsertWindow = new DictEntryInsertWindow();       
        this.constructionUpdateWindow = new DictEntryUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictEntryGrid.superclass.constructor.call(this, {
        	id : 'gird_id',
        	region:'east',
        	title: '图文消息内容',
        	collapsible: true,
        	stripeRows: true,
            frame: true,
            //width:470,
            width : 600,
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
                {header:'消息标题',dataIndex:'xxbt',width:300,sortable:true,hidden:false},
  	            {header: '添加时间', width: 150, dataIndex: 'tjsj', sortable: true,hidden: false},
  	            {header:'消息内容',dataIndex:'xxnr',width:150,sortable:true,hidden:true},
  	            {header:'排序',dataIndex:'px',width:80,sortable:true,hidden:false},
  	            {header:'主体id',dataIndex:'ztid',width:100,sortable:true,hidden:true}
				
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
    		var sjId = this.sjid;
    		if(sjId != null&&sjId !=''){
		    	var win = this.constructionInsertWindow;
		    	var winForm = win.constructionForm;
		    	win.show();
		    	//winForm.thumbMediaId.setValue(mediaid);
		    	win.constructionForm.getForm().reset();
		    	winForm.sjId.setValue(this.sjid);
    		}else{
    			Ext.Msg.alert('系统提示', BLANKSTR + '请选择消息主体！' + BLANKSTR);
    		}
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.constructionUpdateWindow;
   		    	//win.constructionForm.getForm().reset();
   		    	var winForm = win.constructionForm;
   		    	win.show();
//   		    this.xxbt,
//            	this.tp,
//            	this.px,
//            	this.xxnr,
//            	this.sjId,
//            	this.ids 
   		    	winForm.xxbt.setValue(vrecord.data.xxbt);
   		    	winForm.ids.setValue(vrecord.data.id);
				winForm.xxnr.setValue(vrecord.data.xxnr);
				winForm.px.setValue(vrecord.data.px);
				winForm.tp.setValue(vrecord.data.sltid);
				winForm.sltid.setValue(vrecord.data.sltid);
				winForm.sjId.setValue(vrecord.data.ztid);
   		    	
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
				       	   url:PROJECT_NAME+'/weixin/twxx/deleteNews',
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
    onLookClick: function(){
    	var records=this.getSelectionModel().getSelections();
    	var valueStr=[];
   		if(records.length>0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				
   				
   			}else{
   				Ext.Msg.alert('系统提示', '不能预览多条记录..');
   			}
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