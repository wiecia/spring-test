package com.wiecia.springtest.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
		rootCtx.scan("com.wiecia.springtest.config");

		sc.setInitParameter("defaultHtmlEscape", "true");

		// sc.setInitParameter("log4jConfigLocation",
		// "classpath:/META-INF/conf/log4j.properties");
		// sc.addListener(new Log4jConfigListener());

		sc.setInitParameter("logback.configurationFile",
				"classpath:/META-INF/conf/logback.xml");
		sc.addListener(new ContextLoaderListener(rootCtx));

		// enconding filter
		FilterRegistration.Dynamic encodingFilter = sc.addFilter(
				"encodingFilter", CharacterEncodingFilter.class);
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, false, "/*");

		// Main dispatcher servlet
		ServletRegistration.Dynamic appServlet = sc.addServlet("appServlet",
				new DispatcherServlet(new GenericWebApplicationContext()));
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");

	}
}
