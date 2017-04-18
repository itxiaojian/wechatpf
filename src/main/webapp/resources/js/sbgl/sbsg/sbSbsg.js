var USER_GRID_STORE_URL = 'getSbsgList';
var USER_GRID_STORE_URL_CHECK='getSbsgCheck';
var PAGESIZE=20;
var ryflByBeforeLoad=null;
var yearll=new Ext.form.TextField();
var amtd = new Ext.form.TextField();

//添加页面
var ACT_DEAL_WINDOW;
/*************************************** ConstructionForm组件 **************************************************/
ConstructionForm = Ext.extend(Ext.ux.Form, {
    constructor: function() {
    	this.idHidden = this.createHidden('id','id');
    	this.sbmc = this.createTextField('<font color="red">*</font>设备名称:', 'sbmc', '25%','设备名称不能为空！');
    	this.sbxh = this.createTextField('<font color="red">*</font>规格型号:', 'sbxh', '25%','规格型号不能为空！');
        this.cgsl = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>数量:',
            name: 'cgsl',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
          blankText: '该选项为必填项,请输入内容...',
        });
        this.cgsl.allowBlank = false;
    	this.sblb = this.createCombo('<span style="color:red">*</span>设备类别:', 'zdz', 'zdmc', 'sblb', '95%', PROJECT_NAME+'/sb/sbSbxx/getDicByLx');
		this.sblb.store.load();
		this.sblb.allowBlank = false;
    	this.sccj = this.createTextField('<font color="red">*</font>制作厂商:', 'sccj', '25%','制作厂商不能为空！');
        this.sgrq = this.createDateTimeField('<font color="red">*</font>申请时间:', 'sgrq', 'H:i','申请时间不能为空！');
        this.sgzt = this.createCombo('<span style="color:red">*</span>申请状态:', 'zdz', 'zdbm', 'sgzt', '95%', PROJECT_NAME+'/sbgl/SbSbsg/getZt');
		this.sgzt.store.load();
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
				layoutConfig: {columns: 2},
				defaults: {
                    columnWidth: .99,
                    anchor: '95%',
                    layout: "form"
                },
				items : [   
						this.sbmc,
						this.sbxh,
						this.cgsl,
						this.sblb,
						this.sccj,
						this.sgrq,
						this.sgzt
						
		        ]},
            this.idHidden    
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '增加', width: 20,iconCls: 'add', hidden: false, handler: this.addFormClick, scope: this},  
                      {text:'修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
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
              		var win=constructionGrid.constructionInsertWindow;
              		if(win==null){
              			win=constructionGrid.constructionUpdateWindow
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
                 url: 'save', 
                 method: 'POST',
                 success: function(form, action) { 
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	constructionGrid.constructionInsertWindow.hide();
                 	constructionGrid.vbbar.doLoad(constructionGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
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
            title: "设备申购信息添加",
            width: 800,
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
            heigth:500,
            anchor: '100%',
            plain: true,
            modal: true,
            closeAction: 'hide',
            autoScroll: false,
            resizable: true,
            frame:true,
            border: false,
            items: [this.constructionForm]
        });
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
            //autoHeight:true, 
            autoScroll: true,
            viewConfig: {
                forceFit: false
            },
            loadMask: { 
                msg : '正在载入数据,请稍候...'
            },
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                {header:'编号',dataIndex:'id',width:80,sortable: true, hidden:true},
                {header:'审批人姓名',dataIndex:'sprxm',width:100,align:'center',sortable: true},
            	{header:'审批意见',dataIndex:'spyj',width:230,align:'center',sortable: true,
                	 renderer: function(value,metadata,record,rowIndex,colIndex,store) { 
                         metadata.attr = 'ext:qtip="' + record.get("spyj") + '"';
                         return record.get("spyj");
                     }
            	},
            	{header:'审批时间',dataIndex:'spsj',width:135,align:'center',sortable: true},
            	{header:'审批是否通过',dataIndex:'sftg',width:100,align:'center',sortable: true},
            ]),
          //  tbar: this.vtbar,
         //   bbar: this.vbbar,
          // ds: this.store,
        });
    },
    onAddClick: function() {
    	var win = this.constructionInsertWindow;
    	win.constructionForm.getForm().reset();
    	win.constructionForm.idHidden.setValue(0);
    	win.constructionForm.sgzt.setValue('1');
    	win.show();
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				if(vrecord.data.sgzt=='申购申请中' || vrecord.data.sgzt=='部门驳回' ){
   		    		var win = this.constructionUpdateWindow;
   	   		    	win.show();
   	   		    	var winForm = win.constructionForm;
   	   		    	win.constructionForm.getForm().loadRecord(vrecord);
   				}else{
   					Ext.Msg.alert('系统提示', '不能修改审核中的记录..');
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
                       		{name:'xm'},{name:'sgr'},{name:'sgrq'},{name:'spyj'},{name:'sgzt'}
                       ])
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                '-',{text:'申购',iconCls: 'add',handler:this.onAddClick,scope:this},
                '-',{text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},
            	'-',{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},
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
		  			         proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+'/sbgl/SbSbsg/getSqzt', method: 'POST'}),
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
                {header:'设备名称',dataIndex:'sbmc',width:120,align:'center',sortable: true},
            	{header:'规格型号',dataIndex:'sbxh',width:110,align:'center',sortable: true},
            	{header:'数量',dataIndex:'cgsl',width:80,align:'center',sortable: true},
            	{header:'设备类型',dataIndex:'sblb',width:110,align:'center',sortable: true,
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
            	{header:'申请部门',dataIndex:'bmmc',width:120,align:'center',sortable: true},
            	{header:'申请部门',dataIndex:'sybm',width:120,align:'center',sortable: true,hidden:true},
            	{header:'申请人',dataIndex:'xm',width:110,align:'center',sortable: true},
            	{header:'申请人',dataIndex:'sgr',width:100,align:'center',sortable: true,hidden:true},
            	{header:'申请日期',dataIndex:'sgrq',width:120,align:'center',sortable: true},
            //	{header:'申批人姓名',dataIndex:'sprxm',width:120,align:'center',sortable: true},
            //	{header:'审批意见',dataIndex:'spyj',width:120,align:'center',sortable: true},
            	{header:'申请状态',dataIndex:'sgzt',width:120,align:'center',sortable: true},
            	{header: '审批意见', width: 100, dataIndex: 'id', align:"center",  sortable: true,hidden: false,
					renderer:function(value, cellmeta, record){
						   return "<a href='#' style='cursor:pointer;'onclick='constructionGrid.onLook()'>查看</a>";
            		}
				},
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
            }
        });
    },
    onAddClick: function() {
    	var win = this.constructionInsertWindow;
    	win.constructionForm.getForm().reset();
    	win.constructionForm.idHidden.setValue(0);
    	win.constructionForm.sgzt.setValue('1');
    	win.show();
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
   				if(vrecord.data.sgzt=='申购申请中' || vrecord.data.sgzt=='部门驳回' ){
   		    		var win = this.constructionUpdateWindow;
   	   		    	win.show();
   	   		    	var winForm = win.constructionForm;
   	   		    	win.constructionForm.getForm().loadRecord(vrecord);
   				}else{
   					Ext.Msg.alert('系统提示', '不能修改审核中的记录..');
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

