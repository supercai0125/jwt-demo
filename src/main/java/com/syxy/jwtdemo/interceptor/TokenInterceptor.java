package com.syxy.jwtdemo.interceptor;

import com.syxy.jwtdemo.mapper.UserMapper;
import com.syxy.jwtdemo.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ercai
 * @date 2019/9/11 - 23:10
 */
@Slf4j
@Service
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("开始校验token");
        //从请求头中获取token
        String token=request.getHeader("token");
        System.out.println(token);
        System.out.println("校验结果" + JwtUtils.verify(token));
        return JwtUtils.verify(token);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}