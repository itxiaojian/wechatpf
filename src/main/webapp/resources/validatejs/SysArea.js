var saveSysAreaConfig = {
	parentId:[{rule:notBlank,msg:"上级区域 不能为空"},{rule:length,params:{min:0,max:36},msg:"上级区域 长度不在0-36范围"}],
	areaCode:[{rule:notBlank,msg:"区域代码 不能为空"},{rule:length,params:{min:0,max:50},msg:"区域代码 长度不在0-50范围"}],
	areaName:[{rule:notBlank,msg:"区域名称 不能为空"},{rule:length,params:{min:0,max:256},msg:"区域名称 长度不在0-256范围"}]
};
var saveSysZzjgConfig = {
	parentId:[{rule:notBlank,msg:"上级机构 不能为空"},{rule:length,params:{min:0,max:36},msg:"上级机构 长度不在0-36范围"}],
	bmbh:[{rule:notBlank,msg:"部门代码 不能为空"},{rule:length,params:{min:0,max:50},msg:"部门代码 长度不在0-50范围"}],
	bmmc:[{rule:notBlank,msg:"部门名称 不能为空"},{rule:length,params:{min:0,max:256},msg:"部门名称 长度不在0-256范围"}]
};
