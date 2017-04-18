var DICT_GRID_STORE_URL = '/wxutil/meeting/ownerpageList';
var DICT_JG_URL = '/sys/SysArea/orgOrMebTree';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {

    constructor: function() {
    	this.id = this.createTextField('<font color="red">*</font>id','id','95%','',null,100,'长度超过不能100');
    	this.id.allowBlank = true;
    	this.id.hidden= true;
		this.hybt = this.createTextField('<font color="red">*</font> 标题','hybt','96%','',null,500,'长度超过不能100');
        this.hybt.allowBlank = false;
        
        this.hynr = new Ext.form.HtmlEditor({
            fieldLabel: '<font color="red">*</font> 会议内容',
            name: 'hynr',
            readOnly: false,
            anchor: '96%',
            height:150,
            maxLength: 500,
            maxLengthText: '最大字符数500！',
            fontFamilies:['宋体','隶书','黑体']
        });
//        this.hynr = new Ext.form.createHtmlEditor({
//            fieldLabel: '<font color="red">*</font> 会议内容',
//            name: 'hynr',
//            readOnly: false,
//            anchor: '99%',
//            height:100,
//            maxLength: 500,
//            maxLengthText: '最大字符数500！'
//        });
        this.hynr.allowBlank = false;
        
        this.kssj = new Ext.ux.DateTimeField({
        	fieldLabel:'<font color="red">*</font> 开始时间',
        	name:'kssj',
        	xtype : 'datetimefield',
        	// format : 'Y-m-d',
        	//width : 150,
        	anchor: '92%',
        	editable : false
    	});
        this.kssj.allowBlank = false;
        
        this.jhjssj = new Ext.ux.DateTimeField({
        	fieldLabel:'<font color="red">*</font> 计划结束时间',
        	name:'jhjssj',
        	xtype : 'datetimefield',
        	// format : 'Y-m-d',
        	//width : 150,
        	anchor: '92%',
        	editable : false
    	});
        this.jhjssj.allowBlank = false;
        
        //this.jg = this.createTreeCombo('<font color="red">*</font> 机构', 'treeContact', 'jg', PROJECT_NAME+""+DICT_JG_URL, '92%');
        
        var comboTree = new Ext.form.ComboBoxTree({  
            mode            : "local",  
            fieldLabel      : "机构",  
            hiddenName      : "mutliselect",  
            name            : "mutliselect",  
            url             : PROJECT_NAME+""+DICT_JG_URL,  
            //typeAhead     : true,  
            selectNodeModel : "all",  
            displayField    : "text",  
            valueField      : "id",  
            parentField     : "PID",  
            rootValue       : -1,  
            dataRoot        : "treeContact",  
            //value         : 0,  
            width           : 220,  
            resizable       : true,  
            value           : "5,584",  
            store           : new Ext.data.JsonStore({  
                autoLoad    : true,  
                url         : PROJECT_NAME+""+DICT_JG_URL, 
                root        : "treeContact",  
                fields      : [  
                    {name : "text"},  
                    {name : "id"},  
                    {name : "PID"}  
                ]  
            })  
        }) 
        
        
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
            items: [{
        		layout : 'column',
				border : false,
				items : [{
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [this.hybt]
				}, {
					columnWidth : .5,
					layout : 'form',
					border : false,
					items : [this.kssj]
				}, {
					columnWidth : .5,
					layout : 'form',
					border : false,
					items : [this.jhjssj]
				}, {
					columnWidth : .5,
					layout : 'form',
					border : false,
					items : [this.comboTree]
				}, {
					columnWidth : .5,
					layout : 'form',
					border : false,
					items : [this.comboTree]
				}, {
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [this.hynr]
				}]
			}],
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
            title: "添加会议信息",
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
            	{name:'id'}, {name:'hybt'}, {name:'hynr'}, {name:'kssj'}, {name:'jhjssj'},
		        {name:'sjjssj'}, {name:'cjr'}, {name:'hyzt'}, {name:'hyrs'}, {name:'bz'}
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
        	title: '会议管理',
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
	            {header: '会议标题', width: 300, dataIndex: 'hybt', sortable: true,hidden: false},
	            {header:'发起人',dataIndex:'cjr',width:100,sortable:true,hidden:false},
	            {header:'会议开始时间',dataIndex:'kssj',width:260,sortable:true,hidden:false},
	            {header:'计划结束时间',dataIndex:'jhjssj',width:260,sortable:true,hidden:false},
	            {header:'实际结束时间',dataIndex:'sjjssj',width:260,sortable:true,hidden:false},
	            {header:'状态',dataIndex:'hyzt',width:100,sortable:true,
	            	renderer:function(value){
	                    if(value == '1') {
	                        return "<span style='color:blue'>开始</span>";
	                    }else if(value == '0') {
	                        return "<span style='color:grey'>结束</span>";
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
				winForm.id.setValue(vrecord.data.id);
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