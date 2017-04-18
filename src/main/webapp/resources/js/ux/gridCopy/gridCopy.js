if(!Ext.grid.GridView.prototype.templates) {        
    Ext.grid.GridView.prototype.templates = {};        
}        
Ext.grid.GridView.prototype.templates.cell =  new  Ext.Template(        
     '<td class="x-grid3-col x-grid3-cell x-grid3-td-{id} x-selectable {css}" style="{style}" tabIndex="0" {cellAttr}>' ,        
     '<div class="x-grid3-cell-inner x-grid3-col-{id}" {attr}>{value}</div>' ,        
     '</td>'        
);     
  
Ext.grid.GridView.prototype.cellTpl = new Ext.Template(Ext.grid.GridView.prototype.cellTpl.html    
        .replace('unselectable="on"', '').replace('class="','class="x-selectable '));  