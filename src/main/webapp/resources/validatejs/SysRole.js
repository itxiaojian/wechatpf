var saveSysRoleConfig = {
	roleName:[{rule:notBlank,msg:"角色名称 不能为空"},{rule:length,params:{min:0,max:255},msg:"角色名称 长度不在0-255范围"}],
	descn:[{rule:length,params:{min:0,max:255},msg:"描述 长度不在0-255范围"}],
	enabled:[{rule:integer,msg:"是否生效(0:无效,1:有效) 必须是数字"}]
};

var saveSysJsConfig = {
		jsmc:[{rule:notBlank,msg:"角色名称 不能为空"},{rule:length,params:{min:0,max:255},msg:"角色名称 长度不在0-255范围"}],
		bz:[{rule:length,params:{min:0,max:255},msg:"描述 长度不在0-255范围"}],
		jszt:[{rule:integer,msg:"是否生效(0:无效,1:有效) 必须是数字"}]
};
