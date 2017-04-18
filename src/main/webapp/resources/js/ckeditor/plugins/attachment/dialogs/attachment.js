/*
liuxintao 20141219
*/

CKEDITOR.dialog.add('attachment', function(editor){
	var a;
    var elements = {
            type: 'button',
            id: 'browseAttachment',
            style: 'float:center',
            label: "浏览附件",
            onClick:function(){onAction({url:editor.config.filebrowserBrowseUrl,
            	callback:function(){
            		var files = top.get("files");
            		if(files !=null) {
            			var html = "";
            			for(var i = 0; i < files.length; i++) {
            				if('.jpg,.png,.gif,.jpeg'.indexOf(files[i].extension)!= -1)
            					html += "&nbsp;&nbsp;<img src=\"" + files[i].path + "\" width=\"100\" height=\"100\"/>";
            				else 
            					html += "&nbsp;&nbsp;<a href=\"" + files[i].path + "\">"+ files[i].fileName +"</a>";
            				a.hide();
            			}
            			editor.insertHtml(html);
            		}
        		}
            	}
            )}
        }/*,
    	{
            type: 'button',
            id: 'uploadAttachment',
            style: 'float:center',
            label: "附件上传",
            onClick:function(){onAction({url:editor.config.fileUploadUrl})}
        }*/;
    
    
    return {
        title: '选择附件',
        resizable: CKEDITOR.DIALOG_RESIZE_BOTH,
        minWidth: 200,
        minHeight: 100,
        contents: [{
            id: 'cb',
            name: 'cb',
            label: 'cb',
            title: 'cb',
            style: 'margin-top:40px;margin-left:50px;',
            elements: [
               	elements
                ]
        }],
        /*
        onOk: function(){
        	var file = top.get("file");
        	if('.jpg,.png,.gif,.jpeg'.indexOf(file.extension)!= -1)
        		editor.insertHtml("<img src=\"" + file.path + "\" width=\"100\" height=\"100\"/>");
        	else 
        		editor.insertHtml("<a href=\"" + file.path + "\">"+ file.fileName +"</a>");
        },*/
        onLoad: function(){
        	a = this;
        },
        buttons: [CKEDITOR.dialog.cancelButton]
    };
});
