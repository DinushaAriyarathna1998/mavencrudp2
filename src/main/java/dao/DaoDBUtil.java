package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class DaoDBUtil {
    private boolean rowDeleted;
    private boolean rowUpdated;
    private static Connection con = null;
    private static PreparedStatement prep = null;
    private static ResultSet rs = null;


    private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (name, age, gender) VALUES " + " (?, ?, ?);";
    private static final String SELECT_STUDENT_BY_ID = "select id,name,age,gender from student where id =?";
    private static final String SELECT_ALL_STUDENTS = "select * from student";
    private static final String DELETE_STUDENTS_SQL = "delete from student where id = ?;";
    private static final String UPDATE_STUDENTS_SQL = "update student set name = ?,age= ?, gender =? where id = ?;";

    public DaoDBUtil() {}

    public void insertStudent(Student student)throws SQLException{
        System.out.println(INSERT_STUDENT_SQL);
        // try-with-resource statement will auto close the connection.
        try {
            con = DBConnect.getConnection();
            prep = con.prepareStatement(INSERT_STUDENT_SQL);
            prep.setString(1, student.getName());
            prep.setInt(2, student.getAge());
            prep.setString(3, student.getGender());
            System.out.println(prep);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteStudent(int id) throws SQLException {

        System.out.println(DELETE_STUDENTS_SQL);
        try {
            con = DBConnect.getConnection();
            prep = con.prepareStatement(DELETE_STUDENTS_SQL);
            prep.setInt(1, id);
            rowDeleted = prep.executeUpdate() > 0;
            System.out.println(prep);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student) throws SQLException {

        try {
            con = DBConnect.getConnection();
            prep = con.prepareStatement(UPDATE_STUDENTS_SQL);
            prep.setString(1, student.getName());
            prep.setInt(2, student.getAge());
            prep.setString(3, student.getGender());
            prep.setInt(4, student.getId());
            rowUpdated = prep.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public ResultSet selectAllStudents()throws SQLException{
        //establish connection
        try { con = DBConnect.getConnection();
             // Step 2:Create a statement using connection object
            prep = con.prepareStatement(SELECT_ALL_STUDENTS);
            System.out.println(prep);
            // Step 3: Execute the query or update query
            rs = prep.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public ResultSet selectStudent(int id)throws SQLException {

        // Step 1: Establishing a Connection
        try  {con = DBConnect.getConnection();
             // Step 2:Create a statement using connection object
             prep = con.prepareStatement(SELECT_STUDENT_BY_ID);
             prep.setInt(1, id);
             System.out.println(prep);
             // Step 3: Execute the query or update query
             rs = prep.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

