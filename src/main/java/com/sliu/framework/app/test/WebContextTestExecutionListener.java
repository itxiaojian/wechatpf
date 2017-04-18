package com.sliu.framework.app.test;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

public class WebContextTestExecutionListener extends
		AbstractTestExecutionListener {

	private static final String REQUEST_ATTRIBUTES_ATTRIBUTE = RequestContextListener.class
			.getName() + ".REQUEST_ATTRIBUTES";

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(
				request);
		request.setAttribute(REQUEST_ATTRIBUTES_ATTRIBUTE, attributes);
		LocaleContextHolder.setLocale(request.getLocale());
		RequestContextHolder.setRequestAttributes(attributes);

		if (testContext.getApplicationContext() instanceof GenericApplicationContext) {
			GenericApplicationContext context = (GenericApplicationContext) testContext
					.getApplicationContext();
			ConfigurableListableBeanFactory beanFactory = context
					.getBeanFactory();
			Scope requestScope = new RequestScope();
			beanFactory.registerScope("request", requestScope);
			Scope sessionScope = new SessionScope();
			beanFactory.registerScope("session", sessionScope);

		}
	}
}