var ENTITY_URL_LIST = "pager";
var ENTITY_URL_SAVE = "fileUload";
var ENTITY_URL_UPDATE = "update";
var ENTITY_URL_DELETE = "delete";
var PAGESIZE=50;
/*
 * 主页轮播图 
 * @author liujiansen
 * @date 2016-03-07
 */
ZyLbt = Ext.extend(Ext.ux.Form,{
	constructor: function(){
		this.tp = new Ext.ux.form.FileUploadField({
			anchor:'98%',
			name: 'file',
            emptyText: '请选择...',
            labelWidth: 67,
            fieldLabel: '<font color="red">*</font>上传图片:',
            buttonCfg: {
                text: '浏览'
            }
	    });
		this.tpmc = this.createTextField('<font color="red">*</font>图片名称:','tpmc','98%','',null,100,'长度超过不能100');
	    this.tpmc.allowBlank = false;
	    
		this.url = this.createTextField('跳转地址:','url','98%','',null,200,'长度超过不能200');
		this.tpbcid = this.createTextField('跳转地址:','tpbcid','98%','',null,200,'长度超过不能200');
//		this.tpbcid = new Ext.form.TextField({
//			   fieldLabel: "ceshi",
//			   name: 'tpbcid',
//			   hidden: true
//			//   hideLabel:true, 
//			  });
	    this.tpbcid.hidden = true;
	    
		this.lx = new Ext.form.ComboBox({
			name:'lx',
			store:new Ext.data.SimpleStore({
				fields:['value','text'],
				data:[
				      ['1','跳转'],
				      ['2','展示'],
				      ['3','作废']]
			}),
			fieldLabel:"<font color='red'>*</font>文件类型",
			mode:'local',
			triggerAction:'all',
			hideTrigger:false,
			anchor: '95%',
			editable:false,
			valueField:'value',
			displayField:'text'
		});
		this.lx.setValue(1);
		
		this.px = new Ext.form.ComboBox({
			name:'px',
			store:new Ext.data.SimpleStore({
				fields:['value','text'],
				data:[
				      ['1','1'],
				      ['2','2'],
				      ['3','3'],
				      ['4','4'],
				      ['5','5']]
			}),
			fieldLabel:"<font color='red'>*</font>排   &nbsp; &nbsp;序:",
			mode:'local',
			triggerAction:'all',
			hideTrigger:false,
			anchor: '98%',
			editable:false,
			valueField:'value',
			displayField:'text'
		});
		this.px.setValue(1);
		
		this.lx.allowBlank = false;
		this.px.allowBlank = false;
		this.tpbcid.allowBlank = true;
		this.url.allowBlank = true;
        
        ZyLbt.superclass.constructor.call(this, {
	            anchor: '100%',
	            autoHeight:true,
	            labelWidth: 90,
	            labelAlign :'right',
	            frame: true,
	            bodyStyle:"padding: 5px 5px 0",
	            layout: 'tableform',
	            layoutConfig: {columns: 1},
	            items:[
	                this.tp,
	                this.tpbcid,
					this.tpmc,
					this.url,
					this.px
	            ],
	            buttonAlign :'center',
	            buttons: [
	               {text: '保存', width: 20,iconCls: 'save', hidden: false,handler:this.addFormClick,scope:this},
	               {text: '修改', width: 20,iconCls:'edit', hidden: true, handler: this.updateFormClick, scope: this},
	               {text: '重置', width: 20,iconCls:'redo', hidden: true, handler: this.onResumeClick, scope: this},               
	               {text: '清空', width: 20, iconCls:'redo',  handler: this.resetFormClick, scope: this},
	               {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
	            ]
	        });
	},
	addFormClick: function(){
		var tpmc=this.tpmc.getValue();
		var url=this.url.getValue();
		var pxvalue=this.px.getValue();
		if(this.getForm().isValid()){
//			var attachMentFileValue = this.tpmc.getValue();
//	    	   var attachmentType = attachMentFileValue.substring(attachMentFileValue.lastIndexOf(".")+1).toLowerCase();
//	        	if(attachMentFileValue != null && attachMentFileValue != "") {
//	        	    if( attachmentType != "jpg" && attachmentType != "jpeg" && attachmentType != "png" && attachmentType != "gif"){
//	        		    Ext.MessageBox.alert("系统提示:", "上传图片类型为jpg、jpeg、png或gif");
//	        		    return;
//	        		    }
//	        	    }
	       	
	        //设置表单enctype属性
		    $("#ext-gen11").attr("enctype","multipart/form-data");
			this.getForm().submit({
				waitMsg: '正在提交数据...',
				url: ENTITY_URL_SAVE,
				method: 'POST',
				params:{url:url,px:pxvalue,tpmc:tpmc},
				success: function(form,action){
					Ext.Msg.alert("系统提示：","添加成功！");
					zyLbtGrid.store.load({params:{start:0,limit:PAGESIZE}});
					zyLbtGrid.zyLbtWindow.hide();
					
				},
				failure: function(form,action){
					if(action.result=='1'){
						Ext.Msg.alert("系统提示：","添加成功！");
						zyLbtGrid.store.load({params:{start:0,limit:PAGESIZE}});
						zyLbtGrid.zyLbtWindow.hide();
					}else{
						Ext.MessageBox.alert("系统提示：","添加失败！");
					}
				}
			});
		}
	},
	updateFormClick: function(){
		var records = zyLbtGrid.getSelectionModel().getSelections();
		var tp=this.tp.getValue();
		var tpbcid=this.tpbcid.getValue();
		var tpmc=this.tpmc.getValue();
		var url=this.url.getValue();
		var px=this.px.getValue();
		var id=records[0].get('id');
		if(this.getForm().isValid()){
//			var attachMentFileValue = this.tpmc.getValue();
//	    	   var attachmentType = attachMentFileValue.substring(attachMentFileValue.lastIndexOf(".")+1).toLowerCase();
//	        	if(attachMentFileValue != null && attachMentFileValue != "") {
//	        		if( attachmentType != "jpg" && attachmentType != "jpeg" && attachmentType != "png" && attachmentType != "gif"){
//	        		    Ext.MessageBox.alert("系统提示:", "上传图片类型为jpg、jpeg、png或gif");
//	        		    return;
//	        		    }
//	        	    }
	       	
	        //设置表单enctype属性
		    $("#ext-gen13").attr("enctype","multipart/form-data");
			this.getForm().submit({
				waitMsg: '正在提交，请稍后...',
				url: ENTITY_URL_UPDATE,
				method: 'POST',
				params:{url:url,px,px,tpmc:tpmc,id:id},
				success: function(form,action){
					Ext.MessageBox.alert("系统提示：","修改成功！");
					zyLbtGrid.zyLbtUpdateWindow.hide();
					zyLbtGrid.vbbar.doLoad(zyLbtGrid.vbbar.cursor);
				},
				failure: function(form,action){
					if(action.result=='1'){
						Ext.MessageBox.alert("系统提示：","修改成功！");
						zyLbtGrid.zyLbtUpdateWindow.hide();
						zyLbtGrid.vbbar.doLoad(zyLbtGrid.vbbar.cursor);
					}else{
						Ext.MessageBox.alert("系统提示：","修改失败！");
					}
				}
			});
		}
	},
	//关闭
    onCloseClick: function(){
    	if(zyLbtGrid.zyLbtUpdateWindow)
    	zyLbtGrid.zyLbtUpdateWindow.zyLbt.getForm().reset();
        this.ownerCt.hide();
    },
  //清空
    resetFormClick: function() {        
        this.getForm().reset();
    }, 
  //重置
    onResumeClick: function() {        
    	zyLbtGrid.onModifyClick();
    }
	
});

/**************ZyLbtWindow*********************/
ZyLbtWindow = Ext.extend(Ext.Window,{
	
	constructor: function(grid){
		this.zyLbt = new ZyLbt();
		ZyLbtWindow.superclass.constructor.call(this,{
			title: '新增',
			 width: 700,
			 anchor: '100%',
			autoHeight:true,
			resizable : false, //可变尺寸的；可调整大小的
			 plain: true,
			 modal: true,
			closeAction: 'hide',
			items:[this.zyLbt]
		});
	}
});

/********************ZyLbtUpdateWindow组件*************************/
ZyLbtUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.zyLbt = new ZyLbt();
    	this.zyLbt.buttons[0].hide();   //隐藏添加按钮
    	this.zyLbt.buttons[1].show();   //显示修改按钮
    	this.zyLbt.buttons[2].show();   //显示重置按钮
    	this.zyLbt.buttons[3].hide();   //隐藏清空按钮
    	
    	ZyLbtUpdateWindow.superclass.constructor.call(this, {
			title: '修改',
			width: 700,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
            items: [this.zyLbt]
        });
    }
});

