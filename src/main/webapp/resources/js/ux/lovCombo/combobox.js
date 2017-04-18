// 封装的ComboBoxTree组件
ComboBoxTree = Ext.extend(Ext.form.ComboBox, {
    fieldLabel : 'ComboBoxTree',// 标题名称
    baseUrl : null,
    emptyText : null,
    maxHeight : 300,
    treeId : Ext.id() + '-tree',
    tree : null,
    // all:所有结点都可选中
    // exceptRoot：除根结点，其它结点都可选（默认）
    // folder:只有目录（非叶子和非根结点）可选
    // leaf：只有叶子结点可选
    selectNodeModel : 'exceptRoot',

    initComponent : function() {
        var resultTpl = new Ext.XTemplate('<tpl for="."><div style="height:'
                + this.maxHeight + 'px"><div id="' + this.treeId
                + '"></div></div></tpl>');

        this.addEvents('afterchange');

        Ext.apply(this, {
                    fieldLabel : this.fieldLabel,
                    anchor : '100%',
                    emptyText : this.emptyText || '请选择',
                    forceSelection : true,
                    selectedClass : '',
                    // 值为true时将限定选中的值为列表中的值，
                    // 值为false则允许用户将任意文本设置到字段（默认为false）。
                    selectOnFocus : true,
                    // 值为 ture时表示字段获取焦点时自动选择字段既有文本(默认为false)。
                    mode : 'local',
                    store : new Ext.data.SimpleStore({
                                fields : [],
                                data : [[]]
                            }),
                    editable : false,// 是否编辑
                    triggerAction : 'all',
                    typeAhead : false,
                    tpl : resultTpl,
                    onSelect : Ext.emptyFn()
                });
        ComboBoxTree.superclass.initComponent.call(this);
    },
    expand : function() {
        ComboBoxTree.superclass.expand.call(this);
        if (this.tree.rendered) {
            return;
        }

        Ext.apply(this.tree, {
                    height : this.maxHeight,
                    border : false,
                    autoScroll : true
                });
        if (this.tree.xtype) {
            this.tree = Ext.ComponentMgr.create(this.tree, this.tree.xtype);
        }
        this.tree.render(this.treeId);
        var root = this.tree.getRootNode();
        if (!root.isLoaded()) {
            root.reload();
        }

        this.tree.on('click', function(node) {
                    var selModel = this.selectNodeModel;
                    var isLeaf = node.isLeaf();

                    if ((node == root) && selModel != 'all') {
                        return;
                    } else if (selModel == 'folder' && isLeaf) {
                        return;
                    } else if (selModel == 'leaf' && !isLeaf) {
                        return;
                    }

                    var oldNode = this.getNode();
                    if (this.fireEvent('beforeselect', this, node, oldNode) !== false) {
                        this.setValue(node);
                        this.collapse();

                        this.fireEvent('select', this, node, oldNode);
                        (oldNode !== node) ? this.fireEvent('afterchange',
                                this, node, oldNode) : '';
                    }
                }, this);
        this.tree.expandAll();    
    },

    setValue : function(node) {
        this.node = node;
        var text = node.text;
        this.lastSelectionText = text;
        if (this.hiddenField) {
            this.hiddenField.value = node.id;
        }
        Ext.form.ComboBox.superclass.setValue.call(this, text);
        this.value = node.id;
    },

    getValue : function() {
        return typeof this.value != 'undefined' ? this.value : '';
    },

    getNode : function() {
        return this.node;
    },

    clearValue : function() {
        ComboBoxTree.superclass.clearValue.call(this);
        this.node = null;
    },

    // private
    destroy : function() {
        ComboBoxTree.superclass.destroy.call(this);
        Ext.destroy([this.node, this.tree]);
        delete this.node;
    }
});