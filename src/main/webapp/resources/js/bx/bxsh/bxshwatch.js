var ENTITY_URL_LIST = "pager";
var PAGESIZE=20;
/*
 * 报修受理
 * @author chenhui
 * @date 2015-08-10
 */
var ztStore=new Ext.data.Store({
    proxy: new Ext.data.HttpProxy({url:'getBxzt',method: 'POST'}),
    reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'ztmc'},{name:'ztmc'}]))//,
    //autoLoad:true
});

/***************************************DictTypeForm组件**************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
    constructor: function() {
 
    	this.idHidden = this.createHidden('id','id');
        
    	this.bxbh = this.createTextField('申请单号','bxbh','95%','',null,100,'长度超过不能100');
        this.bxbh.allowBlank = true;
        this.bxbh.readOnly = true;
        
    	this.xgh = this.createTextField('学工号','xgh','95%','',null,100,'长度超过不能100');
        this.xgh.allowBlank = true;
        this.xgh.readOnly = true;


        this.sqrxm = this.createTextField('申请人','sqrxm','95%','',null,100,'长度超过不能100');
        this.sqrxm.allowBlank = true;
        this.sqrxm.readOnly = true;

        this.lxhm = this.createTextField('联系号码','lxhm','95%','',null,100,'长度超过不能100');
        this.lxhm.allowBlank = true;
        this.lxhm.readOnly = true;
        
        this.ly = this.createTextField('楼宇','ly','95%','',null,100,'长度超过不能100');
        this.ly.allowBlank = true;
        this.ly.readOnly = true;
        
        this.lh = this.createTextField('楼号','lh','95%','',null,100,'长度超过不能100');
        this.lh.allowBlank = true;
        this.lh.readOnly = true;
        
        this.wxdl = this.createTextField('维修大类','wxdl','95%','',null,100,'长度超过不能100');
        this.wxdl.allowBlank = true;
        this.wxdl.readOnly = true;
        
		this.sbztmc = this.createTextField('主题','sbztmc','95%','',null,100,'长度超过不能100');
	    this.sbztmc.allowBlank = true;
	    this.sbztmc.readOnly = true;

	    //this.nr = this.createTextArea('内容','nr','100%','97.5%',null,100,'长度超过不能100');
	    //this.nr.allowBlank = true;
	    //this.nr.readOnly = true;
	    
	    this.nr = new Ext.form.TextArea({
            fieldLabel: '内容',
            name: 'nr',
            readOnly: false,
            anchor: '97.5%',
            height:120,
        });
	    this.nr.allowBlank = true;
	    this.nr.readOnly = true;
	    
	    this.fw = this.createTextField('服务','fw','95%','',null,100,'长度超过不能100');
	    this.fw.allowBlank = true;
	    this.fw.readOnly = true;

	    this.pj = this.createTextArea('评价','pj','100%','97.5%',null,100,'长度超过不能100');
	    this.pj.allowBlank = true;
	    this.pj.readOnly = true;
	    
	    this.yysj = this.createTextField('预约时间','yysj','95%','',null,100,'长度超过不能100');
	    this.yysj.allowBlank = true;
	    this.yysj.readOnly = true;
	    
	    this.zt = this.createTextField('状态','zt','95%','',null,100,'长度超过不能100');
	    this.zt.allowBlank = true;
	    this.zt.readOnly = true;
	    
	/*    this.wxrxm = this.createTextField('派出人','wxrxm','100%','97.5%',null,100,'长度超过不能100');
	    this.wxrxm.allowBlank = true;
	    this.wxrxm.readOnly = true;*/
	    
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
							this.bxbh
	                    ]  
	                }),
	                new Ext.Panel({  
	                    columnWidth:.5,  
	                    layout:'form',  
	                    border:false,  
	                    labelWidth:80,  
	                    labelAlign:'right',  
	                    items:[  
	                        this.xgh
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
	                        this.lxhm
	                    ]  
	                }),   
	                new Ext.Panel({  
	                    columnWidth:.5,  
	                    layout:'form',  
	                    border:false,  
	                    labelWidth:80,  
	                    labelAlign:'right',  
	                    items:[  
	                        this.sqrxm
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
	                        this.wxdl
	                    ]  
	                }), 
	                new Ext.Panel({  
	                    columnWidth:.5,  
	                    layout:'form',  
	                    border:false,  
	                    labelWidth:80,  
	                    labelAlign:'right',  
	                    items:[  
	                        this.sbztmc
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
	                        this.ly
	                    ]  
	                }), 
	                new Ext.Panel({  
	                    columnWidth:.5,  
	                    layout:'form',  
	                    border:false,  
	                    labelWidth:80,  
	                    labelAlign:'right',  
	                    items:[  
	                        this.lh
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
	                        this.yysj
	                    ]  
	                }), 
	                new Ext.Panel({  
	                    columnWidth:.5,  
	                    layout:'form',  
	                    border:false,  
	                    labelWidth:80, 
	                    labelAlign:'right',  
	                    items:[  
	                           this.zt
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
	                    labelAlign:'right',  
	                    items:[  
	                        this.nr
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
	            /*	this.bxbh,
	            	this.xgh,
	            	this.lxhm,
	            	this.sqrxm,
	            	this.wxdl,
	            	this.ly,
	            	this.lh,
	            	this.dz,
	            	this.fw,
	            	this.yysj,
	            	this.zt,
	            	this.nr,
	            	this.pj,*/
	            	pnRow1,
	            	pnRow2,
	            	pnRow3,
	            	pnRow4,
	            	pnRow5,
	            	pnRow6
	            	
	            ],
	            buttonAlign :'center',
	            buttons: [
	                {text:'审核并派单', width: 20,iconCls:'save', handler: this.onPaid, scope: this},   
	                {text:'驳回', width: 20,iconCls:'edit', handler: this.onRejec, scope: this},
  	            	{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
	            ]
	        
	        });
	     },
	     onCloseClick: function(){ 			//关闭
	         this.ownerCt.hide();
	     },
	    
	     onRejec: function() {       //驳回
	    	 var records=txGlpzGrid.getSelectionModel().getSelections();
		    	var valueStr=[];
			       		valueStr.push(records[0].get('id')); 
			        	var win =txGlpzGrid.bxControlRejecWindow;
			        	win.rejecForm.getForm().reset();
			        	win.rejecForm.idHidden.setValue(valueStr);
			        	win.show();	 
		   		},
			    onPaid: function() { 		  //派单
					var records=txGlpzGrid.getSelectionModel().getSelections();
			    	var valueStr=[];
				       		valueStr.push(records[0].get('id')); 
				        	var win = txGlpzGrid.bxControlPlpdWindow;
				        	win.sendForm.getForm().reset();
				        	win.sendForm.idHidden.setValue(valueStr);
				        	win.show();	   	
			    }
	});