/****************ZyLbtGrid***********************/
ZyLbtGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			
			proxy: new Ext.data.HttpProxy({url:ENTITY_URL_LIST,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
		            {name:'id'},
		            {name:'tpmc'},
		            {name:'tpbcid'},
		            {name:'lx'},
		            {name:'url'},
		            {name:'px'}
					 ])
		});
		this.vtbar = new Ext.Toolbar({
			items: [
			       '-',{xtype:'button',text:'添加',iconCls:'add',handler:this.onAddClick,scope:this}, 
			       '-',{xtype:'button',text:'修改',iconCls:'edit',handler:this.onModifyClick,scope:this}, 
			       '-',{xtype:'button',text:'删除',iconCls:'delete',handler:this.onDeleteClick,scope:this}
//			       '-',{xtype:'label',text:'图片名称'},{xtype:'textfield',id:'tpmc'},
//					'-',{xtype:'label',text:'图片类型'},{xtype:'combo',id:'tplx',
//				    	   editable: false,
//				    	   store: new Ext.data.Store({
//				                proxy: new Ext.data.HttpProxy({url: 'getTplx', method: 'POST'}),
//				                reader: new Ext.data.JsonReader({},new Ext.data.Record.create([{name:'zdmc'},{name:'zdz'}])),
//				                autoLoad:true,
//				                listeners:{  
//				                    load : function(store, records, options ){      
//				                        var data ={ "zdz": "", "zdmc": "全部"};      
//				                        var rs = [new Ext.data.Record(data)];      
//				                        store.insert(0,rs);  
//				                    }  
//				                }
//				            }),
//	                       displayField: 'zdmc', //显示文本字段
//	                       valueField: 'zdz',//value值字段id
//	                       mode: 'local',
//	                       blankText:'请选择....',  
//	                       emptyText:'请选择....', 
//	                       triggerAction: 'all',
//	                       selectOnFocus: true,
//	                       typeAhead: true
//			       		},
//			       '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
//			       			var params = {};
//			       			params['tplx']=Ext.getCmp('tplx').getValue();
//			       			zyLbtGrid.vtbar.items.each(function(item,index,length){ 
//		       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
//		       						if (item.getXType() == 'datefield') {
//		       							params[item.getId()] = item.getValue().format(item.format);
//		       						} else {
//		       							params[item.getId()] = item.getValue();
//		       						}
//		       					}
//							});
//	    	   				zyLbtGrid.store.baseParams= params;
//	    	   				zyLbtGrid.store.load({params:{start:0,limit:PAGESIZE}});
//	    	   			}
//	       			},
//					'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
//						zyLbtGrid.vtbar.items.each(function(item,index,length){   
//							if((""+item.getXType()).indexOf("field") != -1) {
//								item.setValue('');
//							}
//						  }); 
//						Ext.getCmp('tplx').setValue('');
//    	   			}}
			]
		});
		this.vbbar = this.createPagingToolbar(PAGESIZE);

		this.zyLbtWindow = new ZyLbtWindow();
		this.zyLbtUpdateWindow = new ZyLbtUpdateWindow();
		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'主键',dataIndex:'id',width:100,sortable:true,hidden:true},
		            {header:'图片名称',dataIndex:'tpmc',width:150,sortable:true,hidden:false},
		            {header:'图片保存id',dataIndex:'tpbcid',width:150,sortable:true,hidden:false},
		            {header:'图片类型',dataIndex:'lx',width:150,sortable:true,hidden:false,
		            	renderer:function(value){
	                           if(value == '1') {
	                                return "<span>跳转</span>";
	                           }if(value == '2') {
	                                return "<span>展示</span>";
	                           }else{
	                           		return "<span>作废</span>";
	                           }
	                   	 }	
		            },
		            {header:'链接地址',dataIndex:'url',width:150,sortable:true,hidden:false},
		            {header:'排序',dataIndex:'px',width:150,sortable:true,hidden:false,
		            	renderer:function(value){
		            	if(value == '1') {
                            return "<span>1</span>";
                       }if(value == '2') {
                            return "<span>2</span>";
                       }if(value == '3') {
                            return "<span>3</span>";
                       }if(value == '4') {
                            return "<span>4</span>";
                       }else{
                       		return "<span>5</span>";
                       }	
		            }}
		           ]);
		ZyLbtGrid.superclass.constructor.call(this,{
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
			sm: this.vsm,
			cm: this.vcm,
			tbar: this.vtbar,
			bbar: this.vbbar,
			ds: this.store
		});
	},
    onAddClick: function(){
    	var win = this.zyLbtWindow;
		win.show();
		win.zyLbt.getForm().reset();
    },
    onModifyClick: function() {
    	var records = this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				
   		    	var win = this.zyLbtUpdateWindow;
				var winForm = win.zyLbt;
				
				win.show();
				winForm.tpbcid.setValue(vrecord.data.tpbcid);	
				winForm.tp.setValue(vrecord.data.tpmc);
				winForm.tpmc.setValue(vrecord.data.tpmc);
				winForm.px.setValue(vrecord.data.px);
				winForm.url.setValue(vrecord.data.url);
   		    	
   			}else{
   				Ext.Msg.alert('系统提示','不能修改多条记录！');
   			}
   		}else{
   			Ext.Msg.alert('系统提示','请选择一条记录！');
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
    						zyLbtGrid.vbbar.doLoad(zyLbtGrid.vbbar.cursor);
    					},
    					failure: function(form,action){
							Ext.MessageBox.alert("系统提示：",action.result.message);
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
    
    zyLbtGrid = new ZyLbtGrid(Ext.getBody().getViewSize().height);
    zyLbtGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        zyLbtGrid   
		]
	});
});

