package StudentPackage.dao;

import StudentPackage.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    void insertStudent(Student student) throws SQLException;
    void deleteStudent(int id) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    List<Student> selectAllStudents() throws SQLException;
    Student selectStudent(int id) throws SQLException;
}