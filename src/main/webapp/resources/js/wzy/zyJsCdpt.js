var DICT_GRID_STORE_URL = '/wzy/ZyCdpt/getJsList';
var DICT_ENTRY_GRID_STORE_URL = '/wzy/ZyCdpt/getAllJsList';
var PAGESIZE=20;


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
//    	this.vtbar = new Ext.Toolbar({
//            items:[
//                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
//                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
//            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this},'-',
//            	{xtype:'button',text:'同步微信',iconCls:'db-icn-upload',handler:this.onUploadWx,scope:this}
////            	,'-',
////            	{xtype:'button',text:'发送图文',iconCls:'db-icn-upload',handler:this.onSendTw,scope:this}
//            ]
//        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        DictTypeGrid.superclass.constructor.call(this, {
        	region:'west',
        	title: '角色列表',
        	stripeRows: true,
            frame: true,
            height: height,
            width :'30%',
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
                {header: 'jsbh', width: 150, dataIndex: 'jsbh', hidden: true},
	            {header: '角色编号', width: 150, dataIndex: 'jsmc', sortable: true,hidden: false},
	            {header:'角色名称',dataIndex:'bz',width:150,sortable:true,hidden:false}
            ]),
//            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            	'click':function(){
            		var records=this.getSelectionModel().getSelections();
            		var jsbh = records[0].get('jsmc');
            		var jsmc = records[0].get('bz');
            		wfwGrid.jsmc = jsbh;
            		wshGrid.jsmc = jsbh;
            		//wfwGrid.setTitle('<span style="color:red">'+jsmc+'</span>');
            		wfwGrid.store.baseParams = {
			    		limit:100,
			    		jsmc:jsbh,
			    		mklx:2
			    	};
            		wshGrid.store.baseParams = {
    			    		limit:100,
    			    		jsmc:jsbh,
    			    		mklx:3
    			    	};
			    	wfwGrid.store.load({params:{start:0}});
			    	wshGrid.store.load({params:{start:0}});
            	}
            }
        });
    },
//    onRightMenuClick: function(grid, rowIndex, e) {//右键菜单
//        this.getSelectionModel().selectRow(rowIndex);
//        e.preventDefault();
//        this.rightMenu.showAt(e.getXY());
//    },
//    onQueryClick:function(){
//    	dictTypeGrid.store.baseParams = {
//    		limit:dictTypeGrid.vbbar.pageSize,
//    		//module:this.module_name.getValue(),
//    		scmc:this.type_name.getValue(),
//    		sclx : "4"
//    	};
//    	dictTypeGrid.store.load({params:{start:0}});
//    },
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


/**************************WfwGrid*******************************************/
WfwGrid = Ext.extend(UxGrid, {
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    jsmc:'',
    cdlx:'',
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+DICT_ENTRY_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
	     		{name:'id'}, {name:'cdmc'}, {name:'tjr'}, {name:'cdtbmc'}, {name:'sfqy'},
		        {name:'tjsj'}, {name:'px'}, {name:'cdurl'}, {name:'mklx'},{name:'jsmc'},{name:'cdid'},{name:'pzid'},{name:'pzzt'}
            ])
        });

//    	this.vtbar = new Ext.Toolbar({
//            items:[
//                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
//                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
//            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this}
//            ]
//        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        WfwGrid.superclass.constructor.call(this, {
        	region:'center',
        	title: '微服务主页菜单',
        	collapsible: true,
        	stripeRows: true,
            frame: true,
            //width:470,
            width : '35%',
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
                {header: 'id', width: 150, dataIndex: 'id', hidden: true},
                {header: '配置id', width: 150, dataIndex: 'pzid', hidden: true},
  	            {header: '菜单名称', width: 100, dataIndex: 'cdmc', sortable: true,hidden: false},
  	            {header: '操作', width: 100, dataIndex: 'pzzt', sortable: true,hidden: false,renderer:function(value,m){
                    if(value == 0 ) {
                    	m.style.backgroundColor='#DCDCDC';
                    	return '<a class="zlink" href="javascript:void(0)" onclick="wfwGrid.upCd();"><span style="color:blue">配置</span></a>';
                        //return "<span style='color:green'>配置</span>";
                    } else {
                    	return '<span style="color:blue">(已配置)</span> <a class="zlink" href="javascript:void(0)" onclick="wfwGrid.downCd();"><span style="color:red">取消</span></a>';
                        //return "<span style='color:green'>取消</span>";
                    }
        		}}
            ]),
            //tbar: this.vtbar,
            //bbar: this.vbbar,
            ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            }
        });
    },
    upCd: function() {//配置菜单
    	var record = this.selectedRecord();
    	var cdid = record.data.id;
    	if(this.jsmc ==null || this.jsmc ==''){
    		Ext.MessageBox.alert("提示", "请选择角色！");
    		return false;
    	}
    	var jsbh = this.jsmc;
       	Ext.Ajax.request({
	       	url:'upCd',
	       	method : 'POST', 
	       	params: {cdid: cdid,jsmc:this.jsmc},
            success: function(form, action) {
            	wfwGrid.store.baseParams = {
			    		limit:100,
			    		jsmc:jsbh,
			    		mklx:2
		    	};
            	wfwGrid.store.load({params:{start:0}});
	        },
            failure: function(form, action) {
         	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "签收失败!" + BLANKSTR);
            }
       	});	    	
    },
    downCd: function() {//取消菜单
    	var record = this.selectedRecord();
    	var pzid = record.data.pzid;
    	if(this.jsmc ==null || this.jsmc ==''){
    		Ext.MessageBox.alert("提示", "请选择角色！");
    		return false;
    	}
    	var jsbh = this.jsmc;
       	Ext.Ajax.request({
	       	url:'downCd',
	       	method : 'POST', 
	       	params: { pzid: pzid},
            success: function(form, action) {
            	wfwGrid.store.baseParams = {
			    		limit:100,
			    		jsmc:jsbh,
			    		mklx:2
			    	};
            	wfwGrid.store.load({params:{start:0}});
	        },
            failure: function(form, action) {
         	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "签收失败!" + BLANKSTR);
            }
       	});	    	
    },
    onSaveClick: function() {
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
				               Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除成功!" + BLANKSTR);
				               wfwGrid.store.reload();
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

/**************************WshGrid*******************************************/
WshGrid = Ext.extend(UxGrid, {
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    jsmc:'',
    cdlx:'',
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+DICT_ENTRY_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},
            [
	     		{name:'id'}, {name:'cdmc'}, {name:'tjr'}, {name:'cdtbmc'}, {name:'sfqy'},
		        {name:'tjsj'}, {name:'px'}, {name:'cdurl'}, {name:'mklx'},{name:'jsmc'},{name:'cdid'},{name:'pzid'},{name:'pzzt'}
            ])
        });

