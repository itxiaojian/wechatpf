var DICT_GRID_STORE_URL = '/sb/sbSbxx/getSbxxList';
var PAGESIZE=20;
var tjorxg=0;
var org = new wxpt.OrgSingelSelect();
var user = new wxpt.ClerkSingelSelect();
/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
		this.sbmc = this.createTextField('<font color="red">*</font>设备名称','sbmc','97.5%','',null,100,'长度超过不能100');
		this.sbmc.allowBlank = false;
		
//		this.sbbh = this.createTextField('<font color="red">*</font>设备编号','sbbh','95%','',null,100,'长度超过不能100');
//		this.sbbh.allowBlank = false;
		
		this.sbxh = this.createTextField('<font color="red">*</font>规格型号','sbxh','95%','',null,100,'长度超过不能100');
		this.sbxh.allowBlank = false;
		
		this.sblb = this.createCombo('<span style="color:red">*</span>设备类别', 'zdz', 'zdmc', 'sblb', '95%', PROJECT_NAME+'/sb/sbSbxx/getDicByLx');
		this.sblb.store.load();
		this.sblb.allowBlank = false;

		this.sccj = this.createTextField('<font color="red">*</font>生产厂商','sccj','95%','',null,100,'长度超过不能100');
		this.sccj.allowBlank = false;
		
		this.sbjb = this.createTextField('<font color="red">*</font>设备级别','sbjb','95%','',null,32,'长度超过不能32');
		//this.sybm = this.createTextField('使用部门','sybm','96.8%','',null,32,'长度超过不能32');
		this.sybm = new wxpt.OrgSingelSelect('<font color="red">*</font>使用部门','sybm','sybm','96.8%');
		this.syfw = this.createTextField('<font color="red">*</font>使用范围','syfw','95%','',null,256,'长度超过不能256');
		this.sccj = this.createTextField('<font color="red">*</font>生产厂家','sccj','95%','',null,256,'长度超过不能256');
		this.ccbh = this.createTextField('<font color="red">*</font>出厂编号','ccbh','93.8%','',null,32,'长度超过不能32');
		this.ccrq = this.createDateField('<font color="red">*</font>出厂日期','ccrq','Y-m-d','95%');
		this.ccrq.value = new Date().format('Y-m-d');
		this.gmrq = this.createDateField('<font color="red">*</font>购买日期','gmrq','Y-m-d','95%');
		this.gmrq.value = new Date().format('Y-m-d');
		this.gmjg = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>购买价格',
            name: 'gmjg',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '93.8%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.jdzq = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>检定周期',
            name: 'jdzq',
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
             		var winForm = win.sbSbxx;
             		var jddw=winForm.jddw.getValue();
             		var scrq=winForm.scjdrq.getValue();//.format('Y-m-d');
             		if(jddw!=''&&scrq!=''){
             			var date=scrq;
             			if(jddw==0){
             				date.setYear(parseInt(scrq.getFullYear())+parseInt(e.value));
             			}else if(jddw==1){
             				date.setMonth(parseInt(scrq.getMonth())+parseInt(e.value));
             			}else if(jddw==2){
             				date.setDate(parseInt(scrq.getDate())+parseInt(e.value));
             			}
             			winForm.xcjdrq.setValue(date);
             		}
             	}
             }
        });
		this.jddw = this.createCombo1(null, 'zdz', 'zdmc', 'jddw', '85.8%', PROJECT_NAME+'/sb/sbSbxx/getDicByZl');
		this.jddw.store.load();
		this.jddw.allowBlank = false;
		this.syqx = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>使用期限',
            name: 'syqx',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.scjdrq = this.createDateField1('<font color="red">*</font>上次检定日期','scjdrq','Y-m-d','95%');
		this.scjdrq.value = new Date().format('Y-m-d');
		this.xcjdrq = this.createDateField('<font color="red">*</font>下次检定日期','xcjdrq','Y-m-d','93.8%');
		this.xcjdrq.value = new Date().format('Y-m-d');
		this.syzt = this.createCombo('<font color="red">*</font>使用状态', 'zdz', 'zdmc', 'syzt', '95%', PROJECT_NAME+'/sb/sbSbxx/getDicBySy');
		this.syzt.store.load();
		//this.sbwhr = this.createTextField('设备维护人','sbwhr','93.8%','',null,32,'长度超过不能32');
		this.sbwhr = new wxpt.ClerkSingelSelect('<font color="red">*</font>设备维护人','sbwhr','sbwhr','93.8%');
		this.zjff = this.createCombo('<font color="red">*</font>折旧方法', 'zdz', 'zdmc', 'zjff', '95%', PROJECT_NAME+'/sb/sbSbxx/getDicByZj');
		this.zjff.store.load();
		this.zcyz = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>资产原值',
            name: 'zcyz',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.jcl = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>净残率%',
            name: 'jcl',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.zjnx = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>折旧年限',
            name: 'zjnx',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '93.8%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		
		this.sbjb.allowBlank = false;
		this.sybm.allowBlank = false;
		this.syfw.allowBlank = false;
		this.sccj.allowBlank = false;
		this.ccbh.allowBlank = false;
		this.ccrq.allowBlank = false;
		this.gmrq.allowBlank = false;
		this.gmjg.allowBlank = false;
		this.jdzq.allowBlank = false;
		this.syqx.allowBlank = false;
		this.scjdrq.allowBlank = false;
		this.xcjdrq.allowBlank = false;
		this.syzt.allowBlank = false;
		this.sbwhr.allowBlank = false;
		this.zjff.allowBlank = false;
		this.zcyz.allowBlank = false;
		this.jcl.allowBlank = false;
		this.zjnx.allowBlank = false;
		this.xcjdrq.readOnly = true;
		
		
    	var pnRow1=new Ext.Panel({
            border:false,  
            layout:'column',  
            items:[  
                new Ext.Panel({  
                    columnWidth:1,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                           this.sbmc
                    ]  
                })/*,  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                         this.sbbh
                    ]  
                }) */
            ]  
        });  
    	var pnRow101=new Ext.Panel({
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
                         this.sblb
                    ]  
                })
            ]  
        });  
        var pnRow2=new Ext.Panel({  
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
                        this.gmrq
                    ] 
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.zcyz 
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sbwhr 
                    ]  
                })  
            ]  
        });
        var pnRow201=new Ext.Panel({  
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
                        this.zjff
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jcl 
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.zjnx 
                    ]  
                })  
            ]  
        });
        var pnRow3=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.2,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jdzq  
                    ]  
                }),
                new Ext.Panel({  
                    columnWidth:.1,  
                    layout:'form',  
                    border:false,  
                    labelWidth:1,  
                    labelAlign:'right',  
                    items:[  
                        this.jddw  
                    ]  
                }),
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.scjdrq  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:90,  
                    labelAlign:'right',  
                    items:[  
                        this.xcjdrq  
                    ]  
                })  
            ]  
        });  
        var pnRow301=new Ext.Panel({  
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
                        this.syzt  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.7,  
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
        var pnRow4=new Ext.Panel({//当然这里直接在FormPanel中添加TextField就可以了，因为只有一行，但是为了一致以及对齐方便我这里还是放到了panel中  
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
                        this.syfw  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.syqx  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.gmjg  
                    ]  
                })  
            ]
        });  
        var pnRow5=new Ext.Panel({  
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
                        this.sccj  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.ccrq  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.ccbh  
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
        

        DictTypeForm.superclass.constructor.call(this, {
        	anchor: '100%',
        	autoHeight:true,
        	layout:"tableform",
        	layoutConfig: {columns: 1},
        	labelWidth: 80,
            labelAlign :'right',
            frame:true,
            bodyStyle:"padding: 5px 5px 0",
            width: '100%',
            items: [
				pnRow1, 
				pnRow101,
				pnRow2, 
				pnRow201,
				pnRow3,  
				pnRow4,  
				pnRow5,  
				pnRow301  
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
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
              	'blur':function(e){  
              		var win='';
             		if(tjorxg==1){
             			win=dictTypeGrid.constructionInsertWindow;
             		}else if(tjorxg==2){
             			win=dictTypeGrid.constructionUpdateWindow
             		}
              		var winForm = win.sbSbxx;
              		var jdzq=winForm.jdzq.getValue();
              		var scrq=winForm.scjdrq.getValue();//.format('Y-m-d');
              		if(jdzq!=''&&scrq!=''){
              			var date=scrq;
              			if(e.value==0){
              				date.setYear(parseInt(scrq.getFullYear())+parseInt(jdzq));
              			}else if(e.value==1){
              				date.setMonth(parseInt(scrq.getMonth())+parseInt(jdzq));
              			}else if(e.value==2){
              				date.setDate(parseInt(scrq.getDate())+parseInt(jdzq));
              			}
              			winForm.xcjdrq.setValue(date);
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
              		var winForm = win.sbSbxx;
              		var jdzq=winForm.jdzq.getValue();
              		var jddw=winForm.jddw.getValue();//.format('Y-m-d');
              		if(jdzq!=''&&jddw!=''){
              			var date=new Date();
              			//var arr[]=(e.value).split("-");
              			date.setFullYear((e.value).substring(0,4),(parseInt((e.value).substring(6,7))-1),(e.value).substring(8));
              			if(jddw==0){
              				date.setYear(parseInt(date.getFullYear())+parseInt(jdzq));
              			}else if(jddw==1){
              				date.setMonth(parseInt(date.getMonth())+parseInt(jdzq));
              			}else if(jddw==2){
              				date.setDate(parseInt(date.getDate())+parseInt(jdzq));
              			}
              			winForm.xcjdrq.setValue(date);
              		}
              	}
              }
 		});
 		return df;
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
        	if(this.sybm.getOrgId()==null||this.sybm.getOrgId()==''){
        		Ext.MessageBox.alert("系统提示:", "请选择使用部门");
        		return false;
        	}
        	if(this.sbwhr.getClerkCode()==null||this.sbwhr.getClerkCode()==''){
        		Ext.MessageBox.alert("系统提示:", "请选择维护人");
        		return false;
        	}
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sb/sbSbxx/saveSbxx',   
                 method: 'POST',
                 params:{
                	 sybmbh:this.sybm.getOrgId(),sbwhr:this.sbwhr.getClerkCode()
                  },
                 success: function(form, action) {
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.constructionInsertWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:", action.result.message);
                 }
         	});
         }
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	var sybmbh=record[0].get('sybmbh');
         	var sbwhr=record[0].get('sbwhr');
         	if(this.sybm.getValue()!=record[0].get('sybm')){
         		if(this.sybm.getOrgId()==null||this.sybm.getOrgId()==''){
         			Ext.MessageBox.alert("系统提示:", "请选择使用部门");
         			return false;
         		}
         		sybmbh=this.sybm.getOrgId();
         	}
         	if(this.sbwhr.getValue()!=record[0].get('sbwhrxm')){
         		if(this.sbwhr.getClerkCode()==null||this.sbwhr.getClerkCode()==''){
         			Ext.MessageBox.alert("系统提示:", "请选择维护人");
         			return false;
         		}
         		sbwhr=this.sbwhr.getClerkCode();
         	}
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sb/sbSbxx/update', 
                 method: 'POST',
                 params:{
                 	id:record[0].get('id'),sybmbh:sybmbh,sbwhr:sbwhr
                 },
                 success: function(form, action) { 
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictTypeGrid.constructionUpdateWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:", action.result.message);
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
        this.sbSbxx = new DictTypeForm();
        this.sbSbxx.buttons[0].show();   //隐藏添加按钮
    	this.sbSbxx.buttons[1].hide();   //显示修改按钮
        DictTypeInsertWindow.superclass.constructor.call(this, {
            title: "添加设备信息",
            width: 700,
            anchor: '100%',
            autoHeight:true,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.sbSbxx]
        });
    }
});

