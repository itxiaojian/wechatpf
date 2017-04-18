var ENTITY_URL_LIST = "/wxauth/materialmgr/getSuCaiPagination";
var ENTITY_URL_SAVE = "";
var ENTITY_URL_UPDATE = "";
var ENTITY_URL_DELETE = "";

var ENTITY_URL_UPLOAD = "/wxauth/materialmgr/uploadImage";

var PAGESIZE=50;

/**
 * 上传图片素材
 * @author zhangyi
 * @date 2015-6-09
 */
MyUploadForm = Ext.extend(Ext.ux.Form,{
	constructor: function(){
		this.tishi = new Ext.form.DisplayField({
			value:'<span  style="color:red;font-weight:bold;">图片大小1M，支持JPG格式</span>'
		});
		this.fibasic = new Ext.ux.form.FileUploadField({
			anchor:'98%',
			name: 'attachMentFile',
            emptyText: '请选择...',
            fieldLabel: '<font color="red">*</font>文件',
            buttonCfg: {
                text: '浏览'
            }
	    });
		MyUploadForm.superclass.constructor.call(this, {
			anchor: '100%',
			fileUpload: true,
			autoHeight:true,
			labelWidth: 60,
			labelAlign :'right',
			frame: true,
			bodyStyle:"padding: 5px 5px 0",
			layout: 'tableform',
			layoutConfig: {columns: 1},
			items:[
			 	   this.tishi,
			       this.fibasic
			      
			       ],
			       buttonAlign :'center',
			       buttons: [
			                 {text: '上传', width: 20,iconCls:'save', handler: this.onUploadClick, scope: this},
			                 {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
			                 ]
		});
	},
	onUploadClick: function(){
		if(this.getForm().isValid()){
			var attachMentFileValue = this.fibasic.getValue();
        	if(attachMentFileValue == null || attachMentFileValue == "") {
        		Ext.MessageBox.alert("系统提示:", BLANKSTR + "请选择文件!" + BLANKSTR);
        		return;
        	}
        	
        	var attachmentType = attachMentFileValue.substring(attachMentFileValue.lastIndexOf(".")+1).toLowerCase();
        	if(attachmentType != "jpg" && attachmentType != "png"){
        		Ext.MessageBox.alert("系统提示:", BLANKSTR + "图片类型为jpg或png" + BLANKSTR);
        		return;
        	}
        	
			this.getForm().submit({
				waitMsg: '正在提交，请稍后...',
				url: PROJECT_NAME+""+ENTITY_URL_UPLOAD,
				success: function(form, action){
					Ext.MessageBox.alert("系统提示：","上传成功！");
					unteckAttachmentGrid.myUploadWindow.hide();
					unteckAttachmentGrid.store.load({params:{start:0,limit:PAGESIZE}});
				},
				failure: function(form, action){
					Ext.MessageBox.alert("系统提示：",action.result.failure);
				}
			});
		}
	},
	//关闭
	onCloseClick: function(){
		unteckAttachmentGrid.myUploadWindow.myUploadForm.getForm().reset();
		this.ownerCt.hide();
	}
	
});

/********************UploadWindow组件*************************/
MyUploadWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
	constructor: function() {
		this.myUploadForm = new MyUploadForm();
		MyUploadWindow.superclass.constructor.call(this, {
			title: '上传图片',
			width: 400,
			anchor: '100%',
			autoHeight: true,
			resizable: false,
			plain: true,
			modal: true,
			closeAction: 'hide',
			items: [this.myUploadForm]
		});
	}
});


