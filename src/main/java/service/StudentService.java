package service;

import model.Student;

import java.util.List;

public interface StudentService {

     void insertStudent(Student student);
     boolean updatestudent(Student student);
     boolean deletestudent(int id);
     Student selectstudent(int id);
     List<Student> selectallstudent();
}
