//需要补充的空格
var BLANKSTR = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';   
var PENDPOOL_GRID_STORE_URL = 'getPendPoolList';
var PAGESIZE=20;
//环节处理窗口全局变量
var ACT_DEAL_WINDOW;
/**************************PendPoolGrid*******************************************/
PendPoolGrid = Ext.extend(UxGrid, {
	pageSizeCombo: null,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.store = new Ext.data.Store({
    		baseParams: {rolekind: ROLE_KIND},//Grid Store
            proxy: new Ext.data.HttpProxy({url: PENDPOOL_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({fields:[
            		{name:'taskId'},{name:'processDefinitionId'},{name:'processInstanceId'},{name:'name'},
            		{name:'taskDefinitionKey'},{name:'assignee'},{name:'owner'},{name:'priority'},
            		{name:'createTime'},{name:'dueDate'},{name:'description'},{name:'khqh'},{name:'bkhr'}
            		,{name:'df'},{name:'xm'},{name:'businessKey'},{name:'xm'}
            ]})
        });
    	
    	this.vbbar= this.createPagingToolbar(PAGESIZE);
    	this.vtbar = new Ext.Toolbar({
            items:[
                '-',{text:'刷新',iconCls: 'refresh',handler:this.onRefreshClick,scope:this}
            ]
        });  	

        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        PendPoolGrid.superclass.constructor.call(this, {
        	region:'center',
        	renderTo:Ext.getBody(),
        //	title: '待办任务列表',
        	stripeRows: true,
            frame: false,
            height: height,
            width:width,
            viewConfig: {
                forceFit: false,  
                getRowClass : function(record,rowIndex,rowParams,store){  
                    //禁用数据显示红色  
                    if(record.data.assignee == null || record.data.assignee == "" ){  
                        return 'x-grid-record-red';  
                    }else{  
                        return '';  
                    }                       
                } 
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
               {header:'操作',dataIndex:'assignee',width:60,sortable: true,
                    renderer:function(value, cellmeta, record){
                       if(value == null || value == "" ) {
                           return '<a class="zlink" href="javascript:void(0)" onclick="pendPoolGrid.signFor();">签收</a>';
                       }else {
                           return '<a class="redlink" href="javascript:void(0)" onclick="pendPoolGrid.dealTask();">办理</a>';
                       }
                    }
                },                                          
                {header:'考核期号',dataIndex:'khqh',width:100,sortable: true},
                {header:'被考核人',dataIndex:'bkhr',width:200,sortable: true,hidden:true},
                {header:'被考核人',dataIndex:'xm',width:200,sortable: true},
                {header:'得分',dataIndex:'df',width:100,sortable: true},
            	{header:'流程实例ID',dataIndex:'processInstanceId',width:100,sortable: true},
            	{header:'优先级',dataIndex:'priority',width:60,sortable: true},
            	{header:'任务创建时间',dataIndex:'createTime',width:140,sortable: true}
            ]),
            tbar: this.vtbar,
            ds: this.store
        });
    },
    signFor: function() {//签收任务
    	var record = this.selectedRecord();
    	var taskId = record.data.taskId;
       	Ext.Ajax.request({
	       	url:'claimTask',
	       	method : 'POST', 
	       	params: { taskId: taskId},
            success: function(form, action) { 
	             pendPoolGrid.store.load();             
	        },
            failure: function(form, action) {
         	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "签收失败!" + BLANKSTR);
            }
	       	});	    	
    },
    onRefreshClick: function() {
    	pendPoolGrid.store.load();  
    },
    dealTask: function() {//处理任务
    	var record = this.selectedRecord();
    	var taskId = record.data.taskId;
    	var khqh = record.data.khqh;
    	var bkhr = record.data.bkhr;
    	var businessKey = record.data.businessKey;
    	var rolekind = ROLE_KIND;
    	var url = "openTaskDealPage/" + taskId + "/" + businessKey + "/" + khqh + "/" + bkhr + "/" +rolekind;   	
    	html = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>';    		
    	ACT_DEAL_WINDOW = new ActDealWindow();
    	ACT_DEAL_WINDOW.setTitle("绩效审批流程");
    	ACT_DEAL_WINDOW.html = html;
    	ACT_DEAL_WINDOW.show();
    },
    lookApproveTrace: function() {//查看申请信息及审批意见
    	var record = this.selectedRecord();
    	var businessKey = record.data.businessKey;
    	window.open("/cps/processXN/showProcessOption/" + businessKey);   
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

/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';        //提示方式"side"
    
    pendPoolGrid = new PendPoolGrid(Ext.getBody().getViewSize().height, Ext.getBody().getViewSize().width);
    pendPoolGrid.store.load();  
});