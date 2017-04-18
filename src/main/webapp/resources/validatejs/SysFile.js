var saveSysFileConfig = {
	folderId:[{rule:length,params:{min:0,max:255},msg:"文件夹 长度不在0-255范围"}],
	fileName:[{rule:notBlank,msg:"文件名 不能为空"},{rule:length,params:{min:0,max:256},msg:"文件名 长度不在0-256范围"}],
	descn:[{rule:length,params:{min:0,max:256},msg:"描述 长度不在0-256范围"}],
	path:[{rule:length,params:{min:0,max:256},msg:"文件路径 长度不在0-256范围"}],
	type:[{rule:length,params:{min:0,max:200},msg:"文件类型 长度不在0-200范围"}],
	extension:[{rule:length,params:{min:0,max:200},msg:"扩展名 长度不在0-200范围"}],
	size:[{rule:integer,msg:"文件大小 必须是数字"}],
	createTime:[{rule:date,msg:"创建时间 必须是日期"}],
	refId:[{rule:length,params:{min:0,max:32},msg:"对象主键 长度不在0-32范围"}],
	refObj:[{rule:length,params:{min:0,max:100},msg:"引用对象 长度不在0-100范围"}],
	userId:[{rule:length,params:{min:0,max:32},msg:"上传人ID 长度不在0-32范围"}],
	username:[{rule:length,params:{min:0,max:32},msg:"上传人名称 长度不在0-32范围"}]
};
