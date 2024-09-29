package com.checklist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.checklist.interceptor.AuthInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@EnableWebMvc
@ComponentScan("com.checklist")
public class WebConfig implements WebMvcConfigurer  {
	
    @Autowired
    private AuthInterceptor authInterceptor;
	
	@Bean
	public InternalResourceViewResolver getResolver() {
		InternalResourceViewResolver obj = new InternalResourceViewResolver();
		obj.setPrefix("/WEB-INF/page/");
		obj.setSuffix(".jsp");
		return obj;
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")  // Intercept all paths
                .excludePathPatterns("/login", "/register", "/resources/**");  // Exclude login, register, and static resources
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("application.properties"));
        return configurer;
    }

}
