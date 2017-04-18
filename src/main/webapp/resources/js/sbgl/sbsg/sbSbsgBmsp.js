var USER_GRID_STORE_URL = 'getSbsgBmspList';
var USER_GRID_STORE_URL_CHECK='getSbsgCheck';
var PAGESIZE=20;
var yearll=new Ext.form.TextField();
var amtd = new Ext.form.TextField();

//添加页面
var ACT_DEAL_WINDOW;
/*************************************** ConstructionForm组件 **************************************************/
ConstructionForm = Ext.extend(Ext.ux.Form, {
    constructor: function() {
    	this.idHidden = this.createHidden('id','id');
       //	this.spyj = this.createTextArea('<font color="red">*</font>审批意见:', 'spyj', '95%','90%',5);
    	  this.spyj = new Ext.form.TextArea({
              fieldLabel: '<font color="red">*</font>审批意见',
              name: 'spyj',
              readOnly: false,
              anchor: '85%',
              height:100,
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
        ConstructionForm.superclass.constructor.call(this, {
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
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
                      {text: '取消', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
 	createDateTimeField: function(fieldLabel, name, format, anchor) {
    	var df = new Ext.ux.form.DateTimeField({
			fieldLabel: fieldLabel,
			name: name,
			format: format,
			anchor: anchor,
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
		return df;
    },
    
     addFormClick: function() {
            if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'saveSpyjBm', 
                 method: 'GET',
                 success: function() { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	constructionGrid.constructionInsertWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function() {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR); 
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'update', 
                 method: 'POST',
                 success: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	constructionGrid.constructionUpdateWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
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

ConstructionForm1 = Ext.extend(Ext.ux.Form, {
    constructor: function() {
    	this.idHidden = this.createHidden('id','id');
    	//this.spyj = this.createTextArea('<font color="red">*</font>驳回理由:', 'spyj', '95%','90%',5);
    	 this.spyj = new Ext.form.TextArea({
             fieldLabel: '<font color="red">*</font>驳回理由',
             name: 'spyj',
             readOnly: false,
             anchor: '85%',
             height:100,
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
        ConstructionForm1.superclass.constructor.call(this, {
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
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormBhClick, scope: this},  
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormBhClick, scope: this},  
                      {text: '取消', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
 	createDateTimeField: function(fieldLabel, name, format, anchor) {
    	var df = new Ext.ux.form.DateTimeField({
			fieldLabel: fieldLabel,
			name: name,
			format: format,
			anchor: anchor,
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
		return df;
    },
     addFormBhClick: function() {
         if(this.getForm().isValid()) {
      	this.getForm().submit({
              waitMsg: '正在提交数据...',
              url: 'saveSpyjBmBh', 
              method: 'GET',
              success: function() { 
              	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
              	constructionGrid.constructionInsertWindow1.hide();
              	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
              },
              failure: function() {
              	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR); 
              }
      	});
      }
  },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});


/***************************************ActDealWindow组件**************************************************/
ActDealWindow = Ext.extend(Ext.Window,{
    constructor: function(grid) {
    	ActDealWindow.superclass.constructor.call(this, {
            width: 800,
            anchor: '100%',
            maximized :true,
            height: 400,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'close',
            html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src=""></iframe>'
        });
    }
});


/***************************************ConstructionInsertWindow组件**************************************************/
ConstructionInsertWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new ConstructionForm();
        this.constructionForm.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //显示修改按钮
        ConstructionInsertWindow.superclass.constructor.call(this, {
            title: "设备申购审批",
            width: 600,
            anchor: '100%',
            autoHeight:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.constructionForm]
        });
    }
});

ConstructionInsertWindow1 = Ext.extend(Ext.Window,{
	constructionForm1 : null,
    constructor: function(grid) {
        this.constructionForm1 = new ConstructionForm1();
        this.constructionForm1.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm1.buttons[1].hide();   //显示修改按钮
        ConstructionInsertWindow1.superclass.constructor.call(this, {
            title: "设备申购驳回",
            width: 600,
            anchor: '100%',
            autoHeight:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.constructionForm1]
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
    	ConstructionUpdateWindow.superclass.constructor.call(this, {
        	title: "修改设备申购信息",
            width: 800,
            autoHeight:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.constructionForm]
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
                       		{name:'id'},{name:'sbmc'},{name:'sbxh'},{name:'cgsl'},{name:'sblb'},{name:'sccj'},{name:'bmmc'},{name:'sybm'},
                       		{name:'xm'},{name:'sgr'},{name:'sgrq'},{name:'sgzt'},{name:'sprxm'},{name:'spyj'}
                       ])
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
             //   '-',{text:'申购',iconCls: 'add',handler:this.onAddClick,scope:this},
                '-',{text:'批准',iconCls: 'accept',handler:this.onAddClick,scope:this},
            	'-',{text:'驳回',iconCls: 'cancel',handler:this.onCancelClick,scope:this},
            	 {xtype:'textfield',id:'code',
   				    emptyText:'请输入名称或型号....', 
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
	  				{xtype:'label',text:'申请状态：'},{xtype:'combo',id:'sqzt',
		  				   editable: false,
		  				   store: new Ext.data.Store({
		  			         proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/sbgl/SbSbsg/getBmzt', method: 'POST'}),
		  			         reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'zdbm'},{name:'zdz'}])),
		  			         autoLoad:true,
		  			         listeners:{  
		  			             load : function(store, records, options ){      
		  			                 var data ={ "zdz": "", "zdbm": "全部"};      
		  			                 var rs = [new Ext.data.Record(data)];      
		  			                 store.insert(0,rs);  
		  			             }  
			  			         }
			  			     }),
		  			    displayField: 'zdbm', //显示文本字段
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
   						var sblb = Ext.getCmp('sblb').getValue();
   						var sqzt = Ext.getCmp('sqzt').getValue();
   						var params = {};
		       			params['sblb']=Ext.getCmp('sblb').getValue();
		       			params['sqzt']=Ext.getCmp('sqzt').getValue();
		       			constructionGrid.vtbar.items.each(function(item,index,length){ 
	       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
	       						if (item.getXType() == 'datefield') {
	       							params[item.getId()] = item.getValue().format(item.format);
	       						} else {
	       							params[item.getId()] = item.getValue();
	       						}
	       					}
						});
		       			constructionGrid.store.baseParams= {code:code,sblb:sblb,sqzt:sqzt};
		       			constructionGrid.store.load({params:{start:0,limit:PAGESIZE}});
   				    }},'-',
   				    {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
   	   				Ext.getCmp('code').setValue("");
   	   				Ext.getCmp('sblb').setValue("");
   	   			   Ext.getCmp('sqzt').setValue("");
      			  }
               },
            ]
        });	
	
        this.constructionInsertWindow = new ConstructionInsertWindow();     
        this.constructionInsertWindow1 = new ConstructionInsertWindow1(); 
        this.constructionUpdateWindow = new ConstructionUpdateWindow();
        this.dictTypeLookWindow = new DictTypeLookWindow();
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
                {header:'设备名称',dataIndex:'sbmc',width:112,align:'center',sortable: true},
            	{header:'规格型号',dataIndex:'sbxh',width:110,align:'center',sortable: true},
            	{header:'数量',dataIndex:'cgsl',width:80,align:'center',sortable: true},
            	{header:'设备类型',dataIndex:'sblb',width:120,align:'center',sortable: true,
            		renderer:function(value){
	                    if(value == 'tzsb') {
	                        return "<span>特种设备</span>";
	                    }else if(value == 'jxsb') {
	                        return "<span>机械设备</span>";
	                     }else if(value == 'jxsb') {
	                        return "<span>机械设备</span>";
	                    }else if(value == 'dlsb') {
	                        return "<span>动力设备</span>";
	                    }else if(value == 'cdsb') {
	                        return "<span>传导设备</span>";
	                    }else if(value == 'yssb') {
	                        return "<span>运输设备</span>";
	                    }else if(value == 'gjyq') {
	                        return "<span>工具仪器</span>";
	                    }else if(value == 'bgsb') {
	                        return "<span>办公设备</span>";
	                    }else if(value == 'qtsb') {
	                        return "<span>其他设备</span>";
	                    }else {
	                        return value;
	                    }
            		}	
            	},
            	{header:'制造厂商',dataIndex:'sccj',width:120,align:'center',sortable: true},
            	{header:'申请部门',dataIndex:'bmmc',width:110,align:'center',sortable: true},
            	{header:'申请部门',dataIndex:'sybm',width:110,align:'center',sortable: true,hidden:true},
            	{header:'申请人',dataIndex:'xm',width:110,align:'center',sortable: true},
            	{header:'申请人',dataIndex:'sgr',width:120,align:'center',sortable: true,hidden:true},
            	{header:'申请日期',dataIndex:'sgrq',width:110,align:'center',sortable: true},
            	{header:'申请状态',dataIndex:'sgzt',width:120,align:'center',sortable: true},
            	{header: '审批意见', width: 120, dataIndex: 'id', align:"center",  sortable: true,hidden: false,
					renderer:function(value, cellmeta, record){
						   return "<a href='#' style='cursor:pointer;' onclick='constructionGrid.onLook()'>查看</a>";
            		}
				},
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            }
        });
    },
    
    //批准点击
    onAddClick: function() {
		var records=this.getSelectionModel().getSelections();
    	var valueStr=[];
   		if(records.length>0) {
	       	for(var i=0;i<records.length;i++){
	       		valueStr.push(records[i].get('id')); 
	        	var win = this.constructionInsertWindow;
	        	win.constructionForm.getForm().reset();
	        	win.constructionForm.spyj.setValue('通过');
	        	win.constructionForm.idHidden.setValue(valueStr);
	        	win.show();	       
	        	}
   		}
   		else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    
    //驳回click
    onCancelClick: function() {
		var records=this.getSelectionModel().getSelections();
    	var valueStr=[];
   		if(records.length>0) {
	       	for(var i=0;i<records.length;i++){
	       		valueStr.push(records[i].get('id')); 
	        	var win = this.constructionInsertWindow1;
	        	win.constructionForm1.getForm().reset();
	        	win.constructionForm1.idHidden.setValue(valueStr);
	        	win.show();	       
	        	}
   		}
   		else{
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
    		    	this.lookForm = new LookForm();
    		    	var id = vrecord.data.id;
    		        //win.lookForm.getForm().loadRecord(vrecord);
    		    	winForm.store.load({params:{id:id}});
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
   		    	var win = this.constructionUpdateWindow;
   		    	//win.constructionForm.getForm().reset();
             	//win.constructionForm.product.setDisabled(true);//不允许修改帐户编码
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
				               Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除成功!" + BLANKSTR);
				               constructionGrid.store.reload();
			               },
			               failure: function(form, action) {
			            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
			               }
				       	});					
				}
	    	});	
    	}else{
    		 Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
         	return;
    	}
    },
    onOpenClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				var valueStr = records[0].get('id');
   				var valueStatue = records[0].get('processStatus');
   				if(valueStatue == '2'){
   					Ext.Msg.alert('系统提示', '流程已经启动！');
   				}else if(valueStatue == '3'){
   					Ext.Msg.alert('系统提示', '流程已经结束！');
   				}else{
	   				Ext.Msg.confirm("提醒信息", "确定要启动此流程吗?",function(btn){
	   					if (btn == 'yes') {
	   				       	Ext.Ajax.request({
	   				       		url: 'openTestProcess', 
	   					       	   method : 'POST', 
	   					       	   params: { id: valueStr},
	   				               success: function(form, action) {
	   					               Ext.MessageBox.alert("系统提示:", BLANKSTR + "启动成功!" + BLANKSTR);
	   					               constructionGrid.store.reload();
	   				               },
	   				               failure: function(form, action) {
	   				            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "启动失败!" + BLANKSTR);
	   				               }
	   					       	});					
	   					}
	   		    	});
   				}
   			}else{
   				Ext.Msg.alert('系统提示', '不能开启多条流程..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
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
    },
    formatTime : function(val) {
    		var valStr = val.constructor == Date ? Ext.util.Format.date(val, 'Y-m-d H:i:s') : formatDate(new Date(val["time"]),"yyyy-MM-dd HH:mm:ss");    		
    		var valArr = valStr.split('-');
    		var valDate = new Date(valArr[0], valArr[1] - 1, valArr[2]);
    		return valStr;
    }
});

