package com.syxy.jwtdemo;

import com.syxy.jwtdemo.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author ercai
 * @date 2019/9/11 - 10:52
 */
@Slf4j
public class JwtTest extends JwtDemoApplicationTests {

    @Test
    public void testJwt(){
        String password = DigestUtils.md5Hex("123456");
        log.info("MD5加密："+password);
        String str = JwtUtils.sign("admin");
        log.info("JWT加密："+str);
        Boolean a= JwtUtils.verify(str);
        log.info(a.toString());
        String username=JwtUtils.getUsername(str);
        log.info(username);
    }
}
