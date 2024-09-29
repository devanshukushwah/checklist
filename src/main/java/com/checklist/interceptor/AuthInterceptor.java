package com.checklist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

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
