var USER_GRID_STORE_URL = 'getXjyslList';
var PAGESIZE=20;
var yearll=new Ext.form.TextField();
var amtd = new Ext.form.TextField();

//添加页面
var ACT_DEAL_WINDOW;
/*************************************** LookForm 组件 **************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.xjbt = this.createTextField('信件标题', 'xjbt', '95%','',null);
    	
    	this.fjrxm = this.createTextField('发件人', 'fjrxm', '100%','',null,100,'长度超过不能100');
    	
    	this.shr = this.createTextField('审核人', 'xm', '100%','',null,100,'长度超过不能100');
    	
    	this.clr = this.createTextField('受理人', 'clr', '90%','',null,100,'长度超过不能100');
    	
    	var xxsj =  new Ext.form.DateField({
			fieldLabel: '写信时间',
			name: "xxsj",
			format: "Y-m-d",
			anchor: '70%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
    	
    	var shsj =  new Ext.form.DateField({
			fieldLabel: '审核时间',
			name: "shsj",
			format: "Y-m-d",
			anchor: '70%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
    	
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
        
        this.txdz = this.createTextField('电子邮箱', 'txdz', '100%','',null,100,'长度超过不能100！');
        
        this.slbmmc =  new wxpt.OrgSingelSelect('受理部门名称','slbmmc','slbmmc','100%');
        
        this.clzt = this.createCombo('处理状态', 'zdz', 'zdmc', 'clzt', '90%', PROJECT_NAME+'/xzxx/XxXjxxb/getClztByLx');
		this.clzt.store.load();
        
        this.cljg = new Ext.form.TextArea({
            fieldLabel: '处理结果',
            name: 'cljg',
            readOnly: false,
            anchor: '95%',
            height:120,
        });
        
        this.xjbt.readOnly = true;
        this.lxdh.readOnly = true;
        this.txdz.readOnly = true;
        this.slbmmc.readOnly = true;
        this.fjrxm.readOnly = true;
        xxsj.readOnly = true;
        shsj.readOnly = true;
        this.shr.readOnly = true;
        this.clzt.readOnly = true;
        this.clr.readOnly = true;
        this.cljg.readOnly = true;
        
        this.xjbt.allowBlank = true;
        this.lxdh.allowBlank = true;
        this.txdz.allowBlank = true;
        this.slbmmc.allowBlank = true;
        this.fjrxm.allowBlank = true;
        xxsj.allowBlank = true;
        shsj.allowBlank = true;
        this.shr.allowBlank = true;
        this.clzt.allowBlank = true;
        this.clr.allowBlank = true;
        this.cljg.allowBlank = true;

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
						this.fjrxm 
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
                        this.clr
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
                        this.clzt
                    ]  
                }),
            ]  
        });
       /* var pnRow5=new Ext.Panel({  
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
            ]  
        });*/
        var pnRow6=new Ext.Panel({  
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
        var pnRow7=new Ext.Panel({  
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
                        this.cljg
                    ]  
                }),  
            ]  
        });
        
        LookForm.superclass.constructor.call(this, {
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
						//pnRow5,
						pnRow6,
						pnRow7
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
/***************************************ConstructionLookWindow组件**************************************************/
ConstructionLookWindow = Ext.extend(Ext.Window, {
	lookForm : null,
    constructor: function() {
    	this.lookForm = new LookForm();
    	ConstructionLookWindow.superclass.constructor.call(this, {
        	title: "查看邮件",
        	width: 845,
            anchor: '100%',
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
                           {name:'bz'},{name:'clr'},{name:'ipdz'}
            ])
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
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
                {header: '操作', width: 80, dataIndex: 'sbbh', align:"center",sortable: true,hidden: false,
					renderer:function(value, cellmeta, record){
						   return "<a href='javascript:;' onclick='constructionGrid.onLook()'>" +
					   		  "<img src='"+PROJECT_NAME+"/resources/img/ck.png' title='查看' style='width: 25px;cursor: pointer;'/></a>";
            		}
				},
                {header:'信件标题',dataIndex:'xjbt',width:200,sortable: true},
                {header:'发件人',dataIndex:'fjrxm',width:100,sortable: true},
                {header:'IP地址',dataIndex:'ipdz',width:100,sortable: true},
            	{header:'发件时间',dataIndex:'xxsj',width:150,sortable: true},
            /*	{header:'审核人',dataIndex:'shr',width:100,sortable: true},*/
            	{header:'审核时间',dataIndex:'shsj',width:150,sortable: true},
            	{header:'受理单位',dataIndex:'slbmmc',width:150,sortable: true},
            	{header:'受理人',dataIndex:'clr',width:100,sortable: true},
            	{header:'受理时间',dataIndex:'clsj',width:150,sortable: true},
            	{header:'处理结果',dataIndex:'cljg',width:350,sortable: true},
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