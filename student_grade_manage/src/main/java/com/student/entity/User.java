package com.student.entity;



import lombok.Data;

import java.util.ArrayList;

@Data
public class User extends ArrayList<User> {
    private int id;
    private String username;
    private String password;
    private String sex;
    private int age;
    private String address;
    private String tel;
    private String post;


}
