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
        //token拦截器除了login register都拦截
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/register","/register.html","/login.html","/main.html");

        //跨域拦截器 拦截所有方法
        registry.addInterceptor(crossOriginInterceptor)
                .addPathPatterns("/**");
    }
}