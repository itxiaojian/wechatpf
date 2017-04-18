//需要补充的空格
var BLANKSTR = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';   
var RUNNING_PROCESS_GRID_STORE_URL = 'runningProcess/list';
var PAGESIZE=20;

/**************************RunningProcessGrid*******************************************/
RunningProcessGrid = Ext.extend(UxGrid, {
	pageSizeCombo: null,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: RUNNING_PROCESS_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},[
            		{name:'processDefinitionId'},{name:'processInstanceId'},{name:'businessKey'},{name:'activityId'},
            		{name:'suspensionState'},{name:'isEnded'},{name:'id'},{name:'activityName'},{name:'bdlcid'},{name:'tname'},
            		{name:'zl'},{name:'khqh'},{name:'bkhr'},{name:'df'},{name:'shzt'},{name:'startTime'},{name:'endTime'},{name:'xm'}
            ])
        });

    	this.vtbar = new Ext.Toolbar({
            items:[
            	{xtype:'label',text:'被考核人:'},{xtype:'textfield',id:'bkhr_query'},
            	'-',{xtype:'label',text:'考核期号:'},{xtype:'textfield',id:'khqh_query'},
            	'-',{text:'查询',iconCls: 'query',handler:this.onQueryClick,scope:this}
            ]
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);

        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        RunningProcessGrid.superclass.constructor.call(this, {
        	renderTo:Ext.getBody(),
     //   	title: '运行中流程',
        	stripeRows: true,
            frame: false,
            height: height,
            width:width,
            viewConfig: {
                forceFit: false
            },
            loadMask: {
                msg : '正在载入数据,请稍候...'
            },
            sm: sm,
            cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
                                          sm,
				{header:'考核期号',dataIndex:'khqh',width:100,sortable: true},
				{header:'被考核人',dataIndex:'bkhr',width:200,sortable: true,hidden:true},
				{header:'被考核人',dataIndex:'xm',width:200,sortable: true},
				{header:'得分',dataIndex:'df',width:100,sortable: true}, 
            	{header:'审核状态',dataIndex:'shzt',width:180,sortable: true, hidden:true},
            	{header:'绑定流程ID',dataIndex:'bdlcid',width:180,sortable: true, hidden:true},
            	{header:'表名',dataIndex:'tname',width:180,sortable: true, hidden:true},
            	{header:'流程实例ID',dataIndex:'processInstanceId',width:100,sortable: true},
                {header:'流程启动时间',dataIndex:'startTime',width:140,sortable: true},
            	{header:'流程定义ID',dataIndex:'processDefinitionId',width:180,sortable: true, hidden:true},
            	{header:'当前节点',dataIndex:'activityName',width:140,sortable: true,
            		renderer:function(value, cellmeta, record){
            			var activityId = record.data.activityId;
            			if(activityId == null || activityId == "") {
            				return "";
            			}else if(value == null || value == "") {
            				return '<a class="redlink" title="点击查看流程图" href="javascript:void(0)" onclick="runningProcessGrid.lookGraphTrace();">' + activityId + '</a>';
            			}else {
            				return '<a class="redlink" title="点击查看流程图" href="javascript:void(0)" onclick="runningProcessGrid.lookGraphTrace();">' + value + '</a>';
            			}
            		}
            	},
            	{header:'是否挂起',dataIndex:'suspensionState',width:80,sortable: true,
            		renderer:function(value, cellmeta, record){
            			if(value == false) {
                			return "<span style='color:green;font-weight:bold;'>正常</span>";
            			}else if(value == true) {
                			return "<span style='color:red;font-weight:bold;'>挂起</span>";
            			}else {
                			return value;
            			}
            		}
            	},
            	{header:'操作',dataIndex:'opt',width:60,sortable: true,hidden:true,
            		renderer:function(value, cellmeta, record){
            			var suspensionState = record.data.suspensionState;
            			if(suspensionState == true) {
            				return '<a class="redlink" href="javascript:void(0)" onclick="runningProcessGrid.activate();">激活</a>';
            			}else {
            				return '<a class="zlink" href="javascript:void(0)" onclick="runningProcessGrid.hangUp();">挂起</a>';
            			}
            		}
            	},
            	{header:'强制结束',dataIndex:'forceOver',width:60,sortable: true,
            		renderer:function(value, cellmeta, record){
            			return '<a class="zlink" href="javascript:void(0)" onclick="runningProcessGrid.forceOver();">强制结束</a>';
            		}
            	}
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    activate: function() {//激活流程
    	var record = this.selectedRecord();
    	var processInstanceId = record.data.processInstanceId;
    	Ext.Msg.confirm("提醒信息", "确定要激活流程实例【" + processInstanceId + "】吗？",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url:'/cps/processXN/activateProcessInst',
			       	   method : 'POST', 
			       	   params: { processInstanceId: processInstanceId},
		               success: function(form, action) { 
			               Ext.MessageBox.alert("系统提示:", BLANKSTR + "激活成功!" + BLANKSTR);
			               runningProcessGrid.vbbar.doLoad(runningProcessGrid.vbbar.cursor);		               },
		               failure: function(form, action) {
		            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "激活失败!" + BLANKSTR);
		               }
			       	});					
			}
    	});	
    },
    hangUp: function() {//挂起流程
    	var record = this.selectedRecord();
    	var processInstanceId = record.data.processInstanceId;
    	Ext.Msg.confirm("提醒信息", "确定要挂起流程实例【" + processInstanceId + "】吗？",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url:'/cps/processXN/suspendProcessInst',
			       	   method : 'POST', 
			       	   params: { processInstanceId: processInstanceId},
		               success: function(form, action) { 
			               Ext.MessageBox.alert("系统提示:", BLANKSTR + "挂起成功!" + BLANKSTR);
			               runningProcessGrid.vbbar.doLoad(runningProcessGrid.vbbar.cursor);		               },
		               failure: function(form, action) {
		            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "挂起失败!" + BLANKSTR);
		               }
			       	});					
			}
    	});	
    },
    forceOver: function() {//强制结束流程
    	var record = this.selectedRecord();
    	var processInstanceId = record.data.processInstanceId;
    	var bkhr = record.data.bkhr;
    	var khqh = record.data.khqh;
    	var bdlcid = record.data.bdlcid;
    	var tname = record.data.tname;
    	Ext.Msg.confirm("提醒信息", "确定要强制结束流程实例【" + processInstanceId + "】吗？",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
			       	   url:'forceOverProcess',
			       	   method : 'POST', 
			       	   params: { processInstanceId: processInstanceId,bkhr:bkhr,khqh:khqh,bdlcid:bdlcid,tname:tname},
		               success: function(form, action) { 
			               Ext.MessageBox.alert("系统提示:", BLANKSTR + "强制结束成功!" + BLANKSTR);
			               runningProcessGrid.vbbar.doLoad(runningProcessGrid.vbbar.cursor);		               },
		               failure: function(form, action) {
		            	   Ext.MessageBox.alert("系统提示:", BLANKSTR + "强制结束失败!" + BLANKSTR);
		               }
			       	});					
			}
    	});	
    },
    lookGraphTrace : function() {//查看流程图
    	var record = this.selectedRecord();
    	var processInstanceId = record.data.processInstanceId;
    	window.open("showProcessTrack?hisFlag=0&processInstanceId=" + processInstanceId);
    },
    lookApproveTrace: function() {//查看申请信息及审批意见
    	var record = this.selectedRecord();
    	var businessKey = record.data.businessKey;
    	window.open("/cps/processXN/showProcessOption/" + businessKey);   
    },
    onQueryClick: function() {
		var bkhr = Ext.getCmp("bkhr_query").getValue();
		var khqh = Ext.getCmp("khqh_query").getValue();
		runningProcessGrid.store.baseParams = {bkhr:bkhr, khqh:khqh};
		runningProcessGrid.store.load({params:{start:0,limit:PAGESIZE}});
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


/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';        //提示方式"side"
    
    runningProcessGrid = new RunningProcessGrid(Ext.getBody().getViewSize().height, Ext.getBody().getViewSize().width);
    runningProcessGrid.store.load({params:{start:0,limit:PAGESIZE}});  
});