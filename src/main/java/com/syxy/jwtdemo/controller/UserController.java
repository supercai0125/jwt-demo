package com.syxy.jwtdemo.controller;

import com.syxy.jwtdemo.entity.User;
import com.syxy.jwtdemo.mapper.UserMapper;
import com.syxy.jwtdemo.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ercai
 * @date 2019/9/11 - 11:38
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 注册状态信息
     */
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(String username,String password){

        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //1.保存用户信息到数据库
        if (userMapper.insertSelective(user)>0) {
            //插入数据库成功 返回注册成功信息
            map.put("success", true);
            map.put("msg", "注册成功");
            return map;
        } else {
            //插入数据库成功 返回注册失败信息
            map.put("success", false);
            map.put("msg", "注册失败");
        }

        return map;
    }

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录状态信息
     */
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String username,String password){
        Map<String, Object> map = new HashMap<>();

        //1.登录页登录 查询数据库
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (userMapper.selectOne(user) == null) {
            //数据库没有该用户 返回登录失败信息
            map.put("success", false);
            map.put("msg", "用户名密码不正确");
            return map;
        }

        //数据库有该用户 返回登录成功信息(包含token)
        //生成token
        String token = JwtUtils.sign(username);
        map.put("success", true);
        map.put("msg", "登陆成功");
        map.put("token", token);

        return map;
    }

    /**
     * 主页获取用户数据
     * @return 返回用户数据
     */
    @GetMapping("/getUserDate")
    @ResponseBody
    public Map<String, Object> getUserDate(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        //获取token中的用户信息
        String username = JwtUtils.getUsername(request.getHeader("token"));
        map.put("username", username);

        return map;
    }

}
