var DICT_GRID_STORE_URL = '/sys/SysRole/getList';
var DICT_JG_URL = '/sys/SysArea/orgOrMebTree';
var YHXX_URL = '/sys/SysUser/getYhxxList';
var PAGESIZE=20;

/***************************************DictTypeForm组件**************************************************/
DictTypeForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {
    	this.jsbh = this.createTextField('<font color="red">*</font>角色编号:','jsbh','95%','',null,100,'长度超过不能100');
    	this.jsbh.allowBlank = true;
    	this.jsbh.hidden= true;
    	
		this.jsmc = this.createTextField('<font color="red">*</font>角色名称:','jsmc','95%','',null,100,'长度超过不能100');
        this.jsmc.allowBlank = false;

        
        this.bz = this.createTextField('<font color="red">*</font>角色描述:','bz','95%','',null,100,'长度超过不能100');
        this.bz.allowBlank = false;
               
        this.jszt = this.createCombo('<font color="red">*</font>角色状态:','zdz', 'zdmc', 'jszt', '95%',PROJECT_NAME+'/wzy/ZyCdpt/getDicByLx');
        this.jszt.store.load();
        this.jszt.allowBlank = false;
        
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
                this.jsbh,
            	this.jsmc,
            	this.bz,
            	this.jszt
            ],
            buttonAlign :'center',
            buttons: [
                      {text: '保存', width: 20,iconCls: 'save', hidden: false, handler: this.addFormClick, scope: this},  
                      {text: '修改',iconCls: 'edit',handler:this.updateFormClick,scope:this},
                      {text: '关闭', width: 20,iconCls:'delete', handler: this.onCloseClick, scope: this}
              ]
        });
     },
     addFormClick: function() {
         if(this.getForm().isValid()) { 
         	this.getForm().submit({
                 waitMsg: '正在提交数据...',
                 url: PROJECT_NAME+'/sys/SysRole/saveRole',   
                 method: 'POST',
                 success: function(form, action) {
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "添加成功!" + BLANKSTR);
                 	dictTypeGrid.constructionInsertWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
                 	Ext.MessageBox.alert("系统提示:", action.result.message);
                 }
         	});
         }
     },
     onCloseClick: function(){ 			//关闭
         this.ownerCt.hide();
     }
});

