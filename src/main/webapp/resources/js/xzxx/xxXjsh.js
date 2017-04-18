var USER_GRID_STORE_URL = 'getXjshList';
var PAGESIZE=20;
var yearll=new Ext.form.TextField();
var amtd = new Ext.form.TextField();

//添加页面
var ACT_DEAL_WINDOW;
/*************************************** ConstructionForm组件 **************************************************/
ConstructionForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.xjbt = this.createTextField('<font color="red">*</font>信件标题', 'xjbt', '95%','',null);
    	
    	this.fjrxm = this.createTextField('发件人', 'fjrxm', '90%','',null,100,'长度超过不能100');
    	
    	this.lxdh = new Ext.form.TextField({
            fieldLabel: '联系电话',
            name: 'lxdh',
            allowNegative :false,
           maxLength:11,
           maxLengthText:'长度超过不能11位', 
            anchor: '90%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        
    	this.txdz = new Ext.form.TextField({
            fieldLabel: '电子邮箱',
            name: 'txdz',
            xtype: 'textfield',
            anchor: '100%',
            blankText: '该选项为必填项,请输入内容...',
       //     regex: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
        });
        
        this.slbmmc =  new wxpt.OrgSingelSelectAll('<font color="red">*</font>受理部门名称','slbmmc','slbmmc','100%');
        
        this.xjbt.allowBlank = false;
        this.lxdh.allowBlank = true;
        this.txdz.allowBlank = true;
        this.slbmmc.allowBlank = false;
        this.fjrxm.allowBlank = false;
        
        this.thly = new Ext.form.TextArea({
            fieldLabel: '退回理由',
            name: 'thly',
            readOnly: false,
            anchor: '95%',
            height:100,
            maxLength: 700,
            maxLengthText: '700!'
        });
       this.thly.hide();
       //this.thly.allowBlank = false;
   	   var pnRow5=new Ext.Panel({  
              layout:'column',  
              border:false,  
              items:[  
                  new Ext.Panel({  
                      columnWidth:1,  
                      layout:'form',  
                      border:false,  
                      labelWidth:90,  
                      labelAlign:'left',  
                      items:[  
                          this.thly
                      ]  
                  }),  
              ]  
          });

        var pnRow1=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:1,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.xjbt
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
						this.txdz 
                    ]  
                }),  
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
                        this.slbmmc
                    ]  
                }), 
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.fjrxm
                    ]  
                }),
            ]  
        });
        var pnRow4=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:1,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'left',  
                    items:[  
							{  
							    xtype:'StarHtmleditor',  
							    fieldLabel:'<font color="red">*</font>信件内容',
							    name:'xjnr',  
							    border:false, 
							    layout:'form', 
							    labelWidth:40,
							    width:660,
							    height:300,
							    labelAlign:'right'
							} 
                    ]  
                }),  
            ]  
        });
        
        ConstructionForm.superclass.constructor.call(this, {
        	anchor: '80%',
        	autoHeight:true,
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
						pnRow5
            ],
            buttonAlign :'center',
            buttons: [
                      {text:'通过',iconCls: 'edit',handler:this.completeFormClick,scope:this},
                      {text:'退回',iconCls: 'edit',handler:this.tuihuiFormClick,scope:this,hidden:false},
                      {text:'确认退回',iconCls: 'edit',handler:this.tuihuiFormConClick,scope:this,hidden:true},
                      {text:'禁用',iconCls: 'edit',handler:this.jinyongFormClick,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this},
              ]
        });
     },
     completeFormClick: function() {       //通过
 	    	if(this.getForm().isValid()) {
 	    	 var record = constructionGrid.getSelectionModel().getSelections(); 
 	      	 var slbmbh = this.slbmmc.getOrgId();
 	      	 if(this.slbmmc.getValue()!=null&&this.slbmmc.getValue()!=''){
 	      		 if(this.slbmmc.getValue()==record[0].get('slbmmc')){
 	      			 slbmbh=record[0].get('slbmbh');
 	      		 }else{
 	      			 slbmbh=this.slbmmc.getOrgId();
 	      		 }
 	      	 }else{
 	      		 if(this.slbmmc.getOrgId()==null||this.slbmmc.getOrgId()==''||this.slbmmc.getOrgId()=='null'){
 	      			 Ext.MessageBox.alert("系统提示:", "请选择使用部门");
 	      			 return false;
 	      		 }
 	      	 }
 	      	/*slbmbh=record[0].get('slbmbh');*/
// 	      	 if(slbmbh==null){
// 	      		Ext.MessageBox.alert("系统提示:", BLANKSTR + "请输入部门名称!" + BLANKSTR);
// 	      		return false;
// 	      	 }
            if(this.slbmmc.getValue()!=record[0].get('slbmmc')){
          		slbmbh=this.slbmmc.getOrgId();
          	}
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'tongguo', 
                 method: 'POST',
                 params:{
                   	id:record[0].get('id'),slbmbh:slbmbh,
                   },
                 success: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "审核成功" + BLANKSTR);
                 	constructionGrid.constructionUpdateWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "审核失败" + BLANKSTR);
                 }
         	});
         }
     },
     tuihuiFormClick: function() {       //退回
    	var  win=constructionGrid.constructionUpdateWindow;
    	var  winForm = win.constructionForm;
          winForm.thly.show();
          win.constructionForm.buttons[1].hide();   //隐藏退回按钮
          win.constructionForm.buttons[2].show();   //显示确认退回按钮
     },
     tuihuiFormConClick: function() {       //退回
     	var  win=constructionGrid.constructionUpdateWindow;
     	var  winForm = win.constructionForm;
           winForm.thly.show();
           var thly = winForm.thly.value;
     	 if(this.getForm().isValid()) {
         	 var record = constructionGrid.getSelectionModel().getSelections();
          	this.getForm().submit({
                  waitMsg: '正在提交数据...',
                  url: 'tuihui', 
                  method: 'POST',
                  params:{
                    	id:record[0].get('id'),slbmbh:this.slbmmc.getOrgId(),thly:thly
                    },
                  success: function(form, action) {
                  	Ext.MessageBox.alert("系统提示:", BLANKSTR + "退回成功" + BLANKSTR);
                  	constructionGrid.constructionUpdateWindow.hide();
                  	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                  },
                  failure: function(form, action) {
                  	Ext.MessageBox.alert("系统提示:", BLANKSTR + "退回失败" + BLANKSTR);
                  }
          	});
          }
      },
     jinyongFormClick: function() {       //禁用
         if(this.getForm().isValid()) {
        	 var record = constructionGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'jinyong', 
                 method: 'POST',
                 params:{
                   	id:record[0].get('id'),slbmbh:this.slbmmc.getOrgId(),
                   },
                 success: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "禁用成功" + BLANKSTR);
                 	constructionGrid.constructionUpdateWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "禁用失败" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});
