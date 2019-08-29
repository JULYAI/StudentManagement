package net.student.bean.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import net.student.bean.bean.Student;
import net.student.bean.dao.impl.StudentDao;
import net.student.bean.dbutil.ConnectionManager;



public class StudentDaoImpl implements StudentDao{
    @Override
    public int deleteByCourse(String course) {
        // 定义删除记录数
        int count = 0;

        // 获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "DELETE FROM stuinformation WHERE course = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, course);
            // 执行更新操作，删除记录
            count = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        // 返回删除记录数
        return count;
    }

    @Override
    public int deleteById(String id) {
        // 定义删除记录数
        int count = 0;

        // 获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "DELETE FROM stuinformation WHERE id = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, id);
            // 执行更新操作，删除记录
            count = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        // 返回删除记录数
        return count;
    }

    @Override
    public List<Student> findAll() {
        // 声明用户列表
        List<Student> students = new ArrayList<Student>();
        // 获取数据库连接对象
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "SELECT * FROM stuinformation";
        try {
            // 创建语句对象
            Statement stmt = conn.createStatement();
            // 执行SQL，返回结果集
            ResultSet rs = stmt.executeQuery(strSQL);
            // 遍历结果集
            while (rs.next()) {
                // 创建学生实体
                Student student = new Student();
                // 设置实体属性
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setMyCourses(rs.getString("course"));

                // 将实体添加到学生列表
                students.add(student);
            }
            // 关闭结果集
            rs.close();
            // 关闭语句对象
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            ConnectionManager.closeConnection(conn);
        }
        // 返回学生列表
        return students;
    }



    /**
     * 按课程查询学生
     */
    @Override
    public List<Student> findByCourse(String course) {
        // 声明用户列表
        List<Student> students = new ArrayList<Student>();
        // 获取数据库连接对象
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "SELECT * FROM stuinformation WHERE course LIKE ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, course+ "%");
            // 执行SQL，返回结果集
            ResultSet rs = pstmt.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                // 创建学生实体
                Student student = new Student();
                // 设置实体属性
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setMyCourses(rs.getString("course"));
                // 将实体添加到学生列表
                students.add(student);
            }
            // 关闭结果集
            rs.close();
            // 关闭语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            ConnectionManager.closeConnection(conn);
        }
        // 返回学生列表
        return students;
    }

    @Override
    public Student findById(String id) {
        // 声明学生
        Student student = null;

        // 获取数据库连接对象
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "SELECT * FROM stumanage.stuinformation WHERE id = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, id);
            // 执行SQL查询，返回结果集
            ResultSet rs = pstmt.executeQuery();
            // 判断结果集是否有记录
            if (rs.next()) {
                // 创建学生实体
                student = new Student();
                // 设置实体属性
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setMyCourses(rs.getString("course"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        // 返回学生
        return student;
    }

    @Override
    public List<Student> findByName(String name) {
        // 声明用户列表
        List<Student> students = new ArrayList<Student>();
        // 获取数据库连接对象
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "SELECT * FROM stuinformation WHERE name LIKE ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, name + "%");
            // 执行SQL，返回结果集
            ResultSet rs = pstmt.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                // 创建学生实体
                Student student = new Student();
                // 设置实体属性
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setMyCourses(rs.getString("course"));
                // 将实体添加到学生列表
                students.add(student);
            }
            // 关闭结果集
            rs.close();
            // 关闭语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            ConnectionManager.closeConnection(conn);
        }
        // 返回学生列表
        return students;
    }



    @Override
    public Vector findRowsByMajor() {
        // 定义向量
        Vector rows = new Vector();
        // 获取数据库连接对象
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "SELECT course AS '课程', count(*) AS '人数'"
                + " FROM stuinformation GROUP BY major ORDER BY major DESC";
        try {
            // 创建语句对象
            Statement stmt = conn.createStatement();
            // 执行SQL，返回结果集
            ResultSet rs = stmt.executeQuery(strSQL);
            // 遍历结果集
            while (rs.next()) {
                Vector<String> currentRow = new Vector();
                currentRow.addElement(rs.getString("课程"));
                currentRow.addElement(rs.getInt("人数") + "");
                rows.addElement(currentRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public Vector findRowsBySex() {
        // 定义向量
        Vector rows = new Vector();
        // 获取数据库连接对象
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "SELECT sex AS '性别', count(*) AS '人数'" + " FROM stuinformation GROUP BY sex ORDER BY sex DESC";
        try {
            // 创建语句对象
            Statement stmt = conn.createStatement();
            // 执行SQL，返回结果集
            ResultSet rs = stmt.executeQuery(strSQL);
            // 遍历结果集
            while (rs.next()) {
                Vector<String> currentRow = new Vector();
                currentRow.addElement(rs.getString("性别"));
                currentRow.addElement(rs.getInt("人数") + "");
                rows.addElement(currentRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int insert(Student student) {
        // 定义插入记录数
        int count = 0;

        // 获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "INSERT INTO stuinformation (id, name, sex, course)"
                + " VALUES (?, ?, ?, ?)";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getSex());
            pstmt.setString(4, student.getMyCourses());
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
    public int update(Student student) {
        // 定义更新记录数
        int count = 0;

        // 获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 定义SQL字符串
        String strSQL = "UPDATE stuinformation SET name = ?, sex = ?,"
                + " course = ? WHERE id = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 设置占位符的值
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSex());
            pstmt.setString(3, student.getMyCourses());
            pstmt.setString(4, student.getId());
            // 执行更新操作，更新记录
            count = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        // 返回更新记录数
        return count;
    }

}
