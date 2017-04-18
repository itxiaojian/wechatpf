var saveSysDictConfig = {
	kind:[{rule:notBlank,msg:"种类 不能为空"},{rule:length,params:{min:0,max:50},msg:"种类 长度不在0-50范围"}],
	name:[{rule:notBlank,msg:"名称 不能为空"},{rule:length,params:{min:0,max:200},msg:"名称 长度不在0-200范围"}],
	code:[{rule:notBlank,msg:"代码 不能为空"},{rule:length,params:{min:0,max:100},msg:"代码 长度不在0-100范围"}],
	sn:[{rule:integer,msg:"排序 必须是数字"}]
};

var saveSysSjzdConfig = {
		zl:[{rule:notBlank,msg:"种类 不能为空"},{rule:length,params:{min:0,max:50},msg:"种类 长度不在0-50范围"}],
		zdmc:[{rule:notBlank,msg:"名称 不能为空"},{rule:length,params:{min:0,max:200},msg:"名称 长度不在0-200范围"}],
		zdbm:[{rule:notBlank,msg:"代码 不能为空"},{rule:length,params:{min:0,max:100},msg:"代码 长度不在0-100范围"}],
		zdz:[{rule:notBlank,msg:"字典值 不能为空"},{rule:length,params:{min:0,max:200},msg:"字典值 长度不在0-200范围"}],
		jb:[{rule:integer,msg:"级别 必须是数字"}],
		px:[{rule:integer,msg:"排序 必须是数字"}]
};
