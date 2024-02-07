package com.csye5.CloudComputing.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {


        if(request.getMethod().equals("GET")){
            String requestBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            if(!requestBody.isEmpty() || request.getQueryString()!=null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return false;
            }
            return true;
        }

        if (request.getMethod().equals("PUT")) {
            if(request.getQueryString()!=null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return false;
            }
            return true;
        }

        return false;
    }
}
