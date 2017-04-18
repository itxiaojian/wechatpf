//需要补充的空格
var BLANKSTR = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';   
var MY_PROCESS_GRID_STORE_URL = 'myProcess/list';
var PAGESIZE=20;

/**************************MyProcessGrid*******************************************/
MyProcessGrid = Ext.extend(UxGrid, {
	pageSizeCombo: null,
	vtbar:null,				//面板顶部的工具条
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: MY_PROCESS_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},[
            		{name:'processDefinitionId'},{name:'processInstanceId'},{name:'businessKey'},{name:'activityId'},
            		{name:'processStatus'},{name:'processFlag'},{name:'pid'},{name:'activityName'},{name:'name'},{name:'processCode'},
            		{name:'deleteReason'},{name:'startTime'},{name:'endTime'},{name:'bdlcid'},{name:'tname'},
            		{name:'zl'},{name:'khqh'},{name:'bkhr'},{name:'df'},{name:'shzt'},{name:'xm'}
            ])
        });
    	
    	this.vtbar = new Ext.Toolbar({
            items:[
            	{xtype:'label',text:'流程代号:'},{xtype:'textfield',id:'customerId_query'},
            	'-',{xtype:'label',text:'流程名称:'},{xtype:'textfield',id:'custName_query'},
            	'-',{text:'查询',iconCls: 'query',handler:this.onQueryClick,scope:this}
            ]
        });
    	this.vbbar= this.createPagingToolbar(PAGESIZE);

        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true}); 
        MyProcessGrid.superclass.constructor.call(this, {
        	renderTo:Ext.getBody(),
        	//title: '我发起的流程',
        	stripeRows: true,
            frame: false,
            height: height,
            width:width,
            viewConfig: {
                forceFit: false,  
                getRowClass : function(record,rowIndex,rowParams,store){  
                    //禁用数据显示红色  
                    if(record.data.processFlag == "0"){  
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
  				{header:'考核期号',dataIndex:'khqh',width:100,sortable: true},
				{header:'被考核人',dataIndex:'bkhr',width:100,sortable: true,hidden:true},
				{header:'被考核人',dataIndex:'xm',width:200,sortable: true},
				{header:'得分',dataIndex:'df',width:100,sortable: true}, 
            	{header:'审核状态',dataIndex:'shzt',width:180,sortable: true, hidden:true},
            	{header:'绑定流程ID',dataIndex:'bdlcid',width:180,sortable: true, hidden:true},
            	{header:'表名',dataIndex:'tname',width:180,sortable: true, hidden:true},
              	{header:'流程状态',dataIndex:'processFlag',width:100,sortable: true,
              		renderer:function(value, cellmeta, record){
              			var deleteReason = record.data.deleteReason;
              			if(deleteReason != null && deleteReason != "") {
              				return "<span style='color:#DB9370;font-weight:bold;'>作废</span>";
              			}else if(value == "1") {
              				return "<span style='color:green;font-weight:bold;'>流程已结束</span>";
              			}else if(value == "0") {
              				return "<span style='color:red;font-weight:bold;'>运行中</span>";
              			}else {
              				return value;
              			}
              		}
              	},{header:'当前节点',dataIndex:'activityName',width:140,sortable: true,
              		renderer:function(value, cellmeta, record){
              			if(value == null || value == "") {
                			return '<a class="redlink" title="点击查看流程图" href="javascript:void(0)" onclick="myProcessGrid.lookGraphTraceHis();">流程结束 </a>';
              			}else {
              				return '<a class="redlink" title="点击查看流程图" href="javascript:void(0)" onclick="myProcessGrid.lookGraphTrace();">' + value + '</a>';
              			}
              		}
              	},  
              	{header:'流程实例ID',dataIndex:'processInstanceId',width:100,sortable: true,
        		renderer:function(value, cellmeta, record){
        			return '<a class="redlink" title="点击查看流程图" href="javascript:void(0)" onclick="myProcessGrid.lookGraphTrace();">' + value + '</a>';
        		}
              	},
                {header:'流程代号',dataIndex:'processCode',width:100,sortable: true,
	        		renderer:function(value, cellmeta, record){
	        			return '<a class="redlink" title="点击查看审批信息" href="javascript:void(0)" onclick="myProcessGrid.lookApproveTrace();">' + value + '</a>';
	        			
	        		}
                },    
            	{header:'流程实例ID',dataIndex:'processInstanceId',width:100,sortable: true,hidden:true},
                {header:'流程启动时间',dataIndex:'startTime',width:140,sortable: true,
            		renderer:function(value, cellmeta, record){
            			if(value == null || value == "") {
              				return "";
              			}else {
              				return value;
//              				var time = Date.parse(value);
//              				var format_time = date_format(time, 1);
////              				alert(format_time);
//              				return format_time;
              			}        		
            		}},
            	{header:'流程结束时间',dataIndex:'endTime',width:140,sortable: true},            	
            	{header:'流程定义ID',dataIndex:'processDefinitionId',width:180,sortable: true, hidden:true} 
            ]),
            tbar: this.vtbar,
            bbar: this.vbbar,
            ds: this.store
        });
    },
    lookGraphTrace : function() {//查看流程图
    	var record = this.selectedRecord();
    	var processInstanceId = record.data.processInstanceId;
    	window.open("showProcessTrack?hisFlag=0&processInstanceId=" + processInstanceId);
    },
    lookGraphTraceHis : function() {//查看流程图
    	var record = this.selectedRecord();
    	var processInstanceId = record.data.processInstanceId;
    	window.open("showProcessTrack?hisFlag=1&processInstanceId=" + processInstanceId);    	
    },
    lookApproveTrace: function() {//查看申请信息及审批意见
    	var record = this.selectedRecord();
    	var businessKey = record.data.businessKey;
    	window.open("showProcessOption/" + businessKey);   
    },
    onQueryClick: function() {
		var customerId = Ext.getCmp("customerId_query").getValue();
		var custName = Ext.getCmp("custName_query").getValue();
		myProcessGrid.store.baseParams = {customerId:customerId, custName:custName};
		myProcessGrid.store.load({params:{start:0,limit:PAGESIZE}});
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
    date_format:function (time, type, separator1, separator2){

    	var TExp = /\d{13,13}/;
    	if(!TExp.test(time))return false;
    	if(separator1 == undefined)separator1 = "-";
    	if(separator2 == undefined)separator2 = ":";


    	var D = new Date(time);
    	var y = D.getFullYear();
    	var m = D.getMonth() + 1;
    	var d = D.getDate();
    	var h = D.getHours();
    	var i = D.getMinutes();
    	var s = D.getSeconds();

    	time = y + separator1 + m + separator1 + d;
    	if(type){
    	time += " " + h + separator2 + i + separator2 + s;
    	}

    	return time;

    	}
});


/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';        //提示方式"side"
    
    myProcessGrid = new MyProcessGrid(Ext.getBody().getViewSize().height, Ext.getBody().getViewSize().width);
    myProcessGrid.store.load({params:{start:0,limit:PAGESIZE}});  
});