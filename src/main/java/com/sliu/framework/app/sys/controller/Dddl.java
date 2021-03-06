package com.sliu.framework.app.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:zhangyi
 * @version 创建时间：2015年9月14日 上午11:47:11 类说明
 */
@Controller
@RequestMapping("/sys/Dddl")
public class Dddl {
	
	@RequestMapping(value = "/ddDl")
	public void ddDl(HttpServletRequest request,HttpServletResponse response){
		
		Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		/*
		 * 获取用户的唯一标识信息由UIA的配置不同可分为两种：(1)学生：学号；教工：身份证号(2)学生：学号；教工：教工号
		 */
		String ssoid = assertion.getPrincipal().getName();
		/*
		 * 获取用户扩展信息扩展信息由UIA的SSO配置决定其中，由于用户可能拥有多个角色，岗位，部门
		 * 所以角色，岗位，部门需要被转换为List<Map<String,String>类型
		 */
		Map<String, Object> map = assertion.getPrincipal().getAttributes();
		/*
		 * 角色集合集合内元素为Map<String,String>类型Map中详细信息为：key：ROLECNNAME;value:角色中文名称key:
		 * ROLEIDENTIFY;value:角色代码
		 */
		List<Map<String, String>> role = parseStringToList((String) map
				.get("comsys_role"));
		/*
		 * 岗位集合集合内元素为Map<String,String>类型Map中详细信息为：key：POSTNAME;value:岗位中文名称key:
		 * POSTIDENTIFY;value:岗位代码
		 */
		List<Map<String, String>> post = parseStringToList((String) map
				.get("comsys_post"));
		/*
		 * 部门集合集合内元素为Map<String,String>类型Map中详细信息为：key：DEPARTMENTNAME;value:部门中文名称
		 * key: DEPARTMENTIDENTIFY;value:部门代码
		 */
		List<Map<String, String>> department = parseStringToList((String) map
				.get("comsys_department"));
		// 学生院系名称
		String faculetyName = (String) map.get("comsys_faculetyname");
		// 学生院系代码
		String faculetyCode = (String) map.get("comsys_faculetycode");
		// 学生年级名称
		String gradeName = (String) map.get("comsys_gradename");
		// 学生年级代码
		String gradeCode = (String) map.get("comsys_gradecode");
		// 学生专业名称
		String disciplineName = (String) map.get("comsys_disciplinename");
		// 学生专业代码
		String disciplineCode = (String) map.get("comsys_disciplinecode");
		// 学生班级名称
		String className = (String) map.get("comsys_classname");
		// 学生班级代码
		String classCode = (String) map.get("comsys_classcode");
		// 电话号码
		String phone = (String) map.get("comsys_phone");
		// 民族
		String national = (String) map.get("comsys_national");
		// 性别
		String genders = (String) map.get("comsys_genders");
		// 邮箱
		String email = (String) map.get("comsys_email");
		// 1:学生;2:教工
		String userType = (String) map.get("comsys_usertype");
		// 其它职位
		String otherPost = (String) map.get("comsys_other_post");
		// 教育程度
		String educational = (String) map.get("comsys_educational");
		// 教工号
		String teachingNumber = (String) map.get("comsys_teaching_number");
		// 学生号
		String studentNumber = (String) map.get("comsys_student_number");
		// 用户姓名
		String studentName = (String) map.get("comsys_name");
	
	}

	/**
	 * 将SSO传递过来的String类型的用户信息转换为List类型
	 * 
	 * @param str
	 *            需要被转换的String
	 * @return List-
	 */
	private List<Map<String, String>> parseStringToList(String str) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (str == null || str.equals("")) {
			return list;
		}
		String[] array = str.split("-");
		for (String subArray : array) {
			String[] keyResult = subArray.split(",");
			Map<String, String> map = new HashMap<String, String>();
			for (String subResult : keyResult) {
				String[] value = subResult.split(":");
				map.put(value[0], value[1]);
			}
			list.add(map);
		}
		return list;
	}

}
