package dao;

import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface StudentDao {

    void insertStudent(Student student) throws SQLException;
    boolean deleteStudent(int id) throws SQLException;
    boolean updateStudent(Student student) throws SQLException;
    ResultSet selectAllStudents() throws SQLException;
    ResultSet selectStudent(int id) throws SQLException;
}