/***************************************DictTypeForm组件**************************************************/
UpdateForm = Ext.extend(Ext.ux.Form, {
	
    constructor: function() {

    	this.jsbh = this.createTextField('<font color="red">*</font>用户编号:','jsbh','95%','',null,100,'长度超过不能100');
    	this.jsbh.allowBlank = true;
    	this.jsbh.hidden= true;

		this.jsmc = this.createTextField('<font color="red">*</font>角色名称:','jsmc','95%','',null,100,'长度超过不能100');
        this.jsmc.allowBlank = false;
        this.jsmc.hidden= true;
        
		this.newjsmc = this.createTextField('<font color="red">*</font>角色名称:','newjsmc','95%','',null,100,'长度超过不能100');
        this.newjsmc.allowBlank = false;

        this.bz = this.createTextField('<font color="red">*</font>角色描述:','bz','95%','',null,100,'长度超过不能100');
        this.bz.allowBlank = false;

        this.jszt = this.createCombo('<font color="red">*</font>角色状态:','zdz', 'zdmc', 'jszt', '95%',PROJECT_NAME+'/wzy/ZyCdpt/getDicByLx');
        this.jszt.store.load();
        this.jszt.allowBlank = false;
        
        UpdateForm.superclass.constructor.call(this, {
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
                this.jsbh,
            	this.jsmc,
            	this.newjsmc,
            	this.bz,
            	this.jszt
            ],
            buttonAlign :'center',
            buttons: [
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
                 url: PROJECT_NAME+'/sys/SysRole/updateRole', 
                 method: 'POST',
                 success: function(form, action) { 
           		 	Ext.MessageBox.alert("系统提示:", BLANKSTR + "修改成功!" + BLANKSTR);
                 	dictTypeGrid.constructionUpdateWindow.hide();
                 	dictTypeGrid.vbbar.doLoad(dictTypeGrid.vbbar.cursor);
                 },
                 failure: function(form, action) {
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
        this.constructionForm = new DictTypeForm();
        this.constructionForm.buttons[0].show();   //隐藏添加按钮
    	this.constructionForm.buttons[1].hide();   //显示修改按钮
        DictTypeInsertWindow.superclass.constructor.call(this, {
            title: "添加用户角色",
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

/***************************************DictTypeInsertWindow组件**************************************************/
RoleSourserWindow = Ext.extend(Ext.Window,{
	constructionForm : null,
    constructor: function(grid) {
    	this.tree = new Ext.tree.TreePanel({
                //{el:'tree'}//表示渲染的DOM的id.界面中有<div id = "tree"></div>相对应最后这棵树就出现在这个div位置上  
    			id:'navTree',
    			//itemId:'navTree',
    			autoScroll:true,
    			containerScroll:true,
    			height:400,
    			bodyStyle:'background-color:white',
    			checkModel: 'cascade'
    		  });  
    	
    	 this.tree.on("click",function(node,checked){
//    		 	alert(node.text+" ;"+node.id+" = "+checked);
//    		 	alert(node.isSelected());
//    		 	node.isSelected = true;
    		 }); //注册"check"事件   
            
    	RoleSourserWindow.superclass.constructor.call(this, {
            title: "配置角色权限",
            width: 600,
            anchor: '100%',
            autoHeight:true,
            constrainHeader:true,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'destroy',
            closable:false,
            items: [this.tree],
        	buttons: [
    	      	{	text: "保存",
    	      		iconCls: 'save',
    	      		itemId : 'btn_save'	,
    	      		handler: this.getTreeNode
    	      	},
    	      	{	text: "关闭",
    	      		iconCls: 'delete',
    	      		itemId : 'btn_delete'	,
    	      		handler: function (){
    	      			location="javascript:location.reload()";//窗口关闭后刷新当前页面 
    	      		}
    	      	}
    	    ]
        });
    },
    getTreeNode:function() {       //修改
    		var selectnodes  = Ext.getCmp('navTree').getChecked();
    		var valueStr=[];
    		for(var i=0;i<selectnodes.length;i++){
	       		valueStr.push(selectnodes[i].id);
    	 	}
    		var records=dictTypeGrid.getSelectionModel().getSelections();
    		vrecord = records[0];
    		var jsbh = vrecord.data.jsbh;
    		Ext.Msg.confirm("提醒信息", "确定要配置菜单吗?",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/sys/SysRole/editJsCd',   //配置角色菜单
				       	   method : 'POST', 
				       	   params: { jsbh: jsbh,cdbhs:valueStr},
			               success: function(form, action) {
				               	var obj = Ext.util.JSON.decode(form.responseText);
				               	if(obj.success == true){   //true / false
				               		Ext.MessageBox.alert("系统提示:", BLANKSTR + "配置成功!" + BLANKSTR);
				               		location="javascript:location.reload()";//窗口关闭后刷新当前页面           
				               	}else{
				               		 Ext.MessageBox.alert("系统提示:", obj.message);
				               	}
			               },
			               failure: function(form, action) {
			               		Ext.MessageBox.alert("系统提示：",action.result.failure);
			               }
				       	});					
				}
	    	});	
    		  
     },
    winclose : function() {
          location="javascript:location.reload()";//窗口关闭后刷新当前页面                
	}
});
/***************************************DictTypeUpdateWindow组件**************************************************/
DictTypeUpdateWindow = Ext.extend(Ext.Window, {
	constructionForm : null,
    constructor: function() {
    	this.constructionForm = new UpdateForm();
    	DictTypeUpdateWindow.superclass.constructor.call(this, {
        	title: "修改用户角色",
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

/***************************************YhxxpenWindow组件**************************************************/
YhxxpenWindow = Ext.extend(Ext.Window,{
	yhxxGrid : null,
	constructionForm : null,
    constructor: function(grid) {
        this.yhxxGrid = new YhxxGrid();
    	var jsmc = Ext.getCmp("jsmc").getValue();
        YhxxpenWindow.superclass.constructor.call(this, {
            title: "用户授权>>"+jsmc,
            width: 800,
            anchor: '100%',
            autoHeight:false,
            constrainHeader:false,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'hide',
            items: [this.yhxxGrid]
        });
    }
});

/**************************yhxxGrid*******************************************/
YhxxGrid = Ext.extend(UxGrid, {
	yhxxpenWindow:null,
	pageSizeCombo: PAGESIZE,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
	id:'yhxxGrid',
    store:null,
    module_name:null,
    type_name:null,
    constructor: function(height, width){
    	var thiz = this;
    	var jsbh = Ext.getCmp("jsbh").getValue();
    	var code = Ext.getCmp("code1").getValue();
    	this.store = new Ext.data.Store({          //Grid Store
    		baseParams:{jsbh:jsbh,code:code},
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+YHXX_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
             {name:'yhbh'},{name:'dlm'},{name:'bmmc'},{name:'xm'},{name:'sjh'},{name:'yx'},{name:'jsbh'},{name:'jsmc'}
            ])
        });
    	
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                   {text:'授权',iconCls: 'edit',handler:this.onSqClick,scope:this},'-',
               	   {text:'取消授权',iconCls: 'delete',handler:this.onQxClick,scope:this},'->',
				   {xtype:'textfield',id:'code',emptyText:'',},'-',
				   {xtype:'button',text:'查询',iconCls:'query',handler:function(){
					    document.getElementById('code1').value=Ext.getCmp("code").getValue();
						code = Ext.getCmp("code").getValue();
						Ext.getCmp("yhxxGrid").store.load({params:{start:0,limit:PAGESIZE,code:code,jsbh:jsbh}});
   				    }},'-',
   				   {xtype:'button',text:'清空',iconCls:'redo',handler:function(){document.getElementById('code').value='';}
               },
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        YhxxGrid.superclass.constructor.call(this, {
        	region:'center',
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
            cm: new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
                                          sm,
                {header:'id', width: 150, dataIndex: 'yhbh', hidden: true},
	            {header:'用户账号', width: 100, dataIndex: 'dlm', sortable: true},
	            {header:'用户姓名', width: 100, dataIndex: 'xm', sortable: true},
	            {header:'部门名称', width: 100, dataIndex: 'bmmc', sortable: true},
	            {header:'角色名称', width: 155, dataIndex: 'jsmc', sortable: true},
	            {header:'手机号', width: 100, dataIndex: 'sjh', sortable: true},
	            {header:'个人邮箱', width: 100, dataIndex: 'yx', sortable: true},
	            {header:'是否授权', width: 70, dataIndex: 'jsbh', sortable: true,
            		renderer:function(value, cellmeta, record){
            			if(value!= null && value == jsbh) {
            				return '<span style="color:blue;">已授权</span>';
            			}else {
            				return '<span style="color:red;">未授权</span>';
            			}
            		}}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
        });
    },
    onSqClick: function() {
    	var jsbh = Ext.getCmp("jsbh").getValue();
    	var code = document.getElementById('code1').value;
    	var records=this.getSelectionModel().getSelections();
    	var valueStr=[];
    	var valueYh=[];
    	if(records.length>0) {
    		Ext.Msg.confirm("提醒信息", "确定将这 " + records.length + " 位用户授权吗",function(btn){
    			if (btn == 'yes') {
    				for(var i=0;i<records.length;i++){
    					if(records[i].get('jsbh')!= null){
    						valueYh.push(records[i].get('xm'));
    					}else{
    						valueStr.push(records[i].get('yhbh'));
    					}
    	    		}
    				if(valueYh.length < records.length){
    					Ext.Ajax.request({
    						url:PROJECT_NAME+'/sys/SysRole/saveUserRole',   
    						method : 'POST', 
    						params: { yhbhs: valueStr,jsbh:jsbh},
    						success: function(form, action) {
    							var obj = Ext.util.JSON.decode(form.responseText);
    							if(obj.success == true){   //true / false
    								if(valueYh.length == 0){
    									Ext.MessageBox.alert("系统提示:", BLANKSTR + "配置成功!" + BLANKSTR);
    								}else{
    									Ext.MessageBox.alert("系统提示:", BLANKSTR + "配置成功! 其中，用户："+valueYh+" 已有该角色权限，已自动忽略！" + BLANKSTR);
    								}
    								Ext.getCmp("yhxxGrid").store.load({params:{start:0,limit:PAGESIZE,code:code,jsbh:jsbh}});
    							}else{
    								Ext.MessageBox.alert("系统提示:", obj.message);
    								Ext.getCmp("yhxxGrid").store.load({params:{start:0,limit:PAGESIZE,code:code,jsbh:jsbh}});
    							}
    						},
    						failure: function(form, action) {
    							Ext.MessageBox.alert("系统提示：",action.result.failure);
    						}
    					});					
    				}else{
    					Ext.MessageBox.alert("系统提示:", BLANKSTR + "所选用户："+valueYh+" 已有该角色权限，已自动忽略配置！" + BLANKSTR);
    				}
    			}
    		});	
    	}else{
    		Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
    		return;
    	}
    },
    
    onQxClick: function() {
    	var jsbh = Ext.getCmp("jsbh").getValue();
    	var code = document.getElementById('code1').value;
    	var records=this.getSelectionModel().getSelections();
    	var valueStr=[];
    	var valueYh=[];
    	if(records.length>0) {
//    		console.log(jsbh.value);
    		Ext.Msg.confirm("提醒信息", "确定将这 " + records.length + " 位用户取消授权吗",function(btn){
    			if (btn == 'yes') {
    				for(var i=0;i<records.length;i++){
    					if(records[i].get('jsbh')== null){
    						valueYh.push(records[i].get('xm'));
    					}else{
    						valueStr.push(records[i].get('yhbh'));
    					}
    	    		}
//    				console.log(valueYh);
    				if(valueYh.length < records.length){
    					Ext.Ajax.request({
    						url:PROJECT_NAME+'/sys/SysRole/deleteUserRole',   
    						method : 'POST', 
    						params: { yhbhs: valueStr,jsbh:jsbh},
    						success: function(form, action) {
    							var obj = Ext.util.JSON.decode(form.responseText);
    							if(obj.success == true){   //true / false
    								if(valueYh.length == 0){
    									Ext.MessageBox.alert("系统提示:", BLANKSTR + "配置成功!" + BLANKSTR);
    								}else{
    									Ext.MessageBox.alert("系统提示:", BLANKSTR + "配置成功! 其中，用户："+valueYh+" 无该角色权限，已自动忽略！" + BLANKSTR);
    								}
    								Ext.getCmp("yhxxGrid").store.load({params:{start:0,limit:PAGESIZE,code:code,jsbh:jsbh}});
    							}else{
    								Ext.MessageBox.alert("系统提示:", obj.message);
    								Ext.getCmp("yhxxGrid").store.load({params:{start:0,limit:PAGESIZE,code:code,jsbh:jsbh}});
    							}
    						},
    						failure: function(form, action) {
    							Ext.MessageBox.alert("系统提示：",action.result.failure);
    						}
    					});					
    				}else{
    					Ext.MessageBox.alert("系统提示:", BLANKSTR + "所选用户："+valueYh+" 无该角色权限，已自动忽略配置！" + BLANKSTR);
    				}
    			}
    		});	
    	}else{
    		Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
    		return;
    	}
    }
});

/***************************************ActDealWindow组件**************************************************/
ActDealWindow = Ext.extend(Ext.Window,{
    constructor: function(grid) {
    	ActDealWindow.superclass.constructor.call(this, {
            width: 800,
            anchor: '40%',
            maximized :false,
            height: 500,
            resizable : false,
            plain: true,
            modal: true,
            autoScroll: true,
            closeAction: 'close',
            html:'<iframe scrolling="auto" frameborder="0" width="70%" height="50%" src=""></iframe>'
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
            	{name:'jsbh'}, {name:'jsmc'}, {name:'bz'}, {name:'jszt'}
            ])
        });

    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                {xtype:'hidden',id:'jsbh'},{xtype:'hidden',id:'jsmc'},{xtype:'hidden',id:'code1'},
                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
                {text:'权限配置',iconCls: 'edit',handler:this.onQxpzClick,scope:this},'-',
                {text:'用户授权',iconCls: 'edit',handler:this.onYhsqClick,scope:this},'-',
            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'->',
            	{xtype:'label',text:'角色名称:'},'-',
				{xtype:'textfield',id:'jsmc_query'},'-',
				{xtype:'label',text:'角色描述:'},'-',
				{xtype:'textfield',id:'jsms_query'},'-',
            	{text:'查询',iconCls: 'query',handler:this.onQueryClick,scope:this}
            ]
        });	
        this.constructionInsertWindow = new DictTypeInsertWindow();       
        this.constructionUpdateWindow = new DictTypeUpdateWindow();
        this.yhxxpenWindow = new YhxxpenWindow();
        this.rolewin = new RoleSourserWindow();
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '用户角色',
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
                {header:'角色编号', width: 150, dataIndex: 'jsbh', hidden: true},
	            {header:'角色名称', width: 200, dataIndex: 'jsmc', sortable: true,hidden: false},
	            {header:'角色描述',dataIndex:'bz',width:220,sortable:true,hidden:false},
	            {header:'角色状态',dataIndex:'jszt',width:80,sortable:true,
            		renderer:function(value, cellmeta, record){
            			if(value == "1") {
            				return '<span style="color:blue;">启用</span>';
            			}else {
            				return '<span style="color:red;">禁用</span>';
            			}
            		}
	            },
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
//    onQxpzClick: function(){//权限配置
//    	var records=this.getSelectionModel().getSelections();
//    	if(records.length > 0) {
//   			if(records.length == 1){
//   				var win = this.rolewin;
//   		        win.tree.setRootNode(root);//把根节点添加到树中  
//   		        win.rolename = 'ROLE_ADMIN';
//   		    	win.show();
//   			}else{
//   				Ext.Msg.alert('系统提示', '只能配置单个角色');
//   			}
//   		}else{
//   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条角色' + BLANKSTR);
//   		} 
//    },
    
    onQxpzClick: function() {    //权限配置
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				var jsbh=records[0].get('jsbh');
				var url = "authorize?roleId="+jsbh;  	
				html = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>';    		
				ACT_DEAL_WINDOW = new ActDealWindow();
				ACT_DEAL_WINDOW.setTitle("配置角色权限");
				ACT_DEAL_WINDOW.html = html;
				ACT_DEAL_WINDOW.show(); 

   			}else{
   				Ext.Msg.alert('系统提示', '只能配置单个角色');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条角色' + BLANKSTR);
   		}    	
    },
    
    onYhsqClick: function(){//用户授权
    	var records=this.getSelectionModel().getSelections();
    	if(records.length > 0) {
   			if(records.length == 1){
   				document.getElementById('jsbh').value=records[0].get('jsbh');
   				document.getElementById('jsmc').value=records[0].get('bz');
   				var jsbh = records[0].get('jsbh');
   				var jsmc = records[0].get('bz');
   				var win = new YhxxpenWindow();
           		win.constructionForm = this.constructionForm;
         		win.show();
         		win.yhxxGrid.yhxxpenWindow = win;
         		win.yhxxGrid.store.load({params:{start:0,limit:PAGESIZE,jsbh:jsbh,jsmc:jsmc}});
   			}else{
   				Ext.Msg.alert('系统提示', '只能配置单个角色');
   			}
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条角色' + BLANKSTR);
   		} 
    },
    
    onQueryClick: function() {
		var jsmc = Ext.getCmp("jsmc_query").getValue();
		var jsms = Ext.getCmp("jsms_query").getValue();
		dictTypeGrid.store.baseParams = {jsmc:jsmc, jsms:jsms};
		dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
    },
    onAddClick: function() {
		var win = this.constructionInsertWindow;
		win.show();
		win.constructionForm.getForm().reset();
    },
    onModifyClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			if(records.length == 1){
   				vrecord = records[0];
   				var win = this.constructionUpdateWindow;
   		    	win.show();
   		    	var winForm = win.constructionForm;
   		    	winForm.jsbh.setValue(vrecord.data.jsbh);
   		    	winForm.jsmc.setValue(vrecord.data.jsmc);
				winForm.newjsmc.setValue(vrecord.data.jsmc);
				winForm.bz.setValue(vrecord.data.bz);
				winForm.jszt.setValue(vrecord.data.jszt);
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
	       		valueStr.push(records[i].get('jsbh'));
    	 	}
	    	Ext.Msg.confirm("提醒信息", "确定要删除这 " + records.length + " 条信息吗",function(btn){
				if (btn == 'yes') {
			       	Ext.Ajax.request({
				       	   url:PROJECT_NAME+'/sys/SysRole/deleteRole',   
				       	   method : 'POST', 
				       	   params: { jsbhs: valueStr},
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
    }
});


/*********************onReady 组件渲染及处理 **********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    dictTypeGrid = new DictTypeGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
//    var param={};
//    param['sclx'] = "4";
//    dictTypeGrid.store.baseParams = param;
    dictTypeGrid.store.load({params:{start:0,limit:PAGESIZE}});
    
    new Ext.Viewport({
    	layout: 'border',
    	items:[
			dictTypeGrid
    	]
    });
//    dictTypeGrid.constructionInsertWindow.show();
//    dictTypeGrid.constructionInsertWindow.hide();
    
});