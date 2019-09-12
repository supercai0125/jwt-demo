package com.syxy.jwtdemo.mapper;

import com.syxy.jwtdemo.JwtDemoApplicationTests;
import com.syxy.jwtdemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ercai
 * @date 2019/9/11 - 15:03
 */
@Slf4j
public class UserMapperTest extends JwtDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByUser(){
        User user = new User();
        user.setUsername("risd");
        user.setPassword("123");
        User user1 = userMapper.selectOne(user);
        log.info(user1.toString());
    }

    @Test
    public void saveUser(){
        User user = new User();
        user.setUsername("xiaoming");
        user.setPassword("123");
        userMapper.insertSelective(user);
    }
}
