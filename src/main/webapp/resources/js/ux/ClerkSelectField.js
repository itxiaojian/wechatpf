Ext.ns('Ipas');
var clerkNameWindow;
var isdeselect = true;
var nameStr;
var number=true;
/******************* 行员选择组件 *****************/
Ipas.ClerkSelectField = Ext.extend(Ext.form.Field,  {
	constructor: function(fieldLabel,name,hiddenName,anchor) {
		this.name = name;
		this.allowBlank = false;
		new Ext.form.TextField({id:hiddenName});
		var PARA_ORG_URL = '/ipas/org/findAttacheArea';
		var ORG_ARCHIVE_URL = '/ipas/org/findOrgArchive';
		var nodeOrgId = [];//放入机构ID
		var orgNum;
		var areaIdStr;//当前所属机构Id，数据权限
		var telrNameText;//输入框对象，方便给输入框设置值

		var isparentId;//判断是否第一次进来
		var panelSwitch;//两个panel相互切换
		var addNum;//添加的总数
		var orgNum;//添加显示的数量
		var element;
		var name;
		var telrName;
		var codeName;
		var code;
		var assobjcode;
		var nameArrays;

		var isparentId2=true;
		var panelSwitch2=true;
		var addNum2=0;
		var orgNum2=0;
		var element2=0;
		var name2 = [];
		var telrName2 = [];
		var codeName2 = [];
		var code2 = [];
		var assobjcode2 = [];
		var nameArrays2 = [];
		
		/*******
		 *柜员业务量明细查询中的柜员姓名查询条件，在以下弹出窗口中选择
		 * @author fanhua
		 *
		 */
		var panel2 = new Ext.Panel({  
			resizable: false,
			plain: true,
			width: 222,
			height: 330,
			autoScroll : true,
			layout: 'column',
		     layoutConfig: {columns: 2},
			bodyStyle :'overflow-x:hidden;overflow-y:scroll', //隐藏水平滚动条,显示用overflow-x:visible
			items:[
			       {
			    	   width: 100,
			    	   border : false
			       },{
			    	   width: 100,
			    	   border : false
			       }
			       ]

		});
		
		/********************clerkNameWindow组件*************************/
		var ClerkNameWindow = Ext.extend(Ext.Window, {
		    constructor: function(areaId,telrName) {
		    	telrNameText = telrName;
		    	areaIdStr=areaId;
		    	isparentId=isparentId2;
		    	panelSwitch=panelSwitch2;
		    	addNum=addNum2;
		    	orgNum=orgNum2;
		    	element=element2;
				name = name2;
				telrName = telrName2;
				codeName = codeName2;
				code = code2;
				assobjcode = assobjcode2;
				nameArrays = nameArrays2;
		    	this.clerkNameTree = new ClerkNameTree(270,330);
		    	
		    	this.vtbar=new Ext.Toolbar({
		    		items: [
		    		        	'->',{xtype:'button',text:'确认',iconCls:'add',handler:function(){
		    		        		this.hide();
		    		        	},scope:this},
		    		        	'-',{xtype:'button',text:'清空',iconCls:'refresh',handler:function(){
		    		        		isparentId=true;
		    		    			panelSwitch=true;
		    		    			addNum=0;
		    		    			orgNum=0;
		    		    			element=0;
		    		    			name = [];
		    		    			telrName = [];
		    		    			codeName = [];
		    		    			code = [];
		    		    			assobjcode=[];
		    		    			nameArrays = [];
		    		    			if(clerkNameWindow!=null){
		    		    				clerkNameWindow.get(2).get(0).getEl().update('',true);
		    		    				clerkNameWindow.get(2).get(1).getEl().update('',true);
		    		    			}
		    		    			telrNameText.setValue("");
		    		    			ClerkGrid.store.load({params:{start:0,limit:50,OrgId:1}});
		    		        	},scope:this}
		    		        ]
		    	});
		        
		    	ClerkNameWindow.superclass.constructor.call(this, {
		        	width: 750,
					anchor: '100%',
					height: 390,
					resizable: false,
					plain: true,
					modal: true,
					layout: 'column',
					closable:true,
					tbar:this.vtbar,
					closeAction: 'hide',
					listeners:{'hide':{fn:this.onCheckHide}},
		            items: [
		                    this.clerkNameTree,
		                    ClerkGrid,
		                    panel2
		                    ]
		        });
		    },
		    onCheckHide:function(){
		    	addNum2=addNum;
		    	orgNum2=orgNum;
		    	element2=element;
				name2 = name;
				telrName2 = telrName;
				codeName2 = codeName;
				code2 = code;
				assobjcode2 = assobjcode;
				nameArrays2 = nameArrays;
		    	isparentId=true;
		    	panelSwitch=true;
		    }
		});
		this.getClerkCode = function(){//获取行员代号数组
			if(code==null){
				return null;
			}else{
				var s="";
       			var arr=(code+"").split(",");
       			for(var i=0;i<arr.length;i++){
       				if(arr[i] != ""){
       					s+=arr[i]+",";
       				}
       			}
       			s=s.substring(0, s.lastIndexOf(","));
				return s;
			}
		};
		this.getAssobjcode = function(){//获取行员代号数组
			if(assobjcode==null){
				return null;
			}else{
				var s="";
       			var arr=(assobjcode+"").split(",");
       			for(var i=0;i<arr.length;i++){
       				if(arr[i] != ""){
       					s+=arr[i]+",";
       				}
       			}
       			s=s.substring(0, s.lastIndexOf(","));
				return s;
			}
		};
		this.getClerkName = function(){//获取行员名称数组
			if(nameArrays==null){
				return null;
			}else{
				return nameArrays;
			}
		};
		this.setClerkName = function(value){//获取行员名称数组
			this.setValue(value);
		};
		this.empty = function(){
			isparentId2=true;
			panelSwitch2=true;
			addNum2=0;
			orgNum2=0;
			element2=0;
			name2 = [];
			telrName2 = [];
			codeName2 = [];
			code2 = [];
			assobjcode2=[];
			nameArrays2 = [];
			if(clerkNameWindow!=null){
				clerkNameWindow.get(2).get(0).getEl().update('',true);
				clerkNameWindow.get(2).get(1).getEl().update('',true);
			}
			document.getElementById('check').checked=false;
			vsm.clearSelections();
		};
		/***********************AreaOrgTree组件***********************************/
		var ClerkNameTree  = Ext.extend(Ext.tree.TreePanel, {
			constructor : function(width, height) {

				ClerkNameTree.superclass.constructor.call(this, {
					collapsible :false,
					autoScroll : true,
					enableDD : false,//是否支持拖拽效果
					containerScroll : true,//是否支持滚动条
					width : width,
					height : height,
					expanded : true,
					border : true,
					frame : true,
					rootVisible : false,//是否显示根节点
					margins : '0 0 0 0',
					loader : new Ext.tree.TreeLoader({
						dataUrl : '/ipas/org/orgTree'
					}),
					root:new Ext.tree.AsyncTreeNode({
				          id : "treeContact",
				         // text: '机构树',
				          draggable:false,                                 
				          expanded: true   // 展开根节点下的节点
				    }),
					listeners : {
						'click': {fn: this.onCheckClick, scope: this},
						'load' : {fn: function(){
							clerkNameWindow.clerkNameTree.expandAll();
						}, scope: this},
					},
					tbar : new Ext.Toolbar({
						items : [
						{
							iconCls : "refresh",
							text : "刷新",
							handler : function() {
								isparentId = true;
								clerkNameWindow.clerkNameTree.root.reload();
								clerkNameWindow.clerkNameTree.expandAll();
							}
						}, {
							text : "展开",
							iconCls : "expand",
							handler : function() {
								clerkNameWindow.clerkNameTree.expandAll();
							}
						}, {
							text : "收起",
							iconCls : "collapse",
							handler : function() {
								clerkNameWindow.clerkNameTree.collapseAll();
							}
						}
						]
					})
				});
			},
			onCheckClick : function(node,e) {
				var OrgId = node.attributes.id;
				nodeOrgId.push(OrgId);
				Ext.Ajax.request({
					url: ORG_ARCHIVE_URL,
					method: 'POST',
					success: function(){
						ClerkGrid.store.load({params:{start:0,limit:50,OrgId:nodeOrgId}});
					},
					failure: function(){
						Ext.Msg.alert('系统提示','添加失败！');
					}
				});
				NODES = node;
			},
			onBeforeLoad : function(node, e) {
				var pid = node.attributes.id;
				if(isparentId == true){
					pid = areaIdStr;
				this.loader.dataUrl = PARA_ORG_URL+"?id="+pid+"&isparentId=true"; //定义子节点的Loader
				isparentId = false;
				}else{
					if(pid==null){
						pid=areaIdStr;
					}
					this.loader.dataUrl = PARA_ORG_URL+"?id="+pid; //定义子节点的Loader
				}
			}
		});
		/****************AreaGrid***********************/
		var vsm = new Ext.grid.CheckboxSelectionModel({
				header : '',
				checkOnly : true,
				listeners : {
//					'click': {fn: function(e){
//						for(var i=0;i<code.length;i++){
//							var codes = ClerkGrid.getSelectionModel().getSelections()[i].data.userCode;
//	//						alert(selectMode.isSelected(i));
//							if(code[i] == rec.data.userCode){
//								document.deleteClick(i+1,isdeselect);
//							}
//						}
//					}, scope: this}
					'rowdeselect':{fn:function(selectMode,rowindex,rec){
						for(var i=0;i<code.length;i++){
							if(code[i] == rec.data.userCode){
								if(number == true){
									element = element-1;
//									telrNameText.setValue(nameStr.trim()+"等（"+element+"）人");
									document.ClerkDeleteClick(i+1);
								}
							}
						}
						number = true;
					}}
				}
			});
		var ClerkGrid = new Ext.grid.GridPanel({
		    store: new Ext.data.Store({
		    	proxy : new Ext.data.HttpProxy({
					url : ORG_ARCHIVE_URL,
					method : 'POST'
				}),
				reader : new Ext.data.JsonReader({},
						new Ext.data.Record.create([{name : 'userCode'},{name : 'userName'},{name : 'assobjcode'}]))
		    }),
		     sm: vsm,
		     cm:new Ext.grid.ColumnModel([ 
              new Ext.grid.RowNumberer({header : '<input id="check" type="checkbox"'+
              ' onclick="document.CheckAllClick()" />'}),
		                                   vsm,
		    {header:'考核对象代号',dataIndex:'assobjcode',width:90,sortable:true,hidden:true},
            {header:'行员代号',dataIndex:'userCode',width:80,sortable:true},
            {header:'行员名称',dataIndex:'userName',width:90,sortable:true}                       
		     ]),
		    width: 240,
		    //frame: true,
			height: 330,
			//autoScroll : true,
			bodyStyle :'overflow-x:hidden;overflow-y:scroll', //隐藏水平滚动条,显示用overflow-x:visible
			viewConfig: {
		        forceFit: false
		    },
			loadMask: {
				msg: '正在载入数据，请稍后...'
			}
		});
		document.CheckAllClick = function(){
			if(document.getElementById('check').checked == true){
				ClerkGrid.getSelectionModel().selectAll();
			}else{
				for(var k=0;vsm.getCount();k++){
					ClerkGrid.getSelectionModel().deselectRange(k,k+1);
				}
				telrNameText.empty();
				telrNameText.setValue('');
				isparentId=true;
				panelSwitch=true;
				addNum=0;
				orgNum=0;
				element=0;
				name = [];
				telrName = [];
				codeName = [];
				code = [];
				assobjcode = [];
				nameArrays = [];
			}
		};
		ClerkGrid.on("click",function(e){
			var count = ClerkGrid.getSelectionModel().getCount();
			for(var namber=0;namber<count;namber++){
				var userName = ClerkGrid.getSelectionModel().getSelections()[namber].data.userName;
				var userCode = ClerkGrid.getSelectionModel().getSelections()[namber].data.userCode;
				var userAssobjcode = ClerkGrid.getSelectionModel().getSelections()[namber].data.assobjcode;
				if(name.length>0){
					for(var y=1;y<name.length;y++){
						if(userName+userCode == name[y]){
//							Ext.Msg.alert('提示','该用户已存在！');
							break;
						}else if(name.length == y+1){
							if(panelSwitch == true){
								addNum++;
								orgNum++;
								code[addNum-1]=userCode;
								assobjcode[addNum-1] = userAssobjcode;
								name[addNum]=userName+userCode;
								nameArrays[addNum-1] = userName;
								nameStr = userName;
								clerkNameWindow.get(2).get(0).getEl().insertHtml('beforeEnd','<div id = "usNm'+addNum+'">'+userName+
								'<input type="image" src="/resources/images/icons/cross.png" onclick="document.ClerkDeleteClick('+addNum+','+
								ClerkGrid.getStore().indexOf(ClerkGrid.getSelectionModel().getSelections()[namber])+');" style="color: red" />'+'</div>'
								);
								panelSwitch = false;
								element = orgNum;
								telrNameText.setValue(nameStr.trim()+"等（"+element+"）人");
								break;
							}else{
								addNum++;
								orgNum++;
								code[addNum-1]=userCode;
								assobjcode[addNum-1] = userAssobjcode;
								name[addNum]=userName+userCode;
								nameArrays[addNum-1] = userName;
								nameStr = userName;
								clerkNameWindow.get(2).get(1).getEl().insertHtml('beforeEnd','<div id = "usNm'+addNum+'">'+userName+
								'<input type="image" src="/resources/images/icons/cross.png" onclick="document.ClerkDeleteClick('+addNum+','+
								ClerkGrid.getStore().indexOf(ClerkGrid.getSelectionModel().getSelections()[namber])+');" style="color: red" />'
								+'</div>');
								panelSwitch = true;
								element = orgNum;
								telrNameText.setValue(nameStr.trim()+"等（"+element+"）人");
								break;
							}
						}
					}
				}
				if(name.length==0){
					if(panelSwitch == true){
						addNum++;
						orgNum++;
						code[addNum-1]=userCode;
						assobjcode[addNum-1] = userAssobjcode;
						name[addNum]=userName+userCode;
						nameArrays[addNum-1] = userName;
						nameStr = userName;
						clerkNameWindow.get(2).get(0).getEl().insertHtml('beforeEnd','<div id = "usNm'+addNum+'">'+userName
						+'<input type="image" src="/resources/images/icons/cross.png" onclick="document.ClerkDeleteClick('+addNum+','+
						ClerkGrid.getStore().indexOf(ClerkGrid.getSelectionModel().getSelections()[namber])+');" style="color: red" />'
						+'</div>');
						panelSwitch = false;
						element = orgNum;
					}else{
						addNum++;
						orgNum++;
						code[addNum-1]=userCode;
						assobjcode[addNum-1] = userAssobjcode;
						name[addNum]=userName+userCode;
						nameArrays[addNum-1] = userName;
						nameStr = userName;
						clerkNameWindow.get(2).get(1).getEl().insertHtml('beforeEnd','<div id = "usNm'+addNum+'">'+userName
						+'<input type="image" src="/resources/images/icons/cross.png" onclick="document.ClerkDeleteClick('+addNum+','+
						ClerkGrid.getStore().indexOf(ClerkGrid.getSelectionModel().getSelections()[namber])+');" style="color: red" />'
						+'</div>');//ClerkGrid.getStore().indexOf(ClerkGrid.getSelectionModel().getSelections()[namber])
						panelSwitch = true;
						element = orgNum;
					}
					telrNameText.setValue(nameStr.trim()+"等（"+element+"）人");
				}
			}
		});
		document.ClerkDeleteClick = function(e,storeName){ 
//			alert(ClerkGrid.getStore().getAt(e).data.userName+"=="+e);
			name[e] = "";
			if(storeName!=null){
				number=false;
				element = element-1;
				if(storeName<ClerkGrid.getStore().getCount()){
					var storNameString = ClerkGrid.getStore().getAt(storeName).data.userName;
//					alert(storNameString+"=="+nameArrays[e-1]);
					if(storNameString==nameArrays[e-1]){
						ClerkGrid.getSelectionModel().deselectRow(storeName);
					}
				}
			}else{
				number=true;
			}
			nameArrays[e-1] = "";
			for(var str=nameArrays.length;str>=1;str--){
				if(nameArrays[str] != ""){
					nameStr = nameArrays[str];
				}
			}
			code[e-1]="";
			assobjcode[e-1] = "";
			if(nameStr == null){
				telrNameText.setValue("");
			}else{
				telrNameText.setValue(nameStr.trim()+"等（"+element+"）人");
			}
			orgNum = element;
			Ext.get('usNm'+e).remove();
		};
		Ipas.ClerkSelectField.superclass.constructor.call(this, {
			fieldLabel :fieldLabel,
		 	layout:'form',
		 	anchor:anchor,
			listeners : {
				'focus' : {//获取焦点时触发
					fn : function() {
						if (!this.readOnly) {
							clerkNameWindow = new ClerkNameWindow('',this);
//							clerkNameWindow.setPosition(this.el.getX(), this.el.getY()+25);
							clerkNameWindow.show();
						}
					},
					scope : this
				}
			}
	 })
		}
	});