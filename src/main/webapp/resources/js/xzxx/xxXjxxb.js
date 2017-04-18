var USER_GRID_STORE_URL = 'getXjxxbList';
var PAGESIZE=20;
var yearll=new Ext.form.TextField();
var amtd = new Ext.form.TextField();

//添加页面
var ACT_DEAL_WINDOW;
/*************************************** ConstructionForm组件 **************************************************/
ConstructionForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	/*this.xjbt = this.createTextField('<font color="red">*</font>信件标题', 'xjbt', '85%','',null,32,'长度超过不能32');*/
    	this.xjbt = new Ext.form.TextField({
            fieldLabel: '<font color="red">*</font>信件标题',
            name: 'xjbt',
            xtype: 'textfield',
            anchor: '95%',
            blankText: '该选项为必填项,请输入内容...',
            regex: /\s*\S+/
        });
    	
    	this.lxdh = new Ext.form.TextField({
            fieldLabel: '联系电话',
            name: 'lxdh',
            allowNegative :false,
            //maxLength:11,
            //maxLengthText:'长度超过不能11位', 
            anchor: '90%',
            allowDecimals: false,
            allowNegative: false,
            //regex: /^\d+(\.\d+)?$/,
            regex:/^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/,
            hidden:false
        });
        
        this.txdz = new Ext.form.TextField({
            fieldLabel: '电子邮箱',
            name: 'txdz',
            xtype: 'textfield',
            anchor: '100%',
            blankText: '该选项为必填项,请输入内容...',
           regex: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
        });
        
        this.slbmmc =  new wxpt.OrgSingelSelectAll('受理部门名称','slbmmc','slbmmc','100%');
        
        this.xjnr = new Ext.form.TextArea({
            fieldLabel: '<font color="red">*</font>内容',
            name: 'xjnr',
            readOnly: false,
            anchor: '90%',
            height:60
            //maxLength: 256,
            //maxLengthText: '256！'
        });
        
        this.xjbt.allowBlank = false;
        this.lxdh.allowBlank = true;
        this.txdz.allowBlank = true;
        this.slbmmc.allowBlank = true;
        this.xjnr.allowBlank = false;

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
				    labelAlign:'right',  
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
				})
            ]  
        });
        /* var pnRow5=new Ext.Panel({  
        layout:'form',  
        border:false,  
        labelWidth:40,  
        labelAlign:'right',  
        items:[  
            {  
                xtype:'htmleditor',  
                name:'note',  
                fieldLabel:'备注',  
                height:200,  
                anchor:'98%'  
            }  
        ]  
    }); */
        
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
						pnRow4
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '提交', width: 20,iconCls: 'add', hidden: false, handler: this.addFormClick, scope: this},  
                      {text:'修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
                      {text:'重新提交',iconCls: 'edit',handler:this.submitFormClick,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'save', 
                 method: 'POST',
                 params:{
                	 slbmbh:this.slbmmc.getOrgId(),
                  },
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "发布成功" + BLANKSTR);
                 	constructionGrid.constructionInsertWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "发布失败" + BLANKSTR);
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
        	 var record = constructionGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'update', 
                 method: 'POST',
                 params:{
                   	id:record[0].get('id'),slbmbh:this.slbmmc.getOrgId(),
                   },
                 success: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功" + BLANKSTR);
                 	constructionGrid.constructionUpdateWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败" + BLANKSTR);
                 }
         	});
         }
     },
     submitFormClick: function() {       //重新提交
         if(this.getForm().isValid()) {
        	 var record = constructionGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'submit', 
                 method: 'POST',
                 params:{
                   	id:record[0].get('id'),slbmbh:this.slbmmc.getOrgId(),
                   },
                 success: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "重新提交成功" + BLANKSTR);
                 	constructionGrid.constructionSubmitWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "重新提交失败" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});

