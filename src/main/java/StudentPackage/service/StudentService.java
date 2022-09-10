package StudentPackage.service;

import StudentPackage.model.Student;

import java.util.List;

public interface StudentService {

     void insertStudent(Student student);
     void updatestudent(Student student);
     void deletestudent(int id);
     Student selectstudent(int id);
     List<Student> selectallstudent();
}
