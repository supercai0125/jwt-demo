package com.syxy.jwtdemo.mapper;

import com.syxy.jwtdemo.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ercai
 * @date 2019/9/11 - 14:06
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
}
