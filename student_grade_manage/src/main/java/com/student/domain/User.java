package com.student.domain;

import lombok.Data;

/**
 * @author aloneAtri
 * @version 1.0
 * @description 描述信息
 * @date 2023/6/2 0002 13:28
 */

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String address;
    private String tel;
    private String post;
}
