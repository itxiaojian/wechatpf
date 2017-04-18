Ext.ns('Ipas');
/******************* 行员选择组件 *****************/
Ipas.HySelectField = Ext.extend(Ext.form.Field,  {
	 constructor: function() {
		 //机构树
		 var orgTree = new Ext.tree.TreePanel({
				 region:'west',
				 width : 200,
				 height:380,
				 rootVisible : false,//是否显示根节点
				 loader : new Ext.tree.TreeLoader({
						dataUrl : "/ipas/org/getOrgTree?id=0"
				 }),
				 root : new Ext.tree.AsyncTreeNode({
					id: "0",
					level: "0",
					hidden: false
				 }),	
				 listeners : {
						'beforeload' : {//在节点加载之前触发，返回false取消操作
							fn : function(node, e) {
								var pid = node.attributes.id;
								orgTree.loader.dataUrl ='/ipas/org/getOrgTree?id=' + pid; //定义子节点的Loader
							},
							scope : this
						},
						'click': {
							fn : function(node, e) {
								var orgId = node.id;
								hangyGrid.store.load({params:{orgId:orgId}});
							},
							scope : this
						}
				}
		 });
		 //行员列表
		 var sm = new Ext.grid.CheckboxSelectionModel(); 
		 var hangyGrid = new Ext.grid.GridPanel({
			 region:'center',
	         loadMask: {
	                msg : '正在载入数据,请稍候...'
	         },			 
			 sm: sm,
	         cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
	                                          sm,
	            	{header:'ID',dataIndex:'id',width:100,sortable: true, hidden:true},
	            	{header:'行员',dataIndex:'userName',width:100,sortable: true}
	         ]),			 
			 ds:new Ext.data.Store({        
		            proxy: new Ext.data.HttpProxy({url: '/ipas/org/getHyList', method: 'POST'}),
		            reader: new Ext.data.JsonReader({fields:[
		            		{name:'id'},{name:'userName'},{name:'userCode'}
		            ]})
		     })
		      
		 });
		 //行员选择窗口
		 var win = new Ext.Window({
			 layout:'border',
			 title:'行员选择',
			 plain: true,
			 width: 600,
			 height:400,
			 items:[orgTree, hangyGrid],
			 closeAction: 'hide'
		 });
		 Ipas.HySelectField.superclass.constructor.call(this, {
			 	fieldLabel :'行员',
				listeners : {
					'focus' : {//获取焦点时触发
						fn : function() {
							win.setPosition(this.el.getX(), this.el.getY()+25);
							win.show();
						},
						scope : this
					}
				}
		 })
	 }
})