<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.*"%>
<%@page  import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page  import="org.jasig.cas.client.validation.Assertion"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<style type="text/css">
	td{
		border: 1px solid;
	}
</style>
</head>
<body>
	<%Assertion assertion=
	    (Assertion)
	    session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
	/*获取用户扩展信息
	 *扩展信息由UIA的SSO配置决定
	 *其中，由于用户可能拥有多个角色，岗位，部门
	 *所以角色，岗位，部门需要被转换为List<Map<String,String>类型
	*/
	Map<String, Object> map = assertion.getPrincipal().getAttributes();
	
	
	%>
	<table style="border: 1px solid;width: 100%;">
		<tr>
			<td style="width: 10%;">唯一标识</td>
			<td><%= assertion.getPrincipal().getName()%></td>
		</tr>
		<tr>
			<td >角色集合</td>
			<td><%= parseStringToList((String)map.get("comsys_role"))%></td>
		</tr>
		<tr>
			<td >岗位集合</td>
			<td><%= parseStringToList((String)map.get("comsys_post"))%></td>
		</tr>
		<tr>
			<td >部门集合</td>
			<td><%= parseStringToList((String)map.get("comsys_department"))%></td>
		</tr>
		<tr>
			<td >学生院系名称</td>
			<td><%= decode((String)map.get("comsys_faculetyname"))%></td>
		</tr>
		<tr>
			<td >学生院系代码</td>
			<td><%= decode((String)map.get("comsys_faculetycode"))%></td>
		</tr>
		<tr>
			<td >学生年级名称</td>
			<td><%= decode((String)map.get("comsys_gradename"))%></td>
		</tr>
		<tr>
			<td >学生年级名称</td>
			<td><%= decode((String)map.get("comsys_gradename"))%></td>
		</tr>
		<tr>
			<td >学生专业名称</td>
			<td><%= decode((String)map.get("comsys_disciplinename"))%></td>
		</tr>
		<tr>
			<td >学生专业代码</td>
			<td><%= decode((String)map.get("comsys_disciplinecode"))%></td>
		</tr>
		<tr>
			<td >学生班级名称</td>
			<td><%= decode((String)map.get("comsys_classname"))%></td>
		</tr>
		<tr>
			<td >学生班级代码</td>
			<td><%= decode((String)map.get("comsys_classcode"))%></td>
		</tr>
		<tr>
			<td >电话号码</td>
			<td><%= decode((String)map.get("comsys_phone"))%></td>
		</tr>
		<tr>
			<td >民族</td>
			<td><%= decode((String)map.get("comsys_national"))%></td>
		</tr>
		<tr>
			<td >性别</td>
			<td><%= decode((String)map.get("comsys_genders"))%></td>
		</tr>
		<tr>
			<td >邮箱</td>
			<td><%= decode((String)map.get("comsys_email"))%></td>
		</tr>
		<tr>
			<td >1:学生;2:教工</td>
			<td><%= decode((String)map.get("comsys_usertype"))%></td>
		</tr>
		<tr>
			<td >其它职位</td>
			<td><%= decode((String)map.get("comsys_other_post"))%></td>
		</tr>
		<tr>
			<td >教育程度</td>
			<td><%= decode((String)map.get("comsys_educational"))%></td>
		</tr>
		<tr>
			<td >教工号</td>
			<td><%= decode((String)map.get("comsys_teaching_number"))%></td>
		</tr>
		<tr>
			<td>学生号</td>
			<td><%= decode((String)map.get("comsys_student_number"))%></td>
		</tr>
		<tr>
			<td>用户姓名</td>
			<td><%= decode((String)map.get("comsys_name"))%></td>
		</tr>
		<tr>
			<td><a href="http://192.168.4.253:8080/sso/logout?service=http://192.168.4.253:8080/logout.action">退出</a>
		</tr>
	</table>
	<%! 
	private List<Map<String,String>> parseStringToList(String str) throws Exception {
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        if(str == null || str.equals("")){
           return list;
        }
	    str = decode(str);
        String[] array = str.split("-");
        for (String subArray : array) {
            String[] keyResult = subArray.split(",");
         Map<String,String> map = new HashMap<String, String>();
            for (String subResult : keyResult) {
                String[] value = subResult.split(":");
                map.put(value[0], value[1]);
            }
            list.add(map);
        }
        return list;
    } 
	private String decode(String str) throws Exception{
	    if(str != null){
		    str = URLDecoder.decode(str,"UTF-8");
		}
	    return str;
	}
    %>
</body>
</html>