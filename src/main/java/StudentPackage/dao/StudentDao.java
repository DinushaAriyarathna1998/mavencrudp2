package StudentPackage.dao;

import StudentPackage.model.Student;


import java.util.List;

public interface StudentDao {

    void insertStudent(Student student);
    void deleteStudent(int id);
    void updateStudent(Student student);
    List selectAllStudents();
    Student selectStudent(int id);
}