var ENTITY_URL_LIST = "pager";
var ENTITY_URL_UPDATE = "pljs";
var PAGESIZE=20;
/*
 * 报修管理
 * @author oufeng
 * @date 2015-08-10
 */

/***************************************DictTypeForm组件**************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
    constructor: function() {
    	this.bxbh = this.createTextField('报修编号','bxbh','95%','',null,100,'长度超过不能100');
        this.bxbh.allowBlank = true;
        this.bxbh.readOnly = true;
        
        this.xgh = this.createTextField('学工号','xgh','95%','',null,100,'长度超过不能100');
        this.xgh.allowBlank = true;
        this.xgh.readOnly = true;
        
        this.lxhm = this.createTextField('联系号码','lxhm','95%','',null,100,'长度超过不能100');
        this.lxhm.allowBlank = true;
        this.lxhm.readOnly = true;

        this.sqrxm = this.createTextField('申请人','sqrxm','95%','',null,100,'长度超过不能100');
        this.sqrxm.allowBlank = true;
        this.sqrxm.readOnly = true;
        
        this.wxdl = this.createTextField('维修大类','wxdl','95%','',null,100,'长度超过不能100');
        this.wxdl.allowBlank = true;
        this.wxdl.readOnly = true;
		
        this.ly = this.createTextField('楼宇','ly','95%','',null,100,'长度超过不能100');
        this.ly.allowBlank = true;
        this.ly.readOnly = true;
		
		this.lh = this.createTextField('楼号','lh','95%','',null,100,'长度超过不能100');
	    this.lh.allowBlank = true;
	    this.lh.readOnly = true;
	    
		this.dz = this.createTextField('地址','dz','95%','',null,100,'长度超过不能100');
	    this.dz.allowBlank = true;
	    this.dz.readOnly = true;
	    
		this.sbztmc = this.createTextField('主题','sbztmc','95%','',null,100,'长度超过不能100');
	    this.sbztmc.allowBlank = true;
	    this.sbztmc.readOnly = true;
	    
	    
	    this.fw = this.createTextField('服务','fw','95%','',null,100,'长度超过不能100');
	    this.fw.allowBlank = true;
	    this.fw.readOnly = true;
	    
	    this.yysj = this.createTextField('预约时间','yysj','95%','',null,100,'长度超过不能100');
	    this.yysj.allowBlank = true;
	    this.yysj.readOnly = true;
	    
	    this.zt = this.createTextField('状态','zt','95%','',null,100,'长度超过不能100');
	    this.zt.allowBlank = true;
	    this.zt.readOnly = true;
        
	    this.nr = this.createTextArea('内容','nr','100%','97.5%',null,100,'长度超过不能100');
	    this.nr.allowBlank = true;
	    this.nr.readOnly = true;
		
	    this.pj = this.createTextArea('评价','pj','100%','97.5%',null,100,'长度超过不能100');
	    this.pj.allowBlank = true;
	    this.pj.readOnly = true;
	    
	    this.bz = this.createTextArea('备注','bz','100%','95%',null,100,'长度超过不能100');
	    this.bz.allowBlank = true;
	    this.bz.readOnly = true;
	    
	    var pnRow7=new Ext.Panel({  
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
						    fieldLabel:'备注',
						    name:'bz',  
						    border:false, 
						    layout:'form', 
						    labelWidth:40,  
						    width:650,
						    height:180,
						   // labelAlign:'right'
						} 
				    ]  
				})
            ]  
        });
	    
	    
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
	                        this.ly
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
	                        this.lh
	                    ]  
	                }),  
	                new Ext.Panel({  
	                    columnWidth:.5,  
	                    layout:'form',  
	                    border:false,  
	                    labelWidth:80,  
	                    labelAlign:'right',  
	                    items:[  
	                        this.dz
	                    ]  
	                })
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
	                        this.fw
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
	        
	        var pnRow8=new Ext.Panel({  
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
	                        this.pj
	                    ]  
	                }),  
	            ]  
	        });
	        
	        var pnRow9=new Ext.Panel({  
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
	                }),  
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
            	pnRow6,
            	pnRow9,
            	pnRow8,
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


/***************************************BxControlLookWindow组件**************************************************/
BxControlLookWindow = Ext.extend(Ext.Window, {
	lookForm : null,
	constructor: function() {
		this.lookForm = new LookForm();
		BxControlLookWindow.superclass.constructor.call(this, {
			title: "查看报修管理信息",
			width: 800,
			//autoHeight:true,
			height:420,
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
	    		proxy: new Ext.data.HttpProxy({url:ENTITY_URL_LIST,method:'POST'}),
				reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
			            {name:'id'},
			            {name:'bxbh'},
			            {name:'xgh'},
			            {name:'lxhm'},
			            {name:'sqrxm'},
			            {name:'wxdl'},
			            {name:'ly'},
			            {name:'lh'},
			            {name:'fw'},
			            {name:'yysj'},
			          //  {name:'sbztbh'},
			            {name:'sbztmc'},
			            {name:'dz'},
			            {name:'nr'},
			            {name:'zt'},
			            {name:'pj'},
			            {name:'bz'}
						 ])
	        });
	    	this.vbbar= this.createPagingToolbar(PAGESIZE);
	    	this.vtbar = new Ext.Toolbar({
				items: [
				       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this},
				       {xtype:'label',text:'关键字'} ,{xtype:'textfield',id:'code',
		   				    emptyText:'请输入学号/号码/姓名....', 
		               	    },
						'-',{xtype:'label',text:'状态'},{xtype:'combo',id:'zt',
					    	   editable: false,
					    	   store: new Ext.data.Store({
					                proxy: new Ext.data.HttpProxy({url: 'getZt', method: 'POST'}),
					                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'ztmc'},{name:'zt'}])),
					                autoLoad:true,
					                listeners:{  
					                    load : function(store, records, options ){      
					                        var data ={ "zt": "", "ztmc": "全部"};      
					                        var rs = [new Ext.data.Record(data)];      
					                        store.insert(0,rs);  
					                    }  
					                }
					            }),
		                       displayField: 'ztmc', //显示文本字段
		                       valueField: 'zt',//value值字段id
		                       mode: 'local',
		                       blankText:'请选择....',  
		                       emptyText:'请选择....', 
		                       triggerAction: 'all',
		                       selectOnFocus: true,
		                       typeAhead: true
				       		},
				       '-',{xtype:'label',text:'时间起'},{xtype:'datefield',format:'Y-m-d',id:'starttime',editable : false,value:new Date().format('Y-m-d')},
				       '-',{xtype:'label',text:'时间止'},{xtype:'datefield',format:'Y-m-d',id:'endtime',editable : false,value:new Date().format('Y-m-d')},
				       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
				    	//	var code = Ext.getCmp('code').getValue();
				    	//	var zt = Ext.getCmp('zt').getValue();
				       			var params = {};
				       			params['zt']=Ext.getCmp('zt').getValue();
				       			params['code']=Ext.getCmp('code').getValue();
				       			params['starttime']=Ext.getCmp('starttime').getValue();
				       			params['endtime']=Ext.getCmp('endtime').getValue();
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
							Ext.getCmp('zt').setValue('');
							Ext.getCmp('code').setValue('');
	    	   			}
		       			},
		       			'-',{xtype:'button',text:'结束',iconCls:'accept',handler:function(){
		       				if(Ext.getCmp('starttime').getValue()=='' || Ext.getCmp('starttime').getValue()==null){
		       					alert("请选择开始时间!");
		       					return false;
		       				}
		       				if(Ext.getCmp('endtime').getValue()=='' || Ext.getCmp('endtime').getValue()==null){
		       					alert("请选择结束时间!");
		       					return false;
		       				}
		       				if(Ext.getCmp('starttime').getValue()>Ext.getCmp('endtime').getValue()){
		       					alert("开始时间不能大于结束时间!");
		       					return false;
		       				}
		       				var starttime=Ext.getCmp('starttime').getValue().format('Y-m-d');
		       				var endtime=Ext.getCmp('endtime').getValue().format('Y-m-d');
		       				Ext.Msg.confirm('系统提示：',"您确定结束"+starttime+"到"+endtime+"这段时间的报修吗？",function(btn){
		       	    			if(btn == 'yes'){
		       	    				Ext.Ajax.request({
		       	    					url: ENTITY_URL_UPDATE,
		       	    					method: 'POST',
		       	    					params: {
		       	    						starttime:starttime,
		       	    						endtime:endtime
		       	    					},
		       	    					success: function(form,action){
		       	    						Ext.Msg.alert('系统提示',"操作成功！");
		       	    						txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
		       	    					},
		       	    					failure: function(form,action){
		       								Ext.MessageBox.alert("系统提示：","操作失败");
		       							}
		       	    				});
		       	    			}
		       	    		});
							
	    	   			     }
		       			}
				]
			});
	      this.bxControlLookWindow = new BxControlLookWindow();
	        var sm = new Ext.grid.CheckboxSelectionModel(); 
	        TxGlpzGrid.superclass.constructor.call(this, {
	        	region:'center',
	        	title: '报修管理',
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
	                      		            {header:'主键',dataIndex:'id',width:100,sortable:true,hidden:true},
	                    		            {header: '操作', width: 100, dataIndex: '', align:"center",  sortable: true,hidden: false,
	                    		                renderer:function(value, cellmeta, record){
	                    		        			   return "<a style='color:blue;cursor:pointer;' href='javascript:;' onclick='txGlpzGrid.onLook()'>查看</a>" 
	                    		        			   		 +"&nbsp;&nbsp;&nbsp;" +
	                    		        			   		"<a style='color:red;cursor:pointer;' href='javascript:;' onclick='txGlpzGrid.onDeleteClick()'>删除</a>";
	                    		                }
	                    					},
	                    		            {header:'报修编号',dataIndex:'bxbh',width:100,sortable:true,hidden:false},
	                    		            {header:'学号或工号',dataIndex:'xgh',width:100,sortable:true,hidden:false},
	                    		            {header:'联系号码',dataIndex:'lxhm',width:100,sortable:true,hidden:false},
	                    		            {header:'申请人姓名',dataIndex:'sqrxm',width:100,sortable:true,hidden:false},
	                    		            {header:'维修大类',dataIndex:'wxdl',width:100,sortable:true,hidden:false},
	                    		            {header:'楼宇',dataIndex:'ly',width:130,sortable:true,hidden:false},
	                    		            {header:'楼号',dataIndex:'lh',width:80,sortable:true,hidden:false},
	                    		            {header:'服务',dataIndex:'fw',width:80,sortable:true,hidden:false},
	                    		            {header:'预约时间',dataIndex:'yysj',width:150,sortable:true,hidden:false},
	                    		           // {header:'申报主题编号',dataIndex:'sbztbh',width:100,sortable:true,hidden:false},
	                    		            {header:'申报主题名称',dataIndex:'sbztmc',width:100,sortable:true,hidden:false},
	                    		            {header:'地址',dataIndex:'dz',width:100,sortable:true,hidden:false},
	                    		            {header:'内容',dataIndex:'nr',width:100,sortable:true,hidden:false},
	                    		            {header:'状态',dataIndex:'zt',width:50,sortable:true,hidden:false},
	                    		            {header:'评价',dataIndex:'pj',width:100,sortable:true,hidden:false}
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
    onDeleteClick: function(){
    	var records = this.getSelectionModel().getSelections();
    	var ids = {};
		var valueStr = [];
    	for(var i=0;i<records.length;i++){
    		valueStr.push(records[i].get('id'));
    	}
		ids['ids'] = valueStr;
		
    	if(records.length>0){
    		Ext.Msg.confirm('系统提示：',"确定删除这"+records.length+"条信息吗？",function(btn){
    			if(btn == 'yes'){
    				Ext.Ajax.request({
    					url: ENTITY_URL_DELETE,
    					method: 'POST',
    					params: ids,
    					success: function(form,action){
    						Ext.Msg.alert('系统提示','删除成功！');
    						txGlpzGrid.vbbar.doLoad(txGlpzGrid.vbbar.cursor);
    					},
    					failure: function(form,action){
							Ext.MessageBox.alert("系统提示：","删除成功");
						}
    				});
    			}
    		});
    	}else{
    		Ext.Msg.alert('系统提示','请选择一条记录！');
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
