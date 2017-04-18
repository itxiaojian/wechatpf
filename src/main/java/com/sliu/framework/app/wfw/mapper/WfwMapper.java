package com.sliu.framework.app.wfw.mapper;

import java.util.List;
import java.util.Map;

import com.likegene.framework.core.Pager;
import com.likegene.framework.core.query.QueryFilter;

/**
 * 
 * @author liujiasen
 * @date 2015年5月29日
 */
public interface WfwMapper {
	/**
	 * 培养方案信息
	 * @author liujiasen
	 * @date 2015年6月1日
	 * @param filter
	 * @return
	 */
	Pager listAllPyfa(QueryFilter filter);
	
	/**
	 * 培养方案具体信息
	 * @author liujiasen
	 * @date 2015年6月1日
	 * @param filter
	 * @return
	 */
	List<Map<String, Object>> listPyfa(QueryFilter filter);
	
	/**
	 * 学生缴欠费信息
	 * @author liujiasen
	 * @date 2015年6月1日
	 * @param filter
	 * @return
	 */
	Pager listAllJqfxx(QueryFilter filter);
	
	/**
	 * 学生贷款信息
	 * @author liujiasen
	 * @date 2015年6月1日
	 * @param filter
	 * @return
	 */
	Pager listAllDkxx(QueryFilter filter);
	
	/**
	 * 学籍异动信息
	 * @author liujiasen
	 * @date 2015年6月1日
	 * @param filter
	 * @return
	 */
	Pager listAllXjyd(QueryFilter filter);
	
	/**
	 * 学籍异动详细信息
	 * @author liujiasen
	 * @date 2015年6月1日
	 * @param filter
	 * @return
	 */
	List<Map<String, Object>> listXjyd(QueryFilter filter);
	
	/**
	 * 教学成果信息
	 * @author liujiasen
	 * @date 2015年6月1日
	 * @param filter
	 * @return
	 */
	Pager listAllJxcg(QueryFilter filter);
	
	/**
	 * 教师监考信息
	 * @author liujiasen
	 * @date 2015年6月3日
	 * @param filter
	 * @return
	 */
	Pager listAllJsjkxx(QueryFilter filter);
	
	/**
	 * 网络选修课信息
	 * @author liujiasen
	 * @date 2015年6月3日
	 * @param filter
	 * @return
	 */
	Pager listAllXxkxx(QueryFilter filter);
	
	/**
	 * 科研项目信息
	 * @author liujiasen
	 * @date 2015年6月3日
	 * @param filter
	 * @return
	 */
	Pager listAllKyxm(QueryFilter filter);
	
	/**
	 * 图书分类
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月9日  
	 * @param filter
	 * @return
	 */
	Pager listAllTsfl(QueryFilter filter);
	
	/**
	 * 贫困生信息
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月9日  
	 * @param filter
	 * @return
	 */
	Pager listAllPksxx(QueryFilter filter);
	
	/**
	 * 校车时刻
	 * @author   duanpeijun
	 * @version 创建时间：2015年6月9日  
	 * @param filter
	 * @return
	 */
	Pager listAllXcsk(QueryFilter filter);
	
}