/*************************************** ConstructionAdmForm组件 **************************************************/
ConstructionAdmForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	/*this.xjbt = this.createTextField('<font color="red">*</font>信件标题', 'xjbt', '85%','',null,32,'长度超过不能32');*/
    	this.xjbt = new Ext.form.TextField({
            fieldLabel: '<font color="red">*</font>信件标题',
            name: 'xjbt',
            xtype: 'textfield',
            anchor: '95%',
            blankText: '该选项为必填项,请输入内容...',
            regex: /\s*\S+/
        });
    	
    	this.fjrxm = this.createTextField('发件人', 'fjrxm', '90%','',null,100,'长度超过不能100');
    	this.shr = this.createTextField('审核人', 'xm', '100%','',null,100,'长度超过不能100');
    	this.clr = this.createTextField('受理人', 'clr', '90%','',null,100,'长度超过不能100');
    	
    	this.lxdh = new Ext.form.TextField({
            fieldLabel: '联系电话',
            name: 'lxdh',
            allowNegative :false,
           // maxLength:11,
            //maxLengthText:'长度超过不能11位', 
            anchor: '90%',
            allowDecimals: false,
            allowNegative: false,
            regex:/^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/,
            hidden:false
        });
        
        this.txdz = new Ext.form.TextField({
            fieldLabel: '电子邮箱',
            name: 'txdz',
            xtype: 'textfield',
            anchor: '100%',
            blankText: '该选项为必填项,请输入内容...',
            regex: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
        });
        
        this.slbmmc =  new wxpt.OrgSingelSelectAll('受理部门名称','slbmmc','slbmmc','100%');
        
        this.xjnr = new Ext.form.TextArea({
            fieldLabel: '<font color="red">*</font>内容',
            name: 'xjnr',
            readOnly: false,
            anchor: '90%',
            height:60
            //maxLength: 256,
           // maxLengthText: '256！'
        });
        
        this.myd = this.createCombo('满意度', 'zdz', 'zdmc', 'myd', '100%', PROJECT_NAME+'/xzxx/XxXjxxb/getDicByMyd');
		this.myd. blankText="";
        this.myd.store.load();
		
        
        this.cljg = new Ext.form.TextArea({
            fieldLabel: '处理结果',
            name: 'cljg',
            readOnly: false,
            anchor: '95%',
            height:50,
        });
        
        this.pj = new Ext.form.TextArea({
            fieldLabel: '评价',
            name: 'pj',
            readOnly: false,
            anchor: '95%',
            height:50,
            maxLength: 500,
            maxLengthText: '500!'
        });
        
        this.xjbt.allowBlank = false;
        this.lxdh.allowBlank = true;
        this.txdz.allowBlank = true;
        this.slbmmc.allowBlank = true;
        this.xjnr.allowBlank = false;

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
                }) 
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
                })  
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
                })
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
				    labelAlign:'right',  
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
				})
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
                        this.shr
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
                })
            ]  
        });
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
                        this.cljg
                    ]  
                })  
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
                        this.pj
                    ]  
                })  
            ]  
        });
        var pnRow8=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'left',  
                    items:[  
                        this.myd
                    ]  
                }) 
            ]  
        });

        this.fjrxm.readOnly = true;
        this.shr.readOnly = true;
        this.clr.readOnly = true;
        
        this.cljg.allowBlank = true;
        this.pj.allowBlank = true;
        this.shr.allowBlank = true;
        this.clr.allowBlank = true;
        this.myd.allowBlank = true;
        
        ConstructionAdmForm.superclass.constructor.call(this, {
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
						pnRow5,
						pnRow6,
						pnRow8,
						pnRow7,
						pnRow4
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '提交', width: 20,iconCls: 'add', hidden: false, handler: this.addFormClick, scope: this},  
                      {text:'修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
                      {text:'重新提交',iconCls: 'edit',handler:this.submitFormClick,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'save', 
                 method: 'POST',
                 params:{
                	 slbmbh:this.slbmmc.getOrgId(),
                  },
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "发布成功" + BLANKSTR);
                 	constructionGrid.constructionInsertWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "发布失败" + BLANKSTR);
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
        	 var record = constructionGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'update', 
                 method: 'POST',
                 params:{
                   	id:record[0].get('id'),slbmbh:this.slbmmc.getOrgId(),
                   },
                 success: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功" + BLANKSTR);
                 	constructionGrid.constructionAdmUpdateWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败" + BLANKSTR);
                 }
         	});
         }
     },
     submitFormClick: function() {       //重新提交
         if(this.getForm().isValid()) {
        	 var record = constructionGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'submit', 
                 method: 'POST',
                 params:{
                   	id:record[0].get('id'),slbmbh:this.slbmmc.getOrgId(),
                   },
                 success: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "重新提交成功" + BLANKSTR);
                 	constructionGrid.constructionSubmitWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "重新提交失败" + BLANKSTR);
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
    	this.clzt= this.createTextField('处理状态', 'clzt', '95%','',null);
    	
    	this.fjrxm = this.createTextField('发件人', 'fjrxm', '90%','',null,100,'长度超过不能100');
    	this.shr = this.createTextField('审核人', 'xm', '100%','',null,100,'长度超过不能100');
    	this.clr = this.createTextField('受理人', 'clr', '90%','',null,100,'长度超过不能100');
    	
    	this.lxdh = new Ext.form.TextField({
            fieldLabel: '联系电话',
            name: 'lxdh',
            allowNegative :false,
           // maxLength:11,
            //maxLengthText:'长度超过不能11位', 
            anchor: '90%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            regex:/^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/,
            hidden:false
        });
        
        this.txdz = this.createTextField('电子邮箱', 'txdz', '100%','',null,100,'长度超过不能100！');
        
        this.slbmmc =  new wxpt.OrgSingelSelectAll('受理部门名称','slbmmc','slbmmc','100%');
        
        this.myd = this.createCombo('满意度', 'zdz', 'zdmc', 'myd', '100%', PROJECT_NAME+'/xzxx/XxXjxxb/getDicByMyd');
		this.myd. blankText="";
        this.myd.store.load();
		
        
        this.cljg = new Ext.form.TextArea({
            fieldLabel: '处理结果',
            name: 'cljg',
            readOnly: false,
            anchor: '95%',
            height:50,
        });
        
        this.pj = new Ext.form.TextArea({
            fieldLabel: '评价',
            name: 'pj',
            readOnly: false,
            anchor: '95%',
            height:50,
            maxLength: 500,
            maxLengthText: '500！'
        });
        
        this.xjbt.readOnly = true;
        this.lxdh.readOnly = true;
        this.txdz.readOnly = true;
        this.slbmmc.readOnly = true;
        this.cljg.readOnly = true;
        this.pj.readOnly = true;
        this.fjrxm.readOnly = true;
        this.shr.readOnly = true;
        this.clr.readOnly = true;
        this.myd.readOnly = true;
        
        this.xjbt.allowBlank = true;
        this.lxdh.allowBlank = true;
        this.txdz.allowBlank = true;
        this.slbmmc.allowBlank = true;
        this.cljg.allowBlank = true;
        this.pj.allowBlank = true;
        this.shr.allowBlank = true;
        this.clr.allowBlank = true;
        this.myd.allowBlank = true;

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
                })
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
                })  
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
                })
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
                        this.shr
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
						{  
						    xtype:'StarHtmleditor',  
						    fieldLabel:'<font color="red">*</font>信件内容',
						    name:'xjnr',  
						    border:false, 
						    layout:'form', 
						    labelWidth:40,
						    width:660,
						    height:200,
						    labelAlign:'right'
						} 
                    ]  
                }),  
            ]  
        });
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
                        this.cljg
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
                        this.pj
                    ]  
                }),  
            ]  
        });
        var pnRow8=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'left',  
                    items:[  
                        this.myd
                    ]  
                }),  
            ]  
        });
        
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
       this.thly.readOnly = true;
       
   	   var pnRow9=new Ext.Panel({  
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
						pnRow6,
						pnRow8,
						pnRow7,
						pnRow5,
						pnRow9
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

/*************************************** XjpjForm 组件 **************************************************/
XjpjForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.xjbt = this.createTextField('<font color="red">*</font>信件标题', 'xjbt', '95%','',null);
    	
    	this.lxdh = new Ext.form.TextField({
            fieldLabel: '联系电话',
            name: 'lxdh',
            allowNegative :false,
           // maxLength:11,
            //maxLengthText:'长度超过不能11位', 
            anchor: '90%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            regex:/^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/,
            hidden:false
        });
        
        this.txdz = this.createTextField('电子邮箱', 'txdz', '100%','',null,100,'长度超过不能100！');
        this.clsj = this.createTextField('处理时间', 'clsj', '90%','',null,100,'长度超过不能100！');
        
        this.slbmmc =  new wxpt.OrgSingelSelectAll('受理部门名称','slbmmc','slbmmc','100%');
        this.cljg = new Ext.form.TextArea({
            fieldLabel: '<font color="red">*</font>处理结果',
            name: 'cljg',
            readOnly: false,
            anchor: '95%',
            height:50
           // maxLength: 256,
           // maxLengthText: '256！'
        });
        this.pj = new Ext.form.TextArea({
            fieldLabel: '<font color="red"></font>评价',
            name: 'pj',
            readOnly: false,
            anchor: '95%',
            height:50,
            maxLength: 500,
            maxLengthText: '500！'
        });
        this.fcmy = this.createRadio('非常满意','myd',true,1);
        this.my = this.createRadio('满意','myd',false,2);
        this.bmy = this.createRadio('不满意','myd',false,3);
        
        this.xjbt.readOnly = true;
        this.lxdh.readOnly = true;
        this.txdz.readOnly = true;
        this.clsj.readOnly = true;
        this.slbmmc.readOnly = true;
        this.cljg.readOnly = true;
        this.txdz.allowBlank = true;
        this.pj.allowBlank = true;

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
                        this.clsj
                    ]  
                })
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
						    fieldLabel:'信件内容',
						    name:'xjnr',  
						    border:false, 
						    layout:'form', 
						    labelWidth:40,
						    width:660,
						    height:200,
						    labelAlign:'right'
						} 
                    ]  
                }),  
            ]  
        });
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
                        this.cljg
                    ]  
                }),  
            ]  
        });
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
        	    	          this.pj
        	    	          ]  
        	       }),  
        	       ]  
        });
        var pnRow7=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.3,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
						this.fcmy 
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.2,  
                    layout:'form',  
                    border:false,  
                    labelWidth:1,  
                    labelAlign:'right',  
                    items:[  
                        this.my
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.2,  
                    layout:'form',  
                    border:false,  
                    labelWidth:5,  
                    labelAlign:'right',  
                    items:[  
                        this.bmy
                    ]  
                }), 
            ]  
        });
        
        XjpjForm.superclass.constructor.call(this, {
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
						pnRow5,
						pnRow7,
						pnRow6
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '提交', width: 20,iconCls: 'add', hidden: false, handler: this.addFormClick, scope: this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
        	var record = constructionGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'savePjxx', 
                 method: 'POST',
                 params:{
                	 id:record[0].get('id'),pj:this.pj.getValue()
                  },
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "评价成功" + BLANKSTR);
                 	constructionGrid.constructionXjpjWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "评价失败" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});
