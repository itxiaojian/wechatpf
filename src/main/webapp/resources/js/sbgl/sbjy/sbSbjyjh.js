var DICT_GRID_STORE_URL = '/sbgl/SbSbjyjh/getSbjyjhList';
var SBXX_URL = '/sb/sbSbxx/getSbxxList';
var PAGESIZE=20;
var tjorxg=0;

/***************************************DictTypeInsertWindow组件**************************************************/
SbSbxxOpenWindow = Ext.extend(Ext.Window,{
	sbSbxxGrid : null,
	dictTypeForm : null,
    constructor: function(grid) {
        this.sbSbxxGrid = new SbSbxxGrid();
        //this.sbSbxxGrid.sbSbxxOpenWindow = this;
    	SbSbxxOpenWindow.superclass.constructor.call(this, {
            title: "选择设备",
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

/**************************SbSbxxGrid*******************************************/
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
		            ,{name:'zjnx'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	/*var win = this.sbSbxxOpenWindow;*/
    	this.vtbar = new Ext.Toolbar({
            items:[
					{xtype:'textfield',id:'cd',
						emptyText:'请输入查询关键字....', 
               	    },'-',
					{xtype:'button',text:'查询',iconCls:'query',handler:function(){
						var cd = document.getElementById('cd').value;
   						/*alert(cd);*/
						dictTypeGrid.constructionInsertWindow.constructionForm.sbSbxxOpenWindow.sbSbxxGrid.store.baseParams= {cd:cd};
						dictTypeGrid.constructionInsertWindow.constructionForm.sbSbxxOpenWindow.sbSbxxGrid.store.load({params:{start:0,limit:PAGESIZE}});
						//alert(cd);
   				    }},'-',
   				    {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
   				    	document.getElementById('cd').value='';
      			  }
               },
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        SbSbxxGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '设备信息列表',
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
	            {header:'使用部门',dataIndex:'sybm',width:100,sortable:true,hidden:false}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
            	'rowdblclick':function(){
//            		window.parent.pendPoolGrid.store.load();
            		var records=this.getSelectionModel().getSelections();
            		var sjid = records[0].get('id');
            		this.sbSbxxOpenWindow.dictTypeForm.sbbh.setValue(records[0].get('sbbh'));
            		this.sbSbxxOpenWindow.dictTypeForm.sbmc.setValue(records[0].get('sbmc'));
            		this.sbSbxxOpenWindow.dictTypeForm.sbxh.setValue(records[0].get('sbxh'));
            		this.sbSbxxOpenWindow.dictTypeForm.sybm.setValue(records[0].get('sybm'));
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
        this.sbbh.readOnly = true;
        
        this.sbmc = this.createTextField('<font color="red">*</font>设备名称','sbmc','95%','',null,100,'长度超过不能100');
        this.sbmc.readOnly = true;
        
        this.sbxh = this.createTextField('<font color="red">*</font>设备型号','sbxh','95%','',null,100,'长度超过不能100');
        this.sbxh.readOnly = true;
        
        this.sybm = this.createTextField('<font color="red">*</font>使用部门','sybm','95%','',null,100,'长度超过不能100');
        this.sybm.readOnly = true;
        
        this.jyfs = this.createCombo('<span style="color:red">*</span>检验方式', 'zdz', 'zdmc', 'jyfs', '95%', PROJECT_NAME+'/sbgl/SbSbjyjh/getJyfsByLx');
        this.jyfs.store.load();
		this.jyfs.allowBlank = false;

        
		this.scwxsj = this.createDateField1('上次检验日期','scwxsj','Y-m-d','95%');
		this.scwxsj.value = new Date().format('Y-m-d');
		this.scwxsj.allowBlank = false;
		
		this.xcwxsj = this.createDateField('下次检验日期','xcwxsj','Y-m-d','95%');
		this.xcwxsj.value = new Date().format('Y-m-d');
		this.xcwxsj.readOnly = true;
		this.xcwxsj.allowBlank = false;
        
		this.jg = new Ext.form.NumberField({
            fieldLabel: '间隔',
            name: 'jg',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            hideTrigger: false,  
            keyNavEnabled: false,  
            mouseWheelEnabled: false,
            blankText: '该选项为必填项,请输入内容...',
            listeners : {
             	'blur':function(e){  
             		var win='';
             		if(tjorxg==1){
             			win=dictTypeGrid.constructionInsertWindow;
             		}else if(tjorxg==2){
             			win=dictTypeGrid.constructionUpdateWindow
             		}
             		var winForm = win.constructionForm;
             		var jddw=winForm.jddw.getValue();
             		var scrq=winForm.scwxsj.getValue();//.format('Y-m-d');
             		if(jddw!=''&&scrq!=''){
             			var date=scrq;
             			if(jddw==0){
             				date.setYear(parseInt(scrq.getFullYear())+parseInt(e.value));
             			}else if(jddw==1){
             				date.setMonth(parseInt(scrq.getMonth())+parseInt(e.value));
             			}else if(jddw==2){
             				date.setDate(parseInt(scrq.getDate())+parseInt(e.value));
             			}
             			winForm.xcwxsj.setValue(date);
             		}
             	}
             }
        });
		this.jg.allowBlank = false;
		
		this.jddw = this.createCombo1(null, 'zdz', 'zdmc', 'jddw', '85.8%', PROJECT_NAME+'/sb/sbSbxx/getDicByZl');
		this.jddw.store.load();
		this.jddw.allowBlank = false;
        
		this.wxrbh = this.createCombo('<span style="color:red">*</span>检验项目', 'zdz', 'zdmc', 'wxrbh', '95%', PROJECT_NAME+'/sbgl/SbSbjyjh/getDicByLx');
		this.wxrbh.store.load();
		this.wxrbh.allowBlank = false;
		this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   
        
		var pnRow1=new Ext.Panel({
            border:false,  
            layout:'column',  
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
                }) 
            ]  
        }); 
		var pnRow3=new Ext.Panel({
            border:false,  
            layout:'column',  
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
                         this.wxrbh
                    ]  
                }) 
            ]  
        });  
		var pnRow4=new Ext.Panel({
            border:false,  
            layout:'column',  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                           this.scwxsj
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                         this.jg
                    ]  
                }) ,
                new Ext.Panel({  
                    columnWidth:.15,  
                    layout:'form',  
                    border:false,  
                    labelWidth:1,  
                    labelAlign:'right',  
                    items:[  
                         this.jddw
                    ]  
                }) 
            ]  
        });  
		var pnRow5=new Ext.Panel({
            border:false,  
            layout:'column',  
            items:[  
                new Ext.Panel({  
                    columnWidth:.51,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                           this.xcwxsj
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
            ],
            buttonAlign :'center',
            buttons: [
				{text: '选择设备', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
				{text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
				{text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
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
              	'blur':function(e){  
              		var win='';
             		if(tjorxg==1){
             			win=dictTypeGrid.constructionInsertWindow;
             		}else if(tjorxg==2){
             			win=dictTypeGrid.constructionUpdateWindow
             		}
              		var winForm = win.constructionForm;
              		var jg=winForm.jg.getValue();
              		var scrq=winForm.scwxsj.getValue();//.format('Y-m-d');
              		if(jg!=''&&scrq!=''){
              			var date=scrq;
              			if(e.value==0){
              				date.setYear(parseInt(scrq.getFullYear())+parseInt(jg));
              			}else if(e.value==1){
              				date.setMonth(parseInt(scrq.getMonth())+parseInt(jg));
              			}else if(e.value==2){
              				date.setDate(parseInt(scrq.getDate())+parseInt(jg));
              			}
              			winForm.xcwxsj.setValue(date);
              		}
              	}
              }
         });
         return combo;
     }, 
     createDateField1: function(fieldLabel, name, format, anchor) {
      	var df =  new Ext.form.DateField({
  			fieldLabel: fieldLabel,
  			name: name,
  			format: format,
  			anchor: anchor,
  			allowBlank: false,
  			editable:false,//不能手动输入
  			blankText: '请选择时间',
  			listeners : {
               	'blur':function(e){  
               		var win='';
             		if(tjorxg==1){
             			win=dictTypeGrid.constructionInsertWindow;
             		}else if(tjorxg==2){
             			win=dictTypeGrid.constructionUpdateWindow
             		}
               		var winForm = win.constructionForm;
               		var jg=winForm.jg.getValue();
               		var jddw=winForm.jddw.getValue();//.format('Y-m-d');
               		if(jg!=''&&jddw!=''){
               			var date=new Date();
               			//var arr[]=(e.value).split("-");
               			date.setFullYear((e.value).substring(0,4),(parseInt((e.value).substring(6,7))-1),(e.value).substring(8));
               			if(jddw==0){
               				date.setYear(parseInt(date.getFullYear())+parseInt(jg));
               			}else if(jddw==1){
               				date.setMonth(parseInt(date.getMonth())+parseInt(jg));
               			}else if(jddw==2){
               				date.setDate(parseInt(date.getDate())+parseInt(jg));
               			}
               			winForm.xcwxsj.setValue(date);
               		}
               	}
               }
  		});
  		return df;
      },
     addSbClick: function() {       //添加设备
 		var win = this.sbSbxxOpenWindow;
 		win.dictTypeForm = this;
 		win.show();
 		win.sbSbxxGrid.sbSbxxOpenWindow = win;
 		win.sbSbxxGrid.store.load({params:{start:0,limit:PAGESIZE}});
 		
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: 'saveSbjyjh',   
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
        
		this.scwxsj = this.createDateField1('上次检验日期','scwxsj','Y-m-d','95%');
		this.scwxsj.value = new Date().format('Y-m-d');
		this.scwxsj.readOnly = true;
		
		this.xcwxsj = this.createDateField('下次检验日期','xcwxsj','Y-m-d','93.8%');
		this.xcwxsj.value = new Date().format('Y-m-d');
		this.xcwxsj.readOnly = true;
        
		this.jg = new Ext.form.NumberField({
            fieldLabel: '间隔',
            name: 'jg',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            hideTrigger: false,  
            keyNavEnabled: false,  
            mouseWheelEnabled: false,
            blankText: '该选项为必填项,请输入内容...',
            listeners : {
             	'blur':function(e){  
             		var win='';
             		if(tjorxg==1){
             			win=dictTypeGrid.constructionInsertWindow;
             		}else if(tjorxg==2){
             			win=dictTypeGrid.constructionUpdateWindow
             		}
             		var winForm = win.constructionForm;
             		var jddw=winForm.jddw.getValue();
             		var scrq=winForm.scwxsj.getValue();//.format('Y-m-d');
             		if(jddw!=''&&scrq!=''){
             			var date=scrq;
             			if(jddw==0){
             				date.setYear(parseInt(scrq.getFullYear())+parseInt(e.value));
             			}else if(jddw==1){
             				date.setMonth(parseInt(scrq.getMonth())+parseInt(e.value));
             			}else if(jddw==2){
             				date.setDate(parseInt(scrq.getDate())+parseInt(e.value));
             			}
             			winForm.xcwxsj.setValue(date);
             		}
             	}
             }
        });
		this.jg.readOnly = true;
        
        this.jddw = this.createCombo1(null, 'zdz', 'zdmc', 'jddw', '85.8%', PROJECT_NAME+'/sb/sbSbxx/getDicByZl');
		this.jddw.store.load();
		this.jddw.readOnly = true;
        
        this.wxrbh = this.createCombo('<span style="color:red">*</span>检验项目', 'zdz', 'zdmc', 'wxrbh', '95%', PROJECT_NAME+'/sbgl/SbSbjyjh/getDicByLx');
		this.wxrbh.store.load();
        this.wxrbh.readOnly = true;
        
        var pnRow1=new Ext.Panel({
            border:false,  
            layout:'column',  
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
                }) 
            ]  
        }); 
		var pnRow3=new Ext.Panel({
            border:false,  
            layout:'column',  
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
                         this.wxrbh
                    ]  
                }) 
            ]  
        });  
		var pnRow4=new Ext.Panel({
            border:false,  
            layout:'column',  
            items:[  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                           this.scwxsj
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                         this.jg
                    ]  
                }) ,
                new Ext.Panel({  
                    columnWidth:.15,  
                    layout:'form',  
                    border:false,  
                    labelWidth:1,  
                    labelAlign:'right',  
                    items:[  
                         this.jddw
                    ]  
                }) 
            ]  
        });  
		var pnRow5=new Ext.Panel({
            border:false,  
            layout:'column',  
            items:[  
                new Ext.Panel({  
                    columnWidth:.51,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                           this.xcwxsj
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
            ],
            buttonAlign :'center',
            buttons: [
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
              	'blur':function(e){  
              		var win='';
             		if(tjorxg==1){
             			win=dictTypeGrid.constructionInsertWindow;
             		}else if(tjorxg==2){
             			win=dictTypeGrid.constructionUpdateWindow
             		}
              		var winForm = win.constructionForm;
              		var jg=winForm.jg.getValue();
              		var scrq=winForm.scwxsj.getValue();//.format('Y-m-d');
              		if(jg!=''&&scrq!=''){
              			var date=scrq;
              			if(e.value==0){
              				date.setYear(parseInt(scrq.getFullYear())+parseInt(jg));
              			}else if(e.value==1){
              				date.setMonth(parseInt(scrq.getMonth())+parseInt(jg));
              			}else if(e.value==2){
              				date.setDate(parseInt(scrq.getDate())+parseInt(jg));
              			}
              			winForm.xcwxsj.setValue(date);
              		}
              	}
              }
         });
         return combo;
     }, 
     createDateField1: function(fieldLabel, name, format, anchor) {
       	var df =  new Ext.form.DateField({
   			fieldLabel: fieldLabel,
   			name: name,
   			format: format,
   			anchor: anchor,
   			allowBlank: false,
   			editable:false,//不能手动输入
   			blankText: '请选择时间',
   			listeners : {
                	'blur':function(e){  
                		var win='';
                 		if(tjorxg==1){
                 			win=dictTypeGrid.constructionInsertWindow;
                 		}else if(tjorxg==2){
                 			win=dictTypeGrid.constructionUpdateWindow
                 		}
                		var winForm = win.constructionForm;
                		var jg=winForm.jg.getValue();
                		var jddw=winForm.jddw.getValue();//.format('Y-m-d');
                		if(jg!=''&&jddw!=''){
                			var date=new Date();
                			//var arr[]=(e.value).split("-");
                			date.setFullYear((e.value).substring(0,4),(parseInt((e.value).substring(6,7))-1),(e.value).substring(8));
                			if(jddw==0){
                				date.setYear(parseInt(date.getFullYear())+parseInt(jg));
                			}else if(jddw==1){
                				date.setMonth(parseInt(date.getMonth())+parseInt(jg));
                			}else if(jddw==2){
                				date.setDate(parseInt(date.getDate())+parseInt(jg));
                			}
                			winForm.xcwxsj.setValue(date);
                		}
                	}
                }
   		});
   		return df;
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
            title: "添加设备检验计划",
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
        	title: "修改设备检验计划",
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
            		{name:'id'}, {name:'sbbh'}, {name:'sbmc'},{name:'sbxh'},{name:'sybm'},{name:'jyfs'}, {name:'scwxsj'}, {name:'jg'},
		            {name:'xcwxsj'}, {name:'wxrbh'},{name:'jddw'},{name:'jdzqs'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                   {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                   {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
                   {text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
               	   {xtype:'textfield',id:'jh',width: 200,
   				    emptyText:'查询设备的编号、名称、型号...', 
               	    },'-',
               	   {xtype:'label',text:'检验项目：'},{xtype:'combo',id:'wxrbh',
	  				   editable: false,
	  				   store: new Ext.data.Store({
	  			         proxy: new Ext.data.HttpProxy({url: 'getDicByLx', method: 'POST'}),
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
   						var jh = Ext.getCmp('jh').getValue();
   						var wxrbh = Ext.getCmp('wxrbh').getValue();
   						var params = {};
		       			params['wxrbh']=Ext.getCmp('wxrbh').getValue();
		       			dictTypeGrid.vtbar.items.each(function(item,index,length){ 
	       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
	       						if (item.getXType() == 'datefield') {
	       							params[item.getId()] = item.getValue().format(item.format);
	       						} else {
	       							params[item.getId()] = item.getValue();
	       						}
	       					}
						});
   						dictTypeGrid.store.baseParams= {jh:jh,wxrbh:wxrbh};
   						dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
   				    }},'-',
   				    {xtype:'button',text:'清空',iconCls:'redo',handler:function(){
   	   				Ext.getCmp('jh').setValue("");
   	   				Ext.getCmp('wxrbh').setValue("");
      			  }
               },
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        this.dictTypeLookWindow = new DictTypeLookWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '设备检验计划',
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
						   return "<a href='javascript:' onclick='dictTypeGrid.onLook()'>查看</a>";
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
	            {header:'上次检验日期',dataIndex:'scwxsj',width:100, align:"center", sortable:true,hidden:false},
	            {header:'检定单位',dataIndex:'jddw',width:100, align:"center", sortable:true,hidden:true},
	            {header:'间隔',dataIndex:'jg',width:100, align:"center", sortable:true,hidden:false,
	            	renderer:function(value, cellmeta, record){
            			var a = record.get("jddw");
	                    if(a == '0') {
	                        return "<span>"+value+"年</span>";
	                    }else if(a == '1') {
	                        return "<span>"+value+"月</span>";
	                    }else if(a == '2'){
	                    	return"<span>"+value+"日</span>";
	                    }else{
	                        return a;
	                    }
            		}},
	            {header:'下次检验日期',dataIndex:'xcwxsj',width:100, align:"center", sortable:true,hidden:false},
	            {header:'检验项目',dataIndex:'wxrbh',width:100, align:"center", sortable:true,hidden:false,
            		renderer:function(value){
	                    if(value == '1') {
	                        return "<span style='color:blue;'>安全检验</span>";
	                    }else if(value == '2') {
	                        return "<span style='color:red;'>压力检验</span>";
	                    }else if(value == '3'){
	                    	return"<span style='color:green;'>精度检验</span>";
	                    }else{
	                        return value;
	                    }
            		}
	            }
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
        });
    },
    onAddClick: function() {       //添加计划
	    	var count = this.getStore().getTotalCount();
	    	tjorxg=0;
    		var win = this.constructionInsertWindow;
    		tjorxg=1;
    		win.show();
    		win.constructionForm.getForm().reset();
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				tjorxg=0;
   				var win = this.constructionUpdateWindow;
   		    	var winForm = win.constructionForm;
   		    	winForm.sbbh.setValue(vrecord.data.sbbh);
				winForm.sbmc.setValue(vrecord.data.sbmc);
				winForm.sbxh.setValue(vrecord.data.sbxh);
				winForm.sybm.setValue(vrecord.data.sybm);
				winForm.jyfs.setValue(vrecord.data.jyfs);
				winForm.scwxsj.setValue(vrecord.data.scwxsj);
				winForm.jg.setValue(vrecord.data.jg);
				winForm.xcwxsj.setValue(vrecord.data.xcwxsj);
				winForm.wxrbh.setValue(vrecord.data.wxrbh);
				winForm.jddw.setValue(vrecord.data.jddw);
				tjorxg=2;
				win.show();
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
});

/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    dictTypeGrid = new DictTypeGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
    
    //sbSbxxGrid = new SbSbxxGrid();
    
    new Ext.Viewport({
    	layout: 'border',
    	items:[
			dictTypeGrid
    	]
    });
});