/***************************************DictTypeUpdateWindow组件**************************************************/
DictTypeUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.sbSbxx = new DictTypeForm();
    	this.sbSbxx.buttons[0].hide();   //隐藏添加按钮
    	this.sbSbxx.buttons[1].show();   //显示修改按钮
    	DictTypeUpdateWindow.superclass.constructor.call(this, {
        	title: "修改设备信息",
            width: 700,
            anchor: '100%',
            autoHeight:true,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.sbSbxx]
        });
    }
});

/***************************************SbwxjlForm组件**************************************************/
SbwxjlForm = Ext.extend(Ext.ux.Form, {
	
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
		
        
		//this.wxzt = this.createCombo('<span style="color:red">*</span>维修状态', 'zdz', 'zdmc', 'wxzt', '95%', PROJECT_NAME+'/sbgl/SbSbwxjh/getZt');
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
	    this.gzms = this.createTextArea('<span style="color:red">*</span>故障描述','gzms','90%','95%', null);
	    this.gzms.allowBlank = false;
	    this.gzlb = this.createCombo('<font color="red">*</font>故障类别','zdz', 'zdmc','gzlb','95%',PROJECT_NAME+'/sbgl/SbSbwxjl/getGzlb');
	    this.gzlb.store.load();
	    this.gzlb.allowBlank = false;
		//this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   
        

		SbwxjlForm.superclass.constructor.call(this, {
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
				//{text: '选择维修计划', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
				{text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
				{text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
            ]
        });
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
        	var record = dictTypeGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sbgl/SbSbwxjl/save',   
                 method: 'POST',
                 params:{
                  	//id:record[0].get('id'),wxdh:record[0].get('wxdh')
                	// wxdw:this.wxdw.getOrgId(),wxfzr:this.wxfzr.getClerkCode(),wxsqr:this.wxsqr.getClerkCode()
                  },
                 success: function(form, action) {
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.sbwxjlWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:",BLANKSTR + "添加失败!" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});
/***************************************SbwxjlWindow组件**************************************************/
SbwxjlWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new SbwxjlForm();
        this.constructionForm.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //显示修改按钮
    	SbwxjlWindow.superclass.constructor.call(this, {
            title: "设备维修",
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
/***************************************SbbyjlForm组件**************************************************/
SbbyjlForm = Ext.extend(Ext.ux.Form, {
	
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
        
        this.wxjb = this.createCombo('<span style="color:red">*</span>保养级别', 'zdz', 'zdmc', 'wxjb', '95%', PROJECT_NAME+'/sbgl/SbSbbyjl/getByjb');
		this.wxjb.store.load();
		this.wxjb.allowBlank = false;
		
		this.wxzt = this.createCombo('<span style="color:red">*</span>保养状态', 'zdz', 'zdmc', 'wxzt', '95%', PROJECT_NAME+'/sbgl/SbSbbyjh/getYxzt');
		this.wxzt.store.load();
		this.wxzt.allowBlank = false;
		//this.wxfzr = this.createTextField('<font color="red">*</font>保养人','wxfzr','95%','',null,100,'长度超过不能100');
		this.wxfzr = new wxpt.ClerkSingelSelect('<font color="red">*</font>保养人','wxfzr','wxfzr','95%');
	    this.wxfzr.allowBlank = false;
	    //this.wxsqr = this.createTextField('<font color="red">*</font>申请人','wxsqr','95%','',null,100,'长度超过不能100');
	    this.wxsqr = new wxpt.ClerkSingelSelect('<font color="red">*</font>申请人','wxsqr','wxsqr','95%');
	    this.wxsqr.allowBlank = false;
	    //this.wxdw = this.createTextField('<font color="red">*</font>保养单位','wxdw','95%','',null,100,'长度超过不能100');
	    this.wxdw = new wxpt.OrgSingelSelect('<font color="red">*</font>保养单位','wxdw','wxdw','95%');
	    this.wxdw.allowBlank = false;
	    
//		this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   
        

	    SbbyjlForm.superclass.constructor.call(this, {
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
            	this.wxzt
            ],
            buttonAlign :'center',
            buttons: [
//				{text: '选择设备', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
				{text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
				{text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
            ]
        });
     },
     addFormClick: function() {
    	 if(this.wxdw.getOrgId()==null||this.wxdw.getOrgId()==''){
       		 Ext.MessageBox.alert("系统提示:", "请选择保养单位");
      		 return false;
      	 }
      	 if(this.wxfzr.getClerkCode()==null||this.wxfzr.getClerkCode()==''){
      		 Ext.MessageBox.alert("系统提示:", "请选择保养人");
      		 return false;
      	 }
      	 if(this.wxsqr.getClerkCode()==null||this.wxsqr.getClerkCode()==''){
      		 Ext.MessageBox.alert("系统提示:", "请选择申请人");
      		 return false;
      	 }
         if(this.getForm().isValid()) {
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sbgl/SbSbbyjl/save',   
                 method: 'POST',
                 success: function(form, action) {
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.sbbyjlWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:",BLANKSTR + "添加失败!" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});

/***************************************SbbyjlWindow组件**************************************************/
SbbyjlWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new SbbyjlForm();
        this.constructionForm.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //显示修改按钮
    	SbbyjlWindow.superclass.constructor.call(this, {
            title: "设备保养",
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

/***************************************LookForm组件**************************************************/
LookForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
		this.sbmc = this.createTextField('<font color="red">*</font>设备名称','sbmc','95%','',null,100,'长度超过不能100');
		this.sbmc.allowBlank = false;
		this.sbmc.readOnly = true;
		
		this.sbbh = this.createTextField('<font color="red">*</font>设备编号','sbbh','95%','',null,100,'长度超过不能100');
		this.sbbh.allowBlank = false;
		this.sbbh.readOnly = true;
		
		this.sbxh = this.createTextField('<font color="red">*</font>规格型号','sbxh','95%','',null,100,'长度超过不能100');
		this.sbxh.allowBlank = false;
		this.sbxh.readOnly = true;
		
		this.sblb = this.createCombo('<span style="color:red">*</span>设备类别', 'zdz', 'zdmc', 'sblb', '95%', PROJECT_NAME+'/sb/sbSbxx/getDicByLx');
		this.sblb.store.load();
		this.sblb.allowBlank = false;
		this.sblb.readOnly = true;

		this.sbjb = this.createTextField('设备级别','sbjb','95%','',null,32,'长度超过不能32');
		this.sbjb.readOnly = true;
		//this.sybm = this.createTextField('使用部门','sybm','96.8%','',null,32,'长度超过不能32');
		this.sybm = new wxpt.OrgSingelSelect('使用部门','sybm','sybm','95%');
		this.sybm.readOnly = true;
		this.syfw = this.createTextField('使用范围','syfw','95%','',null,256,'长度超过不能256');
		this.syfw.readOnly = true;
		this.sccj = this.createTextField('生产厂家','sccj','95%','',null,256,'长度超过不能256');
		this.sccj.readOnly = true;
		this.ccbh = this.createTextField('出厂编号','ccbh','93.8%','',null,32,'长度超过不能32');
		this.ccbh.readOnly = true;
		this.ccrq = this.createDateField('出厂日期','ccrq','Y-m-d','95%');
		this.ccrq.value = new Date().format('Y-m-d');
		this.ccrq.readOnly = true;
		this.gmrq = this.createDateField('购买日期','gmrq','Y-m-d','95%');
		this.gmrq.value = new Date().format('Y-m-d');
		this.gmrq.readOnly = true;
		this.gmjg = new Ext.form.NumberField({
            fieldLabel: '购买价格',
            name: 'gmjg',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '93.8%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.gmjg.readOnly = true;
		this.jdzq = new Ext.form.NumberField({
            fieldLabel: '检定周期',
            name: 'jdzq',
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
             		var winForm = win.sbSbxx;
             		var jddw=winForm.jddw.getValue();
             		var scrq=winForm.scjdrq.getValue();//.format('Y-m-d');
             		if(jddw!=''&&scrq!=''){
             			var date=scrq;
             			if(jddw==0){
             				date.setYear(parseInt(scrq.getFullYear())+parseInt(e.value));
             			}else if(jddw==1){
             				date.setMonth(parseInt(scrq.getMonth())+parseInt(e.value));
             			}else if(jddw==2){
             				date.setDate(parseInt(scrq.getDate())+parseInt(e.value));
             			}
             			winForm.xcjdrq.setValue(date);
             		}
             	}
             }
        });
		this.jdzq.readOnly = true;
		this.jddw = this.createCombo1(null, 'zdz', 'zdmc', 'jddw', '85.8%', PROJECT_NAME+'/sb/sbSbxx/getDicByZl');
		this.jddw.store.load();
		this.jddw.allowBlank = false;
		this.jddw.readOnly = true;
		this.syqx = new Ext.form.NumberField({
            fieldLabel: '使用期限',
            name: 'syqx',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.syqx.readOnly = true;
		this.scjdrq = this.createDateField1('上次检定日期','scjdrq','Y-m-d','95%');
		this.scjdrq.value = new Date().format('Y-m-d');
		this.scjdrq.readOnly = true;
		this.xcjdrq = this.createDateField('下次检定日期','xcjdrq','Y-m-d','93.8%');
		this.xcjdrq.value = new Date().format('Y-m-d');
		this.xcjdrq.readOnly = true;
		this.syzt = this.createCombo('使用状态', 'zdz', 'zdmc', 'syzt', '95%', PROJECT_NAME+'/sb/sbSbxx/getDicBySy');
		this.syzt.store.load();
		this.syzt.readOnly = true;
		//this.sbwhr = this.createTextField('设备维护人','sbwhr','93.8%','',null,32,'长度超过不能32');
		this.sbwhr = new wxpt.ClerkSingelSelect('设备维护人','sbwhr','sbwhr','93.8%');
		this.sbwhr.readOnly = true;
		this.zjff = this.createCombo('折旧方法', 'zdz', 'zdmc', 'zjff', '95%', PROJECT_NAME+'/sb/sbSbxx/getDicByZj');
		this.zjff.store.load();
		this.zjff.readOnly = true;
		this.zcyz = new Ext.form.NumberField({
            fieldLabel: '资产原值',
            name: 'zcyz',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.zcyz.readOnly = true;
		this.jcl = new Ext.form.NumberField({
            fieldLabel: '净残率%',
            name: 'jcl',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.jcl.readOnly = true;
		this.zjnx = new Ext.form.NumberField({
            fieldLabel: '折旧年限',
            name: 'zjnx',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '93.8%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.zjnx.readOnly = true;
		this.zcjz = new Ext.form.NumberField({
            fieldLabel: '资产净值',
            name: 'zcjz',
            allowBlank: true,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '93.8%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.zcjz.readOnly = true;
		this.sbjb.allowBlank = false;
		this.sybm.allowBlank = false;
		this.syfw.allowBlank = false;
		this.sccj.allowBlank = false;
		this.ccbh.allowBlank = false;
		this.ccrq.allowBlank = false;
		this.gmrq.allowBlank = false;
		this.gmjg.allowBlank = false;
		this.jdzq.allowBlank = false;
		this.syqx.allowBlank = false;
		this.scjdrq.allowBlank = false;
		this.xcjdrq.allowBlank = false;
		this.syzt.allowBlank = false;
		this.sbwhr.allowBlank = false;
		this.zjff.allowBlank = false;
		this.zcyz.allowBlank = false;
		this.jcl.allowBlank = false;
		this.zjnx.allowBlank = false;
		this.xcjdrq.readOnly = true;
		
		
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
                           this.sbmc
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                         this.sbbh
                    ]  
                }) 
            ]  
        });  
    	var pnRow101=new Ext.Panel({
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
                         this.sblb
                    ]  
                })
            ]  
        });  
        var pnRow2=new Ext.Panel({  
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
                        this.gmrq
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.zcyz 
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sbwhr 
                    ]  
                })  
            ]  
        });
        var pnRow201=new Ext.Panel({  
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
                        this.zjff
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jcl 
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.zjnx 
                    ]  
                })  
            ]  
        });
        var pnRow3=new Ext.Panel({  
            layout:'column',  
            border:false,  
            items:[  
                new Ext.Panel({  
                    columnWidth:.2,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jdzq  
                    ]  
                }),
                new Ext.Panel({  
                    columnWidth:.1,  
                    layout:'form',  
                    border:false,  
                    labelWidth:1,  
                    labelAlign:'right',  
                    items:[  
                        this.jddw  
                    ]  
                }),
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.scjdrq  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.xcjdrq  
                    ]  
                })  
            ]  
        });  
        var pnRow301=new Ext.Panel({  
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
                        this.syzt  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sybm  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.zcjz  
                    ]  
                })
            ]  
        });  
        var pnRow4=new Ext.Panel({//当然这里直接在FormPanel中添加TextField就可以了，因为只有一行，但是为了一致以及对齐方便我这里还是放到了panel中  
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
                        this.syfw  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.syqx  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.gmjg  
                    ]  
                })  
            ]
        });  
        var pnRow5=new Ext.Panel({  
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
                        this.sccj  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.ccrq  
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.35,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.ccbh  
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
				pnRow101,
				pnRow2, 
				pnRow201,
				pnRow3,  
				pnRow4,  
				pnRow5,  
				pnRow301  
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
              		var winForm = win.sbSbxx;
              		var jdzq=winForm.jdzq.getValue();
              		var scrq=winForm.scjdrq.getValue();//.format('Y-m-d');
              		if(jdzq!=''&&scrq!=''){
              			var date=scrq;
              			if(e.value==0){
              				date.setYear(parseInt(scrq.getFullYear())+parseInt(jdzq));
              			}else if(e.value==1){
              				date.setMonth(parseInt(scrq.getMonth())+parseInt(jdzq));
              			}else if(e.value==2){
              				date.setDate(parseInt(scrq.getDate())+parseInt(jdzq));
              			}
              			winForm.xcjdrq.setValue(date);
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
              		var winForm = win.sbSbxx;
              		var jdzq=winForm.jdzq.getValue();
              		var jddw=winForm.jddw.getValue();//.format('Y-m-d');
              		if(jdzq!=''&&jddw!=''){
              			var date=new Date();
              			//var arr[]=(e.value).split("-");
              			date.setFullYear((e.value).substring(0,4),(parseInt((e.value).substring(6,7))-1),(e.value).substring(8));
              			if(jddw==0){
              				date.setYear(parseInt(date.getFullYear())+parseInt(jdzq));
              			}else if(jddw==1){
              				date.setMonth(parseInt(date.getMonth())+parseInt(jdzq));
              			}else if(jddw==2){
              				date.setDate(parseInt(date.getDate())+parseInt(jdzq));
              			}
              			winForm.xcjdrq.setValue(date);
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
/***************************************LookWindow组件**************************************************/
LookWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.look = new LookForm();
    	//this.sbSbxx.buttons[0].hide();   //隐藏添加按钮
    	//this.sbSbxx.buttons[1].show();   //显示修改按钮
    	LookWindow.superclass.constructor.call(this, {
        	title: "设备信息",
            width: 700,
            anchor: '100%',
            autoHeight:true,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.look]
        });
    }
});
/***************************************SbzjForm组件**************************************************/
SbzjForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
		this.sbbh = this.createTextField('<font color="red">*</font>设备编号','sbbh','95%','',null,100,'长度超过不能100');
        this.sbbh.readOnly = true;
        
        this.sbmc = this.createTextField('<font color="red">*</font>设备名称','sbmc','95%','',null,100,'长度超过不能100');
        this.sbmc.readOnly = true;
        
        this.sbxh = this.createTextField('<font color="red">*</font>设备型号','sbxh','95%','',null,100,'长度超过不能100');
        this.sbxh.readOnly = true;
        
        this.sybm = this.createTextField('<font color="red">*</font>使用部门','sybm','95%','',null,100,'长度超过不能100');
        this.sybm.readOnly = true;
        
        this.gmrq = this.createDateField('<span style="color:red">*</span>购买日期','gmrq','Y-m-d','95%');
		this.gmrq.value = new Date().format('Y-m-d');
		
		this.zcyz = new Ext.form.NumberField({
            fieldLabel: '<span style="color:red">*</span>资产原值',
            name: 'zcyz',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.zcyz.allowBlank = false;
		
		this.zjff = this.createCombo('<span style="color:red">*</span>折旧方法', 'zdz', 'zdmc', 'zjff', '95%', PROJECT_NAME+'/sb/sbSbxx/getDicByZj');
		this.zjff.store.load();
	    
		this.jcl = new Ext.form.NumberField({
            fieldLabel: '<span style="color:red">*</span>净残率%',
            name: 'jcl',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.jcl.allowBlank = false;
		
		this.zjnx = new Ext.form.NumberField({
            fieldLabel: '<span style="color:red">*</span>折旧年限',
            name: 'zjnx',
            allowBlank: false,
            allowNegative :false,
            maxLength:10,
            maxLengthText:'长度超过不能10', 
            anchor: '95%',
            regex: /^\d+(\.\d+)?$/,
            blankText: '该选项为必填项,请输入内容...'
        });
		this.zjnx.allowBlank = false;

	    SbzjForm.superclass.constructor.call(this, {
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
            	this.gmrq,
            	this.zjff,
            	this.zcyz,
            	this.jcl,
            	this.zjnx
            ],
            buttonAlign :'center',
            buttons: [
//				{text: '选择设备', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
				{text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.updateFormClick, scope: this},  
				{text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
            ]
        });
     },
     updateFormClick: function() {       //修改
         if(this.getForm().isValid()) {
         	var record = dictTypeGrid.getSelectionModel().getSelections();
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sb/sbSbxx/updatezj',
                 method: 'POST',
                 params:{
                 	id:record[0].get('id')
                 },
                 success: function(form, action) { 
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "折旧成功!" + BLANKSTR);
                 	dictTypeGrid.sbzjWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	//Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
                 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "折旧失败!" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});
/***************************************SbzjWindow组件**************************************************/
SbzjWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new SbzjForm();
        this.constructionForm.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //显示修改按钮
    	SbzjWindow.superclass.constructor.call(this, {
            title: "设备折旧",
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

/***************************************SbjyjlForm组件**************************************************/
SbjyjlForm = Ext.extend(Ext.ux.Form, {
	
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
        
        this.jyfs = this.createCombo('<span style="color:red">*</span>检验方式', 'zdz', 'zdmc', 'jyfs', '95%', PROJECT_NAME+'/sbgl/SbSbjyjh/getJyfsByLx');
		this.jyfs.store.load();
		this.jyfs.allowBlank = false;
        
        var sjsj =  new Ext.form.DateField({
			fieldLabel: '<font color="red">*</font>送检时间',
			name: "sjsj",
			format: "Y-m-d",
			anchor: '95%',
			allowBlank: false,
			editable:false,//不能手动输入
			blankText: '请选择时间'
		});
        
        //this.sjr = this.createTextField('<font color="red">*</font>送检人','sjr','95%','',null,100,'长度超过不能100');
        this.sjr = new wxpt.ClerkSingelSelect('<font color="red">*</font>送检人','sjr','sjr','95%');
        this.sjr.allowBlank = false;
        
        //this.jydw = this.createTextField('<font color="red">*</font>检验单位','jydw','95%','',null,100,'长度超过不能100');
        this.jydw = new wxpt.OrgSingelSelect('<font color="red">*</font>检验单位','jydw','jydw','95%');
        this.jydw.allowBlank = false;
        
        this.lxdh = new Ext.form.NumberField({
            fieldLabel: '<font color="red">*</font>联系电话',
            name: 'lxdh',
            allowBlank: true,
            allowNegative :false,
            maxLength:11,
            maxLengthText:'长度超过不能11位', 
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        this.lxdh.allowBlank = false;
        
		this.jyzt = this.createCombo('<span style="color:red">*</span>检验状态', 'zdz', 'zdmc', 'jyzt', '95%', PROJECT_NAME+'/sbgl/SbSbjyjl/getJyztByLx');
		this.jyzt.store.load();
		this.jyzt.allowBlank = false;
		
		this.jyfy = new Ext.form.NumberField({
            fieldLabel: '检验费用',
            name: 'jyfy',
            allowBlank: true,
            allowNegative :false,
            anchor: '95%',
            cls:'forbiddenZH',
            blankText: '该选项为必填项,请输入内容...',
            hidden:false
        });
        this.jyfy.allowBlank = true;
        
        this.bz = new Ext.form.TextArea({
            fieldLabel: '备注',
            name: 'bz',
            readOnly: false,
            anchor: '95%',
            height:60,
            maxLength: 256,
            maxLengthText: '最大字符数256！'
        });
        this.bz.allowBlank = true;
//        this.sbSbxxOpenWindow = new SbSbxxOpenWindow();   

        var pnRow1=new Ext.Panel({  
            layout:'column',  
            border:false,  
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
                        sjsj 
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.sjr
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jydw 
                    ]  
                }),  
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.lxdh
                    ]  
                }),  
                new Ext.Panel({  
                    columnWidth:.5,  
                    layout:'form',  
                    border:false,  
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyzt 
                    ]  
                }),  
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.jyfy
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
                    labelWidth:80,  
                    labelAlign:'right',  
                    items:[  
                        this.bz 
                    ]  
                })  
            ]  
        });
        
        SbjyjlForm.superclass.constructor.call(this, {
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
            	pnRow6,
            	pnRow7,
            ],
            buttonAlign :'center',
            buttons: [
//				{text: '选择检验计划', width: 20,iconCls:'add', handler: this.addSbClick, scope: this},
				{text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
				{text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
            ]
        });
     },
     addFormClick: function() {
         if(this.getForm().isValid()) {
        	 if(this.jydw.getOrgId()==null||this.jydw.getOrgId()==''){
           		 Ext.MessageBox.alert("系统提示:", "请选择检验单位");
          		 return false;
          	 }
          	 if(this.sjr.getClerkCode()==null||this.sjr.getClerkCode()==''){
          		 Ext.MessageBox.alert("系统提示:", "请选择送检人");
          		 return false;
          	 }
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sbgl/SbSbjyjl/saveSbjyjl',   
                 method: 'POST',
                 success: function(form, action) {
                 	
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.sbjyjlWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	
                 	Ext.MessageBox.alert("系统提示:",BLANKSTR + "添加失败!" + BLANKSTR);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});

/***************************************SbjyjlWindow组件**************************************************/
SbjyjlWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
        this.constructionForm = new SbjyjlForm();
        SbjyjlWindow.superclass.constructor.call(this, {
            title: "添加设备检验记录",
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
            		{name:'id'}, {name:'sblb'}, {name:'sbmc'}, {name:'sbbh'}, {name:'sbxh'},
		            {name:'sbjb'}, {name:'sybmbh'}, {name:'sybm'}, {name:'syfw'}, {name:'sccj'}, {name:'ccrq'},{name:'ccbh'},{name:'gmrq'},{name:'gmjg'}
		            ,{name:'jdzq'},{name:'syqx'},{name:'scjdrq'},{name:'xcjdrq'},{name:'syzt'},{name:'sbwhr'},{name:'zjff'},{name:'zcyz'},{name:'jcl'}
		            ,{name:'zjnx'},{name:'zcjz'},{name:'jddw'},{name:'jdzqs'},{name:'sbwhrxm'},{name:'lbmc'},{name:'syztmc'},{name:'zjffmc'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
            	//{text:'一键折旧',iconCls: 'edit',handler:this.onZheJiuClick,scope:this}//,'-',
            	//{xtype:'label',text:'人员'},user,'-',
            	//{xtype:'label',text:'部门'},org,
            	/*'-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
					var params = {};
	       			var jgdh = org.getOrgId();
	       			//var jgmc = org.getOrgName();
	       			var hydh = user.getClerkCode();
	       			//var name = user.getClerkName();
	       			alert("部门编号："+jgdh);
	       			//alert("部门名称："+jgmc);
	       			alert("人员编号："+hydh);
	       			//alert("人员名称："+name);
	   				}
   				},*/
            	
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        this.sbwxjlWindow = new SbwxjlWindow();
        this.sbbyjlWindow = new SbbyjlWindow();
        this.sbjyjlWindow = new SbjyjlWindow();
        this.sbzjWindow = new SbzjWindow();
        this.dictTypeLookWindow = new LookWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '设备信息列表',
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
                                        {header: '操作', width: 230, dataIndex: '', align:"center",  sortable: true,hidden: false,
                        	                renderer:function(value, cellmeta, record){
                        						if(record.get("syzt") != 6){
                        	        			   return "<a href='#' onclick='dictTypeGrid.onLook()'>查看</a>&nbsp;&nbsp;&nbsp;" +
                        	        			   		"<a href='#' onclick='dictTypeGrid.onWxClick()'>维修</a>&nbsp;&nbsp;&nbsp;" +
                        	        			   		"<a href='#' onclick='dictTypeGrid.onByClick()'>保养</a>&nbsp;&nbsp;&nbsp;" +
                        	        			   		"<a href='#' onclick='dictTypeGrid.onJyClick()'>检验</a>&nbsp;&nbsp;&nbsp;" +
                        	        			   		"<a href='#' onclick='dictTypeGrid.onZjClick()'>折旧</a>&nbsp;&nbsp;&nbsp;" +
                        	        			   		"<a href='#' onclick='dictTypeGrid.onBfclClick()'>报废</a>&nbsp;&nbsp;&nbsp;";
                        						}else{
                        						   return "<a href='javascript:;' onclick='dictTypeGrid.onLook()'>查看</a>&nbsp;&nbsp;&nbsp;" +
                   	        			   		"<a href='javascript:;' onclick='' style='color:grey;'>维修</a>&nbsp;&nbsp;&nbsp;" +
                	        			   		"<a href='javascript:;' onclick='' style='color:grey;'>保养</a>&nbsp;&nbsp;&nbsp;" +
                	        			   		"<a href='javascript:;' onclick='' style='color:grey;'>检验</a>&nbsp;&nbsp;&nbsp;" +
                	        			   		"<a href='javascript:;' onclick='' style='color:grey;'>折旧</a>&nbsp;&nbsp;&nbsp;" +
                	        			   		"<a href='javascript:;' onclick='' style='color:grey;'>报废</a>&nbsp;&nbsp;&nbsp;";
                        						}
                        	        		}
                        				},
                      		            {header:'设备类别',dataIndex:'lbmc',width:100,sortable:true,hidden:false},
                      		            {header:'设备名称',dataIndex:'sbmc',width:100,sortable:true,hidden:false},
                      		            {header:'设备编号',dataIndex:'sbbh',width:100,sortable:true,hidden:false},
                      		            {header:'设备型号',dataIndex:'sbxh',width:100,sortable:true,hidden:false},
//                      		            {header:'设备级别',dataIndex:'sbjb',width:100,sortable:true,hidden:false},
                      		            {header:'使用部门',dataIndex:'sybm',width:100,sortable:true,hidden:false},
                      		            {header:'使用范围',dataIndex:'syfw',width:100,sortable:true,hidden:false},
                      		            {header:'生产厂家',dataIndex:'sccj',width:100,sortable:true,hidden:false},
                      		            {header:'出厂编号',dataIndex:'ccbh',width:100,sortable:true,hidden:false},
                      		            {header:'出厂日期',dataIndex:'ccrq',width:100,sortable:true,hidden:false},
                      		            {header:'购买日期',dataIndex:'gmrq',width:100,sortable:true,hidden:false},
                      		            {header:'购买价格',dataIndex:'gmjg',width:100,sortable:true,hidden:false},
                      		            {header:'检定周期',dataIndex:'jdzqs',width:100,sortable:true,hidden:false},
                      		            {header:'使用期限',dataIndex:'syqx',width:100,sortable:true,hidden:false},
                      		            {header:'上次检定日期',dataIndex:'scjdrq',width:100,sortable:true,hidden:false},
                      		            {header:'下次检定日期',dataIndex:'xcjdrq',width:100,sortable:true,hidden:false},
                      		            {header:'使用状态',dataIndex:'syztmc',width:100,sortable:true,hidden:false},
                      		            {header:'设备维护人',dataIndex:'sbwhrxm',width:100,sortable:true,hidden:false},
                      		            {header:'折旧方法',dataIndex:'zjffmc',width:100,sortable:true,hidden:false},
                      		            {header:'资产原值',dataIndex:'zcyz',width:100,sortable:true,hidden:false},
                      		            {header:'净残率',dataIndex:'jcl',width:100,sortable:true,hidden:false},
                      		            {header:'折旧年限',dataIndex:'zjnx',width:100,sortable:true,hidden:false},
                      		            {header:'资产净值',dataIndex:'zcjz',width:100,sortable:true,hidden:false}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    onAddClick: function() {
    	tjorxg=0;
    	var win = this.constructionInsertWindow;
    	tjorxg=1;
    	win.show();
    	win.sbSbxx.getForm().reset();
    },
    onLook: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				var win = this.dictTypeLookWindow;
   		    	var winForm = win.look;
   		    	winForm.getForm().loadRecord(vrecord);
   		    	winForm.sbwhr.setValue(vrecord.data.sbwhrxm);
   		    	win.show();
   			}else{
   				Ext.Msg.alert('系统提示', '不能查看多条记录..');
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
   				if(vrecord.data.syzt!='6'){
   					tjorxg=0;
   					var win = this.constructionUpdateWindow;
   					var winForm = win.sbSbxx;
   					winForm.sblb.setValue(vrecord.data.sblb);
   					winForm.sbmc.setValue(vrecord.data.sbmc);
   					//winForm.sbbh.setValue(vrecord.data.sbbh);
   					winForm.sbxh.setValue(vrecord.data.sbxh);
   					winForm.sbjb.setValue(vrecord.data.sbjb);
   					winForm.sybm.setValue(vrecord.data.sybm);
   					winForm.syfw.setValue(vrecord.data.syfw);
   					winForm.sccj.setValue(vrecord.data.sccj);
   					winForm.ccbh.setValue(vrecord.data.ccbh);
   					winForm.ccrq.setValue(vrecord.data.ccrq);
   					winForm.gmrq.setValue(vrecord.data.gmrq);
   					winForm.gmjg.setValue(vrecord.data.gmjg);
   					winForm.jdzq.setValue(vrecord.data.jdzq);
   					winForm.syqx.setValue(vrecord.data.syqx);
   					winForm.scjdrq.setValue(vrecord.data.scjdrq);
   					winForm.xcjdrq.setValue(vrecord.data.xcjdrq);
   					winForm.syzt.setValue(vrecord.data.syzt);
   					winForm.sbwhr.setValue(vrecord.data.sbwhrxm);
   					winForm.zjff.setValue(vrecord.data.zjff);
   					winForm.zcyz.setValue(vrecord.data.zcyz);
   					winForm.jcl.setValue(vrecord.data.jcl);
   					winForm.zjnx.setValue(vrecord.data.zjnx);
   					winForm.jddw.setValue(vrecord.data.jddw);
   					tjorxg=2;
   					win.show();
   				}else{
   					Ext.Msg.alert('系统提示', '只可修改未报废的记录');
   				}
   			}else{
   				Ext.Msg.alert('系统提示', '不能修改多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}    	
    },
    onWxClick: function() {       //设备维修
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
		    	var count = this.getStore().getTotalCount();
		    	var win = this.sbwxjlWindow;
		    	win.show();
		    	win.constructionForm.sbmc.setValue(vrecord.data.sbmc);
		    	win.constructionForm.sbbh.setValue(vrecord.data.sbbh);
		    	win.constructionForm.sbxh.setValue(vrecord.data.sbxh);
		    	win.constructionForm.sybm.setValue(vrecord.data.sybm);
		    	//win.constructionForm.getForm().reset();
   			}else{
   				Ext.Msg.alert('系统提示', '不能选择多条记录..');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}
    },
    onByClick: function() {       //设备保养
    	var records=this.getSelectionModel().getSelections();
    	if(records.length > 0) {
    		if(records.length == 1){
    			vrecord = records[0];
    			var count = this.getStore().getTotalCount();
    			var win = this.sbbyjlWindow;
    			win.show();
    			win.constructionForm.sbmc.setValue(vrecord.data.sbmc);
    			win.constructionForm.sbbh.setValue(vrecord.data.sbbh);
    			win.constructionForm.sbxh.setValue(vrecord.data.sbxh);
    			win.constructionForm.sybm.setValue(vrecord.data.sybm);
    			//win.constructionForm.getForm().reset();
    		}else{
    			Ext.Msg.alert('系统提示', '不能选择多条记录..');
    		}
    	}else{
    		Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
    	}
    },
    onZjClick: function() {       //折旧
    	var records=this.getSelectionModel().getSelections();
    	if(records.length > 0) {
    		if(records.length == 1){
    			vrecord = records[0];
    			var count = this.getStore().getTotalCount();
    			var win = this.sbzjWindow;
    			win.constructionForm.sbmc.setValue(vrecord.data.sbmc);
    			win.constructionForm.sbbh.setValue(vrecord.data.sbbh);
    			win.constructionForm.sbxh.setValue(vrecord.data.sbxh);
    			win.constructionForm.sybm.setValue(vrecord.data.sybm);
				win.constructionForm.gmrq.setValue(vrecord.data.gmrq);
				win.constructionForm.zjff.setValue(vrecord.data.zjff);
				win.constructionForm.zcyz.setValue(vrecord.data.zcyz);
				win.constructionForm.jcl.setValue(vrecord.data.jcl);
				win.constructionForm.zjnx.setValue(vrecord.data.zjnx);
    			win.show();
    		}else{
    			Ext.Msg.alert('系统提示', '不能选择多条记录..');
    		}
    	}else{
    		Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
    	}
    },
    onJyClick: function() {       //设备检验
    	var records=this.getSelectionModel().getSelections();
    	if(records.length > 0) {
    		if(records.length == 1){
    			vrecord = records[0];
    			var count = this.getStore().getTotalCount();
    			var win = this.sbjyjlWindow;
    			win.show();
    			win.constructionForm.sbmc.setValue(vrecord.data.sbmc);
    			win.constructionForm.sbbh.setValue(vrecord.data.sbbh);
    			win.constructionForm.sbxh.setValue(vrecord.data.sbxh);
    			win.constructionForm.sybm.setValue(vrecord.data.sybm);
    			//win.constructionForm.getForm().reset();
    		}else{
    			Ext.Msg.alert('系统提示', '不能选择多条记录..');
    		}
    	}else{
    		Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
    	}
    },
    onBfclClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				Ext.Msg.confirm("提醒信息", "确定要将该设备报废处理吗",function(btn){
   					if (btn == 'yes') {
   				       	Ext.Ajax.request({
   					       	   url:PROJECT_NAME+'/sb/sbSbxx/sbBfcl',   
   					       	   method : 'POST', 
   					       	   params: { id: vrecord.data.id},
   				               success: function(form, action) { 
   					               	var obj = Ext.util.JSON.decode(form.responseText);
   					               	if(obj.success == true){   //true / false
   					               		 Ext.MessageBox.alert("系统提示:", BLANKSTR + "操作成功!" + BLANKSTR);
   						               	 dictTypeGrid.store.reload();
   					               	}else{
   					               		 Ext.MessageBox.alert("系统提示:", "操作失败!");
   						               	 dictTypeGrid.store.reload();
   					               	}
   					              
   				               },
   				               failure: function(form, action) {
   				            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "操作失败!" + BLANKSTR);
   				               	 //Ext.MessageBox.alert("系统提示：",action.result.failure);
   				               }
   					       	});					
   					}
   		    	});	
   			}else{
   				Ext.Msg.alert('系统提示', '不能更改多条记录..');
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
				       	   url:PROJECT_NAME+'/sb/sbSbxx/delete',   
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
			            	   //Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改失败!" + BLANKSTR);
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