package org.example.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;

public class RepeatSameInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String auth = request.getHeader("Authorization");
        String host = request.getHeader("Host");
        System.out.println("gjb authorization: " + auth + " Host: " + host);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        System.out.println("gjb postHandle: ");
        if (modelAndView != null) {
            Map<String, Object> mp = modelAndView.getModel();
            for (Map.Entry<String, Object> entry : mp.entrySet()) {
                System.out.println("gjb key " + entry.getKey());
                System.out.println("gjb value" + entry.getValue());
            }
        } else {
            System.out.println("gjb ,,,,,,,,,,,,,,,,,,,,");
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        System.out.println("gjb afterCompletion this time is :" + LocalDateTime.now());
    }
}
