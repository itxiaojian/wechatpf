var p_id=""; 
var orgPanel,appContextmenu,orgTreePanel= new Ext.tree.TreePanel({
            id:'orgTree',
            //enableDD: true,
            //region : 'center',
            autoScroll : true,
			containerScroll : true,//是否支持滚动条
            enableSort:false,
            expand : true,
            border:false,
            width:'100%',
            height:'600',
            region:"west",
            margins:'0 12 0 0',
            title:'报修主题',
            useArrows: true,
            animate: true,
            rootVisible : true,//是否显示根节点
            dataUrl : PROJECT_NAME+'/bx/bxzt/orgOrMebTree',
            root: new Ext.tree.AsyncTreeNode({
                //id:'0',
                id : '0',
		        text: '报修主题',
		        draggable:false,    
                expanded:true
            }),
            tbar :[{
                iconCls : 'refresh',
                text : '刷新',
                handler : function() {
                    orgTreePanel.loader.dataUrl = PROJECT_NAME+'/bx/bxzt/orgOrMebTree';
                    orgTreePanel.root.reload();
                    orgTreePanel.root.expand(true);
                }
            }, {
                text : '展开',
                tooltip : '展开',
                iconCls : 'expand',
                handler : function(){
                    orgTreePanel.root.expand(true);
                }
            }, {
                text : '收起',
                tooltip : '收起',
                iconCls : 'collapse',
                handler : function(){
                    orgTreePanel.collapseAll();
                }
            }],
            
            contextMenu: new Ext.menu.Menu({
                items: [{
                    iconCls: 'add',
                    text: '新建'
                }, {
                    iconCls: 'edit',
                    text: '编辑'
                }, {
                    iconCls: 'delete',
                    text: '删除'
                }],
                listeners: {
                    itemclick: function(item){
                    	console.log(item);
                        var nodeDataDel = item.parentMenu.contextNode.attributes;
                        var parentNodeData = item.parentMenu.contextNode.parentNode;
                        var childNode= item.parentMenu.contextNode;
                        //var nodeData = orgPanel.getSelectionModel().getSelectedNode();
                        switch (item.iconCls) {
                            case 'add':
                                createOrganizationFun(nodeDataDel,parentNodeData);
                                break;
                            case 'edit':
                                updateOrganizationFun(nodeDataDel,parentNodeData);
                                break;
                            case 'delete':
                                deleteOrganizationFun(nodeDataDel.id,childNode.childNodes.length==0?true:false);
                                break;
                        }
                        item.parentMenu.hide();
                    }
                }
            }),
            listeners: {
                afterrender: function(t) {
                    treeMask=new Ext.LoadMask(t.getEl(), {msg:"数据加载中..."})
                    orgTreePanel.root.expand(true);
                    orgPanel = t;
                },
                contextmenu: function(node, e){
                    appContextmenu = false;
                    node.select();
                    var tree = node.getOwnerTree();
                    var c = tree.contextMenu;
                    c.contextNode = node;
                    c.showAt(e.getXY());
                }

            },
	onBeforeLoad : function(node, e) {
		var pid = node.attributes.id;
		if(isparentId == true){
			pid = areaIdStr;
		this.loader.dataUrl = PROJECT_NAME+"/bx/bxzt/findAttacheArea?id="+pid+"&isparentId=true"; //定义子节点的Loader
		isparentId = false;
		}else{
			if(pid==null){
				pid=areaIdStr;
			}
			this.loader.dataUrl = PROJECT_NAME+"/bx/bxzt/findAttacheArea?id="+pid; //定义子节点的Loader
		}
	}

});
var creatOrganizationWin,
createOrganizationFun = function (data,pData) {
	if(pData==null){p_id="0";}else if(pData.id=='0'){p_id='1';}else{p_id=pData.id;}
	creatOrganizationWin = new Ext.Window({
        title:"新增",
        width:480,
        buttonAlign:'center',
        modal:true,
        items:[
            {
                xtype:'form',
                plain:true,
                url:PROJECT_NAME+'/bx/bxzt/add',
                labelWidth:60,
                baseCls:"x-plain",
                bodyStyle:"padding:12px;margin-left:42px;",
                defaultType:"textfield",
                labelAlign:'right',
                defaults:{
                    anchor:'80%'
                },
                items:[
                    {
                        fieldLabel:'<font color="red">*</font>名称',
                        name:'name',
                        allowBlank:false
                    },/*{
                        xtype:'textarea',
                        fieldLabel:'组织描述',
                        name:'desc',
                        allowBlank:false,
                        value:data.desc
                    },*/{
                    	 name:"dm",
                        fieldLabel:'<font color="red">*</font>代码',
                        allowBlank:false
                    },/*{
                        xtype:'combo',
                        id:'typeCombo',
                        fieldLabel:'类型',
                        typeAhead:true,
                        triggerAction:'all',
                        hiddenName:'type',
                        lazyRender:true,
                        allowBlank:false,
                        editable:false,
                        mode:'local',
                        store:[
                            [ 0, '公司' ],
                            [1, '部门'],
                            [2, '其他组织']
                        ]
                    },*/{
                        name:"px",
                        fieldLabel:'<font color="red">*</font>排序',
                        regex: /^[0-9]*$/ ,
                        maxLength:3,
                        allowBlank:false
                    },{
                        name:"bz",
                        fieldLabel:'备注'
                        //allowBlank:false,
                    },{
                        hidden: true,
                        name:'pid',
                        value:p_id
                    },{
                        hidden: true,
                        name:'sjbh',
                        value:data.id
                    }
                ]}
        ],
        buttons:[
            {
                text:'保存',
                handler:function () {
                    var fromPanel = creatOrganizationWin.get(0);
                    if (!fromPanel.getForm().isValid()) {
                        Ext.Msg.alert("提示", "请按相关提示正确填写");
                        return;
                    }
                    fromPanel.form.submit({
                        waitMsg:'正在处理中...',
                        failure:function (form, action) {
                            Ext.Msg.alert('错误消息', "新增失败,请联系管理员!");
                        },
                        success:function (form, action) {
                            var JSON = Ext.decode(action.response.responseText);
                            if (JSON.message == 1) {
                                Ext.MessageBox.alert('消息提示', "代码已经存在！");
                            } else {
                                Ext.MessageBox.alert('消息提示', '新增成功!');
                                orgTreePanel.root.reload();
                                orgTreePanel.root.expand(true);
                                creatOrganizationWin.destroy();
                            }
                        }
                    });
                }
            },
            {
                text:'取消',
                handler:function () {
                	creatOrganizationWin.destroy();
                }
            }
        ]
    });
	creatOrganizationWin.show();
    //赋值控件显示值
   // Ext.getCmp("typeCombo").setValue(1);
},
//编辑组织的方法
updateOrganizationFun = function (data,pData) {
    updateOrganizationWin = new Ext.Window({
        title:"编辑",
        width:480,
        buttonAlign:'center',
        modal:true,
        items:[
            {
                xtype:'form',
                plain:true,
                url:PROJECT_NAME+'/bx/bxzt/edit',
                labelWidth:60,
                baseCls:"x-plain",
                bodyStyle:"padding:12px;argin-left:42px;",
                defaultType:"textfield",
                labelAlign:'right',
                defaults:{
                    anchor:'80%'
                },
                items:[
                    {
                        fieldLabel:'<font color="red">*</font>名称',
                        name:'name',
                        allowBlank:false,
                        value:data.text
                    },/*{
                        xtype:'textarea',
                        fieldLabel:'组织描述',
                        name:'desc',
                        allowBlank:false,
                        value:data.desc
                    },*/{
                    	 name:"dm",
                        fieldLabel:'<font color="red">*</font>代码',
                        allowBlank:false,
                        readOnly:true,
                        value:data.bmbh
                    },/*{
                        xtype:'combo',
                        id:'typeCombo',
                        fieldLabel:'类型',
                        typeAhead:true,
                        triggerAction:'all',
                        hiddenName:'type',
                        lazyRender:true,
                        allowBlank:false,
                        editable:false,
                        mode:'local',
                        store:[
                            [ 0, '公司' ],
                            [1, '部门'],
                            [2, '其他组织']
                        ]
                    },*/{
                        name:"px",
                        fieldLabel:'<font color="red">*</font>排序',
                        regex: /^[0-9]*$/ ,
                        maxLength:3,
                        allowBlank:false,
                        value:data.px
                    },{
                        name:"bz",
                        fieldLabel:'备注',
                        //allowBlank:false,
                        value:data.bz
                    },{
                        hidden: true,
                        name:'pid',
                        value:pData.id
                    },{
                        hidden: true,
                        name:'id',
                        value:data.id
                    }
                ]}
        ],
        buttons:[
            {
                text:'确定',
                handler:function () {
                    var fromPanel = updateOrganizationWin.get(0);
                    if (!fromPanel.getForm().isValid()) {
                        Ext.Msg.alert("提示", "请按相关提示正确填写");
                        return;
                    }
                    fromPanel.form.submit({
                        waitMsg:'正在处理中...',
                        failure:function (form, action) {
                            Ext.Msg.alert('错误消息', "修改失败,请联系管理员!");
                        },
                        success:function (form, action) {
                            var JSON = Ext.decode(action.response.responseText);
                            if (JSON.message == 1) {
                                Ext.MessageBox.alert('消息提示', "代码已经存在！");
                            } else {
                                Ext.MessageBox.alert('消息提示', '编辑成功!');
                                orgTreePanel.root.reload();
                                orgTreePanel.root.expand(true);
                                updateOrganizationWin.destroy();
                            }
                        }
                    });
                }
            },
            {
                text:'取消',
                handler:function () {
                    updateOrganizationWin.destroy();
                }
            }
        ]
    });
    updateOrganizationWin.show();
    //赋值控件显示值
   // Ext.getCmp("typeCombo").setValue(1);
},
deleteOrganizationFun  = function (id,flag) {
	if(flag){
    	Ext.Msg.confirm("提醒信息", "确定要删除这条信息吗",function(btn){
			if (btn == 'yes') {
		       	Ext.Ajax.request({
		       		url: 'delete', 
			       	   method : 'POST', 
			       	   params: {id:id},
		               success: function(form, action) {
			               Ext.MessageBox.alert("系统提示:","删除成功!");
			               orgTreePanel.root.reload();
		               },
		               failure: function(form, action) {
		            	   Ext.MessageBox.alert("系统提示:","删除失败!");
		               }
			       	});					
			}
    	});	
	}else{
		 Ext.MessageBox.alert("系统提示:","请先删除该目录下存在的子节点!");
	}
}

/*********************onReady 组件渲染及处理**********************************************/
Ext.onReady(function() {
    Ext.QuickTips.init();                               //开启快速提示
    Ext.form.Field.prototype.msgTarget = 'side';
    new Ext.Viewport({
    	layout: 'border',
    	items:[
    	       orgTreePanel
    	]
    });
});