package net.student.bean.service.impl;

import java.util.List;
import java.util.Vector;

import net.student.bean.bean.Student;
import net.student.bean.dao.impl.StudentDao;
import net.student.bean.dao.impl.StudentDaoImpl;
import net.student.bean.service.impl.StudentService;

public class StudentServiceImpl implements StudentService{
    /**
     * 声明学生数据访问对象
     */
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public int addStudent(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public int deleteStudentById(String id) {
        return studentDao.deleteById(id);
    }

    @Override
    public int deleteStudentsByCourse(String course) {
        return studentDao.deleteByCourse(course);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }


    @Override
    public Vector findRowsByMajor() {
        return studentDao.findRowsByMajor();
    }

    @Override
    public Vector findRowsBySex() {
        return studentDao.findRowsBySex();
    }

    @Override
    public Student findStudentById(String id) {
        return studentDao.findById(id);
    }


    @Override
    public List<Student> findStudentsByCourse(String course) {
        return studentDao.findByCourse(course);
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentDao.findByName(name);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.update(student);

    }
}
