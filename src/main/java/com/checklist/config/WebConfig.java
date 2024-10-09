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

/**
 * Web configuration class for the Spring MVC application.
 * This class is responsible for configuring view resolvers, interceptors, 
 * and loading property files.
 * It implements {@link WebMvcConfigurer} to allow customizing the configuration for Spring MVC.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.checklist")
public class WebConfig implements WebMvcConfigurer  {

    @Autowired
    private AuthInterceptor authInterceptor;

    /**
     * Configures the view resolver for the application.
     * It resolves JSP pages from the /WEB-INF/page/ directory with a ".jsp" suffix.
     *
     * @return the configured {@link InternalResourceViewResolver}
     */
    @Bean
    public InternalResourceViewResolver getResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/page/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * Registers the authentication interceptor for the application.
     * It intercepts all paths except for the login, register pages, and static resources.
     *
     * @param registry the {@link InterceptorRegistry} used to register the interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")  // Intercept all paths
                .excludePathPatterns("/login", "/register", "/resources/**");  // Exclude login, register, and static resources
    }

    /**
     * Loads the application properties from the classpath resource "application.properties".
     * This bean allows property placeholders to be used within the configuration.
     *
     * @return the {@link PropertySourcesPlaceholderConfigurer} bean
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("application.properties"));
        return configurer;
    }

}
