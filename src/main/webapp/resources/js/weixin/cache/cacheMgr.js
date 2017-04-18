var CACHE_GRID_STORE_URL = '/wxauth/cache/getWxCacheList';
var PAGESIZE=20;

/***********************WxCacheGrid组件*******************
 *author        ： zhangyi
 *description   : 
 *date          : 2015-05-28
********************************************************/
WxCacheGrid = Ext.extend(UxGrid, {
	setRemarkWindow:null,
	vtbar:null,				//面板顶部的工具条
	vbbar:null,				//面板底部的工具条
    store:null,
    constructor: function(height, width){
    	this.store = new Ext.data.Store({          //Grid Store
            proxy: new Ext.data.HttpProxy({url: PROJECT_NAME+""+CACHE_GRID_STORE_URL, method: 'POST'}),
            reader: new Ext.data.JsonReader({fields:[
                   {name:'key'},{name:'value'}
            ]})
        });
    	this.vtbar = new Ext.Toolbar({
            items:[
                {text:'清除缓存',iconCls: 'delete',handler:this.onEvictClick,scope:this},
                '-',{text:'刷新',iconCls: 'refresh',handler:this.onRefreshClick,scope:this}
            ]
        });	
        var sm = new Ext.grid.CheckboxSelectionModel(); 
        WxCacheGrid.superclass.constructor.call(this, {
        	region:'center',
        	stripeRows: true,
            frame: true,
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
                {header:'key',dataIndex:'key',width:120,sortable: true},
                {header:'value',dataIndex:'value',width:800,sortable: true}
            ]),
            tbar: this.vtbar,
            ds: this.store
        });
    },
    onEvictClick: function() {
    	var records=this.getSelectionModel().getSelections();
   		if(records.length > 0) {
   			var keys = new Array();
   			for(var i =0; i<records.length; i++) {
   				keys.push(records[i].data.key);
   			}
   		 Ext.Msg.confirm("提醒信息", "确定要清除这 " + records.length + " 条缓存信息吗",function(btn){
             if (btn == 'yes') {
                 Ext.Ajax.request({
                        url:PROJECT_NAME+'/wxauth/cache/evictCache',
                        method : 'POST', 
                        params: { keys: keys},
                        success: function(form, action) { 
                             Ext.MessageBox.alert("系统提示:", BLANKSTR + "清除成功!" + BLANKSTR);
                             wxCacheGrid.store.load();
                        },
                        failure: function(form, action) {
                         Ext.MessageBox.alert("系统提示:", BLANKSTR + "清除失败!" + BLANKSTR);
                        }
                     });                 
             }
         });
   		}else{
   			Ext.Msg.alert('系统提示', BLANKSTR + '请选择一条记录' + BLANKSTR);
   		}      	
    },
    onRefreshClick: function() {
    	 wxCacheGrid.store.load();
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

/********************* onReady 组件渲染及处理 *******************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    wxCacheGrid = new WxCacheGrid(Ext.getBody().getViewSize().height);
    wxCacheGrid.store.load();
    new Ext.Viewport({
    	layout: 'border',
    	items:[{
				region: "center",
				items:[
				       wxCacheGrid
				]
			}
    	]
    });
});