package net.student.bean.dao.impl;

import java.util.List;

import net.student.bean.bean.User;

public interface UserDao {
    int insert(User user);
    int deleteByUserName(String username);
    int update(User user);
    User findByUserName(String username);
    List<User> findAll();
    User login(String username, String password);

}
