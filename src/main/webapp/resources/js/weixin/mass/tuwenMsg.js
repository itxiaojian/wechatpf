var DICT_GRID_STORE_URL = '/wxauth/materialmgr/getSuCaiPagination';
var DICT_ENTRY_GRID_STORE_URL = '/wxauth/materialmgr/getTuWenInfos';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.scId = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>素材编号',
            name: 'scId',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:true
        });
		this.scName = this.createTextField('<font color="red">*</font>图文名称','scName','95%','',null,100,'长度超过不能100');
        this.scName.allowBlank = false;
        this.remark = new Ext.form.TextArea({
            fieldLabel: '描述:',
            name: 'remark',
            readOnly: false,
            anchor: '95%',
            height:80,
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
            	this.scName,
//            	this.name,
//            	this.code,
            	this.remark,
            	this.scId 
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
                 url: PROJECT_NAME+'/wxauth/materialmgr/saveTuWenMcInfo',   
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
                 url: PROJECT_NAME+'/wxauth/materialmgr/updateTuWenMcInfo', 
                 method: 'POST',
                 params:{
                 	scid:record[0].get('scid')
                 },
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
            title: "添加图文消息",
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
        	title: "修改图文消息",
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
            		{name:'scid'}, {name:'sctype'}, {name:'scname'}, {name:'mediaid'}, {name:'createuser'},
		            {name:'createtime'}, {name:'scpath'}
            ])
        });
        this.module_name = new ST.ux.ExtField.ComboBox({
        	dictTypeCode: 'CORE.MODULE',
            fieldLabel: '图片名称',
            allowBlank: true,
            width:120,
            hiddenName :'scName',
            name:'scName',
            showAll:false
        });
		this.type_name = new Ext.form.TextField({
            fieldLabel: '图片名称',
            width:120,
            name:'name'
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
            	{xtype:'button',text:'上传微信',iconCls:'db-icn-upload',handler:this.onUploadWx,scope:this},'->',
            	{xtype:'button',text:'发送提醒',iconCls:'db-icn-upload',handler:this.onTest,scope:this},
            	//new Ext.form.Label({text:'模块名称:'}),
            	//thiz.module_name,'-',
            	new Ext.form.Label({text:'图片名称'}),
            	thiz.type_name,'-',
            	{text:'查询',iconCls: 'query',handler:this.onQueryClick,scope:this}
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '添加图文消息',
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
                {header: '素材ID', width: 150, dataIndex: 'scid', hidden: true},
	            {header: '图文名称', width: 100, dataIndex: 'scname', sortable: true,hidden: false},
	            {header:'素材类型',dataIndex:'sctype',width:100,sortable:true,hidden:true},
	            {header:'素材名称',dataIndex:'scname',width:200,sortable:true,hidden:true},
	            {header:'MEDIA_ID',dataIndex:'mediaid',width:430,sortable:true,hidden:true},
	            {header:'操作状态',dataIndex:'mediaid',width:60,sortable:true,hidden:false,
	            renderer: function(value){
		            		if( value != null ){
		            			return '<font color="Lime">已上传</font>';
		            			
		            		}else{
		            			return '<font color="red">未上传</font>';
		            		}
 						}},
	            {header:'描述信息',dataIndex:'scpath',width:280,sortable:true,hidden:false},
	            {header:'上传者',dataIndex:'createuser',width:100,sortable:true,hidden:true},
	            {header:'上传时间',dataIndex:'createtime',width:100,sortable:true,hidden:true}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            	'click':function(){
            		var records=this.getSelectionModel().getSelections();
            		var scid = records[0].get('scid');
            		var name = records[0].get('scname');
            		dictEntryGrid.setTitle(name+'对应的图文属性');
            		dictEntryGrid.scid = scid;
            		dictEntryGrid.store.baseParams = {
			    		limit:PAGESIZE,
			    		scid:scid
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
   		    	//win.constructionForm.getForm().reset();
   		    	var winForm = win.constructionForm;
   		    	win.show();
   		    	//win.constructionForm.getForm().loadRecord(vrecord);
   		    	//alert(vrecord.data.scid+';'+vrecord.data.scname+";"+vrecord.data.scpath);
   		    	winForm.scId.setValue(vrecord.data.scid);
				winForm.scName.setValue(vrecord.data.scname);
				winForm.remark.setValue(vrecord.data.scpath);
   		    	
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
	       		valueStr.push(records[i].get('scid'));
    	 	}
	    	Ext.Msg.confirm("提醒信息", "确定要删除这 " + records.length + " 条信息吗",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/wxauth/materialmgr/deleteTuWenInfo',   
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
    onTest: function() {
    	Ext.Ajax.request({
	       	   url:PROJECT_NAME+'/wxauth/materialmgr/dsfsXwtxfs',   
	       	   method : 'POST', 
               success: function(form, action) { 
	               	var obj = Ext.util.JSON.decode(form.responseText);
	               	if(obj.success == true){   //true / false
	               		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "提醒成功!" + BLANKSTR);
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
    },
    onUploadWx: function(){    //上传到微信服务器
    	var records=this.getSelectionModel().getSelections();
    	var ds = Ext.getCmp('gird_id').getStore().getCount();  //获得图文消息详细属性的条数
    	
    	//alert(ds);
    	//console.info(ds);
    	
    	if(records.length > 0){
    		
    		if(records.length == 1){
    			
    			if(ds == 0){
    				Ext.MessageBox.alert("系统提示：","请填写图文详细信息");
    			}else{
    				Ext.Msg.confirm("提醒信息", "确定要上传这 " + records.length + " 条信息吗",function(btn){
						if (btn == 'yes') {
							var valueStr = records[0].get('scid');
					       	Ext.Ajax.request({
						       	   url:PROJECT_NAME+'/wxauth/materialmgr/uploadTuWenInfo',   
						       	   method : 'POST', 
						       	   params: { ids: valueStr},
					               success: function(form, action) { 
						               var obj = Ext.util.JSON.decode(form.responseText);
						               	if(obj.success == true){   //true / false
						               		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "上传成功!" + BLANKSTR);
						               		 dictTypeGrid.store.reload();
						               	}else{
						               		 Ext.MessageBox.alert("系统提示:", obj.message);
							               	dictTypeGrid.store.reload();
						               	}
						               
						               
						               
					               },
					               failure: function(form, action) {
					            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "上传失败!" + BLANKSTR);
					               		//Ext.MessageBox.alert("系统提示：",action.result.failure);
					               }
						       	});	
						 }
					 });
    			
    			}
    			
    		}else{
    			Ext.Msg.alert('系统提示', '不能上传多条图文消息..');
    		}
    		
    	}else{
    		Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条图文消息' + BLANKSTR);
    	}
    },
    onQueryClick:function(){
    	dictTypeGrid.store.baseParams = {
    		limit:dictTypeGrid.vbbar.pageSize,
    		//module:this.module_name.getValue(),
    		scmc:this.type_name.getValue(),
    		sclx : "4"
    	};
    	dictTypeGrid.store.load({params:{start:0}});
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

/***************************************DictEntryForm组件**************************************************/

DictEntryForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
        this.mpnewsId = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>图文消息ID',
            name: 'mpnewsId',
            allowBlank: true,
            allowNegative :false,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:true
        });
        this.scId = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>素材编号',
            name: 'scId',
            allowBlank: true,
            allowNegative :false,
            readOnly: true,
            maxLength:19,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:true
        });

        this.thumbMediaId = new Ext.form.ComboBox({
        	isFormField: true,
            fieldLabel: '<font color="red">*</font>图文消息缩略图',
            emptyText: '请选择...',
            anchor: '95%',
            hiddenName: 'thumbMediaId',//取valueField值用form提交后取该id值  
            name:'thumbMediaId',
            allowBlank: false,
            blankText:'请选择...',
            forceSelection: true,
            triggerAction: 'all',
            displayField:'name',
            valueField:'thumbMediaId',
            store: new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/wxauth/materialmgr/getImageList', method: 'POST'}),
                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'thumbMediaId'},{name:'name'}]))
            }),
            editable : false
        });
        this.author = this.createTextField('作者','author','95%','',null,100,'长度超过不能100');
        this.title1 = this.createTextField('<font color="red">*</font>标题','title','95%','',null,100,'长度超过不能100');
       	this.contentSourceUrl = this.createTextField('资源url','contentSourceUrl','95%','',null,100,'长度超过不能100');
       	 this.mpContent = new Ext.form.HtmlEditor({
       		fieldLabel :'<font color="red">*</font>消息体内容',
       	    anchor: '95%',
       	    height: 120,
       	    name:'mpContent',
       	    allowBlank:false
       	});
        this.digest = this.createTextField('描述信息','digest','95%','',null,100,'长度超过不能100');
        this.showCoverPic  = this.createMemoryCombo('是否显示封面','valueField', 'displayField', '95%', SHOW_PIC_DATA, 'showCoverPic');
        this.showCoverPic.allowBlank = true;
        this.showCoverPic.store.load();
		this.author.allowBlank = true;
		this.scId.readOnly= true;
		this.title1.allowBlank = false;
		this.contentSourceUrl.allowBlank = true;
		this.mpContent.allowBlank = false;
		this.digest.allowBlank = true;	
        
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
            	
            	this.scId,
            	this.author,
            	this.title1,
            	this.contentSourceUrl,
            	this.digest,
            	this.showCoverPic,
            	this.thumbMediaId,
            	this.mpnewsId,
            	this.mpContent
            	
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
                 url: PROJECT_NAME+'/wxauth/wxsucaimpnews/saveSucaiMpnews', 
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
                 url: PROJECT_NAME+'/wxauth/wxsucaimpnews/updateSucaiMpnews', 
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
            title: "添加属性",
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
        	title: "修改属性",
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
	scid:'',
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+DICT_ENTRY_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
            		{name:'mpnewsid'},{name:'scid'},{name:'thumbmediaid'},{name:'author'},{name:'contentsourceurl'},
            		{name:'mpcontent'},{name:'digest'},{name:'showcoverpic'},{name:'title'}
            ])
        });

    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
            	{text:'<font color="red">(增删改操作后必须重新<B>上传微信</B>才生效)</font>'}
            ]
        });	
        this.constructionInsertWindow = new DictEntryInsertWindow();       
        this.constructionUpdateWindow = new DictEntryUpdateWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictEntryGrid.superclass.constructor.call(this, {
        	id : 'gird_id',
        	region:'east',
        	title: '添加图文消息属性',
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
                {header: "图文消息ID", width: 120, dataIndex: 'mpnewsid',hidden: true},
		        {header: "素材ID", width: 120, dataIndex: 'scid',hidden : true},
		        {header: "图文编号", width: 120, dataIndex: 'thumbmediaid',hidden : true},
				{header: "标题", width: 100, dataIndex:'title'},
				{header: "内容", width: 100, dataIndex: 'mpcontent', 
					renderer: function(value) {
						return '...........';
					}
				},
				{header: "描述信息", width: 100, dataIndex: 'digest'},
				{header: "作者", width: 100, dataIndex: 'author'},
				{header: "资源URL", width: 100, dataIndex: 'contentsourceurl'},
				{header: "是否显示封面", width: 100, dataIndex: 'showcoverpic'}
				
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
    	if(this.scid ==null || this.scid ==''){
    		Ext.MessageBox.alert("提示", "请选择一条图文消息！");
    		return false;
    	}
    	var win = this.constructionInsertWindow;
    	var winForm = win.constructionForm;
    	winForm.thumbMediaId.store.load();
    	win.show();
    	
    	//winForm.thumbMediaId.setValue(mediaid);
    	win.constructionForm.getForm().reset();
    	winForm.scId.setValue(this.scid );
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
   		    	
   		    	winForm.scId.setValue(vrecord.data.scid);
   		    	winForm.author.setValue(vrecord.data.author);
   		    	winForm.title1.setValue(vrecord.data.title);
   		    	winForm.contentSourceUrl.setValue(vrecord.data.contentsourceurl);
   		    	winForm.digest.setValue(vrecord.data.digest);
   		    	//winForm.showCoverPic.setValue(vrecord.data.showcoverpic);
   		    	
   		    	winForm.thumbMediaId.setValue(vrecord.data.thumbmediaid);
   		    	winForm.mpContent.setValue(vrecord.data.mpcontent);
   		    	winForm.mpnewsId.setValue(vrecord.data.mpnewsid);
   		    	
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
	       		valueStr.push(records[i].get('mpnewsid'));
    	 	}
	    	Ext.Msg.confirm("提醒信息", "确定要删除这 " + records.length + " 条信息吗",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/wxauth/wxsucaimpnews/deleteSucaiMpnews',
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

/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    dictTypeGrid = new DictTypeGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    var param={};
    param['sclx'] = "4";
    dictTypeGrid.store.baseParams = param;
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