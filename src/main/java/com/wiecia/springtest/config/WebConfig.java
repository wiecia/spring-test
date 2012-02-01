package com.wiecia.springtest.config;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.thymeleaf.TemplateMode;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@ComponentScan(basePackages = { "com.wiecia.springtest.web" })
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	SessionFactory sessionFactory;

	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
		cnvr.setOrder(Ordered.HIGHEST_PRECEDENCE);
		Map<String, String> mediaTypes = new HashMap<String, String>();
		mediaTypes.put("html", "text/html");
		mediaTypes.put("pdf", "application/pdf");
		mediaTypes.put("xls", "application/vnd.ms-excel");
		mediaTypes.put("xml", "application/xml");
		mediaTypes.put("json", "application/json");
		cnvr.setMediaTypes(mediaTypes);
		return cnvr;
	}

	@Bean
	public ServletContextTemplateResolver templeteResolver() {
		ServletContextTemplateResolver tplResolver = new ServletContextTemplateResolver();
		tplResolver.setPrefix("/WEB-INF/templates/");
		tplResolver.setSuffix(".html");
		tplResolver.setTemplateMode(TemplateMode.XHTML);
		tplResolver.setCacheable(false);
		return tplResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templeteResolver());
		return engine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setOrder(contentNegotiatingViewResolver().getOrder() + 1);
		resolver.setTemplateEngine(templateEngine());
		// resolver.setViewNames(new String[] { "*.html", "*.xhtml" });
		return resolver;
	}

	// @Bean
	public ViewResolver internalViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setOrder(contentNegotiatingViewResolver().getOrder() + 2);
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		OpenSessionInViewInterceptor openSessionInterceptor = new OpenSessionInViewInterceptor();
		openSessionInterceptor.setSessionFactory(sessionFactory);
		registry.addWebRequestInterceptor(openSessionInterceptor);
	}

	@Bean
	public ObjectMapper jacksonObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public MappingJacksonJsonView restJsonMarshalView() {
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setObjectMapper(jacksonObjectMapper());
		return view;
	}
}
