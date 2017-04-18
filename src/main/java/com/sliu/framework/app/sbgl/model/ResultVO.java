package com.sliu.framework.app.sbgl.model;

import java.util.List;
import java.util.Map;

/**
*
@Author oufeng	
@Date 2015年9月1日 下午3:09:12
@Version 1.0
*/
public class ResultVO {
	
	private List<Map<String, Object>> rows ;
	
	private Integer total;

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}


