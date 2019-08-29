package net.student.bean.service.impl;


import java.util.List;
import java.util.Vector;

import net.student.bean.bean.Student;

public interface StudentService {
    int addStudent(Student student);
    int deleteStudentById(String id);
    int deleteStudentsByCourse(String course);
    int updateStudent(Student student);
    Student findStudentById(String id);
    List<Student> findStudentsByName(String name);
    List<Student> findStudentsByCourse(String course);
    List<Student> findAllStudents();
    Vector findRowsBySex();
    Vector findRowsByMajor();
}
