var DICT_GRID_STORE_URL = '/wxutil/yhfz/ownerpageList';
var DICT_JG_URL = '/sys/SysArea/orgOrMebTree';
var DICT_YHJG_URL = '/sys/SysArea/getOrgOrMebTree';
var MENBER_GRID_STORE_URL = '/wxutil/yhfz/menberList';
var PAGESIZE=15;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {

    constructor: function() {
    	this.ids = this.createTextField('<font color="red">*</font>id','id','95%','',null,100,'长度超过不能100');
    	this.ids.allowBlank = true;
    	this.ids.hidden= true;
    	
		this.fzmc = this.createTextField('<font color="red">*</font> 分组名称','fzmc','96%','',null,500,'长度超过不能100');
        this.fzmc.allowBlank = false;
		
		this.users = new Ext.form.ComboBoxTree({
		    mode            : "local",  
		    fieldLabel      : '<font color="red">*</font> 选择机构',  
		    hiddenName      : "users",  
		    name            : "users",  
		    url             : PROJECT_NAME+""+DICT_YHJG_URL,  
		    //typeAhead     : true,  
		    anchor: '96%',
		    selectNodeModel : "all",
		    displayField    : "text",
		    valueField      : "value",
		    parentField     : "pid",
		    rootValue       : -1,
		    dataRoot        : "datasource",  
		    //value         : 0,  
		    //width         : 220,  
		    resizable       : true,  
			onCheckChange 	: function(node,checked) {
				var isRoot = (node == this.tree.getRootNode());
				var selModel = this.selectNodeModel;
				var isLeaf = node.isLeaf();
				if (isRoot && selModel != 'all') {
					return;
				} else if (selModel == 'folder' && isLeaf) {
					return;
				} else if (selModel == 'leaf' && !isLeaf) {
					return;
				}
				var r = this.findRecord(this.valueField, node.attributes[this.valueField]);
				if(r){
					r["data"][this.checkField] = checked;
				}
				//alert("1+:"+this.getCheckedDisplay()+";2+:"+this.getCheckedDisplay1());
				this.setRawValue(this.getCheckedDisplay());
				this.setValue(this.getCheckedDisplay1());
				var jgId = this.getCheckedDisplay1();  
                toUser.store.baseParams = {
			    		jgId:jgId
			    	};
                toUser.store.load();
			},
		    store           : new Ext.data.JsonStore({
		        autoLoad    : true,  
		        url         : PROJECT_NAME+""+DICT_YHJG_URL,
		        root        : "datasource",  
		        fields      : [  
		            {name : "text"},  
		            {name : "value"},  
		            {name : "pid"}  
		        ]  
		    })
		    
		}) ;
		
		var toUser = new Ext.ux.form.LovCombo({
			//id:'LUser',
        	colspan:'',
        	autoLoad: true,
            fieldLabel: '<font color="red">*</font> 选择用户',
            emptyText: '请选择...',
            isFormField: true,
            anchor: '96%',
            mode: 'local',
            hiddenName :'toUser',
            name:'toUsers',
            allowBlank: false,
            blankText:'请选择...',
            forceSelection: true,
            lastQuery: '',
            triggerAction: 'all',
            showSelectAll   : true,
            displayField:'text',
            valueField:'id',
			store: new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/sys/SysUser/getUserByJgId', method: 'POST'}),
                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'id'},{name:'text'},{name:''},{name:''}]))
			}) 
        });
		toUser.allowBlank = false;
		
        this.fzsm = new Ext.form.HtmlEditor({
            fieldLabel: '分组说明',
            name: 'bz',
            readOnly: false,
            anchor: '96%',
            height:150,
            maxLength: 500,
            maxLengthText: '最大字符数500！',
            fontFamilies:['宋体','隶书','黑体']
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
            items: [{
        		layout : 'column',
				border : false,
				items : [{
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [this.hybt]
				}, {
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [this.users]
				}, {
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [toUser]
				}, {
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [this.fzsm]
				},{
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [this.ids]
				}]
			}],
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
                 url: PROJECT_NAME+'/wxutil/yhfz/save',   
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
                 url: PROJECT_NAME+'/wxutil/meeting/update', 
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
            title: "添加分组信息",
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
        	title: "修改分组信息",
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

/*****************************MeetMenberWindow***************
 *description   : 查看会议签到信息窗口
*************************************************************/
MeetMenberWindow = Ext.extend(Ext.Window, {
    constructor: function() {
    	this.meetMenberGrid = new MeetMenberGrid(); 	
    	MeetMenberWindow.superclass.constructor.call(this, {
    		title:'查看分组人员信息',
    		width: 500,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.meetMenberGrid],
        	buttonAlign:'center'
//        		,
//	        buttons:[
//			    {text:'关闭',width: 20,iconCls: 'delete', handler:this.closeClick, scope:this}
//	        ]	
    	});
    },
    closeClick: function() {
    	this.hide();
    }
    
});

/*****************************MeetMenberGrid*****************
 *description   : 会议签到信息查看
*************************************************************/
MeetMenberGrid = Ext.extend(UxGrid, {
    store:null,
    constructor: function(height, width){

    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+MENBER_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
            	{name:'id'}, {name:'xm'},{name:'fzid'}, {name:'rybh'}, {name:'bmbh'}, {name:'bmmc'}
            ])
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        MeetMenberGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: true,
            height: 385,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          //sm,
                {header:'id',dataIndex:'id',width:180,sortable: true,hidden:true},
                {header:'部门名称',dataIndex:'bmmc',width:140,sortable: true},
                {header:'人员姓名',dataIndex:'xm',width:180,sortable: true}
            ]),
            bbar: this.vbbar,
            ds: this.store
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
            	{name:'id'}, {name:'fzmc'},{name:'bz'}, {name:'tjsj'},{name:'cjr'}
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
	            {header: '分组名称', width: 300, dataIndex: 'fzmc', sortable: true,hidden: false},
	            {header:'发起人',dataIndex:'cjr',width:100,sortable:true,hidden:true},
	            {header:'添加时间',dataIndex:'tjsj',width:200,sortable:true,hidden:false},
	            {header:'分组说明',dataIndex:'bz',width:500,sortable:true,hidden:false},
            	{header:'操作',dataIndex:'bz',width:120,sortable:true,
            		renderer:function(value){
            			return '<a class="redlink" title="点击查看分组人员信息" href="javascript:void(0)" onclick="dictTypeGrid.onViewClick();">查看分组人员</a>';
            		}
            	}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    onViewClick: function() {
    	var records=this.getSelectionModel().getSelections();
    	if(records.length > 0) {
    		var fzid = records[0].data.id;
    		meetMenberWindow.show();
    		meetMenberWindow.meetMenberGrid.store.baseParams = {
    	    		fzid:fzid,limit:PAGESIZE
    	    };
    		meetMenberWindow.meetMenberGrid.store.load({params:{start:0}});
    	}
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
   		    	winForm.hybt.setValue(vrecord.data.hybt);
   		    	winForm.kssj.setValue(vrecord.data.kssj);
				winForm.jhjssj.setValue(vrecord.data.jhjssj);
				winForm.hynr.setValue(vrecord.data.hynr);
				winForm.ids.setValue(vrecord.data.id);
				
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
	    	Ext.Msg.confirm("提醒信息", "确定要删除这 " + records.length + " 条会议吗",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/wxutil/yhfz/delete',   
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
    meetMenberWindow = new MeetMenberWindow();
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