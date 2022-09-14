package com.dinusha.service;

import com.dinusha.model.Student;

import java.util.List;

public interface StudentService {

    void insertStudent(Student student);

    void updatestudent(Student student);

    void deletestudent(int id);

    Student selectstudent(int id);

    List<Student> selectallstudent();
}
