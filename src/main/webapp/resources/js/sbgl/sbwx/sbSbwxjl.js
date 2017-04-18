var DICT_GRID_STORE_URL = '/sbgl/SbSbwxjl/getSbwxjlList';
var SBXX_URL = '/sbgl/SbSbwxjh/getSbwxjhList';
var PAGESIZE=20;


/***************************************DictTypeInsertWindow组件**************************************************/
SbSbxxOpenWindow = Ext.extend(Ext.Window,{
	sbSbxxGrid : null,
	dictTypeForm : null,
    constructor: function(grid) {
        this.sbSbxxGrid = new SbSbxxGrid();
        this.sbSbxxGrid.sbSbxxOpenWindow = this;
    	SbSbxxOpenWindow.superclass.constructor.call(this, {
            title: "选择维修计划",
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
		            ,{name:'zjnx'},{name:'wxrxm'},{name:'zt'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
						{xtype:'textfield',id:'wxjh',
							emptyText:'请输入查询关键字....', 
						   },'-',
						{xtype:'button',text:'查询',iconCls:'query',handler:function(){
							var wxjh = document.getElementById('wxjh').value;
								/*alert(cd);*/
							dictTypeGrid.constructionInsertWindow.constructionForm.sbSbxxOpenWindow.sbSbxxGrid.store.baseParams= {wxjh:wxjh};
							dictTypeGrid.constructionInsertWindow.constructionForm.sbSbxxOpenWindow.sbSbxxGrid.store.load({params:{start:0,limit:PAGESIZE}});
							//alert(cd);
						   }},'-',
						   {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
						   	document.getElementById('wxjh').value='';
						}
						   },
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '设备维修列表',
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
	            {header:'计划状态',dataIndex:'zt',width:100,sortable:true,hidden:false,
            		renderer:function(value){
	                    if(value == '1') {
	                        return "<span style='color:blue;'>有效</span>";
	                    }else if(value == '2') {
	                        return "<span style='color:red;'>已结束</span>";
	                    }
            		}
	            }
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
            	'rowdblclick':function(){
//            		window.parent.pendPoolGrid.store.load();
            		var records=this.getSelectionModel().getSelections();
            		if(records[0].get('zt')==1){
            			var sjid = records[0].get('id');
            			this.sbSbxxOpenWindow.dictTypeForm.sbbh.setValue(records[0].get('sbbh'));
            			this.sbSbxxOpenWindow.dictTypeForm.sbmc.setValue(records[0].get('sbmc'));
            			this.sbSbxxOpenWindow.dictTypeForm.sbxh.setValue(records[0].get('sbxh'));
            			this.sbSbxxOpenWindow.dictTypeForm.sybm.setValue(records[0].get('sybm'));
            			this.sbSbxxOpenWindow.hide();
            		}else{
            			Ext.MessageBox.alert("系统提示:",BLANKSTR + "请选择有效的计划!" + BLANKSTR);
            		}
            	}
            }
        });
    }
//    onRightMenuClick: function(grid, rowIndex, e) {//右键菜单
//        this.getSelectionModel().selectRow(rowIndex);
//        e.preventDefault();
//        this.rightMenu.showAt(e.getXY());
//    },
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
        
        this.wxjb = this.createCombo('<span style="color:red">*</span>维修级别', 'zdz', 'zdmc', 'wxjb', '95%', PROJECT_NAME+'/sbgl/SbSbwxjl/getWxjb');
		this.wxjb.store.load();
		this.wxjb.allowBlank = false;
		
		this.jjd = this.createCombo('<span style="color:red">*</span>紧急度', 'zdz', 'zdmc', 'jjd', '95%', PROJECT_NAME+'/sbgl/SbSbwxjl/getJjd');
		this.jjd.store.load();
		this.jjd.allowBlank = false;

        
		this.sxsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>送修日期',
			name: "sxsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
		this.sxsj.value = new Date().format('Y-m-d');
		
        
		this.wxzt = this.createCombo('<span style="color:red">*</span>维修状态', 'zdz', 'zdmc', 'wxzt', '95%', PROJECT_NAME+'/sbgl/SbSbwxjh/getYxzt');
		this.wxzt.store.load();
		this.wxzt.allowBlank = false;
		//this.wxfzr = this.createTextField('<font color="red">*</font>维修人','wxfzr','95%','',null,100,'长度超过不能100');
		this.wxfzr = new wxpt.ClerkSingelSelect('<font color="red">*</font>维修人','wxfzr','wxfzr','95%');
	    this.wxfzr.allowBlank = false;
	    //this.wxsqr = this.createTextField('<font color="red">*</font>申请人','wxsqr','95%','',null,100,'长度超过不能100');
	    this.wxsqr = new wxpt.ClerkSingelSelect('<font color="red">*</font>申请人','wxsqr','wxsqr','95%');
	    this.wxsqr.allowBlank = false;
	    //this.wxdw = this.createTextField('<font color="red">*</font>维修单位','wxdw','95%','',null,100,'长度超过不能100');
	    this.wxdw = new wxpt.OrgSingelSelect('<font color="red">*</font>维修单位','wxdw','wxdw','95%');
	    this.wxdw.allowBlank = false;
	    this.gzms = this.createTextArea('故障描述','gzms','90%','95%', null);
	    this.gzms.allowBlank = true;
	    this.gzlb = this.createCombo('<font color="red">*</font>故障类别','zdz', 'zdmc','gzlb','95%',PROJECT_NAME+'/sbgl/SbSbwxjl/getGzlb');
	    this.gzlb.store.load();
	    this.gzlb.allowBlank = false;
		this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   
        

        DictTypeForm.superclass.constructor.call(this, {
        	anchor: '100%',
        	autoHeight:true,
        	layout:"tableform",
        	layoutConfig: {columns: 2},
        	labelWidth: 90,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [
            	this.sbbh,
            	this.sbmc,
            	this.sbxh,
            	this.sybm,
            	this.wxjb,
            	this.wxfzr,
            	this.wxsqr,
            	this.wxdw,
            	this.jjd,
            	this.sxsj,
            	this.wxzt,
            	this.gzlb,
            	this.gzms
            ],
            buttonAlign :'center',
            buttons: [
				{text: '选择维修计划', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
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
        	if(this.wxdw.getOrgId()==null||this.wxdw.getOrgId()==''){
          		Ext.MessageBox.alert("系统提示:", "请选择维修单位");
          		return false;
          	}
          	if(this.wxfzr.getClerkCode()==null||this.wxfzr.getClerkCode()==''){
          		Ext.MessageBox.alert("系统提示:", "请选择维修人");
          		return false;
          	}
          	if(this.wxsqr.getClerkCode()==null||this.wxsqr.getClerkCode()==''){
          		Ext.MessageBox.alert("系统提示:", "请选择申请人");
          		return false;
          	}
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'save',   
                 method: 'POST',
                 params:{
                   	//id:record[0].get('id'),wxdh:record[0].get('wxdh')
                 	 //wxdw:this.wxdw.getOrgId(),wxfzr:this.wxfzr.getClerkCode(),wxsqr:this.wxsqr.getClerkCode()
                 },
                 success: function(form, action) {
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.constructionInsertWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:",BLANKSTR + "添加失败!" + BLANKSTR);
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	if(this.wxdw.getValue()!=record[0].get('wxdw')){
         		if(this.wxdw.getOrgId()==null||this.wxdw.getOrgId()==''){
             		 Ext.MessageBox.alert("系统提示:", "请选择维修单位");
            		 return false;
            	}
         	}
         	if(this.wxfzr.getValue()!=record[0].get('wxfzr')){
         		if(this.wxfzr.getClerkCode()==null||this.wxfzr.getClerkCode()==''){
            		 Ext.MessageBox.alert("系统提示:", "请选择维修人");
            		 return false;
            	 }
         	}
         	if(this.wxsqr.getValue()!=record[0].get('wxsqr')){
         		if(this.wxsqr.getClerkCode()==null||this.wxsqr.getClerkCode()==''){
            		 Ext.MessageBox.alert("系统提示:", "请选择申请人");
            		 return false;
            	 }
         	}
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'update', 
                 method: 'POST',
                 params:{
                 	id:record[0].get('id'),wxdh:record[0].get('wxdh')//,
                 	//wxdw:wxdw,wxfzr:wxfzr,wxsqr:wxsqr
                 },
                 success: function(form, action) { 
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictTypeGrid.constructionUpdateWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
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
        this.constructionForm.buttons[1].show();   //隐藏添加按钮
    	this.constructionForm.buttons[2].hide();   //显示修改按钮
        DictTypeInsertWindow.superclass.constructor.call(this, {
            title: "添加设备维修记录",
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
    	this.constructionForm.wxzt.readOnly = true;
    	
    	DictTypeUpdateWindow.superclass.constructor.call(this, {
        	title: "修改设备维修记录",
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

/***************************************DictTypeForm组件**************************************************/
SbwxjhForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
		this.wxzt = this.createCombo('<span style="color:red">*</span>维修状态', 'zdz', 'zdmc', 'wxzt', '95%', PROJECT_NAME+'/sbgl/SbSbwxjh/getZt');
		this.wxzt.store.load();
		this.wxzt.allowBlank = false;
		
		this.kssj = this.createDateField('开始时间','kssj','Y-m-d','95%');
		this.kssj.value = new Date().format('Y-m-d');
		this.wcsj = this.createDateField('完成时间','wcsj','Y-m-d','95%');
		this.wcsj.value = new Date().format('Y-m-d');
		
		this.wxfy = new Ext.form.NumberField({
            fieldLabel: '维修费用（元）',
            name: 'syqx',
            allowBlank: true,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.gzfxjcl = this.createTextArea('故障分析处理','gzfxjcl','100%','97.5%', null);

		var pnRow1=new Ext.Panel({
            border:false,  
            layout:'column',  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                           	this.wxzt
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                         this.wxfy
                    ]  
                }) 
            ]  
        });  
		
		var pnRow2=new Ext.Panel({
            border:false,  
            layout:'column',  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                           this.kssj
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                         this.wcsj
                    ]  
                }) 
            ]  
        });  
		
		var pnRow3=new Ext.Panel({
            border:false,  
            layout:'column',  
            items:[  
                new Ext.Panel({  
                    columnWidth:1,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                           this.gzfxjcl
                    ]  
                }) 
            ]  
        });  
		
	    SbwxjhForm.superclass.constructor.call(this, {
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
					pnRow3
            ],
            buttonAlign :'center',
            buttons: [
				{text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.updateClick, scope: this},  
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
            ]
        });
     },
     updateClick: function() {       //修改
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'updateWc', 
                 method: 'POST',
                 params:{
                 	id:record[0].get('id'),wxzt:this.wxzt.getValue(),gzfxjcl:this.gzfxjcl.getValue(),
                 	wxfy:this.wxfy.getValue(),kssj:this.kssj.getValue().format('Y-m-d'),wcsj:this.wcsj.getValue().format('Y-m-d')
                 },
                 success: function(form, action) { 
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "操作成功!" + BLANKSTR);
                 	dictTypeGrid.sbwxjhWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "操作失败!" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});
/***************************************DictTypeInsertWindow组件**************************************************/
SbwxjhWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new SbwxjhForm();
        //this.constructionForm.buttons[1].show();   //添加按钮
    	//this.constructionForm.buttons[2].show();   //关闭按钮
    	SbwxjhWindow.superclass.constructor.call(this, {
            title: "维修记录完成",
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
/***************************************DictTypeForm组件**************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
	
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
        
        this.wxjb = this.createCombo('<span style="color:red">*</span>维修级别', 'zdz', 'zdmc', 'wxjb', '95%', PROJECT_NAME+'/sbgl/SbSbwxjl/getWxjb');
		this.wxjb.store.load();
		this.wxjb.allowBlank = false;
		this.wxjb.readOnly = true;
		
		this.jjd = this.createCombo('<span style="color:red">*</span>紧急度', 'zdz', 'zdmc', 'jjd', '95%', PROJECT_NAME+'/sbgl/SbSbwxjl/getJjd');
		this.jjd.store.load();
		this.jjd.allowBlank = false;
		this.jjd.readOnly = true;
		
		this.wxdh = this.createTextField('<font color="red">*</font>维修单号','wxdh','95%','',null,100,'长度超过不能100');
	    this.wxdh.allowBlank = false;
	    this.wxdh.readOnly = true;

        
		this.sxsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>送修日期',
			name: "sxsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
		this.sxsj.value = new Date().format('Y-m-d');
		this.sxsj.readOnly = true;
		
        
		this.wxzt = this.createCombo('<span style="color:red">*</span>维修状态', 'zdz', 'zdmc', 'wxzt', '95%', PROJECT_NAME+'/sbgl/SbSbwxjh/getZt');
		this.wxzt.store.load();
		this.wxzt.allowBlank = false;
		this.wxzt.readOnly = true;
		
		this.wxfzr = this.createTextField('<font color="red">*</font>维修人','wxfzr','95%','',null,100,'长度超过不能100');
	    this.wxfzr.allowBlank = false;
	    this.wxfzr.readOnly = true;
	    
	    this.wxsqr = this.createTextField('<font color="red">*</font>申请人','wxsqr','95%','',null,100,'长度超过不能100');
	    this.wxsqr.allowBlank = false;
	    this.wxsqr.readOnly = true;
	    
	    this.wxdw = this.createTextField('<font color="red">*</font>维修单位','wxdw','95%','',null,100,'长度超过不能100');
	    this.wxdw.allowBlank = false;
	    this.wxdw.readOnly = true;
	    
	    this.gzms = this.createTextArea('<span style="color:red">*</span>故障描述','gzms','90%','95%', null);
	    this.gzms.allowBlank = false;
	    this.gzms.readOnly = true;
	    
	    this.gzlb = this.createCombo('<font color="red">*</font>故障类别','zdz', 'zdmc','gzlb','95%',PROJECT_NAME+'/sbgl/SbSbwxjl/getGzlb');
	    this.gzlb.store.load();
	    this.gzlb.allowBlank = false;
	    this.gzlb.readOnly = true;
	    
	    this.kssj = this.createDateField('开始时间','kssj','Y-m-d','95%');
		this.kssj.value = new Date().format('Y-m-d');
		this.kssj.allowBlank = true;
		this.kssj.readOnly = true;
		
		this.wcsj = this.createDateField('完成时间','wcsj','Y-m-d','95%');
		this.wcsj.value = new Date().format('Y-m-d');
		this.wcsj.allowBlank = true;
		this.wcsj.readOnly = true;
		
		this.wxfy = new Ext.form.NumberField({
            fieldLabel: '维修费用',
            name: 'wxfy',
            allowBlank: true,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.wxfy.readOnly = true;
		this.gzfxjcl = this.createTextArea('<span style="color:red">*</span>故障分析处理','gzfxjcl','100%','95%', null);
		this.gzfxjcl.readOnly = true;
//		this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   
        

		LookForm.superclass.constructor.call(this, {
        	anchor: '100%',
        	autoHeight:true,
        	layout:"tableform",
        	layoutConfig: {columns: 2},
        	labelWidth: 90,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [
            	this.sbbh,
            	this.sbmc,
            	this.sbxh,
            	this.sybm,
            	this.wxdh,
            	this.wxjb,
            	this.wxfzr,
            	this.wxsqr,
            	this.wxdw,
            	this.jjd,
            	this.sxsj,
            	this.wxzt,
            	this.gzlb,
            	this.gzms,
            	this.kssj,
            	this.wcsj,
            	this.wxfy,
            	this.gzfxjcl
            ],
            buttonAlign :'center',
            buttons: [
//				{text: '选择设备', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
//				{text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
//				{text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
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
              		var win=dictTypeGrid.constructionInsertWindow;
              		if(win==null){
              			win=dictTypeGrid.constructionUpdateWindow
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
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});
/***************************************DictTypeLookWindow组件**************************************************/
DictTypeLookWindow = Ext.extend(Ext.Window, {
	lookForm : null,
	constructor: function() {
		this.lookForm = new LookForm();
		DictTypeLookWindow.superclass.constructor.call(this, {
			title: "查看设备维修记录",
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
            		{name:'id'}, {name:'sbbh'}, {name:'sbmc'}, {name:'sbxh'},{name:'sybm'},{name:'jjd'}, {name:'wxdh'},
		            {name:'wxsqr'}, {name:'gzms'}, {name:'sxsj'}, {name:'wxdw'}, {name:'wxfzr'}, {name:'wxzt'},{name:'wxjb'},
		            {name:'gzlb'}, {name:'lb'}, {name:'kssj'}, {name:'wcsj'}, {name:'wxfy'}, {name:'gzfxjcl'},
		            {name:'jb'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                   {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                   {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
                   {text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
//                   {text:'计划完成',iconCls: 'edit',handler:this.onWcClick,scope:this},'-',
               	   {xtype:'textfield',id:'code',
   				    emptyText:'请输入设备编号或名称....', 
               	    },'-',
               	   {xtype:'label',text:'维修级别：'},{xtype:'combo',id:'wxjb',
	  				   editable: false,
	  				   store: new Ext.data.Store({
	  			         proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/sbgl/SbSbwxjl/getWxjb', method: 'POST'}),
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
	  				{xtype:'label',text:'设备类别：'},{xtype:'combo',id:'sblb',
		  				   editable: false,
		  				   store: new Ext.data.Store({
		  			         proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/sb/sbSbxx/getDicByLx', method: 'POST'}),
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
   						var code = Ext.getCmp('code').getValue();
   						var wxjb = Ext.getCmp('wxjb').getValue();
   						var sblb = Ext.getCmp('sblb').getValue();
   						var params = {};
		       			params['wxjb']=Ext.getCmp('wxjb').getValue();
		       			params['sblb']=Ext.getCmp('sblb').getValue();
		       			dictTypeGrid.vtbar.items.each(function(item,index,length){ 
	       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
	       						if (item.getXType() == 'datefield') {
	       							params[item.getId()] = item.getValue().format(item.format);
	       						} else {
	       							params[item.getId()] = item.getValue();
	       						}
	       					}
						});
   						dictTypeGrid.store.baseParams= {code:code,wxjb:wxjb,sblb:sblb};
   						dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
   				    }},'-',
   				    {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
   	   				Ext.getCmp('code').setValue("");
   	   				Ext.getCmp('wxjb').setValue("");
   	   				Ext.getCmp('sblb').setValue("");
      			  }
               },
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        this.sbwxjhWindow = new SbwxjhWindow();
        this.dictTypeLookWindow = new DictTypeLookWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '设备维修记录',
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
                {header: '操作', width: 100, dataIndex: '', align:"center",  sortable: true,hidden: false,
	                renderer:function(value, cellmeta, record){
						if(record.get("wxzt") == 1){
	        			   return "<a href='javascript:;' onclick='dictTypeGrid.onLook()'>查看</a>&nbsp;&nbsp;&nbsp;" +
	        			   		"<a href='javascript:;' onclick='dictTypeGrid.onWcClick()'>完成</a>";
						}else{
						   return "<a href='javascript:;' onclick='dictTypeGrid.onLook()'>查看</a>&nbsp;&nbsp;&nbsp;" +
	        			   		"<a href='javascript:;' onclick='' style='color:grey;'>完成</a>";
						}
	        		}
				},
	            {header: '设备编号', width: 100, dataIndex: 'sbbh', sortable: true,hidden: false},
	            {header: '设备名称', width: 100, dataIndex: 'sbmc', sortable: true,hidden: false},
	            {header: '设备型号', width: 100, dataIndex: 'sbxh', sortable: true,hidden: false},
	            {header: '使用部门', width: 100, dataIndex: 'sybm', sortable: true,hidden: false},
	            {header: '维修单号', width: 100, dataIndex: 'wxdh', sortable: true,hidden: false},
	            {header: '维修级别', width: 100, dataIndex: 'jb', sortable: true,hidden: false},
	            {header: '紧急度', width: 100, dataIndex: 'jjd', sortable: true,hidden: false,
	            	renderer:function(value){
	                    if(value == '1') {
	                        return "<span style='color:red;'>紧急</span>"
	                    }else if(value == '2') {
	                        return "<span style='color:blue;'>重大</span>";
	                    }else{
	                        return "<span style='color:green;'>一般</span>";
	                    }
            		}
	            },
	            {header: '维修申请人', width: 100, dataIndex: 'wxsqr', sortable: true,hidden: false},
	            {header: '故障描述', width: 100, dataIndex: 'gzms', sortable: true,hidden: false},
	            {header: '送修时间', width: 100, dataIndex: 'sxsj', sortable: true,hidden: false},
	            {header: '维修单位', width: 100, dataIndex: 'wxdw', sortable: true,hidden: false},
	            {header: '维修人', width: 100, dataIndex: 'wxfzr', sortable: true,hidden: false},
	            {header: '维修状态',dataIndex:'wxzt',width:100,sortable:true,hidden:false,
            		renderer:function(value){
	                    if(value == '1') {
	                        return "<span style='color:blue;'>进行中</span>";
	                    }else if(value == '2') {
	                        return "<span style='color:red;'>已结束</span>";
	                    }
            		}
	            },
	            {header: '故障类别', width: 100, dataIndex: 'lb', sortable: true,hidden: false},
	            {header: '开始时间', width: 100, dataIndex: 'kssj', sortable: true,hidden: false},
	            {header: '完成时间', width: 100, dataIndex: 'wcsj', sortable: true,hidden: false},
	            {header: '维修费用', width: 100, dataIndex: 'wxfy', sortable: true,hidden: false},
	            {header: '分析处理',dataIndex:'gzfxjcl',width:100,sortable:true,hidden:false}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    onAddClick: function() {       //添加计划
    	var count = this.getStore().getTotalCount();
    		var win = this.constructionInsertWindow;
    		win.show();
    		win.constructionForm.getForm().reset();
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
   				Ext.Msg.alert('系统提示', '不能查看多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onWcClick: function() {       //完成计划
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
		    	var count = this.getStore().getTotalCount();
		    	var win = this.sbwxjhWindow;
		    	win.show();
		    	win.constructionForm.getForm().reset();
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	if(vrecord.data.wxzt=='1'){
   		    		var win = this.constructionUpdateWindow;
   	   		    	win.show();
   	   		    	var winForm = win.constructionForm;
   	   		    	win.constructionForm.getForm().loadRecord(vrecord);
   	   		    	if(vrecord.data.wxzt=='1'){
   	   	   		  		winForm.wxzt.setValue("有效");
   	   	   		  	}else if(vrecord.data.wxzt=='2'){
   	   	   		  		winForm.wxzt.setValue("已结束");
   	   	   		  	}
   				}else{
   					Ext.Msg.alert('系统提示', '不能修改已结束的记录..');
   				}
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