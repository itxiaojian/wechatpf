var saveSysYhConfig = {
		xm:[{rule:notBlank,msg:"用户名 不能为空"},{rule:length,params:{min:0,max:255},msg:"用户名 长度不在0-255范围"}],
		username:[{rule:notBlank,msg:"登录名 不能为空"},{rule:length,params:{min:0,max:255},msg:"登录名 长度不在0-255范围"}],
		bmbh:[{rule:notBlank,msg:"部门 不能为空"},{rule:length,params:{min:0,max:255},msg:"部门 长度不在0-255范围"}],
		yx:[{rule:length,params:{min:0,max:255},msg:"邮箱 长度不在0-255范围"}],
		sjh:[{rule:length,params:{min:0,max:255},msg:"手机号 长度不在0-255范围"}],
		zhdlsj:[{rule:date,msg:"最后登录时间 必须是日期"}],
		yhzt:[{rule:integer,msg:"状态(0:已删除,1:启用,2:禁用) 必须是数字"}]
};