/*********************************************************************************************************************/
SendForm= Ext.extend(Ext.ux.Form, {
    constructor: function() {
    	this.idHidden = this.createHidden('id','id');
    	//this.spyj = this.createTextArea('<font color="red">*</font>驳回理由:', 'spyj', '95%','90%',5);
    	this.wxg = this.createCombo('维修工','yhbh','xm','','80%','getWxg','','',null);
		this.wxg.store.load();
    	this.wxg.allowBlank = false;
    	//this.bhly = this.createTextArea('<font color="red">*</font>审批意见:', 'bhly', '75%','审批意见不能为空！',5);
    	SendForm.superclass.constructor.call(this, {
        	anchor: '85%',
        	autoHeight:true,
        	layout:"form",
        	labelWidth: 80,
        	labelHeight: 600,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [{xtype:'fieldset', 'title':'',collapsible : false,anchor:'100%',
				layout: 'tableform',
				layoutConfig: {columns: 1},
				defaults: {
                    columnWidth: .99,
                    anchor: '95%',
                    layout: "form"
                },
				items : [   
                          this.wxg
		        ]},
            this.idHidden    
            ],
            buttonAlign :'center',
            buttons: [ 
                      {text: '确定', width: 20,iconCls: 'save', hidden: false, handler: this.addFormPlpdClick, scope: this},  
                      {text: '取消', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
    },
    addFormPlpdClick: function() {
         if(this.getForm().isValid()) {
      	this.getForm().submit({
              waitMsg: '正在提交数据...',
              url: 'checkBoxSend', 
              method: 'get',
              params:{
            	  sender:this.wxg.getRawValue(),
            	  sendBh:this.wxg.getValue()
              },
              success: function() { 
              	Ext.MessageBox.alert("系统提示:", BLANKSTR + "派单成功!" + BLANKSTR);
              	txGlpzGrid.bxControlPlpdWindow.hide();
              	txGlpzGrid.bxControlLookWindow.hide();
              	txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
              },
              failure: function() {
              	Ext.MessageBox.alert("系统提示:", BLANKSTR + "派单失败!" + BLANKSTR); 
              }
      	});
      }
  },
  onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});

/*********************************************************************************************************************/
RejecForm= Ext.extend(Ext.ux.Form, {
    constructor: function() {
    	this.idHidden = this.createHidden('id','id');
    	//this.spyj = this.createTextArea('<font color="red">*</font>驳回理由:', 'spyj', '95%','90%',5);
    	 this.spyj = new Ext.form.TextArea({
             fieldLabel: '<font color="red">*</font>驳回理由',
             name: 'spyj',
             readOnly: false,
             anchor: '85%',
             height:130,
             maxLength: 100,
             maxLengthText: '100!'
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
                       labelAlign:'left',  
                       items:[  
                           this.spyj
                       ]  
                   }),  
               ]  
           });
    	   
    	this.spyj.allowBlank = false;
    	//this.bhly = this.createTextArea('<font color="red">*</font>审批意见:', 'bhly', '75%','审批意见不能为空！',5);
    	RejecForm.superclass.constructor.call(this, {
        	anchor: '85%',
        	autoHeight:true,
        	layout:"form",
        	labelWidth: 80,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [{xtype:'fieldset', 'title':'',collapsible : false,anchor:'100%',
				layout: 'tableform',
				layoutConfig: {columns: 1},
				defaults: {
                    columnWidth: .99,
                    anchor: '95%',
                    layout: "form"
                },
				items : [   
                          pnRow1
						//this.bhly
		        ]},
            this.idHidden    
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '确定', width: 20,iconCls: 'save', hidden: false, handler: this.addFormPlBhClick, scope: this},  
                      {text: '确定', width: 20,iconCls: 'save', hidden: false, handler: this.addFormBhClick, scope: this},  
                      {text: '取消', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
    },
     addFormBhClick: function() {
         if(this.getForm().isValid()) {
      	this.getForm().submit({
              waitMsg: '正在提交数据...',
              url: 'rejecCon', 
              method: 'get',
              success: function() { 
              	Ext.MessageBox.alert("系统提示:", BLANKSTR + "驳回成功!" + BLANKSTR);
              	txGlpzGrid.bxControlRejecWindow.hide();
              	txGlpzGrid.bxControlLookWindow.hide();
              	txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
              },
              failure: function() {
              	Ext.MessageBox.alert("系统提示:", BLANKSTR + "驳回失败!" + BLANKSTR); 
              }
      	});
      }
  },
  addFormPlBhClick: function() {
      if(this.getForm().isValid()) {
   	this.getForm().submit({
           waitMsg: '正在提交数据...',
           url: 'rejecChecBox', 
           method: 'get',
           success: function() { 
           	Ext.MessageBox.alert("系统提示:", BLANKSTR + "驳回成功!" + BLANKSTR);
           	txGlpzGrid.bxControlPlRejecWindow.hide();
           	txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
           },
           failure: function() {
           	Ext.MessageBox.alert("系统提示:", BLANKSTR + "驳回失败!" + BLANKSTR); 
           }
   	});
   }
},
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});

/***************************************BxControlLookWindow组件**************************************************/
BxControlLookWindow = Ext.extend(Ext.Window, {
	lookForm : null,
	constructor: function() {
		this.lookForm = new LookForm();
		BxControlLookWindow.superclass.constructor.call(this, {
			title: "查看报修审核信息",
			 width: 900,
	            anchor: '100%',
	           // autoHeight:true,
	            height:350,
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

/***************************************bxControlRejecWindow组件**************************************************/
BxControlRejecWindow = Ext.extend(Ext.Window,{
	rejecForm : null,
    constructor: function(gird) {
        this.rejecForm = new RejecForm();
        this.rejecForm.buttons[0].hide();   //隐藏添加按钮
    	this.rejecForm.buttons[1].show();   //显示修改按钮
    	BxControlRejecWindow.superclass.constructor.call(this, {
            title: "报修驳回",
            width: 800,
            anchor: '100%',
           // autoHeight:true,
            height:260,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.rejecForm]
        });
    }
});

/***************************************bxControlPlRejecWindow组件**************************************************/
BxControlPlRejecWindow = Ext.extend(Ext.Window,{
	rejecForm : null,
    constructor: function(gird) {
        this.rejecForm = new RejecForm();
        this.rejecForm.buttons[0].show();   //隐藏添加按钮
    	this.rejecForm.buttons[1].hide();   //显示修改按钮
    	BxControlPlRejecWindow.superclass.constructor.call(this, {
            title: "报修驳回",
            width: 800,
            anchor: '100%',
           // autoHeight:true,
            height:260,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.rejecForm]
        });
    }
});

/***************************************bxControlPlPdWindow组件**************************************************/
BxControlPlPdWindow = Ext.extend(Ext.Window,{
	sendForm : null,
    constructor: function(gird) {
        this.sendForm = new SendForm();
        BxControlPlPdWindow.superclass.constructor.call(this, {
            title: "审核并派单",
            width: 800,
            anchor: '100%',
           // autoHeight:true,
            height:150,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.sendForm]
        });
    }
});

/****************TxGlpzGrid***********************/
TxGlpzGrid = Ext.extend(UxGrid,{
		pageSizeCombo: PAGESIZE,
		vtbar:null,				//面板顶部的工具条	
		vbbar:null,				//面板底部的工具条
	    store:null,
	    module_name:null,
	    type_name:null,
	    constructor: function(height, width){
	    	var thiz = this;
	    	this.store = new Ext.data.Store({          //Grid Store
	    		proxy: new Ext.data.HttpProxy({url: ENTITY_URL_LIST,method:'POST'}),
				reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
			            {name:'id'},
			            {name:'bxbh'},
			            {name:'xgh'},
			            {name:'sqrxm'},
			            {name:'lxhm'},
			            {name:'ly'},
			            {name:'lh'},
			            {name:'wxdl'},
			            {name:'sbztmc'},
			            {name:'nr'},
			            {name:'fw'},
			            {name:'pj'},
			            {name:'yysj'},
			            {name:'zt'}
						 ])
	        });
	    	this.vbbar= this.createPagingToolbar(PAGESIZE);
	    	this.vtbar = new Ext.Toolbar({
				items: [
				       {xtype:'label',text:'关键字'} ,{xtype:'textfield',id:'bm',
		   				    emptyText:'报修编号/申请人/联系号码', 
		               	    },
		               	 '-',{xtype:'label',text:'维修大类'},{xtype:'combo',id:'wxdl',
					    	   editable: false,
					    	   store: new Ext.data.Store({
					                proxy: new Ext.data.HttpProxy({url: 'getWxdl', method: 'POST'}),
					                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'ztmc'},{name:'id'}])),
					                autoLoad:true
					            }),
		                       displayField: 'ztmc', //显示文本字段
		                       valueField: 'id',//value值字段id
		                       mode: 'local',
		                       blankText:'请选择....',  
		                       emptyText:'请选择....', 
		                       triggerAction: 'all',
		                       selectOnFocus: true,
		                       typeAhead: true,
				                listeners:{  
				                	select  : function(combo,record,opts){
//				                		ztStore.proxy= new Ext.data.HttpProxy({url: PATHNAME+""+'/bx/bxgl/getBxzt?wxdl=' + combo.value});   
				                		ztStore.load({params:{wxdl:combo.value}}); 
				                     	//Ext.getCmp('bxzt').setValue('');
//				                		bxztCombo.reset();
//				                        var data ={ "wxdl": "", "ztmc": "全部"};      
//				                        var rs = [new Ext.data.Record(data)];      
//				                        store.insert(0,rs);
				                        
				                    }  
				                }
				       		},
				       	 '-',{xtype:'label',text:'报修主题'},{ xtype:'combo',id:'bxzt',
					    	   editable: false,
					    	   store: ztStore,
		                       displayField: 'ztmc', //显示文本字段
		                       valueField: 'ztmc',//value值字段id
		                       mode: 'local',
		                       blankText:'请选择....',  
		                       emptyText:'请选择....', 
		                       triggerAction: 'all',
		                       selectOnFocus: true,
		                       typeAhead: true
				       	 },
				       		
				       	 '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
						    	//	var code = Ext.getCmp('code').getValue();
						    	//	var zt = Ext.getCmp('zt').getValue();
						       			var params = {};
						       			params['bxzt']=Ext.getCmp('bxzt').getValue();
						       			params['wxdl']=Ext.getCmp('wxdl').getRawValue();
						       			params['bm']=Ext.getCmp('bm').getValue();
						       			txGlpzGrid.vtbar.items.each(function(item,index,length){ 
					       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
					       						if (item.getXType() == 'datefield') {
					       							params[item.getId()] = item.getValue().format(item.format);
					       						} else {
					       							params[item.getId()] = item.getValue();
					       						}
					       					}
										});
				    	   				txGlpzGrid.store.baseParams= params;
				    	   				txGlpzGrid.store.load({params:{start:0,limit:PAGESIZE}});
				    	   			}
				       			},
				       			'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
									txGlpzGrid.vtbar.items.each(function(item,index,length){   
										if((""+item.getXType()).indexOf("field") != -1) {
											item.setValue('');
										}
									  });
									Ext.getCmp('bxzt').setValue('');
									Ext.getCmp('wxdl').setValue('');
									Ext.getCmp('bm').setValue('');
			    	   			  }
				       			},
						       	 '-',{xtype:'button',text:'批量驳回',iconCls:'delete',handler:this.plbhClick,scope:this},
						      	 '-',{xtype:'button',text:'批量审核',iconCls:'save',handler: this.plpdClick,scope:this }
				]
			});
	    	this.bxControlPlpdWindow = new BxControlPlPdWindow();
	    	this.bxControlPlRejecWindow = new BxControlPlRejecWindow();
		    this.bxControlRejecWindow = new BxControlRejecWindow();
		    this.bxControlLookWindow = new BxControlLookWindow();
	        var sm = new Ext.grid.CheckboxSelectionModel(); 
	        TxGlpzGrid.superclass.constructor.call(this, {
	        	region:'center',
	        	title: '报修审核',
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
	                      		            {header:'序号',dataIndex:'id',width:100,sortable:true,hidden:true},
	                    		            {header:'申请单号',dataIndex:'bxbh',width:190,sortable:true,hidden:false},
	                    		            {header:'申请人姓名',dataIndex:'sqrxm',width:90,sortable:true,hidden:false},
	                    		            {header:'联系号码',dataIndex:'lxhm',width:110,sortable:true,hidden:false},
	                    		            {header:'楼宇',dataIndex:'ly',width:160,sortable:true,hidden:false},
	                    		            {header:'维修大类',dataIndex:'wxdl',width:80,sortable:true,hidden:false},
	                    		            {header:'申报主题名称',dataIndex:'sbztmc',width:90,sortable:true,hidden:false},
	                    		            {header:'项目',dataIndex:'nr',width:200,sortable:true,hidden:false,
	                    		            	renderer: function(value,metadata,record,rowIndex,colIndex,store) { 
	                    		            		metadata.attr = 'ext:qtip="' + record.get("nr") + '"';
	                    		            		return record.get("nr");
                    		                 }	
	                    		            },
	                    		            {header:'服务',dataIndex:'fw',width:50,sortable:true,hidden:false},
	                    		           // {header:'评价',dataIndex:'pj',width:80,sortable:true,hidden:false},
	                    		            {header:'预约时间',dataIndex:'yysj',width:100,sortable:true,hidden:false},
	                    		            {header:'状态',dataIndex:'zt',width:90,sortable:true,hidden:false},
	                    		            {header: '管理操作',width: 130, dataIndex: '', align:"center",  sortable: true,hidden: false,
	                      		            	renderer:function(value, cellmeta, record){
	                      	                    	var id = record.data.id;
	                      	                        return  "<a style='color:blue;cursor:pointer;' href='javascript:;' onclick='txGlpzGrid.onLook()'>查看</a>";
	                      	                    }
	                      	            	}
	                    		        /*    {header:'备注',dataIndex:'bz',width:100,sortable:true,hidden:false},*/
	                    		           ])
	        });
		                    		TxGlpzGrid.superclass.constructor.call(this,{
		                    			region: 'center',
		                    			frame: true,
		                    			height: height,
		                                viewConfig: {
		                                    forceFit: false
		                                },
		                                loadMask: new Ext.LoadMask(document.body,{ 
		                    				msg: '正在载入数据，请稍后...',
		                    				store   : this.store
		                    			}),
		            tbar: this.vtbar,
		            bbar: this.vbbar,
		            ds: this.store
		        });
		    },
			onLook: function() {
		    	var records=this.getSelectionModel().getSelections();
		   		if(records.length > 0) {
		   			if(records.length == 1){
		  				vrecord = records[0];
		  				var win = this.bxControlLookWindow;
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
		    plbhClick: function() {
		    	var records=this.getSelectionModel().getSelections();
		    	var idList=[];
		   		if(records.length>0) {
			       	for(var i=0;i<records.length;i++){
			       		idList.push(records[i].get('id'));
		    	 	}
			    	Ext.Msg.confirm("提醒信息", "确定要驳回这 " + records.length + " 条信息吗",function(btn){
						if (btn == 'yes') {
				        	var win = txGlpzGrid.bxControlPlRejecWindow;
				        	win.rejecForm.getForm().reset();
				        	win.rejecForm.idHidden.setValue(idList);
				        	win.show();	       
				       	}
			   		})
		   		}else{
			   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);    
		    	}
		    },
		 
		    plpdClick: function() {
		    	var records=this.getSelectionModel().getSelections();
		    	var idList=[];
		   		if(records.length>0) {
			       	for(var i=0;i<records.length;i++){
			       		idList.push(records[i].get('id'));
		    	 	}
			    	Ext.Msg.confirm("提醒信息", "确定要派单这 " + records.length + " 条信息吗",function(btn){
						if (btn == 'yes') {
				        	var win = txGlpzGrid.bxControlPlpdWindow;
				        	win.sendForm.getForm().reset();
				        	win.sendForm.idHidden.setValue(idList);
				        	win.show();	       
				       	}
			   		})
		   		}else{
			   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);    
		    	}
		    }
	});

/*************onReady组件渲染处理***********************/
Ext.onReady(function(){
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';        //提示方式"side"
    
    txGlpzGrid = new TxGlpzGrid(Ext.getBody().getViewSize().height);
    txGlpzGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        txGlpzGrid   
		]
	});
});
