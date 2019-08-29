package net.student.bean.service.impl;

import java.util.List;

import net.student.bean.bean.User;
public interface UserService {
    int addUser(User user);
    int deleteUserByUserName(String userName);
    int updateUser(User user);
    User findUserByUserName(String userName);
    List<User> findAllUsers();
    User login(String username, String password);
}