/***************************************ConstructionInsertWindow组件**************************************************/
ConstructionInsertWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new ConstructionForm();
        this.constructionForm.buttons[0].show();   //显示发布按钮
    	this.constructionForm.buttons[1].hide();   //隐藏修改按钮
    	this.constructionForm.buttons[2].hide();   //隐藏重新提交按钮
        ConstructionInsertWindow.superclass.constructor.call(this, {
            title: "发布邮件",
            width: 700,
            anchor: '100%',
          //  autoHeight:true,
        	height:360,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.constructionForm]
        });
    }
});

/***************************************ConstructionUpdateWindow组件**************************************************/
ConstructionUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new ConstructionForm();
    	this.constructionForm.buttons[0].hide();   //隐藏添加按钮
    	this.constructionForm.buttons[1].show();   //显示修改按钮
    	this.constructionForm.buttons[2].hide();   //隐藏重新提交按钮
    	ConstructionUpdateWindow.superclass.constructor.call(this, {
        	title: "修改邮件",
            width: 845,
            anchor: '100%',
          //  autoHeight:true,
        	height:470,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.constructionForm]
        });
    }
});

/***************************************ConstructionAdmUpdateWindow组件**************************************************/
ConstructionAdmUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new ConstructionAdmForm();
    	this.constructionForm.buttons[0].hide();   //隐藏添加按钮
    	this.constructionForm.buttons[1].show();   //显示修改按钮
    	this.constructionForm.buttons[2].hide();   //隐藏重新提交按钮
    	ConstructionAdmUpdateWindow.superclass.constructor.call(this, {
        	title: "修改邮件",
            width: 845,
            anchor: '100%',
          //  autoHeight:true,
        	height:470,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.constructionForm]
        });
    }
});

