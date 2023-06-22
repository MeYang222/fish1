package com.student.mapper;


import com.student.domain.User;

import java.util.List;

/**
 * @author aloneAtri
 * @version 1.0
 * @description 描述信息
 * @date 2023/5/31 0031 18:21
 */
public interface UserMapper {
    List<User> selectAllUsers();

    User selectByUsernameUser(String username);
    User selectUserById(Integer id);
    void updateUser(User user);

    void deleteUser(Integer id);
    void addUser(User user);
}
