var ENTITY_URL_LIST = "pager";
var PAGESIZE=20;
/* 
 * 报修受理
 * @author chenhui
 * @date 2015-08-10
 */
var ztStore=new Ext.data.Store({
    proxy: new Ext.data.HttpProxy({url:'getBxzt',method: 'POST'}),
    reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'ztmc'},{name:'ztmc'}])),
    autoLoad:true
});

/***************************************DictTypeForm组件**************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
    constructor: function() {
    	
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
        
        this.wxdl = this.createTextField('维修大类','wxdl','95%','',null,100,'长度超过不能100');
        this.wxdl.allowBlank = true;
        this.wxdl.readOnly = true;
        
		this.sbztmc = this.createTextField('主题','sbztmc','95%','',null,100,'长度超过不能100');
	    this.sbztmc.allowBlank = true;
	    this.sbztmc.readOnly = true;

	  /*  this.nr = this.createTextArea('内容','nr','100%','97.5%',null,100,'长度超过不能100');
	    this.nr.allowBlank = true;
	    this.nr.readOnly = true;*/
	    
	    this.nr = new Ext.form.TextArea({
            fieldLabel: '内容',
            name: 'nr',
            readOnly: false,
            anchor: '97.5%',
            height:65,
        });
	    this.nr.allowBlank = true;
	    this.nr.readOnly = true;
	    
	    this.fw = this.createTextField('服务','fw','95%','',null,100,'长度超过不能100');
	    this.fw.allowBlank = true;
	    this.fw.readOnly = true;

	   /* this.pj = this.createTextArea('评价','pj','100%','97.5%',null,100,'长度超过不能100');
	    this.pj.allowBlank = true;
	    this.pj.readOnly = true;*/
	    
	    this.pj = new Ext.form.TextArea({
            fieldLabel: '评价',
            name: 'pj',
            readOnly: false,
            anchor: '97.5%',
            height:65,
        });
	    this.nr.allowBlank = true;
	    this.nr.readOnly = true;
	    
	    this.yysj = this.createTextField('预约时间','yysj','95%','',null,100,'长度超过不能100');
	    this.yysj.allowBlank = true;
	    this.yysj.readOnly = true;
	    
	    this.zt = this.createTextField('状态','zt','95%','',null,100,'长度超过不能100');
	    this.zt.allowBlank = true;
	    this.zt.readOnly = true;
	    
	    this.sprxm = this.createTextField('审核人','sprxm','95%','',null,100,'长度超过不能100');
	    this.sprxm.allowBlank = true;
	    this.sprxm.readOnly = true;
	    
	    this.spsj = this.createTextField('审核时间','spsj','95%','',null,100,'长度超过不能100');
	    this.spsj.allowBlank = true;
	    this.spsj.readOnly = true;
	    
	    this.pdsj = this.createTextField('派单时间','pdsj','95%','',null,100,'长度超过不能100');
	    this.pdsj.allowBlank = true;
	    this.pdsj.readOnly = true;
	    
	    this.wxrxm = this.createTextField('派出人','wxrxm','95%','',null,100,'长度超过不能100');
	    this.wxrxm.allowBlank = true;
	    this.wxrxm.readOnly = true;
	    
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
	                        this.ly
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
	                        this.fw
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
	                           this.zt
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
	                        this.sprxm
	                    ]  
	                }), 
	                new Ext.Panel({  
	                    columnWidth:.5,  
	                    layout:'form',  
	                    border:false,  
	                    labelWidth:80,  
	                    labelAlign:'right',  
	                    items:[  
	                        this.spsj
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
	                    labelAlign:'right',  
	                    items:[  
	                        this.wxrxm
	                    ]  
	                }), 
	                new Ext.Panel({  
	                    columnWidth:.5,  
	                    layout:'form',  
	                    border:false,  
	                    labelWidth:80,  
	                    labelAlign:'right',  
	                    items:[  
	                           this.pdsj
		                    ]   
	                })
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
	                        this.pj
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
	            	pnRow7,
	            	pnRow8,
	            	pnRow9,
	            	pnRow6
	            	//pnRow7
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
			title: "查看报修受理信息",
			 width: 900,
	            anchor: '100%',
	           // autoHeight:true,
	            height:415,
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
	    		proxy: new Ext.data.HttpProxy({url: ENTITY_URL_LIST,method:'POST'}),
				reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
			            {name:'id'},
			            {name:'bxbh'},
			            {name:'xgh'},
			            {name:'sqrxm'},
			            {name:'lxhm'},
			            {name:'ly'},
			            {name:'wxdl'},
			            {name:'sbztmc'},
			            {name:'nr'},
			            {name:'fw'},
			            {name:'pj'},
			            {name:'yysj'},
			            {name:'zt'},
			            {name:'sprxm'},
			            {name:'spsj'},
			            {name:'pdsj'},
			            {name:'ztbh'},
			            {name:'wxrxm'}
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
//					                		ztStore.proxy= new Ext.data.HttpProxy({url: PATHNAME+""+'/bx/bxgl/getBxzt?wxdl=' + combo.value});   
					                		ztStore.load({params:{wxdl:combo.value}}); 
					                     	//Ext.getCmp('bxzt').setValue('');
//					                		bxztCombo.reset();
//					                        var data ={ "wxdl": "", "ztmc": "全部"};      
//					                        var rs = [new Ext.data.Record(data)];      
//					                        store.insert(0,rs);
					                        
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
				       			},'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
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
				]
			});
	        this.bxControlLookWindow = new BxControlLookWindow();
	        var sm = new Ext.grid.CheckboxSelectionModel(); 
	        TxGlpzGrid.superclass.constructor.call(this, {
	        	region:'center',
	        	title: '报修受理',
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
	                      		            {header:'序号',dataIndex:'id',width:50,sortable:true,hidden:true},
	                    		            {header:'申请单号',dataIndex:'bxbh',width:200,align:"center",sortable:true,hidden:false},
	                    		            {header:'申请人姓名',dataIndex:'sqrxm',width:80,align:"center",sortable:true,hidden:false},
	                    		            {header:'联系号码',dataIndex:'lxhm',width:110,align:"center",sortable:true,hidden:false},
	                    		            {header:'楼宇',dataIndex:'ly',width:160,align:"center",sortable:true,hidden:false},
	                    		            {header:'维修大类',dataIndex:'wxdl',width:80,align:"center",sortable:true,hidden:false},
	                    		            {header:'申报主题名称',dataIndex:'sbztmc',align:"center",width:90,sortable:true,hidden:false},
	                    		            {header:'项目',dataIndex:'nr',width:180,align:"center",sortable:true,hidden:false,
	                    		            	renderer: function(value,metadata,record,rowIndex,colIndex,store) { 
	                    		            		metadata.attr = 'ext:qtip="' + record.get("nr") + '"';
	                    		            		return record.get("nr");
                    		                   }	
	                    		            },
	                    		            {header:'服务',dataIndex:'fw',width:60,align:"center",sortable:true,hidden:false},
	                    		            {header:'评价',dataIndex:'pj',width:80,align:"center",sortable:true,hidden:false,
	                    		            	renderer: function(value,metadata,record,rowIndex,colIndex,store) { 
	                    		            		var ztbh = record.data.ztbh;
	                    		            		if(ztbh=='5'){
	                    		            			metadata.attr = 'ext:qtip="用户未反馈"';
	                    		            			return "用户未反馈";
	                    		            		}else{
	                    		            			metadata.attr = 'ext:qtip="' + record.get("pj") + '"';
	                    		            			return record.get("pj");
                    		                      }	
	                    		            	}
	                    		            },
	                    		            {header:'预约时间',dataIndex:'yysj',width:100,align:"center",sortable:true,hidden:false},
	                    		            {header:'状态',dataIndex:'ztbh',width:60,align:"center",sortable:true,hidden:false,
	                    		             	renderer:function(value){
	                    		                    if(value == '1') {
	                    		                        return "<span style='color:#00FF1E;'>申请中</span>";
	                    		                    }else if(value == '2') {
	                    		                        return "<span style='color:#BB3D00;'>受理中</span>";
	                    		                    }else if(value == '3') {
	                    		                        return "<span style='color:red;'>申请驳回</span>";
	                    		                    }else if(value == '4') {
	                    		                        return "<span style='color:#00CCFF;'>处理中</span>";
	                    		                    }else if(value == '5') {
	                    		                        return "<span style='color:#DC143C;'>完成</span>";
	                    		                    }else if(value == '6') {
	                    		                        return "<span style='color:#FFAE00;'>回访</span>";
	                    		                    }else if(value == '7') {
	                    		                        return "<span style='color:red;'>不满意</span>";
	                    		                    }else if(value == '8') {
	                    		                        return "<span style='color:opacity:0.8;'>结束</span>";
	                    		                    }else if(value == '9') {
	                    		                        return "<span style='color:#FF0000;'>用户未反馈</span>";
	                    		                    }else {
	                    		                        return value;
	                    		                    }
	                    	            		}	
	                    		            },
	                    		            {header:'审核人',dataIndex:'sprxm',width:80, align:"center",sortable:true,hidden:true},
	                    		            {header:'审核时间',dataIndex:'spsj',width:80, align:"center",sortable:true,hidden:true},
	                    		            {header:'派单时间',dataIndex:'pdsj',width:150, align:"center",sortable:true,hidden:true},
	                    		            {header:'派出人',dataIndex:'wxrxm',width:50,align:"center",sortable:true,hidden:true},
	                    		            {header:'管理操作', width: 100, dataIndex: 'ztbh', align:"center",sortable: true,
	                      		            	renderer:function(value,metadata,record,rowIndex){
	                      	                    	var id = record.data.id;
	                      	                    	if(value == "4"){
	                      	                    	return  "<a style='color:blue;cursor:pointer;' href='javascript:;' onclick='txGlpzGrid.onLook()'>查看</a>"
	                      	                    	+"&nbsp;&nbsp;&nbsp;" +
	                      	                    	 "<a style='color:#ADAD5A;cursor:pointer;' href='javascript:;' onclick='hf("+record.data.id+")'>回访</a>";
	                      	                    }else if(value=="2"||value=="7"){
	                      		            		return  "<a style='color:blue;cursor:pointer;' href='javascript:;' onclick='txGlpzGrid.onLook()'>查看</a>"
	                      	                    	+"&nbsp;&nbsp;&nbsp;" +
	                      	                    	 "<a style='color:#ff9a00;cursor:pointer;' href='javascript:;' onclick='dyClick("+record.data.id+")'>打印</a>";
	                      		            	}else if(value=="5"){
	                      		            		return "<a style='color:blue;cursor:pointer;' href='javascript:;' onclick='txGlpzGrid.onLook()'>查看</a>"
	                      		            		+"&nbsp;&nbsp;&nbsp;" +
	                      	                    	 "<a style='color:#FF00FF;cursor:pointer;' href='javascript:;' onclick='js("+record.data.id+")'>结束</a>";
	                      		            	}
	                      	            	  }
	                    		            }
	                    		           /*{header:'备注',dataIndex:'bz',width:100,sortable:true,hidden:false},*/
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
