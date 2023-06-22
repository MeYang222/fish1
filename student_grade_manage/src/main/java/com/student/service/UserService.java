package com.student.service;

import com.student.domain.User;

import java.util.List;

/**
 * @author aloneAtri
 * @version 1.0
 * @description 描述信息
 * @date 2023/6/9 0009 13:25
 */
public interface UserService {
    /**
     * @description: 查询所有用户
     * @param:
     * @return: java.util.List<com.student.domain.User>
     * @date: 2023/6/9 0009 14:10
     */
    public List<User> getAllUser();

    /**
     * @description: 查询用户通过姓名
     * @param: name
     * @return: com.student.domain.User
     * @date: 2023/6/9 0009 14:11
     */
    public User getUserByName(String name);

    /**
     * @description: 通过id查询用户
     * @param: id
     * @return: com.student.domain.User
     * @date: 2023/6/9 0009 14:11
     */
    public User getUserById(Integer id);

    /**
     * @description: 通过id删除用户
     * @param: id
     * @return: boolean
     * @date: 2023/6/9 0009 14:11
     */
    public boolean deleteUser(Integer id);

    /**
     * @description: 添加用户
     * @param: user
     * @return: boolean
     * @date: 2023/6/9 0009 14:11
     */
    public boolean addUser(User user);

    /**
     * @description: 更新用户
     * @param: user
     * @return: boolean
     * @date: 2023/6/9 0009 14:11
     */
    public boolean updateUser(User user);
}
