package com.dinusha.dao;

import com.dinusha.model.Student;

import java.util.List;

public interface StudentDao {

    void insertStudent(Student student);

    void deleteStudent(int id);

    void updateStudent(Student student);

    List<Student> selectAllStudents();

    Student selectStudent(int id);
}