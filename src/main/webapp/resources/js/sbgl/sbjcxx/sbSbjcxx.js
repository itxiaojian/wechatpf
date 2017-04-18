var DICT_GRID_STORE_URL = '/sbgl/SbSbjcxx/getSbjcxxList';
var SBXX_URL = '/sb/sbSbxx/getSbxxList';
var PAGESIZE=20;


/***************************************DictTypeInsertWindow组件**************************************************/
SbSbxxOpenWindow = Ext.extend(Ext.Window,{
	sbSbxxGrid : null,
	dictTypeForm : null,
    constructor: function(grid) {
        this.sbSbxxGrid = new SbSbxxGrid();
        this.sbSbxxGrid.sbSbxxOpenWindow = this;
    	SbSbxxOpenWindow.superclass.constructor.call(this, {
            title: "申请设备",
            width: 600,
            anchor: '100%',
            autoHeight:false,
            constrainHeader:false,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.sbSbxxGrid]
        });
    }
});

/**************************DictTypeGrid*******************************************/
SbSbxxGrid = Ext.extend(UxGrid, {
	sbSbxxOpenWindow:null,
	pageSizeCombo: PAGESIZE,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    module_name:null,
    type_name:null,
    constructor: function(height, width){
    	var thiz = this;
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+SBXX_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
            		{name:'id'}, {name:'sblb'}, {name:'sbmc'}, {name:'sbbh'}, {name:'sbxh'},
		            {name:'sbjb'}, {name:'sybmbh'}, {name:'sybm'}, {name:'syfw'}, {name:'sccj'}, {name:'ccrq'},{name:'ccbh'},{name:'gmrq'},{name:'gmjg'}
		            ,{name:'jdzq'},{name:'syqx'},{name:'scjdrq'},{name:'xcjdrq'},{name:'syzt'},{name:'sbwhr'},{name:'zjff'},{name:'zcyz'},{name:'jcl'}
		            ,{name:'zjnx'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
						{xtype:'textfield',id:'cd',
							emptyText:'请输入查询关键字....', 
						   },'-',
						{xtype:'button',text:'查询',iconCls:'query',handler:function(){
							var cd = document.getElementById('cd').value;
								/*alert(cd);*/
							dictTypeGrid.constructionInsertWindow.constructionForm.sbSbxxOpenWindow.sbSbxxGrid.store.baseParams= {cd:cd};
							dictTypeGrid.constructionInsertWindow.constructionForm.sbSbxxOpenWindow.sbSbxxGrid.store.load({params:{start:0,limit:PAGESIZE}});
							//alert(cd);
						   }},'-',
						   {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
						   	document.getElementById('cd').value='';
						}
						},
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '设备信息列表',
        	stripeRows: true,
            frame: true,
            height: 300,
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
                {header:'id', width: 150, dataIndex: 'id', hidden: true},
	            {header:'设备编号', width: 100, dataIndex: 'sbbh', sortable: true,hidden: false},
	            {header:'设备名称',dataIndex:'sbmc',width:100,sortable:true,hidden:false},
	            {header:'规格型号',dataIndex:'sbxh',width:100,sortable:true,hidden:false},
	            {header:'使用部门',dataIndex:'sybm',width:100,sortable:true,hidden:false}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
            	'rowdblclick':function(){
//            		window.parent.pendPoolGrid.store.load();
            		var records=this.getSelectionModel().getSelections();
            		var sjid = records[0].get('id');
            		this.sbSbxxOpenWindow.dictTypeForm.sbbh.setValue(records[0].get('sbbh'));
            		this.sbSbxxOpenWindow.dictTypeForm.sbmc.setValue(records[0].get('sbmc'));
            		this.sbSbxxOpenWindow.hide();
            	}
            }
        });
    }
});

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
		this.sbbh = this.createTextField('<font color="red">*</font>设备编号','sbbh','95%','',null,100,'长度超过不能100');
        this.sbbh.readOnly = true;
        
        this.sbmc = this.createTextField('<font color="red">*</font>设备名称','sbmc','95%','',null,100,'长度超过不能100');
        this.sbmc.readOnly = true;
        
        /*this.jcrbh = this.createTextField('<font color="red">*</font>借出人编号','jcrbh','95%','',null,100,'长度超过不能100');
        this.jcrbh.readOnly = false;
        this.jcrbh.allowBlank = false;*/
       
        this.jcrxm =  new wxpt.ClerkSingelSelect('<font color="red">*</font>借出人名称','jcrxm','sbwhr','95%');
        this.jcrxm.allowBlank = false;
        
       /* this.jcdwbh = this.createTextField('<font color="red">*</font>借出单位编号','jcdwbh','95%','',null,100,'长度超过不能100');
        this.jcdwbh.readOnly = false;
        this.jcdwbh.allowBlank = false;*/
       
        this.jcdwxm =  new wxpt.OrgSingelSelect('<font color="red">*</font>借出单位名称','jcdwxm','sybm','95%');
        this.jcdwxm.allowBlank = false;
        
       /* this.fzrbh = this.createTextField('<font color="red">*</font>负责人编号','fzrbh','95%','',null,100,'长度超过不能100');
        this.fzrbh.readOnly = false;
        this.fzrbh.allowBlank = false;*/
        
        this.fzrxm =  new wxpt.ClerkSingelSelect('<font color="red">*</font>负责人名称','fzrxm','sbwhr','95%');
        this.fzrxm.allowBlank = false;
        
        var jcsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>上次检验日期',
			name: "jcsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        
        var ghsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>下次检验日期',
			name: "ghsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        
        
		this.jczt = this.createCombo('<span style="color:red">*</span>借出状态', 'zdz', 'zdmc', 'jczt', '95%', PROJECT_NAME+'/sbgl/SbSbjcxx/getJcztByLx');
		this.jczt.store.load();
		this.jczt.allowBlank = false;
		
		this.bz = new Ext.form.TextArea({
            fieldLabel: '备注',
            name: 'bz',
            readOnly: false,
            anchor: '95%',
            height:60,
            maxLength: 256,
            maxLengthText: '最大字符数256！'
        });
        this.bz.allowBlank = true;
		this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   
		
		var pnRow1=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.sbbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.sbmc 
                    ]  
                }),  
            ]  
        });
		var pnRow2=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jcrxm
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jcdwxm 
                    ]  
                }),  
            ]  
        });
		var pnRow3=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.fzrxm
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        jcsj
                    ]  
                }),  
            ]  
        });
		var pnRow5=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        ghsj
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jczt 
                    ]  
                }),  
            ]  
        });
		var pnRow6=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.bz
                    ]  
                }),  
            ]  
        });
        

        DictTypeForm.superclass.constructor.call(this, {
        	anchor: '100%',
        	autoHeight:true,
        	layout:"tableform",
        	layoutConfig: {columns: 1},
        	labelWidth: 100,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [
            	pnRow1,
            	pnRow2,
            	pnRow3,
            	pnRow5,
            	pnRow6,
            ],
            buttonAlign :'center',
            buttons: [
				{text: '选择设备', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
				{text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
				{text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
            ]
        });
     },
     addSbClick: function() {       //添加设备
 		var win = this.sbSbxxOpenWindow;
 		win.dictTypeForm = this;
 		win.show();
 		win.sbSbxxGrid.store.load({params:{start:0,limit:PAGESIZE}});
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'saveSbjcxx',   
                 method: 'POST',
                 params:{
                	 jcrbh:this.jcrxm.getClerkCode(),jcdwbh:this.jcdwxm.getOrgId(),fzrbh:this.fzrxm.getClerkCode()
                  },
                 success: function(form, action) {
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "申请成功!" + BLANKSTR);
                 	dictTypeGrid.constructionInsertWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	
                 	Ext.MessageBox.alert("系统提示:",BLANKSTR + "申请失败!" + BLANKSTR);
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'update', 
                 method: 'POST',
                 params:{
                   	id:record[0].get('id')
                   },
                 success: function(form, action) { 
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictTypeGrid.constructionUpdateWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
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
/***************************************CompleteForm组件**************************************************/
CompleteForm = Ext.extend(Ext.ux.Form, {
	
constructor: function() {
		
		this.sbbh = this.createTextField('设备编号','sbbh','95%','',null,100,'长度超过不能100');
        this.sbbh.readOnly = true;
        
        this.sbmc = this.createTextField('设备名称','sbmc','95%','',null,100,'长度超过不能100');
        this.sbmc.readOnly = true;
        
       /* this.jcrbh = this.createTextField('借出人编号','jcrbh','95%','',null,100,'长度超过不能100');
        this.jcrbh.readOnly = true;*/
        
        this.jcrxm = this.createTextField('借出人名称','jcrxm','95%','',null,100,'长度超过不能100');
        this.jcrxm.readOnly = true;
        
        /*this.jcdwbh = this.createTextField('借出单位编号','jcdwbh','95%','',null,100,'长度超过不能100');
        this.jcdwbh.readOnly = true;*/
        
        this.jcdwxm = this.createTextField('借出单位名称','jcdwxm','95%','',null,100,'长度超过不能100');
        this.jcdwxm.readOnly = true;
        
        /*this.fzrbh = this.createTextField('负责人编号','fzrbh','95%','',null,100,'长度超过不能100');
        this.fzrbh.readOnly = true;*/
        
        this.fzrxm = this.createTextField('负责人名称','fzrxm','95%','',null,100,'长度超过不能100');
        this.fzrxm.readOnly = true;
        
        var jcsj =  new Ext.form.DateField({
			fieldLabel: '上次检验日期',
			name: "jcsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        jcsj.readOnly = true;
        
        var ghsj =  new Ext.form.DateField({
			fieldLabel: '下次检验日期',
			name: "ghsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        ghsj.readOnly = true;
        
		this.jczt = this.createCombo('<span style="color:red">*</span>借出状态', 'zdz', 'zdmc', 'jczt', '95%', PROJECT_NAME+'/sbgl/SbSbjcxx/getJcztByLx');
		this.jczt.store.load();
		this.jczt.allowBlank = false;
		this.jczt.readOnly = false;
		
		this.bz = new Ext.form.TextArea({
            fieldLabel: '备注',
            name: 'bz',
            readOnly: false,
            anchor: '95%',
            height:60,
            maxLength: 256,
            maxLengthText: '最大字符数256！'
        });
        this.bz.allowBlank = true;
        this.bz.readOnly = true;
		this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   
		
		var pnRow1=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.sbbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.sbmc 
                    ]  
                }),  
            ]  
        });
		var pnRow2=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jcrxm
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jcdwxm 
                    ]  
                }),  
            ]  
        });
		var pnRow3=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.fzrxm
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        jcsj 
                    ]  
                }),  
            ]  
        });
		var pnRow5=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        ghsj
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                           this.jczt 
                    ]  
                }),  
            ]  
        });
		var pnRow6=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.bz
                    ]  
                }),  
            ]  
        });
        

		CompleteForm.superclass.constructor.call(this, {
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
            	pnRow1,
            	pnRow2,
            	pnRow3,
            	pnRow5,
            	pnRow6,
            ],
            buttonAlign :'center',
            buttons: [
				{text: '确定归还',iconCls: 'edit',handler:this.updateFormClick,scope:this},
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
            ]
        });
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'update', 
                 method: 'POST',
                 params:{
                  	id:record[0].get('id')
                  },
                 success: function(form, action) { 
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "归还成功!" + BLANKSTR);
                 	dictTypeGrid.dictTypeCompleteWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "归还失败!" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});
