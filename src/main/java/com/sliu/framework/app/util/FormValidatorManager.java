package com.sliu.framework.app.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.UniqueTag;
import org.mozilla.javascript.tools.SourceReader;

/**
 * 
 * Description of the class
 * 
 * @author lxt
 * @version 1.0
 * @since 2013-1-28
 */
public class FormValidatorManager {
	private static String contextPath = null;

	private static String formScriptFolder = "/resources/validatejs/";

	private static List<String> validateJSList;

	private static Map<String, Map<String, List<Map<String, Object>>>> ruleMap = new HashMap<String, Map<String, List<Map<String, Object>>>>();

	private static String DEFINED = "var isEmpty = 'isEmpty';var notInRange = 'notInRange';var notIn = 'notIn';var notDate='notDate';var notNum='notNum';var notUrl='notUrl';var notNumeric='notNumeric';var notEmail='notEmail';var notCercode='notCercode';var notPhoneCall='notPhoneCall';var notPhone='notPhone';var notZip='notZip';";

	/**
	 * 判断日期格式和范围
	 */
	public static final String DATE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(( )(\\d{2})(:)(\\d{2})(:)(\\d{2}))?";
	/**
	 * URL正则表达式 匹配 http www ftp
	 */
	public static final String URL = "^(http|www|ftp|)?(://)?(//w+(-//w+)*)(//.(//w+(-//w+)*))*((://d+)?)(/(//w+(-//w+)*))*(//.?(//w)*)(//?)?"
			+ "(((//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*(//w*%)*(//w*//?)*"
			+ "(//w*:)*(//w*//+)*(//w*//.)*"
			+ "(//w*&)*(//w*-)*(//w*=)*)*(//w*)*)$";

