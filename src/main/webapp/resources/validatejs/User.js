var saveUserConfig = {
	schoolId:[{rule:length,params:{min:0,max:36},msg:"所属学校 长度不在0-36范围"}],
	name:[{rule:length,params:{min:0,max:100},msg:"姓名 长度不在0-100范围"}],
	userCode:[{rule:notBlank,msg:"登录名 不能为空"},{rule:length,params:{min:0,max:50},msg:"登录名 长度不在0-50范围"}],
	email:[{rule:email,msg:"email格式不正确"}],
	phone:[{rule:phone,msg:"电话格式不正确"}],
	type:[{rule:length,params:{min:0,max:50},msg:"用户类型(teacher:老师,student:学生) 长度不在0-50范围"}],
	status:[{rule:integer,msg:"状态(0:禁用,1:启用) 必须是数字"}],
	level:[{rule:integer,msg:"等级 必须是数字"}],
	beginTime:[{rule:integer,msg:"开始时间 必须是数字"}],
	endTime:[{rule:integer,msg:"到期时间 必须是数字"}]
};
