package com.syxy.jwtdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ercai
 * @date 2019/9/11 - 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private String gender;
}
