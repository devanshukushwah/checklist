package com.checklist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor class that handles authentication checks for incoming requests.
 * This class implements {@link HandlerInterceptor} and is marked as a Spring
 * component.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * Pre-handle method that is called before the actual handler is executed.
     * It checks if the user is authenticated before allowing access to protected
     * resources.
     *
     * @param request the current HTTP request
     * @param response the current HTTP response
     * @param handler the chosen handler to execute, for type and instance examination
     * @return {@code true} if the request should proceed to the handler; 
     *         {@code false} if the request should be aborted
     * @throws Exception if any error occurs during pre-processing
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // Allow access to login and register pages
        if (uri.endsWith("/login") || uri.endsWith("/register")) {
            return true;
        }
        
        // Check if user is logged in (assuming user session attribute "user" holds the authenticated user)
        if (request.getSession().getAttribute("user") != null) {
            return true;  // User is logged in, allow access
        }

        // If user is not logged in, redirect to login page
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
