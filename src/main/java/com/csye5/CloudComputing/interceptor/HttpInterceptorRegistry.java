package com.csye5.CloudComputing.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class HttpInterceptorRegistry implements WebMvcConfigurer {
    HealthInterceptor healthInterceptor;
    UserInterceptor userInterceptor;
    UserInterceptor2 userInterceptor2;

    public HttpInterceptorRegistry(HealthInterceptor healthInterceptor, UserInterceptor userInterceptor, UserInterceptor2 userInterceptor2) {
        this.healthInterceptor = healthInterceptor;
        this.userInterceptor = userInterceptor;
        this.userInterceptor2 = userInterceptor2;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(healthInterceptor).addPathPatterns("/healthz");
        registry.addInterceptor(userInterceptor2).addPathPatterns("/v1/user");
        registry.addInterceptor(userInterceptor).addPathPatterns("/v1/user/self");
    }
}