/****************UnteckAttachmentGrid***********************/
UnteckAttachmentGrid = Ext.extend(UxGrid,{
	constructor: function(height,width){
		this.store = new Ext.data.Store({
			
			proxy: new Ext.data.HttpProxy({url:PROJECT_NAME+""+ENTITY_URL_LIST,method:'POST'}),
			reader: new Ext.data.JsonReader({totalProperty:'total',root:'rows'},[
		            {name:'scid'},
		            {name:'sctype'},
		            {name:'filename'},
		            {name:'scname'},
		            {name:'mediaid'},
		            {name:'createuser'},
		            {name:'createtime'},
		            {name:'scpath'},
		            {name:'fileCode'}])
		});
		
		//var query_filetype = this.createMemoryCombo('素材类型', 'code', 'name', '100', QUERY_FILETYPE);
    	//query_filetype.store.load();
    	//query_filetype.setValue(0);
		
		this.vtbar = new Ext.Toolbar({
			items: [
					'-',{xtype:'button',text:'上传图片',iconCls:'db-icn-upload',handler:this.onUploadClick,scope:this},
					'-',{xtype:'label',text:'素材名称'},{xtype:'textfield',id:'fileName'},
			        '-',{xtype:'button',text:'查询',iconCls:'query',handler:function(){
	    	   				var params = {};
			       			unteckAttachmentGrid.vtbar.items.each(function(item,index,length){ 
		       					if((""+item.getXType()).indexOf("field") != -1 && item.getValue() != '') {
		       						if (item.getXType() == 'datefield') {
		       							params[item.getId()] = item.getValue().format(item.format);
		       						} else {
		       							params[item.getId()] = item.getValue();
		       						}
		       					}
							});
							
							var scmc = Ext.getCmp('fileName').getValue();
							var sclx = "1";
			       			unteckAttachmentGrid.store.baseParams = {scmc:scmc,sclx:sclx};
	    	   				unteckAttachmentGrid.store.load({params:{start:0,limit:PAGESIZE}});
	    	   			}
	       			},
					'-',{xtype:'button',text:'重置',iconCls:'refresh',handler:function(){
						unteckAttachmentGrid.vtbar.items.each(function(item,index,length){   
							if((""+item.getXType()).indexOf("field") != -1) {
								item.setValue('');
							}
						  });  
    	   			}}
			]
		});
		this.vbbar= new Ext.PagingToolbar({ 
            pageSize: PAGESIZE, 
            store: this.store, 
            displayInfo: true, 
            displayMsg: '显示第 {0} 条到 {1} 条记录，一共 {2} 条', 
            emptyMsg: "没有记录" 
    	});
		this.vsm = new Ext.grid.CheckboxSelectionModel();
		this.vcm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
		             this.vsm,
		            {header:'ID',dataIndex:'scid',width:100,sortable:true,hidden:true},
		            {header:'素材类型',dataIndex:'sctype',width:100,sortable:true,hidden:false},
		            {header:'原始文件名',dataIndex:'filename',width:200,sortable:true},
		            {header:'服务器文件名',dataIndex:'fileCode',width:200,sortable:true,hidden:true},
		            {header:'MEDIA_ID',dataIndex:'mediaid',width:430,sortable:true,hidden:false},
		            {header:'素材路径',dataIndex:'scpath',width:280,sortable:true,hidden:false},
		            {header:'上传者',dataIndex:'createuser',width:100,sortable:true,hidden:true},
		            {header:'上传时间',dataIndex:'createtime',width:100,sortable:true,hidden:true}
		           ]);
		UnteckAttachmentGrid.superclass.constructor.call(this,{
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
	onUploadClick: function(){
    	if(!this.myUploadWindow)
    		this.myUploadWindow = new MyUploadWindow();
    	var win = this.myUploadWindow;
    	win.show();
    	win.myUploadForm.getForm().reset();
    }
   
});


/*************onReady组件渲染处理***********************/
Ext.onReady(function(){
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';        //提示方式"side"
    
    unteckAttachmentGrid = new UnteckAttachmentGrid(Ext.getBody().getViewSize().height);
    
    var param={};
    param['sclx'] = "1";
    unteckAttachmentGrid.store.baseParams = param;
    
    unteckAttachmentGrid.store.load({params:{start:0,limit:PAGESIZE}});
	new Ext.Viewport({
		layout: 'border',
		items: [
		        unteckAttachmentGrid   
		]
	});
});

