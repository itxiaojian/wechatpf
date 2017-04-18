/*var saveBxztConfig = {
	parentId:[{rule:notBlank,msg:"上级报修 不能为空"},{rule:length,params:{min:0,max:36},msg:"上级报修  长度不在0-36范围"}],
	areaCode:[{rule:notBlank,msg:"报修代码不能为空"},{rule:length,params:{min:0,max:50},msg:"报修代码 长度不在0-50范围"}],
	areaName:[{rule:notBlank,msg:"报修名称 不能为空"},{rule:length,params:{min:0,max:256},msg:"报修名称 长度不在0-256范围"}]
};*/
var saveBxztConfig = {
	parentId:[{rule:notBlank,msg:"上级报修 不能为空"},{rule:length,params:{min:0,max:36},msg:"上级报修 长度不在0-36范围"}],
	ztbh:[{rule:notBlank,msg:"报修代码不能为空"},{rule:length,params:{min:0,max:50},msg:"报修代码 长度不在0-50范围"}],
	ztmc:[{rule:notBlank,msg:"报修名称 不能为空"},{rule:length,params:{min:0,max:256},msg:"报修名称 长度不在0-256范围"}]
};