/*************************************** LookForm 组件 **************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.xjbt = this.createTextField('信件标题', 'xjbt', '95%','',null);
    	
    	this.fjrxm = this.createTextField('发件人', 'fjrxm', '90%','',null,100,'长度超过不能100');
    	
    	this.lxdh = new Ext.form.TextField({
            fieldLabel: '联系电话',
            name: 'lxdh',
            allowNegative :false,
           // maxLength:11,
           // maxLengthText:'长度超过不能11位', 
            anchor: '90%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        
        this.txdz = this.createTextField('电子邮箱', 'txdz', '100%','',null,100,'长度超过不能100！');
        
        this.slbmmc =  new wxpt.OrgSingelSelectAll('受理部门名称','slbmmc','slbmmc','100%');
        
        
        this.xjbt.readOnly = true;
        this.lxdh.readOnly = true;
        this.txdz.readOnly = true;
        this.slbmmc.readOnly = true;
        this.fjrxm.readOnly = true;
        
        this.xjbt.allowBlank = true;
        this.lxdh.allowBlank = true;
        this.txdz.allowBlank = true;
        this.slbmmc.allowBlank = true;

        var pnRow1=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:1,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.xjbt
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
						this.txdz 
                    ]  
                }),  
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
                        this.slbmmc
                    ]  
                }), 
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.fjrxm
                    ]  
                }),
            ]  
        });
        var pnRow4=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:1,  
                    layout:'form',  
                    border:false,  
                    labelWidth:95,  
                    labelAlign:'left',  
                    items:[  
						{  
						    xtype:'StarHtmleditor',  
						    fieldLabel:'信件内容',
						    name:'xjnr',  
						    border:false, 
						    layout:'form', 
						    labelWidth:40,
						    width:660,
						    height:300,
						    labelAlign:'right'
						} 
                    ]  
                }),  
            ]  
        });
        
        LookForm.superclass.constructor.call(this, {
        	anchor: '80%',
        	//autoHeight:true,
        	height:450,
        	 autoScroll: true,
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
						pnRow4
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
/***************************************ConstructionUpdateWindow组件**************************************************/
ConstructionUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new ConstructionForm();
    	
    	this.constructionForm.txdz.readOnly = true;
    	this.constructionForm.fjrxm.readOnly = true;
    	this.constructionForm.lxdh.readOnly = true;
    	
    	ConstructionUpdateWindow.superclass.constructor.call(this, {
        	title: "审核邮件",
        	width: 845,
            anchor: '100%',
           // autoHeight:true,
        	height:475,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.constructionForm]
        });
    }
});
/***************************************ConstructionLookWindow组件**************************************************/
ConstructionLookWindow = Ext.extend(Ext.Window, {
	lookForm : null,
    constructor: function() {
    	this.lookForm = new LookForm();
    	ConstructionLookWindow.superclass.constructor.call(this, {
        	title: "查看邮件",
        	width: 845,
            anchor: '100%',
            autoHeight:true,
            height:475,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.lookForm]
        });
    }
});
/**************************ConstructionGrid*******************************************/
ConstructionGrid = Ext.extend(UxGrid, {
	pageSizeCombo: null,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: USER_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},[
                           {name:'id'},{name:'xjbt'},{name:'clbm'},{name:'clzt'},{name:'fjrxm'},{name:'fjrbh'},{name:'slbmbh'},{name:'slbmmc'},
                           {name:'txdz'},{name:'lxdh'},{name:'xxsj'},{name:'xjnr'},{name:'shsj'},{name:'shr'},{name:'cljg'},{name:'clsj'},{name:'pj'},
                           {name:'bz'},{name:'ipdz'}
            ])
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
               /* '-',{text:'审核',iconCls: 'edit',handler:this.onModifyClick,scope:this},*/
            	'-',{xtype:'textfield',id:'code',width: 150,
             	   emptyText:'信件标题&受理单位...',  
            	    },
 	  			'-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
   						var code = Ext.getCmp('code').getValue();
   						constructionGrid.store.baseParams= {code:code};
   						constructionGrid.store.load({params:{start:0,limit:PAGESIZE}});
   				    }},'-',
   				    {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
   	   				Ext.getCmp('code').setValue("");
      			  }
               },
            ]
        });
        this.constructionUpdateWindow = new ConstructionUpdateWindow();
        this.constructionLookWindow = new ConstructionLookWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        ConstructionGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: false,
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
                {header:'编号',dataIndex:'id',width:80,sortable: true, hidden:true},
                {header: '操作', width: 150, dataIndex: 'sbbh', align:"center",sortable: true,hidden: false,
					renderer:function(value, cellmeta, record){
						   return "<a href='javascript:;' onclick='constructionGrid.onLook()'>" +
						   		  "<img src='"+PROJECT_NAME+"/resources/img/ck.png' title='查看' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
						   		  "<a href='javascript:;' onclick='constructionGrid.onModifyClick()'>"+
						   		  "<img src='"+PROJECT_NAME+"/resources/img/sh.png' title='审核' style='width: 25px;cursor: pointer;'/></a>";
            		}
				},
                {header:'信件标题',dataIndex:'xjbt',width:200,sortable: true},
                {header:'发件人',dataIndex:'fjrxm',width:150,sortable: true},
                {header:'IP地址',dataIndex:'ipdz',width:150,sortable: true},
            	{header:'发件时间',dataIndex:'xxsj',width:150,sortable: true},
            	{header:'受理单位',dataIndex:'slbmmc',width:150,sortable: true},
            	{header:'受理状态',dataIndex:'clzt',width:150,sortable: true,
            		renderer:function(value){
	                    if(value == '1') {
	                        return "<span style='color:blue;'>审批中</span>";
	                    }else if(value == '2') {
	                        return "<span style='color:red;'>审批不通过退回</span>";
	                    }else if(value == '3') {
	                        return "<span style='color:fuchsia;'>审批通过</span>";
	                    }else if(value == '4') {
	                        return "<span style='color:lime ;'>未回复</span>";
	                    }else if(value == '5') {
	                        return "<span style='color:green;'>回复未评价</span>";
	                    }else if(value == '6') {
	                        return "<span style='color:grey;'>已评价结束</span>";
	                    }else if(value == '7'){
	                    	return "<span style='color:orange;'>禁用</span>";
	                    }else{
	                    	return value;
	                    }
            		}
            	},
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
        });
    },
    onLook: function() {                  //查看
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.constructionLookWindow;
   		    	win.show();
   		    	win.lookForm.getForm().loadRecord(vrecord);
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onModifyClick: function() {                  //审核
    	var records=this.getSelectionModel().getSelections();
      	var  win=constructionGrid.constructionUpdateWindow;
      	var  winForm = win.constructionForm;
         winForm.thly.hide();
        win.constructionForm.buttons[1].show();   //隐藏退回按钮
        win.constructionForm.buttons[2].hide();   //显示确认退回按钮
      	win.constructionForm.getForm().reset();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.constructionUpdateWindow;
   		    	win.show();
   		    	win.constructionForm.getForm().loadRecord(vrecord);
   			}else{
   				Ext.Msg.alert('系统提示', '不能审核多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
});

/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';

    constructionGrid = new ConstructionGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    constructionGrid.store.load({params:{start:0,limit:PAGESIZE}});
    new Ext.Viewport({
    	layout: 'border',
    	items:[
		//conditionForm,
		constructionGrid
    	]
    });
   
});