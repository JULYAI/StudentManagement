package net.student.bean.dao.impl;

import java.util.List;
import java.util.Vector;


import net.student.bean.bean.Student;

public interface StudentDao {
    int insert(Student student);
    int deleteById(String id);
    int deleteByCourse(String course);
    int update(Student student);
    Student findById(String id);
    List<Student> findByName(String name);
    List<Student> findByCourse(String course);
    List<Student> findAll();
    Vector findRowsBySex();
    Vector findRowsByMajor();
}
