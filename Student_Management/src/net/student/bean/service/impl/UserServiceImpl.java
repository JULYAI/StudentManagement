package net.student.bean.service.impl;

import java.util.List;
import net.student.bean.bean.User;
import net.student.bean.dao.impl.UserDao;
import net.student.bean.dao.impl.UserDaoImpl;
import net.student.bean.service.impl.UserService;

public class UserServiceImpl implements UserService{
    /**
     * 声明用户数据访问对象
     */
    private UserDao userDao = new UserDaoImpl();

    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public int deleteUserByUserName(String userName) {
        return userDao.deleteByUserName(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User findUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }
}
