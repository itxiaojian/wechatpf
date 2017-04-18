var INVOLVED_PROCESS_GRID_STORE_URL = 'involvedProcess/list';
var PAGESIZE=20;

/**************************InvolvedProcessGrid*******************************************/
InvolvedProcessGrid = Ext.extend(UxGrid, {
	pageSizeCombo: null,
	vtbar:null,				//面板顶部的工具条	
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.store = new Ext.data.Store({
    		baseParams: {rolekind: ROLE_KIND},//Grid Store//Grid Store
            proxy: new Ext.data.HttpProxy({url: INVOLVED_PROCESS_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({totalProperty: 'total', root:'rows'},[
            		{name:'processDefinitionId'},{name:'processInstanceId'},{name:'businessKey'},{name:'activityId'},
            		{name:'activityName'},{name:'bkhr'},{name:'df'},{name:'shzt'},{name:'xm'},
            		{name:'khqh'},{name:'startTime'},{name:'endTime'},{name:'businessKey'}
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
        InvolvedProcessGrid.superclass.constructor.call(this, {
        	renderTo:Ext.getBody(),
        //	title: '已处理流程',
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
                {header:'业务主键',dataIndex:'businessKey',width:100,sortable: true, hidden:true},               
                {header:'被考核人',dataIndex:'bkhr',width:100,sortable: true,hidden:true},    
                {header:'被考核人',dataIndex:'xm',width:200,sortable: true},
                {header:'考核期号',dataIndex:'khqh',width:200,sortable: true},
                {header:'得分',dataIndex:'df',width:100,sortable: true},
                {header:'流程实例ID',dataIndex:'processInstanceId',width:100,sortable: true},               
                {header:'流程启动时间',dataIndex:'startTime',width:140,sortable: true},
            	{header:'流程结束时间',dataIndex:'endTime',width:140,sortable: true},
            	{header:'流程定义ID',dataIndex:'processDefinitionId',width:180,sortable: true,hidden:true},
            	{header:'当前节点',dataIndex:'activityName',width:140,sortable: true,
            		renderer:function(value, cellmeta, record){
            			var activityId = record.data.activityId;
            			var flag = record.data.processFlag;
            			if(activityId == null || activityId == "") {
            				if(flag == 0){
                				return '<a class="redlink" title="点击查看流程图" href="javascript:void(0)" onclick="involvedProcessGrid.lookGraphTrace();">查看会签流程</a>';
            				}else{
            					return '<a class="redlink" title="点击查看流程图" href="javascript:void(0)" onclick="involvedProcessGrid.lookHisGraphTrace();">流程已结束</a>';
            				}
            			}else {
            				return '<a class="redlink" title="点击查看流程图" href="javascript:void(0)" onclick="involvedProcessGrid.lookGraphTrace();">' + value + '</a>';
            			}
            		}
            	}
//            	,
//            	{header:'流程状态',dataIndex:'processFlag',width:100,sortable: true,
//            		renderer:function(value, cellmeta, record){
//            			if(value == "1") {
//            				return "<span style='color:green;font-weight:bold;'>流程已结束</span>";
//            			}else if(value == "0") {
//            				return "<span style='color:red;font-weight:bold;'>运行中</span>";
//            			}else {
//            				return value;
//            			}
//            		}
//            	}            	
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
    lookHisGraphTrace : function() {//查看历史流程图
    	var record = this.selectedRecord();
    	var processInstanceId = record.data.processInstanceId;
    	window.open("showProcessTrack?hisFlag=1&processInstanceId=" + processInstanceId);    	    	
    },
    onQueryClick: function() {
		var bkhr = Ext.getCmp("bkhr_query").getValue();
		var khqh = Ext.getCmp("khqh_query").getValue();
		involvedProcessGrid.store.baseParams = {bkhr:bkhr, khqh:khqh,rolekind: ROLE_KIND};
		involvedProcessGrid.store.load({params:{start:0,limit:PAGESIZE}});
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
    showModelDialog: function(page,width,height){//用A4纸大小打开窗口
        var re= window.showModalDialog(page,null,'dialogWidth:'+width+'mm;dialogHeight:'+height+'mm;edge:Raised;center:yes;help:no;resizable:no;status:no;scroll:yes')
        if(re==1){
            window.location.reload();
        }
    }
});


/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';        //提示方式"side"
    
    involvedProcessGrid = new InvolvedProcessGrid(Ext.getBody().getViewSize().height, Ext.getBody().getViewSize().width);
    involvedProcessGrid.store.load({params:{start:0,limit:PAGESIZE}});  
});