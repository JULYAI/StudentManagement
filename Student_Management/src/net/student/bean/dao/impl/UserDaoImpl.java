package net.student.bean.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.student.bean.bean.User;
import net.student.bean.dao.impl.UserDao;
import net.student.bean.dbutil.ConnectionManager;

public class UserDaoImpl implements UserDao {

    @Override
    public int deleteByUserName(String userName) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByUserName(String username) {
        // 声明用户
        User user = null;

        // 获取数据库连接对象
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "SELECT * FROM t_user WHERE id = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, username);
            // 执行SQL查询，返回结果集
            ResultSet rs = pstmt.executeQuery();
            // 判断结果集是否有记录
            if (rs.next()) {
                // 创建用户实体
                user = new User();
                // 设置实体属性
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        // 返回用户
        return user;
    }

    @Override
    public int insert(User user) {
        // 定义插入记录数
        int count = 0;

        // 获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "INSERT INTO t_user (username, password, telephone, register_time)"
                + " VALUES (?, ?, ?, ?)";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            // 执行更新操作，插入新记录
            count = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        // 返回插入记录数
        return count;
    }

    @Override
    public User login(String username, String password) {
        // 声明用户
        User user = null;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 执行查询，返回结果集
            ResultSet rs = pstmt.executeQuery();
            // 判断是否有记录
            if (rs.next()) {
                // 实例化用户
                user = new User();
                // 设置用户属性
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        // 返回用户
        return user;
    }

    @Override
    public int update(User user) {
        // 定义更新记录数
        int count = 0;

        // 获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "UPDATE stuinformation SET username = ?, password = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            // 执行更新操作，更新记录
            count = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        // 返回更新记录数
        return count;
    }


}