DictTypeLookWindow = Ext.extend(Ext.Window, {
	lookForm : null,
	constructor: function() {
		this.lookForm = new LookForm();
		DictTypeLookWindow.superclass.constructor.call(this, {
			title: "查看审批记录",
			width: 600,
			 height: 200,
			//autoHeight:true,
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

/***************************************LookForm组件**************************************************/
LookForm = Ext.extend(UxGrid, {
	pageSizeCombo: null,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: USER_GRID_STORE_URL_CHECK, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},[
                       		{name:'sprxm'},{name:'spyj'},{name:'spsj'},{name:'sftg'}
                       ])
        });
    	//this.vbbar= this.createPagingToolbar(PAGESIZE);
    	LookForm.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: false,
            height: 200,
           // autoHeight:true, 
            viewConfig: {
                forceFit: false
            },
            loadMask: { 
                msg : '正在载入数据,请稍候...'
            },
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                {header:'编号',dataIndex:'id',width:80,sortable: true, hidden:true},
                {header:'审批人姓名',dataIndex:'sprxm',width:100,align:'center',sortable: true},
            	{header:'审批意见',dataIndex:'spyj',width:220,align:'center',sortable: true,
               	 renderer: function(value,metadata,record,rowIndex,colIndex,store) { 
                     metadata.attr = 'ext:qtip="' + record.get("spyj") + '"';
                     return record.get("spyj");
                 }
            	},
            	{header:'审批时间',dataIndex:'spsj',width:135,align:'center',sortable: true},
            	{header:'审批是否通过',dataIndex:'sftg',width:100,align:'center',sortable: true},
            ]),
            //tbar: this.vtbar,
            //bbar: this.vbbar,
          //  ds: this.store,
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
    },
    formatTime : function(val) {
    		var valStr = val.constructor == Date ? Ext.util.Format.date(val, 'Y-m-d H:i:s') : formatDate(new Date(val["time"]),"yyyy-MM-dd HH:mm:ss");    		
    		var valArr = valStr.split('-');
    		var valDate = new Date(valArr[0], valArr[1] - 1, valArr[2]);
    		return valStr;
    }
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