package com.example.linkedin.ConnectionService.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

       String userId = request.getHeader("X-User-ID");
       if (userId != null) {
            UserContextHolder.setContext(Long.valueOf(userId));

       }
       return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        // Clean up ThreadLocal to prevent memory leaks
        UserContextHolder.clear();
    }
}