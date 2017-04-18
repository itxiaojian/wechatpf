/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	// 工具栏（基础'Basic'、全能'Full'、自定义）plugins/toolbar/plugin.js
    config.toolbar = 'Full';
	config.toolbar_Full = [
		//['Source','-','Save','NewPage','Preview','-','Templates'],
		['Source','-','Save','Preview','-','Templates'],
		['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
		['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
		//['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
		'/',
		['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		['Link','Unlink','Anchor'],
		//['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
		['Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
		'/',
		['Styles','Format','Font','FontSize'],
		['TextColor','BGColor','Attachment']
	];


    //工具栏是否可以被收缩
	config.toolbarCanCollapse = true;
    //工具栏的位置
	config.toolbarLocation = 'top';//可选：bottom
    //工具栏默认是否展开
	config.toolbarStartupExpanded = true;

};

