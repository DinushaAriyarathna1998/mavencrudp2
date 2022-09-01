package service;

import model.Student;

import java.util.List;

public interface StudentServiceint {

     void insertstudentservice(Student student);
     boolean updatestudentservice(Student student);
     boolean deletestudentservice(int id);
     Student selectstudentservice(int id);
     List<Student> selectallstudentsservice();
}
