var DICT_GRID_STORE_URL = '/sbgl/SbSbjyjl/getSbjyjlList';
var SBXX_URL = '/sbgl/SbSbjyjh/getSbjyjhList';
var PAGESIZE=20;

/***************************************DictTypeInsertWindow组件**************************************************/
SbSbxxOpenWindow = Ext.extend(Ext.Window,{
	sbSbxxGrid : null,
	dictTypeForm : null,
    constructor: function(grid) {
        this.sbSbxxGrid = new SbSbxxGrid();
        this.sbSbxxGrid.sbSbxxOpenWindow = this;
    	SbSbxxOpenWindow.superclass.constructor.call(this, {
            title: "选择检验计划",
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
            		{name:'id'}, {name:'sbbh'}, {name:'sbmc'},{name:'sbxh'},{name:'sybm'},{name:'jyfs'}, {name:'scwxsj'}, {name:'jg'},
		            {name:'xcwxsj'}, {name:'wxrbh'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
						{xtype:'textfield',id:'jh',
							emptyText:'请输入查询关键字....', 
						   },'-',
						{xtype:'button',text:'查询',iconCls:'query',handler:function(){
							var jh = document.getElementById('jh').value;
								/*alert(cd);*/
							dictTypeGrid.constructionInsertWindow.constructionForm.sbSbxxOpenWindow.sbSbxxGrid.store.baseParams= {jh:jh};
							dictTypeGrid.constructionInsertWindow.constructionForm.sbSbxxOpenWindow.sbSbxxGrid.store.load({params:{start:0,limit:PAGESIZE}});
							//alert(cd);
						   }},'-',
						   {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
						   	document.getElementById('jh').value='';
						}
						   		},
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '检验计划列表',
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
	            {header:'使用部门',dataIndex:'sybm',width:100,sortable:true,hidden:false},
	            {header:'检验方式',dataIndex:'jyfs',width:100,sortable:true,hidden:false,
	            	renderer:function(value){
						if(value == '1') {
							return "<span style='color:blue;'>内检</span>"
							}else if(value == '2') {
								return "<span style='color:red;'>外检</span>";
								}else{
									return value;
									}
							}
				},
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
            	'rowdblclick':function(){
            		var records=this.getSelectionModel().getSelections();
            		var sjid = records[0].get('id');
            		this.sbSbxxOpenWindow.dictTypeForm.sbbh.setValue(records[0].get('sbbh'));
            		this.sbSbxxOpenWindow.dictTypeForm.sbmc.setValue(records[0].get('sbmc'));
            		this.sbSbxxOpenWindow.dictTypeForm.sbxh.setValue(records[0].get('sbxh'));
            		this.sbSbxxOpenWindow.dictTypeForm.sybm.setValue(records[0].get('sybm'));
            		this.sbSbxxOpenWindow.dictTypeForm.jyfs.setValue(records[0].get('jyfs'));
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
        this.sbbh.allowBlank = false;
        this.sbbh.readOnly = true;
        
        this.sbmc = this.createTextField('<font color="red">*</font>设备名称','sbmc','95%','',null,100,'长度超过不能100');
        this.sbmc.allowBlank = false;
        this.sbmc.readOnly = true;
        
        this.sbxh = this.createTextField('<font color="red">*</font>设备型号','sbxh','95%','',null,100,'长度超过不能100');
        this.sbxh.allowBlank = false;
        this.sbxh.readOnly = true;
        
        this.sybm = this.createTextField('<font color="red">*</font>使用部门','sybm','95%','',null,100,'长度超过不能100');
        this.sybm.allowBlank = false;
        this.sybm.readOnly = true;
        
        this.jyfs = this.createCombo('<span style="color:red">*</span>检验方式', 'zdz', 'zdmc', 'jyfs', '95%', PROJECT_NAME+'/sbgl/SbSbjyjh/getJyfsByLx');
		this.jyfs.store.load();
		this.jyfs.allowBlank = false;
        
        var sjsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>送检时间',
			name: "sjsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        
        this.sjr = new wxpt.ClerkSingelSelect('<font color="red">*</font>送检人','sjr','sbwhr','95%');
        
        this.jydw = new wxpt.OrgSingelSelect('<font color="red">*</font>检验单位','jydw','sybm','95%');
        
        this.lxdh = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>联系电话',
            name: 'lxdh',
            allowBlank: true,
            allowNegative :false,
            maxLength:11,
            maxLengthText:'长度超过不能11位', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        this.lxdh.allowBlank = false;
        
		this.jyzt = this.createCombo('<span style="color:red">*</span>检验状态', 'zdz', 'zdmc', 'jyzt', '95%', PROJECT_NAME+'/sbgl/SbSbjyjl/getJyztByLx');
		this.jyzt.store.load();
		this.jyzt.allowBlank = false;
		
		this.jyfy = new Ext.form.NumberField({
            fieldLabel: '检验费用',
            name: 'jyfy',
            allowBlank: true,
            allowNegative :false,
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        this.jyfy.allowBlank = true;
        
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sbbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sbxh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sybm 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyfs
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        sjsj 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sjr
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jydw 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.lxdh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyzt 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyfy
                    ]  
                }) 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.bz 
                    ]  
                })  
            ]  
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
				{text: '选择检验计划', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
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
                 url: 'saveSbjyjl',   
                 method: 'POST',
                 success: function(form, action) {
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.constructionInsertWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	
                 	Ext.MessageBox.alert("系统提示:",BLANKSTR + "添加失败!" + BLANKSTR);
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
        
        this.sbxh = this.createTextField('设备型号','sbxh','95%','',null,100,'长度超过不能100');
        this.sbxh.readOnly = true;
        
        this.sybm = this.createTextField('使用部门','sybm','95%','',null,100,'长度超过不能100');
        this.sybm.readOnly = true;
        
        this.jyfs = this.createCombo('检验方式', 'zdz', 'zdmc', 'jyfs', '95%', PROJECT_NAME+'/sbgl/SbSbjyjh/getJyfsByLx');
		this.jyfs.store.load();
		this.jyfs.allowBlank = false;
		this.jyfs.readOnly = true;
        
        var sjsj =  new Ext.form.DateField({
			fieldLabel: '送检时间',
			name: "sjsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        sjsj.readOnly = true;
        
        this.sjr = new wxpt.ClerkSingelSelect('送检人','sjr','sbwhr','95%');
        this.sjr.readOnly = true;
        
        this.jydw = new wxpt.OrgSingelSelect('检验单位','jydw','sybm','95%');
        this.jydw.readOnly = true;
        
        this.lxdh = new Ext.form.NumberField({
            fieldLabel: '联系电话',
            name: 'lxdh',
            allowBlank: true,
            allowNegative :false,
            maxLength:11,
            maxLengthText:'长度超过不能11位', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        this.lxdh.allowBlank = false;
        this.lxdh.readOnly = true;
        
		this.jyzt = this.createCombo('<span style="color:red">*</span>检验状态', 'zdz', 'zdmc', 'jyzt', '95%', PROJECT_NAME+'/sbgl/SbSbjyjl/getJyztByLx');
		this.jyzt.store.load();
		this.jyzt.allowBlank = false;
		
		this.jyfy = new Ext.form.NumberField({
            fieldLabel: '检验费用',
            name: 'jyfy',
            allowBlank: true,
            allowNegative :false,
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        this.jyfy.allowBlank = true;
        
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
        
        var pnRow1=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sbbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sbxh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sybm 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyfs
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        sjsj 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sjr
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jydw 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.lxdh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyzt 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyfy
                    ]  
                }) 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.bz 
                    ]  
                })  
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
					pnRow4,
					pnRow5,
					pnRow6,
					pnRow7,
            ],
            buttonAlign :'center',
            buttons: [
				{text: '确定完成',iconCls: 'edit',handler:this.updateFormClick,scope:this},
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
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictTypeGrid.dictTypeCompleteWindow.hide();
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
/***************************************LookForm组件**************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.sbbh = this.createTextField('<font color="red">*</font>设备编号','sbbh','95%','',null,100,'长度超过不能100');
        this.sbbh.readOnly = true;
        
        this.sbmc = this.createTextField('<font color="red">*</font>设备名称','sbmc','95%','',null,100,'长度超过不能100');
        this.sbmc.readOnly = true;
        
        this.sbxh = this.createTextField('<font color="red">*</font>设备型号','sbxh','95%','',null,100,'长度超过不能100');
        this.sbxh.readOnly = true;
        
        this.sybm = this.createTextField('<font color="red">*</font>使用部门','sybm','95%','',null,100,'长度超过不能100');
        this.sybm.readOnly = true;
        
        this.jyfs = this.createCombo('<span style="color:red">*</span>检验方式', 'zdz', 'zdmc', 'jyfs', '95%', PROJECT_NAME+'/sbgl/SbSbjyjh/getJyfsByLx');
        this.jyfs.store.load();
		this.jyfs.readOnly = true;
        
        var sjsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>送检时间',
			name: "sjsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        sjsj.readOnly = true;
        
        this.sjr = this.createTextField('<font color="red">*</font>送检人','sjr','95%','',null,100,'长度超过不能100');
        this.sjr.readOnly = true;
        
        this.jydw = this.createTextField('<font color="red">*</font>检验单位','jydw','95%','',null,100,'长度超过不能100');
        this.jydw.readOnly = true;
        
        this.lxdh = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>联系电话',
            name: 'lxdh',
            allowBlank: true,
            allowNegative :false,
            maxLength:11,
            maxLengthText:'长度超过不能11位', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        this.lxdh.readOnly = true;
        
		this.jyzt = this.createCombo('<span style="color:red">*</span>检验状态', 'zdz', 'zdmc', 'jyzt', '95%', PROJECT_NAME+'/sbgl/SbSbjyjl/getJyztByLx');
		this.jyzt.store.load();
		this.jyzt.readOnly = true;
		
		this.jyfy = new Ext.form.NumberField({
            fieldLabel: '检验费用',
            name: 'jyfy',
            allowBlank: true,
            allowNegative :false,
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        this.jyfy.allowBlank = true;
        
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
        
        var pnRow1=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sbbh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sbxh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sybm 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyfs
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        sjsj 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sjr
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jydw 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.lxdh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyzt 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyfy
                    ]  
                }) 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.bz 
                    ]  
                })  
            ]  
        });

        LookForm.superclass.constructor.call(this, {
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
            title: "添加设备检验记录",
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
        	title: "修改设备检验记录",
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
        	title: "确认完成设备检测",
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
        	title: "查看设备检测记录",
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
            		{name:'id'}, {name:'sbbh'}, {name:'sbmc'},{name:'sbxh'},{name:'sybm'},{name:'jyfs'}, {name:'sjsj'}, {name:'sjr'},
		            {name:'jydw'}, {name:'lxdh'},{name:'jyzt'},{name:'jyfy'},{name:'bz'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                   {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                   {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
                   {text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
               	   {xtype:'textfield',id:'jl',
                	   emptyText:'查询设备的编号、名称、型号...', 
               	    },'-',
               	   {xtype:'label',text:'检验状态：'},{xtype:'combo',id:'jyzt',
	  				   editable: false,
	  				   store: new Ext.data.Store({
	  			         proxy: new Ext.data.HttpProxy({url: 'getJyztByLx', method: 'POST'}),
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
   						var jl = Ext.getCmp('jl').getValue();
   						var jyzt = Ext.getCmp('jyzt').getValue();
   						var params = {};
		       			params['jyzt']=Ext.getCmp('jyzt').getValue();
		       			dictTypeGrid.vtbar.items.each(function(item,index,length){ 
	       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
	       						if (item.getXType() == 'datefield') {
	       							params[item.getId()] = item.getValue().format(item.format);
	       						} else {
	       							params[item.getId()] = item.getValue();
	       						}
	       					}
						});
   						dictTypeGrid.store.baseParams= {jl:jl,jyzt:jyzt};
   						dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
   				    }},'-',
   				    {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
   	   				Ext.getCmp('jl').setValue("");
   	   				Ext.getCmp('jyzt').setValue("");
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
        	title: '设备检验记录',
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
						//alert(record.get("jyzt"));
						if(record.get("jyzt") == 1){
            			   return "<a href='javascript:' onclick='dictTypeGrid.onLook()'>查看</a>&nbsp;&nbsp;&nbsp;" +
            			   		  "<a href='javascript:' onclick='dictTypeGrid.onComplete()'>完成</a>";
						}else{
							return "<a href='javascript:;' onclick='dictTypeGrid.onLook()'>查看</a>&nbsp;&nbsp;&nbsp;" +
      			   		           "<a href='javascript:;' onclick='' style='color:grey;'>完成</a>";
						}
            		}
				},
				{header: '设备编号', width: 100, dataIndex: 'sbbh', align:"center", sortable: true,hidden: false},
				{header: '设备名称', width: 100, dataIndex: 'sbmc', align:"center", sortable: true,hidden: false},
				{header: '设备型号', width: 100, dataIndex: 'sbxh', align:"center", sortable: true,hidden: false},
				{header: '使用部门', width: 100, dataIndex: 'sybm', align:"center", sortable: true,hidden: false},
				{header:'检验方式',dataIndex:'jyfs',width:100, align:"center", sortable:true,hidden:false,
					renderer:function(value){
						if(value == '1') {
							return "<span style='color:blue;'>内检</span>"
							}else if(value == '2') {
								return "<span style='color:red;'>外检</span>";
								}else{
									return value;
									}
							}
				},
				{header:'送检时间',dataIndex:'sjsj',width:100, align:"center", sortable:true,hidden:false},
				{header:'送检人',dataIndex:'sjr',width:100, align:"center", sortable:true,hidden:false},
				{header:'检验单位',dataIndex:'jydw',width:100, align:"center", sortable:true,hidden:false},
				{header:'联系电话',dataIndex:'lxdh',width:100, align:"center", sortable:true,hidden:false},
				{header:'检验状态',dataIndex:'jyzt',width:100, align:"center", sortable:true,hidden:false,
					renderer:function(value){
						if(value == '1') {
							return "<span style='color:blue;'>在检</span>"
							}else if(value == '2') {
								return "<span style='color:red;'>已结束</span>";
								}else{
									return value;
									}
						}
				},
				{header:'检验费用',dataIndex:'jyfy',width:100, align:"center", sortable:true,hidden:false},
				{header:'备注',dataIndex:'bz',width:100, align:"center", sortable:true,hidden:false}
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
    onZheJiuClick: function() {
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