package com.checklist.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initializes the DispatcherServlet for the Spring MVC application.
 * This class is responsible for configuring the root and servlet contexts, 
 * as well as defining the mappings for the DispatcherServlet.
 * It extends {@link AbstractAnnotationConfigDispatcherServletInitializer}, 
 * which provides base configurations for a Spring MVC application.
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specifies the root configuration classes for the application.
     * This method can return configurations for components like security, persistence, etc.
     *
     * @return an array of root configuration classes, or {@code null} if there are none
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // No root configuration for this application
        return null;
    }

    /**
     * Specifies the servlet configuration classes for the DispatcherServlet.
     * These configurations typically define Spring MVC-related beans.
     *
     * @return an array of servlet configuration classes, in this case, {@link WebConfig}
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Configure WebConfig class for the servlet
        return new Class[] { WebConfig.class };
    }

    /**
     * Specifies the servlet mapping(s) for the DispatcherServlet.
     * In this case, it maps all requests ("/") to the DispatcherServlet.
     *
     * @return an array of URL patterns that the DispatcherServlet will be mapped to
     */
    @Override
    protected String[] getServletMappings() {
        // Map all requests to the DispatcherServlet
        return new String[] { "/" };
    }
}