	/**
	 * 身份证正则表达式
	 */
	public static final String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})"
			+ "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}"
			+ "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";

	/**
	 * 
	 * Description of this Method
	 * 
	 * @author Administrator
	 * @since 2013-1-29
	 */
	private static void loadJS() {
		validateJSList = new ArrayList<String>();
		ruleMap = new HashMap<String, Map<String, List<Map<String, Object>>>>();
		try {
			File file = new File(contextPath + formScriptFolder);
			File[] js = file.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					return pathname.getName().endsWith(".js");
				}
			});
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < js.length; i++) {
				File f = js[i];
				sb.append((String) SourceReader.readFileOrUrl(f.getPath(),
						true, "utf-8"));
				if (sb.length() > 1024 * 32) {
					validateJSList.add(sb.toString());
					sb = new StringBuffer();
				}

				if (i == js.length - 1) {
					validateJSList.add(sb.toString());
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @author lxt
	 * @param confMap
	 * @param map
	 * @return
	 */
	private static Map<String, Object> validate(
			Map<String, List<Map<String, Object>>> confMap,
			Map<String, String> map) {
		Map<String, Object> result = new java.util.HashMap();

		List<String> paramMap = new ArrayList();
		Iterator<String> parameterNames = map.keySet().iterator();
		while (parameterNames.hasNext()) {
			paramMap.add(parameterNames.next());
		}

		Iterator<String> confIter = confMap.keySet().iterator();
		while (confIter.hasNext()) {
			String name = confIter.next();
			boolean exist = false;
			for (int i = 0; i < paramMap.size(); i++) {
				if (name.matches("^" + paramMap.get(i) + "$")) {
					exist = true;
					String val = map.get(paramMap.get(i));
					for (int j = 0; j < confMap.get(name).size(); j++) {
						Map<String, Object> validateConfig = confMap.get(name)
								.get(j);
						String fun = (String) validateConfig.get("fun");
						if (validateConfig.get("params") != null) {
							if (fun.equals("notInRange")) {
								Map<String, Object> params = (Map<String, Object>) validateConfig
										.get("params");
								if ((val.length() < ((Number) params.get("min"))
										.intValue() || val.length() > ((Number) params
										.get("max")).intValue())) {
									putError(result, paramMap.get(i),
											(String) validateConfig.get("msg"));
									break;
								}
							} else if (fun.equals("notIn")
									&& notIn(val,
											(List<String>) validateConfig
													.get("params"))) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							}
						} else {
							if (fun.equals("isEmpty")
									&& StringUtils.isBlank(val)) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notDate")
									&& !val.matches(DATE)) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notNum")
									&& !NumberUtils.isNumber(val)) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notUrl")
									&& !val.matches(URL)) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notNumeric")
									&& !NumberUtils.isDigits(val)) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notEmail")
									&& !val.matches("^//w+([-+.]//w+)*@//w+([-.]//w+)*//.//w+([-.]//w+)*$")) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notCercode")
									&& !val.matches(IDCARD)) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notPhoneCall")
									&& !val.matches("(^(//d{2,4}[-_－—]?)?//d{3,8}([-_－—]?//d{3,8})?([-_－—]?//d{1,7})?$)|(^0?1[35]//d{9}$)")) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notPhone")
									&& !val.matches("^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])//d{8}$")) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							} else if (fun.equals("notZip")
									&& !val.matches("^[1-9]//d{5}(?!//d)$")) {
								putError(result, paramMap.get(i),
										(String) validateConfig.get("msg"));
								break;
							}
						}
					}
					if (name == paramMap.get(i)) {
						break;
					}
				}
			}
			if (!exist) {
				for (int j = 0; j < confMap.get(name).size(); j++) {
					Map<String, Object> validateConfig = confMap.get(name).get(
							j);
					if (((String) validateConfig.get("fun"))
							.indexOf("isBlank(") != -1) {
						putError(result, name,
								(String) validateConfig.get("msg"));
						break;
					}
				}
			}
		}
		return result;
	}

	private static boolean notIn(String val, List<String> params) {
		for (int k = 0; k < params.size(); k++) {
			if (StringUtils.equals(val, params.get(k))) {
				return false;
			}
		}
		return true;
	}

	private static void putError(Map<String, Object> result, String param,
			String msg) {
		Object _paramnames = result.get(msg);
		if (_paramnames == null) {
			result.put(msg, param);
		} else {
			if (!(_paramnames instanceof ArrayList)) {
				_paramnames = new ArrayList();
				((ArrayList) _paramnames).add(result.get(msg));
				result.put(msg, _paramnames);
			}
			((ArrayList) _paramnames).add(param);
		}
	}

	/**
	 * 
	 * Description of this Method
	 * 
	 * @author Administrator
	 * @since 2013-1-29
	 * @param param
	 * @return
	 */
	private static Map<String, List<Map<String, Object>>> getRules(String param) {
		if (ruleMap.get(param) != null) {
			return ruleMap.get(param);
		}
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();
			Object result = null;
			for (String js : validateJSList) {
				cx.evaluateString(scope, DEFINED + js, "function test source",
						1, null);
				result = scope.get(param, scope);
				if (!(result instanceof UniqueTag)) {
					Map<String, List<Map<String, Object>>> r = process((NativeObject) result);
					ruleMap.put(param, r);
					return r;
				}
			}
			return null;
		} finally {
			Context.exit();
		}
	}

	private static Map<String, List<Map<String, Object>>> process(
			NativeObject conf) {

		Object[] properties = conf.getAllIds();
		Map<String, List<Map<String, Object>>> confMap = new HashMap<String, List<Map<String, Object>>>();
		for (Object property : properties) {
			List<Map<String, Object>> ruleList = new ArrayList<Map<String, Object>>();
			confMap.put((String) property, ruleList);

			NativeArray rules = (NativeArray) conf.get((String) property, null);
			for (int i = 0; i < rules.getLength(); i++) {
				NativeObject rule = (NativeObject) rules.get(i, null);
				Object[] keys = rule.getAllIds();

				Map<String, Object> ruleMap = new HashMap<String, Object>();

				for (Object k : keys) {
					Object obj = rule.get((String) k, null);
					if (obj instanceof NativeArray) {
						List<Object> p = new ArrayList<Object>();
						for (int jj = 0; jj < ((NativeArray) obj).getLength(); jj++) {
							p.add("" + ((NativeArray) obj).get(jj, null));
						}
						ruleMap.put((String) k, p);
					} else if (obj instanceof NativeObject) {
						Object[] params = ((NativeObject) obj).getAllIds();
						Map<String, Object> p = new HashMap<String, Object>();
						for (Object param : params) {
							Object o = ((NativeObject) obj).get((String) param,
									null);
							p.put((String) param, o);
						}
						ruleMap.put((String) k, p);
					} else {
						ruleMap.put((String) k, obj);
					}
				}
				ruleList.add(ruleMap);
			}
		}
		return confMap;
	}

	/**
	 * 
	 * Description of this Method
	 * 
	 * @author liuxantao
	 * @since 2013-1-29
	 * @param config
	 * @param request
	 * @return
	 */
	public static Map<String, Object> validate(String config,
			HttpServletRequest request) {
		contextPath = request.getRealPath("");
		loadJS();
		Map<String, List<Map<String, Object>>> rules = getRules(config);

		if (rules == null) {
			throw new RuntimeException("验证规则未定义 " + config);
		}

		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> paramEnu = request.getParameterNames();
		while (paramEnu.hasMoreElements()) {
			String paramName = paramEnu.nextElement();

			params.put(paramName, request.getParameter(paramName));
		}
		Map<String, Object> result = validate(rules, params);

		request.setAttribute("paramErrors", result);
		return result;
	}
}