/***************************************ConstructionSubmitWindow组件**************************************************/
ConstructionSubmitWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new ConstructionForm();
    	this.constructionForm.buttons[0].hide();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //隐藏修改按钮
    	this.constructionForm.buttons[2].show();   //显示重新提交按钮
    	ConstructionSubmitWindow.superclass.constructor.call(this, {
        	title: "重新提交邮件",
        	width: 845,
            anchor: '100%',
            //autoHeight:true,
        	height:470,
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
            //autoHeight:true,
        	height:470,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.lookForm]
        });
    }
});

/***************************************ConstructionLookWindow组件**************************************************/
ConstructionXjpjWindow = Ext.extend(Ext.Window, {
	xjpjForm : null,
    constructor: function() {
    	this.xjpjForm = new XjpjForm();
    	ConstructionXjpjWindow.superclass.constructor.call(this, {
        	title: "信件评价",
        	width: 845,
            anchor: '100%',
            //autoHeight:true,
        	height:470,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.xjpjForm]
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
                           {name:'txdz'},{name:'lxdh'},{name:'xxsj'},{name:'xjnr'},{name:'shsj'},{name:'xm'},{name:'cljg'},{name:'clsj'},{name:'pj'},
                           {name:'bz'},{name:'clr'},{name:'myd'},{name:'ipdz'},{name:'thly'}
            ])
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                '-',{text:'发件',iconCls: 'add',handler:this.onAddClick,scope:this},
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
        this.constructionInsertWindow = new ConstructionInsertWindow();       
        this.constructionUpdateWindow = new ConstructionUpdateWindow();
        this.constructionAdmUpdateWindow = new ConstructionAdmUpdateWindow();
        this.constructionSubmitWindow = new ConstructionSubmitWindow();
        this.constructionLookWindow = new ConstructionLookWindow();
        this.constructionXjpjWindow = new ConstructionXjpjWindow();
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
                {header: '操作', width: 180, dataIndex: 'sbbh', align:"center",  sortable: true,hidden: false,
					renderer:function(value, cellmeta, record){
						if(isAdm=='1'){
							return "<a href='javascript:' onclick='constructionGrid.onLook()'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/ck.png' title='查看' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<a href='javascript:' onclick='constructionGrid.onAdmModifyClick()' style='cursor: pointer;'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/xg.png' title='修改' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<a href='javascript:' onclick='constructionGrid.onSubmit()'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/cxtj.png' title='重新提交' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<a href='javascript:;' onclick='constructionGrid.onXjpj()'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/pj.png' title='评价' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<a href='javascript:' onclick='constructionGrid.onDeleteClick()'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/sc.png' title='删除' style='width: 25px;cursor: pointer;'/></a>";
						}else{
							if(record.get("clzt") == 1){
								return "<a href='javascript:' onclick='constructionGrid.onLook()'>" +
									"<img src='"+PROJECT_NAME+"/resources/img/ck.png' title='查看' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
									"<a href='javascript:' onclick='constructionGrid.onModifyClick()' style='cursor: pointer;'>" +
									"<img src='"+PROJECT_NAME+"/resources/img/xg.png' title='修改' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
									"<img src='"+PROJECT_NAME+"/resources/img/cxtjhs.png' title='重新提交' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
									"<img src='"+PROJECT_NAME+"/resources/img/pjhs.png' title='评价' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
									"<a href='javascript:' onclick='constructionGrid.onDeleteClick()'>" +
									"<img src='"+PROJECT_NAME+"/resources/img/sc.png' title='删除' style='width: 25px;cursor: pointer;'/></a>";
							}else if(record.get("clzt") == 2){
								return "<a href='javascript:' onclick='constructionGrid.onLook()' style='cursor: pointer;'>" +
									"<img src='"+PROJECT_NAME+"/resources/img/ck.png' title='查看' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
									"<a href='javascript:' onclick='constructionGrid.onModifyClick()' style='cursor: pointer;'>" +
									"<img src='"+PROJECT_NAME+"/resources/img/xg.png' title='修改' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
									"<a href='javascript:' onclick='constructionGrid.onSubmit()'>" +
									"<img src='"+PROJECT_NAME+"/resources/img/cxtj.png' title='重新提交' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
									"<img src='"+PROJECT_NAME+"/resources/img/pjhs.png' title='评价' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
									"<a href='javascript:' onclick='constructionGrid.onDeleteClick()' style='cursor: pointer;'>"+
									"<img src='"+PROJECT_NAME+"/resources/img/sc.png' title='删除' style='width: 25px;cursor: pointer;'/></a>";
							}else if(record.get("clzt") == 5){
								return "<a href='javascript:;' onclick='constructionGrid.onLook()' style='cursor: pointer;'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/ck.png' title='查看' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/xghs.png' title='修改' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/cxtjhs.png' title='重新提交' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<a href='javascript:;' onclick='constructionGrid.onXjpj()'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/pj.png' title='评价' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/schs.png' title='删除' style='width: 25px;cursor: pointer;'/></a>";
							}else if(record.get("clzt") == 7){
								return "<a href='javascript:;' onclick='constructionGrid.onLook()' style='cursor: pointer;'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/ck.png' title='查看' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/xghs.png' title='修改' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/cxtjhs.png' title='重新提交' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/pjhs.png' title='评价' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<a href='javascript:' onclick='constructionGrid.onDeleteClick()' style='cursor: pointer;'>"+
								"<img src='"+PROJECT_NAME+"/resources/img/sc.png' title='删除' style='width: 25px;cursor: pointer;'/></a>";
							}else{
								return "<a href='javascript:;' onclick='constructionGrid.onLook()' style='cursor: pointer;'>" +
								"<img src='"+PROJECT_NAME+"/resources/img/ck.png' title='查看' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/xghs.png' title='修改' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/cxtjhs.png' title='重新提交' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/pjhs.png' title='评价' style='width: 25px;cursor: pointer;'/></a>&nbsp;&nbsp;" +
								"<img src='"+PROJECT_NAME+"/resources/img/schs.png' title='删除' style='width: 25px;cursor: pointer;'/></a>";
							}
						}
            		}
				},
                {header:'信件标题',dataIndex:'xjbt',width:200,sortable: true},
                {header:'发件人',dataIndex:'fjrxm',width:150,sortable: true},
                {header:'登录名',dataIndex:'fjrbh',width:150,sortable: true},
                {header:'联系电话',dataIndex:'lxdh',width:150,sortable: true},
                {header:'IP地址',dataIndex:'ipdz',width:150,sortable: true},
            	{header:'发件时间',dataIndex:'xxsj',width:150,sortable: true},
            	{header:'受理单位',dataIndex:'slbmmc',width:150,sortable: true},
            	{header:'退回理由',dataIndex:'thly',width:150,sortable: true,hidden:true},
            	{header:'满意度',dataIndex:'myd',width:150,sortable: true,
            		renderer:function(value){
            			if(value == '1'){
            				return "<span style='color:blue;'>非常满意</span>";
            			}else if(value == '2'){
            				return "<span style='color:green;'>满意</span>";
            			}else if(value == '3'){
            				return "<span style= 'color:red;'>不满意</span>";
            			}else{
            				return value;
            			}
            		}
            	},
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
    onAddClick: function() {                     //发布
          	 var win = this.constructionInsertWindow;
          	$.ajax({
          		cache : true,
          		type : "POST",
                url: 'getClztsize', 
                async : false,
    			/*error : function(request) {
    				alert("保存失败，请联系管理员。");
    			},*/
    			success : function(data) {
    				if(data=='0'){
    					win.constructionForm.getForm().reset();
    			    	win.show();
    				}else if (data=='1'){
    					alert('您的信件正在处理中,请等待信件处理结束后再写信!');
    				}else if(data == '2'){
    					alert("您有未评价的信件！");
    				}
    			},
    			error : function(request) {
    				alert("错误！联系管理员。");
    			}
           	});
    	
    },
    
    onLook: function() {                  //查看
    	var records=this.getSelectionModel().getSelections();
      	var  win=constructionGrid.constructionLookWindow;
      	var  winForm = win.lookForm;
      	 var clzt = records[0].get('clzt');
      	 if(clzt=='2'){
         winForm.thly.show();
      	 }else{
      		winForm.thly.hide();
      	 }
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.constructionLookWindow;
   		    	win.show();
   		    	win.lookForm.getForm().loadRecord(vrecord);
   			}else{
   				Ext.Msg.alert('系统提示', '不能查看多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onModifyClick: function() {                  //修改
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.constructionUpdateWindow;
   		    	win.show();
   		    	win.constructionForm.getForm().loadRecord(vrecord);
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onAdmModifyClick: function() {                  //修改Adm
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.constructionAdmUpdateWindow;
   		    	win.show();
   		    	win.constructionForm.getForm().loadRecord(vrecord);
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onXjpj: function() {                  //评价
    	var records=this.getSelectionModel().getSelections();
    	if(records.length > 0) {
    		if(records.length == 1){
    			vrecord = records[0];
    			var win = this.constructionXjpjWindow;
    			win.show();
    			win.xjpjForm.getForm().loadRecord(vrecord);
    		}else{
    			Ext.Msg.alert('系统提示', '不能修改多条记录..');
    		}
    	}else{
    		Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
    	}    	
    },
    onSubmit: function() {                  //重新提交
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   		    	var win = this.constructionSubmitWindow;
   		    	win.show();
   		    	win.constructionForm.getForm().loadRecord(vrecord);
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
			       		url: 'delete', 
				       	   method : 'POST', 
				       	   params: { ids: valueStr},
			               success: function(form, action) {
				               Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除成功" + BLANKSTR);
				               constructionGrid.store.reload();
			               },
			               failure: function(form, action) {
			            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除失败" + BLANKSTR);
			               }
				       	});					
				}
	    	});	
    	}else{
    		 Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
         	return;
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