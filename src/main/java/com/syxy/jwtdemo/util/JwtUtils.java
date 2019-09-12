package com.syxy.jwtdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ercai
 * @date 2019/9/11 - 10:41
 */
@Slf4j
public class JwtUtils {
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    //秘钥
    private static final String SECRET = "session_secret";

    /**
     * 1. 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();  //使用秘钥解密
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);   //验证token
            return true;
        } catch (Exception e) {
        }
        return false;  //jwt.getClaims();
    }

    /**
     * 2. 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 3. 生成签名,5min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withHeader(map)    //添加头部信息
                .withClaim("username", username)    //添加自定义数据
                .withExpiresAt(date)    //设置过期时间
                .withIssuedAt(new Date(System.currentTimeMillis())) // 签发时间
                .sign(Algorithm.HMAC256(SECRET));   //加密
    }
}



