package com.syxy.jwtdemo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ercai
 * @date 2019/9/11 - 23:11
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private CrossOriginInterceptor crossOriginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/register","/register.html","/login.html","/main.html");

        registry.addInterceptor(crossOriginInterceptor)
                .addPathPatterns("/**");
    }
}