//    	this.vtbar = new Ext.Toolbar({
//            items:[
//                {text:'添加',iconCls: 'add',handler:this.onAddClick,scope:this},'-',
//                {text:'修改',iconCls: 'edit',handler:this.onModifyClick,scope:this},'-',
//            	{text:'删除',iconCls: 'delete',handler:this.onDeleteClick,scope:this}
//            ]
//        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        WshGrid.superclass.constructor.call(this, {
        	region:'east',
        	title: '微生活主页菜单',
        	collapsible: true,
        	stripeRows: true,
            frame: true,
            //width:470,
            width : '35%',
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
                {header: 'id', width: 150, dataIndex: 'id', hidden: true},
  	            {header: '菜单名称', width: 100, dataIndex: 'cdmc', sortable: true,hidden: false},
  	            {header: '操作', width: 100, dataIndex: 'pzzt', sortable: true,hidden: false,renderer:function(value,m){
                    if(value == 0 ) {
                    	m.style.backgroundColor='#DCDCDC';
                    	return '<a class="zlink" href="javascript:void(0)" onclick="wshGrid.upCd();"><span style="color:blue">配置</span></a>';
                        //return "<span style='color:green'>配置</span>";
                    } else {
                    	return '<span style="color:blue">(已配置)</span> <a class="zlink" href="javascript:void(0)" onclick="wshGrid.downCd();"><span style="color:red">取消</span></a>';
                        //return "<span style='color:green'>取消</span>";
                    }
        		}}
            ]),
//            tbar: this.vtbar,
            //bbar: this.vbbar,
            ds: this.store,
            listeners: {
                //"dblclick": { fn: this.onModifyClick, scope: this}, 		//响应双击事件
                //"rowcontextmenu": {fn: this.onRightMenuClick, scope: this}  //响应右击事件
            }
        });
    },
//    onRightMenuClick: function(grid, rowIndex, e) {//右键菜单
//        this.getSelectionModel().selectRow(rowIndex);
//        e.preventDefault();
//        this.rightMenu.showAt(e.getXY());
//    },
    upCd: function() {//配置菜单
    	var record = this.selectedRecord();
    	var cdid = record.data.id;
    	if(this.jsmc ==null || this.jsmc ==''){
    		Ext.MessageBox.alert("提示", "请选择角色！");
    		return false;
    	}
    	var jsbh = this.jsmc;
       	Ext.Ajax.request({
	       	url:'upCd',
	       	method : 'POST', 
	       	params: {cdid: cdid,jsmc:this.jsmc},
            success: function(form, action) {
            	wshGrid.store.baseParams = {
			    		limit:100,
			    		jsmc:jsbh,
			    		mklx:3
		    	};
            	wshGrid.store.load({params:{start:0}});
	        },
            failure: function(form, action) {
         	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "签收失败!" + BLANKSTR);
            }
       	});	    	
    },
    downCd: function() {//取消菜单
    	var record = this.selectedRecord();
    	var pzid = record.data.pzid;
    	if(this.jsmc ==null || this.jsmc ==''){
    		Ext.MessageBox.alert("提示", "请选择角色！");
    		return false;
    	}
    	var jsbh = this.jsmc;
       	Ext.Ajax.request({
	       	url:'downCd',
	       	method : 'POST', 
	       	params: { pzid: pzid},
            success: function(form, action) {
            	wshGrid.store.baseParams = {
			    		limit:100,
			    		jsmc:jsbh,
			    		mklx:3
			    	};
            	wshGrid.store.load({params:{start:0}});
	        },
            failure: function(form, action) {
         	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "签收失败!" + BLANKSTR);
            }
       	});	    	
    },
    onSaveClick: function() {
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
				               Ext.MessageBox.alert("系统提示:", BLANKSTR + "删除成功!" + BLANKSTR);
				               wfwGrid.store.reload();
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
    
    wfwGrid = new WfwGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    
    wshGrid = new WshGrid(Ext.getBody().getViewSize().height-160, Ext.getBody().getViewSize().width);
    
    //wfwGrid.store.load({params:{start:0,limit:PAGESIZE}});
    new Ext.Viewport({
    	layout: 'border',
    	items:[
			dictTypeGrid,
			wfwGrid,
			wshGrid
    	]
    });
//    dictTypeGrid.constructionInsertWindow.show();
//    dictTypeGrid.constructionInsertWindow.hide();
   
});