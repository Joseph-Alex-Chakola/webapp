package com.csye5.CloudComputing.interceptor;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.nio.charset.StandardCharsets;

@Component
public class HealthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request,
                             @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) throws Exception {
       String requestBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        if( HttpMethod.GET.matches(request.getMethod())){
            if(!requestBody.isEmpty() || request.getQueryString()!=null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return false;
            }
            return true;
        }
        return false;
    }
}
