package com.checklist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan("com.checklist")
public class AppConfig {
	@Bean
	public InternalResourceViewResolver getResolver() {
		InternalResourceViewResolver obj = new InternalResourceViewResolver();
		obj.setPrefix("/WEB-INF/page/");
		obj.setSuffix(".jsp");
		return obj;
	}
}
