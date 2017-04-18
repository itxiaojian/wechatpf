var DICT_GRID_STORE_URL = '/wzy/ZyCdpt/getAllList';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.ids = this.createTextField('<font color="red">*</font>id','ids','95%','',null,100,'长度超过不能100');
    	this.ids.allowBlank = true;
    	this.ids.hidden= true;
    	this.mklx = this.createTextField('<font color="red">*</font>模块类型','mklx','95%','',null,100,'长度超过不能100');
    	this.mklx.allowBlank = true;
    	this.mklx.hidden= true;
    	
		this.cdyrl = this.createTextField('<font color="red">*</font>菜单url','cdurl','95%','',null,500,'长度超过不能100');
        this.cdyrl.allowBlank = false;
        this.cdmc = this.createTextField('<font color="red">*</font>菜单名称','cdmc','95%','',null,100,'长度超过不能100');
        this.cdmc.allowBlank = false;
        this.cdtbmc = this.createTextField('<font color="red">*</font>菜单图标名称','cdtbmc','95%','',null,100,'长度超过不能100');
        this.cdtbmc.allowBlank = false;
        this.px = this.createNumberField('<font color="red">*</font>排序','px','95%','',null,100,'长度超过不能100');
        this.px.allowBlank = false;
        this.px.emptyText="只能输入数字";
//      this.tjsj = this.createTextField('<font color="red">*</font>添加时间','tjsj','95%','',null,100,'长度超过不能100');
//      this.tjsj.allowBlank = false;
        this.sfqy = this.createCombo('<font color="red">*</font>是否启用','zdz', 'zdmc', 'sfqy', '95%',PROJECT_NAME+'/wzy/ZyCdpt/getDicByLx');
        this.sfqy.store.load();
        this.sfqy.allowBlank = false;
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
              //this.id,
                this.mklx,
            	this.cdyrl,
            	this.cdmc,
            	this.cdtbmc,
            	this.px,
              //this.tbyhm,
            	this.sfqy
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
                      {text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
//                      {text: '测试连接',iconCls: 'edit',handler:this.testConnect,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     createCombo1: function(fieldLabel,id,name,formName,anchor,url,extra1,extra2,colspan) {    //生成一个通用的ComboBox
         var combo = new Ext.form.ComboBox({
         	colspan:colspan,
         	autoLoad: true,
             fieldLabel: fieldLabel,
             emptyText: '请选择...',
             isFormField: true,
             anchor: anchor,
             mode: 'local',
             hiddenName :formName,
             name:formName,
             allowBlank: false,
             blankText:'请选择...',
             forceSelection: true,
             lastQuery: '',
             triggerAction: 'all',
             displayField:name,
             valueField:id,
             store: new Ext.data.Store({
                 proxy: new Ext.data.HttpProxy({url: url, method: 'POST'}),
                 reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:id},{name:name},{name:extra1},{name:extra2}]))
             }),
             editable : false,
             listeners : {
              	'select':function(e){  
              		var win=constructionGrid.constructionInsertWindow;
              		if(win==null){
              			win=constructionGrid.constructionUpdateWindow
              		}
              		var winForm = win.constructionForm;
              		if(e.value==1){
              			winForm.xcwxsj.hide();
              		}
              		if(e.value==2){
              			winForm.xcwxsj.show();
              		}
              	}
              }
         });
         return combo;
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/wzy/ZyCdpt/save',   
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
                 url: PROJECT_NAME+'/wzy/ZyCdpt/update',
                 method: 'POST',
                 params: {id: this.ids.value},
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
            title: "主页菜单配置",
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
        	title: "修改主页菜单配置",
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
            		{name:'id'}, {name:'cdmc'}, {name:'tjr'}, {name:'cdtbmc'}, {name:'sfqy'},
		            {name:'tjsj'}, {name:'px'}, {name:'cdurl'}, {name:'mklx'}
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
        	//title: '主页菜单配置',
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
	            {header: '菜单名称', width: 100, dataIndex: 'cdmc', sortable: true,hidden: false},
	            {header:'菜单图标名称',dataIndex:'cdtbmc',width:100,sortable:true,hidden:false},
	            {header:'菜单url',dataIndex:'cdurl',width:450,sortable:true,hidden:false},
	            /*{header:'添加人',dataIndex:'tjr',width:200,sortable:true,hidden:false},*/
	            {header:'排序',dataIndex:'px',width:50,sortable:true,hidden:false},
	            {header:'所属模块',dataIndex:'mklx',width:100,sortable:true,renderer:function(value){
                    if(value == '1') {
                        return "<span style='color:green'>微主页</span>";
                    }else if(value == '2') {
                        return "<span style='color:green'>微服务</span>";
                    }else if(value == '3') {
                        return "<span style='color:green'>微生活</span>";
                    }else if(value == '0'){ 
                    	return "<span style='color:green'>招生专栏</span>";
                    }else{
                        return value;
                    }
        		}},
	            /*{header:'添加时间',dataIndex:'tjsj',width:200,sortable:true,hidden:false},*/
	            {header:'是否启用',dataIndex:'sfqy',width:100,sortable:true,
	            	renderer:function(value){
	                    if(value == '1') {
	                        return "<span style='color:blue'>启用</span>";
	                    }else if(value == '0') {
	                        return "<span style='color:red'>不启用</span>";
	                    }else{
	                        return value;
	                    }
            		}
	            
            	}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },

    onAddClick: function() {
    	var count = this.getStore().getTotalCount();
//    	if(count>=1){
//    		Ext.Msg.alert('系统提示', BLANKSTR + '配置信息不能多于1条，请执行修改!' + BLANKSTR);
//    	}else{
    		var win = this.constructionInsertWindow;
    		win.show();
    		win.constructionForm.getForm().reset();
    		win.constructionForm.mklx.setValue(MKLX);
//    	}
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				var win = this.constructionUpdateWindow;
   		    	win.show();
   		    	var winForm = win.constructionForm;
   		    	winForm.cdyrl.setValue(vrecord.data.cdurl);
   		    	winForm.cdmc.setValue(vrecord.data.cdmc);
				winForm.px.setValue(vrecord.data.px);
				winForm.sfqy.setValue(vrecord.data.sfqy);
				winForm.cdtbmc.setValue(vrecord.data.cdtbmc);
				winForm.ids.setValue(vrecord.data.id);
				winForm.mklx.setValue(vrecord.data.mklx);
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    sheetNoChange: function(value) {
    	return '<a href="javascript:void(0)" onclick=javascript:clickSheetNo('+value+')><b><font color=red>'+ value + '</font></b></a>';
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
				       	   url:PROJECT_NAME+'/wzy/ZyCdpt/delete',   
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
//  var param={};
//  param['sclx'] = "4";
//  dictTypeGrid.store.baseParams = param;
    dictTypeGrid.store.baseParams = {mklx:MKLX};
    dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
    
    new Ext.Viewport({
    	layout: 'border',
    	items:[
			dictTypeGrid
    	]
    });

   
});