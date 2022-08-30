package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class DaoDBUtil {

    private String jdbcURL = "jdbc:mysql://localhost:3306/student";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";


    private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (name, age, gender) VALUES " + " (?, ?, ?);";
    private static final String SELECT_STUDENT_BY_ID = "select id,name,age,gender from student where id =?";
    private static final String SELECT_ALL_STUDENTS = "select * from student";
    private static final String DELETE_STUDENTS_SQL = "delete from student where id = ?;";
    private static final String UPDATE_STUDENTS_SQL = "update student set name = ?,age= ?, gender =? where id = ?;";

    public DaoDBUtil() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            //Establishing a Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertStudent(Student student)throws SQLException{
        System.out.println(INSERT_STUDENT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getGender());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        System.out.println(DELETE_STUDENTS_SQL);
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            System.out.println(statement);
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENTS_SQL)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getGender());
            statement.setInt(4, student.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Student> selectAllStudents()throws SQLException{

        List<Student> student = new ArrayList<>();
        //establish connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                student.add(new Student(id, name, age, gender));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }


    public Student selectStudent(int id)throws SQLException {
        Student student = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
             preparedStatement.setInt(1, id);
             System.out.println(preparedStatement);
             // Step 3: Execute the query or update query
             ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                student = new Student(id, name, age, gender);
                System.out.println(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}