/***************************************LookForm组件**************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
	
	constructor: function() {
		
		this.sbbh = this.createTextField('<font color="red">*</font>设备编号','sbbh','95%','',null,100,'长度超过不能100');
        this.sbbh.readOnly = true;
        
        this.sbmc = this.createTextField('<font color="red">*</font>设备名称','sbmc','95%','',null,100,'长度超过不能100');
        this.sbmc.readOnly = true;
        
        this.jcrbh = this.createTextField('<font color="red">*</font>借出人编号','jcrbh','95%','',null,100,'长度超过不能100');
        this.jcrbh.readOnly = true;
        
        this.jcrxm = this.createTextField('<font color="red">*</font>借出人名称','jcrxm','95%','',null,100,'长度超过不能100');
        this.jcrxm.readOnly = true;
        
        this.jcdwbh = this.createTextField('<font color="red">*</font>借出单位编号','jcdwbh','95%','',null,100,'长度超过不能100');
        this.jcdwbh.readOnly = true;
        
        this.jcdwxm = this.createTextField('<font color="red">*</font>借出单位名称','jcdwxm','95%','',null,100,'长度超过不能100');
        this.jcdwxm.readOnly = true;
        
        this.fzrbh = this.createTextField('<font color="red">*</font>负责人编号','fzrbh','95%','',null,100,'长度超过不能100');
        this.fzrbh.readOnly = true;
        
        this.fzrxm = this.createTextField('<font color="red">*</font>负责人名称','fzrxm','95%','',null,100,'长度超过不能100');
        this.fzrxm.readOnly = true;
        
        var jcsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>上次检验日期',
			name: "jcsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        jcsj.readOnly = true;
        
        var ghsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>下次检验日期',
			name: "ghsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        ghsj.readOnly = true;
        
		this.jczt = this.createCombo('<span style="color:red">*</span>借出状态', 'zdz', 'zdmc', 'jczt', '95%', PROJECT_NAME+'/sbgl/SbSbjcxx/getJcztByLx');
		this.jczt.store.load();
		this.jczt.allowBlank = false;
		this.jczt.readOnly = true;
		
		this.bz = new Ext.form.TextArea({
            fieldLabel: '备注',
            name: 'bz',
            readOnly: false,
            anchor: '95%',
            height:60,
            maxLength: 256,
            maxLengthText: '最大字符数256！'
        });
        this.bz.allowBlank = true;
        this.bz.readOnly = true;
		this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   
		
		var pnRow1=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.sbbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.sbmc 
                    ]  
                }),  
            ]  
        });
		var pnRow2=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jcrbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jcrxm 
                    ]  
                }),  
            ]  
        });
		var pnRow3=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jcdwbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jcdwxm 
                    ]  
                }),  
            ]  
        });
		var pnRow4=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.fzrbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.fzrxm 
                    ]  
                }),  
            ]  
        });
		var pnRow5=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        jcsj
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        ghsj 
                    ]  
                }),  
            ]  
        });
		var pnRow6=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.jczt
                    ]  
                }),  
            ]  
        });
		var pnRow7=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.bz 
                    ]  
                }),  
            ]  
        });
        

		LookForm.superclass.constructor.call(this, {
        	anchor: '100%',
        	autoHeight:true,
        	layout:"tableform",
        	layoutConfig: {columns: 1},
        	labelWidth: 100,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [
            	pnRow1,
            	pnRow2,
            	pnRow3,
            	pnRow4,
            	pnRow5,
            	pnRow6,
            	pnRow7,
            ],
            buttonAlign :'center',
            buttons: [
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
            ]
        });
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
        this.constructionForm.buttons[1].show();   //隐藏添加按钮
    	this.constructionForm.buttons[2].hide();   //显示修改按钮
        DictTypeInsertWindow.superclass.constructor.call(this, {
            title: "申请设备借出信息",
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
    	this.constructionForm.buttons[1].hide();   //隐藏添加按钮
    	this.constructionForm.buttons[2].show();   //显示修改按钮
    	DictTypeUpdateWindow.superclass.constructor.call(this, {
        	title: "修改设备借出信息",
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
/***************************************DictTypeCompleteWindow组件**************************************************/
DictTypeCompleteWindow = Ext.extend(Ext.Window, {
	completeForm : null,
    constructor: function() {
    	this.completeForm = new CompleteForm();
    	DictTypeCompleteWindow.superclass.constructor.call(this, {
        	title: "确认归还设备",
            width: 600,
            autoHeight:true,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.completeForm]
        });
    }
});
/***************************************DictTypeLookWindow组件**************************************************/
DictTypeLookWindow = Ext.extend(Ext.Window, {
	lookForm : null,
    constructor: function() {
    	this.lookForm = new LookForm();
    	DictTypeLookWindow.superclass.constructor.call(this, {
        	title: "查看设备借出信息",
            width: 600,
            autoHeight:true,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.lookForm]
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
            		{name:'id'}, {name:'sbbh'}, {name:'sbmc'},{name:'jcrbh'},{name:'jcrxm'},{name:'jcdwbh'}, {name:'jcdwxm'}, {name:'fzrbh'},
		            {name:'fzrxm'}, {name:'jcsj'},{name:'ghsj'},{name:'jczt'},{name:'bz'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                   {text:'申请',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                   {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
                   {text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
               	   {xtype:'textfield',id:'jc',width: 200,
                	   emptyText:'查询名称和姓名、...',  
               	    },'-',
               	   {xtype:'label',text:'借出状态：'},{xtype:'combo',id:'jczt',
	  				   editable: false,
	  				   store: new Ext.data.Store({
	  			         proxy: new Ext.data.HttpProxy({url: 'getJcztByLx', method: 'POST'}),
	  			         reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'zdmc'},{name:'zdz'}])),
	  			         autoLoad:true,
	  			         listeners:{  
	  			             load : function(store, records, options ){      
	  			                 var data ={ "zdz": "", "zdmc": "全部"};      
	  			                 var rs = [new Ext.data.Record(data)];      
	  			                 store.insert(0,rs);  
	  			             }  
		  			         }
		  			     }),
	  			    displayField: 'zdmc', //显示文本字段
	  			    valueField: 'zdz',//value值字段id
	  			    mode: 'local',
	  			    blankText:'请选择....',  
	  			    emptyText:'请选择....', 
	  			    triggerAction: 'all',
	  			    selectOnFocus: true,
	  			    typeAhead: true
	  				},'-',
   			        {xtype:'button',text:'查询',iconCls:'query',handler:function(){
   						var jc = Ext.getCmp('jc').getValue();
   						var jczt = Ext.getCmp('jczt').getValue();
   						var params = {};
		       			params['jczt']=Ext.getCmp('jczt').getValue();
		       			dictTypeGrid.vtbar.items.each(function(item,index,length){ 
	       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
	       						if (item.getXType() == 'datefield') {
	       							params[item.getId()] = item.getValue().format(item.format);
	       						} else {
	       							params[item.getId()] = item.getValue();
	       						}
	       					}
						});
   						dictTypeGrid.store.baseParams= {jczt:jczt,jc:jc};
   						dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
   				    }},'-',
   				    {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
   	   				Ext.getCmp('jc').setValue("");
   	   				Ext.getCmp('jczt').setValue("");
      			  }
               },
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        this.dictTypeCompleteWindow = new DictTypeCompleteWindow();
        this.dictTypeLookWindow = new DictTypeLookWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '设备借出信息',
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
                {header: '操作', width: 100, dataIndex: 'sbbh', align:"center",  sortable: true,hidden: false,
					renderer:function(value, cellmeta, record){
						if(record.get("jczt") == 1){
						   return "<a href='javascript:' onclick='dictTypeGrid.onLook()'>查看</a>&nbsp;&nbsp;" +
						   		  "<a href='javascript:' onclick='dictTypeGrid.onComplete()'>归还</a>";
						}else{
						   return "<a href='javascript:;' onclick='dictTypeGrid.onLook()'>查看</a>&nbsp;&nbsp;" +
					   		      "<a href='javascript:;' onclick='' style='color:grey;'>归还</a>";
						}
            		}
				},
	            {header: '设备编号', width: 100, dataIndex: 'sbbh', align:"center", sortable: true,hidden: false},
	            {header: '设备名称', width: 100, dataIndex: 'sbmc', align:"center", sortable: true,hidden: false},
	            /*{header: '借出人编号', width: 100, dataIndex: 'jcrbh', align:"center", sortable: true,hidden: false},*/
	            {header: '借出人姓名', width: 100, dataIndex: 'jcrxm', align:"center", sortable: true,hidden: false},
	            /*{header: '借出单位编号',dataIndex:'jcdwbh',width:100, align:"center", sortable:true,hidden:false},*/
	            {header: '借出单位名称',dataIndex:'jcdwxm',width:100, align:"center", sortable:true,hidden:false},
	            /*{header: '负责人编号',dataIndex:'fzrbh',width:100, align:"center", sortable:true,hidden:false},*/
	            {header: '负责人姓名',dataIndex:'fzrxm',width:100, align:"center", sortable:true,hidden:false},
	            {header: '借出时间',dataIndex:'jcsj',width:100, align:"center", sortable:true,hidden:false},
	            {header: '归还时间',dataIndex:'ghsj',width:100, align:"center", sortable:true,hidden:false},
	            {header: '借出状态',dataIndex:'jczt',width:100, align:"center", sortable:true,hidden:false,
            		renderer:function(value){
	                    if(value == '1') {
	                        return "<span style='color:blue;'>借出</span>";
	                    }else if(value == '2') {
	                        return "<span style='color:red;'>归还</span>";
	                    }else {
	                        return value;
	                    }
            		}
	            },
	            {header: '备注',dataIndex:'bz',width:100, align:"center", sortable:true,hidden:false},
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
            }
        });
    },
    onAddClick: function() {       //添加计划
    	var count = this.getStore().getTotalCount();
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
   		  	win.constructionForm.getForm().loadRecord(vrecord);
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onLook: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				var win = this.dictTypeLookWindow;
   		    	win.show();
   		    	var winForm = win.lookForm;
   		  	win.lookForm.getForm().loadRecord(vrecord);
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onComplete: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				var win = this.dictTypeCompleteWindow;
   		    	win.show();
   		    	var winForm = win.completeForm;
   		  	win.completeForm.getForm().loadRecord(vrecord);
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
				       	   url:'delete',   
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

/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    dictTypeGrid = new DictTypeGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
    
    new Ext.Viewport({
    	layout: 'border',
    	items:[
			dictTypeGrid
    	]
    });
});