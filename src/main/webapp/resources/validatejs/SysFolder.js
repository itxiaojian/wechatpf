var saveSysFolderConfig = {
	parentId:[{rule:length,params:{min:0,max:36},msg:"上级文件夹 长度不在0-36范围"}],
	name:[{rule:notBlank,msg:"文件夹名称 不能为空"},{rule:length,params:{min:0,max:128},msg:"文件夹名称 长度不在0-128范围"}],
	descn:[{rule:length,params:{min:0,max:256},msg:"描述 长度不在0-256范围"}],
	sn:[{rule:integer,msg:"排序 必须是数字"}]
};
