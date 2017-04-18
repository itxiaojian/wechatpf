var saveSysBackupConfig = {
	fileName:[{rule:length,params:{min:0,max:100},msg:"文件备份名 长度不在0-100范围"}],
	backupTime:[{rule:date,msg:"文件备份时间 必须是日期"}],
	path:[{rule:length,params:{min:0,max:100},msg:"备份文件路径 长度不在0-100范围"}]
};
