var DICT_GRID_STORE_URL = '/sys/sysGjzgl/getSysGjzglList';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.id = this.createTextField('<font color="red">*</font>id','id','95%','',null,100,'长度超过不能100');
    	this.id.allowBlank = true;
    	this.id.hidden= true;
		this.gjz = this.createTextField('<font color="red">*</font>关键字','gjz','95%','',null,100,'长度超过不能100');
        this.gjz.allowBlank = false;
 //       this.zt = this.createTextField('<font color="red">*</font>状态','zt','95%','',null,100,'长度超过不能100');
  //      this.zt.allowBlank = false;
        this.zt = this.createCombo('<font color="red">*</font>状态','zdz', 'zdmc', 'zt', '95%',PROJECT_NAME+'/sys/sysGjzgl/getDicByLx');
        this.zt.store.load();
		this.zt.allowBlank = false;
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
            	this.gjz,
            	this.zt
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
                 url: PROJECT_NAME+'/sys/sysGjzgl/save',   
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
                 url: PROJECT_NAME+'/sys/sysGjzgl/update', 
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
            title: "关键字管理",
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
        	title: "修改关键字",
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
            		{name:'id'}, {name:'gjz'}, {name:'zt'}
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
        	title: '添加关键字',
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
	            {header: '关键字', width: 200, dataIndex: 'gjz', sortable: true,hidden: false},
                {header:'状态',dataIndex:'zt',width:100,sortable:true,
            	renderer:function(value){
                    if(value == '1') {
                        return "<span style='color:blue'>屏蔽</span>";
                    }else if(value == '0') {
                        return "<span style='color:red'>不屏蔽</span>";
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
   		    	winForm.id.setValue(vrecord.data.id);
   		    	winForm.gjz.setValue(vrecord.data.gjz);
   		    	winForm.zt.setValue(vrecord.data.zt);
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
				       	   url:PROJECT_NAME+'/sys/sysGjzgl/delete',   
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